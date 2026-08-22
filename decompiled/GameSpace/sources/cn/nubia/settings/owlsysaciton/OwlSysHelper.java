package cn.nubia.settings.owlsysaciton;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import cn.nubia.gamecenter.settings.net.NetFragment;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.settings.trackclient.NubiaTrackManager;

/* loaded from: classes.dex */
public class OwlSysHelper {
    public static final String EVENT_NAME_GAME_CENTER_ATHLETIC_SWITCH_OFF_STATUS = "game_center_athletic_switch_off_status";
    public static final String KEY_SWITCH_STATUS = "switch_status";
    public static final String OPTION = "option";
    public static final String PKG_NAME_GAME_SPACE = "cn.nubia.gamelauncher";
    private static final String REPORT_ACTION_TYPE = "action_type";
    private static final String REPORT_ACTION_VALUE = "action_value";
    private static final String REPORT_EVENT_NAME = "event_name";
    private static final String REPORT_INTERVAL = "report_interval";
    private static final String REPORT_PKG_NAME = "package_name";
    public static final String SWITCH_OFF = "off";
    public static final String SWITCH_ON = "on";
    private static ContentResolver mContentResolver;
    private static Context mCtx;
    private static OwlSysHelper mOwlSysHelper;

    public static OwlSysHelper getInstance(Context context) {
        mCtx = context.getApplicationContext();
        if (mOwlSysHelper == null) {
            mOwlSysHelper = new OwlSysHelper();
            mContentResolver = mCtx.getContentResolver();
        }
        return mOwlSysHelper;
    }

    public static void insertOwlDayCv(String str, String str2, String str3) {
        insertOwlDayCv("cn.nubia.gamelauncher", str, str2, str3);
    }

    public static void insertOwlDayCv(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        bundle.putString("event_name", str2);
        bundle.putString("action_type", str3);
        bundle.putString("action_value", str4);
        bundle.putInt("report_interval", 1);
        NubiaTrackManager.getInstance().sendEvent(str, bundle);
    }

    private void insertOwlShieldSecondaryCard() {
        insertOwlDayCv("gamespace_shield_secondary_card_statue", "switch_status", Settings.Global.getInt(mCtx.getContentResolver(), NetFragment.DB_NAME_GAME_ASSISTANT_SIM, 0) == 1 ? "on" : "off");
    }

    private void insertOwl_GameSpaceRedmagicTime_init() {
        insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_status", "switch_status video_quality full_video death_video live_death_video", (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_wzry", 0) == 1 ? "on" : "off") + (Settings.Global.getInt(mCtx.getContentResolver(), "db_game_video_quality_wzry", 0) == 1 ? " HD" : " SD") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_full_video_switch_wzry", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_death_video_switch_wzry", 1) == 1 ? " on" : " off") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_real_time_death_switch", 1) == 1 ? " on" : " off"));
        insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_hpjy", 0) == 1 ? "on" : "off") + (Settings.Global.getInt(mCtx.getContentResolver(), "db_game_video_quality_hpjy", 0) == 1 ? " HD" : " SD") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_full_video_switch_hpjy", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_death_video_switch_hpjy", 1) == 1 ? " on" : " off"));
        insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_pubg", 0) == 1 ? "on" : "off") + (Settings.Global.getInt(mCtx.getContentResolver(), "db_game_video_quality_pubg", 0) == 1 ? " HD" : " SD") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_full_video_switch_pubg", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_death_video_switch_pubg", 1) == 1 ? " on" : " off"));
        insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_blzy", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(mCtx.getContentResolver(), "db_game_video_quality_blzy", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_full_video_switch_blzy", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(mCtx.getContentResolver(), "persist_sys_nubia_death_video_switch_blzy", 1) != 1 ? " off" : " on"));
    }

    private void insertOwl_persCenter_init() {
        insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_acceleration_option", "switch_status option", "on xunyou");
        insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_network_protection_status", "switch_status", "on");
        insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_net_network_changing_protection_status", "switch_status", "on");
        insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_basic_brightness_protect_status", "switch_status", "on");
        insertOwlDayCv("cn.nubia.gamelauncher", "game_center_athletic_switch_off_status", "option", "small_window");
        insertOwlDayCv("cn.nubia.gamelauncher", "game_mis_operate_switch", "switch_status option", "on ".concat((Build.DEVICE.contains("NX629") || Build.DEVICE.contains("NX651") || Utils.isZte(mCtx)) ? "game_cc" : Build.DEVICE.contains("NX666") ? "both" : "edge"));
        insertOwlDayCv("cn.nubia.gamelauncher", "roast_chicken_mode", "switch_status option", "off super_high");
    }

    public void initOwlCv() {
        insertOwl_persCenter_init();
        insertOwlShieldSecondaryCard();
        insertOwl_GameSpaceRedmagicTime_init();
        insertOwlNotCrossStatusBar();
        insertOwlGameStartAnimation();
        insertOwlMirrorHostMode();
        insertOwlGameLTMColorEnhanced();
    }

    public void insertOwlGameLTMColorEnhanced() {
        insertOwlDayCv("ltm_game_switch", "switch", Settings.Global.getInt(mCtx.getContentResolver(), "game_screen_color_enhanced", 0) != 0 ? "on" : "off");
    }

    public void insertOwlGameStartAnimation() {
        insertOwlDayCv("game_assistant_animation_switch", "switch_status", Settings.Global.getInt(mCtx.getContentResolver(), "db_game_start_animation", 0) == 1 ? "on" : "off");
    }

    public void insertOwlMirrorHostMode() {
        insertOwlDayCv("host_mode_switch", "switch", Settings.Global.getInt(mCtx.getContentResolver(), "db_mirror_host_mode", 0) == 1 ? "on" : "off");
    }

    public void insertOwlNotCrossStatusBar() {
        insertOwlDayCv("prohibit_status_bar_switch", "switch_status", Settings.Global.getInt(mCtx.getContentResolver(), "nubia_game_ban_naviges", 0) == 1 ? "on" : "off");
    }
}
