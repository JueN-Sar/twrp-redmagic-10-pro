package com.zte.mifavor.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.extres.R;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes2.dex */
public class RippleDrawableZTE extends LayerDrawable {
    private static final boolean DEBUG = false;
    private static final int POST_DURATION = 180;
    private static final int PRE_DURATION = 180;
    private final String TAG;
    PathInterpolator fastEaseInterpolator;
    private int mCircleX;
    private int mCircleY;
    private long mClickTime;
    private boolean mIsInited;
    private boolean mIsMonkey;
    private boolean mIsPressed;
    private int mOrigAlpha;
    private Paint mPaint;
    private int mRadius;

    @Nullable
    private ObjectAnimator mRippleEnterAnim;
    private RippleState mState;
    private String mStringID;
    private long mUnclickTime;

    @Nullable
    private ValueAnimator valueAnimatorExit;

    private class RippleState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        private Drawable.ConstantState f17744a;

        /* renamed from: b, reason: collision with root package name */
        private ColorStateList f17745b;

        /* renamed from: c, reason: collision with root package name */
        private int f17746c;

        public RippleState(RippleDrawableZTE rippleDrawableZTE, RippleState rippleState, Drawable.ConstantState constantState) {
            if (rippleState != null) {
                this.f17745b = rippleState.f17745b;
                this.f17746c = rippleState.f17746c;
            }
            this.f17744a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            Drawable.ConstantState constantState = this.f17744a;
            if (constantState != null) {
                return constantState.getChangingConfigurations();
            }
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            RippleDrawableZTE rippleDrawableZTE = new RippleDrawableZTE();
            rippleDrawableZTE.mState = this;
            return rippleDrawableZTE;
        }
    }

    public RippleDrawableZTE() {
        this(new Drawable[]{new ColorDrawable(0)});
        String obj = toString();
        if (obj != null) {
            this.mStringID = "[" + obj.substring(obj.length() - 4) + "] ";
        }
        this.mIsMonkey = Utils.w();
    }

    private void createRippleEnterAnim() {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "radius", (int) (this.mState.f17746c * 0.65d), this.mState.f17746c);
        this.mRippleEnterAnim = ofInt;
        if (ofInt != null) {
            ofInt.setInterpolator(this.fastEaseInterpolator);
            this.mRippleEnterAnim.setDuration(180L);
            this.mRippleEnterAnim.addListener(new Animator.AnimatorListener() { // from class: com.zte.mifavor.widget.RippleDrawableZTE.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    Log.w("RippleDrawableZTE", RippleDrawableZTE.this.mStringID + "onAnimationCancel Enter Anim ===== value Animator Exit. ");
                    RippleDrawableZTE.this.setAlpha(0);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    RippleDrawableZTE rippleDrawableZTE = RippleDrawableZTE.this;
                    rippleDrawableZTE.setRadius(rippleDrawableZTE.mState.f17746c);
                    RippleDrawableZTE.this.createRippleExitAnim(RippleDrawableZTE.this.mPaint.getAlpha());
                    if (RippleDrawableZTE.this.mIsPressed || RippleDrawableZTE.this.valueAnimatorExit == null) {
                        return;
                    }
                    RippleDrawableZTE.this.valueAnimatorExit.start();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    RippleDrawableZTE.this.mPaint.setAlpha(RippleDrawableZTE.this.mOrigAlpha);
                    RippleDrawableZTE.this.valueAnimatorExit = null;
                }
            });
        } else {
            Log.w("RippleDrawableZTE", this.mStringID + "create Ripple Enter Anim warning. mRipple Enter Anim is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createRippleExitAnim(int i2) {
        if (i2 <= 0 || i2 > 255) {
            i2 = this.mOrigAlpha;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, 0);
        this.valueAnimatorExit = ofInt;
        if (ofInt != null) {
            ofInt.setDuration(180L);
            this.valueAnimatorExit.setInterpolator(new LinearInterpolator());
            this.valueAnimatorExit.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.widget.RippleDrawableZTE.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    if (intValue == 0) {
                        RippleDrawableZTE.this.mIsPressed = false;
                    }
                    RippleDrawableZTE.this.setAlpha(intValue);
                }
            });
        } else {
            Log.w("RippleDrawableZTE", this.mStringID + "+++++++++++ create Ripple Exit Anim warnning. value Animator Exit is null. ");
        }
    }

    private int dp2px(int i2) {
        return (int) TypedValue.applyDimension(1, i2, Resources.getSystem().getDisplayMetrics());
    }

    private boolean isEnterAnimRunning() {
        ObjectAnimator objectAnimator = this.mRippleEnterAnim;
        return objectAnimator != null && objectAnimator.isRunning();
    }

    private boolean isExitAnimatorRunning() {
        ValueAnimator valueAnimator = this.valueAnimatorExit;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    private TypedArray obtainAttributesZTE(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mIsMonkey) {
            return;
        }
        canvas.drawCircle(this.mCircleX, this.mCircleY, this.mRadius, this.mPaint);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributesZTE = obtainAttributesZTE(resources, theme, attributeSet, R.styleable.RippleDrawable);
        if (obtainAttributesZTE.hasValue(R.styleable.RippleDrawable_android_color)) {
            this.mState.f17745b = obtainAttributesZTE.getColorStateList(R.styleable.RippleDrawable_android_color);
        } else {
            this.mState.f17745b = ColorStateList.valueOf(436207616);
        }
        this.mState.f17746c = obtainAttributesZTE.getDimensionPixelSize(R.styleable.RippleDrawable_android_radius, dp2px(24));
        obtainAttributesZTE.recycle();
        super.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    public void initAnim() {
        if (this.mIsInited) {
            return;
        }
        this.mPaint.setColor(this.mState.f17745b.getColorForState(getState(), 436207616));
        this.mOrigAlpha = this.mPaint.getAlpha();
        createRippleEnterAnim();
        this.mIsInited = true;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public boolean isProjected() {
        return true;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public Drawable mutate() {
        super.mutate();
        this.mState = new RippleState(this, this.mState, super.getConstantState());
        return this;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mCircleX = rect.centerX();
        this.mCircleY = rect.centerY();
        if (this.mState.f17746c == -1) {
            this.mState.f17746c = (int) Math.sqrt((rect.width() * rect.width()) + (rect.height() * rect.height()));
        }
        initAnim();
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        if (this.mIsMonkey) {
            return super.onStateChange(iArr);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z = true;
            } else if (i2 == 16842919) {
                z2 = true;
            }
        }
        if (z && this.mIsPressed) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mUnclickTime = currentTimeMillis;
            this.mIsPressed = false;
            long j2 = currentTimeMillis - this.mClickTime;
            if (this.valueAnimatorExit != null) {
                if (isExitAnimatorRunning()) {
                    return super.onStateChange(iArr);
                }
                if (j2 > 180) {
                    this.valueAnimatorExit.start();
                } else {
                    int alpha = this.mPaint.getAlpha();
                    if (alpha >= 0) {
                        this.valueAnimatorExit.start();
                    } else {
                        Log.w("RippleDrawableZTE", this.mStringID + "is unpressed, alpha = " + alpha);
                    }
                }
            }
        }
        if (z && z2) {
            if (isEnterAnimRunning()) {
                return super.onStateChange(iArr);
            }
            this.mClickTime = System.currentTimeMillis();
            this.mIsPressed = true;
            setAlpha(0);
            ObjectAnimator objectAnimator = this.mRippleEnterAnim;
            if (objectAnimator != null) {
                objectAnimator.start();
            }
        } else {
            this.mIsPressed = false;
        }
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    public void setOpacity(float f2) {
        this.mPaint.setAlpha((int) ((this.mOrigAlpha * f2) + 0.5f));
    }

    public void setRadius(int i2) {
        this.mRadius = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(@NonNull int[] iArr) {
        return super.setState(iArr);
    }

    public RippleDrawableZTE(Drawable[] drawableArr) {
        super(drawableArr);
        this.TAG = "RippleDrawableZTE";
        this.fastEaseInterpolator = new PathInterpolator(0.25f, 0.55f, 0.0f, 1.0f);
        this.mIsPressed = false;
        this.mIsInited = false;
        this.mStringID = "";
        this.mIsMonkey = false;
        this.mPaint = new Paint();
        this.mState = new RippleState(this, null, super.getConstantState());
    }
}
