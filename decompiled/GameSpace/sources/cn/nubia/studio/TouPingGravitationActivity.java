package cn.nubia.studio;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;
import cn.nubia.studio.PopWindowManager;

/* loaded from: classes.dex */
public class TouPingGravitationActivity extends Activity implements View.OnClickListener, PopWindowManager.PopupWindowDismissCallBack {
    private static final int BIG_PLAY_PREVIEW_MODE = 1;
    private static final int COMPUTER_PLAY_PREVIEW_MODE = 2;
    private static final boolean DEBUG = true;
    private static final String EVENT_KEY = "xgravity_superbase_cast";
    private static final String HOST_MODE_KEY = "db_mirror_host_mode";
    private static final String HOST_MODE_TYPE = "xgravity_casting_hostmode";
    private static final String PARAM_FROM_KEY = "from";
    private static final String PARAM_KEY = "fromotherapp_key";
    private static final String PARAM_VALUE = "FROMOTHERAPP_GAMESPACE";
    private static final String QRCODE_TYPE = "xgravity_casting_qrcode";
    public static final int REQUEST_FROM_PC = 103;
    public static final int REQUEST_FROM_TV = 102;
    private static final String SEARCH_TYPE = "xgravity_casting_search";
    private static final String TAG = "TouPingGravitationActivity";
    private static final String TOUPING_CLASS_NAME = "cn.nubia.touping.HomeActivity";
    private static final String TOUPING_PACKAGE_NAME = "cn.nubia.touping";
    private static final String VALUE_KEY = "xgravity_cast";
    private float density;
    private int densityDpi;
    private ImageView mBackView;
    private TextView mBigPlayView;
    private TextView mComputerPlayView;
    private AnimatorSet mEnterAnimSet;
    private AnimatorSet mExitAnimSet;
    private TextView mHelpView;
    private TextView mHostModeView;
    private TextView mInstructionContentView;
    private TextView mInstructionTitleView;
    private View mLeftLayout;
    private ImageView mPlayModePreviewView;
    private PopWindowManager mPopWindowManager;
    private View mRightLayout;
    private View mScanProjectLayout;
    private View mSearchProjectLayout;
    private TextView mSecretProjectView;
    private TextView mSportEquipmentKeyboard;
    private TextView mSportEquipmentLabel;
    private TextView mSportEquipmentMonitor;
    private TextView mSportEquipmentMouse;
    private int CURRENT_PLAY_PREVIEW_MODE = 1;
    private Rect mMonitorRect = new Rect();
    private Rect mKeyboardRect = new Rect();
    private Rect mMouseRect = new Rect();

