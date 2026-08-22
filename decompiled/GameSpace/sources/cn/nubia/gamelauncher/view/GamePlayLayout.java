package cn.nubia.gamelauncher.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.helper.VibratorHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import com.airbnb.lottie.LottieAnimationView;

/* loaded from: classes.dex */
public class GamePlayLayout extends ConstraintLayout implements View.OnClickListener {
    private final int BACK_DURATION;
    private final int EXPAND_DURATION;
    String TAG;
    AnimatorSet mAnimatorSet;
    LottieAnimationView mEnterLottie;
    ZoomButton mHandle;
    private boolean mIsExpand;
    ZoomButton mMirror;
    ZoomButton mMouse;
    String mSelectedPkg;
    GameStartView mStart;
    LottieAnimationView mSwitchView;

    public GamePlayLayout(Context context) {
        this(context, null);
    }

    public GamePlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "GamePlayLayout";
        this.mIsExpand = false;
        this.EXPAND_DURATION = 300;
        this.BACK_DURATION = 400;
        this.mSelectedPkg = null;
        initChild(context);
    }

    private void back() {
        LogUtil.d(this.TAG, "back()");
        LogUtil.d(this.TAG, "back()");
        cancelAnimator();
        boolean z = this.mMirror.getVisibility() == 8;
        ZoomButton zoomButton = this.mMouse;
        if (z) {
            zoomButton = zoomButton.getVisibility() == 8 ? this.mHandle : this.mMouse;
        }
        AnimBean animBean = new AnimBean(View.ALPHA, 1.0f, 0.0f);
        AnimBean animBean2 = new AnimBean(View.TRANSLATION_X, 0.0f, z ? 0.0f : 600.0f);
        AnimBean animBean3 = new AnimBean(View.TRANSLATION_X, 0.0f, 300.0f);
        if (!z) {
            zoomButton = this.mMouse;
        }
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(zoomButton, 400, animBean, animBean2);
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mHandle, 400, animBean, animBean3);
        ObjectAnimator createPropertyAnim3 = AnimHelper.createPropertyAnim(this.mMirror, 400, animBean);
        ObjectAnimator createPropertyAnim4 = AnimHelper.createPropertyAnim(this.mStart, 400, new AnimBean(View.ALPHA, 0.0f, 1.0f));
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        if (z) {
            animatorSet.play(createPropertyAnim).with(createPropertyAnim4);
        } else {
            animatorSet.play(createPropertyAnim).with(createPropertyAnim2).with(createPropertyAnim3).with(createPropertyAnim4);
        }
        this.mAnimatorSet.start();
        doSwitchAnim(false);
        this.mIsExpand = false;
    }

    private void doStartBtnAnim() {
        GameStartView gameStartView = this.mStart;
        if (gameStartView != null) {
            gameStartView.doEnterAnim();
        }
    }

    private void doSwitchAnim(boolean z) {
        this.mSwitchView.cancelAnimation();
        this.mSwitchView.setAnimation(z ? "lottie/switch_expand.json" : "lottie/switch_back.json");
        this.mSwitchView.playAnimation();
    }

    private void doSwitchEnterAnim(LottieAnimationView lottieAnimationView) {
        if (supportExpand()) {
            if (lottieAnimationView == null) {
                loadSwitch();
                return;
            }
            initEnterLottie(lottieAnimationView);
            if (getHandler() != null) {
                getHandler().postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.view.GamePlayLayout$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        GamePlayLayout.this.m337x8c68bfee();
                    }
                }, 800L);
            }
        }
    }

    private void expand() {
        LogUtil.d(this.TAG, "expand()");
        cancelAnimator();
        this.mMirror.setVisibility(0);
        this.mHandle.setVisibility(0);
        this.mMouse.setVisibility(0);
        AnimBean animBean = new AnimBean(View.ALPHA, 0.0f, 1.0f);
        AnimBean animBean2 = new AnimBean(View.TRANSLATION_X, 600.0f, 0.0f);
        AnimBean animBean3 = new AnimBean(View.TRANSLATION_X, 300.0f, 0.0f);
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mMouse, 300, animBean, animBean2);
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mHandle, 300, animBean, animBean3);
        ObjectAnimator createPropertyAnim3 = AnimHelper.createPropertyAnim(this.mMirror, 300, animBean);
        ObjectAnimator createPropertyAnim4 = AnimHelper.createPropertyAnim(this.mStart, 300, new AnimBean(View.ALPHA, 1.0f, 0.0f));
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.play(createPropertyAnim).with(createPropertyAnim2).with(createPropertyAnim3).with(createPropertyAnim4);
        this.mAnimatorSet.start();
        doSwitchAnim(true);
        this.mIsExpand = true;
    }

    private void initChild(Context context) {
        LayoutInflater.from(context).inflate(Controller.getInstance().isPureMode() ? R.layout.game_play_layout_pure : R.layout.game_play_layout, this);
        this.mStart = (GameStartView) findViewById(R.id.start_button);
        this.mMouse = (ZoomButton) findViewById(R.id.play_by_mouse);
        this.mHandle = (ZoomButton) findViewById(R.id.play_by_handle);
        this.mMirror = (ZoomButton) findViewById(R.id.play_by_mirror);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.switch_view);
        this.mSwitchView = lottieAnimationView;
        lottieAnimationView.setCacheComposition(false);
        if (supportExpand()) {
            this.mSwitchView.setOnClickListener(this);
            return;
        }
        this.mSwitchView.setVisibility(8);
        this.mMouse.setAlpha(0.0f);
        this.mHandle.setAlpha(0.0f);
        this.mMirror.setAlpha(0.0f);
    }

    private void initEnterLottie(LottieAnimationView lottieAnimationView) {
        this.mEnterLottie = lottieAnimationView;
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: cn.nubia.gamelauncher.view.GamePlayLayout.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                GamePlayLayout.this.mEnterLottie.setAlpha(0.0f);
                GamePlayLayout.this.loadSwitch();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                GamePlayLayout.this.mEnterLottie.setAlpha(0.0f);
                GamePlayLayout.this.loadSwitch();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSwitch() {
        this.mSwitchView.setVisibility(0);
        this.mSwitchView.setAnimation(this.mIsExpand ? "lottie/switch_expand.json" : "lottie/switch_back.json");
    }

    public void cancelAnimator() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet == null) {
            return;
        }
        animatorSet.cancel();
    }

    public void doEnterAnim(LottieAnimationView lottieAnimationView) {
        LogUtil.d(this.TAG, "doEnterAnim() mEnterLottie : " + this.mEnterLottie);
        lottieAnimationView.setCacheComposition(false);
        doSwitchEnterAnim(lottieAnimationView);
        doStartBtnAnim();
    }

    public void enterSingleMode(boolean z) {
        String str;
        LogUtil.d(this.TAG, "enterOnlyMouseMode()");
        this.mIsExpand = true;
        expand();
        this.mStart.setAlpha(0.0f);
        this.mMirror.setVisibility(8);
        if (z) {
            this.mHandle.setVisibility(8);
            this.mMouse.setAlpha(1.0f);
            str = "xgravity_superbase_kmouse";
        } else {
            this.mMouse.setVisibility(8);
            this.mHandle.setAlpha(1.0f);
            str = "xgravity_superbase_handle";
        }
        if (this.mSelectedPkg != null) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", str, "game_package", this.mSelectedPkg);
        }
        this.mSwitchView.setAnimation("lottie/switch_back.json");
    }

    /* renamed from: lambda$doSwitchEnterAnim$0$cn-nubia-gamelauncher-view-GamePlayLayout, reason: not valid java name */
    /* synthetic */ void m337x8c68bfee() {
        this.mEnterLottie.playAnimation();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.switch_view) {
            return;
        }
        LottieAnimationView lottieAnimationView = this.mEnterLottie;
        if (lottieAnimationView != null) {
            lottieAnimationView.setAlpha(0.0f);
        }
        if (this.mIsExpand) {
            back();
        } else {
            expand();
            if (this.mSelectedPkg == null) {
                return;
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_button_record", "game_package", this.mSelectedPkg);
            }
        }
        LobbySoundPoolHelper.getInstance().play();
        VibratorHelper.getInstance().vibrateSync();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        LogUtil.d(this.TAG, "onVisibilityChanged() visibility : " + i);
        if (i != 0) {
            resetLayout();
        }
    }

    public void resetLayout() {
        LogUtil.d(this.TAG, "resetLayout()");
        cancelAnimator();
        this.mIsExpand = false;
        this.mMirror.setAlpha(0.0f);
        this.mHandle.setAlpha(0.0f);
        this.mMouse.setAlpha(0.0f);
        this.mStart.setAlpha(1.0f);
        doSwitchAnim(false);
    }

    public void setChildEnabled(boolean z) {
        this.mStart.setEnabled(z);
        this.mHandle.setEnabled(z);
        this.mMirror.setEnabled(z);
        this.mMouse.setEnabled(z);
    }

    public void setSelectedPkg(String str) {
        this.mSelectedPkg = str;
    }

    public boolean supportExpand() {
        return GameSpaceConfig.supportPlayMode();
    }
}
