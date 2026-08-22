package androidx.lifecycle;

import java.util.ArrayDeque;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata
/* loaded from: classes.dex */
public final class DispatchQueue {

    /* renamed from: b, reason: collision with root package name */
    private boolean f4284b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4285c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f4283a = true;

    /* renamed from: d, reason: collision with root package name */
    private final Queue f4286d = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(DispatchQueue this$0, Runnable runnable) {
        Intrinsics.e(this$0, "this$0");
        Intrinsics.e(runnable, "$runnable");
        this$0.f(runnable);
    }

    private final void f(Runnable runnable) {
        if (!this.f4286d.offer(runnable)) {
            throw new IllegalStateException("cannot enqueue any more runnables".toString());
        }
        e();
    }

    public final boolean b() {
        return this.f4284b || !this.f4283a;
    }

    public final void c(CoroutineContext context, final Runnable runnable) {
        Intrinsics.e(context, "context");
        Intrinsics.e(runnable, "runnable");
        MainCoroutineDispatcher n0 = Dispatchers.c().n0();
        if (n0.l0(context) || b()) {
            n0.j0(context, new Runnable() { // from class: androidx.lifecycle.a
                @Override // java.lang.Runnable
                public final void run() {
                    DispatchQueue.d(DispatchQueue.this, runnable);
                }
            });
        } else {
            f(runnable);
        }
    }

    public final void e() {
        if (this.f4285c) {
            return;
        }
        try {
            this.f4285c = true;
            while ((!this.f4286d.isEmpty()) && b()) {
                Runnable runnable = (Runnable) this.f4286d.poll();
                if (runnable != null) {
                    runnable.run();
                }
            }
        } finally {
            this.f4285c = false;
        }
    }

    public final void g() {
        this.f4284b = true;
        e();
    }

    public final void h() {
        this.f4283a = true;
    }

    public final void i() {
        if (this.f4283a) {
            if (!(!this.f4284b)) {
                throw new IllegalStateException("Cannot resume a finished dispatcher".toString());
            }
            this.f4283a = false;
            e();
        }
    }
}
