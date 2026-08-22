package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
public interface Flow<T> {
    Object a(FlowCollector flowCollector, Continuation continuation);
}
