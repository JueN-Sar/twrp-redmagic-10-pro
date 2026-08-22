package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {

    /* renamed from: a, reason: collision with root package name */
    private final double[] f1738a;

    /* renamed from: b, reason: collision with root package name */
    Arc[] f1739b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1740c = true;

    private static class Arc {

        /* renamed from: s, reason: collision with root package name */
        private static double[] f1741s = new double[91];

        /* renamed from: a, reason: collision with root package name */
        double[] f1742a;

        /* renamed from: b, reason: collision with root package name */
        double f1743b;

        /* renamed from: c, reason: collision with root package name */
        double f1744c;

        /* renamed from: d, reason: collision with root package name */
        double f1745d;

        /* renamed from: e, reason: collision with root package name */
        double f1746e;

        /* renamed from: f, reason: collision with root package name */
        double f1747f;

        /* renamed from: g, reason: collision with root package name */
        double f1748g;

        /* renamed from: h, reason: collision with root package name */
        double f1749h;

        /* renamed from: i, reason: collision with root package name */
        double f1750i;

        /* renamed from: j, reason: collision with root package name */
        double f1751j;

        /* renamed from: k, reason: collision with root package name */
        double f1752k;

        /* renamed from: l, reason: collision with root package name */
        double f1753l;

        /* renamed from: m, reason: collision with root package name */
        double f1754m;

        /* renamed from: n, reason: collision with root package name */
        double f1755n;

        /* renamed from: o, reason: collision with root package name */
        double f1756o;

        /* renamed from: p, reason: collision with root package name */
        double f1757p;

        /* renamed from: q, reason: collision with root package name */
        boolean f1758q;

        /* renamed from: r, reason: collision with root package name */
        boolean f1759r;

        Arc(int i2, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.f1759r = false;
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (i2 == 1) {
                this.f1758q = true;
            } else if (i2 == 4) {
                this.f1758q = d9 > 0.0d;
            } else if (i2 != 5) {
                this.f1758q = false;
            } else {
                this.f1758q = d9 < 0.0d;
            }
            this.f1744c = d2;
            this.f1745d = d3;
            this.f1750i = 1.0d / (d3 - d2);
            if (3 == i2) {
                this.f1759r = true;
            }
            if (!this.f1759r && Math.abs(d8) >= 0.001d && Math.abs(d9) >= 0.001d) {
                this.f1742a = new double[101];
                boolean z = this.f1758q;
                this.f1751j = d8 * (z ? -1 : 1);
                this.f1752k = d9 * (z ? 1 : -1);
                this.f1753l = z ? d6 : d4;
                this.f1754m = z ? d5 : d7;
                a(d4, d5, d6, d7);
                this.f1755n = this.f1743b * this.f1750i;
                return;
            }
            this.f1759r = true;
            this.f1746e = d4;
            this.f1747f = d6;
            this.f1748g = d5;
            this.f1749h = d7;
            double hypot = Math.hypot(d9, d8);
            this.f1743b = hypot;
            this.f1755n = hypot * this.f1750i;
            double d10 = this.f1745d;
            double d11 = this.f1744c;
            this.f1753l = d8 / (d10 - d11);
            this.f1754m = d9 / (d10 - d11);
        }

        private void a(double d2, double d3, double d4, double d5) {
            double d6;
            double d7 = d4 - d2;
            double d8 = d3 - d5;
            int i2 = 0;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            while (true) {
                if (i2 >= f1741s.length) {
                    break;
                }
                double d12 = d9;
                double radians = Math.toRadians((i2 * 90.0d) / (r15.length - 1));
                double sin = Math.sin(radians) * d7;
                double cos = Math.cos(radians) * d8;
                if (i2 > 0) {
                    d6 = Math.hypot(sin - d10, cos - d11) + d12;
                    f1741s[i2] = d6;
                } else {
                    d6 = d12;
                }
                i2++;
                d11 = cos;
                d9 = d6;
                d10 = sin;
            }
            double d13 = d9;
            this.f1743b = d13;
            int i3 = 0;
            while (true) {
                double[] dArr = f1741s;
                if (i3 >= dArr.length) {
                    break;
                }
                dArr[i3] = dArr[i3] / d13;
                i3++;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.f1742a.length) {
                    return;
                }
                double length = i4 / (r1.length - 1);
                int binarySearch = Arrays.binarySearch(f1741s, length);
                if (binarySearch >= 0) {
                    this.f1742a[i4] = binarySearch / (f1741s.length - 1);
                } else if (binarySearch == -1) {
                    this.f1742a[i4] = 0.0d;
                } else {
                    int i5 = -binarySearch;
                    int i6 = i5 - 2;
                    double[] dArr2 = f1741s;
                    double d14 = dArr2[i6];
                    this.f1742a[i4] = (i6 + ((length - d14) / (dArr2[i5 - 1] - d14))) / (dArr2.length - 1);
                }
                i4++;
            }
        }

        double b() {
            double d2 = this.f1751j * this.f1757p;
            double hypot = this.f1755n / Math.hypot(d2, (-this.f1752k) * this.f1756o);
            if (this.f1758q) {
                d2 = -d2;
            }
            return d2 * hypot;
        }

        double c() {
            double d2 = this.f1751j * this.f1757p;
            double d3 = (-this.f1752k) * this.f1756o;
            double hypot = this.f1755n / Math.hypot(d2, d3);
            return this.f1758q ? (-d3) * hypot : d3 * hypot;
        }

        public double d(double d2) {
            return this.f1753l;
        }

        public double e(double d2) {
            return this.f1754m;
        }

        public double f(double d2) {
            double d3 = (d2 - this.f1744c) * this.f1750i;
            double d4 = this.f1746e;
            return d4 + (d3 * (this.f1747f - d4));
        }

        public double g(double d2) {
            double d3 = (d2 - this.f1744c) * this.f1750i;
            double d4 = this.f1748g;
            return d4 + (d3 * (this.f1749h - d4));
        }

        double h() {
            return this.f1753l + (this.f1751j * this.f1756o);
        }

        double i() {
            return this.f1754m + (this.f1752k * this.f1757p);
        }

        double j(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f1742a;
            double length = d2 * (dArr.length - 1);
            int i2 = (int) length;
            double d3 = length - i2;
            double d4 = dArr[i2];
            return d4 + (d3 * (dArr[i2 + 1] - d4));
        }

        void k(double d2) {
            double j2 = j((this.f1758q ? this.f1745d - d2 : d2 - this.f1744c) * this.f1750i) * 1.5707963267948966d;
            this.f1756o = Math.sin(j2);
            this.f1757p = Math.cos(j2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        if (r5 == 1) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcCurveFit(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r2 = 1
            r0.f1740c = r2
            r0.f1738a = r1
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r3 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit.Arc[r3]
            r0.f1739b = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r7 = r0.f1739b
            int r8 = r7.length
            if (r4 >= r8) goto L58
            r8 = r25[r4]
            r9 = 3
            if (r8 == 0) goto L2e
            if (r8 == r2) goto L37
            r10 = 2
            if (r8 == r10) goto L35
            if (r8 == r9) goto L30
            r9 = 4
            if (r8 == r9) goto L2e
            r9 = 5
            if (r8 == r9) goto L2e
            goto L39
        L2e:
            r6 = r9
            goto L39
        L30:
            if (r5 != r2) goto L37
            goto L35
        L33:
            r6 = r5
            goto L39
        L35:
            r5 = r10
            goto L33
        L37:
            r5 = r2
            goto L33
        L39:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc r22 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r3]
            r16 = r8[r2]
            r8 = r27[r23]
            r18 = r8[r3]
            r20 = r8[r2]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L16
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double c(double d2, int i2) {
        double g2;
        double e2;
        double i3;
        double c2;
        double g3;
        double e3;
        int i4 = 0;
        if (this.f1740c) {
            Arc[] arcArr = this.f1739b;
            Arc arc = arcArr[0];
            double d3 = arc.f1744c;
            if (d2 < d3) {
                double d4 = d2 - d3;
                if (arc.f1759r) {
                    if (i2 == 0) {
                        g3 = arc.f(d3);
                        e3 = this.f1739b[0].d(d3);
                    } else {
                        g3 = arc.g(d3);
                        e3 = this.f1739b[0].e(d3);
                    }
                    return g3 + (d4 * e3);
                }
                arc.k(d3);
                if (i2 == 0) {
                    i3 = this.f1739b[0].h();
                    c2 = this.f1739b[0].b();
                } else {
                    i3 = this.f1739b[0].i();
                    c2 = this.f1739b[0].c();
                }
                return i3 + (d4 * c2);
            }
            if (d2 > arcArr[arcArr.length - 1].f1745d) {
                double d5 = arcArr[arcArr.length - 1].f1745d;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                if (i2 == 0) {
                    g2 = arcArr[length].f(d5);
                    e2 = this.f1739b[length].d(d5);
                } else {
                    g2 = arcArr[length].g(d5);
                    e2 = this.f1739b[length].e(d5);
                }
                return g2 + (d6 * e2);
            }
        } else {
            Arc[] arcArr2 = this.f1739b;
            double d7 = arcArr2[0].f1744c;
            if (d2 < d7) {
                d2 = d7;
            } else if (d2 > arcArr2[arcArr2.length - 1].f1745d) {
                d2 = arcArr2[arcArr2.length - 1].f1745d;
            }
        }
        while (true) {
            Arc[] arcArr3 = this.f1739b;
            if (i4 >= arcArr3.length) {
                return Double.NaN;
            }
            Arc arc2 = arcArr3[i4];
            if (d2 <= arc2.f1745d) {
                if (arc2.f1759r) {
                    return i2 == 0 ? arc2.f(d2) : arc2.g(d2);
                }
                arc2.k(d2);
                return i2 == 0 ? this.f1739b[i4].h() : this.f1739b[i4].i();
            }
            i4++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void d(double d2, double[] dArr) {
        if (this.f1740c) {
            Arc[] arcArr = this.f1739b;
            Arc arc = arcArr[0];
            double d3 = arc.f1744c;
            if (d2 < d3) {
                double d4 = d2 - d3;
                if (arc.f1759r) {
                    dArr[0] = arc.f(d3) + (this.f1739b[0].d(d3) * d4);
                    dArr[1] = this.f1739b[0].g(d3) + (d4 * this.f1739b[0].e(d3));
                    return;
                } else {
                    arc.k(d3);
                    dArr[0] = this.f1739b[0].h() + (this.f1739b[0].b() * d4);
                    dArr[1] = this.f1739b[0].i() + (d4 * this.f1739b[0].c());
                    return;
                }
            }
            if (d2 > arcArr[arcArr.length - 1].f1745d) {
                double d5 = arcArr[arcArr.length - 1].f1745d;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                Arc arc2 = arcArr[length];
                if (arc2.f1759r) {
                    dArr[0] = arc2.f(d5) + (this.f1739b[length].d(d5) * d6);
                    dArr[1] = this.f1739b[length].g(d5) + (d6 * this.f1739b[length].e(d5));
                    return;
                } else {
                    arc2.k(d2);
                    dArr[0] = this.f1739b[length].h() + (this.f1739b[length].b() * d6);
                    dArr[1] = this.f1739b[length].i() + (d6 * this.f1739b[length].c());
                    return;
                }
            }
        } else {
            Arc[] arcArr2 = this.f1739b;
            double d7 = arcArr2[0].f1744c;
            if (d2 < d7) {
                d2 = d7;
            }
            if (d2 > arcArr2[arcArr2.length - 1].f1745d) {
                d2 = arcArr2[arcArr2.length - 1].f1745d;
            }
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr3 = this.f1739b;
            if (i2 >= arcArr3.length) {
                return;
            }
            Arc arc3 = arcArr3[i2];
            if (d2 <= arc3.f1745d) {
                if (arc3.f1759r) {
                    dArr[0] = arc3.f(d2);
                    dArr[1] = this.f1739b[i2].g(d2);
                    return;
                } else {
                    arc3.k(d2);
                    dArr[0] = this.f1739b[i2].h();
                    dArr[1] = this.f1739b[i2].i();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void e(double d2, float[] fArr) {
        if (this.f1740c) {
            Arc[] arcArr = this.f1739b;
            Arc arc = arcArr[0];
            double d3 = arc.f1744c;
            if (d2 < d3) {
                double d4 = d2 - d3;
                if (arc.f1759r) {
                    fArr[0] = (float) (arc.f(d3) + (this.f1739b[0].d(d3) * d4));
                    fArr[1] = (float) (this.f1739b[0].g(d3) + (d4 * this.f1739b[0].e(d3)));
                    return;
                } else {
                    arc.k(d3);
                    fArr[0] = (float) (this.f1739b[0].h() + (this.f1739b[0].b() * d4));
                    fArr[1] = (float) (this.f1739b[0].i() + (d4 * this.f1739b[0].c()));
                    return;
                }
            }
            if (d2 > arcArr[arcArr.length - 1].f1745d) {
                double d5 = arcArr[arcArr.length - 1].f1745d;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                Arc arc2 = arcArr[length];
                if (arc2.f1759r) {
                    fArr[0] = (float) (arc2.f(d5) + (this.f1739b[length].d(d5) * d6));
                    fArr[1] = (float) (this.f1739b[length].g(d5) + (d6 * this.f1739b[length].e(d5)));
                    return;
                } else {
                    arc2.k(d2);
                    fArr[0] = (float) this.f1739b[length].h();
                    fArr[1] = (float) this.f1739b[length].i();
                    return;
                }
            }
        } else {
            Arc[] arcArr2 = this.f1739b;
            double d7 = arcArr2[0].f1744c;
            if (d2 < d7) {
                d2 = d7;
            } else if (d2 > arcArr2[arcArr2.length - 1].f1745d) {
                d2 = arcArr2[arcArr2.length - 1].f1745d;
            }
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr3 = this.f1739b;
            if (i2 >= arcArr3.length) {
                return;
            }
            Arc arc3 = arcArr3[i2];
            if (d2 <= arc3.f1745d) {
                if (arc3.f1759r) {
                    fArr[0] = (float) arc3.f(d2);
                    fArr[1] = (float) this.f1739b[i2].g(d2);
                    return;
                } else {
                    arc3.k(d2);
                    fArr[0] = (float) this.f1739b[i2].h();
                    fArr[1] = (float) this.f1739b[i2].i();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double f(double d2, int i2) {
        Arc[] arcArr = this.f1739b;
        int i3 = 0;
        double d3 = arcArr[0].f1744c;
        if (d2 < d3) {
            d2 = d3;
        }
        if (d2 > arcArr[arcArr.length - 1].f1745d) {
            d2 = arcArr[arcArr.length - 1].f1745d;
        }
        while (true) {
            Arc[] arcArr2 = this.f1739b;
            if (i3 >= arcArr2.length) {
                return Double.NaN;
            }
            Arc arc = arcArr2[i3];
            if (d2 <= arc.f1745d) {
                if (arc.f1759r) {
                    return i2 == 0 ? arc.d(d2) : arc.e(d2);
                }
                arc.k(d2);
                return i2 == 0 ? this.f1739b[i3].b() : this.f1739b[i3].c();
            }
            i3++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void g(double d2, double[] dArr) {
        Arc[] arcArr = this.f1739b;
        double d3 = arcArr[0].f1744c;
        if (d2 < d3) {
            d2 = d3;
        } else if (d2 > arcArr[arcArr.length - 1].f1745d) {
            d2 = arcArr[arcArr.length - 1].f1745d;
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr2 = this.f1739b;
            if (i2 >= arcArr2.length) {
                return;
            }
            Arc arc = arcArr2[i2];
            if (d2 <= arc.f1745d) {
                if (arc.f1759r) {
                    dArr[0] = arc.d(d2);
                    dArr[1] = this.f1739b[i2].e(d2);
                    return;
                } else {
                    arc.k(d2);
                    dArr[0] = this.f1739b[i2].b();
                    dArr[1] = this.f1739b[i2].c();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] h() {
        return this.f1738a;
    }
}
