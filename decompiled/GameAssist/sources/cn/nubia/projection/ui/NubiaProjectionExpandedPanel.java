package cn.nubia.projection.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import cn.nubia.projection.ProjectionManager;
import cn.nubia.projection.ProjectionUIController;
import cn.nubia.projection.R;
import cn.nubia.projection.util.PLog;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.Constants;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.mifavor.widget.SwitchZTE;
import com.zte.shared.wrapper.GameAssistControllerWrapper;
import java.io.PrintWriter;
import java.util.Locale;

/* loaded from: classes.dex */
public class NubiaProjectionExpandedPanel extends FrameLayout implements View.OnClickListener {
    public static final Interpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.2f, 0.22f, 0.17f, 1.0f);
    private static final int MIRROR_FIT_TO_DISPLAY = 3;
    public static final int MIRROR_STOP_GAMEBOX = 5;
    public static final String NB_APP_MIRROR_SUPPORT_WHITE_APP = "nb_app_mirror_support_white_app";
    private float mArrowHeight;
    private GameAssistDialog mDialog;
    private LinearLayout mExtendedAppContainer;
    private LinearLayout mGameBoxTouchPadContainer;
    private final Runnable mHandleScreenShotRunnable;
    private ProjectionWindowView mHost;
    private ImageView mIvArrow;
    private ImageView mIvFinishProjection;
    private ImageView mIvSuspend;
    private LinearLayout mLlMoreFeature;
    private LinearLayout mLlPanelContainer;
    private Locale mLocale;
    private FrameLayout mModuleContainer;
    private float mModuleContainerMoveX;
    private int mModuleExpandWidth;
    private float mModuleMarginEnd;
    private int mModuleMirrorWidth;
    private ValueAnimator mModuleSelectAnimator;
    private int mModuleSelectBgWidth;
    private float mMoreFeatureHeight;
    private SwitchZTE mNsRemoveBlack;
    private final Runnable mRadioGroupEnableRunnable;
    private RadioButton mRb2K;
    private RadioButton mRb4K;
    private RadioButton mRbDefault;
    private RadioGroup mRgResolution;
    private LinearLayout mScreenContainer;
    private float mScreenMirrorMarginEnd;
    private float mScreenMirrorMarginStart;
    private final Runnable mSmallWindowAppChangeAnimation;
    private boolean mSupportVirtualHandle;
    private LinearLayout mTouchPadContainer;
    private TextView mTvExtendedApp;
    private TextView mTvGameBoxTouchPad;
    private TextView mTvModuleExpanded;
    private TextView mTvModuleGameBox;
    private TextView mTvModuleMirror;
    private TextView mTvProjectionStatus;
    private TextView mTvRemoveBlackTag;
    private TextView mTvResolutionTag;
    private TextView mTvRestScreen;
    private TextView mTvTouchPad;
    private TextView mTvVirtualHandle;
    private ProjectionUIController mUIControl;
    private View mVModuleSelectBg;
    private LinearLayout mVirtualHandleContainer;
    private int mVirtualHandleTextOffset;

    public NubiaProjectionExpandedPanel(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRadioGroupEnableRunnable = new Runnable() { // from class: cn.nubia.projection.ui.h
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.D();
            }
        };
        this.mSmallWindowAppChangeAnimation = new Runnable() { // from class: cn.nubia.projection.ui.i
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.E();
            }
        };
        this.mHandleScreenShotRunnable = new Runnable() { // from class: cn.nubia.projection.ui.j
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.F();
            }
        };
    }

    private void A() {
        this.mLlPanelContainer = (LinearLayout) findViewById(R.id.ll_panel_container);
        this.mTvModuleExpanded = (TextView) findViewById(R.id.tv_module_expanded);
        this.mTvModuleGameBox = (TextView) findViewById(R.id.tv_module_gamebox);
        this.mTvModuleMirror = (TextView) findViewById(R.id.tv_module_mirror);
        this.mVModuleSelectBg = findViewById(R.id.view_module_select);
        this.mModuleContainer = (FrameLayout) findViewById(R.id.fl_module_container);
        this.mVirtualHandleContainer = (LinearLayout) findViewById(R.id.fl_virtual_handles_container);
        this.mScreenContainer = (LinearLayout) findViewById(R.id.fl_screen_container);
        this.mExtendedAppContainer = (LinearLayout) findViewById(R.id.fl_extended_app_container);
        this.mTouchPadContainer = (LinearLayout) findViewById(R.id.fl_touch_pad_container);
        this.mIvSuspend = (ImageView) findViewById(R.id.iv_projection_suspend);
        this.mIvArrow = (ImageView) findViewById(R.id.iv_extended_arrow);
        this.mLlMoreFeature = (LinearLayout) findViewById(R.id.ll_more_feature);
        this.mTvResolutionTag = (TextView) findViewById(R.id.tv_resolution_tag);
        this.mRgResolution = (RadioGroup) findViewById(R.id.rg_resolution_container);
        this.mRbDefault = (RadioButton) findViewById(R.id.rb_resolution_default);
        this.mRb2K = (RadioButton) findViewById(R.id.rb_resolution_2k);
        this.mRb4K = (RadioButton) findViewById(R.id.rb_resolution_4k);
        this.mNsRemoveBlack = (SwitchZTE) findViewById(R.id.ns_remove_black);
        this.mTvProjectionStatus = (TextView) findViewById(R.id.tv_projection_status);
        this.mIvFinishProjection = (ImageView) findViewById(R.id.iv_finish_projection);
        this.mGameBoxTouchPadContainer = (LinearLayout) findViewById(R.id.fl_game_box_touch_pad_container);
        this.mTvVirtualHandle = (TextView) findViewById(R.id.tv_virtual_handles);
        this.mTvRestScreen = (TextView) findViewById(R.id.tv_screen);
        this.mTvExtendedApp = (TextView) findViewById(R.id.tv_extended_app);
        this.mTvTouchPad = (TextView) findViewById(R.id.tv_touch_pad);
        this.mTvGameBoxTouchPad = (TextView) findViewById(R.id.tv_game_box_touch_pad);
        this.mTvRemoveBlackTag = (TextView) findViewById(R.id.tv_remove_black_tag);
        this.mRbDefault.setOnClickListener(this);
        this.mRb2K.setOnClickListener(this);
        this.mRb4K.setOnClickListener(this);
        this.mNsRemoveBlack.setOnClickListener(this);
        this.mTvModuleExpanded.setOnClickListener(this);
        this.mTvModuleMirror.setOnClickListener(this);
        this.mVirtualHandleContainer.setOnClickListener(this);
        this.mScreenContainer.setOnClickListener(this);
        this.mExtendedAppContainer.setOnClickListener(this);
        this.mTouchPadContainer.setOnClickListener(this);
        this.mGameBoxTouchPadContainer.setOnClickListener(this);
        this.mIvSuspend.setOnClickListener(this);
        this.mIvArrow.setOnClickListener(this);
        this.mIvFinishProjection.setOnClickListener(this);
        this.mTvProjectionStatus.setOnClickListener(this);
        this.mSupportVirtualHandle = ZteFeature.supportVirtualHandle();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.projection_panel_width);
        this.mModuleMirrorWidth = getResources().getDimensionPixelSize(R.dimen.projection_module_mirror_width);
        this.mModuleMarginEnd = getResources().getDimension(R.dimen.projection_mirror_module_margin_end);
        this.mModuleContainerMoveX = getResources().getDimension(R.dimen.projection_module_container_move_x);
        this.mMoreFeatureHeight = getResources().getDimension(R.dimen.projection_more_feature_container_height);
        this.mArrowHeight = getResources().getDimension(R.dimen.projection_more_feature_arrow_height);
        this.mModuleExpandWidth = getResources().getDimensionPixelSize(R.dimen.projection_module_expand_width);
        this.mModuleSelectBgWidth = getResources().getDimensionPixelSize(R.dimen.projection_module_select_bg_width);
        this.mVirtualHandleTextOffset = -getResources().getDimensionPixelSize(R.dimen.projection_module_virtual_handle_text_offset);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mScreenContainer.getLayoutParams();
        if (this.mSupportVirtualHandle) {
            this.mScreenMirrorMarginStart = 0.0f;
            this.mScreenMirrorMarginEnd = this.mModuleMarginEnd;
            this.mVirtualHandleContainer.setVisibility(0);
        } else {
            float f2 = (int) ((dimensionPixelSize - this.mModuleMirrorWidth) / 2.0f);
            this.mScreenMirrorMarginStart = f2;
            this.mScreenMirrorMarginEnd = f2;
            this.mVirtualHandleContainer.setVisibility(8);
        }
        layoutParams.leftMargin = (int) this.mScreenMirrorMarginStart;
        layoutParams.rightMargin = (int) this.mScreenMirrorMarginEnd;
        this.mScreenContainer.setLayoutParams(layoutParams);
        if (ZteFeature.isSprdVendor()) {
            this.mMoreFeatureHeight = getResources().getDimensionPixelSize(R.dimen.projection_more_feature_remove_black__height);
            this.mRgResolution.setVisibility(8);
            this.mTvResolutionTag.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C(boolean z, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (!z) {
            animatedFraction = 1.0f - animatedFraction;
        }
        this.mModuleContainer.setTranslationX((-this.mModuleContainerMoveX) * animatedFraction);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mScreenContainer.getLayoutParams();
        layoutParams.rightMargin = (int) (this.mScreenMirrorMarginEnd * (1.0f - animatedFraction));
        if (this.mSupportVirtualHandle) {
            layoutParams.leftMargin = 0;
        } else {
            float f2 = this.mScreenMirrorMarginStart;
            layoutParams.leftMargin = (int) (f2 + ((f2 - this.mModuleMarginEnd) * animatedFraction));
        }
        layoutParams.width = (int) (this.mModuleMirrorWidth + ((this.mModuleExpandWidth - r0) * animatedFraction));
        this.mScreenContainer.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D() {
        setRadioButtonClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E() {
        r0(true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F() {
        this.mUIControl.S0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G() {
        g0(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H(DialogInterface dialogInterface, int i2) {
        this.mUIControl.W0(3, 1, -1);
        b0(this.mUIControl.Y());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(DialogInterface dialogInterface, int i2) {
        r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(ValueAnimator valueAnimator) {
        this.mIvSuspend.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
        this.mIvSuspend.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(boolean z, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (!z) {
            animatedFraction = 1.0f - animatedFraction;
        }
        ViewGroup.LayoutParams layoutParams = this.mLlMoreFeature.getLayoutParams();
        layoutParams.height = (int) (this.mMoreFeatureHeight * animatedFraction);
        this.mLlMoreFeature.setLayoutParams(layoutParams);
        this.mLlMoreFeature.setAlpha(animatedFraction);
        this.mIvArrow.setRotation(animatedFraction * 180.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void L(View view, float f2, float f3, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f4 = 1.0f - floatValue;
        view.setScaleX(f4);
        view.setScaleY(f4);
        view.setAlpha(f4);
        view.setTranslationX(f2 * floatValue);
        view.setTranslationY(f3 * floatValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(float f2, boolean z, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (f2 > 0.0f) {
            floatValue = 1.0f - floatValue;
        }
        this.mVModuleSelectBg.setTranslationX(this.mModuleSelectBgWidth * floatValue);
        this.mTvVirtualHandle.setTranslationX(this.mVirtualHandleTextOffset * floatValue);
        ViewGroup.LayoutParams layoutParams = this.mIvArrow.getLayoutParams();
        layoutParams.height = (int) (this.mArrowHeight * floatValue);
        this.mIvArrow.setLayoutParams(layoutParams);
        if (valueAnimator.getAnimatedFraction() <= 0.9f || this.mUIControl.t0() == z) {
            return;
        }
        this.mUIControl.a1(z);
        s0();
        l0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(boolean z) {
        r0(z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(int i2, int i3) {
        this.mRbDefault.setEnabled((i2 & 2) != 0);
        this.mRb2K.setEnabled((i2 & 4) != 0);
        this.mRb4K.setEnabled((i2 & 8) != 0);
        this.mNsRemoveBlack.setChecked((i3 & 1) != 0);
        if ((i3 & 2) != 0) {
            this.mRgResolution.check(R.id.rb_resolution_default);
        } else if ((i3 & 4) != 0) {
            this.mRgResolution.check(R.id.rb_resolution_2k);
        } else if ((i3 & 8) != 0) {
            this.mRgResolution.check(R.id.rb_resolution_4k);
        }
    }

    private void P(final boolean z, boolean z2) {
        if (z2) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(200L);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.c
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    NubiaProjectionExpandedPanel.this.C(z, valueAnimator);
                }
            });
            ofFloat.start();
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mScreenContainer.getLayoutParams();
        if (z) {
            this.mModuleContainer.setTranslationX(-this.mModuleContainerMoveX);
            layoutParams.rightMargin = 0;
            if (this.mSupportVirtualHandle) {
                layoutParams.leftMargin = 0;
            } else {
                layoutParams.leftMargin = (int) ((this.mScreenMirrorMarginStart * 2.0f) - this.mModuleMarginEnd);
            }
            layoutParams.width = this.mModuleExpandWidth;
        } else {
            layoutParams.rightMargin = (int) this.mScreenMirrorMarginEnd;
            layoutParams.leftMargin = (int) this.mScreenMirrorMarginStart;
            layoutParams.width = this.mModuleMirrorWidth;
            this.mModuleContainer.setTranslationX(0.0f);
        }
        this.mScreenContainer.setLayoutParams(layoutParams);
    }

    private void Q() {
        if (this.mUIControl.v0()) {
            boolean z = Settings.Global.getInt(getContext().getContentResolver(), "virtual_game_key", 0) == 1;
            if (ZteFeature.isSuppprtRedMagicGameKey() && Constants.f16465e && z) {
                PLog.a("click exit GameBox");
                Settings.Global.putInt(getContext().getContentResolver(), "virtual_game_key", 0);
            }
            ProjectionUIController projectionUIController = this.mUIControl;
            projectionUIController.W0(5, projectionUIController.U(), 0);
        }
    }

    private void R() {
        if (j0()) {
            this.mUIControl.W0(3, this.mNsRemoveBlack.isChecked() ? 1 : 0, -1);
        } else if (this.mNsRemoveBlack.isChecked()) {
            d0();
        } else {
            this.mUIControl.W0(3, 0, -1);
        }
    }

    private void S() {
        f0(this.mIvArrow.getRotation() == 0.0f);
    }

    private void T() {
        boolean x = ProjectionManager.o().x();
        if (this.mUIControl.E0()) {
            this.mUIControl.c1(R.string.close_virtual_to_try);
            return;
        }
        if (this.mUIControl.s0()) {
            this.mUIControl.c1(R.string.not_allowd_small_window_for_cloud_computer);
            return;
        }
        if (SystemMgr.x == 86) {
            this.mUIControl.c1(R.string.not_allowd_small_window_mode_multi_mfv);
        }
        if (x) {
            if (this.mUIControl.C0() && this.mUIControl.A0()) {
                this.mUIControl.n1(false);
            }
            h0();
        }
    }

    private void U() {
        if (this.mUIControl.t0()) {
            if (this.mUIControl.X() <= 0) {
                PLog.a("last display is null when click mirror");
            } else {
                i0(false, true);
                f0(false);
            }
        }
    }

    private void V() {
        try {
            Class.forName("com.redmagic.os.RedMagicAppManager$Trigger").getMethod("openScreenOffTP", Boolean.TYPE).invoke(null, Boolean.TRUE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void W() {
        this.mUIControl.n1(!r0.A0());
        e0();
    }

    private void X() {
        this.mUIControl.W0(9, 0, 0);
        s();
    }

    private void Y() {
        if (this.mUIControl.E0()) {
            return;
        }
        if (this.mHost.G() || !SystemMgr.H()) {
            this.mUIControl.c1(R.string.try_in_land_app);
        } else {
            this.mUIControl.o1();
            s();
        }
    }

    private void a0(TextView textView) {
        textView.setAutoSizeTextTypeWithDefaults(1);
        textView.setAutoSizeTextTypeUniformWithConfiguration(getContext().getResources().getDimensionPixelSize(R.dimen.projection_feature_small_text_size), getContext().getResources().getDimensionPixelSize(R.dimen.projection_feature_text_size), getContext().getResources().getDimensionPixelSize(R.dimen.projection_feature_auto_size_step_granularity), 0);
    }

    private void b0(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, str + "");
        try {
            GameAssistControllerWrapper.invake("restart_package", bundle, new GameAssistControllerWrapper.Callback(this) { // from class: cn.nubia.projection.ui.NubiaProjectionExpandedPanel.5
                @Override // com.zte.shared.wrapper.GameAssistControllerWrapper.Callback
                protected void onCallback(Bundle bundle2) {
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c0(int i2, int i3) {
        setRadioButtonClickable(false);
        removeCallbacks(this.mRadioGroupEnableRunnable);
        postDelayed(this.mRadioGroupEnableRunnable, 2000L);
        this.mUIControl.c1(R.string.projection_resolution_send_cmd_tip);
        this.mUIControl.W0(3, i2, i3);
    }

    private void d0() {
        Context context;
        GameAssistDialog gameAssistDialog = this.mDialog;
        if ((gameAssistDialog == null || !gameAssistDialog.isShowing()) && (context = getContext()) != null) {
            GameAssistDialog g2 = GameAssistDialog.g(context);
            this.mDialog = g2;
            g2.setTitle(context.getString(R.string.projection_module_full_screen_display));
            this.mDialog.setIsShowTitle();
            this.mDialog.setMessage(context.getString(R.string.projection_module_full_screen_display_dialog_content));
            this.mDialog.i(R.string.projection_module_full_screen_display_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.projection.ui.l
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    NubiaProjectionExpandedPanel.this.H(dialogInterface, i2);
                }
            });
            this.mDialog.setButton(-2, context.getString(R.string.monitor_dialog_cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.projection.ui.m
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    NubiaProjectionExpandedPanel.this.I(dialogInterface, i2);
                }
            });
            this.mDialog.show();
        }
    }

    private void e0() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.5f, 1.0f);
        ofFloat.setDuration(200L);
        ofFloat.setInterpolator(new PathInterpolator(0.0f, 0.0f, 0.58f, 1.0f));
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NubiaProjectionExpandedPanel.this.J(valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.NubiaProjectionExpandedPanel.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z) {
                NubiaProjectionExpandedPanel.this.p0();
            }
        });
        ofFloat.start();
    }

    private void g0(int i2) {
        final ImageView W = this.mUIControl.W();
        if (W != null) {
            WindowManager.LayoutParams a0 = this.mUIControl.a0();
            final float width = a0.x + (getWidth() / 2.0f);
            final float height = a0.y + (getHeight() / 2.0f);
            W.setPivotX(0.0f);
            W.setPivotY(0.0f);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(400L);
            ofFloat.setStartDelay(i2);
            ofFloat.setInterpolator(FAST_OUT_SLOW_IN);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.b
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    NubiaProjectionExpandedPanel.L(W, width, height, valueAnimator);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.NubiaProjectionExpandedPanel.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    NubiaProjectionExpandedPanel.this.mUIControl.S0();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    NubiaProjectionExpandedPanel.this.mUIControl.S0();
                }
            });
            ofFloat.start();
        }
    }

    private void h0() {
        this.mUIControl.Z0(true);
        if (this.mUIControl.t0()) {
            if (this.mHost.G()) {
                this.mUIControl.L();
            }
            removeCallbacks(this.mSmallWindowAppChangeAnimation);
            postDelayed(this.mSmallWindowAppChangeAnimation, 200L);
        } else if (!B()) {
            this.mUIControl.J0(true);
            if (this.mHost.G()) {
                this.mUIControl.L();
            }
            i0(true, true);
        }
        removeCallbacks(this.mHandleScreenShotRunnable);
        postDelayed(this.mHandleScreenShotRunnable, 3000L);
    }

    private void i0(final boolean z, boolean z2) {
        P(z, z2);
        if (!z2) {
            ViewGroup.LayoutParams layoutParams = this.mIvArrow.getLayoutParams();
            this.mVModuleSelectBg.setTranslationX(z ? this.mModuleSelectBgWidth : 0.0f);
            this.mTvVirtualHandle.setTranslationX(z ? this.mVirtualHandleTextOffset : 0.0f);
            layoutParams.height = (int) (z ? this.mArrowHeight : 0.0f);
            this.mIvArrow.setLayoutParams(layoutParams);
            s0();
            l0();
            return;
        }
        final float x = this.mVModuleSelectBg.getX();
        PLog.a("startSwitchMode: " + this.mVModuleSelectBg.getX() + ",expanded:" + z);
        if ((x > 0.0f) == z) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mModuleSelectAnimator = ofFloat;
        ofFloat.setDuration(250L);
        this.mModuleSelectAnimator.setInterpolator(ProjectionWindowView.FAST_OUT_SLOW_IN);
        this.mModuleSelectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.f
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NubiaProjectionExpandedPanel.this.M(x, z, valueAnimator);
            }
        });
        this.mModuleSelectAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.NubiaProjectionExpandedPanel.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z3) {
                NubiaProjectionExpandedPanel.this.mHost.setAnimationPlaying(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator, boolean z3) {
                NubiaProjectionExpandedPanel.this.mHost.setAnimationPlaying(true);
                NubiaProjectionExpandedPanel.this.mTvModuleExpanded.setClickable(false);
                NubiaProjectionExpandedPanel.this.mTvModuleMirror.setClickable(false);
            }
        });
        this.mModuleSelectAnimator.start();
        postDelayed(new Runnable() { // from class: cn.nubia.projection.ui.g
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.N(z);
            }
        }, 300L);
    }

    private void k0(Locale locale) {
        if (locale.equals(this.mLocale)) {
            return;
        }
        this.mLocale = locale;
        t0();
        p0();
        m0();
    }

    private void l0() {
        if (this.mUIControl.v0()) {
            this.mTvModuleGameBox.setVisibility(0);
            this.mTvModuleMirror.setVisibility(8);
            this.mTvModuleExpanded.setVisibility(8);
            this.mVModuleSelectBg.setVisibility(8);
            return;
        }
        this.mTvModuleGameBox.setVisibility(8);
        this.mTvModuleMirror.setVisibility(0);
        this.mTvModuleExpanded.setVisibility(0);
        this.mVModuleSelectBg.setVisibility(0);
        if (this.mUIControl.t0()) {
            this.mTvModuleExpanded.setTextColor(ContextCompat.c(getContext(), R.color.module_select_color));
            this.mTvModuleMirror.setTextColor(ContextCompat.c(getContext(), R.color.module_un_select_color));
            this.mTvModuleExpanded.setClickable(false);
            this.mTvModuleMirror.setClickable(true);
            return;
        }
        this.mTvModuleMirror.setTextColor(ContextCompat.c(getContext(), R.color.module_select_color));
        this.mTvModuleExpanded.setTextColor(ContextCompat.c(getContext(), R.color.module_un_select_color));
        this.mTvModuleExpanded.setClickable(true);
        this.mTvModuleMirror.setClickable(false);
    }

    private void n0() {
        ViewGroup.LayoutParams layoutParams = this.mModuleContainer.getLayoutParams();
        if (this.mUIControl.v0()) {
            this.mModuleContainer.setVisibility(8);
            this.mGameBoxTouchPadContainer.setVisibility(0);
        } else {
            this.mModuleContainer.setVisibility(0);
            this.mGameBoxTouchPadContainer.setVisibility(8);
        }
        this.mModuleContainer.setLayoutParams(layoutParams);
        if (this.mUIControl.t0()) {
            return;
        }
        this.mModuleContainer.setTranslationX(0.0f);
    }

    private void o0() {
        if (this.mUIControl.D0()) {
            this.mIvArrow.setVisibility(0);
            return;
        }
        this.mIvArrow.setVisibility(8);
        this.mIvArrow.setRotation(0.0f);
        ViewGroup.LayoutParams layoutParams = this.mLlMoreFeature.getLayoutParams();
        layoutParams.height = 0;
        this.mLlMoreFeature.setLayoutParams(layoutParams);
    }

    private void r() {
        GameAssistDialog gameAssistDialog = this.mDialog;
        if (gameAssistDialog == null || !gameAssistDialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
        this.mNsRemoveBlack.setChecked(false);
    }

    private void s0() {
        if ((this.mUIControl.C0() || this.mUIControl.B0()) && !this.mUIControl.v0()) {
            this.mIvSuspend.setVisibility(0);
            this.mIvFinishProjection.setVisibility(0);
            if (this.mUIControl.C0()) {
                this.mIvSuspend.setAlpha(1.0f);
                this.mIvSuspend.setClickable(true);
            } else {
                this.mIvSuspend.setAlpha(0.15f);
                this.mIvSuspend.setClickable(false);
            }
            if (this.mUIControl.B0()) {
                this.mIvFinishProjection.setAlpha(1.0f);
                this.mIvFinishProjection.setClickable(true);
            } else {
                this.mIvFinishProjection.setAlpha(0.15f);
                this.mIvFinishProjection.setClickable(false);
            }
        } else {
            this.mIvSuspend.setVisibility(8);
            this.mIvFinishProjection.setVisibility(8);
        }
        p0();
    }

    private void setRadioButtonClickable(boolean z) {
        this.mRbDefault.setClickable(z);
        this.mRb2K.setClickable(z);
        this.mRb4K.setClickable(z);
    }

    private void t0() {
        this.mTvModuleGameBox.setText(getResources().getString(R.string.projection_mode_game_box));
        this.mTvModuleExpanded.setText(getResources().getString(R.string.projection_mode_expanded));
        this.mTvModuleMirror.setText(getResources().getString(R.string.projection_mode_mirror));
        this.mTvVirtualHandle.setText(getResources().getString(R.string.virtual_handles_title));
        this.mTvRestScreen.setText(getResources().getString(R.string.screen_off_projection_title));
        this.mTvExtendedApp.setText(getResources().getString(R.string.projection_expanded_app));
        this.mTvTouchPad.setText(getResources().getString(R.string.projection_module_touch_pad));
        this.mTvGameBoxTouchPad.setText(getResources().getString(R.string.projection_module_touch_pad));
        this.mTvResolutionTag.setText(getResources().getString(R.string.projection_resolution_tag));
        this.mTvRemoveBlackTag.setText(getResources().getString(R.string.projection_module_full_screen_display));
        a0(this.mTvExtendedApp);
        a0(this.mTvRemoveBlackTag);
    }

    private boolean u() {
        return this.mUIControl.w0() && this.mUIControl.z0();
    }

    public boolean B() {
        return this.mHost.D();
    }

    public void Z() {
        post(new Runnable() { // from class: cn.nubia.projection.ui.k
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.G();
            }
        });
    }

    public void f0(final boolean z) {
        if (this.mUIControl.t0()) {
            if ((z ? 180 : 0) == this.mIvArrow.getRotation()) {
                return;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(200L);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.e
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    NubiaProjectionExpandedPanel.this.K(z, valueAnimator);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter(this) { // from class: cn.nubia.projection.ui.NubiaProjectionExpandedPanel.3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator, boolean z2) {
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                }
            });
            ofFloat.start();
        }
    }

    public boolean j0() {
        return Settings.Global.getInt(getContext().getContentResolver(), NB_APP_MIRROR_SUPPORT_WHITE_APP, 0) == 1;
    }

    public void m0() {
        s0();
        l0();
        n0();
        o0();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        u0();
        if (id == R.id.iv_projection_suspend) {
            W();
            return;
        }
        if (id == R.id.tv_module_expanded || id == R.id.fl_extended_app_container) {
            T();
            return;
        }
        if (id == R.id.tv_module_mirror) {
            U();
            return;
        }
        if (id == R.id.iv_extended_arrow) {
            S();
            return;
        }
        if (id == R.id.fl_virtual_handles_container) {
            Y();
            return;
        }
        if (id == R.id.fl_screen_container) {
            V();
            return;
        }
        if (id == R.id.fl_touch_pad_container || id == R.id.fl_game_box_touch_pad_container) {
            X();
            return;
        }
        if (id == R.id.ns_remove_black) {
            R();
            return;
        }
        if (id == R.id.iv_finish_projection) {
            this.mUIControl.k1();
            return;
        }
        if (id == R.id.rb_resolution_default) {
            c0(-1, 0);
            return;
        }
        if (id == R.id.rb_resolution_2k) {
            c0(-1, 1);
            return;
        }
        if (id == R.id.rb_resolution_4k) {
            c0(-1, 2);
        } else {
            if (id != R.id.tv_projection_status || u()) {
                return;
            }
            Q();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        k0(configuration.locale);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        A();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 == 8) {
            r();
        } else {
            this.mNsRemoveBlack.setChecked(this.mUIControl.R0());
        }
    }

    public void p0() {
        String string;
        ViewGroup.LayoutParams layoutParams = this.mTvProjectionStatus.getLayoutParams();
        layoutParams.width = 0;
        if (this.mUIControl.A0()) {
            this.mIvSuspend.setBackground(ContextCompat.e(getContext(), com.android.ZteWidget.R.drawable.play_arrow));
            string = getResources().getString(R.string.projection_status_suspend);
        } else {
            this.mIvSuspend.setBackground(ContextCompat.e(getContext(), com.android.ZteWidget.R.drawable.pause));
            string = getResources().getString(R.string.projection_status_ing);
        }
        if (this.mUIControl.v0()) {
            string = getResources().getString(u() ? R.string.projection_game_box_status_tips : R.string.projection_game_box_status_tips_not_game_key);
            layoutParams.width = -1;
        }
        this.mTvProjectionStatus.setLayoutParams(layoutParams);
        this.mTvProjectionStatus.setText(string);
    }

    public void q0(final int i2, final int i3) {
        PLog.a("updateResolutionView: support:" + i2 + ",now:" + i3);
        this.mUIControl.b0().post(new Runnable() { // from class: cn.nubia.projection.ui.d
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.O(i2, i3);
            }
        });
    }

    public void r0(boolean z, boolean z2) {
        if (z) {
            p0();
        }
        ProjectionManager.o().S(z, z2);
    }

    public void s() {
        this.mHost.o();
    }

    public void setProjectionUIControl(ProjectionUIController projectionUIController) {
        this.mUIControl = projectionUIController;
        m0();
    }

    public void setupHost(ProjectionWindowView projectionWindowView) {
        this.mHost = projectionWindowView;
    }

    public void t(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Nubia NubiaProjectionExpandedPanel Status:");
        printWriter.println("    TranslationX:" + getTranslationX());
        printWriter.println("    Visibility:" + getVisibility());
        printWriter.println("    moduleTranslationX:" + this.mModuleContainer.getTranslationX());
        printWriter.println("    moduleWidth:" + this.mModuleContainer.getWidth());
        printWriter.println("    default:" + this.mRbDefault.isEnabled() + "," + this.mRbDefault.isChecked());
        printWriter.println("    2K:" + this.mRb2K.isEnabled() + "," + this.mRb2K.isChecked());
        printWriter.println("    4K:" + this.mRb4K.isEnabled() + "," + this.mRb4K.isChecked());
        printWriter.println("    black:" + this.mNsRemoveBlack.isEnabled() + "," + this.mNsRemoveBlack.isChecked());
    }

    public void u0() {
        this.mHost.a0();
    }

    public void v() {
        m0();
        o0();
    }

    public void w() {
        m0();
        o0();
    }

    public void x() {
        PLog.a("handleResetUI: " + this.mUIControl.t0());
        i0(this.mUIControl.t0(), false);
        this.mLocale = null;
        k0(getResources().getConfiguration().locale);
        o0();
    }

    public void y() {
        this.mVirtualHandleContainer.setAlpha(1.0f);
        this.mVirtualHandleContainer.setClickable(true);
    }

    public void z() {
        this.mVirtualHandleContainer.setAlpha(0.15f);
        this.mVirtualHandleContainer.setClickable(false);
    }

    public NubiaProjectionExpandedPanel(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRadioGroupEnableRunnable = new Runnable() { // from class: cn.nubia.projection.ui.h
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.D();
            }
        };
        this.mSmallWindowAppChangeAnimation = new Runnable() { // from class: cn.nubia.projection.ui.i
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.E();
            }
        };
        this.mHandleScreenShotRunnable = new Runnable() { // from class: cn.nubia.projection.ui.j
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.F();
            }
        };
    }

    public NubiaProjectionExpandedPanel(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mRadioGroupEnableRunnable = new Runnable() { // from class: cn.nubia.projection.ui.h
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.D();
            }
        };
        this.mSmallWindowAppChangeAnimation = new Runnable() { // from class: cn.nubia.projection.ui.i
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.E();
            }
        };
        this.mHandleScreenShotRunnable = new Runnable() { // from class: cn.nubia.projection.ui.j
            @Override // java.lang.Runnable
            public final void run() {
                NubiaProjectionExpandedPanel.this.F();
            }
        };
    }
}
