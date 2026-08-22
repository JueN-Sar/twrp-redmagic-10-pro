package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public class LongProgression implements Iterable<Long>, KMappedMarker {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f18619j = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final long f18620c;

    /* renamed from: h, reason: collision with root package name */
    private final long f18621h;

    /* renamed from: i, reason: collision with root package name */
    private final long f18622i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LongProgression(long j2, long j3, long j4) {
        if (j4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j4 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.f18620c = j2;
        this.f18621h = ProgressionUtilKt.d(j2, j3, j4);
        this.f18622i = j4;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LongProgression) {
            if (!isEmpty() || !((LongProgression) obj).isEmpty()) {
                LongProgression longProgression = (LongProgression) obj;
                if (this.f18620c != longProgression.f18620c || this.f18621h != longProgression.f18621h || this.f18622i != longProgression.f18622i) {
                }
            }
            return true;
        }
        return false;
    }

    public final long g() {
        return this.f18620c;
    }

    public final long h() {
        return this.f18621h;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j2 = 31;
        long j3 = this.f18620c;
        long j4 = this.f18621h;
        long j5 = j2 * (((j3 ^ (j3 >>> 32)) * j2) + (j4 ^ (j4 >>> 32)));
        long j6 = this.f18622i;
        return (int) (j5 + (j6 ^ (j6 >>> 32)));
    }

    @Override // java.lang.Iterable
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public LongIterator iterator() {
        return new LongProgressionIterator(this.f18620c, this.f18621h, this.f18622i);
    }

    public boolean isEmpty() {
        long j2 = this.f18622i;
        long j3 = this.f18620c;
        long j4 = this.f18621h;
        if (j2 > 0) {
            if (j3 <= j4) {
                return false;
            }
        } else if (j3 >= j4) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb;
        long j2;
        if (this.f18622i > 0) {
            sb = new StringBuilder();
            sb.append(this.f18620c);
            sb.append("..");
            sb.append(this.f18621h);
            sb.append(" step ");
            j2 = this.f18622i;
        } else {
            sb = new StringBuilder();
            sb.append(this.f18620c);
            sb.append(" downTo ");
            sb.append(this.f18621h);
            sb.append(" step ");
            j2 = -this.f18622i;
        }
        sb.append(j2);
        return sb.toString();
    }
}
