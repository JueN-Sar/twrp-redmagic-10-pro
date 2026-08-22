package com.google.android.material.theme.overlay;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.view.ContextThemeWrapper;

/* loaded from: classes.dex */
public class MaterialThemeOverlay {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f15464a = {R.attr.theme, com.google.android.material.R.attr.theme};

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f15465b = {com.google.android.material.R.attr.materialThemeOverlay};

    private static int a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f15464a);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return resourceId != 0 ? resourceId : resourceId2;
    }

    private static int b(Context context, AttributeSet attributeSet, int i2, int i3) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f15465b, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static Context c(Context context, AttributeSet attributeSet, int i2, int i3) {
        int b2 = b(context, attributeSet, i2, i3);
        boolean z = (context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == b2;
        if (b2 == 0 || z) {
            return context;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, b2);
        int a2 = a(context, attributeSet);
        if (a2 != 0) {
            contextThemeWrapper.getTheme().applyStyle(a2, true);
        }
        return contextThemeWrapper;
    }
}
