package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

@Metadata
/* loaded from: classes2.dex */
final class ThreadState {

    /* renamed from: a, reason: collision with root package name */
    public final CoroutineContext f19416a;

    /* renamed from: b, reason: collision with root package name */
    private final Object[] f19417b;

    /* renamed from: c, reason: collision with root package name */
    private final ThreadContextElement[] f19418c;

    /* renamed from: d, reason: collision with root package name */
    private int f19419d;

    public ThreadState(CoroutineContext coroutineContext, int i2) {
        this.f19416a = coroutineContext;
        this.f19417b = new Object[i2];
        this.f19418c = new ThreadContextElement[i2];
    }

    public final void a(ThreadContextElement threadContextElement, Object obj) {
        Object[] objArr = this.f19417b;
        int i2 = this.f19419d;
        objArr[i2] = obj;
        ThreadContextElement[] threadContextElementArr = this.f19418c;
        this.f19419d = i2 + 1;
        threadContextElementArr[i2] = threadContextElement;
    }

    public final void b(CoroutineContext coroutineContext) {
        int length = this.f19418c.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            ThreadContextElement threadContextElement = this.f19418c[length];
            Intrinsics.b(threadContextElement);
            threadContextElement.E(coroutineContext, this.f19417b[length]);
            if (i2 < 0) {
                return;
            } else {
                length = i2;
            }
        }
    }
}
