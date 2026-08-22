package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class AppOpsManagerCompat {

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static <T> T a(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        @DoNotInline
        static int b(AppOpsManager appOpsManager, String str, String str2) {
            return appOpsManager.noteProxyOp(str, str2);
        }

        @DoNotInline
        static int c(AppOpsManager appOpsManager, String str, String str2) {
            return appOpsManager.noteProxyOpNoThrow(str, str2);
        }

        @DoNotInline
        static String d(String str) {
            return AppOpsManager.permissionToOp(str);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static int a(@Nullable AppOpsManager appOpsManager, @NonNull String str, int i2, @NonNull String str2) {
            if (appOpsManager == null) {
                return 1;
            }
            return appOpsManager.checkOpNoThrow(str, i2, str2);
        }

        @NonNull
        @DoNotInline
        static String b(@NonNull Context context) {
            return context.getOpPackageName();
        }

        @Nullable
        @DoNotInline
        static AppOpsManager c(@NonNull Context context) {
            return (AppOpsManager) context.getSystemService(AppOpsManager.class);
        }
    }

    public static int a(Context context, int i2, String str, String str2) {
        AppOpsManager c2 = Api29Impl.c(context);
        int a2 = Api29Impl.a(c2, str, Binder.getCallingUid(), str2);
        return a2 != 0 ? a2 : Api29Impl.a(c2, str, i2, Api29Impl.b(context));
    }

    public static int b(Context context, String str, String str2) {
        return Api23Impl.c((AppOpsManager) Api23Impl.a(context, AppOpsManager.class), str, str2);
    }

    public static String c(String str) {
        return Api23Impl.d(str);
    }
}
