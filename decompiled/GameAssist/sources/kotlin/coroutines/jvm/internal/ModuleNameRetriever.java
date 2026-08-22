package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
final class ModuleNameRetriever {

    /* renamed from: a, reason: collision with root package name */
    public static final ModuleNameRetriever f18417a = new ModuleNameRetriever();

    /* renamed from: b, reason: collision with root package name */
    private static final Cache f18418b = new Cache(null, null, null);

    /* renamed from: c, reason: collision with root package name */
    private static Cache f18419c;

    @Metadata
    private static final class Cache {

        /* renamed from: a, reason: collision with root package name */
        public final Method f18420a;

        /* renamed from: b, reason: collision with root package name */
        public final Method f18421b;

        /* renamed from: c, reason: collision with root package name */
        public final Method f18422c;

        public Cache(Method method, Method method2, Method method3) {
            this.f18420a = method;
            this.f18421b = method2;
            this.f18422c = method3;
        }
    }

    private ModuleNameRetriever() {
    }

    private final Cache a(BaseContinuationImpl baseContinuationImpl) {
        try {
            Cache cache = new Cache(Class.class.getDeclaredMethod("getModule", null), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
            f18419c = cache;
            return cache;
        } catch (Exception unused) {
            Cache cache2 = f18418b;
            f18419c = cache2;
            return cache2;
        }
    }

    public final String b(BaseContinuationImpl continuation) {
        Intrinsics.e(continuation, "continuation");
        Cache cache = f18419c;
        if (cache == null) {
            cache = a(continuation);
        }
        if (cache == f18418b) {
            return null;
        }
        Method method = cache.f18420a;
        Object invoke = method != null ? method.invoke(continuation.getClass(), null) : null;
        if (invoke == null) {
            return null;
        }
        Method method2 = cache.f18421b;
        Object invoke2 = method2 != null ? method2.invoke(invoke, null) : null;
        if (invoke2 == null) {
            return null;
        }
        Method method3 = cache.f18422c;
        Object invoke3 = method3 != null ? method3.invoke(invoke2, null) : null;
        if (invoke3 instanceof String) {
            return (String) invoke3;
        }
        return null;
    }
}
