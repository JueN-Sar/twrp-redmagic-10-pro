package com.zte.mifavor.utils;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class DisplayModeManager {

    /* renamed from: b, reason: collision with root package name */
    private static Object f17422b;

    /* renamed from: a, reason: collision with root package name */
    private static HashMap f17421a = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private static boolean f17423c = false;

    private static boolean a(Context context) {
        if (f17423c) {
            return f17422b != null;
        }
        try {
            f17422b = Class.forName("com.zte.dualLcdManager.DisplayModeManager").getDeclaredMethod("getInstance", Context.class).invoke(null, context);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        f17423c = true;
        return f17422b != null;
    }

    public static int b(Context context) {
        Method declaredMethod;
        if (!a(context)) {
            return 0;
        }
        try {
            if (f17421a.containsKey("getCurrentMode")) {
                declaredMethod = (Method) f17421a.get("getCurrentMode");
            } else {
                declaredMethod = f17422b.getClass().getDeclaredMethod("getCurrentMode", null);
                f17421a.put("getCurrentMode", declaredMethod);
            }
            return ((Integer) declaredMethod.invoke(f17422b, null)).intValue();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return 0;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return 0;
        }
    }

    public static boolean c(Context context) {
        return d(context) && b(context) == 2;
    }

    public static boolean d(Context context) {
        return a(context);
    }
}
