package kotlin.ranges;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class OpenEndDoubleRange implements OpenEndRange<Double> {

    /* renamed from: c, reason: collision with root package name */
    private final double f18629c;

    /* renamed from: h, reason: collision with root package name */
    private final double f18630h;

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Double f() {
        return Double.valueOf(this.f18630h);
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Double b() {
        return Double.valueOf(this.f18629c);
    }

    public boolean d() {
        return this.f18629c >= this.f18630h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof OpenEndDoubleRange) {
            if (!d() || !((OpenEndDoubleRange) obj).d()) {
                OpenEndDoubleRange openEndDoubleRange = (OpenEndDoubleRange) obj;
                if (this.f18629c != openEndDoubleRange.f18629c || this.f18630h != openEndDoubleRange.f18630h) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (d()) {
            return -1;
        }
        return Double.hashCode(this.f18630h) + (Double.hashCode(this.f18629c) * 31);
    }

    public String toString() {
        return this.f18629c + "..<" + this.f18630h;
    }
}
