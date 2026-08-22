package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbvc implements zbvk {

    /* renamed from: a, reason: collision with root package name */
    private final zbvk[] f12994a;

    zbvc(zbvk... zbvkVarArr) {
        this.f12994a = zbvkVarArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvk
    public final zbvj a(Class cls) {
        for (int i2 = 0; i2 < 2; i2++) {
            zbvk zbvkVar = this.f12994a[i2];
            if (zbvkVar.b(cls)) {
                return zbvkVar.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvk
    public final boolean b(Class cls) {
        for (int i2 = 0; i2 < 2; i2++) {
            if (this.f12994a[i2].b(cls)) {
                return true;
            }
        }
        return false;
    }
}
