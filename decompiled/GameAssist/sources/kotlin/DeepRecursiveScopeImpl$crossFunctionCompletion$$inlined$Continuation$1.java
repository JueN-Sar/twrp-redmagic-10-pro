package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1 implements Continuation<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ CoroutineContext f18255c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ DeepRecursiveScopeImpl f18256h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function3 f18257i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ Continuation f18258j;

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        this.f18256h.f18252c = this.f18257i;
        this.f18256h.f18253h = this.f18258j;
        this.f18256h.f18254i = obj;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f18255c;
    }
}
