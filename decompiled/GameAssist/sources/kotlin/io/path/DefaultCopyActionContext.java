package kotlin.io.path;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@ExperimentalPathApi
/* loaded from: classes2.dex */
final class DefaultCopyActionContext implements CopyActionContext {

    /* renamed from: a, reason: collision with root package name */
    public static final DefaultCopyActionContext f18496a = new DefaultCopyActionContext();

    private DefaultCopyActionContext() {
    }

    @Override // kotlin.io.path.CopyActionContext
    public CopyActionResult a(Path path, Path target, boolean z) {
        Intrinsics.e(path, "<this>");
        Intrinsics.e(target, "target");
        LinkOption[] a2 = LinkFollowing.f18513a.a(z);
        LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(a2, a2.length);
        if (!Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length)) || !Files.isDirectory(target, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
            CopyOption[] copyOptionArr = (CopyOption[]) Arrays.copyOf(a2, a2.length);
            Intrinsics.d(Files.copy(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length)), "copy(this, target, *options)");
        }
        return CopyActionResult.CONTINUE;
    }
}
