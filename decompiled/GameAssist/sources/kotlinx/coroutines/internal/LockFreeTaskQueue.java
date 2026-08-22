package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public class LockFreeTaskQueue<E> {

    /* renamed from: a, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19383a = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueue.class, Object.class, "_cur");

    @NotNull
    private volatile /* synthetic */ Object _cur;

    public LockFreeTaskQueue(boolean z) {
        this._cur = new LockFreeTaskQueueCore(8, z);
    }

    public final boolean a(Object obj) {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            int a2 = lockFreeTaskQueueCore.a(obj);
            if (a2 == 0) {
                return true;
            }
            if (a2 == 1) {
                f19383a.compareAndSet(this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.i());
            } else if (a2 == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            if (lockFreeTaskQueueCore.d()) {
                return;
            } else {
                f19383a.compareAndSet(this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.i());
            }
        }
    }

    public final int c() {
        return ((LockFreeTaskQueueCore) this._cur).f();
    }

    public final Object d() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            Object j2 = lockFreeTaskQueueCore.j();
            if (j2 != LockFreeTaskQueueCore.f19387h) {
                return j2;
            }
            f19383a.compareAndSet(this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.i());
        }
    }
}
