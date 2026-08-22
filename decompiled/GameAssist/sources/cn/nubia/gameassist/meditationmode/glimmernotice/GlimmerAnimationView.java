package cn.nubia.gameassist.meditationmode.glimmernotice;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerNoticeManager;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GlimmerAnimationView extends View {
    private static final int GLIMMER_WAVE_COUNT = 3;
    private static final int MAX_ALPHA = 76;
    private static final float MAX_RADIUS = 345.0f;
    private static final long SCALE_ANIM_DURATION_TIME = 1360;
    private static final long SCALE_ANIM_START_TIME = 380;
    private static final String TAG = "GlimmerNotice";
    private List<Animator> mAnimatorList;
    private AnimatorSet mAnimatorSet;
    private float[] mCurrentAnimationValues;
    private GlimmerNoticeManager.OnAnimationEndCallbackListener mOnAnimationEndCallBack;
    private Paint[] mPaints;

    public GlimmerAnimationView(Context context) {
        this(context, null);
    }

    private void c() {
        this.mAnimatorSet = new AnimatorSet();
        this.mAnimatorList = new ArrayList();
        this.mAnimatorSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerAnimationView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (GlimmerAnimationView.this.mOnAnimationEndCallBack != null) {
                    GlimmerAnimationView.this.mOnAnimationEndCallBack.a();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        for (int i2 = 0; i2 < 3; i2++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(SCALE_ANIM_DURATION_TIME);
            ofFloat.setRepeatMode(1);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.setStartDelay(i2 * SCALE_ANIM_START_TIME);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerAnimationView.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    GlimmerAnimationView.this.g(valueAnimator);
                }
            });
            this.mAnimatorList.add(ofFloat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(ValueAnimator valueAnimator) {
        long startDelay = valueAnimator.getStartDelay();
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int i2 = 0;
        while (true) {
            float[] fArr = this.mCurrentAnimationValues;
            if (i2 >= fArr.length) {
                invalidate();
                return;
            }
            if (startDelay == 0) {
                fArr[0] = floatValue;
            } else if (startDelay == SCALE_ANIM_START_TIME) {
                fArr[1] = floatValue;
            } else if (startDelay == 760) {
                fArr[2] = floatValue;
            }
            i2++;
        }
    }

    public boolean d() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        return animatorSet != null && animatorSet.isRunning();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mCurrentAnimationValues == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            float[] fArr = this.mCurrentAnimationValues;
            if (i2 >= fArr.length) {
                return;
            }
            this.mPaints[i2].setAlpha((int) ((1.0f - fArr[i2]) * 76.0f));
            canvas.drawCircle(getWidth() / 2, 0.0f, this.mCurrentAnimationValues[i2] * MAX_RADIUS, this.mPaints[i2]);
            i2++;
        }
    }

    public void e() {
        if (this.mAnimatorSet != null) {
            GaLog.a(TAG, "startGlimmerAnimation!");
            this.mAnimatorSet.playTogether(this.mAnimatorList);
            this.mAnimatorSet.start();
        }
    }

    public void f() {
        if (d()) {
            GaLog.a(TAG, "stopGlimmerAnimation!");
            this.mAnimatorSet.cancel();
            this.mAnimatorSet = null;
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public void setOnWindowRemoveCallback(GlimmerNoticeManager.OnAnimationEndCallbackListener onAnimationEndCallbackListener) {
        this.mOnAnimationEndCallBack = onAnimationEndCallbackListener;
    }

    public GlimmerAnimationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlimmerAnimationView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public GlimmerAnimationView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mCurrentAnimationValues = new float[3];
        this.mPaints = new Paint[3];
        this.mAnimatorSet = new AnimatorSet();
        this.mAnimatorList = new ArrayList();
        for (int i4 = 0; i4 < this.mPaints.length; i4++) {
            Paint paint = new Paint(1);
            paint.setColor(Color.parseColor("#FFB500"));
            paint.setAlpha(MAX_ALPHA);
            paint.setStyle(Paint.Style.FILL);
            this.mPaints[i4] = paint;
        }
        c();
    }
}
