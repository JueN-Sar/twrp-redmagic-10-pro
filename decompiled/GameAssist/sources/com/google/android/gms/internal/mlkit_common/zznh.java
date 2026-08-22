package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public final class zznh {

    /* renamed from: a, reason: collision with root package name */
    private final String f11759a;

    /* renamed from: b, reason: collision with root package name */
    private final String f11760b;

    /* renamed from: c, reason: collision with root package name */
    private final zznf f11761c;

    /* renamed from: d, reason: collision with root package name */
    private final String f11762d;

    /* renamed from: e, reason: collision with root package name */
    private final String f11763e;

    /* renamed from: f, reason: collision with root package name */
    private final zzne f11764f;

    /* renamed from: g, reason: collision with root package name */
    private final Long f11765g;

    /* renamed from: h, reason: collision with root package name */
    private final Boolean f11766h;

    /* renamed from: i, reason: collision with root package name */
    private final Boolean f11767i;

    /* synthetic */ zznh(zznd zzndVar, zzng zzngVar) {
        String str;
        zznf zznfVar;
        String str2;
        zzne zzneVar;
        str = zzndVar.f11755a;
        this.f11759a = str;
        this.f11760b = null;
        zznfVar = zzndVar.f11756b;
        this.f11761c = zznfVar;
        this.f11762d = null;
        str2 = zzndVar.f11757c;
        this.f11763e = str2;
        zzneVar = zzndVar.f11758d;
        this.f11764f = zzneVar;
        this.f11765g = null;
        this.f11766h = null;
        this.f11767i = null;
    }

    public final zzne a() {
        return this.f11764f;
    }

    public final zznf b() {
        return this.f11761c;
    }

    public final String c() {
        return this.f11763e;
    }

    public final String d() {
        return this.f11759a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zznh)) {
            return false;
        }
        zznh zznhVar = (zznh) obj;
        return Objects.a(this.f11759a, zznhVar.f11759a) && Objects.a(null, null) && Objects.a(this.f11761c, zznhVar.f11761c) && Objects.a(null, null) && Objects.a(this.f11763e, zznhVar.f11763e) && Objects.a(this.f11764f, zznhVar.f11764f) && Objects.a(null, null) && Objects.a(null, null) && Objects.a(null, null);
    }

    public final int hashCode() {
        return Objects.b(this.f11759a, null, this.f11761c, null, this.f11763e, this.f11764f, null, null, null);
    }
}
