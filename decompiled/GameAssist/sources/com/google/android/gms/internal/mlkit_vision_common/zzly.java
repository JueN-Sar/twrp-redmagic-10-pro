package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes.dex */
final class zzly extends zzmd {

    /* renamed from: a, reason: collision with root package name */
    private String f12601a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f12602b;

    /* renamed from: c, reason: collision with root package name */
    private int f12603c;

    /* renamed from: d, reason: collision with root package name */
    private byte f12604d;

    zzly() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmd
    public final zzmd a(boolean z) {
        this.f12602b = true;
        this.f12604d = (byte) (1 | this.f12604d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmd
    public final zzmd b(int i2) {
        this.f12603c = 1;
        this.f12604d = (byte) (this.f12604d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmd
    public final zzme c() {
        String str;
        if (this.f12604d == 3 && (str = this.f12601a) != null) {
            return new zzma(str, this.f12602b, this.f12603c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f12601a == null) {
            sb.append(" libraryName");
        }
        if ((this.f12604d & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.f12604d & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final zzmd d(String str) {
        this.f12601a = "vision-common";
        return this;
    }
}
