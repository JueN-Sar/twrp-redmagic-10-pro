package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import sun.misc.Unsafe;

/* loaded from: classes.dex */
final class zbwp extends zbwr {
    zbwp(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final double a(Object obj, long j2) {
        return Double.longBitsToDouble(this.f13055a.getLong(obj, j2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final float b(Object obj, long j2) {
        return Float.intBitsToFloat(this.f13055a.getInt(obj, j2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final void c(Object obj, long j2, boolean z) {
        if (zbws.f13063h) {
            zbws.d(obj, j2, r3 ? (byte) 1 : (byte) 0);
        } else {
            zbws.e(obj, j2, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final void d(Object obj, long j2, byte b2) {
        if (zbws.f13063h) {
            zbws.d(obj, j2, b2);
        } else {
            zbws.e(obj, j2, b2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final void e(Object obj, long j2, double d2) {
        this.f13055a.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final void f(Object obj, long j2, float f2) {
        this.f13055a.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwr
    public final boolean g(Object obj, long j2) {
        return zbws.f13063h ? zbws.y(obj, j2) : zbws.z(obj, j2);
    }
}
