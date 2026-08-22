package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class ExceptionsConstructorKt {

    /* renamed from: a, reason: collision with root package name */
    private static final int f19356a = f(Throwable.class, -1);

    /* renamed from: b, reason: collision with root package name */
    private static final CtorCache f19357b;

    static {
        CtorCache ctorCache;
        try {
            ctorCache = FastServiceLoaderKt.a() ? WeakMapCtorCache.f19420a : ClassValueCtorCache.f19342a;
        } catch (Throwable unused) {
            ctorCache = WeakMapCtorCache.f19420a;
        }
        f19357b = ctorCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 b(Class cls) {
        List V;
        ExceptionsConstructorKt$createConstructor$nullResult$1 exceptionsConstructorKt$createConstructor$nullResult$1 = new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$nullResult$1
            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Void c(Throwable th) {
                return null;
            }
        };
        if (f19356a != f(cls, 0)) {
            return exceptionsConstructorKt$createConstructor$nullResult$1;
        }
        V = ArraysKt___ArraysKt.V(cls.getConstructors(), new Comparator() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int a2;
                a2 = ComparisonsKt__ComparisonsKt.a(Integer.valueOf(((Constructor) obj2).getParameterTypes().length), Integer.valueOf(((Constructor) obj).getParameterTypes().length));
                return a2;
            }
        });
        Iterator it = V.iterator();
        while (it.hasNext()) {
            Function1 c2 = c((Constructor) it.next());
            if (c2 != null) {
                return c2;
            }
        }
        return exceptionsConstructorKt$createConstructor$nullResult$1;
    }

    private static final Function1 c(final Constructor constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length == 0) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final Throwable c(Throwable th) {
                    Object b2;
                    Object newInstance;
                    try {
                        Result.Companion companion = Result.Companion;
                        newInstance = constructor.newInstance(null);
                    } catch (Throwable th2) {
                        Result.Companion companion2 = Result.Companion;
                        b2 = Result.b(ResultKt.a(th2));
                    }
                    if (newInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                    Throwable th3 = (Throwable) newInstance;
                    th3.initCause(th);
                    b2 = Result.b(th3);
                    return (Throwable) (Result.f(b2) ? null : b2);
                }
            };
        }
        if (length != 1) {
            if (length == 2 && Intrinsics.a(parameterTypes[0], String.class) && Intrinsics.a(parameterTypes[1], Throwable.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public final Throwable c(Throwable th) {
                        Object b2;
                        Object newInstance;
                        try {
                            Result.Companion companion = Result.Companion;
                            newInstance = constructor.newInstance(th.getMessage(), th);
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.Companion;
                            b2 = Result.b(ResultKt.a(th2));
                        }
                        if (newInstance == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                        b2 = Result.b((Throwable) newInstance);
                        if (Result.f(b2)) {
                            b2 = null;
                        }
                        return (Throwable) b2;
                    }
                };
            }
            return null;
        }
        Class<?> cls = parameterTypes[0];
        if (Intrinsics.a(cls, Throwable.class)) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final Throwable c(Throwable th) {
                    Object b2;
                    Object newInstance;
                    try {
                        Result.Companion companion = Result.Companion;
                        newInstance = constructor.newInstance(th);
                    } catch (Throwable th2) {
                        Result.Companion companion2 = Result.Companion;
                        b2 = Result.b(ResultKt.a(th2));
                    }
                    if (newInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                    b2 = Result.b((Throwable) newInstance);
                    if (Result.f(b2)) {
                        b2 = null;
                    }
                    return (Throwable) b2;
                }
            };
        }
        if (Intrinsics.a(cls, String.class)) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final Throwable c(Throwable th) {
                    Object b2;
                    Object newInstance;
                    try {
                        Result.Companion companion = Result.Companion;
                        newInstance = constructor.newInstance(th.getMessage());
                    } catch (Throwable th2) {
                        Result.Companion companion2 = Result.Companion;
                        b2 = Result.b(ResultKt.a(th2));
                    }
                    if (newInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                    Throwable th3 = (Throwable) newInstance;
                    th3.initCause(th);
                    b2 = Result.b(th3);
                    if (Result.f(b2)) {
                        b2 = null;
                    }
                    return (Throwable) b2;
                }
            };
        }
        return null;
    }

    private static final int d(Class cls, int i2) {
        do {
            int length = cls.getDeclaredFields().length;
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                if (!Modifier.isStatic(r0[i4].getModifiers())) {
                    i3++;
                }
            }
            i2 += i3;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i2;
    }

    static /* synthetic */ int e(Class cls, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        return d(cls, i2);
    }

    private static final int f(Class cls, int i2) {
        Object b2;
        JvmClassMappingKt.c(cls);
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b(Integer.valueOf(e(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        Integer valueOf = Integer.valueOf(i2);
        if (Result.f(b2)) {
            b2 = valueOf;
        }
        return ((Number) b2).intValue();
    }
}
