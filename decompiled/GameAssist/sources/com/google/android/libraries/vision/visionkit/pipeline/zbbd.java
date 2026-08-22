package com.google.android.libraries.vision.visionkit.pipeline;

/* loaded from: classes.dex */
public final class zbbd {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f13760a;

    /* renamed from: b, reason: collision with root package name */
    private long f13761b;

    /* renamed from: c, reason: collision with root package name */
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcr f13762c;

    /* renamed from: d, reason: collision with root package name */
    private int f13763d;

    /* renamed from: e, reason: collision with root package name */
    private int f13764e;

    public final zbbd a(byte[] bArr) {
        this.f13760a = bArr;
        return this;
    }

    public final zbbd b(com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcr zbcrVar) {
        this.f13762c = zbcrVar;
        return this;
    }

    public final zbbd c(long j2) {
        this.f13761b = j2;
        return this;
    }

    public final zbbe d() {
        return new zbbe(this.f13760a, this.f13761b, this.f13762c, this.f13763d, this.f13764e);
    }

    public final zbbd e(int i2) {
        this.f13763d = 2;
        return this;
    }

    public final zbbd f(int i2) {
        this.f13764e = i2;
        return this;
    }
}
