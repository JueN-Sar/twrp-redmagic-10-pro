package cn.nubia.gameassist.dessert.policy.fan;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.RelativeLayout;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.fan.NubiaFanView;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class NubiaFanView extends RelativeLayout {
    private static final int ANIMATION_ALL_TRANSY = 1;
    private static final int ANIMATION_NORMAL_ROTATE = 5;
    private static final int ANIMATION_PARTICLE_ALPHA = 4;
    private static final int ANIMATION_PARTICLE_ROTATE = 2;
    private static final int ANIMATION_PARTICLE_SCALE = 3;
    private static final String TAG = "FanView";
    private View mAniView;
    protected boolean mAnimating;
    private AnimatorSet mAnimators;
    private int mDistanceY;
    private int mDistanceY2;
    private View mFan;
    private Interpolator mLinearPath;
    private View mParticle;
    private PathInterpolator mScalePath;
    private PathInterpolator mTranslatePath;
    protected WindowManager mWindowManager;
    protected WindowManager.LayoutParams mlp;

    /* renamed from: cn.nubia.gameassist.dessert.policy.fan.NubiaFanView$1, reason: invalid class name */
    class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            NubiaFanView.this.j();
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            NubiaFanView.this.getViewTreeObserver().removeOnPreDrawListener(this);
            NubiaFanView.this.getHandler().postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.fan.c
                @Override // java.lang.Runnable
                public final void run() {
                    NubiaFanView.AnonymousClass1.this.b();
                }
            }, 300L);
            return false;
        }
    }

    public NubiaFanView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private ValueAnimator d(final int i2, int i3, int i4, TimeInterpolator timeInterpolator, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setDuration(i3);
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setStartDelay(i4);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.dessert.policy.fan.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NubiaFanView.this.h(i2, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.gameassist.dessert.policy.fan.NubiaFanView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (i2 == 1 && animator.getStartDelay() > 2000) {
                    NubiaFanView.this.e();
                }
            }
        });
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
    
        if (r1 == null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e() {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "finish "
            r0.append(r1)
            boolean r1 = r7.mAnimating
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "FanView"
            com.zte.gameassist.utils.GaLog.a(r1, r0)
            boolean r0 = r7.mAnimating
            if (r0 == 0) goto L64
            r0 = 0
            r2 = 0
            r3 = 0
            android.view.WindowManager r4 = r7.mWindowManager     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r4.removeView(r7)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r7.mAnimating = r3
            android.animation.AnimatorSet r1 = r7.mAnimators
            if (r1 == 0) goto L2f
        L2a:
            r1.cancel()
            r7.mAnimators = r2
        L2f:
            android.view.View r7 = r7.mParticle
            r7.setAlpha(r0)
            goto L64
        L35:
            r1 = move-exception
            goto L53
        L37:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L35
            r5.<init>()     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "remove view error:"
            r5.append(r6)     // Catch: java.lang.Throwable -> L35
            r5.append(r4)     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L35
            com.zte.gameassist.utils.GaLog.b(r1, r4)     // Catch: java.lang.Throwable -> L35
            r7.mAnimating = r3
            android.animation.AnimatorSet r1 = r7.mAnimators
            if (r1 == 0) goto L2f
            goto L2a
        L53:
            r7.mAnimating = r3
            android.animation.AnimatorSet r3 = r7.mAnimators
            if (r3 == 0) goto L5e
            r3.cancel()
            r7.mAnimators = r2
        L5e:
            android.view.View r7 = r7.mParticle
            r7.setAlpha(r0)
            throw r1
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.dessert.policy.fan.NubiaFanView.e():void");
    }

    private void f() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tile_clean_slide_distance_y);
        this.mDistanceY = dimensionPixelSize;
        this.mDistanceY2 = dimensionPixelSize - getResources().getDimensionPixelSize(R.dimen.tile_clean_slide_distance_y_difference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(int i2, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (i2 == 1) {
            this.mAniView.setTranslationY(floatValue);
            return;
        }
        if (i2 == 2) {
            GaLog.a(TAG, "particle rotate=" + floatValue);
            this.mParticle.setRotation(floatValue);
            return;
        }
        if (i2 == 3) {
            this.mParticle.setScaleX(floatValue);
            this.mParticle.setScaleY(floatValue);
            return;
        }
        if (i2 == 4) {
            GaLog.a(TAG, "alpha=" + floatValue);
            this.mParticle.setAlpha(floatValue);
            return;
        }
        if (i2 != 5) {
            return;
        }
        GaLog.a(TAG, "fan rotate=" + floatValue);
        this.mFan.setRotation(floatValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimators = animatorSet;
        animatorSet.playSequentially(d(1, 200, 0, this.mTranslatePath, 0.0f, this.mAniView.getHeight() - this.mDistanceY), d(1, 100, 0, this.mTranslatePath, this.mAniView.getHeight() - this.mDistanceY, this.mAniView.getHeight() - this.mDistanceY2), d(1, 350, 2200, this.mTranslatePath, this.mAniView.getHeight() - this.mDistanceY2, this.mAniView.getHeight()));
        this.mAnimators.playTogether(d(3, 200, 300, this.mScalePath, 0.8f, 1.0f), d(4, 200, 300, this.mScalePath, 0.0f, 1.0f), d(2, 4500, 500, this.mLinearPath, 0.0f, 8640.0f));
        this.mAnimators.playSequentially(d(5, 850, 0, this.mLinearPath, 0.0f, 720.0f), d(5, 5851, 0, this.mLinearPath, 720.0f, 7920.0f));
        this.mAnimators.start();
    }

    public boolean g() {
        return this.mAnimating;
    }

    public void i() {
        if (this.mAnimating) {
            return;
        }
        ContextWrapper.updateDisplay(getContext());
        try {
            GaLog.a(TAG, "add view");
            this.mWindowManager.addView(this, this.mlp);
            this.mAnimating = true;
            getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1());
        } catch (Exception e2) {
            GaLog.b(TAG, "add view error:" + e2);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        this.mAniView = findViewById(R.id.view);
        View findViewById = findViewById(R.id.particle);
        this.mParticle = findViewById;
        findViewById.setAlpha(0.0f);
        this.mFan = findViewById(R.id.fan_bg);
    }

    public NubiaFanView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTranslatePath = new PathInterpolator(0.2f, 0.22f, 0.17f, 1.0f);
        this.mScalePath = new PathInterpolator(0.3f, 0.1f, 0.3f, 1.0f);
        this.mLinearPath = new LinearInterpolator();
        setWillNotDraw(false);
        f();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2008, 280);
        this.mlp = layoutParams;
        layoutParams.gravity = 51;
        layoutParams.setTitle("NubiaFan");
        WindowManager.LayoutParams layoutParams2 = this.mlp;
        layoutParams2.format = -2;
        layoutParams2.layoutInDisplayCutoutMode = 3;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams2);
    }
}
