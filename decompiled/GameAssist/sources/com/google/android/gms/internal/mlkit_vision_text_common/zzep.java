package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class zzep {

    /* renamed from: a, reason: collision with root package name */
    private zzes f13165a;

    /* renamed from: b, reason: collision with root package name */
    private Integer f13166b;

    /* renamed from: c, reason: collision with root package name */
    private zznw f13167c;

    public final zzep a(Integer num) {
        this.f13166b = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zzep b(zznw zznwVar) {
        this.f13167c = zznwVar;
        return this;
    }

    public final zzep c(zzes zzesVar) {
        this.f13165a = zzesVar;
        return this;
    }

    public final zzeu e() {
        return new zzeu(this, null);
    }
}
