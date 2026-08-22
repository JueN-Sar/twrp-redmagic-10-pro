package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbkk extends zbki {
    private final Object zba;

    zbkk(Object obj) {
        this.zba = obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final Object a() {
        return this.zba;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final Object b(Object obj) {
        return this.zba;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final boolean c() {
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final boolean equals(Object obj) {
        if (obj instanceof zbkk) {
            return this.zba.equals(((zbkk) obj).zba);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final int hashCode() {
        return this.zba.hashCode() + 1502476572;
    }

    public final String toString() {
        return "Optional.of(" + this.zba.toString() + ")";
    }
}
