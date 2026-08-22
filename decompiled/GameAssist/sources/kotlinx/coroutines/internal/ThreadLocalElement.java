package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

@Metadata
/* loaded from: classes2.dex */
public final class ThreadLocalElement<T> implements ThreadContextElement<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Object f19411c;

    /* renamed from: h, reason: collision with root package name */
    private final ThreadLocal f19412h;

    /* renamed from: i, reason: collision with root package name */
    private final CoroutineContext.Key f19413i;

    @Override // kotlinx.coroutines.ThreadContextElement
    public void E(CoroutineContext coroutineContext, Object obj) {
        this.f19412h.set(obj);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext R(CoroutineContext coroutineContext) {
        return ThreadContextElement.DefaultImpls.b(this, coroutineContext);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        return Intrinsics.a(getKey(), key) ? EmptyCoroutineContext.INSTANCE : this;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        if (Intrinsics.a(getKey(), key)) {
            return this;
        }
        return null;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public Object e0(Object obj, Function2 function2) {
        return ThreadContextElement.DefaultImpls.a(this, obj, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.ThreadContextElement
    public Object f0(CoroutineContext coroutineContext) {
        Object obj = this.f19412h.get();
        this.f19412h.set(this.f19411c);
        return obj;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key getKey() {
        return this.f19413i;
    }

    public String toString() {
        return "ThreadLocal(value=" + this.f19411c + ", threadLocal = " + this.f19412h + ')';
    }
}
