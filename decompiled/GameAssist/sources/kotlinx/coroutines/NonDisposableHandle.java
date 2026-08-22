package kotlinx.coroutines;

import kotlin.Metadata;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public final class NonDisposableHandle implements DisposableHandle, ChildHandle {

    /* renamed from: c, reason: collision with root package name */
    public static final NonDisposableHandle f18921c = new NonDisposableHandle();

    private NonDisposableHandle() {
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
    }

    @Override // kotlinx.coroutines.ChildHandle
    public Job getParent() {
        return null;
    }

    @Override // kotlinx.coroutines.ChildHandle
    public boolean h(Throwable th) {
        return false;
    }

    public String toString() {
        return "NonDisposableHandle";
    }
}
