package cn.nubia.common.util;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class FeatureUtil {
    private static final String EXPAND_PROJECTION_SCREEN_3D = "ZTE_FEATURE_EXPAND_PROJECTION_SCREEN_3D";
    private static final String FALSE_TOUCH_DEFAULT = "game_mode_fang_wu_chu,game_mode_fang_wu_chu_type_1,mis_operate_ban_toast,game_mode_fang_wu_chu_type_3,game_mode_fang_wu_chu_type_4,key_off_intell_screen,key_off_screen_assistant,key_off_three_finger_shot";
    private static final String FALSE_TOUCH_DEFAULT_ABROAD = "game_mode_fang_wu_chu,game_mode_fang_wu_chu_type_1,mis_operate_ban_toast,game_mode_fang_wu_chu_type_3,game_mode_fang_wu_chu_type_4,key_off_screen_assistant,key_off_three_finger_shot";
    private static final String FEATURE_CLASS_NAME = "com.zte.feature.Feature";
    private static final String GAME_CENTER_ABOUT = "ZTE_FEATURE_GAME_CENTER_ABOUT";
    private static final String GAME_CENTER_ABOUT_DEFAULT = "gcs_system_update,gcs_icp,gcs_about_update,gcs_privacy_policy,redmagic";
    private static final String GAME_CENTER_ABOUT_DEFAULT_ABROAD = "gcs_about_update,redmagic";
    private static final String GAME_CENTER_ABOUT_ZTE_DEFAULT = "gcs_icp,gcs_about_update,gcs_privacy_policy,flagship";
    private static final String GAME_CENTER_ABOUT_ZTE_DEFAULT_ABROAD = "gcs_about_update,flagship";
    private static final String GAME_CENTER_FALSE_TOUCH = "ZTE_FEATURE_GAME_CENTER_FLASE_TOUCH";
    public static final String GAME_CENTER_KEYS_LAMP = "ro.vendor.feature.zte_feature_game_center_keys_lamp";
    public static final String GAME_CENTER_KEYS_LAMP_DEFAULT = "128:80_0,96_1,112_2,128_3,144_4,160_5:0_FFFF0000,2_FFFFFF00,4_FF0000FF,3_FF00FF00,5_FF3C01FE/FFFF01F3,6_FFEEFF33/FF411CFF,7_FF8036FF/FF0DFF9F";
    private static final String GAME_CENTER_MENU = "ZTE_FEATURE_GAME_CENTER_MENU";
    private static final String GAME_CENTER_MODE = "ZTE_FEATURE_GAME_CENTER_MODE_SETTINGS";
    private static final String GAME_CENTER_MODE_DEFAULT;
    private static final String GAME_CENTER_MODE_DEFAULT_NUBIA_ABROAD = "key_mirror_host_mode";
    private static final String GAME_CENTER_MODE_DEFAULT_NUBIA_CHANNA = "key_mirror_host_mode,key_hand_held_mode";
    private static final String GAME_CENTER_NET = "ZTE_FEATURE_GAME_CENTER_NET";
    private static final String GAME_CENTER_NET_DEFAULT = "gamemode_network,gamemode_change_network,gamemode_assistant_sim,gamemode_networkacceleration";
    private static final String GAME_CENTER_NET_DEFAULT_ABROAD = "gamemode_change_network,gamemode_assistant_sim";
    private static final String GAME_CENTER_NOT_DISTURB = "ZTE_FEATURE_GAME_CENTER_NOT_DISTURB";
    private static final String GAME_CENTER_OTHER_OPTIONS = "ZTE_FEATURE_GAME_CENTER_OTHER_OPTIONS";
    private static final String GAME_CENTER_RACE_KEY_OFF = "ZTE_FEATURE_GAME_CENTER_RACE_KEY_OFF";
    private static final String GAME_CENTER_SCREEN_SETTINGS = "ZTE_FEATURE_GAME_CENTER_SCREEN_SETTINGS";
    private static final String GAME_CENTER_SCREEN_SETTINGS_DEFAULT = "gamemode_fix_light,gamemode_save_energy";
    private static final String GAME_CENTER_SCREEN_SETTINGS_DEFAULT2 = "gamemode_fix_light,gamemode_color_enhanced,gamemode_save_energy";
    private static final String GAME_CENTER_STREAMING = "ZTE_FEATURE_GAME_CENTER_STREAMING";
    private static final String GAME_CENTER_STREAMING_DEFAULT = "device,mute";
    private static final String GAME_CENTER_ZTE_FLASE_TOUCH = "ZTE_FEATURE_GAME_CENTER_ZTE_FLASE_TOUCH";
    private static final String GAME_CENTER_ZTE_FLASE_TOUCH_DEFAULT = "game_mode_fang_wu_chu_type_3,game_mode_fang_wu_chu_type_4,key_gcs_barrage_message,key_off_intell_screen,key_off_screen_assistant,key_off_three_finger_shot";
    private static final String KEY_GAME_HAND_HELD_MODE = "key_hand_held_mode";
    public static final String MAGIC_GAME_SCREEN_SAVER = "ZTE_FEATURE_MAGIC_GAME_SCREEN_SAVER";
    private static final String MENU_NUBIA_ABROAD = "SummaryFragment,divider,NetFragment,ScreenSettingsFragment,NotDisturbFragment,divider,FlaseTouchFragment,MirrorHostFragment,RaceKeyOffFragment,GameKeysLampFragment,WallpaperListFragment,WatermarkFragment,OtherOptionsFragment,AboutFragment";
    private static final String MENU_NUBIA_CHANNA = "ArkBaseFragment,DataManagerFragment,SummaryFragment,divider,NetFragment,ScreenSettingsFragment,NotDisturbFragment,divider,FlaseTouchFragment,MirrorHostFragment,RaceKeyOffFragment,GameKeysLampFragment,WallpaperListFragment,WatermarkFragment,OtherOptionsFragment,AboutFragment";
    private static final String MENU_ZTE_ABROAD = "SummaryFragment,divider,NetFragment,ScreenSettingsFragment,NotDisturbFragment,divider,FlaseTouchFragment,RaceKeyOffFragment,WallpaperListFragment,OtherOptionsFragment,AboutFragment";
    private static final String MENU_ZTE_CHANNA = "ArkBaseFragment,DataManagerFragment,SummaryFragment,divider,NetFragment,ScreenSettingsFragment,NotDisturbFragment,divider,FlaseTouchFragment,RaceKeyOffFragment,WallpaperListFragment,WatermarkFragment,OtherOptionsFragment,AboutFragment";
    private static final String METHOD_GET_BOOLEAN = "getBoolean";
    private static final String METHOD_GET_INT = "getInt";
    private static final String METHOD_GET_STRING = "get";
    private static final String NOT_DISTURB_DEFAULT = "key_call_widonw,key_gcs_barrage_message";
    private static final String NOT_DISTURB_DEFAULT_ABROAD = "key_gcs_barrage_message";
    private static final String OPTIONS_DEFAULT = "hide_games_icon,game_space_start_anim_volume,key_game_start_animation,lite_mode,game_time_remind,fiber_catcher";
    private static final String TAG = "FeatureUtil";
    public static final String WINDOWREPLY_ENTRANCE_DISPLAY = "ZTE_FEATURE_WINDOWREPLY_ENTRANCE_DISPLAY";
    public static final String X_GRAVITY_GAMEPAD = "ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD";
    private static final String ZTE_FEATURE_AI_GAME_PREDICTION = "ZTE_FEATURE_AI_GAME_PREDICTION";
    public static final String ZTE_FEATURE_GAME_FAN = "ZTE_FEATURE_GAME_FAN";
    private static final String ZTE_FEATURE_GAME_RANDOM_RECORD = "ZTE_FEATURE_GAME_RANDOM_RECORD";
    private static final String ZTE_FEATURE_GAME_STRATEGY_STATION = "ZTE_FEATURE_GAME_STRATEGY_STATION";
    private static final String ZTE_FEATURE_GAME_VOICE_ASSIST = "ZTE_FEATURE_GAME_VOICE_ASSIST";
    private static final String ZTE_FEATURE_GAME_VOICE_ASSIST_V2 = "ZTE_FEATURE_GAME_VOICE_ASSIST_V2";
    public static final String ZTE_FEATURE_GAME_X_GRAVITY_CONFIG = "ZTE_FEATURE_GAME_X_GRAVITY_CONFIG";
    public static final String ZTE_FEATURE_KEY_MOUSE_MAP = "ZTE_FEATURE_KEY_MOUSE_MAP";
    private static final String ZTE_FEATURE_LEARNED_BEHAVIOR_X_GRAVITY = "ZTE_FEATURE_LEARNED_BEHAVIOR_X_GRAVITY";
    private static final String ZTE_FEATURE_LOBBY_SCORE_RECORD = "ZTE_FEATURE_LOBBY_SCORE_RECORD";
    private static final String ZTE_FEATURE_LOW_SUGAR = "ZTE_FEATURE_LOW_SUGAR";
    private static final String ZTE_FEATURE_MULTI_SUB_SCREEN = "ZTE_FEATURE_MULTI_SUB_SCREEN";
    private static final String ZTE_FEATURE_OFF_INTELL_SCREEN_DEFAULT = "ZTE_FEATURE_OFF_INTELL_SCREEN_DEFAULT";
    private static final String ZTE_FEATURE_OFF_SCREEN_ASSISTANT_DEFAULT = "ZTE_FEATURE_OFF_SCREEN_ASSISTANT_DEFAULT";
    private static final String ZTE_FEATURE_OFF_THREE_FINGER_SHOT_DEFAULT = "ZTE_FEATURE_OFF_THREE_FINGER_SHOT_DEFAULT";
    private static final String ZTE_FEATURE_PLANET_AGENT = "ZTE_FEATURE_PLANET_AGENT";
    private static final String ZTE_FEATURE_PLANET_MORA = "ZTE_FEATURE_PLANET_MORA";
    private static final String ZTE_FEATURE_PLANET_RESOURCE_LIB = "ZTE_FEATURE_PLANET_RESOURCE_LIB";
    private static final String ZTE_FEATURE_PLANET_VIDEO_BANNER = "ZTE_FEATURE_PLANET_VIDEO_BANNER";
    public static final String ZTE_FEATURE_REDMAGIC_AIKEY = "ZTE_FEATURE_REDMAGIC_AIKEY";
    private static final String ZTE_FEATURE_REDMAGIC_GAMEKEY = "ZTE_FEATURE_REDMAGIC_GAMEKEY";
    public static final String ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = "ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY";
    private static final String ZTE_FEATURE_SAR_CONTROL_4 = "ZTE_FEATURE_SAR_CONTROL_4";
    private static final String ZTE_FEATURE_SHOULDER_KEY_LAUNCH_GAMESPACE = "ZTE_FEATURE_SHOULDER_KEY_LAUNCH_GAMESPACE";
    private static final String ZTE_FEATURE_SIDE_SHORTCUT_KEY = "ZTE_FEATURE_SIDE_SHORTCUT_KEY";
    public static final String ZTE_FEATURE_STREAM_GAME = "ZTE_FEATURE_STREAM_GAME";
    public static final String ZTE_FEATURE_SUPPORT_WIDOWREPLY = "MFV_FEATURE_WINDOWREPLY";
    private static int mFeatureScoreRecord;

    static {
        GAME_CENTER_MODE_DEFAULT = CommonUtil.isNubia() ? CommonUtil.isNubiaChina() ? GAME_CENTER_MODE_DEFAULT_NUBIA_CHANNA : GAME_CENTER_MODE_DEFAULT_NUBIA_ABROAD : "";
        mFeatureScoreRecord = -1;
    }

    public static boolean behaviorLearnedEnable() {
        return getBoolean(ZTE_FEATURE_LEARNED_BEHAVIOR_X_GRAVITY, false).booleanValue();
    }

    public static boolean contains679Or709() {
        return Build.MODEL.contains("NX709") || Build.MODEL.contains("NX679");
    }

    public static boolean containsNX679JOrNX709J() {
        return Build.MODEL.contains("NX709J") || Build.MODEL.contains("NX679J");
    }

    public static boolean gameAgentEnable() {
        return getBoolean(ZTE_FEATURE_PLANET_AGENT, false).booleanValue();
    }

    public static String get(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName(FEATURE_CLASS_NAME).getDeclaredMethod(METHOD_GET_STRING, String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e = e;
        }
        try {
            if (!TextUtils.isEmpty(str3)) {
                str2 = str3;
            }
            Log.d(TAG, str + ":" + str2);
        } catch (Exception e2) {
            e = e2;
            str2 = str3;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public static Boolean getBoolean(String str, boolean z) {
        try {
            z = ((Boolean) Class.forName(FEATURE_CLASS_NAME).getMethod(METHOD_GET_BOOLEAN, String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
            Log.d(TAG, str + ":" + z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.valueOf(z);
    }

    public static List<String> getGameCenterAbout() {
        return Arrays.asList(get(GAME_CENTER_ABOUT, CommonUtil.isNubia() ? CommonUtil.isInter() ? GAME_CENTER_ABOUT_DEFAULT_ABROAD : GAME_CENTER_ABOUT_DEFAULT : CommonUtil.isInter() ? GAME_CENTER_ABOUT_ZTE_DEFAULT_ABROAD : GAME_CENTER_ABOUT_ZTE_DEFAULT).split(","));
    }

    public static List<String> getGameCenterFalseTouch() {
        return Arrays.asList(get(GAME_CENTER_FALSE_TOUCH, CommonUtil.isInter() ? FALSE_TOUCH_DEFAULT_ABROAD : FALSE_TOUCH_DEFAULT).split(","));
    }

    public static String[] getGameCenterMenu() {
        return get(GAME_CENTER_MENU, CommonUtil.isNubia() ? CommonUtil.isInter() ? MENU_NUBIA_ABROAD : MENU_NUBIA_CHANNA : CommonUtil.isInter() ? MENU_ZTE_ABROAD : MENU_ZTE_CHANNA).split(",");
    }

    public static List<String> getGameCenterMenuList() {
        return Arrays.asList(get(GAME_CENTER_MENU, CommonUtil.isNubia() ? CommonUtil.isInter() ? MENU_NUBIA_ABROAD : MENU_NUBIA_CHANNA : CommonUtil.isInter() ? MENU_ZTE_ABROAD : MENU_ZTE_CHANNA).split(","));
    }

    public static List<String> getGameCenterMode() {
        String str = GAME_CENTER_MODE_DEFAULT;
        if (CommonUtil.isNubiaChina()) {
            str = GAME_CENTER_MODE_DEFAULT_NUBIA_CHANNA;
        } else if (CommonUtil.isNubia() && CommonUtil.isInter()) {
            str = GAME_CENTER_MODE_DEFAULT_NUBIA_ABROAD;
        }
        return Arrays.asList(get(GAME_CENTER_MODE, str).split(","));
    }

    public static List<String> getGameCenterNet() {
        return Arrays.asList(get(GAME_CENTER_NET, CommonUtil.isInter() ? GAME_CENTER_NET_DEFAULT_ABROAD : GAME_CENTER_NET_DEFAULT).split(","));
    }

    public static List<String> getGameCenterNotDisturb() {
        return Arrays.asList(get(GAME_CENTER_NOT_DISTURB, CommonUtil.isInter() ? "key_gcs_barrage_message" : NOT_DISTURB_DEFAULT).split(","));
    }

    public static List<String> getGameCenterOtherOptions() {
        String str = !CommonUtil.isInter() ? "highlights,hide_games_icon,game_space_start_anim_volume,key_game_start_animation,lite_mode,game_time_remind,fiber_catcher" : OPTIONS_DEFAULT;
        if (!CommonUtil.isInter()) {
            str = "key_game_recommended_content," + str;
        }
        return Arrays.asList(get(GAME_CENTER_OTHER_OPTIONS, str).split(","));
    }

    public static List<String> getGameCenterScreenSettings() {
        return Arrays.asList(get(GAME_CENTER_SCREEN_SETTINGS, GAME_CENTER_SCREEN_SETTINGS_DEFAULT2).split(","));
    }

    public static List<String> getGameCenterSupport(String str) {
        return GAME_CENTER_MENU.equals(str) ? getGameCenterMenuList() : GAME_CENTER_OTHER_OPTIONS.equals(str) ? getGameCenterOtherOptions() : GAME_CENTER_ZTE_FLASE_TOUCH.equals(str) ? getGameCenterZteFlaseTouch() : GAME_CENTER_NET.equals(str) ? getGameCenterNet() : GAME_CENTER_NOT_DISTURB.equals(str) ? getGameCenterNotDisturb() : GAME_CENTER_FALSE_TOUCH.equals(str) ? getGameCenterFalseTouch() : GAME_CENTER_MODE.equals(str) ? getGameCenterMode() : GAME_CENTER_SCREEN_SETTINGS.equals(str) ? getGameCenterScreenSettings() : GAME_CENTER_STREAMING.equals(str) ? getStreaming() : GAME_CENTER_ABOUT.equals(str) ? getGameCenterAbout() : new ArrayList();
    }

    public static List<String> getGameCenterZteFlaseTouch() {
        return Arrays.asList(get(GAME_CENTER_ZTE_FLASE_TOUCH, GAME_CENTER_ZTE_FLASE_TOUCH_DEFAULT).split(","));
    }

    public static Integer getInt(String str, int i) {
        try {
            i = ((Integer) Class.forName(FEATURE_CLASS_NAME).getDeclaredMethod(METHOD_GET_INT, String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
            Log.d(TAG, str + ":" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.valueOf(i);
    }

    public static List<String> getStreaming() {
        String str = GAME_CENTER_STREAMING_DEFAULT;
        String str2 = get(GAME_CENTER_STREAMING, GAME_CENTER_STREAMING_DEFAULT);
        if (!TextUtils.isEmpty(str2)) {
            str = str2;
        }
        return Arrays.asList(str.split(","));
    }

    public static boolean getZtFeatureGameRandomRecord() {
        return getBoolean("ZTE_FEATURE_GAME_RANDOM_RECORD", false).booleanValue();
    }

    public static boolean handHeldEnable() {
        return getGameCenterMode().contains(KEY_GAME_HAND_HELD_MODE);
    }

    public static boolean isMtk() {
        try {
            return "mediatek".equals(get("SOC_VENDOR", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSprd() {
        return "sprd".equals(get("SOC_VENDOR", ""));
    }

    public static boolean isSupportGamePrediction() {
        return getBoolean(ZTE_FEATURE_AI_GAME_PREDICTION, false).booleanValue();
    }

    public static boolean isSupportGameQuickInfo() {
        return getBoolean(MAGIC_GAME_SCREEN_SAVER, false).booleanValue();
    }

    public static boolean isSupportScreen3D() {
        return getBoolean(EXPAND_PROJECTION_SCREEN_3D, false).booleanValue();
    }

    public static boolean isSupportShoulderKeyLaunchGamespace() {
        return getBoolean(ZTE_FEATURE_SHOULDER_KEY_LAUNCH_GAMESPACE, false).booleanValue();
    }

    public static boolean isSupportXGravityGamepad() {
        return getBoolean(X_GRAVITY_GAMEPAD, false).booleanValue();
    }

    public static boolean isXGravitationEnable() {
        String str = get(ZTE_FEATURE_GAME_X_GRAVITY_CONFIG, "***");
        try {
            if (!str.matches("([a-zA-Z\\d]+:[a-zA-Z\\d+]+,?)*([a-zA-Z\\d]+:[a-zA-Z\\d+]+)$")) {
                Log.w(TAG, "x gravity feature format error, use default config 0.");
                str = "main:0";
            }
            for (String str2 : str.split(",")) {
                String[] split = str2.split(":");
                if (TextUtils.equals("main", split[0])) {
                    return TextUtils.equals(split[1], "1");
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean lowsugarEnable() {
        return getBoolean(ZTE_FEATURE_LOW_SUGAR, false).booleanValue();
    }

    public static boolean moraEnable() {
        if (Build.MODEL.contains("NX709") || Build.MODEL.contains("NX679")) {
            return true;
        }
        return getBoolean(ZTE_FEATURE_PLANET_MORA, false).booleanValue();
    }

    public static boolean multiScreenEnable() {
        return getBoolean(ZTE_FEATURE_MULTI_SUB_SCREEN, false).booleanValue();
    }

    public static boolean offIntellScreenDefault() {
        return getBoolean(ZTE_FEATURE_OFF_INTELL_SCREEN_DEFAULT, false).booleanValue();
    }

    public static boolean offScreenAssistantDefault() {
        return getBoolean(ZTE_FEATURE_OFF_SCREEN_ASSISTANT_DEFAULT, false).booleanValue();
    }

    public static boolean offThreeFingerShotDefault() {
        return getBoolean(ZTE_FEATURE_OFF_THREE_FINGER_SHOT_DEFAULT, false).booleanValue();
    }

    public static boolean planetVideoBannerEnable() {
        return getBoolean(ZTE_FEATURE_PLANET_VIDEO_BANNER, false).booleanValue();
    }

    public static boolean resourceLibEnable() {
        if (Build.MODEL.contains("NX709") || Build.MODEL.contains("NX679")) {
            return true;
        }
        return getBoolean(ZTE_FEATURE_PLANET_RESOURCE_LIB, false).booleanValue();
    }

    public static boolean sarControlDefault() {
        return getBoolean(ZTE_FEATURE_SAR_CONTROL_4, false).booleanValue();
    }

    public static boolean scoreRecordEnable() {
        if (mFeatureScoreRecord == -1) {
            mFeatureScoreRecord = getBoolean(ZTE_FEATURE_LOBBY_SCORE_RECORD, false).booleanValue() ? 1 : 0;
        }
        if (Build.MODEL.contains("NX709") || Build.MODEL.contains("NX679")) {
            mFeatureScoreRecord = 1;
        }
        return mFeatureScoreRecord == 1;
    }

    public static boolean supportFan() {
        return getBoolean(ZTE_FEATURE_GAME_FAN, false).booleanValue();
    }

    public static boolean supportGameKey() {
        return getBoolean(ZTE_FEATURE_REDMAGIC_GAMEKEY, false).booleanValue();
    }

    public static boolean supportGameStrategyStation() {
        return getBoolean(ZTE_FEATURE_GAME_STRATEGY_STATION, false).booleanValue();
    }

    public static boolean supportHostMode() {
        if (Arrays.asList(getGameCenterMenu()).contains("MirrorHostFragment")) {
            return get(GAME_CENTER_MODE, GAME_CENTER_MODE_DEFAULT).contains(GAME_CENTER_MODE_DEFAULT_NUBIA_ABROAD);
        }
        return false;
    }

    public static boolean supportMirrorHost() {
        if (isSupportScreen3D()) {
            return true;
        }
        if (!CommonUtil.isNubia()) {
            return false;
        }
        String str = get(GAME_CENTER_MODE, GAME_CENTER_MODE_DEFAULT);
        return str.contains(GAME_CENTER_MODE_DEFAULT_NUBIA_ABROAD) || str.contains(KEY_GAME_HAND_HELD_MODE);
    }

    public static boolean supportRaceKeyOff() {
        return supportGameKey();
    }

    public static boolean supportSideKey() {
        return getBoolean(ZTE_FEATURE_SIDE_SHORTCUT_KEY, false).booleanValue();
    }

    public static boolean supportStream() {
        Log.i(TAG, "supportStream() value:" + get(ZTE_FEATURE_STREAM_GAME, "false"));
        return !"false".equals(r0);
    }

    public static boolean supportStreaming() {
        return getBoolean(ZTE_FEATURE_STREAM_GAME, false).booleanValue();
    }

    public static boolean supportWatermark() {
        return CommonUtil.isNubia();
    }

    public static boolean voiceInteractionEnable() {
        return getBoolean(ZTE_FEATURE_GAME_VOICE_ASSIST, false).booleanValue();
    }

    public static boolean voiceInteractionV2Enable() {
        return getBoolean(ZTE_FEATURE_GAME_VOICE_ASSIST_V2, false).booleanValue();
    }

    public static boolean windowReplyEnable() {
        if (getBoolean(ZTE_FEATURE_SUPPORT_WIDOWREPLY, false).booleanValue()) {
            return getBoolean(WINDOWREPLY_ENTRANCE_DISPLAY, true).booleanValue();
        }
        return false;
    }
}
