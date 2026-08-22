package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.media3.common.MimeTypes;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import com.zte.gameassist.ai.AIFlickerTips;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GameStrengthenVoiceView extends FrameLayout implements View.OnClickListener, GameControlDialog.ISetViewAnimation {
    private static final String SUPPORT_DTS_GAME_ENHANCE = "isDtsGameEnhanceSupported=on";
    private static final String TAG = "GameStrengthenVoiceView";
    private static final int VOICE_STRENGTH_MODE_DEFAULT = 0;
    private static final int VOICE_STRENGTH_MODE_MOVIES = 3;
    private static final int VOICE_STRENGTH_MODE_MUSIC = 2;
    private static final int VOICE_STRENGTH_MODE_SHOOTING = 1;
    private LinearLayout itemContent;
    private TextView itemHeadset;
    private String mCurrentPkgName;
    protected IGameStrengthSelectedListener mGameStrengthSelectedListener;
    private Boolean mHasConnected;
    private BroadcastReceiver mHeadsetReceiver;
    private boolean mIsInGameLauncher;
    private int mSelectedIndex;
    private View mTopLayout;
    private View mVoiceStrengthParentLayout;
    private TextView vPrompt;
    private List<GameStrengthenVoiceItemView> vVoiceItems;
    private GameStrengthenVoiceItemView voiceDefault;
    private GameStrengthenVoiceItemView voiceMovie;
    private GameStrengthenVoiceItemView voiceMusic;
    private GameStrengthenVoiceItemView voiceShoot;

    public GameStrengthenVoiceView(Context context) {
        this(context, null);
    }

    public GameStrengthenVoiceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameStrengthenVoiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSelectedIndex = -1;
        this.mHasConnected = false;
        this.mHeadsetReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenVoiceView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                LogUtil.i(GameStrengthenVoiceView.TAG, "*--------onReceive-----------" + action);
                if ("android.intent.action.HEADSET_PLUG".equals(action) && intent.hasExtra("state")) {
                    LogUtil.i(GameStrengthenVoiceView.TAG, "*--------onReceive--------- state = " + intent.getIntExtra("state", 0) + " ;; microphone = " + intent.getIntExtra("microphone", 0));
                    GameStrengthenVoiceView.this.mHasConnected = Boolean.valueOf(intent.getIntExtra("state", 0) != 0);
                    GameStrengthenVoiceView.this.initVoiceDtsView();
                }
            }
        };
    }

    private boolean checkHeadsetIsConnected(Context context) {
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).isWiredHeadsetOn() || this.mHasConnected.booleanValue();
    }

    private boolean checkIfDeepbufferVoice() {
        AudioManager audioManager = (AudioManager) getContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        int appUidByPkgName = Utils.getAppUidByPkgName(getContext(), this.mCurrentPkgName);
        StringBuilder sb = new StringBuilder("isDtsGameEnhanceSupported=");
        sb.append(appUidByPkgName);
        LogUtil.e(TAG, " ;; parameters = " + ((Object) sb));
        Boolean isDtsSupportedForUid = Utils.isDtsSupportedForUid(getContext(), appUidByPkgName);
        LogUtil.i(TAG, " isSupport = " + isDtsSupportedForUid);
        if (isDtsSupportedForUid != null) {
            return isDtsSupportedForUid.booleanValue();
        }
        String parameters = audioManager.getParameters(sb.toString());
        LogUtil.e(TAG, "isDtsGameEnhanceSupported: " + parameters);
        return SUPPORT_DTS_GAME_ENHANCE.equals(parameters);
    }

    private void initListener() {
        int size = this.vVoiceItems.size();
        for (int i = 0; i < size; i++) {
            this.vVoiceItems.get(i).setOnClickListener(this);
        }
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.nubia_game_strengthen_view_voice_port : R.layout.nubia_game_strengthen_view_voice, this);
        this.mTopLayout = findViewById(R.id.strength_voice_top_layout);
        this.vVoiceItems = new ArrayList(4);
        GameStrengthenVoiceItemView gameStrengthenVoiceItemView = (GameStrengthenVoiceItemView) findViewById(R.id.nubia_game_strength_voice_default);
        this.voiceDefault = gameStrengthenVoiceItemView;
        gameStrengthenVoiceItemView.setBackgroundResource(R.drawable.voice_bg_default_selector);
        this.voiceDefault.setOnClickListener(this);
        this.vVoiceItems.add(this.voiceDefault);
        GameStrengthenVoiceItemView gameStrengthenVoiceItemView2 = (GameStrengthenVoiceItemView) findViewById(R.id.nubia_game_strength_voice_shoot);
        this.voiceShoot = gameStrengthenVoiceItemView2;
        gameStrengthenVoiceItemView2.setBackgroundResource(R.drawable.voice_bg_shoot_selector);
        this.voiceShoot.setOnClickListener(this);
        this.vVoiceItems.add(this.voiceShoot);
        GameStrengthenVoiceItemView gameStrengthenVoiceItemView3 = (GameStrengthenVoiceItemView) findViewById(R.id.nubia_game_strength_voice_music);
        this.voiceMusic = gameStrengthenVoiceItemView3;
        gameStrengthenVoiceItemView3.setBackgroundResource(R.drawable.voice_bg_music_selector);
        this.voiceMusic.setOnClickListener(this);
        this.vVoiceItems.add(this.voiceMusic);
        GameStrengthenVoiceItemView gameStrengthenVoiceItemView4 = (GameStrengthenVoiceItemView) findViewById(R.id.nubia_game_strength_voice_movie);
        this.voiceMovie = gameStrengthenVoiceItemView4;
        gameStrengthenVoiceItemView4.setBackgroundResource(R.drawable.voice_bg_movie_selector);
        this.voiceMovie.setOnClickListener(this);
        this.vVoiceItems.add(this.voiceMovie);
        initVoiceDtsView();
        showFlicker(this.mVoiceStrengthParentLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initVoiceDtsView() {
        this.vPrompt = (TextView) findViewById(R.id.nubia_game_voice_strengthen_close_prompt);
        this.itemHeadset = (TextView) findViewById(R.id.voice_items_headset);
        this.itemContent = (LinearLayout) findViewById(R.id.voice_items_content);
        this.mVoiceStrengthParentLayout = findViewById(R.id.nubia_game_strength_voice_items_vp);
        boolean checkIfDeepbufferVoice = checkIfDeepbufferVoice();
        boolean checkHeadsetIsConnected = checkHeadsetIsConnected(getContext());
        LogUtil.i(TAG, "isDeepbuffer: " + checkIfDeepbufferVoice);
        LogUtil.i(TAG, "isConnectedHeadset: " + checkHeadsetIsConnected);
        if (this.mIsInGameLauncher) {
            if (checkHeadsetIsConnected) {
                this.itemHeadset.setText(R.string.voice_items_title_has_headset);
            } else {
                this.itemHeadset.setText(R.string.voice_items_title_no_headset);
            }
            this.vPrompt.setText(R.string.voice_strengthen_in_gameLauncher);
            this.vPrompt.setVisibility(0);
            setEnable(false);
            return;
        }
        if (!checkHeadsetIsConnected) {
            this.itemHeadset.setText(R.string.voice_items_title_no_headset);
            if (checkIfDeepbufferVoice) {
                this.vPrompt.setVisibility(8);
            }
            setEnable(false);
            this.vPrompt.setText(R.string.nubia_game_voice_strengthen_dts_closed);
            return;
        }
        this.itemHeadset.setText(R.string.voice_items_title_has_headset);
        if (checkIfDeepbufferVoice) {
            setEnable(true);
            this.vPrompt.setVisibility(8);
        } else {
            setEnable(false);
            this.vPrompt.setText(R.string.nubia_game_voice_strengthen_dts_closed);
            this.vPrompt.setVisibility(0);
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.setPriority(1000);
        getContext().registerReceiver(this.mHeadsetReceiver, intentFilter, 2);
    }

    private void reportVoiceStrengthUsed(int i) {
        String str = SuperResolutionHelper.DEFAULT_SUPPORT;
        if (i != 0) {
            if (i == 1) {
                str = "shooting";
            } else if (i == 2) {
                str = "music";
            } else if (i == 3) {
                str = "movies";
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("level", str);
        bundle.putCharSequence("app_name ", Utils.getCurrentAppName());
        LogUtil.d(TAG, "reportVoiceStrengthUsed: level = " + str + "  ;; event = game_enhance_sound_switch_used");
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "game_enhance_sound_switch_used", bundle);
    }

    private void setEnable(boolean z) {
        if (z) {
            this.itemContent.setAlpha(1.0f);
            this.voiceDefault.setBackgroundResource(R.drawable.voice_bg_default_selector);
            this.voiceDefault.setOnClickListener(this);
            this.voiceShoot.setBackgroundResource(R.drawable.voice_bg_shoot_selector);
            this.voiceShoot.setOnClickListener(this);
            this.voiceMusic.setBackgroundResource(R.drawable.voice_bg_music_selector);
            this.voiceMusic.setOnClickListener(this);
            this.voiceMovie.setBackgroundResource(R.drawable.voice_bg_movie_selector);
            this.voiceMovie.setOnClickListener(this);
            return;
        }
        this.itemContent.setAlpha(0.7f);
        this.voiceDefault.setBackgroundResource(R.drawable.voice_bg_default);
        this.voiceDefault.setOnClickListener(null);
        this.voiceShoot.setBackgroundResource(R.drawable.voice_bg_shoot);
        this.voiceShoot.setOnClickListener(null);
        this.voiceMusic.setBackgroundResource(R.drawable.voice_bg_music);
        this.voiceMusic.setOnClickListener(null);
        this.voiceMovie.setBackgroundResource(R.drawable.voice_bg_movie);
        this.voiceMovie.setOnClickListener(null);
    }

    private void updateGameStrengthVoice(int i) {
        if (this.mSelectedIndex == i) {
            return;
        }
        this.mSelectedIndex = i;
        int size = this.vVoiceItems.size();
        int i2 = 0;
        while (i2 < size) {
            this.vVoiceItems.get(i2).setChecked(i == i2);
            IGameStrengthSelectedListener iGameStrengthSelectedListener = this.mGameStrengthSelectedListener;
            if (iGameStrengthSelectedListener != null && i == i2) {
                iGameStrengthSelectedListener.onGameStrengthSelected(2, i, null);
                reportVoiceStrengthUsed(i);
            }
            i2++;
        }
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenVoiceView.2
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    GameStrengthenVoiceView.this.mTopLayout.setAlpha(0.0f);
                    GameStrengthenVoiceView.this.itemContent.setAlpha(0.0f);
                    GameStrengthenVoiceView.this.vPrompt.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(GameStrengthenVoiceView.this.mTopLayout);
                    AnimationUtil.setGcsRedItemAlpha(GameStrengthenVoiceView.this.mTopLayout);
                    AnimationUtil.setDoublePxTranslationY(GameStrengthenVoiceView.this.itemContent);
                    AnimationUtil.setGcsRedItemAlpha(GameStrengthenVoiceView.this.itemContent);
                    AnimationUtil.setDoublePxTranslationY(GameStrengthenVoiceView.this.vPrompt);
                    AnimationUtil.setGcsRedItemAlpha(GameStrengthenVoiceView.this.vPrompt);
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerReceiver();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.nubia_game_strength_voice_default) {
            updateGameStrengthVoice(0);
            return;
        }
        if (id == R.id.nubia_game_strength_voice_shoot) {
            updateGameStrengthVoice(1);
        } else if (id == R.id.nubia_game_strength_voice_music) {
            updateGameStrengthVoice(2);
        } else if (id == R.id.nubia_game_strength_voice_movie) {
            updateGameStrengthVoice(3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        try {
            getContext().unregisterReceiver(this.mHeadsetReceiver);
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException unused) {
        }
    }

    public void setCurrentPkgName(String str) {
        this.mCurrentPkgName = str;
    }

    public void setGameStrengthSelectedListener(IGameStrengthSelectedListener iGameStrengthSelectedListener) {
        this.mGameStrengthSelectedListener = iGameStrengthSelectedListener;
    }

    public void setGameStrengthenVoiceMode(int i) {
        updateGameStrengthVoice(i);
    }

    public void setIsInGameLauncher(boolean z) {
        this.mIsInGameLauncher = z;
        LogUtil.i(TAG, "*--------GameStrengthenVoiceView-----------" + this.mIsInGameLauncher);
        initView();
        initListener();
    }

    public void showFlicker(View view) {
        String highLightViewId = Utils.getHighLightViewId();
        if (TextUtils.isEmpty(highLightViewId) || view == null) {
            return;
        }
        AIFlickerTips.setFlickerName(view, highLightViewId);
        AIFlickerTips.setFlickerPadding(view, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(highLightViewId);
    }
}
