package cn.nubia.gamelauncher.gamecontrolpanel.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FunctionAllocationHelper {
    private static final String GAME_HIGH_LIGHTS_MENU_CONFIG_VALUE_TEST = "2+com.tencent.tmgp.sgame_wzry_1,com.tencent.tmgp.pubgmhd_hpjy_0,com.tencent.ig_pubg_0,com.tencent.lolm_lol_1";
    private static final String GAME_TGPA_PREDOWNLOAD_SETTINGS_KEY = "game_tgpa_predownload";
    private static final int IS_SUPPORT_RECORD_DIE = 1;
    private static final int IS_SUPPORT_REDMAGIC = 2;
    private static final String NUBIA_GAME_RANDOM_BACK_RECORD_TIME = "nubia_game_random_back_record_time";
    private static final String NUBIA_GAME_RANDOM_BACK_RECORD_TIME_DEFAULT_VALUE = "30";
    private static final String NUBIA_GAME_RANDOM_POSITIVE_RECORD_TIME = "nubia_game_random_positive_record_time";
    private static final String NUBIA_GAME_RANDOM_POSITIVE_RECORD_TIME_DEFAULT_VALUE = "0";
    private static final String REDMAGIC_HIGH_WHITE_LIST = "redmagic_high_white_list";
    private static final String RESOURCE_PRE_DOWNLOAD_WHITE_LIST = "resource_pre_download_white_list";
    private static final String TAG = "FunctionAllocationHelper";
    private static final String TP_GAME_PARTITION_WHITE_LIST = "tp_game_partition_white_list";
    private static FunctionAllocationHelper mInstance;
    private static final String GAME_HIGH_LIGHTS_MENU_CONFIG_VALUE = ControlPanelFeatureHelper.getZteFeatureGameHighLightsMenuConfig();
    private static final boolean ONLY_SUPPORT_MANUAL_RECORD = ControlPanelFeatureHelper.getOnlySupportManualRecord();
    private static final boolean ONLY_SUPPORT_RANDOM_MANUAL_RECORD = ControlPanelFeatureHelper.getZtFeatureGameRandomRecord().booleanValue();
    private static final ArrayList<String> RECORD_DIE_LIST = new ArrayList<>();
    private static final ArrayList<String> DB_PACKAGENAME_PRES = new ArrayList<>();
    private static final ArrayList<String> DB_KEY_PRES = new ArrayList<>();
    private static final ArrayList<String> OLD_DB_PACKAGENAME_PRES = new ArrayList<String>() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper.1
        {
            add(HighLightsUtils.WZRY_PACKAGE_NAME);
            add(HighLightsUtils.CJZC_PACKAGE_NAME);
            add(HighLightsUtils.PUBG_PACKAGE_NAME);
            add(HighLightsUtils.LOL_PACKAGE_NAME);
            add("com.tencent.tmgp.cf");
        }
    };
    private static final ArrayList<String> OLD_RECORD_DIE_LIST = new ArrayList<String>() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper.2
        {
            add(HighLightsUtils.WZRY_PACKAGE_NAME);
            add(HighLightsUtils.LOL_PACKAGE_NAME);
        }
    };
    private static final ArrayList<String> OLD_DB_KEY_PRES = new ArrayList<String>() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper.3
        {
            add("_wzry");
            add("_hpjy");
            add("_pubg");
            add("_lol");
            add("_cf");
        }
    };
    private static final ArrayList<String> SUPPORT_RESOURCE_PRE_DOWNLOAD_WHITE_LIST = new ArrayList() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper.4
        {
            add(HighLightsUtils.WZRY_PACKAGE_NAME);
            add(HighLightsUtils.CJZC_PACKAGE_NAME);
        }
    };

    public FunctionAllocationHelper() {
        initData();
    }

    private String buildSaveValue(Context context, String str, String str2, String str3) {
        if (str2 == null || str2.isEmpty()) {
            return str + "_" + str3 + ",";
        }
        if (str2.contains(str)) {
            String[] split = str2.split(",");
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str4 = split[i];
                if (!TextUtils.isEmpty(str4) && str4.contains(str)) {
                    str2 = str2.replace(str4, str + "_" + str3);
                    break;
                }
                i++;
            }
        } else {
            str2 = str2 + str + "_" + str3 + ",";
        }
        LogUtil.d(TAG, "buildSaveValue new dbValue = " + str2);
        return str2;
    }

    private String buildValue(Context context, String str, int i) {
        String string = Settings.Global.getString(context.getContentResolver(), GAME_TGPA_PREDOWNLOAD_SETTINGS_KEY);
        if (TextUtils.isEmpty(string)) {
            string = str + ":" + i + ",";
        } else if (string.contains(str)) {
            String[] split = string.split(",");
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str2 = split[i2];
                if (!TextUtils.isEmpty(str2) && str2.contains(str)) {
                    string = string.replace(str2, str + ":" + i);
                    break;
                }
                i2++;
            }
        } else {
            string = string + str + ":" + i + ",";
        }
        LogUtil.d(TAG, " buildValue new dbValue = " + string);
        return string;
    }

    public static FunctionAllocationHelper getInstance() {
        if (mInstance == null) {
            mInstance = new FunctionAllocationHelper();
        }
        return mInstance;
    }

    private void initData() {
        ArrayList<String> arrayList = RECORD_DIE_LIST;
        arrayList.clear();
        ArrayList<String> arrayList2 = DB_KEY_PRES;
        arrayList2.clear();
        ArrayList<String> arrayList3 = DB_PACKAGENAME_PRES;
        arrayList3.clear();
        StringBuilder sb = new StringBuilder(" initData GAME_HIGH_LIGHTS_MENU_CONFIG_VALUE = ");
        String str = GAME_HIGH_LIGHTS_MENU_CONFIG_VALUE;
        StringBuilder append = sb.append(str).append(";; ONLY_SUPPORT_MANUAL_RECORD = ");
        boolean z = ONLY_SUPPORT_MANUAL_RECORD;
        LogUtil.i(TAG, append.append(z).toString());
        if (z) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            arrayList.addAll(OLD_RECORD_DIE_LIST);
            arrayList2.addAll(OLD_DB_KEY_PRES);
            arrayList3.addAll(OLD_DB_PACKAGENAME_PRES);
            return;
        }
        if (str.contains("+")) {
            String substring = str.substring(str.indexOf("+") + 1);
            LogUtil.d(TAG, " initData tempGameHighLight = " + substring);
            try {
                for (String str2 : substring.split(",")) {
                    LogUtil.d(TAG, " initData value = " + str2);
                    String[] split = str2.split("_");
                    LogUtil.d(TAG, " initData pkgName = " + split[0] + " , key = " + split[1] + " , supportRecordDie = " + split[2]);
                    DB_PACKAGENAME_PRES.add(split[0]);
                    DB_KEY_PRES.add("_" + split[1]);
                    if (Integer.parseInt(split[2]) == 1) {
                        RECORD_DIE_LIST.add(split[0]);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                LogUtil.e(TAG, " initData happens exception :  ", e);
            }
        }
    }

    public ArrayList<String> getDbKeyPres() {
        return DB_KEY_PRES;
    }

    public ArrayList<String> getDbPackageNamePres() {
        return DB_PACKAGENAME_PRES;
    }

    public String getNubiaGameRandomBackRecordTime(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), NUBIA_GAME_RANDOM_BACK_RECORD_TIME);
        LogUtil.d(TAG, "getNubiaGameRandomBackRecordTime ---- originalValue = " + string);
        if (!TextUtils.isEmpty(string) && string.contains(str)) {
            String[] split = string.split(",");
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str2 = split[i];
                if (str2.contains(str)) {
                    LogUtil.d(TAG, "getNubiaGameRandomBackRecordTime  value = " + str2);
                    String[] split2 = str2.split("_");
                    if (split2.length > 1) {
                        return split2[split2.length - 1];
                    }
                    LogUtil.e(TAG, "Invalid format for value: " + str2);
                } else {
                    i++;
                }
            }
        }
        return NUBIA_GAME_RANDOM_BACK_RECORD_TIME_DEFAULT_VALUE;
    }

    public String getNubiaGameRandomPositiveRecordTime(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), NUBIA_GAME_RANDOM_POSITIVE_RECORD_TIME);
        LogUtil.d(TAG, "getNubiaGameRandomPositiveRecordTime ---- originalValue = " + string);
        if (string != null && string.contains(str)) {
            for (String str2 : string.split(",")) {
                if (str2.contains(str)) {
                    String[] split = str2.split("_");
                    if (split.length > 1) {
                        String str3 = split[split.length - 1];
                        LogUtil.d(TAG, "getNubiaGameRandomPositiveRecordTime  value = " + str2);
                        return str3;
                    }
                }
            }
        }
        return "0";
    }

    public void getRedmagicHighWhiteList(Context context) {
        Settings.Global.putString(context.getContentResolver(), REDMAGIC_HIGH_WHITE_LIST, DB_PACKAGENAME_PRES.toString());
    }

    public int getResourcePreDownloadSwitchByCurPkg(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), GAME_TGPA_PREDOWNLOAD_SETTINGS_KEY);
        if (TextUtils.isEmpty(string) || !string.contains(str)) {
            return 0;
        }
        int i = 0;
        for (String str2 : string.split(",")) {
            if (str2.contains(str)) {
                i = Integer.parseInt(str2.split(":")[1]);
            }
        }
        return i;
    }

    public void initResourcePreDownloadWhiteList(Context context) {
        Settings.Global.putString(context.getContentResolver(), RESOURCE_PRE_DOWNLOAD_WHITE_LIST, SUPPORT_RESOURCE_PRE_DOWNLOAD_WHITE_LIST.toString());
    }

    public void initTpGameWhiteList(Context context) {
        Settings.Global.putString(context.getContentResolver(), TP_GAME_PARTITION_WHITE_LIST, FeatureUtil.get(ControlPanelFeatureHelper.ZTE_FEATURE_TP_GAME_PARTITION_SUPPORT_GAMES, ""));
    }

    public boolean isOnlySupportManualRecord() {
        return ONLY_SUPPORT_MANUAL_RECORD;
    }

    public boolean isOnlySupportRandomManualRecord() {
        return ONLY_SUPPORT_RANDOM_MANUAL_RECORD;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
    
        if (2 == java.lang.Integer.parseInt(r6)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
    
        if (cn.nubia.common.util.CommonUtil.isRedMagicLegacyProject() == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isRedMagicDevice() {
        /*
            r6 = this;
            java.lang.String r6 = cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper.GAME_HIGH_LIGHTS_MENU_CONFIG_VALUE
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 1
            java.lang.String r2 = "FunctionAllocationHelper"
            r3 = 0
            if (r0 == 0) goto L19
            boolean r6 = cn.nubia.common.util.CommonUtil.isRedMagicRunOnMyOs()
            if (r6 != 0) goto L56
            boolean r6 = cn.nubia.common.util.CommonUtil.isRedMagicLegacyProject()
            if (r6 == 0) goto L55
            goto L56
        L19:
            java.lang.String r0 = "+"
            boolean r4 = r6.contains(r0)
            if (r4 == 0) goto L55
            int r0 = r6.indexOf(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = " isRedMagicDevice firstIndex = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r2, r4)
            java.lang.String r6 = r6.substring(r3, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = " isRedMagicDevice supportRedMagicChar = "
            r0.<init>(r4)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r0 = r0.toString()
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r2, r0)
            r0 = 2
            int r6 = java.lang.Integer.parseInt(r6)
            if (r0 != r6) goto L55
            goto L56
        L55:
            r1 = r3
        L56:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "isRedMagicDevice: isRedMagicDevice = "
            r6.<init>(r0)
            java.lang.StringBuilder r6 = r6.append(r1)
            java.lang.String r6 = r6.toString()
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r2, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper.isRedMagicDevice():boolean");
    }

    public void saveNubiaGameRandomBackRecordTime(Context context, String str, String str2) {
        Settings.Global.putString(context.getContentResolver(), NUBIA_GAME_RANDOM_BACK_RECORD_TIME, buildSaveValue(context, str, Settings.Global.getString(context.getContentResolver(), NUBIA_GAME_RANDOM_BACK_RECORD_TIME), str2));
    }

    public void saveNubiaGameRandomPositiveRecordTime(Context context, String str, String str2) {
        Settings.Global.putString(context.getContentResolver(), NUBIA_GAME_RANDOM_POSITIVE_RECORD_TIME, buildSaveValue(context, str, Settings.Global.getString(context.getContentResolver(), NUBIA_GAME_RANDOM_POSITIVE_RECORD_TIME), str2));
    }

    public void saveResourcePreDownloadSwitchStatus(Context context, String str, int i) {
        Settings.Global.putString(context.getContentResolver(), GAME_TGPA_PREDOWNLOAD_SETTINGS_KEY, buildValue(context, str, i));
    }

    public Boolean supportGameHighLight() {
        return Boolean.valueOf(ControlPanelFeatureHelper.getZteFeatureGameHighLights().booleanValue() || CommonUtil.isRedMagicLegacyProject());
    }

    public boolean supportGameHighLight(String str) {
        boolean contains = DB_PACKAGENAME_PRES.contains(str);
        LogUtil.d(TAG, "supportGameHighLight: support = " + contains);
        return contains;
    }

    public boolean supportRecordDie(String str) {
        boolean contains = RECORD_DIE_LIST.contains(str);
        LogUtil.d(TAG, " supportRecordDie = " + contains);
        return contains;
    }

    public Boolean supportResourcePreDownload() {
        return ControlPanelFeatureHelper.getZteFeatureMtgpaPredownload();
    }

    public boolean supportResourcePreDownloadByCurPkg(String str) {
        return SUPPORT_RESOURCE_PRE_DOWNLOAD_WHITE_LIST.contains(str);
    }
}
