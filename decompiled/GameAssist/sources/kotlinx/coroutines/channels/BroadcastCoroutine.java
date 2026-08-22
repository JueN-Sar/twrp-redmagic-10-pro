package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.channels.SendChannel;

@Metadata
/* loaded from: classes2.dex */
class BroadcastCoroutine<E> extends AbstractCoroutine<Unit> implements ProducerScope<E>, BroadcastChannel<E> {

    /* renamed from: i, reason: collision with root package name */
    private final BroadcastChannel f19000i;

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean J(Throwable th) {
        boolean J = this.f19000i.J(th);
        start();
        return J;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object M(Object obj, Continuation continuation) {
        return this.f19000i.M(obj, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean N() {
        return this.f19000i.N();
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void a(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(d0(), null, this);
        }
        a0(cancellationException);
    }

    @Override // kotlinx.coroutines.JobSupport
    public void a0(Throwable th) {
        CancellationException V0 = JobSupport.V0(this, th, null, 1, null);
        this.f19000i.a(V0);
        X(V0);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void d1(Throwable th, boolean z) {
        if (this.f19000i.J(th) || z) {
            return;
        }
        CoroutineExceptionHandlerKt.a(getContext(), th);
    }

    protected final BroadcastChannel g1() {
        return this.f19000i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.AbstractCoroutine
    /* renamed from: h1, reason: merged with bridge method [inline-methods] */
    public void e1(Unit unit) {
        SendChannel.DefaultImpls.a(this.f19000i, null, 1, null);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.channels.ProducerScope
    public SendChannel l() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel q() {
        return this.f19000i.q();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void u(Function1 function1) {
        this.f19000i.u(function1);
    }
}
