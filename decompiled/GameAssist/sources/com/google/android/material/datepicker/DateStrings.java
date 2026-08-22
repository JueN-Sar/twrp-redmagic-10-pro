package com.google.android.material.datepicker;

import android.content.Context;
import androidx.core.util.Pair;
import com.google.android.material.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
class DateStrings {
    static Pair a(Long l2, Long l3) {
        return b(l2, l3, null);
    }

    static Pair b(Long l2, Long l3, SimpleDateFormat simpleDateFormat) {
        if (l2 == null && l3 == null) {
            return Pair.a(null, null);
        }
        if (l2 == null) {
            return Pair.a(null, d(l3.longValue(), simpleDateFormat));
        }
        if (l3 == null) {
            return Pair.a(d(l2.longValue(), simpleDateFormat), null);
        }
        Calendar k2 = UtcDates.k();
        Calendar m2 = UtcDates.m();
        m2.setTimeInMillis(l2.longValue());
        Calendar m3 = UtcDates.m();
        m3.setTimeInMillis(l3.longValue());
        if (simpleDateFormat != null) {
            return Pair.a(simpleDateFormat.format(new Date(l2.longValue())), simpleDateFormat.format(new Date(l3.longValue())));
        }
        return m2.get(1) == m3.get(1) ? m2.get(1) == k2.get(1) ? Pair.a(g(l2.longValue(), Locale.getDefault()), g(l3.longValue(), Locale.getDefault())) : Pair.a(g(l2.longValue(), Locale.getDefault()), n(l3.longValue(), Locale.getDefault())) : Pair.a(n(l2.longValue(), Locale.getDefault()), n(l3.longValue(), Locale.getDefault()));
    }

    static String c(long j2) {
        return d(j2, null);
    }

    static String d(long j2, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j2)) : q(j2) ? f(j2) : m(j2);
    }

    static String e(Context context, long j2, boolean z, boolean z2, boolean z3) {
        String j3 = j(j2);
        if (z) {
            j3 = String.format(context.getString(R.string.mtrl_picker_today_description), j3);
        }
        return z2 ? String.format(context.getString(R.string.mtrl_picker_start_date_description), j3) : z3 ? String.format(context.getString(R.string.mtrl_picker_end_date_description), j3) : j3;
    }

    static String f(long j2) {
        return g(j2, Locale.getDefault());
    }

    static String g(long j2, Locale locale) {
        return UtcDates.b(locale).format(new Date(j2));
    }

    static String h(long j2) {
        return i(j2, Locale.getDefault());
    }

    static String i(long j2, Locale locale) {
        return UtcDates.h(locale).format(new Date(j2));
    }

    static String j(long j2) {
        return q(j2) ? h(j2) : o(j2);
    }

    static String k(Context context, int i2) {
        return UtcDates.k().get(1) == i2 ? String.format(context.getString(R.string.mtrl_picker_navigate_to_current_year_description), Integer.valueOf(i2)) : String.format(context.getString(R.string.mtrl_picker_navigate_to_year_description), Integer.valueOf(i2));
    }

    static String l(long j2) {
        return UtcDates.p(Locale.getDefault()).format(new Date(j2));
    }

    static String m(long j2) {
        return n(j2, Locale.getDefault());
    }

    static String n(long j2, Locale locale) {
        return UtcDates.o(locale).format(new Date(j2));
    }

    static String o(long j2) {
        return p(j2, Locale.getDefault());
    }

    static String p(long j2, Locale locale) {
        return UtcDates.q(locale).format(new Date(j2));
    }

    private static boolean q(long j2) {
        Calendar k2 = UtcDates.k();
        Calendar m2 = UtcDates.m();
        m2.setTimeInMillis(j2);
        return k2.get(1) == m2.get(1);
    }
}
