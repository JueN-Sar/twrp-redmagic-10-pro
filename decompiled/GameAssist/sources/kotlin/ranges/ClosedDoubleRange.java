package kotlin.ranges;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class ClosedDoubleRange implements ClosedFloatingPointRange<Double> {

    /* renamed from: c, reason: collision with root package name */
    private final double f18601c;

    /* renamed from: h, reason: collision with root package name */
    private final double f18602h;

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Double d() {
        return Double.valueOf(this.f18602h);
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Double b() {
        return Double.valueOf(this.f18601c);
    }

    public boolean e() {
        return this.f18601c > this.f18602h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ClosedDoubleRange) {
            if (!e() || !((ClosedDoubleRange) obj).e()) {
                ClosedDoubleRange closedDoubleRange = (ClosedDoubleRange) obj;
                if (this.f18601c != closedDoubleRange.f18601c || this.f18602h != closedDoubleRange.f18602h) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (e()) {
            return -1;
        }
        return Double.hashCode(this.f18602h) + (Double.hashCode(this.f18601c) * 31);
    }

    public String toString() {
        return this.f18601c + ".." + this.f18602h;
    }
}
