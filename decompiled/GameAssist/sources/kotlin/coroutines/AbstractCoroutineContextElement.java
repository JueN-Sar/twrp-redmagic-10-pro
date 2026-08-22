package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractCoroutineContextElement implements CoroutineContext.Element {

    /* renamed from: c, reason: collision with root package name */
    private final CoroutineContext.Key f18406c;

    public AbstractCoroutineContextElement(CoroutineContext.Key key) {
        Intrinsics.e(key, "key");
        this.f18406c = key;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext R(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.d(this, coroutineContext);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        return CoroutineContext.Element.DefaultImpls.c(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        return CoroutineContext.Element.DefaultImpls.b(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public Object e0(Object obj, Function2 function2) {
        return CoroutineContext.Element.DefaultImpls.a(this, obj, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key getKey() {
        return this.f18406c;
    }
}
