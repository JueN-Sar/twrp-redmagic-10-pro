package com.google.android.gms.internal.mlkit_vision_text_common;

/* loaded from: classes.dex */
final class zztq extends zztu {

    /* renamed from: a, reason: collision with root package name */
    private final String f13549a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f13550b;

    /* renamed from: c, reason: collision with root package name */
    private final int f13551c;

    /* synthetic */ zztq(String str, boolean z, int i2, zztp zztpVar) {
        this.f13549a = str;
        this.f13550b = z;
        this.f13551c = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztu
    public final int a() {
        return this.f13551c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztu
    public final String b() {
        return this.f13549a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztu
    public final boolean c() {
        return this.f13550b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zztu) {
            zztu zztuVar = (zztu) obj;
            if (this.f13549a.equals(zztuVar.b()) && this.f13550b == zztuVar.c() && this.f13551c == zztuVar.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f13549a.hashCode() ^ 1000003;
        return this.f13551c ^ (((hashCode * 1000003) ^ (true != this.f13550b ? 1237 : 1231)) * 1000003);
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.f13549a + ", enableFirelog=" + this.f13550b + ", firelogEventType=" + this.f13551c + "}";
    }
}
