package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlinx.coroutines.InternalCoroutinesApi;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface ThreadSafeHeapNode {
    void c(ThreadSafeHeap threadSafeHeap);

    ThreadSafeHeap d();

    int getIndex();

    void setIndex(int i2);
}
