package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
@WasExperimental
/* loaded from: classes2.dex */
public class ULongProgression implements Iterable<ULong>, KMappedMarker {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f18643j = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final long f18644c;

    /* renamed from: h, reason: collision with root package name */
    private final long f18645h;

    /* renamed from: i, reason: collision with root package name */
    private final long f18646i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ ULongProgression(long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3, j4);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ULongProgression) {
            if (!isEmpty() || !((ULongProgression) obj).isEmpty()) {
                ULongProgression uLongProgression = (ULongProgression) obj;
                if (this.f18644c != uLongProgression.f18644c || this.f18645h != uLongProgression.f18645h || this.f18646i != uLongProgression.f18646i) {
                }
            }
            return true;
        }
        return false;
    }

    public final long g() {
        return this.f18644c;
    }

    public final long h() {
        return this.f18645h;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j2 = this.f18644c;
        int d2 = ((int) ULong.d(j2 ^ ULong.d(j2 >>> 32))) * 31;
        long j3 = this.f18645h;
        int d3 = (d2 + ((int) ULong.d(j3 ^ ULong.d(j3 >>> 32)))) * 31;
        long j4 = this.f18646i;
        return ((int) ((j4 >>> 32) ^ j4)) + d3;
    }

    public boolean isEmpty() {
        long j2 = this.f18646i;
        int compareUnsigned = Long.compareUnsigned(this.f18644c, this.f18645h);
        if (j2 > 0) {
            if (compareUnsigned <= 0) {
                return false;
            }
        } else if (compareUnsigned >= 0) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator<ULong> iterator() {
        return new ULongProgressionIterator(this.f18644c, this.f18645h, this.f18646i, null);
    }

    public String toString() {
        StringBuilder sb;
        long j2;
        if (this.f18646i > 0) {
            sb = new StringBuilder();
            sb.append((Object) ULong.h(this.f18644c));
            sb.append("..");
            sb.append((Object) ULong.h(this.f18645h));
            sb.append(" step ");
            j2 = this.f18646i;
        } else {
            sb = new StringBuilder();
            sb.append((Object) ULong.h(this.f18644c));
            sb.append(" downTo ");
            sb.append((Object) ULong.h(this.f18645h));
            sb.append(" step ");
            j2 = -this.f18646i;
        }
        sb.append(j2);
        return sb.toString();
    }

    private ULongProgression(long j2, long j3, long j4) {
        if (j4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j4 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.f18644c = j2;
        this.f18645h = UProgressionUtilKt.c(j2, j3, j4);
        this.f18646i = j4;
    }
}
