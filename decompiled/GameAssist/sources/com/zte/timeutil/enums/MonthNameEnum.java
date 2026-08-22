package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum MonthNameEnum {
    Jan(1, "January", "一月", "一"),
    Feb(2, "February", "二月", "二"),
    Mar(3, "March", "三月", "三"),
    Apr(4, "April", "四月", "四"),
    May(5, "May", "五月", "五"),
    Jun(6, "June", "六月", "六"),
    Jul(7, "July", "七月", "七"),
    Aug(8, "August", "八月", "八"),
    Sep(9, "September", "九月", "九"),
    Oct(10, "October", "十月", "十"),
    Nov(11, "November", "十一月", "十一"),
    Dec(12, "December", "十二月", "十二");

    private static final MonthNameEnum[] ENUMS = values();
    private int code;
    private String fullNameCn;
    private String fullNameEn;
    private String shortNameCn;

    MonthNameEnum(int i2, String str, String str2, String str3) {
        this.code = i2;
        this.fullNameEn = str;
        this.fullNameCn = str2;
        this.shortNameCn = str3;
    }

    public static MonthNameEnum d(int i2) {
        if (i2 < 1 || i2 > 12) {
            return null;
        }
        return ENUMS[i2 - 1];
    }

    public static String f(int i2) {
        MonthNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.e();
        }
        return null;
    }

    public static String j(int i2) {
        MonthNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.h();
        }
        return null;
    }

    public static String n(int i2) {
        MonthNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.l();
        }
        return null;
    }

    public static String o(int i2) {
        MonthNameEnum d2 = d(i2);
        if (d2 != null) {
            return d2.name();
        }
        return null;
    }

    public String e() {
        return this.fullNameCn;
    }

    public String h() {
        return this.fullNameEn;
    }

    public String l() {
        return this.shortNameCn;
    }
}
