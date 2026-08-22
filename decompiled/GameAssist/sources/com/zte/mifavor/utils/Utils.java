package com.zte.mifavor.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class Utils {
    public static void a(Context context, int i2) {
        Log.d("Utils", "doMyOsVibrate() result : " + b(context, i2, 1, 50, 255, 0));
    }

    public static boolean b(Context context, int i2, int i3, int i4, int i5, int i6) {
        try {
            Class<?> cls = Class.forName("com.zte.richtap.ZTERichtapUtils");
            Class cls2 = Integer.TYPE;
            Method method = cls.getMethod("richtapVibrate", Context.class, cls2, cls2, cls2, cls2, cls2);
            method.setAccessible(true);
            return ((Boolean) method.invoke(cls, context, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6))).booleanValue();
        } catch (ClassNotFoundException e2) {
            Log.w("Utils", "doVibrate() but ClassNotFoundException " + e2.getMessage());
            return false;
        } catch (IllegalAccessException e3) {
            Log.w("Utils", "doVibrate() but IllegalAccessException " + e3.getMessage());
            return false;
        } catch (NoSuchMethodException e4) {
            Log.w("Utils", "doVibrate() but NoSuchMethodException " + e4.getMessage());
            return false;
        } catch (InvocationTargetException e5) {
            Log.w("Utils", "doVibrate() but InvocationTargetException " + e5.getMessage());
            return false;
        } catch (Exception e6) {
            Log.w("Utils", "doVibrate() but Exception " + e6.getMessage());
            return false;
        }
    }

    public static int c(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int d(Context context) {
        new DisplayMetrics();
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int e(Context context) {
        new DisplayMetrics();
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int f(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
