package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class StartedEagerly implements SharingStarted {
    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow a(StateFlow stateFlow) {
        return FlowKt.t(SharingCommand.START);
    }

    public String toString() {
        return "SharingStarted.Eagerly";
    }
}
