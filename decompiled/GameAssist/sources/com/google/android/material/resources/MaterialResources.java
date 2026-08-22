package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.RestrictTo;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialResources {
    public static ColorStateList a(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        ColorStateList a2;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (a2 = AppCompatResources.a(context, resourceId)) == null) ? typedArray.getColorStateList(i2) : a2;
    }

    public static ColorStateList b(Context context, TintTypedArray tintTypedArray, int i2) {
        int n2;
        ColorStateList a2;
        return (!tintTypedArray.s(i2) || (n2 = tintTypedArray.n(i2, 0)) == 0 || (a2 = AppCompatResources.a(context, n2)) == null) ? tintTypedArray.c(i2) : a2;
    }

    private static int c(TypedValue typedValue) {
        return typedValue.getComplexUnit();
    }

    public static int d(Context context, TypedArray typedArray, int i2, int i3) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i2, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i2, i3);
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, i3);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static Drawable e(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        Drawable b2;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (b2 = AppCompatResources.b(context, resourceId)) == null) ? typedArray.getDrawable(i2) : b2;
    }

    public static float f(Context context) {
        return context.getResources().getConfiguration().fontScale;
    }

    static int g(TypedArray typedArray, int i2, int i3) {
        return typedArray.hasValue(i2) ? i2 : i3;
    }

    public static TextAppearance h(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        if (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return new TextAppearance(context, resourceId);
    }

    public static int i(Context context, int i2, int i3) {
        if (i2 == 0) {
            return i3;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.TextAppearance);
        TypedValue typedValue = new TypedValue();
        boolean value = obtainStyledAttributes.getValue(R.styleable.TextAppearance_android_textSize, typedValue);
        obtainStyledAttributes.recycle();
        return !value ? i3 : c(typedValue) == 2 ? Math.round(TypedValue.complexToFloat(typedValue.data) * context.getResources().getDisplayMetrics().density) : TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    public static boolean j(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }

    public static boolean k(Context context) {
        return context.getResources().getConfiguration().fontScale >= 2.0f;
    }
}
