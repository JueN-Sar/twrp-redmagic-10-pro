package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
final class SequenceBuilderIterator<T> extends SequenceScope<T> implements Iterator<T>, Continuation<Unit>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f18712c;

    /* renamed from: h, reason: collision with root package name */
    private Object f18713h;

    /* renamed from: i, reason: collision with root package name */
    private Iterator f18714i;

    /* renamed from: j, reason: collision with root package name */
    private Continuation f18715j;

    private final Throwable f() {
        int i2 = this.f18712c;
        if (i2 == 4) {
            return new NoSuchElementException();
        }
        if (i2 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.f18712c);
    }

    private final Object h() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    @Override // kotlin.sequences.SequenceScope
    public Object b(Object obj, Continuation continuation) {
        Object d2;
        Object d3;
        Object d4;
        this.f18713h = obj;
        this.f18712c = 3;
        this.f18715j = continuation;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        if (d2 == d3) {
            DebugProbesKt.c(continuation);
        }
        d4 = IntrinsicsKt__IntrinsicsKt.d();
        return d2 == d4 ? d2 : Unit.f18288a;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object c(Iterator it, Continuation continuation) {
        Object d2;
        Object d3;
        Object d4;
        if (!it.hasNext()) {
            return Unit.f18288a;
        }
        this.f18714i = it;
        this.f18712c = 2;
        this.f18715j = continuation;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        if (d2 == d3) {
            DebugProbesKt.c(continuation);
        }
        d4 = IntrinsicsKt__IntrinsicsKt.d();
        return d2 == d4 ? d2 : Unit.f18288a;
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        ResultKt.b(obj);
        this.f18712c = 4;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            int i2 = this.f18712c;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2 || i2 == 3) {
                        return true;
                    }
                    if (i2 == 4) {
                        return false;
                    }
                    throw f();
                }
                Iterator it = this.f18714i;
                Intrinsics.b(it);
                if (it.hasNext()) {
                    this.f18712c = 2;
                    return true;
                }
                this.f18714i = null;
            }
            this.f18712c = 5;
            Continuation continuation = this.f18715j;
            Intrinsics.b(continuation);
            this.f18715j = null;
            Result.Companion companion = Result.Companion;
            continuation.g(Result.b(Unit.f18288a));
        }
    }

    public final void i(Continuation continuation) {
        this.f18715j = continuation;
    }

    @Override // java.util.Iterator
    public Object next() {
        int i2 = this.f18712c;
        if (i2 == 0 || i2 == 1) {
            return h();
        }
        if (i2 == 2) {
            this.f18712c = 1;
            Iterator it = this.f18714i;
            Intrinsics.b(it);
            return it.next();
        }
        if (i2 != 3) {
            throw f();
        }
        this.f18712c = 0;
        Object obj = this.f18713h;
        this.f18713h = null;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
