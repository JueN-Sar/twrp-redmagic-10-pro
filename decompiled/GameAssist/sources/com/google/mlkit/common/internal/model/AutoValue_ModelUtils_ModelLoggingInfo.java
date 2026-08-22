package com.google.mlkit.common.internal.model;

import com.google.mlkit.common.internal.model.ModelUtils;

/* loaded from: classes.dex */
final class AutoValue_ModelUtils_ModelLoggingInfo extends ModelUtils.ModelLoggingInfo {

    /* renamed from: a, reason: collision with root package name */
    private final long f15891a;

    /* renamed from: b, reason: collision with root package name */
    private final String f15892b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f15893c;

    @Override // com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo
    public String a() {
        return this.f15892b;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo
    public long b() {
        return this.f15891a;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo
    public boolean c() {
        return this.f15893c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.ModelLoggingInfo) {
            ModelUtils.ModelLoggingInfo modelLoggingInfo = (ModelUtils.ModelLoggingInfo) obj;
            if (this.f15891a == modelLoggingInfo.b() && this.f15892b.equals(modelLoggingInfo.a()) && this.f15893c == modelLoggingInfo.c()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f15891a;
        return (true != this.f15893c ? 1237 : 1231) ^ ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.f15892b.hashCode()) * 1000003);
    }

    public final String toString() {
        return "ModelLoggingInfo{size=" + this.f15891a + ", hash=" + this.f15892b + ", manifestModel=" + this.f15893c + "}";
    }
}
