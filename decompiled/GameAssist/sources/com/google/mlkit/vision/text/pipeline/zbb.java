package com.google.mlkit.vision.text.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki;

/* loaded from: classes.dex */
final class zbb extends zbo {

    /* renamed from: a, reason: collision with root package name */
    private final int f16151a;

    /* renamed from: b, reason: collision with root package name */
    private final zbki f16152b;

    zbb(int i2, zbki zbkiVar) {
        this.f16151a = i2;
        this.f16152b = zbkiVar;
    }

    @Override // com.google.mlkit.vision.text.pipeline.zbo
    public final int a() {
        return this.f16151a;
    }

    @Override // com.google.mlkit.vision.text.pipeline.zbo
    public final zbki b() {
        return this.f16152b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zbo) {
            zbo zboVar = (zbo) obj;
            if (this.f16151a == zboVar.a() && this.f16152b.equals(zboVar.b())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.f16151a;
        return this.f16152b.hashCode() ^ ((i2 ^ 1000003) * 1000003);
    }

    public final String toString() {
        return "VkpStatus{exceptionType=" + this.f16151a + ", remoteException=" + this.f16152b.toString() + "}";
    }
}
