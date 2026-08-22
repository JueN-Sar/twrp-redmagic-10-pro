package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.intrinsics.CancellableKt;

@Metadata
/* loaded from: classes2.dex */
final class LazyBroadcastCoroutine<E> extends BroadcastCoroutine<E> {

    /* renamed from: j, reason: collision with root package name */
    private final Continuation f19025j;

    @Override // kotlinx.coroutines.JobSupport
    protected void L0() {
        CancellableKt.b(this.f19025j, this);
    }

    @Override // kotlinx.coroutines.channels.BroadcastCoroutine, kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel q() {
        ReceiveChannel q2 = g1().q();
        start();
        return q2;
    }
}
