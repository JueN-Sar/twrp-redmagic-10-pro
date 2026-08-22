package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

@Metadata
@DelicateCoroutinesApi
/* loaded from: classes2.dex */
public final class GlobalScope implements CoroutineScope {

    /* renamed from: c, reason: collision with root package name */
    public static final GlobalScope f18891c = new GlobalScope();

    private GlobalScope() {
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext K() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
