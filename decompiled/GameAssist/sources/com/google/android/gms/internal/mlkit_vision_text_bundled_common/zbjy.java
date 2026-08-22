package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbjy extends zbki {
    static final zbjy zba = new zbjy();

    private zbjy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final Object a() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final Object b(Object obj) {
        return "";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final boolean c() {
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki
    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }
}
