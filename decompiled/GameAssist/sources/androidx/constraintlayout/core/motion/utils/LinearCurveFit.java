package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class LinearCurveFit extends CurveFit {

    /* renamed from: a, reason: collision with root package name */
    private double[] f1807a;

    /* renamed from: b, reason: collision with root package name */
    private double[][] f1808b;

    /* renamed from: c, reason: collision with root package name */
    private double f1809c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1810d = true;

    /* renamed from: e, reason: collision with root package name */
    double[] f1811e;

    public LinearCurveFit(double[] dArr, double[][] dArr2) {
        this.f1809c = Double.NaN;
        int length = dArr2[0].length;
        this.f1811e = new double[length];
        this.f1807a = dArr;
        this.f1808b = dArr2;
        if (length <= 2) {
            return;
        }
        int i2 = 0;
        double d2 = 0.0d;
        while (true) {
            double d3 = d2;
            if (i2 >= dArr.length) {
                this.f1809c = 0.0d;
                return;
            }
            double d4 = dArr2[i2][0];
            if (i2 > 0) {
                Math.hypot(d4 - d2, d4 - d3);
            }
            i2++;
            d2 = d4;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double c(double d2, int i2) {
        double[] dArr = this.f1807a;
        int length = dArr.length;
        int i3 = 0;
        if (this.f1810d) {
            double d3 = dArr[0];
            if (d2 <= d3) {
                return this.f1808b[0][i2] + ((d2 - d3) * f(d3, i2));
            }
            int i4 = length - 1;
            double d4 = dArr[i4];
            if (d2 >= d4) {
                return this.f1808b[i4][i2] + ((d2 - d4) * f(d4, i2));
            }
        } else {
            if (d2 <= dArr[0]) {
                return this.f1808b[0][i2];
            }
            int i5 = length - 1;
            if (d2 >= dArr[i5]) {
                return this.f1808b[i5][i2];
            }
        }
        while (i3 < length - 1) {
            double[] dArr2 = this.f1807a;
            double d5 = dArr2[i3];
            if (d2 == d5) {
                return this.f1808b[i3][i2];
            }
            int i6 = i3 + 1;
            double d6 = dArr2[i6];
            if (d2 < d6) {
                double d7 = (d2 - d5) / (d6 - d5);
                double[][] dArr3 = this.f1808b;
                return (dArr3[i3][i2] * (1.0d - d7)) + (dArr3[i6][i2] * d7);
            }
            i3 = i6;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void d(double d2, double[] dArr) {
        double[] dArr2 = this.f1807a;
        int length = dArr2.length;
        int i2 = 0;
        int length2 = this.f1808b[0].length;
        if (this.f1810d) {
            double d3 = dArr2[0];
            if (d2 <= d3) {
                g(d3, this.f1811e);
                for (int i3 = 0; i3 < length2; i3++) {
                    dArr[i3] = this.f1808b[0][i3] + ((d2 - this.f1807a[0]) * this.f1811e[i3]);
                }
                return;
            }
            int i4 = length - 1;
            double d4 = dArr2[i4];
            if (d2 >= d4) {
                g(d4, this.f1811e);
                while (i2 < length2) {
                    dArr[i2] = this.f1808b[i4][i2] + ((d2 - this.f1807a[i4]) * this.f1811e[i2]);
                    i2++;
                }
                return;
            }
        } else {
            if (d2 <= dArr2[0]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    dArr[i5] = this.f1808b[0][i5];
                }
                return;
            }
            int i6 = length - 1;
            if (d2 >= dArr2[i6]) {
                while (i2 < length2) {
                    dArr[i2] = this.f1808b[i6][i2];
                    i2++;
                }
                return;
            }
        }
        int i7 = 0;
        while (i7 < length - 1) {
            if (d2 == this.f1807a[i7]) {
                for (int i8 = 0; i8 < length2; i8++) {
                    dArr[i8] = this.f1808b[i7][i8];
                }
            }
            double[] dArr3 = this.f1807a;
            int i9 = i7 + 1;
            double d5 = dArr3[i9];
            if (d2 < d5) {
                double d6 = dArr3[i7];
                double d7 = (d2 - d6) / (d5 - d6);
                while (i2 < length2) {
                    double[][] dArr4 = this.f1808b;
                    dArr[i2] = (dArr4[i7][i2] * (1.0d - d7)) + (dArr4[i9][i2] * d7);
                    i2++;
                }
                return;
            }
            i7 = i9;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void e(double d2, float[] fArr) {
        double[] dArr = this.f1807a;
        int length = dArr.length;
        int i2 = 0;
        int length2 = this.f1808b[0].length;
        if (this.f1810d) {
            double d3 = dArr[0];
            if (d2 <= d3) {
                g(d3, this.f1811e);
                for (int i3 = 0; i3 < length2; i3++) {
                    fArr[i3] = (float) (this.f1808b[0][i3] + ((d2 - this.f1807a[0]) * this.f1811e[i3]));
                }
                return;
            }
            int i4 = length - 1;
            double d4 = dArr[i4];
            if (d2 >= d4) {
                g(d4, this.f1811e);
                while (i2 < length2) {
                    fArr[i2] = (float) (this.f1808b[i4][i2] + ((d2 - this.f1807a[i4]) * this.f1811e[i2]));
                    i2++;
                }
                return;
            }
        } else {
            if (d2 <= dArr[0]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    fArr[i5] = (float) this.f1808b[0][i5];
                }
                return;
            }
            int i6 = length - 1;
            if (d2 >= dArr[i6]) {
                while (i2 < length2) {
                    fArr[i2] = (float) this.f1808b[i6][i2];
                    i2++;
                }
                return;
            }
        }
        int i7 = 0;
        while (i7 < length - 1) {
            if (d2 == this.f1807a[i7]) {
                for (int i8 = 0; i8 < length2; i8++) {
                    fArr[i8] = (float) this.f1808b[i7][i8];
                }
            }
            double[] dArr2 = this.f1807a;
            int i9 = i7 + 1;
            double d5 = dArr2[i9];
            if (d2 < d5) {
                double d6 = dArr2[i7];
                double d7 = (d2 - d6) / (d5 - d6);
                while (i2 < length2) {
                    double[][] dArr3 = this.f1808b;
                    fArr[i2] = (float) ((dArr3[i7][i2] * (1.0d - d7)) + (dArr3[i9][i2] * d7));
                    i2++;
                }
                return;
            }
            i7 = i9;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0012, code lost:
    
        if (r8 >= r3) goto L4;
     */
    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public double f(double r8, int r10) {
        /*
            r7 = this;
            double[] r0 = r7.f1807a
            int r1 = r0.length
            r2 = 0
            r3 = r0[r2]
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 >= 0) goto Lc
        La:
            r8 = r3
            goto L15
        Lc:
            int r3 = r1 + (-1)
            r3 = r0[r3]
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 < 0) goto L15
            goto La
        L15:
            int r0 = r1 + (-1)
            if (r2 >= r0) goto L35
            double[] r0 = r7.f1807a
            int r3 = r2 + 1
            r4 = r0[r3]
            int r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r6 > 0) goto L33
            r8 = r0[r2]
            double r4 = r4 - r8
            double[][] r7 = r7.f1808b
            r8 = r7[r2]
            r8 = r8[r10]
            r7 = r7[r3]
            r0 = r7[r10]
            double r0 = r0 - r8
            double r0 = r0 / r4
            return r0
        L33:
            r2 = r3
            goto L15
        L35:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.LinearCurveFit.f(double, int):double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0017, code lost:
    
        if (r11 >= r4) goto L4;
     */
    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(double r11, double[] r13) {
        /*
            r10 = this;
            double[] r0 = r10.f1807a
            int r1 = r0.length
            double[][] r2 = r10.f1808b
            r3 = 0
            r2 = r2[r3]
            int r2 = r2.length
            r4 = r0[r3]
            int r6 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r6 > 0) goto L11
        Lf:
            r11 = r4
            goto L1a
        L11:
            int r4 = r1 + (-1)
            r4 = r0[r4]
            int r0 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r0 < 0) goto L1a
            goto Lf
        L1a:
            r0 = r3
        L1b:
            int r4 = r1 + (-1)
            if (r0 >= r4) goto L41
            double[] r4 = r10.f1807a
            int r5 = r0 + 1
            r6 = r4[r5]
            int r8 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r8 > 0) goto L3f
            r11 = r4[r0]
            double r6 = r6 - r11
        L2c:
            if (r3 >= r2) goto L41
            double[][] r11 = r10.f1808b
            r12 = r11[r0]
            r8 = r12[r3]
            r11 = r11[r5]
            r11 = r11[r3]
            double r11 = r11 - r8
            double r11 = r11 / r6
            r13[r3] = r11
            int r3 = r3 + 1
            goto L2c
        L3f:
            r0 = r5
            goto L1b
        L41:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.LinearCurveFit.g(double, double[]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] h() {
        return this.f1807a;
    }
}
