package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {

    /* renamed from: i, reason: collision with root package name */
    private static final byte[] f19570i = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: c, reason: collision with root package name */
    Segment f19571c;

    /* renamed from: h, reason: collision with root package name */
    long f19572h;

    /* renamed from: okio.Buffer$1, reason: invalid class name */
    class AnonymousClass1 extends OutputStream {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Buffer f19573c;

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return this.f19573c + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            this.f19573c.writeByte((byte) i2);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            this.f19573c.Y(bArr, i2, i3);
        }
    }

    public static final class UnsafeCursor implements Closeable {

        /* renamed from: c, reason: collision with root package name */
        public Buffer f19575c;

        /* renamed from: h, reason: collision with root package name */
        private Segment f19576h;

        /* renamed from: i, reason: collision with root package name */
        public long f19577i;

        /* renamed from: j, reason: collision with root package name */
        public byte[] f19578j;

        /* renamed from: k, reason: collision with root package name */
        public int f19579k;

        /* renamed from: l, reason: collision with root package name */
        public int f19580l;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f19575c == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.f19575c = null;
            this.f19576h = null;
            this.f19577i = -1L;
            this.f19578j = null;
            this.f19579k = -1;
            this.f19580l = -1;
        }
    }

    private boolean t(Segment segment, int i2, ByteString byteString, int i3, int i4) {
        int i5 = segment.f19642c;
        byte[] bArr = segment.f19640a;
        while (i3 < i4) {
            if (i2 == i5) {
                Segment segment2 = segment.f19645f;
                byte[] bArr2 = segment2.f19640a;
                i2 = segment2.f19641b;
                segment = segment2;
                i5 = segment2.f19642c;
                bArr = bArr2;
            }
            if (bArr[i2] != byteString.f(i3)) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    public byte[] A() {
        try {
            return B(this.f19572h);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public byte[] B(long j2) {
        Util.b(this.f19572h, 0L, j2);
        if (j2 <= 2147483647L) {
            byte[] bArr = new byte[(int) j2];
            D(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
    }

    public ByteString C() {
        return new ByteString(A());
    }

    public void D(byte[] bArr) {
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = read(bArr, i2, bArr.length - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
    }

    public int E() {
        long j2 = this.f19572h;
        if (j2 < 4) {
            throw new IllegalStateException("size < 4: " + this.f19572h);
        }
        Segment segment = this.f19571c;
        int i2 = segment.f19641b;
        int i3 = segment.f19642c;
        if (i3 - i2 < 4) {
            return (readByte() & 255) | ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8);
        }
        byte[] bArr = segment.f19640a;
        int i4 = i2 + 3;
        int i5 = ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 2] & 255) << 8);
        int i6 = i2 + 4;
        int i7 = (bArr[i4] & 255) | i5;
        this.f19572h = j2 - 4;
        if (i6 == i3) {
            this.f19571c = segment.b();
            SegmentPool.a(segment);
        } else {
            segment.f19641b = i6;
        }
        return i7;
    }

    public String F(long j2, Charset charset) {
        Util.b(this.f19572h, 0L, j2);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j2 > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
        }
        if (j2 == 0) {
            return "";
        }
        Segment segment = this.f19571c;
        if (segment.f19641b + j2 > segment.f19642c) {
            return new String(B(j2), charset);
        }
        String str = new String(segment.f19640a, segment.f19641b, (int) j2, charset);
        int i2 = (int) (segment.f19641b + j2);
        segment.f19641b = i2;
        this.f19572h -= j2;
        if (i2 == segment.f19642c) {
            this.f19571c = segment.b();
            SegmentPool.a(segment);
        }
        return str;
    }

    public String I() {
        try {
            return F(this.f19572h, Util.f19653a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public short J() {
        return Util.d(readShort());
    }

    public String L(long j2) {
        return F(j2, Util.f19653a);
    }

    @Override // okio.BufferedSource
    public void O(long j2) {
        if (this.f19572h < j2) {
            throw new EOFException();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0055, code lost:
    
        if (r19 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0057, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0058, code lost:
    
        return r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int P(okio.Options r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            okio.Segment r1 = r1.f19571c
            r2 = -2
            if (r1 != 0) goto L13
            if (r19 == 0) goto Lc
            return r2
        Lc:
            okio.ByteString r1 = okio.ByteString.EMPTY
            int r0 = r0.indexOf(r1)
            return r0
        L13:
            byte[] r3 = r1.f19640a
            int r4 = r1.f19641b
            int r5 = r1.f19642c
            int[] r0 = r0.f19612h
            r6 = 0
            r7 = -1
            r9 = r1
            r8 = r6
            r10 = r7
        L20:
            int r11 = r8 + 1
            r12 = r0[r8]
            int r8 = r8 + 2
            r11 = r0[r11]
            if (r11 == r7) goto L2b
            r10 = r11
        L2b:
            if (r9 != 0) goto L2e
            goto L55
        L2e:
            r11 = 0
            if (r12 >= 0) goto L72
            int r12 = r12 * (-1)
            int r13 = r8 + r12
        L35:
            int r12 = r4 + 1
            r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r14 = r8 + 1
            r8 = r0[r8]
            if (r4 == r8) goto L42
            return r10
        L42:
            if (r14 != r13) goto L46
            r4 = 1
            goto L47
        L46:
            r4 = r6
        L47:
            if (r12 != r5) goto L62
            okio.Segment r3 = r9.f19645f
            int r5 = r3.f19641b
            byte[] r8 = r3.f19640a
            int r9 = r3.f19642c
            if (r3 != r1) goto L5c
            if (r4 != 0) goto L59
        L55:
            if (r19 == 0) goto L58
            return r2
        L58:
            return r10
        L59:
            r3 = r8
            r8 = r11
            goto L65
        L5c:
            r16 = r8
            r8 = r3
            r3 = r16
            goto L65
        L62:
            r8 = r9
            r9 = r5
            r5 = r12
        L65:
            if (r4 == 0) goto L6d
            r4 = r0[r14]
            r13 = r5
            r5 = r9
            r9 = r8
            goto L94
        L6d:
            r4 = r5
            r5 = r9
            r9 = r8
            r8 = r14
            goto L35
        L72:
            int r13 = r4 + 1
            r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r14 = r8 + r12
        L7a:
            if (r8 != r14) goto L7d
            return r10
        L7d:
            r15 = r0[r8]
            if (r4 != r15) goto L9a
            int r8 = r8 + r12
            r4 = r0[r8]
            if (r13 != r5) goto L94
            okio.Segment r9 = r9.f19645f
            int r3 = r9.f19641b
            byte[] r5 = r9.f19640a
            int r8 = r9.f19642c
            r13 = r3
            r3 = r5
            r5 = r8
            if (r9 != r1) goto L94
            r9 = r11
        L94:
            if (r4 < 0) goto L97
            return r4
        L97:
            int r8 = -r4
            r4 = r13
            goto L20
        L9a:
            int r8 = r8 + 1
            goto L7a
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.P(okio.Options, boolean):int");
    }

    @Override // okio.BufferedSource
    public long Q(byte b2) {
        return k(b2, 0L, Long.MAX_VALUE);
    }

    public final ByteString R() {
        long j2 = this.f19572h;
        if (j2 <= 2147483647L) {
            return T((int) j2);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f19572h);
    }

    public final ByteString T(int i2) {
        return i2 == 0 ? ByteString.EMPTY : new SegmentedByteString(this, i2);
    }

    @Override // okio.BufferedSource
    public boolean V() {
        return this.f19572h == 0;
    }

    Segment W(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.f19571c;
        if (segment != null) {
            Segment segment2 = segment.f19646g;
            return (segment2.f19642c + i2 > 8192 || !segment2.f19644e) ? segment2.c(SegmentPool.b()) : segment2;
        }
        Segment b2 = SegmentPool.b();
        this.f19571c = b2;
        b2.f19646g = b2;
        b2.f19645f = b2;
        return b2;
    }

    public Buffer Y(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = i3;
        Util.b(bArr.length, i2, j2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment W = W(1);
            int min = Math.min(i4 - i2, 8192 - W.f19642c);
            System.arraycopy(bArr, i2, W.f19640a, W.f19642c, min);
            i2 += min;
            W.f19642c += min;
        }
        this.f19572h += j2;
        return this;
    }

    public final void a() {
        try {
            skip(this.f19572h);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSink
    /* renamed from: a0, reason: merged with bridge method [inline-methods] */
    public Buffer writeByte(int i2) {
        Segment W = W(1);
        byte[] bArr = W.f19640a;
        int i3 = W.f19642c;
        W.f19642c = i3 + 1;
        bArr[i3] = (byte) i2;
        this.f19572h++;
        return this;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer b() {
        return this;
    }

    @Override // okio.BufferedSource
    public int b0() {
        return Util.c(E());
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.f19572h == 0) {
            return buffer;
        }
        Segment d2 = this.f19571c.d();
        buffer.f19571c = d2;
        d2.f19646g = d2;
        d2.f19645f = d2;
        Segment segment = this.f19571c;
        while (true) {
            segment = segment.f19645f;
            if (segment == this.f19571c) {
                buffer.f19572h = this.f19572h;
                return buffer;
            }
            buffer.f19571c.f19646g.c(segment.d());
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.Source
    public long d0(Buffer buffer, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        long j3 = this.f19572h;
        if (j3 == 0) {
            return -1L;
        }
        if (j2 > j3) {
            j2 = j3;
        }
        buffer.w(this, j2);
        return j2;
    }

    public final long e() {
        long j2 = this.f19572h;
        if (j2 == 0) {
            return 0L;
        }
        Segment segment = this.f19571c.f19646g;
        return (segment.f19642c >= 8192 || !segment.f19644e) ? j2 : j2 - (r2 - segment.f19641b);
    }

    public Buffer e0(int i2) {
        Segment W = W(4);
        byte[] bArr = W.f19640a;
        int i3 = W.f19642c;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        bArr[i3 + 1] = (byte) ((i2 >>> 16) & 255);
        bArr[i3 + 2] = (byte) ((i2 >>> 8) & 255);
        bArr[i3 + 3] = (byte) (i2 & 255);
        W.f19642c = i3 + 4;
        this.f19572h += 4;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        long j2 = this.f19572h;
        if (j2 != buffer.f19572h) {
            return false;
        }
        long j3 = 0;
        if (j2 == 0) {
            return true;
        }
        Segment segment = this.f19571c;
        Segment segment2 = buffer.f19571c;
        int i2 = segment.f19641b;
        int i3 = segment2.f19641b;
        while (j3 < this.f19572h) {
            long min = Math.min(segment.f19642c - i2, segment2.f19642c - i3);
            int i4 = 0;
            while (i4 < min) {
                int i5 = i2 + 1;
                int i6 = i3 + 1;
                if (segment.f19640a[i2] != segment2.f19640a[i3]) {
                    return false;
                }
                i4++;
                i2 = i5;
                i3 = i6;
            }
            if (i2 == segment.f19642c) {
                segment = segment.f19645f;
                i2 = segment.f19641b;
            }
            if (i3 == segment2.f19642c) {
                segment2 = segment2.f19645f;
                i3 = segment2.f19641b;
            }
            j3 += min;
        }
        return true;
    }

    @Override // okio.BufferedSink
    /* renamed from: f0, reason: merged with bridge method [inline-methods] */
    public Buffer U(int i2) {
        return e0(Util.c(i2));
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource
    public InputStream g0() {
        return new InputStream() { // from class: okio.Buffer.2
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.f19572h, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                Buffer buffer = Buffer.this;
                if (buffer.f19572h > 0) {
                    return buffer.readByte() & 255;
                }
                return -1;
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) {
                return Buffer.this.read(bArr, i2, i3);
            }
        };
    }

    public final Buffer h(Buffer buffer, long j2, long j3) {
        if (buffer == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.b(this.f19572h, j2, j3);
        if (j3 == 0) {
            return this;
        }
        buffer.f19572h += j3;
        Segment segment = this.f19571c;
        while (true) {
            int i2 = segment.f19642c;
            int i3 = segment.f19641b;
            if (j2 < i2 - i3) {
                break;
            }
            j2 -= i2 - i3;
            segment = segment.f19645f;
        }
        while (j3 > 0) {
            Segment d2 = segment.d();
            int i4 = (int) (d2.f19641b + j2);
            d2.f19641b = i4;
            d2.f19642c = Math.min(i4 + ((int) j3), d2.f19642c);
            Segment segment2 = buffer.f19571c;
            if (segment2 == null) {
                d2.f19646g = d2;
                d2.f19645f = d2;
                buffer.f19571c = d2;
            } else {
                segment2.f19646g.c(d2);
            }
            j3 -= d2.f19642c - d2.f19641b;
            segment = segment.f19645f;
            j2 = 0;
        }
        return this;
    }

    @Override // okio.BufferedSink
    /* renamed from: h0, reason: merged with bridge method [inline-methods] */
    public Buffer v(String str) {
        return y(str, 0, str.length());
    }

    public int hashCode() {
        Segment segment = this.f19571c;
        if (segment == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.f19642c;
            for (int i4 = segment.f19641b; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.f19640a[i4];
            }
            segment = segment.f19645f;
        } while (segment != this.f19571c);
        return i2;
    }

    @Override // okio.BufferedSink
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public Buffer r() {
        return this;
    }

    @Override // okio.BufferedSource
    public int i0(Options options) {
        int P = P(options, false);
        if (P == -1) {
            return -1;
        }
        try {
            skip(options.f19611c[P].r());
            return P;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final byte j(long j2) {
        int i2;
        Util.b(this.f19572h, j2, 1L);
        long j3 = this.f19572h;
        if (j3 - j2 <= j2) {
            long j4 = j2 - j3;
            Segment segment = this.f19571c;
            do {
                segment = segment.f19646g;
                int i3 = segment.f19642c;
                i2 = segment.f19641b;
                j4 += i3 - i2;
            } while (j4 < 0);
            return segment.f19640a[i2 + ((int) j4)];
        }
        Segment segment2 = this.f19571c;
        while (true) {
            int i4 = segment2.f19642c;
            int i5 = segment2.f19641b;
            long j5 = i4 - i5;
            if (j2 < j5) {
                return segment2.f19640a[i5 + ((int) j2)];
            }
            j2 -= j5;
            segment2 = segment2.f19645f;
        }
    }

    @Override // okio.BufferedSink
    /* renamed from: j0, reason: merged with bridge method [inline-methods] */
    public Buffer y(String str, int i2, int i3) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
        }
        if (i3 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
        }
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                Segment W = W(1);
                byte[] bArr = W.f19640a;
                int i4 = W.f19642c - i2;
                int min = Math.min(i3, 8192 - i4);
                int i5 = i2 + 1;
                bArr[i2 + i4] = (byte) charAt;
                while (i5 < min) {
                    char charAt2 = str.charAt(i5);
                    if (charAt2 >= 128) {
                        break;
                    }
                    bArr[i5 + i4] = (byte) charAt2;
                    i5++;
                }
                int i6 = W.f19642c;
                int i7 = (i4 + i5) - i6;
                W.f19642c = i6 + i7;
                this.f19572h += i7;
                i2 = i5;
            } else {
                if (charAt < 2048) {
                    writeByte((charAt >> 6) | 192);
                    writeByte((charAt & '?') | 128);
                } else if (charAt < 55296 || charAt > 57343) {
                    writeByte((charAt >> '\f') | 224);
                    writeByte(((charAt >> 6) & 63) | 128);
                    writeByte((charAt & '?') | 128);
                } else {
                    int i8 = i2 + 1;
                    char charAt3 = i8 < i3 ? str.charAt(i8) : (char) 0;
                    if (charAt > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                        writeByte(63);
                        i2 = i8;
                    } else {
                        int i9 = (((charAt & 10239) << 10) | (9215 & charAt3)) + 65536;
                        writeByte((i9 >> 18) | 240);
                        writeByte(((i9 >> 12) & 63) | 128);
                        writeByte(((i9 >> 6) & 63) | 128);
                        writeByte((i9 & 63) | 128);
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
        return this;
    }

    public long k(byte b2, long j2, long j3) {
        Segment segment;
        long j4 = 0;
        if (j2 < 0 || j3 < j2) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f19572h), Long.valueOf(j2), Long.valueOf(j3)));
        }
        long j5 = this.f19572h;
        long j6 = j3 > j5 ? j5 : j3;
        if (j2 == j6 || (segment = this.f19571c) == null) {
            return -1L;
        }
        if (j5 - j2 < j2) {
            while (j5 > j2) {
                segment = segment.f19646g;
                j5 -= segment.f19642c - segment.f19641b;
            }
        } else {
            while (true) {
                long j7 = (segment.f19642c - segment.f19641b) + j4;
                if (j7 >= j2) {
                    break;
                }
                segment = segment.f19645f;
                j4 = j7;
            }
            j5 = j4;
        }
        Segment segment2 = segment;
        long j8 = j2;
        while (j5 < j6) {
            byte[] bArr = segment2.f19640a;
            int min = (int) Math.min(segment2.f19642c, (segment2.f19641b + j6) - j5);
            for (int i2 = (int) ((segment2.f19641b + j8) - j5); i2 < min; i2++) {
                if (bArr[i2] == b2) {
                    return (i2 - segment2.f19641b) + j5;
                }
            }
            j5 += segment2.f19642c - segment2.f19641b;
            segment2 = segment2.f19645f;
            j8 = j5;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public long m(ByteString byteString) {
        return p(byteString, 0L);
    }

    @Override // okio.Sink
    public Timeout n() {
        return Timeout.f19649d;
    }

    public long p(ByteString byteString, long j2) {
        byte[] bArr;
        if (byteString.r() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j3 = 0;
        if (j2 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment = this.f19571c;
        long j4 = -1;
        if (segment == null) {
            return -1L;
        }
        long j5 = this.f19572h;
        if (j5 - j2 < j2) {
            while (j5 > j2) {
                segment = segment.f19646g;
                j5 -= segment.f19642c - segment.f19641b;
            }
        } else {
            while (true) {
                long j6 = (segment.f19642c - segment.f19641b) + j3;
                if (j6 >= j2) {
                    break;
                }
                segment = segment.f19645f;
                j3 = j6;
            }
            j5 = j3;
        }
        byte f2 = byteString.f(0);
        int r2 = byteString.r();
        long j7 = 1 + (this.f19572h - r2);
        long j8 = j2;
        Segment segment2 = segment;
        long j9 = j5;
        while (j9 < j7) {
            byte[] bArr2 = segment2.f19640a;
            int min = (int) Math.min(segment2.f19642c, (segment2.f19641b + j7) - j9);
            int i2 = (int) ((segment2.f19641b + j8) - j9);
            while (i2 < min) {
                if (bArr2[i2] == f2) {
                    bArr = bArr2;
                    if (t(segment2, i2 + 1, byteString, 1, r2)) {
                        return (i2 - segment2.f19641b) + j9;
                    }
                } else {
                    bArr = bArr2;
                }
                i2++;
                bArr2 = bArr;
            }
            j9 += segment2.f19642c - segment2.f19641b;
            segment2 = segment2.f19645f;
            j8 = j9;
            j4 = -1;
        }
        return j4;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.a(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public long q(ByteString byteString) {
        return s(byteString, 0L);
    }

    public int read(byte[] bArr, int i2, int i3) {
        Util.b(bArr.length, i2, i3);
        Segment segment = this.f19571c;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i3, segment.f19642c - segment.f19641b);
        System.arraycopy(segment.f19640a, segment.f19641b, bArr, i2, min);
        int i4 = segment.f19641b + min;
        segment.f19641b = i4;
        this.f19572h -= min;
        if (i4 == segment.f19642c) {
            this.f19571c = segment.b();
            SegmentPool.a(segment);
        }
        return min;
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        long j2 = this.f19572h;
        if (j2 == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.f19571c;
        int i2 = segment.f19641b;
        int i3 = segment.f19642c;
        int i4 = i2 + 1;
        byte b2 = segment.f19640a[i2];
        this.f19572h = j2 - 1;
        if (i4 == i3) {
            this.f19571c = segment.b();
            SegmentPool.a(segment);
        } else {
            segment.f19641b = i4;
        }
        return b2;
    }

    @Override // okio.BufferedSource
    public short readShort() {
        long j2 = this.f19572h;
        if (j2 < 2) {
            throw new IllegalStateException("size < 2: " + this.f19572h);
        }
        Segment segment = this.f19571c;
        int i2 = segment.f19641b;
        int i3 = segment.f19642c;
        if (i3 - i2 < 2) {
            return (short) ((readByte() & 255) | ((readByte() & 255) << 8));
        }
        byte[] bArr = segment.f19640a;
        int i4 = i2 + 1;
        int i5 = (bArr[i2] & 255) << 8;
        int i6 = i2 + 2;
        int i7 = (bArr[i4] & 255) | i5;
        this.f19572h = j2 - 2;
        if (i6 == i3) {
            this.f19571c = segment.b();
            SegmentPool.a(segment);
        } else {
            segment.f19641b = i6;
        }
        return (short) i7;
    }

    public long s(ByteString byteString, long j2) {
        int i2;
        int i3;
        long j3 = 0;
        if (j2 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment = this.f19571c;
        if (segment == null) {
            return -1L;
        }
        long j4 = this.f19572h;
        if (j4 - j2 < j2) {
            while (j4 > j2) {
                segment = segment.f19646g;
                j4 -= segment.f19642c - segment.f19641b;
            }
        } else {
            while (true) {
                long j5 = (segment.f19642c - segment.f19641b) + j3;
                if (j5 >= j2) {
                    break;
                }
                segment = segment.f19645f;
                j3 = j5;
            }
            j4 = j3;
        }
        if (byteString.r() == 2) {
            byte f2 = byteString.f(0);
            byte f3 = byteString.f(1);
            while (j4 < this.f19572h) {
                byte[] bArr = segment.f19640a;
                i2 = (int) ((segment.f19641b + j2) - j4);
                int i4 = segment.f19642c;
                while (i2 < i4) {
                    byte b2 = bArr[i2];
                    if (b2 == f2 || b2 == f3) {
                        i3 = segment.f19641b;
                        return (i2 - i3) + j4;
                    }
                    i2++;
                }
                j4 += segment.f19642c - segment.f19641b;
                segment = segment.f19645f;
                j2 = j4;
            }
            return -1L;
        }
        byte[] j6 = byteString.j();
        while (j4 < this.f19572h) {
            byte[] bArr2 = segment.f19640a;
            i2 = (int) ((segment.f19641b + j2) - j4);
            int i5 = segment.f19642c;
            while (i2 < i5) {
                byte b3 = bArr2[i2];
                for (byte b4 : j6) {
                    if (b3 == b4) {
                        i3 = segment.f19641b;
                        return (i2 - i3) + j4;
                    }
                }
                i2++;
            }
            j4 += segment.f19642c - segment.f19641b;
            segment = segment.f19645f;
            j2 = j4;
        }
        return -1L;
    }

    public final long size() {
        return this.f19572h;
    }

    @Override // okio.BufferedSource
    public void skip(long j2) {
        while (j2 > 0) {
            if (this.f19571c == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j2, r0.f19642c - r0.f19641b);
            long j3 = min;
            this.f19572h -= j3;
            j2 -= j3;
            Segment segment = this.f19571c;
            int i2 = segment.f19641b + min;
            segment.f19641b = i2;
            if (i2 == segment.f19642c) {
                this.f19571c = segment.b();
                SegmentPool.a(segment);
            }
        }
    }

    public String toString() {
        return R().toString();
    }

    @Override // okio.Sink
    public void w(Buffer buffer, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer == this) {
            throw new IllegalArgumentException("source == this");
        }
        Util.b(buffer.f19572h, 0L, j2);
        while (j2 > 0) {
            Segment segment = buffer.f19571c;
            if (j2 < segment.f19642c - segment.f19641b) {
                Segment segment2 = this.f19571c;
                Segment segment3 = segment2 != null ? segment2.f19646g : null;
                if (segment3 != null && segment3.f19644e) {
                    if ((segment3.f19642c + j2) - (segment3.f19643d ? 0 : segment3.f19641b) <= 8192) {
                        segment.f(segment3, (int) j2);
                        buffer.f19572h -= j2;
                        this.f19572h += j2;
                        return;
                    }
                }
                buffer.f19571c = segment.e((int) j2);
            }
            Segment segment4 = buffer.f19571c;
            long j3 = segment4.f19642c - segment4.f19641b;
            buffer.f19571c = segment4.b();
            Segment segment5 = this.f19571c;
            if (segment5 == null) {
                this.f19571c = segment4;
                segment4.f19646g = segment4;
                segment4.f19645f = segment4;
            } else {
                segment5.f19646g.c(segment4).a();
            }
            buffer.f19572h -= j3;
            this.f19572h += j3;
            j2 -= j3;
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int remaining = byteBuffer.remaining();
        int i2 = remaining;
        while (i2 > 0) {
            Segment W = W(1);
            int min = Math.min(i2, 8192 - W.f19642c);
            byteBuffer.get(W.f19640a, W.f19642c, min);
            i2 -= min;
            W.f19642c += min;
        }
        this.f19572h += remaining;
        return remaining;
    }

    @Override // okio.BufferedSource
    public boolean z(long j2) {
        return this.f19572h >= j2;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        Segment segment = this.f19571c;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.f19642c - segment.f19641b);
        byteBuffer.put(segment.f19640a, segment.f19641b, min);
        int i2 = segment.f19641b + min;
        segment.f19641b = i2;
        this.f19572h -= min;
        if (i2 == segment.f19642c) {
            this.f19571c = segment.b();
            SegmentPool.a(segment);
        }
        return min;
    }
}
