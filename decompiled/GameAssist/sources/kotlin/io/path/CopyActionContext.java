package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.SinceKotlin;

@SinceKotlin
@Metadata
@ExperimentalPathApi
/* loaded from: classes2.dex */
public interface CopyActionContext {
    CopyActionResult a(Path path, Path path2, boolean z);
}
