package org.telegram.messenger;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import org.telegram.ui.LaunchActivity;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class YandexBrowserDownloadReceiver extends BroadcastReceiver {
    private static final String PACKAGE_NAME = "com.yandex.browser";
    private static final long VERSION_CODE = 1927119778L;
    private static final String APK_SHA256 = "f95543e3f4b2438f1c9eeae4aa15887d9ae53ce334ee1fc84bf19c6bae57c282";
    private static final String CERTIFICATE_SHA256 = "aca405ded8b25cb2e8c6da69425d2b4307d087c1276fc06ad5942731ccc51dba";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
            Uri data = intent.getData();
            if (data != null && PACKAGE_NAME.equals(data.getSchemeSpecificPart())) {
                SharedConfig.setFlintGramUseYandexBrowser(true);
            }
            return;
        }
        if (!DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            return;
        }
        long expectedId = context.getSharedPreferences("mainconfig", Context.MODE_PRIVATE)
                .getLong("flintGramYandexBrowserDownloadId", -1L);
        long completedId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
        if (expectedId < 0 || completedId != expectedId) {
            return;
        }

        PendingResult pendingResult = goAsync();
        Utilities.globalQueue.postRunnable(() -> {
            try {
                File apk = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "YandexBrowser-26.6.6.58.apk");
                if (!isExpectedApk(context, apk)) {
                    AndroidUtilities.runOnUIThread(() -> Toast.makeText(
                            context,
                            LocaleController.getString(R.string.FlintGramYandexBrowserDownloadInvalid),
                            Toast.LENGTH_LONG
                    ).show());
                    return;
                }
                AndroidUtilities.runOnUIThread(() -> openInstaller(context, apk));
            } catch (Exception e) {
                FileLog.e(e);
            } finally {
                pendingResult.finish();
            }
        });
    }

    private static boolean isExpectedApk(Context context, File apk) throws Exception {
        if (!apk.isFile() || !APK_SHA256.equals(sha256(apk))) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apk.getAbsolutePath(), PackageManager.GET_SIGNING_CERTIFICATES);
        if (packageInfo == null || !PACKAGE_NAME.equals(packageInfo.packageName)) {
            return false;
        }
        long versionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
        if (versionCode != VERSION_CODE || packageInfo.signingInfo == null) {
            return false;
        }
        android.content.pm.Signature[] signatures = packageInfo.signingInfo.getApkContentsSigners();
        return signatures.length == 1 && CERTIFICATE_SHA256.equals(sha256(signatures[0].toByteArray()));
    }

    private static void openInstaller(Context context, File apk) {
        Activity activity = LaunchActivity.instance;
        if (activity != null) {
            AndroidUtilities.openForView(apk, apk.getName(), "application/vnd.android.package-archive", activity, null, false);
            return;
        }
        try {
            Uri uri = FileProvider.getUriForFile(context, ApplicationLoader.getApplicationId() + ".provider", apk);
            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(installIntent);
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    private static String sha256(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[1024 * 1024];
        try (FileInputStream input = new FileInputStream(file)) {
            int read;
            while ((read = input.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        }
        return toHex(digest.digest());
    }

    private static String sha256(byte[] bytes) throws Exception {
        return toHex(MessageDigest.getInstance("SHA-256").digest(bytes));
    }

    private static String toHex(byte[] bytes) {
        StringBuilder result = new StringBuilder(bytes.length * 2);
        for (byte value : bytes) {
            result.append(String.format(java.util.Locale.US, "%02x", value & 0xff));
        }
        return result.toString();
    }
}
