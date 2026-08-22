package cn.nubia.gamecenter.settings.basic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageFragment;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.recordSettings.GameManualActivity;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;
import java.util.List;

/* loaded from: classes.dex */
public class NotDisturbFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String DB_GAME_CALL_FLOW_WINDOW = "phone_call_floating_window";
    private static final String DB_NEW_MESSAGE_REMIND = "db_competitive_key_reminder_off_on";
    public static final String KEY_OFF_INTELL_SCREEN = "key_off_intell_screen";
    public static final String KEY_OFF_SCREEN_ASSISTANT = "key_off_screen_assistant";
    public static final String KEY_OFF_THREE_FINGER_SHOT = "key_off_three_finger_shot";
    private static final String TAG = "NotDisturbFragment";
    private ContentObserver mBarrageMessageObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamecenter.settings.basic.NotDisturbFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.i(NotDisturbFragment.TAG, "onChange " + z);
            NotDisturbFragment.this.mGameKeysBarrageMessage.setSummary(BarrageMessageFragment.getSummaryFromBarrageMessage(NotDisturbFragment.this.getActivity()));
        }
    };
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterPreference mGameKeysBarrageMessage;
    private GameCenterSwitchPreference mGameModeCallFlowWindow;
    private GameCenterSwitchPreference mGameModeNewMessageRemind;
    private GameCenterSwitchPreference mGameModeOffInterScreen;
    private GameCenterSwitchPreference mGameModeOffScreenAssistant;
    private GameCenterSwitchPreference mGameModeOffThreeFingerShot;
    private String m_tag;
    public static final String KEY_CALL_WINDOW = "key_call_widonw";
    public static final String KEY_NEW_MESSAGE_REMIND = "key_new_message_remind";
    public static final String KEY_BAEEAGE_MESSAGE = "key_gcs_barrage_message";
    public static final String[] PREFERENCE_ITEMS = {KEY_CALL_WINDOW, KEY_NEW_MESSAGE_REMIND, KEY_BAEEAGE_MESSAGE, "key_off_intell_screen", "key_off_screen_assistant", "key_off_three_finger_shot"};

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(NotDisturbFragment.class, R.drawable.not_disturb_settings, R.string.gcs_gamecenter_menu_not_disturb);
    }

    private String getSummaryFromBarrageMessage() {
        FragmentActivity activity = getActivity();
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), BarrageMessageFragment.GSC_BARRAGE_MESSAGE, 0);
        return activity.getResources().getString(i != 0 ? i != 1 ? R.string.gcs_game_video_close : R.string.gcs_game_video_open : R.string.gcs_game_video_close);
    }

    private void iniAllPerferences() {
        this.mGameModeCallFlowWindow.setChecked(SettingUtil.getCallFlowWindow(this.mContext));
        this.mGameModeOffInterScreen.setChecked(SettingUtil.getOffIntellScreen(this.mContext));
        this.mGameModeOffScreenAssistant.setChecked(SettingUtil.getOffScreenAssistant(this.mContext));
        this.mGameModeOffThreeFingerShot.setChecked(SettingUtil.getOffThreeFingerShot(this.mContext));
        this.mGameModeNewMessageRemind.setChecked(SettingUtil.getNewMesageRemind(this.mContext));
    }

    private void init() {
        List<String> gameCenterNotDisturb = FeatureUtil.getGameCenterNotDisturb();
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterNotDisturb.contains(str)) {
                removePreference(str);
            }
        }
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

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        ContentResolver contentResolver = getContentResolver();
        addPreferencesFromResource(R.xml.gcs_not_disturb_settings);
        this.mGameModeCallFlowWindow = (GameCenterSwitchPreference) findPreference(KEY_CALL_WINDOW);
        this.mGameModeOffInterScreen = (GameCenterSwitchPreference) findPreference("key_off_intell_screen");
        this.mGameModeOffScreenAssistant = (GameCenterSwitchPreference) findPreference("key_off_screen_assistant");
        this.mGameModeOffThreeFingerShot = (GameCenterSwitchPreference) findPreference("key_off_three_finger_shot");
        this.mGameModeNewMessageRemind = (GameCenterSwitchPreference) findPreference(KEY_NEW_MESSAGE_REMIND);
        this.mGameKeysBarrageMessage = (GameCenterPreference) findPreference(KEY_BAEEAGE_MESSAGE);
        this.mGameModeCallFlowWindow.setOnPreferenceChangeListener(this);
        this.mGameModeOffInterScreen.setOnPreferenceChangeListener(this);
        this.mGameModeOffScreenAssistant.setOnPreferenceChangeListener(this);
        this.mGameModeOffThreeFingerShot.setOnPreferenceChangeListener(this);
        this.mGameModeNewMessageRemind.setOnPreferenceChangeListener(this);
        init();
        if (this.mGameKeysBarrageMessage != null) {
            contentResolver.registerContentObserver(Settings.Global.getUriFor(BarrageMessageFragment.GSC_BARRAGE_MESSAGE), true, this.mBarrageMessageObserver);
        }
        if (CommonUtil.isNubia()) {
            this.mGameModeOffInterScreen.setTitle(R.string.gcs_game_off_intell_screen_title_redmagic);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        getContentResolver().unregisterContentObserver(this.mBarrageMessageObserver);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference == this.mGameModeCallFlowWindow) {
            SettingUtil.setCallFlowWindow(this.mContext, booleanValue);
            this.mGameModeCallFlowWindow.setChecked(booleanValue);
            Track.switchStatus(Track.PERS_CENTER_BASIC_CALLING_FLOAT_SWITCH, booleanValue);
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
        if (switchPreference == this.mGameModeOffThreeFingerShot) {
            SettingUtil.setOffThreeFingerShot(this.mContext, booleanValue);
            this.mGameModeOffThreeFingerShot.setChecked(booleanValue);
            return false;
        }
        if (switchPreference != this.mGameModeNewMessageRemind) {
            return false;
        }
        SettingUtil.setNewMesageRemind(this.mContext, booleanValue);
        this.mGameModeNewMessageRemind.setChecked(booleanValue);
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
