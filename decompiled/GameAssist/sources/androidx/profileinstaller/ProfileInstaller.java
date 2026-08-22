package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.profileinstaller.ProfileInstaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class ProfileInstaller {

    /* renamed from: a, reason: collision with root package name */
    private static final DiagnosticsCallback f4823a = new DiagnosticsCallback() { // from class: androidx.profileinstaller.ProfileInstaller.1
        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void a(int i2, Object obj) {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void b(int i2, Object obj) {
        }
    };

    /* renamed from: b, reason: collision with root package name */
    static final DiagnosticsCallback f4824b = new DiagnosticsCallback() { // from class: androidx.profileinstaller.ProfileInstaller.2
        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void a(int i2, Object obj) {
            String str;
            switch (i2) {
                case 1:
                    str = "RESULT_INSTALL_SUCCESS";
                    break;
                case 2:
                    str = "RESULT_ALREADY_INSTALLED";
                    break;
                case 3:
                    str = "RESULT_UNSUPPORTED_ART_VERSION";
                    break;
                case 4:
                    str = "RESULT_NOT_WRITABLE";
                    break;
                case 5:
                    str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                    break;
                case 6:
                    str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                    break;
                case 7:
                    str = "RESULT_IO_EXCEPTION";
                    break;
                case 8:
                    str = "RESULT_PARSE_EXCEPTION";
                    break;
                case 9:
                default:
                    str = "";
                    break;
                case 10:
                    str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                    break;
                case 11:
                    str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                    break;
            }
            if (i2 == 6 || i2 == 7 || i2 == 8) {
                Log.e("ProfileInstaller", str, (Throwable) obj);
            } else {
                Log.d("ProfileInstaller", str);
            }
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void b(int i2, Object obj) {
            Log.d("ProfileInstaller", i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : "DIAGNOSTIC_PROFILE_IS_COMPRESSED" : "DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST" : "DIAGNOSTIC_REF_PROFILE_EXISTS" : "DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST" : "DIAGNOSTIC_CURRENT_PROFILE_EXISTS");
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface DiagnosticCode {
    }

    public interface DiagnosticsCallback {
        void a(int i2, Object obj);

        void b(int i2, Object obj);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ResultCode {
    }

    static boolean b(File file) {
        return new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
    }

    static void c(Context context, Executor executor, DiagnosticsCallback diagnosticsCallback) {
        b(context.getFilesDir());
        g(executor, diagnosticsCallback, 11, null);
    }

    static boolean d(PackageInfo packageInfo, File file, DiagnosticsCallback diagnosticsCallback) {
        File file2 = new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
        if (!file2.exists()) {
            return false;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file2));
            try {
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                boolean z = readLong == packageInfo.lastUpdateTime;
                if (z) {
                    diagnosticsCallback.a(2, null);
                }
                return z;
            } finally {
            }
        } catch (IOException unused) {
            return false;
        }
    }

    static void f(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    static void g(Executor executor, final DiagnosticsCallback diagnosticsCallback, final int i2, final Object obj) {
        executor.execute(new Runnable() { // from class: androidx.profileinstaller.c
            @Override // java.lang.Runnable
            public final void run() {
                ProfileInstaller.DiagnosticsCallback.this.a(i2, obj);
            }
        });
    }

    private static boolean h(AssetManager assetManager, String str, PackageInfo packageInfo, File file, String str2, Executor executor, DiagnosticsCallback diagnosticsCallback) {
        DeviceProfileWriter deviceProfileWriter = new DeviceProfileWriter(assetManager, executor, diagnosticsCallback, str2, "dexopt/baseline.prof", "dexopt/baseline.profm", new File(new File("/data/misc/profiles/cur/0", str), "primary.prof"));
        if (!deviceProfileWriter.e()) {
            return false;
        }
        boolean n2 = deviceProfileWriter.i().m().n();
        if (n2) {
            f(packageInfo, file);
        }
        return n2;
    }

    public static void i(Context context) {
        j(context, new b(), f4823a);
    }

    public static void j(Context context, Executor executor, DiagnosticsCallback diagnosticsCallback) {
        k(context, executor, diagnosticsCallback, false);
    }

    static void k(Context context, Executor executor, DiagnosticsCallback diagnosticsCallback, boolean z) {
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        AssetManager assets = applicationContext.getAssets();
        String name = new File(applicationInfo.sourceDir).getName();
        boolean z2 = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            File filesDir = context.getFilesDir();
            if (!z && d(packageInfo, filesDir, diagnosticsCallback)) {
                Log.d("ProfileInstaller", "Skipping profile installation for " + context.getPackageName());
                ProfileVerifier.c(context, false);
                return;
            }
            Log.d("ProfileInstaller", "Installing profile for " + context.getPackageName());
            if (h(assets, packageName, packageInfo, filesDir, name, executor, diagnosticsCallback) && z) {
                z2 = true;
            }
            ProfileVerifier.c(context, z2);
        } catch (PackageManager.NameNotFoundException e2) {
            diagnosticsCallback.a(7, e2);
            ProfileVerifier.c(context, false);
        }
    }

    static void l(Context context, Executor executor, DiagnosticsCallback diagnosticsCallback) {
        try {
            f(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
            g(executor, diagnosticsCallback, 10, null);
        } catch (PackageManager.NameNotFoundException e2) {
            g(executor, diagnosticsCallback, 7, e2);
        }
    }
}
