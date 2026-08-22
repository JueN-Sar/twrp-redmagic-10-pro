package cn.nubia.gamelauncher.helper;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;

/* loaded from: classes.dex */
public class NetWhitelistHelper {
    private static final String AUTHORITY = "com.zte.zteconfigupdate.provider";
    private static final String LIST_NAME = "game_white_list_info";
    private static final String PKG_NAME = "pkg_name";
    private static final Uri WHITELIST_URI = Uri.parse("content://com.zte.zteconfigupdate.provider/game_white_list_info");
    private static Cursor cursor;

    public static void getNetWhitelist() {
        Cursor cursor2;
        Cursor query;
        try {
            try {
                query = GameLauncherApplication.CONTEXT.getContentResolver().query(WHITELIST_URI, new String[]{PKG_NAME}, null, null);
                cursor = query;
            } catch (Exception e) {
                Log.d("NetWhitelistHelper", "getNetWhitelist error : " + e.getMessage());
                cursor2 = cursor;
                if (cursor2 == null) {
                    return;
                }
            }
            if (query == null) {
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            while (cursor.moveToNext()) {
                Cursor cursor3 = cursor;
                String string = cursor3.getString(cursor3.getColumnIndex(PKG_NAME));
                ConstantVariable.LOCAL_GAME_IMAGE_MAP.put(string, "");
                Log.d("NetWhitelistHelper", string + " added to the Whitelist");
            }
            cursor2 = cursor;
            if (cursor2 == null) {
                return;
            }
            cursor2.close();
        } catch (Throwable th) {
            Cursor cursor4 = cursor;
            if (cursor4 != null) {
                cursor4.close();
            }
            throw th;
        }
    }
}
