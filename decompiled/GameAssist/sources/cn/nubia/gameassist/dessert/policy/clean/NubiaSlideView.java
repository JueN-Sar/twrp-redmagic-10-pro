package cn.nubia.gameassist.dessert.policy.clean;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.function.Supplier;

/* loaded from: classes.dex */
public class NubiaSlideView extends RelativeLayout implements GameMonitor.Callback {
    private static final int ANIMATION_ALL_TRANSY = 1;
    private static final int ANIMATION_ARROWALL_ALPHA = 15;
    private static final int ANIMATION_ARROWALL_TRANSY = 5;
    private static final int ANIMATION_ARROW_ALPHA = 4;
    private static final int ANIMATION_ARROW_SCALE = 3;
    private static final int ANIMATION_CENTER_ROTATEX = 6;
    private static final int ANIMATION_GRID_ROTATE = 2;
    private static final int ANIMATION_HALO_ALPHA = 14;
    private static final int ANIMATION_HALO_RADIUS = 13;
    private static final int ANIMATION_ICON_STATE = 12;
    private static final int ANIMATION_PARTICLE_ALPHA = 8;
    private static final int ANIMATION_PARTICLE_ROTATION = 7;
    private static final int ANIMATION_PROGRESS_DONE = 10;
    private static final int ANIMATION_PROGRESS_DONE_ALPHA = 11;
    private static final int ANIMATION_PROGRESS_TEXT = 9;
    private static final int RADIUS_HALO_RING = 6;
    private static final String TAG = "NubiaSlideView";
    protected boolean isAdded;
    private View mAniView;
    private boolean mAnimating;
    private AnimatorSet mAnimators;
    private View mArrow;
    private View mArrowBg;
    private SlideViewCallback mCallback;
    private View mCenterIcons;
    private String mCleanSize;
    private final Handler mDBHandler;
    private int mDistanceY;
    private int mDistanceY2;
    private View mExpandIcon;
    private CCCleanTextView mExpandText;
    private Supplier<String> mGetMemFunction;
    private View mGridView;
    private HaloView mHaloView;
    private final PathInterpolator mPath;
    private ImageView mProgressFinish;
    private TextView mProgressText;
    protected WindowManager mWindowManager;
    protected WindowManager.LayoutParams mlp;

