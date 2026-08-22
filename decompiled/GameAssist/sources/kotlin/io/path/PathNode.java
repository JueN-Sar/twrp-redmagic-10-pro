package kotlin.io.path;

import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class PathNode {

    /* renamed from: a, reason: collision with root package name */
    private final Path f18518a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f18519b;

    /* renamed from: c, reason: collision with root package name */
    private final PathNode f18520c;

    /* renamed from: d, reason: collision with root package name */
    private Iterator f18521d;

    public PathNode(Path path, Object obj, PathNode pathNode) {
        Intrinsics.e(path, "path");
        this.f18518a = path;
        this.f18519b = obj;
        this.f18520c = pathNode;
    }

    public final Iterator a() {
        return this.f18521d;
    }

    public final Object b() {
        return this.f18519b;
    }

    public final PathNode c() {
        return this.f18520c;
    }

    public final Path d() {
        return this.f18518a;
    }

    public final void e(Iterator it) {
        this.f18521d = it;
    }
}
