package okio;

import java.util.Arrays;

/* loaded from: classes2.dex */
final class SegmentedByteString extends ByteString {
    final transient int[] directory;
    final transient byte[][] segments;

    SegmentedByteString(Buffer buffer, int i2) {
        super(null);
        Util.b(buffer.f19572h, 0L, i2);
        Segment segment = buffer.f19571c;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = segment.f19642c;
            int i7 = segment.f19641b;
            if (i6 == i7) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += i6 - i7;
            i5++;
            segment = segment.f19645f;
        }
        this.segments = new byte[i5][];
        this.directory = new int[i5 * 2];
        Segment segment2 = buffer.f19571c;
        int i8 = 0;
        while (i3 < i2) {
            byte[][] bArr = this.segments;
            bArr[i8] = segment2.f19640a;
            int i9 = segment2.f19642c;
            int i10 = segment2.f19641b;
            i3 += i9 - i10;
            if (i3 > i2) {
                i3 = i2;
            }
            int[] iArr = this.directory;
            iArr[i8] = i3;
            iArr[bArr.length + i8] = i10;
            segment2.f19643d = true;
            i8++;
            segment2 = segment2.f19645f;
        }
    }

    private int v(int i2) {
        int binarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i2 + 1);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    private Object writeReplace() {
        return x();
    }

    private ByteString x() {
        return new ByteString(w());
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.r() == r() && n(0, byteString, 0, r())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public byte f(int i2) {
        Util.b(this.directory[this.segments.length - 1], i2, 1L);
        int v = v(i2);
        int i3 = v == 0 ? 0 : this.directory[v - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[v][(i2 - i3) + iArr[bArr.length + v]];
    }

    @Override // okio.ByteString
    public String h() {
        return x().h();
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 != 0) {
            return i2;
        }
        int length = this.segments.length;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i3 < length) {
            byte[] bArr = this.segments[i3];
            int[] iArr = this.directory;
            int i6 = iArr[length + i3];
            int i7 = iArr[i3];
            int i8 = (i7 - i5) + i6;
            while (i6 < i8) {
                i4 = (i4 * 31) + bArr[i6];
                i6++;
            }
            i3++;
            i5 = i7;
        }
        this.hashCode = i4;
        return i4;
    }

    @Override // okio.ByteString
    byte[] j() {
        return w();
    }

    @Override // okio.ByteString
    public boolean n(int i2, ByteString byteString, int i3, int i4) {
        if (i2 < 0 || i2 > r() - i4) {
            return false;
        }
        int v = v(i2);
        while (i4 > 0) {
            int i5 = v == 0 ? 0 : this.directory[v - 1];
            int min = Math.min(i4, ((this.directory[v] - i5) + i5) - i2);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!byteString.o(i3, bArr[v], (i2 - i5) + iArr[bArr.length + v], min)) {
                return false;
            }
            i2 += min;
            i3 += min;
            i4 -= min;
            v++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean o(int i2, byte[] bArr, int i3, int i4) {
        if (i2 < 0 || i2 > r() - i4 || i3 < 0 || i3 > bArr.length - i4) {
            return false;
        }
        int v = v(i2);
        while (i4 > 0) {
            int i5 = v == 0 ? 0 : this.directory[v - 1];
            int min = Math.min(i4, ((this.directory[v] - i5) + i5) - i2);
            int[] iArr = this.directory;
            byte[][] bArr2 = this.segments;
            if (!Util.a(bArr2[v], (i2 - i5) + iArr[bArr2.length + v], bArr, i3, min)) {
                return false;
            }
            i2 += min;
            i3 += min;
            i4 -= min;
            v++;
        }
        return true;
    }

    @Override // okio.ByteString
    public int r() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public ByteString t(int i2, int i3) {
        return x().t(i2, i3);
    }

    @Override // okio.ByteString
    public String toString() {
        return x().toString();
    }

    @Override // okio.ByteString
    public String u() {
        return x().u();
    }

    public byte[] w() {
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr2 = this.directory;
            int i4 = iArr2[length + i2];
            int i5 = iArr2[i2];
            System.arraycopy(this.segments[i2], i4, bArr2, i3, i5 - i3);
            i2++;
            i3 = i5;
        }
        return bArr2;
    }
}
