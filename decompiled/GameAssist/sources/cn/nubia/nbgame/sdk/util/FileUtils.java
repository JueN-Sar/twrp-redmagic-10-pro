package cn.nubia.nbgame.sdk.util;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

/* loaded from: classes.dex */
public class FileUtils {
    public static long a(File file) {
        if (file == null) {
            return -1L;
        }
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockSizeLong() * (statFs.getAvailableBlocksLong() - 4);
    }

    public static File b(String str) {
        if (str == null) {
            return null;
        }
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        if (str.startsWith(downloadCacheDirectory.getPath())) {
            return downloadCacheDirectory;
        }
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (str.startsWith(externalStorageDirectory.getPath())) {
                return externalStorageDirectory;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        throw new IllegalArgumentException("Cannot determine filesystem root for " + str);
    }
}
