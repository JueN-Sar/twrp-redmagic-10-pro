package cn.nubia.tgk;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import androidx.core.os.EnvironmentCompat;
import androidx.media3.common.PlaybackException;
import cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity;
import cn.nubia.config.android.NubiaFeatureConfig;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.tgk.data.TgkData;
import cn.nubia.tgk.data.TgkDataContract;
import cn.nubia.tgk.data.TgkGameInfo;
import cn.nubia.tgk.proxy.InputManagerProxy;
import cn.nubia.tgk.trackclient.NubiaTrackManager;
import cn.nubia.tgk.util.TgkFeatureUtil;
import cn.nubia.tgk.util.TgkFileHelper;
import cn.nubia.tgk.util.TgkLampHelper;
import cn.nubia.tgk.util.TgkUtils;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class TgkHelper {
    public static final String ACTION_GAME_DUAL = "cn.nubia.intent.action.TOUCH_GAME_KEY_MAP_OPTION";
    public static final String ACTIVITY_WEIXIN_MINI_GAME = "com.tencent.mm.plugin.appbrand.ui.AppBrand";
    public static final String CLASS_NAME_ACTIVITY_EVENT = "com.zte.activityevent.ActivityEventsManager";
    private static boolean DEBUG = false;
    public static int[][] DEFAULT_TGK_POINT_1_LANDSCAPE = null;
    public static int[][] DEFAULT_TGK_POINT_1_PORTRAIT = null;
    public static int[][] DEFAULT_TGK_POINT_2_LANDSCAPE = null;
    public static int[][] DEFAULT_TGK_POINT_2_PORTRAIT = null;
    private static final String FEATURE_CLASS_NAME = "com.zte.feature.Feature";
    public static final String GAME_TENCENT_CF = "com.tencent.tmgp.cf";
    public static final boolean IS_SHOW_LINK_BUTTON_VIEW = true;
    public static final boolean IS_SUPPORT_GAME_KEY_LINK_FUNCTION = true;
    public static boolean IS_SUPPORT_LAMP_FUNCTION = false;
    public static final boolean IS_SUPPORT_MIDDLE_TGK;
    public static final boolean IS_SUPPORT_SENSITIVITY_ENABLE = true;
    public static final boolean IS_SUPPORT_TGK_MOVE_VISION = true;
    private static final String IS_TGK_LINK_NO_REMIND_NAME = "nubia_touch_key_link_no_remind";
    private static final String METHOD_GET_BOOLEAN = "getBoolean";
    public static final String METHOD_NAME_INSTANCE = "getInstance";
    public static final String METHOD_NAME_VISIBLE_PACKAGE = "getVisiblePackageDates";
    public static final String NOTIFY_SHOW_SOFT_INPUT = "notify_show_soft_input";
    private static final String NUBIA_COLORFULLIGHT_MANAGER = "com.zte.hardware.ColorfulLightManager";
    public static final String NUBIA_GAME_SCENE = "nubia_game_scene";
    public static final String P720F03_DEVICE = "P720F03";
    public static final String P720F10_DEVICE = "P720F10";
    public static final String P780F01_DEVICE = "P780F01";
    public static final String P820F03_DEVICE = "P820F03";
    public static final String P820F05_DEVICE = "P820F05";
    public static final String PACKAGE_WEIXIN = "com.tencent.mm";
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_1080 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_1224 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_1612 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_1940 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_2030 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_2250 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_2392 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_2720 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_720 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_900 = 0;
    public static final String SHARED_PREFERENCES_NAME = "tgk_data";
    public static final int SHOW_SOFT_INPUT_STATE = 1;
    private static final String TAG = "TgkHelper";
    public static final String TGK_CASE_SHOW_STATUS = "tgk_case_show_status";
    public static final String TGK_CASE_VIEW_POSTION = "tgk_case_view_postion";
    public static final int TGK_COPY_OPT = 1;
    public static final int TGK_COUNT;
    public static final int TGK_DISABLE_COPY_OPT = 2;
    public static final int TGK_DISABLE_LINK_OPT = 16;
    public static final int TGK_DISABLE_LONG_PRESS_OPT = 32;
    public static final int TGK_DISABLE_MOVE_VISION_OPT = 8;
    public static final int TGK_DISABLE_MULT_CLICKS_OPT = 64;
    public static final int TGK_DISABLE_SINGLE_OPT = 1;
    public static final int TGK_DISABLE_UP_DOWN_OPT = 4;
    public static final int TGK_FB_LEFT_NUM = 16;
    public static final int TGK_FB_MIDDLE_NUM = 128;
    public static final int TGK_FB_NULL_NUM = 15;
    public static final int TGK_FB_RIGHT_NUM = 32;
    public static final int TGK_FB_T_NUM = 64;
    public static final String TGK_GUIDE_USE_STATUS = "cc_guide_used";
    public static final int TGK_HIDE_FB_VIEW = 10;
    public static final int TGK_LEFT_KEYCODE = 137;
    public static final int TGK_LINK_OPT = 4;
    public static final int TGK_LONG_PRESS_OPT = 5;
    public static final int TGK_MIDDLE_KEYCODE = 136;
    public static final int TGK_MOVE_VISION_OPT = 3;
    public static final int TGK_MULT_CLICKS_DEFAULT_VALUE = 5;
    public static final int TGK_MULT_CLICKS_OPT = 6;
    public static final int TGK_MULT_CLICKS_OPT_TAB = 100;
    public static final int TGK_OFF_OPT = 9;
    public static final int TGK_OPTION_LEFT = 0;
    public static final int TGK_OPTION_MIDDLE = 2;
    public static final int TGK_OPTION_RIGHT = 1;
    public static final int TGK_RIGHT_KEYCODE = 138;
    public static final int TGK_SET_LINK_FUNTION_OPT = 1000;
    public static final int TGK_SINGLE_OPT = 0;
    public static final int TGK_SLIDE_COPY_OPT = 8;
    public static final int TGK_SLIDE_SINGLE_OPT = 7;
    public static final int TGK_UP_DOWN_OPT = 2;
    public static final String TGK_USE_MULT_CASE_STATUS = "tgk_use_mult_case_status";
    private static boolean mClickMoreButtonStatus;
    private static boolean mGameLeftKeyLinkFunctionEnable;
    private static String mGameLeftKeyLinkName;
    private static boolean mGameMiddleKeyLinkFunctionEnable;
    private static String mGameMiddleKeyLinkName;
    private static boolean mGameRightKeyLinkFunctionEnable;
    private static String mGameRightKeyLinkName;
    public static int mScreenHeightInLandscape;
    public static int mScreenWidthInLandscape;
    private Context mContext;

    static {
        boolean z = true;
        DEBUG = "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
        mGameLeftKeyLinkFunctionEnable = false;
        mGameRightKeyLinkFunctionEnable = false;
        mGameMiddleKeyLinkFunctionEnable = false;
        mGameRightKeyLinkName = "";
        mGameMiddleKeyLinkName = "";
        mGameLeftKeyLinkName = "";
        boolean equals = "true".equals(NubiaFeatureConfig.getSubValue("nubia_touch_game_key_v4_feature", "nubia_support_middle_tgk"));
        IS_SUPPORT_MIDDLE_TGK = equals;
        TGK_COUNT = equals ? 3 : 2;
        mClickMoreButtonStatus = false;
        mScreenWidthInLandscape = (Build.DEVICE.contains("NX729J") || Build.DEVICE.contains("PQ82A01_MAGIC")) ? 2480 : HighLightsUtils.NORMAL_WIDTH;
        mScreenHeightInLandscape = (Build.DEVICE.contains("NX729J") || Build.DEVICE.contains("PQ82A01_MAGIC")) ? 1116 : 1080;
        SCREEN_WIDTH_OR_HEIGHT_IS_900 = ChatAssistantSettingsActivity.REQUEST_CODE;
        SCREEN_WIDTH_OR_HEIGHT_IS_2030 = 2030;
        SCREEN_WIDTH_OR_HEIGHT_IS_720 = 720;
        SCREEN_WIDTH_OR_HEIGHT_IS_1612 = 1612;
        SCREEN_WIDTH_OR_HEIGHT_IS_1080 = 1080;
        SCREEN_WIDTH_OR_HEIGHT_IS_2392 = 2392;
        SCREEN_WIDTH_OR_HEIGHT_IS_1224 = 1224;
        SCREEN_WIDTH_OR_HEIGHT_IS_1940 = 1940;
        SCREEN_WIDTH_OR_HEIGHT_IS_2250 = 2250;
        SCREEN_WIDTH_OR_HEIGHT_IS_2720 = 2720;
        DEFAULT_TGK_POINT_1_LANDSCAPE = new int[][]{new int[]{-1, -1}, new int[]{-1, -1}, new int[]{-1, -1}};
        DEFAULT_TGK_POINT_2_LANDSCAPE = new int[][]{new int[]{-1, -1}, new int[]{-1, -1}, new int[]{-1, -1}};
        DEFAULT_TGK_POINT_1_PORTRAIT = new int[][]{new int[]{-1, -1}, new int[]{-1, -1}, new int[]{-1, -1}};
        DEFAULT_TGK_POINT_2_PORTRAIT = new int[][]{new int[]{-1, -1}, new int[]{-1, -1}, new int[]{-1, -1}};
        if (!SystemProperties.get("ro.product.name").contains("NX769J") && !SystemProperties.get("ro.product.name").contains("NX789J")) {
            z = false;
        }
        IS_SUPPORT_LAMP_FUNCTION = z;
    }

    public TgkHelper(Context context) {
        this.mContext = context;
    }

    private static void adjustTgkOptId(ContentResolver contentResolver, TgkData tgkData) {
        int[] iArr = {137, 138, 136};
        for (int i = 0; i < TGK_COUNT; i++) {
            int i2 = iArr[i];
            int i3 = tgkData.optionArray[i];
            int i4 = tgkData.setLinkFlagArray[i];
            Log.e(TAG, "linkFlagValue=" + i4);
            if (i4 == 1000) {
                if (getTgkLinkState(i2)) {
                    i3 = 4;
                }
                tgkData.setLinkFlagArray[i] = 0;
            }
            if (4 == i3 && !getTgkLinkState(i2)) {
                i3 = 0;
            }
            Log.e(TAG, "outputValue=" + i3);
            tgkData.optionArray[i] = i3;
        }
        int tgkDisableOpt = getTgkDisableOpt(contentResolver, tgkData.packageName);
        int[] iArr2 = {1, 2, 4, 8, 16, 32, 64};
        for (int i5 = 0; i5 < 7; i5++) {
            if ((iArr2[i5] & tgkDisableOpt) > 0) {
                if (i5 == tgkData.optionArray[0]) {
                    tgkData.optionArray[0] = 0;
                }
                if (i5 == tgkData.optionArray[1]) {
                    tgkData.optionArray[1] = 0;
                }
                int i6 = tgkData.optionArray[2];
                if (i5 == i6) {
                    tgkData.optionArray[2] = 0;
                } else if (1 == i5 && i6 == 8) {
                    tgkData.optionArray[2] = 7;
                }
            }
        }
    }

    public static int binarySearch(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static byte[] bitmapTobyte(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void bubbleSort(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length - 1; i++) {
            int i2 = 0;
            while (i2 < (length - i) - 1) {
                int i3 = iArr[i2];
                int i4 = i2 + 1;
                int i5 = iArr[i4];
                if (i3 > i5) {
                    iArr[i2] = i5;
                    iArr[i4] = i3;
                }
                i2 = i4;
            }
        }
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static int checkSelectedAndDeleteTgkCase(ContentResolver contentResolver, String str, String str2) {
        TgkData queryTgkCaseStatic;
        String[] strArr = {"_id", "state"};
        TgkData queryTgkCaseStatic2 = queryTgkCaseStatic(contentResolver, 1, strArr, "original_name LIKE ? AND package_name LIKE ?", new String[]{str, str2}, null);
        if (queryTgkCaseStatic2 == null) {
            return 0;
        }
        if ((queryTgkCaseStatic2.state & 1) > 0 && (queryTgkCaseStatic = queryTgkCaseStatic(contentResolver, 0, strArr, "package_name LIKE ?", new String[]{str2}, "_id ASC")) != null) {
            queryTgkCaseStatic.state |= 1;
            updateTgkCase(contentResolver, 0, queryTgkCaseStatic.ID, "state", queryTgkCaseStatic.state);
        }
        return deleteTgkCaseStatic(contentResolver, 1, queryTgkCaseStatic2.ID);
    }

    private static String[] cursorToString(Cursor cursor) {
        String[] strArr = {"", String.valueOf(cursor.getInt(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), String.valueOf(cursor.getInt(5)), String.valueOf(cursor.getInt(6)), String.valueOf(cursor.getInt(7)), String.valueOf(cursor.getInt(8)), String.valueOf(cursor.getInt(9)), String.valueOf(cursor.getInt(10)), String.valueOf(cursor.getInt(11)), cursor.getString(12), cursor.getString(13), cursor.getString(14), String.valueOf(cursor.getInt(15)), String.valueOf(cursor.getInt(16)), String.valueOf(cursor.getInt(17)), ""};
        byte[] blob = cursor.getBlob(18);
        strArr[18] = blob == null ? null : blob.toString();
        return strArr;
    }

    public static TgkData cursorToTgkDataNoPicture(Cursor cursor) {
        if (cursor.getColumnCount() <= 0) {
            return null;
        }
        TgkData tgkData = new TgkData();
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex >= 0) {
            tgkData.ID = cursor.getLong(columnIndex);
        }
        int columnIndex2 = cursor.getColumnIndex("state");
        if (columnIndex2 >= 0) {
            tgkData.state = cursor.getInt(columnIndex2);
        }
        int columnIndex3 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME);
        if (columnIndex3 >= 0) {
            tgkData.originalName = cursor.getString(columnIndex3);
        }
        int columnIndex4 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME);
        if (columnIndex4 >= 0) {
            tgkData.showName = cursor.getString(columnIndex4);
        }
        int columnIndex5 = cursor.getColumnIndex("package_name");
        if (columnIndex5 >= 0) {
            tgkData.packageName = cursor.getString(columnIndex5);
        }
        int columnIndex6 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW);
        if (columnIndex6 >= 0) {
            tgkData.mainSw = cursor.getInt(columnIndex6) == 1;
        }
        int columnIndex7 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_SW);
        if (columnIndex7 >= 0) {
            tgkData.optionSwArray[0] = cursor.getInt(columnIndex7) == 1;
        }
        int columnIndex8 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_SW);
        if (columnIndex8 >= 0) {
            tgkData.optionSwArray[1] = cursor.getInt(columnIndex8) == 1;
        }
        int columnIndex9 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_M_SW);
        if (columnIndex9 >= 0) {
            tgkData.optionSwArray[2] = cursor.getInt(columnIndex9) == 1;
        }
        int columnIndex10 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW);
        if (columnIndex10 >= 0) {
            tgkData.vibrateSw = cursor.getInt(columnIndex10) == 1;
        }
        int columnIndex11 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY);
        if (columnIndex11 >= 0) {
            tgkData.sensitivityArray[0] = cursor.getInt(columnIndex11);
        }
        int columnIndex12 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY);
        if (columnIndex12 >= 0) {
            tgkData.sensitivityArray[1] = cursor.getInt(columnIndex12);
        }
        int columnIndex13 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS);
        if (columnIndex13 >= 0) {
            tgkData.pointsArray[0] = stringToRect(cursor.getString(columnIndex13));
        }
        int columnIndex14 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS);
        if (columnIndex14 >= 0) {
            tgkData.pointsArray[1] = stringToRect(cursor.getString(columnIndex14));
        }
        int columnIndex15 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS);
        if (columnIndex15 >= 0) {
            tgkData.pointsArray[2] = stringToRect(cursor.getString(columnIndex15));
        }
        int columnIndex16 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION);
        if (columnIndex16 >= 0) {
            getOptionData(tgkData, cursor.getInt(columnIndex16), 0);
        }
        int columnIndex17 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION);
        if (columnIndex17 >= 0) {
            getOptionData(tgkData, cursor.getInt(columnIndex17), 1);
        }
        int columnIndex18 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION);
        if (columnIndex18 >= 0) {
            getOptionData(tgkData, cursor.getInt(columnIndex18), 2);
        }
        int columnIndex19 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE);
        if (columnIndex19 >= 0) {
            tgkData.shotPicture = cursor.getString(columnIndex19);
        }
        int columnIndex20 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_KEY);
        if (columnIndex20 >= 0) {
            tgkData.uniqueId = cursor.getString(columnIndex20);
        }
        int columnIndex21 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_CHANGE);
        if (columnIndex21 >= 0) {
            tgkData.change = cursor.getInt(columnIndex21);
        }
        int columnIndex22 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME);
        if (columnIndex22 >= 0) {
            tgkData.updateTime = cursor.getLong(columnIndex22);
        }
        int columnIndex23 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE);
        if (columnIndex23 < 0) {
            return tgkData;
        }
        tgkData.setIsLandscape(cursor.getInt(columnIndex23));
        return tgkData;
    }

    public static TgkData cursorToTgkDataPrecise(Cursor cursor) {
        if (cursor.getColumnCount() <= 0) {
            return null;
        }
        TgkData tgkData = new TgkData();
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex >= 0) {
            tgkData.ID = cursor.getLong(columnIndex);
        }
        int columnIndex2 = cursor.getColumnIndex("state");
        if (columnIndex2 >= 0) {
            tgkData.state = cursor.getInt(columnIndex2);
        }
        int columnIndex3 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME);
        if (columnIndex3 >= 0) {
            tgkData.originalName = cursor.getString(columnIndex3);
        }
        int columnIndex4 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME);
        if (columnIndex4 >= 0) {
            tgkData.showName = cursor.getString(columnIndex4);
        }
        int columnIndex5 = cursor.getColumnIndex("package_name");
        if (columnIndex5 >= 0) {
            tgkData.packageName = cursor.getString(columnIndex5);
        }
        int columnIndex6 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW);
        if (columnIndex6 >= 0) {
            tgkData.mainSw = cursor.getInt(columnIndex6) == 1;
        }
        int columnIndex7 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_SW);
        if (columnIndex7 >= 0) {
            tgkData.optionSwArray[0] = cursor.getInt(columnIndex7) == 1;
        }
        int columnIndex8 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_SW);
        if (columnIndex8 >= 0) {
            tgkData.optionSwArray[1] = cursor.getInt(columnIndex8) == 1;
        }
        int columnIndex9 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_M_SW);
        if (columnIndex9 >= 0) {
            tgkData.optionSwArray[2] = cursor.getInt(columnIndex9) == 1;
        }
        int columnIndex10 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW);
        if (columnIndex10 >= 0) {
            tgkData.vibrateSw = cursor.getInt(columnIndex10) == 1;
        }
        int columnIndex11 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY);
        if (columnIndex11 >= 0) {
            tgkData.sensitivityArray[0] = cursor.getInt(columnIndex11);
        }
        int columnIndex12 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY);
        if (columnIndex12 >= 0) {
            tgkData.sensitivityArray[1] = cursor.getInt(columnIndex12);
        }
        int columnIndex13 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS);
        if (columnIndex13 >= 0) {
            tgkData.pointsArray[0] = stringToRect(cursor.getString(columnIndex13));
        }
        int columnIndex14 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS);
        if (columnIndex14 >= 0) {
            tgkData.pointsArray[1] = stringToRect(cursor.getString(columnIndex14));
        }
        int columnIndex15 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS);
        if (columnIndex15 >= 0) {
            tgkData.pointsArray[2] = stringToRect(cursor.getString(columnIndex15));
        }
        int columnIndex16 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION);
        if (columnIndex16 >= 0) {
            getOptionData(tgkData, cursor.getInt(columnIndex16), 0);
        }
        int columnIndex17 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION);
        if (columnIndex17 >= 0) {
            getOptionData(tgkData, cursor.getInt(columnIndex17), 1);
        }
        int columnIndex18 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION);
        if (columnIndex18 >= 0) {
            getOptionData(tgkData, cursor.getInt(columnIndex18), 2);
        }
        int columnIndex19 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_PICTURE);
        if (columnIndex19 >= 0) {
            tgkData.picture = byteToBitmap(cursor.getBlob(columnIndex19));
        }
        int columnIndex20 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE);
        if (columnIndex20 >= 0) {
            tgkData.shotPicture = cursor.getString(columnIndex20);
        }
        int columnIndex21 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_KEY);
        if (columnIndex21 >= 0) {
            tgkData.uniqueId = cursor.getString(columnIndex21);
        }
        int columnIndex22 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_CHANGE);
        if (columnIndex22 >= 0) {
            tgkData.change = cursor.getInt(columnIndex22);
        }
        int columnIndex23 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME);
        if (columnIndex23 >= 0) {
            tgkData.updateTime = cursor.getLong(columnIndex23);
        }
        int columnIndex24 = cursor.getColumnIndex(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE);
        if (columnIndex24 < 0) {
            return tgkData;
        }
        tgkData.setIsLandscape(cursor.getInt(columnIndex24));
        return tgkData;
    }

    private static TgkData cursorToTgkDataToShow(Cursor cursor) {
        TgkData tgkData = new TgkData();
        tgkData.ID = cursor.getLong(0);
        tgkData.state = cursor.getInt(1);
        tgkData.originalName = cursor.getString(2);
        tgkData.showName = cursor.getString(3);
        tgkData.packageName = cursor.getString(4);
        tgkData.optionArray = null;
        tgkData.optionSwArray = null;
        tgkData.pointsArray = null;
        tgkData.sensitivityArray = null;
        return tgkData;
    }

    public static void deleteSelectedTgkCase(Context context, int i, long j, String str, String str2) {
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList<TgkData> queryTgkCasesNoPicture = queryTgkCasesNoPicture(contentResolver, 0, str);
        TgkGameInfo queryTgkGameMoreInfo = queryTgkGameMoreInfo(contentResolver, str);
        if (queryTgkCasesNoPicture != null) {
            TgkData tgkData = queryTgkCasesNoPicture.get(0);
            InputManagerProxy inputManagerProxy = new InputManagerProxy(context);
            if (inputManagerProxy.isGameKeyEnable()) {
                setTgkParaToNative(inputManagerProxy, tgkData, queryTgkGameMoreInfo, context.getResources().getConfiguration().orientation == 2 ? 1 : 0);
            }
            tgkData.state |= 1;
            updateTgkCase(contentResolver, 0, tgkData.ID, "state", tgkData.state);
        }
        deleteTgkCase(contentResolver, i, j, context, str2);
    }

    public static void deleteTgkCase(ContentResolver contentResolver, int i, long j, Context context, String str) {
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null) {
            TgkFileHelper.deletePreviewPictureFile(context, str);
            Log.d(TAG, "deleteTgkCase rows =" + contentResolver.delete(uriByTableId, "_id=?", new String[]{Long.toString(j)}) + ", caseId=" + j + ", tableId=" + i);
        }
    }

    public static int deleteTgkCaseStatic(ContentResolver contentResolver, int i, long j) {
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId == null) {
            return -1;
        }
        int delete = contentResolver.delete(uriByTableId, "_id=?", new String[]{Long.toString(j)});
        Log.d(TAG, "deleteTgkCaseStatic rows =" + delete + ", caseId=" + j);
        return delete;
    }

    public static boolean disableTgkFunction(String str) {
        if (!getBoolean("ZTE_FEATURE_RED_MAGIC", false).booleanValue()) {
            return false;
        }
        if ("com.android.permissioncontroller".equals(str)) {
            return true;
        }
        return HighLightsUtils.WZRY_PACKAGE_NAME.equals(str) || "com.levelinfinite.sgameGlobal".equals(str) || "com.levelinfinite.sgameGlobal.midaspay".equals(str) || "com.tencent.tmgp.sgamece".equals(str) || "com.tencent.KiHan".equals(str);
    }

    public static void disableTgkMap(Context context, String str) {
        disableTgkMap(context, str, false);
    }

    public static void disableTgkMap(Context context, String str, boolean z) {
        InputManagerProxy inputManagerProxy = new InputManagerProxy(context);
        if (factoryFlag(str, context)) {
            int[] iArr = {-1, -1};
            inputManagerProxy.setGameKeyEnable(true);
            inputManagerProxy.setTgkPoint(iArr, iArr, 137);
            inputManagerProxy.setTgkPoint(iArr, iArr, 138);
            inputManagerProxy.setTgkPoint(iArr, iArr, 136);
            inputManagerProxy.setTouchHapticFeedbackEnable(true);
            inputManagerProxy.setLeftTgkEnable(true);
            inputManagerProxy.setRightTgkEnable(true);
            inputManagerProxy.setMiddleTgkEnable(true);
            inputManagerProxy.setTgkTopEffectEnable(false);
            inputManagerProxy.setTgkCenterEffectEnable(false);
            inputManagerProxy.setTgkTransparency(0);
            inputManagerProxy.setDefaultGameKeyLinkFunctionEnable(0);
        } else {
            if (z) {
                TgkGameInfo tgkGameInfo = new TgkGameInfo(str);
                ContentResolver contentResolver = context.getContentResolver();
                loadingTgkCases(contentResolver, tgkGameInfo);
                TgkData selectedCaseData = tgkGameInfo.getSelectedCaseData();
                if (selectedCaseData != null && selectedCaseData.mainSw) {
                    selectedCaseData.mainSw = false;
                    updateTgkCaseExceptPicture(contentResolver, tgkGameInfo.selectedTableId, selectedCaseData);
                }
            }
            setTgkParaToNativeDisableEnable(inputManagerProxy);
        }
        openLampScene(0);
    }

    public static TgkGameInfo enableTgkMap(Context context, String str, int i) {
        return enableTgkMap(context, str, false, i);
    }

    public static TgkGameInfo enableTgkMap(Context context, String str, boolean z, int i) {
        TgkGameInfo gameInfo = z ? getGameInfo(context, str, z, i) : getGameInfoForEnableTgk(context, str);
        if (isSPRDDevice() && gameInfo != null && isDefaultTgkEnableGame(gameInfo.gameName) && gameInfo.presetTableList == null) {
            getDefaultTgkCaseList(context, gameInfo, z, i);
        }
        TgkData selectedCaseData = gameInfo.getSelectedCaseData();
        Log.d(TAG, "enableTgkMap selectedTgkData=" + selectedCaseData);
        ContentResolver contentResolver = context.getContentResolver();
        InputManagerProxy inputManagerProxy = new InputManagerProxy(context);
        if (selectedCaseData != null) {
            if (z && !selectedCaseData.mainSw) {
                selectedCaseData.mainSw = true;
                updateTgkCaseExceptPicture(contentResolver, gameInfo.selectedTableId, selectedCaseData);
            }
            queryTgkLinkState(contentResolver, str, selectedCaseData.ID, selectedCaseData.state);
            adjustTgkOptId(contentResolver, selectedCaseData);
            if (disableTgkFunction(str)) {
                selectedCaseData.mainSw = false;
            }
            setTgkParaToNativeForEnableTgk(context, str, inputManagerProxy, selectedCaseData, gameInfo, i);
        } else {
            TgkData tgkData = new TgkData();
            if (getLowerVersionTgkData(contentResolver, str, tgkData, false)) {
                ArrayList<TgkData> arrayList = new ArrayList<>();
                arrayList.add(tgkData);
                gameInfo.selectedTableId = 0;
                gameInfo.selectedCasePosition = 0;
                gameInfo.presetTableList = arrayList;
            } else {
                tgkData.mainSw = false;
            }
            if (disableTgkFunction(str)) {
                tgkData.mainSw = false;
            }
            Log.d(TAG, "in setTgkMapParas selectedTgkData = null");
            setTgkParaToNativeForEnableTgk(context, str, inputManagerProxy, tgkData, gameInfo, i);
        }
        return gameInfo;
    }

    private static boolean factoryFlag(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            Log.d(TAG, "factoryFlag packageName is null!");
            str = getCurResumingPackage(context);
        }
        Log.d(TAG, "factoryFlag packageName:" + str);
        return "cn.nubia.factory".equals(str) || "cn.nubia.testtest".equals(str) || "cn.nubia.testtest1".equals(str);
    }

    private static String getAppName(Context context, String str) {
        return getAppNameByPackageName(context, str);
    }

    public static String getAppNameByPackageName(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        if (str.contains("@")) {
            return getTaskDescLabel(context);
        }
        try {
            return packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Boolean getBoolean(String str, boolean z) {
        try {
            z = ((Boolean) Class.forName(FEATURE_CLASS_NAME).getMethod(METHOD_GET_BOOLEAN, String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
            Log.d(TAG, str + ":" + z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.valueOf(z);
    }

    private static ContentValues getContentValuesAllData(TgkData tgkData) {
        if (tgkData == null) {
            Log.e(TAG, "Null data!");
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(tgkData.state));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME, tgkData.originalName);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME, tgkData.showName);
        contentValues.put("package_name", tgkData.packageName);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW, Boolean.valueOf(tgkData.mainSw));
        if (tgkData.optionSwArray != null) {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_SW, Boolean.valueOf(tgkData.optionSwArray[0]));
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_SW, Boolean.valueOf(tgkData.optionSwArray[1]));
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_SW, Boolean.valueOf(tgkData.optionSwArray[2]));
        }
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW, Boolean.valueOf(tgkData.vibrateSw));
        if (tgkData.sensitivityArray != null) {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY, Integer.valueOf(tgkData.sensitivityArray[0]));
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY, Integer.valueOf(tgkData.sensitivityArray[1]));
        }
        if (tgkData.pointsArray != null) {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS, rectToString(tgkData.pointsArray[0]));
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS, rectToString(tgkData.pointsArray[1]));
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS, rectToString(tgkData.pointsArray[2]));
        }
        if (tgkData.optionArray != null) {
            if (tgkData.rapidFireCountArray[0] == 5 || tgkData.optionArray[0] != 6) {
                contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[0] + tgkData.optionArray[0]));
            } else {
                contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[0] + tgkData.optionArray[0] + 100 + tgkData.rapidFireCountArray[0]));
            }
            if (tgkData.rapidFireCountArray[1] == 5 || tgkData.optionArray[1] != 6) {
                contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[1] + tgkData.optionArray[1]));
            } else {
                contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[1] + tgkData.optionArray[1] + 100 + tgkData.rapidFireCountArray[1]));
            }
            if (tgkData.rapidFireCountArray[2] == 5 || tgkData.optionArray[2] != 6) {
                contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[2] + tgkData.optionArray[2]));
            } else {
                contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[2] + tgkData.optionArray[2] + 100 + tgkData.rapidFireCountArray[2]));
            }
        }
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_PICTURE, bitmapTobyte(tgkData.picture));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE, tgkData.shotPicture);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_KEY, tgkData.uniqueId);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_CHANGE, Integer.valueOf(tgkData.change));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, Long.valueOf(tgkData.updateTime));
        contentValues.put(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE, Integer.valueOf(tgkData.getIsLandscape()));
        return contentValues;
    }

    private static ContentValues getContentValuesByMoreInfo(String str, boolean z, boolean z2, int i) {
        ContentValues contentValues = new ContentValues();
        if (str != null) {
            contentValues.put("package_name", str);
        }
        contentValues.put(TgkDataContract.TgkEntry.TGK_TOP_VISUAL_EFFECT_SW, Integer.valueOf(z ? 1 : 0));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CENTER_VISUAL_EFFECT_SW, Integer.valueOf(z2 ? 1 : 0));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CENTER_VISUAL_EFFECT_TRANSPARENCY, Integer.valueOf(i));
        return contentValues;
    }

    private static ContentValues getContentValuesByTgkDataNoPicture(TgkData tgkData) {
        if (tgkData == null) {
            Log.e(TAG, "Null data!");
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(tgkData.state));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME, tgkData.originalName);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME, tgkData.showName);
        contentValues.put("package_name", tgkData.packageName);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW, Boolean.valueOf(tgkData.mainSw));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_SW, Boolean.valueOf(tgkData.optionSwArray[0]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_SW, Boolean.valueOf(tgkData.optionSwArray[1]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_SW, Boolean.valueOf(tgkData.optionSwArray[2]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW, Boolean.valueOf(tgkData.vibrateSw));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY, Integer.valueOf(tgkData.sensitivityArray[0]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY, Integer.valueOf(tgkData.sensitivityArray[1]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS, rectToString(tgkData.pointsArray[0]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS, rectToString(tgkData.pointsArray[1]));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS, rectToString(tgkData.pointsArray[2]));
        if (tgkData.rapidFireCountArray[0] == 5 || tgkData.optionArray[0] != 6) {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[0] + tgkData.optionArray[0]));
        } else {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[0] + tgkData.optionArray[0] + 100 + tgkData.rapidFireCountArray[0]));
        }
        if (tgkData.rapidFireCountArray[1] == 5 || tgkData.optionArray[1] != 6) {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[1] + tgkData.optionArray[1]));
        } else {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[1] + tgkData.optionArray[1] + 100 + tgkData.rapidFireCountArray[1]));
        }
        if (tgkData.rapidFireCountArray[2] == 5 || tgkData.optionArray[2] != 6) {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[2] + tgkData.optionArray[2]));
        } else {
            contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION, Integer.valueOf(tgkData.setLinkFlagArray[2] + tgkData.optionArray[2] + 100 + tgkData.rapidFireCountArray[2]));
        }
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE, tgkData.shotPicture);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_KEY, tgkData.uniqueId);
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_CHANGE, Integer.valueOf(tgkData.change));
        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, Long.valueOf(tgkData.updateTime));
        contentValues.put(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE, Integer.valueOf(tgkData.getIsLandscape()));
        return contentValues;
    }

    public static String getCurResumingPackage(Context context) {
        return context != null ? getVisiblePackage(context) : "";
    }

    private static Rect[] getDefaultFVPos(String str, int i, int i2) {
        Rect[][] rectArr = {new Rect[]{new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, HighLightsUtils.RESET_DELAY_TIME, 984, 584), null}, new Rect[]{new Rect(1320, HighLightsUtils.RESET_DELAY_TIME, 1404, 584), null}, new Rect[]{new Rect(1400, HighLightsUtils.RESET_DELAY_TIME, 1484, 584), null}, new Rect[]{new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, HighLightsUtils.RESET_DELAY_TIME, 984, 584), new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, 590, 984, 674)}, new Rect[]{new Rect(1320, HighLightsUtils.RESET_DELAY_TIME, 1404, 584), new Rect(1320, 590, 1404, 674)}, new Rect[]{new Rect(1320, 600, 1404, 684), new Rect(1300, HighLightsUtils.RESET_DELAY_TIME, 1384, 584)}, new Rect[]{new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, HighLightsUtils.RESET_DELAY_TIME, 984, 584), new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, 590, 984, 674)}, new Rect[]{new Rect(1320, HighLightsUtils.RESET_DELAY_TIME, 1404, 584), new Rect(1320, 590, 1404, 674)}, new Rect[]{new Rect(1320, 600, 1404, 684), new Rect(1300, HighLightsUtils.RESET_DELAY_TIME, 1384, 584)}, new Rect[]{new Rect(870, 200, 1152, 482), null}, new Rect[]{new Rect(1150, 200, 1432, 482), null}, new Rect[]{new Rect(1200, 200, 1284, 482), null}, new Rect[]{new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, HighLightsUtils.RESET_DELAY_TIME, 984, 584), null}, new Rect[]{new Rect(1320, HighLightsUtils.RESET_DELAY_TIME, 1404, 584), null}, new Rect[]{new Rect(1200, 200, 1284, 482), null}, new Rect[]{new Rect(800, HighLightsUtils.RESET_DELAY_TIME, 884, 584), null}, new Rect[]{new Rect(1200, HighLightsUtils.RESET_DELAY_TIME, 1284, 584), null}, new Rect[]{new Rect(1500, HighLightsUtils.RESET_DELAY_TIME, 1584, 584), null}, new Rect[]{new Rect(700, HighLightsUtils.RESET_DELAY_TIME, 784, 584), null}, new Rect[]{new Rect(1100, HighLightsUtils.RESET_DELAY_TIME, 1184, 584), null}, new Rect[]{new Rect(1600, HighLightsUtils.RESET_DELAY_TIME, 1684, 584), null}, new Rect[]{null, null}, new Rect[]{null, null}, new Rect[]{new Rect(1500, HighLightsUtils.RESET_DELAY_TIME, 1584, 584), null}, new Rect[]{null, null}, new Rect[]{null, null}, new Rect[]{new Rect(1200, HighLightsUtils.RESET_DELAY_TIME, 1284, 584), new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, 600, 984, 684)}};
        if (Build.DEVICE.contains("NX666J")) {
            rectArr[0][0] = new Rect(137, 503, 221, 587);
            rectArr[1][0] = new Rect(2230, 514, 2314, 598);
        } else {
            rectArr[0][0] = new Rect(141, 504, 225, 588);
            rectArr[1][0] = new Rect(2229, 518, 2313, 602);
        }
        Rect[][] rectArr2 = {new Rect[]{new Rect(257, 386, 341, 470), null}, new Rect[]{new Rect(1280, 919, 1364, 1003), null}, new Rect[]{new Rect(1280, ChatAssistantSettingsActivity.REQUEST_CODE, 1364, 984), null}, new Rect[]{new Rect(257, 386, 341, 470), new Rect(257, 529, 341, 613)}, new Rect[]{new Rect(1617, 595, 1701, 679), new Rect(1499, 788, 1583, 872)}, new Rect[]{new Rect(1617, 600, 1701, 684), new Rect(1499, 800, 1583, 884)}, new Rect[]{new Rect(428, 310, 524, 394), new Rect(219, 149, 315, 233)}, new Rect[]{new Rect(1808, 6, 1904, 90), new Rect(1669, 6, 1792, 90)}, new Rect[]{new Rect(1808, 16, 1904, 100), new Rect(1669, 26, 1792, 110)}, new Rect[]{new Rect(853, 207, 1135, 491), null}, new Rect[]{new Rect(1130, 207, 1412, 491), null}, new Rect[]{new Rect(1808, 26, 1904, 110), new Rect(1669, 36, 1792, 120)}, new Rect[]{new Rect(257, 386, 341, 470), null}, new Rect[]{new Rect(1280, 919, 1364, 1003), null}, new Rect[]{new Rect(1280, 1000, 1364, 1084), null}, new Rect[]{new Rect(800, HighLightsUtils.RESET_DELAY_TIME, 884, 584), null}, new Rect[]{new Rect(1200, HighLightsUtils.RESET_DELAY_TIME, 1284, 584), null}, new Rect[]{new Rect(1500, HighLightsUtils.RESET_DELAY_TIME, 1584, 584), null}, new Rect[]{new Rect(700, HighLightsUtils.RESET_DELAY_TIME, 784, 584), null}, new Rect[]{new Rect(1100, HighLightsUtils.RESET_DELAY_TIME, 1184, 584), null}, new Rect[]{new Rect(1600, HighLightsUtils.RESET_DELAY_TIME, 1684, 584), null}, new Rect[]{null, null}, new Rect[]{null, null}, new Rect[]{new Rect(1500, HighLightsUtils.RESET_DELAY_TIME, 1584, 584), null}, new Rect[]{null, null}, new Rect[]{null, null}, new Rect[]{new Rect(1200, HighLightsUtils.RESET_DELAY_TIME, 1284, 584), new Rect(ChatAssistantSettingsActivity.REQUEST_CODE, 600, 984, 684)}};
        if (!HighLightsUtils.CJZC_PACKAGE_NAME.equals(str) && !"com.tencent.tmgp.pubgm".equals(str) && !str.startsWith("com.netease.hyxd") && !str.startsWith("com.netease.zjz") && HighLightsUtils.WZRY_PACKAGE_NAME.equals(str)) {
            rectArr = rectArr2;
        }
        return rectArr[(TGK_COUNT * i) + i2];
    }

    private static void getDefaultTgkCaseList(Context context, TgkGameInfo tgkGameInfo, boolean z, int i) {
        String[] stringArray = context.getResources().getStringArray(R.array.tgk_preset_case_names);
        tgkGameInfo.presetTableList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        for (int i2 = 0; i2 < 5; i2++) {
            TgkData tgkData = new TgkData(tgkGameInfo.gameName, 0);
            if (i == 0) {
                tgkData.updateDefaultPointsArray(i);
            }
            String str = stringArray[i2];
            tgkData.showName = str;
            tgkData.originalName = str;
            tgkData.setIsLandscape(i);
            if (i2 == 0) {
                tgkData.state |= 1;
                if (!getLowerVersionTgkData(contentResolver, tgkGameInfo.gameName, tgkData, true)) {
                    if (isDefaultTgkEnableGame(tgkGameInfo.gameName) || z) {
                        tgkData.mainSw = true;
                    } else {
                        tgkData.mainSw = false;
                    }
                }
                outData(tgkData);
            } else if (1 == i2) {
                tgkData.setCustomizedTgkData(context);
            }
            tgkData.setCustomizedTgkData(context, i2);
            tgkGameInfo.presetTableList.add(tgkData);
        }
        tgkGameInfo.selectedTableId = 0;
        tgkGameInfo.selectedCasePosition = 0;
        insertPresetCases(contentResolver, tgkGameInfo.presetTableList);
    }

    private static void getDefaultTgkCaseListNotInsert(Context context, TgkGameInfo tgkGameInfo) {
        String[] stringArray = context.getResources().getStringArray(R.array.tgk_preset_case_names);
        tgkGameInfo.presetTableList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        for (int i = 0; i < 5; i++) {
            TgkData tgkData = new TgkData(tgkGameInfo.gameName, 0);
            String str = stringArray[i];
            tgkData.showName = str;
            tgkData.originalName = str;
            if (i == 0) {
                tgkData.state |= 1;
                if (!getLowerVersionTgkData(contentResolver, tgkGameInfo.gameName, tgkData, true)) {
                    if (isDefaultTgkEnableGame(tgkGameInfo.gameName)) {
                        tgkData.mainSw = true;
                    } else {
                        tgkData.mainSw = false;
                    }
                }
                outData(tgkData);
            } else if (1 == i) {
                tgkData.setCustomizedTgkData(context);
            }
            tgkData.setCustomizedTgkData(context, i);
            tgkGameInfo.presetTableList.add(tgkData);
        }
        tgkGameInfo.selectedTableId = 0;
        tgkGameInfo.selectedCasePosition = 0;
    }

    private static int getFingerTouchNum(ContentResolver contentResolver, String str) {
        int i = 0;
        try {
            i = Settings.Global.getInt(contentResolver, str + "_touch_game_finger_num", 48);
            Log.e(TAG, "getFingerTouchNumStatus fingerCount=" + i);
            return i;
        } catch (Exception unused) {
            Log.e(TAG, "getFingerTouchNumStatus failed!");
            return i;
        }
    }

    private static String[] getFloatViewDataKey(String str, int[] iArr) {
        int i = TGK_COUNT;
        String[] strArr = new String[i];
        String replace = str.replace('.', '_');
        String[][] strArr2 = {new String[]{"tgk_single_rect_l_", "tgk_copy_rect_l_", "tgk_up_down_rect_l_", "tgk_move_vision_rect_l_", "tgk_single_rect_l_", "tgk_long_prs_rect_l_", "tgk_mlt_clc_rect_l_", "tgk_slide_single_rect_l_", "tgk_slide_copy_rect_l_"}, new String[]{"tgk_single_rect_r_", "tgk_copy_rect_r_", "tgk_up_down_rect_r_", "tgk_move_vision_rect_r_", "tgk_single_rect_r_", "tgk_long_prs_rect_r_", "tgk_mlt_clc_rect_r_", "tgk_slide_single_rect_r_", "tgk_slide_copy_rect_r_"}, new String[]{"tgk_single_rect_m_", "tgk_copy_rect_m_", "tgk_up_down_rect_m_", "tgk_move_vision_rect_m_", "tgk_single_rect_m_", "tgk_long_prs_rect_m_", "tgk_mlt_clc_rect_m_", "tgk_slide_single_rect_m_", "tgk_slide_copy_rect_m_"}};
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = strArr2[i2][iArr[i2]] + replace;
        }
        return strArr;
    }

    public static TgkGameInfo getGameInfo(Context context, String str, int i) {
        return getGameInfo(context, str, false, i);
    }

    public static TgkGameInfo getGameInfo(Context context, String str, boolean z, int i) {
        TgkGameInfo tgkGameInfo = new TgkGameInfo(str);
        ContentResolver contentResolver = context.getContentResolver();
        loadingTgkCases(contentResolver, tgkGameInfo);
        if (tgkGameInfo.presetTableList == null) {
            getDefaultTgkCaseList(context, tgkGameInfo, z, i);
        }
        queryTgkGameMoreInfo(contentResolver, tgkGameInfo);
        tgkGameInfo.picture = queryTgkCasePicture(contentResolver, tgkGameInfo.getSelectedCaseData().ID);
        Log.d(TAG, "select table id=" + tgkGameInfo.selectedTableId + ", selected case id=" + tgkGameInfo.selectedCasePosition);
        return tgkGameInfo;
    }

    public static TgkGameInfo getGameInfoForEnableTgk(Context context, String str) {
        TgkGameInfo tgkGameInfo = new TgkGameInfo(str);
        ContentResolver contentResolver = context.getContentResolver();
        loadingTgkCases(contentResolver, tgkGameInfo);
        queryTgkGameMoreInfo(contentResolver, tgkGameInfo);
        return tgkGameInfo;
    }

    public static boolean getISUseMultCaseStates(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getBoolean(TGK_USE_MULT_CASE_STATUS, false);
    }

    public static void getIsSupportLampFunction(Context context) {
        try {
            if (TextUtils.isEmpty(Settings.Global.getString(context.getContentResolver(), "lighting_shoulder_config_json"))) {
                IS_SUPPORT_LAMP_FUNCTION = false;
            } else {
                IS_SUPPORT_LAMP_FUNCTION = true;
            }
        } catch (Exception e) {
            IS_SUPPORT_LAMP_FUNCTION = false;
            e.printStackTrace();
        }
    }

    public static String getLampCode(int i) {
        Log.d(TAG, "getLampCode state =" + i);
        if (i != -1 && i < TgkLampHelper.getLampCaseCodesSize()) {
            Log.d(TAG, "getLampCode state =" + i);
            return TgkLampHelper.getLampCaseCodesForIndex(i);
        }
        return TgkLampHelper.getSelection();
    }

    private static boolean getLowerVersionTgkData(ContentResolver contentResolver, String str, TgkData tgkData, boolean z) {
        int touchHapticFeedbackState = getTouchHapticFeedbackState(contentResolver, str);
        if (touchHapticFeedbackState < 0) {
            Log.d(TAG, str + " no old tgk data!");
            return false;
        }
        tgkData.mainSw = getTgkMainSwState(contentResolver, str);
        setTgkOptSwArray(contentResolver, str, tgkData.optionSwArray);
        setTgkOptArray(contentResolver, str, tgkData.optionArray);
        setTgkFloatViewPosition(contentResolver, str, tgkData.optionArray, tgkData.pointsArray);
        tgkData.vibrateSw = touchHapticFeedbackState == 0;
        setSensitivityArray(contentResolver, str, tgkData.sensitivityArray);
        if (z) {
            setTouchHapticFeedbackState(contentResolver, str);
        }
        return true;
    }

    private static String getModeString(int i) {
        return (i < 0 || 8 < i) ? "single" : new String[]{"single", "double", "up_down", "visual_field", "onekeylink", "press", "multiple_clicks", "single", "double"}[i];
    }

    public static void getOptionData(TgkData tgkData, int i, int i2) {
        if (i < 1000) {
            if (i > 100) {
                tgkData.optionArray[i2] = 6;
                tgkData.rapidFireCountArray[i2] = i + PlaybackException.ERROR_CODE_NOT_AVAILABLE_IN_REGION;
                tgkData.rapidFireCountArray[i2] = getUpdateFireCount(tgkData.rapidFireCountArray[i2]);
            } else {
                tgkData.optionArray[i2] = i;
                tgkData.rapidFireCountArray[i2] = 5;
            }
            tgkData.setLinkFlagArray[i2] = 0;
            return;
        }
        int i3 = i - 1000;
        if (i3 > 100) {
            tgkData.optionArray[i2] = 6;
            tgkData.rapidFireCountArray[i2] = i - 1106;
            tgkData.rapidFireCountArray[i2] = getUpdateFireCount(tgkData.rapidFireCountArray[i2]);
        } else {
            tgkData.optionArray[i2] = i3;
            tgkData.rapidFireCountArray[i2] = 5;
        }
        tgkData.setLinkFlagArray[i2] = 1000;
    }

    public static String getProp(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, EnvironmentCompat.MEDIA_UNKNOWN);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static void getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        if (point.x > point.y) {
            mScreenHeightInLandscape = point.y;
            mScreenWidthInLandscape = point.x;
        } else {
            mScreenHeightInLandscape = point.x;
            mScreenWidthInLandscape = point.y;
        }
        int[][] iArr = DEFAULT_TGK_POINT_1_LANDSCAPE;
        int i = mScreenWidthInLandscape;
        int i2 = mScreenHeightInLandscape;
        iArr[0] = new int[]{i / 4, i2 / 2};
        iArr[1] = new int[]{(i / 4) * 3, i2 / 2};
        iArr[2] = new int[]{i / 2, i2 / 2};
        int[][] iArr2 = DEFAULT_TGK_POINT_2_LANDSCAPE;
        iArr2[0] = new int[]{i / 4, (i2 / 3) * 2};
        iArr2[1] = new int[]{(i / 4) * 3, (i2 / 3) * 2};
        iArr2[2] = new int[]{i / 2, (i2 / 3) * 2};
        int[][] iArr3 = DEFAULT_TGK_POINT_1_PORTRAIT;
        iArr3[0] = new int[]{i2 / 4, i / 2};
        iArr3[1] = new int[]{(i2 / 4) * 3, i / 2};
        iArr3[2] = new int[]{i2 / 2, i / 2};
        int[][] iArr4 = DEFAULT_TGK_POINT_2_PORTRAIT;
        iArr4[0] = new int[]{i2 / 4, (i / 3) * 2};
        iArr4[1] = new int[]{(i2 / 4) * 3, (i / 3) * 2};
        iArr4[2] = new int[]{i2 / 2, (i / 3) * 2};
        Log.d(TAG, "getScreenSize mScreenHeightInLandscape=" + mScreenHeightInLandscape + ";mScreenWidthInLandscape=" + mScreenWidthInLandscape);
    }

    private static String getSwString(boolean z) {
        return z ? "on" : "off";
    }

    public static String getTaskDescLabel(Context context) {
        ActivityManager activityManager;
        String str = "";
        try {
            activityManager = (ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error retrieving task description: " + e.getMessage());
        }
        if (activityManager == null) {
            Log.i(TAG, "getTaskDescLabel am = null");
            return "";
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        if (runningTasks != null && !runningTasks.isEmpty()) {
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            Log.i(TAG, "running task name : " + runningTaskInfo.topActivity);
            if (runningTaskInfo.topActivity != null && runningTaskInfo.topActivity.getClassName().startsWith("com.tencent.mm.plugin.appbrand.ui.AppBrand")) {
                ActivityManager.TaskDescription taskDescription = runningTaskInfo.taskDescription;
                if (taskDescription != null) {
                    str = taskDescription.getLabel();
                }
            }
            return "";
        }
        Log.i(TAG, "getTaskDescLabel = " + str);
        return str;
    }

    public static TgkData getTgkCase(Context context, long j, int i, String str) {
        Cursor query;
        int i2 = (i & 4) > 0 ? 0 : 1;
        if ((i & 8) > 0) {
            i2 = 1;
        }
        try {
            Uri uriByTableId = getUriByTableId(i2);
            if (uriByTableId != null && (query = context.getContentResolver().query(uriByTableId, null, "_id = ? AND package_name LIKE ?", new String[]{Long.toString(j), str}, null)) != null) {
                r12 = query.moveToNext() ? cursorToTgkDataPrecise(query) : null;
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r12;
    }

    public static boolean getTgkCaseShowStates(Context context, String str) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getBoolean("tgk_case_show_status_" + str, false);
    }

    public static int[] getTgkCaseViewPostion(Context context, String str) {
        int[] iArr = {0, 0};
        try {
            String[] split = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString("tgk_case_view_postion_" + str, "").split(",");
            if (split.length == 2) {
                iArr[0] = Integer.parseInt(split[0]);
                iArr[1] = Integer.parseInt(split[1]);
            }
        } catch (Exception unused) {
            Log.e(TAG, "getTgkCaseViewPostion failed!");
        }
        return iArr;
    }

    public static int getTgkCasesCountStatic(ContentResolver contentResolver, String str) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(1);
        if (uriByTableId == null || (query = contentResolver.query(uriByTableId, new String[]{"_id"}, "package_name LIKE ?", new String[]{str}, "_id DESC")) == null) {
            return 0;
        }
        int count = query.getCount();
        query.close();
        return count;
    }

    public static int getTgkDisableOpt(ContentResolver contentResolver, String str) {
        int i = "com.tencent.tmgp.cf".equals(str) ? 64 : 0;
        if (HighLightsUtils.WZRY_PACKAGE_NAME.equals(str) || "com.levelinfinite.sgameGlobal".equals(str) || "com.levelinfinite.sgameGlobal.midaspay".equals(str) || "com.tencent.tmgp.sgamece".equals(str)) {
            i = 20;
        }
        if (getBoolean("ZTE_FEATURE_RED_MAGIC", false).booleanValue()) {
            if (HighLightsUtils.CJZC_PACKAGE_NAME.equals(str) || HighLightsUtils.PUBG_PACKAGE_NAME.equals(str) || "com.tencent.iglite".equals(str) || "com.pubg.krmobile".equals(str) || "com.vng.pubgmobile".equals(str) || "com.rekoo.pubgm".equals(str) || "com.pubg.imobile".equals(str) || "com.tencent.tmgp.projectg".equals(str)) {
                i = 80;
            }
            if ("com.tencent.tmgp.dfm".equals(str) || "com.garena.game.df".equals(str) || "com.proxima.dfm".equals(str)) {
                i = 80;
            }
        }
        return Settings.Global.getInt(contentResolver, "tgk_disable_opt_" + str, i);
    }

    public static TgkGameInfo getTgkGameInfoNotApply(Context context, String str) {
        TgkGameInfo gameInfoForEnableTgk = getGameInfoForEnableTgk(context, str);
        if (isSPRDDevice() && gameInfoForEnableTgk != null && isDefaultTgkEnableGame(gameInfoForEnableTgk.gameName) && gameInfoForEnableTgk.presetTableList == null) {
            getDefaultTgkCaseListNotInsert(context, gameInfoForEnableTgk);
        }
        TgkData selectedCaseData = gameInfoForEnableTgk.getSelectedCaseData();
        Log.d(TAG, "getTgkGameInfoNotApply selectedTgkData=" + selectedCaseData);
        ContentResolver contentResolver = context.getContentResolver();
        if (selectedCaseData == null) {
            TgkData tgkData = new TgkData();
            if (getLowerVersionTgkData(contentResolver, str, tgkData, false)) {
                ArrayList<TgkData> arrayList = new ArrayList<>();
                arrayList.add(tgkData);
                gameInfoForEnableTgk.selectedTableId = 0;
                gameInfoForEnableTgk.selectedCasePosition = 0;
                gameInfoForEnableTgk.presetTableList = arrayList;
            } else {
                tgkData.mainSw = false;
            }
            Log.d(TAG, "in setTgkMapParas selectedTgkData = null");
        }
        return gameInfoForEnableTgk;
    }

    public static String getTgkLinkCaseName(int i) {
        return i == 137 ? mGameLeftKeyLinkName : i == 138 ? mGameRightKeyLinkName : mGameMiddleKeyLinkName;
    }

    public static boolean getTgkLinkCaseState(Context context, String str, long j, int i, int i2) {
        queryTgkLinkState(context.getContentResolver(), str, j, i);
        return i2 == 137 ? !mGameLeftKeyLinkFunctionEnable || TextUtils.isEmpty(mGameLeftKeyLinkName) : i2 == 138 ? !mGameRightKeyLinkFunctionEnable || TextUtils.isEmpty(mGameRightKeyLinkName) : !mGameMiddleKeyLinkFunctionEnable || TextUtils.isEmpty(mGameMiddleKeyLinkName);
    }

    public static String getTgkLinkKey(long j, int i, int i2) {
        int tableId = TgkData.getTableId(i);
        StringBuilder sb = new StringBuilder();
        sb.append(tableId);
        sb.append("_");
        sb.append(j);
        sb.append("_");
        sb.append(i2);
        Log.e("GameKeyLink", "getTgkLinkKey key=" + sb.toString());
        return sb.toString();
    }

    public static boolean getTgkLinkState(int i) {
        return i == 137 ? mGameLeftKeyLinkFunctionEnable : i == 138 ? mGameRightKeyLinkFunctionEnable : mGameMiddleKeyLinkFunctionEnable;
    }

    public static boolean getTgkMainSwState(ContentResolver contentResolver, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String string = Settings.System.getString(contentResolver, "touch_game_key_enable_game_list");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return string.contains(str + ";");
        } catch (Exception unused) {
            Log.e(TAG, "Get TGK option index failed!");
            return false;
        }
    }

    public static int getTgkOptIndex(ContentResolver contentResolver, int i, String str) {
        int i2 = 0;
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            i2 = Settings.Global.getInt(contentResolver, getTgkOptionIndexKey(i, str), 2 == i ? 7 : 0);
            Log.e(TAG, "getTgkOptIndex direction=" + i + ", index=" + i2);
            return i2;
        } catch (Exception unused) {
            Log.e(TAG, "Get TGK option index failed!");
            return i2;
        }
    }

    private static String getTgkOptionIndexKey(int i, String str) {
        return i == 0 ? "TGK_option_left_id_" + str.replace('.', '_') : 1 == i ? "TGK_option_right_id_" + str.replace('.', '_') : "TGK_option_middle_id_" + str.replace('.', '_');
    }

    private static int getTgkSensitivityValue(ContentResolver contentResolver, String str, int i) {
        int i2 = 1;
        try {
            i2 = Settings.Global.getInt(contentResolver, str + (i == 0 ? "_touch_game_key_sensitivity_l" : "_touch_game_key_sensitivity_r"), 1);
            Log.e(TAG, "getTgkOptIndex direction=" + i + ", sensitivityValue=" + i2);
            return i2;
        } catch (Exception unused) {
            Log.e(TAG, "getTouchGameKeySensitivityValue FAIL !");
            return i2;
        }
    }

    private static int getTouchHapticFeedbackState(ContentResolver contentResolver, String str) {
        try {
            return Settings.Global.getInt(contentResolver, str + "_touch_haptic_feed_back", -1);
        } catch (Exception unused) {
            Log.e(TAG, "Get tgk touch haptic feedback state failed!");
            return -1;
        }
    }

    public static int getUpdateFireCount(int i) {
        if (i < 1 || i >= 3) {
            return (i < 3 || i >= 6) ? 10 : 5;
        }
        return 2;
    }

    public static Uri getUriByTableId(int i) {
        if (i == 0) {
            return Uri.parse("content://cn.nubia.tgk.data.TgkDataProvider/preset_case_table");
        }
        if (1 == i) {
            return Uri.parse("content://cn.nubia.tgk.data.TgkDataProvider/import_case_table");
        }
        if (10 == i) {
            return Uri.parse("content://cn.nubia.tgk.data.TgkDataProvider/game_more_info_table");
        }
        if (2 == i) {
            return Uri.parse("content://cn.nubia.tgk.data.TgkDataProvider/lamp_case_table");
        }
        Log.e(TAG, "Error tableId = " + i);
        return null;
    }

    private static String getVisiblePackage(Context context) {
        Object invoke;
        Method method;
        List<Bundle> list;
        try {
            Class<?> cls = Class.forName(CLASS_NAME_ACTIVITY_EVENT);
            invoke = cls.getMethod(METHOD_NAME_INSTANCE, Context.class).invoke(null, context);
            method = cls.getMethod(METHOD_NAME_VISIBLE_PACKAGE, new Class[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (invoke != null && method != null && (list = (List) method.invoke(invoke, new Object[0])) != null && !list.isEmpty()) {
            for (Bundle bundle : list) {
                if (bundle.getInt("windowMode") == 1) {
                    String string = bundle.getString("packageName");
                    Log.d(TAG, "getVisiblePackage pkgName =" + string);
                    if (!TextUtils.equals(string, "com.tencent.mm")) {
                        return string;
                    }
                    String taskDescLabel = getTaskDescLabel(context);
                    return !TextUtils.isEmpty(taskDescLabel) ? string + "@" + taskDescLabel.hashCode() : string;
                }
            }
            Log.w(TAG, "getVisiblePackageDates empty package");
            return "";
        }
        return "";
    }

    public static boolean hasSameTgkCaseName(ContentResolver contentResolver, Long l, String str, String str2) {
        String[] strArr = {Long.toString(l.longValue()), str2, str};
        TgkData queryTgkCase = queryTgkCase(contentResolver, 1, str, "_id != ? AND show_name LIKE ? AND package_name LIKE ?", strArr);
        if (queryTgkCase == null) {
            queryTgkCase = queryTgkCase(contentResolver, 0, str, "_id != ? AND show_name LIKE ? AND package_name LIKE ?", strArr);
        }
        if (queryTgkCase != null) {
            Log.d(TAG, "hasSameTgkCaseName data =" + queryTgkCase.toString());
        }
        return queryTgkCase != null;
    }

    public static boolean hasSameTgkCaseName(TgkGameInfo tgkGameInfo, String str) {
        int i = 0;
        boolean z = false;
        while (i < 2) {
            ArrayList<TgkData> arrayList = i == 0 ? tgkGameInfo.presetTableList : tgkGameInfo.importTableList;
            if (arrayList != null) {
                int i2 = 0;
                while (true) {
                    if (i2 < arrayList.size()) {
                        TgkData tgkData = arrayList.get(i2);
                        if (tgkGameInfo.selectedCasePosition != i2 && tgkData.showName.equals(str)) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                }
            }
            i++;
        }
        return z;
    }

    public static long insertImportTgkCase(Context context, TgkData tgkData) {
        Uri insert;
        Uri uriByTableId = getUriByTableId(1);
        tgkData.state |= 8;
        ContentValues contentValuesAllData = getContentValuesAllData(tgkData);
        if (uriByTableId == null || contentValuesAllData == null || (insert = context.getContentResolver().insert(uriByTableId, contentValuesAllData)) == null) {
            return 0L;
        }
        long parseId = ContentUris.parseId(insert);
        Log.d(TAG, "insert tgk case ret=" + insert + ", insertId=" + parseId);
        return parseId;
    }

    public static long insertLampCase(ContentResolver contentResolver, int i, String str) {
        Uri insert;
        Uri uriByTableId = getUriByTableId(2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(i));
        contentValues.put("package_name", str);
        if (uriByTableId == null || (insert = contentResolver.insert(uriByTableId, contentValues)) == null) {
            return 0L;
        }
        return ContentUris.parseId(insert);
    }

    private static void insertPresetCases(ContentResolver contentResolver, ArrayList<TgkData> arrayList) {
        Iterator<TgkData> it = arrayList.iterator();
        while (it.hasNext()) {
            TgkData next = it.next();
            next.uniqueId = TgkUtils.genUniqueId(next);
            next.updateTime = System.currentTimeMillis();
            next.ID = insertTgkCase(contentResolver, 0, next);
        }
    }

    public static long insertTgkCase(ContentResolver contentResolver, int i, TgkData tgkData) {
        Uri insert;
        Uri uriByTableId = getUriByTableId(i);
        ContentValues contentValuesAllData = getContentValuesAllData(tgkData);
        if (uriByTableId == null || contentValuesAllData == null || (insert = contentResolver.insert(uriByTableId, contentValuesAllData)) == null) {
            return 0L;
        }
        return ContentUris.parseId(insert);
    }

    public static boolean is1080x2250ScreenSize() {
        return mScreenHeightInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_1080 && mScreenWidthInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_2250;
    }

    public static boolean is1080x2392ScreenSize() {
        return mScreenHeightInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_1080 && mScreenWidthInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_2392;
    }

    public static boolean is1224x2720ScreenSize() {
        return mScreenHeightInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_1224 && mScreenWidthInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_2720;
    }

    public static boolean is720x1612ScreenSize() {
        return mScreenHeightInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_720 && mScreenWidthInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_1612;
    }

    public static boolean is900x1940ScreenSize() {
        return mScreenHeightInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_900 && mScreenWidthInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_1940;
    }

    public static boolean is900x2030ScreenSize() {
        return mScreenHeightInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_900 && mScreenWidthInLandscape == SCREEN_WIDTH_OR_HEIGHT_IS_2030;
    }

    private static boolean isDefaultTgkEnableGame(String str) {
        return isSPRDDevice() && !TextUtils.isEmpty(str) && ("com.gameloft.android.GloftROLP".equals(str) || "com.gameloft.android.GloftAN2HP".equals(str) || str.contains("com.dts.freefire") || isDefaultTgkEnableInEfootballGame(str));
    }

    private static boolean isDefaultTgkEnableInEfootballGame(String str) {
        return (is1080x2250ScreenSize() || is1080x2392ScreenSize()) && "jp.konami.pesam".equals(str);
    }

    public static boolean isInValidRect(Rect rect, int i, int i2, int i3, int i4) {
        return rect != null && rect.left >= i && rect.right <= i3 && rect.top >= i2 && rect.bottom <= i4;
    }

    public static boolean isP720F03OrP820F03Device() {
        String prop = getProp("ro.product.vendor.device", "");
        boolean z = prop.contains(P720F03_DEVICE) || prop.contains(P820F03_DEVICE);
        Log.d(TAG, "isP720F03OrP820F03Device ret= " + z + ", prop = " + prop);
        return z;
    }

    public static boolean isP720F10Device() {
        String prop = getProp("ro.product.vendor.device", "");
        boolean contains = prop.contains(P720F10_DEVICE);
        Log.d(TAG, "isP720F10Device ret= " + contains + ", prop = " + prop);
        return contains;
    }

    public static boolean isP780F01Device() {
        String prop = getProp("ro.product.vendor.device", "");
        boolean contains = prop.contains(P780F01_DEVICE);
        Log.d(TAG, "isP780F01Device ret= " + contains + ", prop = " + prop);
        return contains;
    }

    public static boolean isP820F05Device() {
        String prop = getProp("ro.product.vendor.device", "");
        boolean contains = prop.contains(P820F05_DEVICE);
        Log.d(TAG, "isP820F05Device ret= " + contains + ", prop = " + prop);
        return contains;
    }

    public static boolean isSPRDDevice() {
        String prop = getProp("ro.product.vendor.device", "");
        boolean z = prop.contains(P720F03_DEVICE) || prop.contains(P820F03_DEVICE) || prop.contains(P820F05_DEVICE) || prop.contains(P780F01_DEVICE) || prop.contains(P720F10_DEVICE) || getProp("ro.vendor.feature.soc_vendor", "").equals("sprd") || getProp("ro.vendor.feature.soc_vendor", "").equals("mediatek");
        Log.d(TAG, "isSPRDDevice ret= " + z + ", prop = " + prop);
        return z;
    }

    public static boolean isShowRemind(ContentResolver contentResolver) {
        return queryTgkCase(contentResolver, 0, IS_TGK_LINK_NO_REMIND_NAME, IS_TGK_LINK_NO_REMIND_NAME) == null;
    }

    public static boolean isSmallWindowMode(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "pip_pkg");
        Log.d(TAG, "noteFreeformModeChange pipPkg:" + string);
        return (string == null || "".equals(string)) ? false : true;
    }

    public static void loadingTgkCases(ContentResolver contentResolver, TgkGameInfo tgkGameInfo) {
        int i = 0;
        while (i < 2) {
            Uri uriByTableId = getUriByTableId(i);
            ArrayList<TgkData> arrayList = null;
            if (uriByTableId != null) {
                Cursor query = contentResolver.query(uriByTableId, null, "package_name = ?", new String[]{tgkGameInfo.gameName}, i == 0 ? "_id ASC" : "_id DESC");
                if (query != null) {
                    if (query.getCount() > 0) {
                        ArrayList<TgkData> arrayList2 = new ArrayList<>();
                        while (query.moveToNext()) {
                            TgkData cursorToTgkDataNoPicture = cursorToTgkDataNoPicture(query);
                            arrayList2.add(cursorToTgkDataNoPicture);
                            if ((cursorToTgkDataNoPicture.state & 1) > 0) {
                                tgkGameInfo.selectedTableId = i;
                                tgkGameInfo.selectedCasePosition = arrayList2.size() - 1;
                            }
                        }
                        arrayList = arrayList2;
                    }
                    query.close();
                }
            }
            if (1 == i) {
                tgkGameInfo.importTableList = arrayList;
            } else {
                tgkGameInfo.presetTableList = arrayList;
            }
            i++;
        }
    }

    public static void openLampScene(int i) {
        if (IS_SUPPORT_LAMP_FUNCTION) {
            String lampCode = getLampCode(i);
            Log.d(TAG, "openLampScene code =" + lampCode);
            setShoulderConfig(lampCode);
        }
    }

    public static void openLampScene(Context context, String str) {
        if (IS_SUPPORT_LAMP_FUNCTION) {
            openLampScene(queryLameCastSelect(context, str));
        }
    }

    private static void outData(TgkData tgkData) {
        Log.d(TAG, tgkData.toString());
    }

    public static ArrayList<TgkData> queryBeChangedPresetTgkCasesToShow(ContentResolver contentResolver, String str) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(0);
        ArrayList<TgkData> arrayList = null;
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, null, "package_name = ?", new String[]{str}, "_id ASC")) != null) {
            if (query.getCount() > 0) {
                ArrayList<TgkData> arrayList2 = new ArrayList<>();
                while (query.moveToNext()) {
                    TgkData cursorToTgkDataToShow = cursorToTgkDataToShow(query);
                    if ((cursorToTgkDataToShow.state & 2) > 0) {
                        arrayList2.add(cursorToTgkDataToShow);
                    }
                }
                Log.d(TAG, "queryBeChangedPresetTgkCasesToShow tgkDataList Count=" + arrayList2.size());
                arrayList = arrayList2;
            }
            query.close();
        }
        return arrayList;
    }

    public static ArrayList<TgkData> queryImportTgkCasesToShow(ContentResolver contentResolver, String str) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(1);
        ArrayList<TgkData> arrayList = null;
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, null, "package_name = ?", new String[]{str}, "_id DESC")) != null) {
            if (query.getCount() > 0) {
                ArrayList<TgkData> arrayList2 = new ArrayList<>();
                while (query.moveToNext()) {
                    arrayList2.add(cursorToTgkDataToShow(query));
                }
                Log.d(TAG, "queryImportTgkCases tgkDataList Count=" + arrayList2.size());
                arrayList = arrayList2;
            }
            query.close();
        }
        return arrayList;
    }

    public static int queryLameCastSelect(Context context, String str) {
        Uri uriByTableId = getUriByTableId(2);
        if (uriByTableId != null) {
            try {
                Cursor query = context.getContentResolver().query(uriByTableId, null, null, null, null);
                if (query != null) {
                    try {
                        int columnIndex = query.getColumnIndex("state");
                        int columnIndex2 = query.getColumnIndex("package_name");
                        query.moveToPosition(-1);
                        while (query.moveToNext()) {
                            int i = query.getInt(columnIndex);
                            String string = query.getString(columnIndex2);
                            Log.i(TAG, "packagesName = " + string + " ;state = " + i);
                            if (str.equals(string)) {
                                if (query != null) {
                                    query.close();
                                }
                                return i;
                            }
                        }
                    } finally {
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static TgkData queryTgkCase(ContentResolver contentResolver, int i, String str, String str2) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, null, "original_name LIKE ? AND package_name LIKE ?", new String[]{str, str2}, null)) != null) {
            r7 = query.moveToNext() ? cursorToTgkDataPrecise(query) : null;
            query.close();
        }
        return r7;
    }

    public static TgkData queryTgkCase(ContentResolver contentResolver, int i, String str, String str2, String[] strArr) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, null, str2, strArr, null)) != null) {
            r7 = query.moveToNext() ? cursorToTgkDataPrecise(query) : null;
            query.close();
        }
        return r7;
    }

    public static Bitmap queryTgkCasePicture(ContentResolver contentResolver, long j) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(1);
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, new String[]{TgkDataContract.TgkEntry.TGK_CASE_PICTURE}, "_id = ?", new String[]{Long.toString(j)}, null)) != null) {
            r7 = query.moveToNext() ? byteToBitmap(query.getBlob(0)) : null;
            query.close();
        }
        return r7;
    }

    public static TgkData queryTgkCaseStatic(ContentResolver contentResolver, int i, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, strArr, str, strArr2, str2)) != null) {
            r7 = query.moveToNext() ? cursorToTgkDataPrecise(query) : null;
            query.close();
        }
        return r7;
    }

    public static String[] queryTgkCaseToShare(ContentResolver contentResolver, int i, String str, String str2, String str3) {
        Cursor query;
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null && (query = contentResolver.query(uriByTableId, null, str2 + " LIKE ? AND package_name LIKE ?", new String[]{str3, str}, null)) != null) {
            r7 = query.moveToNext() ? cursorToString(query) : null;
            query.close();
        }
        return r7;
    }

    public static ArrayList<TgkData> queryTgkCasesByResourceSettings(ContentResolver contentResolver, String str) {
        ArrayList<TgkData> queryImportTgkCasesToShow = queryImportTgkCasesToShow(contentResolver, str);
        ArrayList<TgkData> queryBeChangedPresetTgkCasesToShow = queryBeChangedPresetTgkCasesToShow(contentResolver, str);
        if (queryImportTgkCasesToShow == null) {
            return queryBeChangedPresetTgkCasesToShow;
        }
        if (queryBeChangedPresetTgkCasesToShow == null) {
            return queryImportTgkCasesToShow;
        }
        queryImportTgkCasesToShow.addAll(queryBeChangedPresetTgkCasesToShow);
        return queryImportTgkCasesToShow;
    }

    public static ArrayList<TgkData> queryTgkCasesNoPicture(ContentResolver contentResolver, int i, String str) {
        Uri uriByTableId = getUriByTableId(i);
        ArrayList<TgkData> arrayList = null;
        if (uriByTableId != null) {
            Cursor query = contentResolver.query(uriByTableId, null, "package_name = ?", new String[]{str}, i == 0 ? "_id ASC" : "_id DESC");
            if (query != null) {
                if (query.getCount() > 0) {
                    ArrayList<TgkData> arrayList2 = new ArrayList<>();
                    while (query.moveToNext()) {
                        arrayList2.add(cursorToTgkDataNoPicture(query));
                    }
                    Log.d(TAG, "queryTgkCasesNoPicture Count=" + arrayList2.size());
                    arrayList = arrayList2;
                }
                query.close();
            }
        }
        return arrayList;
    }

    public static TgkGameInfo queryTgkGameMoreInfo(ContentResolver contentResolver, String str) {
        TgkGameInfo tgkGameInfo = new TgkGameInfo(str);
        Uri uriByTableId = getUriByTableId(10);
        if (uriByTableId != null) {
            Cursor query = contentResolver.query(uriByTableId, null, "package_name = ?", new String[]{str}, null);
            if (query != null) {
                if (query.moveToNext()) {
                    tgkGameInfo.topVisualEffectSw = query.getInt(1) == 1;
                    tgkGameInfo.centerVisualEffectSw = query.getInt(2) == 1;
                    tgkGameInfo.centerVisualEffectTransparency = query.getInt(3);
                }
                query.close();
            }
        }
        return tgkGameInfo;
    }

    public static void queryTgkGameMoreInfo(ContentResolver contentResolver, TgkGameInfo tgkGameInfo) {
        Uri uriByTableId = getUriByTableId(10);
        if (uriByTableId != null) {
            Cursor query = contentResolver.query(uriByTableId, null, "package_name = ?", new String[]{tgkGameInfo.gameName}, null);
            if (query != null) {
                if (query.moveToNext()) {
                    tgkGameInfo.topVisualEffectSw = query.getInt(1) == 1;
                    tgkGameInfo.centerVisualEffectSw = query.getInt(2) == 1;
                    tgkGameInfo.centerVisualEffectTransparency = query.getInt(3);
                } else {
                    contentResolver.insert(uriByTableId, getContentValuesByMoreInfo(tgkGameInfo.gameName, tgkGameInfo.topVisualEffectSw, tgkGameInfo.centerVisualEffectSw, tgkGameInfo.centerVisualEffectTransparency));
                }
                query.close();
            }
        }
    }

    public static void queryTgkLinkState(ContentResolver contentResolver, String str, long j, int i) {
        try {
            Bundle bundle = new Bundle();
            Log.d(TAG, "queryTgkLinkState packageName=" + str);
            String tgkLinkKey = getTgkLinkKey(j, i, 138);
            bundle.putString("packageName", "" + str);
            bundle.putString("touch_key_name", tgkLinkKey);
            Bundle call = contentResolver.call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "hasTouchLink", bundle);
            mGameRightKeyLinkFunctionEnable = call.getBoolean("hasTouchLink");
            mGameRightKeyLinkName = call.getString("hasTouchLinkName");
            Log.d(TAG, " RightKey link enable=" + mGameRightKeyLinkFunctionEnable + ";mGameRightKeyLinkName=" + mGameRightKeyLinkName);
            bundle.putString("touch_key_name", getTgkLinkKey(j, i, 137));
            Bundle call2 = contentResolver.call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "hasTouchLink", bundle);
            mGameLeftKeyLinkFunctionEnable = call2.getBoolean("hasTouchLink");
            mGameLeftKeyLinkName = call2.getString("hasTouchLinkName");
            Log.d(TAG, " LeftKey link enable=" + mGameLeftKeyLinkFunctionEnable + ";mGameLeftKeyLinkName=" + mGameLeftKeyLinkName);
            if (IS_SUPPORT_MIDDLE_TGK) {
                bundle.putString("touch_key_name", getTgkLinkKey(j, i, 136));
                Bundle call3 = contentResolver.call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "hasTouchLink", bundle);
                mGameMiddleKeyLinkFunctionEnable = call3.getBoolean("hasTouchLink");
                mGameMiddleKeyLinkName = call3.getString("hasTouchLinkName");
                Log.d(TAG, " middlekey link enable=" + mGameMiddleKeyLinkFunctionEnable + ";mGameMiddleKeyLinkName=" + mGameMiddleKeyLinkName);
            }
        } catch (Exception e) {
            Log.e("GameKeyLink", "Exception=" + e.toString());
        }
    }

    public static String rectToString(Rect[] rectArr) {
        int length = rectArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(rectArr[i].left);
            stringBuffer.append("|");
            stringBuffer.append(rectArr[i].top);
            stringBuffer.append("|");
            stringBuffer.append(rectArr[i].right);
            stringBuffer.append("|");
            stringBuffer.append(rectArr[i].bottom);
            if (i < length - 1) {
                stringBuffer.append("|");
            }
        }
        return stringBuffer.toString();
    }

    public static void reportClickMoreButton(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gamelauncher");
        bundle.putString("event_name", "gamespace_touch_button_advanced");
        bundle.putString("action_type", "app_name exset");
        String appName = getAppName(context, str);
        bundle.putString(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, appName + " " + (mClickMoreButtonStatus ? "on" : "off"));
        if (DEBUG) {
            Log.e(TAG, "reportClickMoreButton mClickMoreButtonStatus=" + mClickMoreButtonStatus + ";appName=" + appName);
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    public static void reportClickTgkCaseButton(Context context, String str) {
        if (DEBUG) {
            Log.e(TAG, "reportClickTgkCaseButton gameName=" + str);
        }
        Bundle bundle = new Bundle();
        bundle.putString("event_name", "touch_button_plan_switch");
        String appName = getAppName(context, str);
        bundle.putString("app_name", appName);
        if (DEBUG) {
            Log.e(TAG, "reportClickTgkCaseButton appName=" + appName);
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    public static void reportDataInit(Context context) {
        NubiaTrackManager.getInstance().init(context);
    }

    public static void reportTgkData(Context context, TgkGameInfo tgkGameInfo) {
        String[] strArr = new String[14];
        strArr[0] = getAppName(context, tgkGameInfo.gameName);
        Log.d(TAG, "reportTgkData acitonValue[0] =" + strArr[0]);
        strArr[1] = tgkGameInfo.gameName;
        TgkData selectedCaseData = tgkGameInfo.getSelectedCaseData();
        strArr[2] = getSwString(selectedCaseData.optionSwArray[0]);
        strArr[3] = getModeString(selectedCaseData.optionArray[0]);
        strArr[4] = getSwString(selectedCaseData.optionSwArray[1]);
        strArr[5] = getModeString(selectedCaseData.optionArray[1]);
        strArr[6] = getSwString(selectedCaseData.optionSwArray[2]);
        strArr[7] = 7 > selectedCaseData.optionArray[2] ? "click" : "slide";
        strArr[8] = getModeString(selectedCaseData.optionArray[2]);
        String[] strArr2 = {"low", "medium", "high"};
        strArr[9] = strArr2[selectedCaseData.sensitivityArray[0]] + "-" + strArr2[selectedCaseData.sensitivityArray[1]];
        strArr[10] = String.valueOf(5);
        strArr[11] = tgkGameInfo.importTableList == null ? "0" : String.valueOf(tgkGameInfo.importTableList.size());
        strArr[12] = getSwString(tgkGameInfo.topVisualEffectSw);
        strArr[13] = getSwString(tgkGameInfo.centerVisualEffectSw);
        reportTgkDataImpl(strArr);
    }

    private static void reportTgkDataImpl(String[] strArr) {
        String[] strArr2 = {"app_name", "package_name", "L_switch_status", "L_option", "R_switch_status", "R_option", "M_switch_status", "M_function", "M_option", "sensitivity", "customize_plans_num", "download_plans_num", "trigger_position_light", "trigger_display"};
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gamelauncher");
        bundle.putString("event_name", "gamespace_touch_button_situation");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            if (IS_SUPPORT_MIDDLE_TGK || 6 > i || i > 8) {
                sb.append(strArr2[i]);
                sb2.append(strArr[i]);
                if (i != 13) {
                    sb.append(" ");
                    sb2.append(" ");
                }
            }
        }
        bundle.putString("action_type", sb.toString());
        bundle.putString(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, sb2.toString());
        bundle.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
        if (DEBUG) {
            Log.e(TAG, "reportTgkData actionSb=" + sb.toString());
        }
        if (DEBUG) {
            Log.e(TAG, "reportTgkData valueSb=" + sb2.toString());
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    public static boolean requestColorfulLight(int i) {
        if (IS_SUPPORT_LAMP_FUNCTION) {
            try {
                Log.d(TAG, "requestColorfulLight known=" + i);
                Method declaredMethod = Class.forName(NUBIA_COLORFULLIGHT_MANAGER).getDeclaredMethod("requestColorfulLight", Integer.TYPE);
                declaredMethod.setAccessible(true);
                return ((Boolean) declaredMethod.invoke(null, Integer.valueOf(i))).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void setClickMoreButtonStatus(Boolean bool) {
        mClickMoreButtonStatus = bool.booleanValue();
    }

    public static void setNoRemindStatus(ContentResolver contentResolver) {
        insertTgkCase(contentResolver, 0, new TgkData(IS_TGK_LINK_NO_REMIND_NAME, 100));
    }

    private static void setSensitivityArray(ContentResolver contentResolver, String str, int[] iArr) {
        iArr[0] = getTgkSensitivityValue(contentResolver, str, 0);
        iArr[1] = getTgkSensitivityValue(contentResolver, str, 1);
    }

    public static boolean setShoulderConfig(String str) {
        if (IS_SUPPORT_LAMP_FUNCTION) {
            try {
                Log.d(TAG, "setShoulderConfig config=" + str);
                Method declaredMethod = Class.forName(NUBIA_COLORFULLIGHT_MANAGER).getDeclaredMethod("setShoulderConfig", String.class);
                declaredMethod.setAccessible(true);
                return ((Boolean) declaredMethod.invoke(null, str)).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void setTgkCaseShowStates(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        String str2 = "tgk_case_show_status_" + str;
        if (z) {
            edit.putBoolean(str2, true);
        } else {
            edit.remove(str2);
        }
        edit.apply();
    }

    public static void setTgkCaseViewPostion(Context context, String str, int[] iArr) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        edit.putString("tgk_case_view_postion_" + str, iArr[0] + "," + iArr[1]);
        edit.apply();
    }

    private static void setTgkFloatViewPosition(ContentResolver contentResolver, String str, int[] iArr, Rect[][] rectArr) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String[] floatViewDataKey = getFloatViewDataKey(str, iArr);
            String string = Settings.System.getString(contentResolver, floatViewDataKey[0]);
            String string2 = Settings.System.getString(contentResolver, floatViewDataKey[1]);
            String string3 = Settings.System.getString(contentResolver, floatViewDataKey[2]);
            Rect[] stringToRect = stringToRect(string);
            Rect[] stringToRect2 = stringToRect(string2);
            Rect[] stringToRect3 = stringToRect(string3);
            if (stringToRect == null) {
                stringToRect = getDefaultFVPos(str, iArr[0], 0);
            }
            Rect rect = stringToRect[0];
            if (rect != null) {
                rectArr[0][0] = rect;
            }
            Rect rect2 = stringToRect[1];
            if (rect2 != null) {
                rectArr[0][1] = rect2;
            }
            if (stringToRect2 == null) {
                stringToRect2 = getDefaultFVPos(str, iArr[1], 1);
            }
            Rect rect3 = stringToRect2[0];
            if (rect3 != null) {
                rectArr[1][0] = rect3;
            }
            Rect rect4 = stringToRect2[1];
            if (rect4 != null) {
                rectArr[1][1] = rect4;
            }
            if (stringToRect3 == null) {
                stringToRect3 = getDefaultFVPos(str, iArr[2], 2);
            }
            Rect rect5 = stringToRect3[0];
            if (rect5 != null) {
                rectArr[2][0] = rect5;
            }
            Rect rect6 = stringToRect3[1];
            if (rect6 != null) {
                rectArr[2][1] = rect6;
            }
        } catch (Exception unused) {
            Log.d(TAG, "getFloatViewPositionV4 failed!");
        }
    }

    private static void setTgkOptArray(ContentResolver contentResolver, String str, int[] iArr) {
        iArr[0] = getTgkOptIndex(contentResolver, 0, str);
        iArr[1] = getTgkOptIndex(contentResolver, 1, str);
        iArr[2] = getTgkOptIndex(contentResolver, 2, str);
    }

    private static void setTgkOptSwArray(ContentResolver contentResolver, String str, boolean[] zArr) {
        int fingerTouchNum = getFingerTouchNum(contentResolver, str);
        zArr[0] = (fingerTouchNum & 16) > 0;
        zArr[1] = (fingerTouchNum & 32) > 0;
        zArr[2] = (fingerTouchNum & 128) > 0;
    }

    public static void setTgkParaToNative(InputManagerProxy inputManagerProxy, TgkData tgkData, TgkGameInfo tgkGameInfo, int i) {
        if (tgkData.mainSw) {
            setTgkParaToNativeEnable(inputManagerProxy, tgkData, tgkGameInfo, i);
        } else {
            setTgkParaToNativeDisableEnable(inputManagerProxy);
        }
    }

    public static void setTgkParaToNativeDisableEnable(InputManagerProxy inputManagerProxy) {
        if (inputManagerProxy != null) {
            inputManagerProxy.setTouchHapticFeedbackEnable(false);
            inputManagerProxy.setTgkTopEffectEnable(false);
            inputManagerProxy.setTgkCenterEffectEnable(false);
            inputManagerProxy.setLeftTgkEnable(false);
            inputManagerProxy.setRightTgkEnable(false);
            inputManagerProxy.setMiddleTgkEnable(false);
            inputManagerProxy.setDefaultGameKeyLinkFunctionEnable(0);
            inputManagerProxy.setGameKeyEnable(false);
        }
    }

    public static void setTgkParaToNativeEnable(InputManagerProxy inputManagerProxy, TgkData tgkData, TgkGameInfo tgkGameInfo, int i) {
        if (inputManagerProxy == null || tgkData == null) {
            return;
        }
        inputManagerProxy.setDefaultGameKeyLinkFunctionEnable(0);
        inputManagerProxy.setGameKeyEnable(true);
        updateValidRect(tgkData, i);
        inputManagerProxy.sendTgkRectsToNative(tgkData.pointsArray);
        int[] iArr = {137, 138, 136};
        for (int i2 = 0; i2 < TGK_COUNT; i2++) {
            if (tgkData.optionSwArray[i2]) {
                int i3 = iArr[i2];
                int i4 = tgkData.optionArray[i2];
                if (4 == i4) {
                    inputManagerProxy.setGameKeyLinkFunctionEnable(i3);
                } else if (6 == i4) {
                    inputManagerProxy.setTgkRapidFireCount(tgkData.rapidFireCountArray[i2], i3);
                }
                inputManagerProxy.setTgkMode(i4, i3);
            }
        }
        if (tgkData.optionSwArray[0]) {
            inputManagerProxy.setLeftTgkEnable(true);
            inputManagerProxy.setTgkSensitivity(tgkData.sensitivityArray[0], 137);
        } else {
            inputManagerProxy.setLeftTgkEnable(false);
        }
        if (tgkData.optionSwArray[1]) {
            inputManagerProxy.setRightTgkEnable(true);
            inputManagerProxy.setTgkSensitivity(tgkData.sensitivityArray[1], 138);
        } else {
            inputManagerProxy.setRightTgkEnable(false);
        }
        if (IS_SUPPORT_MIDDLE_TGK && tgkData.optionSwArray[2]) {
            inputManagerProxy.setMiddleTgkEnable(true);
        } else {
            inputManagerProxy.setMiddleTgkEnable(false);
        }
        inputManagerProxy.setTouchHapticFeedbackEnable(tgkData.vibrateSw);
        if (tgkGameInfo != null) {
            inputManagerProxy.setTgkTopEffectEnable(tgkGameInfo.topVisualEffectSw);
            inputManagerProxy.setTgkCenterEffectEnable(tgkGameInfo.centerVisualEffectSw);
            inputManagerProxy.setTgkTransparency(tgkGameInfo.centerVisualEffectTransparency);
        }
    }

    public static void setTgkParaToNativeForEnableTgk(Context context, String str, InputManagerProxy inputManagerProxy, TgkData tgkData, TgkGameInfo tgkGameInfo, int i) {
        if (!tgkData.mainSw) {
            setTgkParaToNativeDisableEnable(inputManagerProxy);
            openLampScene(0);
            return;
        }
        if (!TgkFeatureUtil.isTgkSupportPortrait().booleanValue()) {
            setTgkParaToNativeEnable(inputManagerProxy, tgkData, tgkGameInfo, i);
            reportTgkData(context, tgkGameInfo);
            openLampScene(context, str);
        } else if (!TgkFeatureUtil.isSupportTgkPortraitLandscapeEnable().booleanValue()) {
            setTgkParaToNativeEnable(inputManagerProxy, tgkData, tgkGameInfo, i);
            reportTgkData(context, tgkGameInfo);
            openLampScene(context, str);
        } else if (tgkData.getIsLandscape() != i) {
            setTgkParaToNativeDisableEnable(inputManagerProxy);
            openLampScene(0);
        } else {
            setTgkParaToNativeEnable(inputManagerProxy, tgkData, tgkGameInfo, i);
            reportTgkData(context, tgkGameInfo);
            openLampScene(context, str);
        }
    }

    private static void setTouchHapticFeedbackState(ContentResolver contentResolver, String str) {
        try {
            Settings.Global.putInt(contentResolver, str + "_touch_haptic_feed_back", -1);
        } catch (Exception unused) {
            Log.e(TAG, "set tgk touch haptic feedback state failed!");
        }
    }

    public static void setUseMultCaseStates(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        edit.putBoolean(TGK_USE_MULT_CASE_STATUS, z);
        edit.apply();
    }

    public static void shellSort(int[] iArr) {
        int length = iArr.length;
        for (int i = length / 2; i > 0; i /= 2) {
            for (int i2 = i; i2 < length; i2++) {
                int i3 = iArr[i2];
                int i4 = i2;
                while (i4 >= i) {
                    int i5 = i4 - i;
                    int i6 = iArr[i5];
                    if (i6 > i3) {
                        iArr[i4] = i6;
                        i4 = i5;
                    }
                }
                iArr[i4] = i3;
            }
        }
    }

    public static Rect[] stringToRect(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("|")) {
            return null;
        }
        String[] split = str.split("\\|");
        Rect[] rectArr = new Rect[split.length / 4];
        int i = 0;
        while (true) {
            int i2 = i * 4;
            if (i2 >= split.length) {
                return rectArr;
            }
            Rect rect = new Rect();
            rect.set(Integer.parseInt(split[i2]), Integer.parseInt(split[i2 + 1]), Integer.parseInt(split[i2 + 2]), Integer.parseInt(split[i2 + 3]));
            rectArr[i] = rect;
            i++;
        }
    }

    public static void updateGameInfo(ContentResolver contentResolver, TgkGameInfo tgkGameInfo) {
        updateTgkCaseList(contentResolver, tgkGameInfo.presetTableList, tgkGameInfo.importTableList);
        updateGameMoreInfo(contentResolver, tgkGameInfo);
    }

    public static void updateGameMoreInfo(ContentResolver contentResolver, TgkGameInfo tgkGameInfo) {
        Uri uriByTableId = getUriByTableId(10);
        if (uriByTableId != null) {
            contentResolver.update(uriByTableId, getContentValuesByMoreInfo(null, tgkGameInfo.topVisualEffectSw, tgkGameInfo.centerVisualEffectSw, tgkGameInfo.centerVisualEffectTransparency), "package_name=?", new String[]{tgkGameInfo.gameName});
        }
    }

    public static void updateLampCase(ContentResolver contentResolver, int i, String str) {
        Uri uriByTableId = getUriByTableId(2);
        if (uriByTableId != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", Integer.valueOf(i));
            contentValues.put("package_name", str);
            Log.d(TAG, "updateLampCase int ret=" + contentResolver.update(uriByTableId, contentValues, null, null));
        }
    }

    public static void updateTgkCase(ContentResolver contentResolver, int i, long j, String str, int i2) {
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(str, Integer.valueOf(i2));
            Log.d(TAG, "updateTgkCase int ret=" + contentResolver.update(uriByTableId, contentValues, "_id=?", new String[]{Long.toString(j)}));
        }
    }

    public static void updateTgkCase(ContentResolver contentResolver, int i, long j, String str, String str2) {
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(str, str2);
            Log.d(TAG, "updateTgkCase String ret=" + contentResolver.update(uriByTableId, contentValues, "_id=?", new String[]{Long.toString(j)}));
        }
    }

    public static void updateTgkCaseAllData(ContentResolver contentResolver, int i, TgkData tgkData) {
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null) {
            Log.d(TAG, "updateTgkCaseStatic ret=" + contentResolver.update(uriByTableId, getContentValuesAllData(tgkData), "_id=?", new String[]{Long.toString(tgkData.ID)}));
        }
    }

    public static void updateTgkCaseExceptPicture(ContentResolver contentResolver, int i, TgkData tgkData) {
        Uri uriByTableId = getUriByTableId(i);
        if (uriByTableId != null) {
            Log.d(TAG, "updateTgkCase String ret=" + contentResolver.update(uriByTableId, getContentValuesByTgkDataNoPicture(tgkData), "_id=?", new String[]{Long.toString(tgkData.ID)}));
        }
    }

    private static void updateTgkCaseList(ContentResolver contentResolver, ArrayList<TgkData> arrayList, ArrayList<TgkData> arrayList2) {
        Iterator<TgkData> it = arrayList.iterator();
        while (it.hasNext()) {
            updateTgkCaseExceptPicture(contentResolver, 0, it.next());
        }
        if (arrayList2 != null) {
            Iterator<TgkData> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                updateTgkCaseExceptPicture(contentResolver, 1, it2.next());
            }
        }
    }

    public static void updateValidRect(TgkData tgkData, int i) {
        if (tgkData != null) {
            for (int i2 = 0; i2 < TGK_COUNT; i2++) {
                for (int i3 = 0; i3 < 2; i3++) {
                    Rect rect = tgkData.pointsArray[i2][i3];
                    if (rect != null) {
                        if (!isInValidRect(rect, 0, 0, i == 1 ? mScreenWidthInLandscape : mScreenHeightInLandscape, i == 1 ? mScreenHeightInLandscape : mScreenWidthInLandscape)) {
                            Log.d(TAG, "!isInValidRect");
                            int i4 = rect.right - rect.left;
                            int i5 = rect.bottom - rect.top;
                            if (i3 == 0) {
                                tgkData.pointsArray[i2][i3] = new Rect((i == 1 ? DEFAULT_TGK_POINT_1_LANDSCAPE[i2][0] : DEFAULT_TGK_POINT_1_PORTRAIT[i2][0]) - (i4 / 2), (i == 1 ? DEFAULT_TGK_POINT_1_LANDSCAPE[i2][1] : DEFAULT_TGK_POINT_1_PORTRAIT[i2][1]) - (i5 / 2), (i == 1 ? DEFAULT_TGK_POINT_1_LANDSCAPE[i2][0] : DEFAULT_TGK_POINT_1_PORTRAIT[i2][0]) + (i4 / 2), (i == 1 ? DEFAULT_TGK_POINT_1_LANDSCAPE[i2][1] : DEFAULT_TGK_POINT_1_PORTRAIT[i2][1]) + (i5 / 2));
                            } else {
                                tgkData.pointsArray[i2][i3] = new Rect((i == 1 ? DEFAULT_TGK_POINT_2_LANDSCAPE[i2][0] : DEFAULT_TGK_POINT_2_PORTRAIT[i2][0]) - (i4 / 2), (i == 1 ? DEFAULT_TGK_POINT_2_LANDSCAPE[i2][1] : DEFAULT_TGK_POINT_2_PORTRAIT[i2][1]) - (i5 / 2), (i == 1 ? DEFAULT_TGK_POINT_2_LANDSCAPE[i2][0] : DEFAULT_TGK_POINT_2_PORTRAIT[i2][0]) + (i4 / 2), (i == 1 ? DEFAULT_TGK_POINT_2_LANDSCAPE[i2][1] : DEFAULT_TGK_POINT_2_PORTRAIT[i2][1]) + (i5 / 2));
                            }
                        }
                    }
                }
            }
        }
    }
}
