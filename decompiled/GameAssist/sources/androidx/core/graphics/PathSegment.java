package androidx.core.graphics;

import android.graphics.PointF;

/* loaded from: classes.dex */
public final class PathSegment {

    /* renamed from: a, reason: collision with root package name */
    private final PointF f2929a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2930b;

    /* renamed from: c, reason: collision with root package name */
    private final PointF f2931c;

    /* renamed from: d, reason: collision with root package name */
    private final float f2932d;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        return Float.compare(this.f2930b, pathSegment.f2930b) == 0 && Float.compare(this.f2932d, pathSegment.f2932d) == 0 && this.f2929a.equals(pathSegment.f2929a) && this.f2931c.equals(pathSegment.f2931c);
    }

    public int hashCode() {
        int hashCode = this.f2929a.hashCode() * 31;
        float f2 = this.f2930b;
        int floatToIntBits = (((hashCode + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31) + this.f2931c.hashCode()) * 31;
        float f3 = this.f2932d;
        return floatToIntBits + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0);
    }

    public String toString() {
        return "PathSegment{start=" + this.f2929a + ", startFraction=" + this.f2930b + ", end=" + this.f2931c + ", endFraction=" + this.f2932d + '}';
    }
}
