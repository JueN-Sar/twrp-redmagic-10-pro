package kotlinx.coroutines.debug.internal;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.Job;

@Metadata
/* loaded from: classes2.dex */
public final class DebugProbesImpl {

    /* renamed from: a, reason: collision with root package name */
    public static final DebugProbesImpl f19070a;

    /* renamed from: b, reason: collision with root package name */
    private static final SimpleDateFormat f19071b;

    /* renamed from: c, reason: collision with root package name */
    private static final ConcurrentWeakMap f19072c;

    /* renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ SequenceNumberRefVolatile f19073d;

    /* renamed from: e, reason: collision with root package name */
    private static final /* synthetic */ AtomicLongFieldUpdater f19074e;

    /* renamed from: f, reason: collision with root package name */
    private static final ReentrantReadWriteLock f19075f;

    /* renamed from: g, reason: collision with root package name */
    private static boolean f19076g;

    /* renamed from: h, reason: collision with root package name */
    private static boolean f19077h;

    /* renamed from: i, reason: collision with root package name */
    private static final Function1 f19078i;
    private static volatile int installations;

    /* renamed from: j, reason: collision with root package name */
    private static final ConcurrentWeakMap f19079j;

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata
    static final class CoroutineOwner<T> implements Continuation<T>, CoroutineStackFrame {

        /* renamed from: c, reason: collision with root package name */
        public final Continuation f19080c;

        /* renamed from: h, reason: collision with root package name */
        public final DebugCoroutineInfoImpl f19081h;

        /* renamed from: i, reason: collision with root package name */
        private final CoroutineStackFrame f19082i;

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public StackTraceElement B() {
            CoroutineStackFrame coroutineStackFrame = this.f19082i;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.B();
            }
            return null;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public CoroutineStackFrame f() {
            CoroutineStackFrame coroutineStackFrame = this.f19082i;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.f();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        public void g(Object obj) {
            DebugProbesImpl.f19070a.g(this);
            this.f19080c.g(obj);
        }

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.f19080c.getContext();
        }

        public String toString() {
            return this.f19080c.toString();
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.debug.internal.DebugProbesImpl$SequenceNumberRefVolatile] */
    static {
        DebugProbesImpl debugProbesImpl = new DebugProbesImpl();
        f19070a = debugProbesImpl;
        f19071b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        f19072c = new ConcurrentWeakMap(false, 1, null);
        f19073d = new Object(0L) { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl.SequenceNumberRefVolatile
            volatile long sequenceNumber;

            {
                this.sequenceNumber = r1;
            }
        };
        f19075f = new ReentrantReadWriteLock();
        f19076g = true;
        f19077h = true;
        f19078i = debugProbesImpl.d();
        f19079j = new ConcurrentWeakMap(true);
        f19074e = AtomicLongFieldUpdater.newUpdater(SequenceNumberRefVolatile.class, "sequenceNumber");
    }

    private DebugProbesImpl() {
    }

    private final Function1 d() {
        Object b2;
        Object newInstance;
        try {
            Result.Companion companion = Result.Companion;
            newInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        if (newInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
        }
        b2 = Result.b((Function1) TypeIntrinsics.a(newInstance, 1));
        return (Function1) (Result.f(b2) ? null : b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean f(CoroutineOwner coroutineOwner) {
        Job job;
        CoroutineContext c2 = coroutineOwner.f19081h.c();
        if (c2 == null || (job = (Job) c2.c(Job.f18898f)) == null || !job.L()) {
            return false;
        }
        f19072c.remove(coroutineOwner);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g(CoroutineOwner coroutineOwner) {
        CoroutineStackFrame h2;
        f19072c.remove(coroutineOwner);
        CoroutineStackFrame f2 = coroutineOwner.f19081h.f();
        if (f2 == null || (h2 = h(f2)) == null) {
            return;
        }
        f19079j.remove(h2);
    }

    private final CoroutineStackFrame h(CoroutineStackFrame coroutineStackFrame) {
        do {
            coroutineStackFrame = coroutineStackFrame.f();
            if (coroutineStackFrame == null) {
                return null;
            }
        } while (coroutineStackFrame.B() == null);
        return coroutineStackFrame;
    }

    public final boolean e() {
        return f19077h;
    }
}
