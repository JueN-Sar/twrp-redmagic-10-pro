package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbvf {

    /* renamed from: a, reason: collision with root package name */
    private final zbve f13001a;

    private zbvf(zbww zbwwVar, Object obj, zbww zbwwVar2, Object obj2) {
        this.f13001a = new zbve(zbwwVar, obj, zbwwVar2, obj2);
    }

    static int b(zbve zbveVar, Object obj, Object obj2) {
        return zbtu.a(zbveVar.f12997a, 1, obj) + zbtu.a(zbveVar.f12999c, 2, obj2);
    }

    public static zbvf d(zbww zbwwVar, Object obj, zbww zbwwVar2, Object obj2) {
        return new zbvf(zbwwVar, obj, zbwwVar2, obj2);
    }

    static void e(zbtk zbtkVar, zbve zbveVar, Object obj, Object obj2) {
        zbtu.k(zbtkVar, zbveVar.f12997a, 1, obj);
        zbtu.k(zbtkVar, zbveVar.f12999c, 2, obj2);
    }

    public final int a(int i2, Object obj, Object obj2) {
        zbve zbveVar = this.f13001a;
        int d2 = zbtk.d(i2 << 3);
        int b2 = b(zbveVar, obj, obj2);
        return d2 + zbtk.d(b2) + b2;
    }

    final zbve c() {
        return this.f13001a;
    }
}
