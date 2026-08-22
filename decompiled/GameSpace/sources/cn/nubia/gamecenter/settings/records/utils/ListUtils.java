package cn.nubia.gamecenter.settings.records.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.recordsdb.RTimeDataBaseHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ListUtils {

    private static class SingleInstance {
        static ListUtils instance = new ListUtils();

        private SingleInstance() {
        }
    }

    private ContentValues covertToVideoContentValues(HighLightsDb highLightsDb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", highLightsDb.getPath());
        contentValues.put("isPreview", Integer.valueOf(highLightsDb.getIsPreview()));
        contentValues.put("packageName", highLightsDb.getPackageName());
        contentValues.put("appName", highLightsDb.getAppName());
        return contentValues;
    }

    public static ListUtils getInstance() {
        return SingleInstance.instance;
    }

    public static String getPackageNameFromUid(Context context, int i) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        return (packagesForUid == null || packagesForUid.length <= 0) ? "" : packagesForUid[0];
    }

    private String parseName(Map<String, String> map, String str, boolean z) {
        for (String str2 : map.keySet()) {
            if (z) {
                if (str.contains(str2.replace(":", "_"))) {
                    return str2;
                }
            } else if (str.contains(str2.replace(":", ""))) {
                return str2;
            }
        }
        return "";
    }

    private String parsingAppName(String str) {
        if (str.contains("/storage/emulated/0/红魔时刻/王者荣耀")) {
            return "王者荣耀";
        }
        if (str.contains("/storage/emulated/0/红魔时刻/和平精英")) {
            return "和平精英";
        }
        if (str.contains("/storage/emulated/0/红魔时刻/PUBGMOBILE")) {
            return HighLightsUtils.PUBG_APP_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/英雄联盟")) {
            return "英雄联盟手游";
        }
        return null;
    }

    private String parsingPackageName(String str) {
        if (str.contains("/storage/emulated/0/红魔时刻/王者荣耀")) {
            return HighLightsUtils.WZRY_PACKAGE_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/和平精英")) {
            return HighLightsUtils.CJZC_PACKAGE_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/PUBGMOBILE")) {
            return HighLightsUtils.PUBG_PACKAGE_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/英雄联盟")) {
            return HighLightsUtils.LOL_PACKAGE_NAME;
        }
        return null;
    }

    public void deleteVideoToDB(Context context, List<HighLightsDb> list) {
        ContentResolver contentResolver = context.getContentResolver();
        for (int i = 0; i < list.size(); i++) {
            HighLightsDb highLightsDb = list.get(i);
            if (highLightsDb != null) {
                if (i == list.size() - 1) {
                    contentResolver.delete(RTimeDataBaseHelper.REDMAGICTIME_NOT_NOTIFY_URI, "path=?", new String[]{highLightsDb.getPath()});
                } else {
                    contentResolver.delete(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, "path=?", new String[]{highLightsDb.getPath()});
                }
            }
        }
    }

    public void insertVideoToDB(Context context, List<HighLightsDb> list) {
        ContentResolver contentResolver = context.getContentResolver();
        for (int i = 0; i < list.size(); i++) {
            HighLightsDb highLightsDb = list.get(i);
            if (highLightsDb != null) {
                if (i == list.size() - 1) {
                    contentResolver.insert(RTimeDataBaseHelper.REDMAGICTIME_NOT_NOTIFY_URI, covertToVideoContentValues(highLightsDb));
                } else {
                    contentResolver.insert(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, covertToVideoContentValues(highLightsDb));
                }
            }
        }
    }

    public List<HighLightsDb> parsingPath(List<HighLightsDb> list, Map<String, String> map) {
        String path;
        String str;
        String str2;
        String str3;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            HighLightsDb highLightsDb = list.get(i);
            if (highLightsDb != null && (path = highLightsDb.getPath()) != null) {
                if (path.contains("/storage/emulated/0/Pictures/Game Space Screenshot") || path.contains("/storage/emulated/0/Pictures/Redmagic Time Screenshot")) {
                    String[] split = path.split("/");
                    String str4 = split.length - 1 <= 0 ? "" : split[split.length - 1];
                    String[] split2 = str4.split("_");
                    str = split2.length >= 2 ? split2[1] : "";
                    String str5 = map.get(str);
                    if (str5 == null) {
                        String parseName = parseName(map, str4, true);
                        str3 = parseName;
                        str2 = map.get(parseName);
                    } else {
                        str2 = str5;
                        str3 = str;
                    }
                } else if (parsingPackageName(path) != null && parsingAppName(path) != null) {
                    str2 = parsingPackageName(path);
                    str3 = parsingAppName(path);
                } else if (path.contains("/") && path.contains("_")) {
                    String[] split3 = path.split("/");
                    String str6 = split3.length - 1 <= 0 ? "" : split3[split3.length - 1];
                    String[] split4 = str6.split("_");
                    if (str6.contains("manual")) {
                        String str7 = split4.length < 2 ? "" : split4[0];
                        str = split4.length >= 2 ? split4[1] : "";
                        str3 = str7;
                        str2 = str;
                    } else {
                        str3 = split4.length < 2 ? "" : split4[0];
                        String str8 = map.get(str3);
                        if (!"".equals(str8)) {
                            if (str8 == null) {
                                str3 = parseName(map, str6, false);
                                str2 = map.get(str3);
                            } else {
                                str2 = str8;
                            }
                        }
                    }
                }
                arrayList.add(new HighLightsDb(path, 1, str2, str3));
            }
        }
        return arrayList;
    }

    public void removeHadAll(List<HighLightsDb> list, List<HighLightsDb> list2) {
        for (int size = list.size() - 1; size >= 0; size--) {
            int size2 = list2.size();
            while (true) {
                if (size2 > 0) {
                    int i = size2 - 1;
                    String path = list2.get(i).getPath();
                    if (path != null && path.equals(list.get(size).getPath())) {
                        list2.remove(i);
                        list.remove(size);
                        break;
                    }
                    size2--;
                }
            }
        }
    }
}