    /* renamed from: cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView$1, reason: invalid class name */
    class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            NubiaSlideView nubiaSlideView = NubiaSlideView.this;
            if (nubiaSlideView.isAdded) {
                nubiaSlideView.mAnimating = true;
                NubiaSlideView.this.q();
            }
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            NubiaSlideView.this.getViewTreeObserver().removeOnPreDrawListener(this);
            NubiaSlideView.this.getHandler().postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.clean.e
                @Override // java.lang.Runnable
                public final void run() {
                    NubiaSlideView.AnonymousClass1.this.b();
                }
            }, 300L);
            return false;
        }
    }

    public interface SlideViewCallback {
        void a();

        void b();
    }

    public NubiaSlideView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        GaLog.e(TAG, "buildIntoAnimator");
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimators = animatorSet;
        animatorSet.playSequentially(s(1, 200, 0, this.mPath, 0.0f, this.mAniView.getHeight() - this.mDistanceY), s(1, 100, 0, this.mPath, this.mAniView.getHeight() - this.mDistanceY, this.mAniView.getHeight() - this.mDistanceY2), s(1, 350, 3834, this.mPath, this.mAniView.getHeight() - this.mDistanceY2, this.mAniView.getHeight()));
        ValueAnimator s2 = s(4, 850, 150, null, 1.0f, 0.3f, 1.0f);
        s2.setRepeatMode(1);
        s2.setRepeatCount(6);
        this.mAnimators.playTogether(s(2, 850, 150, null, 0.0f, 720.0f), s2, s(3, 350, 300, null, 1.0f, 0.925f, 0.85f, 0.775f, 0.7f, 0.8f, 0.9f, 1.0f), s(6, 150, 850, null, 0.0f, 80.0f), s(6, 150, 1000, null, 80.0f, 180.0f), s(2, 1400, 1000, null, 0.0f, 2880.0f), s(9, 1300, 1000, null, 0.0f, 100.0f), s(12, 350, 2150, this.mPath, 0.0f, 1.0f), s(12, 250, 3500, this.mPath, 1.0f, 0.0f), s(10, 1200, 2300, this.mPath, 0.6f, 1.0f), s(7, 1500, 1000, null, 2880.0f), s(8, 100, 2400, null, 255.0f, 0.0f), s(5, 150, 3500, null, 20.0f, -30.0f), s(5, 100, 3650, null, -30.0f, 0.0f), s(15, 100, 3500, null, 0.0f, 1.0f), s(13, 350, 2467, null, 3.5f, 1.5f), s(14, 100, 3600, null, 255.0f, 0.0f), s(2, 1500, 2534, null, 0.0f, 720.0f));
        this.mAnimators.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public void x() {
        GaLog.e(TAG, "cancel isAdded=" + this.isAdded);
        if (this.isAdded) {
            AnimatorSet animatorSet = this.mAnimators;
            if (animatorSet != null && animatorSet.isRunning()) {
                this.mAnimators.cancel();
            }
            t();
        }
    }

    private ValueAnimator s(final int i2, int i3, int i4, TimeInterpolator timeInterpolator, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setDuration(i3);
        ofFloat.setStartDelay(i4);
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.dessert.policy.clean.d
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NubiaSlideView.this.w(i2, valueAnimator);
            }
        });
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                int i5 = i2;
                if (i5 != 6) {
                    if (i5 != 14) {
                        return;
                    }
                    NubiaSlideView.this.mHaloView.a();
                } else {
                    NubiaSlideView.this.mCenterIcons.setRotationX(0.0f);
                    NubiaSlideView.this.mHaloView.c();
                    NubiaSlideView.this.mHaloView.setHaloRadius(6.0f);
                    NubiaSlideView.this.mHaloView.setHaloAlpha(255);
                    NubiaSlideView.this.mExpandText.setText(NubiaSlideView.this.getResources().getString(R.string.ic_qs_clean_ani_tip, NubiaSlideView.this.mCleanSize));
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i5 = i2;
                if (i5 == 1) {
                    if (animator.getStartDelay() > 3700) {
                        NubiaSlideView.this.t();
                        return;
                    }
                    return;
                }
                if (i5 == 6) {
                    NubiaSlideView.this.mCenterIcons.setRotationX(0.0f);
                    NubiaSlideView.this.mHaloView.c();
                    NubiaSlideView.this.mHaloView.setHaloRadius(6.0f);
                    NubiaSlideView.this.mHaloView.setHaloAlpha(255);
                    if (NubiaSlideView.this.mGetMemFunction != null) {
                        NubiaSlideView nubiaSlideView = NubiaSlideView.this;
                        nubiaSlideView.mCleanSize = (String) nubiaSlideView.mGetMemFunction.get();
                    }
                    NubiaSlideView.this.mExpandText.setText(NubiaSlideView.this.getResources().getString(R.string.ic_qs_clean_ani_tip, NubiaSlideView.this.mCleanSize));
                    return;
                }
                if (i5 == 14) {
                    NubiaSlideView.this.mHaloView.a();
                } else if (i5 == 9) {
                    NubiaSlideView.this.mProgressText.setTextSize(1, 14.0f);
                } else {
                    if (i5 != 10) {
                        return;
                    }
                    NubiaSlideView.this.mProgressFinish.setVisibility(8);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                int i5 = i2;
                if (i5 == 4) {
                    if (NubiaSlideView.this.mCallback != null) {
                        NubiaSlideView.this.mCallback.b();
                        return;
                    }
                    return;
                }
                if (i5 == 5) {
                    NubiaSlideView.this.mArrow.setVisibility(0);
                    NubiaSlideView.this.mArrowBg.setVisibility(0);
                    return;
                }
                if (i5 == 6) {
                    NubiaSlideView.this.mArrow.setVisibility(4);
                    NubiaSlideView.this.mArrowBg.setVisibility(4);
                    NubiaSlideView.this.mProgressText.setAlpha(1.0f);
                    NubiaSlideView.this.mHaloView.setParticleBGAlpha(255);
                    return;
                }
                if (i5 == 9) {
                    NubiaSlideView.this.mProgressText.setVisibility(0);
                    NubiaSlideView.this.mProgressText.setTextSize(1, 14.0f);
                } else {
                    if (i5 != 10) {
                        return;
                    }
                    NubiaSlideView.this.mProgressFinish.setVisibility(0);
                    ((AnimatedVectorDrawable) NubiaSlideView.this.mProgressFinish.getDrawable()).start();
                    if (Settings.Global.getFloat(NubiaSlideView.this.getContext().getContentResolver(), "animator_duration_scale", -1.0f) == 0.0f) {
                        if (NubiaSlideView.this.mGetMemFunction != null) {
                            NubiaSlideView nubiaSlideView = NubiaSlideView.this;
                            nubiaSlideView.mCleanSize = (String) nubiaSlideView.mGetMemFunction.get();
                        }
                        ToastUtil.a(NubiaSlideView.this.getContext().getString(R.string.ic_qs_clean_ani_tip, NubiaSlideView.this.mCleanSize));
                    }
                }
            }
        });
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r0 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        r0.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
    
        r6.mCallback = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0067, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0064, code lost:
    
        if (r0 == null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void t() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "finish isAdded="
            r0.append(r1)
            boolean r1 = r6.isAdded
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "NubiaSlideView"
            com.zte.gameassist.utils.GaLog.e(r1, r0)
            boolean r0 = r6.isAdded
            if (r0 != 0) goto L1d
            return
        L1d:
            r6.y()
            r0 = 0
            r2 = 0
            android.view.WindowManager r3 = r6.mWindowManager     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r3.removeView(r6)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            android.animation.AnimatorSet r1 = r6.mAnimators
            if (r1 == 0) goto L2e
            r1.cancel()
        L2e:
            r6.mAnimators = r2
            r6.isAdded = r0
            r6.mAnimating = r0
            cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView$SlideViewCallback r0 = r6.mCallback
            if (r0 == 0) goto L3b
        L38:
            r0.a()
        L3b:
            r6.mCallback = r2
            goto L67
        L3e:
            r1 = move-exception
            goto L68
        L40:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3e
            r4.<init>()     // Catch: java.lang.Throwable -> L3e
            java.lang.String r5 = "remove view error:"
            r4.append(r5)     // Catch: java.lang.Throwable -> L3e
            r4.append(r3)     // Catch: java.lang.Throwable -> L3e
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L3e
            com.zte.gameassist.utils.GaLog.b(r1, r3)     // Catch: java.lang.Throwable -> L3e
            android.animation.AnimatorSet r1 = r6.mAnimators
            if (r1 == 0) goto L5c
            r1.cancel()
        L5c:
            r6.mAnimators = r2
            r6.isAdded = r0
            r6.mAnimating = r0
            cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView$SlideViewCallback r0 = r6.mCallback
            if (r0 == 0) goto L3b
            goto L38
        L67:
            return
        L68:
            android.animation.AnimatorSet r3 = r6.mAnimators
            if (r3 == 0) goto L6f
            r3.cancel()
        L6f:
            r6.mAnimators = r2
            r6.isAdded = r0
            r6.mAnimating = r0
            cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView$SlideViewCallback r0 = r6.mCallback
            if (r0 == 0) goto L7c
            r0.a()
        L7c:
            r6.mCallback = r2
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView.t():void");
    }

    private void u() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tile_clean_slide_distance_y);
        this.mDistanceY = dimensionPixelSize;
        this.mDistanceY2 = dimensionPixelSize - getResources().getDimensionPixelSize(R.dimen.tile_clean_slide_distance_y_difference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(int i2, ValueAnimator valueAnimator) {
        Float f2 = (Float) valueAnimator.getAnimatedValue();
        float floatValue = f2.floatValue();
        switch (i2) {
            case 1:
                this.mAniView.setTranslationY(floatValue);
                break;
            case 2:
                this.mGridView.setRotation(floatValue);
                break;
            case 3:
                this.mArrow.setScaleX(floatValue);
                this.mArrow.setScaleY(floatValue);
                break;
            case 4:
                this.mArrowBg.setAlpha(floatValue);
                break;
            case 5:
                this.mArrowBg.setTranslationY(floatValue);
                this.mArrow.setTranslationY(floatValue);
                break;
            case 6:
                this.mCenterIcons.setRotationX(floatValue);
                break;
            case 7:
                this.mHaloView.setParticleBGRotation(floatValue);
                break;
            case 8:
                this.mHaloView.setParticleBGAlpha((int) floatValue);
                break;
            case 9:
                if (floatValue >= 100.0f) {
                    this.mProgressText.setVisibility(8);
                    break;
                } else {
                    this.mProgressText.setText(String.format("%.0f%%", f2));
                    break;
                }
            case 12:
                this.mExpandIcon.setTranslationX((r3.getWidth() - this.mExpandText.getWidth()) * floatValue);
                this.mExpandText.setClipTrans((r3.getWidth() * (1.0f - floatValue)) + (this.mExpandIcon.getWidth() * floatValue));
                break;
            case 13:
                this.mHaloView.setHaloRadius(floatValue);
                break;
            case 14:
                this.mHaloView.setHaloAlpha((int) floatValue);
                break;
            case 15:
                this.mArrow.setAlpha(floatValue);
                break;
        }
    }

    private void y() {
        this.mHaloView.setHaloRadius(0.0f);
        this.mProgressText.setTranslationY(0.0f);
        this.mProgressText.setAlpha(0.0f);
        this.mHaloView.setParticleBGAlpha(0);
        this.mArrowBg.setAlpha(1.0f);
        this.mArrow.setAlpha(1.0f);
        this.mArrow.setScaleX(1.0f);
        this.mArrow.setScaleY(1.0f);
        this.mCenterIcons.setRotationX(0.0f);
        this.mGridView.setRotation(0.0f);
        this.mExpandIcon.setTranslationX(0.0f);
        this.mExpandText.setClipTrans(r0.getWidth());
        this.mAniView.setTranslationY(r3.getHeight());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        GaLog.e(TAG, "onAttachedToWindow");
        SystemMgr.y(getContext()).h(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GaLog.e(TAG, "onDetachedFromWindow");
        SystemMgr.y(getContext()).i(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mExpandIcon = findViewById(R.id.icon);
        this.mExpandText = (CCCleanTextView) findViewById(R.id.text);
        this.mAniView = findViewById(R.id.view);
        this.mCenterIcons = findViewById(R.id.center);
        this.mArrow = findViewById(R.id.arrow);
        this.mArrowBg = findViewById(R.id.arrow_bg);
        this.mGridView = findViewById(R.id.grid);
        HaloView haloView = (HaloView) findViewById(R.id.halo);
        this.mHaloView = haloView;
        haloView.setParticleBGAlpha(0);
        this.mProgressText = (TextView) findViewById(R.id.progress);
        this.mProgressFinish = (ImageView) findViewById(R.id.progress_finish);
        this.mProgressText.setAlpha(0.0f);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.mDBHandler.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.clean.c
            @Override // java.lang.Runnable
            public final void run() {
                NubiaSlideView.this.x();
            }
        });
    }

    public void setGetMemFunction(Supplier<String> supplier) {
        this.mGetMemFunction = supplier;
    }

    public boolean v() {
        return this.mAnimating;
    }

    public void z(SlideViewCallback slideViewCallback) {
        if (this.mAnimating) {
            GaLog.k(TAG, "clean animation already play");
            return;
        }
        this.mCallback = slideViewCallback;
        GaLog.e(TAG, "startEnterIntoAnimator");
        ContextWrapper.updateDisplay(getContext());
        try {
            this.mWindowManager.addView(this, this.mlp);
            this.isAdded = true;
            getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1());
        } catch (Exception e2) {
            GaLog.b(TAG, "add view error:" + e2);
        }
    }

    public NubiaSlideView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCleanSize = "1KB";
        this.mPath = new PathInterpolator(0.2f, 0.22f, 0.17f, 1.0f);
        this.isAdded = false;
        setWillNotDraw(false);
        u();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2027, 280);
        this.mlp = layoutParams;
        layoutParams.gravity = 51;
        layoutParams.setTitle("GameAssistClean");
        WindowManager.LayoutParams layoutParams2 = this.mlp;
        layoutParams2.format = -2;
        layoutParams2.layoutInDisplayCutoutMode = 3;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams2);
        this.mDBHandler = new Handler(ThreadManager.c().b());
    }
}
