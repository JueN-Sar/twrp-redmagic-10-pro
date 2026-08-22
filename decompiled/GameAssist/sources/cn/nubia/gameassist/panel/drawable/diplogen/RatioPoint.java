package cn.nubia.gameassist.panel.drawable.diplogen;

import java.util.Objects;

/* loaded from: classes.dex */
public class RatioPoint {

    /* renamed from: a, reason: collision with root package name */
    public final float f6972a;

    /* renamed from: b, reason: collision with root package name */
    public final float f6973b;

    public RatioPoint(float f2, float f3) {
        this.f6972a = f2;
        this.f6973b = f3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RatioPoint ratioPoint = (RatioPoint) obj;
        return Float.compare(ratioPoint.f6972a, this.f6972a) == 0 && Float.compare(ratioPoint.f6973b, this.f6973b) == 0;
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(this.f6972a), Float.valueOf(this.f6973b));
    }
}
