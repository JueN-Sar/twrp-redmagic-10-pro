package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata
/* loaded from: classes2.dex */
public final class MainDispatchersKt {
    private static final MissingMainCoroutineDispatcher a(Throwable th, String str) {
        if (th != null) {
            throw th;
        }
        d();
        throw new KotlinNothingValueException();
    }

    static /* synthetic */ MissingMainCoroutineDispatcher b(Throwable th, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            th = null;
        }
        if ((i2 & 2) != 0) {
            str = null;
        }
        return a(th, str);
    }

    public static final boolean c(MainCoroutineDispatcher mainCoroutineDispatcher) {
        return mainCoroutineDispatcher.n0() instanceof MissingMainCoroutineDispatcher;
    }

    public static final Void d() {
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }

    public static final MainCoroutineDispatcher e(MainDispatcherFactory mainDispatcherFactory, List list) {
        try {
            return mainDispatcherFactory.b(list);
        } catch (Throwable th) {
            return a(th, mainDispatcherFactory.a());
        }
    }
}
