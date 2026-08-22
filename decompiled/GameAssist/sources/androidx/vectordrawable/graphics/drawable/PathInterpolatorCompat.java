package androidx.vectordrawable.graphics.drawable;

import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class PathInterpolatorCompat implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    private float[] f5651a;

    /* renamed from: b, reason: collision with root package name */
    private float[] f5652b;

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        int length = this.f5651a.length - 1;
        int i2 = 0;
        while (length - i2 > 1) {
            int i3 = (i2 + length) / 2;
            if (f2 < this.f5651a[i3]) {
                length = i3;
            } else {
                i2 = i3;
            }
        }
        float[] fArr = this.f5651a;
        float f3 = fArr[length];
        float f4 = fArr[i2];
        float f5 = f3 - f4;
        if (f5 == 0.0f) {
            return this.f5652b[i2];
        }
        float[] fArr2 = this.f5652b;
        float f6 = fArr2[i2];
        return f6 + (((f2 - f4) / f5) * (fArr2[length] - f6));
    }
}
