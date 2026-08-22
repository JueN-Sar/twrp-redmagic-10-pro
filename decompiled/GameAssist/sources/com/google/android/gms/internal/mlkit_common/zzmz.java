package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
public final class zzmz {

    /* renamed from: a, reason: collision with root package name */
    private zznl f11743a;

    /* renamed from: b, reason: collision with root package name */
    private Long f11744b;

    /* renamed from: c, reason: collision with root package name */
    private zzmu f11745c;

    /* renamed from: d, reason: collision with root package name */
    private Long f11746d;

    /* renamed from: e, reason: collision with root package name */
    private zzna f11747e;

    /* renamed from: f, reason: collision with root package name */
    private Long f11748f;

    public final zzmz b(Long l2) {
        this.f11748f = l2;
        return this;
    }

    public final zzmz c(zzna zznaVar) {
        this.f11747e = zznaVar;
        return this;
    }

    public final zzmz d(zzmu zzmuVar) {
        this.f11745c = zzmuVar;
        return this;
    }

    public final zzmz e(Long l2) {
        this.f11746d = Long.valueOf(l2.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzmz f(zznl zznlVar) {
        this.f11743a = zznlVar;
        return this;
    }

    public final zzmz g(Long l2) {
        this.f11744b = Long.valueOf(l2.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zznc i() {
        return new zznc(this, null);
    }
}
