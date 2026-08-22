package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public final class zznl {

    /* renamed from: a, reason: collision with root package name */
    private final zznh f11769a;

    /* renamed from: b, reason: collision with root package name */
    private final zznj f11770b;

    /* renamed from: c, reason: collision with root package name */
    private final zznj f11771c;

    /* renamed from: d, reason: collision with root package name */
    private final Boolean f11772d;

    /* synthetic */ zznl(zzni zzniVar, zznk zznkVar) {
        zznh zznhVar;
        zznhVar = zzniVar.f11768a;
        this.f11769a = zznhVar;
        this.f11770b = null;
        this.f11771c = null;
        this.f11772d = null;
    }

    public final zznh a() {
        return this.f11769a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zznl) && Objects.a(this.f11769a, ((zznl) obj).f11769a) && Objects.a(null, null) && Objects.a(null, null) && Objects.a(null, null);
    }

    public final int hashCode() {
        return Objects.b(this.f11769a, null, null, null);
    }
}
