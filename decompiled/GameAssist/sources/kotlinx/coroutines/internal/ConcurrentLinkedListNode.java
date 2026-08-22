package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {

    /* renamed from: a, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19346a = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");

    /* renamed from: b, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19347b = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");

    @NotNull
    private volatile /* synthetic */ Object _next = null;

    @NotNull
    private volatile /* synthetic */ Object _prev;

    public ConcurrentLinkedListNode(ConcurrentLinkedListNode concurrentLinkedListNode) {
        this._prev = concurrentLinkedListNode;
    }

    private final ConcurrentLinkedListNode c() {
        ConcurrentLinkedListNode f2 = f();
        while (f2 != null && f2.g()) {
            f2 = (ConcurrentLinkedListNode) f2._prev;
        }
        return f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object e() {
        return this._next;
    }

    private final ConcurrentLinkedListNode h() {
        ConcurrentLinkedListNode d2 = d();
        Intrinsics.b(d2);
        while (d2.g()) {
            d2 = d2.d();
            Intrinsics.b(d2);
        }
        return d2;
    }

    public final void b() {
        f19347b.lazySet(this, null);
    }

    public final ConcurrentLinkedListNode d() {
        Symbol symbol;
        Object e2 = e();
        symbol = ConcurrentLinkedListKt.f19345a;
        if (e2 == symbol) {
            return null;
        }
        return (ConcurrentLinkedListNode) e2;
    }

    public final ConcurrentLinkedListNode f() {
        return (ConcurrentLinkedListNode) this._prev;
    }

    public abstract boolean g();

    public final boolean i() {
        return d() == null;
    }

    public final void j() {
        while (true) {
            ConcurrentLinkedListNode c2 = c();
            ConcurrentLinkedListNode h2 = h();
            h2._prev = c2;
            if (c2 != null) {
                c2._next = h2;
            }
            if (!h2.g() && (c2 == null || !c2.g())) {
                return;
            }
        }
    }

    public final boolean k(ConcurrentLinkedListNode concurrentLinkedListNode) {
        return f19346a.compareAndSet(this, null, concurrentLinkedListNode);
    }
}
