package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {

    /* renamed from: e, reason: collision with root package name */
    public static final Key f18849e = Key.f18850c;

    @Metadata
    public static final class DefaultImpls {
    }

    @Metadata
    public static final class Key implements CoroutineContext.Key<CoroutineExceptionHandler> {

        /* renamed from: c, reason: collision with root package name */
        static final /* synthetic */ Key f18850c = new Key();

        private Key() {
        }
    }

    void P(CoroutineContext coroutineContext, Throwable th);
}
