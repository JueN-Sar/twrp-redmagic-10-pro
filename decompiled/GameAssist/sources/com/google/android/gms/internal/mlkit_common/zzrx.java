package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* loaded from: classes.dex */
final class zzrx extends zzsj {

    /* renamed from: a, reason: collision with root package name */
    private final zzmu f11817a;

    /* renamed from: b, reason: collision with root package name */
    private final String f11818b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f11819c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f11820d;

    /* renamed from: e, reason: collision with root package name */
    private final ModelType f11821e;

    /* renamed from: f, reason: collision with root package name */
    private final zzna f11822f;

    /* renamed from: g, reason: collision with root package name */
    private final int f11823g;

    /* synthetic */ zzrx(zzmu zzmuVar, String str, boolean z, boolean z2, ModelType modelType, zzna zznaVar, int i2, zzrw zzrwVar) {
        this.f11817a = zzmuVar;
        this.f11818b = str;
        this.f11819c = z;
        this.f11820d = z2;
        this.f11821e = modelType;
        this.f11822f = zznaVar;
        this.f11823g = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final int a() {
        return this.f11823g;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final ModelType b() {
        return this.f11821e;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final zzmu c() {
        return this.f11817a;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final zzna d() {
        return this.f11822f;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final String e() {
        return this.f11818b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzsj) {
            zzsj zzsjVar = (zzsj) obj;
            if (this.f11817a.equals(zzsjVar.c()) && this.f11818b.equals(zzsjVar.e()) && this.f11819c == zzsjVar.g() && this.f11820d == zzsjVar.f() && this.f11821e.equals(zzsjVar.b()) && this.f11822f.equals(zzsjVar.d()) && this.f11823g == zzsjVar.a()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final boolean f() {
        return this.f11820d;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsj
    public final boolean g() {
        return this.f11819c;
    }

    public final int hashCode() {
        return this.f11823g ^ ((((((((((((this.f11817a.hashCode() ^ 1000003) * 1000003) ^ this.f11818b.hashCode()) * 1000003) ^ (true != this.f11819c ? 1237 : 1231)) * 1000003) ^ (true != this.f11820d ? 1237 : 1231)) * 1000003) ^ this.f11821e.hashCode()) * 1000003) ^ this.f11822f.hashCode()) * 1000003);
    }

    public final String toString() {
        zzna zznaVar = this.f11822f;
        ModelType modelType = this.f11821e;
        return "RemoteModelLoggingOptions{errorCode=" + this.f11817a.toString() + ", tfliteSchemaVersion=" + this.f11818b + ", shouldLogRoughDownloadTime=" + this.f11819c + ", shouldLogExactDownloadTime=" + this.f11820d + ", modelType=" + modelType.toString() + ", downloadStatus=" + zznaVar.toString() + ", failureStatusCode=" + this.f11823g + "}";
    }
}
