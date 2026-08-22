package com.zte.gameassist.config;

import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.File;

/* loaded from: classes2.dex */
public final class ZteFeature {
    private static final String METHOD_GET_BOOLEAN = "getBoolean";
    private static final String METHOD_GET_INT = "getInt";
    public static final String NON_LINEARITY_BRIGHTNESS = "non_linearity_brightness";
    private static final String NUBIA_CONFIG_CLASS_NAME = "com.zte.feature.Feature";
    private static final String SOC_VENDOR = "soc_vendor";
    private static final String TAG = "ZteFeature";
    public static final String ZTE_FEATURE_AI_GAME_PREDICTION = "ZTE_FEATURE_AI_GAME_PREDICTION";
    private static final String ZTE_FEATURE_AI_SPEAKER = "ZTE_FEATURE_AI_SPEAKER";
    private static final String ZTE_FEATURE_AI_TRANSLATION = "ZTE_FEATURE_AI_TRANSLATION";
    private static final String ZTE_FEATURE_ANTI_MISOPERATE_NUBIA = "ZTE_FEATURE_ANTI_MISOPERATE_NUBIA";
    public static final String ZTE_FEATURE_BEND_INDECATE = "ZTE_FEATURE_BEND_INDECATE";
    public static final String ZTE_FEATURE_BYPASS_CHARGE_SEPARATION = "ZTE_FEATURE_BYPASS_CHARGE_SEPARATION";
    public static final String ZTE_FEATURE_COLORFUL_LIGHT = "ZTE_FEATURE_COLORFUL_LIGHT";
    public static final String ZTE_FEATURE_DISPLAY_MAGIC_DETACH_ENABLE = "ZTE_FEATURE_DISPLAY_MAGIC_DETACH_ENABLE";
    public static final String ZTE_FEATURE_EXPAND_PROJECTION_SCREEN = "ZTE_FEATURE_EXPAND_PROJECTION_SCREEN";
    public static final String ZTE_FEATURE_EXPAND_PROJECTION_SCREEN_3D_TOUCHPANEL = "ZTE_FEATURE_EXPAND_PROJECTION_SCREEN_3D_TOUCHPANEL";
    public static final String ZTE_FEATURE_EXPAND_PROJECTION_SCREEN_FREEFORM = "ZTE_FEATURE_EXPAND_PROJECTION_SCREEN_FREEFORM";
    private static final String ZTE_FEATURE_GAMEAIASST = "ZTE_FEATURE_GAMEAIASST";
    private static final String ZTE_FEATURE_GAMEAIASST_ABROAD = "ZTE_FEATURE_GAMEAIASST_ABROAD";
    private static final String ZTE_FEATURE_GAMEASSIST_AUDIO_EQUALIZER = "ZTE_FEATURE_GAMEASSIST_AUDIO_EQUALIZER";
    public static final String ZTE_FEATURE_GAMEASSIST_GLOBAL_SEARCH = "ZTE_FEATURE_GAMEASSIST_GLOBAL_SEARCH";
    public static final String ZTE_FEATURE_GAMEASSIST_ONE_KEY_LINK = "ZTE_FEATURE_GAMEASSIST_ONE_KEY_LINK";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_4D_VIBRATE = "ZTE_FEATURE_GAMEASSIST_PLUGIN_4D_VIBRATE";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_GAMENOTES = "ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_GAMENOTES";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_TRIGGER = "ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_TRIGGER";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_BIABLO = "ZTE_FEATURE_GAMEASSIST_PLUGIN_BIABLO";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_CARD_ASSIST = "ZTE_FEATURE_GAMEASSIST_PLUGIN_CARD_ASSIST";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_CHAT_ASSIST = "ZTE_FEATURE_GAMEASSIST_PLUGIN_CHAT_ASSIST";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_COUNTER = "ZTE_FEATURE_GAME_PLUGIN_COUNTER";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_DATA_PANEL = "ZTE_FEATURE_GAMEASSIST_PLUGIN_DATA_PANEL";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_FIXEDLOOK = "ZTE_FEATURE_GAMEASSIST_PLUGIN_FIXEDLOOK";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_GAME_RATIO = "ZTE_FEATURE_GAMEASSIST_PLUGIN_GAME_RATIO";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_MODE_CARD = "ZTE_FEATURE_GAMEASSIST_PLUGIN_MODE_CARD";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_OPERATION_DEVICES = "ZTE_FEATURE_GAMEASSIST_PLUGIN_OPERATION_DEVICES";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_REDMAGIC_BROADCAST = "ZTE_FEATURE_GAMEASSIST_PLUGIN_REDMAGIC_BROADCAST";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_REDMAGIC_ELVESAID = "ZTE_FEATURE_GAMEASSIST_PLUGIN_REDMAGIC_ELVESAID";
    public static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_SORT = "ZTE_FEATURE_GAMEASSIST_PLUGIN_SORT";
    private static final String ZTE_FEATURE_GAMEASSIST_VOICE_CONTROLLER = "ZTE_FEATURE_GAMEASSIST_VOICE_CONTROLLER";
    private static final String ZTE_FEATURE_GAME_AI_JARVIS = "ZTE_FEATURE_GAME_AI_JARVIS";
    private static final String ZTE_FEATURE_GAME_AI_TIPS = "ZTE_FEATURE_GAME_AI_TIPS";
    public static final String ZTE_FEATURE_GAME_DISPLAY_FILTER_EFFECT = "ZTE_FEATURE_GAME_DISPLAY_FILTER_EFFECT";
    public static final String ZTE_FEATURE_GAME_DTS_EQ_FLOAT = "ZTE_FEATURE_GAME_DTS_EQ_FLOAT";
    public static final String ZTE_FEATURE_GAME_FAN = "ZTE_FEATURE_GAME_FAN";
    public static final String ZTE_FEATURE_GAME_NEO = "ZTE_FEATURE_GAME_NEO_TRANSLATE";
    private static final String ZTE_FEATURE_GAME_RANDOM_RECORD = "ZTE_FEATURE_GAME_RANDOM_RECORD";
    public static final String ZTE_FEATURE_GAME_SOUND_PROBE = "ZTE_FEATURE_GAME_SOUND_PROBE";
    public static final String ZTE_FEATURE_GAME_STRATEGY_STATION = "ZTE_FEATURE_GAME_STRATEGY_STATION";
    private static final String ZTE_FEATURE_GAME_VOICE_ASSIST = "ZTE_FEATURE_GAME_VOICE_ASSIST";
    public static final String ZTE_FEATURE_HOST_PERFORMANCE_MONITOR = "ZTE_FEATURE_HOST_PERFORMANCE_MONITOR";
    public static final String ZTE_FEATURE_KEYMAP_SENSITIVITY_WHEEL_DISC = "ZTE_FEATURE_KEYMAP_SENSITIVITY_WHEEL_DISC";
    public static final String ZTE_FEATURE_KEY_MOUSE_MAP = "ZTE_FEATURE_KEY_MOUSE_MAP";
    public static final String ZTE_FEATURE_LEARNED_BEHAVIOR_X_GRAVITY = "ZTE_FEATURE_LEARNED_BEHAVIOR_X_GRAVITY";
    public static final String ZTE_FEATURE_LEIA_3D_UART = "ZTE_FEATURE_lEIA_3D_UART";
    private static final String ZTE_FEATURE_LOW_SUGAR = "ZTE_FEATURE_LOW_SUGAR";
    public static final String ZTE_FEATURE_MAGIC_GAME_ASSIST = "ZTE_FEATURE_MAGIC_GAME_ASSIST";
    public static final String ZTE_FEATURE_MAGIC_RESOLUTIONS_SETTINGS = "ZTE_FEATURE_MAGIC_RESOLUTIONS_SETTINGS";
    public static final String ZTE_FEATURE_MAGIC_SUPER_RESOLUTION = "ZTE_FEATURE_GFRC";
    public static final String ZTE_FEATURE_MAGIC_SUPER_RESOLUTION_OLD = "ZTE_FEATURE_MAGIC_SUPER_RESOLUTION";
    private static final String ZTE_FEATURE_MAGIC_VIRTUAL_HANDLE = "ZTE_FEATURE_MAGIC_VIRTUAL_HANDLE";
    public static final String ZTE_FEATURE_MINI_PROGRAME_ADD_GAMESPACE = "ZTE_FEATURE_MINI_PROGRAME_ADD_GAMESPACE";
    private static final String ZTE_FEATURE_MIRROR_PROJECTION_SCREEN = "ZTE_FEATURE_MIRROR_PROJECTION_SCREEN";
    private static final String ZTE_FEATURE_MULTI_SUB_SCREEN = "ZTE_FEATURE_MULTI_SUB_SCREEN";
    private static final String ZTE_FEATURE_NEO_GAME_LIB = "ZTE_FEATURE_NEO_GAME_LIB";
    public static final String ZTE_FEATURE_PACKAGE_PLUGIN_VIBRATE = "ZTE_FEATURE_PACKAGE_PLUGIN_VIBRATE";
    public static final String ZTE_FEATURE_PERFORMANCE_CUSTOM_MODE = "ZTE_FEATURE_ZPERF_CUBE_GPSETTING_ENABLED";
    public static final String ZTE_FEATURE_REDMAGIC_GAMEKEY = "ZTE_FEATURE_REDMAGIC_GAMEKEY";
    public static final String ZTE_FEATURE_REDMAGIC_TOUCH_CAMERAKEY = "ZTE_FEATURE_CAMERAKEY_VIRTUAL_TOUCH";
    public static final String ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = "ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY";
    public static final String ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD = "ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD";
    public static final String ZTE_FEATURE_RED_MAGIC = "ZTE_FEATURE_RED_MAGIC";
    public static final String ZTE_FEATURE_RED_MAGIC_PHONE = "ZTE_FEATURE_RED_MAGIC_PHONE";
    public static final String ZTE_FEATURE_REFRESH_RATE_LITE_OPTION_MODE = "ZTE_FEATURE_REFRESH_RATE_LITE_OPTION_MODE";
    public static final String ZTE_FEATURE_SENSOR_OPERATION_TOUCH = "ZTE_FEATURE_SENSOR_OPERATION_TOUCH";
    private static final String ZTE_FEATURE_STREAM_GAME = "ZTE_FEATURE_STREAM_GAME";
    public static final String ZTE_FEATURE_SUPERIOR_QUALITY_GAME = "ZTE_FEATURE_SUPERIOR_QUALITY_GAME";
    public static final String ZTE_FEATURE_SUPPORT_CHARGE_SEPARATION = "ZTE_FEATURE_SUPPORT_CHARGE_SEPARATION";
    private static final String ZTE_FEATURE_SUPPORT_DEMI = "ZTE_FEATURE_GAMEASSIST_SUPPORT_DEMI";
    public static final String ZTE_FEATURE_SUPPORT_WIDOWREPLY = "MFV_FEATURE_WINDOWREPLY";
    public static final String ZTE_FEATURE_WINDOWREPLY_ENTRANCE_DISPLAY = "ZTE_FEATURE_WINDOWREPLY_ENTRANCE_DISPLAY";
    private static final String ZTE_FEATURE_ZTE_FOLDABLE = "ZTE_FOLDABLE";
    private static final String ZTE_FOLDABLE_IN_BIG_PHONE = "ZTE_FOLDABLE";
    public static final String ZTE_PUBGMHD_SUPPORT_AUDIO_VIBRATE = "ZTE_PUBGMHD_SUPPORT_AUDIO_VIBRATE";
    public static final String ZTE_SUPPORTED_REFRESH_RATE = "ZTE_SUPPORTED_REFRESH_RATE";
    public static final String ZTE_TABLET_ENABLE = "ZTE_TABLET_ENABLE";
    private static final String NODE_PATH_MICROPUMP_ENABLE = "/proc/driver/micropump/enable";
    private static final File MICROPUMP_ENABLE_FILE = new File(NODE_PATH_MICROPUMP_ENABLE);
    public static final boolean IS_INTER_VERSION = "abroad".equals(SystemProperties.get("ro.vendor.mifavor.custom", "home"));

