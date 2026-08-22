package kotlin.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
/* loaded from: classes2.dex */
public final class FileTreeWalk implements Sequence<File> {

    /* renamed from: a, reason: collision with root package name */
    private final File f18439a;

    /* renamed from: b, reason: collision with root package name */
    private final FileWalkDirection f18440b;

    /* renamed from: c, reason: collision with root package name */
    private final Function1 f18441c;

    /* renamed from: d, reason: collision with root package name */
    private final Function1 f18442d;

    /* renamed from: e, reason: collision with root package name */
    private final Function2 f18443e;

    /* renamed from: f, reason: collision with root package name */
    private final int f18444f;

    @Metadata
    @SourceDebugExtension
    private static abstract class DirectoryState extends WalkState {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DirectoryState(File rootDir) {
            super(rootDir);
            Intrinsics.e(rootDir, "rootDir");
        }
    }

    @Metadata
    private final class FileTreeWalkIterator extends AbstractIterator<File> {

        /* renamed from: i, reason: collision with root package name */
        private final ArrayDeque f18445i;

        @Metadata
        private final class BottomUpDirectoryState extends DirectoryState {

            /* renamed from: b, reason: collision with root package name */
            private boolean f18447b;

            /* renamed from: c, reason: collision with root package name */
            private File[] f18448c;

            /* renamed from: d, reason: collision with root package name */
            private int f18449d;

            /* renamed from: e, reason: collision with root package name */
            private boolean f18450e;

            /* renamed from: f, reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f18451f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BottomUpDirectoryState(FileTreeWalkIterator fileTreeWalkIterator, File rootDir) {
                super(rootDir);
                Intrinsics.e(rootDir, "rootDir");
                this.f18451f = fileTreeWalkIterator;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File b() {
                if (!this.f18450e && this.f18448c == null) {
                    Function1 function1 = FileTreeWalk.this.f18441c;
                    if (function1 != null && !((Boolean) function1.c(a())).booleanValue()) {
                        return null;
                    }
                    File[] listFiles = a().listFiles();
                    this.f18448c = listFiles;
                    if (listFiles == null) {
                        Function2 function2 = FileTreeWalk.this.f18443e;
                        if (function2 != null) {
                            function2.y(a(), new AccessDeniedException(a(), null, "Cannot list files in a directory", 2, null));
                        }
                        this.f18450e = true;
                    }
                }
                File[] fileArr = this.f18448c;
                if (fileArr != null) {
                    int i2 = this.f18449d;
                    Intrinsics.b(fileArr);
                    if (i2 < fileArr.length) {
                        File[] fileArr2 = this.f18448c;
                        Intrinsics.b(fileArr2);
                        int i3 = this.f18449d;
                        this.f18449d = i3 + 1;
                        return fileArr2[i3];
                    }
                }
                if (!this.f18447b) {
                    this.f18447b = true;
                    return a();
                }
                Function1 function12 = FileTreeWalk.this.f18442d;
                if (function12 != null) {
                    function12.c(a());
                }
                return null;
            }
        }

        @Metadata
        @SourceDebugExtension
        private final class SingleFileState extends WalkState {

            /* renamed from: b, reason: collision with root package name */
            private boolean f18452b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f18453c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SingleFileState(FileTreeWalkIterator fileTreeWalkIterator, File rootFile) {
                super(rootFile);
                Intrinsics.e(rootFile, "rootFile");
                this.f18453c = fileTreeWalkIterator;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File b() {
                if (this.f18452b) {
                    return null;
                }
                this.f18452b = true;
                return a();
            }
        }

        @Metadata
        private final class TopDownDirectoryState extends DirectoryState {

            /* renamed from: b, reason: collision with root package name */
            private boolean f18454b;

            /* renamed from: c, reason: collision with root package name */
            private File[] f18455c;

            /* renamed from: d, reason: collision with root package name */
            private int f18456d;

