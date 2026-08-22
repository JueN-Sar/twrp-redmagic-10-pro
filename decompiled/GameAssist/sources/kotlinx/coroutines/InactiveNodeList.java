package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class InactiveNodeList implements Incomplete {

    /* renamed from: c, reason: collision with root package name */
    private final NodeList f18892c;

    public InactiveNodeList(NodeList nodeList) {
        this.f18892c = nodeList;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList i() {
        return this.f18892c;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return false;
    }

    public String toString() {
        return super.toString();
    }
}
