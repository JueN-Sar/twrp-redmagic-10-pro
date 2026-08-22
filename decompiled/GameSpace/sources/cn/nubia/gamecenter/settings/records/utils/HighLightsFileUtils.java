package cn.nubia.gamecenter.settings.records.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamecenter.settings.records.bean.HighlightsFile;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.recordsdb.RTimeDataBaseHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class HighLightsFileUtils {
    private static final String TAG = "RecordsActivity";
    private static ArrayList<String> mPathList = new ArrayList<>();
    private static final Pattern MANUAL_VIDEO_PACKAGE_PATTERN = Pattern.compile("_((?:[a-zA-Z][a-zA-Z0-9_]*\\.)+[a-zA-Z][a-zA-Z0-9_]*)_\\d{4}-\\d{2}-\\d{2}");

    private static void addHighlightsFileToPackageMap(LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap, String str, HighlightsFile highlightsFile) {
        ArrayList<HighlightsFile> arrayList = linkedHashMap.get(str);
        if (arrayList != null) {
            arrayList.add(highlightsFile);
            return;
        }
        ArrayList<HighlightsFile> arrayList2 = new ArrayList<>();
        arrayList2.add(highlightsFile);
        linkedHashMap.put(str, arrayList2);
    }

    private static ArrayList<String> distinctDisplayNames(Map<String, String> map) {
        return new ArrayList<>(new LinkedHashSet(map.values()));
    }

    static String extractPackageFromManualVideoTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = MANUAL_VIDEO_PACKAGE_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String getApplicationLabel(String str, Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(str, 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHighPath() {
        return (CommonUtil.isRedMagicRunOnMyOs() || HighLightsUtils.isRedMagicPad() || HighLightsUtils.isNP02J() || HighLightsUtils.isNubiaOS() || HighLightsUtils.isRedMagic()) ? HighLightsUtils.isInternal() ? "/storage/emulated/0/Red Magic Moment" : "/storage/emulated/0/红魔时刻" : HighLightsUtils.isInternal() ? "/storage/emulated/0/Wonderful Time" : "/storage/emulated/0/精彩时刻";
    }

    public static String[] getImageDataPath() {
        return HighLightsUtils.IMAGE_PATH_ARRAY;
    }

    private static String getImageGameName(String str) {
        if (str != null && str.contains("_")) {
            String[] split = str.split("_");
            if (split.length >= 3) {
                return split[1];
            }
        }
        return null;
    }

    private static String getOldPath(String str) {
        return HighLightsUtils.WZRY_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/王者荣耀" : HighLightsUtils.CJZC_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/和平精英" : HighLightsUtils.PUBG_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/PUBGMOBILE" : HighLightsUtils.LOL_PACKAGE_NAME.equals(str) ? "/storage/emulated/0/红魔时刻/英雄联盟" : str;
    }

    private static String getVideoGameName(String str) {
        if (str == null || !str.contains("_")) {
            return null;
        }
        return str.substring(0, str.indexOf("_"));
    }

    public static boolean isImageExist(Context context, String str) {
        if (str != null) {
            str = str.replace(":", "_");
        }
        return context != null && isImageFilesExist(context, getImageDataPath(), str);
    }

    private static boolean isImageFilesExist(Context context, String[] strArr, String str) {
        boolean z = false;
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_size", "title"}, new String("_data like ? or _data like ?"), strArr, null, null);
            while (true) {
                try {
                    if (!query.moveToNext()) {
                        break;
                    }
                    String string = query.getString(query.getColumnIndex("_data"));
                    if (!isSafePathName(string)) {
                        break;
                    }
                    if (string.contains(str + "_")) {
                        z = true;
                        break;
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
        return z;
    }

    public static boolean isNewPathVideoExist(Context context, String str) {
        return isNewVideoFilesExist(context, getHighPath(), str);
    }

    public static boolean isNewVideoFilesExist(Context context, String str, String str2) {
        Cursor query;
        mPathList.clear();
        LogUtil.d(TAG, "******isNewVideoFilesExist videoPath =" + str);
        boolean z = false;
        try {
            query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "date_modified", "title", "duration"}, "_data like ? AND duration <> 0", new String[]{str + "/" + str2 + "%"}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query == null) {
            if (query != null) {
                query.close();
            }
            return false;
        }
        while (query.moveToNext()) {
            try {
                String string = query.getString(query.getColumnIndex("_data"));
                if (isSafePathName(string) && string.split("/").length == 6) {
                    mPathList.add(string);
                    if (string.contains(str2)) {
                        z = true;
                    }
                }
            } finally {
            }
        }
        if (query != null) {
            query.close();
        }
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
            int i = query.getInt(query.getColumnIndex("isPreview"));
            String string = query.getString(query.getColumnIndex("path"));
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

    public static boolean isOldVideoFilesExist(Context context, String str, String str2) {
        Cursor query;
        LogUtil.d(TAG, "******isOldVideoFilesExist packageName =" + str);
        boolean z = false;
        try {
            query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "date_modified", "title", "duration"}, "_data like ? OR _data like ? AND duration <> 0", new String[]{getOldPath(str) + "/%", "/storage/emulated/0/红魔时刻/手动回录/%" + str2}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query == null) {
            if (query != null) {
                query.close();
            }
            return false;
        }
        while (query.moveToNext()) {
            try {
                String string = query.getString(query.getColumnIndex("_data"));
                if (isSafePathName(string)) {
                    mPathList.add(string);
                }
            } finally {
            }
        }
        query.moveToPosition(-1);
        if (query.moveToNext()) {
            LogUtil.d(TAG, "******isOldVideoFilesExist path =" + query.getString(query.getColumnIndex("_data")));
            z = true;
        }
        if (query != null) {
            query.close();
        }
        return z;
    }

    public static boolean isSafePathName(String str) {
        if (str.contains("../") || str.contains("./") || str.contains("~/")) {
            return false;
        }
        return str.startsWith("/storage") || str.startsWith("/data") || str.startsWith("file:") || str.startsWith("content:");
    }

    public static boolean isVideoExist(Context context, String str, String str2) {
        if (context == null || str == null || str2 == null) {
            return false;
        }
        String replace = str2.replace(":", "");
        return isNewPathVideoExist(context, replace) || isOldVideoFilesExist(context, str, replace);
    }

    private static String mapDisplayNameToPackageForVideo(String str, String str2, List<String> list, Map<String, String> map) {
        if (TextUtils.isEmpty(str) || list == null || map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : list) {
            if (str.equals(map.get(str3))) {
                arrayList.add(str3);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (String) arrayList.get(0);
        }
        String extractPackageFromManualVideoTitle = extractPackageFromManualVideoTitle(str2);
        if (extractPackageFromManualVideoTitle != null && arrayList.contains(extractPackageFromManualVideoTitle)) {
            return extractPackageFromManualVideoTitle;
        }
        if (!TextUtils.isEmpty(str2)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str2.contains(str4)) {
                    return str4;
                }
            }
        }
        return (String) arrayList.get(0);
    }

    public static ArrayList<HighlightsFile> mergeLists(List<HighlightsFile> list, List<HighlightsFile> list2) {
        ArrayList<HighlightsFile> arrayList = new ArrayList<>();
        int i = 0;
        int i2 = 0;
        while (i < list.size() && i2 < list2.size()) {
            if (list.get(i).getModified() > list2.get(i2).getModified()) {
                arrayList.add(list.get(i));
                i++;
            } else {
                arrayList.add(list2.get(i2));
                i2++;
            }
        }
        while (i < list.size()) {
            arrayList.add(list.get(i));
            i++;
        }
        while (i2 < list2.size()) {
            arrayList.add(list2.get(i2));
            i2++;
        }
        return arrayList;
    }

    private static String parseName(Map<String, String> map, List<String> list, String str, boolean z) {
        for (String str2 : list) {
            if (z) {
                if (str.contains(str2.replace(":", "_"))) {
                    return str2;
                }
            } else if (str.contains(str2.replace(":", ""))) {
                return str2;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                if (str.contains(entry.getKey().replace(":", "_")) || str.contains(entry.getKey())) {
                    return entry.getValue();
                }
            } else if (str.contains(entry.getKey().replace(":", "")) || str.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }

    private static String parseVideoDisplayName(String str, Map<String, String> map, Map<String, String> map2) {
        if (TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return "";
        }
        int indexOf = str.indexOf("_com.");
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            ArrayList<String> distinctDisplayNames = distinctDisplayNames(map);
            distinctDisplayNames.sort(new Comparator() { // from class: cn.nubia.gamecenter.settings.records.utils.HighLightsFileUtils$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Integer.compare(((String) obj2).replace(":", "").length(), ((String) obj).replace(":", "").length());
                    return compare;
                }
            });
            Iterator<String> it = distinctDisplayNames.iterator();
            while (it.hasNext()) {
                String next = it.next();
                String replace = next.replace(":", "");
                if (substring.equals(replace) || substring.startsWith(replace + ".")) {
                    return next;
                }
            }
        }
        return parseName(map2, distinctDisplayNames(map), str, false);
    }

    public static List<HighLightsDb> queryImageFileFromImage(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, "_data like ? or _data like ?", strArr, "date_added DESC");
            while (query.moveToNext()) {
                try {
                    String string = query.getString(query.getColumnIndex("_data"));
                    if (isSafePathName(string)) {
                        arrayList.add(new HighLightsDb(string, 0));
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
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00d7 A[Catch: all -> 0x00ed, TryCatch #2 {all -> 0x00ed, blocks: (B:10:0x004b, B:12:0x0051, B:16:0x0098, B:19:0x009f, B:21:0x00ad, B:25:0x00c0, B:27:0x00c8, B:32:0x00cf, B:34:0x00d7, B:36:0x00e3, B:39:0x00b9), top: B:9:0x004b, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e3 A[Catch: all -> 0x00ed, TRY_LEAVE, TryCatch #2 {all -> 0x00ed, blocks: (B:10:0x004b, B:12:0x0051, B:16:0x0098, B:19:0x009f, B:21:0x00ad, B:25:0x00c0, B:27:0x00c8, B:32:0x00cf, B:34:0x00d7, B:36:0x00e3, B:39:0x00b9), top: B:9:0x004b, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.LinkedHashMap<java.lang.String, java.util.ArrayList<cn.nubia.gamecenter.settings.records.bean.HighlightsFile>> queryImageFiles(android.content.Context r24, java.lang.String[] r25, java.util.List<java.lang.String> r26, java.util.Map<java.lang.String, java.lang.String> r27, java.util.Map<java.lang.String, java.lang.String> r28) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.records.utils.HighLightsFileUtils.queryImageFiles(android.content.Context, java.lang.String[], java.util.List, java.util.Map, java.util.Map):java.util.LinkedHashMap");
    }

    public static List<HighLightsDb> queryNullPackageName(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("packageName");
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex("path"));
                    String string2 = query.getString(columnIndex);
                    if (isSafePathName(string) && string2 == null) {
                        arrayList.add(new HighLightsDb(string, 0));
                    }
                }
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void queryPreViewHashMap(Context context, HashMap<String, Integer> hashMap) {
        hashMap.clear();
        try {
            Cursor query = context.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, null, null, null, null);
            try {
                LogUtil.d(TAG, "******queryPreViewHashMap cursor=" + query);
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex("path"));
                    if (isSafePathName(string)) {
                        hashMap.put(string, Integer.valueOf(query.getInt(query.getColumnIndex("isPreview"))));
                    }
                }
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<HighLightsDb> queryRedMagicTime(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    String string = query.getString(query.getColumnIndex("path"));
                    if (isSafePathName(string)) {
                        arrayList.add(new HighLightsDb(string, 0));
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
        return arrayList;
    }

    public static List<HighLightsDb> queryVideoFileFromMedia(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "duration"}, "_data like ? or _data like ?", strArr, "date_added DESC");
            while (query.moveToNext()) {
                try {
                    String string = query.getString(query.getColumnIndexOrThrow("_data"));
                    if (isSafePathName(string) && query.getInt(query.getColumnIndexOrThrow("duration")) > 0) {
                        arrayList.add(new HighLightsDb(string, 0));
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
        return arrayList;
    }

    public static LinkedHashMap<String, ArrayList<HighlightsFile>> queryVideoFiles(Context context, String[] strArr, List<String> list, Map<String, String> map, Map<String, String> map2) {
        String mapDisplayNameToPackageForVideo;
        LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap = new LinkedHashMap<>();
        if (list != null && map != null && !map.isEmpty()) {
            try {
                Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "date_modified", "duration", "title"}, new String("_data like ? or _data like ?"), strArr, "date_modified DESC");
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(query.getColumnIndexOrThrow("_id"));
                        String string2 = query.getString(query.getColumnIndexOrThrow("_data"));
                        String string3 = query.getString(query.getColumnIndexOrThrow("title"));
                        long j = query.getLong(query.getColumnIndexOrThrow("date_modified"));
                        Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string);
                        int i = query.getInt(query.getColumnIndexOrThrow("duration"));
                        if (i > 0) {
                            HighlightsFile highlightsFile = (TextUtils.isEmpty(string3) || !string3.contains("HighLights")) ? new HighlightsFile(string2, string3, j, withAppendedPath, i, 2) : new HighlightsFile(string2, string3, j, withAppendedPath, i, 3);
                            if (!TextUtils.isEmpty(string3)) {
                                string2 = string3;
                            }
                            String extractPackageFromManualVideoTitle = extractPackageFromManualVideoTitle(string2);
                            if (extractPackageFromManualVideoTitle == null || !list.contains(extractPackageFromManualVideoTitle)) {
                                String parseVideoDisplayName = parseVideoDisplayName(string2, map, map2);
                                if (!TextUtils.isEmpty(parseVideoDisplayName) && (mapDisplayNameToPackageForVideo = mapDisplayNameToPackageForVideo(parseVideoDisplayName, string2, list, map)) != null) {
                                    addHighlightsFileToPackageMap(linkedHashMap, mapDisplayNameToPackageForVideo, highlightsFile);
                                }
                            } else {
                                addHighlightsFileToPackageMap(linkedHashMap, extractPackageFromManualVideoTitle, highlightsFile);
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
        return linkedHashMap;
    }

    private static String resolveImageToPackageKey(String str, String str2, String str3, List<String> list, Map<String, String> map) {
        if (TextUtils.isEmpty(str3) || list == null || map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str4 : list) {
            if (str3.equals(map.get(str4))) {
                arrayList.add(str4);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (String) arrayList.get(0);
        }
        String replace = str3.replace(":", "_");
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        StringBuilder append = sb.append(str);
        if (str2 == null) {
            str2 = "";
        }
        if (append.append(str2).toString().contains(replace + ".US_")) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str5 = (String) it.next();
                if (str5.contains(".usa") || str5.endsWith(".usa")) {
                    return str5;
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String str6 = (String) it2.next();
            if (!str6.contains(".usa")) {
                return str6;
            }
        }
        return (String) arrayList.get(0);
    }

    public static void sortFile(List<HighlightsFile> list, LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap) {
        linkedHashMap.clear();
        new ArrayList();
        for (HighlightsFile highlightsFile : list) {
            ArrayList<HighlightsFile> arrayList = linkedHashMap.get(DateUtils.format(highlightsFile.getModified(), false));
            if (arrayList == null) {
                ArrayList<HighlightsFile> arrayList2 = new ArrayList<>();
                arrayList2.add(highlightsFile);
                linkedHashMap.put(DateUtils.format(highlightsFile.getModified(), false), arrayList2);
            } else {
                arrayList.add(highlightsFile);
                linkedHashMap.put(DateUtils.format(highlightsFile.getModified(), false), arrayList);
            }
        }
    }
}
