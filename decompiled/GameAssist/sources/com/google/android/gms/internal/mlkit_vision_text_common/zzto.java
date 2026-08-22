package com.google.android.gms.internal.mlkit_vision_text_common;

/* loaded from: classes.dex */
final class zzto extends zztt {

    /* renamed from: a, reason: collision with root package name */
    private String f13545a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f13546b;

    /* renamed from: c, reason: collision with root package name */
    private int f13547c;

    /* renamed from: d, reason: collision with root package name */
    private byte f13548d;

    zzto() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztt
    public final zztt a(boolean z) {
        this.f13546b = true;
        this.f13548d = (byte) (1 | this.f13548d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztt
    public final zztt b(int i2) {
        this.f13547c = 1;
        this.f13548d = (byte) (this.f13548d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zztt
    public final zztu c() {
        String str;
        if (this.f13548d == 3 && (str = this.f13545a) != null) {
            return new zztq(str, this.f13546b, this.f13547c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f13545a == null) {
            sb.append(" libraryName");
        }
        if ((this.f13548d & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.f13548d & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final zztt d(String str) {
        if (str == null) {
            throw new NullPointerException("Null libraryName");
        }
        this.f13545a = str;
        return this;
    }
}
