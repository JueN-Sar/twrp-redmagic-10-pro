package kotlin.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class PlatformImplementations {

    @Metadata
    @SourceDebugExtension
    private static final class ReflectThrowable {

        /* renamed from: a, reason: collision with root package name */
        public static final ReflectThrowable f18425a = new ReflectThrowable();

        /* renamed from: b, reason: collision with root package name */
        public static final Method f18426b;

        /* renamed from: c, reason: collision with root package name */
        public static final Method f18427c;

        static {
            Method method;
            Method method2;
            Object T;
            Method[] throwableMethods = Throwable.class.getMethods();
            Intrinsics.d(throwableMethods, "throwableMethods");
            int length = throwableMethods.length;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                method = null;
                if (i3 >= length) {
                    method2 = null;
                    break;
                }
                method2 = throwableMethods[i3];
                if (Intrinsics.a(method2.getName(), "addSuppressed")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    Intrinsics.d(parameterTypes, "it.parameterTypes");
                    T = ArraysKt___ArraysKt.T(parameterTypes);
                    if (Intrinsics.a(T, Throwable.class)) {
                        break;
                    }
                }
                i3++;
            }
            f18426b = method2;
            int length2 = throwableMethods.length;
            while (true) {
                if (i2 >= length2) {
                    break;
                }
                Method method3 = throwableMethods[i2];
                if (Intrinsics.a(method3.getName(), "getSuppressed")) {
                    method = method3;
                    break;
                }
                i2++;
            }
            f18427c = method;
        }

        private ReflectThrowable() {
        }
    }

    public void a(Throwable cause, Throwable exception) {
        Intrinsics.e(cause, "cause");
        Intrinsics.e(exception, "exception");
        Method method = ReflectThrowable.f18426b;
        if (method != null) {
            method.invoke(cause, exception);
        }
    }

    public Random b() {
        return new FallbackThreadLocalRandom();
    }
}
