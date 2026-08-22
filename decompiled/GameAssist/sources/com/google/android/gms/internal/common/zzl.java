package com.google.android.gms.internal.common;

/* loaded from: classes.dex */
final class zzl extends zzk {

    /* renamed from: a, reason: collision with root package name */
    private final char f11399a;

    @Override // com.google.android.gms.internal.common.zzo
    public final boolean a(char c2) {
        return c2 == this.f11399a;
    }

    public final String toString() {
        char[] cArr = new char[6];
        cArr[0] = '\\';
        cArr[1] = 'u';
        cArr[2] = 0;
        cArr[3] = 0;
        cArr[4] = 0;
        cArr[5] = 0;
        int i2 = this.f11399a;
        for (int i3 = 0; i3 < 4; i3++) {
            cArr[5 - i3] = "0123456789ABCDEF".charAt(i2 & 15);
            i2 >>= 4;
        }
        return "CharMatcher.is('" + String.copyValueOf(cArr) + "')";
    }
}
