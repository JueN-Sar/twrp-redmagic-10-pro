package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import com.zte.gameassist.ext.GameAssistChannel;

/* loaded from: classes.dex */
public class GameAgentActivity extends Activity implements View.OnClickListener {
    private static final String KEY_SETTINGS_VOICE_ASSIST_V2 = "game_voice_assist_v2_switch";
    private static final String TAG = "GameAgentActivity";
    private ConstraintLayout behavior_learned_item;
    private String[] descriptions = new String[2];
    private int firstInit = 0;
    private LinearLayout game_agent_indicator;
    private ViewPager game_agent_viewpager;
    private View indicator_0;
    private View indicator_1;
    private ConstraintLayout low_sugar_game_item;
    private View mBackView;
    private Handler mHandler;
    private boolean mIsLowSugarSettingsOpened;
    private boolean mIsVoiceInteractionOpened;
    private GameAgentViewPagerAdapter mPageAdapter;
    private ToggleButton mSwitch;
    private View mSwitchLayout;
    private int mVoiceInteractionVersion;
    private ImageView switchButtonThumbBlack;
    private ImageView switchButtonThumbRed;
    private ImageView switchButtonTrackBlack;
    private ImageView switchButtonTrackGray;
    private TextView tv_description;
    private ConstraintLayout voice_interaction_item;

