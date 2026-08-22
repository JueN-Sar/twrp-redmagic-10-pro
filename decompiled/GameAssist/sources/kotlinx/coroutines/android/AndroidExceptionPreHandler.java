package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class AndroidExceptionPreHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {

    @Nullable
    private volatile Object _preHandler;

    public AndroidExceptionPreHandler() {
        super(CoroutineExceptionHandler.f18849e);
        this._preHandler = this;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void P(CoroutineContext coroutineContext, Throwable th) {
    }
}
