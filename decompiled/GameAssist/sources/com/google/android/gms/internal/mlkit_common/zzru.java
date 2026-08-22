package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
final class zzru extends zzsb {

    /* renamed from: a, reason: collision with root package name */
    private final String f11806a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f11807b;

    /* renamed from: c, reason: collision with root package name */
    private final int f11808c;

    /* synthetic */ zzru(String str, boolean z, int i2, zzrt zzrtVar) {
        this.f11806a = str;
        this.f11807b = z;
        this.f11808c = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsb
    public final int a() {
        return this.f11808c;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsb
    public final String b() {
        return this.f11806a;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsb
    public final boolean c() {
        return this.f11807b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzsb) {
            zzsb zzsbVar = (zzsb) obj;
            if (this.f11806a.equals(zzsbVar.b()) && this.f11807b == zzsbVar.c() && this.f11808c == zzsbVar.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f11806a.hashCode() ^ 1000003;
        return this.f11808c ^ (((hashCode * 1000003) ^ (true != this.f11807b ? 1237 : 1231)) * 1000003);
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.f11806a + ", enableFirelog=" + this.f11807b + ", firelogEventType=" + this.f11808c + "}";
    }
}
