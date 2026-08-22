package com.zte.timeutil.calendar;

import com.zte.timeutil.calculator.DateTimeCalculatorUtil;
import com.zte.timeutil.utils.CollectionUtil;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class MonthWrapper implements Serializable {
    private static final long serialVersionUID = 6688876063027489849L;
    private List<DayWrapper> days;
    private int length;
    private int month;
    private String monthCnLong;
    private String monthCnShort;
    private String monthEnLong;
    private String monthEnShort;
    private String monthEnShortUpper;

    public MonthWrapper(int i2, List<DayWrapper> list, int i3) {
        DayWrapper dayWrapper;
        this.month = i2;
        this.days = list;
        this.length = i3;
        if (!CollectionUtil.c(list) || (dayWrapper = list.get(0)) == null) {
            return;
        }
        this.monthCnShort = DateTimeCalculatorUtil.j(dayWrapper.a());
        this.monthCnLong = DateTimeCalculatorUtil.i(dayWrapper.a());
        this.monthEnShort = DateTimeCalculatorUtil.l(dayWrapper.a());
        this.monthEnShortUpper = DateTimeCalculatorUtil.m(dayWrapper.a());
        this.monthEnLong = DateTimeCalculatorUtil.k(dayWrapper.a());
    }
}
