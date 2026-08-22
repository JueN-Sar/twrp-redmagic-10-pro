package com.zte.gameassist.lowsugar;

import android.content.Context;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.lowsugar.receiver.AccountChangeReceiver;
import com.zte.gameassist.lowsugar.receiver.LowSugarReceiver;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;

/* loaded from: classes2.dex */
public class LowSugarApplication implements RotationMgr.Callback {

    /* renamed from: j, reason: collision with root package name */
    private static volatile LowSugarApplication f16696j;

    /* renamed from: c, reason: collision with root package name */
    private Context f16697c;

    /* renamed from: h, reason: collision with root package name */
    private AccountChangeReceiver f16698h = new AccountChangeReceiver();

    /* renamed from: i, reason: collision with root package name */
    private LowSugarReceiver f16699i = new LowSugarReceiver();

    private LowSugarApplication() {
    }

    public static LowSugarApplication c() {
        if (f16696j == null) {
            synchronized (LowSugarApplication.class) {
                try {
                    if (f16696j == null) {
                        f16696j = new LowSugarApplication();
                    }
                } finally {
                }
            }
        }
        return f16696j;
    }

    private void e() {
        this.f16698h.b(this.f16697c);
    }

    private void f() {
        this.f16699i.d(this.f16697c);
    }

    public void a(AccountChangeReceiver.AccountChangeCallback accountChangeCallback) {
        this.f16698h.a(accountChangeCallback);
    }

    public Context b() {
        return this.f16697c;
    }

    public void d(Context context) {
        this.f16697c = context;
        e();
        f();
        LowSugarUtils.q(context);
        LowSugarUtils.c(context);
        RotationMgr.e(b()).c(this);
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        LowSugarUtils.c(this.f16697c);
    }
}
