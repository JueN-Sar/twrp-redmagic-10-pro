package okio;

/* loaded from: classes2.dex */
final class Segment {

    /* renamed from: a, reason: collision with root package name */
    final byte[] f19640a;

    /* renamed from: b, reason: collision with root package name */
    int f19641b;

    /* renamed from: c, reason: collision with root package name */
    int f19642c;

    /* renamed from: d, reason: collision with root package name */
    boolean f19643d;

    /* renamed from: e, reason: collision with root package name */
    boolean f19644e;

    /* renamed from: f, reason: collision with root package name */
    Segment f19645f;

    /* renamed from: g, reason: collision with root package name */
    Segment f19646g;

    Segment() {
        this.f19640a = new byte[8192];
        this.f19644e = true;
        this.f19643d = false;
    }

    public final void a() {
        Segment segment = this.f19646g;
        if (segment == this) {
            throw new IllegalStateException();
        }
        if (segment.f19644e) {
            int i2 = this.f19642c - this.f19641b;
            if (i2 > (8192 - segment.f19642c) + (segment.f19643d ? 0 : segment.f19641b)) {
                return;
            }
            f(segment, i2);
            b();
            SegmentPool.a(this);
        }
    }

    public final Segment b() {
        Segment segment = this.f19645f;
        Segment segment2 = segment != this ? segment : null;
        Segment segment3 = this.f19646g;
        segment3.f19645f = segment;
        this.f19645f.f19646g = segment3;
        this.f19645f = null;
        this.f19646g = null;
        return segment2;
    }

    public final Segment c(Segment segment) {
        segment.f19646g = this;
        segment.f19645f = this.f19645f;
        this.f19645f.f19646g = segment;
        this.f19645f = segment;
        return segment;
    }

    final Segment d() {
        this.f19643d = true;
        return new Segment(this.f19640a, this.f19641b, this.f19642c, true, false);
    }

    public final Segment e(int i2) {
        Segment b2;
        if (i2 <= 0 || i2 > this.f19642c - this.f19641b) {
            throw new IllegalArgumentException();
        }
        if (i2 >= 1024) {
            b2 = d();
        } else {
            b2 = SegmentPool.b();
            System.arraycopy(this.f19640a, this.f19641b, b2.f19640a, 0, i2);
        }
        b2.f19642c = b2.f19641b + i2;
        this.f19641b += i2;
        this.f19646g.c(b2);
        return b2;
    }

    public final void f(Segment segment, int i2) {
        if (!segment.f19644e) {
            throw new IllegalArgumentException();
        }
        int i3 = segment.f19642c;
        if (i3 + i2 > 8192) {
            if (segment.f19643d) {
                throw new IllegalArgumentException();
            }
            int i4 = segment.f19641b;
            if ((i3 + i2) - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = segment.f19640a;
            System.arraycopy(bArr, i4, bArr, 0, i3 - i4);
            segment.f19642c -= segment.f19641b;
            segment.f19641b = 0;
        }
        System.arraycopy(this.f19640a, this.f19641b, segment.f19640a, segment.f19642c, i2);
        segment.f19642c += i2;
        this.f19641b += i2;
    }

    Segment(byte[] bArr, int i2, int i3, boolean z, boolean z2) {
        this.f19640a = bArr;
        this.f19641b = i2;
        this.f19642c = i3;
        this.f19643d = z;
        this.f19644e = z2;
    }
}
