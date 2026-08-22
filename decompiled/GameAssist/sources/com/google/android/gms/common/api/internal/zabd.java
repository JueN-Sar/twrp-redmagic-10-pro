package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class zabd extends zabw {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference f10719a;

    zabd(zabe zabeVar) {
        this.f10719a = new WeakReference(zabeVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabw
    public final void a() {
        zabe zabeVar = (zabe) this.f10719a.get();
        if (zabeVar == null) {
            return;
        }
        zabe.r(zabeVar);
    }
}
