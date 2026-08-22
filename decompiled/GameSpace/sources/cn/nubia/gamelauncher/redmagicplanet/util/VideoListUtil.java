package cn.nubia.gamelauncher.redmagicplanet.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import cn.nubia.gamelauncher.redmagicplanet.VideoFile;
import cn.nubia.plug.PlugUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class VideoListUtil {
    private static final String GAME_HIGHT_PATH = "/storage/emulated/0/红魔时刻";
    private static final String GAME_HIGHT_PATH_INTER = "/storage/emulated/0/Red Magic Moment";
    private static final String GAME_HIGHT_PATH_ZTE = "/storage/emulated/0/精彩时刻";
    private static final String GAME_HIGHT_PATH_ZTE_INTER = "/storage/emulated/0/Wonderful Time";
    private static final String GAME_MANUAL = "/storage/emulated/0/红魔时刻/手动回录";
    private static final String TAG = "VideoListUtil";

    public static String getQuerySelection() {
        new String();
        if (cn.nubia.common.util.CommonUtil.isNubia() || PlugUtil.isRedMagic7()) {
            return "_data like  '/storage/emulated/0/Red Magic Moment/%' or _data like '/storage/emulated/0/红魔时刻/%' AND duration <> 0";
        }
        return "_data like  '" + (cn.nubia.gamelauncher.util.CommonUtil.isInternalVersion() ? "/storage/emulated/0/Wonderful Time" : "/storage/emulated/0/精彩时刻") + "/%' AND duration <> 0";
    }

    public static VideoFile queryLatestVideoFiles(Context context) {
        Exception exc;
        VideoFile videoFile;
        int i;
        Cursor query;
        Cursor cursor = null;
        r6 = null;
        VideoFile videoFile2 = null;
        cursor = null;
        if (context == null) {
            Log.d(TAG, "******queryVideoFiles context is null");
            return null;
        }
        try {
            try {
                i = 0;
                query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", "date_modified", "date_added", "title", "duration"}, getQuerySelection(), null, "date_added DESC");
            } catch (Exception e) {
                exc = e;
                videoFile = null;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            Log.d(TAG, "******queryVideoFiles cursor.getCount() =" + (query != null ? query.getCount() : 0));
            while (query.moveToNext() && i < 1) {
                String string = query.getString(query.getColumnIndex("_id"));
                String string2 = query.getString(query.getColumnIndex("_data"));
                String string3 = query.getString(query.getColumnIndex("title"));
                int i2 = query.getInt(query.getColumnIndex("duration"));
                Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string);
                Log.d(TAG, "******queryVideoFiles path =" + string2 + " ,title =" + string3 + ", time =" + i2 + " ;; uri = " + withAppendedPath);
                Uri secureUri = cn.nubia.common.util.CommonUtil.getSecureUri(withAppendedPath);
                if (!cn.nubia.common.util.CommonUtil.isSecurePath(string2) || secureUri == null) {
                    break;
                }
                i++;
                videoFile2 = new VideoFile(string2, secureUri, string3, i2);
            }
            if (query == null) {
                return videoFile2;
            }
            query.close();
            return videoFile2;
        } catch (Exception e2) {
            exc = e2;
            videoFile = videoFile2;
            cursor = query;
            exc.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
            return videoFile;
        } catch (Throwable th2) {
            th = th2;
            cursor = query;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cb, code lost:
    
        if (r8 != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00da, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d7, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d5, code lost:
    
        if (r8 != null) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<cn.nubia.gamelauncher.redmagicplanet.VideoFile> queryVideoFiles(android.content.Context r15, int r16) {
        /*
            java.lang.String r0 = "VideoListUtil"
            java.lang.String r1 = "duration"
            java.lang.String r2 = "title"
            java.lang.String r3 = "_id"
            java.lang.String r4 = "_data"
            java.lang.String r5 = "******queryVideoFils cursor.getCount() ="
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r7 = 6
            r8 = 0
            java.lang.String[] r11 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r7 = 0
            r11[r7] = r4     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r9 = 1
            r11[r9] = r3     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r9 = "date_modified"
            r10 = 2
            r11[r10] = r9     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r9 = "date_added"
            r10 = 3
            r11[r10] = r9     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r9 = 4
            r11[r9] = r2     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r9 = 5
            r11[r9] = r1     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r12 = getQuerySelection()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            android.content.ContentResolver r9 = r15.getContentResolver()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            android.net.Uri r10 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r14 = "date_added DESC"
            r13 = 0
            android.database.Cursor r8 = r9.query(r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r9.<init>(r5)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            if (r8 == 0) goto L49
            int r5 = r8.getCount()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            goto L4a
        L49:
            r5 = r7
        L4a:
            java.lang.StringBuilder r5 = r9.append(r5)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            android.util.Log.d(r0, r5)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
        L55:
            boolean r5 = r8.moveToNext()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            if (r5 == 0) goto Lcb
            r5 = r16
            if (r7 < r5) goto L60
            goto Lcb
        L60:
            int r9 = r8.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            int r10 = r8.getColumnIndex(r4)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r10 = r8.getString(r10)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            int r11 = r8.getColumnIndex(r2)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r11 = r8.getString(r11)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            int r12 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            int r12 = r8.getInt(r12)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            android.net.Uri r13 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            android.net.Uri r9 = android.net.Uri.withAppendedPath(r13, r9)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r13.<init>()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r14 = "******queryVideoFils path ="
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.StringBuilder r13 = r13.append(r10)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r14 = " ,title ="
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.StringBuilder r13 = r13.append(r11)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r14 = ", time ="
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.StringBuilder r13 = r13.append(r12)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            android.util.Log.d(r0, r13)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            if (r12 > 0) goto Lb3
            goto L55
        Lb3:
            android.net.Uri r9 = cn.nubia.common.util.CommonUtil.getSecureUri(r9)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            boolean r13 = cn.nubia.common.util.CommonUtil.isSecurePath(r10)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            if (r13 == 0) goto L55
            if (r9 != 0) goto Lc0
            goto L55
        Lc0:
            cn.nubia.gamelauncher.redmagicplanet.VideoFile r13 = new cn.nubia.gamelauncher.redmagicplanet.VideoFile     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r13.<init>(r10, r9, r11, r12)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            r6.add(r13)     // Catch: java.lang.Throwable -> Lce java.lang.Exception -> Ld5
            int r7 = r7 + 1
            goto L55
        Lcb:
            if (r8 == 0) goto Lda
            goto Ld7
        Lce:
            r0 = move-exception
            if (r8 == 0) goto Ld4
            r8.close()
        Ld4:
            throw r0
        Ld5:
            if (r8 == 0) goto Lda
        Ld7:
            r8.close()
        Lda:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.redmagicplanet.util.VideoListUtil.queryVideoFiles(android.content.Context, int):java.util.List");
    }

    public static List<VideoFile> sortList(List<VideoFile> list) {
        new ArrayList();
        Collections.sort(list, new Comparator<VideoFile>() { // from class: cn.nubia.gamelauncher.redmagicplanet.util.VideoListUtil.1
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
