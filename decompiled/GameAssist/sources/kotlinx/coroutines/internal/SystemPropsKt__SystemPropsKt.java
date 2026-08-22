package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class SystemPropsKt__SystemPropsKt {

    /* renamed from: a, reason: collision with root package name */
    private static final int f19406a = Runtime.getRuntime().availableProcessors();

    public static final int a() {
        return f19406a;
    }

    public static final String b(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
