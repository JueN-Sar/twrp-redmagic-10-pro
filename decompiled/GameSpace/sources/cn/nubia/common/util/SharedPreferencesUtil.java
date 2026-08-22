package cn.nubia.common.util;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class SharedPreferencesUtil {
    public static final String CHARGE_SEPARATION_OPEN_TIME = "charge_separation_open_time";
    public static final String CHARGE_SEPARATION_WARNING_FLAG = "charge_separation_warning_flag";
    public static final String GAME_CC_CUSTOM_TILES = "cc_custom_tiles";
    public static final String GAME_FIRST_START_APP = "firstStartApp";
    public static final String GAME_FIRST_START_GAMEKRY = "firstStartGameKey";
    public static final String GAME_FIRST_SWITCH = "firstSwitch";
    public static final String REDMAGIC_BROADCST_WARNING_FLAG = "red_magic_broadcast_warning_flag";
    public static final String SHARED_PREFERENCES_NAME = "data";
    public static final String UPGRADE_APK_PATH = "apk_path";
    public static final String UPGRADE_CHECK_DAY = "check_day";
    public static final String UPGRADE_IGNORE_VERSIONDATA = "ignore_version_code";
    public static final String UPGRADE_INSTALL_VERSION_CODE = "install_version_code";
    public static final String UPGRADE_NEW_VERSION_FLAG = "new_version_flag";
    public static final String UPGRADE_PAUSED_TYPE = "paused_type";
    public static final String UPGRADE_PROGRESS = "download_progress";
    public static final String UPGRADE_STATE = "self_upgrade_state";
    public static final String UPGRADE_VERSION_DATA = "version_data";
    public static final String UPGRADE_WIFI_ONLY = "wifi_only";
    private static Context mContext;
    private final String GAME_NOTICE_ID = "notice_id";
    private final String WALLPAPER_TYPE = "wallpaper_type";

    private static class SharedPreferencesUtilHolder {
        public static final SharedPreferencesUtil INSTANCE = new SharedPreferencesUtil();

        private SharedPreferencesUtilHolder() {
        }
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (mContext == null) {
            mContext = context.getApplicationContext();
        }
        return SharedPreferencesUtilHolder.INSTANCE;
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences("data", 0);
    }

    private void setData(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public String getApkPath() {
        return getData(UPGRADE_APK_PATH, null);
    }

    public long getChargeSeparationOpenTime() {
        return getSharedPreferences().getLong(CHARGE_SEPARATION_OPEN_TIME, 0L);
    }

    public boolean getChargeSeparationWarningFlag() {
        return getSharedPreferences().getBoolean(CHARGE_SEPARATION_WARNING_FLAG, true);
    }

    public String getData(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public String getGameCcCustomTiles() {
        return getData(GAME_CC_CUSTOM_TILES, null);
    }

    public String getIgnoreVersionCode() {
        return getData(UPGRADE_IGNORE_VERSIONDATA, null);
    }

    public String getInstallVersionCode() {
        return getData(UPGRADE_INSTALL_VERSION_CODE, null);
    }

    public boolean getIsWifiOnly() {
        return getSharedPreferences().getBoolean(UPGRADE_WIFI_ONLY, true);
    }

    public int getLastCheckUpgradeDay() {
        return getSharedPreferences().getInt(UPGRADE_CHECK_DAY, 0);
    }

    public int getLastGameNoticeId() {
        return mContext.getSharedPreferences("data", 0).getInt("notice_id", -1);
    }

    public boolean getNewVersionFlag() {
        return getSharedPreferences().getBoolean(UPGRADE_NEW_VERSION_FLAG, false);
    }

    public boolean getPausedType() {
        return getSharedPreferences().getBoolean(UPGRADE_PAUSED_TYPE, true);
    }

    public int getProgress() {
        return getSharedPreferences().getInt(UPGRADE_PROGRESS, 0);
    }

    public boolean getRedMagicBroadcastWarningFlag() {
        return getSharedPreferences().getBoolean(REDMAGIC_BROADCST_WARNING_FLAG, true);
    }

    public int getUpgradeState() {
        return getSharedPreferences().getInt(UPGRADE_STATE, 0);
    }

    public String getVersionData() {
        return getData(UPGRADE_VERSION_DATA, null);
    }

    public int getWallpaperId(int i) {
        return mContext.getSharedPreferences("data", 0).getInt("wallpaper_type", i);
    }

    public boolean isFirstStartApp() {
        return mContext.getSharedPreferences("data", 0).getBoolean(GAME_FIRST_START_APP, true);
    }

    public boolean isFirstStartGameKey() {
        return mContext.getSharedPreferences("data", 0).getBoolean(GAME_FIRST_START_GAMEKRY, true);
    }

    public int isFirstSwitch() {
        return mContext.getSharedPreferences("data", 0).getInt(GAME_FIRST_SWITCH, 0);
    }

    public void setApkPath(String str) {
        setData(UPGRADE_APK_PATH, str);
    }

    public void setChargeSeparationOpenTime(long j) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putLong(CHARGE_SEPARATION_OPEN_TIME, j);
        edit.apply();
    }

    public void setChargeSeparationWarningFlag(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(CHARGE_SEPARATION_WARNING_FLAG, z);
        edit.apply();
    }

    public void setFirstStartGameKey(boolean z) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences("data", 0).edit();
        edit.putBoolean(GAME_FIRST_START_GAMEKRY, z);
        edit.apply();
    }

    public void setFirstStartValue() {
        SharedPreferences.Editor edit = mContext.getSharedPreferences("data", 0).edit();
        edit.putBoolean(GAME_FIRST_START_APP, false);
        edit.apply();
    }

    public void setFirstSwitchValue() {
        SharedPreferences.Editor edit = mContext.getSharedPreferences("data", 0).edit();
        edit.putInt(GAME_FIRST_SWITCH, 1);
        edit.apply();
    }

    public void setGameCcCustomTiles(String str) {
        setData(GAME_CC_CUSTOM_TILES, str);
    }

    public void setGameNoticeId(int i) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences("data", 0).edit();
        edit.putInt("notice_id", i);
        edit.apply();
    }

    public void setIgnoreVersionCode(String str) {
        setData(UPGRADE_IGNORE_VERSIONDATA, str);
    }

    public void setInstallVersionCode(String str) {
        setData(UPGRADE_INSTALL_VERSION_CODE, str);
    }

    public void setIsWifiOnly(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(UPGRADE_WIFI_ONLY, z);
        edit.apply();
    }

    public void setLastCheckUpgradeDay(int i) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(UPGRADE_CHECK_DAY, i);
        edit.apply();
    }

    public void setNewVersionFlag(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(UPGRADE_NEW_VERSION_FLAG, z);
        edit.apply();
    }

    public void setPausedType(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(UPGRADE_PAUSED_TYPE, z);
        edit.apply();
    }

    public void setProgress(int i) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(UPGRADE_PROGRESS, i);
        edit.apply();
    }

    public void setRedMagicBroadcastWarningFlag(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(REDMAGIC_BROADCST_WARNING_FLAG, z);
        edit.apply();
    }

    public void setUpgradeState(int i) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(UPGRADE_STATE, i);
        edit.apply();
    }

    public void setVersionData(String str) {
        setData(UPGRADE_VERSION_DATA, str);
    }

    public void setWallpaperId(int i) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences("data", 0).edit();
        edit.putInt("wallpaper_type", i);
        edit.apply();
    }
}
