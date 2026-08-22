package androidx.core.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
public final class TreeIterator<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Function1 f3364c;

    /* renamed from: h, reason: collision with root package name */
    private final List f3365h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private Iterator f3366i;

    public TreeIterator(Iterator it, Function1 function1) {
        this.f3364c = function1;
        this.f3366i = it;
    }

    private final void b(Object obj) {
        Object F;
        Iterator it = (Iterator) this.f3364c.c(obj);
        if (it != null && it.hasNext()) {
            this.f3365h.add(this.f3366i);
            this.f3366i = it;
            return;
        }
        while (!this.f3366i.hasNext() && (!this.f3365h.isEmpty())) {
            F = CollectionsKt___CollectionsKt.F(this.f3365h);
            this.f3366i = (Iterator) F;
            CollectionsKt__MutableCollectionsKt.r(this.f3365h);
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3366i.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        Object next = this.f3366i.next();
        b(next);
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
