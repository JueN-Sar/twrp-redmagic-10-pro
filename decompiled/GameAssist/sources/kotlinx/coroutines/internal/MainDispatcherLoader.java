package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata
/* loaded from: classes2.dex */
public final class MainDispatcherLoader {

    /* renamed from: a, reason: collision with root package name */
    public static final MainDispatcherLoader f19393a;

    /* renamed from: b, reason: collision with root package name */
    public static final MainCoroutineDispatcher f19394b;

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        f19393a = mainDispatcherLoader;
        SystemPropsKt.e("kotlinx.coroutines.fast.service.loader", true);
        f19394b = mainDispatcherLoader.a();
    }

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher a() {
        Sequence c2;
        List l2;
        Object next;
        MainCoroutineDispatcher e2;
        try {
            c2 = SequencesKt__SequencesKt.c(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator());
            l2 = SequencesKt___SequencesKt.l(c2);
            Iterator it = l2.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int c3 = ((MainDispatcherFactory) next).c();
                    do {
                        Object next2 = it.next();
                        int c4 = ((MainDispatcherFactory) next2).c();
                        if (c3 < c4) {
                            next = next2;
                            c3 = c4;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
            if (mainDispatcherFactory != null && (e2 = MainDispatchersKt.e(mainDispatcherFactory, l2)) != null) {
                return e2;
            }
            return MainDispatchersKt.b(null, null, 3, null);
        } catch (Throwable th) {
            return MainDispatchersKt.b(th, null, 2, null);
        }
    }
}
