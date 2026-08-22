package cn.nubia.gamelauncher.gamecontrolpanel.utils;

import android.text.TextUtils;
import cn.nubia.common.util.FeatureUtil;
import java.io.File;

/* loaded from: classes.dex */
public class ControlPanelFeatureHelper {
    public static final String CPU_PATH_FEATURE_TEST = "/sys/devices/system/cpu/cpu7/cpufreq/cpuinfo_max_freq,/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq,/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq,/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    private static String CPU_PATH_FEATURE_VALUE = null;
    private static final boolean DEBUG_TEST = false;
    public static final String GPU_PATH_FEATURE_TEST = "/sys/class/kgsl/kgsl-3d0/max_gpuclk,/sys/class/kgsl/kgsl-3d0/gpuclk";
    private static String GPU_PATH_FEATURE_VALUE = null;
    private static final String TAG = "ControlPanelFeatureHelper";
    public static final String ZTE_FEATURE_DISPLAY_MAGIC_DETACH_ENABLE = "ZTE_FEATURE_DISPLAY_MAGIC_DETACH_ENABLE";
    public static final String ZTE_FEATURE_GAME_CONTROLPANEL_ADJUST_OPERATION = "ZTE_FEATURE_GAME_CONTROLPANEL_ADJUST_OPERATION";
    public static final String ZTE_FEATURE_GAME_CONTROLPANEL_ADJUST_OPERATION_DEFAULT_VALUE = "ZTE_FEATURE_GAME_CONTROLPANEL_ADJUST_OPERATION_DEFAULT_VALUE";
    public static final String ZTE_FEATURE_GAME_CONTROLPANEL_MENU = "ZTE_FEATURE_GAME_CONTROLPANEL_MENU";
    public static final String ZTE_FEATURE_GAME_CONTROLPANEL_PERFORMANCE_CPU_PATH = "ZTE_FEATURE_GAME_CONTROLPANEL_PERFORMANCE_CPU_PATH";
    public static final String ZTE_FEATURE_GAME_CONTROLPANEL_PERFORMANCE_GPU_PATH = "ZTE_FEATURE_GAME_CONTROLPANEL_PERFORMANCE_GPU_PATH";
    private static final String ZTE_FEATURE_GAME_FAN = "ZTE_FEATURE_GAME_FAN";
    public static final String ZTE_FEATURE_GAME_HIGH_LIGHTS = "ZTE_FEATURE_GAME_HIGH_LIGHTS";
    public static final String ZTE_FEATURE_GAME_HIGH_LIGHTS_MENU_CONFIG = "ZTE_FEATURE_GAME_HIGH_LIGHTS_MENU_CONFIG";
    public static final String ZTE_FEATURE_GAME_PRECISION_CONTROL = "ZTE_FEATURE_GAME_PRECISION_CONTROL";
    private static final String ZTE_FEATURE_GAME_RANDOM_RECORD = "ZTE_FEATURE_GAME_RANDOM_RECORD";
    private static final String ZTE_FEATURE_LDD_TP_INTERFACE = "ZTE_FEATURE_LDD_TP_INTERFACE";
    private static final String ZTE_FEATURE_LIQUID_COOLING = "ZTE_FEATURE_LIQUID_COOLING";
    public static final String ZTE_FEATURE_MAGIC_RESOLUTIONS = "ZTE_FEATURE_MAGIC_RESOLUTIONS";
    public static final String ZTE_FEATURE_MAGIC_SUPER_RESOLUTION = "ZTE_FEATURE_MAGIC_SUPER_RESOLUTION";
    public static final String ZTE_FEATURE_MANUAL_RECORD_ONLY = "ZTE_FEATURE_MANUAL_RECORD_ONLY";
    public static final String ZTE_FEATURE_MTGPA_PREDOWNLOAD = "ZTE_FEATURE_MTGPA_PREDOWNLOAD";
    private static final String ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH = "ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH";
    private static final String ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = "ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY";
    private static final String ZTE_FEATURE_SUPPORT_MIPMAP_LOD = "ZTE_FEATURE_SUPPORT_MIPMAP_LOD";
    public static final String ZTE_FEATURE_TP_GAME_PARTITION = "ZTE_FEATURE_TP_GAME_PARTITION";
    public static final String ZTE_FEATURE_TP_GAME_PARTITION_SUPPORT_GAMES = "ZTE_FEATURE_TP_GAME_PARTITION_SUPPORT_GAMES";
    private static final String ZTE_FEATURE_USE_GPU_DRIVER_UPDATE = "ZTE_FEATURE_USE_GPU_DRIVER_UPDATE";
    private static final String ZTE_FEATURE_ZPERF_CUBE_GPSETTING_ENABLED = "ZTE_FEATURE_ZPERF_CUBE_GPSETTING_ENABLED";
    private static final String ZTE_TABLET_ENABLE = "ZTE_TABLET_ENABLE";
    private static final String ZTE_TOUCH_RATE_GEAR_CONFIGURATION = "ZTE_TOUCH_RATE_GEAR_CONFIGURATION";

