package kotlinx.coroutines.debug.internal;

import java.io.Serializable;
import java.lang.Thread;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class DebuggerInfo implements Serializable {

    @Nullable
    private final Long coroutineId;

    @Nullable
    private final String dispatcher;

    @NotNull
    private final List<StackTraceElement> lastObservedStackTrace;

    @Nullable
    private final String lastObservedThreadName;

    @Nullable
    private final String lastObservedThreadState;

    @Nullable
    private final String name;
    private final long sequenceNumber;

    @NotNull
    private final String state;

    public DebuggerInfo(@NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @NotNull CoroutineContext coroutineContext) {
        Thread.State state;
        CoroutineId coroutineId = (CoroutineId) coroutineContext.c(CoroutineId.f18853i);
        this.coroutineId = coroutineId != null ? Long.valueOf(coroutineId.j0()) : null;
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.c(ContinuationInterceptor.f18409d);
        this.dispatcher = continuationInterceptor != null ? continuationInterceptor.toString() : null;
        CoroutineName coroutineName = (CoroutineName) coroutineContext.c(CoroutineName.f18855i);
        this.name = coroutineName != null ? coroutineName.j0() : null;
        this.state = debugCoroutineInfoImpl.g();
        Thread thread = debugCoroutineInfoImpl.f19068e;
        this.lastObservedThreadState = (thread == null || (state = thread.getState()) == null) ? null : state.toString();
        Thread thread2 = debugCoroutineInfoImpl.f19068e;
        this.lastObservedThreadName = thread2 != null ? thread2.getName() : null;
        this.lastObservedStackTrace = debugCoroutineInfoImpl.h();
        this.sequenceNumber = debugCoroutineInfoImpl.f19065b;
    }
}
