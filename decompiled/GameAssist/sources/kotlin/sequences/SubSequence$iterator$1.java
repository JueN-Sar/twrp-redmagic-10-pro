package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class SubSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18734c;

    /* renamed from: h, reason: collision with root package name */
    private int f18735h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ SubSequence f18736i;

    SubSequence$iterator$1(SubSequence subSequence) {
        Sequence sequence;
        this.f18736i = subSequence;
        sequence = subSequence.f18731a;
        this.f18734c = sequence.iterator();
    }

    /* JADX WARN: Incorrect condition in loop: B:2:0x0008 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void b() {
        /*
            r2 = this;
        L0:
            int r0 = r2.f18735h
            kotlin.sequences.SubSequence r1 = r2.f18736i
            int r1 = kotlin.sequences.SubSequence.c(r1)
            if (r0 >= r1) goto L1e
            java.util.Iterator r0 = r2.f18734c
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L1e
            java.util.Iterator r0 = r2.f18734c
            r0.next()
            int r0 = r2.f18735h
            int r0 = r0 + 1
            r2.f18735h = r0
            goto L0
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SubSequence$iterator$1.b():void");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i2;
        b();
        int i3 = this.f18735h;
        i2 = this.f18736i.f18733c;
        return i3 < i2 && this.f18734c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        int i2;
        b();
        int i3 = this.f18735h;
        i2 = this.f18736i.f18733c;
        if (i3 >= i2) {
            throw new NoSuchElementException();
        }
        this.f18735h++;
        return this.f18734c.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
