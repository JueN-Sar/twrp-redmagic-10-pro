package androidx.core.util;

import androidx.annotation.RestrictTo;
import cn.nubia.gameassist.view.NubiaTextClock;
import java.io.PrintWriter;

@RestrictTo
/* loaded from: classes.dex */
public final class TimeUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f3304a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static char[] f3305b = new char[24];

    private static int a(int i2, int i3, boolean z, int i4) {
        if (i2 > 99 || (z && i4 >= 3)) {
            return i3 + 3;
        }
        if (i2 > 9 || (z && i4 >= 2)) {
            return i3 + 2;
        }
        if (z || i2 > 0) {
            return i3 + 1;
        }
        return 0;
    }

    public static void b(long j2, long j3, PrintWriter printWriter) {
        if (j2 == 0) {
            printWriter.print("--");
        } else {
            d(j2 - j3, printWriter, 0);
        }
    }

    public static void c(long j2, PrintWriter printWriter) {
        d(j2, printWriter, 0);
    }

    public static void d(long j2, PrintWriter printWriter, int i2) {
        synchronized (f3304a) {
            printWriter.print(new String(f3305b, 0, f(j2, i2)));
        }
    }

    public static void e(long j2, StringBuilder sb) {
        synchronized (f3304a) {
            sb.append(f3305b, 0, f(j2, 0));
        }
    }

    private static int f(long j2, int i2) {
        char c2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j3 = j2;
        if (f3305b.length < i2) {
            f3305b = new char[i2];
        }
        char[] cArr = f3305b;
        if (j3 == 0) {
            int i8 = i2 - 1;
            while (i8 > 0) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 1;
        }
        if (j3 > 0) {
            c2 = '+';
        } else {
            j3 = -j3;
            c2 = '-';
        }
        int i9 = (int) (j3 % 1000);
        int floor = (int) Math.floor(j3 / 1000);
        if (floor > 86400) {
            i3 = floor / 86400;
            floor -= 86400 * i3;
        } else {
            i3 = 0;
        }
        if (floor > 3600) {
            i4 = floor / 3600;
            floor -= i4 * 3600;
        } else {
            i4 = 0;
        }
        if (floor > 60) {
            int i10 = floor / 60;
            i5 = floor - (i10 * 60);
            i6 = i10;
        } else {
            i5 = floor;
            i6 = 0;
        }
        if (i2 != 0) {
            int a2 = a(i3, 1, false, 0);
            int a3 = a2 + a(i4, 1, a2 > 0, 2);
            int a4 = a3 + a(i6, 1, a3 > 0, 2);
            int a5 = a4 + a(i5, 1, a4 > 0, 2);
            i7 = 0;
            for (int a6 = a5 + a(i9, 2, true, a5 > 0 ? 3 : 0) + 1; a6 < i2; a6++) {
                cArr[i7] = ' ';
                i7++;
            }
        } else {
            i7 = 0;
        }
        cArr[i7] = c2;
        int i11 = i7 + 1;
        boolean z = i2 != 0;
        int g2 = g(cArr, i3, 'd', i11, false, 0);
        int g3 = g(cArr, i4, 'h', g2, g2 != i11, z ? 2 : 0);
        int g4 = g(cArr, i6, 'm', g3, g3 != i11, z ? 2 : 0);
        int g5 = g(cArr, i5, NubiaTextClock.SECONDS, g4, g4 != i11, z ? 2 : 0);
        int g6 = g(cArr, i9, 'm', g5, true, (!z || g5 == i11) ? 0 : 3);
        cArr[g6] = NubiaTextClock.SECONDS;
        return g6 + 1;
    }

    private static int g(char[] cArr, int i2, char c2, int i3, boolean z, int i4) {
        int i5;
        if (!z && i2 <= 0) {
            return i3;
        }
        if ((!z || i4 < 3) && i2 <= 99) {
            i5 = i3;
        } else {
            int i6 = i2 / 100;
            cArr[i3] = (char) (i6 + 48);
            i5 = i3 + 1;
            i2 -= i6 * 100;
        }
        if ((z && i4 >= 2) || i2 > 9 || i3 != i5) {
            int i7 = i2 / 10;
            cArr[i5] = (char) (i7 + 48);
            i5++;
            i2 -= i7 * 10;
        }
        cArr[i5] = (char) (i2 + 48);
        cArr[i5 + 1] = c2;
        return i5 + 2;
    }
}
