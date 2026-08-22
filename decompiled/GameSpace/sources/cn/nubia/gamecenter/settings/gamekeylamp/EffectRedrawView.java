package cn.nubia.gamecenter.settings.gamekeylamp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
class EffectRedrawView extends View {
    private static final int LAMP_BREATH_FREQUENCE = 750;
    private static final int LAMP_DOUBLE_FREQUENCE = 300;
    public static final int LAMP_EFFECT_ALL_LIGHT = 2;
    public static final int LAMP_EFFECT_BREATH = 3;
    public static final int LAMP_EFFECT_COLOR_EFFECT2 = 114;
    public static final int LAMP_EFFECT_CYCLE_FLASH = 11;
    public static final int LAMP_EFFECT_DOUBLE_FLASH = 5;
    public static final int LAMP_EFFECT_ECHO = 8;
    public static final int LAMP_EFFECT_FLASH = 4;
    public static final int LAMP_EFFECT_FLASHING = 10;
    public static final int LAMP_EFFECT_FLOW = 6;
    public static final int LAMP_EFFECT_HOPPING = 9;
    public static final int LAMP_EFFECT_MECH = 12;
    public static final int LAMP_EFFECT_MUSIC_WITH_LIGHT = 1;
    public static final int LAMP_EFFECT_RIPPLE = 7;
    private static final int LAMP_FLASHING_FREQUENCE = 150;
    private static final int LAMP_FLASHING_SLEEP = 200;
    private static final int LAMP_FLASH_FREQUENCE = 350;
    private static final int LAMP_FLOW_FREQUENCE = 100;
    private static final int LAMP_MUSIC_WITH_LIGHT_FREQUENCE = 200;
    private static final int MECH_PHASE_FLASHING = 1;
    private static final int MECH_PHASE_FLOW = 0;
    private static final long RANDOM_COLOR_201_UPDATE_INTERVAL_MS = 500;
    private static final float ROUND_RECTANGLE_DEGREES = 4.5f;
    private static final String TAG = "EffectRedrawView";
    private AnimatorSet animatorSet;
    private int color_fade_Level;
    private int color_nubia_light_effect_background;
    private int flowCount;
    private volatile Object lock;
    private int mEffectId;
    private Matrix mGradientMatrix;
    private int mHight;
    private long mLastRandomColorUpdateMs;
    private float mMechFlowFraction;
    private int mMechPhase;
    private Paint mPaint;
    private int[] mPaintColors;
    private int[] mRectHegihts;
    private float mScale;
    private int mTranslate;
    private int mWidth;
    private boolean runable;
    private boolean[] signs;
    private Thread thread;

    /* renamed from: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView$4, reason: invalid class name */
    class AnonymousClass4 extends AnimatorListenerAdapter {
        AnonymousClass4() {
        }

