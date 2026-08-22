package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbvd implements zbvy {

    /* renamed from: b, reason: collision with root package name */
    private static final zbvk f12995b = new zbvb();

    /* renamed from: a, reason: collision with root package name */
    private final zbvk f12996a;

    public zbvd() {
        zbty c2 = zbty.c();
        int i2 = zbvu.f13023d;
        zbvc zbvcVar = new zbvc(c2, f12995b);
        byte[] bArr = zbuo.f12985b;
        this.f12996a = zbvcVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvy
    public final zbvx a(Class cls) {
        int i2 = zbvz.f13034b;
        if (!zbuf.class.isAssignableFrom(cls)) {
            int i3 = zbvu.f13023d;
        }
        zbvj a2 = this.f12996a.a(cls);
        if (a2.c()) {
            int i4 = zbvu.f13023d;
            return zbvq.j(zbvz.r(), zbts.a(), a2.a());
        }
        int i5 = zbvu.f13023d;
        return zbvp.B(cls, a2, zbvt.a(), zbuz.a(), zbvz.r(), a2.zbc() + (-1) != 1 ? zbts.a() : null, zbvi.a());
    }
}
