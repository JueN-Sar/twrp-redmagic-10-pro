package kotlin.ranges;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class OpenEndFloatRange implements OpenEndRange<Float> {

    /* renamed from: c, reason: collision with root package name */
    private final float f18631c;

    /* renamed from: h, reason: collision with root package name */
    private final float f18632h;

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float f() {
        return Float.valueOf(this.f18632h);
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Float b() {
        return Float.valueOf(this.f18631c);
    }

    public boolean d() {
        return this.f18631c >= this.f18632h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof OpenEndFloatRange) {
            if (!d() || !((OpenEndFloatRange) obj).d()) {
                OpenEndFloatRange openEndFloatRange = (OpenEndFloatRange) obj;
                if (this.f18631c != openEndFloatRange.f18631c || this.f18632h != openEndFloatRange.f18632h) {
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
        return Float.hashCode(this.f18632h) + (Float.hashCode(this.f18631c) * 31);
    }

    public String toString() {
        return this.f18631c + "..<" + this.f18632h;
    }
}
