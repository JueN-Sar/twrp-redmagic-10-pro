package kotlin.io.path;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
class PathsKt__PathRecursiveFunctionsKt extends PathsKt__PathReadWriteKt {

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18527a;

        /* renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f18528b;

        static {
            int[] iArr = new int[CopyActionResult.values().length];
            try {
                iArr[CopyActionResult.CONTINUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CopyActionResult.TERMINATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CopyActionResult.SKIP_SUBTREE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f18527a = iArr;
            int[] iArr2 = new int[OnErrorResult.values().length];
            try {
                iArr2[OnErrorResult.TERMINATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[OnErrorResult.SKIP_SUBTREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            f18528b = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileVisitResult c(Function3 function3, Path path, Path path2, Function3 function32, Path path3, BasicFileAttributes basicFileAttributes) {
        try {
            return m((CopyActionResult) function3.u(DefaultCopyActionContext.f18496a, path3, d(path, path2, path3)));
        } catch (Exception e2) {
            return e(function32, path, path2, path3, e2);
        }
    }

    private static final Path d(Path path, Path path2, Path path3) {
        Path resolve = path2.resolve(PathsKt__PathUtilsKt.o(path3, path).toString());
        Intrinsics.d(resolve, "target.resolve(relativePath.pathString)");
        return resolve;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileVisitResult e(Function3 function3, Path path, Path path2, Path path3, Exception exc) {
        return n((OnErrorResult) function3.u(path3, d(path, path2, path3), exc));
    }

    public static final void f(Path path) {
        Intrinsics.e(path, "<this>");
        List g2 = g(path);
        if (!g2.isEmpty()) {
            FileSystemException fileSystemException = new FileSystemException("Failed to delete one or more files. See suppressed exceptions for details.");
            Iterator it = g2.iterator();
            while (it.hasNext()) {
                ExceptionsKt__ExceptionsKt.a(fileSystemException, (Exception) it.next());
            }
            throw fileSystemException;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0035, code lost:
    
        if (r1 != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.util.List g(java.nio.file.Path r7) {
        /*
            kotlin.io.path.ExceptionsCollector r0 = new kotlin.io.path.ExceptionsCollector
            r1 = 0
            r2 = 1
            r3 = 0
            r0.<init>(r1, r2, r3)
            java.nio.file.Path r4 = r7.getParent()
            if (r4 == 0) goto L3e
            java.nio.file.DirectoryStream r5 = java.nio.file.Files.newDirectoryStream(r4)     // Catch: java.lang.Throwable -> L13
            goto L14
        L13:
            r5 = r3
        L14:
            if (r5 == 0) goto L3e
            boolean r6 = r5 instanceof java.nio.file.SecureDirectoryStream     // Catch: java.lang.Throwable -> L2d
            if (r6 == 0) goto L2f
            r0.f(r4)     // Catch: java.lang.Throwable -> L2d
            r2 = r5
            java.nio.file.SecureDirectoryStream r2 = (java.nio.file.SecureDirectoryStream) r2     // Catch: java.lang.Throwable -> L2d
            java.nio.file.Path r4 = r7.getFileName()     // Catch: java.lang.Throwable -> L2d
            java.lang.String r6 = "this.fileName"
            kotlin.jvm.internal.Intrinsics.d(r4, r6)     // Catch: java.lang.Throwable -> L2d
            i(r2, r4, r0)     // Catch: java.lang.Throwable -> L2d
            goto L30
        L2d:
            r7 = move-exception
            goto L38
        L2f:
            r1 = r2
        L30:
            kotlin.Unit r2 = kotlin.Unit.f18288a     // Catch: java.lang.Throwable -> L2d
            kotlin.io.CloseableKt.a(r5, r3)
            if (r1 == 0) goto L41
            goto L3e
        L38:
            throw r7     // Catch: java.lang.Throwable -> L39
        L39:
            r0 = move-exception
            kotlin.io.CloseableKt.a(r5, r7)
            throw r0
        L3e:
            k(r7, r0)
        L41:
            java.util.List r7 = r0.d()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.g(java.nio.file.Path):java.util.List");
    }

    private static final void h(SecureDirectoryStream secureDirectoryStream, Path path, ExceptionsCollector exceptionsCollector) {
        SecureDirectoryStream secureDirectoryStream2;
        try {
            try {
                secureDirectoryStream2 = secureDirectoryStream.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS);
            } catch (Exception e2) {
                exceptionsCollector.a(e2);
                return;
            }
        } catch (NoSuchFileException unused) {
            secureDirectoryStream2 = null;
        }
        if (secureDirectoryStream2 == null) {
            return;
        }
        try {
            Iterator it = secureDirectoryStream2.iterator();
            while (it.hasNext()) {
                Path fileName = ((Path) it.next()).getFileName();
                Intrinsics.d(fileName, "entry.fileName");
                i(secureDirectoryStream2, fileName, exceptionsCollector);
            }
            Unit unit = Unit.f18288a;
            CloseableKt.a(secureDirectoryStream2, null);
        } finally {
        }
    }

    private static final void i(SecureDirectoryStream secureDirectoryStream, Path path, ExceptionsCollector exceptionsCollector) {
        exceptionsCollector.b(path);
        try {
        } catch (Exception e2) {
            exceptionsCollector.a(e2);
        }
        if (l(secureDirectoryStream, path, LinkOption.NOFOLLOW_LINKS)) {
            int e3 = exceptionsCollector.e();
            h(secureDirectoryStream, path, exceptionsCollector);
            if (e3 == exceptionsCollector.e()) {
                secureDirectoryStream.deleteDirectory(path);
                Unit unit = Unit.f18288a;
            }
            exceptionsCollector.c(path);
        }
        secureDirectoryStream.deleteFile(path);
        Unit unit2 = Unit.f18288a;
        exceptionsCollector.c(path);
    }

    private static final void j(Path path, ExceptionsCollector exceptionsCollector) {
        DirectoryStream<Path> directoryStream;
        try {
            try {
                directoryStream = Files.newDirectoryStream(path);
            } catch (Exception e2) {
                exceptionsCollector.a(e2);
                return;
            }
        } catch (NoSuchFileException unused) {
            directoryStream = null;
        }
        if (directoryStream == null) {
            return;
        }
        try {
            for (Path entry : directoryStream) {
                Intrinsics.d(entry, "entry");
                k(entry, exceptionsCollector);
            }
            Unit unit = Unit.f18288a;
            CloseableKt.a(directoryStream, null);
        } finally {
        }
    }

    private static final void k(Path path, ExceptionsCollector exceptionsCollector) {
        try {
            if (Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
                int e2 = exceptionsCollector.e();
                j(path, exceptionsCollector);
                if (e2 == exceptionsCollector.e()) {
                    Files.deleteIfExists(path);
                }
            } else {
                Files.deleteIfExists(path);
            }
        } catch (Exception e3) {
            exceptionsCollector.a(e3);
        }
    }

    private static final boolean l(SecureDirectoryStream secureDirectoryStream, Path path, LinkOption... linkOptionArr) {
        Boolean bool;
        try {
            bool = Boolean.valueOf(((BasicFileAttributeView) secureDirectoryStream.getFileAttributeView(path, BasicFileAttributeView.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))).readAttributes().isDirectory());
        } catch (NoSuchFileException unused) {
            bool = null;
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private static final FileVisitResult m(CopyActionResult copyActionResult) {
        int i2 = WhenMappings.f18527a[copyActionResult.ordinal()];
        if (i2 == 1) {
            return FileVisitResult.CONTINUE;
        }
        if (i2 == 2) {
            return FileVisitResult.TERMINATE;
        }
        if (i2 == 3) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final FileVisitResult n(OnErrorResult onErrorResult) {
        int i2 = WhenMappings.f18528b[onErrorResult.ordinal()];
        if (i2 == 1) {
            return FileVisitResult.TERMINATE;
        }
        if (i2 == 2) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        throw new NoWhenBranchMatchedException();
    }
}
