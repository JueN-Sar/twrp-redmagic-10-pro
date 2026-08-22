package com.zte.timeutil.utils;

import com.zte.timeutil.constants.Constant;
import java.util.HashMap;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class StringUtil {

    /* renamed from: com.zte.timeutil.utils.StringUtil$1, reason: invalid class name */
    class AnonymousClass1 implements Supplier<Object> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f18199a;

        @Override // java.util.function.Supplier
        public Object get() {
            HashMap hashMap = new HashMap();
            for (String str : this.f18199a.replace(" ", "").split(",")) {
                String[] split = str.split(":");
                hashMap.put(split[0], Integer.valueOf(split[1]));
            }
            return hashMap;
        }
    }

    public static int a(String str, String str2) {
        if (str == null) {
            return 0;
        }
        return str.length() - str.replace(str2, "").length();
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return Constant.f18120c.matcher(str).find();
    }

    public static boolean c(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean d(String str) {
        return !c(str);
    }

    public static boolean e(String str) {
        if (str == null) {
            return false;
        }
        return Constant.f18118a.matcher(str).matches();
    }

    public static boolean f(String str) {
        if (str == null) {
            return false;
        }
        return Constant.f18119b.matcher(str).matches();
    }
}
