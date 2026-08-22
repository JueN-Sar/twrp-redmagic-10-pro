package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class Schlick extends Easing {

    /* renamed from: d, reason: collision with root package name */
    double f1825d;

    /* renamed from: e, reason: collision with root package name */
    double f1826e;

    Schlick(String str) {
        this.f1764a = str;
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(44, indexOf);
        this.f1825d = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
        int i2 = indexOf2 + 1;
        this.f1826e = Double.parseDouble(str.substring(i2, str.indexOf(44, i2)).trim());
    }

    private double d(double d2) {
        double d3 = this.f1826e;
        if (d2 < d3) {
            double d4 = this.f1825d;
            return ((d4 * d3) * d3) / ((((d3 - d2) * d4) + d2) * ((d4 * (d3 - d2)) + d2));
        }
        double d5 = this.f1825d;
        return (((d3 - 1.0d) * d5) * (d3 - 1.0d)) / (((((-d5) * (d3 - d2)) - d2) + 1.0d) * ((((-d5) * (d3 - d2)) - d2) + 1.0d));
    }

    private double e(double d2) {
        double d3 = this.f1826e;
        return d2 < d3 ? (d3 * d2) / (d2 + (this.f1825d * (d3 - d2))) : ((1.0d - d3) * (d2 - 1.0d)) / ((1.0d - d2) - (this.f1825d * (d3 - d2)));
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double a(double d2) {
        return e(d2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double b(double d2) {
        return d(d2);
    }
}
