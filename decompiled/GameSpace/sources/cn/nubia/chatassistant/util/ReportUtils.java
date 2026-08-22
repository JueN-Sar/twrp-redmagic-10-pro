package cn.nubia.chatassistant.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import cn.nubia.gamelauncher.util.GameCountTrack;
import cn.nubia.gamelauncher.util.NubiaTrackManager;

/* loaded from: classes.dex */
public class ReportUtils {
    private static final String TAG = "ReportUtils";

    public static String getAppName(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "game_pack_name");
        LogUtils.i(TAG, "gamePackageName : " + string);
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(string, 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void onReportChatAssistantAddOrDelete(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("app_name", getAppName(context));
        bundle.putString(NotificationCompat.CATEGORY_EVENT, "chat_assistant_settings");
        bundle.putString("edit_type", str);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    public static void onReportChatAssistantUsed(Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("app_name", getAppName(context));
        bundle.putString(NotificationCompat.CATEGORY_EVENT, "chat_assistant_used");
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    public static void onReportChatAssistantUsedAndAccountLogin(Context context) {
        GameCountTrack.getInstance().sendChatAssistantCount(getAppName(context));
        String string = Settings.Global.getString(context.getContentResolver(), "ark_base_open_id");
        if (TextUtils.isEmpty(string)) {
            LogUtils.e(TAG, "account is not login!");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("app_name", getAppName(context));
        bundle.putString(NotificationCompat.CATEGORY_EVENT, "chat_assistant_used_labeling");
        bundle.putString("openid", string);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    public static void onReportPowerPanelClose(Context context, int i) {
        LogUtils.infoPowerPanel(TAG, "onReportPowerPanelClose: ");
        Bundle bundle = new Bundle();
        bundle.putString("app_name", getAppName(context));
        bundle.putInt("duration", i);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "data_panel_used", bundle);
    }
}
