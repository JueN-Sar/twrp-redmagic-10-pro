package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class zzin {

    /* renamed from: a, reason: collision with root package name */
    private Long f12547a;

    /* renamed from: b, reason: collision with root package name */
    private zzio f12548b;

    /* renamed from: c, reason: collision with root package name */
    private zzii f12549c;

    /* renamed from: d, reason: collision with root package name */
    private Integer f12550d;

    /* renamed from: e, reason: collision with root package name */
    private Integer f12551e;

    /* renamed from: f, reason: collision with root package name */
    private Integer f12552f;

    /* renamed from: g, reason: collision with root package name */
    private Integer f12553g;

    public final zzin b(Long l2) {
        this.f12547a = Long.valueOf(l2.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzin c(Integer num) {
        this.f12550d = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zzin d(zzii zziiVar) {
        this.f12549c = zziiVar;
        return this;
    }

    public final zzin e(Integer num) {
        this.f12552f = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zzin f(zzio zzioVar) {
        this.f12548b = zzioVar;
        return this;
    }

    public final zzin g(Integer num) {
        this.f12551e = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zzin h(Integer num) {
        this.f12553g = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zziq j() {
        return new zziq(this, null);
    }
}