    public static Boolean getBoolean(String str, boolean z) {
        try {
            return (Boolean) Class.forName(NUBIA_CONFIG_CLASS_NAME).getMethod(METHOD_GET_BOOLEAN, String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z));
        } catch (Error | Exception e2) {
            GaLog.b(TAG, "getBoolean " + e2);
            return Boolean.FALSE;
        }
    }

    public static String getBrowserPackage() {
        return isRedMagicProduct() ? "cn.nubia.browser" : "com.ume.browser";
    }

    public static Integer getInt(String str, int i2) {
        try {
            return (Integer) Class.forName(NUBIA_CONFIG_CLASS_NAME).getDeclaredMethod(METHOD_GET_INT, String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i2));
        } catch (Error | Exception e2) {
            GaLog.b(TAG, "getInt " + e2);
            return 0;
        }
    }

    public static String[] getSupportRefreshRate() {
        String str = ZteFeatureWrapper.get(ZTE_SUPPORTED_REFRESH_RATE, null);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(",");
    }

    public static boolean isAZSupport4DVibrate(String str) {
        return ZteFeatureWrapper.get(ZTE_FEATURE_PACKAGE_PLUGIN_VIBRATE, "").equals(str);
    }

    public static boolean isDtsEqFloat() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_DTS_EQ_FLOAT, false);
    }

    public static boolean isNeoProduct() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_NEO, false);
    }

    public static boolean isNonLinearityBrightness() {
        return ZteFeatureWrapper.getBoolean(NON_LINEARITY_BRIGHTNESS, true);
    }

    public static boolean isPluginNeedRemove() {
        return isRedMagicProduct() || !isSupportOneKeyLink();
    }

    public static boolean isRedMagicPhone() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_RED_MAGIC, false) && !isTabletProduct();
    }

    public static boolean isRedMagicProduct() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_RED_MAGIC, false);
    }

    public static boolean isSM8850Project() {
        return "SM8850".equals(SystemProperties.get("ro.vendor.qti.soc_model", "0"));
    }

    public static boolean isSprdVendor() {
        return "sprd".equals(ZteFeatureWrapper.get(SOC_VENDOR, null));
    }

    public static boolean isSuperResolutionDetachEnable() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_DISPLAY_MAGIC_DETACH_ENABLE, false);
    }

    public static boolean isSuperiorQualityGame() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_SUPERIOR_QUALITY_GAME, false);
    }

    public static boolean isSupport3D() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_LEIA_3D_UART, false);
    }

    public static boolean isSupport3DTouchPanel() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_EXPAND_PROJECTION_SCREEN_3D_TOUCHPANEL, false);
    }

    public static boolean isSupport4DVIBRATEPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_4D_VIBRATE, false);
    }

    public static boolean isSupportAIChat() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEAIASST, false);
    }

    public static boolean isSupportAIChatAbroad() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEAIASST_ABROAD, false);
    }

    public static boolean isSupportAISpeaker() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_AI_SPEAKER, false);
    }

    public static boolean isSupportAITip() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_AI_TIPS, false);
    }

    public static boolean isSupportAITranslation() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_AI_TRANSLATION, false);
    }

    public static boolean isSupportAITriggerPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_TRIGGER, false);
    }

    public static boolean isSupportAudioEqualizer() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_AUDIO_EQUALIZER, true);
    }

    public static boolean isSupportAudioVibrate() {
        return ZteFeatureWrapper.getBoolean(ZTE_PUBGMHD_SUPPORT_AUDIO_VIBRATE, false);
    }

    public static boolean isSupportBiabloPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_BIABLO, false) && "qcom".equals(ZteFeatureWrapper.get(SOC_VENDOR, null));
    }

    public static boolean isSupportCardAssistPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_CARD_ASSIST, false);
    }

    public static boolean isSupportCardModeInPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_MODE_CARD, false);
    }

    public static boolean isSupportChatAssistInPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_CHAT_ASSIST, false);
    }

    public static boolean isSupportCounter() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_COUNTER, false);
    }

    public static boolean isSupportCustom() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_PERFORMANCE_CUSTOM_MODE, false);
    }

    public static boolean isSupportDataPanelInPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_DATA_PANEL, false);
    }

    public static boolean isSupportDemi() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_SUPPORT_DEMI, false);
    }

    public static boolean isSupportFixedLookPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_FIXEDLOOK, false);
    }

    public static boolean isSupportFoldBig() {
        return ZteFeatureWrapper.getInt("ZTE_FOLDABLE", 0) == 2;
    }

    public static boolean isSupportGameAssist() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_MAGIC_GAME_ASSIST, false);
    }

    public static boolean isSupportGameBenefit() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_NEO_GAME_LIB, false);
    }

    public static boolean isSupportGameDisplayFilterEffect() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_DISPLAY_FILTER_EFFECT, false);
    }

    public static boolean isSupportGameNote() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_GAMENOTES, false);
    }

    public static boolean isSupportGamePrediction() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_AI_GAME_PREDICTION, false);
    }

    public static boolean isSupportGameRandomRecord() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_RANDOM_RECORD, false);
    }

    public static boolean isSupportGameVoiceAssist() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_VOICE_ASSIST, false);
    }

    public static boolean isSupportGlobalSearch() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_GLOBAL_SEARCH, false);
    }

    public static boolean isSupportHighSensitivityWheel() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_KEYMAP_SENSITIVITY_WHEEL_DISC, false);
    }

    public static boolean isSupportHostFreeform() {
        return false;
    }

    public static boolean isSupportHostPerformanceMonitor() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_HOST_PERFORMANCE_MONITOR, false);
    }

    public static boolean isSupportLearnedActionForXGravity() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_LEARNED_BEHAVIOR_X_GRAVITY, false);
    }

    public static boolean isSupportLowSugar() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_LOW_SUGAR, false);
    }

    public static boolean isSupportMagicWindow() {
        return ZteFeatureWrapper.getInt("ZTE_FOLDABLE", 0) == 2;
    }

    public static boolean isSupportMultiSubScreen() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_MULTI_SUB_SCREEN, false);
    }

    private static boolean isSupportOneKeyLink() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_ONE_KEY_LINK, true);
    }

    public static boolean isSupportPeripheralControl() {
        return !ZteFeatureWrapper.getBoolean(ZTE_FEATURE_KEY_MOUSE_MAP, false) && ZteFeatureWrapper.getBoolean(ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD, false);
    }

    public static boolean isSupportPleasedDisplay() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_GAME_RATIO, false);
    }

    public static boolean isSupportRedmagicBroadcastInPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_REDMAGIC_BROADCAST, false);
    }

    public static boolean isSupportRedmagicElvesaidInPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_REDMAGIC_ELVESAID, false);
    }

    public static boolean isSupportSensorOperation() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_SENSOR_OPERATION_TOUCH, false);
    }

    public static boolean isSupportSort() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_SORT, false);
    }

    public static boolean isSupportSoundProbe() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_SOUND_PROBE, false);
    }

    public static boolean isSupportStrategyStation() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_STRATEGY_STATION, false);
    }

    public static boolean isSupportStreamGame() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_STREAM_GAME, false);
    }

    public static boolean isSupportSuperResolution() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_MAGIC_SUPER_RESOLUTION, false);
    }

    public static boolean isSupportSuperResolutionOld() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_MAGIC_SUPER_RESOLUTION_OLD, false);
    }

    public static boolean isSupportSuperResolutionSettings() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_MAGIC_RESOLUTIONS_SETTINGS, false);
    }

    public static boolean isSupportTouchCameraKey() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_REDMAGIC_TOUCH_CAMERAKEY, false);
    }

    public static boolean isSupportTouchGameKey() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY, false);
    }

    public static boolean isSupportUserGuide() {
        return isSupportLearnedActionForXGravity();
    }

    public static boolean isSupportVoiceController() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_VOICE_CONTROLLER, false);
    }

    public static boolean isSupportWechatGameApp() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_MINI_PROGRAME_ADD_GAMESPACE, false);
    }

    public static boolean isSupportXGravityPlugin() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAMEASSIST_PLUGIN_OPERATION_DEVICES, false) || ZteFeatureWrapper.getBoolean(ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD, false);
    }

    public static boolean isSuppprtRedMagicGameKey() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_REDMAGIC_GAMEKEY, false);
    }

    public static boolean isTabletProduct() {
        return ZteFeatureWrapper.getBoolean(ZTE_TABLET_ENABLE, false);
    }

    public static boolean liteOptionModeEnable() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_REFRESH_RATE_LITE_OPTION_MODE, false);
    }

    public static boolean supportBypassChargeSeparation() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_BYPASS_CHARGE_SEPARATION, false);
    }

    public static boolean supportChargeSeparation() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_SUPPORT_CHARGE_SEPARATION, true);
    }

    public static boolean supportColorfulLight() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_COLORFUL_LIGHT, false);
    }

    public static boolean supportExpandProjection() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_EXPAND_PROJECTION_SCREEN, false);
    }

    public static boolean supportFan() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_FAN, false);
    }

    public static boolean supportImageSearch() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_GAME_AI_JARVIS, false);
    }

    public static boolean supportLiquidCooling() {
        return MICROPUMP_ENABLE_FILE.exists();
    }

    public static boolean supportVirtualHandle() {
        return Build.VERSION.SDK_INT >= 34 ? getBoolean(ZTE_FEATURE_MAGIC_VIRTUAL_HANDLE, false).booleanValue() : getBoolean(ZTE_FEATURE_MIRROR_PROJECTION_SCREEN, false).booleanValue() || getBoolean(ZTE_FEATURE_ANTI_MISOPERATE_NUBIA, false).booleanValue();
    }

    public static boolean supportWindowReply() {
        return ZteFeatureWrapper.getBoolean(ZTE_FEATURE_WINDOWREPLY_ENTRANCE_DISPLAY, false);
    }
}
