package cn.nubia.gamelauncher.gamecontrolpanel.performancetuning;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.CustomPerfProfile;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.AdapterHeadItem;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.AdapterItem;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import com.zte.performance.mindsync.MindSyncManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class CustomPerfProfileManager {
    public static final int CUSTOM_NORMAL_SERIAL_ID = 1;
    private static final int MAX_SERIAL = 12;
    public static final int NORMAL_SERIAL_ID = 0;
    private static final String TAG = "PerformanceProfileParser";
    private static CustomPerfProfileManager sInstance;
    private String mCurrentPkgName;
    private final Map<String, CustomPerfProfile.SettingItem> mNormalSettingMap = new HashMap();
    private final Map<String, Integer> mSettingGroupNameMap = buildGPGroupName2ResIdMap();
    private final Map<String, Integer> mSettingItemNameMap = buildGPDispName2ResIdMap();
    private final Map<String, Integer> mSettingItemTypeMap = buildGPGroupUITypeMap();
    private final List<CustomPerfProfile> mCustomPerfProfileList = new ArrayList();
    private final Map<String, Integer> mTipTextMap = buildTipTextMap();

    private CustomPerfProfileManager() {
    }

    private Map<String, Integer> buildGPDispName2ResIdMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("scaling_governor_li", Integer.valueOf(R.string.scaling_governor_li));
        Integer valueOf = Integer.valueOf(R.string.scaling_governor_big);
        hashMap.put("scaling_governor_big", valueOf);
        hashMap.put("scaling_governor_gold", valueOf);
        hashMap.put("scaling_governor_ti", Integer.valueOf(R.string.scaling_governor_ti));
        hashMap.put("scaling_governor_pri", Integer.valueOf(R.string.scaling_governor_pri));
        hashMap.put("cpu_freq_li", Integer.valueOf(R.string.cpu_freq_li));
        Integer valueOf2 = Integer.valueOf(R.string.cpu_freq_big);
        hashMap.put("cpu_freq_big", valueOf2);
        hashMap.put("cpu_freq_gold", valueOf2);
        hashMap.put("cpu_freq_ti", Integer.valueOf(R.string.cpu_freq_ti));
        hashMap.put("cpu_freq_pri", Integer.valueOf(R.string.cpu_freq_pri));
        hashMap.put("hispeed_load_li", Integer.valueOf(R.string.hispeed_load_li));
        Integer valueOf3 = Integer.valueOf(R.string.hispeed_load_big);
        hashMap.put("hispeed_load_big", valueOf3);
        hashMap.put("hispeed_load_gold", valueOf3);
        hashMap.put("hispeed_load_ti", Integer.valueOf(R.string.hispeed_load_ti));
        hashMap.put("hispeed_load_pri", Integer.valueOf(R.string.hispeed_load_pri));
        hashMap.put("hispeed_freq_li", Integer.valueOf(R.string.hispeed_feq_li));
        Integer valueOf4 = Integer.valueOf(R.string.hispeed_feq_big);
        hashMap.put("hispeed_freq_big", valueOf4);
        hashMap.put("hispeed_freq_gold", valueOf4);
        hashMap.put("hispeed_freq_ti", Integer.valueOf(R.string.hispeed_feq_ti));
        hashMap.put("hispeed_freq_pri", Integer.valueOf(R.string.hispeed_feq_pri));
        hashMap.put("cpu_num_cores_li", Integer.valueOf(R.string.cpu_num_cores_li));
        Integer valueOf5 = Integer.valueOf(R.string.cpu_num_cores_big);
        hashMap.put("cpu_num_cores_big", valueOf5);
        hashMap.put("cpu_num_cores_gold", valueOf5);
        hashMap.put("cpu_num_cores_ti", Integer.valueOf(R.string.cpu_num_cores_ti));
        hashMap.put("cpu_num_cores_pri", Integer.valueOf(R.string.cpu_num_cores_pri));
        hashMap.put("cpu_freq_pl_li", Integer.valueOf(R.string.cpu_freq_pl_li));
        Integer valueOf6 = Integer.valueOf(R.string.cpu_freq_pl_big);
        hashMap.put("cpu_freq_pl_big", valueOf6);
        hashMap.put("cpu_freq_pl_gold", valueOf6);
        hashMap.put("cpu_freq_pl_ti", Integer.valueOf(R.string.cpu_freq_pl_ti));
        hashMap.put("cpu_freq_pl_pri", Integer.valueOf(R.string.cpu_freq_pl_pri));
        hashMap.put("gpu_freq", Integer.valueOf(R.string.gpu_freq));
        hashMap.put("gpu_idle_time", Integer.valueOf(R.string.gpu_idle_time));
        hashMap.put("sched_updown_migrate_silver_gold", Integer.valueOf(R.string.sched_updown_migrate_silver_gold));
        hashMap.put("sched_updown_migrate_gold_prime", Integer.valueOf(R.string.sched_updown_migrate_gold_pri));
        hashMap.put("sched_updown_migrate_ti_prime", Integer.valueOf(R.string.sched_updown_migrate_ti_pri));
        hashMap.put("sched_group_updown_migrate", Integer.valueOf(R.string.sched_group_updown_migrate));
        hashMap.put("sched_cpu_top_app_uclamp_min", Integer.valueOf(R.string.sched_cpu_top_app_uclamp_min));
        hashMap.put("sched_cpuset_top_app_cpus", Integer.valueOf(R.string.sched_cpuset_top_app_cpus));
        hashMap.put("sched_cpuset_fg_cpus", Integer.valueOf(R.string.sched_cpuset_fg_cpus));
        hashMap.put("sched_cpuset_sys_bg_cpus", Integer.valueOf(R.string.sched_cpuset_sys_bg_cpus));
        hashMap.put("sched_cpuset_bg_cpus", Integer.valueOf(R.string.sched_cpuset_bg_cpus));
        hashMap.put("sched_boost", Integer.valueOf(R.string.sched_boost));
        hashMap.put("sched_min_task_util_for_boost", Integer.valueOf(R.string.sched_min_task_util_for_boost));
        hashMap.put("sched_min_task_util_for_colocation", Integer.valueOf(R.string.sched_min_task_util_for_colocation));
        hashMap.put("sched_many_wakeup_threshold", Integer.valueOf(R.string.sched_many_wakeup_threshold));
        hashMap.put("busdcvs_llcc_bwmon_min_freq", Integer.valueOf(R.string.busdcvs_llcc_bwmon_min_freq));
        hashMap.put("busdcvs_llcc_bwmon_gold_min_freq", Integer.valueOf(R.string.busdcvs_llcc_bwmon_gold_min_freq));
        hashMap.put("busdcvs_llcc_bwmon_prime_min_freq", Integer.valueOf(R.string.busdcvs_llcc_bwmon_prime_min_freq));
        hashMap.put("busdcvs_llcc_bwmon_io_percent", Integer.valueOf(R.string.busdcvs_llcc_bwmon_io_percent));
        hashMap.put("busdcvs_llcc_bwmon_gold_io_percent", Integer.valueOf(R.string.busdcvs_llcc_bwmon_gold_io_percent));
        hashMap.put("busdcvs_llcc_bwmon_prime_io_percent", Integer.valueOf(R.string.busdcvs_llcc_bwmon_prime_io_percent));
        hashMap.put("busdcvs_ddr_bwmon_min_freq", Integer.valueOf(R.string.busdcvs_ddr_bwmon_min_freq));
        hashMap.put("busdcvs_ddr_bwmon_io_percent", Integer.valueOf(R.string.busdcvs_ddr_bwmon_io_percent));
        hashMap.put("busdcvs_i3_silver_ipm_ceil", Integer.valueOf(R.string.busdcvs_i3_silver_ipm_ceil));
        hashMap.put("busdcvs_i3_gold_ipm_ceil", Integer.valueOf(R.string.busdcvs_i3_gold_ipm_ceil));
        hashMap.put("busdcvs_llcc_silver_ipm_ceil", Integer.valueOf(R.string.busdcvs_llcc_silver_ipm_ceil));
        hashMap.put("busdcvs_llcc_gold_ipm_ceil", Integer.valueOf(R.string.busdcvs_llcc_gold_ipm_ceil));
        hashMap.put("busdcvs_llcc_prime_ipm_ceil", Integer.valueOf(R.string.busdcvs_llcc_prime_ipm_ceil));
        hashMap.put("busdcvs_ddr_silver_ipm_ceil", Integer.valueOf(R.string.busdcvs_ddr_silver_ipm_ceil));
        hashMap.put("busdcvs_ddr_gold_ipm_ceil", Integer.valueOf(R.string.busdcvs_ddr_gold_ipm_ceil));
        hashMap.put("busdcvs_ddr_prime_ipm_ceil", Integer.valueOf(R.string.busdcvs_ddr_prime_ipm_ceil));
        hashMap.put("busdcvs_llcc_memlat_silver_min_freq", Integer.valueOf(R.string.busdcvs_llcc_memlat_silver_min_freq));
        hashMap.put("busdcvs_llcc_memlat_gold_min_freq", Integer.valueOf(R.string.busdcvs_llcc_memlat_gold_min_freq));
        hashMap.put("busdcvs_llcc_memlat_prime_min_freq", Integer.valueOf(R.string.busdcvs_llcc_memlat_prime_min_freq));
        hashMap.put("busdcvs_ddr_memlat_silver_min_freq", Integer.valueOf(R.string.busdcvs_ddr_memlat_silver_min_freq));
        hashMap.put("busdcvs_ddr_memlat_gold_min_freq", Integer.valueOf(R.string.busdcvs_ddr_memlat_gold_min_freq));
        hashMap.put("busdcvs_ddr_memlat_prime_min_freq", Integer.valueOf(R.string.busdcvs_ddr_memlat_prime_min_freq));
        hashMap.put("block_sda_read_ahead_kb", Integer.valueOf(R.string.block_sda_read_ahead_kb));
        hashMap.put("ufs_freq_lock_to_hi", Integer.valueOf(R.string.ufs_freq_lock_to_hi));
        return hashMap;
    }

    private Map<String, Integer> buildGPGroupName2ResIdMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("gp_group_scaling_governor", Integer.valueOf(R.string.gp_group_scaling_governor));
        hashMap.put("gp_group_cpu_freq", Integer.valueOf(R.string.gp_group_cpu_freq));
        hashMap.put("gp_group_cpu_highspeed_load", Integer.valueOf(R.string.gp_group_cpu_highspeed_load));
        hashMap.put("gp_group_cpu_highspeed_freq", Integer.valueOf(R.string.gp_group_cpu_highspeed_freq));
        hashMap.put("gp_group_cpu_cpus", Integer.valueOf(R.string.gp_group_cpu_cpus));
        hashMap.put("gp_group_cpu_freq_pl", Integer.valueOf(R.string.gp_group_cpu_freq_pl));
        hashMap.put("gp_group_gpu", Integer.valueOf(R.string.gp_group_gpu));
        hashMap.put("gp_group_sched_migrate", Integer.valueOf(R.string.gp_group_sched_migrate));
        hashMap.put("gp_group_sched_uclamp", Integer.valueOf(R.string.gp_group_sched_uclamp));
        hashMap.put("gp_group_sched_cpuset", Integer.valueOf(R.string.gp_group_sched_cpuset));
        hashMap.put("gp_group_sched_related", Integer.valueOf(R.string.gp_group_sched_related));
        hashMap.put("gp_group_io_llcc", Integer.valueOf(R.string.gp_group_io_llcc));
        hashMap.put("gp_group_io_ipm", Integer.valueOf(R.string.gp_group_io_ipm));
        hashMap.put("gp_group_io_cache_bus_min_freq", Integer.valueOf(R.string.gp_group_io_cache_bus_min_freq));
        hashMap.put("gp_io_fs", Integer.valueOf(R.string.gp_io_fs));
        return hashMap;
    }

    private Map<String, Integer> buildGPGroupUITypeMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("gp_group_scaling_governor", 3);
        hashMap.put("gp_group_cpu_freq", 4);
        hashMap.put("gp_group_cpu_highspeed_load", 4);
        hashMap.put("gp_group_cpu_highspeed_freq", 4);
        hashMap.put("gp_group_cpu_cpus", 4);
        hashMap.put("gp_group_cpu_freq_pl", 3);
        hashMap.put("gp_group_gpu", 4);
        hashMap.put("gp_group_sched_migrate", 5);
        hashMap.put("gp_group_sched_uclamp", 4);
        hashMap.put("gp_group_sched_cpuset", 4);
        hashMap.put("gp_group_sched_related", 4);
        hashMap.put("gp_group_io_llcc", 4);
        hashMap.put("gp_group_io_ipm", 4);
        hashMap.put("gp_group_io_cache_bus_min_freq", 4);
        hashMap.put("gp_io_fs", 4);
        hashMap.put("ufs_freq_lock_to_hi", 3);
        return hashMap;
    }

    private Map<String, Integer> buildTipTextMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("gp_group_scaling_governor", Integer.valueOf(R.string.gp_group_scaling_governor_tip));
        hashMap.put("gp_group_cpu_freq", Integer.valueOf(R.string.gp_group_cpu_freq_tip));
        hashMap.put("gp_group_cpu_highspeed_load", Integer.valueOf(R.string.gp_group_cpu_highspeed_load_tip));
        hashMap.put("gp_group_cpu_highspeed_freq", Integer.valueOf(R.string.gp_group_cpu_highspeed_freq_tip));
        hashMap.put("gp_group_cpu_cpus", Integer.valueOf(R.string.gp_group_cpu_cpus_tip));
        hashMap.put("gp_group_cpu_freq_pl", Integer.valueOf(R.string.gp_group_cpu_freq_pl_tip));
        hashMap.put("gpu_freq", Integer.valueOf(R.string.gpu_freq_tip));
        hashMap.put("gpu_idle_time", Integer.valueOf(R.string.gpu_idle_time_tip));
        hashMap.put("gp_group_sched_migrate", Integer.valueOf(R.string.gp_group_sched_migrate_tip));
        hashMap.put("gp_group_sched_uclamp", Integer.valueOf(R.string.gp_group_sched_uclamp_tip));
        hashMap.put("gp_group_sched_cpuset", Integer.valueOf(R.string.gp_group_sched_cpuset_tip));
        hashMap.put("sched_boost", Integer.valueOf(R.string.sched_boost_tip));
        hashMap.put("sched_min_task_util_for_boost", Integer.valueOf(R.string.sched_min_task_util_for_boost_tip));
        hashMap.put("sched_min_task_util_for_colocation", Integer.valueOf(R.string.sched_min_task_util_for_colocation_tip));
        hashMap.put("sched_many_wakeup_threshold", Integer.valueOf(R.string.sched_many_wakeup_threshold_tip));
        hashMap.put("gp_group_io_llcc", Integer.valueOf(R.string.gp_group_io_llcc_tip));
        hashMap.put("gp_group_io_ipm", Integer.valueOf(R.string.gp_group_io_ipm_tip));
        hashMap.put("gp_group_io_cache_bus_min_freq", Integer.valueOf(R.string.gp_group_io_cache_bus_min_freq_tip));
        hashMap.put("block_sda_read_ahead_kb", Integer.valueOf(R.string.block_sda_read_ahead_kb_tip));
        hashMap.put("ufs_freq_lock_to_hi", Integer.valueOf(R.string.ufs_freq_lock_to_hi_tip));
        return hashMap;
    }

    private String deParseCpuSetBitmask(String str) {
        int i = 0;
        for (String str2 : str.split(" , ")) {
            String[] split = str2.split(" - ");
            if (split.length == 2) {
                for (int parseInt = parseInt(split[0]); parseInt <= parseInt(split[1]); parseInt++) {
                    i |= 1 << parseInt;
                }
            }
        }
        return String.valueOf(i);
    }

    private String deParseMigrateBitmask(String str) {
        Matcher matcher = Pattern.compile("(\\d+)\\D*(\\d+)").matcher(str);
        if (!matcher.find()) {
            return "";
        }
        return Integer.toHexString(Integer.parseInt(matcher.group(2)) | (Integer.parseInt(matcher.group(1)) << 16));
    }

    private void deleteProfileFromList(int i) {
        if (getProfileList() != null) {
            for (int i2 = 0; i2 < getProfileList().size(); i2++) {
                if (getProfileList().get(i2).getSerial() == i) {
                    getProfileList().remove(i2);
                }
            }
        }
    }

    private void displayList2ValueList(String str, CustomPerfProfile.SettingItem settingItem) {
        Iterator<String> it = settingItem.getValueList().iterator();
        while (it.hasNext()) {
            if (!it.next().endsWith("k")) {
                if (TextUtils.equals(str, "gp_group_sched_cpuset")) {
                    settingItem.setValue(deParseCpuSetBitmask(settingItem.getValue()));
                    if (settingItem.isScoped()) {
                        settingItem.setValue2(deParseCpuSetBitmask(settingItem.getValue2()));
                    }
                    ArrayList arrayList = new ArrayList();
                    Iterator<String> it2 = settingItem.getValueList().iterator();
                    while (it2.hasNext()) {
                        arrayList.add(deParseCpuSetBitmask(it2.next()));
                    }
                    settingItem.setValueList(arrayList);
                    return;
                }
                if (TextUtils.equals(str, "gp_group_sched_migrate")) {
                    settingItem.setValue(deParseMigrateBitmask(settingItem.getValue()));
                    if (settingItem.isScoped()) {
                        settingItem.setValue2(deParseMigrateBitmask(settingItem.getValue2()));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<String> it3 = settingItem.getValueList().iterator();
                    while (it3.hasNext()) {
                        arrayList2.add(deParseMigrateBitmask(it3.next()));
                    }
                    settingItem.setValueList(arrayList2);
                    return;
                }
                return;
            }
        }
        settingItem.setValue(replaceKTo000(settingItem.getValue()));
        if (settingItem.isScoped()) {
            settingItem.setValue2(replaceKTo000(settingItem.getValue2()));
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator<String> it4 = settingItem.getValueList().iterator();
        while (it4.hasNext()) {
            arrayList3.add(replaceKTo000(it4.next()));
        }
        settingItem.setValueList(arrayList3);
    }

    private Parcelable generateSettingItem(CustomPerfProfile.SettingItem settingItem) {
        try {
            Object newInstance = MindSyncManager.GamePerfSettingListItem.class.getConstructor(String.class, String.class, String.class).newInstance(settingItem.getKey(), settingItem.getValue(), settingItem.getItemPath());
            Field field = MindSyncManager.GamePerfSettingListItem.class.getField("mValue2");
            Field field2 = MindSyncManager.GamePerfSettingListItem.class.getField("mIsScoped");
            field.set(newInstance, settingItem.getValue2());
            field2.set(newInstance, Boolean.valueOf(settingItem.isScoped()));
            return (Parcelable) newInstance;
        } catch (Exception e) {
            LogUtil.e(TAG, "generateSettingItem error = " + e);
            return null;
        }
    }

    public static CustomPerfProfileManager getInstance() {
        if (sInstance == null) {
            sInstance = new CustomPerfProfileManager();
        }
        return sInstance;
    }

    private String getItemPath(MindSyncManager.GamePerfSettingListItem gamePerfSettingListItem) {
        try {
            return (String) MindSyncManager.GamePerfSettingListItem.class.getField("mItemPath").get(gamePerfSettingListItem);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(TAG, "getItemPath error = " + e);
            return "";
        }
    }

    private String parseCpuSetBitmask(String str) {
        int parseInt = parseInt(str);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 12; i++) {
            if (((parseInt >> i) & 1) == 1) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (arrayList.isEmpty()) {
            return str;
        }
        if (arrayList.size() == 1) {
            return String.valueOf(arrayList.get(0));
        }
        StringBuilder sb = new StringBuilder();
        int intValue = ((Integer) arrayList.get(0)).intValue();
        for (int i2 = 1; i2 < arrayList.size(); i2++) {
            int i3 = i2 - 1;
            if (((Integer) arrayList.get(i2)).intValue() - ((Integer) arrayList.get(i3)).intValue() > 1) {
                sb.append(intValue);
                sb.append(" - ");
                sb.append(arrayList.get(i3));
                if (i2 != arrayList.size() - 1) {
                    sb.append(" , ");
                }
                intValue = ((Integer) arrayList.get(i2)).intValue();
            } else if (i2 == arrayList.size() - 1) {
                sb.append(intValue);
                sb.append(" - ");
                sb.append(arrayList.get(i2));
            }
        }
        return sb.toString();
    }

    private int parseHexInt(String str) {
        try {
            return Integer.parseInt(str, 16);
        } catch (Exception unused) {
            return 0;
        }
    }

    private int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    private String parseMigrateBitmask(String str) {
        int parseHexInt = parseHexInt(str);
        return String.format(GameLauncherApplication.getAppContext().getString(R.string.custom_perf_display_migrate), Integer.valueOf((parseHexInt >> 16) & 255), Integer.valueOf(parseHexInt & 255));
    }

    private void parseProfile() {
        int i;
        if (!this.mCustomPerfProfileList.isEmpty()) {
            this.mCustomPerfProfileList.clear();
            this.mNormalSettingMap.clear();
        }
        List gpGetAllUserConfig = MindSyncManager.Trigger.gpGetAllUserConfig();
        List<String> gpGetAllGroupNames = MindSyncManager.Trigger.gpGetAllGroupNames();
        Map<String, List<String>> gpGetDispName2ValueList = MindSyncManager.Trigger.gpGetDispName2ValueList();
        if (gpGetAllUserConfig == null || gpGetAllUserConfig.isEmpty() || gpGetAllGroupNames.isEmpty()) {
            LogUtil.e(TAG, "profileBundleList = " + gpGetAllUserConfig + " groupKeyList = " + gpGetAllGroupNames + " settingKey2ValueList = " + gpGetDispName2ValueList);
            return;
        }
        Iterator it = gpGetAllUserConfig.iterator();
        boolean z = false;
        Bundle bundle = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Bundle bundle2 = (Bundle) it.next();
            if (bundle2 != null && (i = bundle2.getInt("gp_user_serial", -1)) != -1) {
                Bundle bundle3 = i == 0 ? bundle2 : bundle;
                boolean z2 = i != 1 ? z : true;
                String string = bundle2.getString("gp_user_dispname", "");
                CustomPerfProfile customPerfProfile = new CustomPerfProfile();
                customPerfProfile.setSerial(i);
                customPerfProfile.setDisplayName(string);
                LogUtil.i(TAG, "serial = " + i + " name = " + string);
                parseSettingGroup(bundle2, i, customPerfProfile, gpGetAllGroupNames, gpGetDispName2ValueList);
                this.mCustomPerfProfileList.add(customPerfProfile);
                z = z2;
                bundle = bundle3;
            }
        }
        if (z || bundle == null) {
            return;
        }
        bundle.putInt("gp_user_serial", 1);
        saveProfile(bundle, this.mCurrentPkgName);
        String string2 = bundle.getString("gp_user_dispname", "");
        CustomPerfProfile customPerfProfile2 = new CustomPerfProfile();
        customPerfProfile2.setSerial(1);
        customPerfProfile2.setDisplayName(string2);
        parseSettingGroup(bundle, 1, customPerfProfile2, gpGetAllGroupNames, gpGetDispName2ValueList);
        this.mCustomPerfProfileList.add(1, customPerfProfile2);
    }

    private void parseSettingGroup(Bundle bundle, int i, CustomPerfProfile customPerfProfile, List<String> list, Map<String, List<String>> map) {
        ArrayList parcelableArrayList;
        List<String> list2;
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            Integer num = this.mSettingGroupNameMap.get(str);
            if (num != null && (parcelableArrayList = bundle.getParcelableArrayList(str)) != null && !parcelableArrayList.isEmpty()) {
                String string = GameLauncherApplication.getAppContext().getString(num.intValue());
                CustomPerfProfile.SettingGroup settingGroup = new CustomPerfProfile.SettingGroup();
                settingGroup.setKey(str);
                settingGroup.setName(string);
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < parcelableArrayList.size(); i2++) {
                    MindSyncManager.GamePerfSettingListItem gamePerfSettingListItem = (MindSyncManager.GamePerfSettingListItem) parcelableArrayList.get(i2);
                    Integer num2 = this.mSettingItemNameMap.get(gamePerfSettingListItem.mKey);
                    if (num2 != null && (list2 = map.get(gamePerfSettingListItem.mKey)) != null && !list2.isEmpty()) {
                        String string2 = GameLauncherApplication.getAppContext().getString(num2.intValue());
                        CustomPerfProfile.SettingItem settingItem = new CustomPerfProfile.SettingItem();
                        settingItem.setKey(gamePerfSettingListItem.mKey);
                        settingItem.setValue(gamePerfSettingListItem.mValue);
                        settingItem.setValue2(gamePerfSettingListItem.mValue2);
                        settingItem.setScoped(gamePerfSettingListItem.mIsScoped);
                        settingItem.setItemPath(getItemPath(gamePerfSettingListItem));
                        settingItem.setName(string2);
                        settingItem.setValueList(list2);
                        arrayList2.add(settingItem);
                        if (i == 0) {
                            this.mNormalSettingMap.put(settingItem.getKey(), settingItem);
                        }
                    }
                }
                settingGroup.setSettingList(arrayList2);
                LogUtil.i(TAG, "settingGroup = " + settingGroup);
                arrayList.add(settingGroup);
            }
        }
        customPerfProfile.setSettingGroupList(arrayList);
    }

    private String replace000ToK(String str) {
        return str.replaceAll("000$", "k");
    }

    private String replaceKTo000(String str) {
        return str.replaceAll("k$", "000");
    }

    private void saveProfile(Bundle bundle, String str) {
        MindSyncManager.Trigger.gpSaveUserConfig(bundle, str);
    }

    private void valueList2DisplayList(AdapterItem adapterItem) {
        Iterator<String> it = adapterItem.mValueList.iterator();
        while (it.hasNext()) {
            if (!it.next().endsWith("000")) {
                if (TextUtils.equals(adapterItem.mGroupKey, "gp_group_sched_cpuset")) {
                    adapterItem.mValue = parseCpuSetBitmask(adapterItem.mValue);
                    adapterItem.mNormalValue = parseCpuSetBitmask(adapterItem.mNormalValue);
                    if (adapterItem.mIsScoped) {
                        adapterItem.mValue2 = parseCpuSetBitmask(adapterItem.mValue2);
                        adapterItem.mNormalValue2 = parseCpuSetBitmask(adapterItem.mNormalValue2);
                    }
                    ArrayList arrayList = new ArrayList();
                    Iterator<String> it2 = adapterItem.mValueList.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(parseCpuSetBitmask(it2.next()));
                    }
                    adapterItem.mValueList = arrayList;
                    return;
                }
                if (TextUtils.equals(adapterItem.mGroupKey, "gp_group_sched_migrate")) {
                    adapterItem.mValue = parseMigrateBitmask(adapterItem.mValue);
                    adapterItem.mNormalValue = parseMigrateBitmask(adapterItem.mNormalValue);
                    if (adapterItem.mIsScoped) {
                        adapterItem.mValue2 = parseMigrateBitmask(adapterItem.mValue2);
                        adapterItem.mNormalValue2 = parseMigrateBitmask(adapterItem.mNormalValue2);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<String> it3 = adapterItem.mValueList.iterator();
                    while (it3.hasNext()) {
                        arrayList2.add(parseMigrateBitmask(it3.next()));
                    }
                    adapterItem.mValueList = arrayList2;
                    return;
                }
                return;
            }
        }
        adapterItem.mValue = replace000ToK(adapterItem.mValue);
        adapterItem.mNormalValue = replace000ToK(adapterItem.mNormalValue);
        if (adapterItem.mIsScoped) {
            adapterItem.mValue2 = replace000ToK(adapterItem.mValue2);
            adapterItem.mNormalValue2 = replace000ToK(adapterItem.mNormalValue2);
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator<String> it4 = adapterItem.mValueList.iterator();
        while (it4.hasNext()) {
            arrayList3.add(replace000ToK(it4.next()));
        }
        adapterItem.mValueList = arrayList3;
    }

    public void applyProfile(int i) {
        LogUtil.i(TAG, "applyProfile serial = " + i + " pkgName = " + this.mCurrentPkgName);
        MindSyncManager.Trigger.gpApplyUserConfigForPkg(i, this.mCurrentPkgName);
    }

    public List<AdapterItem> convert2AdapterItem(int i) {
        ArrayList arrayList = new ArrayList();
        AdapterHeadItem adapterHeadItem = new AdapterHeadItem();
        adapterHeadItem.mValueList = new ArrayList();
        adapterHeadItem.mSettingType = 1;
        adapterHeadItem.mIsScoped = false;
        arrayList.add(adapterHeadItem);
        for (CustomPerfProfile customPerfProfile : getProfileList()) {
            if (customPerfProfile.getSerial() != 0) {
                adapterHeadItem.mValueList.add(customPerfProfile.getDisplayName());
            }
            if (customPerfProfile.getSerial() == i) {
                adapterHeadItem.mName = customPerfProfile.getDisplayName();
                adapterHeadItem.mValue = String.valueOf(customPerfProfile.getSerial());
                if (customPerfProfile.getSettingGroupList() != null) {
                    for (CustomPerfProfile.SettingGroup settingGroup : customPerfProfile.getSettingGroupList()) {
                        AdapterItem adapterItem = new AdapterItem();
                        adapterItem.mSettingType = 2;
                        adapterItem.mKey = settingGroup.getKey();
                        adapterItem.mGroupKey = settingGroup.getKey();
                        adapterItem.mName = settingGroup.getName();
                        adapterItem.mIsScoped = false;
                        arrayList.add(adapterItem);
                        List<CustomPerfProfile.SettingItem> settingList = settingGroup.getSettingList();
                        if (settingList != null) {
                            for (int i2 = 0; i2 < settingList.size(); i2++) {
                                CustomPerfProfile.SettingItem settingItem = settingList.get(i2);
                                Integer num = this.mSettingItemTypeMap.get(settingItem.getKey());
                                if (num == null) {
                                    num = this.mSettingItemTypeMap.get(settingGroup.getKey());
                                }
                                if (num != null) {
                                    AdapterItem adapterItem2 = new AdapterItem();
                                    adapterItem2.mGroupKey = settingGroup.getKey();
                                    adapterItem2.mKey = settingItem.getKey();
                                    adapterItem2.mName = settingItem.getName();
                                    adapterItem2.mPath = settingItem.getItemPath();
                                    adapterItem2.mIsScoped = settingItem.isScoped();
                                    adapterItem2.mSettingType = num.intValue();
                                    adapterItem2.mValue = settingItem.getValue();
                                    adapterItem2.mValue2 = settingItem.getValue2();
                                    adapterItem2.mValueList = settingItem.getValueList();
                                    CustomPerfProfile.SettingItem settingItem2 = this.mNormalSettingMap.get(settingItem.getKey());
                                    if (settingItem2 != null) {
                                        adapterItem2.mNormalValue = settingItem2.getValue();
                                        adapterItem2.mNormalValue2 = settingItem2.getValue2();
                                    } else {
                                        adapterItem2.mNormalValue = adapterItem2.mValue;
                                        adapterItem2.mNormalValue2 = adapterItem2.mValue2;
                                    }
                                    valueList2DisplayList(adapterItem2);
                                    if (!TextUtils.equals(adapterItem2.mNormalValue, adapterItem2.mValue) || !TextUtils.equals(adapterItem2.mNormalValue2, adapterItem2.mValue2)) {
                                        adapterHeadItem.mSupportReset = true;
                                    }
                                    if (i2 == settingList.size() - 1) {
                                        adapterItem2.mGroupLastItem = true;
                                    }
                                    arrayList.add(adapterItem2);
                                }
                            }
                        }
                    }
                }
            }
        }
        AdapterItem adapterItem3 = new AdapterItem();
        adapterItem3.mSettingType = 6;
        arrayList.add(adapterItem3);
        return arrayList;
    }

    public CustomPerfProfile convert2Profile(List<AdapterItem> list) {
        CustomPerfProfile customPerfProfile = new CustomPerfProfile();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (AdapterItem adapterItem : list) {
            if (adapterItem.mSettingType != 6) {
                if (adapterItem.mSettingType == 1) {
                    customPerfProfile.setSerial(Integer.parseInt(adapterItem.mValue));
                    customPerfProfile.setDisplayName(adapterItem.mName);
                } else {
                    CustomPerfProfile.SettingGroup settingGroup = (CustomPerfProfile.SettingGroup) linkedHashMap.get(adapterItem.mGroupKey);
                    if (settingGroup == null) {
                        settingGroup = new CustomPerfProfile.SettingGroup();
                        settingGroup.setSettingList(new ArrayList());
                        linkedHashMap.put(adapterItem.mGroupKey, settingGroup);
                    }
                    if (adapterItem.mSettingType == 2) {
                        settingGroup.setKey(adapterItem.mGroupKey);
                        settingGroup.setName(adapterItem.mName);
                    } else {
                        CustomPerfProfile.SettingItem settingItem = new CustomPerfProfile.SettingItem();
                        settingItem.setKey(adapterItem.mKey);
                        settingItem.setName(adapterItem.mName);
                        settingItem.setItemPath(adapterItem.mPath);
                        settingItem.setValue(adapterItem.mValue);
                        settingItem.setValue2(adapterItem.mValue2);
                        settingItem.setScoped(adapterItem.mIsScoped);
                        settingItem.setValueList(adapterItem.mValueList);
                        displayList2ValueList(adapterItem.mGroupKey, settingItem);
                        settingGroup.getSettingList().add(settingItem);
                    }
                }
            }
        }
        customPerfProfile.setSettingGroupList(new ArrayList(linkedHashMap.values()));
        return customPerfProfile;
    }

    public String copyNewName(String str) {
        int lastIndexOf;
        int parseInt;
        int lastIndexOf2 = str.lastIndexOf("_");
        int i = 0;
        if (lastIndexOf2 > 0 && parseInt(str.substring(lastIndexOf2 + 1)) > 0) {
            str = str.substring(0, lastIndexOf2);
        }
        Iterator<CustomPerfProfile> it = getProfileList().iterator();
        while (it.hasNext()) {
            String displayName = it.next().getDisplayName();
            if (displayName.contains(str) && (lastIndexOf = displayName.lastIndexOf("_")) >= 0 && (parseInt = parseInt(displayName.substring(lastIndexOf + 1))) > i) {
                i = parseInt;
            }
        }
        return str + "_" + (i + 1);
    }

    public void deleteProfile(int i) {
        LogUtil.i(TAG, "deleteProfile serial = " + i);
        deleteProfileFromList(i);
        MindSyncManager.Trigger.gpDeleteUserConfig(i);
    }

    public void existEditProfile(List<AdapterItem> list) {
        LogUtil.i(TAG, "existEditProfile");
        if (list == null || list.isEmpty()) {
            MindSyncManager.Trigger.gpNotifyExitUserConfigUI();
        } else {
            saveProfile(convert2Profile(list));
            MindSyncManager.Trigger.gpNotifyExitUserConfigUI();
        }
    }

    public int getApplyProfile(String str) {
        return MindSyncManager.Trigger.gpGetUserConfigForPkg(str);
    }

    public String getHelpText() {
        StringBuilder sb = new StringBuilder();
        List<CustomPerfProfile.SettingGroup> settingGroupList = getProfileList().get(0).getSettingGroupList();
        for (int i = 0; i < settingGroupList.size(); i++) {
            CustomPerfProfile.SettingGroup settingGroup = settingGroupList.get(i);
            Integer num = this.mSettingGroupNameMap.get(settingGroup.getKey());
            if (num != null) {
                if (i != 0) {
                    sb.append("\n");
                }
                sb.append(i + 1);
                sb.append(". ");
                sb.append(GameLauncherApplication.getAppContext().getString(num.intValue()));
                Integer num2 = this.mTipTextMap.get(settingGroup.getKey());
                if (num2 != null) {
                    sb.append("\n     ");
                    sb.append(GameLauncherApplication.getAppContext().getString(num2.intValue()));
                }
                List<CustomPerfProfile.SettingItem> settingList = settingGroup.getSettingList();
                for (int i2 = 0; i2 < settingList.size(); i2++) {
                    CustomPerfProfile.SettingItem settingItem = settingList.get(i2);
                    Integer num3 = this.mSettingItemNameMap.get(settingItem.getKey());
                    Integer num4 = this.mTipTextMap.get(settingItem.getKey());
                    if (num4 != null && num3 != null) {
                        sb.append("\n     ");
                        sb.append(GameLauncherApplication.getAppContext().getString(num3.intValue()));
                        sb.append("：");
                        sb.append(GameLauncherApplication.getAppContext().getString(num4.intValue()));
                    }
                }
            }
        }
        return sb.toString();
    }

    public int getIdleSerial() {
        if (getProfileList().size() >= 12) {
            return -1;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < 12; i++) {
            hashSet.add(Integer.valueOf(i));
        }
        for (int i2 = 0; i2 < getProfileList().size(); i2++) {
            hashSet.remove(Integer.valueOf(getProfileList().get(i2).getSerial()));
        }
        if (hashSet.isEmpty()) {
            return -1;
        }
        return ((Integer) hashSet.iterator().next()).intValue();
    }

    public List<CustomPerfProfile> getProfileList() {
        if (this.mCustomPerfProfileList.isEmpty()) {
            parseProfile();
        }
        return this.mCustomPerfProfileList;
    }

    public int getSerialByName(String str) {
        for (CustomPerfProfile customPerfProfile : getProfileList()) {
            if (TextUtils.equals(customPerfProfile.getDisplayName(), str)) {
                int serial = customPerfProfile.getSerial();
                if (serial == 0) {
                    return 1;
                }
                return serial;
            }
        }
        return -1;
    }

    public boolean isNormalProfile(String str) {
        return TextUtils.equals(str, String.valueOf(1));
    }

    public void renameProfile(int i, String str) {
        LogUtil.i(TAG, "renameProfile serial = " + i + " newName = " + str);
        Iterator<CustomPerfProfile> it = getProfileList().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CustomPerfProfile next = it.next();
            if (next.getSerial() == i) {
                next.setDisplayName(str);
                break;
            }
        }
        MindSyncManager.Trigger.gpRenameUserConfig(i, str);
    }

    public void resetProfile(int i) {
        LogUtil.i(TAG, "resetProfile serial = " + i);
        MindSyncManager.Trigger.gpResetUserConfig(i);
        CustomPerfProfile customPerfProfile = getProfileList().get(i);
        if (customPerfProfile == null) {
            return;
        }
        Iterator<CustomPerfProfile.SettingGroup> it = customPerfProfile.getSettingGroupList().iterator();
        while (it.hasNext()) {
            for (CustomPerfProfile.SettingItem settingItem : it.next().getSettingList()) {
                CustomPerfProfile.SettingItem settingItem2 = this.mNormalSettingMap.get(settingItem.getKey());
                if (settingItem2 != null) {
                    settingItem.setValue(settingItem2.getValue());
                    settingItem.setValue2(settingItem2.getValue2());
                }
            }
        }
    }

    public void saveProfile(CustomPerfProfile customPerfProfile) {
        LogUtil.i(TAG, "saveProfile pkgName" + this.mCurrentPkgName + " serial = " + customPerfProfile.getSerial() + " name = " + customPerfProfile.getDisplayName());
        Iterator<CustomPerfProfile.SettingGroup> it = customPerfProfile.getSettingGroupList().iterator();
        while (it.hasNext()) {
            LogUtil.i(TAG, "settingGroup = " + it.next());
        }
        List<CustomPerfProfile> profileList = getProfileList();
        int i = 0;
        while (true) {
            if (i >= profileList.size()) {
                i = -1;
                break;
            } else if (profileList.get(i).getSerial() == customPerfProfile.getSerial()) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            profileList.add(customPerfProfile);
        } else {
            profileList.remove(i);
            profileList.add(i, customPerfProfile);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("gp_user_serial", customPerfProfile.getSerial());
        bundle.putString("gp_user_dispname", customPerfProfile.getDisplayName());
        for (CustomPerfProfile.SettingGroup settingGroup : customPerfProfile.getSettingGroupList()) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<CustomPerfProfile.SettingItem> it2 = settingGroup.getSettingList().iterator();
            while (it2.hasNext()) {
                arrayList.add(generateSettingItem(it2.next()));
            }
            bundle.putParcelableArrayList(settingGroup.getKey(), arrayList);
        }
        saveProfile(bundle, this.mCurrentPkgName);
    }

    public void setPackageName(String str) {
        this.mCurrentPkgName = str;
    }
}
