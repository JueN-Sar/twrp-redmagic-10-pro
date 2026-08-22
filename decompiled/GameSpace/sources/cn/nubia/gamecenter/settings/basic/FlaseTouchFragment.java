package cn.nubia.gamecenter.settings.basic;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.CheckBoxPreference;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.settings.owlsysaciton.OwlSysHelper;
import java.util.List;

/* loaded from: classes.dex */
public class FlaseTouchFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener, CheckBoxPreference.OnCheckedChangeListener {
    private static final String DB_BAN_TOAST = "cc_game_mis_operate_ban_toast";
    private static final String DB_GAME_MODE_FANG_WU_CHU_SWITCH = "cc_game_mis_operate";
    private static final String DB_GAME_MODE_FANG_WU_CHU_TYPE = "cc_game_mis_operate_type";
    private static final String DB_GAME_MODE_NUBIA_GAME_BAN_NAVIGES = "nubia_game_ban_naviges";
    private static final String DB_GAME_MODE_NUBIA_GAME_BAN_STATUSBAR = "nubia_game_ban_statusbar";
    private static final int FANG_WU_CHU_CHOOSE_BAR = 1;
    private static final int FANG_WU_CHU_CHOOSE_EDGE = 2;
    private static final int FANG_WU_CHU_CHOOSE_GAMEING = 3;
    public static final String KEY_OFF_INTELL_SCREEN = "key_off_intell_screen";
    public static final String KEY_OFF_SCREEN_ASSISTANT = "key_off_screen_assistant";
    public static final String KEY_OFF_THREE_FINGER_SHOT = "key_off_three_finger_shot";
    private static final String TAG = "FlaseTouchFragment";
    private CheckBoxPreference mBanToastPreference;
    private CheckBoxPreference mCheckBoxFangWuChuBar;
    private CheckBoxPreference mCheckBoxFangWuChuEdge;
    private CheckBoxPreference mCheckBoxFangWuChuGameing;
    private CheckBoxPreference mCheckBoxFangWuChuNaviges;
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGameFangWuChuSwitch;
    private GameCenterSwitchPreference mGameModeOffInterScreen;
    private GameCenterSwitchPreference mGameModeOffScreenAssistant;
    private GameCenterSwitchPreference mGameModeOffThreeFingerShot;
    private String m_tag;
    private boolean supportBanToast = false;
    private static final String KEY_GAME_MODE_FANG_WU_CHU = "game_mode_fang_wu_chu";
    private static final String KEY_GAME_MODE_FANG_WU_CHU_TYPE_1 = "game_mode_fang_wu_chu_type_1";
    private static final String KEY_BAN_TOAST = "mis_operate_ban_toast";
    private static final String KEY_GAME_MODE_FANG_WU_CHU_TYPE_2 = "game_mode_fang_wu_chu_type_2";
    private static final String KEY_GAME_MODE_FANG_WU_CHU_TYPE_3 = "game_mode_fang_wu_chu_type_3";
    private static final String KEY_GAME_MODE_FANG_WU_CHU_TYPE_4 = "game_mode_fang_wu_chu_type_4";
    private static final String[] PREFERENCE_ITEMS = {KEY_GAME_MODE_FANG_WU_CHU, KEY_GAME_MODE_FANG_WU_CHU_TYPE_1, KEY_BAN_TOAST, KEY_GAME_MODE_FANG_WU_CHU_TYPE_2, KEY_GAME_MODE_FANG_WU_CHU_TYPE_3, KEY_GAME_MODE_FANG_WU_CHU_TYPE_4, "key_off_intell_screen", "key_off_screen_assistant", "key_off_three_finger_shot"};

    private void enableFangWuChuOptions(boolean z) {
        this.mGameFangWuChuSwitch.setChecked(z);
        if (z) {
            this.mCheckBoxFangWuChuBar.setEnabled(true);
            this.mBanToastPreference.setEnabled(true);
            this.mCheckBoxFangWuChuEdge.setEnabled(true);
            this.mCheckBoxFangWuChuGameing.setEnabled(true);
            this.mCheckBoxFangWuChuNaviges.setEnabled(true);
            return;
        }
        this.mCheckBoxFangWuChuBar.setEnabled(false);
        this.mBanToastPreference.setEnabled(false);
        this.mCheckBoxFangWuChuEdge.setEnabled(false);
        this.mCheckBoxFangWuChuGameing.setEnabled(false);
        this.mCheckBoxFangWuChuNaviges.setEnabled(false);
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(FlaseTouchFragment.class, R.drawable.false_touch_settings, R.string.gcs_gamecenter_menu_false_touch_zte);
    }

    private int getFangWuChuType() {
        int i = 1;
        if (!Build.DEVICE.contains("NX629") && !Build.DEVICE.contains("NX651") && !Utils.isZte(this.mContext) && Build.DEVICE.contains("NX666")) {
            i = 3;
        }
        return SettingUtil.getInt(this.mContext, DB_GAME_MODE_FANG_WU_CHU_TYPE, i);
    }

