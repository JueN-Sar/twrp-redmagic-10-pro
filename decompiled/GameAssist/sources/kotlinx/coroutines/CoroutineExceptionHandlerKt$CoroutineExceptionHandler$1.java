package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f18852h;

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void P(CoroutineContext coroutineContext, Throwable th) {
        this.f18852h.y(coroutineContext, th);
    }
}
