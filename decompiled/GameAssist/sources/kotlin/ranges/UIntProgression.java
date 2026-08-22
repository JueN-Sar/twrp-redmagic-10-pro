package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
@WasExperimental
/* loaded from: classes2.dex */
public class UIntProgression implements Iterable<UInt>, KMappedMarker {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f18633j = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final int f18634c;

    /* renamed from: h, reason: collision with root package name */
    private final int f18635h;

    /* renamed from: i, reason: collision with root package name */
    private final int f18636i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ UIntProgression(int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (obj instanceof UIntProgression) {
            if (!isEmpty() || !((UIntProgression) obj).isEmpty()) {
                UIntProgression uIntProgression = (UIntProgression) obj;
                if (this.f18634c != uIntProgression.f18634c || this.f18635h != uIntProgression.f18635h || this.f18636i != uIntProgression.f18636i) {
                }
            }
            return true;
        }
        return false;
    }

    public final int g() {
        return this.f18634c;
    }

    public final int h() {
        return this.f18635h;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.f18636i + (((this.f18634c * 31) + this.f18635h) * 31);
    }

    public boolean isEmpty() {
        if (this.f18636i > 0) {
            if (Integer.compareUnsigned(this.f18634c, this.f18635h) <= 0) {
                return false;
            }
        } else if (Integer.compareUnsigned(this.f18634c, this.f18635h) >= 0) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator<UInt> iterator() {
        return new UIntProgressionIterator(this.f18634c, this.f18635h, this.f18636i, null);
    }

    public String toString() {
        StringBuilder sb;
        int i2;
        if (this.f18636i > 0) {
            sb = new StringBuilder();
            sb.append((Object) UInt.h(this.f18634c));
            sb.append("..");
            sb.append((Object) UInt.h(this.f18635h));
            sb.append(" step ");
            i2 = this.f18636i;
        } else {
            sb = new StringBuilder();
            sb.append((Object) UInt.h(this.f18634c));
            sb.append(" downTo ");
            sb.append((Object) UInt.h(this.f18635h));
            sb.append(" step ");
            i2 = -this.f18636i;
        }
        sb.append(i2);
        return sb.toString();
    }

    private UIntProgression(int i2, int i3, int i4) {
        if (i4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i4 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f18634c = i2;
        this.f18635h = UProgressionUtilKt.d(i2, i3, i4);
        this.f18636i = i4;
    }
}
