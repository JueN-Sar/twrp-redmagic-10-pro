package cn.nubia.gamelauncher.redmagicplanet.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes.dex */
public class CommonUtil {
    public static String DEFAULT_VIDEO_RUL = null;
    private static String GAME_CJZC = "/storage/emulated/0/红魔时刻/和平精英";
    private static String GAME_MANUAL = "/storage/emulated/0/红魔时刻/手动回录";
    private static String GAME_PUBG = "/storage/emulated/0/红魔时刻/PUBGMOBILE";
    private static String GAME_WZRY = "/storage/emulated/0/红魔时刻/王者荣耀";
    private static final String SHOW_PERMISSION__WARNING_DIALOG = "show_permission_warning_dialog";
    private static final String SHOW_PERMISSION__WARNING_DIALOG_PREFERENCES = "show_permission_warning_dialog_preferences";
    private static final String TAG = "CommonUtil";

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 1), Math.max(drawable.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
        drawable.draw(canvas);
        canvas.setBitmap(null);
        return createBitmap;
    }

    public static String formatTime(long j) {
        if (j <= 0 || j >= 86400000) {
            return "00:00";
        }
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        return j5 > 0 ? formatter.format("%d:%02d:%02d", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString() : formatter.format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j3)).toString();
    }

    public static String getGameNameByVideoFileTile(Context context, String str) {
        String string = context.getResources().getString(R.string.red_magic_video_wzry);
        try {
            if (TextUtils.isEmpty(str) || !str.contains("_")) {
                return string;
            }
            Log.d("CommonUtil", "getGameNameByVideoFileTile: _ position : " + str.indexOf("_"));
            return str.substring(0, str.indexOf("_"));
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String getPackageNameByPath(String str) {
        String str2;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.contains(GAME_WZRY)) {
                str2 = HighLightsUtils.WZRY_PACKAGE_NAME;
            } else if (str.contains(GAME_CJZC)) {
                str2 = HighLightsUtils.CJZC_PACKAGE_NAME;
            } else if (str.contains(GAME_PUBG)) {
                str2 = HighLightsUtils.PUBG_PACKAGE_NAME;
            } else {
                if (!str.contains(GAME_MANUAL)) {
                    return null;
                }
                String substring = str.substring(str.lastIndexOf("/") + 1);
                LogUtil.d("CommonUtil", " getPackageNameByPath  fileName : " + substring);
                if (!substring.contains("_")) {
                    return null;
                }
                str2 = substring.split("_")[1];
            }
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap getVideoFirstFrameImage(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            android.media.MediaMetadataRetriever r1 = new android.media.MediaMetadataRetriever     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            r1.<init>()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L39
            r1.setDataSource(r3, r4)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L39
            r3 = 0
            android.graphics.Bitmap r0 = r1.getFrameAtTime(r3)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L39
            r1.release()     // Catch: java.io.IOException -> L17
            goto L38
        L17:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            r4.<init>(r3)
            throw r4
        L1e:
            r3 = move-exception
            goto L24
        L20:
            r3 = move-exception
            goto L3b
        L22:
            r3 = move-exception
            r1 = r0
        L24:
            java.lang.String r4 = "CommonUtil"
            java.lang.String r2 = " getVideoFirstFrameImage "
            cn.nubia.gamelauncher.redmagicplanet.util.LogUtil.e(r4, r2, r3)     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L38
            r1.release()     // Catch: java.io.IOException -> L31
            goto L38
        L31:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            r4.<init>(r3)
            throw r4
        L38:
            return r0
        L39:
            r3 = move-exception
            r0 = r1
        L3b:
            if (r0 == 0) goto L48
            r0.release()     // Catch: java.io.IOException -> L41
            goto L48
        L41:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            r4.<init>(r3)
            throw r4
        L48:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil.getVideoFirstFrameImage(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static boolean isInternalVersion() {
        return cn.nubia.gamelauncher.util.CommonUtil.isInternalVersion();
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void setDefaultVideoRul(String str) {
        DEFAULT_VIDEO_RUL = str;
    }

    public static void setDisplayPermissionDialog(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHOW_PERMISSION__WARNING_DIALOG_PREFERENCES, 0).edit();
        edit.putInt(SHOW_PERMISSION__WARNING_DIALOG, 1);
        edit.apply();
    }

    public static boolean showPermissionWaringDialog(Context context) {
        return context.getSharedPreferences(SHOW_PERMISSION__WARNING_DIALOG_PREFERENCES, 0).getInt(SHOW_PERMISSION__WARNING_DIALOG, 0) == 1;
    }
}
