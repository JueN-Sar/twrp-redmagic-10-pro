package cn.nubia.gamecenter.settings.basic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageFragment;
import cn.nubia.gamecenter.settings.compatible.CheckBoxPreference;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.recordSettings.GameManualActivity;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.settings.owlsysaciton.OwlSysHelper;
import java.util.List;

/* loaded from: classes.dex */
public class ZteFlaseTouchFragment extends PreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener, CheckBoxPreference.OnCheckedChangeListener {
    private static final String DB_GAME_MODE_FANG_WU_CHU_SWITCH = "cc_game_mis_operate";
    private static final String DB_GAME_MODE_FANG_WU_CHU_TYPE = "cc_game_mis_operate_type";
    private static final String DB_GAME_MODE_NUBIA_GAME_BAN_NAVIGES = "nubia_game_ban_naviges";
    private static final String DB_GAME_MODE_NUBIA_GAME_BAN_STATUSBAR = "nubia_game_ban_statusbar";
    private static final int FANG_WU_CHU_CHOOSE_BAR = 1;
    private static final int FANG_WU_CHU_CHOOSE_EDGE = 2;
    private static final int FANG_WU_CHU_CHOOSE_GAMEING = 3;
    private static final String KEY_BAEEAGE_MESSAGE = "key_gcs_barrage_message";
    private static final String KEY_OFF_INTELL_SCREEN = "key_off_intell_screen";
    private static final String KEY_OFF_SCREEN_ASSISTANT = "key_off_screen_assistant";
    private static final String KEY_OFF_THREE_FINGER_SHOT = "key_off_three_finger_shot";
    private static final String TAG = "FlaseTouchFragment";
    private ContentObserver mBarrageMessageObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamecenter.settings.basic.ZteFlaseTouchFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.i(ZteFlaseTouchFragment.TAG, "onChange:" + z);
            ZteFlaseTouchFragment.this.mGameKeysBarrageMessage.setSummary(BarrageMessageFragment.getSummaryFromBarrageMessage(ZteFlaseTouchFragment.this.getActivity()));
        }
    };
    private CheckBoxPreference mCheckBoxFangWuChuGameing;
    private CheckBoxPreference mCheckBoxFangWuChuNaviges;
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGameFangWuChuSwitch;
    private GameCenterPreference mGameKeysBarrageMessage;
    private GameCenterSwitchPreference mGameModeOffInterScreen;
    private GameCenterSwitchPreference mGameModeOffScreenAssistant;
    private GameCenterSwitchPreference mGameModeOffThreeFingerShot;
    private String m_tag;
    private static final String KEY_GAME_MODE_FANG_WU_CHU = "game_mode_fang_wu_chu";
    private static final String KEY_GAME_MODE_FANG_WU_CHU_TYPE_3 = "game_mode_fang_wu_chu_type_3";
    private static final String KEY_GAME_MODE_FANG_WU_CHU_TYPE_4 = "game_mode_fang_wu_chu_type_4";
    private static final String[] PREFERENCE_ITEMS = {KEY_GAME_MODE_FANG_WU_CHU, KEY_GAME_MODE_FANG_WU_CHU_TYPE_3, KEY_GAME_MODE_FANG_WU_CHU_TYPE_4, "key_gcs_barrage_message", "key_off_intell_screen", "key_off_screen_assistant", "key_off_three_finger_shot"};

    private void enableFangWuChuOptions(boolean z) {
        this.mGameFangWuChuSwitch.setChecked(z);
        if (z) {
            this.mCheckBoxFangWuChuGameing.setEnabled(true);
            this.mCheckBoxFangWuChuNaviges.setEnabled(true);
        } else {
            this.mCheckBoxFangWuChuGameing.setEnabled(false);
            this.mCheckBoxFangWuChuNaviges.setEnabled(false);
        }
        SettingUtil.putBoolean(this.mContext, DB_GAME_MODE_FANG_WU_CHU_SWITCH, z);
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(ZteFlaseTouchFragment.class, R.drawable.false_touch_settings, R.string.gcs_gamecenter_menu_false_touch_zte);
    }

    private int getFangWuChuType() {
        int i = 1;
        if (!Build.DEVICE.contains("NX629") && !Build.DEVICE.contains("NX651") && !Utils.isZte(this.mContext) && Build.DEVICE.contains("NX666")) {
            i = 3;
        }
        return SettingUtil.getInt(this.mContext, DB_GAME_MODE_FANG_WU_CHU_TYPE, i);
    }

    private String getSummaryFromBarrageMessage() {
        FragmentActivity activity = getActivity();
        int i = SettingUtil.getInt(this.mContext, BarrageMessageFragment.GSC_BARRAGE_MESSAGE, 0);
        return activity.getResources().getString(i != 0 ? i != 1 ? R.string.gcs_game_video_close : R.string.gcs_game_video_open : R.string.gcs_game_video_close);
    }

    private void iniAllPerferences() {
        if (this.mGameFangWuChuSwitch != null) {
            enableFangWuChuOptions(SettingUtil.getBoolean(this.mContext, DB_GAME_MODE_FANG_WU_CHU_SWITCH, false));
        }
        this.mCheckBoxFangWuChuGameing.setChecked(SettingUtil.getBoolean(this.mContext, DB_GAME_MODE_NUBIA_GAME_BAN_STATUSBAR, false));
        this.mCheckBoxFangWuChuNaviges.setChecked(SettingUtil.getBoolean(this.mContext, DB_GAME_MODE_NUBIA_GAME_BAN_NAVIGES, false));
        this.mGameModeOffInterScreen.setChecked(SettingUtil.getOffIntellScreen(this.mContext));
        this.mGameModeOffScreenAssistant.setChecked(SettingUtil.getOffScreenAssistant(this.mContext));
        this.mGameModeOffThreeFingerShot.setChecked(SettingUtil.getOffThreeFingerShot(this.mContext));
    }

    private void init() {
        List<String> gameCenterZteFlaseTouch = FeatureUtil.getGameCenterZteFlaseTouch();
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterZteFlaseTouch.contains(str)) {
                removePreference(str);
            }
        }
        if (gameCenterZteFlaseTouch.contains(KEY_GAME_MODE_FANG_WU_CHU_TYPE_3) || gameCenterZteFlaseTouch.contains(KEY_GAME_MODE_FANG_WU_CHU_TYPE_4)) {
            return;
        }
        removePreference(KEY_GAME_MODE_FANG_WU_CHU);
    }

    private void setFangWuChuType(int i, boolean z) {
        if (i == 1) {
            setFangWuChuTypeValue(z, getFangWuChuType() == 2 || getFangWuChuType() == 3);
        } else if (i == 2) {
            setFangWuChuTypeValue(getFangWuChuType() == 1 || getFangWuChuType() == 3, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setFangWuChuTypeValue(boolean z, boolean z2) {
        int i = z;
        if (z2) {
            i = z + 2;
        }
        SettingUtil.putInt(this.mContext, DB_GAME_MODE_FANG_WU_CHU_TYPE, i);
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.m_tag;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public int getMetricsCategory() {
        return 5;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mDashboard = (RecyclerView) getView().findViewById(R.id.recycler_view);
        this.mDashboard.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        this.mDashboard.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
    }

    @Override // cn.nubia.gamecenter.settings.compatible.CheckBoxPreference.OnCheckedChangeListener
    public boolean onCheckedChanged(CheckBoxPreference checkBoxPreference, Object obj) {
        Boolean bool = (Boolean) obj;
        boolean booleanValue = bool.booleanValue();
        if (checkBoxPreference == this.mCheckBoxFangWuChuGameing) {
            SettingUtil.putBoolean(this.mContext, DB_GAME_MODE_NUBIA_GAME_BAN_STATUSBAR, booleanValue);
            this.mCheckBoxFangWuChuGameing.setChecked(booleanValue);
        } else if (checkBoxPreference == this.mCheckBoxFangWuChuNaviges) {
            SettingUtil.putBoolean(this.mContext, DB_GAME_MODE_NUBIA_GAME_BAN_NAVIGES, booleanValue);
            this.mCheckBoxFangWuChuNaviges.setChecked(booleanValue);
            OwlSysHelper.getInstance(getContext()).insertOwlNotCrossStatusBar();
        }
        return bool.booleanValue();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ContentResolver contentResolver = getContentResolver();
        this.mContext = getActivity();
        addPreferencesFromResource(R.xml.gcs_basic_false_touch_zte);
        this.mGameFangWuChuSwitch = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU);
        this.mCheckBoxFangWuChuGameing = (CheckBoxPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU_TYPE_3);
        this.mCheckBoxFangWuChuNaviges = (CheckBoxPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU_TYPE_4);
        this.mGameModeOffInterScreen = (GameCenterSwitchPreference) findPreference("key_off_intell_screen");
        this.mGameModeOffScreenAssistant = (GameCenterSwitchPreference) findPreference("key_off_screen_assistant");
        this.mGameModeOffThreeFingerShot = (GameCenterSwitchPreference) findPreference("key_off_three_finger_shot");
        this.mGameKeysBarrageMessage = (GameCenterPreference) findPreference("key_gcs_barrage_message");
        this.mGameFangWuChuSwitch.setOnPreferenceChangeListener(this);
        this.mCheckBoxFangWuChuGameing.setOnCheckedChangeWidgetListener(this);
        this.mCheckBoxFangWuChuNaviges.setOnCheckedChangeWidgetListener(this);
        this.mGameModeOffInterScreen.setOnPreferenceChangeListener(this);
        this.mGameModeOffScreenAssistant.setOnPreferenceChangeListener(this);
        this.mGameModeOffThreeFingerShot.setOnPreferenceChangeListener(this);
        if (this.mGameKeysBarrageMessage != null) {
            contentResolver.registerContentObserver(Settings.Global.getUriFor(BarrageMessageFragment.GSC_BARRAGE_MESSAGE), true, this.mBarrageMessageObserver);
        }
        init();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(this.mBarrageMessageObserver);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference == this.mGameFangWuChuSwitch) {
            enableFangWuChuOptions(booleanValue);
            return false;
        }
        if (switchPreference == this.mGameModeOffInterScreen) {
            SettingUtil.setOffIntellScreen(this.mContext, booleanValue);
            this.mGameModeOffInterScreen.setChecked(booleanValue);
            return false;
        }
        if (switchPreference == this.mGameModeOffScreenAssistant) {
            SettingUtil.setOffScreenAssistant(this.mContext, booleanValue);
            this.mGameModeOffScreenAssistant.setChecked(booleanValue);
            return false;
        }
        if (switchPreference != this.mGameModeOffThreeFingerShot) {
            return false;
        }
        SettingUtil.setOffThreeFingerShot(this.mContext, booleanValue);
        this.mGameModeOffThreeFingerShot.setChecked(booleanValue);
        return false;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != this.mGameKeysBarrageMessage) {
            return super.onPreferenceTreeClick(preference);
        }
        Context context = this.mContext;
        GameManualActivity.startActivity(context, context.getString(R.string.gamemode_barrage_message_title), "");
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        iniAllPerferences();
        GameCenterPreference gameCenterPreference = this.mGameKeysBarrageMessage;
        if (gameCenterPreference != null) {
            gameCenterPreference.setSummary(getSummaryFromBarrageMessage());
        }
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
