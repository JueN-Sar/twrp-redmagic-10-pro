package cn.nubia.gamelauncher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.service.GameFeatureService;

/* loaded from: classes.dex */
public class GameFeatureReceiver extends BroadcastReceiver {
    private static final String TAG = "gcs:GameFeatureReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.i(TAG, "onReceive, action = " + action);
        if (TextUtils.isEmpty(action) || !action.equals(GameFeatureService.ACTION_CONTROL_PANEL)) {
            return;
        }
        Intent intent2 = new Intent(context.getApplicationContext(), (Class<?>) GameFeatureService.class);
        intent2.putExtra("packageName", intent.getStringExtra("packageName"));
        intent2.putExtra(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY, intent.getStringExtra(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY));
        intent2.putExtra(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_CALLING_PKGNAME, intent.getStringExtra(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_CALLING_PKGNAME));
        intent2.putExtra("type", GameFeatureService.ACTION_TYPE_CONTROL_PANEL);
        context.startService(intent2);
    }
}
