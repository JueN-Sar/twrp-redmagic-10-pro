package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

@RestrictTo
/* loaded from: classes.dex */
public final class MaterialCalendar<S> extends PickerFragment<S> {
    private int j0;
    private DateSelector k0;
    private CalendarConstraints l0;
    private DayViewDecorator m0;
    private Month n0;
    private CalendarSelector o0;
    private CalendarStyle p0;
    private RecyclerView q0;
    private RecyclerView r0;
    private View s0;
    private View t0;
    private View u0;
    private View v0;

    @VisibleForTesting
    static final Object MONTHS_VIEW_GROUP_TAG = "MONTHS_VIEW_GROUP_TAG";

    @VisibleForTesting
    static final Object NAVIGATION_PREV_TAG = "NAVIGATION_PREV_TAG";

    @VisibleForTesting
    static final Object NAVIGATION_NEXT_TAG = "NAVIGATION_NEXT_TAG";

    @VisibleForTesting
    static final Object SELECTOR_TOGGLE_TAG = "SELECTOR_TOGGLE_TAG";

    enum CalendarSelector {
        DAY,
        YEAR
    }

    interface OnDayClickListener {
        void a(long j2);
    }

    private void j2(View view, final MonthsPagerAdapter monthsPagerAdapter) {
        final MaterialButton materialButton = (MaterialButton) view.findViewById(R.id.month_navigation_fragment_toggle);
        materialButton.setTag(SELECTOR_TOGGLE_TAG);
        ViewCompat.i0(materialButton, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.6
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view2, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.s0(MaterialCalendar.this.v0.getVisibility() == 0 ? MaterialCalendar.this.b0(R.string.mtrl_picker_toggle_to_year_selection) : MaterialCalendar.this.b0(R.string.mtrl_picker_toggle_to_day_selection));
            }
        });
        View findViewById = view.findViewById(R.id.month_navigation_previous);
        this.s0 = findViewById;
        findViewById.setTag(NAVIGATION_PREV_TAG);
        View findViewById2 = view.findViewById(R.id.month_navigation_next);
        this.t0 = findViewById2;
        findViewById2.setTag(NAVIGATION_NEXT_TAG);
        this.u0 = view.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.v0 = view.findViewById(R.id.mtrl_calendar_day_selector_frame);
        v2(CalendarSelector.DAY);
        materialButton.setText(this.n0.v());
        this.r0.l(new RecyclerView.OnScrollListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.7
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void a(RecyclerView recyclerView, int i2) {
                if (i2 == 0) {
                    recyclerView.announceForAccessibility(materialButton.getText());
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void b(RecyclerView recyclerView, int i2, int i3) {
                int i22 = i2 < 0 ? MaterialCalendar.this.r2().i2() : MaterialCalendar.this.r2().l2();
                MaterialCalendar.this.n0 = monthsPagerAdapter.M(i22);
                materialButton.setText(monthsPagerAdapter.N(i22));
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                MaterialCalendar.this.x2();
            }
        });
        this.t0.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int i2 = MaterialCalendar.this.r2().i2() + 1;
                if (i2 < MaterialCalendar.this.r0.getAdapter().m()) {
                    MaterialCalendar.this.u2(monthsPagerAdapter.M(i2));
                }
            }
        });
        this.s0.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int l2 = MaterialCalendar.this.r2().l2() - 1;
                if (l2 >= 0) {
                    MaterialCalendar.this.u2(monthsPagerAdapter.M(l2));
                }
            }
        });
    }

    private RecyclerView.ItemDecoration k2() {
        return new RecyclerView.ItemDecoration() { // from class: com.google.android.material.datepicker.MaterialCalendar.5

            /* renamed from: a, reason: collision with root package name */
            private final Calendar f14469a = UtcDates.m();

            /* renamed from: b, reason: collision with root package name */
            private final Calendar f14470b = UtcDates.m();

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
                if ((recyclerView.getAdapter() instanceof YearGridAdapter) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                    YearGridAdapter yearGridAdapter = (YearGridAdapter) recyclerView.getAdapter();
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    for (Pair pair : MaterialCalendar.this.k0.t()) {
                        Object obj = pair.f3270a;
                        if (obj != null && pair.f3271b != null) {
                            this.f14469a.setTimeInMillis(((Long) obj).longValue());
                            this.f14470b.setTimeInMillis(((Long) pair.f3271b).longValue());
                            int N = yearGridAdapter.N(this.f14469a.get(1));
                            int N2 = yearGridAdapter.N(this.f14470b.get(1));
                            View I = gridLayoutManager.I(N);
                            View I2 = gridLayoutManager.I(N2);
                            int h3 = N / gridLayoutManager.h3();
                            int h32 = N2 / gridLayoutManager.h3();
                            int i2 = h3;
                            while (i2 <= h32) {
                                if (gridLayoutManager.I(gridLayoutManager.h3() * i2) != null) {
                                    canvas.drawRect((i2 != h3 || I == null) ? 0 : I.getLeft() + (I.getWidth() / 2), r9.getTop() + MaterialCalendar.this.p0.f14439d.c(), (i2 != h32 || I2 == null) ? recyclerView.getWidth() : I2.getLeft() + (I2.getWidth() / 2), r9.getBottom() - MaterialCalendar.this.p0.f14439d.b(), MaterialCalendar.this.p0.f14443h);
                                }
                                i2++;
                            }
                        }
                    }
                }
            }
        };
    }

    static int p2(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    private static int q2(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height);
        int i2 = MonthAdapter.f14498m;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding);
    }

    public static MaterialCalendar s2(DateSelector dateSelector, int i2, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        MaterialCalendar materialCalendar = new MaterialCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", dayViewDecorator);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.o());
        materialCalendar.J1(bundle);
        return materialCalendar;
    }

    private void t2(final int i2) {
        this.r0.post(new Runnable() { // from class: com.google.android.material.datepicker.MaterialCalendar.11
            @Override // java.lang.Runnable
            public void run() {
                MaterialCalendar.this.r0.s1(i2);
            }
        });
    }

    private void w2() {
        ViewCompat.i0(this.r0, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.4
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.E0(false);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        final int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(z(), this.j0);
        this.p0 = new CalendarStyle(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month q2 = this.l0.q();
        if (MaterialDatePicker.G2(contextThemeWrapper)) {
            i2 = R.layout.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = R.layout.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        inflate.setMinimumHeight(q2(D1()));
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.i0(gridView, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendar.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.j0(null);
            }
        });
        int m2 = this.l0.m();
        gridView.setAdapter((ListAdapter) (m2 > 0 ? new DaysOfWeekAdapter(m2) : new DaysOfWeekAdapter()));
        gridView.setNumColumns(q2.f14494j);
        gridView.setEnabled(false);
        this.r0 = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.r0.setLayoutManager(new SmoothCalendarLayoutManager(z(), i3, false) { // from class: com.google.android.material.datepicker.MaterialCalendar.2
            @Override // androidx.recyclerview.widget.LinearLayoutManager
            protected void V1(RecyclerView.State state, int[] iArr) {
                if (i3 == 0) {
                    iArr[0] = MaterialCalendar.this.r0.getWidth();
                    iArr[1] = MaterialCalendar.this.r0.getWidth();
                } else {
                    iArr[0] = MaterialCalendar.this.r0.getHeight();
                    iArr[1] = MaterialCalendar.this.r0.getHeight();
                }
            }
        });
        this.r0.setTag(MONTHS_VIEW_GROUP_TAG);
        MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, this.k0, this.l0, this.m0, new OnDayClickListener() { // from class: com.google.android.material.datepicker.MaterialCalendar.3
            @Override // com.google.android.material.datepicker.MaterialCalendar.OnDayClickListener
            public void a(long j2) {
                if (MaterialCalendar.this.l0.k().j(j2)) {
                    MaterialCalendar.this.k0.L(j2);
                    Iterator it = MaterialCalendar.this.i0.iterator();
                    while (it.hasNext()) {
                        ((OnSelectionChangedListener) it.next()).b(MaterialCalendar.this.k0.F());
                    }
                    MaterialCalendar.this.r0.getAdapter().r();
                    if (MaterialCalendar.this.q0 != null) {
                        MaterialCalendar.this.q0.getAdapter().r();
                    }
                }
            }
        });
        this.r0.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.q0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.q0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.q0.setAdapter(new YearGridAdapter(this));
            this.q0.h(k2());
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            j2(inflate, monthsPagerAdapter);
        }
        if (!MaterialDatePicker.G2(contextThemeWrapper)) {
            new PagerSnapHelper().b(this.r0);
        }
        this.r0.l1(monthsPagerAdapter.O(this.n0));
        w2();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.j0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.k0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.l0);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.m0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.n0);
    }

    @Override // com.google.android.material.datepicker.PickerFragment
    public boolean a2(OnSelectionChangedListener onSelectionChangedListener) {
        return super.a2(onSelectionChangedListener);
    }

    CalendarConstraints l2() {
        return this.l0;
    }

    CalendarStyle m2() {
        return this.p0;
    }

    Month n2() {
        return this.n0;
    }

    public DateSelector o2() {
        return this.k0;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = x();
        }
        this.j0 = bundle.getInt("THEME_RES_ID_KEY");
        this.k0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.l0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.m0 = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.n0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    LinearLayoutManager r2() {
        return (LinearLayoutManager) this.r0.getLayoutManager();
    }

    void u2(Month month) {
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.r0.getAdapter();
        int O = monthsPagerAdapter.O(month);
        int O2 = O - monthsPagerAdapter.O(this.n0);
        boolean z = Math.abs(O2) > 3;
        boolean z2 = O2 > 0;
        this.n0 = month;
        if (z && z2) {
            this.r0.l1(O - 3);
            t2(O);
        } else if (!z) {
            t2(O);
        } else {
            this.r0.l1(O + 3);
            t2(O);
        }
    }

    void v2(CalendarSelector calendarSelector) {
        this.o0 = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.q0.getLayoutManager().G1(((YearGridAdapter) this.q0.getAdapter()).N(this.n0.f14493i));
            this.u0.setVisibility(0);
            this.v0.setVisibility(8);
            this.s0.setVisibility(8);
            this.t0.setVisibility(8);
            return;
        }
        if (calendarSelector == CalendarSelector.DAY) {
            this.u0.setVisibility(8);
            this.v0.setVisibility(0);
            this.s0.setVisibility(0);
            this.t0.setVisibility(0);
            u2(this.n0);
        }
    }

    void x2() {
        CalendarSelector calendarSelector = this.o0;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            v2(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            v2(calendarSelector2);
        }
    }
}
