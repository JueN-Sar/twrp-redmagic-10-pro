package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes.dex */
final class zzad implements zzai {

    /* renamed from: a, reason: collision with root package name */
    private final int f11860a;

    /* renamed from: b, reason: collision with root package name */
    private final zzah f11861b;

    zzad(int i2, zzah zzahVar) {
        this.f11860a = i2;
        this.f11861b = zzahVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzai.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzai)) {
            return false;
        }
        zzai zzaiVar = (zzai) obj;
        return this.f11860a == zzaiVar.zza() && this.f11861b.equals(zzaiVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f11860a ^ 14552422) + (this.f11861b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f11860a + "intEncoding=" + this.f11861b + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzai
    public final int zza() {
        return this.f11860a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzai
    public final zzah zzb() {
        return this.f11861b;
    }
}
