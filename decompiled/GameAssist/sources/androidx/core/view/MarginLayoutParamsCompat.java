package androidx.core.view;

import android.view.ViewGroup;

@Deprecated
/* loaded from: classes.dex */
public final class MarginLayoutParamsCompat {
    public static int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginEnd();
    }

    public static int b(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginStart();
    }

    public static void c(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        marginLayoutParams.setMarginEnd(i2);
    }

    public static void d(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        marginLayoutParams.setMarginStart(i2);
    }
}
