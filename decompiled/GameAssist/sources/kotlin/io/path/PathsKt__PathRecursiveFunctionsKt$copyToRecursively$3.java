package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$3 extends Lambda implements Function3 {
    public static final PathsKt__PathRecursiveFunctionsKt$copyToRecursively$3 INSTANCE = new PathsKt__PathRecursiveFunctionsKt$copyToRecursively$3();

    PathsKt__PathRecursiveFunctionsKt$copyToRecursively$3() {
        super(3);
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Void u(Path path, Path path2, Exception exception) {
        Intrinsics.e(path, "<anonymous parameter 0>");
        Intrinsics.e(path2, "<anonymous parameter 1>");
        Intrinsics.e(exception, "exception");
        throw exception;
    }
}
