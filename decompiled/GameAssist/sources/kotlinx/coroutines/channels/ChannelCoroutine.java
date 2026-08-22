package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata
/* loaded from: classes2.dex */
public class ChannelCoroutine<E> extends AbstractCoroutine<Unit> implements Channel<E> {

    /* renamed from: i, reason: collision with root package name */
    private final Channel f19004i;

    public ChannelCoroutine(CoroutineContext coroutineContext, Channel channel, boolean z, boolean z2) {
        super(coroutineContext, z, z2);
        this.f19004i = channel;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean J(Throwable th) {
        return this.f19004i.J(th);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object M(Object obj, Continuation continuation) {
        return this.f19004i.M(obj, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean N() {
        return this.f19004i.N();
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void a(CancellationException cancellationException) {
        if (isCancelled()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(d0(), null, this);
        }
        a0(cancellationException);
    }

    @Override // kotlinx.coroutines.JobSupport
    public void a0(Throwable th) {
        CancellationException V0 = JobSupport.V0(this, th, null, 1, null);
        this.f19004i.a(V0);
        X(V0);
    }

    public final Channel g1() {
        return this;
    }

    protected final Channel h1() {
        return this.f19004i;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator iterator() {
        return this.f19004i.iterator();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2 o() {
        return this.f19004i.o();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void u(Function1 function1) {
        this.f19004i.u(function1);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 w() {
        return this.f19004i.w();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 x() {
        return this.f19004i.x();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object y() {
        return this.f19004i.y();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object z(Continuation continuation) {
        Object z = this.f19004i.z(continuation);
        IntrinsicsKt__IntrinsicsKt.d();
        return z;
    }
}
