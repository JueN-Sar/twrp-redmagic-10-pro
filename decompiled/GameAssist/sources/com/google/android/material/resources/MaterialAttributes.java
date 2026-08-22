package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialAttributes {
    public static TypedValue a(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean b(Context context, int i2, boolean z) {
        TypedValue a2 = a(context, i2);
        return (a2 == null || a2.type != 18) ? z : a2.data != 0;
    }

    public static int c(Context context, int i2, int i3) {
        TypedValue a2 = a(context, i2);
        return (a2 == null || a2.type != 16) ? i3 : a2.data;
    }

    public static int d(Context context, int i2, String str) {
        return e(context, i2, str).data;
    }

    public static TypedValue e(Context context, int i2, String str) {
        TypedValue a2 = a(context, i2);
        if (a2 != null) {
            return a2;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
    }

    public static TypedValue f(View view, int i2) {
        return e(view.getContext(), i2, view.getClass().getCanonicalName());
    }
}
