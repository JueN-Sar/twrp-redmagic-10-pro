package cn.nubia.nbgame.sdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class SharedPsHelps {

    private static class SharedPreferencesCompat {

        /* renamed from: a, reason: collision with root package name */
        private static final Method f8329a = b();

        public static void a(SharedPreferences.Editor editor) {
            try {
                Method method = f8329a;
                if (method != null) {
                    method.invoke(editor, null);
                    return;
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
            editor.commit();
        }

        private static Method b() {
            try {
                return SharedPreferences.Editor.class.getMethod("apply", null);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
    }

    public static Object a(Context context, String str, Object obj) {
        return b(context, "nbgame_chl", str, obj);
    }

    public static Object b(Context context, String str, String str2, Object obj) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        if (obj instanceof String) {
            return sharedPreferences.getString(str2, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str2, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str2, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str2, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str2, ((Long) obj).longValue()));
        }
        return null;
    }

    public static void c(Context context, String str, Object obj) {
        d(context, "nbgame_chl", str, obj);
    }

    public static void d(Context context, String str, String str2, Object obj) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        if (obj instanceof String) {
            edit.putString(str2, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str2, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str2, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str2, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str2, ((Long) obj).longValue());
        } else {
            edit.putString(str2, obj.toString());
        }
        SharedPreferencesCompat.a(edit);
    }
}
