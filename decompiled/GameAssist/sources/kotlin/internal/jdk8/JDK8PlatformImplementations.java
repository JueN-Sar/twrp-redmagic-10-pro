package kotlin.internal.jdk8;

import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;

@Metadata
/* loaded from: classes2.dex */
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {

    @Metadata
    @SourceDebugExtension
    private static final class ReflectSdkVersion {

        /* renamed from: a, reason: collision with root package name */
        public static final ReflectSdkVersion f18431a = new ReflectSdkVersion();

        /* renamed from: b, reason: collision with root package name */
        public static final Integer f18432b;

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
                f18432b = num2;
            }
            num = null;
            if (num != null) {
                num2 = num;
            }
            f18432b = num2;
        }

        private ReflectSdkVersion() {
        }
    }

    private final boolean c(int i2) {
        Integer num = ReflectSdkVersion.f18432b;
        return num == null || num.intValue() >= i2;
    }

    @Override // kotlin.internal.PlatformImplementations
    public Random b() {
        return c(34) ? new PlatformThreadLocalRandom() : super.b();
    }
}
