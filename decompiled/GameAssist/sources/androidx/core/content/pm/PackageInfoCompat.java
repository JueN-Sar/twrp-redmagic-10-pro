package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class PackageInfoCompat {

    @RequiresApi
    private static class Api28Impl {
        @Nullable
        @DoNotInline
        static Signature[] a(@NonNull SigningInfo signingInfo) {
            return signingInfo.getApkContentsSigners();
        }

        @DoNotInline
        static long b(PackageInfo packageInfo) {
            return packageInfo.getLongVersionCode();
        }

        @Nullable
        @DoNotInline
        static Signature[] c(@NonNull SigningInfo signingInfo) {
            return signingInfo.getSigningCertificateHistory();
        }

        @DoNotInline
        static boolean d(@NonNull SigningInfo signingInfo) {
            return signingInfo.hasMultipleSigners();
        }

        @DoNotInline
        static boolean e(@NonNull PackageManager packageManager, @NonNull String str, @NonNull byte[] bArr, int i2) {
            return packageManager.hasSigningCertificate(str, bArr, i2);
        }
    }
}
