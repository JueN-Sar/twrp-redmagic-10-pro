package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Arrays;

/* loaded from: classes.dex */
class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    private static final float EPSILON = 0.001f;
    private static final int INITIAL_CAPACITY = 12;
    private static final String VALUE_PLACEHOLDER = "";
    private final int clockHandPadding;
    private final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private final RectF scratch;
    private final Rect scratchLineBounds;
    private final ColorStateList textColor;
    private final SparseArray<TextView> textViewPool;
    private final Rect textViewRect;
    private final AccessibilityDelegateCompat valueAccessibilityDelegate;
    private String[] values;

    public ClockFaceView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    private void P() {
        RectF f2 = this.clockHandView.f();
        TextView S = S(f2);
        for (int i2 = 0; i2 < this.textViewPool.size(); i2++) {
            TextView textView = this.textViewPool.get(i2);
            if (textView != null) {
                textView.setSelected(textView == S);
                textView.getPaint().setShader(R(f2, textView));
                textView.invalidate();
            }
        }
    }

    private RadialGradient R(RectF rectF, TextView textView) {
        textView.getHitRect(this.textViewRect);
        this.scratch.set(this.textViewRect);
        textView.getLineBounds(0, this.scratchLineBounds);
        RectF rectF2 = this.scratch;
        Rect rect = this.scratchLineBounds;
        rectF2.inset(rect.left, rect.top);
        if (RectF.intersects(rectF, this.scratch)) {
            return new RadialGradient(rectF.centerX() - this.scratch.left, rectF.centerY() - this.scratch.top, rectF.width() * 0.5f, this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
        }
        return null;
    }

    private TextView S(RectF rectF) {
        float f2 = Float.MAX_VALUE;
        TextView textView = null;
        for (int i2 = 0; i2 < this.textViewPool.size(); i2++) {
            TextView textView2 = this.textViewPool.get(i2);
            if (textView2 != null) {
                textView2.getHitRect(this.textViewRect);
                this.scratch.set(this.textViewRect);
                this.scratch.union(rectF);
                float width = this.scratch.width() * this.scratch.height();
                if (width < f2) {
                    textView = textView2;
                    f2 = width;
                }
            }
        }
        return textView;
    }

    private static float T(float f2, float f3, float f4) {
        return Math.max(Math.max(f2, f3), f4);
    }

    private void W(int i2) {
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = this.textViewPool.size();
        boolean z = false;
        for (int i3 = 0; i3 < Math.max(this.values.length, size); i3++) {
            TextView textView = this.textViewPool.get(i3);
            if (i3 >= this.values.length) {
                removeView(textView);
                this.textViewPool.remove(i3);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(R.layout.material_clockface_textview, (ViewGroup) this, false);
                    this.textViewPool.put(i3, textView);
                    addView(textView);
                }
                textView.setText(this.values[i3]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i3));
                int i4 = (i3 / 12) + 1;
                textView.setTag(R.id.material_clock_level, Integer.valueOf(i4));
                if (i4 > 1) {
                    z = true;
                }
                ViewCompat.i0(textView, this.valueAccessibilityDelegate);
                textView.setTextColor(this.textColor);
                if (i2 != 0) {
                    textView.setContentDescription(getResources().getString(i2, this.values[i3]));
                }
            }
        }
        this.clockHandView.t(z);
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public void H(int i2) {
        if (i2 != G()) {
            super.H(i2);
            this.clockHandView.o(G());
        }
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    protected void J() {
        super.J();
        for (int i2 = 0; i2 < this.textViewPool.size(); i2++) {
            this.textViewPool.get(i2).setVisibility(0);
        }
    }

    int Q() {
        return this.clockHandView.e();
    }

    void U(int i2) {
        this.clockHandView.p(i2);
    }

    public void V(String[] strArr, int i2) {
        this.values = strArr;
        W(i2);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void e(float f2, boolean z) {
        if (Math.abs(this.currentHandRotation - f2) > EPSILON) {
            this.currentHandRotation = f2;
            P();
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo).j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.b(1, this.values.length, false, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        P();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int T = (int) (this.clockSize / T(this.minimumHeight / displayMetrics.heightPixels, this.minimumWidth / displayMetrics.widthPixels, 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(T, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        setMeasuredDimension(T, T);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.scratchLineBounds = new Rect();
        this.textViewPool = new SparseArray<>();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockFaceView, i2, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList a2 = MaterialResources.a(context, obtainStyledAttributes, R.styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = a2;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.clockHandView = clockHandView;
        this.clockHandPadding = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = a2.getColorForState(new int[]{android.R.attr.state_selected}, a2.getDefaultColor());
        this.gradientColors = new int[]{colorForState, colorForState, a2.getDefaultColor()};
        clockHandView.b(this);
        int defaultColor = AppCompatResources.a(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList a3 = MaterialResources.a(context, obtainStyledAttributes, R.styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(a3 != null ? a3.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.timepicker.ClockFaceView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView.this.H(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.clockHandView.j()) - ClockFaceView.this.clockHandPadding);
                return true;
            }
        });
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.google.android.material.timepicker.ClockFaceView.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                int intValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
                if (intValue > 0) {
                    accessibilityNodeInfoCompat.L0((View) ClockFaceView.this.textViewPool.get(intValue - 1));
                }
                accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(0, 1, intValue, 1, false, view.isSelected()));
                accessibilityNodeInfoCompat.i0(true);
                accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3486i);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean j(View view, int i3, Bundle bundle) {
                if (i3 != 16) {
                    return super.j(view, i3, bundle);
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                view.getHitRect(ClockFaceView.this.textViewRect);
                float centerX = ClockFaceView.this.textViewRect.centerX();
                float centerY = ClockFaceView.this.textViewRect.centerY();
                ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, centerX, centerY, 0));
                ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, centerX, centerY, 0));
                return true;
            }
        };
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        V(strArr, 0);
        this.minimumHeight = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.clockSize = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }
}
