package kotlin.io.path;

import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

@Metadata
@ExperimentalPathApi
/* loaded from: classes2.dex */
public final class PathTreeWalk implements Sequence<Path> {

    /* renamed from: a, reason: collision with root package name */
    private final Path f18525a;

    /* renamed from: b, reason: collision with root package name */
    private final PathWalkOption[] f18526b;

    private final Iterator e() {
        Iterator a2;
        a2 = SequencesKt__SequenceBuilderKt.a(new PathTreeWalk$bfsIterator$1(this, null));
        return a2;
    }

    private final Iterator f() {
        Iterator a2;
        a2 = SequencesKt__SequenceBuilderKt.a(new PathTreeWalk$dfsIterator$1(this, null));
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean g() {
        boolean w;
        w = ArraysKt___ArraysKt.w(this.f18526b, PathWalkOption.FOLLOW_LINKS);
        return w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean h() {
        boolean w;
        w = ArraysKt___ArraysKt.w(this.f18526b, PathWalkOption.INCLUDE_DIRECTORIES);
        return w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LinkOption[] i() {
        return LinkFollowing.f18513a.a(g());
    }

    private final boolean j() {
        boolean w;
        w = ArraysKt___ArraysKt.w(this.f18526b, PathWalkOption.BREADTH_FIRST);
        return w;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return j() ? e() : f();
    }
}
