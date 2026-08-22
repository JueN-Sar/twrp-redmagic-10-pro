package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

@Metadata
/* loaded from: classes2.dex */
public final class ContextScope implements CoroutineScope {

    /* renamed from: c, reason: collision with root package name */
    private final CoroutineContext f19348c;

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext K() {
        return this.f19348c;
    }

    public String toString() {
        return "CoroutineScope(coroutineContext=" + K() + ')';
    }
}
