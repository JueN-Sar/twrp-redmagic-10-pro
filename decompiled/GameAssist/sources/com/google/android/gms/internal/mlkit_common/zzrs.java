package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
final class zzrs extends zzsa {

    /* renamed from: a, reason: collision with root package name */
    private String f11802a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f11803b;

    /* renamed from: c, reason: collision with root package name */
    private int f11804c;

    /* renamed from: d, reason: collision with root package name */
    private byte f11805d;

    zzrs() {
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsa
    public final zzsa a(boolean z) {
        this.f11803b = true;
        this.f11805d = (byte) (1 | this.f11805d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsa
    public final zzsa b(int i2) {
        this.f11804c = 1;
        this.f11805d = (byte) (this.f11805d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzsa
    public final zzsb c() {
        String str;
        if (this.f11805d == 3 && (str = this.f11802a) != null) {
            return new zzru(str, this.f11803b, this.f11804c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f11802a == null) {
            sb.append(" libraryName");
        }
        if ((this.f11805d & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.f11805d & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final zzsa d(String str) {
        this.f11802a = "common";
        return this;
    }
}
