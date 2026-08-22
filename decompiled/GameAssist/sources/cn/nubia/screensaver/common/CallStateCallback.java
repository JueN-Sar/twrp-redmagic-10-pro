package cn.nubia.screensaver.common;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import cn.nubia.screensaver.GSWindowController;
import cn.nubia.screensaver.GameScreensaverManager;

/* loaded from: classes.dex */
public class CallStateCallback extends TelephonyCallback implements TelephonyCallback.CallStateListener {

    /* renamed from: a, reason: collision with root package name */
    private final GameScreensaverManager f9018a;

    /* renamed from: b, reason: collision with root package name */
    private final GSWindowController f9019b;

    /* renamed from: c, reason: collision with root package name */
    private final Handler f9020c;

    /* renamed from: d, reason: collision with root package name */
    private final Context f9021d;

    /* renamed from: e, reason: collision with root package name */
    private final TelephonyManager f9022e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f9023f = true;

    public CallStateCallback(GameScreensaverManager gameScreensaverManager) {
        this.f9018a = gameScreensaverManager;
        Handler C = gameScreensaverManager.C();
        this.f9020c = C;
        Context H = gameScreensaverManager.H();
        this.f9021d = H;
        this.f9019b = (GSWindowController) gameScreensaverManager.I(GSWindowController.class);
        this.f9022e = (TelephonyManager) H.getSystemService("phone");
        C.post(new Runnable() { // from class: cn.nubia.screensaver.common.e
            @Override // java.lang.Runnable
            public final void run() {
                CallStateCallback.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f9022e.registerTelephonyCallback(new HandlerExecutor(this.f9020c), this);
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e() {
        this.f9018a.o0("callStatus");
    }

    private void f() {
        if (!this.f9019b.J() || this.f9022e.getCallState() == 0) {
            return;
        }
        this.f9020c.post(new Runnable() { // from class: cn.nubia.screensaver.common.d
            @Override // java.lang.Runnable
            public final void run() {
                CallStateCallback.this.e();
            }
        });
    }

    public boolean c() {
        return this.f9023f;
    }

    @Override // android.telephony.TelephonyCallback.CallStateListener
    public void onCallStateChanged(int i2) {
        this.f9023f = i2 == 0;
        f();
    }
}
