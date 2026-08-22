package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public interface MutableStateFlow<T> extends StateFlow<T>, MutableSharedFlow<T> {
    void setValue(Object obj);
}
