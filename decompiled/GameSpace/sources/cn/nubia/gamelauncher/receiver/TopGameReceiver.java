package cn.nubia.gamelauncher.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.nubia.gamelauncher.aimhelper.AimService;
import cn.nubia.gamelauncher.util.LogUtil;

/* loaded from: classes.dex */
public class TopGameReceiver extends BroadcastReceiver {
    public static final String ACTION_TOP_GAME_CHANGE = "cn.nubia.gamelauncher.action.TOP_STACK_GAME_CHANGE";
    private static final String TAG = "TopGameReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (ACTION_TOP_GAME_CHANGE.equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("packageName");
            LogUtil.i(TAG, "cn.nubia.gamelauncher.action.TOP_STACK_GAME_CHANGE packageName=" + stringExtra + "  status=" + intent.getIntExtra("status", -1));
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            intent.setComponent(new ComponentName(context, (Class<?>) AimService.class));
            context.startService(intent);
        }
    }
}
