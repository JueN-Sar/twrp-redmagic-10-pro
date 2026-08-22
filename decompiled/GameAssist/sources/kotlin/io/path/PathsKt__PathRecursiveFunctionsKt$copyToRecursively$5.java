package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5 extends Lambda implements Function1<FileVisitorBuilder, Unit> {
    final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
    final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
    final /* synthetic */ Path $target;
    final /* synthetic */ Path $this_copyToRecursively;

    @Metadata
    /* renamed from: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<Path, BasicFileAttributes, FileVisitResult> {
        final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
        final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
        final /* synthetic */ Path $target;
        final /* synthetic */ Path $this_copyToRecursively;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
            super(2, Intrinsics.Kotlin.class, "copy", "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
            this.$copyAction = function3;
            this.$this_copyToRecursively = path;
            this.$target = path2;
            this.$onError = function32;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public final FileVisitResult y(Path p0, BasicFileAttributes p1) {
            FileVisitResult c2;
            Intrinsics.e(p0, "p0");
            Intrinsics.e(p1, "p1");
            c2 = PathsKt__PathRecursiveFunctionsKt.c(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError, p0, p1);
            return c2;
        }
    }

    @Metadata
    /* renamed from: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<Path, BasicFileAttributes, FileVisitResult> {
        final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
        final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
        final /* synthetic */ Path $target;
        final /* synthetic */ Path $this_copyToRecursively;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
            super(2, Intrinsics.Kotlin.class, "copy", "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
            this.$copyAction = function3;
            this.$this_copyToRecursively = path;
            this.$target = path2;
            this.$onError = function32;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public final FileVisitResult y(Path p0, BasicFileAttributes p1) {
            FileVisitResult c2;
            Intrinsics.e(p0, "p0");
            Intrinsics.e(p1, "p1");
            c2 = PathsKt__PathRecursiveFunctionsKt.c(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError, p0, p1);
            return c2;
        }
    }

    @Metadata
    /* renamed from: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$3, reason: invalid class name */
    /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<Path, Exception, FileVisitResult> {
        final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
        final /* synthetic */ Path $target;
        final /* synthetic */ Path $this_copyToRecursively;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function3, Path path, Path path2) {
            super(2, Intrinsics.Kotlin.class, "error", "copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/lang/Exception;)Ljava/nio/file/FileVisitResult;", 0);
            this.$onError = function3;
            this.$this_copyToRecursively = path;
            this.$target = path2;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public final FileVisitResult y(Path p0, Exception p1) {
            FileVisitResult e2;
            Intrinsics.e(p0, "p0");
            Intrinsics.e(p1, "p1");
            e2 = PathsKt__PathRecursiveFunctionsKt.e(this.$onError, this.$this_copyToRecursively, this.$target, p0, p1);
            return e2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
        super(1);
        this.$copyAction = function3;
        this.$this_copyToRecursively = path;
        this.$target = path2;
        this.$onError = function32;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((FileVisitorBuilder) obj);
        return Unit.f18288a;
    }

    public final void d(FileVisitorBuilder visitFileTree) {
        Intrinsics.e(visitFileTree, "$this$visitFileTree");
        visitFileTree.b(new AnonymousClass1(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError));
        visitFileTree.a(new AnonymousClass2(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError));
        visitFileTree.d(new AnonymousClass3(this.$onError, this.$this_copyToRecursively, this.$target));
        final Function3<Path, Path, Exception, OnErrorResult> function3 = this.$onError;
        final Path path = this.$this_copyToRecursively;
        final Path path2 = this.$target;
        visitFileTree.c(new Function2<Path, IOException, FileVisitResult>() { // from class: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final FileVisitResult y(Path directory, IOException iOException) {
                FileVisitResult e2;
                Intrinsics.e(directory, "directory");
                if (iOException == null) {
                    return FileVisitResult.CONTINUE;
                }
                e2 = PathsKt__PathRecursiveFunctionsKt.e(function3, path, path2, directory, iOException);
                return e2;
            }
        });
    }
}
