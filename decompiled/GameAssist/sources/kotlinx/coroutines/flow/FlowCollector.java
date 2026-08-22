package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
public interface FlowCollector<T> {
    Object k(Object obj, Continuation continuation);
}