        /* renamed from: lambda$onAnimationEnd$0$cn-nubia-gamecenter-settings-gamekeylamp-EffectRedrawView$4, reason: not valid java name */
        /* synthetic */ void m210x840a1600() {
            if (EffectRedrawView.this.runable && EffectRedrawView.this.mEffectId == 12) {
                EffectRedrawView.this.startMechAnimator();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            EffectRedrawView.this.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EffectRedrawView.AnonymousClass4.this.m210x840a1600();
                }
            });
        }
    }

    public EffectRedrawView(Context context) {
        super(context);
        this.flowCount = 1;
        this.mRectHegihts = new int[35];
        this.signs = new boolean[2];
        this.lock = new Object();
    }

    public EffectRedrawView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EffectRedrawView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.flowCount = 1;
        this.mRectHegihts = new int[35];
        this.signs = new boolean[2];
        this.lock = new Object();
    }

    private void drawAllLight(Canvas canvas) {
        canvas.drawRoundRect(0.0f, 0.0f, this.mWidth, this.mHight, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
    }

    private void drawDoubleFlashLight(Canvas canvas) {
        drawAllLight(canvas);
        this.mPaint.setShader(getFadeGradientColor(this.signs[1]));
        canvas.drawRoundRect(0.0f, 0.0f, this.mWidth, this.mHight, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
    }

    private void drawFlowLight(Canvas canvas) {
        LogUtil.d(TAG, "drawFlowLight: color:" + (this.mPaintColors[0] == -16711936));
        drawAllLight(canvas);
        if (this.color_fade_Level == 0) {
            this.color_fade_Level = ContextCompat.getColor(getContext(), R.color.lamp_flow_fade);
        }
        int i = this.color_fade_Level;
        int[] iArr = {i, ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK, i};
        LogUtil.d(TAG, "drawFlowLight, color:" + Arrays.toString(iArr));
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, this.mWidth, 0.0f, iArr, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
        this.mPaint.setShader(linearGradient);
        if (this.mGradientMatrix != null && this.signs[0]) {
            int i2 = this.mTranslate;
            int i3 = this.mWidth;
            int i4 = i2 + (i3 / 4);
            this.mTranslate = i4;
            if (i4 > i3 * 2) {
                this.mTranslate = -i3;
                this.flowCount++;
                maybeUpdateRandomColorsFor201(true);
            }
        }
        this.mGradientMatrix.setTranslate(this.mTranslate, 0.0f);
        if (this.mEffectId == 9 && this.flowCount % 2 == 0) {
            this.mGradientMatrix.setTranslate(this.mWidth - this.mTranslate, 0.0f);
        }
        linearGradient.setLocalMatrix(this.mGradientMatrix);
        canvas.drawRoundRect(0.0f, 0.0f, this.mWidth, this.mHight, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
    }

    private void drawMech(Canvas canvas) {
        if (this.mMechPhase == 0) {
            drawMechFlow(canvas);
        } else {
            drawAllLight(canvas);
        }
    }

    private void drawMechFlow(Canvas canvas) {
        drawAllLight(canvas);
        if (this.color_fade_Level == 0) {
            this.color_fade_Level = ContextCompat.getColor(getContext(), R.color.lamp_flow_fade);
        }
        int i = this.color_fade_Level;
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, this.mWidth, 0.0f, new int[]{i, ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK, i}, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
        this.mPaint.setShader(linearGradient);
        int i2 = this.mWidth;
        int i3 = (int) ((-i2) + (i2 * 3 * this.mMechFlowFraction));
        this.mTranslate = i3;
        Matrix matrix = this.mGradientMatrix;
        if (matrix != null) {
            matrix.setTranslate(i3, 0.0f);
            linearGradient.setLocalMatrix(this.mGradientMatrix);
        }
        canvas.drawRoundRect(0.0f, 0.0f, this.mWidth, this.mHight, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
    }

    private void drawMusicLine(Canvas canvas) {
        double d = this.mWidth / 35.0d;
        int i = this.mHight / 2;
        int i2 = 0;
        while (i2 < 35) {
            if (this.signs[0]) {
                this.mRectHegihts[i2] = (int) (Math.random() * i);
            }
            float f = (float) ((i2 * d) + 2.0d);
            int i3 = i2 + 1;
            float f2 = (float) (i3 * d);
            float f3 = i;
            canvas.drawRoundRect(f, i - this.mRectHegihts[i2], f2, f3, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
            canvas.drawRoundRect(f, f3, f2, this.mRectHegihts[i2] + i, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
            i2 = i3;
        }
    }

    private void drawRipple(Canvas canvas) {
        LogUtil.d(TAG, "drawRipple: color:" + (this.mPaintColors[0] == -16711936));
        drawAllLight(canvas);
        if (this.color_fade_Level == 0) {
            this.color_fade_Level = ContextCompat.getColor(getContext(), R.color.lamp_flow_fade);
        }
        int i = this.color_fade_Level;
        int[] iArr = {i, ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK, i};
        LogUtil.d(TAG, "drawRipple, color:" + Arrays.toString(iArr));
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, this.mWidth, 0.0f, iArr, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
        this.mPaint.setShader(linearGradient);
        if (this.mGradientMatrix != null && this.signs[0]) {
            float f = this.mScale + 0.1f;
            this.mScale = f;
            if (f > 1.0f) {
                this.mScale = 0.1f;
                this.flowCount++;
                maybeUpdateRandomColorsFor201(true);
            }
        }
        this.mGradientMatrix.setScale(this.mScale, 1.0f, this.mWidth / 2.0f, 0.0f);
        if (this.mEffectId == 8 && this.flowCount % 2 == 0) {
            this.mGradientMatrix.setScale(1.0f - this.mScale, 1.0f, this.mWidth / 2.0f, 0.0f);
        }
        linearGradient.setLocalMatrix(this.mGradientMatrix);
        canvas.drawRoundRect(0.0f, 0.0f, this.mWidth, this.mHight, ROUND_RECTANGLE_DEGREES, ROUND_RECTANGLE_DEGREES, this.mPaint);
    }

    private void ensureInitialRandomColorFor201() {
        int[] randomColorsFromCurrentPalette;
        int[] iArr = this.mPaintColors;
        if ((iArr == null || iArr.length <= 0 || isAllBlack(iArr)) && (randomColorsFromCurrentPalette = KeyLampHelper.getInstance().getRandomColorsFromCurrentPalette()) != null && randomColorsFromCurrentPalette.length > 0) {
            this.mPaintColors = randomColorsFromCurrentPalette;
        }
    }

    private LinearGradient getFadeGradientColor(boolean z) {
        int[] iArr;
        if (this.color_fade_Level == 0) {
            this.color_fade_Level = ContextCompat.getColor(getContext(), R.color.lamp_double_flash_fade);
        }
        if (z) {
            int i = this.color_fade_Level;
            iArr = new int[]{0, 0, i, i};
        } else {
            int i2 = this.color_fade_Level;
            iArr = new int[]{i2, i2, 0, 0};
        }
        return new LinearGradient(0.0f, 0.0f, this.mWidth, 0.0f, iArr, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
    }

    private void initFlashAnimation(int i) {
        WeakReference weakReference = new WeakReference(this);
        this.animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(weakReference.get(), AnimatorHelper.Item.ALPHA, 0.15f, 1.0f);
        ofFloat.setDuration(i);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(2);
        ofFloat.setInterpolator(new ThirdBezierInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        this.animatorSet.play(ofFloat);
        this.animatorSet.start();
    }

    private void initFlashingAnimation() {
        WeakReference weakReference = new WeakReference(this);
        this.animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(weakReference.get(), AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ofFloat.setDuration(150L);
        ofFloat.setRepeatCount(2);
        ofFloat.setRepeatMode(2);
        ofFloat.setInterpolator(new ThirdBezierInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        this.animatorSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                EffectRedrawView.this.animatorSet.setStartDelay(200L);
                EffectRedrawView.this.animatorSet.start();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        this.animatorSet.play(ofFloat);
        this.animatorSet.start();
    }

    private static boolean isAllBlack(int[] iArr) {
        if (iArr != null && iArr.length != 0) {
            for (int i : iArr) {
                if (i != -16777216) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isFlowLikeEffect(int i) {
        return i == 6 || i == 9 || i == 7 || i == 8 || i == 114;
    }

    private void maybeUpdateRandomColorsFor201(boolean z) {
        try {
            if (KeyLampHelper.getInstance().isRandomCode(KeyLampHelper.getInstance().getSelectedColorCode())) {
                if (isFlowLikeEffect(this.mEffectId) && !z) {
                    ensureInitialRandomColorFor201();
                    return;
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                if (isFlowLikeEffect(this.mEffectId) || uptimeMillis - this.mLastRandomColorUpdateMs >= 500) {
                    this.mLastRandomColorUpdateMs = uptimeMillis;
                    int[] randomColorsFromCurrentPalette = KeyLampHelper.getInstance().getRandomColorsFromCurrentPalette();
                    if (randomColorsFromCurrentPalette == null || randomColorsFromCurrentPalette.length <= 0) {
                        return;
                    }
                    this.mPaintColors = randomColorsFromCurrentPalette;
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void paintSetColors(int[] iArr) {
        if (iArr == null) {
            iArr = new int[]{SupportMenu.CATEGORY_MASK};
            this.mPaintColors = iArr;
        }
        if (iArr.length == 1) {
            this.mPaint.setColor(iArr[0]);
            this.mPaint.setShader(null);
            return;
        }
        int length = iArr.length;
        int i = length * 2;
        float[] fArr = new float[i];
        int[] iArr2 = new int[i];
        float f = 1.0f / length;
        float min = Math.min(0.001f, 0.5f * f);
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            int i4 = i3 + 1;
            fArr[i3] = i2 * f;
            if (i2 == length - 1) {
                fArr[i4] = 1.0f;
            } else {
                fArr[i4] = ((i2 + 1) * f) - min;
            }
            iArr2[i3] = iArr[i2];
            iArr2[i4] = iArr[i2];
        }
        this.mPaint.setShader(new LinearGradient(0.0f, 0.0f, this.mWidth, 0.0f, iArr2, fArr, Shader.TileMode.CLAMP));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldWait() {
        return this.signs[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMechAnimator() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animatorSet.cancel();
        }
        this.animatorSet = new AnimatorSet();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(1000L);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                EffectRedrawView.this.m208x11aef7a6(valueAnimator);
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ofFloat2.setDuration(150L);
        ofFloat2.setRepeatCount(1);
        ofFloat2.setRepeatMode(2);
        ofFloat2.setInterpolator(new ThirdBezierInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(0.0f, 0.0f);
        ofFloat3.setDuration(200L);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                EffectRedrawView.this.m209x2264c467(valueAnimator);
            }
        });
        animatorSet2.playSequentially(ofFloat2, ofFloat3, ofFloat2.clone(), ofFloat3.clone(), ofFloat2.clone());
        animatorSet2.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                EffectRedrawView.this.setAlpha(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                EffectRedrawView.this.mMechPhase = 1;
                EffectRedrawView.this.postInvalidateOnAnimation();
            }
        });
        this.animatorSet.playSequentially(ofFloat, animatorSet2);
        this.animatorSet.addListener(new AnonymousClass4());
        this.animatorSet.start();
    }

    private void startThreadPaint(final int i) {
        Thread thread = new Thread() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectRedrawView.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (EffectRedrawView.this.runable) {
                    synchronized (EffectRedrawView.this.lock) {
                        while (EffectRedrawView.this.shouldWait()) {
                            try {
                                EffectRedrawView.this.lock.wait(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        EffectRedrawView.this.signs[0] = true;
                        EffectRedrawView.this.signs[1] = !EffectRedrawView.this.signs[1];
                        EffectRedrawView.this.postInvalidate();
                    }
                }
            }
        };
        this.thread = thread;
        thread.start();
    }

    /* renamed from: lambda$startMechAnimator$0$cn-nubia-gamecenter-settings-gamekeylamp-EffectRedrawView, reason: not valid java name */
    /* synthetic */ void m208x11aef7a6(ValueAnimator valueAnimator) {
        this.mMechPhase = 0;
        this.mMechFlowFraction = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        setAlpha(1.0f);
        postInvalidateOnAnimation();
    }

    /* renamed from: lambda$startMechAnimator$1$cn-nubia-gamecenter-settings-gamekeylamp-EffectRedrawView, reason: not valid java name */
    /* synthetic */ void m209x2264c467(ValueAnimator valueAnimator) {
        setAlpha(0.0f);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.runable = false;
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animatorSet.cancel();
        }
        this.mMechFlowFraction = 0.0f;
        this.mMechPhase = 0;
        setAlpha(1.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mWidth = getWidth();
        this.mHight = getHeight();
        this.mPaint = new Paint();
        maybeUpdateRandomColorsFor201(false);
        paintSetColors(this.mPaintColors);
        canvas.drawColor(this.color_nubia_light_effect_background);
        int i = this.mEffectId;
        if (i != 114) {
            switch (i) {
                case 1:
                    drawMusicLine(canvas);
                    break;
                case 2:
                case 3:
                case 4:
                case 10:
                    drawAllLight(canvas);
                    break;
                case 5:
                case 11:
                    drawDoubleFlashLight(canvas);
                    break;
                case 6:
                case 9:
                    break;
                case 7:
                case 8:
                    drawRipple(canvas);
                    break;
                case 12:
                    drawMech(canvas);
                    break;
                default:
                    drawAllLight(canvas);
                    break;
            }
            this.signs[0] = false;
        }
        drawFlowLight(canvas);
        this.signs[0] = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.color_nubia_light_effect_background = ContextCompat.getColor(getContext(), R.color.nubia_light_effect_background);
        this.mGradientMatrix = new Matrix();
    }

    void setEffectType(int i) {
        this.mEffectId = i;
    }

    void setPaintColor(int[] iArr) {
        this.mPaintColors = iArr;
    }

    public void start() {
        if (this.runable) {
            return;
        }
        this.runable = true;
        int i = this.mEffectId;
        if (i != 114) {
            switch (i) {
                case 1:
                    startThreadPaint(200);
                    break;
                case 2:
                    postInvalidate();
                    break;
                case 3:
                    postInvalidate();
                    initFlashAnimation(LAMP_BREATH_FREQUENCE);
                    break;
                case 4:
                    postInvalidate();
                    initFlashAnimation(LAMP_FLASH_FREQUENCE);
                    break;
                case 5:
                case 11:
                    startThreadPaint(300);
                    break;
                case 10:
                    postInvalidate();
                    initFlashingAnimation();
                    break;
                case 12:
                    startMechAnimator();
                    break;
            }
            return;
        }
        startThreadPaint(100);
    }

    public void stop() {
        this.runable = false;
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animatorSet.cancel();
        }
        this.mMechFlowFraction = 0.0f;
        this.mMechPhase = 0;
        setAlpha(1.0f);
    }
}
