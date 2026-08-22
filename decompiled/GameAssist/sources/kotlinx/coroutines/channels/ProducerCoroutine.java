package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.channels.SendChannel;

@Metadata
/* loaded from: classes2.dex */
final class ProducerCoroutine<E> extends ChannelCoroutine<E> implements ProducerScope<E> {
    public ProducerCoroutine(CoroutineContext coroutineContext, Channel channel) {
        super(coroutineContext, channel, true, true);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void d1(Throwable th, boolean z) {
        if (h1().J(th) || z) {
            return;
        }
        CoroutineExceptionHandlerKt.a(getContext(), th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.AbstractCoroutine
    /* renamed from: i1, reason: merged with bridge method [inline-methods] */
    public void e1(Unit unit) {
        SendChannel.DefaultImpls.a(h1(), null, 1, null);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.channels.ProducerScope
    public /* bridge */ /* synthetic */ SendChannel l() {
        return g1();
    }
}
