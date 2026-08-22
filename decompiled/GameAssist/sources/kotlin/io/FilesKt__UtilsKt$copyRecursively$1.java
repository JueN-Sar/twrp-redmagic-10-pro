package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class FilesKt__UtilsKt$copyRecursively$1 extends Lambda implements Function2 {
    public static final FilesKt__UtilsKt$copyRecursively$1 INSTANCE = new FilesKt__UtilsKt$copyRecursively$1();

    FilesKt__UtilsKt$copyRecursively$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Void y(File file, IOException exception) {
        Intrinsics.e(file, "<anonymous parameter 0>");
        Intrinsics.e(exception, "exception");
        throw exception;
    }
}
