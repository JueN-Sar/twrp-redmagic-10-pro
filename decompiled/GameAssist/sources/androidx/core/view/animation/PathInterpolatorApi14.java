package androidx.core.view.animation;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
class PathInterpolatorApi14 implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f3509a;

    /* renamed from: b, reason: collision with root package name */
    private final float[] f3510b;

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        int length = this.f3509a.length - 1;
        int i2 = 0;
        while (length - i2 > 1) {
            int i3 = (i2 + length) / 2;
            if (f2 < this.f3509a[i3]) {
                length = i3;
            } else {
                i2 = i3;
            }
        }
        float[] fArr = this.f3509a;
        float f3 = fArr[length];
        float f4 = fArr[i2];
        float f5 = f3 - f4;
        if (f5 == 0.0f) {
            return this.f3510b[i2];
        }
        float[] fArr2 = this.f3510b;
        float f6 = fArr2[i2];
        return f6 + (((f2 - f4) / f5) * (fArr2[length] - f6));
    }
}
