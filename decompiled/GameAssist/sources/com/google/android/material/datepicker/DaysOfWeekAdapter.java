package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
class DaysOfWeekAdapter extends BaseAdapter {

    /* renamed from: j, reason: collision with root package name */
    private static final int f14458j = 4;

    /* renamed from: c, reason: collision with root package name */
    private final Calendar f14459c;

    /* renamed from: h, reason: collision with root package name */
    private final int f14460h;

    /* renamed from: i, reason: collision with root package name */
    private final int f14461i;

    public DaysOfWeekAdapter() {
        Calendar m2 = UtcDates.m();
        this.f14459c = m2;
        this.f14460h = m2.getMaximum(7);
        this.f14461i = m2.getFirstDayOfWeek();
    }

    private int b(int i2) {
        int i3 = i2 + this.f14461i;
        int i4 = this.f14460h;
        return i3 > i4 ? i3 - i4 : i3;
    }

    @Override // android.widget.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i2) {
        if (i2 >= this.f14460h) {
            return null;
        }
        return Integer.valueOf(b(i2));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f14460h;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f14459c.set(7, b(i2));
        textView.setText(this.f14459c.getDisplayName(7, f14458j, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R.string.mtrl_picker_day_of_week_column_header), this.f14459c.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }

    public DaysOfWeekAdapter(int i2) {
        Calendar m2 = UtcDates.m();
        this.f14459c = m2;
        this.f14460h = m2.getMaximum(7);
        this.f14461i = i2;
    }
}
