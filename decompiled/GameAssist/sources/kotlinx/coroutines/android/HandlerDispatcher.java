package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata
/* loaded from: classes2.dex */
public abstract class HandlerDispatcher extends MainCoroutineDispatcher implements Delay {
    public /* synthetic */ HandlerDispatcher(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext) {
        return Delay.DefaultImpls.a(this, j2, runnable, coroutineContext);
    }

    private HandlerDispatcher() {
    }
}
