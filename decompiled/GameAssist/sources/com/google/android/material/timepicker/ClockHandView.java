package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.math.MathUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class ClockHandView extends View {
    private static final int DEFAULT_ANIMATION_DURATION = 200;
    private boolean animatingOnTouchUp;
    private final int animationDuration;
    private final TimeInterpolator animationInterpolator;
    private final float centerDotRadius;
    private boolean changedDuringTouch;
    private int circleRadius;
    private int currentLevel;
    private double degRad;
    private float downX;
    private float downY;
    private boolean isInTapRegion;
    private boolean isMultiLevel;
    private final List<OnRotateListener> listeners;
    private OnActionUpListener onActionUpListener;
    private float originalDeg;
    private final Paint paint;
    private final ValueAnimator rotationAnimator;
    private final int scaledTouchSlop;
    private final RectF selectorBox;
    private final int selectorRadius;

    @Px
    private final int selectorStrokeWidth;

    public interface OnActionUpListener {
        void b(float f2, boolean z);
    }

    public interface OnRotateListener {
        void e(float f2, boolean z);
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    private void c(float f2, float f3) {
        this.currentLevel = MathUtils.a((float) (getWidth() / 2), (float) (getHeight() / 2), f2, f3) > ((float) i(2)) + ViewUtils.h(getContext(), 12) ? 1 : 2;
    }

    private void d(Canvas canvas) {
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float f2 = width;
        float i2 = i(this.currentLevel);
        float cos = (((float) Math.cos(this.degRad)) * i2) + f2;
        float f3 = height;
        float sin = (i2 * ((float) Math.sin(this.degRad))) + f3;
        this.paint.setStrokeWidth(0.0f);
        canvas.drawCircle(cos, sin, this.selectorRadius, this.paint);
        double sin2 = Math.sin(this.degRad);
        double cos2 = Math.cos(this.degRad);
        this.paint.setStrokeWidth(this.selectorStrokeWidth);
        canvas.drawLine(f2, f3, width + ((int) (cos2 * r7)), height + ((int) (r7 * sin2)), this.paint);
        canvas.drawCircle(f2, f3, this.centerDotRadius, this.paint);
    }

    private int g(float f2, float f3) {
        int degrees = (int) Math.toDegrees(Math.atan2(f3 - (getHeight() / 2), f2 - (getWidth() / 2)));
        int i2 = degrees + 90;
        return i2 < 0 ? degrees + 450 : i2;
    }

    private int i(int i2) {
        int i3 = this.circleRadius;
        return i2 == 2 ? Math.round(i3 * 0.66f) : i3;
    }

    private Pair k(float f2) {
        float h2 = h();
        if (Math.abs(h2 - f2) > 180.0f) {
            if (h2 > 180.0f && f2 < 180.0f) {
                f2 += 360.0f;
            }
            if (h2 < 180.0f && f2 > 180.0f) {
                h2 += 360.0f;
            }
        }
        return new Pair(Float.valueOf(h2), Float.valueOf(f2));
    }

    private boolean l(float f2, float f3, boolean z, boolean z2, boolean z3) {
        float g2 = g(f2, f3);
        boolean z4 = false;
        boolean z5 = h() != g2;
        if (z2 && z5) {
            return true;
        }
        if (!z5 && !z) {
            return false;
        }
        if (z3 && this.animatingOnTouchUp) {
            z4 = true;
        }
        r(g2, z4);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(ValueAnimator valueAnimator) {
        s(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
    }

    private void s(float f2, boolean z) {
        float f3 = f2 % 360.0f;
        this.originalDeg = f3;
        this.degRad = Math.toRadians(f3 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float i2 = i(this.currentLevel);
        float cos = width + (((float) Math.cos(this.degRad)) * i2);
        float sin = height + (i2 * ((float) Math.sin(this.degRad)));
        RectF rectF = this.selectorBox;
        int i3 = this.selectorRadius;
        rectF.set(cos - i3, sin - i3, cos + i3, sin + i3);
        Iterator<OnRotateListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().e(f3, z);
        }
        invalidate();
    }

    public void b(OnRotateListener onRotateListener) {
        this.listeners.add(onRotateListener);
    }

    int e() {
        return this.currentLevel;
    }

    public RectF f() {
        return this.selectorBox;
    }

    public float h() {
        return this.originalDeg;
    }

    public int j() {
        return this.selectorRadius;
    }

    public void n(boolean z) {
        this.animatingOnTouchUp = z;
    }

    public void o(int i2) {
        this.circleRadius = i2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        d(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.rotationAnimator.isRunning()) {
            return;
        }
        q(h());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        OnActionUpListener onActionUpListener;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.downX = x;
            this.downY = y;
            this.isInTapRegion = true;
            this.changedDuringTouch = false;
            z = false;
            z2 = false;
            z3 = true;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i2 = (int) (x - this.downX);
            int i3 = (int) (y - this.downY);
            this.isInTapRegion = (i2 * i2) + (i3 * i3) > this.scaledTouchSlop;
            boolean z4 = this.changedDuringTouch;
            z = actionMasked == 1;
            if (this.isMultiLevel) {
                c(x, y);
            }
            z3 = false;
            z2 = z4;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        boolean l2 = l(x, y, z2, z3, z) | this.changedDuringTouch;
        this.changedDuringTouch = l2;
        if (l2 && z && (onActionUpListener = this.onActionUpListener) != null) {
            onActionUpListener.b(g(x, y), this.isInTapRegion);
        }
        return true;
    }

    void p(int i2) {
        this.currentLevel = i2;
        invalidate();
    }

    public void q(float f2) {
        r(f2, false);
    }

    public void r(float f2, boolean z) {
        ValueAnimator valueAnimator = this.rotationAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            s(f2, false);
            return;
        }
        Pair k2 = k(f2);
        this.rotationAnimator.setFloatValues(((Float) k2.first).floatValue(), ((Float) k2.second).floatValue());
        this.rotationAnimator.setDuration(this.animationDuration);
        this.rotationAnimator.setInterpolator(this.animationInterpolator);
        this.rotationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.timepicker.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ClockHandView.this.m(valueAnimator2);
            }
        });
        this.rotationAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.timepicker.ClockHandView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                animator.end();
            }
        });
        this.rotationAnimator.start();
    }

    void t(boolean z) {
        if (this.isMultiLevel && !z) {
            this.currentLevel = 1;
        }
        this.isMultiLevel = z;
        invalidate();
    }

    public void u(OnActionUpListener onActionUpListener) {
        this.onActionUpListener = onActionUpListener;
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.rotationAnimator = new ValueAnimator();
        this.listeners = new ArrayList();
        Paint paint = new Paint();
        this.paint = paint;
        this.selectorBox = new RectF();
        this.currentLevel = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockHandView, i2, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.animationDuration = MotionUtils.f(context, R.attr.motionDurationLong2, 200);
        this.animationInterpolator = MotionUtils.g(context, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13815b);
        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0);
        this.selectorRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0);
        this.selectorStrokeWidth = getResources().getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.centerDotRadius = r7.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = obtainStyledAttributes.getColor(R.styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        q(0.0f);
        this.scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewCompat.s0(this, 2);
        obtainStyledAttributes.recycle();
    }
}
