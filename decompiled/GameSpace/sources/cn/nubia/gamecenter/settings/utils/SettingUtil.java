package cn.nubia.gamecenter.settings.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.format.DateFormat;
import androidx.media3.extractor.ts.PsExtractor;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.compatible.GameModeHelper;
import cn.nubia.gamecenter.settings.net.NetFragment;

/* loaded from: classes.dex */
public class SettingUtil {
    public static final String DATE_FORMAT = "yyyyMMdd";
    private static final String DB_GAME_CALL_FLOW_WINDOW = "phone_call_floating_window";
    private static final String DB_GAME_FIBER_CATCHER = "nubia_fiber_catcher";
    private static final String DB_GAME_OFF_INTELL_SCREEN = "db_game_off_intell_screen";
    private static final String DB_GAME_OFF_SCREEN_ASSISTANT = "db_game_off_screen_assistant";
    private static final String DB_GAME_OFF_THREE_FINGER_SHOT = "db_game_off_three_finger_shot";
    private static final String DB_GAME_SAVE_ENERGY = "game_screen_save_energy";
    private static final String DB_NEW_MESSAGE_REMIND = "db_competitive_key_reminder_off_on";
    public static final int DEFAULT_GAME_KEYS = 0;
    public static final String GAME_KEYS = "nubia_db_game_keys";
    public static final int GAME_KEYS_COLOR_ENHANCED = 1;
    public static final int GAME_KEYS_LIGHT = 16;
    private static final String GAME_KEYS_MODE = "nubia_gamekeys_lamp";
    public static final int GAME_KEYS_OFF_ON = 1;
    public static final int GAME_KEYS_OFF_ON_CHAT = 8;
    public static final int GAME_KEYS_OFF_ON_DEFAULT = 0;
    public static final int GAME_KEYS_OFF_ON_NOTIFICATION = 4;
    public static final int GAME_KEYS_OFF_ON_PHONE = 2;
    public static final int GAME_KEYS_OFF_ON_PHONE_DEFAULT = 0;
    public static final int GAME_KEYS_OFF_ON_SUPER_PERFORMANCE = 32;
    private static final String GAME_SCREEN_COLOR_ENHANCED = "game_screen_color_enhanced";
    private static final String HAND_HELD_MODE = "db_hand_held_mode";
    public static final String MAIN_LAMP_ENABLE = "switch_main_lamp_enable";
    private static final String MIRROR_HOST_MODE = "db_mirror_host_mode";
    private static final String NUBIA_NETWORKACC_ONOFF = "nubia_networkacc_onoff";
    public static final String SCENE_TYPE_CONFIG_GAME = "scene_type_config_GAME";
    public static final String STREAMING_GAMES_AUTO_DISSIPATING = "gamespace_streaming_games_auto_dissipating";
    public static final String STREAMING_GAMES_PRIVACY_SETTINGS = "gamespace_streaming_games_privacy_settings";
    private static final String TAG = "SettingUtil";
    private static final String XR_MIRROR_HOST_MODE = "db_xr_mirror_host_mode";

    public static boolean getAssistantSim(Context context) {
        return getBoolean(context, NetFragment.DB_NAME_GAME_ASSISTANT_SIM, false);
    }

    public static boolean getBit(int i, int i2) {
        return (i & i2) != 0;
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getInt(context, str, z ? 1 : 0) == 1;
    }

    public static boolean getCallFlowWindow(Context context) {
        return getBoolean(context, DB_GAME_CALL_FLOW_WINDOW, true);
    }

    private static int getColorEnhanced(Context context) {
        return getInt(context, GAME_SCREEN_COLOR_ENHANCED, 0);
    }

    public static boolean getColorEnhanced(Context context, int i) {
        return getBit(getColorEnhanced(context), i);
    }

    public static String getDate() {
        return (String) DateFormat.format(DATE_FORMAT, System.currentTimeMillis());
    }

    public static boolean getFiberCatcher(Context context) {
        return getBoolean(context, DB_GAME_FIBER_CATCHER, false);
    }

    private static int getGameKeys(Context context) {
        return getInt(context, "nubia_db_game_keys", 0);
    }

    public static boolean getGameKeysLight(Context context) {
        return (getGameKeys(context) & 16) != 0;
    }

    public static int getGameKeysMode(Context context, int i) {
        return getInt(context, "nubia_gamekeys_lamp", i);
    }

    public static int getGameMode(Context context) {
        return getInt(context, GameModeHelper.STR_GAME_MODE_OFF_ON, GameModeHelper.getDefaultValue());
    }

    public static boolean getGameMode(Context context, int i) {
        return getBit(getGameMode(context), i);
    }

    public static boolean getHandHeldMode(Context context) {
        return getBoolean(context, HAND_HELD_MODE, true);
    }

    public static int getInt(Context context, String str, int i) {
        int i2 = Settings.Global.getInt(context.getContentResolver(), str, i);
        LogUtil.i(TAG, "get " + str + ":" + Integer.toBinaryString(i2));
        return i2;
    }

    public static int getLampEffectCode(Context context, int i) {
        return getGameKeysMode(context, i) & PsExtractor.VIDEO_STREAM_MASK;
    }

    public static boolean getMainLamp(Context context) {
        return getBoolean(context, "switch_main_lamp_enable", true);
    }

