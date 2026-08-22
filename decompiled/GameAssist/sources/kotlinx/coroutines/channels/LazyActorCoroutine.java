package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
final class LazyActorCoroutine<E> extends ActorCoroutine<E> implements SelectClause2<E, SendChannel<? super E>> {

    /* renamed from: j, reason: collision with root package name */
    private Continuation f19024j;

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    public boolean J(Throwable th) {
        boolean J = super.J(th);
        start();
        return J;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected void L0() {
        CancellableKt.b(this.f19024j, this);
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    public Object M(Object obj, Continuation continuation) {
        Object d2;
        start();
        Object M = super.M(obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return M == d2 ? M : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.channels.ChannelCoroutine, kotlinx.coroutines.channels.SendChannel
    public SelectClause2 o() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public void v(SelectInstance selectInstance, Object obj, Function2 function2) {
        start();
        super.o().v(selectInstance, obj, function2);
    }
}
