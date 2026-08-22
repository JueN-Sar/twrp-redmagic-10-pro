package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
final class EmptyFlow implements Flow {

    /* renamed from: c, reason: collision with root package name */
    public static final EmptyFlow f19100c = new EmptyFlow();

    private EmptyFlow() {
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        return Unit.f18288a;
    }
}