    private void initViewPager() {
        char c;
        this.mPageAdapter = new GameAgentViewPagerAdapter(this);
        if (FeatureUtil.behaviorLearnedEnable()) {
            this.behavior_learned_item.setVisibility(0);
            c = 1;
        } else {
            this.behavior_learned_item.setVisibility(8);
            this.mPageAdapter.removeBehavior();
            this.indicator_1.setVisibility(8);
            c = 65535;
        }
        if (FeatureUtil.lowsugarEnable()) {
            this.low_sugar_game_item.setVisibility(0);
            c = 0;
        } else {
            this.low_sugar_game_item.setVisibility(8);
            this.mPageAdapter.removeLowSugar();
            this.indicator_0.setVisibility(8);
        }
        updateVoiceInteractionItem();
        if (c == 65535) {
            return;
        }
        this.game_agent_viewpager.setAdapter(this.mPageAdapter);
        this.game_agent_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                GameAgentActivity.this.updatePageIndicator(i);
                GameAgentActivity.this.tv_description.setText(GameAgentActivity.this.descriptions[i]);
            }
        });
        updatePageIndicator(0);
        this.tv_description.setText(this.descriptions[c]);
    }

    private void initViews() {
        View findViewById = findViewById(R.id.back);
        this.mBackView = findViewById;
        findViewById.setOnClickListener(this);
        this.game_agent_viewpager = (ViewPager) findViewById(R.id.game_agent_viewpager);
        this.tv_description = (TextView) findViewById(R.id.tv_description);
        this.game_agent_indicator = (LinearLayout) findViewById(R.id.game_agent_indicator);
        this.indicator_0 = findViewById(R.id.indicator_0);
        this.indicator_1 = findViewById(R.id.indicator_1);
        this.low_sugar_game_item = (ConstraintLayout) findViewById(R.id.low_sugar_game_item);
        this.behavior_learned_item = (ConstraintLayout) findViewById(R.id.behavior_learned_item);
        this.voice_interaction_item = (ConstraintLayout) findViewById(R.id.voice_interaction_item);
        this.low_sugar_game_item.setOnClickListener(this);
        this.behavior_learned_item.setOnClickListener(this);
        this.voice_interaction_item.setOnClickListener(this);
        this.mSwitch = (ToggleButton) findViewById(R.id.gamespace_switch);
        this.mSwitchLayout = findViewById(R.id.switch_button_layout);
        this.switchButtonTrackBlack = (ImageView) findViewById(R.id.switch_button_track_black);
        this.switchButtonTrackGray = (ImageView) findViewById(R.id.switch_button_track_gray);
        this.switchButtonThumbRed = (ImageView) findViewById(R.id.switch_button_thumb_red);
        this.switchButtonThumbBlack = (ImageView) findViewById(R.id.switch_button_thumb_black);
        this.mSwitchLayout.setAlpha(1.0f);
        this.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Log.i(GameAgentActivity.TAG, "voice interaction switch changed: " + z + ", isOpened: " + GameAgentActivity.this.mIsVoiceInteractionOpened);
                GameAgentActivity.this.syncSwitchView(z, true);
                if (z != GameAgentActivity.this.mIsVoiceInteractionOpened) {
                    GameAgentActivity.this.setVoiceInteractionStatus(z);
                }
            }
        });
    }

    private boolean queryVoiceInteractionEnabled() {
        Bundle bundle = null;
        try {
            bundle = getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "wakeup_feature", (Bundle) null);
        } catch (Exception e) {
            Log.e(TAG, "queryVoiceInteractionEnabled error", e);
        }
        if (bundle != null) {
            return bundle.getBoolean("result", false);
        }
        return false;
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
    public void setVoiceInteractionStatus(final boolean z) {
        Log.i(TAG, "setVoiceInteractionStatus: isOpen=" + z);
        this.mIsVoiceInteractionOpened = z;
        new Thread(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                GameAgentActivity.this.m330xd1308326(z);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncSwitchView(boolean z, boolean z2) {
        this.mSwitch.setChecked(z);
        if (z2) {
            setThumbAnim(this.switchButtonThumbBlack, this.switchButtonThumbRed, this.switchButtonTrackBlack, this.switchButtonTrackGray, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePageIndicator(int i) {
        int childCount = this.game_agent_indicator.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            this.game_agent_indicator.getChildAt(i2).setBackgroundResource(i2 == i ? R.drawable.shape_redmagic_indicator_select : R.drawable.shape_redmagic_indicator_unselect);
            i2++;
        }
    }

    private void updateVoiceInteractionItem() {
        new Thread(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                GameAgentActivity.this.m332xfd97b605();
            }
        }).start();
    }

    private void updateVoiceInteractionSwitch() {
        new Thread(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GameAgentActivity.this.m334xdbead2a8();
            }
        }).start();
    }

    /* renamed from: lambda$setVoiceInteractionStatus$4$cn-nubia-gamelauncher-redmagicplanet-ui-GameAgentActivity, reason: not valid java name */
    /* synthetic */ void m330xd1308326(boolean z) {
        int i = this.mVoiceInteractionVersion;
        if (i == 2) {
            Settings.Global.putInt(getContentResolver(), KEY_SETTINGS_VOICE_ASSIST_V2, z ? 1 : 0);
        } else if (i == 1) {
            Bundle bundle = new Bundle();
            bundle.putInt("message", z ? 1 : 0);
            getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "wakeup_status_set", bundle);
        }
    }

    /* renamed from: lambda$updateVoiceInteractionItem$0$cn-nubia-gamelauncher-redmagicplanet-ui-GameAgentActivity, reason: not valid java name */
    /* synthetic */ void m331xece1e944(int i) {
        if (i > 0) {
            updateVoiceInteractionSwitch();
        }
        this.mVoiceInteractionVersion = i;
        this.voice_interaction_item.setVisibility(i > 0 ? 0 : 8);
    }

    /* renamed from: lambda$updateVoiceInteractionItem$1$cn-nubia-gamelauncher-redmagicplanet-ui-GameAgentActivity, reason: not valid java name */
    /* synthetic */ void m332xfd97b605() {
        final int i = FeatureUtil.voiceInteractionV2Enable() ? 2 : (FeatureUtil.voiceInteractionEnable() && queryVoiceInteractionEnabled()) ? 1 : 0;
        Log.i(TAG, "updateVoiceInteractionItem: version=" + i);
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                GameAgentActivity.this.m331xece1e944(i);
            }
        });
    }

    /* renamed from: lambda$updateVoiceInteractionSwitch$2$cn-nubia-gamelauncher-redmagicplanet-ui-GameAgentActivity, reason: not valid java name */
    /* synthetic */ void m333xcb3505e7(boolean z) {
        Log.i(TAG, "updateVoiceInteractionSwitch: isOpened=" + z);
        this.mIsVoiceInteractionOpened = z;
        syncSwitchView(z, false);
    }

    /* renamed from: lambda$updateVoiceInteractionSwitch$3$cn-nubia-gamelauncher-redmagicplanet-ui-GameAgentActivity, reason: not valid java name */
    /* synthetic */ void m334xdbead2a8() {
        final boolean z;
        int i = this.mVoiceInteractionVersion;
        if (i == 1) {
            z = queryVoiceInteractionStatus();
        } else if (i == 2) {
            z = Settings.Global.getInt(getContentResolver(), KEY_SETTINGS_VOICE_ASSIST_V2, 1) == 1;
        } else {
            z = false;
        }
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameAgentActivity.this.m333xcb3505e7(z);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back /* 2131361945 */:
                finish();
                break;
            case R.id.behavior_learned_item /* 2131361956 */:
                startActivity(new Intent(this, (Class<?>) BehaviorLearnedActivity.class));
                break;
            case R.id.low_sugar_game_item /* 2131362728 */:
                this.mIsLowSugarSettingsOpened = true;
                GameAssistChannel.sendToGameAssist("showLowSugarSettingsPanel");
                break;
            case R.id.voice_interaction_item /* 2131363665 */:
                Log.i(TAG, "onClick: voice_interaction_item isChecked=" + this.mSwitch.isChecked());
                syncSwitchView(!this.mSwitch.isChecked(), false);
                break;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.game_agent_activity_main);
        getWindow().getDecorView().setSystemUiVisibility(4102);
        Resources resources = GameLauncherApplication.CONTEXT.getResources();
        this.descriptions[0] = resources.getString(R.string.low_sugar_game_name) + ":" + resources.getString(R.string.low_sugar_game_content);
        this.descriptions[1] = resources.getString(R.string.learned_behavior_name) + ":" + resources.getString(R.string.learned_behavior_content_1);
        this.mHandler = new Handler(Looper.getMainLooper());
        initViews();
        initViewPager();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: mIsLowSugarSettingsOpened = " + this.mIsLowSugarSettingsOpened);
        if (this.mIsLowSugarSettingsOpened) {
            this.mIsLowSugarSettingsOpened = false;
            GameAssistChannel.sendToGameAssist("hideLowSugarSettingsPanel");
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: mVoiceInteractionVersion=" + this.mVoiceInteractionVersion);
        if (this.mVoiceInteractionVersion > 0) {
            updateVoiceInteractionSwitch();
        }
    }

    public boolean queryVoiceInteractionStatus() {
        Bundle call = getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "wakeup_status_get", (Bundle) null);
        if (call != null) {
            return call.getBoolean("result", false);
        }
        return false;
    }
}
