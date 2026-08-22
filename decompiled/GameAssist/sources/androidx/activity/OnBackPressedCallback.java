package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class OnBackPressedCallback {

    /* renamed from: a, reason: collision with root package name */
    private boolean f55a;

    /* renamed from: b, reason: collision with root package name */
    private final CopyOnWriteArrayList f56b = new CopyOnWriteArrayList();

    /* renamed from: c, reason: collision with root package name */
    private Function0 f57c;

    public OnBackPressedCallback(boolean z) {
        this.f55a = z;
    }

    public final void a(Cancellable cancellable) {
        Intrinsics.e(cancellable, "cancellable");
        this.f56b.add(cancellable);
    }

    public final Function0 b() {
        return this.f57c;
    }

    public void c() {
    }

    public abstract void d();

    public void e(BackEventCompat backEvent) {
        Intrinsics.e(backEvent, "backEvent");
    }

    public void f(BackEventCompat backEvent) {
        Intrinsics.e(backEvent, "backEvent");
    }

    public final boolean g() {
        return this.f55a;
    }

    public final void h() {
        Iterator it = this.f56b.iterator();
        while (it.hasNext()) {
            ((Cancellable) it.next()).cancel();
        }
    }

    public final void i(Cancellable cancellable) {
        Intrinsics.e(cancellable, "cancellable");
        this.f56b.remove(cancellable);
    }

    public final void j(boolean z) {
        this.f55a = z;
        Function0 function0 = this.f57c;
        if (function0 != null) {
            function0.a();
        }
    }

    public final void k(Function0 function0) {
        this.f57c = function0;
    }
}
