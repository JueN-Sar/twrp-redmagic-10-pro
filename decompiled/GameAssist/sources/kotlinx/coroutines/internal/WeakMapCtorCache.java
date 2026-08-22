package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class WeakMapCtorCache extends CtorCache {

    /* renamed from: a, reason: collision with root package name */
    public static final WeakMapCtorCache f19420a = new WeakMapCtorCache();

    /* renamed from: b, reason: collision with root package name */
    private static final ReentrantReadWriteLock f19421b = new ReentrantReadWriteLock();

    /* renamed from: c, reason: collision with root package name */
    private static final WeakHashMap f19422c = new WeakHashMap();

    private WeakMapCtorCache() {
    }
}
