package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class zzoa {

    /* renamed from: a, reason: collision with root package name */
    private zzob f13475a;

    /* renamed from: b, reason: collision with root package name */
    private Integer f13476b;

    public final zzoa a(zzob zzobVar) {
        this.f13475a = zzobVar;
        return this;
    }

    public final zzoa b(Integer num) {
        this.f13476b = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zzod d() {
        return new zzod(this, null);
    }
}
