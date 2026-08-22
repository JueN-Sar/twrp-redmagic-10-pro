package cn.nubia.gamelauncher.gamecontrolpanel.utils;

import android.text.TextUtils;
import androidx.media3.extractor.ts.PsExtractor;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;

/* loaded from: classes.dex */
public class TouchOperationHelper {
    private static final int PAD_DEFAULT_TOUCH_SIMPLE_RATE = 120;
    private static final String TAG = "TouchOperationHelper";
    private static final int[] TOUCH_FOLLOW_DEFAULT_VALUE;
    private static final int[] TOUCH_MICRO_SENSITIVE_DEFAULT_VALUE;
    private static final int TOUCH_RATE_ARRAY_LENGTH = 2;
    private static final String ADJUST_OPERATION_DEFAULT_VALUE = ControlPanelFeatureHelper.getAdjustOperationDefaultValue();
    private static final String ADJUST_OPERATION_SUPPORT_SUB_ITEM_VALUE = ControlPanelFeatureHelper.getAdjustOperationSupportItem();
    private static final String ZTE_TOUCH_RATE_GEAR_CONFIGURATION_VALUE = ControlPanelFeatureHelper.getZteTouchRateGearConfiguration();
    private static final int[] TOUCH_SIMPLE_RATE_DEFAULT_VALUE = {360};
    private static final int[] TOUCH_SEN_DEFAULT_VALUE = {0};
    private static final int[] GYROSEN_DEFAULT_VALUE = {100, 100};
    private static final int[] TOUCH_PROTECT_OPEN_DEFAULT_VALUE = {1, 0};
    private static final int[] HIGH_TOUCH_RATE_DEFAULT_GEAR = {480, 960};
    private static final int[] LOW_TOUCH_RATE_DEFAULT_GEAR = {360, 720};
    private static final int[] PAD_TOUCH_RATE_DEFAULT_GEAR = {120, PsExtractor.VIDEO_STREAM_MASK};

    static {
        int[] iArr = {0};
        TOUCH_FOLLOW_DEFAULT_VALUE = iArr;
        TOUCH_MICRO_SENSITIVE_DEFAULT_VALUE = iArr;
    }

