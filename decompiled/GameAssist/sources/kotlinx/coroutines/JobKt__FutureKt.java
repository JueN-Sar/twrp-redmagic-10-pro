package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class JobKt__FutureKt {
    public static final void a(CancellableContinuation cancellableContinuation, Future future) {
        cancellableContinuation.m(new CancelFutureOnCancel(future));
    }
}
