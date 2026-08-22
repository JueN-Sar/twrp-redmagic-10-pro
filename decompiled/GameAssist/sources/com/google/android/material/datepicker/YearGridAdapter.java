package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: c, reason: collision with root package name */
    private final MaterialCalendar f14541c;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        final TextView f14544s;

        ViewHolder(TextView textView) {
            super(textView);
            this.f14544s = textView;
        }
    }

    YearGridAdapter(MaterialCalendar materialCalendar) {
        this.f14541c = materialCalendar;
    }

    private View.OnClickListener M(final int i2) {
        return new View.OnClickListener() { // from class: com.google.android.material.datepicker.YearGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YearGridAdapter.this.f14541c.u2(YearGridAdapter.this.f14541c.l2().i(Month.f(i2, YearGridAdapter.this.f14541c.n2().f14492h)));
                YearGridAdapter.this.f14541c.v2(MaterialCalendar.CalendarSelector.DAY);
            }
        };
    }

    int N(int i2) {
        return i2 - this.f14541c.l2().q().f14493i;
    }

    int O(int i2) {
        return this.f14541c.l2().q().f14493i + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public void A(ViewHolder viewHolder, int i2) {
        int O = O(i2);
        viewHolder.f14544s.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(O)));
        TextView textView = viewHolder.f14544s;
        textView.setContentDescription(DateStrings.k(textView.getContext(), O));
        CalendarStyle m2 = this.f14541c.m2();
        Calendar k2 = UtcDates.k();
        CalendarItemStyle calendarItemStyle = k2.get(1) == O ? m2.f14441f : m2.f14439d;
        Iterator it = this.f14541c.o2().E().iterator();
        while (it.hasNext()) {
            k2.setTimeInMillis(((Long) it.next()).longValue());
            if (k2.get(1) == O) {
                calendarItemStyle = m2.f14440e;
            }
        }
        calendarItemStyle.d(viewHolder.f14544s);
        viewHolder.f14544s.setOnClickListener(M(O));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public ViewHolder C(ViewGroup viewGroup, int i2) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f14541c.l2().r();
    }
}
