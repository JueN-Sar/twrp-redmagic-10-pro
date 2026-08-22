package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ContinuationKt$Continuation$1 implements Continuation<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ CoroutineContext f18411c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function1 f18412h;

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        this.f18412h.c(Result.a(obj));
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f18411c;
    }
}
