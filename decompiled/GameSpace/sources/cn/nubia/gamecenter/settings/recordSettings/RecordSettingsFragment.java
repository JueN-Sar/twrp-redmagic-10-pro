package cn.nubia.gamecenter.settings.recordSettings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialogCenter;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameSpaceListPreference;
import cn.nubia.gamecenter.settings.recordSettings.GuidePageAdapter;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.plug.Constant;
import cn.nubia.settings.owlsysaciton.OwlSysHelper;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecordSettingsFragment extends PreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener, GuidePageAdapter.Callback {
    public static final String APPADD_NAME = "gamename";
    private static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    private static final String ATTR_APP_NAME = "component";
    private static final String DB_GAMES_DEATH_VIDEO = "persist_sys_nubia_death_video_switch";
    private static final String DB_GAMES_FULL_VIDEO = "persist_sys_nubia_full_video_switch";
    private static final String DB_GAMES_REAL_TIME_DEATH_VIDEO = "persist_sys_nubia_real_time_death_switch";
    private static final String DB_GAMES_SCENELAMP_MAIN = "scenelamp_main_switch";
    private static final String DB_GAMES_SCENE_INTERACTION = "persist_sys_nubia_scene_switch";
    private static final String DB_GAMES_SWITCH = "persist_sys_nubia_redmagic_time_switch";
    private static final String DB_GAMES_WATER_MARK = "persist_sys_nubia_logo_switch";
    private static final String DB_GUIDE = "settings_gcs_game_guide";
    private static boolean DEBUG = true;
    private static final String GAMES_BLZY = "com.epicgames.fortnite";
    private static final String GAMES_CJZC = "com.tencent.tmgp.pubgmhd";
    private static final String GAMES_PUBG = "com.tencent.ig";
    private static final String GAMES_WZRY = "com.tencent.tmgp.sgame";
    private static final String KEY_GAME_KEYS_BLZY = "gcs_record_blzy";
    private static final String KEY_GAME_KEYS_CJZC = "gcs_record_cjzc";
    private static final String KEY_GAME_KEYS_DEATH_VIDEO = "gcs_game_video_death";
    private static final String KEY_GAME_KEYS_FULL_VIDEO = "gcs_record_full_video";
    private static final String KEY_GAME_KEYS_PUBGMOBILE = "gcs_record_pubg";
    private static final String KEY_GAME_KEYS_REAL_TIME_DEATH_VIDEO = "gcs_game_real_time_video_death";
    private static final String KEY_GAME_KEYS_SCENE_INTERACTION = "gcs_game_scene_interaction_switch";
    private static final String KEY_GAME_KEYS_SWITCH = "gcs_record_game_switch";
    private static final String KEY_GAME_KEYS_VIDEO_QUALITY = "gamemode_video_quality";
    private static final String KEY_GAME_KEYS_WATER_MARK = "gcs_record_watermark";
    private static final String KEY_GAME_KEYS_WZRY = "gcs_record_wzry";
    private static final String TAG = "RecordSettingsFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGameKeysDeathVideo;
    private GameCenterSwitchPreference mGameKeysFullVideo;
    private GameCenterSwitchPreference mGameKeysRealTimeDeathVideo;
    private GameCenterSwitchPreference mGameKeysSceneInteraction;
    private GameCenterSwitchPreference mGameKeysSwitch;
    private GameSpaceListPreference mGameKeysVideoQuality;
    private List<String> mGamePackagesName = new ArrayList();
    private RecyclerView mGuideList;
    private String m_startType;
    private String m_tag;

    private void confirmGuideShowed(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), DB_GUIDE, !z ? 0 : 1);
    }

    private void doConfirmGuide() {
        hideGuide();
        confirmGuideShowed(true);
        showOpenRedMagicTimeAlertDialog();
    }

    private static int getSubDB(Context context, String str, String str2, int i) {
        return Settings.Global.getInt(context.getContentResolver(), str + str2, i);
    }

    private int getSubDB(String str, int i) {
        return getSubDB(this.mContext, str, this.m_startType, i);
    }

    private boolean hasGuideShowed() {
        return !isTestMode() && Settings.Global.getInt(this.mContext.getContentResolver(), DB_GUIDE, 0) == 1;
    }

    private void hideGuide() {
        RecyclerView recyclerView = this.mGuideList;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
    }

    private void hideNavigationBar() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void iniAllPerferences() {
        boolean isGameSwitchOn = isGameSwitchOn();
        GameCenterSwitchPreference gameCenterSwitchPreference = this.mGameKeysSwitch;
        if (gameCenterSwitchPreference != null) {
            gameCenterSwitchPreference.setChecked(isGameSwitchOn);
        }
        GameSpaceListPreference gameSpaceListPreference = this.mGameKeysVideoQuality;
        if (gameSpaceListPreference != null) {
            gameSpaceListPreference.setStartType(this.m_startType);
        }
        int subDB = getSubDB(DB_GAMES_FULL_VIDEO, 0);
        GameCenterSwitchPreference gameCenterSwitchPreference2 = this.mGameKeysFullVideo;
        if (gameCenterSwitchPreference2 != null) {
            gameCenterSwitchPreference2.setChecked(subDB != 0);
        }
        if (Build.DEVICE.contains("NX627")) {
            this.mGameKeysSceneInteraction.setChecked(getSubDB(DB_GAMES_SCENE_INTERACTION, 0) != 0);
        }
        int subDB2 = getSubDB(DB_GAMES_DEATH_VIDEO, 1);
        GameCenterSwitchPreference gameCenterSwitchPreference3 = this.mGameKeysDeathVideo;
        if (gameCenterSwitchPreference3 != null) {
            gameCenterSwitchPreference3.setChecked(subDB2 != 0);
        }
        if (this.m_startType.contains("wzry")) {
            int subDB3 = getSubDB(DB_GAMES_REAL_TIME_DEATH_VIDEO, 1);
            GameCenterSwitchPreference gameCenterSwitchPreference4 = this.mGameKeysRealTimeDeathVideo;
            if (gameCenterSwitchPreference4 != null) {
                gameCenterSwitchPreference4.setChecked(subDB3 != 0);
            }
        } else {
            removePreference(KEY_GAME_KEYS_REAL_TIME_DEATH_VIDEO);
        }
        isShowMoreVideo(isGameSwitchOn);
    }

    private void initGameList() {
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            try {
                int columnIndex = query.getColumnIndex("component");
                new ArrayList();
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    String[] split = query.getString(columnIndex).split(",");
                    this.mGamePackagesName.add(split == null ? "" : split[0]);
                }
                query.close();
                LogUtil.d(TAG, "******mGamePackagesName =" + this.mGamePackagesName);
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed load game app data.", e);
        }
    }

    private void initGuide(Activity activity) {
        if (this.mGuideList != null) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.guide_page_list);
        this.mGuideList = recyclerView;
        if (recyclerView == null) {
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(0);
        this.mGuideList.setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(this.mGuideList);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.mipmap.gcs_game_guide_1));
        arrayList.add(Integer.valueOf(R.mipmap.gcs_game_guide_2));
        ArrayList arrayList2 = new ArrayList();
        if (Utils.isSupportVirtualGameKey()) {
            arrayList2.add(Integer.valueOf(R.string.gcs_game_guide_1));
            arrayList2.add(Integer.valueOf(R.string.gcs_game_guide_2));
        } else {
            arrayList2.add(Integer.valueOf(R.string.gcs_game_guide_1));
            arrayList2.add(Integer.valueOf(R.string.gcs_game_guide_2));
        }
        this.mGuideList.setAdapter(new GuidePageAdapter(this, arrayList, arrayList2));
    }

    private void initStartType() {
        Object obj = this.mContext;
        if (obj instanceof StartInfo) {
            this.m_startType = ((StartInfo) obj).getStartType();
        } else {
            this.m_startType = "";
        }
    }

    public static boolean isGameSwitchOn(Context context, String str) {
        return getSubDB(context, "persist_sys_nubia_redmagic_time_switch", str, 0) == 1;
    }

    private void isShowMoreVideo(boolean z) {
        GameCenterSwitchPreference gameCenterSwitchPreference;
        GameSpaceListPreference gameSpaceListPreference = this.mGameKeysVideoQuality;
        if (gameSpaceListPreference != null) {
            gameSpaceListPreference.setEnabled(z);
        }
        GameCenterSwitchPreference gameCenterSwitchPreference2 = this.mGameKeysFullVideo;
        if (gameCenterSwitchPreference2 != null) {
            gameCenterSwitchPreference2.setEnabled(z);
        }
        GameCenterSwitchPreference gameCenterSwitchPreference3 = this.mGameKeysDeathVideo;
        if (gameCenterSwitchPreference3 != null) {
            gameCenterSwitchPreference3.setEnabled(z);
        }
        if (!this.m_startType.contains("wzry") || (gameCenterSwitchPreference = this.mGameKeysRealTimeDeathVideo) == null) {
            return;
        }
        gameCenterSwitchPreference.setEnabled(z);
    }

    private boolean isTestMode() {
        Object obj = this.mContext;
        if (obj instanceof StartInfo) {
            return ((StartInfo) obj).isTestMode();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChoiceGameSettings(boolean z) {
        setSubDB(z, "persist_sys_nubia_redmagic_time_switch");
        this.mGameKeysSwitch.setChecked(z);
        isShowMoreVideo(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSubDB(boolean z, String str) {
        Settings.Global.putInt(this.mContext.getContentResolver(), str + this.m_startType, z ? 1 : 0);
        if (str == null || !str.contains("persist_sys_nubia_redmagic_time_switch")) {
            return;
        }
        String str2 = this.m_startType;
        if (str2 != null && str2.contains("wzry")) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_click", "switch_status", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_click", "switch_status", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_status", "switch_status video_quality full_video death_video live_death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_wzry", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_wzry", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_wzry", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_wzry", 1) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), DB_GAMES_REAL_TIME_DEATH_VIDEO, 1) != 1 ? " off" : " on"));
            return;
        }
        String str3 = this.m_startType;
        if (str3 != null && str3.contains("hpjy")) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_click", "switch_status", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_click", "switch_status", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_hpjy", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_hpjy", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_hpjy", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_hpjy", 1) != 1 ? " off" : " on"));
            return;
        }
        String str4 = this.m_startType;
        if (str4 != null && str4.contains(Constant.GAME_TAG_PUBG)) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_click", "switch_on", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_click", "switch_on", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_pubg", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_pubg", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_pubg", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_pubg", 1) != 1 ? " off" : " on"));
            return;
        }
        String str5 = this.m_startType;
        if (str5 == null || !str5.contains("blzy")) {
            return;
        }
        if (z) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_click", "switch_on", "on");
        } else {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_click", "switch_on", "off");
        }
        OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_blzy", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_blzy", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_blzy", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_blzy", 1) != 1 ? " off" : " on"));
    }

    private void showGuide() {
        if (hasGuideShowed()) {
            showOpenRedMagicTimeAlertDialog();
            return;
        }
        initGuide(getActivity());
        RecyclerView recyclerView = this.mGuideList;
        if (recyclerView != null) {
            recyclerView.setVisibility(0);
        } else {
            showOpenRedMagicTimeAlertDialog();
        }
    }

    private void showOpenRedMagicTimeAlertDialog() {
        new AlertDialogCenter.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert).setMessage(this.mContext.getString(R.string.gcs_game_video_remind)).setPositiveButton(R.string.gamekeys_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.recordSettings.RecordSettingsFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                RecordSettingsFragment.this.setChoiceGameSettings(true);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(R.string.gamemode_account_login_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.recordSettings.RecordSettingsFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    private void showOpenSceneInteractionAlertDialog() {
        new AlertDialogCenter.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert).setMessage(this.mContext.getString(R.string.gcs_game_scene_interaction_effect_dialog_no_gamekey)).setPositiveButton(R.string.gamekeys_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.recordSettings.RecordSettingsFragment.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                RecordSettingsFragment.this.setSubDB(true, RecordSettingsFragment.DB_GAMES_SCENE_INTERACTION);
                RecordSettingsFragment.this.setSubDB(true, RecordSettingsFragment.DB_GAMES_SCENELAMP_MAIN);
                RecordSettingsFragment.this.mGameKeysSceneInteraction.setChecked(true);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(R.string.gamemode_account_login_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.recordSettings.RecordSettingsFragment.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
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
        return 4;
    }

    public boolean isGameSwitchOn() {
        return isGameSwitchOn(this.mContext, this.m_startType);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mDashboard = (RecyclerView) getView().findViewById(R.id.recycler_view);
        this.mDashboard.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        this.mDashboard.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
    }

    @Override // cn.nubia.gamecenter.settings.recordSettings.GuidePageAdapter.Callback
    public void onConfirm() {
        doConfirmGuide();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        initStartType();
        if (!Utils.isSupportVirtualGameKey() || Build.DEVICE.contains("NX666")) {
            addPreferencesFromResource(R.xml.gcs_records_settings);
        } else {
            addPreferencesFromResource(R.xml.gcs_records_settings_no_game_key);
            GameCenterSwitchPreference gameCenterSwitchPreference = (GameCenterSwitchPreference) findPreference(KEY_GAME_KEYS_SCENE_INTERACTION);
            this.mGameKeysSceneInteraction = gameCenterSwitchPreference;
            gameCenterSwitchPreference.setOnPreferenceChangeListener(this);
            removePreference(KEY_GAME_KEYS_SCENE_INTERACTION);
        }
        this.mGameKeysVideoQuality = (GameSpaceListPreference) findPreference(KEY_GAME_KEYS_VIDEO_QUALITY);
        this.mGameKeysDeathVideo = (GameCenterSwitchPreference) findPreference(KEY_GAME_KEYS_DEATH_VIDEO);
        this.mGameKeysSwitch = (GameCenterSwitchPreference) findPreference(KEY_GAME_KEYS_SWITCH);
        this.mGameKeysFullVideo = (GameCenterSwitchPreference) findPreference(KEY_GAME_KEYS_FULL_VIDEO);
        GameCenterSwitchPreference gameCenterSwitchPreference2 = (GameCenterSwitchPreference) findPreference(KEY_GAME_KEYS_REAL_TIME_DEATH_VIDEO);
        this.mGameKeysRealTimeDeathVideo = gameCenterSwitchPreference2;
        gameCenterSwitchPreference2.setOnPreferenceChangeListener(this);
        this.mGameKeysSwitch.setOnPreferenceChangeListener(this);
        this.mGameKeysFullVideo.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference3 = this.mGameKeysDeathVideo;
        if (gameCenterSwitchPreference3 != null) {
            gameCenterSwitchPreference3.setOnPreferenceChangeListener(this);
        }
        iniAllPerferences();
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
        if (switchPreference == this.mGameKeysSwitch) {
            LogUtil.d(TAG, "... onchange mGameKeysSwitch");
            String str = this.m_startType;
            if (str == null || !str.contains("wzry")) {
                String str2 = this.m_startType;
                if (str2 == null || !str2.contains("hpjy")) {
                    String str3 = this.m_startType;
                    if (str3 == null || !str3.contains(Constant.GAME_TAG_PUBG)) {
                        String str4 = this.m_startType;
                        if (str4 != null && str4.contains("blzy")) {
                            List<String> list = this.mGamePackagesName;
                            if (list == null || !list.contains(GAMES_BLZY)) {
                                Toast.makeText(this.mContext, R.string.gcs_game_video_blzy_remind, 0).show();
                            } else if (booleanValue) {
                                showGuide();
                            } else {
                                setChoiceGameSettings(false);
                            }
                        }
                    } else {
                        List<String> list2 = this.mGamePackagesName;
                        if (list2 == null || !list2.contains("com.tencent.ig")) {
                            Toast.makeText(this.mContext, R.string.gcs_game_video_pubj_mobile_remind, 0).show();
                        } else if (booleanValue) {
                            showGuide();
                        } else {
                            setChoiceGameSettings(false);
                        }
                    }
                } else {
                    List<String> list3 = this.mGamePackagesName;
                    if (list3 == null || !list3.contains("com.tencent.tmgp.pubgmhd")) {
                        Toast.makeText(this.mContext, R.string.gcs_game_video_cjzc_install_remind, 0).show();
                    } else if (booleanValue) {
                        showGuide();
                    } else {
                        setChoiceGameSettings(false);
                    }
                }
            } else {
                List<String> list4 = this.mGamePackagesName;
                if (list4 == null || !list4.contains("com.tencent.tmgp.sgame")) {
                    Toast.makeText(this.mContext, R.string.gcs_game_video_wzry_remind, 0).show();
                } else if (booleanValue) {
                    showGuide();
                } else {
                    setChoiceGameSettings(false);
                }
            }
        } else if (switchPreference == this.mGameKeysFullVideo) {
            LogUtil.d(TAG, "... onchange mGameKeysFullVideo");
            setSubDB(booleanValue, DB_GAMES_FULL_VIDEO);
            this.mGameKeysFullVideo.setChecked(booleanValue);
        } else if (switchPreference == this.mGameKeysSceneInteraction) {
            LogUtil.d(TAG, "... onchange mGameKeysSceneInteraction");
            if (booleanValue) {
                showOpenSceneInteractionAlertDialog();
            } else {
                setSubDB(booleanValue, DB_GAMES_SCENE_INTERACTION);
                this.mGameKeysSceneInteraction.setChecked(booleanValue);
            }
        } else {
            GameCenterSwitchPreference gameCenterSwitchPreference = this.mGameKeysDeathVideo;
            if (switchPreference != gameCenterSwitchPreference) {
                GameCenterSwitchPreference gameCenterSwitchPreference2 = this.mGameKeysRealTimeDeathVideo;
                if (switchPreference == gameCenterSwitchPreference2 && gameCenterSwitchPreference2 != null) {
                    LogUtil.d(TAG, "... onchange mGameKeysRealTimeDeathVideo");
                    setSubDB(booleanValue, DB_GAMES_REAL_TIME_DEATH_VIDEO);
                    this.mGameKeysRealTimeDeathVideo.setChecked(booleanValue);
                }
            } else if (gameCenterSwitchPreference != null) {
                LogUtil.d(TAG, "... onchange mGameKeysDeathVideo");
                setSubDB(booleanValue, DB_GAMES_DEATH_VIDEO);
                this.mGameKeysDeathVideo.setChecked(booleanValue);
            }
        }
        return false;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        hideNavigationBar();
        initGameList();
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
        confirmGuideShowed(false);
        setChoiceGameSettings(false);
    }
}
