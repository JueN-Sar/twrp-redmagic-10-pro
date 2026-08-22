package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum CommonTimeEnum {
    TODAY("today", "今天"),
    TOMORROW("tomorrow", "明天"),
    NEXTWEEK("nextWeek", "下周"),
    NEXTMONTH("nextMonth", "下月"),
    NEXTYEAR("nextYear", "明年"),
    YESTERDAY("yesterday", "昨天"),
    LASTWEEK("lastWeek", "上周"),
    LASTMONTH("lastMonth", "上月"),
    LASTYEAR("lastYear", "去年");

    private String code;
    private String name;

    CommonTimeEnum(String str, String str2) {
        this.code = str;
        this.name = str2;
    }
}
