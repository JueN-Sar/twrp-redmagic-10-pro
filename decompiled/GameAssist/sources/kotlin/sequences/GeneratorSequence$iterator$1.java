package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class GeneratorSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private Object f18700c;

    /* renamed from: h, reason: collision with root package name */
    private int f18701h = -2;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ GeneratorSequence f18702i;

    GeneratorSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.f18702i = generatorSequence;
    }

    private final void b() {
        Function1 function1;
        Object c2;
        Function0 function0;
        if (this.f18701h == -2) {
            function0 = this.f18702i.f18698a;
            c2 = function0.a();
        } else {
            function1 = this.f18702i.f18699b;
            Object obj = this.f18700c;
            Intrinsics.b(obj);
            c2 = function1.c(obj);
        }
        this.f18700c = c2;
        this.f18701h = c2 == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.f18701h < 0) {
            b();
        }
        return this.f18701h == 1;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.f18701h < 0) {
            b();
        }
        if (this.f18701h == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.f18700c;
        Intrinsics.c(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
        this.f18701h = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
