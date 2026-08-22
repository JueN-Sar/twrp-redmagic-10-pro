package kotlin.io.path;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@ExperimentalPathApi
/* loaded from: classes2.dex */
public final class FileVisitorBuilderImpl implements FileVisitorBuilder {

    /* renamed from: a, reason: collision with root package name */
    private Function2 f18504a;

    /* renamed from: b, reason: collision with root package name */
    private Function2 f18505b;

    /* renamed from: c, reason: collision with root package name */
    private Function2 f18506c;

    /* renamed from: d, reason: collision with root package name */
    private Function2 f18507d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f18508e;

    private final void e() {
        if (this.f18508e) {
            throw new IllegalStateException("This builder was already built");
        }
    }

    private final void f(Object obj, String str) {
        if (obj == null) {
            return;
        }
        throw new IllegalStateException(str + " was already defined");
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void a(Function2 function) {
        Intrinsics.e(function, "function");
        e();
        f(this.f18505b, "onVisitFile");
        this.f18505b = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void b(Function2 function) {
        Intrinsics.e(function, "function");
        e();
        f(this.f18504a, "onPreVisitDirectory");
        this.f18504a = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void c(Function2 function) {
        Intrinsics.e(function, "function");
        e();
        f(this.f18507d, "onPostVisitDirectory");
        this.f18507d = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void d(Function2 function) {
        Intrinsics.e(function, "function");
        e();
        f(this.f18506c, "onVisitFileFailed");
        this.f18506c = function;
    }
}
