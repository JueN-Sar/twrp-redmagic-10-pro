package cn.nubia.componentsdk.until;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import cn.nubia.componentsdk.MiscCallbackListener;
import cn.nubia.componentsdk.constant.CallbackListener;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
public class PayBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static PayBroadcastReceiver f6067a = null;

    /* renamed from: b, reason: collision with root package name */
    private static CallbackListener f6068b = null;

    /* renamed from: c, reason: collision with root package name */
    private static String f6069c = "PayBroadcastReceiver";

    public static void a(Context context, CallbackListener callbackListener) {
        PayLog.a(f6069c, "start register payBroadcast!");
        if (f6067a == null) {
            f6067a = new PayBroadcastReceiver();
        }
        f6068b = callbackListener;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("nubia.pay.broadcast.action");
        if (Build.VERSION.SDK_INT > 33) {
            context.registerReceiver(f6067a, intentFilter, 2);
        } else {
            context.registerReceiver(f6067a, intentFilter);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("response_code", -112);
        String stringExtra = intent.getStringExtra(Constants.EXTRA_RESULT);
        PayBroadcastReceiver payBroadcastReceiver = f6067a;
        if (payBroadcastReceiver != null) {
            context.unregisterReceiver(payBroadcastReceiver);
            PayLog.a(f6069c, "start unregister payBroadcast!");
        }
        PayLog.a("PayBroadcast", "response_code：" + intExtra);
        if (intExtra != 31) {
            MiscCallbackListener.a(intExtra, stringExtra);
            return;
        }
        CallbackListener callbackListener = f6068b;
        if (callbackListener != null) {
            callbackListener.a(0, "silent_install");
        } else {
            MiscCallbackListener.a(intExtra, stringExtra);
        }
    }
}
