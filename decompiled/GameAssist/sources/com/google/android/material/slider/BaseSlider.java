package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.gms.common.api.Api;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import com.zte.distbus.basetransfer.Status;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    private static final int DEFAULT_LABEL_ANIMATION_ENTER_DURATION = 83;
    private static final int DEFAULT_LABEL_ANIMATION_EXIT_DURATION = 117;
    private static final String EXCEPTION_ILLEGAL_DISCRETE_VALUE = "Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION = "minSeparation(%s) must be greater or equal to 0";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE = "minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT = "minSeparation(%s) cannot be set as a dimension when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_STEP_SIZE = "The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range";
    private static final String EXCEPTION_ILLEGAL_VALUE = "Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_FROM = "valueFrom(%s) must be smaller than valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_TO = "valueTo(%s) must be greater than valueFrom(%s)";
    private static final int HALO_ALPHA = 63;

    @Dimension
    private static final int MIN_TOUCH_TARGET_DP = 48;
    private static final String TAG = "BaseSlider";
    private static final double THRESHOLD = 1.0E-4d;
    private static final float THUMB_WIDTH_PRESSED_RATIO = 0.5f;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    static final int UNIT_PX = 0;
    static final int UNIT_VALUE = 1;
    private static final String WARNING_FLOATING_POINT_ERROR = "Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.";
    private BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender;

    @NonNull
    private final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    private int activeThumbIdx;

    @NonNull
    private final Paint activeTicksPaint;

    @NonNull
    private final Paint activeTrackPaint;

    @NonNull
    private final List<L> changeListeners;

    @NonNull
    private final RectF cornerRect;

    @Nullable
    private Drawable customThumbDrawable;

    @NonNull
    private List<Drawable> customThumbDrawablesForValues;

    @NonNull
    private final MaterialShapeDrawable defaultThumbDrawable;
    private int defaultThumbRadius;
    private int defaultThumbTrackGapSize;
    private int defaultThumbWidth;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackHeight;
    private boolean dirtyConfig;
    private int focusedThumbIdx;
    private boolean forceDrawCompatHalo;
    private LabelFormatter formatter;

    @NonNull
    private ColorStateList haloColor;

    @NonNull
    private final Paint haloPaint;
    private int haloRadius;

    @NonNull
    private final Paint inactiveTicksPaint;

    @NonNull
    private final Paint inactiveTrackPaint;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private int labelStyle;

    @NonNull
    private final List<TooltipDrawable> labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTickSpacing;

    @Px
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetHeight;

    @NonNull
    private final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private final int scaledTouchSlop;
    private int separationUnit;
    private float stepSize;

    @NonNull
    private final Paint stopIndicatorPaint;
    private int thumbHeight;
    private boolean thumbIsPressed;

    @NonNull
    private final Paint thumbPaint;
    private int thumbTrackGapSize;
    private int thumbWidth;
    private int tickActiveRadius;

    @NonNull
    private ColorStateList tickColorActive;

    @NonNull
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private boolean tickVisible;
    private float[] ticksCoordinates;
    private float touchDownX;

    @NonNull
    private final List<T> touchListeners;
    private float touchPosition;

    @NonNull
    private ColorStateList trackColorActive;

    @NonNull
    private ColorStateList trackColorInactive;
    private int trackHeight;
    private int trackInsideCornerSize;

    @NonNull
    private final Path trackPath;

    @NonNull
    private final RectF trackRect;
    private int trackSidePadding;
    private int trackStopIndicatorSize;
    private int trackWidth;
    private float valueFrom;
    private float valueTo;
    private ArrayList<Float> values;
    private int widgetHeight;
    static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_Slider;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = R.attr.motionDurationMedium4;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = R.attr.motionDurationShort3;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;

    /* renamed from: com.google.android.material.slider.BaseSlider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f15273a;

        static {
            int[] iArr = new int[FullCornerDirection.values().length];
            f15273a = iArr;
            try {
                iArr[FullCornerDirection.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15273a[FullCornerDirection.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15273a[FullCornerDirection.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15273a[FullCornerDirection.BOTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private class AccessibilityEventSender implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        int f15274c;

        private AccessibilityEventSender() {
            this.f15274c = -1;
        }

        void a(int i2) {
            this.f15274c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.accessibilityHelper.W(this.f15274c, 4);
        }
    }

    private static class AccessibilityHelper extends ExploreByTouchHelper {

        /* renamed from: q, reason: collision with root package name */
        private final BaseSlider f15276q;

        /* renamed from: r, reason: collision with root package name */
        final Rect f15277r;

        AccessibilityHelper(BaseSlider baseSlider) {
            super(baseSlider);
            this.f15277r = new Rect();
            this.f15276q = baseSlider;
        }

        private String Y(int i2) {
            return i2 == this.f15276q.getValues().size() + (-1) ? this.f15276q.getContext().getString(R.string.material_slider_range_end) : i2 == 0 ? this.f15276q.getContext().getString(R.string.material_slider_range_start) : "";
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected int B(float f2, float f3) {
            for (int i2 = 0; i2 < this.f15276q.getValues().size(); i2++) {
                this.f15276q.r0(i2, this.f15277r);
                if (this.f15277r.contains((int) f2, (int) f3)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void C(List list) {
            for (int i2 = 0; i2 < this.f15276q.getValues().size(); i2++) {
                list.add(Integer.valueOf(i2));
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected boolean L(int i2, int i3, Bundle bundle) {
            if (!this.f15276q.isEnabled()) {
                return false;
            }
            if (i3 != 4096 && i3 != 8192) {
                if (i3 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    if (this.f15276q.p0(i2, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                        this.f15276q.s0();
                        this.f15276q.postInvalidate();
                        E(i2);
                        return true;
                    }
                }
                return false;
            }
            float l2 = this.f15276q.l(20);
            if (i3 == 8192) {
                l2 = -l2;
            }
            if (this.f15276q.Q()) {
                l2 = -l2;
            }
            if (!this.f15276q.p0(i2, MathUtils.a(this.f15276q.getValues().get(i2).floatValue() + l2, this.f15276q.getValueFrom(), this.f15276q.getValueTo()))) {
                return false;
            }
            this.f15276q.s0();
            this.f15276q.postInvalidate();
            E(i2);
            return true;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void P(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.AccessibilityActionCompat.L);
            List<Float> values = this.f15276q.getValues();
            float floatValue = values.get(i2).floatValue();
            float valueFrom = this.f15276q.getValueFrom();
            float valueTo = this.f15276q.getValueTo();
            if (this.f15276q.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.a(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.a(4096);
                }
            }
            accessibilityNodeInfoCompat.B0(AccessibilityNodeInfoCompat.RangeInfoCompat.a(1, valueFrom, valueTo, floatValue));
            accessibilityNodeInfoCompat.h0(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.f15276q.getContentDescription() != null) {
                sb.append(this.f15276q.getContentDescription());
                sb.append(",");
            }
            String A = this.f15276q.A(floatValue);
            String string = this.f15276q.getContext().getString(R.string.material_slider_value);
            if (values.size() > 1) {
                string = Y(i2);
            }
            sb.append(String.format(Locale.US, "%s, %s", string, A));
            accessibilityNodeInfoCompat.l0(sb.toString());
            this.f15276q.r0(i2, this.f15277r);
            accessibilityNodeInfoCompat.c0(this.f15277r);
        }
    }

    private enum FullCornerDirection {
        BOTH,
        LEFT,
        RIGHT,
        NONE
    }

    static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new Parcelable.Creator<SliderState>() { // from class: com.google.android.material.slider.BaseSlider.SliderState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SliderState[] newArray(int i2) {
                return new SliderState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        float f15278c;

        /* renamed from: h, reason: collision with root package name */
        float f15279h;

        /* renamed from: i, reason: collision with root package name */
        ArrayList f15280i;

        /* renamed from: j, reason: collision with root package name */
        float f15281j;

        /* renamed from: k, reason: collision with root package name */
        boolean f15282k;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeFloat(this.f15278c);
            parcel.writeFloat(this.f15279h);
            parcel.writeList(this.f15280i);
            parcel.writeFloat(this.f15281j);
            parcel.writeBooleanArray(new boolean[]{this.f15282k});
        }

        SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        private SliderState(Parcel parcel) {
            super(parcel);
            this.f15278c = parcel.readFloat();
            this.f15279h = parcel.readFloat();
            ArrayList arrayList = new ArrayList();
            this.f15280i = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.f15281j = parcel.readFloat();
            this.f15282k = parcel.createBooleanArray()[0];
        }
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String A(float f2) {
        if (J()) {
            return this.formatter.a(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", Float.valueOf(f2));
    }

    private void A0() {
        if (this.valueFrom >= this.valueTo) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_VALUE_FROM, Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)));
        }
    }

    private float[] B() {
        float floatValue = this.values.get(0).floatValue();
        ArrayList<Float> arrayList = this.values;
        float floatValue2 = arrayList.get(arrayList.size() - 1).floatValue();
        if (this.values.size() == 1) {
            floatValue = this.valueFrom;
        }
        float b0 = b0(floatValue);
        float b02 = b0(floatValue2);
        return Q() ? new float[]{b02, b0} : new float[]{b0, b02};
    }

    private void B0() {
        if (this.valueTo <= this.valueFrom) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_VALUE_TO, Float.valueOf(this.valueTo), Float.valueOf(this.valueFrom)));
        }
    }

    private static float C(ValueAnimator valueAnimator, float f2) {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return f2;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        valueAnimator.cancel();
        return floatValue;
    }

    private void C0() {
        Iterator<Float> it = this.values.iterator();
        while (it.hasNext()) {
            Float next = it.next();
            if (next.floatValue() < this.valueFrom || next.floatValue() > this.valueTo) {
                throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_VALUE, next, Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)));
            }
            if (this.stepSize > 0.0f && !D0(next.floatValue())) {
                throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_DISCRETE_VALUE, next, Float.valueOf(this.valueFrom), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)));
            }
        }
    }

    private float D(int i2, float f2) {
        float minSeparation = getMinSeparation();
        if (this.separationUnit == 0) {
            minSeparation = q(minSeparation);
        }
        if (Q()) {
            minSeparation = -minSeparation;
        }
        int i3 = i2 + 1;
        int i4 = i2 - 1;
        return MathUtils.a(f2, i4 < 0 ? this.valueFrom : this.values.get(i4).floatValue() + minSeparation, i3 >= this.values.size() ? this.valueTo : this.values.get(i3).floatValue() - minSeparation);
    }

    private boolean D0(float f2) {
        return O(new BigDecimal(Float.toString(f2)).subtract(new BigDecimal(Float.toString(this.valueFrom)), MathContext.DECIMAL64).doubleValue());
    }

    private int E(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    private float E0(float f2) {
        return (b0(f2) * this.trackWidth) + this.trackSidePadding;
    }

    private float[] F(float f2, float f3) {
        return new float[]{f2, f2, f3, f3, f3, f3, f2, f2};
    }

    private void F0() {
        float f2 = this.stepSize;
        if (f2 == 0.0f) {
            return;
        }
        if (((int) f2) != f2) {
            Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, "stepSize", Float.valueOf(f2)));
        }
        float f3 = this.valueFrom;
        if (((int) f3) != f3) {
            Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, "valueFrom", Float.valueOf(f3)));
        }
        float f4 = this.valueTo;
        if (((int) f4) != f4) {
            Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, "valueTo", Float.valueOf(f4)));
        }
    }

    private float G() {
        double o0 = o0(this.touchPosition);
        if (Q()) {
            o0 = 1.0d - o0;
        }
        float f2 = this.valueTo;
        return (float) ((o0 * (f2 - r4)) + this.valueFrom);
    }

    private float H() {
        float f2 = this.touchPosition;
        if (Q()) {
            f2 = 1.0f - f2;
        }
        float f3 = this.valueTo;
        float f4 = this.valueFrom;
        return (f2 * (f3 - f4)) + f4;
    }

    private boolean I() {
        return this.thumbTrackGapSize > 0;
    }

    private Drawable K(Drawable drawable) {
        Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
        h(newDrawable);
        return newDrawable;
    }

    private void L() {
        this.inactiveTrackPaint.setStrokeWidth(this.trackHeight);
        this.activeTrackPaint.setStrokeWidth(this.trackHeight);
    }

    private boolean M() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private static boolean N(MotionEvent motionEvent) {
        return motionEvent.getToolType(0) == 3;
    }

    private boolean O(double d2) {
        double doubleValue = new BigDecimal(Double.toString(d2)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(doubleValue)) - doubleValue) < THRESHOLD;
    }

    private boolean P(MotionEvent motionEvent) {
        return !N(motionEvent) && M();
    }

    private boolean R() {
        Rect rect = new Rect();
        ViewUtils.j(this).getHitRect(rect);
        return getLocalVisibleRect(rect);
    }

    private void S(Resources resources) {
        this.minWidgetHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.minTrackSidePadding = dimensionPixelOffset;
        this.trackSidePadding = dimensionPixelOffset;
        this.defaultThumbRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.defaultTrackHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_track_height);
        this.defaultTickActiveRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.defaultTickInactiveRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.minTickSpacing = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_min_spacing);
        this.labelPadding = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
    }

    private void T() {
        if (this.stepSize <= 0.0f) {
            return;
        }
        x0();
        int min = Math.min((int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f), (this.trackWidth / this.minTickSpacing) + 1);
        float[] fArr = this.ticksCoordinates;
        if (fArr == null || fArr.length != min * 2) {
            this.ticksCoordinates = new float[min * 2];
        }
        float f2 = this.trackWidth / (min - 1);
        for (int i2 = 0; i2 < min * 2; i2 += 2) {
            float[] fArr2 = this.ticksCoordinates;
            fArr2[i2] = this.trackSidePadding + ((i2 / 2.0f) * f2);
            fArr2[i2 + 1] = m();
        }
    }

    private void U(Canvas canvas, int i2, int i3) {
        if (m0()) {
            canvas.drawCircle((int) (this.trackSidePadding + (b0(this.values.get(this.focusedThumbIdx).floatValue()) * i2)), i3, this.haloRadius, this.haloPaint);
        }
    }

    private void V(Canvas canvas, int i2) {
        if (this.trackStopIndicatorSize <= 0) {
            return;
        }
        if (this.values.size() >= 1) {
            ArrayList<Float> arrayList = this.values;
            float floatValue = arrayList.get(arrayList.size() - 1).floatValue();
            float f2 = this.valueTo;
            if (floatValue < f2) {
                canvas.drawPoint(E0(f2), i2, this.stopIndicatorPaint);
            }
        }
        if (this.values.size() > 1) {
            float floatValue2 = this.values.get(0).floatValue();
            float f3 = this.valueFrom;
            if (floatValue2 > f3) {
                canvas.drawPoint(E0(f3), i2, this.stopIndicatorPaint);
            }
        }
    }

    private void W(Canvas canvas) {
        if (!this.tickVisible || this.stepSize <= 0.0f) {
            return;
        }
        float[] B = B();
        int ceil = (int) Math.ceil(B[0] * ((this.ticksCoordinates.length / 2.0f) - 1.0f));
        int floor = (int) Math.floor(B[1] * ((this.ticksCoordinates.length / 2.0f) - 1.0f));
        if (ceil > 0) {
            canvas.drawPoints(this.ticksCoordinates, 0, ceil * 2, this.inactiveTicksPaint);
        }
        if (ceil <= floor) {
            canvas.drawPoints(this.ticksCoordinates, ceil * 2, ((floor - ceil) + 1) * 2, this.activeTicksPaint);
        }
        int i2 = (floor + 1) * 2;
        float[] fArr = this.ticksCoordinates;
        if (i2 < fArr.length) {
            canvas.drawPoints(fArr, i2, fArr.length - i2, this.inactiveTicksPaint);
        }
    }

    private boolean X() {
        int max = this.minTrackSidePadding + Math.max(Math.max(Math.max((this.thumbWidth / 2) - this.defaultThumbRadius, 0), Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0)), Math.max(Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0), Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0)));
        if (this.trackSidePadding == max) {
            return false;
        }
        this.trackSidePadding = max;
        if (!ViewCompat.N(this)) {
            return true;
        }
        v0(getWidth());
        return true;
    }

    private boolean Y() {
        int max = Math.max(this.minWidgetHeight, Math.max(this.trackHeight + getPaddingTop() + getPaddingBottom(), this.thumbHeight + getPaddingTop() + getPaddingBottom()));
        if (max == this.widgetHeight) {
            return false;
        }
        this.widgetHeight = max;
        return true;
    }

    private boolean Z(int i2) {
        int i3 = this.focusedThumbIdx;
        int c2 = (int) MathUtils.c(i3 + i2, 0L, this.values.size() - 1);
        this.focusedThumbIdx = c2;
        if (c2 == i3) {
            return false;
        }
        if (this.activeThumbIdx != -1) {
            this.activeThumbIdx = c2;
        }
        s0();
        postInvalidate();
        return true;
    }

    private boolean a0(int i2) {
        if (Q()) {
            i2 = i2 == Integer.MIN_VALUE ? Api.BaseClientBuilder.API_PRIORITY_OTHER : -i2;
        }
        return Z(i2);
    }

    private float b0(float f2) {
        float f3 = this.valueFrom;
        float f4 = (f2 - f3) / (this.valueTo - f3);
        return Q() ? 1.0f - f4 : f4;
    }

    private Boolean c0(int i2, KeyEvent keyEvent) {
        if (i2 == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(Z(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(Z(-1)) : Boolean.FALSE;
        }
        if (i2 != 66) {
            if (i2 != 81) {
                if (i2 == 69) {
                    Z(-1);
                    return Boolean.TRUE;
                }
                if (i2 != 70) {
                    switch (i2) {
                        case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                            a0(-1);
                            break;
                        case 22:
                            a0(1);
                            break;
                    }
                    return Boolean.TRUE;
                }
            }
            Z(1);
            return Boolean.TRUE;
        }
        this.activeThumbIdx = this.focusedThumbIdx;
        postInvalidate();
        return Boolean.TRUE;
    }

    private void d0() {
        Iterator<T> it = this.touchListeners.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void e0() {
        Iterator<T> it = this.touchListeners.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    private void g0(TooltipDrawable tooltipDrawable, float f2) {
        int b0 = (this.trackSidePadding + ((int) (b0(f2) * this.trackWidth))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int m2 = m() - (this.labelPadding + (this.thumbHeight / 2));
        tooltipDrawable.setBounds(b0, m2 - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + b0, m2);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.c(ViewUtils.j(this), this, rect);
        tooltipDrawable.setBounds(rect);
    }

    private void h(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
        } else {
            float max = Math.max(this.thumbWidth, this.thumbHeight) / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * max), (int) (intrinsicHeight * max));
        }
    }

    private void h0(Context context, AttributeSet attributeSet, int i2) {
        TypedArray i3 = ThemeEnforcement.i(context, attributeSet, R.styleable.Slider, i2, DEF_STYLE_RES, new int[0]);
        this.labelStyle = i3.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip);
        this.valueFrom = i3.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.valueTo = i3.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.valueFrom));
        this.stepSize = i3.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        this.minTouchTargetSize = (int) Math.ceil(i3.getDimension(R.styleable.Slider_minTouchTargetSize, (float) Math.ceil(ViewUtils.h(getContext(), MIN_TOUCH_TARGET_DP))));
        boolean hasValue = i3.hasValue(R.styleable.Slider_trackColor);
        int i4 = hasValue ? R.styleable.Slider_trackColor : R.styleable.Slider_trackColorInactive;
        int i5 = hasValue ? R.styleable.Slider_trackColor : R.styleable.Slider_trackColorActive;
        ColorStateList a2 = MaterialResources.a(context, i3, i4);
        if (a2 == null) {
            a2 = AppCompatResources.a(context, R.color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(a2);
        ColorStateList a3 = MaterialResources.a(context, i3, i5);
        if (a3 == null) {
            a3 = AppCompatResources.a(context, R.color.material_slider_active_track_color);
        }
        setTrackActiveTintList(a3);
        this.defaultThumbDrawable.a0(MaterialResources.a(context, i3, R.styleable.Slider_thumbColor));
        if (i3.hasValue(R.styleable.Slider_thumbStrokeColor)) {
            setThumbStrokeColor(MaterialResources.a(context, i3, R.styleable.Slider_thumbStrokeColor));
        }
        setThumbStrokeWidth(i3.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList a4 = MaterialResources.a(context, i3, R.styleable.Slider_haloColor);
        if (a4 == null) {
            a4 = AppCompatResources.a(context, R.color.material_slider_halo_color);
        }
        setHaloTintList(a4);
        this.tickVisible = i3.getBoolean(R.styleable.Slider_tickVisible, true);
        boolean hasValue2 = i3.hasValue(R.styleable.Slider_tickColor);
        int i6 = hasValue2 ? R.styleable.Slider_tickColor : R.styleable.Slider_tickColorInactive;
        int i7 = hasValue2 ? R.styleable.Slider_tickColor : R.styleable.Slider_tickColorActive;
        ColorStateList a5 = MaterialResources.a(context, i3, i6);
        if (a5 == null) {
            a5 = AppCompatResources.a(context, R.color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(a5);
        ColorStateList a6 = MaterialResources.a(context, i3, i7);
        if (a6 == null) {
            a6 = AppCompatResources.a(context, R.color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(a6);
        setThumbTrackGapSize(i3.getDimensionPixelSize(R.styleable.Slider_thumbTrackGapSize, 0));
        setTrackStopIndicatorSize(i3.getDimensionPixelSize(R.styleable.Slider_trackStopIndicatorSize, 0));
        setTrackInsideCornerSize(i3.getDimensionPixelSize(R.styleable.Slider_trackInsideCornerSize, 0));
        int dimensionPixelSize = i3.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0) * 2;
        int dimensionPixelSize2 = i3.getDimensionPixelSize(R.styleable.Slider_thumbWidth, dimensionPixelSize);
        int dimensionPixelSize3 = i3.getDimensionPixelSize(R.styleable.Slider_thumbHeight, dimensionPixelSize);
        setThumbWidth(dimensionPixelSize2);
        setThumbHeight(dimensionPixelSize3);
        setHaloRadius(i3.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(i3.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(i3.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        setTickActiveRadius(i3.getDimensionPixelSize(R.styleable.Slider_tickRadiusActive, this.trackStopIndicatorSize / 2));
        setTickInactiveRadius(i3.getDimensionPixelSize(R.styleable.Slider_tickRadiusInactive, this.trackStopIndicatorSize / 2));
        setLabelBehavior(i3.getInt(R.styleable.Slider_labelBehavior, 0));
        if (!i3.getBoolean(R.styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        i3.recycle();
    }

    private void i(TooltipDrawable tooltipDrawable) {
        tooltipDrawable.A0(ViewUtils.j(this));
    }

    private void i0(int i2) {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender = this.accessibilityEventSender;
        if (accessibilityEventSender == null) {
            this.accessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(accessibilityEventSender);
        }
        this.accessibilityEventSender.a(i2);
        postDelayed(this.accessibilityEventSender, 200L);
    }

    private Float j(int i2) {
        float l2 = this.isLongPress ? l(20) : k();
        if (i2 == 21) {
            if (!Q()) {
                l2 = -l2;
            }
            return Float.valueOf(l2);
        }
        if (i2 == 22) {
            if (Q()) {
                l2 = -l2;
            }
            return Float.valueOf(l2);
        }
        if (i2 == 69) {
            return Float.valueOf(-l2);
        }
        if (i2 == 70 || i2 == 81) {
            return Float.valueOf(l2);
        }
        return null;
    }

    private void j0(TooltipDrawable tooltipDrawable, float f2) {
        tooltipDrawable.C0(A(f2));
        g0(tooltipDrawable, f2);
        ViewUtils.k(this).a(tooltipDrawable);
    }

    private float k() {
        float f2 = this.stepSize;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    private void k0(ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.values.size() == arrayList.size() && this.values.equals(arrayList)) {
            return;
        }
        this.values = arrayList;
        this.dirtyConfig = true;
        this.focusedThumbIdx = 0;
        s0();
        o();
        s();
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float l(int i2) {
        float k2 = k();
        return (this.valueTo - this.valueFrom) / k2 <= i2 ? k2 : Math.round(r1 / r2) * k2;
    }

    private boolean l0() {
        return this.labelBehavior == 3;
    }

    private int m() {
        return (this.widgetHeight / 2) + ((this.labelBehavior == 1 || l0()) ? this.labels.get(0).getIntrinsicHeight() : 0);
    }

    private boolean m0() {
        return this.forceDrawCompatHalo || !(getBackground() instanceof RippleDrawable);
    }

    private ValueAnimator n(boolean z) {
        int f2;
        TimeInterpolator g2;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(C(z ? this.labelsOutAnimator : this.labelsInAnimator, z ? 0.0f : 1.0f), z ? 1.0f : 0.0f);
        if (z) {
            f2 = MotionUtils.f(getContext(), LABEL_ANIMATION_ENTER_DURATION_ATTR, DEFAULT_LABEL_ANIMATION_ENTER_DURATION);
            g2 = MotionUtils.g(getContext(), LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.f13818e);
        } else {
            f2 = MotionUtils.f(getContext(), LABEL_ANIMATION_EXIT_DURATION_ATTR, DEFAULT_LABEL_ANIMATION_EXIT_DURATION);
            g2 = MotionUtils.g(getContext(), LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.f13816c);
        }
        ofFloat.setDuration(f2);
        ofFloat.setInterpolator(g2);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.slider.BaseSlider.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                Iterator it = BaseSlider.this.labels.iterator();
                while (it.hasNext()) {
                    ((TooltipDrawable) it.next()).B0(floatValue);
                }
                ViewCompat.Z(BaseSlider.this);
            }
        });
        return ofFloat;
    }

    private boolean n0(float f2) {
        return p0(this.activeThumbIdx, f2);
    }

    private void o() {
        if (this.labels.size() > this.values.size()) {
            List<TooltipDrawable> subList = this.labels.subList(this.values.size(), this.labels.size());
            for (TooltipDrawable tooltipDrawable : subList) {
                if (ViewCompat.M(this)) {
                    p(tooltipDrawable);
                }
            }
            subList.clear();
        }
        while (true) {
            if (this.labels.size() >= this.values.size()) {
                break;
            }
            TooltipDrawable u0 = TooltipDrawable.u0(getContext(), null, 0, this.labelStyle);
            this.labels.add(u0);
            if (ViewCompat.M(this)) {
                i(u0);
            }
        }
        int i2 = this.labels.size() != 1 ? 1 : 0;
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            it.next().m0(i2);
        }
    }

    private double o0(float f2) {
        float f3 = this.stepSize;
        if (f3 <= 0.0f) {
            return f2;
        }
        return Math.round(f2 * r2) / ((int) ((this.valueTo - this.valueFrom) / f3));
    }

    private void p(TooltipDrawable tooltipDrawable) {
        ViewOverlayImpl k2 = ViewUtils.k(this);
        if (k2 != null) {
            k2.b(tooltipDrawable);
            tooltipDrawable.w0(ViewUtils.j(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p0(int i2, float f2) {
        this.focusedThumbIdx = i2;
        if (Math.abs(f2 - this.values.get(i2).floatValue()) < THRESHOLD) {
            return false;
        }
        this.values.set(i2, Float.valueOf(D(i2, f2)));
        r(i2);
        return true;
    }

    private float q(float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f3 = (f2 - this.trackSidePadding) / this.trackWidth;
        float f4 = this.valueFrom;
        return (f3 * (f4 - this.valueTo)) + f4;
    }

    private boolean q0() {
        return n0(G());
    }

    private void r(int i2) {
        Iterator<L> it = this.changeListeners.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.values.get(i2).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        i0(i2);
    }

    private void s() {
        for (L l2 : this.changeListeners) {
            Iterator<Float> it = this.values.iterator();
            while (it.hasNext()) {
                l2.a(this, it.next().floatValue(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0() {
        if (m0() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int b0 = (int) ((b0(this.values.get(this.focusedThumbIdx).floatValue()) * this.trackWidth) + this.trackSidePadding);
            int m2 = m();
            int i2 = this.haloRadius;
            DrawableCompat.l(background, b0 - i2, m2 - i2, b0 + i2, m2 + i2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void t(android.graphics.Canvas r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.t(android.graphics.Canvas, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t0() {
        int i2 = this.labelBehavior;
        if (i2 == 0 || i2 == 1) {
            if (this.activeThumbIdx == -1 || !isEnabled()) {
                y();
                return;
            } else {
                x();
                return;
            }
        }
        if (i2 == 2) {
            y();
            return;
        }
        if (i2 != 3) {
            throw new IllegalArgumentException("Unexpected labelBehavior: " + this.labelBehavior);
        }
        if (isEnabled() && R()) {
            x();
        } else {
            y();
        }
    }

    private void u(Canvas canvas, int i2, int i3) {
        float[] B = B();
        float f2 = i2;
        float f3 = this.trackSidePadding + (B[1] * f2);
        if (f3 < r1 + i2) {
            if (I()) {
                float f4 = i3;
                int i4 = this.trackHeight;
                this.trackRect.set(f3 + this.thumbTrackGapSize, f4 - (i4 / 2.0f), this.trackSidePadding + i2 + (i4 / 2.0f), f4 + (i4 / 2.0f));
                u0(canvas, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.RIGHT);
            } else {
                this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
                this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
                float f5 = i3;
                canvas.drawLine(f3, f5, this.trackSidePadding + i2, f5, this.inactiveTrackPaint);
            }
        }
        int i5 = this.trackSidePadding;
        float f6 = i5 + (B[0] * f2);
        if (f6 > i5) {
            if (!I()) {
                this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
                this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
                float f7 = i3;
                canvas.drawLine(this.trackSidePadding, f7, f6, f7, this.inactiveTrackPaint);
                return;
            }
            RectF rectF = this.trackRect;
            float f8 = this.trackSidePadding;
            int i6 = this.trackHeight;
            float f9 = i3;
            rectF.set(f8 - (i6 / 2.0f), f9 - (i6 / 2.0f), f6 - this.thumbTrackGapSize, f9 + (i6 / 2.0f));
            u0(canvas, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.LEFT);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void u0(android.graphics.Canvas r9, android.graphics.Paint r10, android.graphics.RectF r11, com.google.android.material.slider.BaseSlider.FullCornerDirection r12) {
        /*
            r8 = this;
            int r0 = r8.trackHeight
            float r1 = (float) r0
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            float r0 = (float) r0
            float r0 = r0 / r2
            int[] r3 = com.google.android.material.slider.BaseSlider.AnonymousClass3.f15273a
            int r4 = r12.ordinal()
            r4 = r3[r4]
            r5 = 3
            r6 = 2
            r7 = 1
            if (r4 == r7) goto L22
            if (r4 == r6) goto L1e
            if (r4 == r5) goto L1a
            goto L26
        L1a:
            int r1 = r8.trackInsideCornerSize
            float r1 = (float) r1
            goto L26
        L1e:
            int r0 = r8.trackInsideCornerSize
        L20:
            float r0 = (float) r0
            goto L26
        L22:
            int r0 = r8.trackInsideCornerSize
            float r1 = (float) r0
            goto L20
        L26:
            android.graphics.Paint$Style r4 = android.graphics.Paint.Style.FILL
            r10.setStyle(r4)
            android.graphics.Paint$Cap r4 = android.graphics.Paint.Cap.BUTT
            r10.setStrokeCap(r4)
            r10.setAntiAlias(r7)
            android.graphics.Path r4 = r8.trackPath
            r4.reset()
            float r4 = r11.width()
            float r7 = r1 + r0
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 < 0) goto L53
            android.graphics.Path r12 = r8.trackPath
            float[] r0 = r8.F(r1, r0)
            android.graphics.Path$Direction r1 = android.graphics.Path.Direction.CW
            r12.addRoundRect(r11, r0, r1)
            android.graphics.Path r8 = r8.trackPath
            r9.drawPath(r8, r10)
            goto Lac
        L53:
            float r4 = java.lang.Math.min(r1, r0)
            float r0 = java.lang.Math.max(r1, r0)
            r9.save()
            android.graphics.Path r1 = r8.trackPath
            android.graphics.Path$Direction r7 = android.graphics.Path.Direction.CW
            r1.addRoundRect(r11, r4, r4, r7)
            android.graphics.Path r1 = r8.trackPath
            r9.clipPath(r1)
            int r12 = r12.ordinal()
            r12 = r3[r12]
            if (r12 == r6) goto L97
            if (r12 == r5) goto L88
            android.graphics.RectF r12 = r8.cornerRect
            float r1 = r11.centerX()
            float r1 = r1 - r0
            float r2 = r11.top
            float r3 = r11.centerX()
            float r3 = r3 + r0
            float r11 = r11.bottom
            r12.set(r1, r2, r3, r11)
            goto La4
        L88:
            android.graphics.RectF r12 = r8.cornerRect
            float r1 = r11.right
            float r2 = r2 * r0
            float r2 = r1 - r2
            float r3 = r11.top
            float r11 = r11.bottom
            r12.set(r2, r3, r1, r11)
            goto La4
        L97:
            android.graphics.RectF r12 = r8.cornerRect
            float r1 = r11.left
            float r3 = r11.top
            float r2 = r2 * r0
            float r2 = r2 + r1
            float r11 = r11.bottom
            r12.set(r1, r3, r2, r11)
        La4:
            android.graphics.RectF r8 = r8.cornerRect
            r9.drawRoundRect(r8, r0, r0, r10)
            r9.restore()
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.u0(android.graphics.Canvas, android.graphics.Paint, android.graphics.RectF, com.google.android.material.slider.BaseSlider$FullCornerDirection):void");
    }

    private void v(Canvas canvas, int i2, int i3, float f2, Drawable drawable) {
        canvas.save();
        canvas.translate((this.trackSidePadding + ((int) (b0(f2) * i2))) - (drawable.getBounds().width() / 2.0f), i3 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    private void v0(int i2) {
        this.trackWidth = Math.max(i2 - (this.trackSidePadding * 2), 0);
        T();
    }

    private void w(Canvas canvas, int i2, int i3) {
        for (int i4 = 0; i4 < this.values.size(); i4++) {
            float floatValue = this.values.get(i4).floatValue();
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                v(canvas, i2, i3, floatValue, drawable);
            } else if (i4 < this.customThumbDrawablesForValues.size()) {
                v(canvas, i2, i3, floatValue, this.customThumbDrawablesForValues.get(i4));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle(this.trackSidePadding + (b0(floatValue) * i2), i3, getThumbRadius(), this.thumbPaint);
                }
                v(canvas, i2, i3, floatValue, this.defaultThumbDrawable);
            }
        }
    }

    private void w0() {
        boolean Y = Y();
        boolean X = X();
        if (Y) {
            requestLayout();
        } else if (X) {
            postInvalidate();
        }
    }

    private void x() {
        if (!this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = true;
            ValueAnimator n2 = n(true);
            this.labelsInAnimator = n2;
            this.labelsOutAnimator = null;
            n2.start();
        }
        Iterator<TooltipDrawable> it = this.labels.iterator();
        for (int i2 = 0; i2 < this.values.size() && it.hasNext(); i2++) {
            if (i2 != this.focusedThumbIdx) {
                j0(it.next(), this.values.get(i2).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.labels.size()), Integer.valueOf(this.values.size())));
        }
        j0(it.next(), this.values.get(this.focusedThumbIdx).floatValue());
    }

    private void x0() {
        if (this.dirtyConfig) {
            A0();
            B0();
            z0();
            C0();
            y0();
            F0();
            this.dirtyConfig = false;
        }
    }

    private void y() {
        if (this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator n2 = n(false);
            this.labelsOutAnimator = n2;
            this.labelsInAnimator = null;
            n2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.slider.BaseSlider.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewOverlayImpl k2 = ViewUtils.k(BaseSlider.this);
                    Iterator it = BaseSlider.this.labels.iterator();
                    while (it.hasNext()) {
                        k2.b((TooltipDrawable) it.next());
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    private void y0() {
        float minSeparation = getMinSeparation();
        if (minSeparation < 0.0f) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_MIN_SEPARATION, Float.valueOf(minSeparation)));
        }
        float f2 = this.stepSize;
        if (f2 <= 0.0f || minSeparation <= 0.0f) {
            return;
        }
        if (this.separationUnit != 1) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT, Float.valueOf(minSeparation), Float.valueOf(this.stepSize)));
        }
        if (minSeparation < f2 || !O(minSeparation)) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE, Float.valueOf(minSeparation), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)));
        }
    }

    private void z(int i2) {
        if (i2 == 1) {
            Z(Api.BaseClientBuilder.API_PRIORITY_OTHER);
            return;
        }
        if (i2 == 2) {
            Z(Integer.MIN_VALUE);
        } else if (i2 == 17) {
            a0(Api.BaseClientBuilder.API_PRIORITY_OTHER);
        } else {
            if (i2 != 66) {
                return;
            }
            a0(Integer.MIN_VALUE);
        }
    }

    private void z0() {
        if (this.stepSize > 0.0f && !D0(this.valueTo)) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_STEP_SIZE, Float.valueOf(this.stepSize), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)));
        }
    }

    public boolean J() {
        return this.formatter != null;
    }

    final boolean Q() {
        return ViewCompat.v(this) == 1;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.accessibilityHelper.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(E(this.trackColorInactive));
        this.activeTrackPaint.setColor(E(this.trackColorActive));
        this.inactiveTicksPaint.setColor(E(this.tickColorInactive));
        this.activeTicksPaint.setColor(E(this.tickColorActive));
        this.stopIndicatorPaint.setColor(E(this.trackColorActive));
        for (TooltipDrawable tooltipDrawable : this.labels) {
            if (tooltipDrawable.isStateful()) {
                tooltipDrawable.setState(getDrawableState());
            }
        }
        if (this.defaultThumbDrawable.isStateful()) {
            this.defaultThumbDrawable.setState(getDrawableState());
        }
        this.haloPaint.setColor(E(this.haloColor));
        this.haloPaint.setAlpha(HALO_ALPHA);
    }

    protected boolean f0() {
        if (this.activeThumbIdx != -1) {
            return true;
        }
        float H = H();
        float E0 = E0(H);
        this.activeThumbIdx = 0;
        float abs = Math.abs(this.values.get(0).floatValue() - H);
        for (int i2 = 1; i2 < this.values.size(); i2++) {
            float abs2 = Math.abs(this.values.get(i2).floatValue() - H);
            float E02 = E0(this.values.get(i2).floatValue());
            if (Float.compare(abs2, abs) > 0) {
                break;
            }
            boolean z = !Q() ? E02 - E0 >= 0.0f : E02 - E0 <= 0.0f;
            if (Float.compare(abs2, abs) < 0) {
                this.activeThumbIdx = i2;
            } else {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else {
                    if (Math.abs(E02 - E0) < this.scaledTouchSlop) {
                        this.activeThumbIdx = -1;
                        return false;
                    }
                    if (z) {
                        this.activeThumbIdx = i2;
                    }
                }
            }
            abs = abs2;
        }
        return this.activeThumbIdx != -1;
    }

    @VisibleForTesting
    void forceDrawCompatHalo(boolean z) {
        this.forceDrawCompatHalo = z;
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    @VisibleForTesting
    final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.x();
    }

    public int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    public int getFocusedThumbIndex() {
        return this.focusedThumbIdx;
    }

    public int getHaloRadius() {
        return this.haloRadius;
    }

    public ColorStateList getHaloTintList() {
        return this.haloColor;
    }

    public int getLabelBehavior() {
        return this.labelBehavior;
    }

    protected float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public float getThumbElevation() {
        return this.defaultThumbDrawable.w();
    }

    public int getThumbHeight() {
        return this.thumbHeight;
    }

    public int getThumbRadius() {
        return this.thumbWidth / 2;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.defaultThumbDrawable.E();
    }

    public float getThumbStrokeWidth() {
        return this.defaultThumbDrawable.G();
    }

    public ColorStateList getThumbTintList() {
        return this.defaultThumbDrawable.x();
    }

    public int getThumbTrackGapSize() {
        return this.thumbTrackGapSize;
    }

    public int getThumbWidth() {
        return this.thumbWidth;
    }

    public int getTickActiveRadius() {
        return this.tickActiveRadius;
    }

    public ColorStateList getTickActiveTintList() {
        return this.tickColorActive;
    }

    public int getTickInactiveRadius() {
        return this.tickInactiveRadius;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.tickColorInactive;
    }

    public ColorStateList getTickTintList() {
        if (this.tickColorInactive.equals(this.tickColorActive)) {
            return this.tickColorActive;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.trackColorActive;
    }

    public int getTrackHeight() {
        return this.trackHeight;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.trackColorInactive;
    }

    public int getTrackInsideCornerSize() {
        return this.trackInsideCornerSize;
    }

    public int getTrackSidePadding() {
        return this.trackSidePadding;
    }

    public int getTrackStopIndicatorSize() {
        return this.trackStopIndicatorSize;
    }

    public ColorStateList getTrackTintList() {
        if (this.trackColorInactive.equals(this.trackColorActive)) {
            return this.trackColorActive;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.trackWidth;
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    public float getValueTo() {
        return this.valueTo;
    }

    @NonNull
    List<Float> getValues() {
        return new ArrayList(this.values);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            i(it.next());
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender = this.accessibilityEventSender;
        if (accessibilityEventSender != null) {
            removeCallbacks(accessibilityEventSender);
        }
        this.labelsAreAnimatedIn = false;
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            p(it.next());
        }
        getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.dirtyConfig) {
            x0();
            T();
        }
        super.onDraw(canvas);
        int m2 = m();
        float floatValue = this.values.get(0).floatValue();
        ArrayList<Float> arrayList = this.values;
        float floatValue2 = arrayList.get(arrayList.size() - 1).floatValue();
        if (floatValue2 < this.valueTo || (this.values.size() > 1 && floatValue > this.valueFrom)) {
            u(canvas, this.trackWidth, m2);
        }
        if (floatValue2 > this.valueFrom) {
            t(canvas, this.trackWidth, m2);
        }
        W(canvas);
        V(canvas, m2);
        if ((this.thumbIsPressed || isFocused()) && isEnabled()) {
            U(canvas, this.trackWidth, m2);
        }
        t0();
        w(canvas, this.trackWidth, m2);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z) {
            z(i2);
            this.accessibilityHelper.V(this.focusedThumbIdx);
        } else {
            this.activeThumbIdx = -1;
            this.accessibilityHelper.o(this.focusedThumbIdx);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (this.values.size() == 1) {
            this.activeThumbIdx = 0;
        }
        if (this.activeThumbIdx == -1) {
            Boolean c0 = c0(i2, keyEvent);
            return c0 != null ? c0.booleanValue() : super.onKeyDown(i2, keyEvent);
        }
        this.isLongPress |= keyEvent.isLongPress();
        Float j2 = j(i2);
        if (j2 != null) {
            if (n0(this.values.get(this.activeThumbIdx).floatValue() + j2.floatValue())) {
                s0();
                postInvalidate();
            }
            return true;
        }
        if (i2 != 23) {
            if (i2 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return Z(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return Z(-1);
                }
                return false;
            }
            if (i2 != 66) {
                return super.onKeyDown(i2, keyEvent);
            }
        }
        this.activeThumbIdx = -1;
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        this.isLongPress = false;
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(this.widgetHeight + ((this.labelBehavior == 1 || l0()) ? this.labels.get(0).getIntrinsicHeight() : 0), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.valueFrom = sliderState.f15278c;
        this.valueTo = sliderState.f15279h;
        k0(sliderState.f15280i);
        this.stepSize = sliderState.f15281j;
        if (sliderState.f15282k) {
            requestFocus();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.f15278c = this.valueFrom;
        sliderState.f15279h = this.valueTo;
        sliderState.f15280i = new ArrayList(this.values);
        sliderState.f15281j = this.stepSize;
        sliderState.f15282k = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        v0(i2);
        s0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0033, code lost:
    
        if (r2 != 3) goto L56;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        ViewOverlayImpl k2;
        super.onVisibilityChanged(view, i2);
        if (i2 == 0 || (k2 = ViewUtils.k(this)) == null) {
            return;
        }
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            k2.b(it.next());
        }
    }

    void r0(int i2, Rect rect) {
        int b0 = this.trackSidePadding + ((int) (b0(getValues().get(i2).floatValue()) * this.trackWidth));
        int m2 = m();
        int max = Math.max(this.thumbWidth / 2, this.minTouchTargetSize / 2);
        int max2 = Math.max(this.thumbHeight / 2, this.minTouchTargetSize / 2);
        rect.set(b0 - max, m2 - max2, b0 + max, m2 + max2);
    }

    protected void setActiveThumbIndex(int i2) {
        this.activeThumbIdx = i2;
    }

    void setCustomThumbDrawable(int i2) {
        setCustomThumbDrawable(getResources().getDrawable(i2));
    }

    void setCustomThumbDrawablesForValues(@NonNull @DrawableRes int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            drawableArr[i2] = getResources().getDrawable(iArr[i2]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i2) {
        if (i2 < 0 || i2 >= this.values.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.focusedThumbIdx = i2;
        this.accessibilityHelper.V(i2);
        postInvalidate();
    }

    public void setHaloRadius(int i2) {
        if (i2 == this.haloRadius) {
            return;
        }
        this.haloRadius = i2;
        Drawable background = getBackground();
        if (m0() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            DrawableUtils.m((RippleDrawable) background, this.haloRadius);
        }
    }

    public void setHaloRadiusResource(int i2) {
        setHaloRadius(getResources().getDimensionPixelSize(i2));
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.haloColor)) {
            return;
        }
        this.haloColor = colorStateList;
        Drawable background = getBackground();
        if (!m0() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.haloPaint.setColor(E(colorStateList));
        this.haloPaint.setAlpha(HALO_ALPHA);
        invalidate();
    }

    public void setLabelBehavior(int i2) {
        if (this.labelBehavior != i2) {
            this.labelBehavior = i2;
            requestLayout();
        }
    }

    public void setLabelFormatter(LabelFormatter labelFormatter) {
        this.formatter = labelFormatter;
    }

    protected void setSeparationUnit(int i2) {
        this.separationUnit = i2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setStepSize(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException(String.format(EXCEPTION_ILLEGAL_STEP_SIZE, Float.valueOf(f2), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)));
        }
        if (this.stepSize != f2) {
            this.stepSize = f2;
            this.dirtyConfig = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f2) {
        this.defaultThumbDrawable.Z(f2);
    }

    public void setThumbElevationResource(int i2) {
        setThumbElevation(getResources().getDimension(i2));
    }

    public void setThumbHeight(int i2) {
        if (i2 == this.thumbHeight) {
            return;
        }
        this.thumbHeight = i2;
        this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, i2);
        Drawable drawable = this.customThumbDrawable;
        if (drawable != null) {
            h(drawable);
        }
        Iterator<Drawable> it = this.customThumbDrawablesForValues.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
        w0();
    }

    public void setThumbHeightResource(int i2) {
        setThumbHeight(getResources().getDimensionPixelSize(i2));
    }

    public void setThumbRadius(int i2) {
        int i3 = i2 * 2;
        setThumbWidth(i3);
        setThumbHeight(i3);
    }

    public void setThumbRadiusResource(int i2) {
        setThumbRadius(getResources().getDimensionPixelSize(i2));
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.defaultThumbDrawable.l0(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(int i2) {
        if (i2 != 0) {
            setThumbStrokeColor(AppCompatResources.a(getContext(), i2));
        }
    }

    public void setThumbStrokeWidth(float f2) {
        this.defaultThumbDrawable.m0(f2);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(int i2) {
        if (i2 != 0) {
            setThumbStrokeWidth(getResources().getDimension(i2));
        }
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.defaultThumbDrawable.x())) {
            return;
        }
        this.defaultThumbDrawable.a0(colorStateList);
        invalidate();
    }

    public void setThumbTrackGapSize(int i2) {
        if (this.thumbTrackGapSize == i2) {
            return;
        }
        this.thumbTrackGapSize = i2;
        invalidate();
    }

    public void setThumbWidth(int i2) {
        if (i2 == this.thumbWidth) {
            return;
        }
        this.thumbWidth = i2;
        this.defaultThumbDrawable.setShapeAppearanceModel(ShapeAppearanceModel.a().q(0, this.thumbWidth / 2.0f).m());
        this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
        Drawable drawable = this.customThumbDrawable;
        if (drawable != null) {
            h(drawable);
        }
        Iterator<Drawable> it = this.customThumbDrawablesForValues.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
        w0();
    }

    public void setThumbWidthResource(int i2) {
        setThumbWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setTickActiveRadius(int i2) {
        if (this.tickActiveRadius != i2) {
            this.tickActiveRadius = i2;
            this.activeTicksPaint.setStrokeWidth(i2 * 2);
            w0();
        }
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.tickColorActive)) {
            return;
        }
        this.tickColorActive = colorStateList;
        this.activeTicksPaint.setColor(E(colorStateList));
        invalidate();
    }

    public void setTickInactiveRadius(int i2) {
        if (this.tickInactiveRadius != i2) {
            this.tickInactiveRadius = i2;
            this.inactiveTicksPaint.setStrokeWidth(i2 * 2);
            w0();
        }
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.tickColorInactive)) {
            return;
        }
        this.tickColorInactive = colorStateList;
        this.inactiveTicksPaint.setColor(E(colorStateList));
        invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z) {
        if (this.tickVisible != z) {
            this.tickVisible = z;
            postInvalidate();
        }
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.trackColorActive)) {
            return;
        }
        this.trackColorActive = colorStateList;
        this.activeTrackPaint.setColor(E(colorStateList));
        this.stopIndicatorPaint.setColor(E(this.trackColorActive));
        invalidate();
    }

    public void setTrackHeight(int i2) {
        if (this.trackHeight != i2) {
            this.trackHeight = i2;
            L();
            w0();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.trackColorInactive)) {
            return;
        }
        this.trackColorInactive = colorStateList;
        this.inactiveTrackPaint.setColor(E(colorStateList));
        invalidate();
    }

    public void setTrackInsideCornerSize(int i2) {
        if (this.trackInsideCornerSize == i2) {
            return;
        }
        this.trackInsideCornerSize = i2;
        invalidate();
    }

    public void setTrackStopIndicatorSize(int i2) {
        if (this.trackStopIndicatorSize == i2) {
            return;
        }
        this.trackStopIndicatorSize = i2;
        this.stopIndicatorPaint.setStrokeWidth(i2);
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f2) {
        this.valueFrom = f2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValueTo(float f2) {
        this.valueTo = f2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    void setValues(@NonNull Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        k0(arrayList);
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(MaterialThemeOverlay.c(context, attributeSet, i2, DEF_STYLE_RES), attributeSet, i2);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.defaultThumbWidth = -1;
        this.defaultThumbTrackGapSize = -1;
        this.thumbIsPressed = false;
        this.values = new ArrayList<>();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.tickVisible = true;
        this.isLongPress = false;
        this.trackPath = new Path();
        this.trackRect = new RectF();
        this.cornerRect = new RectF();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.defaultThumbDrawable = materialShapeDrawable;
        this.customThumbDrawablesForValues = Collections.emptyList();
        this.separationUnit = 0;
        this.onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.google.android.material.slider.a
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                BaseSlider.this.t0();
            }
        };
        Context context2 = getContext();
        this.inactiveTrackPaint = new Paint();
        this.activeTrackPaint = new Paint();
        Paint paint = new Paint(1);
        this.thumbPaint = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint2 = new Paint(1);
        this.haloPaint = paint2;
        paint2.setStyle(style);
        Paint paint3 = new Paint();
        this.inactiveTicksPaint = paint3;
        Paint.Style style2 = Paint.Style.STROKE;
        paint3.setStyle(style2);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint3.setStrokeCap(cap);
        Paint paint4 = new Paint();
        this.activeTicksPaint = paint4;
        paint4.setStyle(style2);
        paint4.setStrokeCap(cap);
        Paint paint5 = new Paint();
        this.stopIndicatorPaint = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        S(context2.getResources());
        h0(context2, attributeSet, i2);
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.i0(2);
        this.scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        AccessibilityHelper accessibilityHelper = new AccessibilityHelper(this);
        this.accessibilityHelper = accessibilityHelper;
        ViewCompat.i0(this, accessibilityHelper);
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    void setCustomThumbDrawable(Drawable drawable) {
        this.customThumbDrawable = K(drawable);
        this.customThumbDrawablesForValues.clear();
        postInvalidate();
    }

    void setValues(@NonNull List<Float> list) {
        k0(new ArrayList(list));
    }

    void setCustomThumbDrawablesForValues(@NonNull Drawable... drawableArr) {
        this.customThumbDrawable = null;
        this.customThumbDrawablesForValues = new ArrayList();
        for (Drawable drawable : drawableArr) {
            this.customThumbDrawablesForValues.add(K(drawable));
        }
        postInvalidate();
    }
}