    public static boolean getMirrorHostMode(Context context) {
        return getBoolean(context, MIRROR_HOST_MODE, true);
    }

    public static int getNetworkacc(Context context) {
        return getInt(context, NUBIA_NETWORKACC_ONOFF, -1);
    }

    public static boolean getNewMesageRemind(Context context) {
        return getBoolean(context, DB_NEW_MESSAGE_REMIND, true);
    }

    public static boolean getOffIntellScreen(Context context) {
        return getBoolean(context, DB_GAME_OFF_INTELL_SCREEN, FeatureUtil.offIntellScreenDefault());
    }

    public static boolean getOffScreenAssistant(Context context) {
        return getBoolean(context, DB_GAME_OFF_SCREEN_ASSISTANT, FeatureUtil.offScreenAssistantDefault());
    }

    public static boolean getOffThreeFingerShot(Context context) {
        return getBoolean(context, DB_GAME_OFF_THREE_FINGER_SHOT, FeatureUtil.offThreeFingerShotDefault());
    }

    public static boolean getReminder(Context context, long j) {
        return getDate().equals(getString(context, String.valueOf(j)));
    }

    public static boolean getScreenSaveEnergy(Context context) {
        return FeatureUtil.sarControlDefault() ? getBoolean(context, DB_GAME_SAVE_ENERGY, false) : getBoolean(context, DB_GAME_SAVE_ENERGY, true);
    }

    public static String getString(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), str);
        LogUtil.i(TAG, "get " + str + ":" + string);
        return string;
    }

    public static boolean getXrMirrorHostMode(Context context) {
        return getBoolean(context, XR_MIRROR_HOST_MODE, true);
    }

    public static void putBoolean(Context context, String str, boolean z) {
        putInt(context, str, z ? 1 : 0);
    }

    public static void putInt(Context context, String str, int i) {
        LogUtil.i(TAG, "put " + str + ":" + Integer.toBinaryString(i));
        Settings.Global.putInt(context.getContentResolver(), str, i);
    }

    public static void putString(Context context, String str, String str2) {
        LogUtil.i(TAG, "put " + str + ":" + str2);
        Settings.Global.putString(context.getContentResolver(), str, str2);
    }

    public static void setAssistantSim(Context context, boolean z) {
        putBoolean(context, NetFragment.DB_NAME_GAME_ASSISTANT_SIM, z);
    }

    public static int setBit(int i, int i2, boolean z) {
        return z ? i | i2 : i & (~i2);
    }

    public static void setCallFlowWindow(Context context, boolean z) {
        putBoolean(context, DB_GAME_CALL_FLOW_WINDOW, z);
    }

    private static void setColorEnhanced(Context context, int i) {
        putInt(context, GAME_SCREEN_COLOR_ENHANCED, i);
    }

    public static void setColorEnhanced(Context context, int i, boolean z) {
        setColorEnhanced(context, setBit(getColorEnhanced(context), i, z));
    }

    public static void setFiberCatcher(Context context, boolean z) {
        putBoolean(context, DB_GAME_FIBER_CATCHER, z);
    }

    private static void setGameKeys(Context context, int i) {
        putInt(context, "nubia_db_game_keys", i);
    }

    public static void setGameKeysLight(Context context, boolean z) {
        int gameKeys = getGameKeys(context);
        if (z) {
            setGameKeys(context, gameKeys | 16);
        } else {
            setGameKeys(context, gameKeys & (-17));
        }
    }

    public static void setGameKeysMode(Context context, int i) {
        putInt(context, "nubia_gamekeys_lamp", i);
    }

    public static void setGameMode(Context context, int i) {
        putInt(context, GameModeHelper.STR_GAME_MODE_OFF_ON, i);
    }

    public static void setGameMode(Context context, int i, boolean z) {
        setGameMode(context, setBit(getGameMode(context), i, z));
    }

    public static void setHandHeldMode(Context context, boolean z) {
        putBoolean(context, HAND_HELD_MODE, z);
    }

    public static void setMainLamp(Context context, boolean z) {
        putBoolean(context, "switch_main_lamp_enable", z);
    }

    public static void setMirrorHostMode(Context context, boolean z) {
        putBoolean(context, MIRROR_HOST_MODE, z);
    }

    public static void setNetworkacc(Context context, boolean z) {
        putBoolean(context, NUBIA_NETWORKACC_ONOFF, z);
    }

    public static void setNewMesageRemind(Context context, boolean z) {
        putBoolean(context, DB_NEW_MESSAGE_REMIND, z);
    }

    public static void setOffIntellScreen(Context context, boolean z) {
        putBoolean(context, DB_GAME_OFF_INTELL_SCREEN, z);
    }

    public static void setOffScreenAssistant(Context context, boolean z) {
        putBoolean(context, DB_GAME_OFF_SCREEN_ASSISTANT, z);
    }

    public static void setOffThreeFingerShot(Context context, boolean z) {
        putBoolean(context, DB_GAME_OFF_THREE_FINGER_SHOT, z);
    }

    public static void setReminder(Context context, long j) {
        putString(context, String.valueOf(j), getDate());
    }

    public static void setScreenSaveEnergy(Context context, boolean z) {
        putBoolean(context, DB_GAME_SAVE_ENERGY, z);
    }

    public static void setXrMirrorHostMode(Context context, boolean z) {
        putBoolean(context, XR_MIRROR_HOST_MODE, z);
    }
}