    public static boolean defaultTouchRateIs120() {
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchSampleRate.toString();
        if (!str.contains(touchOperationType)) {
            return false;
        }
        for (String str2 : str.split(",")) {
            if (str2.contains(touchOperationType)) {
                String[] split = str2.split("_");
                LogUtil.i(TAG, " ---- getTouchSampleRateDefaultValue --- touchSampleRate = " + Integer.parseInt(split[2]));
                if (new int[]{Integer.parseInt(split[2])}[0] == 120) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] getGyroSenDefaultValue() {
        int[] iArr = GYROSEN_DEFAULT_VALUE;
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (!TextUtils.isEmpty(str)) {
            String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.GyroSen.toString();
            if (str.contains(touchOperationType)) {
                for (String str2 : str.split(",")) {
                    if (str2.contains(touchOperationType)) {
                        String[] split = str2.split("_");
                        LogUtil.i(TAG, " ---- getGyroSenDefaultValue --- x = " + split[1] + " ;; y = " + split[2]);
                        iArr = new int[]{Integer.parseInt(split[1]), Integer.parseInt(split[2])};
                    }
                }
            }
        }
        return iArr;
    }

    public static int getHighTouchRate() {
        return getZteTouchRateGearConfiguration()[0];
    }

    public static int getSuperHighTouchRate() {
        return getZteTouchRateGearConfiguration()[1];
    }

    public static int[] getTouchFollowDefaultValue() {
        int[] iArr = TOUCH_FOLLOW_DEFAULT_VALUE;
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (!TextUtils.isEmpty(str)) {
            String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchFollow.toString();
            if (str.contains(touchOperationType)) {
                for (String str2 : str.split(",")) {
                    if (str2.contains(touchOperationType)) {
                        String[] split = str2.split("_");
                        LogUtil.i(TAG, " ---- getTouchFollowDefaultValue --- touchFollowDefault = " + Integer.valueOf(split[1]));
                        iArr = new int[]{Integer.parseInt(split[1])};
                    }
                }
            }
        }
        return iArr;
    }

    public static int[] getTouchMicroSensitiveDefaultValue() {
        int[] iArr = TOUCH_MICRO_SENSITIVE_DEFAULT_VALUE;
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (!TextUtils.isEmpty(str)) {
            String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchMicroSensitive.toString();
            if (str.contains(touchOperationType)) {
                for (String str2 : str.split(",")) {
                    if (str2.contains(touchOperationType)) {
                        String[] split = str2.split("_");
                        LogUtil.i(TAG, " ---- getTouchMicroSensitiveDefaultValue --- touchMicroSensitiveDefault = " + Integer.valueOf(split[1]));
                        iArr = new int[]{Integer.parseInt(split[1])};
                    }
                }
            }
        }
        return iArr;
    }

    public static int[] getTouchProtectOpenDefaultValue() {
        int[] iArr = TOUCH_PROTECT_OPEN_DEFAULT_VALUE;
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (!TextUtils.isEmpty(str)) {
            String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchProtectOpen.toString();
            if (str.contains(touchOperationType)) {
                for (String str2 : str.split(",")) {
                    if (str2.contains(touchOperationType)) {
                        String[] split = str2.split("_");
                        LogUtil.i(TAG, " ---- getTouchProtectOpenDefaultValue --- switchStatus = " + split[1] + " ;; defaultValue = " + split[2]);
                        iArr = new int[]{Integer.parseInt(split[1]), Integer.parseInt(split[2])};
                    }
                }
            }
        }
        return iArr;
    }

    public static int[] getTouchSampleRateDefaultValue() {
        int[] iArr = TOUCH_SIMPLE_RATE_DEFAULT_VALUE;
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (!TextUtils.isEmpty(str)) {
            String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchSampleRate.toString();
            if (str.contains(touchOperationType)) {
                for (String str2 : str.split(",")) {
                    if (str2.contains(touchOperationType)) {
                        String[] split = str2.split("_");
                        LogUtil.i(TAG, " ---- getTouchSampleRateDefaultValue --- touchSampleRate = " + Integer.parseInt(split[2]));
                        iArr = new int[]{Integer.parseInt(split[2])};
                    }
                }
            }
        }
        return iArr;
    }

    public static int[] getTouchSenDefaultValue() {
        int[] iArr = TOUCH_SEN_DEFAULT_VALUE;
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (!TextUtils.isEmpty(str)) {
            String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchSen.toString();
            if (str.contains(touchOperationType)) {
                for (String str2 : str.split(",")) {
                    if (str2.contains(touchOperationType)) {
                        String[] split = str2.split("_");
                        LogUtil.d(TAG, " ---- getTouchSenDefaultValue --- touchSenDefault = " + Integer.valueOf(split[1]));
                        iArr = new int[]{Integer.parseInt(split[1])};
                    }
                }
            }
        }
        return iArr;
    }

    public static int[] getZteTouchRateGearConfiguration() {
        int[] iArr = HIGH_TOUCH_RATE_DEFAULT_GEAR;
        String str = ZTE_TOUCH_RATE_GEAR_CONFIGURATION_VALUE;
        if (TextUtils.isEmpty(str)) {
            return isPad().booleanValue() ? defaultTouchRateIs120() ? PAD_TOUCH_RATE_DEFAULT_GEAR : iArr : !supportHighTouchRate() ? LOW_TOUCH_RATE_DEFAULT_GEAR : iArr;
        }
        if (!str.contains(",")) {
            return iArr;
        }
        LogUtil.i(TAG, " getZteTouchRateGearConfiguration value = " + str);
        String[] split = str.split(",");
        return (split == null || split.length != 2) ? iArr : new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
    }

    public static Boolean isPad() {
        return Boolean.valueOf(ControlPanelFeatureHelper.getZteTabletEnable());
    }

    public static Boolean supportAdjustOperationItem(ControlPanelFeatureHelper.TouchOperationType touchOperationType) {
        String str = ADJUST_OPERATION_SUPPORT_SUB_ITEM_VALUE;
        return Boolean.valueOf(!TextUtils.isEmpty(str) ? str.contains(touchOperationType.toString()) : false);
    }

    public static boolean supportHighTouchRate() {
        String str = ADJUST_OPERATION_DEFAULT_VALUE;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String touchOperationType = ControlPanelFeatureHelper.TouchOperationType.TouchSampleRate.toString();
        if (!str.contains(touchOperationType)) {
            return true;
        }
        boolean z = true;
        for (String str2 : str.split(",")) {
            if (str2.contains(touchOperationType)) {
                String[] split = str2.split("_");
                LogUtil.i(TAG, " ---- supportHighTouchRate ---  = " + Integer.valueOf(split[1]));
                z = "1".equals(split[1]);
            }
        }
        return z;
    }
}
