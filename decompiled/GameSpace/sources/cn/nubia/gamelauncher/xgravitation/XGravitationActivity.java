package cn.nubia.gamelauncher.xgravitation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.activity.LargeGameActivity;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.xgravitation.ui.XGravitationTextureView;
import cn.nubia.gamelauncher.xgravitation.util.InterUIController;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;
import cn.nubia.gamelauncher.xgravitation.util.PlaySoundController;
import cn.nubia.gamelauncher.xgravitation.util.RandomUntil;
import cn.nubia.gamelauncher.xgravitation.util.TipsHelper;
import cn.nubia.studio.TouPingGravitationActivity;

/* loaded from: classes.dex */
public class XGravitationActivity extends Activity implements View.OnClickListener, IController {
    private static final String DB_GAMEPAD_NEW_SCHEME_SHOW_TIPS = "nubia_gamepad_new_scheme_show_tips";
    private static final String DB_LKM_NEW_SCHEME_SHOW_TIPS = "nubia_lkm_new_scheme_show_tips";
    private static final String EVENT_KEY = "xgravity_superbase_play";
    private static final String HANDLE_PLAY_ACTION = "cn.nubia.gamepad.SCHEME_LIST";
    private static final String HANDLE_PLAY_PACKAGE_NAME = "cn.nubia.gamepad";
    private static final String KEYBOARD_MOUSE_PLAY_ACTION = "cn.nubia.keymapcenter.intent.action.LKM_SCHEME_LIST";
    private static final String TAG = "XGravitationActivity";
    private static final String VALUE_KEY = "xgravity_play_page";
    private static final String X_GRAVITATION_PREFERENCES_FILE = "x_gravitation_switch";
    private static final String X_GRAVITATION_VOICE_SWITCH_KEY = "x_gravitation_switch_status";
    private float density;
    private int densityDpi;
    private ImageView m3AGame;
    private View mBackView;
    private ImageView mHandleImage;
    private ImageView mHandlePlayRedPointImage;
    private IVideoPlayerController mIVideoPlayerController;
    private ImageView mInterStaticImage;
    private InterUIController mInterUIController;
    private ImageView mKeyBoardAndMousePlayRedPointImage;
    private View mLeftLayout;
    private ImageView mMouseImage;
    private PlaySoundController mPlaySoundController;
    private ProgressBar mProgressBar;
    private View mRightLayout;
    private XGravitationTextureView mTextureView;
    private TextView mTipsContentTextView;
    private View mTipsLayout;
    private TextView mTipsTileTextView;
    private View mTouPingLayout;
    private View mVoiceSwitchView;
    private AnimatorSet mXGravitationEnterAnimSet;
    private AnimatorSet mXGravitationExitAnimSet;
    private boolean mIsInternational = CommonUtil.isInternalVersion();
    private boolean mUIIsVisible = false;
    private boolean mIsFirstEnter = false;

    private void closeVoice() {
        PlaySoundController playSoundController = this.mPlaySoundController;
        if (playSoundController != null) {
            playSoundController.setVoiceVolume(false);
        }
    }

    private boolean getIsLkmShowNewSchemeTips() {
        return Settings.Global.getInt(getContentResolver(), DB_LKM_NEW_SCHEME_SHOW_TIPS, 0) == 1;
    }

    private boolean getIsShowNewSchemeTips() {
        return Settings.Global.getInt(getContentResolver(), DB_GAMEPAD_NEW_SCHEME_SHOW_TIPS, 0) == 1;
    }

    private void initData() {
        this.mInterStaticImage.setVisibility(this.mIsInternational ? 0 : 8);
        this.mVoiceSwitchView.setVisibility(this.mIsInternational ? 8 : 0);
        this.mTextureView.setVisibility(this.mIsInternational ? 4 : 0);
        if (this.mIsInternational) {
            initInterUIController();
        } else {
            initPlaySoundController();
        }
        this.mIsFirstEnter = true;
        updateEnterAnimationUI(false);
    }

