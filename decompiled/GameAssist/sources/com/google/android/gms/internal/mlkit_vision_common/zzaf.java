package com.google.android.gms.internal.mlkit_vision_common;

import java.io.OutputStream;

/* loaded from: classes.dex */
final class zzaf extends OutputStream {

    /* renamed from: c, reason: collision with root package name */
    private long f11864c = 0;

    zzaf() {
    }

    final long a() {
        return this.f11864c;
    }

    @Override // java.io.OutputStream
    public final void write(int i2) {
        this.f11864c++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.f11864c += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i2, int i3) {
        int length;
        int i4;
        if (i2 >= 0 && i2 <= (length = bArr.length) && i3 >= 0 && (i4 = i2 + i3) <= length && i4 >= 0) {
            this.f11864c += i3;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
