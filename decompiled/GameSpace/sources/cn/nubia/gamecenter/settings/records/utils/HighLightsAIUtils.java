package cn.nubia.gamecenter.settings.records.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.VideoListUtil;
import cn.nubia.gamecenter.settings.records.bean.HighlightsFile;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HighLightsAIUtils {
    public static int getEmptyText() {
        return (VideoListUtil.isInternal() || FeatureUtil.getBoolean("ZTE_FEATURE_MANUAL_RECORD_ONLY", false).booleanValue()) ? FeatureUtil.getBoolean(HighLightsUtils.ZTE_FEATURE_GAME_RANDOM_RECORD, false).booleanValue() ? R.string.gcs_game_record_empty_no_highlight_new : R.string.gcs_game_record_empty_no_highlight : (CommonUtil.isRedMagicRunOnMyOs() || VideoListUtil.isRedMagicPad() || HighLightsUtils.isNubiaOS() || HighLightsUtils.isRedMagic()) ? FeatureUtil.getBoolean(HighLightsUtils.ZTE_FEATURE_GAME_RANDOM_RECORD, false).booleanValue() ? R.string.gcs_game_record_empty_new : R.string.gcs_game_record_empty : FeatureUtil.getBoolean(HighLightsUtils.ZTE_FEATURE_GAME_RANDOM_RECORD, false).booleanValue() ? R.string.gcs_game_record_empty_no_gamekey_new : R.string.gcs_game_record_empty_no_gamekey;
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

    private static String parseName(Map<String, String> map, List<String> list, String str, boolean z) {
        String str2 = z ? "_" : "";
        for (String str3 : list) {
            if (str.contains(str3.replace(":", str2))) {
                return str3;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.contains(entry.getKey().replace(":", str2)) || str.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }

    public static LinkedHashMap<String, ArrayList<HighlightsFile>> queryImageFiles(Context context, String[] strArr, List<String> list, Map<String, String> map) {
        LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap = new LinkedHashMap<>();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_data like ? or _data like ?", strArr, "date_modified DESC");
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndexOrThrow = query.getColumnIndexOrThrow("_id");
                        int columnIndexOrThrow2 = query.getColumnIndexOrThrow("_data");
                        int columnIndexOrThrow3 = query.getColumnIndexOrThrow("title");
                        int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date_modified");
                        int columnIndexOrThrow5 = query.getColumnIndexOrThrow("_size");
                        do {
                            String string = query.getString(columnIndexOrThrow);
                            String string2 = query.getString(columnIndexOrThrow2);
                            String string3 = query.getString(columnIndexOrThrow3);
                            HighlightsFile highlightsFile = new HighlightsFile(string2, string3, query.getLong(columnIndexOrThrow4), Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, string), query.getInt(columnIndexOrThrow5), 1);
                            String imageGameName = getImageGameName(string3);
                            if (list != null && !list.contains(imageGameName)) {
                                imageGameName = parseName(map, list, string3, true);
                            }
                            if (imageGameName != null && !"".equals(imageGameName)) {
                                ArrayList<HighlightsFile> arrayList = linkedHashMap.get(imageGameName);
                                if (arrayList == null) {
                                    ArrayList<HighlightsFile> arrayList2 = new ArrayList<>();
                                    arrayList2.add(highlightsFile);
                                    linkedHashMap.put(imageGameName, arrayList2);
                                } else {
                                    arrayList.add(highlightsFile);
                                }
                            }
                        } while (query.moveToNext());
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return linkedHashMap;
    }

    public static List<HighLightsDb> queryVideoFileFromMedia(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, "_data like ? or _data like ?", strArr, "date_added DESC");
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        query.getColumnIndex("_id");
                        int columnIndex = query.getColumnIndex("_data");
                        do {
                            String string = query.getString(columnIndex);
                            if (HighLightsFileUtils.isSafePathName(string)) {
                                arrayList.add(new HighLightsDb(string, 0));
                            }
                        } while (query.moveToNext());
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return arrayList;
    }
}
