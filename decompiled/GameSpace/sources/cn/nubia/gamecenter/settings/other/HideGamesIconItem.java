package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;
import java.util.List;

/* loaded from: classes.dex */
public class HideGamesIconItem extends Item {
    public static Uri APPADD_URI = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=true");
    private static final String DYNAMIC_SHOW_HIDDEN_APPS_URI = "content://com.zte.mifavor.launcher.dynamicshowhiddenapps";
    public static final String KEY = "hide_games_icon";
    private static final String SETTINGS_NAME = "switch_hide_games_icon";
    private static final String TAG = "HideGamesIconItem";
    private static final String TRACK_EVENT = "gamespace_hide_desktop_icon_switch";

    private void dynamicShowHiddenApps(Context context, boolean z) {
        Uri parse = Uri.parse("content://com.zte.mifavor.launcher.dynamicshowhiddenapps");
        String str = z ? "hidden" : "show";
        String addedAppListFromDB = getAddedAppListFromDB(context);
        LogUtil.d(TAG, "dynamicShowHiddenApps:" + str + "," + addedAppListFromDB);
        Bundle bundle = new Bundle();
        bundle.putString("app_component_name_list_and_user", addedAppListFromDB);
        try {
            context.getContentResolver().call(parse, str, (String) null, bundle);
        } catch (Exception e) {
            LogUtil.e(TAG, "dynamicShowHiddenApps exception: " + e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        if (r7 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0056, code lost:
    
        return r6.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getAddedAppListFromDB(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            android.content.ContentResolver r0 = r7.getContentResolver()
            android.net.Uri r1 = cn.nubia.gamecenter.settings.other.HideGamesIconItem.APPADD_URI
            r4 = 0
            r5 = 0
            r2 = 0
            r3 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            if (r7 == 0) goto L4d
        L15:
            boolean r0 = r7.moveToNext()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            if (r0 == 0) goto L4d
            java.lang.String r0 = "component"
            int r0 = r7.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r0 = r7.getString(r0)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r1 = ","
            java.lang.String r2 = "/"
            java.lang.String r0 = r0.replace(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r6.append(r0)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r1 = ",0;"
            r6.append(r1)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r6.append(r0)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r0 = ",99900000;"
            r6.append(r0)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            goto L15
        L3e:
            r6 = move-exception
            goto L47
        L40:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r7 == 0) goto L52
            goto L4f
        L47:
            if (r7 == 0) goto L4c
            r7.close()
        L4c:
            throw r6
        L4d:
            if (r7 == 0) goto L52
        L4f:
            r7.close()
        L52:
            java.lang.String r6 = r6.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.other.HideGamesIconItem.getAddedAppListFromDB(android.content.Context):java.lang.String");
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return list.contains(KEY);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public String getKey() {
        return KEY;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean getSettings(Context context) {
        return SettingUtil.getBoolean(context, SETTINGS_NAME, false);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getSummary() {
        return R.string.gcs_hide_games_icon_summary;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.gcs_hide_games_icon_title;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, SETTINGS_NAME, z);
        dynamicShowHiddenApps(context, z);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void track(boolean z) {
        super.track(z);
        Track.switchStatus(TRACK_EVENT, z);
    }
}
