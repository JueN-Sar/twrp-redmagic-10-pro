package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public final class NopCollector implements FlowCollector<Object> {

    /* renamed from: c, reason: collision with root package name */
    public static final NopCollector f19323c = new NopCollector();

    private NopCollector() {
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        return Unit.f18288a;
    }
}
