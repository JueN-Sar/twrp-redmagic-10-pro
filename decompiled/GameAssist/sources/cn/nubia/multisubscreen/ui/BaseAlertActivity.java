package cn.nubia.multisubscreen.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import com.zte.gameassist.utils.GaLog;
import com.zte.mifavor.widget.AlertActivity;
import com.zte.mifavor.widget.AlertDialog;

/* loaded from: classes.dex */
public abstract class BaseAlertActivity extends AlertActivity {

    /* renamed from: m, reason: collision with root package name */
    protected AlertDialog f8093m;

    /* renamed from: n, reason: collision with root package name */
    protected int f8094n;

    /* renamed from: j, reason: collision with root package name */
    final String f8090j = "recentapps";

    /* renamed from: k, reason: collision with root package name */
    final String f8091k = "homekey";

    /* renamed from: l, reason: collision with root package name */
    final String f8092l = "reason";

    /* renamed from: o, reason: collision with root package name */
    private BroadcastReceiver f8095o = new BroadcastReceiver() { // from class: cn.nubia.multisubscreen.ui.BaseAlertActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("com.zte.multi.subscreen.ACTION_CLOSE_ALERT".equalsIgnoreCase(action)) {
                    GaLog.a("MultiSubScreen_BaseAlertActivity", "ACTION_CLOSE_ALERT finish");
                    if (BaseAlertActivity.this.isFinishing()) {
                        return;
                    }
                    BaseAlertActivity.this.finish();
                    return;
                }
                if (("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) && "recentapps".equals(intent.getStringExtra("reason"))) || "homekey".equals(intent.getStringExtra("reason"))) {
                    BaseAlertActivity.this.f();
                    GaLog.a("MultiSubScreen_BaseAlertActivity", "ACTION_CLOSE_SYSTEM_DIALOGS finish");
                    if (BaseAlertActivity.this.isFinishing()) {
                        return;
                    }
                    BaseAlertActivity.this.finish();
                }
            }
        }
    };

    protected abstract void f();

    @Override // com.zte.mifavor.widget.AlertActivity, android.app.Activity
    @CallSuper
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setFinishOnTouchOutside(false);
        setTitle("");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zte.multi.subscreen.ACTION_CLOSE_ALERT");
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        registerReceiver(this.f8095o, intentFilter, 2);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.f8095o;
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (Exception e2) {
                GaLog.k("MultiSubScreen_BaseAlertActivity", "onDestroy unregisterReceiver exception : " + e2.toString());
            }
        }
        AlertDialog alertDialog = this.f8093m;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.f8093m.dismiss();
        this.f8093m = null;
    }
}
