package cn.nubia.gamecenter.settings.records;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.MediaStore;
import android.widget.ImageView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.recordsdb.RTimeDataBaseHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.signature.StringSignature;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class VideoListUtil {
    public static final String GAME_BLZY_WONDERFUL = "/storage/emulated/0/精彩时刻/堡垒之夜";
    public static final String GAME_CJZC = "/storage/emulated/0/红魔时刻/和平精英";
    public static final String GAME_CJZC_WONDERFUL = "/storage/emulated/0/精彩时刻/和平精英";
    public static final String GAME_LOL = "/storage/emulated/0/红魔时刻/英雄联盟";
    public static final String GAME_PUBG = "/storage/emulated/0/红魔时刻/PUBGMOBILE";
    public static final String GAME_PUBG_WONDERFUL = "/storage/emulated/0/精彩时刻/PUBGMOBILE";
    public static final String GAME_WZRY = "/storage/emulated/0/红魔时刻/王者荣耀";
    public static final String GAME_WZRY_WONDERFUL = "/storage/emulated/0/精彩时刻/王者荣耀";
    public static final String HIGH_LIGHTS_PATH = "/storage/emulated/0/红魔时刻/";
    public static final String HPJY_NAME = "和平精英";
    public static final String IMAGE_PATH = "/storage/emulated/0/Pictures/Redmagic Time Screenshot";
    public static final String INTERNAL_REDMAGIC_TIME_PATH = "/storage/emulated/0/Red Magic Moment";
    public static final String INTERNAL_WONDERFUL_TIME_PATH = "/storage/emulated/0/Wonderful Time";
    public static final String LOL_NAME = "英雄联盟手游";
    public static final String REDMAGIC_TIME_PATH = "/storage/emulated/0/红魔时刻";
    private static final String TAG = "RecordsFragment";
    public static final int TWIN_USERID = 999;
    public static final String WONDERFUL_PATH = "/storage/emulated/0/精彩时刻/";
    public static final String WONDERFUL_TIME_PATH = "/storage/emulated/0/精彩时刻";
    public static final String WZRY_NAME = "王者荣耀";
    public static final String ZTE_IMAGE_PATH = "/storage/emulated/0/Pictures/Game Space Screenshot";
    public static final String[] IMAGE_PATH_ARRAY = {"/storage/emulated/0/Pictures/Game Space Screenshot/%", "/storage/emulated/0/Pictures/Redmagic Time Screenshot/%"};
    private static String GAME_MANUAL = HighLightsUtils.GAME_MANUAL;
    private static String WZRY_PACKAGE_NAME = HighLightsUtils.WZRY_PACKAGE_NAME;
    private static String CJZC_PACKAGE_NAME = HighLightsUtils.CJZC_PACKAGE_NAME;
    private static String PUBG_PACKAGE_NAME = HighLightsUtils.PUBG_PACKAGE_NAME;
    private static String LOL_PACKAGE_NAME = HighLightsUtils.LOL_PACKAGE_NAME;
    private static String CF_PACKAGE_NAME = "com.tencent.tmgp.cf";
    private static String ZTE_INTERNAL_KEY = "ro.vendor.mifavor.custom";
    private static String ZTE_INTERNAL_VALUE = "abroad";
    private static int NEW_PATH_ARRAY_LENGTH = 6;
    private static ArrayList<String> mPathList = new ArrayList<>();

    public static String getApplicationLabel(String str, Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(str, 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFormatDuration(int i) {
        return new SimpleDateFormat("mm:ss").format(Integer.valueOf(i));
    }

    public static String getGameName(String str, String str2) {
        return (WZRY_PACKAGE_NAME.equals(str) || CJZC_PACKAGE_NAME.equals(str) || PUBG_PACKAGE_NAME.equals(str) || LOL_PACKAGE_NAME.equals(str) || CF_PACKAGE_NAME.equals(str)) ? str2 : str;
    }

    public static String getHighPath() {
        return (CommonUtil.isRedMagicRunOnMyOs() || isRedMagicPad() || HighLightsUtils.isNubiaOS() || HighLightsUtils.isRedMagic()) ? isInternal() ? "/storage/emulated/0/Red Magic Moment" : "/storage/emulated/0/红魔时刻" : isInternal() ? "/storage/emulated/0/Wonderful Time" : "/storage/emulated/0/精彩时刻";
    }

    public static String[] getImageDataPath() {
        return IMAGE_PATH_ARRAY;
    }

    private static String getNewVideoDataPath() {
        return getHighPath();
    }

    private static String getVideoDataPath(String str) {
        return HighLightsUtils.WZRY_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/王者荣耀" : HighLightsUtils.CJZC_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/和平精英" : HighLightsUtils.PUBG_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/PUBGMOBILE" : HighLightsUtils.LOL_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/英雄联盟" : str;
    }

    public static boolean isImageExist(Context context, String str) {
        return context != null && isImageFilesExist(context, getImageDataPath(), str);
    }

    private static boolean isImageFilesExist(Context context, String[] strArr, String str) {
        boolean z = false;
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_size", "title"}, new String("_data like ? or _data like ?"), strArr, null, null);
            while (true) {
                if (!query.moveToNext()) {
                    break;
                }
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                if (!isSafePathName(string)) {
                    break;
                }
                if (string.contains(str)) {
                    z = true;
                    break;
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public static boolean isImageFilsExist(Context context, String str, String str2) {
        new ArrayList();
        LogUtil.d(TAG, "******isImageFilsExist imagePath =" + str + ", mGameName =" + str2);
        boolean z = false;
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_size", "title"}, new String("_data like ?"), new String[]{str + "/%"}, null);
            while (true) {
                if (!query.moveToNext()) {
                    break;
                }
                String string = query.getString(query.getColumnIndexOrThrow("title"));
                LogUtil.d(TAG, "******isImageFilsExist title =" + string);
                if (string.contains(str2)) {
                    z = true;
                    break;
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public static boolean isInternal() {
        return Utils.isInternalVersion() || isZteInternal();
    }

    private static boolean isManualVideoFilsExist(Context context, String str) {
        Cursor query;
        try {
            query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "date_modified", "title", "duration"}, "_data like ? AND duration <> 0", new String[]{GAME_MANUAL + "/%"}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query == null) {
            return false;
        }
        while (query.moveToNext()) {
            mPathList.add(query.getString(query.getColumnIndexOrThrow("_data")));
        }
        query.moveToPosition(-1);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndexOrThrow("_data"));
            if (isSafePathName(string) && string.contains(str)) {
                query.close();
                return true;
            }
        }
        query.close();
        return false;
    }

    public static boolean isNewPathVideoExist(Context context, String str, String str2) {
        if (context == null || str == null) {
            return false;
        }
        return isNewVideoFilesExist(context, getNewVideoDataPath(), getGameName(str, str2));
    }

    public static boolean isNewVideoFilesExist(Context context, String str, String str2) {
        Cursor query;
        mPathList.clear();
        LogUtil.d(TAG, "******isNewVideoFilesExist videoPath =" + str);
        boolean z = false;
        try {
            query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "date_modified", "title", "duration"}, "_data like ? AND duration <> 0", new String[]{str + "/%"}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query == null) {
            return false;
        }
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndexOrThrow("_data"));
            if (isSafePathName(string) && string.split("/").length == NEW_PATH_ARRAY_LENGTH) {
                mPathList.add(string);
                if (string.contains(str2)) {
                    z = true;
                }
            }
        }
        query.close();
        return z;
    }

    public static boolean isNotPreviewedExist(Context context, String str) {
        Cursor query;
        ArrayList<String> arrayList;
        if (!isVideoExist(context, str, getApplicationLabel(str, context))) {
            return true;
        }
        boolean z = false;
        try {
            query = context.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_NOT_NOTIFY_URI, new String[]{"path", "isPreview"}, "packageName like ?", new String[]{str + "%"}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query == null) {
            return true;
        }
        while (true) {
            if (!query.moveToNext()) {
                break;
            }
            int i = query.getInt(query.getColumnIndexOrThrow("isPreview"));
            String string = query.getString(query.getColumnIndexOrThrow("path"));
            if (!isSafePathName(string)) {
                break;
            }
            if (i == 0 && (arrayList = mPathList) != null && arrayList.contains(string)) {
                z = true;
                break;
            }
        }
        query.close();
        return !z;
    }

    public static boolean isNubiaOS() {
        return "nubia".equals(SystemProperties.get("ro.build.user", "nubia"));
    }

    public static boolean isRedMagicPad() {
        try {
            return "CN_P898P02".equals(SystemProperties.get("ro.product.name"));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isRedMagicTimeExist(Context context, String str, String str2) {
        return isVideoExist(context, str2, str) || isImageExist(context, str2);
    }

    public static boolean isSafePathName(String str) {
        if (str.contains("../") || str.contains("./") || str.contains("~/")) {
            return false;
        }
        return str.startsWith("/storage") || str.startsWith("/data") || str.startsWith("file:") || str.startsWith("content:");
    }

    public static boolean isVideoExist(Context context, String str, String str2) {
        if (context == null || str == null) {
            return false;
        }
        boolean isNewPathVideoExist = isNewPathVideoExist(context, str, str2);
        String videoDataPath = getVideoDataPath(str);
        if (videoDataPath == null) {
            videoDataPath = Build.DEVICE.contains("NX627") ? WONDERFUL_PATH + str + "/" : HIGH_LIGHTS_PATH + str + "/";
        }
        return isNewPathVideoExist || isVideoFilsExist(context, videoDataPath, str);
    }

    public static boolean isVideoFilsExist(Context context, String str, String str2) {
        Cursor query;
        LogUtil.d(TAG, "******isVideoFilsExist videoPath =" + str);
        boolean z = false;
        try {
            query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "date_modified", "title", "duration"}, "_data like ? AND duration <> 0", new String[]{str + "/%"}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query == null) {
            return false;
        }
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndexOrThrow("_data"));
            if (isSafePathName(string)) {
                mPathList.add(string);
            }
        }
        query.moveToPosition(-1);
        if (query.moveToNext()) {
            query.close();
            z = true;
        } else {
            query.close();
        }
        if (isManualVideoFilsExist(context, str2)) {
            return true;
        }
        return z;
    }

    public static boolean isZteInternal() {
        return ZTE_INTERNAL_VALUE.equals(SystemProperties.get(ZTE_INTERNAL_KEY, "0"));
    }

    public static List<HighLightsDb> queryImageFile(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, "_data like ? or _data like ?", strArr, "date_added DESC");
            while (query.moveToNext()) {
                query.getString(query.getColumnIndexOrThrow("_id"));
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                if (isSafePathName(string) && string != null) {
                    arrayList.add(new HighLightsDb(string, 0));
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<VideoFile> queryImageFils(Context context, String[] strArr, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "date_added", "_size", "title"}, new String("_data like ? or _data like ?"), strArr, "date_added DESC");
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndexOrThrow("_id"));
                String string2 = query.getString(query.getColumnIndexOrThrow("_data"));
                if (isSafePathName(string2)) {
                    String string3 = query.getString(query.getColumnIndexOrThrow("title"));
                    LogUtil.d(TAG, "******queryImageFils title =" + string3);
                    VideoFile videoFile = new VideoFile(string2, Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, string), "", 0);
                    if (string3.contains(str)) {
                        arrayList.add(videoFile);
                    }
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<HighLightsDb> queryRedMagicTime(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        try {
            Cursor query = context.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, null, null, null, null);
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndexOrThrow("path"));
                if (isSafePathName(string)) {
                    query.getInt(query.getColumnIndexOrThrow("isPreview"));
                    if (string != null) {
                        arrayList.add(new HighLightsDb(string, 0));
                    }
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static HashMap<String, Integer> queryRedMagicTimeHashMap(Context context) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            Cursor query = context.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, null, null, null, null);
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndexOrThrow("path"));
                if (isSafePathName(string)) {
                    hashMap.put(string, Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("isPreview"))));
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static List<HighLightsDb> queryVideoFile(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, "_data like ?", new String[]{getHighPath() + "/%"}, "date_added DESC");
            while (query.moveToNext()) {
                query.getString(query.getColumnIndexOrThrow("_id"));
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                if (isSafePathName(string) && string != null) {
                    arrayList.add(new HighLightsDb(string, 0));
                }
            }
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<VideoFile> queryVideoFils(Context context, String str, String str2, String str3) {
        String str4;
        String str5;
        String str6 = "duration";
        String gameName = getGameName(str2, str3);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            String[] strArr = {"_id", "_data", "date_modified", "date_added", "duration", "title"};
            Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, new String("_data like ? AND duration <> 0"), new String[]{getHighPath() + "/%"}, "date_added DESC");
            if (query != null) {
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndexOrThrow("_id"));
                    String string2 = query.getString(query.getColumnIndexOrThrow("_data"));
                    if (isSafePathName(string2)) {
                        String string3 = query.getString(query.getColumnIndexOrThrow("title"));
                        int i = query.getInt(query.getColumnIndexOrThrow(str6));
                        Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string);
                        String str7 = str6;
                        if (string2.split("/").length == NEW_PATH_ARRAY_LENGTH && string2.contains(gameName)) {
                            VideoFile videoFile = new VideoFile(string2, withAppendedPath, string3, i);
                            if (string2.contains("manual")) {
                                arrayList2.add(videoFile);
                            } else {
                                arrayList.add(videoFile);
                            }
                        }
                        str6 = str7;
                    }
                }
                str4 = str6;
                query.close();
            } else {
                str4 = "duration";
            }
            Cursor query2 = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, new String("_data like '" + str + "/%' AND duration <> 0"), null, "date_added DESC");
            if (query2 != null) {
                while (query2.moveToNext()) {
                    String string4 = query2.getString(query2.getColumnIndexOrThrow("_id"));
                    String string5 = query2.getString(query2.getColumnIndexOrThrow("_data"));
                    if (isSafePathName(string5)) {
                        String str8 = str4;
                        arrayList.add(new VideoFile(string5, Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string4), query2.getString(query2.getColumnIndexOrThrow("title")), query2.getInt(query2.getColumnIndexOrThrow(str8))));
                        str4 = str8;
                    }
                }
                str5 = str4;
                query2.close();
            } else {
                str5 = str4;
            }
            String str9 = str5;
            Cursor query3 = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, new String("_data like '" + GAME_MANUAL + "/%' AND duration <> 0"), null, "date_added DESC");
            if (query3 != null) {
                while (query3.moveToNext()) {
                    String string6 = query3.getString(query3.getColumnIndexOrThrow("_id"));
                    String string7 = query3.getString(query3.getColumnIndexOrThrow("_data"));
                    if (isSafePathName(string7)) {
                        String string8 = query3.getString(query3.getColumnIndexOrThrow("title"));
                        int i2 = query3.getInt(query3.getColumnIndexOrThrow(str9));
                        Uri withAppendedPath2 = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string6);
                        if (string7.contains(gameName)) {
                            arrayList2.add(new VideoFile(string7, withAppendedPath2, string8, i2));
                        }
                    }
                }
                query3.close();
            }
            arrayList.addAll(arrayList2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void setThumbImage(Context context, VideoFile videoFile, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context).load(videoFile.getAbsolutePath()).centerCrop().signature((Key) new StringSignature(videoFile.lastModified() + "")).dontAnimate().into(imageView);
    }

    public static List<VideoFile> sortList(List<VideoFile> list) {
        new ArrayList();
        Collections.sort(list, new Comparator<VideoFile>() { // from class: cn.nubia.gamecenter.settings.records.VideoListUtil.1
            @Override // java.util.Comparator
            public int compare(VideoFile videoFile, VideoFile videoFile2) {
                if (videoFile.getId() < videoFile2.getId()) {
                    return -1;
                }
                return videoFile.getId() > videoFile2.getId() ? 1 : 0;
            }
        });
        return list;
    }
}
