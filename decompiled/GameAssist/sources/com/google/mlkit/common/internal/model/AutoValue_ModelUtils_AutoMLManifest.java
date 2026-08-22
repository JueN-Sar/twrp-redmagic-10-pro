package com.google.mlkit.common.internal.model;

import com.google.mlkit.common.internal.model.ModelUtils;

/* loaded from: classes.dex */
final class AutoValue_ModelUtils_AutoMLManifest extends ModelUtils.AutoMLManifest {

    /* renamed from: a, reason: collision with root package name */
    private final String f15888a;

    /* renamed from: b, reason: collision with root package name */
    private final String f15889b;

    /* renamed from: c, reason: collision with root package name */
    private final String f15890c;

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    public String a() {
        return this.f15890c;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    public String b() {
        return this.f15889b;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    public String c() {
        return this.f15888a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.AutoMLManifest) {
            ModelUtils.AutoMLManifest autoMLManifest = (ModelUtils.AutoMLManifest) obj;
            if (this.f15888a.equals(autoMLManifest.c()) && this.f15889b.equals(autoMLManifest.b()) && this.f15890c.equals(autoMLManifest.a())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((this.f15888a.hashCode() ^ 1000003) * 1000003) ^ this.f15889b.hashCode();
        return this.f15890c.hashCode() ^ (hashCode * 1000003);
    }

    public final String toString() {
        return "AutoMLManifest{modelType=" + this.f15888a + ", modelFile=" + this.f15889b + ", labelsFile=" + this.f15890c + "}";
    }
}
