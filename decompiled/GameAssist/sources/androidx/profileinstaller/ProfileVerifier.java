package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.concurrent.futures.ResolvableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ProfileVerifier {

    /* renamed from: a, reason: collision with root package name */
    private static final ResolvableFuture f4827a = ResolvableFuture.r();

    /* renamed from: b, reason: collision with root package name */
    private static final Object f4828b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static CompilationStatus f4829c = null;

    @RequiresApi
    private static class Api33Impl {
        static PackageInfo a(PackageManager packageManager, Context context) {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }

    @RestrictTo
    static class Cache {

        /* renamed from: a, reason: collision with root package name */
        final int f4830a;

        /* renamed from: b, reason: collision with root package name */
        final int f4831b;

        /* renamed from: c, reason: collision with root package name */
        final long f4832c;

        /* renamed from: d, reason: collision with root package name */
        final long f4833d;

        Cache(int i2, int i3, long j2, long j3) {
            this.f4830a = i2;
            this.f4831b = i3;
            this.f4832c = j2;
            this.f4833d = j3;
        }

        static Cache a(File file) {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return cache;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        void b(File file) {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.f4830a);
                dataOutputStream.writeInt(this.f4831b);
                dataOutputStream.writeLong(this.f4832c);
                dataOutputStream.writeLong(this.f4833d);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Cache)) {
                return false;
            }
            Cache cache = (Cache) obj;
            return this.f4831b == cache.f4831b && this.f4832c == cache.f4832c && this.f4830a == cache.f4830a && this.f4833d == cache.f4833d;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.f4831b), Long.valueOf(this.f4832c), Integer.valueOf(this.f4830a), Long.valueOf(this.f4833d));
        }
    }

    public static class CompilationStatus {

        /* renamed from: a, reason: collision with root package name */
        final int f4834a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f4835b;

        /* renamed from: c, reason: collision with root package name */
        private final boolean f4836c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f4837d;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface ResultCode {
        }

        CompilationStatus(int i2, boolean z, boolean z2, boolean z3) {
            this.f4834a = i2;
            this.f4836c = z2;
            this.f4835b = z;
            this.f4837d = z3;
        }
    }

    private static long a(Context context) {
        return Api33Impl.a(context.getApplicationContext().getPackageManager(), context).lastUpdateTime;
    }

    private static CompilationStatus b(int i2, boolean z, boolean z2, boolean z3) {
        CompilationStatus compilationStatus = new CompilationStatus(i2, z, z2, z3);
        f4829c = compilationStatus;
        f4827a.o(compilationStatus);
        return f4829c;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:(3:100|101|(2:103|104))|9|(7:10|11|12|13|14|(1:16)(1:85)|17)|18|(1:84)(1:22)|23|(1:83)(1:27)|28|29|30|(2:69|70)(1:32)|33|(8:40|(1:44)|(1:51)|52|(2:59|60)|56|57|58)|(1:66)(1:(1:68))|(1:44)|(3:46|49|51)|52|(1:54)|59|60|56|57|58) */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f0, code lost:
    
        r4 = 196608;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00bc, code lost:
    
        r4 = 327680;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static androidx.profileinstaller.ProfileVerifier.CompilationStatus c(android.content.Context r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.ProfileVerifier.c(android.content.Context, boolean):androidx.profileinstaller.ProfileVerifier$CompilationStatus");
    }
}
