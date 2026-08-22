package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
class MonthAdapter extends BaseAdapter {

    /* renamed from: m, reason: collision with root package name */
    static final int f14498m = UtcDates.m().getMaximum(4);

    /* renamed from: n, reason: collision with root package name */
    private static final int f14499n = (UtcDates.m().getMaximum(5) + UtcDates.m().getMaximum(7)) - 1;

    /* renamed from: c, reason: collision with root package name */
    final Month f14500c;

    /* renamed from: h, reason: collision with root package name */
    final DateSelector f14501h;

    /* renamed from: i, reason: collision with root package name */
    private Collection f14502i;

    /* renamed from: j, reason: collision with root package name */
    CalendarStyle f14503j;

    /* renamed from: k, reason: collision with root package name */
    final CalendarConstraints f14504k;

    /* renamed from: l, reason: collision with root package name */
    final DayViewDecorator f14505l;

    MonthAdapter(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.f14500c = month;
        this.f14501h = dateSelector;
        this.f14504k = calendarConstraints;
        this.f14505l = dayViewDecorator;
        this.f14502i = dateSelector.E();
    }

    private String c(Context context, long j2) {
        return DateStrings.e(context, j2, j(j2), isStartOfRange(j2), isEndOfRange(j2));
    }

    private void f(Context context) {
        if (this.f14503j == null) {
            this.f14503j = new CalendarStyle(context);
        }
    }

    private boolean i(long j2) {
        Iterator it = this.f14501h.E().iterator();
        while (it.hasNext()) {
            if (UtcDates.a(j2) == UtcDates.a(((Long) it.next()).longValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean j(long j2) {
        return UtcDates.k().getTimeInMillis() == j2;
    }

    private void m(TextView textView, long j2, int i2) {
        boolean z;
        CalendarItemStyle calendarItemStyle;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        String c2 = c(context, j2);
        textView.setContentDescription(c2);
        boolean j3 = this.f14504k.k().j(j2);
        if (j3) {
            textView.setEnabled(true);
            boolean i3 = i(j2);
            textView.setSelected(i3);
            calendarItemStyle = i3 ? this.f14503j.f14437b : j(j2) ? this.f14503j.f14438c : this.f14503j.f14436a;
            z = i3;
        } else {
            textView.setEnabled(false);
            z = false;
            calendarItemStyle = this.f14503j.f14442g;
        }
        DayViewDecorator dayViewDecorator = this.f14505l;
        if (dayViewDecorator == null || i2 == -1) {
            calendarItemStyle.d(textView);
            return;
        }
        Month month = this.f14500c;
        int i4 = month.f14493i;
        int i5 = month.f14492h;
        ColorStateList a2 = dayViewDecorator.a(context, i4, i5, i2, j3, z);
        boolean z2 = z;
        calendarItemStyle.e(textView, a2, this.f14505l.k(context, i4, i5, i2, j3, z2));
        Drawable d2 = this.f14505l.d(context, i4, i5, i2, j3, z2);
        Drawable g2 = this.f14505l.g(context, i4, i5, i2, j3, z2);
        Drawable f2 = this.f14505l.f(context, i4, i5, i2, j3, z2);
        boolean z3 = z;
        textView.setCompoundDrawables(d2, g2, f2, this.f14505l.b(context, i4, i5, i2, j3, z3));
        textView.setContentDescription(this.f14505l.i(context, i4, i5, i2, j3, z3, c2));
    }

    private void n(MaterialCalendarGridView materialCalendarGridView, long j2) {
        if (Month.l(j2).equals(this.f14500c)) {
            int u = this.f14500c.u(j2);
            m((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().a(u) - materialCalendarGridView.getFirstVisiblePosition()), j2, u);
        }
    }

    int a(int i2) {
        return b() + (i2 - 1);
    }

    int b() {
        return this.f14500c.o(this.f14504k.m());
    }

    @Override // android.widget.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i2) {
        if (i2 < b() || i2 > k()) {
            return null;
        }
        return Long.valueOf(this.f14500c.r(l(i2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0064  */
    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.f(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.b()
            int r7 = r6 - r7
            if (r7 < 0) goto L54
            com.google.android.material.datepicker.Month r8 = r5.f14500c
            int r2 = r8.f14495k
            if (r7 < r2) goto L2d
            goto L54
        L2d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L5d
        L54:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L5d:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L64
            return r0
        L64:
            long r1 = r6.longValue()
            r5.m(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    boolean g(int i2) {
        return i2 % this.f14500c.f14494j == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return f14499n;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2 / this.f14500c.f14494j;
    }

    boolean h(int i2) {
        return (i2 + 1) % this.f14500c.f14494j == 0;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @VisibleForTesting
    boolean isEndOfRange(long j2) {
        Iterator it = this.f14501h.t().iterator();
        while (it.hasNext()) {
            Object obj = ((Pair) it.next()).f3271b;
            if (obj != null && ((Long) obj).longValue() == j2) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean isStartOfRange(long j2) {
        Iterator it = this.f14501h.t().iterator();
        while (it.hasNext()) {
            Object obj = ((Pair) it.next()).f3270a;
            if (obj != null && ((Long) obj).longValue() == j2) {
                return true;
            }
        }
        return false;
    }

    int k() {
        return (b() + this.f14500c.f14495k) - 1;
    }

    int l(int i2) {
        return (i2 - b()) + 1;
    }

    public void o(MaterialCalendarGridView materialCalendarGridView) {
        Iterator it = this.f14502i.iterator();
        while (it.hasNext()) {
            n(materialCalendarGridView, ((Long) it.next()).longValue());
        }
        DateSelector dateSelector = this.f14501h;
        if (dateSelector != null) {
            Iterator it2 = dateSelector.E().iterator();
            while (it2.hasNext()) {
                n(materialCalendarGridView, ((Long) it2.next()).longValue());
            }
            this.f14502i = this.f14501h.E();
        }
    }

    boolean p(int i2) {
        return i2 >= b() && i2 <= k();
    }
}
