package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.TouchOperationBean;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.TouchOperationHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import com.zte.gameassist.ai.AIFlickerTips;

/* loaded from: classes.dex */
public class GameAdjustOperationView extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, GameControlDialog.ISetViewAnimation {
    private static final int GYON_SENSITIVE_MAX = 200;
    private static final int GYON_SENSITIVE_MIN = 1;
    private static final int HIGH_TOUCH_SAMPLE_RATE_480 = 480;
    private static final int HIGH_TOUCH_SAMPLE_RATE_960 = 960;
    private static final int LOW_TOUCH_SAMPLE_RATE_360 = 360;
    private static final int LOW_TOUCH_SAMPLE_RATE_720 = 720;
    private static final int PAD_TOUCH_SAMPLE_RATE_120 = 120;
    private static final int PAD_TOUCH_SAMPLE_RATE_240 = 240;
    private static final String PERCENT = "%";
    private static final int PREVENT_SWITCH_OFF = 0;
    private static final int PREVENT_SWITCH_ON = 1;
    private static final String TAG = "GameAdjustOperationView";
    private static final int TOUCH_RANGE_BIG = 1;
    private static final int TOUCH_RANGE_MIDDLE = 0;
    private static final int TOUCH_RANGE_SMALL = -1;
    private static final int TYPE_FOLLOW = 11;
    private static final int TYPE_GYON_X = 20;
    private static final int TYPE_GYON_Y = 21;
    private static final int TYPE_KEEN = 10;
    private static final int TYPE_MICRO_SENSITIVE = 12;
    private static final String ZTE_AI_ADJUST_ENABLED = "zte_tp_game_partition_enabled";
    private static final String ZTE_PRECISION_CONTROL_ENABLED = "game_precision_control_enabled_pkgs";
    private int[] TOUCH_SCREEN_LEVEL;
    private int T_LEVEL_DEFAULT;
    private int T_PROGRESS_DEFAULT;
    private int T_PROGRESS_MAX;
    private int T_PROGRESS_MIN;
    private int X_SENSITIVE_PROGRESS_DEFAULT;
    private int Y_SENSITIVE_PROGRESS_DEFAULT;
    private ViewStub mAiAdjustStub;
    private View mAiOperationAdjustLayout;
    private ImageView mAiSwitch;
    private TextView mBigRange;
    private Context mContext;
    private View mFollowLayout;
    private SeekBar mFollowSeekBar;
    protected IGameStrengthSelectedListener mGameStrengthSelectedListener;
    private View mGyroLayout;
    private IGameStrengthSelectedListener mIGameStrengthSelectedListener;
    private View mKeenLayout;
    private SeekBar mKeenSeekBar;
    private View mLayoutView;
    private View mMicroSensitivityLayout;
    private SeekBar mMicroSensitivitySeekBar;
    private TextView mMiddleRange;
    private View mPrecisionControlLayout;
    private ViewStub mPrecisionCtlStub;
    private ImageView mPrecisionSwitch;
    private View mPreventAccidentialTouchLayout;
    private ImageView mPreventSwitch;
    private ImageView mRangeDisplay;
    private int mRecordFollowLevel;
    private int mRecordKeenLevel;
    private int mRecordMicroSensitiveLevel;
    private int mRecordSensitive_X;
    private int mRecordSensitive_Y;
    private TextView mSample480;
    private TextView mSample960;
    private View mSampleRateLayout;
    private ScrollView mScrollView;
    private TextView mSmallRange;
    private boolean mSupportHighSampleRate;
    private View mTouchScreenLayout;
    private ViewStub mViewStub;
    private boolean mVisibleGyon;
    private SeekBar mXAxisSeekBar;
    private ImageView mXSensitiveDown;
    private TextView mXSensitiveLevel;
    private ImageView mXSensitiveUp;
    private SeekBar mYAxisSeekBar;
    private ImageView mYSensitiveDown;
    private TextView mYSensitiveLevel;
    private ImageView mYSensitiveUp;

    public GameAdjustOperationView(Context context) {
        this(context, null);
    }

