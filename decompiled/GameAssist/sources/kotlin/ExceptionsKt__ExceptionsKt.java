package kotlin;

import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class ExceptionsKt__ExceptionsKt {
    public static void a(Throwable th, Throwable exception) {
        Intrinsics.e(th, "<this>");
        Intrinsics.e(exception, "exception");
        if (th != exception) {
            PlatformImplementationsKt.f18428a.a(th, exception);
        }
    }
}
