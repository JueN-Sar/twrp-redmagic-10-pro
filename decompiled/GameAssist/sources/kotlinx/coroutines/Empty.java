package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class Empty implements Incomplete {

    /* renamed from: c, reason: collision with root package name */
    private final boolean f18875c;

    public Empty(boolean z) {
        this.f18875c = z;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList i() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return this.f18875c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empty{");
        sb.append(isActive() ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
