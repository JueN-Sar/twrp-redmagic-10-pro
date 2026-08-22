package cn.nubia.gameassist.plugin.config;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class UpdatePluginConfigReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("uri");
        if (stringExtra != null) {
            Intent intent2 = new Intent(context.getApplicationContext(), (Class<?>) UpdatePluginConfigService.class);
            intent2.putExtra("uri", stringExtra);
            context.startService(intent2);
        }
        GaLog.e("PluginConfig", "Receiver UpdatePluginConfig uri=" + stringExtra);
    }
}
