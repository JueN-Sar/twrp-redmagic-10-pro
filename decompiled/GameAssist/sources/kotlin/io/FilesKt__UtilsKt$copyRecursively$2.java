package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class FilesKt__UtilsKt$copyRecursively$2 extends Lambda implements Function2<File, IOException, Unit> {
    final /* synthetic */ Function2<File, IOException, OnErrorAction> $onError;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FilesKt__UtilsKt$copyRecursively$2(Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        super(2);
        this.$onError = function2;
    }

    public final void d(File f2, IOException e2) {
        Intrinsics.e(f2, "f");
        Intrinsics.e(e2, "e");
        if (this.$onError.y(f2, e2) == OnErrorAction.TERMINATE) {
            throw new TerminateException(f2);
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
        d((File) obj, (IOException) obj2);
        return Unit.f18288a;
    }
}
