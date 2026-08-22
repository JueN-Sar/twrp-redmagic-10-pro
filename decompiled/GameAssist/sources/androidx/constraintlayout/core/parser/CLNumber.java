package androidx.constraintlayout.core.parser;

/* loaded from: classes.dex */
public class CLNumber extends CLElement {

    /* renamed from: l, reason: collision with root package name */
    float f1899l;

    @Override // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CLNumber)) {
            return false;
        }
        float k2 = k();
        float k3 = ((CLNumber) obj).k();
        return (Float.isNaN(k2) && Float.isNaN(k3)) || k2 == k3;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        float f2 = this.f1899l;
        return hashCode + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public float k() {
        if (Float.isNaN(this.f1899l) && i()) {
            this.f1899l = Float.parseFloat(f());
        }
        return this.f1899l;
    }
}
