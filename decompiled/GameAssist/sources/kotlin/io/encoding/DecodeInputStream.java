package kotlin.io.encoding;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;

@ExperimentalEncodingApi
@Metadata
/* loaded from: classes2.dex */
final class DecodeInputStream extends InputStream {

    /* renamed from: c, reason: collision with root package name */
    private final InputStream f18480c;

    /* renamed from: h, reason: collision with root package name */
    private final Base64 f18481h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18482i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f18483j;

    /* renamed from: k, reason: collision with root package name */
    private final byte[] f18484k;

    /* renamed from: l, reason: collision with root package name */
    private final byte[] f18485l;

    /* renamed from: m, reason: collision with root package name */
    private final byte[] f18486m;

    /* renamed from: n, reason: collision with root package name */
    private int f18487n;

    /* renamed from: o, reason: collision with root package name */
    private int f18488o;

    private final void a(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = this.f18486m;
        int i4 = this.f18487n;
        ArraysKt___ArraysJvmKt.d(bArr2, bArr, i2, i4, i4 + i3);
        this.f18487n += i3;
        i();
    }

    private final int c(byte[] bArr, int i2, int i3, int i4) {
        int i5 = this.f18488o;
        this.f18488o = i5 + this.f18481h.e(this.f18485l, this.f18486m, i5, 0, i4);
        int min = Math.min(d(), i3 - i2);
        a(bArr, i2, min);
        j();
        return min;
    }

    private final int d() {
        return this.f18488o - this.f18487n;
    }

    private final int e(int i2) {
        this.f18485l[i2] = 61;
        if ((i2 & 3) != 2) {
            return i2 + 1;
        }
        int h2 = h();
        if (h2 >= 0) {
            this.f18485l[i2 + 1] = (byte) h2;
        }
        return i2 + 2;
    }

    private final int h() {
        int read;
        if (!this.f18481h.k()) {
            return this.f18480c.read();
        }
        do {
            read = this.f18480c.read();
            if (read == -1) {
                break;
            }
        } while (!Base64Kt.e(read));
        return read;
    }

    private final void i() {
        if (this.f18487n == this.f18488o) {
            this.f18487n = 0;
            this.f18488o = 0;
        }
    }

    private final void j() {
        byte[] bArr = this.f18486m;
        int length = bArr.length;
        int i2 = this.f18488o;
        if ((this.f18485l.length / 4) * 3 > length - i2) {
            ArraysKt___ArraysJvmKt.d(bArr, bArr, 0, this.f18487n, i2);
            this.f18488o -= this.f18487n;
            this.f18487n = 0;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f18482i) {
            return;
        }
        this.f18482i = true;
        this.f18480c.close();
    }

    @Override // java.io.InputStream
    public int read() {
        int i2 = this.f18487n;
        if (i2 < this.f18488o) {
            int i3 = this.f18486m[i2] & 255;
            this.f18487n = i2 + 1;
            i();
            return i3;
        }
        int read = read(this.f18484k, 0, 1);
        if (read == -1) {
            return -1;
        }
        if (read == 1) {
            return this.f18484k[0] & 255;
        }
        throw new IllegalStateException("Unreachable".toString());
    }

    @Override // java.io.InputStream
    public int read(byte[] destination, int i2, int i3) {
        int i4;
        boolean z;
        boolean z2;
        Intrinsics.e(destination, "destination");
        if (i2 >= 0 && i3 >= 0 && (i4 = i2 + i3) <= destination.length) {
            if (!this.f18482i) {
                if (this.f18483j) {
                    return -1;
                }
                if (i3 == 0) {
                    return 0;
                }
                if (d() >= i3) {
                    a(destination, i2, i3);
                    return i3;
                }
                int d2 = (((i3 - d()) + 2) / 3) * 4;
                int i5 = i2;
                while (true) {
                    z = this.f18483j;
                    if (z || d2 <= 0) {
                        break;
                    }
                    int min = Math.min(this.f18485l.length, d2);
                    int i6 = 0;
                    while (true) {
                        z2 = this.f18483j;
                        if (z2 || i6 >= min) {
                            break;
                        }
                        int h2 = h();
                        if (h2 == -1) {
                            this.f18483j = true;
                        } else if (h2 != 61) {
                            this.f18485l[i6] = (byte) h2;
                            i6++;
                        } else {
                            i6 = e(i6);
                            this.f18483j = true;
                        }
                    }
                    if (!z2 && i6 != min) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    d2 -= i6;
                    i5 += c(destination, i5, i4, i6);
                }
                if (i5 == i2 && z) {
                    return -1;
                }
                return i5 - i2;
            }
            throw new IOException("The input stream is closed.");
        }
        throw new IndexOutOfBoundsException("offset: " + i2 + ", length: " + i3 + ", buffer size: " + destination.length);
    }
}
