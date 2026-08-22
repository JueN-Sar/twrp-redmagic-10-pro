package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import com.zte.distbus.basetransfer.Status;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class PathParser {

    private static class ExtractFloatResult {

        /* renamed from: a, reason: collision with root package name */
        int f2925a;

        /* renamed from: b, reason: collision with root package name */
        boolean f2926b;

        ExtractFloatResult() {
        }
    }

    private static void a(ArrayList arrayList, char c2, float[] fArr) {
        arrayList.add(new PathDataNode(c2, fArr));
    }

    public static boolean b(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
            if (pathDataNodeArr[i2].f2927a != pathDataNodeArr2[i2].f2927a || pathDataNodeArr[i2].f2928b.length != pathDataNodeArr2[i2].f2928b.length) {
                return false;
            }
        }
        return true;
    }

    static float[] c(float[] fArr, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i2 < 0 || i2 > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = i3 - i2;
        int min = Math.min(i4, length - i2);
        float[] fArr2 = new float[i4];
        System.arraycopy(fArr, i2, fArr2, 0, min);
        return fArr2;
    }

    public static PathDataNode[] d(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 1;
        while (i3 < str.length()) {
            int i4 = i(str, i3);
            String trim = str.substring(i2, i4).trim();
            if (!trim.isEmpty()) {
                a(arrayList, trim.charAt(0), h(trim));
            }
            i2 = i4;
            i3 = i4 + 1;
        }
        if (i3 - i2 == 1 && i2 < str.length()) {
            a(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[0]);
    }

    public static Path e(String str) {
        Path path = new Path();
        try {
            PathDataNode.i(d(str), path);
            return path;
        } catch (RuntimeException e2) {
            throw new RuntimeException("Error in parsing " + str, e2);
        }
    }

    public static PathDataNode[] f(PathDataNode[] pathDataNodeArr) {
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
            pathDataNodeArr2[i2] = new PathDataNode(pathDataNodeArr[i2]);
        }
        return pathDataNodeArr2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039 A[LOOP:0: B:2:0x0007->B:14:0x0039, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void g(java.lang.String r8, int r9, androidx.core.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.f2926b = r0
            r1 = r9
            r2 = r0
            r3 = r2
            r4 = r3
        L7:
            int r5 = r8.length()
            if (r1 >= r5) goto L3c
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L29
            r6 = 69
            if (r5 == r6) goto L35
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L35
            switch(r5) {
                case 44: goto L29;
                case 45: goto L2c;
                case 46: goto L22;
                default: goto L21;
            }
        L21:
            goto L33
        L22:
            if (r3 != 0) goto L27
            r2 = r0
            r3 = r7
            goto L36
        L27:
            r10.f2926b = r7
        L29:
            r2 = r0
            r4 = r7
            goto L36
        L2c:
            if (r1 == r9) goto L33
            if (r2 != 0) goto L33
            r10.f2926b = r7
            goto L29
        L33:
            r2 = r0
            goto L36
        L35:
            r2 = r7
        L36:
            if (r4 == 0) goto L39
            goto L3c
        L39:
            int r1 = r1 + 1
            goto L7
        L3c:
            r10.f2925a = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.g(java.lang.String, int, androidx.core.graphics.PathParser$ExtractFloatResult):void");
    }

    private static float[] h(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i2 = 1;
            int i3 = 0;
            while (i2 < length) {
                g(str, i2, extractFloatResult);
                int i4 = extractFloatResult.f2925a;
                if (i2 < i4) {
                    fArr[i3] = Float.parseFloat(str.substring(i2, i4));
                    i3++;
                }
                i2 = extractFloatResult.f2926b ? i4 : i4 + 1;
            }
            return c(fArr, 0, i3);
        } catch (NumberFormatException e2) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e2);
        }
    }

    private static int i(String str, int i2) {
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    public static void j(PathDataNode[] pathDataNodeArr, Path path) {
        float[] fArr = new float[6];
        char c2 = 'm';
        for (PathDataNode pathDataNode : pathDataNodeArr) {
            PathDataNode.e(path, fArr, c2, pathDataNode.f2927a, pathDataNode.f2928b);
            c2 = pathDataNode.f2927a;
        }
    }

    public static void k(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i2 = 0; i2 < pathDataNodeArr2.length; i2++) {
            pathDataNodeArr[i2].f2927a = pathDataNodeArr2[i2].f2927a;
            for (int i3 = 0; i3 < pathDataNodeArr2[i2].f2928b.length; i3++) {
                pathDataNodeArr[i2].f2928b[i3] = pathDataNodeArr2[i2].f2928b[i3];
            }
        }
    }

    public static class PathDataNode {

        /* renamed from: a, reason: collision with root package name */
        private char f2927a;

        /* renamed from: b, reason: collision with root package name */
        private final float[] f2928b;

        PathDataNode(char c2, float[] fArr) {
            this.f2927a = c2;
            this.f2928b = fArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static void e(Path path, float[] fArr, char c2, char c3, float[] fArr2) {
            int i2;
            int i3;
            int i4;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            char c4 = c3;
            boolean z = false;
            float f10 = fArr[0];
            float f11 = fArr[1];
            float f12 = fArr[2];
            float f13 = fArr[3];
            float f14 = fArr[4];
            float f15 = fArr[5];
            switch (c4) {
                case 'A':
                case 'a':
                    i2 = 7;
                    i3 = i2;
                    break;
                case 'C':
                case 'c':
                    i2 = 6;
                    i3 = i2;
                    break;
                case 'H':
                case 'V':
                case Status.BLE_ERROR /* 104 */:
                case 'v':
                    i3 = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                default:
                    i3 = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i3 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f14, f15);
                    f10 = f14;
                    f12 = f10;
                    f11 = f15;
                    f13 = f11;
                    i3 = 2;
                    break;
            }
            float f16 = f10;
            float f17 = f11;
            float f18 = f14;
            float f19 = f15;
            int i5 = 0;
            char c5 = c2;
            while (i5 < fArr2.length) {
                if (c4 != 'A') {
                    if (c4 == 'C') {
                        i4 = i5;
                        int i6 = i4 + 2;
                        int i7 = i4 + 3;
                        int i8 = i4 + 4;
                        int i9 = i4 + 5;
                        path.cubicTo(fArr2[i4], fArr2[i4 + 1], fArr2[i6], fArr2[i7], fArr2[i8], fArr2[i9]);
                        f16 = fArr2[i8];
                        float f20 = fArr2[i9];
                        float f21 = fArr2[i6];
                        float f22 = fArr2[i7];
                        f17 = f20;
                        f13 = f22;
                        f12 = f21;
                    } else if (c4 == 'H') {
                        i4 = i5;
                        path.lineTo(fArr2[i4], f17);
                        f16 = fArr2[i4];
                    } else if (c4 == 'Q') {
                        i4 = i5;
                        int i10 = i4 + 1;
                        int i11 = i4 + 2;
                        int i12 = i4 + 3;
                        path.quadTo(fArr2[i4], fArr2[i10], fArr2[i11], fArr2[i12]);
                        float f23 = fArr2[i4];
                        float f24 = fArr2[i10];
                        f16 = fArr2[i11];
                        f17 = fArr2[i12];
                        f12 = f23;
                        f13 = f24;
                    } else if (c4 == 'V') {
                        i4 = i5;
                        path.lineTo(f16, fArr2[i4]);
                        f17 = fArr2[i4];
                    } else if (c4 != 'a') {
                        if (c4 != 'c') {
                            if (c4 == 'h') {
                                path.rLineTo(fArr2[i5], 0.0f);
                                f16 += fArr2[i5];
                            } else if (c4 != 'q') {
                                if (c4 == 'v') {
                                    path.rLineTo(0.0f, fArr2[i5]);
                                    f5 = fArr2[i5];
                                } else if (c4 == 'L') {
                                    int i13 = i5 + 1;
                                    path.lineTo(fArr2[i5], fArr2[i13]);
                                    f16 = fArr2[i5];
                                    f17 = fArr2[i13];
                                } else if (c4 == 'M') {
                                    f16 = fArr2[i5];
                                    f17 = fArr2[i5 + 1];
                                    if (i5 > 0) {
                                        path.lineTo(f16, f17);
                                    } else {
                                        path.moveTo(f16, f17);
                                        i4 = i5;
                                        f19 = f17;
                                        f18 = f16;
                                    }
                                } else if (c4 == 'S') {
                                    if (c5 == 'c' || c5 == 's' || c5 == 'C' || c5 == 'S') {
                                        f16 = (f16 * 2.0f) - f12;
                                        f17 = (f17 * 2.0f) - f13;
                                    }
                                    float f25 = f17;
                                    float f26 = f16;
                                    int i14 = i5 + 1;
                                    int i15 = i5 + 2;
                                    int i16 = i5 + 3;
                                    path.cubicTo(f26, f25, fArr2[i5], fArr2[i14], fArr2[i15], fArr2[i16]);
                                    f2 = fArr2[i5];
                                    f3 = fArr2[i14];
                                    f16 = fArr2[i15];
                                    f17 = fArr2[i16];
                                    f12 = f2;
                                    f13 = f3;
                                } else if (c4 == 'T') {
                                    if (c5 == 'q' || c5 == 't' || c5 == 'Q' || c5 == 'T') {
                                        f16 = (f16 * 2.0f) - f12;
                                        f17 = (f17 * 2.0f) - f13;
                                    }
                                    int i17 = i5 + 1;
                                    path.quadTo(f16, f17, fArr2[i5], fArr2[i17]);
                                    i4 = i5;
                                    f13 = f17;
                                    f12 = f16;
                                    f16 = fArr2[i5];
                                    f17 = fArr2[i17];
                                } else if (c4 == 'l') {
                                    int i18 = i5 + 1;
                                    path.rLineTo(fArr2[i5], fArr2[i18]);
                                    f16 += fArr2[i5];
                                    f5 = fArr2[i18];
                                } else if (c4 == 'm') {
                                    float f27 = fArr2[i5];
                                    f16 += f27;
                                    float f28 = fArr2[i5 + 1];
                                    f17 += f28;
                                    if (i5 > 0) {
                                        path.rLineTo(f27, f28);
                                    } else {
                                        path.rMoveTo(f27, f28);
                                        i4 = i5;
                                        f19 = f17;
                                        f18 = f16;
                                    }
                                } else if (c4 == 's') {
                                    if (c5 == 'c' || c5 == 's' || c5 == 'C' || c5 == 'S') {
                                        float f29 = f16 - f12;
                                        f6 = f17 - f13;
                                        f7 = f29;
                                    } else {
                                        f7 = 0.0f;
                                        f6 = 0.0f;
                                    }
                                    int i19 = i5 + 1;
                                    int i20 = i5 + 2;
                                    int i21 = i5 + 3;
                                    path.rCubicTo(f7, f6, fArr2[i5], fArr2[i19], fArr2[i20], fArr2[i21]);
                                    f2 = fArr2[i5] + f16;
                                    f3 = fArr2[i19] + f17;
                                    f16 += fArr2[i20];
                                    f4 = fArr2[i21];
                                } else if (c4 == 't') {
                                    if (c5 == 'q' || c5 == 't' || c5 == 'Q' || c5 == 'T') {
                                        f8 = f16 - f12;
                                        f9 = f17 - f13;
                                    } else {
                                        f9 = 0.0f;
                                        f8 = 0.0f;
                                    }
                                    int i22 = i5 + 1;
                                    path.rQuadTo(f8, f9, fArr2[i5], fArr2[i22]);
                                    float f30 = f8 + f16;
                                    float f31 = f9 + f17;
                                    f16 += fArr2[i5];
                                    f17 += fArr2[i22];
                                    f13 = f31;
                                    f12 = f30;
                                }
                                f17 += f5;
                            } else {
                                int i23 = i5 + 1;
                                int i24 = i5 + 2;
                                int i25 = i5 + 3;
                                path.rQuadTo(fArr2[i5], fArr2[i23], fArr2[i24], fArr2[i25]);
                                f2 = fArr2[i5] + f16;
                                f3 = fArr2[i23] + f17;
                                f16 += fArr2[i24];
                                f4 = fArr2[i25];
                            }
                            i4 = i5;
                        } else {
                            int i26 = i5 + 2;
                            int i27 = i5 + 3;
                            int i28 = i5 + 4;
                            int i29 = i5 + 5;
                            path.rCubicTo(fArr2[i5], fArr2[i5 + 1], fArr2[i26], fArr2[i27], fArr2[i28], fArr2[i29]);
                            f2 = fArr2[i26] + f16;
                            f3 = fArr2[i27] + f17;
                            f16 += fArr2[i28];
                            f4 = fArr2[i29];
                        }
                        f17 += f4;
                        f12 = f2;
                        f13 = f3;
                        i4 = i5;
                    } else {
                        int i30 = i5 + 5;
                        int i31 = i5 + 6;
                        i4 = i5;
                        g(path, f16, f17, fArr2[i30] + f16, fArr2[i31] + f17, fArr2[i5], fArr2[i5 + 1], fArr2[i5 + 2], fArr2[i5 + 3] != 0.0f, fArr2[i5 + 4] != 0.0f);
                        f16 += fArr2[i30];
                        f17 += fArr2[i31];
                    }
                    i5 = i4 + i3;
                    c5 = c3;
                    c4 = c5;
                    z = false;
                } else {
                    i4 = i5;
                    int i32 = i4 + 5;
                    int i33 = i4 + 6;
                    g(path, f16, f17, fArr2[i32], fArr2[i33], fArr2[i4], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3] != 0.0f, fArr2[i4 + 4] != 0.0f);
                    f16 = fArr2[i32];
                    f17 = fArr2[i33];
                }
                f13 = f17;
                f12 = f16;
                i5 = i4 + i3;
                c5 = c3;
                c4 = c5;
                z = false;
            }
            fArr[z ? 1 : 0] = f16;
            fArr[1] = f17;
            fArr[2] = f12;
            fArr[3] = f13;
            fArr[4] = f18;
            fArr[5] = f19;
        }

        private static void f(Path path, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
            double d11 = d4;
            int ceil = (int) Math.ceil(Math.abs((d10 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d8);
            double sin = Math.sin(d8);
            double cos2 = Math.cos(d9);
            double sin2 = Math.sin(d9);
            double d12 = -d11;
            double d13 = d12 * cos;
            double d14 = d5 * sin;
            double d15 = (d13 * sin2) - (d14 * cos2);
            double d16 = d12 * sin;
            double d17 = d5 * cos;
            double d18 = (sin2 * d16) + (cos2 * d17);
            double d19 = d10 / ceil;
            double d20 = d18;
            double d21 = d15;
            int i2 = 0;
            double d22 = d6;
            double d23 = d7;
            double d24 = d9;
            while (i2 < ceil) {
                double d25 = d24 + d19;
                double sin3 = Math.sin(d25);
                double cos3 = Math.cos(d25);
                double d26 = (d2 + ((d11 * cos) * cos3)) - (d14 * sin3);
                double d27 = d3 + (d11 * sin * cos3) + (d17 * sin3);
                double d28 = (d13 * sin3) - (d14 * cos3);
                double d29 = (sin3 * d16) + (cos3 * d17);
                double d30 = d25 - d24;
                double tan = Math.tan(d30 / 2.0d);
                double sin4 = (Math.sin(d30) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                double d31 = d22 + (d21 * sin4);
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) d31, (float) (d23 + (d20 * sin4)), (float) (d26 - (sin4 * d28)), (float) (d27 - (sin4 * d29)), (float) d26, (float) d27);
                i2++;
                d19 = d19;
                sin = sin;
                d22 = d26;
                d16 = d16;
                cos = cos;
                d24 = d25;
                d20 = d29;
                d21 = d28;
                ceil = ceil;
                d23 = d27;
                d11 = d4;
            }
        }

        private static void g(Path path, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z, boolean z2) {
            double d2;
            double d3;
            double radians = Math.toRadians(f8);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d4 = f2;
            double d5 = d4 * cos;
            double d6 = f3;
            double d7 = f6;
            double d8 = (d5 + (d6 * sin)) / d7;
            double d9 = ((-f2) * sin) + (d6 * cos);
            double d10 = f7;
            double d11 = d9 / d10;
            double d12 = f5;
            double d13 = ((f4 * cos) + (d12 * sin)) / d7;
            double d14 = (((-f4) * sin) + (d12 * cos)) / d10;
            double d15 = d8 - d13;
            double d16 = d11 - d14;
            double d17 = (d8 + d13) / 2.0d;
            double d18 = (d11 + d14) / 2.0d;
            double d19 = (d15 * d15) + (d16 * d16);
            if (d19 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d20 = (1.0d / d19) - 0.25d;
            if (d20 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + d19);
                float sqrt = (float) (Math.sqrt(d19) / 1.99999d);
                g(path, f2, f3, f4, f5, f6 * sqrt, f7 * sqrt, f8, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d20);
            double d21 = d15 * sqrt2;
            double d22 = sqrt2 * d16;
            if (z == z2) {
                d2 = d17 - d22;
                d3 = d18 + d21;
            } else {
                d2 = d17 + d22;
                d3 = d18 - d21;
            }
            double atan2 = Math.atan2(d11 - d3, d8 - d2);
            double atan22 = Math.atan2(d14 - d3, d13 - d2) - atan2;
            if (z2 != (atan22 >= 0.0d)) {
                atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d23 = d2 * d7;
            double d24 = d3 * d10;
            f(path, (d23 * cos) - (d24 * sin), (d23 * sin) + (d24 * cos), d7, d10, d4, d6, radians, atan2, atan22);
        }

        public static void i(PathDataNode[] pathDataNodeArr, Path path) {
            PathParser.j(pathDataNodeArr, path);
        }

        public void h(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f2) {
            this.f2927a = pathDataNode.f2927a;
            int i2 = 0;
            while (true) {
                float[] fArr = pathDataNode.f2928b;
                if (i2 >= fArr.length) {
                    return;
                }
                this.f2928b[i2] = (fArr[i2] * (1.0f - f2)) + (pathDataNode2.f2928b[i2] * f2);
                i2++;
            }
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.f2927a = pathDataNode.f2927a;
            float[] fArr = pathDataNode.f2928b;
            this.f2928b = PathParser.c(fArr, 0, fArr.length);
        }
    }
}
