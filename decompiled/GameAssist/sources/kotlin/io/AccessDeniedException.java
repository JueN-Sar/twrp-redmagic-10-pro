package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class AccessDeniedException extends FileSystemException {
    public /* synthetic */ AccessDeniedException(File file, File file2, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i2 & 2) != 0 ? null : file2, (i2 & 4) != 0 ? null : str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessDeniedException(@NotNull File file, @Nullable File file2, @Nullable String str) {
        super(file, file2, str);
        Intrinsics.e(file, "file");
    }
}
