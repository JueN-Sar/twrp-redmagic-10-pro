package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import java.util.Calendar;
import java.util.Iterator;

/* loaded from: classes.dex */
final class MaterialCalendarGridView extends GridView {
    private final Calendar dayCompute;
    private final boolean nestedScrollable;

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a(int i2, Rect rect) {
        if (i2 == 33) {
            setSelection(getAdapter().k());
        } else if (i2 == 130) {
            setSelection(getAdapter().b());
        } else {
            super.onFocusChanged(true, i2, rect);
        }
    }

    private View c(int i2) {
        return getChildAt(i2 - getFirstVisiblePosition());
    }

    private static int d(View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }

    private static boolean e(Long l2, Long l3, Long l4, Long l5) {
        return l2 == null || l3 == null || l4 == null || l5 == null || l4.longValue() > l3.longValue() || l5.longValue() < l2.longValue();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* renamed from: b, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MonthAdapter getAdapter2() {
        return (MonthAdapter) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        int a2;
        int d2;
        int a3;
        int d3;
        int width;
        int i2;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        MonthAdapter adapter = getAdapter();
        DateSelector dateSelector = adapter.f14501h;
        CalendarStyle calendarStyle = adapter.f14503j;
        int max = Math.max(adapter.b(), getFirstVisiblePosition());
        int min = Math.min(adapter.k(), getLastVisiblePosition());
        Long item = adapter.getItem(max);
        Long item2 = adapter.getItem(min);
        Iterator it = dateSelector.t().iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Object obj = pair.f3270a;
            if (obj == null) {
                materialCalendarGridView = this;
            } else if (pair.f3271b != null) {
                Long l2 = (Long) obj;
                long longValue = l2.longValue();
                Long l3 = (Long) pair.f3271b;
                long longValue2 = l3.longValue();
                if (!e(item, item2, l2, l3)) {
                    boolean p2 = ViewUtils.p(this);
                    if (longValue < item.longValue()) {
                        d2 = adapter.g(max) ? 0 : !p2 ? materialCalendarGridView.c(max - 1).getRight() : materialCalendarGridView.c(max - 1).getLeft();
                        a2 = max;
                    } else {
                        materialCalendarGridView.dayCompute.setTimeInMillis(longValue);
                        a2 = adapter.a(materialCalendarGridView.dayCompute.get(5));
                        d2 = d(materialCalendarGridView.c(a2));
                    }
                    if (longValue2 > item2.longValue()) {
                        d3 = adapter.h(min) ? getWidth() : !p2 ? materialCalendarGridView.c(min).getRight() : materialCalendarGridView.c(min).getLeft();
                        a3 = min;
                    } else {
                        materialCalendarGridView.dayCompute.setTimeInMillis(longValue2);
                        a3 = adapter.a(materialCalendarGridView.dayCompute.get(5));
                        d3 = d(materialCalendarGridView.c(a3));
                    }
                    int itemId = (int) adapter.getItemId(a2);
                    int i3 = max;
                    int i4 = min;
                    int itemId2 = (int) adapter.getItemId(a3);
                    while (itemId <= itemId2) {
                        int numColumns = getNumColumns() * itemId;
                        int numColumns2 = (numColumns + getNumColumns()) - 1;
                        View c2 = materialCalendarGridView.c(numColumns);
                        int top = c2.getTop() + calendarStyle.f14436a.c();
                        MonthAdapter monthAdapter = adapter;
                        int bottom = c2.getBottom() - calendarStyle.f14436a.b();
                        if (p2) {
                            int i5 = a3 > numColumns2 ? 0 : d3;
                            width = numColumns > a2 ? getWidth() : d2;
                            i2 = i5;
                        } else {
                            i2 = numColumns > a2 ? 0 : d2;
                            width = a3 > numColumns2 ? getWidth() : d3;
                        }
                        canvas.drawRect(i2, top, width, bottom, calendarStyle.f14443h);
                        itemId++;
                        materialCalendarGridView = this;
                        it = it;
                        adapter = monthAdapter;
                    }
                    materialCalendarGridView = this;
                    max = i3;
                    min = i4;
                }
            }
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        if (z) {
            a(i2, rect);
        } else {
            super.onFocusChanged(false, i2, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!super.onKeyDown(i2, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().b()) {
            return true;
        }
        if (19 != i2) {
            return false;
        }
        setSelection(getAdapter().b());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i2, int i3) {
        if (!this.nestedScrollable) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i2) {
        if (i2 < getAdapter().b()) {
            super.setSelection(getAdapter().b());
        } else {
            super.setSelection(i2);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.dayCompute = UtcDates.m();
        if (MaterialDatePicker.G2(getContext())) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        this.nestedScrollable = MaterialDatePicker.I2(getContext());
        ViewCompat.i0(this, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendarGridView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.j0(null);
            }
        });
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof MonthAdapter)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }
}
