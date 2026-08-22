package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

/* loaded from: classes2.dex */
public class Intrinsics {

    @SinceKotlin
    public static class Kotlin {
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void b(Object obj) {
        if (obj == null) {
            m();
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            n(str);
        }
    }

    public static void d(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((NullPointerException) k(new NullPointerException(str + " must not be null")));
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            o(str);
        }
    }

    public static int f(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 == i3 ? 0 : 1;
    }

    public static int g(long j2, long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j2 == j3 ? 0 : 1;
    }

    private static String h(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = Intrinsics.class.getName();
        int i2 = 0;
        while (!stackTrace[i2].getClassName().equals(name)) {
            i2++;
        }
        while (stackTrace[i2].getClassName().equals(name)) {
            i2++;
        }
        StackTraceElement stackTraceElement = stackTrace[i2];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    public static void i() {
        p();
    }

    public static void j(int i2, String str) {
        p();
    }

    private static Throwable k(Throwable th) {
        return l(th, Intrinsics.class.getName());
    }

    static Throwable l(Throwable th, String str) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stackTrace[i3].getClassName())) {
                i2 = i3;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
        return th;
    }

    public static void m() {
        throw ((NullPointerException) k(new NullPointerException()));
    }

    public static void n(String str) {
        throw ((NullPointerException) k(new NullPointerException(str)));
    }

    private static void o(String str) {
        throw ((NullPointerException) k(new NullPointerException(h(str))));
    }

    public static void p() {
        q("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void q(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static void r(String str) {
        throw ((UninitializedPropertyAccessException) k(new UninitializedPropertyAccessException(str)));
    }

    public static void s(String str) {
        r("lateinit property " + str + " has not been initialized");
    }
}
