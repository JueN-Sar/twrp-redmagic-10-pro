package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public class CharProgression implements Iterable<Character>, KMappedMarker {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f18591j = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final char f18592c;

    /* renamed from: h, reason: collision with root package name */
    private final char f18593h;

    /* renamed from: i, reason: collision with root package name */
    private final int f18594i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CharProgression(char c2, char c3, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i2 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f18592c = c2;
        this.f18593h = (char) ProgressionUtilKt.c(c2, c3, i2);
        this.f18594i = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CharProgression) {
            if (!isEmpty() || !((CharProgression) obj).isEmpty()) {
                CharProgression charProgression = (CharProgression) obj;
                if (this.f18592c != charProgression.f18592c || this.f18593h != charProgression.f18593h || this.f18594i != charProgression.f18594i) {
                }
            }
            return true;
        }
        return false;
    }

    public final char g() {
        return this.f18592c;
    }

    public final char h() {
        return this.f18593h;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.f18594i + (((this.f18592c * 31) + this.f18593h) * 31);
    }

    @Override // java.lang.Iterable
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public CharIterator iterator() {
        return new CharProgressionIterator(this.f18592c, this.f18593h, this.f18594i);
    }

    public boolean isEmpty() {
        if (this.f18594i > 0) {
            if (Intrinsics.f(this.f18592c, this.f18593h) <= 0) {
                return false;
            }
        } else if (Intrinsics.f(this.f18592c, this.f18593h) >= 0) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb;
        int i2;
        if (this.f18594i > 0) {
            sb = new StringBuilder();
            sb.append(this.f18592c);
            sb.append("..");
            sb.append(this.f18593h);
            sb.append(" step ");
            i2 = this.f18594i;
        } else {
            sb = new StringBuilder();
            sb.append(this.f18592c);
            sb.append(" downTo ");
            sb.append(this.f18593h);
            sb.append(" step ");
            i2 = -this.f18594i;
        }
        sb.append(i2);
        return sb.toString();
    }
}
