package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbsw extends zbtb {
    private final int zbc;

    zbsw(byte[] bArr, int i2, int i3) {
        super(bArr);
        zbtc.j(0, i3, bArr.length);
        this.zbc = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtb, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    public final byte b(int i2) {
        int i3 = this.zbc;
        if (((i3 - (i2 + 1)) | i2) >= 0) {
            return ((zbtb) this).zba[i2];
        }
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i2);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i2 + ", " + i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtb, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    final byte d(int i2) {
        return ((zbtb) this).zba[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtb, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
    public final int f() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtb
    protected final int m() {
        return 0;
    }
}
