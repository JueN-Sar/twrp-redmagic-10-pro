package com.google.android.material.timepicker;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;

/* loaded from: classes.dex */
class TimePickerView extends ConstraintLayout implements TimePickerControls {
    static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    private OnDoubleTapListener onDoubleTapListener;
    private OnPeriodChangeListener onPeriodChangeListener;
    private OnSelectionChange onSelectionChangeListener;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    interface OnDoubleTapListener {
        void d();
    }

    interface OnPeriodChangeListener {
        void c(int i2);
    }

    interface OnSelectionChange {
        void d(int i2);
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z) {
        OnPeriodChangeListener onPeriodChangeListener;
        if (z && (onPeriodChangeListener = this.onPeriodChangeListener) != null) {
            onPeriodChangeListener.c(i2 == R.id.material_clock_period_pm_button ? 1 : 0);
        }
    }

    private void T() {
        this.minuteView.setTag(R.id.selection_type, 12);
        this.hourView.setTag(R.id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
        this.minuteView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
        this.hourView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
    }

    private void V() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.google.android.material.timepicker.TimePickerView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                OnDoubleTapListener onDoubleTapListener = TimePickerView.this.onDoubleTapListener;
                if (onDoubleTapListener == null) {
                    return false;
                }
                onDoubleTapListener.d();
                return true;
            }
        });
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.timepicker.TimePickerView.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (((Checkable) view).isChecked()) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
                return false;
            }
        };
        this.minuteView.setOnTouchListener(onTouchListener);
        this.hourView.setOnTouchListener(onTouchListener);
    }

    private void X(Chip chip, boolean z) {
        chip.setChecked(z);
        ViewCompat.k0(chip, z ? 2 : 0);
    }

    public void G(ClockHandView.OnRotateListener onRotateListener) {
        this.clockHandView.b(onRotateListener);
    }

    int H() {
        return this.clockFace.Q();
    }

    public void J(int i2) {
        X(this.minuteView, i2 == 12);
        X(this.hourView, i2 == 10);
    }

    public void K(boolean z) {
        this.clockHandView.n(z);
    }

    void L(int i2) {
        this.clockFace.U(i2);
    }

    public void M(float f2, boolean z) {
        this.clockHandView.r(f2, z);
    }

    public void N(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.i0(this.minuteView, accessibilityDelegateCompat);
    }

    public void O(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.i0(this.hourView, accessibilityDelegateCompat);
    }

    public void P(ClockHandView.OnActionUpListener onActionUpListener) {
        this.clockHandView.u(onActionUpListener);
    }

    void Q(OnDoubleTapListener onDoubleTapListener) {
        this.onDoubleTapListener = onDoubleTapListener;
    }

    void R(OnPeriodChangeListener onPeriodChangeListener) {
        this.onPeriodChangeListener = onPeriodChangeListener;
    }

    void S(OnSelectionChange onSelectionChange) {
        this.onSelectionChangeListener = onSelectionChange;
    }

    public void U(String[] strArr, int i2) {
        this.clockFace.V(strArr, i2);
    }

    public void W() {
        this.toggle.setVisibility(0);
    }

    public void Y(int i2, int i3, int i4) {
        this.toggle.e(i2 == 1 ? R.id.material_clock_period_pm_button : R.id.material_clock_period_am_button);
        Locale locale = getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(i4));
        String format2 = String.format(locale, "%02d", Integer.valueOf(i3));
        if (!TextUtils.equals(this.minuteView.getText(), format)) {
            this.minuteView.setText(format);
        }
        if (TextUtils.equals(this.hourView.getText(), format2)) {
            return;
        }
        this.hourView.setText(format2);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (view == this && i2 == 0) {
            this.hourView.sendAccessibilityEvent(8);
        }
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.selectionListener = new View.OnClickListener() { // from class: com.google.android.material.timepicker.TimePickerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TimePickerView.this.onSelectionChangeListener != null) {
                    TimePickerView.this.onSelectionChangeListener.d(((Integer) view.getTag(R.id.selection_type)).intValue());
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        this.clockFace = (ClockFaceView) findViewById(R.id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup;
        materialButtonToggleGroup.b(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.google.android.material.timepicker.e
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void a(MaterialButtonToggleGroup materialButtonToggleGroup2, int i3, boolean z) {
                TimePickerView.this.I(materialButtonToggleGroup2, i3, z);
            }
        });
        this.minuteView = (Chip) findViewById(R.id.material_minute_tv);
        this.hourView = (Chip) findViewById(R.id.material_hour_tv);
        this.clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        V();
        T();
    }
}
