package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata
/* loaded from: classes2.dex */
public final class UndispatchedCoroutine<T> extends ScopeCoroutine<T> {

    /* renamed from: j, reason: collision with root package name */
    private ThreadLocal f18940j;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public UndispatchedCoroutine(kotlin.coroutines.CoroutineContext r3, kotlin.coroutines.Continuation r4) {
        /*
            r2 = this;
            kotlinx.coroutines.UndispatchedMarker r0 = kotlinx.coroutines.UndispatchedMarker.f18941c
            kotlin.coroutines.CoroutineContext$Element r1 = r3.c(r0)
            if (r1 != 0) goto Ld
            kotlin.coroutines.CoroutineContext r0 = r3.R(r0)
            goto Le
        Ld:
            r0 = r3
        Le:
            r2.<init>(r0, r4)
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            r2.f18940j = r0
            kotlin.coroutines.CoroutineContext r4 = r4.getContext()
            kotlin.coroutines.ContinuationInterceptor$Key r0 = kotlin.coroutines.ContinuationInterceptor.f18409d
            kotlin.coroutines.CoroutineContext$Element r4 = r4.c(r0)
            boolean r4 = r4 instanceof kotlinx.coroutines.CoroutineDispatcher
            if (r4 != 0) goto L31
            r4 = 0
            java.lang.Object r4 = kotlinx.coroutines.internal.ThreadContextKt.c(r3, r4)
            kotlinx.coroutines.internal.ThreadContextKt.a(r3, r4)
            r2.i1(r3, r4)
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.UndispatchedCoroutine.<init>(kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):void");
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void c1(Object obj) {
        Pair pair = (Pair) this.f18940j.get();
        if (pair != null) {
            ThreadContextKt.a((CoroutineContext) pair.a(), pair.b());
            this.f18940j.set(null);
        }
        Object a2 = CompletionStateKt.a(obj, this.f19399i);
        Continuation continuation = this.f19399i;
        CoroutineContext context = continuation.getContext();
        Object c2 = ThreadContextKt.c(context, null);
        UndispatchedCoroutine g2 = c2 != ThreadContextKt.f19407a ? CoroutineContextKt.g(continuation, context, c2) : null;
        try {
            this.f19399i.g(a2);
            Unit unit = Unit.f18288a;
        } finally {
            if (g2 == null || g2.h1()) {
                ThreadContextKt.a(context, c2);
            }
        }
    }

    public final boolean h1() {
        if (this.f18940j.get() == null) {
            return false;
        }
        this.f18940j.set(null);
        return true;
    }

    public final void i1(CoroutineContext coroutineContext, Object obj) {
        this.f18940j.set(TuplesKt.a(coroutineContext, obj));
    }
}
