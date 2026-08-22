package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
final class SemaphoreSegment extends Segment<SemaphoreSegment> {

    /* renamed from: e, reason: collision with root package name */
    /* synthetic */ AtomicReferenceArray f19532e;

    public SemaphoreSegment(long j2, SemaphoreSegment semaphoreSegment, int i2) {
        super(j2, semaphoreSegment, i2);
        int i3;
        i3 = SemaphoreKt.f19531f;
        this.f19532e = new AtomicReferenceArray(i3);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int n() {
        int i2;
        i2 = SemaphoreKt.f19531f;
        return i2;
    }

    public final void q(int i2) {
        Symbol symbol;
        symbol = SemaphoreKt.f19530e;
        this.f19532e.set(i2, symbol);
        o();
    }

    public String toString() {
        return "SemaphoreSegment[id=" + m() + ", hashCode=" + hashCode() + ']';
    }
}
