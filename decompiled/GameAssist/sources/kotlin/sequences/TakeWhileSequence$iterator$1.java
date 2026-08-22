package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class TakeWhileSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18743c;

    /* renamed from: h, reason: collision with root package name */
    private int f18744h;

    /* renamed from: i, reason: collision with root package name */
    private Object f18745i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ TakeWhileSequence f18746j;

    TakeWhileSequence$iterator$1(TakeWhileSequence takeWhileSequence) {
        Sequence sequence;
        this.f18746j = takeWhileSequence;
        sequence = takeWhileSequence.f18741a;
        this.f18743c = sequence.iterator();
        this.f18744h = -1;
    }

    private final void b() {
        Function1 function1;
        if (this.f18743c.hasNext()) {
            Object next = this.f18743c.next();
            function1 = this.f18746j.f18742b;
            if (((Boolean) function1.c(next)).booleanValue()) {
                this.f18744h = 1;
                this.f18745i = next;
                return;
            }
        }
        this.f18744h = 0;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.f18744h == -1) {
            b();
        }
        return this.f18744h == 1;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.f18744h == -1) {
            b();
        }
        if (this.f18744h == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.f18745i;
        this.f18745i = null;
        this.f18744h = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
