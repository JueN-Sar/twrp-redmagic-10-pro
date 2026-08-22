package kotlin.coroutines;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
@ExperimentalStdlibApi
/* loaded from: classes2.dex */
public abstract class AbstractCoroutineContextKey<B extends CoroutineContext.Element, E extends B> implements CoroutineContext.Key<E> {

    /* renamed from: c, reason: collision with root package name */
    private final Function1 f18407c;

    /* renamed from: h, reason: collision with root package name */
    private final CoroutineContext.Key f18408h;

    public AbstractCoroutineContextKey(CoroutineContext.Key baseKey, Function1 safeCast) {
        Intrinsics.e(baseKey, "baseKey");
        Intrinsics.e(safeCast, "safeCast");
        this.f18407c = safeCast;
        this.f18408h = baseKey instanceof AbstractCoroutineContextKey ? ((AbstractCoroutineContextKey) baseKey).f18408h : baseKey;
    }

    public final boolean a(CoroutineContext.Key key) {
        Intrinsics.e(key, "key");
        return key == this || this.f18408h == key;
    }

    public final CoroutineContext.Element b(CoroutineContext.Element element) {
        Intrinsics.e(element, "element");
        return (CoroutineContext.Element) this.f18407c.c(element);
    }
}
