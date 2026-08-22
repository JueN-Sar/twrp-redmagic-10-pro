package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public interface Continuation<T> {
    void g(Object obj);

    CoroutineContext getContext();
}
