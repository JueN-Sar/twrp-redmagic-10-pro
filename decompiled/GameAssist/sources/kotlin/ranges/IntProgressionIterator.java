package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes2.dex */
public final class IntProgressionIterator extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private final int f18613c;

    /* renamed from: h, reason: collision with root package name */
    private final int f18614h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18615i;

    /* renamed from: j, reason: collision with root package name */
    private int f18616j;

    public IntProgressionIterator(int i2, int i3, int i4) {
        this.f18613c = i4;
        this.f18614h = i3;
        boolean z = false;
        if (i4 <= 0 ? i2 >= i3 : i2 <= i3) {
            z = true;
        }
        this.f18615i = z;
        this.f18616j = z ? i2 : i3;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18615i;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        int i2 = this.f18616j;
        if (i2 != this.f18614h) {
            this.f18616j = this.f18613c + i2;
        } else {
            if (!this.f18615i) {
                throw new NoSuchElementException();
            }
            this.f18615i = false;
        }
        return i2;
    }
}