    private void initInterUIController() {
        InterUIController interUIController = InterUIController.getInstance(this, getMainLooper());
        this.mInterUIController = interUIController;
        interUIController.setIController(this);
    }

    private void initPlaySoundController() {
        LogUtils.d(TAG, "initPlaySoundController: ");
        this.mPlaySoundController = PlaySoundController.getInstance();
    }

    private void initViews() {
        this.mInterStaticImage = (ImageView) findViewById(R.id.inter_static_image_preview);
        this.mTipsTileTextView = (TextView) findViewById(R.id.x_gravitation_tips_title);
        this.mTipsContentTextView = (TextView) findViewById(R.id.x_gravitation_tips_content);
        this.mHandlePlayRedPointImage = (ImageView) findViewById(R.id.x_gravitation_equipment_handle_has_update_icon);
        this.mKeyBoardAndMousePlayRedPointImage = (ImageView) findViewById(R.id.x_gravitation_equipment_mouse_has_update_icon);
        this.mTipsContentTextView.setSelected(true);
        View findViewById = findViewById(R.id.voice_switch);
        this.mVoiceSwitchView = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        View findViewById2 = findViewById(R.id.back);
        this.mBackView = findViewById2;
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        View findViewById3 = findViewById(R.id.x_gravitation_equipment_touping);
        this.mTouPingLayout = findViewById3;
        findViewById3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        View findViewById4 = findViewById(R.id.x_gravitation_tips_layout);
        this.mTipsLayout = findViewById4;
        findViewById4.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.x_gravitation_equipment_mouse);
        this.mMouseImage = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        XGravitationTextureView xGravitationTextureView = (XGravitationTextureView) findViewById(R.id.video_preview);
        this.mTextureView = xGravitationTextureView;
        xGravitationTextureView.setIController(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.x_gravitation_3a_game);
        this.m3AGame = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        ImageView imageView3 = (ImageView) findViewById(R.id.x_gravitation_equipment_handle);
        this.mHandleImage = imageView3;
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XGravitationActivity.this.onClick(view);
            }
        });
        this.mIVideoPlayerController = this.mTextureView;
        this.mProgressBar = (ProgressBar) findViewById(R.id.x_gravitation_tips_progressbar);
        this.mLeftLayout = findViewById(R.id.x_gravitation_left_layout);
        this.mRightLayout = findViewById(R.id.x_gravitation_right_layout);
    }

    private void openOrCloseVoice() {
        SharedPreferences sharedPreferences = getSharedPreferences(X_GRAVITATION_PREFERENCES_FILE, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        boolean z = sharedPreferences.getBoolean(X_GRAVITATION_VOICE_SWITCH_KEY, true);
        if (z) {
            closeVoice();
        } else {
            openVoice();
        }
        this.mVoiceSwitchView.setBackgroundResource(z ? R.drawable.x_gravitation_voice_close_selector : R.drawable.x_gravitation_voice_open_selector);
        edit.putBoolean(X_GRAVITATION_VOICE_SWITCH_KEY, !z);
        edit.apply();
    }

    private void openVoice() {
        PlaySoundController playSoundController = this.mPlaySoundController;
        if (playSoundController != null) {
            playSoundController.setVoiceVolume(true);
        }
    }

    private void release() {
        IVideoPlayerController iVideoPlayerController = this.mIVideoPlayerController;
        if (iVideoPlayerController != null) {
            iVideoPlayerController.release();
        }
        PlaySoundController playSoundController = this.mPlaySoundController;
        if (playSoundController != null) {
            playSoundController.release();
        }
        AnimatorSet animatorSet = this.mXGravitationEnterAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mXGravitationEnterAnimSet = null;
        }
    }

    private void releaseInInter() {
        LogUtils.d(TAG, "releaseInInter: ");
        InterUIController interUIController = this.mInterUIController;
        if (interUIController != null) {
            interUIController.release();
        }
    }

    private void resetDensity() {
        if (this.density == 0.0f || this.densityDpi == 0) {
            return;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LogUtils.d(TAG, " displayMetrics  density = " + this.density + " ;; densityDpi = " + this.densityDpi);
        displayMetrics.density = this.density;
        displayMetrics.densityDpi = this.densityDpi;
    }

    private void setFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(4102);
    }

    private void setShortEdges() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
    }

    private void start3AGame() {
        try {
            startActivity(new Intent(this, (Class<?>) LargeGameActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtils.e(TAG, " start3AGame exception ----- ", e);
        }
    }

    private void startEnterXGravitationAnimation() {
        LogUtils.d(TAG, "startEnterXGravitationAnimation: ");
        AnimatorSet animatorSet = this.mXGravitationEnterAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mLeftLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, cn.nubia.common.util.CommonUtil.getLeftLayoutTranslationX(getApplicationContext()), 0.0f), new AnimBean(View.ALPHA, 0.0f, 1.0f));
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mRightLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, cn.nubia.common.util.CommonUtil.getRightLayoutTranslationX(getApplicationContext()), 0.0f), new AnimBean(View.ALPHA, 0.0f, 1.0f));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mXGravitationEnterAnimSet = animatorSet2;
        animatorSet2.playTogether(createPropertyAnim, createPropertyAnim2);
        this.mXGravitationEnterAnimSet.start();
        updateEnterAnimationUI(true);
    }

    private void startExitXGravitationAnimation() {
        LogUtils.d(TAG, "startExitXGravitationAnimation: ");
        AnimatorSet animatorSet = this.mXGravitationExitAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mLeftLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, 0.0f, cn.nubia.common.util.CommonUtil.getLeftLayoutTranslationX(getApplicationContext())), new AnimBean(View.ALPHA, 1.0f, 0.0f));
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mRightLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, 0.0f, cn.nubia.common.util.CommonUtil.getRightLayoutTranslationX(getApplicationContext())), new AnimBean(View.ALPHA, 1.0f, 0.0f));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mXGravitationExitAnimSet = animatorSet2;
        animatorSet2.playTogether(createPropertyAnim, createPropertyAnim2);
        this.mXGravitationExitAnimSet.start();
        this.mXGravitationExitAnimSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gamelauncher.xgravitation.XGravitationActivity.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtils.d(XGravitationActivity.TAG, " onAnimationEnd ");
                XGravitationActivity.this.finish();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    private void startHandlePlay() {
        Intent intent = new Intent();
        intent.setPackage(HANDLE_PLAY_PACKAGE_NAME);
        intent.setAction(HANDLE_PLAY_ACTION);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtils.e(TAG, " startHandlePlay exception ----- ", e);
        }
    }

    private void startMousePlay() {
        Intent intent = new Intent();
        intent.setAction(KEYBOARD_MOUSE_PLAY_ACTION);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtils.e(TAG, " startMousePlay err  ", e);
        }
    }

    private void startProjectionGravitation() {
        try {
            startActivity(new Intent(this, (Class<?>) TouPingGravitationActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtils.e(TAG, " startProjectionGravitation err  ", e);
        }
    }

    private void switchNextRandomTips() {
        if (this.mIsInternational) {
            updateUI();
            return;
        }
        IVideoPlayerController iVideoPlayerController = this.mIVideoPlayerController;
        if (iVideoPlayerController != null) {
            iVideoPlayerController.stop();
        }
        PlaySoundController playSoundController = this.mPlaySoundController;
        if (playSoundController != null) {
            playSoundController.stop();
        }
        IVideoPlayerController iVideoPlayerController2 = this.mIVideoPlayerController;
        XGravitationTextureView xGravitationTextureView = this.mTextureView;
        iVideoPlayerController2.restart(false, xGravitationTextureView == null ? null : xGravitationTextureView.getSurfaceTexture());
    }

    private void updateData() {
        if (this.mIsInternational) {
            startEnterXGravitationAnimation();
            InterUIController interUIController = this.mInterUIController;
            if (interUIController != null) {
                interUIController.start();
            }
        } else {
            boolean z = getSharedPreferences(X_GRAVITATION_PREFERENCES_FILE, 0).getBoolean(X_GRAVITATION_VOICE_SWITCH_KEY, true);
            this.mVoiceSwitchView.setBackgroundResource(z ? R.drawable.x_gravitation_voice_open_selector : R.drawable.x_gravitation_voice_close_selector);
            LogUtils.d(TAG, "onResume: current = " + z + " ;; mLeftLayout.getVisibility() = " + this.mLeftLayout.getVisibility() + " ;; mIsFirstEnter = " + this.mIsFirstEnter);
            if (!z) {
                closeVoice();
            }
            if (this.mIVideoPlayerController != null) {
                if (this.mTextureView.getSurfaceTexture() != null || this.mIsFirstEnter) {
                    IVideoPlayerController iVideoPlayerController = this.mIVideoPlayerController;
                    XGravitationTextureView xGravitationTextureView = this.mTextureView;
                    iVideoPlayerController.restart(true, xGravitationTextureView == null ? null : xGravitationTextureView.getSurfaceTexture());
                } else {
                    updateEnterAnimationUI(true);
                }
            }
        }
        if (this.mIsFirstEnter) {
            this.mIsFirstEnter = false;
        }
        updateHandleUpdateRedPointIcon();
        updateKeyBoardAndMouseUpdateRedPointIcon();
    }

    private void updateEnterAnimationUI(boolean z) {
        LogUtils.d(TAG, " updateEnterAnimationUI show = " + z + " ;; mIsInternational == " + this.mIsInternational + " ;; mIsFirstEnter = " + this.mIsFirstEnter);
        this.mRightLayout.setVisibility(z ? 0 : 4);
        this.mTipsLayout.setVisibility(z ? 0 : 4);
        if (this.mIsInternational) {
            this.mLeftLayout.setVisibility(z ? 0 : 8);
            return;
        }
        this.mVoiceSwitchView.setVisibility(z ? 0 : 8);
        if (this.mIsFirstEnter) {
            return;
        }
        this.mLeftLayout.setVisibility(z ? 0 : 8);
    }

    private void updateHandleUpdateRedPointIcon() {
        this.mHandlePlayRedPointImage.setVisibility(getIsShowNewSchemeTips() ? 0 : 8);
    }

    private void updateKeyBoardAndMouseUpdateRedPointIcon() {
        this.mKeyBoardAndMousePlayRedPointImage.setVisibility(getIsLkmShowNewSchemeTips() ? 0 : 8);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        startExitXGravitationAnimation();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        String[] stringArray = getResources().getStringArray(R.array.upload_data_array);
        switch (view.getId()) {
            case R.id.back /* 2131361945 */:
                startExitXGravitationAnimation();
                str = "";
                break;
            case R.id.voice_switch /* 2131363672 */:
                openOrCloseVoice();
                str = "";
                break;
            case R.id.x_gravitation_3a_game /* 2131363698 */:
                str = stringArray[0];
                start3AGame();
                break;
            case R.id.x_gravitation_equipment_handle /* 2131363701 */:
                str = stringArray[2];
                startHandlePlay();
                break;
            case R.id.x_gravitation_equipment_mouse /* 2131363704 */:
                str = stringArray[1];
                startMousePlay();
                break;
            case R.id.x_gravitation_equipment_touping /* 2131363707 */:
                str = stringArray[3];
                startProjectionGravitation();
                break;
            case R.id.x_gravitation_tips_layout /* 2131363714 */:
                switchNextRandomTips();
                str = "";
                break;
            default:
                str = "";
                break;
        }
        if (cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil.isInternalVersion() || TextUtils.isEmpty(str)) {
            return;
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", EVENT_KEY, VALUE_KEY, str);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        if (Math.max(r4.widthPixels, r4.heightPixels) / Math.min(r4.widthPixels, r4.heightPixels) <= 1.6f) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            this.density = displayMetrics.density;
            this.densityDpi = displayMetrics.densityDpi;
            displayMetrics.density = Math.min(r4.widthPixels, r4.heightPixels) / 800.0f;
            displayMetrics.densityDpi = (int) (displayMetrics.density * 320.0f);
        }
        setShortEdges();
        setContentView(R.layout.xgravitation_activity_main);
        setFullScreen();
        initViews();
        initData();
        LogUtils.d(TAG, "onCreate: widthSize = " + getResources().getDimensionPixelSize(R.dimen.x_gravitation_video_preview_width_size));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        LogUtils.d(TAG, " onDestroy ");
        super.onDestroy();
        this.mUIIsVisible = false;
        if (this.mIsInternational) {
            releaseInInter();
        } else {
            release();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        LogUtils.d(TAG, "onPause: ");
        super.onPause();
        resetDensity();
        this.mUIIsVisible = false;
        if (this.mIsInternational) {
            InterUIController interUIController = this.mInterUIController;
            if (interUIController != null) {
                interUIController.stop();
            }
        } else {
            IVideoPlayerController iVideoPlayerController = this.mIVideoPlayerController;
            if (iVideoPlayerController != null) {
                iVideoPlayerController.stop();
            }
            PlaySoundController playSoundController = this.mPlaySoundController;
            if (playSoundController != null) {
                playSoundController.stop();
            }
        }
        updateEnterAnimationUI(false);
    }

    @Override // android.app.Activity
    protected void onResume() {
        LogUtils.d(TAG, "onResume: ");
        super.onResume();
        this.mUIIsVisible = true;
        updateData();
    }

    @Override // cn.nubia.gamelauncher.xgravitation.IController
    public void playSound() {
        LogUtils.d(TAG, "playSound: ");
        if (this.mPlaySoundController == null || !uiIsVisibility()) {
            return;
        }
        updateUI();
        this.mPlaySoundController.setVideoPreviewPlayer(this.mTextureView.getVideoPreviewPlayer());
        this.mPlaySoundController.playSound(getApplicationContext());
    }

    @Override // cn.nubia.gamelauncher.xgravitation.IController
    public void setProgressMax(int i) {
        this.mProgressBar.setMax(i);
    }

    @Override // cn.nubia.gamelauncher.xgravitation.IController
    public void startEnterAnimation() {
        startEnterXGravitationAnimation();
    }

    @Override // cn.nubia.gamelauncher.xgravitation.IController
    public boolean uiIsVisibility() {
        LogUtils.d(TAG, "uiIsVisibility: mUIIsVisible = " + this.mUIIsVisible);
        return this.mUIIsVisible;
    }

    @Override // cn.nubia.gamelauncher.xgravitation.IController
    public void updateProgress(int i) {
        this.mProgressBar.setProgress(i);
    }

    @Override // cn.nubia.gamelauncher.xgravitation.IController
    public void updateUI() {
        int differentNum = RandomUntil.getDifferentNum();
        LogUtils.d(TAG, "updateUI: randomNum = " + differentNum);
        this.mTipsTileTextView.setText(TipsHelper.getInstance().getTipsTitle(this, differentNum));
        this.mTipsContentTextView.setText(TipsHelper.getInstance().getTipsContent(this, differentNum));
        if (!this.mIsInternational) {
            this.mPlaySoundController.setVoiceId(TipsHelper.getInstance().getTipsVoiceRedId(this, differentNum));
            return;
        }
        InterUIController interUIController = this.mInterUIController;
        if (interUIController != null) {
            interUIController.restart();
        }
    }
}