    public enum MenuHelper {
        AdjustOperation,
        PerformanceStrengthen,
        GpuSettings,
        ScreenShowStrengthen,
        VoiceStrengthen,
        NetSettings,
        FunctionConfiguration,
        ResourceSettings,
        PluginSettings
    }

    public enum TouchOperationType {
        TouchSampleRate,
        TouchSen,
        TouchFollow,
        TouchMicroSensitive,
        GyroSen,
        TouchProtectOpen
    }

    public static String getAdjustOperationDefaultValue() {
        return FeatureUtil.get(ZTE_FEATURE_GAME_CONTROLPANEL_ADJUST_OPERATION_DEFAULT_VALUE, getDefaultValue());
    }

    public static String getAdjustOperationSupportItem() {
        String adjustOperationDefaultValue = getAdjustOperationDefaultValue();
        if (adjustOperationDefaultValue == null) {
            return null;
        }
        String str = adjustOperationDefaultValue.contains("TouchSampleRate") ? "TouchSampleRate" : "";
        if (adjustOperationDefaultValue.contains("TouchSen")) {
            str = str.concat(",TouchSen");
        }
        if (adjustOperationDefaultValue.contains("TouchFollow")) {
            str = str + ",TouchFollow";
        }
        if (adjustOperationDefaultValue.contains("TouchMicroSensitive")) {
            str = str + ",TouchMicroSensitive";
        }
        if (adjustOperationDefaultValue.contains("GyroSen")) {
            str = str + ",GyroSen";
        }
        return adjustOperationDefaultValue.contains("TouchProtectOpen") ? str + ",TouchProtectOpen" : str;
    }

    public static String[] getCpuFileNode() {
        if (TextUtils.isEmpty(CPU_PATH_FEATURE_VALUE)) {
            CPU_PATH_FEATURE_VALUE = getPerformanceCpuFileNode();
        }
        String str = CPU_PATH_FEATURE_VALUE;
        LogUtil.d(TAG, " --- getCpuFileNode --- cpuFileNode = " + str);
        return !TextUtils.isEmpty(str) ? str.split(",") : new String[]{PerformanceConstant.DEFAULT_PATH_MAX_CPU_MAIN, PerformanceConstant.DEFAULT_PATH_CUR_CPU_MAIN, PerformanceConstant.DEFAULT_PATH_CUR_CPU_MIDDLE, PerformanceConstant.DEFAULT_PATH_CUR_CPU_MINOR};
    }

    private static String getDefaultMenu() {
        return FeatureUtil.isSprd() ? "PerformanceStrengthen,NetSettings,PluginSettings" : "AdjustOperation,PerformanceStrengthen,GpuSettings,ScreenShowStrengthen,VoiceStrengthen,NetSettings,FunctionConfiguration";
    }

    private static String getDefaultValue() {
        if (FeatureUtil.isSprd()) {
            return null;
        }
        return "TouchSampleRate_1_480,TouchSen_0,TouchFollow_0,TouchMicroSensitive_0,GyroSen_100_100,TouchProtectOpen_1_0";
    }

    public static String getGameControlpanelMenu() {
        return FeatureUtil.get(ZTE_FEATURE_GAME_CONTROLPANEL_MENU, getDefaultMenu());
    }

    public static boolean getGameFan() {
        return FeatureUtil.getBoolean("ZTE_FEATURE_GAME_FAN", false).booleanValue();
    }

    public static String[] getGpuFileNode() {
        if (TextUtils.isEmpty(GPU_PATH_FEATURE_VALUE)) {
            GPU_PATH_FEATURE_VALUE = getPerformanceGpuFileNode();
        }
        String str = GPU_PATH_FEATURE_VALUE;
        LogUtil.d(TAG, " --- getGpuFileNode --- gpuFileNode = " + str);
        return PerformanceConstant.MTK_CHIP ? new String[]{PerformanceConstant.MTK_GPU_TABLE, PerformanceConstant.MTK_GPU_STATUS} : PerformanceConstant.SPREAD_CHIP ? getSprdGpuFileNode() : !TextUtils.isEmpty(str) ? str.split(",") : new String[]{PerformanceConstant.DEFAULT_PATH_MAX_GPU, PerformanceConstant.DEFAULT_PATH_CUR_GPU};
    }

