package cn.nubia.gamecenter.settings.applearning;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.nubia.gamecenter.settings.utils.LogUtil;

/* loaded from: classes.dex */
public class AppLearningReceiver extends BroadcastReceiver {
    public static final String ACTION_GAME_MODE = "cn.nubia.action.GAME_MODE";
    public static final String GAME_MODE_EXTRA_ISRUNNING = "isRunning";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!ACTION_GAME_MODE.equals(action)) {
            context.startService(new Intent(context, (Class<?>) AppTimeLockService.class));
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("isRunning", false);
        LogUtil.i("AppLearningReceiver", "onReceive:" + action + "," + booleanExtra);
        if (booleanExtra) {
            context.startService(new Intent(context, (Class<?>) AppTimeLockService.class));
        }
    }
}
