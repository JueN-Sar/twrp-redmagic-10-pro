package kotlin.io.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class PathTreeWalkKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean c(PathNode pathNode) {
        for (PathNode c2 = pathNode.c(); c2 != null; c2 = c2.c()) {
            if (c2.b() == null || pathNode.b() == null) {
                try {
                    if (Files.isSameFile(c2.d(), pathNode.d())) {
                        return true;
                    }
                } catch (IOException | SecurityException unused) {
                    continue;
                }
            } else if (Intrinsics.a(c2.b(), pathNode.b())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object d(Path path, LinkOption[] linkOptionArr) {
        try {
            LinkOption[] linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length);
            BasicFileAttributes readAttributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length));
            Intrinsics.d(readAttributes, "readAttributes(this, A::class.java, *options)");
            return readAttributes.fileKey();
        } catch (Throwable unused) {
            return null;
        }
    }
}
