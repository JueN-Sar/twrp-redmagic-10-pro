package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public final class SendingCollector<T> implements FlowCollector<T> {

    /* renamed from: c, reason: collision with root package name */
    private final SendChannel f19329c;

    public SendingCollector(SendChannel sendChannel) {
        this.f19329c = sendChannel;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        Object d2;
        Object M = this.f19329c.M(obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return M == d2 ? M : Unit.f18288a;
    }
}
