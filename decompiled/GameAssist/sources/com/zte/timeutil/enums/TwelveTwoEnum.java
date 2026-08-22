package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum TwelveTwoEnum {
    ZISHI(1, "子时", "23:00:00", "01:00:00"),
    CHOUSHI(2, "丑时", "01:00:00", "03:00:00"),
    YINSHI(3, "寅时", "03:00:00", "05:00:00"),
    MAOSHI(4, "卯辰", "05:00:00", "07:00:00"),
    CHENSHI(5, "辰时", "07:00:00", "09:00:00"),
    SISHI(6, "巳时", "09:00:00", "11:00:00"),
    WUSHI(7, "午时", "11:00:00", "13:00:00"),
    WEISHI(8, "未时", "13:00:00", "15:00:00"),
    SHENSHI(9, "申时", "15:00:00", "17:00:00"),
    YOUSHI(10, "酉时", "17:00:00", "19:00:00"),
    XUSHI(11, "戌时", "19:00:00", "21:00:00"),
    HAISHI(12, "亥时", "21:00:00", "23:00:00");

    private int code;
    private String endTime;
    private String nameCn;
    private String startTime;

    TwelveTwoEnum(int i2, String str, String str2, String str3) {
        this.code = i2;
        this.nameCn = str;
        this.startTime = str2;
        this.endTime = str3;
    }
}
