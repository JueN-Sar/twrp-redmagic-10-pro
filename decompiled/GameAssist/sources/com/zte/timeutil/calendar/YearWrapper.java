package com.zte.timeutil.calendar;

import com.zte.timeutil.calculator.DateTimeCalculatorUtil;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class YearWrapper implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean isLeapYear;
    private int length;
    private List<MonthWrapper> months;
    private int year;

    public YearWrapper(int i2, List<MonthWrapper> list) {
        this.year = i2;
        this.months = list;
        boolean n2 = DateTimeCalculatorUtil.n(i2);
        this.isLeapYear = n2;
        if (n2) {
            this.length = 366;
        } else {
            this.length = 365;
        }
    }
}
