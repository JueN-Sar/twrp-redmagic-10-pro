package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.annotation.VisibleForTesting;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class HandlerDispatcherKt {

    /* renamed from: a, reason: collision with root package name */
    public static final HandlerDispatcher f18950a;

    @Nullable
    private static volatile Choreographer choreographer;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Object b2;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b(new HandlerContext(asHandler(Looper.getMainLooper(), true), objArr2 == true ? 1 : 0, 2, objArr == true ? 1 : 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        f18950a = (HandlerDispatcher) (Result.f(b2) ? null : b2);
    }

    @VisibleForTesting
    @NotNull
    public static final Handler asHandler(@NotNull Looper looper, boolean z) {
        if (!z) {
            return new Handler(looper);
        }
        Object invoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        if (invoke != null) {
            return (Handler) invoke;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.os.Handler");
    }

    private static final void c(Choreographer choreographer2, final CancellableContinuation cancellableContinuation) {
        choreographer2.postFrameCallback(new Choreographer.FrameCallback() { // from class: kotlinx.coroutines.android.b
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                HandlerDispatcherKt.d(CancellableContinuation.this, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(CancellableContinuation cancellableContinuation, long j2) {
        cancellableContinuation.H(Dispatchers.c(), Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(CancellableContinuation cancellableContinuation) {
        Choreographer choreographer2 = choreographer;
        if (choreographer2 == null) {
            choreographer2 = Choreographer.getInstance();
            Intrinsics.b(choreographer2);
            choreographer = choreographer2;
        }
        c(choreographer2, cancellableContinuation);
    }
}
