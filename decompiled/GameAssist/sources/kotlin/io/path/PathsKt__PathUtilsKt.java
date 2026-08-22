package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
    public static final Path o(Path path, Path base) {
        Intrinsics.e(path, "<this>");
        Intrinsics.e(base, "base");
        try {
            return PathRelativizer.f18522a.a(path, base);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(e2.getMessage() + "\nthis path: " + path + "\nbase path: " + base, e2);
        }
    }
}
