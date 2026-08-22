package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$4 extends Lambda implements Function3<CopyActionContext, Path, Path, CopyActionResult> {
    final /* synthetic */ boolean $followLinks;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathsKt__PathRecursiveFunctionsKt$copyToRecursively$4(boolean z) {
        super(3);
        this.$followLinks = z;
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final CopyActionResult u(CopyActionContext copyActionContext, Path src, Path dst) {
        Intrinsics.e(copyActionContext, "$this$null");
        Intrinsics.e(src, "src");
        Intrinsics.e(dst, "dst");
        return copyActionContext.a(src, dst, this.$followLinks);
    }
}
