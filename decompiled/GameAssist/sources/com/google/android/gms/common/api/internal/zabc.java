package com.google.android.gms.common.api.internal;

import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
final class zabc extends com.google.android.gms.internal.base.zau {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zabe f10718a;

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            zabe.s(this.f10718a);
            return;
        }
        if (i2 == 2) {
            zabe.r(this.f10718a);
            return;
        }
        Log.w("GoogleApiClientImpl", "Unknown message id: " + i2);
    }
}
