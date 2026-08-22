package okio;

/* loaded from: classes2.dex */
final class SegmentPool {

    /* renamed from: a, reason: collision with root package name */
    static Segment f19647a;

    /* renamed from: b, reason: collision with root package name */
    static long f19648b;

    static void a(Segment segment) {
        if (segment.f19645f != null || segment.f19646g != null) {
            throw new IllegalArgumentException();
        }
        if (segment.f19643d) {
            return;
        }
        synchronized (SegmentPool.class) {
            try {
                long j2 = f19648b;
                if (j2 + 8192 > 65536) {
                    return;
                }
                f19648b = j2 + 8192;
                segment.f19645f = f19647a;
                segment.f19642c = 0;
                segment.f19641b = 0;
                f19647a = segment;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static Segment b() {
        synchronized (SegmentPool.class) {
            try {
                Segment segment = f19647a;
                if (segment == null) {
                    return new Segment();
                }
                f19647a = segment.f19645f;
                segment.f19645f = null;
                f19648b -= 8192;
                return segment;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
