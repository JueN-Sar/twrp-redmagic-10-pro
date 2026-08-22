package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
final class zabn implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f10759c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zabq f10760h;

    zabn(zabq zabqVar, int i2) {
        this.f10760h = zabqVar;
        this.f10759c = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f10760h.j(this.f10759c);
    }
}
