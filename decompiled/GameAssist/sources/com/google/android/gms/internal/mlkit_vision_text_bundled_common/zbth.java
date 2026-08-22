package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbth extends zbtk {

    /* renamed from: d, reason: collision with root package name */
    private final byte[] f12951d;

    /* renamed from: e, reason: collision with root package name */
    private final int f12952e;

    /* renamed from: f, reason: collision with root package name */
    private int f12953f;

    zbth(byte[] bArr, int i2, int i3) {
        super(null);
        int length = bArr.length;
        if (((length - i3) | i3) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i3)));
        }
        this.f12951d = bArr;
        this.f12953f = 0;
        this.f12952e = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void A(String str) {
        int i2 = this.f12953f;
        try {
            int d2 = zbtk.d(str.length() * 3);
            int d3 = zbtk.d(str.length());
            if (d3 != d2) {
                D(zbwv.c(str));
                byte[] bArr = this.f12951d;
                int i3 = this.f12953f;
                this.f12953f = zbwv.b(str, bArr, i3, this.f12952e - i3);
                return;
            }
            int i4 = i2 + d3;
            this.f12953f = i4;
            int b2 = zbwv.b(str, this.f12951d, i4, this.f12952e - i4);
            this.f12953f = i2;
            D((b2 - i2) - d3);
            this.f12953f = b2;
        } catch (zbwu e2) {
            this.f12953f = i2;
            g(str, e2);
        } catch (IndexOutOfBoundsException e3) {
            throw new zbti(e3);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void B(int i2, int i3) {
        D((i2 << 3) | i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void C(int i2, int i3) {
        D(i2 << 3);
        D(i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void D(int i2) {
        while ((i2 & (-128)) != 0) {
            try {
                byte[] bArr = this.f12951d;
                int i3 = this.f12953f;
                this.f12953f = i3 + 1;
                bArr[i3] = (byte) ((i2 | 128) & 255);
                i2 >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zbti(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f12953f), Integer.valueOf(this.f12952e), 1), e2);
            }
        }
        byte[] bArr2 = this.f12951d;
        int i4 = this.f12953f;
        this.f12953f = i4 + 1;
        bArr2[i4] = (byte) i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void E(int i2, long j2) {
        D(i2 << 3);
        F(j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void F(long j2) {
        boolean z;
        z = zbtk.f12955c;
        if (!z || this.f12952e - this.f12953f < 10) {
            while ((j2 & (-128)) != 0) {
                try {
                    byte[] bArr = this.f12951d;
                    int i2 = this.f12953f;
                    this.f12953f = i2 + 1;
                    bArr[i2] = (byte) ((((int) j2) | 128) & 255);
                    j2 >>>= 7;
                } catch (IndexOutOfBoundsException e2) {
                    throw new zbti(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f12953f), Integer.valueOf(this.f12952e), 1), e2);
                }
            }
            byte[] bArr2 = this.f12951d;
            int i3 = this.f12953f;
            this.f12953f = i3 + 1;
            bArr2[i3] = (byte) j2;
            return;
        }
        while (true) {
            int i4 = (int) j2;
            if ((j2 & (-128)) == 0) {
                byte[] bArr3 = this.f12951d;
                int i5 = this.f12953f;
                this.f12953f = i5 + 1;
                zbws.s(bArr3, i5, (byte) i4);
                return;
            }
            byte[] bArr4 = this.f12951d;
            int i6 = this.f12953f;
            this.f12953f = i6 + 1;
            zbws.s(bArr4, i6, (byte) ((i4 | 128) & 255));
            j2 >>>= 7;
        }
    }

    public final void H(byte[] bArr, int i2, int i3) {
        try {
            System.arraycopy(bArr, 0, this.f12951d, this.f12953f, i3);
            this.f12953f += i3;
        } catch (IndexOutOfBoundsException e2) {
            throw new zbti(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f12953f), Integer.valueOf(this.f12952e), Integer.valueOf(i3)), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final int i() {
        return this.f12952e - this.f12953f;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void j(byte b2) {
        try {
            byte[] bArr = this.f12951d;
            int i2 = this.f12953f;
            this.f12953f = i2 + 1;
            bArr[i2] = b2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zbti(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f12953f), Integer.valueOf(this.f12952e), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void k(int i2, boolean z) {
        D(i2 << 3);
        j(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void l(byte[] bArr, int i2, int i3) {
        D(i3);
        H(bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void m(int i2, zbtc zbtcVar) {
        D((i2 << 3) | 2);
        n(zbtcVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void n(zbtc zbtcVar) {
        D(zbtcVar.f());
        zbtcVar.i(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void o(int i2, int i3) {
        D((i2 << 3) | 5);
        p(i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void p(int i2) {
        try {
            byte[] bArr = this.f12951d;
            int i3 = this.f12953f;
            bArr[i3] = (byte) (i2 & 255);
            bArr[i3 + 1] = (byte) ((i2 >> 8) & 255);
            bArr[i3 + 2] = (byte) ((i2 >> 16) & 255);
            this.f12953f = i3 + 4;
            bArr[i3 + 3] = (byte) ((i2 >> 24) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zbti(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f12953f), Integer.valueOf(this.f12952e), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void q(int i2, long j2) {
        D((i2 << 3) | 1);
        r(j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void r(long j2) {
        try {
            byte[] bArr = this.f12951d;
            int i2 = this.f12953f;
            bArr[i2] = (byte) (((int) j2) & 255);
            bArr[i2 + 1] = (byte) (((int) (j2 >> 8)) & 255);
            bArr[i2 + 2] = (byte) (((int) (j2 >> 16)) & 255);
            bArr[i2 + 3] = (byte) (((int) (j2 >> 24)) & 255);
            bArr[i2 + 4] = (byte) (((int) (j2 >> 32)) & 255);
            bArr[i2 + 5] = (byte) (((int) (j2 >> 40)) & 255);
            bArr[i2 + 6] = (byte) (((int) (j2 >> 48)) & 255);
            this.f12953f = i2 + 8;
            bArr[i2 + 7] = (byte) (((int) (j2 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zbti(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f12953f), Integer.valueOf(this.f12952e), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void s(int i2, int i3) {
        D(i2 << 3);
        t(i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void t(int i2) {
        if (i2 >= 0) {
            D(i2);
        } else {
            F(i2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void u(byte[] bArr, int i2, int i3) {
        H(bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    final void v(int i2, zbvm zbvmVar, zbvx zbvxVar) {
        D((i2 << 3) | 2);
        D(((zbsj) zbvmVar).h(zbvxVar));
        zbvxVar.d(zbvmVar, this.f12956a);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void w(zbvm zbvmVar) {
        D(zbvmVar.a());
        zbvmVar.g(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void x(int i2, zbvm zbvmVar) {
        D(11);
        C(2, i2);
        D(26);
        w(zbvmVar);
        D(12);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void y(int i2, zbtc zbtcVar) {
        D(11);
        C(2, i2);
        m(3, zbtcVar);
        D(12);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtk
    public final void z(int i2, String str) {
        D((i2 << 3) | 2);
        A(str);
    }
}
