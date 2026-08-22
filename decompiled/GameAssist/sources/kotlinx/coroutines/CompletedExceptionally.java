package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public class CompletedExceptionally {

    /* renamed from: b, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f18844b = AtomicIntegerFieldUpdater.newUpdater(CompletedExceptionally.class, "_handled");

    @NotNull
    private volatile /* synthetic */ int _handled;

    /* renamed from: a, reason: collision with root package name */
    public final Throwable f18845a;

    public CompletedExceptionally(Throwable th, boolean z) {
        this.f18845a = th;
        this._handled = z ? 1 : 0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public final boolean a() {
        return this._handled;
    }

    public final boolean b() {
        return f18844b.compareAndSet(this, 0, 1);
    }

    public String toString() {
        return DebugStringsKt.a(this) + '[' + this.f18845a + ']';
    }

    public /* synthetic */ CompletedExceptionally(Throwable th, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i2 & 2) != 0 ? false : z);
    }
}
