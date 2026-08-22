package cn.nubia.gameassist.panel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.nubia.gameassist.common.IHostPanel;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class CollapseReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private IHostPanel f6752a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f6753b = false;

    public CollapseReceiver(IHostPanel iHostPanel) {
        this.f6752a = iHostPanel;
    }

    public synchronized void a(Context context) {
        if (!this.f6753b) {
            context.registerReceiver(this, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 2);
            this.f6753b = true;
        }
    }

    public synchronized void b(Context context) {
        if (this.f6753b) {
            context.unregisterReceiver(this);
            this.f6753b = false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("reason");
        GaLog.j("CollapseReceiver", "receive intent:" + intent + ", reason:" + stringExtra);
        this.f6752a.b(stringExtra);
    }
}
