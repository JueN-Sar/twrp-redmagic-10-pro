package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* loaded from: classes.dex */
public class MatrixEvaluator implements TypeEvaluator<Matrix> {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f13824a = new float[9];

    /* renamed from: b, reason: collision with root package name */
    private final float[] f13825b = new float[9];

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f13826c = new Matrix();

    @Override // android.animation.TypeEvaluator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f13824a);
        matrix2.getValues(this.f13825b);
        for (int i2 = 0; i2 < 9; i2++) {
            float[] fArr = this.f13825b;
            float f3 = fArr[i2];
            float f4 = this.f13824a[i2];
            fArr[i2] = f4 + ((f3 - f4) * f2);
        }
        this.f13826c.setValues(this.f13825b);
        return this.f13826c;
    }
}
