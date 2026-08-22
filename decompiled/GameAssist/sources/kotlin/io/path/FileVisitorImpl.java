package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class FileVisitorImpl extends SimpleFileVisitor<Path> {

    /* renamed from: a, reason: collision with root package name */
    private final Function2 f18509a;

    /* renamed from: b, reason: collision with root package name */
    private final Function2 f18510b;

    /* renamed from: c, reason: collision with root package name */
    private final Function2 f18511c;

    /* renamed from: d, reason: collision with root package name */
    private final Function2 f18512d;

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FileVisitResult postVisitDirectory(Path dir, IOException iOException) {
        FileVisitResult fileVisitResult;
        Intrinsics.e(dir, "dir");
        Function2 function2 = this.f18512d;
        if (function2 != null && (fileVisitResult = (FileVisitResult) function2.y(dir, iOException)) != null) {
            return fileVisitResult;
        }
        FileVisitResult postVisitDirectory = super.postVisitDirectory(dir, iOException);
        Intrinsics.d(postVisitDirectory, "super.postVisitDirectory(dir, exc)");
        return postVisitDirectory;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        FileVisitResult fileVisitResult;
        Intrinsics.e(dir, "dir");
        Intrinsics.e(attrs, "attrs");
        Function2 function2 = this.f18509a;
        if (function2 != null && (fileVisitResult = (FileVisitResult) function2.y(dir, attrs)) != null) {
            return fileVisitResult;
        }
        FileVisitResult preVisitDirectory = super.preVisitDirectory(dir, attrs);
        Intrinsics.d(preVisitDirectory, "super.preVisitDirectory(dir, attrs)");
        return preVisitDirectory;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileVisitResult fileVisitResult;
        Intrinsics.e(file, "file");
        Intrinsics.e(attrs, "attrs");
        Function2 function2 = this.f18510b;
        if (function2 != null && (fileVisitResult = (FileVisitResult) function2.y(file, attrs)) != null) {
            return fileVisitResult;
        }
        FileVisitResult visitFile = super.visitFile(file, attrs);
        Intrinsics.d(visitFile, "super.visitFile(file, attrs)");
        return visitFile;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        FileVisitResult fileVisitResult;
        Intrinsics.e(file, "file");
        Intrinsics.e(exc, "exc");
        Function2 function2 = this.f18511c;
        if (function2 != null && (fileVisitResult = (FileVisitResult) function2.y(file, exc)) != null) {
            return fileVisitResult;
        }
        FileVisitResult visitFileFailed = super.visitFileFailed(file, exc);
        Intrinsics.d(visitFileFailed, "super.visitFileFailed(file, exc)");
        return visitFileFailed;
    }
}
