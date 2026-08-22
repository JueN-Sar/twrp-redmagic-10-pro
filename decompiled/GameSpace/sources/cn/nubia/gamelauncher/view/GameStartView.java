package cn.nubia.gamelauncher.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.util.LogUtil;

/* loaded from: classes.dex */
public class GameStartView extends ConstraintLayout implements View.OnTouchListener {
    private final int DOWN_SCALE_ANIM_DURATION;
    private final int ENTER_DURATION;
    private final float MAX_SCALE_VALUE;
    private final float MIN_SCALE_VALUE;
    String TAG;
    private final int UP_SCALE_ANIM_DURATION;
    AnimatorSet mAnimatorSet;
    ImageView mLeft;
    ValueAnimator mPressAnimator;
    ImageView mRight;
    ImageView mStart;
    MarqueeTextView mText;
    private float mValue;

    public GameStartView(Context context) {
        this(context, null);
    }

    public GameStartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "GameStartView";
        this.mValue = 1.0f;
        this.MIN_SCALE_VALUE = 0.8f;
        this.MAX_SCALE_VALUE = 1.0f;
        this.DOWN_SCALE_ANIM_DURATION = 200;
        this.UP_SCALE_ANIM_DURATION = 150;
        this.ENTER_DURATION = 300;
        initChild(context);
    }

    private void doPressAnimator(final float f, int i) {
        ValueAnimator valueAnimator = this.mPressAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mValue, f);
        this.mPressAnimator = ofFloat;
        ofFloat.setDuration(i);
        this.mPressAnimator.start();
        this.mPressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gamelauncher.view.GameStartView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                GameStartView.this.setState(((Float) valueAnimator2.getAnimatedValue()).floatValue());
            }
        });
        this.mPressAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.gamelauncher.view.GameStartView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                GameStartView.this.setState(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                GameStartView.this.setState(f);
            }
        });
    }

    private void initChild(Context context) {
        LayoutInflater.from(context).inflate(R.layout.game_start_button, this);
        this.mLeft = (ImageView) findViewById(R.id.game_start_left);
        this.mRight = (ImageView) findViewById(R.id.game_start_right);
        this.mText = (MarqueeTextView) findViewById(R.id.game_start_text);
        ImageView imageView = (ImageView) findViewById(R.id.game_start);
        this.mStart = imageView;
        imageView.setOnTouchListener(this);
        if (Controller.getInstance().isPureMode()) {
            this.mText.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.game_start_text_size_pure));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetViewByAnimEnd() {
        this.mLeft.setAlpha(1.0f);
        this.mLeft.setTranslationX(0.0f);
        this.mLeft.setScaleX(1.0f);
        this.mLeft.setScaleY(1.0f);
        this.mText.setAlpha(1.0f);
        this.mRight.setAlpha(1.0f);
        this.mRight.setTranslationX(0.0f);
        this.mRight.setScaleX(1.0f);
        this.mRight.setScaleY(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(float f) {
        this.mValue = f;
        setScaleX(f);
        setScaleY(f);
        setAlpha(f);
    }

    public void cancelAnimator() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet == null) {
            return;
        }
        animatorSet.cancel();
    }

    public void doEnterAnim() {
        cancelAnimator();
        AnimBean animBean = new AnimBean(View.ALPHA, 0.0f, 1.0f);
        AnimBean animBean2 = new AnimBean(View.TRANSLATION_X, -55.0f, 0.0f);
        AnimBean animBean3 = new AnimBean(View.TRANSLATION_X, 55.0f, 0.0f);
        AnimBean animBean4 = new AnimBean(View.SCALE_X, 0.5f, 1.0f);
        AnimBean animBean5 = new AnimBean(View.SCALE_Y, 0.5f, 1.0f);
        AnimBean animBean6 = new AnimBean(View.ALPHA, 0.2f, 1.0f);
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mLeft, 300, animBean, animBean2, animBean4, animBean5);
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mRight, 300, animBean, animBean3, animBean4, animBean5);
        ObjectAnimator createPropertyAnim3 = AnimHelper.createPropertyAnim(this.mText, 300, animBean6);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.play(createPropertyAnim).with(createPropertyAnim2).with(createPropertyAnim3);
        this.mAnimatorSet.start();
        this.mAnimatorSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gamelauncher.view.GameStartView.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                GameStartView.this.resetViewByAnimEnd();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                GameStartView.this.resetViewByAnimEnd();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.mStart.isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            LogUtil.d(this.TAG, "onTouchEvent() ACTION_DOWN");
            doPressAnimator(0.8f, 200);
        } else if (action == 1 || action == 3) {
            LogUtil.d(this.TAG, "onTouchEvent() ACTION_UP or ACTION_CANCEL");
            doPressAnimator(1.0f, 150);
        }
        return false;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        LogUtil.d(this.TAG, "onVisibilityChanged() visibility : " + i);
        if (i != 0) {
            cancelAnimator();
        }
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        this.mStart.setAlpha(f);
        if (0.0f == f) {
            setClickable(false);
            this.mStart.setClickable(false);
            this.mStart.setVisibility(8);
        } else {
            this.mStart.setVisibility(0);
            setClickable(true);
            this.mStart.setClickable(true);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mStart.setEnabled(z);
    }
}
