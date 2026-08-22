package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata
/* loaded from: classes2.dex */
public final class LongProgressionIterator extends LongIterator {

    /* renamed from: c, reason: collision with root package name */
    private final long f18623c;

    /* renamed from: h, reason: collision with root package name */
    private final long f18624h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18625i;

    /* renamed from: j, reason: collision with root package name */
    private long f18626j;

    public LongProgressionIterator(long j2, long j3, long j4) {
        this.f18623c = j4;
        this.f18624h = j3;
        boolean z = false;
        if (j4 <= 0 ? j2 >= j3 : j2 <= j3) {
            z = true;
        }
        this.f18625i = z;
        this.f18626j = z ? j2 : j3;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18625i;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        long j2 = this.f18626j;
        if (j2 != this.f18624h) {
            this.f18626j = this.f18623c + j2;
        } else {
            if (!this.f18625i) {
                throw new NoSuchElementException();
            }
            this.f18625i = false;
        }
        return j2;
    }
}
