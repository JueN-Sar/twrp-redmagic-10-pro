package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zacq implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zact f10815c;

    zacq(zact zactVar) {
        this.f10815c = zactVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zacs zacsVar;
        zacsVar = this.f10815c.zah;
        zacsVar.c(new ConnectionResult(4));
    }
}
