package kotlin.ranges;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class ClosedFloatRange implements ClosedFloatingPointRange<Float> {

    /* renamed from: c, reason: collision with root package name */
    private final float f18603c;

    /* renamed from: h, reason: collision with root package name */
    private final float f18604h;

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float d() {
        return Float.valueOf(this.f18604h);
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Float b() {
        return Float.valueOf(this.f18603c);
    }

    public boolean e() {
        return this.f18603c > this.f18604h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ClosedFloatRange) {
            if (!e() || !((ClosedFloatRange) obj).e()) {
                ClosedFloatRange closedFloatRange = (ClosedFloatRange) obj;
                if (this.f18603c != closedFloatRange.f18603c || this.f18604h != closedFloatRange.f18604h) {
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
        return Float.hashCode(this.f18604h) + (Float.hashCode(this.f18603c) * 31);
    }

    public String toString() {
        return this.f18603c + ".." + this.f18604h;
    }
}
