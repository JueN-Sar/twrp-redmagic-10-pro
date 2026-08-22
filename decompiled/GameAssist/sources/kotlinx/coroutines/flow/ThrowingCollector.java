package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
public final class ThrowingCollector implements FlowCollector<Object> {

    /* renamed from: c, reason: collision with root package name */
    public final Throwable f19285c;

    public ThrowingCollector(Throwable th) {
        this.f19285c = th;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        throw this.f19285c;
    }
}
