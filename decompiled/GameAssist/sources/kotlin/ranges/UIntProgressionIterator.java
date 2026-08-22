package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
final class UIntProgressionIterator implements Iterator<UInt>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final int f18637c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f18638h;

    /* renamed from: i, reason: collision with root package name */
    private final int f18639i;

    /* renamed from: j, reason: collision with root package name */
    private int f18640j;

    public /* synthetic */ UIntProgressionIterator(int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, i4);
    }

    public int b() {
        int i2 = this.f18640j;
        if (i2 != this.f18637c) {
            this.f18640j = UInt.d(this.f18639i + i2);
        } else {
            if (!this.f18638h) {
                throw new NoSuchElementException();
            }
            this.f18638h = false;
        }
        return i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18638h;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ UInt next() {
        return UInt.c(b());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    private UIntProgressionIterator(int i2, int i3, int i4) {
        this.f18637c = i3;
        boolean z = false;
        int compareUnsigned = Integer.compareUnsigned(i2, i3);
        if (i4 <= 0 ? compareUnsigned >= 0 : compareUnsigned <= 0) {
            z = true;
        }
        this.f18638h = z;
        this.f18639i = UInt.d(i4);
        this.f18640j = this.f18638h ? i2 : i3;
    }
}
