package kotlin.io.path;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata
/* loaded from: classes2.dex */
final class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$2 extends Lambda implements Function3<CopyActionContext, Path, Path, CopyActionResult> {
    final /* synthetic */ boolean $followLinks;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathsKt__PathRecursiveFunctionsKt$copyToRecursively$2(boolean z) {
        super(3);
        this.$followLinks = z;
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final CopyActionResult u(CopyActionContext copyToRecursively, Path src, Path dst) {
        Intrinsics.e(copyToRecursively, "$this$copyToRecursively");
        Intrinsics.e(src, "src");
        Intrinsics.e(dst, "dst");
        LinkOption[] a2 = LinkFollowing.f18513a.a(this.$followLinks);
        boolean isDirectory = Files.isDirectory(dst, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1));
        LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(a2, a2.length);
        if (!Files.isDirectory(src, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length)) || !isDirectory) {
            if (isDirectory) {
                PathsKt__PathRecursiveFunctionsKt.f(dst);
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.b(a2);
            spreadBuilder.a(StandardCopyOption.REPLACE_EXISTING);
            CopyOption[] copyOptionArr = (CopyOption[]) spreadBuilder.d(new CopyOption[spreadBuilder.c()]);
            Intrinsics.d(Files.copy(src, dst, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length)), "copy(this, target, *options)");
        }
        return CopyActionResult.CONTINUE;
    }
}
