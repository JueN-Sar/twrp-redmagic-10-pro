package cn.nubia.gamepanel;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.service.GameFeatureService;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class GameForegroundChangeReceiver extends BroadcastReceiver {
    public static final String ACTION_FOREGROUND_GAME = "cn.zte.gamefloat.powerpanel.action.FOREGROUND_GAME_CHANGE";
    private static final String SERVICES_CLASS_NAME = "cn.nubia.gamepanel.PowerPanelService";
    private static final String TAG = "GameForegroundChangeReceiver";

    private boolean isAppEnable(Context context, String str) {
        return isAppEnable(Settings.Global.getString(context.getContentResolver(), "redmagic_ce_switch"), str, ",");
    }

    public static boolean isAppEnable(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str4 : str.split(str3)) {
            if (str4.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isServiceRunning(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY)).getRunningServices(Integer.MAX_VALUE);
        if (runningServices != null && runningServices.size() != 0) {
            Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtils.i(TAG, "onReceive action=" + action);
        if (ACTION_FOREGROUND_GAME.equals(action)) {
            String stringExtra = intent.getStringExtra("packageName");
            boolean isServiceRunning = isServiceRunning(context, SERVICES_CLASS_NAME);
            LogUtils.i(TAG, "packageName=" + stringExtra + ", isAppEnable: " + isAppEnable(context, stringExtra) + ", " + isServiceRunning);
            if (!isAppEnable(context, stringExtra)) {
                PowerPanelService.foregroundPkgChange(context, stringExtra, 1, 1);
            } else {
                if (isServiceRunning) {
                    return;
                }
                PowerPanelService.foregroundPkgChange(context, stringExtra, 0, 1);
            }
        }
    }
}
