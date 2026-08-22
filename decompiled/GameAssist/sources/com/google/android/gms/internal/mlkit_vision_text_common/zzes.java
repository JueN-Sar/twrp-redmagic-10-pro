package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public final class zzes {

    /* renamed from: a, reason: collision with root package name */
    private final zzou f13171a;

    /* renamed from: b, reason: collision with root package name */
    private final Boolean f13172b;

    /* renamed from: c, reason: collision with root package name */
    private final Boolean f13173c;

    /* renamed from: d, reason: collision with root package name */
    private final zzod f13174d;

    /* renamed from: e, reason: collision with root package name */
    private final zzsd f13175e;

    /* synthetic */ zzes(zzeq zzeqVar, zzer zzerVar) {
        zzou zzouVar;
        Boolean bool;
        zzsd zzsdVar;
        zzouVar = zzeqVar.f13168a;
        this.f13171a = zzouVar;
        this.f13172b = null;
        bool = zzeqVar.f13169b;
        this.f13173c = bool;
        this.f13174d = null;
        zzsdVar = zzeqVar.f13170c;
        this.f13175e = zzsdVar;
    }

    public final zzou a() {
        return this.f13171a;
    }

    public final zzsd b() {
        return this.f13175e;
    }

    public final Boolean c() {
        return this.f13173c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzes)) {
            return false;
        }
        zzes zzesVar = (zzes) obj;
        return Objects.a(this.f13171a, zzesVar.f13171a) && Objects.a(null, null) && Objects.a(this.f13173c, zzesVar.f13173c) && Objects.a(null, null) && Objects.a(this.f13175e, zzesVar.f13175e);
    }

    public final int hashCode() {
        return Objects.b(this.f13171a, null, this.f13173c, null, this.f13175e);
    }
}
