package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes.dex */
class TimeSource {

    /* renamed from: c, reason: collision with root package name */
    private static final TimeSource f14537c = new TimeSource(null, null);

    /* renamed from: a, reason: collision with root package name */
    private final Long f14538a;

    /* renamed from: b, reason: collision with root package name */
    private final TimeZone f14539b;

    private TimeSource(Long l2, TimeZone timeZone) {
        this.f14538a = l2;
        this.f14539b = timeZone;
    }

    static TimeSource c() {
        return f14537c;
    }

    Calendar a() {
        return b(this.f14539b);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l2 = this.f14538a;
        if (l2 != null) {
            calendar.setTimeInMillis(l2.longValue());
        }
        return calendar;
    }
}
