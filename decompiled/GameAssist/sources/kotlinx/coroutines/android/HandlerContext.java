package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.NonDisposableHandle;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class HandlerContext extends HandlerDispatcher implements Delay {

    @Nullable
    private volatile HandlerContext _immediate;

    /* renamed from: i, reason: collision with root package name */
    private final Handler f18944i;

    /* renamed from: j, reason: collision with root package name */
    private final String f18945j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f18946k;

    /* renamed from: l, reason: collision with root package name */
    private final HandlerContext f18947l;

    private HandlerContext(Handler handler, String str, boolean z) {
        super(null);
        this.f18944i = handler;
        this.f18945j = str;
        this.f18946k = z;
        this._immediate = z ? this : null;
        HandlerContext handlerContext = this._immediate;
        if (handlerContext == null) {
            handlerContext = new HandlerContext(handler, str, true);
            this._immediate = handlerContext;
        }
        this.f18947l = handlerContext;
    }

    private final void r0(CoroutineContext coroutineContext, Runnable runnable) {
        JobKt.c(coroutineContext, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Dispatchers.b().j0(coroutineContext, runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void t0(HandlerContext handlerContext, Runnable runnable) {
        handlerContext.f18944i.removeCallbacks(runnable);
    }

    @Override // kotlinx.coroutines.android.HandlerDispatcher, kotlinx.coroutines.Delay
    public DisposableHandle B(long j2, final Runnable runnable, CoroutineContext coroutineContext) {
        long d2;
        Handler handler = this.f18944i;
        d2 = RangesKt___RangesKt.d(j2, 4611686018427387903L);
        if (handler.postDelayed(runnable, d2)) {
            return new DisposableHandle() { // from class: kotlinx.coroutines.android.a
                @Override // kotlinx.coroutines.DisposableHandle
                public final void dispose() {
                    HandlerContext.t0(HandlerContext.this, runnable);
                }
            };
        }
        r0(coroutineContext, runnable);
        return NonDisposableHandle.f18921c;
    }

    public boolean equals(Object obj) {
        return (obj instanceof HandlerContext) && ((HandlerContext) obj).f18944i == this.f18944i;
    }

    public int hashCode() {
        return System.identityHashCode(this.f18944i);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        if (this.f18944i.post(runnable)) {
            return;
        }
        r0(coroutineContext, runnable);
    }

    @Override // kotlinx.coroutines.Delay
    public void k(long j2, final CancellableContinuation cancellableContinuation) {
        long d2;
        final Runnable runnable = new Runnable() { // from class: kotlinx.coroutines.android.HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                CancellableContinuation.this.H(this, Unit.f18288a);
            }
        };
        Handler handler = this.f18944i;
        d2 = RangesKt___RangesKt.d(j2, 4611686018427387903L);
        if (handler.postDelayed(runnable, d2)) {
            cancellableContinuation.m(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.android.HandlerContext$scheduleResumeAfterDelay$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object c(Object obj) {
                    d((Throwable) obj);
                    return Unit.f18288a;
                }

                public final void d(Throwable th) {
                    Handler handler2;
                    handler2 = HandlerContext.this.f18944i;
                    handler2.removeCallbacks(runnable);
                }
            });
        } else {
            r0(cancellableContinuation.getContext(), runnable);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean l0(CoroutineContext coroutineContext) {
        return (this.f18946k && Intrinsics.a(Looper.myLooper(), this.f18944i.getLooper())) ? false : true;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    /* renamed from: s0, reason: merged with bridge method [inline-methods] */
    public HandlerContext n0() {
        return this.f18947l;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String o0 = o0();
        if (o0 != null) {
            return o0;
        }
        String str = this.f18945j;
        if (str == null) {
            str = this.f18944i.toString();
        }
        if (!this.f18946k) {
            return str;
        }
        return str + ".immediate";
    }

    public /* synthetic */ HandlerContext(Handler handler, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(handler, (i2 & 2) != 0 ? null : str);
    }

    public HandlerContext(Handler handler, String str) {
        this(handler, str, false);
    }
}
