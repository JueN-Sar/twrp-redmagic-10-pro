package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public class IntProgression implements Iterable<Integer>, KMappedMarker {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f18609j = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final int f18610c;

    /* renamed from: h, reason: collision with root package name */
    private final int f18611h;

    /* renamed from: i, reason: collision with root package name */
    private final int f18612i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final IntProgression a(int i2, int i3, int i4) {
            return new IntProgression(i2, i3, i4);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IntProgression(int i2, int i3, int i4) {
        if (i4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i4 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f18610c = i2;
        this.f18611h = ProgressionUtilKt.c(i2, i3, i4);
        this.f18612i = i4;
    }

    public boolean equals(Object obj) {
        if (obj instanceof IntProgression) {
            if (!isEmpty() || !((IntProgression) obj).isEmpty()) {
                IntProgression intProgression = (IntProgression) obj;
                if (this.f18610c != intProgression.f18610c || this.f18611h != intProgression.f18611h || this.f18612i != intProgression.f18612i) {
                }
            }
            return true;
        }
        return false;
    }

    public final int g() {
        return this.f18610c;
    }

    public final int h() {
        return this.f18611h;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.f18612i + (((this.f18610c * 31) + this.f18611h) * 31);
    }

    public final int i() {
        return this.f18612i;
    }

    public boolean isEmpty() {
        if (this.f18612i > 0) {
            if (this.f18610c <= this.f18611h) {
                return false;
            }
        } else if (this.f18610c >= this.f18611h) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public IntIterator iterator() {
        return new IntProgressionIterator(this.f18610c, this.f18611h, this.f18612i);
    }

    public String toString() {
        StringBuilder sb;
        int i2;
        if (this.f18612i > 0) {
            sb = new StringBuilder();
            sb.append(this.f18610c);
            sb.append("..");
            sb.append(this.f18611h);
            sb.append(" step ");
            i2 = this.f18612i;
        } else {
            sb = new StringBuilder();
            sb.append(this.f18610c);
            sb.append(" downTo ");
            sb.append(this.f18611h);
            sb.append(" step ");
            i2 = -this.f18612i;
        }
        sb.append(i2);
        return sb.toString();
    }
}
