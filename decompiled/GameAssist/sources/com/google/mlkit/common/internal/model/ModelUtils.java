package com.google.mlkit.common.internal.model;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@KeepForSdk
@WorkerThread
/* loaded from: classes.dex */
public class ModelUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final GmsLogger f15894a = new GmsLogger("ModelUtils", "");

    @KeepForSdk
    public static abstract class AutoMLManifest {
        public abstract String a();

        public abstract String b();

        public abstract String c();
    }

    @KeepForSdk
    public static abstract class ModelLoggingInfo {
        public abstract String a();

        public abstract long b();

        public abstract boolean c();
    }

    public static String a(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                String c2 = c(fileInputStream);
                fileInputStream.close();
                return c2;
            } finally {
            }
        } catch (IOException e2) {
            f15894a.c("ModelUtils", "Failed to create FileInputStream for model: ".concat(e2.toString()));
            return null;
        }
    }

    public static boolean b(File file, String str) {
        String a2 = a(file);
        f15894a.b("ModelUtils", "Calculated hash value is: ".concat(String.valueOf(a2)));
        return str.equals(a2);
    }

    private static String c(InputStream inputStream) {
        int i2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bArr = new byte[WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (IOException unused) {
            f15894a.c("ModelUtils", "Failed to read model file");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            f15894a.c("ModelUtils", "Do not have SHA-256 algorithm");
            return null;
        }
    }
}
