package com.zte.mifavor.widget;

import android.graphics.Path;
import android.graphics.RectF;

/* loaded from: classes2.dex */
public class G2ContinuousRoundedPath {

    /* renamed from: a, reason: collision with root package name */
    private static final G2ContinuityProfile f17622a = G2ContinuityProfile.c();

    /* renamed from: b, reason: collision with root package name */
    private static final G2ContinuityProfile f17623b = G2ContinuityProfile.b();

    public static class CubicBezier {

        /* renamed from: a, reason: collision with root package name */
        public final Point f17624a;

        /* renamed from: b, reason: collision with root package name */
        public final Point f17625b;

        /* renamed from: c, reason: collision with root package name */
        public final Point f17626c;

        /* renamed from: d, reason: collision with root package name */
        public final Point f17627d;

        public CubicBezier(Point point, Point point2, Point point3, Point point4) {
            this.f17624a = point;
            this.f17625b = point2;
            this.f17626c = point3;
            this.f17627d = point4;
        }
    }

    public static class G2ContinuityProfile {

        /* renamed from: a, reason: collision with root package name */
        public final double f17628a;

        /* renamed from: b, reason: collision with root package name */
        public final double f17629b;

        /* renamed from: c, reason: collision with root package name */
        public final double f17630c;

        /* renamed from: d, reason: collision with root package name */
        public final double f17631d;

        /* renamed from: e, reason: collision with root package name */
        private CubicBezier f17632e;

        public G2ContinuityProfile(double d2, double d3, double d4, double d5) {
            this.f17628a = d2;
            this.f17629b = d3;
            this.f17630c = d4;
            this.f17631d = d5;
        }

        private CubicBezier a() {
            double d2 = (1.5707963267948966d - (this.f17629b * 1.5707963267948966d)) * 0.5d;
            double sin = Math.sin(d2);
            double cos = Math.cos(d2);
            if (this.f17630c == 1.0d && this.f17631d == 1.0d) {
                double d3 = cos + 1.0d;
                double d4 = sin / d3;
                return new CubicBezier(new Point(-this.f17628a, 0.0d), new Point((1.0d - (1.5d / d3)) * d4, 0.0d), new Point(d4, 0.0d), new Point(sin, 1.0d - cos));
            }
            double d5 = 1.0d / this.f17631d;
            return d(new Point(-this.f17628a, 0.0d), new Point(0.0d, 1.0d).b(new Point(1.0d / Math.sqrt(2.0d), (-1.0d) / Math.sqrt(2.0d)).c(1.0d - d5)).b(new Point(sin, -cos).c(d5)), new Point(1.0d, 0.0d), new Point(cos, sin), this.f17630c);
        }

        public static G2ContinuityProfile b() {
            return new G2ContinuityProfile(0.396498825d, 0.0d, 1.0d, 1.0d);
        }

        public static G2ContinuityProfile c() {
            return new G2ContinuityProfile(0.5286651d, 0.5555555555555556d, 1.0732051d, 1.0732051d);
        }

        private static CubicBezier d(Point point, Point point2, Point point3, Point point4, double d2) {
            double d3 = point3.f17634a;
            double d4 = point4.f17635b;
            double d5 = point3.f17635b;
            double d6 = point4.f17634a;
            double d7 = (d3 * d4) - (d5 * d6);
            double d8 = point2.f17634a - point.f17634a;
            double d9 = point2.f17635b - point.f17635b;
            double d10 = ((-d9) * d3) + (d5 * d8);
            double d11 = ((-((d9 * d6) - (d8 * d4))) / d7) - ((((((1.5d * d2) * d10) * d10) / d7) / d7) / d7);
            double d12 = (-d10) / d7;
            return new CubicBezier(point, point.b(new Point(Math.max(point3.f17634a * d11, 0.0d), Math.max(d11 * point3.f17635b, 0.0d))), point2.a(new Point(Math.max(point4.f17634a * d12, 0.0d), Math.max(d12 * point4.f17635b, 0.0d))), point2);
        }

        public CubicBezier e() {
            if (this.f17632e == null) {
                this.f17632e = a();
            }
            return this.f17632e;
        }
    }

    public static class Point {

        /* renamed from: c, reason: collision with root package name */
        public static final Point f17633c = new Point(0.0d, 0.0d);

        /* renamed from: a, reason: collision with root package name */
        public final double f17634a;

        /* renamed from: b, reason: collision with root package name */
        public final double f17635b;

