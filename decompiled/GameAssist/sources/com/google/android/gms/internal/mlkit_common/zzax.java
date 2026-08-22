package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
final class zzax implements zzbc {

    /* renamed from: a, reason: collision with root package name */
    private final int f11423a;

    /* renamed from: b, reason: collision with root package name */
    private final zzbb f11424b;

    zzax(int i2, zzbb zzbbVar) {
        this.f11423a = i2;
        this.f11424b = zzbbVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzbc.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbc)) {
            return false;
        }
        zzbc zzbcVar = (zzbc) obj;
        return this.f11423a == zzbcVar.zza() && this.f11424b.equals(zzbcVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f11423a ^ 14552422) + (this.f11424b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f11423a + "intEncoding=" + this.f11424b + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbc
    public final int zza() {
        return this.f11423a;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbc
    public final zzbb zzb() {
        return this.f11424b;
    }
}
