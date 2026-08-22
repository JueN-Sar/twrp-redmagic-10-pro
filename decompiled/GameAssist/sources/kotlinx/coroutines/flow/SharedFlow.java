package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
public interface SharedFlow<T> extends Flow<T> {
    @Override // kotlinx.coroutines.flow.Flow
    Object a(FlowCollector flowCollector, Continuation continuation);
}
