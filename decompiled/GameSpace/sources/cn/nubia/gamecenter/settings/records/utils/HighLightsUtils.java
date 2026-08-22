package cn.nubia.gamecenter.settings.records.utils;

import android.os.Build;
import android.os.SystemProperties;
import cn.nubia.gamecenter.settings.utils.Utils;

/* loaded from: classes.dex */
public class HighLightsUtils {
    public static final String APPADD_NAME = "gamename";
    public static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    public static final String ATTR_APP_NAME = "component";
    public static final String AUTO_FIRST = "001";
    public static final String AUTO_FOURTH = "004";
    public static final String AUTO_SECOND = "002";
    public static final String AUTO_THIRD = "003";
    public static final String CF_PACKAGE_NAME = "com.tencent.tmgp.cf";
    public static final String CJZC_APP_NAME = "和平精英";
    public static final String CJZC_PACKAGE_NAME = "com.tencent.tmgp.pubgmhd";
    public static final String CLOSE_GAME_KEY_VALUE = "0";
    public static final String GAME_CJZC = "/storage/emulated/0/红魔时刻/和平精英";
    public static final String GAME_LOL = "/storage/emulated/0/红魔时刻/英雄联盟";
    public static final String GAME_MANUAL = "/storage/emulated/0/红魔时刻/手动回录";
    public static final String GAME_PUBG = "/storage/emulated/0/红魔时刻/PUBGMOBILE";
    public static final String GAME_WZRY = "/storage/emulated/0/红魔时刻/王者荣耀";
    public static String GOOGLE_PHOTO_ACTIVITY = "com.google.android.apps.photos.home.HomeActivity";
    public static String GOOGLE_PHOTO_PACKAGE = "com.google.android.apps.photos";
    public static final String IMAGE_PATH = "/storage/emulated/0/Pictures/Redmagic Time Screenshot";
    public static final int IMAGE_TYPE = 1;
    public static final String INTERNAL_REDMAGIC_TIME_PATH = "/storage/emulated/0/Red Magic Moment";
    public static final String INTERNAL_WONDERFUL_TIME_PATH = "/storage/emulated/0/Wonderful Time";
    public static final String KEY_PACKAGE_NAME = "package_name";
    public static final int LIGHTS_TYPE = 3;
    public static final String LOL_APP_NAME = "英雄联盟手游";
    public static final String LOL_PACKAGE_NAME = "com.tencent.lolm";
    public static final String NEVER_DISPLAY_STORAGE_PERMISSION_DIALOG = "NEVER_DISPLAY_STORAGE_PERMISSION_DIALOG";
    public static final int NEW_PATH_ARRAY_LENGTH = 6;
    public static final int NIGHT_TEXT_COLOR = -5296333;
    public static final int NORMAL_COLUMN = 6;
    public static final int NORMAL_ITEM_WIDTH = 374;
    public static final int NORMAL_TOP_MARGIN = 60;
    public static final int NORMAL_WIDTH = 2400;
    public static final int NO_NIGHT_TEXT_COLOR = -3332565;
    public static final int NUBIA_TWIN_USERID = 9999;
    public static final String PACKAGE_WEIXIN = "com.tencent.mm";
    public static final int PANEL_ALL_TYPE = 0;
    public static final int PANEL_IMAGE_TYPE = 2;
    public static final int PANEL_LIGHTS_TYPE = 3;
    public static final int PANEL_VIDEO_TYPE = 1;
    public static final int POSITIVE_BUTTON = -1;
    public static final String PUBG_APP_NAME = "PUBG MOBILE";
    public static final String PUBG_PACKAGE_NAME = "com.tencent.ig";
    public static final String REDMAGIC_TIME_PATH = "/storage/emulated/0/红魔时刻";
    public static final int REQUEST_PERMISSION_EXTERNAL_STORAGE = 2777;
    public static final int RESET_DELAY_TIME = 500;
    public static final String SHORTCUT_LABEL = "label";
    public static final String SHORTCUT_LABEL_HASH = "hashcode";
    public static final int SMALLEST_TOP_MARGIN = -90;
    public static final String SMZH_PACKAGE_NAME = "com.tencent.tmgp.cod";
    public static final String STORAGE_PERMISSION_DIALOG_OPER = "storage_permission_dialog_oper";
    public static final String TRACK_GAME_NAME_KEY = "game_name";
    public static final String TRACK_SCREENSHOT_EVENT = "pers_center_redmagic_time_screenshots";
    public static final String TRACK_VIDEO_EVENT = "pers_center_redmagic_time_shots";
    public static final int TWIN_USERID = 999;
    public static final String URI_GAME_SPACE_SHORTCUT = "content://cn.nubia.gamelauncher.db.AppAddProvider/shortcut_adds?notify=false";
    public static final int VIDEO_TYPE = 2;
    public static final String WONDERFUL_TIME_PATH = "/storage/emulated/0/精彩时刻";
    public static final String WZRY_APP_NAME = "王者荣耀";
    public static final String WZRY_PACKAGE_NAME = "com.tencent.tmgp.sgame";
    public static final String XQTD_PACKAGE_NAME = "com.miHoYo.hkrpg";
    public static final String YS_PACKAGE_NAME = "com.miHoYo.Yuanshen";
    public static final String ZTE_FEATURE_ANTI_MISOPERATE_NUBIA = "ZTE_FEATURE_ANTI_MISOPERATE_NUBIA";
    public static final String ZTE_FEATURE_GAME_RANDOM_RECORD = "ZTE_FEATURE_GAME_RANDOM_RECORD";
    public static final String ZTE_FEATURE_MANUAL_RECORD_ONLY = "ZTE_FEATURE_MANUAL_RECORD_ONLY";
    public static final String ZTE_IMAGE_PATH = "/storage/emulated/0/Pictures/Game Space Screenshot";
    private static String ZTE_INTERNAL_KEY = "ro.vendor.mifavor.custom";
    private static String ZTE_INTERNAL_VALUE = "abroad";
    public static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE"};
    public static final String[] IMAGE_PATH_ARRAY = {"/storage/emulated/0/Pictures/Game Space Screenshot/%", "/storage/emulated/0/Pictures/Redmagic Time Screenshot/%"};
    public static final String[] VIDEO_PATH_ARRAY = {"/storage/emulated/0/红魔时刻/%", "/storage/emulated/0/精彩时刻/%"};
    public static final String[] INTERNAL_VIDEO_PATH_ARRAY = {"/storage/emulated/0/Red Magic Moment/%", "/storage/emulated/0/Wonderful Time/%"};

    public static String[] getImageDataPath() {
        return IMAGE_PATH_ARRAY;
    }

    public static String[] getInternalVideoPath() {
        return INTERNAL_VIDEO_PATH_ARRAY;
    }

    public static String[] getVideoDataPath() {
        return VIDEO_PATH_ARRAY;
    }

    public static boolean isAboveU() {
        return Build.VERSION.SDK_INT > 33 || "UpsideDownCake".equals(Build.VERSION.CODENAME);
    }

    public static boolean isInternal() {
        return Utils.isInternalVersion() || isZteInternal();
    }

    public static boolean isNP02J() {
        try {
            String str = SystemProperties.get("ro.product.name");
            if (str != null) {
                return str.contains("NP02J");
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isNubiaOS() {
        return "nubia".equals(SystemProperties.get("ro.build.user", "nubia"));
    }

    public static boolean isRedMagic() {
        try {
            return "RedMagic".equals(SystemProperties.get("ro.vendor.feature.brand.internal"));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isRedMagicPad() {
        try {
            return "CN_P898P02".equals(SystemProperties.get("ro.product.name"));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isZteInternal() {
        return ZTE_INTERNAL_VALUE.equals(SystemProperties.get(ZTE_INTERNAL_KEY, "0"));
    }
}
