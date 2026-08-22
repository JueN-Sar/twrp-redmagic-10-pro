package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.internal.MainDispatcherFactory;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public final class MissingMainCoroutineDispatcherFactory implements MainDispatcherFactory {

    /* renamed from: a, reason: collision with root package name */
    public static final MissingMainCoroutineDispatcherFactory f19397a = new MissingMainCoroutineDispatcherFactory();

    private MissingMainCoroutineDispatcherFactory() {
    }

    @Override // kotlinx.coroutines.internal.MainDispatcherFactory
    public String a() {
        return MainDispatcherFactory.DefaultImpls.a(this);
    }

    @Override // kotlinx.coroutines.internal.MainDispatcherFactory
    public MainCoroutineDispatcher b(List list) {
        return new MissingMainCoroutineDispatcher(null, null, 2, null);
    }

    @Override // kotlinx.coroutines.internal.MainDispatcherFactory
    public int c() {
        return -1;
    }
}