    public GameAdjustOperationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameAdjustOperationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int[] iArr = {-2, -1, 0, 1, 2};
        this.TOUCH_SCREEN_LEVEL = iArr;
        this.T_PROGRESS_MIN = 0;
        this.T_PROGRESS_MAX = iArr.length - 1;
        this.T_LEVEL_DEFAULT = 0;
        this.T_PROGRESS_DEFAULT = 2;
        this.X_SENSITIVE_PROGRESS_DEFAULT = 200;
        this.Y_SENSITIVE_PROGRESS_DEFAULT = 50;
        this.mRecordKeenLevel = 0;
        this.mRecordFollowLevel = 0;
        this.mRecordMicroSensitiveLevel = 0;
        this.mRecordSensitive_X = 200;
        this.mRecordSensitive_Y = 50;
        this.mSupportHighSampleRate = true;
        initRootLayout();
        this.mContext = context;
    }

    private String addPackageToList(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        for (String str3 : str.split(";")) {
            if (str3.equals(str2)) {
                return str;
            }
        }
        return str + ";" + str2;
    }

    private void clickAiSwitch(String str) {
        boolean isAiAdjustSwitchOn = isAiAdjustSwitchOn(str);
        LogUtil.d(TAG, "clickAiSwitch() turnTo : " + isAiAdjustSwitchOn);
        String string = Settings.Global.getString(this.mContext.getContentResolver(), ZTE_AI_ADJUST_ENABLED);
        String removePackageFromList = isAiAdjustSwitchOn ? removePackageFromList(string, str) : addPackageToList(string, str);
        Settings.Global.putString(this.mContext.getContentResolver(), ZTE_AI_ADJUST_ENABLED, removePackageFromList);
        LogUtil.d(TAG, "clickAiSwitch() packageNames : " + removePackageFromList);
        updateAiSwitch(!isAiAdjustSwitchOn);
    }

    private void clickPrecisionSwitch(String str) {
        boolean isPrecisionSwitchOn = isPrecisionSwitchOn(str);
        LogUtil.d(TAG, "clickPrecisionSwitch() turnTo : " + isPrecisionSwitchOn);
        String string = Settings.Global.getString(this.mContext.getContentResolver(), ZTE_PRECISION_CONTROL_ENABLED);
        String removePackageFromList = isPrecisionSwitchOn ? removePackageFromList(string, str) : addPackageToList(string, str);
        Settings.Global.putString(this.mContext.getContentResolver(), ZTE_PRECISION_CONTROL_ENABLED, removePackageFromList);
        LogUtil.d(TAG, "clickPrecisionSwitch() packageNames : " + removePackageFromList);
        updatePrecisionSwitch(!isPrecisionSwitchOn);
        sendBroadcastToPrecision(str);
    }

    private int findProgress(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.TOUCH_SCREEN_LEVEL;
            if (i2 >= iArr.length) {
                return this.T_PROGRESS_DEFAULT;
            }
            if (i == iArr[i2]) {
                return i2;
            }
            i2++;
        }
    }

    private View getShowFlickerView(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        switch (str) {
        }
        return null;
    }

    private Drawable getXProgressDrawable(int i) {
        return i > 100 ? getResources().getDrawable(R.drawable.gyro_reverse) : getResources().getDrawable(R.drawable.gyro_normal);
    }

    private Drawable getYProgressDrawable(int i) {
        return i > 100 ? getResources().getDrawable(R.drawable.gyro_reverse) : getResources().getDrawable(R.drawable.gyro_normal);
    }

    private void initAiAdjustStub() {
        if (ControlPanelFeatureHelper.supportAiAdjustByFeature(Utils.getCurrentPkgName()).booleanValue()) {
            LogUtil.d(TAG, "initAiAdjustStub() ");
            ViewStub viewStub = (ViewStub) findViewById(R.id.ai_adjust);
            this.mAiAdjustStub = viewStub;
            viewStub.inflate();
            this.mAiSwitch = (ImageView) findViewById(R.id.ai_adjust_checkbox);
            this.mAiOperationAdjustLayout = findViewById(R.id.ai_adjust_layout);
            this.mAiSwitch.setOnClickListener(this);
            updateAiSwitch(isAiAdjustSwitchOn(Utils.getCurrentPkgName()));
        }
    }

    private void initGronSeekBar() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.x_axis_seek_bar);
        this.mXAxisSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.mXAxisSeekBar.setProgressDrawable(getXProgressDrawable(this.mRecordSensitive_X));
        this.mXAxisSeekBar.setProgress(this.mRecordSensitive_X);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.y_axis_seek_bar);
        this.mYAxisSeekBar = seekBar2;
        seekBar2.setOnSeekBarChangeListener(this);
        this.mYAxisSeekBar.setProgressDrawable(getYProgressDrawable(this.mRecordSensitive_Y));
        this.mYAxisSeekBar.setProgress(this.mRecordSensitive_Y);
    }

    private void initGyonLayout() {
        if (this.mVisibleGyon) {
            initViewStub();
            initGronSeekBar();
            initGyonView();
            initGyonListener();
        }
    }

    private void initGyonListener() {
        this.mXSensitiveDown.setOnClickListener(this);
        this.mXSensitiveUp.setOnClickListener(this);
        this.mYSensitiveDown.setOnClickListener(this);
        this.mYSensitiveUp.setOnClickListener(this);
    }

    private void initGyonView() {
        TextView textView = (TextView) findViewById(R.id.x_sensitive_level);
        this.mXSensitiveLevel = textView;
        textView.setText(String.valueOf(this.mRecordSensitive_X) + PERCENT);
        this.mXSensitiveDown = (ImageView) findViewById(R.id.x_sensitive_down);
        this.mXSensitiveUp = (ImageView) findViewById(R.id.x_sensitive_up);
        TextView textView2 = (TextView) findViewById(R.id.y_sensitive_level);
        this.mYSensitiveLevel = textView2;
        textView2.setText(String.valueOf(this.mRecordSensitive_Y) + PERCENT);
        this.mYSensitiveDown = (ImageView) findViewById(R.id.y_sensitive_down);
        this.mYSensitiveUp = (ImageView) findViewById(R.id.y_sensitive_up);
    }

    private void initPrecisionCtlStub() {
        if (ControlPanelFeatureHelper.supportPrecisionByFeature(Utils.getCurrentPkgName()).booleanValue()) {
            LogUtil.d(TAG, "initPrecisionCtlStub() ");
            ViewStub viewStub = (ViewStub) findViewById(R.id.precision_control);
            this.mPrecisionCtlStub = viewStub;
            viewStub.inflate();
            this.mPrecisionSwitch = (ImageView) findViewById(R.id.precision_control_checkbox);
            this.mPrecisionControlLayout = findViewById(R.id.precision_control_layout);
            this.mPrecisionSwitch.setOnClickListener(this);
            updatePrecisionSwitch(isPrecisionSwitchOn(Utils.getCurrentPkgName()));
        }
    }

    private void initPreventTouchAccidentialLayout(Context context, int i, int i2) {
        this.mPreventAccidentialTouchLayout = findViewById(R.id.prevent_accidential_touch_layout);
        this.mSmallRange = (TextView) findViewById(R.id.small_range_text);
        this.mMiddleRange = (TextView) findViewById(R.id.middle_range_text);
        this.mBigRange = (TextView) findViewById(R.id.big_range_text);
        this.mPreventSwitch = (ImageView) findViewById(R.id.prevent_touch_switch);
        this.mRangeDisplay = (ImageView) findViewById(R.id.range_display_image);
        this.mSmallRange.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameAdjustOperationView.this.setPreventSource(R.drawable.shape_sample_rate, 1.0f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.prevent_touch_small_range);
                GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(1, -1);
            }
        });
        this.mMiddleRange.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameAdjustOperationView.this.setPreventSource(R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate, 1.0f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.prevent_touch_middle_range);
                GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(1, 0);
            }
        });
        this.mBigRange.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameAdjustOperationView.this.setPreventSource(R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate, 1.0f, R.drawable.prevent_tounch_big_range);
                GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(1, 1);
            }
        });
        this.mPreventSwitch.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i3;
                int i4;
                int i5;
                float f;
                float f2;
                int i6;
                float f3;
                int i7;
                float f4;
                int[] value = TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION.getValue();
                int i8 = value[0];
                int i9 = value[1];
                int i10 = R.drawable.prevent_tounch_big_range;
                int i11 = R.drawable.shape_sample_rate2;
                float f5 = 0.5f;
                if (i8 == 1) {
                    if (i9 == -1) {
                        GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(0, -1);
                    } else {
                        if (i9 == 0) {
                            GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(0, 0);
                            i10 = R.drawable.prevent_touch_middle_range;
                        } else if (i9 == 1) {
                            GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(0, 1);
                        }
                        GameAdjustOperationView.this.mSmallRange.setClickable(false);
                        GameAdjustOperationView.this.mMiddleRange.setClickable(false);
                        GameAdjustOperationView.this.mBigRange.setClickable(false);
                        GameAdjustOperationView.this.mPreventSwitch.setImageResource(R.drawable.function_toggle_off);
                        i3 = i10;
                        i6 = R.drawable.shape_sample_rate2;
                        i4 = i6;
                        i5 = i4;
                        f3 = 0.5f;
                        f = 0.5f;
                        f2 = 0.5f;
                    }
                    i10 = R.drawable.prevent_touch_small_range;
                    GameAdjustOperationView.this.mSmallRange.setClickable(false);
                    GameAdjustOperationView.this.mMiddleRange.setClickable(false);
                    GameAdjustOperationView.this.mBigRange.setClickable(false);
                    GameAdjustOperationView.this.mPreventSwitch.setImageResource(R.drawable.function_toggle_off);
                    i3 = i10;
                    i6 = R.drawable.shape_sample_rate2;
                    i4 = i6;
                    i5 = i4;
                    f3 = 0.5f;
                    f = 0.5f;
                    f2 = 0.5f;
                } else {
                    int i12 = R.drawable.shape_sample_rate;
                    float f6 = 1.0f;
                    if (i8 == 0) {
                        if (i9 == -1) {
                            GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(1, -1);
                        } else {
                            if (i9 == 0) {
                                GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(1, 0);
                                i10 = R.drawable.prevent_touch_middle_range;
                                i7 = R.drawable.shape_sample_rate2;
                                f4 = 0.5f;
                            } else if (i9 == 1) {
                                GameAdjustOperationView.this.putValueToDBForPrevenAccidentTouch(1, 1);
                                i7 = R.drawable.shape_sample_rate;
                                f4 = 1.0f;
                                i12 = R.drawable.shape_sample_rate2;
                                f6 = 0.5f;
                            }
                            GameAdjustOperationView.this.mSmallRange.setClickable(true);
                            GameAdjustOperationView.this.mMiddleRange.setClickable(true);
                            GameAdjustOperationView.this.mBigRange.setClickable(true);
                            GameAdjustOperationView.this.mPreventSwitch.setImageResource(R.drawable.function_toggle_on);
                            i5 = i7;
                            f2 = f4;
                            i3 = i10;
                            i6 = i11;
                            f3 = f5;
                            i4 = i12;
                            f = f6;
                        }
                        i10 = R.drawable.prevent_touch_small_range;
                        i7 = R.drawable.shape_sample_rate2;
                        f4 = 0.5f;
                        i11 = R.drawable.shape_sample_rate;
                        i12 = i7;
                        f5 = 1.0f;
                        f6 = 0.5f;
                        GameAdjustOperationView.this.mSmallRange.setClickable(true);
                        GameAdjustOperationView.this.mMiddleRange.setClickable(true);
                        GameAdjustOperationView.this.mBigRange.setClickable(true);
                        GameAdjustOperationView.this.mPreventSwitch.setImageResource(R.drawable.function_toggle_on);
                        i5 = i7;
                        f2 = f4;
                        i3 = i10;
                        i6 = i11;
                        f3 = f5;
                        i4 = i12;
                        f = f6;
                    } else {
                        i3 = R.drawable.prevent_touch_small_range;
                        i4 = R.drawable.shape_sample_rate2;
                        i5 = i4;
                        f = 0.5f;
                        f2 = 0.5f;
                        i6 = R.drawable.shape_sample_rate;
                        f3 = 1.0f;
                    }
                }
                GameAdjustOperationView.this.setPreventSource(i6, f3, i4, f, i5, f2, i3);
            }
        });
        if (i == 1) {
            if (i2 == -1) {
                setPreventSource(R.drawable.shape_sample_rate, 1.0f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.prevent_touch_small_range);
                return;
            } else if (i2 == 0) {
                setPreventSource(R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate, 1.0f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.prevent_touch_middle_range);
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                setPreventSource(R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate, 1.0f, R.drawable.prevent_tounch_big_range);
                return;
            }
        }
        if (i != 0) {
            this.mSmallRange.setClickable(false);
            this.mMiddleRange.setClickable(false);
            this.mBigRange.setClickable(false);
            this.mPreventSwitch.setImageResource(R.drawable.function_toggle_off);
            setPreventSource(R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.prevent_touch_middle_range);
            return;
        }
        int i3 = R.drawable.prevent_touch_small_range;
        if (i2 != -1) {
            if (i2 == 0) {
                i3 = R.drawable.prevent_touch_middle_range;
            } else if (i2 == 1) {
                i3 = R.drawable.prevent_tounch_big_range;
            }
        }
        int i4 = i3;
        this.mSmallRange.setClickable(false);
        this.mMiddleRange.setClickable(false);
        this.mBigRange.setClickable(false);
        this.mPreventSwitch.setImageResource(R.drawable.function_toggle_off);
        setPreventSource(R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, R.drawable.shape_sample_rate2, 0.5f, i4);
    }

    private void initRootLayout() {
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.root_adjust_operation_port : R.layout.root_adjust_operation, this);
        this.mLayoutView = findViewById(R.id.game_adjust_all_layout);
        this.mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.1
            @Override // java.lang.Runnable
            public void run() {
                AnimationUtil.setGpuTranslationY(GameAdjustOperationView.this.mLayoutView);
            }
        });
    }

    private void initSampleRateLayout(int i) {
        this.mSampleRateLayout = findViewById(R.id.sample_rate_layout);
        this.mSample480 = (TextView) findViewById(R.id.sample_text_480);
        this.mSample960 = (TextView) findViewById(R.id.sample_text_960);
        if (i == LOW_TOUCH_SAMPLE_RATE_360 || i == HIGH_TOUCH_SAMPLE_RATE_480 || i == 120) {
            this.mSample480.setBackgroundResource(R.drawable.shape_sample_rate);
            this.mSample960.setBackgroundResource(R.drawable.shape_sample_rate2);
            this.mSample960.setAlpha(0.5f);
            this.mSample480.setAlpha(1.0f);
            switchTextViewFocus(this.mSample480, true);
        } else if (i == LOW_TOUCH_SAMPLE_RATE_720 || i == HIGH_TOUCH_SAMPLE_RATE_960 || i == 240) {
            this.mSample480.setBackgroundResource(R.drawable.shape_sample_rate2);
            this.mSample960.setBackgroundResource(R.drawable.shape_sample_rate);
            this.mSample960.setAlpha(1.0f);
            this.mSample480.setAlpha(0.5f);
            switchTextViewFocus(this.mSample960, true);
        }
        putValueToDBForSampleRate(i);
        this.mSample480.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE.getValue()[0] != GameAdjustOperationView.HIGH_TOUCH_SAMPLE_RATE_480) {
                    GameAdjustOperationView.this.mSample480.setBackgroundResource(R.drawable.shape_sample_rate);
                    GameAdjustOperationView.this.mSample960.setBackgroundResource(R.drawable.shape_sample_rate2);
                    GameAdjustOperationView.this.mSample960.setAlpha(0.5f);
                    GameAdjustOperationView.this.mSample480.setAlpha(1.0f);
                    GameAdjustOperationView gameAdjustOperationView = GameAdjustOperationView.this;
                    gameAdjustOperationView.switchTextViewFocus(gameAdjustOperationView.mSample960, false);
                    GameAdjustOperationView gameAdjustOperationView2 = GameAdjustOperationView.this;
                    gameAdjustOperationView2.switchTextViewFocus(gameAdjustOperationView2.mSample480, true);
                    GameAdjustOperationView.this.putValueToDBForSampleRate(TouchOperationHelper.getHighTouchRate());
                }
            }
        });
        this.mSample960.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE.getValue()[0] != GameAdjustOperationView.HIGH_TOUCH_SAMPLE_RATE_960) {
                    GameAdjustOperationView.this.mSample480.setBackgroundResource(R.drawable.shape_sample_rate2);
                    GameAdjustOperationView.this.mSample960.setBackgroundResource(R.drawable.shape_sample_rate);
                    GameAdjustOperationView.this.mSample960.setAlpha(1.0f);
                    GameAdjustOperationView.this.mSample480.setAlpha(0.5f);
                    GameAdjustOperationView gameAdjustOperationView = GameAdjustOperationView.this;
                    gameAdjustOperationView.switchTextViewFocus(gameAdjustOperationView.mSample480, false);
                    GameAdjustOperationView gameAdjustOperationView2 = GameAdjustOperationView.this;
                    gameAdjustOperationView2.switchTextViewFocus(gameAdjustOperationView2.mSample960, true);
                    GameAdjustOperationView.this.putValueToDBForSampleRate(TouchOperationHelper.getSuperHighTouchRate());
                }
            }
        });
    }

    private void initTouchScreenLayout() {
        this.mTouchScreenLayout = findViewById(R.id.touch_screen_layout);
        this.mKeenLayout = findViewById(R.id.keen_layout);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_keen);
        this.mKeenSeekBar = seekBar;
        seekBar.setMax(this.T_PROGRESS_MAX);
        this.mKeenSeekBar.setMin(this.T_PROGRESS_MIN);
        this.mKeenSeekBar.setProgress(findProgress(this.mRecordKeenLevel));
        this.mKeenSeekBar.setOnSeekBarChangeListener(this);
        putLevelToDBForTouchScreen(10, this.mRecordKeenLevel);
        this.mFollowLayout = findViewById(R.id.follow_layout);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seek_bar_follow);
        this.mFollowSeekBar = seekBar2;
        seekBar2.setMax(this.T_PROGRESS_MAX);
        this.mFollowSeekBar.setMin(this.T_PROGRESS_MIN);
        this.mFollowSeekBar.setProgress(findProgress(this.mRecordFollowLevel));
        this.mFollowSeekBar.setOnSeekBarChangeListener(this);
        putLevelToDBForTouchScreen(11, this.mRecordFollowLevel);
        this.mMicroSensitivityLayout = findViewById(R.id.micro_sensitivity_layout);
        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seek_bar_micro_keen);
        this.mMicroSensitivitySeekBar = seekBar3;
        seekBar3.setMax(this.T_PROGRESS_MAX);
        this.mMicroSensitivitySeekBar.setMin(this.T_PROGRESS_MIN);
        this.mMicroSensitivitySeekBar.setProgress(findProgress(this.mRecordMicroSensitiveLevel));
        this.mMicroSensitivitySeekBar.setOnSeekBarChangeListener(this);
        putLevelToDBForTouchScreen(12, this.mRecordMicroSensitiveLevel);
    }

    private void initViewData(boolean z, boolean z2, boolean z3) {
        ViewStub viewStub;
        int i = TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE.getValue()[0];
        int i2 = TouchOperationBean.OperationTypeParams.TOUCH_SEN.getValue()[0];
        int i3 = TouchOperationBean.OperationTypeParams.TOUCH_FOLLOW.getValue()[0];
        int i4 = TouchOperationBean.OperationTypeParams.TOUCH_MICRO_SENSITIVE.getValue()[0];
        int i5 = TouchOperationBean.OperationTypeParams.GYROSEN.getValue().length > 1 ? TouchOperationBean.OperationTypeParams.GYROSEN.getValue()[0] : 100;
        int i6 = TouchOperationBean.OperationTypeParams.GYROSEN.getValue().length > 1 ? TouchOperationBean.OperationTypeParams.GYROSEN.getValue()[1] : 100;
        int i7 = TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION.getValue()[0];
        int i8 = TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION.getValue()[1];
        LogUtil.d(TAG, "initViewData  isSupportTouchSample:" + z + " isSupportTouchProtection:" + z3 + " isSupportGyro:" + z2 + " touchSample:" + i + " touchSenLevel:" + i2 + " followLevel" + i3 + " microSensitiveLevel" + i4 + " sensitive_X:" + i5 + " sensitive_Y:" + i6 + " touchProtectionOpen:" + i7 + " touchProtectionLevel:" + i8);
        setTouchScreenVariable(i2, i3, i4);
        setGyonVariable(z2, i5, i6);
        initAiAdjustStub();
        initTouchScreenLayout();
        initPrecisionCtlStub();
        initGyonLayout();
        initSampleRateLayout(i);
        initPreventTouchAccidentialLayout(this.mContext, i7, i8);
        if (TextUtils.isEmpty(ControlPanelFeatureHelper.getAdjustOperationSupportItem())) {
            return;
        }
        if (!TouchOperationHelper.supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType.TouchSampleRate).booleanValue()) {
            this.mSampleRateLayout.setVisibility(8);
        }
        if (!TouchOperationHelper.supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType.TouchSen).booleanValue()) {
            this.mKeenLayout.setVisibility(8);
        }
        if (!TouchOperationHelper.supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType.TouchFollow).booleanValue()) {
            this.mFollowLayout.setVisibility(8);
        }
        if (!TouchOperationHelper.supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType.TouchMicroSensitive).booleanValue()) {
            this.mMicroSensitivityLayout.setVisibility(8);
        }
        if (this.mSampleRateLayout.getVisibility() == 8 && this.mKeenLayout.getVisibility() == 8 && this.mFollowLayout.getVisibility() == 8 && this.mMicroSensitivityLayout.getVisibility() == 8) {
            this.mTouchScreenLayout.setVisibility(8);
        }
        if (!TouchOperationHelper.supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType.GyroSen).booleanValue() && (viewStub = this.mViewStub) != null) {
            viewStub.setVisibility(8);
        }
        if (!TouchOperationHelper.supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType.TouchProtectOpen).booleanValue()) {
            this.mPreventAccidentialTouchLayout.setVisibility(8);
        }
        this.mScrollView.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameAdjustOperationView.this.m249xaafb1492();
            }
        });
        showFlicker(Utils.getHighLightViewId());
    }

    private void initViewStub() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.gyro);
        this.mViewStub = viewStub;
        viewStub.inflate();
        this.mGyroLayout = findViewById(R.id.gyro_sensitive_layout);
    }

    private boolean isAiAdjustSwitchOn(String str) {
        String string = Settings.Global.getString(this.mContext.getContentResolver(), ZTE_AI_ADJUST_ENABLED);
        LogUtil.d(TAG, "isAiAdjustSwitchOn() packageNames : " + string);
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(str);
    }

    private boolean isMax(int i) {
        return i >= 200;
    }

    private boolean isMin(int i) {
        return i <= 1;
    }

    private boolean isPrecisionSwitchOn(String str) {
        String string = Settings.Global.getString(this.mContext.getContentResolver(), ZTE_PRECISION_CONTROL_ENABLED);
        LogUtil.d(TAG, "isPrecisionSwitchOn() packageNames : " + string);
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(str);
    }

    private void notifyProgressChanged(SeekBar seekBar, int i, int i2) {
        seekBar.setProgress(i2);
        putLevelToDBForGyon(i);
    }

    private void progressChangedForXAxis(int i) {
        int i2 = this.mRecordSensitive_X;
        if (i2 == i) {
            return;
        }
        if ((i2 > 100 && i <= 100) || (i2 <= 100 && i > 100)) {
            this.mXAxisSeekBar.setProgressDrawable(getXProgressDrawable(i));
        }
        this.mRecordSensitive_X = i;
        this.mXSensitiveLevel.setText(String.valueOf(i) + PERCENT);
    }

    private void progressChangedForYAxis(int i) {
        int i2 = this.mRecordSensitive_Y;
        if (i2 == i) {
            return;
        }
        if ((i2 > 100 && i <= 100) || (i2 <= 100 && i > 100)) {
            this.mYAxisSeekBar.setProgressDrawable(getYProgressDrawable(i));
        }
        this.mRecordSensitive_Y = i;
        this.mYSensitiveLevel.setText(String.valueOf(i) + PERCENT);
    }

    private void putLevelToDBForGyon(int i) {
        if (i == 20) {
            putLevelToDBForGyon(20, this.mRecordSensitive_X);
        } else {
            if (i != 21) {
                return;
            }
            putLevelToDBForGyon(20, this.mRecordSensitive_Y);
        }
    }

    private void putLevelToDBForGyon(int i, int i2) {
        LogUtil.d(TAG, "putLevelToDBForGyon type:" + i + " Sensitive_X:" + this.mRecordSensitive_X + " Sensitive_Y:" + this.mRecordSensitive_Y);
        if (this.mIGameStrengthSelectedListener != null) {
            TouchOperationBean.OperationTypeParams.GYROSEN.setValue(new int[]{this.mRecordSensitive_X, this.mRecordSensitive_Y});
            this.mIGameStrengthSelectedListener.onAdjustOperationDataChanged(TouchOperationBean.OperationTypeParams.GYROSEN);
        }
    }

    private void putLevelToDBForTouchScreen(int i, int i2) {
        TouchOperationBean.OperationTypeParams operationTypeParams;
        LogUtil.d(TAG, "putLevelToDBForTouchScreen type:" + i + " level:" + i2);
        switch (i) {
            case 10:
                operationTypeParams = TouchOperationBean.OperationTypeParams.TOUCH_SEN;
                operationTypeParams.setValue(new int[]{i2});
                break;
            case 11:
                operationTypeParams = TouchOperationBean.OperationTypeParams.TOUCH_FOLLOW;
                operationTypeParams.setValue(new int[]{i2});
                break;
            case 12:
                operationTypeParams = TouchOperationBean.OperationTypeParams.TOUCH_MICRO_SENSITIVE;
                operationTypeParams.setValue(new int[]{i2});
                break;
            default:
                operationTypeParams = null;
                break;
        }
        IGameStrengthSelectedListener iGameStrengthSelectedListener = this.mIGameStrengthSelectedListener;
        if (iGameStrengthSelectedListener == null || operationTypeParams == null) {
            return;
        }
        iGameStrengthSelectedListener.onAdjustOperationDataChanged(operationTypeParams);
        reportTouchScreenUsed(i, operationTypeParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putValueToDBForPrevenAccidentTouch(int i, int i2) {
        if (this.mIGameStrengthSelectedListener != null) {
            TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION.setValue(new int[]{i, i2});
            this.mIGameStrengthSelectedListener.onAdjustOperationDataChanged(TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION);
            reportPreventAccidentTouchUsed(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putValueToDBForSampleRate(int i) {
        if (this.mIGameStrengthSelectedListener != null) {
            TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE.setValue(new int[]{i});
            this.mIGameStrengthSelectedListener.onAdjustOperationDataChanged(TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE);
            reportTouchSampleRateUsed(i);
        }
    }

    private String removePackageFromList(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(";");
        StringBuilder sb = new StringBuilder();
        for (String str3 : split) {
            if (!str3.trim().isEmpty() && !str3.equals(str2)) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(str3);
            }
        }
        return sb.toString();
    }

    private void reportPreventAccidentTouchUsed(int i, int i2) {
        String str;
        String str2 = i != 0 ? "on" : "off";
        if (i2 != -1) {
            str = "mid";
            if (i2 != 0 && i2 == 1) {
                str = "big";
            }
        } else {
            str = "small";
        }
        Bundle bundle = new Bundle();
        bundle.putString("level", str);
        bundle.putString("switch_status", str2);
        LogUtil.d(TAG, "  reportPreventAccidentTouchUsed level = " + str + "  ;; switch_Status = " + str2);
        bundle.putCharSequence("app_name ", Utils.getCurrentAppName());
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "touch_project_switch_used", bundle);
    }

    private void reportTouchSampleRateUsed(int i) {
        String str = "low";
        if (i != 120 && (i == 240 || (i != LOW_TOUCH_SAMPLE_RATE_360 && i != HIGH_TOUCH_SAMPLE_RATE_480 && (i == LOW_TOUCH_SAMPLE_RATE_720 || i == HIGH_TOUCH_SAMPLE_RATE_960)))) {
            str = "high";
        }
        Bundle bundle = new Bundle();
        bundle.putString("level", str);
        LogUtil.d(TAG, "  reportTouchSampleRateUsed level = ".concat(str));
        bundle.putCharSequence("app_name ", Utils.getCurrentAppName());
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "touch_sample_rate_switch_used", bundle);
    }

    private void reportTouchScreenUsed(int i, TouchOperationBean.OperationTypeParams operationTypeParams) {
        String str;
        switch (i) {
            case 10:
                str = "touch_sen_switch_used";
                break;
            case 11:
                str = "touch_follow_switch_used";
                break;
            case 12:
                str = "control_stability_setting";
                break;
            default:
                str = null;
                break;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int i2 = operationTypeParams.getValue()[0];
        Bundle bundle = new Bundle();
        bundle.putInt("level", i2);
        String currentPkgName = Utils.getCurrentPkgName();
        CharSequence currentAppName = Utils.getCurrentAppName();
        bundle.putString("package_name", currentPkgName);
        bundle.putCharSequence("app_name ", currentAppName);
        LogUtil.d(TAG, "  reportTouchScreenUsed level = " + i2 + "  ;; event = " + str + " ;; package_name : " + currentPkgName + " ;; appName = " + ((Object) currentAppName));
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", str, bundle);
    }

    private void sendBroadcastToPrecision(String str) {
        Intent intent = new Intent("cn.zte.gamefloat.precisionctl.ACTION_FOREGROUND_GAME_CHANGE");
        intent.setPackage("cn.zte.gamefloat");
        intent.putExtra("packageName", str);
        intent.putExtra("game_panel", true);
        this.mContext.sendBroadcast(intent);
    }

    private void setGyonVariable(boolean z, int i, int i2) {
        this.mVisibleGyon = z;
        if (z) {
            this.mRecordSensitive_X = i;
            this.mRecordSensitive_Y = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreventSource(int i, float f, int i2, float f2, int i3, float f3, int i4) {
        this.mSmallRange.setBackgroundResource(i);
        this.mSmallRange.setAlpha(f);
        this.mMiddleRange.setBackgroundResource(i2);
        this.mMiddleRange.setAlpha(f2);
        this.mBigRange.setBackgroundResource(i3);
        this.mBigRange.setAlpha(f3);
        this.mRangeDisplay.setImageResource(i4);
    }

    private void setTouchScreenVariable(int i, int i2, int i3) {
        this.mRecordKeenLevel = i;
        this.mRecordFollowLevel = i2;
        this.mRecordMicroSensitiveLevel = i3;
    }

    private void showFlicker(String str) {
        View showFlickerView;
        if (TextUtils.isEmpty(str) || (showFlickerView = getShowFlickerView(str)) == null) {
            return;
        }
        AIFlickerTips.setFlickerName(showFlickerView, str);
        AIFlickerTips.setFlickerPadding(showFlickerView, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchTextViewFocus(TextView textView, boolean z) {
        textView.onWindowFocusChanged(z);
    }

    private void updateAiSwitch(boolean z) {
        if (this.mAiSwitch == null) {
            return;
        }
        LogUtil.d(TAG, "updateAiSwitch() isOn : " + z);
        this.mAiSwitch.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    private void updatePrecisionSwitch(boolean z) {
        if (this.mPrecisionSwitch == null) {
            return;
        }
        LogUtil.d(TAG, "mPrecisionSwitch() isOn : " + z);
        this.mPrecisionSwitch.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameAdjustOperationView.8
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    GameAdjustOperationView.this.mLayoutView.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(GameAdjustOperationView.this.mLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(GameAdjustOperationView.this.mLayoutView);
                }
            }
        });
    }

    public void initData(TouchOperationBean touchOperationBean) {
        initViewData(touchOperationBean.isSupportTouchSample(), touchOperationBean.isSupportGyro(), touchOperationBean.isSupportTouchProtection());
    }

    /* renamed from: lambda$initViewData$0$cn-nubia-gamelauncher-gamecontrolpanel-GameAdjustOperationView, reason: not valid java name */
    /* synthetic */ void m249xaafb1492() {
        View showFlickerView = getShowFlickerView(Utils.getHighLightViewId());
        if (showFlickerView != null) {
            showFlickerView.requestRectangleOnScreen(new Rect(0, 0, showFlickerView.getWidth(), showFlickerView.getHeight()), true);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ai_adjust_checkbox /* 2131361889 */:
                clickAiSwitch(Utils.getCurrentPkgName());
                break;
            case R.id.precision_control_checkbox /* 2131363055 */:
                clickPrecisionSwitch(Utils.getCurrentPkgName());
                break;
            case R.id.x_sensitive_down /* 2131363718 */:
                if (!isMin(this.mRecordSensitive_X)) {
                    notifyProgressChanged(this.mXAxisSeekBar, 20, this.mRecordSensitive_X - 1);
                    break;
                }
                break;
            case R.id.x_sensitive_up /* 2131363720 */:
                if (!isMax(this.mRecordSensitive_X)) {
                    notifyProgressChanged(this.mXAxisSeekBar, 20, this.mRecordSensitive_X + 1);
                    break;
                }
                break;
            case R.id.y_sensitive_down /* 2131363723 */:
                if (!isMin(this.mRecordSensitive_Y)) {
                    notifyProgressChanged(this.mYAxisSeekBar, 21, this.mRecordSensitive_Y - 1);
                    break;
                }
                break;
            case R.id.y_sensitive_up /* 2131363725 */:
                if (!isMax(this.mRecordSensitive_Y)) {
                    notifyProgressChanged(this.mYAxisSeekBar, 21, this.mRecordSensitive_Y + 1);
                    break;
                }
                break;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (seekBar.getId() == R.id.x_axis_seek_bar) {
            progressChangedForXAxis(i);
        } else if (seekBar.getId() == R.id.y_axis_seek_bar) {
            progressChangedForYAxis(i);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seek_bar_keen) {
            int i = this.TOUCH_SCREEN_LEVEL[this.mKeenSeekBar.getProgress()];
            this.mRecordKeenLevel = i;
            putLevelToDBForTouchScreen(10, i);
            return;
        }
        if (seekBar.getId() == R.id.seek_bar_follow) {
            int i2 = this.TOUCH_SCREEN_LEVEL[this.mFollowSeekBar.getProgress()];
            this.mRecordFollowLevel = i2;
            putLevelToDBForTouchScreen(11, i2);
            return;
        }
        if (seekBar.getId() == R.id.seek_bar_micro_keen) {
            int i3 = this.TOUCH_SCREEN_LEVEL[this.mMicroSensitivitySeekBar.getProgress()];
            this.mRecordMicroSensitiveLevel = i3;
            putLevelToDBForTouchScreen(12, i3);
            return;
        }
        if (seekBar.getId() == R.id.x_axis_seek_bar) {
            putLevelToDBForGyon(20, this.mRecordSensitive_X);
        } else if (seekBar.getId() == R.id.y_axis_seek_bar) {
            putLevelToDBForGyon(21, this.mRecordSensitive_Y);
        }
    }

    public void setGameStrengthSelectedListener(IGameStrengthSelectedListener iGameStrengthSelectedListener) {
        this.mIGameStrengthSelectedListener = iGameStrengthSelectedListener;
    }
}
