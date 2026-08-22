package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
class zbtb extends zbta {
    protected final byte[] zba;

    zbtb(byte[] bArr) {
        super(null);
        bArr.getClass();
        this.zba = bArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    public byte b(int i2) {
        return this.zba[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    byte d(int i2) {
        return this.zba[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zbtc) || f() != ((zbtc) obj).f()) {
            return false;
        }
        if (f() == 0) {
            return true;
        }
        if (!(obj instanceof zbtb)) {
            return obj.equals(this);
        }
        zbtb zbtbVar = (zbtb) obj;
        int k2 = k();
        int k3 = zbtbVar.k();
        if (k2 != 0 && k3 != 0 && k2 != k3) {
            return false;
        }
        int f2 = f();
        if (f2 > zbtbVar.f()) {
            throw new IllegalArgumentException("Length too large: " + f2 + f());
        }
        if (f2 > zbtbVar.f()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + f2 + ", " + zbtbVar.f());
        }
        byte[] bArr = this.zba;
        byte[] bArr2 = zbtbVar.zba;
        zbtbVar.m();
        int i2 = 0;
        int i3 = 0;
        while (i2 < f2) {
            if (bArr[i2] != bArr2[i3]) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    public int f() {
        return this.zba.length;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    protected final int g(int i2, int i3, int i4) {
        return zbuo.b(i2, this.zba, 0, i4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    public final zbtc h(int i2, int i3) {
        int j2 = zbtc.j(0, i3, f());
        return j2 == 0 ? zbtc.zbb : new zbsw(this.zba, 0, j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    final void i(zbst zbstVar) {
        ((zbth) zbstVar).H(this.zba, 0, f());
    }

    protected int m() {
        return 0;
    }
}
