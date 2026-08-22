package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
final class zacr implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.signin.internal.zak f10816c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zact f10817h;

    zacr(zact zactVar, com.google.android.gms.signin.internal.zak zakVar) {
        this.f10817h = zactVar;
        this.f10816c = zakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zact.zad(this.f10817h, this.f10816c);
    }
}
