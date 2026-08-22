package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
final class ULongProgressionIterator implements Iterator<ULong>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final long f18647c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f18648h;

    /* renamed from: i, reason: collision with root package name */
    private final long f18649i;

    /* renamed from: j, reason: collision with root package name */
    private long f18650j;

    public /* synthetic */ ULongProgressionIterator(long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3, j4);
    }

    public long b() {
        long j2 = this.f18650j;
        if (j2 != this.f18647c) {
            this.f18650j = ULong.d(this.f18649i + j2);
        } else {
            if (!this.f18648h) {
                throw new NoSuchElementException();
            }
            this.f18648h = false;
        }
        return j2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18648h;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ ULong next() {
        return ULong.c(b());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    private ULongProgressionIterator(long j2, long j3, long j4) {
        this.f18647c = j3;
        boolean z = false;
        if (j4 <= 0 ? Long.compareUnsigned(j2, j3) >= 0 : Long.compareUnsigned(j2, j3) <= 0) {
            z = true;
        }
        this.f18648h = z;
        this.f18649i = ULong.d(j4);
        this.f18650j = this.f18648h ? j2 : j3;
    }
}
