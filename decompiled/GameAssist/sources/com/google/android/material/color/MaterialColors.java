package com.google.android.material.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes.dex */
public class MaterialColors {
    public static int a(int i2, int i3) {
        return ColorUtils.k(i2, (Color.alpha(i2) * i3) / 255);
    }

    public static int b(Context context, int i2, int i3) {
        Integer f2 = f(context, i2);
        return f2 != null ? f2.intValue() : i3;
    }

    public static int c(Context context, int i2, String str) {
        return n(context, MaterialAttributes.e(context, i2, str));
    }

    public static int d(View view, int i2) {
        return n(view.getContext(), MaterialAttributes.f(view, i2));
    }

    public static int e(View view, int i2, int i3) {
        return b(view.getContext(), i2, i3);
    }

    public static Integer f(Context context, int i2) {
        TypedValue a2 = MaterialAttributes.a(context, i2);
        if (a2 != null) {
            return Integer.valueOf(n(context, a2));
        }
        return null;
    }

    public static ColorStateList g(Context context, int i2, ColorStateList colorStateList) {
        TypedValue a2 = MaterialAttributes.a(context, i2);
        ColorStateList o2 = a2 != null ? o(context, a2) : null;
        return o2 == null ? colorStateList : o2;
    }

    public static ColorStateList h(Context context, int i2) {
        TypedValue a2 = MaterialAttributes.a(context, i2);
        if (a2 == null) {
            return null;
        }
        int i3 = a2.resourceId;
        if (i3 != 0) {
            return ContextCompat.d(context, i3);
        }
        int i4 = a2.data;
        if (i4 != 0) {
            return ColorStateList.valueOf(i4);
        }
        return null;
    }

    public static boolean i(int i2) {
        return i2 != 0 && ColorUtils.d(i2) > 0.5d;
    }

    static boolean j(Context context) {
        return MaterialAttributes.b(context, R.attr.isLightTheme, true);
    }

    public static int k(int i2, int i3) {
        return ColorUtils.g(i3, i2);
    }

    public static int l(int i2, int i3, float f2) {
        return k(i2, ColorUtils.k(i3, Math.round(Color.alpha(i3) * f2)));
    }

    public static int m(View view, int i2, int i3, float f2) {
        return l(d(view, i2), d(view, i3), f2);
    }

    private static int n(Context context, TypedValue typedValue) {
        int i2 = typedValue.resourceId;
        return i2 != 0 ? ContextCompat.c(context, i2) : typedValue.data;
    }

    private static ColorStateList o(Context context, TypedValue typedValue) {
        int i2 = typedValue.resourceId;
        return i2 != 0 ? ContextCompat.d(context, i2) : ColorStateList.valueOf(typedValue.data);
    }
}
