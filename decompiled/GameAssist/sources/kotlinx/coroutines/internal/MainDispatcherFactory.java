package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.MainCoroutineDispatcher;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface MainDispatcherFactory {

    @Metadata
    public static final class DefaultImpls {
        public static String a(MainDispatcherFactory mainDispatcherFactory) {
            return null;
        }
    }

    String a();

    MainCoroutineDispatcher b(List list);

    int c();
}
