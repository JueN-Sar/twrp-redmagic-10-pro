package com.google.android.gms.internal.mlkit_vision_text_common;

/* loaded from: classes.dex */
public final class zzoh {

    /* renamed from: a, reason: collision with root package name */
    private Long f13481a;

    /* renamed from: b, reason: collision with root package name */
    private zzou f13482b;

    /* renamed from: c, reason: collision with root package name */
    private Boolean f13483c;

    /* renamed from: d, reason: collision with root package name */
    private Boolean f13484d;

    /* renamed from: e, reason: collision with root package name */
    private Boolean f13485e;

    public final zzoh a(Boolean bool) {
        this.f13484d = bool;
        return this;
    }

    public final zzoh b(Boolean bool) {
        this.f13485e = bool;
        return this;
    }

    public final zzoh c(Long l2) {
        this.f13481a = Long.valueOf(l2.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzoh d(zzou zzouVar) {
        this.f13482b = zzouVar;
        return this;
    }

    public final zzoh e(Boolean bool) {
        this.f13483c = bool;
        return this;
    }

    public final zzoj f() {
        return new zzoj(this, null);
    }
}
