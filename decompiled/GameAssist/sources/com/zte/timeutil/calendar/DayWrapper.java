package com.zte.timeutil.calendar;

import com.zte.timeutil.LunarDate;
import com.zte.timeutil.calculator.DateTimeCalculatorUtil;
import com.zte.timeutil.converter.DateTimeConverterUtil;
import com.zte.timeutil.formatter.DateTimeFormatterUtil;
import com.zte.timeutil.holiday.Holiday;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/* loaded from: classes2.dex */
public class DayWrapper implements Serializable {
    private static final long serialVersionUID = 5710793952115910594L;
    private String chineseHoliday;
    private Date date;
    private String dateStr;
    private int dateType;
    private int day;
    private LocalDateTime localDateTime;
    private String localHoliday;
    private LunarDate lunarDate;
    private String lunarDateStr;
    private String lunarDay;
    private Object obj;
    private String solarTerm;
    private int week;
    private String weekCnLong;
    private String weekCnShort;
    private String weekEnLong;
    private String weekEnShort;
    private String weekEnShortUpper;

    public DayWrapper(LocalDateTime localDateTime) {
        this(localDateTime, false);
    }

    public LocalDateTime a() {
        return this.localDateTime;
    }

    public DayWrapper(LocalDateTime localDateTime, boolean z) {
        this(localDateTime, z, false, null, null);
    }

    public DayWrapper(LocalDateTime localDateTime, boolean z, boolean z2, Map<String, String> map, Map<String, String> map2) {
        this(localDateTime, null, z, z2, map, map2);
    }

    public DayWrapper(LocalDateTime localDateTime, Object obj, boolean z, boolean z2, Map<String, String> map, Map<String, String> map2) {
        this.localDateTime = localDateTime;
        this.date = DateTimeConverterUtil.b(localDateTime);
        this.dateStr = DateTimeFormatterUtil.h(localDateTime);
        this.day = localDateTime.getDayOfMonth();
        this.week = localDateTime.getDayOfWeek().getValue();
        this.weekCnShort = DateTimeCalculatorUtil.d(localDateTime);
        this.weekCnLong = DateTimeCalculatorUtil.c(localDateTime);
        this.weekEnShort = DateTimeCalculatorUtil.f(localDateTime);
        this.weekEnShortUpper = DateTimeCalculatorUtil.g(localDateTime);
        this.weekEnLong = DateTimeCalculatorUtil.e(localDateTime);
        this.obj = obj;
        if (z) {
            LunarDate h2 = LunarDate.h(localDateTime);
            this.lunarDate = h2;
            this.lunarDateStr = h2.r();
            this.lunarDay = this.lunarDate.s();
            this.solarTerm = this.lunarDate.p();
        }
        if (z2) {
            this.localHoliday = Holiday.d(localDateTime, map);
            if (z) {
                this.chineseHoliday = Holiday.c(localDateTime, map2);
            }
        }
        this.dateType = DateTimeCalculatorUtil.o(localDateTime) ? 1 : 0;
    }
}
