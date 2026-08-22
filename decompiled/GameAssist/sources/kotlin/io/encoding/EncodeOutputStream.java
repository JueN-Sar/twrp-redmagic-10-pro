package kotlin.io.encoding;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;

@ExperimentalEncodingApi
@Metadata
/* loaded from: classes2.dex */
final class EncodeOutputStream extends OutputStream {

    /* renamed from: c, reason: collision with root package name */
    private final OutputStream f18489c;

    /* renamed from: h, reason: collision with root package name */
    private final Base64 f18490h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18491i;

    /* renamed from: j, reason: collision with root package name */
    private int f18492j;

    /* renamed from: k, reason: collision with root package name */
    private final byte[] f18493k;

    /* renamed from: l, reason: collision with root package name */
    private final byte[] f18494l;

    /* renamed from: m, reason: collision with root package name */
    private int f18495m;

    private final void a() {
        if (this.f18491i) {
            throw new IOException("The output stream is closed.");
        }
    }

    private final int c(byte[] bArr, int i2, int i3) {
        int min = Math.min(3 - this.f18495m, i3 - i2);
        ArraysKt___ArraysJvmKt.d(bArr, this.f18494l, this.f18495m, i2, i2 + min);
        int i4 = this.f18495m + min;
        this.f18495m = i4;
        if (i4 == 3) {
            d();
        }
        return min;
    }

    private final void d() {
        if (e(this.f18494l, 0, this.f18495m) != 4) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.f18495m = 0;
    }

    private final int e(byte[] bArr, int i2, int i3) {
        int g2 = this.f18490h.g(bArr, this.f18493k, 0, i2, i3);
        if (this.f18492j == 0) {
            this.f18489c.write(Base64.f18470c.m());
            this.f18492j = 76;
            if (g2 > 76) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        this.f18489c.write(this.f18493k, 0, g2);
        this.f18492j -= g2;
        return g2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f18491i) {
            return;
        }
        this.f18491i = true;
        if (this.f18495m != 0) {
            d();
        }
        this.f18489c.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        a();
        this.f18489c.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        a();
        byte[] bArr = this.f18494l;
        int i3 = this.f18495m;
        int i4 = i3 + 1;
        this.f18495m = i4;
        bArr[i3] = (byte) i2;
        if (i4 == 3) {
            d();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] source, int i2, int i3) {
        int i4;
        Intrinsics.e(source, "source");
        a();
        if (i2 < 0 || i3 < 0 || (i4 = i2 + i3) > source.length) {
            throw new IndexOutOfBoundsException("offset: " + i2 + ", length: " + i3 + ", source size: " + source.length);
        }
        if (i3 == 0) {
            return;
        }
        int i5 = this.f18495m;
        if (i5 < 3) {
            if (i5 != 0) {
                i2 += c(source, i2, i4);
                if (this.f18495m != 0) {
                    return;
                }
            }
            while (i2 + 3 <= i4) {
                int min = Math.min((this.f18490h.k() ? this.f18492j : this.f18493k.length) / 4, (i4 - i2) / 3);
                int i6 = (min * 3) + i2;
                if (e(source, i2, i6) != min * 4) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                i2 = i6;
            }
            ArraysKt___ArraysJvmKt.d(source, this.f18494l, 0, i2, i4);
            this.f18495m = i4 - i2;
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
