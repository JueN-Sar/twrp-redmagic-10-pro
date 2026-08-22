package com.google.android.material.resources;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import androidx.annotation.RestrictTo;
import androidx.core.math.MathUtils;

@RestrictTo
/* loaded from: classes.dex */
public class TypefaceUtils {
    public static Typeface a(Context context, Typeface typeface) {
        return b(context.getResources().getConfiguration(), typeface);
    }

    public static Typeface b(Configuration configuration, Typeface typeface) {
        int i2 = configuration.fontWeightAdjustment;
        if (i2 == Integer.MAX_VALUE || i2 == 0 || typeface == null) {
            return null;
        }
        return Typeface.create(typeface, MathUtils.b(typeface.getWeight() + configuration.fontWeightAdjustment, 1, 1000), typeface.isItalic());
    }
}