        public Point(double d2, double d3) {
            this.f17634a = d2;
            this.f17635b = d3;
        }

        public Point a(Point point) {
            return new Point(this.f17634a - point.f17634a, this.f17635b - point.f17635b);
        }

        public Point b(Point point) {
            return new Point(this.f17634a + point.f17634a, this.f17635b + point.f17635b);
        }

        public Point c(double d2) {
            return new Point(this.f17634a * d2, this.f17635b * d2);
        }
    }

    private static void a(Path path, Point point, double d2, double d3, double d4, double d5) {
        double d6 = (0.5d * d5) + d4;
        Point b2 = point.b(new Point(Math.cos(d6), Math.sin(d6)).c((1.0d - d3) * d2));
        double d7 = d2 * d3;
        double d8 = b2.f17634a;
        double d9 = b2.f17635b;
        path.arcTo(new RectF((float) (d8 - d7), (float) (d9 - d7), (float) (d8 + d7), (float) (d9 + d7)), (float) (d4 * 57.29577951308232d), (float) (d5 * 57.29577951308232d));
    }

    private static double b(double d2, double d3, double d4) {
        return Math.max(d3, Math.min(d2, d4));
    }

    public static Path c(double d2, double d3, double d4, double d5, double d6, double d7) {
        double min = Math.min(d2, d3) * 0.5d;
        double max = Math.max(0.0d, Math.min(d4, min));
        double max2 = Math.max(0.0d, Math.min(d5, min));
        double max3 = Math.max(0.0d, Math.min(d6, min));
        double max4 = Math.max(0.0d, Math.min(d7, min));
        if (max + max2 + max3 + max4 != 0.0d) {
            return d(d2, d3, max, max2, max3, max4, f17622a, f17623b);
        }
        Path path = new Path();
        path.addRect(0.0f, 0.0f, (float) d2, (float) d3, Path.Direction.CW);
        return path;
    }

