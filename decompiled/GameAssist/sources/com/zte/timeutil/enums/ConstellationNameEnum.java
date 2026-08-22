package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum ConstellationNameEnum {
    Aries(1, "白羊座", "03-21", "04-19"),
    Taurus(2, "金牛座", "04-20", "05-20"),
    Gemini(3, "双子座", "05-21", "06-21"),
    Cancer(4, "巨蟹座", "06-22", "07-22"),
    Leo(5, "狮子座", "07-23", "08-22"),
    Virgo(6, "处女座", "08-23", "09-22"),
    Libra(7, "天秤座", "09-23", "10-23"),
    Scorpio(8, "天蝎座", "10-24", "11-22"),
    Sagittarius(9, "射手座", "11-23", "12-21"),
    Capricorn(10, "摩羯座", "12-22", "01-19"),
    Aquarius(11, "水瓶座", "01-20", "02-18"),
    Pisces(12, "双鱼座", "02-19", "03-20");

    private int code;
    private String endDate;
    private String nameCn;
    private String startDate;

    ConstellationNameEnum(int i2, String str, String str2, String str3) {
        this.code = i2;
        this.nameCn = str;
        this.startDate = str2;
        this.endDate = str3;
    }
}
