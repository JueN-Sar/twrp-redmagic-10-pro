package cn.nubia.screensaver.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.screensaver.CardContainerController;
import cn.nubia.screensaver.card.BaseCard;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class CardView extends FrameLayout {
    private static final String TAG = "CardView";
    private View mBottomCard;
    private CardContainerController mContainerController;
    private ValueAnimator mFlipCardAnimator;
    private boolean mIsLeftCard;
    private View mMiddleCard;
    private float mMiddleViewTranslationY;
    private final Path mPath;
    private float mRawDownY;
    private float mRawMoveY;
    private int mScaledTouchSlop;
    private View mTopCard;
    private boolean mTouch;
    private boolean mTouchMoving;

    public CardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void f() {
        ValueAnimator valueAnimator = this.mFlipCardAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mFlipCardAnimator = null;
        }
    }

    private void g(float f2, float f3) {
        if (Math.abs(f2 - f3) <= 0.01f || f2 == 0.0f) {
            return;
        }
        if (Math.abs(f2) > Math.abs(f3)) {
            f2 = f3;
        }
        q(f2, f3, false);
    }

    private void h(Context context) {
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.mTopCard.setVisibility(8);
        this.mMiddleCard.setVisibility(0);
        this.mBottomCard.setVisibility(8);
    }

    public void c(BaseCard baseCard) {
        View g2 = baseCard.g(this);
        if (g2.getParent() != null) {
            removeView(g2);
        }
        addView(g2);
    }

    public void d(BaseCard baseCard, int i2) {
        View g2 = baseCard.g(this);
        if (g2.getParent() != null) {
            removeView(g2);
        }
        if (i2 > getChildCount()) {
            addView(g2);
        } else {
            addView(g2, i2);
        }
    }

    public boolean e() {
        return (k() || i()) ? false : true;
    }

    public boolean i() {
        ValueAnimator valueAnimator = this.mFlipCardAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public boolean j() {
        return this.mIsLeftCard;
    }

    public boolean k() {
        return this.mTouch;
    }

    public void l(float f2) {
        View view = this.mTopCard;
        if (view == null || this.mMiddleCard == null || this.mBottomCard == null) {
            GaLog.a(TAG, "card is null");
            return;
        }
        view.setTranslationY(f2 - getHeight());
        this.mMiddleCard.setTranslationY(f2);
        this.mBottomCard.setTranslationY(f2 + getHeight());
    }

    public void m(BaseCard baseCard, boolean z) {
        baseCard.i(j(), z);
    }

    public void n() {
        f();
    }

    public void o(BaseCard baseCard, boolean z) {
        baseCard.k(j(), z);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int childCount = getChildCount();
        if (this.mTouchMoving || childCount != 3) {
            return;
        }
        s();
    }

    public void q(float f2, final float f3, final boolean z) {
        GaLog.a(TAG, "sY:" + f2 + ",eY:" + f3);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        this.mFlipCardAnimator = ofFloat;
        ofFloat.setDuration(200L);
        this.mFlipCardAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.screensaver.view.CardView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CardView.this.l(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.mFlipCardAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.screensaver.view.CardView.2

            /* renamed from: c, reason: collision with root package name */
            boolean f9181c;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.f9181c = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (this.f9181c) {
                    return;
                }
                if (f3 == 0.0f) {
                    CardView.this.p();
                } else {
                    CardView.this.mContainerController.n(CardView.this, f3 < 0.0f, z);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                this.f9181c = false;
            }
        });
        this.mFlipCardAnimator.start();
    }

    public void r(MotionEvent motionEvent) {
        int height;
        int height2;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mTouch = true;
            this.mTouchMoving = false;
            f();
            this.mRawDownY = motionEvent.getRawY();
            this.mTopCard.setVisibility(0);
            this.mBottomCard.setVisibility(0);
            this.mMiddleViewTranslationY = this.mMiddleCard.getTranslationY();
            return;
        }
        float f2 = 0.0f;
        if (action != 1) {
            if (action == 2) {
                float rawY = motionEvent.getRawY();
                this.mRawMoveY = rawY;
                float f3 = this.mMiddleViewTranslationY + (rawY - this.mRawDownY);
                if (f3 > 0.0f) {
                    this.mTopCard.setVisibility(0);
                    this.mBottomCard.setVisibility(8);
                } else {
                    this.mTopCard.setVisibility(8);
                    this.mBottomCard.setVisibility(0);
                }
                l(f3);
                this.mTouchMoving = true;
                return;
            }
            if (action != 3) {
                return;
            }
        }
        this.mTouch = false;
        this.mTouchMoving = false;
        this.mRawMoveY = motionEvent.getRawY();
        float translationY = this.mMiddleCard.getTranslationY();
        float f4 = this.mRawMoveY - this.mRawDownY;
        if (Math.abs(f4) < this.mScaledTouchSlop) {
            if (Math.abs(translationY) > getHeight() / 2.0f) {
                if (translationY > 0.0f) {
                    height2 = getHeight();
                } else {
                    height = getHeight();
                    height2 = -height;
                }
            }
            g(translationY, f2);
        }
        if (f4 <= 0.0f || translationY <= 0.0f) {
            if (f4 < 0.0f && translationY < 0.0f) {
                height = getHeight();
                height2 = -height;
            }
            g(translationY, f2);
        }
        height2 = getHeight();
        f2 = height2;
        g(translationY, f2);
    }

    public void s() {
        this.mTopCard = getChildAt(0);
        this.mMiddleCard = getChildAt(1);
        this.mBottomCard = getChildAt(2);
        this.mTopCard.setTranslationY(-getHeight());
        this.mMiddleCard.setTranslationY(0.0f);
        this.mBottomCard.setTranslationY(getHeight());
        p();
    }

    public void setContainerController(CardContainerController cardContainerController) {
        this.mContainerController = cardContainerController;
    }

    public void setIsLeftCard(boolean z) {
        this.mIsLeftCard = z;
    }

    public void setPath(Path path) {
        this.mPath.reset();
        this.mPath.set(path);
    }

    public CardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPath = new Path();
        this.mMiddleViewTranslationY = 0.0f;
        h(context);
    }
}
