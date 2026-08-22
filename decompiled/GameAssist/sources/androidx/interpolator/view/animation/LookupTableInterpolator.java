package androidx.interpolator.view.animation;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
abstract class LookupTableInterpolator implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f4249a;

    /* renamed from: b, reason: collision with root package name */
    private final float f4250b;

    protected LookupTableInterpolator(float[] fArr) {
        this.f4249a = fArr;
        this.f4250b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f4249a;
        int min = Math.min((int) ((fArr.length - 1) * f2), fArr.length - 2);
        float f3 = this.f4250b;
        float f4 = (f2 - (min * f3)) / f3;
        float[] fArr2 = this.f4249a;
        float f5 = fArr2[min];
        return f5 + (f4 * (fArr2[min + 1] - f5));
    }
}
