package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class StartedLazily implements SharingStarted {
    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow a(StateFlow stateFlow) {
        return FlowKt.s(new StartedLazily$command$1(stateFlow, null));
    }

    public String toString() {
        return "SharingStarted.Lazily";
    }
}
