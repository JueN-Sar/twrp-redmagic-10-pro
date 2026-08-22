package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbty implements zbvk {

    /* renamed from: a, reason: collision with root package name */
    private static final zbty f12973a = new zbty();

    private zbty() {
    }

    public static zbty c() {
        return f12973a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvk
    public final zbvj a(Class cls) {
        if (!zbuf.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
        try {
            return (zbvj) zbuf.w(cls.asSubclass(zbuf.class)).q(3, null, null);
        } catch (Exception e2) {
            throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvk
    public final boolean b(Class cls) {
        return zbuf.class.isAssignableFrom(cls);
    }
}
