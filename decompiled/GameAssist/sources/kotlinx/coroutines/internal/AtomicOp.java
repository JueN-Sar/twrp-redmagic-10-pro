package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public abstract class AtomicOp<T> extends OpDescriptor {

    /* renamed from: a, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19341a = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");

    @NotNull
    private volatile /* synthetic */ Object _consensus = AtomicKt.f19339a;

    @Override // kotlinx.coroutines.internal.OpDescriptor
    public AtomicOp a() {
        return this;
    }

    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object c(Object obj) {
        Object obj2 = this._consensus;
        if (obj2 == AtomicKt.f19339a) {
            obj2 = e(i(obj));
        }
        d(obj, obj2);
        return obj2;
    }

    public abstract void d(Object obj, Object obj2);

    public final Object e(Object obj) {
        Object obj2 = this._consensus;
        Object obj3 = AtomicKt.f19339a;
        return obj2 != obj3 ? obj2 : f19341a.compareAndSet(this, obj3, obj) ? obj : this._consensus;
    }

    public final Object f() {
        return this._consensus;
    }

    public long g() {
        return 0L;
    }

    public final boolean h() {
        return this._consensus != AtomicKt.f19339a;
    }

    public abstract Object i(Object obj);
}
