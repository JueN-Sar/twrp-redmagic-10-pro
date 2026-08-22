package cn.nubia.componentsdk.until;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import cn.nubia.componentsdk.MiscCallbackListener;
import cn.nubia.componentsdk.constant.CallbackListener;

/* loaded from: classes.dex */
public class SilentInstallBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private PayBroadcastReceiver f6071a;

    /* renamed from: b, reason: collision with root package name */
    private CallbackListener f6072b;

    /* renamed from: c, reason: collision with root package name */
    private InstallTimer f6073c;

    /* renamed from: d, reason: collision with root package name */
    private String f6074d = "SilentInstallBroadcastReceiver";

    /* renamed from: e, reason: collision with root package name */
    private Context f6075e;

    private class InstallTimer extends CountDownTimer {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ SilentInstallBroadcastReceiver f6076a;

        @Override // android.os.CountDownTimer
        public void onFinish() {
            PayLog.a(this.f6076a.f6074d, "静默安装计时器 计时结束");
            MiscCallbackListener.a(-110, "升级失败");
            this.f6076a.f6073c.cancel();
            if (this.f6076a.f6071a != null) {
                this.f6076a.f6075e.unregisterReceiver(this.f6076a.f6071a);
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            PayLog.a(this.f6076a.f6074d, " Timer run " + j2);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            CallbackListener callbackListener = this.f6072b;
            if (callbackListener != null) {
                callbackListener.a(0, "静默安装成功");
                this.f6073c.cancel();
                PayLog.a(this.f6074d, "监听到静默安装成功");
            }
            PayBroadcastReceiver payBroadcastReceiver = this.f6071a;
            if (payBroadcastReceiver != null) {
                context.unregisterReceiver(payBroadcastReceiver);
            }
        }
    }
}
