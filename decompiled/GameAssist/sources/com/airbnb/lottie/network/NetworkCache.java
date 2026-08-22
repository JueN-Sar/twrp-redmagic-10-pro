package com.airbnb.lottie.network;

import android.util.Pair;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestrictTo
/* loaded from: classes.dex */
public class NetworkCache {

    /* renamed from: a, reason: collision with root package name */
    private final LottieNetworkCacheProvider f9787a;

    public NetworkCache(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        this.f9787a = lottieNetworkCacheProvider;
    }

    private static String b(String str, FileExtension fileExtension, boolean z) {
        String d2 = z ? fileExtension.d() : fileExtension.extension;
        String replaceAll = str.replaceAll("\\W+", "");
        int length = 242 - d2.length();
        if (replaceAll.length() > length) {
            replaceAll = d(replaceAll, length);
        }
        return "lottie_cache_" + replaceAll + d2;
    }

    private File c(String str) {
        File file = new File(e(), b(str, FileExtension.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(e(), b(str, FileExtension.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        File file3 = new File(e(), b(str, FileExtension.GZIP, false));
        if (file3.exists()) {
            return file3;
        }
        return null;
    }

    private static String d(String str, int i2) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                sb.append(String.format("%02x", Byte.valueOf(b2)));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return str.substring(0, i2);
        }
    }

    private File e() {
        File a2 = this.f9787a.a();
        if (a2.isFile()) {
            a2.delete();
        }
        if (!a2.exists()) {
            a2.mkdirs();
        }
        return a2;
    }

    Pair a(String str) {
        try {
            File c2 = c(str);
            if (c2 == null) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(c2);
            FileExtension fileExtension = c2.getAbsolutePath().endsWith(".zip") ? FileExtension.ZIP : c2.getAbsolutePath().endsWith(".gz") ? FileExtension.GZIP : FileExtension.JSON;
            Logger.a("Cache hit for " + str + " at " + c2.getAbsolutePath());
            return new Pair(fileExtension, fileInputStream);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    void f(String str, FileExtension fileExtension) {
        File file = new File(e(), b(str, fileExtension, true));
        File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean renameTo = file.renameTo(file2);
        Logger.a("Copying temp file to real file (" + file2 + ")");
        if (renameTo) {
            return;
        }
        Logger.c("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + ".");
    }

    File g(String str, InputStream inputStream, FileExtension fileExtension) {
        File file = new File(e(), b(str, fileExtension, true));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return file;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } finally {
            inputStream.close();
        }
    }
}
