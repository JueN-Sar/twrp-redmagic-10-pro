package com.zte.timeutil.calculator;

import com.zte.timeutil.enums.MonthNameEnum;
import com.zte.timeutil.enums.WeekNameEnum;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

/* loaded from: classes2.dex */
public class DateTimeCalculatorUtil {
    public static long a(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
        return Duration.between(localDateTime, localDateTime2).toDays();
    }

    public static int b(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.getDayOfWeek().getValue();
    }

    public static String c(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return WeekNameEnum.j(b(localDateTime));
    }

    public static String d(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return WeekNameEnum.j(b(localDateTime)).substring(2);
    }

    public static String e(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return WeekNameEnum.f(b(localDateTime));
    }

    public static String f(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return WeekNameEnum.l(b(localDateTime));
    }

    public static String g(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return WeekNameEnum.l(b(localDateTime)).toUpperCase();
    }

    public static int h(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.getMonthValue();
    }

    public static String i(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return MonthNameEnum.f(h(localDateTime));
    }

    public static String j(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return MonthNameEnum.n(h(localDateTime));
    }

    public static String k(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return MonthNameEnum.j(h(localDateTime));
    }

    public static String l(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return MonthNameEnum.o(h(localDateTime));
    }

    public static String m(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return MonthNameEnum.o(h(localDateTime)).toUpperCase();
    }

    public static boolean n(int i2) {
        return i2 % 4 == 0 && (i2 % 100 != 0 || i2 % 400 == 0);
    }

    public static boolean o(LocalDateTime localDateTime) {
        int b2 = b(localDateTime);
        return (b2 == 6 || b2 == 7) ? false : true;
    }

    public static Temporal p(Temporal temporal, TemporalUnit temporalUnit, long j2) {
        Objects.requireNonNull(temporal, "temporal");
        return temporal.plus(j2, temporalUnit);
    }

    public static LocalDateTime q(LocalDateTime localDateTime, long j2) {
        return (LocalDateTime) p(localDateTime, ChronoUnit.MILLIS, j2);
    }

    public static Temporal r(Temporal temporal, TemporalField temporalField, long j2) {
        Objects.requireNonNull(temporal, "temporal");
        return temporal.with(temporalField, j2);
    }

    public static LocalDateTime s(LocalDateTime localDateTime, long j2) {
        return (LocalDateTime) r(localDateTime, ChronoField.DAY_OF_WEEK, j2);
    }
}
