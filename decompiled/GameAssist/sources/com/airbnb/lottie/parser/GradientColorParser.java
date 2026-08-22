package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class GradientColorParser implements ValueParser<GradientColor> {

    /* renamed from: a, reason: collision with root package name */
    private int f9825a;

    public GradientColorParser(int i2) {
        this.f9825a = i2;
    }

    private GradientColor b(GradientColor gradientColor, List list) {
        int i2 = this.f9825a * 4;
        if (list.size() <= i2) {
            return gradientColor;
        }
        float[] e2 = gradientColor.e();
        int[] d2 = gradientColor.d();
        int size = (list.size() - i2) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int i3 = 0;
        while (i2 < list.size()) {
            if (i2 % 2 == 0) {
                fArr[i3] = ((Float) list.get(i2)).floatValue();
            } else {
                fArr2[i3] = ((Float) list.get(i2)).floatValue();
                i3++;
            }
            i2++;
        }
        float[] e3 = e(gradientColor.e(), fArr);
        int length = e3.length;
        int[] iArr = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            float f2 = e3[i4];
            int binarySearch = Arrays.binarySearch(e2, f2);
            int binarySearch2 = Arrays.binarySearch(fArr, f2);
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                iArr[i4] = c(f2, fArr2[binarySearch2], e2, d2);
            } else {
                iArr[i4] = d(f2, d2[binarySearch], fArr, fArr2);
            }
        }
        return new GradientColor(e3, iArr);
    }

    private int d(float f2, int i2, float[] fArr, float[] fArr2) {
        float i3;
        if (fArr2.length < 2 || f2 <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
        }
        for (int i4 = 1; i4 < fArr.length; i4++) {
            float f3 = fArr[i4];
            if (f3 >= f2 || i4 == fArr.length - 1) {
                if (f3 <= f2) {
                    i3 = fArr2[i4];
                } else {
                    int i5 = i4 - 1;
                    float f4 = fArr[i5];
                    i3 = MiscUtils.i(fArr2[i5], fArr2[i4], (f2 - f4) / (f3 - f4));
                }
                return Color.argb((int) (i3 * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    protected static float[] e(float[] fArr, float[] fArr2) {
        if (fArr.length == 0) {
            return fArr2;
        }
        if (fArr2.length == 0) {
            return fArr;
        }
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            float f2 = i3 < fArr.length ? fArr[i3] : Float.NaN;
            float f3 = i4 < fArr2.length ? fArr2[i4] : Float.NaN;
            if (Float.isNaN(f3) || f2 < f3) {
                fArr3[i5] = f2;
                i3++;
            } else if (Float.isNaN(f2) || f3 < f2) {
                fArr3[i5] = f3;
                i4++;
            } else {
                fArr3[i5] = f2;
                i3++;
                i4++;
                i2++;
            }
        }
        return i2 == 0 ? fArr3 : Arrays.copyOf(fArr3, length - i2);
    }

    int c(float f2, float f3, float[] fArr, int[] iArr) {
        if (iArr.length < 2 || f2 == fArr[0]) {
            return iArr[0];
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            float f4 = fArr[i2];
            if (f4 >= f2 || i2 == fArr.length - 1) {
                if (i2 == fArr.length - 1 && f2 >= f4) {
                    return Color.argb((int) (f3 * 255.0f), Color.red(iArr[i2]), Color.green(iArr[i2]), Color.blue(iArr[i2]));
                }
                int i3 = i2 - 1;
                float f5 = fArr[i3];
                int c2 = GammaEvaluator.c((f2 - f5) / (f4 - f5), iArr[i3], iArr[i2]);
                return Color.argb((int) (f3 * 255.0f), Color.red(c2), Color.green(c2), Color.blue(c2));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public GradientColor a(JsonReader jsonReader, float f2) {
        ArrayList arrayList = new ArrayList();
        boolean z = jsonReader.C() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.c();
        }
        while (jsonReader.j()) {
            arrayList.add(Float.valueOf((float) jsonReader.p()));
        }
        if (arrayList.size() == 4 && ((Float) arrayList.get(0)).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add((Float) arrayList.get(1));
            arrayList.add((Float) arrayList.get(2));
            arrayList.add((Float) arrayList.get(3));
            this.f9825a = 2;
        }
        if (z) {
            jsonReader.e();
        }
        if (this.f9825a == -1) {
            this.f9825a = arrayList.size() / 4;
        }
        int i2 = this.f9825a;
        float[] fArr = new float[i2];
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.f9825a * 4; i5++) {
            int i6 = i5 / 4;
            double floatValue = ((Float) arrayList.get(i5)).floatValue();
            int i7 = i5 % 4;
            if (i7 == 0) {
                if (i6 > 0) {
                    float f3 = (float) floatValue;
                    if (fArr[i6 - 1] >= f3) {
                        fArr[i6] = f3 + 0.01f;
                    }
                }
                fArr[i6] = (float) floatValue;
            } else if (i7 == 1) {
                i3 = (int) (floatValue * 255.0d);
            } else if (i7 == 2) {
                i4 = (int) (floatValue * 255.0d);
            } else if (i7 == 3) {
                iArr[i6] = Color.argb(255, i3, i4, (int) (floatValue * 255.0d));
            }
        }
        return b(new GradientColor(fArr, iArr), arrayList);
    }
}