    /* renamed from: cn.nubia.studio.TouPingGravitationActivity$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType;

        static {
            int[] iArr = new int[PopWindowManager.EquipmentType.values().length];
            $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType = iArr;
            try {
                iArr[PopWindowManager.EquipmentType.Monitor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType[PopWindowManager.EquipmentType.Mouse.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType[PopWindowManager.EquipmentType.Keyboard.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private PopWindowManager.EquipmentType calculateViewByPosition(int i, int i2) {
        LogUtils.d(TAG, " calculateViewByPosition ");
        PopWindowManager.EquipmentType equipmentType = PopWindowManager.EquipmentType.None;
        if (isMonitorView(i, i2)) {
            LogUtils.d(TAG, " calculateViewByPosition  isMonitorView ");
            return PopWindowManager.EquipmentType.Monitor;
        }
        if (isMouseView(i, i2)) {
            LogUtils.d(TAG, " calculateViewByPosition  isMouseView ");
            return PopWindowManager.EquipmentType.Mouse;
        }
        if (isKeyBoardView(i, i2)) {
            LogUtils.d(TAG, " calculateViewByPosition  isKeyBoardView ");
            return PopWindowManager.EquipmentType.Keyboard;
        }
        LogUtils.d(TAG, " calculateViewByPosition  outSide ");
        return PopWindowManager.EquipmentType.None;
    }

    private boolean getHostModeSwitchStatus() {
        int i = Settings.Global.getInt(getContentResolver(), HOST_MODE_KEY, 1);
        Log.d(TAG, "getHostModeSwitchStatus: hostModeStatus = " + i);
        return i == 1;
    }

    private void initData() {
        this.mComputerPlayView.setPressed(true);
        this.mBigPlayView.setPressed(false);
        this.mBigPlayView.setSelected(true);
        this.mBigPlayView.setClickable(true);
        this.mBigPlayView.setFocusable(true);
        this.mComputerPlayView.setSelected(true);
        this.mComputerPlayView.setClickable(true);
        this.mComputerPlayView.setFocusable(true);
        this.mSportEquipmentLabel.setSelected(true);
        this.mSportEquipmentMonitor.setSelected(true);
        this.mSportEquipmentKeyboard.setSelected(true);
        this.mSportEquipmentMouse.setSelected(true);
        this.mHostModeView.setSelected(true);
        this.mSecretProjectView.setSelected(true);
        updateHostModeSwitchStatus();
        this.mPopWindowManager = new PopWindowManager(this);
    }

    private void initViews() {
        Log.d(TAG, "initViews: ");
        this.mPlayModePreviewView = (ImageView) findViewById(R.id.play_category_preview);
        ImageView imageView = (ImageView) findViewById(R.id.back_icon);
        this.mBackView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        TextView textView = (TextView) findViewById(R.id.big_screen_play_view);
        this.mBigPlayView = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.computer_play_view);
        this.mComputerPlayView = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        this.mInstructionContentView = (TextView) findViewById(R.id.instruction_content_view);
        this.mInstructionTitleView = (TextView) findViewById(R.id.instruction_title_view);
        TextView textView3 = (TextView) findViewById(R.id.project_help);
        this.mHelpView = textView3;
        textView3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        TextView textView4 = (TextView) findViewById(R.id.touping_secret_switch_view);
        this.mSecretProjectView = textView4;
        textView4.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        TextView textView5 = (TextView) findViewById(R.id.host_mode_switch_view);
        this.mHostModeView = textView5;
        textView5.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        View findViewById = findViewById(R.id.scan_project_layout);
        this.mScanProjectLayout = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        View findViewById2 = findViewById(R.id.search_project_layout);
        this.mSearchProjectLayout = findViewById2;
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        this.mSportEquipmentLabel = (TextView) findViewById(R.id.sport_equipment_label);
        this.mSportEquipmentMonitor = (TextView) findViewById(R.id.sport_equipment_monitor);
        this.mSportEquipmentKeyboard = (TextView) findViewById(R.id.sport_equipment_keyboard);
        TextView textView6 = (TextView) findViewById(R.id.sport_equipment_mouse);
        this.mSportEquipmentMouse = textView6;
        textView6.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        this.mSportEquipmentMonitor.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        this.mSportEquipmentKeyboard.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.TouPingGravitationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouPingGravitationActivity.this.onClick(view);
            }
        });
        this.mLeftLayout = findViewById(R.id.touping_gravitation_left_layout);
        this.mRightLayout = findViewById(R.id.touping_gravitation_right_layout);
    }

    private boolean judgeView(Rect rect, int i, int i2) {
        if (rect == null) {
            return false;
        }
        LogUtils.d(TAG, " judgeView rect = " + rect.toString());
        return i >= rect.left && i <= rect.right && i2 >= rect.top && i2 <= rect.bottom;
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

    private void setAnimationUIStatus(boolean z) {
        this.mLeftLayout.setVisibility(z ? 0 : 8);
        this.mRightLayout.setVisibility(z ? 0 : 8);
    }

    private void showEquipmentInstructionWindow(PopWindowManager.EquipmentType equipmentType) {
        LogUtils.d(TAG, " showEquipmentInstructionWindow equipmentType = " + equipmentType);
        int i = AnonymousClass2.$SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType[equipmentType.ordinal()];
        TextView textView = i != 1 ? i != 2 ? i != 3 ? null : this.mSportEquipmentKeyboard : this.mSportEquipmentMouse : this.mSportEquipmentMonitor;
        updateSelectButtonBg(equipmentType);
        this.mPopWindowManager.createWindow(this);
        this.mPopWindowManager.updateContentView(this, equipmentType);
        PopWindowManager popWindowManager = this.mPopWindowManager;
        if (popWindowManager == null || textView == null) {
            return;
        }
        popWindowManager.showPopupWindow(textView);
    }

    private void startEnterAnimation() {
        LogUtils.d(TAG, "startEnterAnimation: ");
        AnimatorSet animatorSet = this.mEnterAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mLeftLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, CommonUtil.getLeftLayoutTranslationX(getApplicationContext()), 0.0f), new AnimBean(View.ALPHA, 0.0f, 1.0f));
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mRightLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, CommonUtil.getRightLayoutTranslationX(getApplicationContext()), 0.0f), new AnimBean(View.ALPHA, 0.0f, 1.0f));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mEnterAnimSet = animatorSet2;
        animatorSet2.playTogether(createPropertyAnim, createPropertyAnim2);
        this.mEnterAnimSet.start();
        setAnimationUIStatus(true);
    }

    private void startExitAnimation() {
        LogUtils.d(TAG, "startExitAnimation: ");
        AnimatorSet animatorSet = this.mExitAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mLeftLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, 0.0f, CommonUtil.getLeftLayoutTranslationX(getApplicationContext())), new AnimBean(View.ALPHA, 1.0f, 0.0f));
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mRightLayout, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new AnimBean(View.TRANSLATION_X, 0.0f, CommonUtil.getRightLayoutTranslationX(getApplicationContext())), new AnimBean(View.ALPHA, 1.0f, 0.0f));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mExitAnimSet = animatorSet2;
        animatorSet2.playTogether(createPropertyAnim, createPropertyAnim2);
        this.mExitAnimSet.start();
        this.mExitAnimSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.studio.TouPingGravitationActivity.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtils.d(TouPingGravitationActivity.TAG, " onAnimationEnd ");
                TouPingGravitationActivity.this.finish();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    private void startTouPingHelp() {
        startActivity(new Intent(this, (Class<?>) TouPingActivity.class));
    }

    private void startTouping(int i) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setClassName(TOUPING_PACKAGE_NAME, TOUPING_CLASS_NAME);
        intent.putExtra(PARAM_FROM_KEY, i);
        intent.putExtra(PARAM_KEY, PARAM_VALUE);
        startActivity(intent);
    }

    private void switchHostModeSwitch() {
        Settings.Global.putInt(getContentResolver(), HOST_MODE_KEY, !getHostModeSwitchStatus() ? 1 : 0);
        updateHostModeSwitchStatus();
    }

    private void switchPlayModePreviewImageAndLabelColor(int i, boolean z) {
        Log.d(TAG, "switchPlayModePreviewImageAndLabelColor: playMode = " + i + " ;; CURRENT_PLAY_PREVIEW_MODE = " + this.CURRENT_PLAY_PREVIEW_MODE + " ;; forceReload = " + z);
        if (!z && i == this.CURRENT_PLAY_PREVIEW_MODE) {
            Log.d(TAG, "switchPlayModePreviewImageAndLabelColor playMode no change, skip !!! ");
            return;
        }
        boolean z2 = i == 1;
        this.mPlayModePreviewView.setImageDrawable(getDrawable(z2 ? R.drawable.big_screen_play_image_preview : R.drawable.computer_play_image_preview));
        this.mBigPlayView.setTextColor(z2 ? getColor(R.color.play_mode_label_selected_text_color) : getColor(R.color.play_mode_label_unselected_text_color));
        this.mBigPlayView.getPaint().setFakeBoldText(z2);
        this.mComputerPlayView.getPaint().setFakeBoldText(!z2);
        this.mComputerPlayView.setTextColor(!z2 ? getColor(R.color.play_mode_label_selected_text_color) : getColor(R.color.play_mode_label_unselected_text_color));
        this.mHostModeView.setVisibility(i != 1 ? 8 : 0);
        this.mInstructionContentView.setText(z2 ? getText(R.string.instruction_content) : getString(R.string.computer_play_instruction_content, new Object[]{getString(R.string.redmagic_studio_download_website)}));
        this.mInstructionTitleView.setText(getText(z2 ? R.string.instruction_title : R.string.computer_play_instruction_title));
        this.CURRENT_PLAY_PREVIEW_MODE = i;
    }

    private void updateHostModeSwitchStatus() {
        Drawable drawable = getDrawable(getHostModeSwitchStatus() ? R.drawable.touping_play_selected_img : R.drawable.touping_play_unselected_img);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.mHostModeView.setCompoundDrawables(drawable, null, null, null);
    }

    private void updateSelectButtonBg(PopWindowManager.EquipmentType equipmentType) {
        if (equipmentType == PopWindowManager.EquipmentType.None) {
            this.mSportEquipmentKeyboard.setBackgroundResource(R.drawable.project_gravitation_contain_stoke_bt_bg);
            this.mSportEquipmentMouse.setBackgroundResource(R.drawable.project_gravitation_contain_stoke_bt_bg);
            this.mSportEquipmentMonitor.setBackgroundResource(R.drawable.project_gravitation_contain_stoke_bt_bg);
        } else if (equipmentType == PopWindowManager.EquipmentType.Mouse) {
            this.mSportEquipmentMouse.setBackgroundResource(R.drawable.project_gravitation_contain_stoke_bt_bg_selector);
        } else if (equipmentType == PopWindowManager.EquipmentType.Monitor) {
            this.mSportEquipmentMonitor.setBackgroundResource(R.drawable.project_gravitation_contain_stoke_bt_bg_selector);
        } else if (equipmentType == PopWindowManager.EquipmentType.Keyboard) {
            this.mSportEquipmentKeyboard.setBackgroundResource(R.drawable.project_gravitation_contain_stoke_bt_bg_selector);
        }
    }

    private void updateViewRect() {
        this.mSportEquipmentMonitor.getGlobalVisibleRect(this.mMonitorRect);
        this.mSportEquipmentMouse.getGlobalVisibleRect(this.mMouseRect);
        this.mSportEquipmentKeyboard.getGlobalVisibleRect(this.mKeyboardRect);
    }

    @Override // cn.nubia.studio.PopWindowManager.PopupWindowDismissCallBack
    public PopWindowManager.EquipmentType getEquipmentTypeByClickPosition(int i, int i2) {
        return calculateViewByPosition(i, i2);
    }

    public boolean isKeyBoardView(int i, int i2) {
        return judgeView(this.mKeyboardRect, i, i2);
    }

    public boolean isMonitorView(int i, int i2) {
        return judgeView(this.mMonitorRect, i, i2);
    }

    public boolean isMouseView(int i, int i2) {
        return judgeView(this.mMouseRect, i, i2);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        startExitAnimation();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        Log.d(TAG, "onClick: ");
        updateViewRect();
        String[] stringArray = getResources().getStringArray(R.array.xgravity_casting_upload_data_array);
        switch (view.getId()) {
            case R.id.back_icon /* 2131361947 */:
                onBackPressed();
                str = "";
                break;
            case R.id.big_screen_play_view /* 2131361960 */:
                switchPlayModePreviewImageAndLabelColor(1, false);
                str = "";
                break;
            case R.id.computer_play_view /* 2131362044 */:
                switchPlayModePreviewImageAndLabelColor(2, false);
                str = "";
                break;
            case R.id.host_mode_switch_view /* 2131362557 */:
                str = stringArray[2];
                switchHostModeSwitch();
                break;
            case R.id.project_help /* 2131363070 */:
                startTouPingHelp();
                str = "";
                break;
            case R.id.scan_project_layout /* 2131363171 */:
                str = stringArray[0];
                startTouping(103);
                break;
            case R.id.search_project_layout /* 2131363197 */:
                str = stringArray[1];
                startTouping(102);
                break;
            case R.id.sport_equipment_keyboard /* 2131363276 */:
                showEquipmentInstructionWindow(PopWindowManager.EquipmentType.Keyboard);
                str = "";
                break;
            case R.id.sport_equipment_monitor /* 2131363278 */:
                showEquipmentInstructionWindow(PopWindowManager.EquipmentType.Monitor);
                str = "";
                break;
            case R.id.sport_equipment_mouse /* 2131363279 */:
                showEquipmentInstructionWindow(PopWindowManager.EquipmentType.Mouse);
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
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        if (Math.max(r4.widthPixels, r4.heightPixels) / Math.min(r4.widthPixels, r4.heightPixels) <= 1.6f) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            this.density = displayMetrics.density;
            this.densityDpi = displayMetrics.densityDpi;
            displayMetrics.density = Math.min(r4.widthPixels, r4.heightPixels) / 800.0f;
            displayMetrics.densityDpi = (int) (displayMetrics.density * 320.0f);
        }
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.touping_gravitation_activity_layout);
        getWindow().getDecorView().setSystemUiVisibility(4102);
        initViews();
        initData();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        resetDensity();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        switchPlayModePreviewImageAndLabelColor(this.CURRENT_PLAY_PREVIEW_MODE, true);
        startEnterAnimation();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        setAnimationUIStatus(false);
    }

    @Override // cn.nubia.studio.PopWindowManager.PopupWindowDismissCallBack
    public void updateButtonStatus(PopWindowManager.EquipmentType equipmentType) {
        LogUtils.d(TAG, " updateButtonStatus mLastEquipmentType = " + this.mPopWindowManager.mLastEquipmentType);
        updateSelectButtonBg(equipmentType);
        if (this.mPopWindowManager.mLastEquipmentType != PopWindowManager.EquipmentType.None) {
            showEquipmentInstructionWindow(this.mPopWindowManager.mLastEquipmentType);
        }
    }
}
