package cn.nubia.gamelauncher.gamecontrolpanel.superresolution;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.superresolution.AppMapConfig;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SuperResolutionHelper {
    private static String[] CURRENT_APP_SUPPORT_GEAR_ARRAY = null;
    public static final String DEFAULT_SUPPORT = "default";
    private static final String[] DEFAULT_SUPPORT_ITEM;
    public static final String MAGIC_SUPER_RESOLUTION_DEFAULT_PREFIX = "persist.maso.";
    public static final HashMap<String, String> PACKAGE_MAPPING_MAP;
    private static final int PKG_NAME_MAX_LENGTH = 25;
    public static final String SUPER_GEAR_1116 = "1116";
    public static final int SUPER_GEAR_1116_VALUE = 1;
    public static final String SUPER_GEAR_1440 = "1440";
    public static final int SUPER_GEAR_1440_VALUE = 2;
    public static final String SUPER_RESOLUTION_SWITCH_KEY = "super_resolution_switch_key";
    public static ArrayList<String> SUPPORT_SUPER_RESOLUTION_WHITE_LIST = new ArrayList<>();
    private static final String TAG = "SuperResolutionHelper";

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        PACKAGE_MAPPING_MAP = hashMap;
        CURRENT_APP_SUPPORT_GEAR_ARRAY = null;
        DEFAULT_SUPPORT_ITEM = new String[]{SUPER_GEAR_1116, SUPER_GEAR_1440};
        SUPPORT_SUPER_RESOLUTION_WHITE_LIST.add(HighLightsUtils.YS_PACKAGE_NAME);
        hashMap.put(HighLightsUtils.YS_PACKAGE_NAME, "yuanshen");
        hashMap.put("com.miHoYo.ys.bilibili", "yuanshen");
        hashMap.put("com.miHoYo.ys.mi", "yuanshen");
    }

    public static String buildValue(Context context, String str, boolean z, int i) {
        String str2;
        String str3 = ReflectSystemPropertiesUtil.get(context, null, null);
        if (TextUtils.isEmpty(str3)) {
            str2 = str + ":" + (z ? i : 0) + ",";
        } else if (str3.contains(str)) {
            String[] split = str3.split(",");
            int length = split.length;
            while (true) {
                if (r4 >= length) {
                    break;
                }
                String str4 = split[r4];
                if (!TextUtils.isEmpty(str4) && str4.contains(str)) {
                    str3 = str3.replace(str4 + ",", "");
                    break;
                }
                r4++;
            }
            ReflectSystemPropertiesUtil.set(context, null, TextUtils.isEmpty(str3) ? null : str3);
            str2 = str3;
        } else {
            str2 = str3 + str + ":" + (z ? i : 0) + ",";
        }
        if (!z) {
            LogUtil.i(TAG, " buildValue old dbValue = " + str2 + " ;; superGear = " + i);
        }
        return str2;
    }

    public static void closeSuperResolution(Context context, String str) {
        buildValue(context, str, false, 0);
        String constructPropertiesKeyByPkgName = constructPropertiesKeyByPkgName(str);
        LogUtil.i(TAG, " closeSuperResolution  ");
        ReflectSystemPropertiesUtil.set(context, constructPropertiesKeyByPkgName, String.valueOf(0));
    }

    public static String constructPropertiesKeyByPkgName(String str) {
        StringBuilder sb = new StringBuilder(MAGIC_SUPER_RESOLUTION_DEFAULT_PREFIX);
        if (!TextUtils.isEmpty(str) && str.length() > 25) {
            int length = str.length() - 25;
            str = str.substring(length);
            LogUtil.i(TAG, "constructPropertiesKey beginIndex = " + length + " ;; targetSuffixStr = " + str);
        }
        sb.append(str);
        String sb2 = sb.toString();
        LogUtil.i(TAG, "constructPropertiesKey targetPropertiesKey = " + sb2);
        return sb2;
    }

    public static int getDescriptionFromSuperGear(String str) {
        str.hashCode();
        return (str.equals(SUPER_GEAR_1116) || !str.equals(SUPER_GEAR_1440)) ? R.string.super_resolution_1116_description : R.string.super_resolution_1440_description;
    }

    public static Integer getLastSuperResolutionSwitchStats(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), SUPER_RESOLUTION_SWITCH_KEY);
        String constructPropertiesKeyByPkgName = constructPropertiesKeyByPkgName(str);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        int i = 0;
        if (string.contains(constructPropertiesKeyByPkgName)) {
            String[] split = string.split(",");
            int length = split.length;
            while (i < length) {
                String str2 = split[i];
                if (!TextUtils.isEmpty(str2) && str2.contains(constructPropertiesKeyByPkgName)) {
                    return Integer.valueOf(Integer.parseInt(str2.split(":")[1]));
                }
                i++;
            }
            return null;
        }
        if (!string.contains(str)) {
            return null;
        }
        String[] split2 = string.split(",");
        int length2 = split2.length;
        while (i < length2) {
            String str3 = split2[i];
            if (!TextUtils.isEmpty(str3) && str3.contains(str)) {
                return Integer.valueOf(Integer.parseInt(str3.split(":")[1]));
            }
            i++;
        }
        return null;
    }

    public static Integer getSuperResolutionSwitchStatus(Context context, String str) {
        String str2 = ReflectSystemPropertiesUtil.get(context, constructPropertiesKeyByPkgName(str), null);
        if (!TextUtils.isEmpty(str2)) {
            return Integer.valueOf(Integer.parseInt(str2));
        }
        String str3 = ReflectSystemPropertiesUtil.get(context, null, null);
        if (TextUtils.isEmpty(str3) || !str3.contains(str)) {
            return null;
        }
        for (String str4 : str3.split(",")) {
            if (!TextUtils.isEmpty(str4) && str4.contains(str)) {
                return Integer.valueOf(Integer.parseInt(str4.split(":")[1]));
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static cn.nubia.gamelauncher.gamecontrolpanel.superresolution.AppMapConfig getSupportGearArrayByMagicConfigXml() {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionHelper.getSupportGearArrayByMagicConfigXml():cn.nubia.gamelauncher.gamecontrolpanel.superresolution.AppMapConfig");
    }

    private static String[] getSupportItem(String[] strArr, String str) {
        String[] strArr2 = null;
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                String[] split = str2.split(":");
                LogUtil.i(TAG, "getSupportItem : tempArray  = " + split[1]);
                strArr2 = split[1].split(",");
            }
        }
        return strArr2;
    }

    public static String[] getSupportResolutionGear(String str) {
        boolean z;
        if (CURRENT_APP_SUPPORT_GEAR_ARRAY == null) {
            r2 = null;
            r2 = null;
            String[] strArr = null;
            if (ControlPanelFeatureHelper.getZteFeatureDisplayMagicDetachEnable().booleanValue()) {
                AppMapConfig supportGearArrayByMagicConfigXml = getSupportGearArrayByMagicConfigXml();
                if (supportGearArrayByMagicConfigXml != null && supportGearArrayByMagicConfigXml.getConfig() != null) {
                    for (AppMapConfig.Config config : supportGearArrayByMagicConfigXml.getConfig()) {
                        if (config != null && config.getItem() != null) {
                            for (AppMapConfig.Item item : config.getItem()) {
                                LogUtil.d(TAG, " getSupportResolutionGear item'getPackage_name = " + item.getPackage_name());
                                if (item != null && !TextUtils.isEmpty(item.getPackage_name()) && item.getPackage_name().equals(str) && !TextUtils.isEmpty(config.getResolution_config())) {
                                    LogUtil.d(TAG, " getSupportResolutionGear config'Resolution_config = " + config.getResolution_config());
                                    strArr = config.getResolution_config().split(",");
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (z) {
                            break;
                        }
                    }
                }
            } else {
                HashMap<String, String> hashMap = PACKAGE_MAPPING_MAP;
                String str2 = hashMap.containsKey(str) ? hashMap.get(str) : null;
                LogUtil.i(TAG, "getSupportResolutionGear: mValue = " + str2);
                String zteFeatureMagicResolutions = ControlPanelFeatureHelper.getZteFeatureMagicResolutions();
                LogUtil.i(TAG, "getSupportResolutionGear: supportString = " + zteFeatureMagicResolutions);
                if (TextUtils.isEmpty(zteFeatureMagicResolutions)) {
                    strArr = DEFAULT_SUPPORT_ITEM;
                } else {
                    String[] split = zteFeatureMagicResolutions.split(";");
                    strArr = zteFeatureMagicResolutions.contains(str2) ? getSupportItem(split, str2) : getSupportItem(split, DEFAULT_SUPPORT);
                }
            }
            CURRENT_APP_SUPPORT_GEAR_ARRAY = strArr;
            if (strArr != null) {
                for (String str3 : strArr) {
                    LogUtil.i(TAG, " getSupportResolutionGear --- supportItem = " + str3);
                }
            }
        }
        return CURRENT_APP_SUPPORT_GEAR_ARRAY;
    }

    public static void openSuperResolution(Context context, String str, int i) {
        buildValue(context, str, true, i);
        String constructPropertiesKeyByPkgName = constructPropertiesKeyByPkgName(str);
        LogUtil.i(TAG, " openSuperResolution  superGear = " + i);
        ReflectSystemPropertiesUtil.set(context, constructPropertiesKeyByPkgName, String.valueOf(i));
    }

    public static void resetCurrentAppSupportGearArray() {
        CURRENT_APP_SUPPORT_GEAR_ARRAY = null;
    }

    public static void saveLastSuperResolutionSwitchStats(Context context, String str, Integer num) {
        String string = Settings.Global.getString(context.getContentResolver(), SUPER_RESOLUTION_SWITCH_KEY);
        String constructPropertiesKeyByPkgName = constructPropertiesKeyByPkgName(str);
        StringBuilder sb = new StringBuilder();
        LogUtil.i(TAG, " saveLastSuperResolutionSwitchStats lastStatus = " + string);
        if (!TextUtils.isEmpty(string)) {
            int i = 0;
            if (string.contains(constructPropertiesKeyByPkgName)) {
                String[] split = string.split(",");
                int length = split.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String str2 = split[i];
                    if (!TextUtils.isEmpty(str2) && str2.contains(constructPropertiesKeyByPkgName)) {
                        sb.append(string.replace(str2, constructPropertiesKeyByPkgName + ":" + num));
                        break;
                    }
                    i++;
                }
            } else if (string.contains(str)) {
                String[] split2 = string.split(",");
                int length2 = split2.length;
                while (true) {
                    if (i >= length2) {
                        break;
                    }
                    String str3 = split2[i];
                    if (!TextUtils.isEmpty(str3) && str3.contains(str)) {
                        sb.append(string.replace(str3, constructPropertiesKeyByPkgName + ":" + num));
                        break;
                    }
                    i++;
                }
            } else {
                sb.append(string).append(constructPropertiesKeyByPkgName).append(":").append(num).append(",");
            }
        } else if (num != null) {
            sb.append(constructPropertiesKeyByPkgName).append(":").append(num).append(",");
        }
        LogUtil.i(TAG, " saveLastSuperResolutionSwitchStats dbValue = " + ((Object) sb));
        Settings.Global.putString(context.getContentResolver(), SUPER_RESOLUTION_SWITCH_KEY, sb.toString());
    }

    public static int superGearToValue(String str) {
        int i = 1;
        if (CURRENT_APP_SUPPORT_GEAR_ARRAY != null) {
            int i2 = 0;
            while (true) {
                String[] strArr = CURRENT_APP_SUPPORT_GEAR_ARRAY;
                if (i2 >= strArr.length) {
                    break;
                }
                if (str == strArr[i2]) {
                    i = i2 + 1;
                }
                i2++;
            }
        }
        return i;
    }

    public static boolean supportSuperResolution() {
        boolean booleanValue = ControlPanelFeatureHelper.getZteFeatureMagicSuperResolution().booleanValue();
        LogUtil.i(TAG, " supportSuperResolution support = " + booleanValue);
        return booleanValue;
    }

    public static boolean supportSuperResolutionByPkgName(String str) {
        boolean contains = SUPPORT_SUPER_RESOLUTION_WHITE_LIST.contains(str);
        LogUtil.i(TAG, " supportSuperResolutionByPkgName support = " + contains + " ;;SUPPORT_SUPER_RESOLUTION_WHITE_LIST = " + SUPPORT_SUPER_RESOLUTION_WHITE_LIST);
        return contains;
    }

    public static String valueToSuperGear(Integer num) {
        String str = SUPER_GEAR_1116;
        if (num != null && CURRENT_APP_SUPPORT_GEAR_ARRAY != null) {
            for (int i = 0; i < CURRENT_APP_SUPPORT_GEAR_ARRAY.length; i++) {
                if (i == num.intValue() - 1) {
                    str = CURRENT_APP_SUPPORT_GEAR_ARRAY[i];
                }
            }
        }
        return str;
    }
}