    public static boolean getLiquidCooling() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_LIQUID_COOLING, false).booleanValue();
    }

    public static boolean getOnlySupportManualRecord() {
        return FeatureUtil.getBoolean("ZTE_FEATURE_MANUAL_RECORD_ONLY", false).booleanValue();
    }

    public static String getPerformanceCpuFileNode() {
        return FeatureUtil.get(ZTE_FEATURE_GAME_CONTROLPANEL_PERFORMANCE_CPU_PATH, null);
    }

    public static String getPerformanceGpuFileNode() {
        return FeatureUtil.get(ZTE_FEATURE_GAME_CONTROLPANEL_PERFORMANCE_GPU_PATH, null);
    }

    private static String[] getSprdGpuFileNode() {
        String[][] strArr = {new String[]{PerformanceConstant.SPRD_DEFAULT_PATH_MAX_GPU, PerformanceConstant.SPRD_DEFAULT_PATH_CUR_GPU}, new String[]{PerformanceConstant.SPRD_DEFAULT_PATH_MAX_GPU_ANDROIDV, PerformanceConstant.SPRD_DEFAULT_PATH_CUR_GPU_ANDROIDV}};
        for (int i = 0; i < 2; i++) {
            String[] strArr2 = strArr[i];
            if (new File(strArr2[1]).exists()) {
                return strArr2;
            }
        }
        return new String[]{PerformanceConstant.SPRD_DEFAULT_PATH_MAX_GPU, PerformanceConstant.SPRD_DEFAULT_PATH_CUR_GPU};
    }

    public static Boolean getZtFeatureGameRandomRecord() {
        return FeatureUtil.getBoolean("ZTE_FEATURE_GAME_RANDOM_RECORD", false);
    }

    public static Boolean getZteFeatureDisplayMagicDetachEnable() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_DISPLAY_MAGIC_DETACH_ENABLE, false);
    }

    public static Boolean getZteFeatureGameHighLights() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_GAME_HIGH_LIGHTS, false);
    }

    public static String getZteFeatureGameHighLightsMenuConfig() {
        return FeatureUtil.get(ZTE_FEATURE_GAME_HIGH_LIGHTS_MENU_CONFIG, null);
    }

    public static String getZteFeatureMagicResolutions() {
        return FeatureUtil.get(ZTE_FEATURE_MAGIC_RESOLUTIONS, null);
    }

    public static Boolean getZteFeatureMagicSuperResolution() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_MAGIC_SUPER_RESOLUTION, false);
    }

    public static Boolean getZteFeatureMtgpaPredownload() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_MTGPA_PREDOWNLOAD, false);
    }

    public static Boolean getZteFeatureRedMagicGameLatencyDataSwitch() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH, false);
    }

    public static Boolean getZteFeatureSupportGPUUpdate() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_USE_GPU_DRIVER_UPDATE, false);
    }

    public static Boolean getZteFeatureSupportMipmapLod() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_SUPPORT_MIPMAP_LOD, false);
    }

    public static Boolean getZteFeatureZperfCubeGpsettingEnabled() {
        return Boolean.valueOf(FeatureUtil.getBoolean(ZTE_FEATURE_ZPERF_CUBE_GPSETTING_ENABLED, false).booleanValue());
    }

    public static boolean getZteTabletEnable() {
        return FeatureUtil.getBoolean(ZTE_TABLET_ENABLE, false).booleanValue();
    }

    public static String getZteTouchRateGearConfiguration() {
        return FeatureUtil.get(ZTE_TOUCH_RATE_GEAR_CONFIGURATION, null);
    }

    public static boolean isLddTpInterfaceSupported() {
        return FeatureUtil.getBoolean(ZTE_FEATURE_LDD_TP_INTERFACE, false).booleanValue();
    }

    public static boolean isTouchGameKeySupported() {
        return FeatureUtil.getBoolean("ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY", false).booleanValue();
    }

    public static boolean precisionSupportGames(String str) {
        LogUtil.d(TAG, "supportGames =com.tencent.tmgp.pubgmhd,com.tencent.tmgp.dfm,com.tencent.tmgp.cod,com.tencent.tmgp.gnyx");
        if (TextUtils.isEmpty("com.tencent.tmgp.pubgmhd,com.tencent.tmgp.dfm,com.tencent.tmgp.cod,com.tencent.tmgp.gnyx") || TextUtils.isEmpty(str)) {
            return false;
        }
        return "com.tencent.tmgp.pubgmhd,com.tencent.tmgp.dfm,com.tencent.tmgp.cod,com.tencent.tmgp.gnyx".contains(str);
    }

    public static Boolean supportAiAdjustByFeature(String str) {
        boolean z = false;
        if (FeatureUtil.getBoolean(ZTE_FEATURE_TP_GAME_PARTITION, false).booleanValue() && supportGames(str)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static boolean supportGames(String str) {
        String str2 = FeatureUtil.get(ZTE_FEATURE_TP_GAME_PARTITION_SUPPORT_GAMES, "");
        LogUtil.d(TAG, "supportGames =" + str2);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        return str2.contains(str);
    }

    public static Boolean supportPrecisionByFeature(String str) {
        boolean z = false;
        if (FeatureUtil.getBoolean(ZTE_FEATURE_GAME_PRECISION_CONTROL, false).booleanValue() && precisionSupportGames(str)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
