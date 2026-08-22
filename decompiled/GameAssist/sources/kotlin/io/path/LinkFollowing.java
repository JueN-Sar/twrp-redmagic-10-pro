package kotlin.io.path;

import java.nio.file.FileVisitOption;
import java.nio.file.LinkOption;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class LinkFollowing {

    /* renamed from: a, reason: collision with root package name */
    public static final LinkFollowing f18513a = new LinkFollowing();

    /* renamed from: b, reason: collision with root package name */
    private static final LinkOption[] f18514b = {LinkOption.NOFOLLOW_LINKS};

    /* renamed from: c, reason: collision with root package name */
    private static final LinkOption[] f18515c = new LinkOption[0];

    /* renamed from: d, reason: collision with root package name */
    private static final Set f18516d;

    /* renamed from: e, reason: collision with root package name */
    private static final Set f18517e;

    static {
        Set d2;
        Set c2;
        d2 = SetsKt__SetsKt.d();
        f18516d = d2;
        c2 = SetsKt__SetsJVMKt.c(FileVisitOption.FOLLOW_LINKS);
        f18517e = c2;
    }

    private LinkFollowing() {
    }

    public final LinkOption[] a(boolean z) {
        return z ? f18515c : f18514b;
    }

    public final Set b(boolean z) {
        return z ? f18517e : f18516d;
    }
}
