package cn.nubia.gamelauncher.gamecontrolpanel.virtual.db;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.Constants;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.AppGameHandleItem;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.tgk.TgkHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class Utils {
    private static final String PREF_DATA_FILE_NAME = "virtual_game_handle_data";
    private static final String PREF_FIELD_FIRST_USED = "first_used";
    private static final String PREF_FIELD_PKG_OFFICIAL_REC_LIST = "pkg_official_rec_list";
    public static final String PREF_FIELD_PKG_OFFICIAL_REC_USED = "pkg_official_rec_used";
    private static final String TAG = "Utils";
    private static Method sGetTopPackages;
    private static boolean sIsFirstUsed;
    private static Object sNubiaSysState;
    private static String sOfficialRecPkgs;
    private static HashMap<String, Integer> mAppStateMap = new HashMap<>();
    public static final List<String> BLACK_PKG_LIST = Arrays.asList("com.android.settings", "com.android.systemui");

    public static void addEnabledPkg(ContentResolver contentResolver, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String string = Settings.Global.getString(contentResolver, Constants.PKG_ENABLED);
        if (string.contains(str + ",")) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(string)) {
            sb.append(string);
        }
        sb.append(str);
        sb.append(",");
        Settings.Global.putString(contentResolver, Constants.PKG_ENABLED, sb.toString());
    }

    public static void addUsedPkgForOfficialRec(Context context, String str) {
        if (TextUtils.isEmpty(str) || !getOfficialRecPkg(context).contains(str)) {
            return;
        }
        String string = context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).getString(PREF_FIELD_PKG_OFFICIAL_REC_USED, "");
        if (string.contains(str)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(string)) {
            sb.append(string);
        }
        sb.append(str);
        sb.append(",");
        context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).edit().putString(PREF_FIELD_PKG_OFFICIAL_REC_USED, sb.toString()).apply();
    }

    public static void clearAppState() {
        mAppStateMap.clear();
    }

    public static void clearEnabledPkg(ContentResolver contentResolver) {
        Settings.Global.putString(contentResolver, Constants.PKG_ENABLED, "");
    }

    public static void clearUsedOfficialRecPkg(Context context) {
        context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).edit().putString(PREF_FIELD_PKG_OFFICIAL_REC_USED, "").apply();
    }

    public static void deleteImage(String str, Context context) {
        if (str == null) {
            LogUtil.e(TAG, "delete image file name is null");
            return;
        }
        LogUtil.i(TAG, "delete image file name : " + str);
        try {
            context.deleteFile(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disableVirtualGameHandle(Context context) {
        setVirtualGameHandleEnable(context, false);
    }

    public static void enableVirtualGameHandle(Context context) {
        setVirtualGameHandleEnable(context, true);
    }

    public static String getAppLabel(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppState(String str) {
        Integer num = mAppStateMap.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static Typeface getAssetsTypeface(Context context, String str) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + str);
    }

    public static boolean getBundleFromAppGameHandleItem(AppGameHandleItem appGameHandleItem, Bundle bundle) {
        if (appGameHandleItem == null) {
            LogUtil.e(TAG, "game handle item is null");
            bundle.putBoolean("enable", false);
            return false;
        }
        bundle.putBoolean("enable", true);
        bundle.putString("_id", appGameHandleItem.getId());
        bundle.putString("package_name", appGameHandleItem.getPackageName());
        bundle.putInt(DBConstant.CUT_SIZE, appGameHandleItem.getCutSize());
        bundle.putString(DBConstant.LEFT_JOYSTICK, appGameHandleItem.getTargetInfo(0).toString());
        bundle.putString(DBConstant.RIGHT_JOYSTICK, appGameHandleItem.getTargetInfo(1).toString());
        bundle.putString(DBConstant.LEFT_ENTITY_KEY, appGameHandleItem.getTargetInfo(2).toString());
        bundle.putString(DBConstant.RIGHT_ENTITY_KEY, appGameHandleItem.getTargetInfo(3).toString());
        bundle.putString(DBConstant.UP_ARROW_KEY, appGameHandleItem.getTargetInfo(6).toString());
        bundle.putString(DBConstant.DOWN_ARROW_KEY, appGameHandleItem.getTargetInfo(7).toString());
        bundle.putString(DBConstant.LEFT_ARROW_KEY, appGameHandleItem.getTargetInfo(4).toString());
        bundle.putString(DBConstant.RIGHT_ARROW_KEY, appGameHandleItem.getTargetInfo(5).toString());
        bundle.putString(DBConstant.LETTER_A_KEY, appGameHandleItem.getTargetInfo(8).toString());
        bundle.putString(DBConstant.LETTER_A1_KEY, appGameHandleItem.getTargetInfo(9).toString());
        bundle.putString(DBConstant.LETTER_A2_KEY, appGameHandleItem.getTargetInfo(10).toString());
        bundle.putString(DBConstant.LETTER_B_KEY, appGameHandleItem.getTargetInfo(11).toString());
        bundle.putString(DBConstant.LETTER_X_KEY, appGameHandleItem.getTargetInfo(12).toString());
        bundle.putString(DBConstant.LETTER_Y_KEY, appGameHandleItem.getTargetInfo(13).toString());
        bundle.putString(DBConstant.LETTER_Z_KEY, appGameHandleItem.getTargetInfo(14).toString());
        bundle.putString(DBConstant.RIGHT_GAME_HANDLE_STYLE, appGameHandleItem.getRightGameHandleStyle());
        return true;
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat(SettingUtil.DATE_FORMAT).format(new Date());
    }

    public static String getEnabledPkg(ContentResolver contentResolver) {
        return Settings.Global.getString(contentResolver, Constants.PKG_ENABLED);
    }

    public static String getFileContent(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(str));
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            try {
                bufferedReader.close();
                return readLine;
            } catch (IOException e2) {
                e2.printStackTrace();
                return readLine;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedReader2 = bufferedReader;
            e.printStackTrace();
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return "-1";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String getFilterTitle(String str) {
        return Constants.WINDOW_TITLE_PREFIX + str;
    }

    public static String getHandleNumber(Context context, List<AppGameHandleItem> list) {
        if (list == null || list.size() == 0) {
            LogUtil.i(TAG, "handle item number is null");
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(getAppLabel(context, list.get(i).getPackageName()));
        }
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (hashMap.containsKey(arrayList.get(i2))) {
                hashMap.put((String) arrayList.get(i2), Integer.valueOf(((Integer) hashMap.get(arrayList.get(i2))).intValue() + 1));
            } else {
                hashMap.put((String) arrayList.get(i2), 1);
            }
        }
        LogUtil.i(TAG, "handle number : " + hashMap.size() + ", " + hashMap.toString());
        return hashMap.toString();
    }

    public static Bitmap getImage(String str, Context context) {
        Bitmap bitmap = null;
        if (str == null) {
            LogUtil.e(TAG, "get image file name is null");
            return null;
        }
        LogUtil.i(TAG, "get image file name : " + str);
        try {
            FileInputStream openFileInput = context.openFileInput(str);
            bitmap = BitmapFactory.decodeStream(openFileInput);
            openFileInput.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            LogUtil.i(TAG, "FileNotFoundException: " + e.toString());
            e.printStackTrace();
            return bitmap;
        } catch (IOException e2) {
            LogUtil.i(TAG, "FileNotFoundException: " + e2.toString());
            e2.printStackTrace();
            return bitmap;
        }
    }

    public static String getOfficialRecPkg(Context context) {
        if (TextUtils.isEmpty(sOfficialRecPkgs)) {
            sOfficialRecPkgs = context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).getString(PREF_FIELD_PKG_OFFICIAL_REC_LIST, isCommonVersion() ? TextUtils.join(",", Constants.COMMON_REC_PKGS) : "");
        }
        return sOfficialRecPkgs;
    }

    public static int getOffset(Context context, int i) {
        return context.getResources().getDimensionPixelOffset(i);
    }

    public static String getTopPkgName() {
        if (sNubiaSysState == null || sGetTopPackages == null) {
            initNubiaSysState();
        }
        return invokeGetTopPkgName();
    }

    public static String getUsedPkgForOfficialRec(Context context) {
        return context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).getString(PREF_FIELD_PKG_OFFICIAL_REC_USED, "");
    }

    public static int getValueInNode(String str, String str2, String str3, int i) {
        String readLine;
        if (!new File(str).exists()) {
            return i;
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                    do {
                        try {
                            readLine = bufferedReader2.readLine();
                            if (readLine != null && readLine.contains(str2)) {
                                for (String str4 : readLine.split(",")) {
                                    if (str4.contains(":")) {
                                        String[] split = str4.trim().split(":");
                                        if (split.length == 2 && split[0].trim().contains(str3)) {
                                            int intValue = Integer.valueOf(split[1].trim()).intValue();
                                            try {
                                                bufferedReader2.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            return intValue;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } while (!TextUtils.isEmpty(readLine));
                    bufferedReader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (Exception e5) {
                e = e5;
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<Bundle> getVisiblePackageDates() {
        if (sNubiaSysState == null || sGetTopPackages == null) {
            initNubiaSysState();
            if (sNubiaSysState == null || sGetTopPackages == null) {
                return null;
            }
        }
        try {
            return (List) sGetTopPackages.invoke(sNubiaSysState, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void initNubiaSysState() {
        try {
            Class<?> cls = Class.forName("android.app.NubiaSysState");
            sNubiaSysState = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            sGetTopPackages = cls.getDeclaredMethod(TgkHelper.METHOD_NAME_VISIBLE_PACKAGE, new Class[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String invokeGetTopPkgName() {
        List<Bundle> visiblePackageDates = getVisiblePackageDates();
        if (visiblePackageDates == null || visiblePackageDates.isEmpty()) {
            return null;
        }
        for (Bundle bundle : visiblePackageDates) {
            String string = bundle.getString("packageName");
            int i = bundle.getInt("windowMode");
            LogUtil.d(TAG, "invoke pkg name =" + string + " windowMode=" + i);
            if (i == 1) {
                LogUtil.i(TAG, "current pkg " + string);
                return string;
            }
        }
        return null;
    }

    public static boolean isCommonVersion() {
        String str;
        try {
            str = (String) Class.forName("android.util.NubiaConfig").getDeclaredMethod("getSubValue", new Class[0]).invoke("nubia_international_feature", "support_international_version");
        } catch (Exception unused) {
            str = null;
        }
        return !"true".equals(str);
    }

    public static boolean isExpandVision(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.EXPAND_VISION, 0) == 1;
    }

    public static boolean isFirstUsed(Context context) {
        if (sIsFirstUsed) {
            return true;
        }
        boolean equals = "true".equals(context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).getString(PREF_FIELD_FIRST_USED, ""));
        sIsFirstUsed = equals;
        return equals;
    }

    public static boolean isGameModeFloatingWindowShow(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "game_mode_floating_window_show", 0) == 1;
    }

    public static boolean isInLandscapeGame(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isKeyDisplayOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.KEY_DISPLAY_STATUS, 1) == 1;
    }

    public static boolean isOpenSuggestOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.OPEN_SUGGEST_STATUS, 0) == 1;
    }

    public static boolean isPortrait(int i) {
        return i == 0 || i == 2;
    }

    public static boolean isShakeFeedbackOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.SHAKE_FEEDBACK_STATUS, 1) == 1;
    }

    public static boolean isVirtualGameHandleEnable(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.VIRTUAL_GAME_HANDLE_ENABLE, 0) != 0;
    }

    public static boolean isVirtualGameHandleOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.VIRTUAL_GAME_HANDLE_ON, 0) != 0;
    }

    public static AppGameHandleItem.TargetInfo parseTargetRect(String str) {
        String[] split;
        AppGameHandleItem.TargetInfo targetInfo = new AppGameHandleItem.TargetInfo();
        if (!TextUtils.isEmpty(str) && (split = str.split("\\|")) != null && split.length == 5) {
            if ("true".equals(split[0])) {
                targetInfo.setEnable(true);
            } else {
                targetInfo.setEnable(false);
            }
            try {
                Rect rect = new Rect();
                rect.left = Integer.parseInt(split[1]);
                rect.top = Integer.parseInt(split[2]);
                rect.right = Integer.parseInt(split[3]);
                rect.bottom = Integer.parseInt(split[4]);
                targetInfo.setTargetRect(rect);
            } catch (NumberFormatException e) {
                LogUtil.e(TAG, e.toString());
            }
        }
        return targetInfo;
    }

    public static void removeAppState(String str) {
        mAppStateMap.remove(str);
    }

    public static void removeEnabledPkg(ContentResolver contentResolver, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.i(TAG, "pkg to be removed for enable is null");
            return;
        }
        String string = Settings.Global.getString(contentResolver, Constants.PKG_ENABLED);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        Settings.Global.putString(contentResolver, Constants.PKG_ENABLED, string.replaceAll(str + "\\,", ""));
    }

    public static void setAppState(String str, int i) {
        mAppStateMap.put(str, Integer.valueOf(i));
    }

    public static void setFirstUsed(Context context, boolean z) {
        context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).edit().putString(PREF_FIELD_FIRST_USED, z ? "true" : "false").apply();
    }

    public static void setOfficialRecPkg(Context context, String str) {
        context.getSharedPreferences(PREF_DATA_FILE_NAME, 0).edit().putString(PREF_FIELD_PKG_OFFICIAL_REC_LIST, str).apply();
    }

    private static void setVirtualGameHandleEnable(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), Constants.VIRTUAL_GAME_HANDLE_ENABLE, z ? 1 : 0);
    }

    public static void setVirtualGameHandleOn(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), Constants.VIRTUAL_GAME_HANDLE_ON, z ? 1 : 0);
        context.getContentResolver().notifyChange(DBConstant.URI_SWITCH_GAME_HANDLE, null);
    }

    public static boolean supportOverClock() {
        try {
            Class<?> cls = Class.forName("android.util.NubiaConfig");
            Method declaredMethod = cls.getDeclaredMethod("getValue", String.class);
            declaredMethod.setAccessible(true);
            String str = (String) declaredMethod.invoke(cls.newInstance(), "nubia_gpu_overclock");
            LogUtil.d("gpu", "supportOverClock() value : " + str);
            return "true".equals(str);
        } catch (Exception e) {
            LogUtil.v(TAG, "supportOverClock() e : " + e);
            return false;
        }
    }
}
