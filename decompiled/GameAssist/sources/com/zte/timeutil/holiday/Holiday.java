package com.zte.timeutil.holiday;

import com.zte.timeutil.LunarDate;
import com.zte.timeutil.formatter.DateTimeFormatterUtil;
import com.zte.timeutil.utils.CollectionUtil;
import com.zte.timeutil.utils.StringUtil;
import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public interface Holiday {
    static String c(Temporal temporal, Map map) {
        Objects.requireNonNull(temporal, "temporal");
        StringBuilder sb = new StringBuilder("");
        if (CollectionUtil.b(map)) {
            map = ChineseHolidayEnum.f();
        }
        LunarDate i2 = LunarDate.i(temporal);
        if (StringUtil.d(i2.n())) {
            return sb.toString();
        }
        String f2 = i2.f();
        for (Map.Entry entry : map.entrySet()) {
            if (((String) entry.getKey()).equals(f2)) {
                if (sb.length() == 0) {
                    sb = new StringBuilder((String) entry.getValue());
                } else {
                    sb.append(" " + ((String) entry.getValue()));
                }
            }
            if (((String) entry.getKey()).equals("CHUXI") && "0101".equals(LunarDate.g(i2.o().plus(1L, (TemporalUnit) ChronoUnit.DAYS)).f())) {
                if (sb.length() == 0) {
                    sb = new StringBuilder((String) entry.getValue());
                } else {
                    sb.append(" " + ((String) entry.getValue()));
                }
            }
        }
        return sb.toString();
    }

    static String d(Temporal temporal, Map map) {
        Objects.requireNonNull(temporal, "temporal");
        StringBuilder sb = new StringBuilder("");
        if (CollectionUtil.b(map)) {
            map = LocalHolidayEnum.f();
        }
        String format = MonthDay.from(temporal).format(DateTimeFormatterUtil.z);
        for (Map.Entry entry : map.entrySet()) {
            if (((String) entry.getKey()).equals(format)) {
                if (sb.length() == 0) {
                    sb = new StringBuilder((String) entry.getValue());
                } else {
                    sb.append(" " + ((String) entry.getValue()));
                }
            }
            if (((String) entry.getKey()).contains("W")) {
                String[] split = ((String) entry.getKey()).split("-");
                if (format.equals(MonthDay.from(temporal.with(ChronoField.MONTH_OF_YEAR, Integer.parseInt(split[0])).with(TemporalAdjusters.dayOfWeekInMonth(Integer.parseInt(split[2]), DayOfWeek.of(Integer.parseInt(split[3]))))).format(DateTimeFormatterUtil.z))) {
                    if (sb.length() == 0) {
                        sb = new StringBuilder((String) entry.getValue());
                    } else {
                        sb.append(" " + ((String) entry.getValue()));
                    }
                }
            }
        }
        return sb.toString();
    }
}
