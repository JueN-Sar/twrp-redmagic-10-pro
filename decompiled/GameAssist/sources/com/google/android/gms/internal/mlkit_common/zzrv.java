package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* loaded from: classes.dex */
final class zzrv extends zzsi {

    /* renamed from: a, reason: collision with root package name */
    private zzmu f11809a;

    /* renamed from: b, reason: collision with root package name */
    private String f11810b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f11811c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f11812d;

    /* renamed from: e, reason: collision with root package name */
    private ModelType f11813e;

    /* renamed from: f, reason: collision with root package name */
    private zzna f11814f;

    /* renamed from: g, reason: collision with root package name */
    private int f11815g;

    /* renamed from: h, reason: collision with root package name */
    private byte f11816h;

    zzrv() {
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsi a(zzna zznaVar) {
        if (zznaVar == null) {
            throw new NullPointerException("Null downloadStatus");
        }
        this.f11814f = zznaVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsi b(zzmu zzmuVar) {
        if (zzmuVar == null) {
            throw new NullPointerException("Null errorCode");
        }
        this.f11809a = zzmuVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsi c(int i2) {
        this.f11815g = i2;
        this.f11816h = (byte) (this.f11816h | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsi d(ModelType modelType) {
        if (modelType == null) {
            throw new NullPointerException("Null modelType");
        }
        this.f11813e = modelType;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsi e(boolean z) {
        this.f11812d = z;
        this.f11816h = (byte) (this.f11816h | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsi f(boolean z) {
        this.f11811c = z;
        this.f11816h = (byte) (this.f11816h | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsi
    public final zzsj g() {
        zzmu zzmuVar;
        String str;
        ModelType modelType;
        zzna zznaVar;
        if (this.f11816h == 7 && (zzmuVar = this.f11809a) != null && (str = this.f11810b) != null && (modelType = this.f11813e) != null && (zznaVar = this.f11814f) != null) {
            return new zzrx(zzmuVar, str, this.f11811c, this.f11812d, modelType, zznaVar, this.f11815g, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f11809a == null) {
            sb.append(" errorCode");
        }
        if (this.f11810b == null) {
            sb.append(" tfliteSchemaVersion");
        }
        if ((this.f11816h & 1) == 0) {
            sb.append(" shouldLogRoughDownloadTime");
        }
        if ((this.f11816h & 2) == 0) {
            sb.append(" shouldLogExactDownloadTime");
        }
        if (this.f11813e == null) {
            sb.append(" modelType");
        }
        if (this.f11814f == null) {
            sb.append(" downloadStatus");
        }
        if ((this.f11816h & 4) == 0) {
            sb.append(" failureStatusCode");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final zzsi h(String str) {
        this.f11810b = "NA";
        return this;
    }
}
