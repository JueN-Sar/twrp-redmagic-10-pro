package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class BuildersKt {
    public static final Job a(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2) {
        return BuildersKt__Builders_commonKt.a(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final Object c(CoroutineContext coroutineContext, Function2 function2, Continuation continuation) {
        return BuildersKt__Builders_commonKt.c(coroutineContext, function2, continuation);
    }
}
