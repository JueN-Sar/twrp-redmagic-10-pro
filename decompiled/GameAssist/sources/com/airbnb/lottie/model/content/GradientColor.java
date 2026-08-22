package com.airbnb.lottie.model.content;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GradientColor {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f9647a;

    /* renamed from: b, reason: collision with root package name */
    private final int[] f9648b;

    public GradientColor(float[] fArr, int[] iArr) {
        this.f9647a = fArr;
        this.f9648b = iArr;
    }

    private void a(GradientColor gradientColor) {
        int i2 = 0;
        while (true) {
            int[] iArr = gradientColor.f9648b;
            if (i2 >= iArr.length) {
                return;
            }
            this.f9647a[i2] = gradientColor.f9647a[i2];
            this.f9648b[i2] = iArr[i2];
            i2++;
        }
    }

    private int c(float f2) {
        int binarySearch = Arrays.binarySearch(this.f9647a, f2);
        if (binarySearch >= 0) {
            return this.f9648b[binarySearch];
        }
        int i2 = -(binarySearch + 1);
        if (i2 == 0) {
            return this.f9648b[0];
        }
        int[] iArr = this.f9648b;
        if (i2 == iArr.length - 1) {
            return iArr[iArr.length - 1];
        }
        float[] fArr = this.f9647a;
        int i3 = i2 - 1;
        float f3 = fArr[i3];
        return GammaEvaluator.c((f2 - f3) / (fArr[i2] - f3), iArr[i3], iArr[i2]);
    }

    public GradientColor b(float[] fArr) {
        int[] iArr = new int[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            iArr[i2] = c(fArr[i2]);
        }
        return new GradientColor(fArr, iArr);
    }

    public int[] d() {
        return this.f9648b;
    }

    public float[] e() {
        return this.f9647a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GradientColor gradientColor = (GradientColor) obj;
        return Arrays.equals(this.f9647a, gradientColor.f9647a) && Arrays.equals(this.f9648b, gradientColor.f9648b);
    }

    public int f() {
        return this.f9648b.length;
    }

    public void g(GradientColor gradientColor, GradientColor gradientColor2, float f2) {
        int[] iArr;
        if (gradientColor.equals(gradientColor2)) {
            a(gradientColor);
            return;
        }
        if (f2 <= 0.0f) {
            a(gradientColor);
            return;
        }
        if (f2 >= 1.0f) {
            a(gradientColor2);
            return;
        }
        if (gradientColor.f9648b.length != gradientColor2.f9648b.length) {
            throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gradientColor.f9648b.length + " vs " + gradientColor2.f9648b.length + ")");
        }
        int i2 = 0;
        while (true) {
            iArr = gradientColor.f9648b;
            if (i2 >= iArr.length) {
                break;
            }
            this.f9647a[i2] = MiscUtils.i(gradientColor.f9647a[i2], gradientColor2.f9647a[i2], f2);
            this.f9648b[i2] = GammaEvaluator.c(f2, gradientColor.f9648b[i2], gradientColor2.f9648b[i2]);
            i2++;
        }
        int length = iArr.length;
        while (true) {
            float[] fArr = this.f9647a;
            if (length >= fArr.length) {
                return;
            }
            int[] iArr2 = gradientColor.f9648b;
            fArr[length] = fArr[iArr2.length - 1];
            int[] iArr3 = this.f9648b;
            iArr3[length] = iArr3[iArr2.length - 1];
            length++;
        }
    }

    public int hashCode() {
        return (Arrays.hashCode(this.f9647a) * 31) + Arrays.hashCode(this.f9648b);
    }
}
