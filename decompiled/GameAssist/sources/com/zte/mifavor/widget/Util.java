package com.zte.mifavor.widget;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import java.util.GregorianCalendar;

/* loaded from: classes2.dex */
public class Util {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f17809a = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f17810b = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f17811c = {"正月", "闰正月", "二月", "闰二月", "三月", "闰三月", "四月", "闰四月", "五月", "闰五月", "六月", "闰六月", "七月", "闰七月", "八月", "闰八月", "九月", "闰九月", "十月", "闰十月", "冬月", "闰冬月", "腊月", "闰腊月"};

    /* renamed from: d, reason: collision with root package name */
    private static final String[] f17812d = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "廿十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    /* renamed from: e, reason: collision with root package name */
    private static final SparseArray f17813e = new SparseArray();

    public static int a(int i2, int i3) {
        if (i3 <= 0) {
            return i3 == 0 ? i2 : i2 == i3 ? (-i2) + 1 : i2 < (-i3) + 1 ? i2 : i2 + 1;
        }
        throw new IllegalArgumentException("convertChineseMonthToMonthSway monthLeap should be in range of [-12, 0]");
    }

    public static int b(int i2, int i3) {
        if (i3 > 0) {
            throw new IllegalArgumentException("convertChineseMonthToMonthSway monthLeap should be in range of [-12, 0]");
        }
        if (i3 == 0) {
            return i2;
        }
        int i4 = (-i3) + 1;
        return i2 == i4 ? i3 : i2 < i4 ? i2 : i2 - 1;
    }

    public static int c(int i2, int i3) {
        return b(i2, i(i3));
    }

    public static Boolean d(Context context) {
        try {
            boolean z = true;
            int i2 = Settings.System.getInt(context.getContentResolver(), "display_motion_effect", 1);
            Log.d("Z#Util", "get Dispaly Motion Effect, isDisplay = " + i2);
            if (1 != i2) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e2) {
            Log.e("Z#Util", "get Dispaly Motion Effect error, e = ", e2);
            return Boolean.TRUE;
        }
    }

    public static String[] e(int i2) {
        if (i2 == 0) {
            return (String[]) f17810b.clone();
        }
        if (i2 < -12 || i2 > 0) {
            throw new IllegalArgumentException("month should be in range of [-12, 0]");
        }
        int abs = Math.abs(i2);
        SparseArray sparseArray = f17813e;
        String[] strArr = (String[]) sparseArray.get(abs);
        if (strArr != null && strArr.length == 13) {
            return strArr;
        }
        String[] strArr2 = new String[13];
        String[] strArr3 = f17810b;
        System.arraycopy(strArr3, 0, strArr2, 0, abs);
        strArr2[abs] = "闰" + g(abs);
        System.arraycopy(strArr3, abs, strArr2, abs + 1, strArr3.length - abs);
        sparseArray.put(abs, strArr2);
        return strArr2;
    }

    public static String f(int i2) {
        if (i2 > 0 && i2 < 31) {
            return f17812d[i2 - 1];
        }
        throw new IllegalArgumentException("day should be in range of [1, 30] day is " + i2);
    }

    public static String g(int i2) {
        if (i2 > 0 && i2 < 13) {
            return f17810b[i2 - 1];
        }
        throw new IllegalArgumentException("month should be in range of [1, 12] month is " + i2);
    }

    public static String h(int i2) {
        if (i2 > 0 && i2 < 25) {
            return f17811c[i2 - 1];
        }
        throw new IllegalArgumentException("month should be in range of [1, 24] month is " + i2);
    }

    public static int i(int i2) {
        return ChineseCalendar.s(i2);
    }

    public static int j(int i2, int i3, boolean z) {
        return z ? k(i2, i3) : m(i2, i3);
    }

    public static int k(int i2, int i3) {
        return new GregorianCalendar(i2, i3, 0).get(5);
    }

    public static int l(int i2, int i3) {
        return ChineseCalendar.l(i2, i3);
    }

    public static int m(int i2, int i3) {
        return ChineseCalendar.l(i2, b(i3, ChineseCalendar.s(i2)));
    }
}
