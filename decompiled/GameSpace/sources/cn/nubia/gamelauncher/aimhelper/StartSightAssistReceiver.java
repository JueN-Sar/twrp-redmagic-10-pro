package cn.nubia.gamelauncher.aimhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class StartSightAssistReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("cn.nubia.gamelauncher.action.START_SIGHTASSIST".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("packagename");
            int intExtra = intent.getIntExtra("enable", 0);
            LogUtil.i(this, "action= " + intent.getAction() + " packageName=" + stringExtra + "  enable=" + intExtra);
            AimService.changeSwitch(context, stringExtra, intExtra != 0);
        }
    }
}
