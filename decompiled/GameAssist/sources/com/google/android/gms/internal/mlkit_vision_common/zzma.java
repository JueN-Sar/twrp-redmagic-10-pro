package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes.dex */
final class zzma extends zzme {

    /* renamed from: a, reason: collision with root package name */
    private final String f12605a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f12606b;

    /* renamed from: c, reason: collision with root package name */
    private final int f12607c;

    /* synthetic */ zzma(String str, boolean z, int i2, zzlz zzlzVar) {
        this.f12605a = str;
        this.f12606b = z;
        this.f12607c = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final int a() {
        return this.f12607c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final String b() {
        return this.f12605a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzme
    public final boolean c() {
        return this.f12606b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzme) {
            zzme zzmeVar = (zzme) obj;
            if (this.f12605a.equals(zzmeVar.b()) && this.f12606b == zzmeVar.c() && this.f12607c == zzmeVar.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f12605a.hashCode() ^ 1000003;
        return this.f12607c ^ (((hashCode * 1000003) ^ (true != this.f12606b ? 1237 : 1231)) * 1000003);
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.f12605a + ", enableFirelog=" + this.f12606b + ", firelogEventType=" + this.f12607c + "}";
    }
}
