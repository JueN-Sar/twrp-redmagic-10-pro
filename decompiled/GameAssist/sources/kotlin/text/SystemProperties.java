package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class SystemProperties {

    /* renamed from: a, reason: collision with root package name */
    public static final SystemProperties f18790a = new SystemProperties();

    /* renamed from: b, reason: collision with root package name */
    public static final String f18791b;

    static {
        String property = System.getProperty("line.separator");
        Intrinsics.b(property);
        f18791b = property;
    }

    private SystemProperties() {
    }
}
