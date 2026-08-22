package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;

/* loaded from: classes.dex */
class MonthsPagerAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: c, reason: collision with root package name */
    private final CalendarConstraints f14506c;

    /* renamed from: d, reason: collision with root package name */
    private final DateSelector f14507d;

    /* renamed from: e, reason: collision with root package name */
    private final DayViewDecorator f14508e;

    /* renamed from: f, reason: collision with root package name */
    private final MaterialCalendar.OnDayClickListener f14509f;

    /* renamed from: g, reason: collision with root package name */
    private final int f14510g;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        final TextView f14513s;
        final MaterialCalendarGridView t;

        ViewHolder(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.f14513s = textView;
            ViewCompat.j0(textView, true);
            this.t = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    MonthsPagerAdapter(Context context, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator, MaterialCalendar.OnDayClickListener onDayClickListener) {
        Month q2 = calendarConstraints.q();
        Month l2 = calendarConstraints.l();
        Month o2 = calendarConstraints.o();
        if (q2.compareTo(o2) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (o2.compareTo(l2) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f14510g = (MonthAdapter.f14498m * MaterialCalendar.p2(context)) + (MaterialDatePicker.G2(context) ? MaterialCalendar.p2(context) : 0);
        this.f14506c = calendarConstraints;
        this.f14507d = dateSelector;
        this.f14508e = dayViewDecorator;
        this.f14509f = onDayClickListener;
        J(true);
    }

    Month M(int i2) {
        return this.f14506c.q().x(i2);
    }

    CharSequence N(int i2) {
        return M(i2).v();
    }

    int O(Month month) {
        return this.f14506c.q().y(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public void A(ViewHolder viewHolder, int i2) {
        Month x = this.f14506c.q().x(i2);
        viewHolder.f14513s.setText(x.v());
        final MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder.t.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !x.equals(materialCalendarGridView.getAdapter().f14500c)) {
            MonthAdapter monthAdapter = new MonthAdapter(x, this.f14507d, this.f14506c, this.f14508e);
            materialCalendarGridView.setNumColumns(x.f14494j);
            materialCalendarGridView.setAdapter((ListAdapter) monthAdapter);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter().o(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.datepicker.MonthsPagerAdapter.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i3, long j2) {
                if (materialCalendarGridView.getAdapter().p(i3)) {
                    MonthsPagerAdapter.this.f14509f.a(materialCalendarGridView.getAdapter().getItem(i3).longValue());
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public ViewHolder C(ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!MaterialDatePicker.G2(viewGroup.getContext())) {
            return new ViewHolder(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.f14510g));
        return new ViewHolder(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f14506c.n();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long n(int i2) {
        return this.f14506c.q().x(i2).w();
    }
}
