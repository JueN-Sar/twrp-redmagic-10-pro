package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public abstract class CurveFit {

    static class Constant extends CurveFit {

        /* renamed from: a, reason: collision with root package name */
        double f1760a;

        /* renamed from: b, reason: collision with root package name */
        double[] f1761b;

        Constant(double d2, double[] dArr) {
            this.f1760a = d2;
            this.f1761b = dArr;
        }

        @Override // androidx.constraintlayout.core.motion.utils.CurveFit
        public double c(double d2, int i2) {
            return this.f1761b[i2];
        }

        @Override // androidx.constraintlayout.core.motion.utils.CurveFit
        public void d(double d2, double[] dArr) {
            double[] dArr2 = this.f1761b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // androidx.constraintlayout.core.motion.utils.CurveFit
        public void e(double d2, float[] fArr) {
            int i2 = 0;
            while (true) {
                double[] dArr = this.f1761b;
                if (i2 >= dArr.length) {
                    return;
                }
                fArr[i2] = (float) dArr[i2];
                i2++;
            }
        }

        @Override // androidx.constraintlayout.core.motion.utils.CurveFit
        public double f(double d2, int i2) {
            return 0.0d;
        }

        @Override // androidx.constraintlayout.core.motion.utils.CurveFit
        public void g(double d2, double[] dArr) {
            for (int i2 = 0; i2 < this.f1761b.length; i2++) {
                dArr[i2] = 0.0d;
            }
        }

        @Override // androidx.constraintlayout.core.motion.utils.CurveFit
        public double[] h() {
            return new double[]{this.f1760a};
        }
    }

    public static CurveFit a(int i2, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i2 = 2;
        }
        return i2 != 0 ? i2 != 2 ? new LinearCurveFit(dArr, dArr2) : new Constant(dArr[0], dArr2[0]) : new MonotonicCurveFit(dArr, dArr2);
    }

    public static CurveFit b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new ArcCurveFit(iArr, dArr, dArr2);
    }

    public abstract double c(double d2, int i2);

    public abstract void d(double d2, double[] dArr);

    public abstract void e(double d2, float[] fArr);

    public abstract double f(double d2, int i2);

    public abstract void g(double d2, double[] dArr);

    public abstract double[] h();
}
