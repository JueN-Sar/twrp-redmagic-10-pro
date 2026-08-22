package com.google.android.gms.internal.mlkit_vision_text_common;

/* loaded from: classes.dex */
final class zzcs implements zzcx {

    /* renamed from: a, reason: collision with root package name */
    private final int f13134a;

    /* renamed from: b, reason: collision with root package name */
    private final zzcw f13135b;

    zzcs(int i2, zzcw zzcwVar) {
        this.f13134a = i2;
        this.f13135b = zzcwVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzcx.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcx)) {
            return false;
        }
        zzcx zzcxVar = (zzcx) obj;
        return this.f13134a == zzcxVar.zza() && this.f13135b.equals(zzcxVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f13134a ^ 14552422) + (this.f13135b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f13134a + "intEncoding=" + this.f13135b + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcx
    public final int zza() {
        return this.f13134a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcx
    public final zzcw zzb() {
        return this.f13135b;
    }
}