    private void hideNavigationBar() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void iniAllPerferences() {
        boolean z = true;
        if (this.mGameFangWuChuSwitch != null) {
            enableFangWuChuOptions(SettingUtil.getBoolean(this.mContext, DB_GAME_MODE_FANG_WU_CHU_SWITCH, true));
        }
        if (getFangWuChuType() != 1 && getFangWuChuType() != 3) {
            z = false;
        }
        this.mCheckBoxFangWuChuBar.setChecked(z);
        if (this.supportBanToast) {
            this.mBanToastPreference.setVisible(z);
        }
        this.mBanToastPreference.setChecked(SettingUtil.getBoolean(this.mContext, DB_BAN_TOAST, false));
        this.mCheckBoxFangWuChuGameing.setChecked(SettingUtil.getBoolean(this.mContext, DB_GAME_MODE_NUBIA_GAME_BAN_STATUSBAR, false));
        this.mCheckBoxFangWuChuNaviges.setChecked(SettingUtil.getBoolean(this.mContext, DB_GAME_MODE_NUBIA_GAME_BAN_NAVIGES, false));
        this.mGameModeOffInterScreen.setChecked(SettingUtil.getOffIntellScreen(this.mContext));
        this.mGameModeOffScreenAssistant.setChecked(SettingUtil.getOffScreenAssistant(this.mContext));
        this.mGameModeOffThreeFingerShot.setChecked(SettingUtil.getOffThreeFingerShot(this.mContext));
    }

    private void init() {
        List<String> gameCenterFalseTouch = FeatureUtil.getGameCenterFalseTouch();
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterFalseTouch.contains(str)) {
                removePreference(str);
            }
        }
        this.supportBanToast = gameCenterFalseTouch.contains(KEY_BAN_TOAST);
    }

    private void setFangWuChuType(int i, boolean z) {
        int fangWuChuType = getFangWuChuType();
        if (i == 1) {
            setFangWuChuTypeValue(z, fangWuChuType == 2 || fangWuChuType == 3);
        } else if (i == 2) {
            setFangWuChuTypeValue(fangWuChuType == 1 || fangWuChuType == 3, z);
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
        if (checkBoxPreference == this.mCheckBoxFangWuChuBar) {
            setFangWuChuType(1, booleanValue);
            this.mCheckBoxFangWuChuBar.setChecked(booleanValue);
            if (this.supportBanToast) {
                this.mBanToastPreference.setVisible(booleanValue);
            }
        } else if (checkBoxPreference == this.mBanToastPreference) {
            SettingUtil.putBoolean(this.mContext, DB_BAN_TOAST, booleanValue);
            this.mBanToastPreference.setChecked(booleanValue);
        } else if (checkBoxPreference == this.mCheckBoxFangWuChuEdge) {
            setFangWuChuType(2, booleanValue);
            this.mCheckBoxFangWuChuEdge.setChecked(booleanValue);
        } else if (checkBoxPreference == this.mCheckBoxFangWuChuGameing) {
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
        this.mContext = getActivity();
        hideNavigationBar();
        addPreferencesFromResource(R.xml.gcs_basic_false_touch);
        this.mGameFangWuChuSwitch = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU);
        this.mCheckBoxFangWuChuBar = (CheckBoxPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU_TYPE_1);
        this.mBanToastPreference = (CheckBoxPreference) findPreference(KEY_BAN_TOAST);
        this.mCheckBoxFangWuChuEdge = (CheckBoxPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU_TYPE_2);
        this.mCheckBoxFangWuChuGameing = (CheckBoxPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU_TYPE_3);
        this.mCheckBoxFangWuChuNaviges = (CheckBoxPreference) findPreference(KEY_GAME_MODE_FANG_WU_CHU_TYPE_4);
        this.mGameModeOffInterScreen = (GameCenterSwitchPreference) findPreference("key_off_intell_screen");
        this.mGameModeOffScreenAssistant = (GameCenterSwitchPreference) findPreference("key_off_screen_assistant");
        this.mGameModeOffThreeFingerShot = (GameCenterSwitchPreference) findPreference("key_off_three_finger_shot");
        this.mGameFangWuChuSwitch.setOnPreferenceChangeListener(this);
        this.mCheckBoxFangWuChuBar.setOnCheckedChangeWidgetListener(this);
        this.mBanToastPreference.setOnCheckedChangeWidgetListener(this);
        this.mCheckBoxFangWuChuEdge.setOnCheckedChangeWidgetListener(this);
        this.mCheckBoxFangWuChuGameing.setOnCheckedChangeWidgetListener(this);
        this.mCheckBoxFangWuChuNaviges.setOnCheckedChangeWidgetListener(this);
        this.mGameModeOffInterScreen.setOnPreferenceChangeListener(this);
        this.mGameModeOffScreenAssistant.setOnPreferenceChangeListener(this);
        this.mGameModeOffThreeFingerShot.setOnPreferenceChangeListener(this);
        init();
        if (CommonUtil.isNubia()) {
            this.mGameModeOffInterScreen.setTitle(R.string.gcs_game_off_intell_screen_title_redmagic);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
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
            SettingUtil.putBoolean(this.mContext, DB_GAME_MODE_FANG_WU_CHU_SWITCH, booleanValue);
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
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        iniAllPerferences();
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
