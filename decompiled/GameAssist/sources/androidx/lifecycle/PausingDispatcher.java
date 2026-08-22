package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata
/* loaded from: classes.dex */
public final class PausingDispatcher extends CoroutineDispatcher {

    /* renamed from: i, reason: collision with root package name */
    public final DispatchQueue f4339i = new DispatchQueue();

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext context, Runnable block) {
        Intrinsics.e(context, "context");
        Intrinsics.e(block, "block");
        this.f4339i.c(context, block);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean l0(CoroutineContext context) {
        Intrinsics.e(context, "context");
        if (Dispatchers.c().n0().l0(context)) {
            return true;
        }
        return !this.f4339i.b();
    }
}
