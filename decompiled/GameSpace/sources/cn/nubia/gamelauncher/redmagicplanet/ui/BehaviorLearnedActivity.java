package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;

/* loaded from: classes.dex */
public class BehaviorLearnedActivity extends Activity implements View.OnClickListener {
    private static final String BEHAVIOR_LEARNED_KEY = "zte_learned_behavior_enable";
    private static final String EVENT_KEY = "switch_status";
    private static final String EVENT_NAME = "behavioral_learning_switch_status";
    private static final String EVENT_VALUE_OFF = "off";
    private static final String EVENT_VALUE_ON = "on";
    private static final String TAG = "BehaviorLearnedActivity";
    private int firstInit = 0;
    private View mBackView;
    private ToggleButton mSwitch;
    private View mSwitchLayout;
    private ImageView switchButtonThumbBlack;
    private ImageView switchButtonThumbRed;
    private ImageView switchButtonTrackBlack;
    private ImageView switchButtonTrackGray;

    private void initViews() {
        View findViewById = findViewById(R.id.back);
        this.mBackView = findViewById;
        findViewById.setOnClickListener(this);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.gamespace_switch);
        this.mSwitch = toggleButton;
        toggleButton.setClickable(true);
        ImageView imageView = (ImageView) findViewById(R.id.iv_behavior_bg);
        getResources().getConfiguration().getLocales().get(0).getLanguage();
        imageView.setImageDrawable(getDrawable(R.drawable.behavior_learned_left_image));
        this.mSwitchLayout = findViewById(R.id.switch_button_layout);
        this.switchButtonTrackBlack = (ImageView) findViewById(R.id.switch_button_track_black);
        this.switchButtonTrackGray = (ImageView) findViewById(R.id.switch_button_track_gray);
        this.switchButtonThumbRed = (ImageView) findViewById(R.id.switch_button_thumb_red);
        this.switchButtonThumbBlack = (ImageView) findViewById(R.id.switch_button_thumb_black);
        this.mSwitchLayout.setAlpha(1.0f);
        this.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.BehaviorLearnedActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BehaviorLearnedActivity.this.syncSwitchView(z, true);
                Settings.Global.putInt(BehaviorLearnedActivity.this.getContentResolver(), BehaviorLearnedActivity.BEHAVIOR_LEARNED_KEY, z ? 1 : 0);
                if (CommonUtil.isInternalVersion()) {
                    return;
                }
                NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", BehaviorLearnedActivity.EVENT_NAME, "switch_status", z ? "on" : "off");
            }
        });
    }

    private void setThumbAnim(View view, View view2, View view3, View view4, boolean z) {
        ObjectAnimator objectAnimator;
        AnimatorSet animatorSet = new AnimatorSet();
        float applyDimension = TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONX, 0.0f, applyDimension);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, AnimatorHelper.Item.TRANSLATIONX, 0.0f, applyDimension);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONX, applyDimension, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view2, AnimatorHelper.Item.TRANSLATIONX, applyDimension, 0.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view2, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view3, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view4, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, 1.0f, 0.0f);
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(view2, AnimatorHelper.Item.ALPHA, 1.0f, 0.0f);
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(view3, AnimatorHelper.Item.ALPHA, 1.0f, 0.0f);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(view4, AnimatorHelper.Item.ALPHA, 1.0f, 0.0f);
        if (this.firstInit == 0) {
            objectAnimator = ofFloat;
            animatorSet.setDuration(0L);
            this.firstInit++;
        } else {
            objectAnimator = ofFloat;
            animatorSet.setDuration(250L);
        }
        if (z) {
            animatorSet.playTogether(objectAnimator, ofFloat2, ofFloat9, ofFloat6, ofFloat7, ofFloat12);
        } else {
            animatorSet.playTogether(ofFloat3, ofFloat4, ofFloat5, ofFloat10, ofFloat11, ofFloat8);
        }
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncSwitchView(boolean z, boolean z2) {
        this.mSwitch.setChecked(z);
        if (z2) {
            setThumbAnim(this.switchButtonThumbBlack, this.switchButtonThumbRed, this.switchButtonTrackBlack, this.switchButtonTrackGray, z);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.back) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.behavior_learned_activity_main);
        getWindow().getDecorView().setSystemUiVisibility(4102);
        initViews();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        syncSwitchView(Settings.Global.getInt(getContentResolver(), BEHAVIOR_LEARNED_KEY, 0) == 1, false);
    }
}
