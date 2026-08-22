package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

@Metadata
/* loaded from: classes2.dex */
final class StartedWhileSubscribed implements SharingStarted {

    /* renamed from: b, reason: collision with root package name */
    private final long f19275b;

    /* renamed from: c, reason: collision with root package name */
    private final long f19276c;

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow a(StateFlow stateFlow) {
        return FlowKt.h(FlowKt.i(FlowKt.B(stateFlow, new StartedWhileSubscribed$command$1(this, null)), new StartedWhileSubscribed$command$2(null)));
    }

    public boolean equals(Object obj) {
        if (obj instanceof StartedWhileSubscribed) {
            StartedWhileSubscribed startedWhileSubscribed = (StartedWhileSubscribed) obj;
            if (this.f19275b == startedWhileSubscribed.f19275b && this.f19276c == startedWhileSubscribed.f19276c) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (Long.hashCode(this.f19275b) * 31) + Long.hashCode(this.f19276c);
    }

    public String toString() {
        List d2;
        List a2;
        String E;
        d2 = CollectionsKt__CollectionsJVMKt.d(2);
        if (this.f19275b > 0) {
            d2.add("stopTimeout=" + this.f19275b + "ms");
        }
        if (this.f19276c < Long.MAX_VALUE) {
            d2.add("replayExpiration=" + this.f19276c + "ms");
        }
        a2 = CollectionsKt__CollectionsJVMKt.a(d2);
        StringBuilder sb = new StringBuilder();
        sb.append("SharingStarted.WhileSubscribed(");
        E = CollectionsKt___CollectionsKt.E(a2, null, null, null, 0, null, null, 63, null);
        sb.append(E);
        sb.append(')');
        return sb.toString();
    }
}
