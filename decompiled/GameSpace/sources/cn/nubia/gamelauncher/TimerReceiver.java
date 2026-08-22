package cn.nubia.gamelauncher;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes.dex */
public class TimerReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (ActivityManager.isUserAMonkey()) {
            try {
                if (GameLauncherApplication.receiver != null) {
                    context.unregisterReceiver(GameLauncherApplication.receiver);
                    GameLauncherApplication.receiver = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("GameSpace", "ActivityManager.isUserAMonkey == true unregisterReceiver time tick");
        }
    }
}
