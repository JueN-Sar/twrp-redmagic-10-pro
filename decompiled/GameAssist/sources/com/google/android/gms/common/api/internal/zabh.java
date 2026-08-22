package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
final class zabh extends com.google.android.gms.internal.base.zau {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zabi f10739a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zabh(zabi zabiVar, Looper looper) {
        super(looper);
        this.f10739a = zabiVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            ((zabg) message.obj).b(this.f10739a);
        } else {
            if (i2 == 2) {
                throw ((RuntimeException) message.obj);
            }
            Log.w("GACStateManager", "Unknown message id: " + i2);
        }
    }
}
