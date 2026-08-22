package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public final class zzky {

    /* renamed from: a, reason: collision with root package name */
    private String f12570a;

    /* renamed from: b, reason: collision with root package name */
    private String f12571b;

    /* renamed from: c, reason: collision with root package name */
    private String f12572c;

    /* renamed from: d, reason: collision with root package name */
    private String f12573d;

    /* renamed from: e, reason: collision with root package name */
    private zzp f12574e;

    /* renamed from: f, reason: collision with root package name */
    private String f12575f;

    /* renamed from: g, reason: collision with root package name */
    private Boolean f12576g;

    /* renamed from: h, reason: collision with root package name */
    private Boolean f12577h;

    /* renamed from: i, reason: collision with root package name */
    private Boolean f12578i;

    /* renamed from: j, reason: collision with root package name */
    private Integer f12579j;

    /* renamed from: k, reason: collision with root package name */
    private Integer f12580k;

    public final zzky b(String str) {
        this.f12570a = str;
        return this;
    }

    public final zzky c(String str) {
        this.f12571b = str;
        return this;
    }

    public final zzky d(Integer num) {
        this.f12579j = Integer.valueOf(num.intValue() & Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return this;
    }

    public final zzky e(Boolean bool) {
        this.f12576g = bool;
        return this;
    }

    public final zzky f(Boolean bool) {
        this.f12578i = bool;
        return this;
    }

    public final zzky g(Boolean bool) {
        this.f12577h = bool;
        return this;
    }

    public final zzky h(zzp zzpVar) {
        this.f12574e = zzpVar;
        return this;
    }

    public final zzky i(String str) {
        this.f12575f = str;
        return this;
    }

    public final zzky j(String str) {
        this.f12572c = str;
        return this;
    }

    public final zzky k(Integer num) {
        this.f12580k = num;
        return this;
    }

    public final zzky l(String str) {
        this.f12573d = str;
        return this;
    }

    public final zzla m() {
        return new zzla(this, null);
    }
}
