package kotlin.coroutines;

import com.zte.distbus.basetransfer.Constants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class SafeContinuation<T> implements Continuation<T>, CoroutineStackFrame {

    /* renamed from: h, reason: collision with root package name */
    private static final Companion f18413h = new Companion(null);

    /* renamed from: i, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f18414i = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, Constants.EXTRA_RESULT);

    /* renamed from: c, reason: collision with root package name */
    private final Continuation f18415c;

    @Nullable
    private volatile Object result;

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation continuation = this.f18415c;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        Object d2;
        Object d3;
        while (true) {
            Object obj2 = this.result;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.UNDECIDED;
            if (obj2 != coroutineSingletons) {
                d2 = IntrinsicsKt__IntrinsicsKt.d();
                if (obj2 != d2) {
                    throw new IllegalStateException("Already resumed");
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f18414i;
                d3 = IntrinsicsKt__IntrinsicsKt.d();
                if (atomicReferenceFieldUpdater.compareAndSet(this, d3, CoroutineSingletons.RESUMED)) {
                    this.f18415c.g(obj);
                    return;
                }
            } else if (f18414i.compareAndSet(this, coroutineSingletons, obj)) {
                return;
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f18415c.getContext();
    }

    public String toString() {
        return "SafeContinuation for " + this.f18415c;
    }
}
