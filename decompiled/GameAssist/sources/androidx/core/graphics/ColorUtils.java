package androidx.core.graphics;

import android.graphics.Color;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ColorUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal f2916a = new ThreadLocal();

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static Color a(Color color, Color color2) {
            if (!Objects.equals(color.getModel(), color2.getModel())) {
                throw new IllegalArgumentException("Color models must match (" + color.getModel() + " vs. " + color2.getModel() + ")");
            }
            if (!Objects.equals(color2.getColorSpace(), color.getColorSpace())) {
                color = color.convert(color2.getColorSpace());
            }
            float[] components = color.getComponents();
            float[] components2 = color2.getComponents();
            float alpha = color.alpha();
            float alpha2 = color2.alpha() * (1.0f - alpha);
            int componentCount = color2.getComponentCount() - 1;
            float f2 = alpha + alpha2;
            components2[componentCount] = f2;
            if (f2 > 0.0f) {
                alpha /= f2;
                alpha2 /= f2;
            }
            for (int i2 = 0; i2 < componentCount; i2++) {
                components2[i2] = (components[i2] * alpha) + (components2[i2] * alpha2);
            }
            return Color.valueOf(components2, color2.getColorSpace());
        }
    }

    public static void a(int i2, int i3, int i4, double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = i2 / 255.0d;
        double pow = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = i3 / 255.0d;
        double pow2 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = i4 / 255.0d;
        double pow3 = d4 < 0.04045d ? d4 / 12.92d : Math.pow((d4 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.4124d * pow) + (0.3576d * pow2) + (0.1805d * pow3)) * 100.0d;
        dArr[1] = ((0.2126d * pow) + (0.7152d * pow2) + (0.0722d * pow3)) * 100.0d;
        dArr[2] = ((pow * 0.0193d) + (pow2 * 0.1192d) + (pow3 * 0.9505d)) * 100.0d;
    }

    public static int b(double d2, double d3, double d4) {
        double d5 = (((3.2406d * d2) + ((-1.5372d) * d3)) + ((-0.4986d) * d4)) / 100.0d;
        double d6 = ((((-0.9689d) * d2) + (1.8758d * d3)) + (0.0415d * d4)) / 100.0d;
        double d7 = (((0.0557d * d2) + ((-0.204d) * d3)) + (1.057d * d4)) / 100.0d;
        return Color.rgb(i((int) Math.round((d5 > 0.0031308d ? (Math.pow(d5, 0.4166666666666667d) * 1.055d) - 0.055d : d5 * 12.92d) * 255.0d), 0, 255), i((int) Math.round((d6 > 0.0031308d ? (Math.pow(d6, 0.4166666666666667d) * 1.055d) - 0.055d : d6 * 12.92d) * 255.0d), 0, 255), i((int) Math.round((d7 > 0.0031308d ? (Math.pow(d7, 0.4166666666666667d) * 1.055d) - 0.055d : d7 * 12.92d) * 255.0d), 0, 255));
    }

    public static int c(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((Color.alpha(i2) * f3) + (Color.alpha(i3) * f2)), (int) ((Color.red(i2) * f3) + (Color.red(i3) * f2)), (int) ((Color.green(i2) * f3) + (Color.green(i3) * f2)), (int) ((Color.blue(i2) * f3) + (Color.blue(i3) * f2)));
    }

    @VisibleForTesting
    static float circularInterpolate(float f2, float f3, float f4) {
        if (Math.abs(f3 - f2) > 180.0f) {
            if (f3 > f2) {
                f2 += 360.0f;
            } else {
                f3 += 360.0f;
            }
        }
        return (f2 + ((f3 - f2) * f4)) % 360.0f;
    }

    public static double d(int i2) {
        double[] j2 = j();
        e(i2, j2);
        return j2[1] / 100.0d;
    }

    public static void e(int i2, double[] dArr) {
        a(Color.red(i2), Color.green(i2), Color.blue(i2), dArr);
    }

    private static int f(int i2, int i3) {
        return 255 - (((255 - i3) * (255 - i2)) / 255);
    }

    public static int g(int i2, int i3) {
        int alpha = Color.alpha(i3);
        int alpha2 = Color.alpha(i2);
        int f2 = f(alpha2, alpha);
        return Color.argb(f2, h(Color.red(i2), alpha2, Color.red(i3), alpha, f2), h(Color.green(i2), alpha2, Color.green(i3), alpha, f2), h(Color.blue(i2), alpha2, Color.blue(i3), alpha, f2));
    }

    private static int h(int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            return 0;
        }
        return (((i2 * 255) * i3) + ((i4 * i5) * (255 - i3))) / (i6 * 255);
    }

    private static int i(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : Math.min(i2, i4);
    }

    private static double[] j() {
        ThreadLocal threadLocal = f2916a;
        double[] dArr = (double[]) threadLocal.get();
        if (dArr != null) {
            return dArr;
        }
        double[] dArr2 = new double[3];
        threadLocal.set(dArr2);
        return dArr2;
    }

    public static int k(int i2, int i3) {
        if (i3 < 0 || i3 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i2 & 16777215) | (i3 << 24);
    }
}
