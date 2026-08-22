package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum WeekNameEnum {
    Mon(1, "Monday", "星期一"),
    Tue(2, "Tuesday", "星期二"),
    Wed(3, "Wednesday", "星期三"),
    Thu(4, "Thursday", "星期四"),
    Fri(5, "Friday", "星期五"),
    Sat(6, "Saturday", "星期六"),
    Sun(7, "Sunday", "星期日");

    private static final WeekNameEnum[] ENUMS = values();
    private int code;
    private String fullNameEn;
    private String nameCn;

    WeekNameEnum(int i2, String str, String str2) {
        this.code = i2;
        this.fullNameEn = str;
        this.nameCn = str2;
    }

    public static WeekNameEnum d(int i2) {
        if (i2 < 1 || i2 > 7) {
            return null;
        }
        return ENUMS[i2 - 1];
    }

    public static String f(int i2) {
        WeekNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.e();
        }
        return null;
    }

    public static String j(int i2) {
        WeekNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.h();
        }
        return null;
    }

    public static String l(int i2) {
        WeekNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.name();
        }
        return null;
    }

    public String e() {
        return this.fullNameEn;
    }

    public String h() {
        return this.nameCn;
    }
}
