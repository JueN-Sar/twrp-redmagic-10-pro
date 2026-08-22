package com.zte.timeutil.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public enum LocalHolidayEnum implements Holiday {
    NEW_YEAR_DAY("元旦", "0101"),
    VALENTINE_DAY("情人节", "0214"),
    WOMEN_DAY("妇女节", "0308"),
    ARBOR_DAY("植树节", "0312"),
    WORLD_CONSUMER_RIGHTS_DAY("消费者权益日", "0315"),
    APRIL_FOOL_DAY("愚人节", "0401"),
    INTERNATIONAL_WORKERS_DAY("劳动节", "0501"),
    CHINA_YOUTH_DAY("青年节", "0504"),
    NURSES_DAY("护士节", "0512"),
    MOTHER_DAY("母亲节", "5-W-2-7"),
    CHILDREN_DAY("儿童节", "0601"),
    FATHER_DAY("父亲节", "6-W-3-7"),
    JIANDANGJIE("建党节", "0701"),
    JIANJUNJIE("建军节", "0801"),
    TEACHER_DAY("教师节", "0910"),
    GUOQINGJIE("国庆节", "1001"),
    GUOQING("国庆", "1001"),
    SHIYI("十一", "1001"),
    ALL_SAINTS_DAY("万圣节", "1101"),
    CHRISTMAS("圣诞节", "1225"),
    DEFAULT_HOLIDAY("", "");

    private final String name;
    private final String pattern;

    LocalHolidayEnum(String str, String str2) {
        this.name = str;
        this.pattern = str2;
    }

    public static Map f() {
        HashMap hashMap = new HashMap();
        for (LocalHolidayEnum localHolidayEnum : values()) {
            hashMap.put(localHolidayEnum.o(), localHolidayEnum.l());
        }
        return hashMap;
    }

    public static Map h() {
        HashMap hashMap = new HashMap();
        for (LocalHolidayEnum localHolidayEnum : values()) {
            hashMap.put(localHolidayEnum.l(), localHolidayEnum.o());
        }
        return hashMap;
    }

    private static LocalDate j(int i2, int i3, int i4, int i5) {
        return LocalDate.of(i2, i3, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.of(i5))).plusWeeks(i4 - 1);
    }

    public static LocalDate n(int i2, int i3, int i4) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate j2 = j(year, i2, i3, i4);
        return !j2.isAfter(now) ? j(year + 1, i2, i3, i4) : j2;
    }

    public String l() {
        return this.name;
    }

    public String o() {
        return this.pattern;
    }
}
