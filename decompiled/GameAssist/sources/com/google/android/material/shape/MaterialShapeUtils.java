package com.google.android.material.shape;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.internal.ViewUtils;

/* loaded from: classes.dex */
public class MaterialShapeUtils {
    static CornerTreatment a(int i2) {
        return i2 != 0 ? i2 != 1 ? b() : new CutCornerTreatment() : new RoundedCornerTreatment();
    }

    static CornerTreatment b() {
        return new RoundedCornerTreatment();
    }

    static EdgeTreatment c() {
        return new EdgeTreatment();
    }

    public static void d(View view, float f2) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).Z(f2);
        }
    }

    public static void e(View view) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            f(view, (MaterialShapeDrawable) background);
        }
    }

    public static void f(View view, MaterialShapeDrawable materialShapeDrawable) {
        if (materialShapeDrawable.R()) {
            materialShapeDrawable.e0(ViewUtils.n(view));
        }
    }
}
