package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public interface MutableSharedFlow<T> extends SharedFlow<T>, FlowCollector<T> {
    boolean e(Object obj);

    StateFlow h();

    void i();
}
