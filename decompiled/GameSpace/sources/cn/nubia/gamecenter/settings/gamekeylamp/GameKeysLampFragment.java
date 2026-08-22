package cn.nubia.gamecenter.settings.gamekeylamp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class GameKeysLampFragment extends AnimationPreferenceFragment implements FragmentInterface, Preference.OnPreferenceChangeListener {
    private static final String COLORFULLIGHT_MANAGER = "com.zte.hardware.ColorfulLightManager";
    private static final String FIELD_COLORFULLIGHT_SCENE_GAME = "COLORFULLIGHT_SCENE_GAME";
    private static final String KEY_GAMEKEY_LAMP_SWITCH = "gamekey_lamp_switch";
    private static final String KEY_SELECT_LAMP_COLOR = "select_gamekey_lamp_color";
    private static final String KEY_SELECT_LAMP_EFFECT = "select_gamekey_lamp_effect";
    private static final String METHOD_PREVIEW_COLORFUL_LIGHT = "previewColorfulLight";
    private static final String NUBIA_COLORFULLIGHT_MANAGER = "nubia.hardware.ColorfulLightManager";
    public static final String TAG = "GameKeysLampFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGamekeyLampSwitch;
    private Ringtone mRingtone;
    private SelectColorPreference mSelectLampColor;
    private SelectEffectPreference mSelectLampEffect;
    private String m_tag;
    private Handler mWorkHandler = null;
    private HandlerThread mWorkHandlerThread = null;
    private boolean mHidden = false;
    protected ContentResolver mContentResolver = null;
    private final ContentObserver mMainLampChangeObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamecenter.settings.gamekeylamp.GameKeysLampFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.d(GameKeysLampFragment.TAG, "switch_main_lamp_enable onChange");
            GameKeysLampFragment.this.initGameKeyLampView();
        }
    };

    private void colorChange() {
        Log.i(KeyLampHelper.TAG, "GKLF -- colorChange() Color : " + KeyLampHelper.getInstance().getSelectedColor());
        lampRingtone(KeyLampHelper.getInstance().isCurrentEffectMusicWithLight());
        this.mSelectLampEffect.doUpdate();
    }

    private void effectChange() {
        Log.i(KeyLampHelper.TAG, "GKLF -- effectChange() Effect : " + KeyLampHelper.getInstance().getSelectedEffect());
        lampRingtone(KeyLampHelper.getInstance().isCurrentEffectMusicWithLight());
        this.mSelectLampColor.doUpdate();
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(GameKeysLampFragment.class, R.drawable.competitive_lamp, R.string.gamekey_lamp_switch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initGameKeyLampView() {
        boolean z = SettingUtil.getMainLamp(this.mContext) && SettingUtil.getGameKeysLight(this.mContext);
        this.mGamekeyLampSwitch.setChecked(z);
        if (z) {
            getPreferenceScreen().addPreference(this.mSelectLampEffect);
            getPreferenceScreen().addPreference(this.mSelectLampColor);
        } else {
            getPreferenceScreen().removePreference(this.mSelectLampEffect);
            getPreferenceScreen().removePreference(this.mSelectLampColor);
        }
        KeyLampHelper.getInstance().previewColorfulLight(z, Settings.Global.getString(getContext().getContentResolver(), "lighting_config_game"));
        lampRingtone(z && KeyLampHelper.getInstance().isCurrentEffectMusicWithLight());
    }

    private void lampPreview(boolean z) {
        try {
            String str = FeatureUtil.contains679Or709() ? NUBIA_COLORFULLIGHT_MANAGER : COLORFULLIGHT_MANAGER;
            Class<?> cls = Class.forName(str);
            Method declaredMethod = cls.getDeclaredMethod(METHOD_PREVIEW_COLORFUL_LIGHT, Integer.TYPE, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            Field declaredField = cls.getDeclaredField(FIELD_COLORFULLIGHT_SCENE_GAME);
            declaredField.setAccessible(true);
            declaredMethod.invoke(null, Integer.valueOf(declaredField.getInt(cls)), Boolean.valueOf(z));
            LogUtil.i(TAG, str + " lampPreview " + z);
        } catch (Exception e) {
            LogUtil.wtf(TAG, e);
        }
    }

    private void lampRingtone(final boolean z) {
        this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.GameKeysLampFragment.2
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    if (GameKeysLampFragment.this.mRingtone == null || !GameKeysLampFragment.this.mRingtone.isPlaying()) {
                        return;
                    }
                    GameKeysLampFragment.this.mRingtone.stop();
                    return;
                }
                if (GameKeysLampFragment.this.mRingtone != null) {
                    try {
                        if (GameKeysLampFragment.this.mRingtone.isPlaying()) {
                            GameKeysLampFragment.this.mRingtone.stop();
                        }
                        GameKeysLampFragment.this.mRingtone.play();
                    } catch (Exception e) {
                        LogUtil.w(GameKeysLampFragment.TAG, e);
                    }
                }
            }
        });
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.m_tag;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mDashboard = (RecyclerView) getView().findViewById(R.id.recycler_view);
        this.mDashboard.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        this.mDashboard.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mContentResolver = activity.getContentResolver();
        KeyLampHelper.getInstance().readSettings();
        addPreferencesFromResource(R.xml.gcs_gamekey_lamp_settings);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mWorkHandlerThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mWorkHandlerThread.getLooper());
        this.mRingtone = RingtoneManager.getRingtone(this.mContext, Settings.System.DEFAULT_RINGTONE_URI);
        this.mGamekeyLampSwitch = (GameCenterSwitchPreference) findPreference(KEY_GAMEKEY_LAMP_SWITCH);
        this.mSelectLampColor = (SelectColorPreference) findPreference(KEY_SELECT_LAMP_COLOR);
        this.mSelectLampEffect = (SelectEffectPreference) findPreference(KEY_SELECT_LAMP_EFFECT);
        this.mGamekeyLampSwitch.setOnPreferenceChangeListener(this);
        this.mSelectLampColor.setOnPreferenceChangeListener(this);
        this.mSelectLampEffect.setOnPreferenceChangeListener(this);
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor("switch_main_lamp_enable"), true, this.mMainLampChangeObserver);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        HandlerThread handlerThread = this.mWorkHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mRingtone = null;
        this.mContentResolver.unregisterContentObserver(this.mMainLampChangeObserver);
        LogUtil.d(TAG, "onDestroy: is running");
        super.onDestroy();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LogUtil.d(TAG, "onHiddenChanged " + z);
        this.mHidden = z;
        if (z) {
            lampRingtone(false);
            KeyLampHelper.getInstance().previewColorfulLight(false, Settings.Global.getString(getContext().getContentResolver(), "lighting_config_game"));
            return;
        }
        this.mSelectLampEffect.doUpdate();
        this.mSelectLampColor.doUpdate();
        KeyLampHelper.getInstance().readSettings();
        this.mSelectLampEffect.doUpdate();
        this.mSelectLampColor.doUpdate();
        initGameKeyLampView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause: is running");
        lampRingtone(false);
        KeyLampHelper.getInstance().previewColorfulLight(false, Settings.Global.getString(getContext().getContentResolver(), "lighting_config_game"));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
    
        return false;
     */
    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onPreferenceChange(androidx.preference.Preference r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.lang.String r4 = r4.getKey()
            r4.hashCode()
            int r0 = r4.hashCode()
            r1 = 0
            r2 = -1
            switch(r0) {
                case 603114673: goto L28;
                case 874927977: goto L1d;
                case 1565443587: goto L11;
                default: goto L10;
            }
        L10:
            goto L33
        L11:
            java.lang.String r0 = "select_gamekey_lamp_effect"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L1b
            goto L33
        L1b:
            r2 = 2
            goto L33
        L1d:
            java.lang.String r0 = "gamekey_lamp_switch"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L26
            goto L33
        L26:
            r2 = 1
            goto L33
        L28:
            java.lang.String r0 = "select_gamekey_lamp_color"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L32
            goto L33
        L32:
            r2 = r1
        L33:
            switch(r2) {
                case 0: goto L45;
                case 1: goto L3b;
                case 2: goto L37;
                default: goto L36;
            }
        L36:
            goto L48
        L37:
            r3.effectChange()
            goto L48
        L3b:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r4 = r5.booleanValue()
            r3.onSwitchChange(r4)
            goto L48
        L45:
            r3.colorChange()
        L48:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.gamekeylamp.GameKeysLampFragment.onPreferenceChange(androidx.preference.Preference, java.lang.Object):boolean");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume: is running, mHidden:" + this.mHidden);
        if (this.mHidden) {
            return;
        }
        initGameKeyLampView();
    }

    protected void onSwitchChange(boolean z) {
        LogUtil.i(TAG, "onSwitchChange: " + z);
        SettingUtil.setGameKeysLight(this.mContext, z);
        if (z) {
            SettingUtil.setMainLamp(this.mContext, true);
        }
        initGameKeyLampView();
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