            /* renamed from: e, reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f18457e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public TopDownDirectoryState(FileTreeWalkIterator fileTreeWalkIterator, File rootDir) {
                super(rootDir);
                Intrinsics.e(rootDir, "rootDir");
                this.f18457e = fileTreeWalkIterator;
            }

            /* JADX WARN: Code restructure failed: missing block: B:29:0x007f, code lost:
            
                if (r0.length == 0) goto L31;
             */
            @Override // kotlin.io.FileTreeWalk.WalkState
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.io.File b() {
                /*
                    r10 = this;
                    boolean r0 = r10.f18454b
                    r1 = 0
                    if (r0 != 0) goto L28
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f18457e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.c(r0)
                    if (r0 == 0) goto L20
                    java.io.File r2 = r10.a()
                    java.lang.Object r0 = r0.c(r2)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 != 0) goto L20
                    return r1
                L20:
                    r0 = 1
                    r10.f18454b = r0
                    java.io.File r10 = r10.a()
                    return r10
                L28:
                    java.io.File[] r0 = r10.f18455c
                    if (r0 == 0) goto L47
                    int r2 = r10.f18456d
                    kotlin.jvm.internal.Intrinsics.b(r0)
                    int r0 = r0.length
                    if (r2 >= r0) goto L35
                    goto L47
                L35:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f18457e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.e(r0)
                    if (r0 == 0) goto L46
                    java.io.File r10 = r10.a()
                    r0.c(r10)
                L46:
                    return r1
                L47:
                    java.io.File[] r0 = r10.f18455c
                    if (r0 != 0) goto L93
                    java.io.File r0 = r10.a()
                    java.io.File[] r0 = r0.listFiles()
                    r10.f18455c = r0
                    if (r0 != 0) goto L77
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f18457e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function2 r0 = kotlin.io.FileTreeWalk.d(r0)
                    if (r0 == 0) goto L77
                    java.io.File r2 = r10.a()
                    kotlin.io.AccessDeniedException r9 = new kotlin.io.AccessDeniedException
                    java.io.File r4 = r10.a()
                    r7 = 2
                    r8 = 0
                    r5 = 0
                    java.lang.String r6 = "Cannot list files in a directory"
                    r3 = r9
                    r3.<init>(r4, r5, r6, r7, r8)
                    r0.y(r2, r9)
                L77:
                    java.io.File[] r0 = r10.f18455c
                    if (r0 == 0) goto L81
                    kotlin.jvm.internal.Intrinsics.b(r0)
                    int r0 = r0.length
                    if (r0 != 0) goto L93
                L81:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f18457e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.e(r0)
                    if (r0 == 0) goto L92
                    java.io.File r10 = r10.a()
                    r0.c(r10)
                L92:
                    return r1
                L93:
                    java.io.File[] r0 = r10.f18455c
                    kotlin.jvm.internal.Intrinsics.b(r0)
                    int r1 = r10.f18456d
                    int r2 = r1 + 1
                    r10.f18456d = r2
                    r10 = r0[r1]
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.FileTreeWalkIterator.TopDownDirectoryState.b():java.io.File");
            }
        }

        @Metadata
        public /* synthetic */ class WhenMappings {

            /* renamed from: a, reason: collision with root package name */
            public static final /* synthetic */ int[] f18458a;

            static {
                int[] iArr = new int[FileWalkDirection.values().length];
                try {
                    iArr[FileWalkDirection.TOP_DOWN.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                f18458a = iArr;
            }
        }

        public FileTreeWalkIterator() {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.f18445i = arrayDeque;
            if (FileTreeWalk.this.f18439a.isDirectory()) {
                arrayDeque.push(f(FileTreeWalk.this.f18439a));
            } else if (FileTreeWalk.this.f18439a.isFile()) {
                arrayDeque.push(new SingleFileState(this, FileTreeWalk.this.f18439a));
            } else {
                c();
            }
        }

        private final DirectoryState f(File file) {
            int i2 = WhenMappings.f18458a[FileTreeWalk.this.f18440b.ordinal()];
            if (i2 == 1) {
                return new TopDownDirectoryState(this, file);
            }
            if (i2 == 2) {
                return new BottomUpDirectoryState(this, file);
            }
            throw new NoWhenBranchMatchedException();
        }

        private final File g() {
            File b2;
            while (true) {
                WalkState walkState = (WalkState) this.f18445i.peek();
                if (walkState == null) {
                    return null;
                }
                b2 = walkState.b();
                if (b2 == null) {
                    this.f18445i.pop();
                } else {
                    if (Intrinsics.a(b2, walkState.a()) || !b2.isDirectory() || this.f18445i.size() >= FileTreeWalk.this.f18444f) {
                        break;
                    }
                    this.f18445i.push(f(b2));
                }
            }
            return b2;
        }

        @Override // kotlin.collections.AbstractIterator
        protected void b() {
            File g2 = g();
            if (g2 != null) {
                d(g2);
            } else {
                c();
            }
        }
    }

    @Metadata
    private static abstract class WalkState {

        /* renamed from: a, reason: collision with root package name */
        private final File f18459a;

        public WalkState(File root) {
            Intrinsics.e(root, "root");
            this.f18459a = root;
        }

        public final File a() {
            return this.f18459a;
        }

        public abstract File b();
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new FileTreeWalkIterator();
    }
}
