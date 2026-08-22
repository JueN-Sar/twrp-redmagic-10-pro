package kotlin.io.path;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
final class DirectoryEntriesReader extends SimpleFileVisitor<Path> {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f18497a;

    /* renamed from: b, reason: collision with root package name */
    private PathNode f18498b;

    /* renamed from: c, reason: collision with root package name */
    private ArrayDeque f18499c = new ArrayDeque();

    public DirectoryEntriesReader(boolean z) {
        this.f18497a = z;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        Intrinsics.e(dir, "dir");
        Intrinsics.e(attrs, "attrs");
        this.f18499c.add(new PathNode(dir, attrs.fileKey(), this.f18498b));
        FileVisitResult preVisitDirectory = super.preVisitDirectory(dir, attrs);
        Intrinsics.d(preVisitDirectory, "super.preVisitDirectory(dir, attrs)");
        return preVisitDirectory;
    }

    public final List b(PathNode directoryNode) {
        Intrinsics.e(directoryNode, "directoryNode");
        this.f18498b = directoryNode;
        Files.walkFileTree(directoryNode.d(), LinkFollowing.f18513a.b(this.f18497a), 1, this);
        this.f18499c.removeFirst();
        ArrayDeque arrayDeque = this.f18499c;
        this.f18499c = new ArrayDeque();
        return arrayDeque;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Intrinsics.e(file, "file");
        Intrinsics.e(attrs, "attrs");
        this.f18499c.add(new PathNode(file, null, this.f18498b));
        FileVisitResult visitFile = super.visitFile(file, attrs);
        Intrinsics.d(visitFile, "super.visitFile(file, attrs)");
        return visitFile;
    }
}
