package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class ThreadState implements Function1<Throwable, Unit> {

    /* renamed from: j, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f18934j = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state");

    /* renamed from: c, reason: collision with root package name */
    private final Job f18935c;

    /* renamed from: i, reason: collision with root package name */
    private DisposableHandle f18937i;

    @NotNull
    private volatile /* synthetic */ int _state = 0;

    /* renamed from: h, reason: collision with root package name */
    private final Thread f18936h = Thread.currentThread();

    public ThreadState(Job job) {
        this.f18935c = job;
    }

    private final Void e(int i2) {
        throw new IllegalStateException(("Illegal state " + i2).toString());
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        f((Throwable) obj);
        return Unit.f18288a;
    }

    public final void d() {
        while (true) {
            int i2 = this._state;
            if (i2 != 0) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        e(i2);
                        throw new KotlinNothingValueException();
                    }
                }
            } else if (f18934j.compareAndSet(this, i2, 1)) {
                DisposableHandle disposableHandle = this.f18937i;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                    return;
                }
                return;
            }
        }
    }

    public void f(Throwable th) {
        int i2;
        do {
            i2 = this._state;
            if (i2 != 0) {
                if (i2 == 1 || i2 == 2 || i2 == 3) {
                    return;
                }
                e(i2);
                throw new KotlinNothingValueException();
            }
        } while (!f18934j.compareAndSet(this, i2, 2));
        this.f18936h.interrupt();
        this._state = 3;
    }

    public final void g() {
        int i2;
        this.f18937i = this.f18935c.p(true, true, this);
        do {
            i2 = this._state;
            if (i2 != 0) {
                if (i2 == 2 || i2 == 3) {
                    return;
                }
                e(i2);
                throw new KotlinNothingValueException();
            }
        } while (!f18934j.compareAndSet(this, i2, 0));
    }
}
