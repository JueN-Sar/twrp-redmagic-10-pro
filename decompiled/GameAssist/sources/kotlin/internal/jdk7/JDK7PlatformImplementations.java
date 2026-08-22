package kotlin.internal.jdk7;

import kotlin.Metadata;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
/* loaded from: classes2.dex */
public class JDK7PlatformImplementations extends PlatformImplementations {

    @Metadata
    @SourceDebugExtension
    private static final class ReflectSdkVersion {

        /* renamed from: a, reason: collision with root package name */
        public static final ReflectSdkVersion f18429a = new ReflectSdkVersion();

        /* renamed from: b, reason: collision with root package name */
        public static final Integer f18430b;

        static {
            Integer num;
            Object obj;
            Integer num2 = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            if (obj instanceof Integer) {
                num = (Integer) obj;
                if (num != null && num.intValue() > 0) {
                    num2 = num;
                }
                f18430b = num2;
            }
            num = null;
            if (num != null) {
                num2 = num;
            }
            f18430b = num2;
        }

        private ReflectSdkVersion() {
        }
    }

    private final boolean c(int i2) {
        Integer num = ReflectSdkVersion.f18430b;
        return num == null || num.intValue() >= i2;
    }

    @Override // kotlin.internal.PlatformImplementations
    public void a(Throwable cause, Throwable exception) {
        Intrinsics.e(cause, "cause");
        Intrinsics.e(exception, "exception");
        if (c(19)) {
            cause.addSuppressed(exception);
        } else {
            super.a(cause, exception);
        }
    }
}
