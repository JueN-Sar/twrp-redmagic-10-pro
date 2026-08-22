package com.google.android.material.datepicker;

import android.content.res.Resources;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import com.google.android.material.R;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
class UtcDates {

    /* renamed from: a, reason: collision with root package name */
    static AtomicReference f14540a = new AtomicReference();

    static long a(long j2) {
        Calendar m2 = m();
        m2.setTimeInMillis(j2);
        return e(m2).getTimeInMillis();
    }

    static DateFormat b(Locale locale) {
        return c("MMMd", locale);
    }

    private static DateFormat c(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(l());
        instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        return instanceForSkeleton;
    }

    static String d(String str) {
        return str.replaceAll("[^dMy/\\-.]", "").replaceAll("d{1,2}", "dd").replaceAll("M{1,2}", "MM").replaceAll("y{1,4}", "yyyy").replaceAll("\\.$", "").replaceAll("My", "M/y");
    }

    static Calendar e(Calendar calendar) {
        Calendar n2 = n(calendar);
        Calendar m2 = m();
        m2.set(n2.get(1), n2.get(2), n2.get(5));
        return m2;
    }

    static SimpleDateFormat f() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(d(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toPattern()), Locale.getDefault());
        simpleDateFormat.setTimeZone(j());
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    static String g(Resources resources, SimpleDateFormat simpleDateFormat) {
        String pattern = simpleDateFormat.toPattern();
        String string = resources.getString(R.string.mtrl_picker_text_input_year_abbr);
        String string2 = resources.getString(R.string.mtrl_picker_text_input_month_abbr);
        String string3 = resources.getString(R.string.mtrl_picker_text_input_day_abbr);
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage())) {
            pattern = pattern.replaceAll("d+", DistBusKeys.KEY_WIFI_DBDC).replaceAll("M+", "M").replaceAll("y+", "y");
        }
        return pattern.replace(DistBusKeys.KEY_WIFI_DBDC, string3).replace("M", string2).replace("y", string);
    }

    static DateFormat h(Locale locale) {
        return c("MMMMEEEEd", locale);
    }

    static TimeSource i() {
        TimeSource timeSource = (TimeSource) f14540a.get();
        return timeSource == null ? TimeSource.c() : timeSource;
    }

    private static TimeZone j() {
        return TimeZone.getTimeZone("UTC");
    }

    static Calendar k() {
        Calendar a2 = i().a();
        a2.set(11, 0);
        a2.set(12, 0);
        a2.set(13, 0);
        a2.set(14, 0);
        a2.setTimeZone(j());
        return a2;
    }

    private static android.icu.util.TimeZone l() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    static Calendar m() {
        return n(null);
    }

    static Calendar n(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(j());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    static DateFormat o(Locale locale) {
        return c("yMMMd", locale);
    }

    static DateFormat p(Locale locale) {
        return c("yMMMM", locale);
    }

    static DateFormat q(Locale locale) {
        return c("yMMMMEEEEd", locale);
    }
}
