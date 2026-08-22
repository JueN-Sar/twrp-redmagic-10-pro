package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.OutputStream;

/* loaded from: classes.dex */
final class zzcu extends OutputStream {

    /* renamed from: c, reason: collision with root package name */
    private long f13138c = 0;

    zzcu() {
    }

    final long a() {
        return this.f13138c;
    }

    @Override // java.io.OutputStream
    public final void write(int i2) {
        this.f13138c++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.f13138c += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i2, int i3) {
        int length;
        int i4;
        if (i2 >= 0 && i2 <= (length = bArr.length) && i3 >= 0 && (i4 = i2 + i3) <= length && i4 >= 0) {
            this.f13138c += i3;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
