package kotlin.io.path;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt___StringsKt;

@Metadata
/* loaded from: classes2.dex */
final class PathRelativizer {

    /* renamed from: a, reason: collision with root package name */
    public static final PathRelativizer f18522a = new PathRelativizer();

    /* renamed from: b, reason: collision with root package name */
    private static final Path f18523b = Paths.get("", new String[0]);

    /* renamed from: c, reason: collision with root package name */
    private static final Path f18524c = Paths.get("..", new String[0]);

    private PathRelativizer() {
    }

    public final Path a(Path path, Path base) {
        boolean i2;
        String R;
        Intrinsics.e(path, "path");
        Intrinsics.e(base, "base");
        Path normalize = base.normalize();
        Path r2 = path.normalize();
        Path relativize = normalize.relativize(r2);
        int min = Math.min(normalize.getNameCount(), r2.getNameCount());
        for (int i3 = 0; i3 < min; i3++) {
            Path name = normalize.getName(i3);
            Path path2 = f18524c;
            if (!Intrinsics.a(name, path2)) {
                break;
            }
            if (!Intrinsics.a(r2.getName(i3), path2)) {
                throw new IllegalArgumentException("Unable to compute relative path");
            }
        }
        if (Intrinsics.a(r2, normalize) || !Intrinsics.a(normalize, f18523b)) {
            String obj = relativize.toString();
            String separator = relativize.getFileSystem().getSeparator();
            Intrinsics.d(separator, "rn.fileSystem.separator");
            i2 = StringsKt__StringsJVMKt.i(obj, separator, false, 2, null);
            if (i2) {
                FileSystem fileSystem = relativize.getFileSystem();
                R = StringsKt___StringsKt.R(obj, relativize.getFileSystem().getSeparator().length());
                r2 = fileSystem.getPath(R, new String[0]);
            } else {
                r2 = relativize;
            }
        }
        Intrinsics.d(r2, "r");
        return r2;
    }
}
