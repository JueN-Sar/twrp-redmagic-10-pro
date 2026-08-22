package kotlin.io.path;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class ExceptionsCollector {

    /* renamed from: a, reason: collision with root package name */
    private final int f18500a;

    /* renamed from: b, reason: collision with root package name */
    private int f18501b;

    /* renamed from: c, reason: collision with root package name */
    private final List f18502c;

    /* renamed from: d, reason: collision with root package name */
    private Path f18503d;

    public ExceptionsCollector(int i2) {
        this.f18500a = i2;
        this.f18502c = new ArrayList();
    }

    public final void a(Exception exception) {
        Intrinsics.e(exception, "exception");
        this.f18501b++;
        if (this.f18502c.size() < this.f18500a) {
            if (this.f18503d != null) {
                Throwable initCause = new FileSystemException(String.valueOf(this.f18503d)).initCause(exception);
                Intrinsics.c(initCause, "null cannot be cast to non-null type java.nio.file.FileSystemException");
                exception = (FileSystemException) initCause;
            }
            this.f18502c.add(exception);
        }
    }

    public final void b(Path name) {
        Intrinsics.e(name, "name");
        Path path = this.f18503d;
        this.f18503d = path != null ? path.resolve(name) : null;
    }

    public final void c(Path name) {
        Intrinsics.e(name, "name");
        Path path = this.f18503d;
        if (!Intrinsics.a(name, path != null ? path.getFileName() : null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Path path2 = this.f18503d;
        this.f18503d = path2 != null ? path2.getParent() : null;
    }

    public final List d() {
        return this.f18502c;
    }

    public final int e() {
        return this.f18501b;
    }

    public final void f(Path path) {
        this.f18503d = path;
    }

    public /* synthetic */ ExceptionsCollector(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 64 : i2);
    }
}
