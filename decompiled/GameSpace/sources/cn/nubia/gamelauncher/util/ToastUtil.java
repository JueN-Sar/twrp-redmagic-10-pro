package cn.nubia.gamelauncher.util;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.widget.Toast;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ToastUtil {
    public static boolean isNubiaOS() {
        return "nubia".equals(SystemProperties.get("ro.build.user", "nubia"));
    }

    public static void showGamemodeToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (isNubiaOS()) {
            showHighLeverToast(str);
        } else {
            showHighLeverToastByZteOS(str);
        }
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

    private static void showHighLeverToastByZteOS(String str) {
        Context appContext = Util.getAppContext();
        try {
            Class<?> loadClass = appContext.getClassLoader().loadClass("android.widget.Toast");
            loadClass.getDeclaredMethod("showHigherLevelTipView", Context.class, CharSequence.class, Integer.TYPE).invoke(loadClass, appContext, str, 0);
        } catch (Exception e) {
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.e("ToastUtil", " showHighLeverToastByZteOS ", e);
            showNormalToast(str);
        }
    }

    public static void showNormalToast(String str) {
        Context appContext = Util.getAppContext();
        if (appContext == null) {
            return;
        }
        Toast.makeText(appContext, str, 0).show();
    }
}
