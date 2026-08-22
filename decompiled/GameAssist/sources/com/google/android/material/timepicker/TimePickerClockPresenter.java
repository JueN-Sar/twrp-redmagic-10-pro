package com.google.android.material.timepicker;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.timepicker.ClockHandView;
import com.google.android.material.timepicker.TimePickerView;

/* loaded from: classes.dex */
class TimePickerClockPresenter implements ClockHandView.OnRotateListener, TimePickerView.OnSelectionChange, TimePickerView.OnPeriodChangeListener, ClockHandView.OnActionUpListener, TimePickerPresenter {

    /* renamed from: l, reason: collision with root package name */
    private static final String[] f15482l = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};

    /* renamed from: m, reason: collision with root package name */
    private static final String[] f15483m = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};

    /* renamed from: n, reason: collision with root package name */
    private static final String[] f15484n = {"00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};

    /* renamed from: c, reason: collision with root package name */
    private final TimePickerView f15485c;

    /* renamed from: h, reason: collision with root package name */
    private final TimeModel f15486h;

    /* renamed from: i, reason: collision with root package name */
    private float f15487i;

    /* renamed from: j, reason: collision with root package name */
    private float f15488j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f15489k = false;

    public TimePickerClockPresenter(TimePickerView timePickerView, TimeModel timeModel) {
        this.f15485c = timePickerView;
        this.f15486h = timeModel;
        i();
    }

    private String[] g() {
        return this.f15486h.f15477i == 1 ? f15483m : f15482l;
    }

    private int h() {
        return (this.f15486h.f() * 30) % 360;
    }

    private void j(int i2, int i3) {
        TimeModel timeModel = this.f15486h;
        if (timeModel.f15479k == i3 && timeModel.f15478j == i2) {
            return;
        }
        this.f15485c.performHapticFeedback(4);
    }

    private void l() {
        TimeModel timeModel = this.f15486h;
        int i2 = 1;
        if (timeModel.f15480l == 10 && timeModel.f15477i == 1 && timeModel.f15478j >= 12) {
            i2 = 2;
        }
        this.f15485c.L(i2);
    }

    private void m() {
        TimePickerView timePickerView = this.f15485c;
        TimeModel timeModel = this.f15486h;
        timePickerView.Y(timeModel.f15481m, timeModel.f(), this.f15486h.f15479k);
    }

    private void n() {
        o(f15482l, "%d");
        o(f15484n, "%02d");
    }

    private void o(String[] strArr, String str) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = TimeModel.b(this.f15485c.getResources(), strArr[i2], str);
        }
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void a() {
        this.f15488j = h();
        TimeModel timeModel = this.f15486h;
        this.f15487i = timeModel.f15479k * 6;
        k(timeModel.f15480l, false);
        m();
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnActionUpListener
    public void b(float f2, boolean z) {
        this.f15489k = true;
        TimeModel timeModel = this.f15486h;
        int i2 = timeModel.f15479k;
        int i3 = timeModel.f15478j;
        if (timeModel.f15480l == 10) {
            this.f15485c.M(this.f15488j, false);
            AccessibilityManager accessibilityManager = (AccessibilityManager) ContextCompat.i(this.f15485c.getContext(), AccessibilityManager.class);
            if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
                k(12, true);
            }
        } else {
            int round = Math.round(f2);
            if (!z) {
                this.f15486h.m(((round + 15) / 30) * 5);
                this.f15487i = this.f15486h.f15479k * 6;
            }
            this.f15485c.M(this.f15487i, z);
        }
        this.f15489k = false;
        m();
        j(i3, i2);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnPeriodChangeListener
    public void c(int i2) {
        this.f15486h.n(i2);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnSelectionChange
    public void d(int i2) {
        k(i2, true);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void e(float f2, boolean z) {
        if (this.f15489k) {
            return;
        }
        TimeModel timeModel = this.f15486h;
        int i2 = timeModel.f15478j;
        int i3 = timeModel.f15479k;
        int round = Math.round(f2);
        TimeModel timeModel2 = this.f15486h;
        if (timeModel2.f15480l == 12) {
            timeModel2.m((round + 3) / 6);
            this.f15487i = (float) Math.floor(this.f15486h.f15479k * 6);
        } else {
            int i4 = (round + 15) / 30;
            if (timeModel2.f15477i == 1) {
                i4 %= 12;
                if (this.f15485c.H() == 2) {
                    i4 += 12;
                }
            }
            this.f15486h.l(i4);
            this.f15488j = h();
        }
        if (z) {
            return;
        }
        m();
        j(i2, i3);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        this.f15485c.setVisibility(8);
    }

    public void i() {
        if (this.f15486h.f15477i == 0) {
            this.f15485c.W();
        }
        this.f15485c.G(this);
        this.f15485c.S(this);
        this.f15485c.R(this);
        this.f15485c.P(this);
        n();
        a();
    }

    void k(int i2, boolean z) {
        boolean z2 = i2 == 12;
        this.f15485c.K(z2);
        this.f15486h.f15480l = i2;
        this.f15485c.U(z2 ? f15484n : g(), z2 ? R.string.material_minute_suffix : this.f15486h.d());
        l();
        this.f15485c.M(z2 ? this.f15487i : this.f15488j, z);
        this.f15485c.J(i2);
        this.f15485c.O(new ClickActionDelegate(this.f15485c.getContext(), R.string.material_hour_selection) { // from class: com.google.android.material.timepicker.TimePickerClockPresenter.1
            @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.l0(view.getResources().getString(TimePickerClockPresenter.this.f15486h.d(), String.valueOf(TimePickerClockPresenter.this.f15486h.f())));
            }
        });
        this.f15485c.N(new ClickActionDelegate(this.f15485c.getContext(), R.string.material_minute_selection) { // from class: com.google.android.material.timepicker.TimePickerClockPresenter.2
            @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.l0(view.getResources().getString(R.string.material_minute_suffix, String.valueOf(TimePickerClockPresenter.this.f15486h.f15479k)));
            }
        });
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.f15485c.setVisibility(0);
    }
}
