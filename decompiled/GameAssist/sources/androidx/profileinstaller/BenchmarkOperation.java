package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.profileinstaller.ProfileInstallReceiver;
import java.io.File;

/* loaded from: classes.dex */
class BenchmarkOperation {

    @RequiresApi
    private static class Api21ContextHelper {
        static File a(Context context) {
            return context.getCodeCacheDir();
        }
    }

    @RequiresApi
    private static class Api24ContextHelper {
        static Context a(Context context) {
            return context.createDeviceProtectedStorageContext();
        }
    }

    static boolean a(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : listFiles) {
            z = a(file2) && z;
        }
        return z;
    }

    static void b(Context context, ProfileInstallReceiver.ResultDiagnostics resultDiagnostics) {
        if (a(Build.VERSION.SDK_INT >= 34 ? Api24ContextHelper.a(context).getCacheDir() : Api21ContextHelper.a(Api24ContextHelper.a(context)))) {
            resultDiagnostics.a(14, null);
        } else {
            resultDiagnostics.a(15, null);
        }
    }
}
