package cn.nubia.tgk.util;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.widget.Toast;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ToastUtil {
    public static boolean isNubiaOS() {
        return "nubia".equals(SystemProperties.get("ro.build.user", "nubia"));
    }

    private static void showHighLeverToast(String str) {
        try {
            Method declaredMethod = Class.forName("nubia.os.ApplicationManager$Trigger").getDeclaredMethod("showGamemodeCenterToast", String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    private static void showHighLeverToastByZteOS(Context context, String str) {
        showNormalToast(str, context);
    }

    public static void showNormalToast(String str, Context context) {
        Toast.makeText(context, str, 0).show();
    }

    public static void showTgkToast(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (isNubiaOS()) {
            showHighLeverToast(str);
        } else {
            showHighLeverToastByZteOS(context, str);
        }
    }
}
