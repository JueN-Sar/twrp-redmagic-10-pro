package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {

    /* renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19400d = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");

    /* renamed from: c, reason: collision with root package name */
    private final long f19401c;

    @NotNull
    private volatile /* synthetic */ int cleanedAndPointers;

    public Segment(long j2, Segment segment, int i2) {
        super(segment);
        this.f19401c = j2;
        this.cleanedAndPointers = i2 << 16;
    }

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean g() {
        return this.cleanedAndPointers == n() && !i();
    }

    public final boolean l() {
        return f19400d.addAndGet(this, -65536) == n() && !i();
    }

    public final long m() {
        return this.f19401c;
    }

    public abstract int n();

    public final void o() {
        if (f19400d.incrementAndGet(this) != n() || i()) {
            return;
        }
        j();
    }

    public final boolean p() {
        int i2;
        do {
            i2 = this.cleanedAndPointers;
            if (i2 == n() && !i()) {
                return false;
            }
        } while (!f19400d.compareAndSet(this, i2, 65536 + i2));
        return true;
    }
}
