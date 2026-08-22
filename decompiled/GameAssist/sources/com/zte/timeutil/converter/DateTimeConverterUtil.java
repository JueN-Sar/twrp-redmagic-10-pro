package com.zte.timeutil.converter;

import com.zte.timeutil.LunarDate;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

/* loaded from: classes2.dex */
public class DateTimeConverterUtil {
    /* JADX WARN: Type inference failed for: r1v2, types: [java.time.ZonedDateTime] */
    public static Date a(LocalDate localDate) {
        Objects.requireNonNull(localDate, "localDate");
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.time.ZonedDateTime] */
    public static Date b(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate c(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.toLocalDate();
    }

    public static LocalDate d(ZonedDateTime zonedDateTime) {
        Objects.requireNonNull(zonedDateTime, "zonedDateTime");
        return zonedDateTime.toLocalDate();
    }

    public static LocalDate e(TemporalAccessor temporalAccessor) {
        return d(o(temporalAccessor));
    }

    public static LocalDate f(Date date) {
        return j(date).toLocalDate();
    }

    public static LocalDateTime g(LocalDate localDate) {
        Objects.requireNonNull(localDate, "localDate");
        return localDate.atStartOfDay();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.time.LocalDateTime] */
    public static LocalDateTime h(ZonedDateTime zonedDateTime) {
        Objects.requireNonNull(zonedDateTime, "zonedDateTime");
        return zonedDateTime.toLocalDateTime();
    }

    public static LocalDateTime i(TemporalAccessor temporalAccessor) {
        return h(o(temporalAccessor));
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.time.LocalDateTime] */
    public static LocalDateTime j(Date date) {
        Objects.requireNonNull(date, "date");
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.time.ZonedDateTime] */
    public static ZonedDateTime k(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).atZone(ZoneId.systemDefault());
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.time.ZonedDateTime] */
    public static ZonedDateTime l(LocalDate localDate) {
        Objects.requireNonNull(localDate, "localDate");
        return localDate.atStartOfDay().atZone(ZoneId.systemDefault());
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.time.ZonedDateTime] */
    public static ZonedDateTime m(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.atZone(ZoneId.systemDefault());
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.time.ZonedDateTime] */
    public static ZonedDateTime n(LocalTime localTime) {
        Objects.requireNonNull(localTime, "localTime");
        return LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault());
    }

    public static ZonedDateTime o(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        return temporalAccessor instanceof Instant ? k((Instant) temporalAccessor) : temporalAccessor instanceof LocalDate ? l((LocalDate) temporalAccessor) : temporalAccessor instanceof LocalDateTime ? m((LocalDateTime) temporalAccessor) : temporalAccessor instanceof LocalTime ? n((LocalTime) temporalAccessor) : temporalAccessor instanceof ZonedDateTime ? (ZonedDateTime) temporalAccessor : temporalAccessor instanceof YearMonth ? o((YearMonth) temporalAccessor) : temporalAccessor instanceof OffsetDateTime ? ((OffsetDateTime) temporalAccessor).toZonedDateTime() : temporalAccessor instanceof OffsetTime ? ((OffsetTime) temporalAccessor).atDate(LocalDate.now()).toZonedDateTime() : temporalAccessor instanceof LunarDate ? l(((LunarDate) temporalAccessor).o()) : ZonedDateTime.from(temporalAccessor);
    }

    public static ZonedDateTime p(Date date) {
        Objects.requireNonNull(date, "date");
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault());
    }
}
