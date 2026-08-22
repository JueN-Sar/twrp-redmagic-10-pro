package androidx.constraintlayout.core.motion.utils;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class KeyCycleOscillator {

    /* renamed from: a, reason: collision with root package name */
    private CurveFit f1772a;

    /* renamed from: b, reason: collision with root package name */
    private CycleOscillator f1773b;

    /* renamed from: c, reason: collision with root package name */
    private String f1774c;

    /* renamed from: d, reason: collision with root package name */
    private int f1775d = 0;

    /* renamed from: e, reason: collision with root package name */
    private String f1776e = null;

    /* renamed from: f, reason: collision with root package name */
    public int f1777f = 0;

    /* renamed from: g, reason: collision with root package name */
    ArrayList f1778g = new ArrayList();

    private static class CoreSpline extends KeyCycleOscillator {
    }

    static class CycleOscillator {

        /* renamed from: a, reason: collision with root package name */
        private final int f1780a;

        /* renamed from: b, reason: collision with root package name */
        Oscillator f1781b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1782c;

        /* renamed from: d, reason: collision with root package name */
        private final int f1783d;

        /* renamed from: e, reason: collision with root package name */
        private final int f1784e;

        /* renamed from: f, reason: collision with root package name */
        float[] f1785f;

        /* renamed from: g, reason: collision with root package name */
        double[] f1786g;

        /* renamed from: h, reason: collision with root package name */
        float[] f1787h;

        /* renamed from: i, reason: collision with root package name */
        float[] f1788i;

        /* renamed from: j, reason: collision with root package name */
        float[] f1789j;

        /* renamed from: k, reason: collision with root package name */
        float[] f1790k;

        /* renamed from: l, reason: collision with root package name */
        int f1791l;

        /* renamed from: m, reason: collision with root package name */
        CurveFit f1792m;

        /* renamed from: n, reason: collision with root package name */
        double[] f1793n;

        /* renamed from: o, reason: collision with root package name */
        double[] f1794o;

        /* renamed from: p, reason: collision with root package name */
        float f1795p;

        CycleOscillator(int i2, String str, int i3, int i4) {
            Oscillator oscillator = new Oscillator();
            this.f1781b = oscillator;
            this.f1782c = 0;
            this.f1783d = 1;
            this.f1784e = 2;
            this.f1791l = i2;
            this.f1780a = i3;
            oscillator.g(i2, str);
            this.f1785f = new float[i4];
            this.f1786g = new double[i4];
            this.f1787h = new float[i4];
            this.f1788i = new float[i4];
            this.f1789j = new float[i4];
            this.f1790k = new float[i4];
        }

        public double a(float f2) {
            CurveFit curveFit = this.f1792m;
            if (curveFit != null) {
                double d2 = f2;
                curveFit.g(d2, this.f1794o);
                this.f1792m.d(d2, this.f1793n);
            } else {
                double[] dArr = this.f1794o;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 0.0d;
            }
            double d3 = f2;
            double e2 = this.f1781b.e(d3, this.f1793n[1]);
            double d4 = this.f1781b.d(d3, this.f1793n[1], this.f1794o[1]);
            double[] dArr2 = this.f1794o;
            return dArr2[0] + (e2 * dArr2[2]) + (d4 * this.f1793n[2]);
        }

        public double b(float f2) {
            CurveFit curveFit = this.f1792m;
            if (curveFit != null) {
                curveFit.d(f2, this.f1793n);
            } else {
                double[] dArr = this.f1793n;
                dArr[0] = this.f1788i[0];
                dArr[1] = this.f1789j[0];
                dArr[2] = this.f1785f[0];
            }
            double[] dArr2 = this.f1793n;
            return dArr2[0] + (this.f1781b.e(f2, dArr2[1]) * this.f1793n[2]);
        }

        public void c(int i2, int i3, float f2, float f3, float f4, float f5) {
            this.f1786g[i2] = i3 / 100.0d;
            this.f1787h[i2] = f2;
            this.f1788i[i2] = f3;
            this.f1789j[i2] = f4;
            this.f1785f[i2] = f5;
        }

        public void d(float f2) {
            this.f1795p = f2;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, this.f1786g.length, 3);
            float[] fArr = this.f1785f;
            this.f1793n = new double[fArr.length + 2];
            this.f1794o = new double[fArr.length + 2];
            if (this.f1786g[0] > 0.0d) {
                this.f1781b.a(0.0d, this.f1787h[0]);
            }
            double[] dArr2 = this.f1786g;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f1781b.a(1.0d, this.f1787h[length]);
            }
            for (int i2 = 0; i2 < dArr.length; i2++) {
                double[] dArr3 = dArr[i2];
                dArr3[0] = this.f1788i[i2];
                dArr3[1] = this.f1789j[i2];
                dArr3[2] = this.f1785f[i2];
                this.f1781b.a(this.f1786g[i2], this.f1787h[i2]);
            }
            this.f1781b.f();
            double[] dArr4 = this.f1786g;
            if (dArr4.length > 1) {
                this.f1792m = CurveFit.a(0, dArr4, dArr);
            } else {
                this.f1792m = null;
            }
        }
    }

    public static class PathRotateSet extends KeyCycleOscillator {
    }

    static class WavePoint {

        /* renamed from: a, reason: collision with root package name */
        int f1796a;

        /* renamed from: b, reason: collision with root package name */
        float f1797b;

        /* renamed from: c, reason: collision with root package name */
        float f1798c;

        /* renamed from: d, reason: collision with root package name */
        float f1799d;

        /* renamed from: e, reason: collision with root package name */
        float f1800e;

        WavePoint(int i2, float f2, float f3, float f4, float f5) {
            this.f1796a = i2;
            this.f1797b = f5;
            this.f1798c = f3;
            this.f1799d = f2;
            this.f1800e = f4;
        }
    }

    public float a(float f2) {
        return (float) this.f1773b.b(f2);
    }

    public float b(float f2) {
        return (float) this.f1773b.a(f2);
    }

    protected void c(Object obj) {
    }

    public void d(int i2, int i3, String str, int i4, float f2, float f3, float f4, float f5) {
        this.f1778g.add(new WavePoint(i2, f2, f3, f4, f5));
        if (i4 != -1) {
            this.f1777f = i4;
        }
        this.f1775d = i3;
        this.f1776e = str;
    }

    public void e(int i2, int i3, String str, int i4, float f2, float f3, float f4, float f5, Object obj) {
        this.f1778g.add(new WavePoint(i2, f2, f3, f4, f5));
        if (i4 != -1) {
            this.f1777f = i4;
        }
        this.f1775d = i3;
        c(obj);
        this.f1776e = str;
    }

    public void f(String str) {
        this.f1774c = str;
    }

    public void g(float f2) {
        int size = this.f1778g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f1778g, new Comparator<WavePoint>() { // from class: androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.1
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(WavePoint wavePoint, WavePoint wavePoint2) {
                return Integer.compare(wavePoint.f1796a, wavePoint2.f1796a);
            }
        });
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 3);
        this.f1773b = new CycleOscillator(this.f1775d, this.f1776e, this.f1777f, size);
        Iterator it = this.f1778g.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            WavePoint wavePoint = (WavePoint) it.next();
            float f3 = wavePoint.f1799d;
            dArr[i2] = f3 * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = wavePoint.f1797b;
            dArr3[0] = f4;
            float f5 = wavePoint.f1798c;
            dArr3[1] = f5;
            float f6 = wavePoint.f1800e;
            dArr3[2] = f6;
            this.f1773b.c(i2, wavePoint.f1796a, f3, f5, f6, f4);
            i2++;
            dArr2 = dArr2;
        }
        this.f1773b.d(f2);
        this.f1772a = CurveFit.a(0, dArr, dArr2);
    }

    public boolean h() {
        return this.f1777f == 1;
    }

    public String toString() {
        String str = this.f1774c;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator it = this.f1778g.iterator();
        while (it.hasNext()) {
            str = str + "[" + ((WavePoint) it.next()).f1796a + " , " + decimalFormat.format(r2.f1797b) + "] ";
        }
        return str;
    }
}
