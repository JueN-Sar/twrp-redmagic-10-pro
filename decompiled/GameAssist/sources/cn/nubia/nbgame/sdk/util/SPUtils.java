package cn.nubia.nbgame.sdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ApplySharedPref"})
/* loaded from: classes.dex */
public final class SPUtils {

    /* renamed from: b, reason: collision with root package name */
    private static final Map f8327b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f8328a;

    private SPUtils(Context context, String str, int i2) {
        this.f8328a = context.getSharedPreferences(str, i2);
    }

    public static SPUtils c(Context context) {
        return d(context, "", 0);
    }

    public static SPUtils d(Context context, String str, int i2) {
        if (e(str)) {
            str = "spUtils";
        }
        Map map = f8327b;
        SPUtils sPUtils = (SPUtils) map.get(str);
        if (sPUtils == null) {
            synchronized (SPUtils.class) {
                try {
                    sPUtils = (SPUtils) map.get(str);
                    if (sPUtils == null) {
                        sPUtils = new SPUtils(context, str, i2);
                        map.put(str, sPUtils);
                    }
                } finally {
                }
            }
        }
        return sPUtils;
    }

    private static boolean e(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public boolean a(String str) {
        return b(str, false);
    }

    public boolean b(String str, boolean z) {
        return this.f8328a.getBoolean(str, z);
    }

    public void f(String str, boolean z) {
        g(str, z, false);
    }

    public void g(String str, boolean z, boolean z2) {
        if (z2) {
            this.f8328a.edit().putBoolean(str, z).commit();
        } else {
            this.f8328a.edit().putBoolean(str, z).apply();
        }
    }
}