    private static Path d(double d2, double d3, double d4, double d5, double d6, double d7, G2ContinuityProfile g2ContinuityProfile, G2ContinuityProfile g2ContinuityProfile2) {
        float f2;
        CubicBezier cubicBezier;
        CubicBezier cubicBezier2;
        CubicBezier cubicBezier3;
        double d8;
        CubicBezier cubicBezier4;
        double d9;
        Path path;
        double d10;
        Path path2;
        double d11 = d2 * 0.5d;
        double d12 = d3 * 0.5d;
        double b2 = b(((d12 / d4) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b3 = b(((d11 / d4) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b4 = b(((d11 / d5) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b5 = b(((d12 / d5) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b6 = b(((d12 / d6) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b7 = b(((d11 / d6) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b8 = b(((d11 / d7) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double b9 = b(((d12 / d7) - 1.0d) / g2ContinuityProfile.f17628a, 0.0d, 1.0d);
        double min = Math.min(b2, b3);
        double min2 = Math.min(b4, b5);
        double min3 = Math.min(b6, b7);
        double min4 = Math.min(b8, b9);
        double e2 = e(g2ContinuityProfile2.f17628a, g2ContinuityProfile.f17628a, min);
        double e3 = e(g2ContinuityProfile2.f17628a, g2ContinuityProfile.f17628a, min2);
        double e4 = e(g2ContinuityProfile2.f17628a, g2ContinuityProfile.f17628a, min3);
        double e5 = e(g2ContinuityProfile2.f17628a, g2ContinuityProfile.f17628a, min4);
        double d13 = e2 * b2;
        double d14 = e2 * b3;
        double d15 = e3 * b4;
        double d16 = e3 * b5;
        double d17 = e4 * b6;
        double d18 = e4 * b7;
        double d19 = e5 * b8;
        double d20 = e5 * b9;
        double d21 = -d4;
        double d22 = d21 * d13;
        double d23 = d21 * d14;
        double d24 = -d5;
        double d25 = d24 * d15;
        double d26 = d24 * d16;
        double d27 = -d6;
        double d28 = d27 * d17;
        double d29 = d27 * d18;
        double d30 = -d7;
        double d31 = d30 * d19;
        double d32 = d30 * d20;
        double e6 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b2);
        double e7 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b3);
        double e8 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b4);
        double e9 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b5);
        double e10 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b6);
        double e11 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b7);
        double e12 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b8);
        double e13 = e(g2ContinuityProfile2.f17630c, g2ContinuityProfile.f17630c, b9);
        double e14 = e(g2ContinuityProfile2.f17629b, g2ContinuityProfile.f17629b, min);
        double e15 = e(g2ContinuityProfile2.f17629b, g2ContinuityProfile.f17629b, min2);
        double e16 = e(g2ContinuityProfile2.f17629b, g2ContinuityProfile.f17629b, min3);
        double e17 = e(g2ContinuityProfile2.f17629b, g2ContinuityProfile.f17629b, min4);
        double d33 = g2ContinuityProfile.f17631d;
        double d34 = ((d33 - 1.0d) * min) + 1.0d;
        double d35 = ((d33 - 1.0d) * min2) + 1.0d;
        double d36 = ((d33 - 1.0d) * min3) + 1.0d;
        double d37 = ((d33 - 1.0d) * min4) + 1.0d;
        CubicBezier f3 = f(new G2ContinuityProfile(d13, e14, e6, d34), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f4 = f(new G2ContinuityProfile(d14, e14, e7, d34), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f5 = f(new G2ContinuityProfile(d15, e15, e8, d35), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f6 = f(new G2ContinuityProfile(d16, e15, e9, d35), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f7 = f(new G2ContinuityProfile(d17, e16, e10, d36), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f8 = f(new G2ContinuityProfile(d18, e16, e11, d36), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f9 = f(new G2ContinuityProfile(d19, e17, e12, d37), g2ContinuityProfile, g2ContinuityProfile2);
        CubicBezier f10 = f(new G2ContinuityProfile(d20, e17, e13, d37), g2ContinuityProfile, g2ContinuityProfile2);
        Path path3 = new Path();
        float f11 = (float) 0.0d;
        path3.moveTo(f11, (float) (d4 - d22));
        if (d4 > 0.0d) {
            Point point = f3.f17625b;
            float f12 = (float) ((point.f17635b * d4) + 0.0d);
            float f13 = (float) (d4 - (point.f17634a * d4));
            Point point2 = f3.f17626c;
            d8 = e15;
            float f14 = (float) ((point2.f17635b * d4) + 0.0d);
            float f15 = (float) (d4 - (point2.f17634a * d4));
            Point point3 = f3.f17627d;
            path3.cubicTo(f12, f13, f14, f15, (float) ((point3.f17635b * d4) + 0.0d), (float) (d4 - (point3.f17634a * d4)));
            path = path3;
            cubicBezier4 = f9;
            f2 = f11;
            cubicBezier = f8;
            cubicBezier2 = f6;
            cubicBezier3 = f7;
            d9 = d5;
            a(path3, new Point(d4, d4), d4, 1.0d / d34, ((1.0d - e14) * 1.5707963267948966d * 0.5d) + 3.141592653589793d, e14 * 1.5707963267948966d);
            Point point4 = f4.f17626c;
            float f16 = (float) (d4 - (point4.f17634a * d4));
            float f17 = (float) ((point4.f17635b * d4) + 0.0d);
            Point point5 = f4.f17625b;
            path.cubicTo(f16, f17, (float) (d4 - (point5.f17634a * d4)), (float) ((point5.f17635b * d4) + 0.0d), (float) (d4 - Math.max(f4.f17624a.f17634a * d4, d23)), (float) ((f4.f17624a.f17635b * d4) + 0.0d));
        } else {
            f2 = f11;
            cubicBezier = f8;
            cubicBezier2 = f6;
            cubicBezier3 = f7;
            d8 = e15;
            cubicBezier4 = f9;
            d9 = d5;
            path = path3;
        }
        double d38 = d2 - d9;
        Path path4 = path;
        path4.lineTo((float) (d38 + d25), f2);
        if (d9 > 0.0d) {
            Point point6 = f5.f17625b;
            float f18 = (float) ((point6.f17634a * d9) + d38);
            float f19 = (float) ((point6.f17635b * d9) + 0.0d);
            Point point7 = f5.f17626c;
            float f20 = (float) ((point7.f17634a * d9) + d38);
            float f21 = (float) ((point7.f17635b * d9) + 0.0d);
            Point point8 = f5.f17627d;
            path4.cubicTo(f18, f19, f20, f21, (float) ((point8.f17634a * d9) + d38), (float) ((point8.f17635b * d9) + 0.0d));
            path2 = path4;
            a(path4, new Point(d38, d9), d5, 1.0d / d35, (((1.0d - d8) * 1.5707963267948966d) * 0.5d) - 1.5707963267948966d, d8 * 1.5707963267948966d);
            CubicBezier cubicBezier5 = cubicBezier2;
            Point point9 = cubicBezier5.f17626c;
            d10 = d2;
            float f22 = (float) (d10 - (point9.f17635b * d9));
            float f23 = (float) (d9 - (point9.f17634a * d9));
            Point point10 = cubicBezier5.f17625b;
            float f24 = (float) (d10 - (point10.f17635b * d9));
            float f25 = (float) (d9 - (point10.f17634a * d9));
            Point point11 = cubicBezier5.f17624a;
            path2.cubicTo(f22, f23, f24, f25, (float) (d10 - (point11.f17635b * d9)), (float) (d9 - Math.max(point11.f17634a * d9, d26)));
        } else {
            d10 = d2;
            path2 = path4;
        }
        double d39 = d3 - d6;
        path2.lineTo((float) d10, (float) (d39 + d28));
        if (d6 > 0.0d) {
            CubicBezier cubicBezier6 = cubicBezier3;
            Point point12 = cubicBezier6.f17625b;
            float f26 = (float) (d10 - (point12.f17635b * d6));
            float f27 = (float) ((point12.f17634a * d6) + d39);
            Point point13 = cubicBezier6.f17626c;
            float f28 = (float) (d10 - (point13.f17635b * d6));
            float f29 = (float) ((point13.f17634a * d6) + d39);
            Point point14 = cubicBezier6.f17627d;
            Path path5 = path2;
            path5.cubicTo(f26, f27, f28, f29, (float) (d10 - (point14.f17635b * d6)), (float) ((point14.f17634a * d6) + d39));
            double d40 = d10 - d6;
            a(path2, new Point(d40, d39), d6, 1.0d / d36, ((1.0d - e16) * 1.5707963267948966d * 0.5d) + 0.0d, e16 * 1.5707963267948966d);
            CubicBezier cubicBezier7 = cubicBezier;
            Point point15 = cubicBezier7.f17626c;
            float f30 = (float) ((point15.f17634a * d6) + d40);
            float f31 = (float) (d3 - (point15.f17635b * d6));
            Point point16 = cubicBezier7.f17625b;
            path5.cubicTo(f30, f31, (float) ((point16.f17634a * d6) + d40), (float) (d3 - (point16.f17635b * d6)), (float) (d40 + Math.max(cubicBezier7.f17624a.f17634a * d6, d29)), (float) (d3 - (cubicBezier7.f17624a.f17635b * d6)));
        }
        path2.lineTo((float) (d7 - d31), (float) d3);
        if (d7 > 0.0d) {
            CubicBezier cubicBezier8 = cubicBezier4;
            Point point17 = cubicBezier8.f17625b;
            float f32 = (float) (d7 - (point17.f17634a * d7));
            float f33 = (float) (d3 - (point17.f17635b * d7));
            Point point18 = cubicBezier8.f17626c;
            float f34 = (float) (d7 - (point18.f17634a * d7));
            float f35 = (float) (d3 - (point18.f17635b * d7));
            Point point19 = cubicBezier8.f17627d;
            path2.cubicTo(f32, f33, f34, f35, (float) (d7 - (point19.f17634a * d7)), (float) (d3 - (point19.f17635b * d7)));
            double d41 = d3 - d7;
            Path path6 = path2;
            a(path6, new Point(d7, d41), d7, 1.0d / d37, ((1.0d - e17) * 1.5707963267948966d * 0.5d) + 1.5707963267948966d, e17 * 1.5707963267948966d);
            Point point20 = f10.f17626c;
            float f36 = (float) ((point20.f17635b * d7) + 0.0d);
            float f37 = (float) ((point20.f17634a * d7) + d41);
            Point point21 = f10.f17625b;
            float f38 = (float) ((point21.f17635b * d7) + 0.0d);
            float f39 = (float) ((point21.f17634a * d7) + d41);
            Point point22 = f10.f17624a;
            path6.cubicTo(f36, f37, f38, f39, (float) ((point22.f17635b * d7) + 0.0d), (float) (d41 + Math.max(point22.f17634a * d7, d32)));
        }
        path2.close();
        return path2;
    }

    private static double e(double d2, double d3, double d4) {
        return d2 + ((d3 - d2) * d4);
    }

    private static CubicBezier f(G2ContinuityProfile g2ContinuityProfile, G2ContinuityProfile g2ContinuityProfile2, G2ContinuityProfile g2ContinuityProfile3) {
        return g2ContinuityProfile.e();
    }
}
