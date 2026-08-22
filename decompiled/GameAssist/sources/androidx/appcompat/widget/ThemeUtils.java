package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;

@RestrictTo
/* loaded from: classes.dex */
public class ThemeUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal f1001a = new ThreadLocal();

    /* renamed from: b, reason: collision with root package name */
    static final int[] f1002b = {-16842910};

    /* renamed from: c, reason: collision with root package name */
    static final int[] f1003c = {R.attr.state_focused};

    /* renamed from: d, reason: collision with root package name */
    static final int[] f1004d = {R.attr.state_activated};

    /* renamed from: e, reason: collision with root package name */
    static final int[] f1005e = {R.attr.state_pressed};

    /* renamed from: f, reason: collision with root package name */
    static final int[] f1006f = {R.attr.state_checked};

    /* renamed from: g, reason: collision with root package name */
    static final int[] f1007g = {R.attr.state_selected};

    /* renamed from: h, reason: collision with root package name */
    static final int[] f1008h = {-16842919, -16842908};

    /* renamed from: i, reason: collision with root package name */
    static final int[] f1009i = new int[0];

    /* renamed from: j, reason: collision with root package name */
    private static final int[] f1010j = new int[1];

    public static void a(View view, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
        try {
            if (!obtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBar)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int b(Context context, int i2) {
        ColorStateList e2 = e(context, i2);
        if (e2 != null && e2.isStateful()) {
            return e2.getColorForState(f1002b, e2.getDefaultColor());
        }
        TypedValue f2 = f();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, f2, true);
        return d(context, i2, f2.getFloat());
    }

    public static int c(Context context, int i2) {
        int[] iArr = f1010j;
        iArr[0] = i2;
        TintTypedArray u = TintTypedArray.u(context, null, iArr);
        try {
            return u.b(0, 0);
        } finally {
            u.x();
        }
    }

    static int d(Context context, int i2, float f2) {
        return ColorUtils.k(c(context, i2), Math.round(Color.alpha(r0) * f2));
    }

    public static ColorStateList e(Context context, int i2) {
        int[] iArr = f1010j;
        iArr[0] = i2;
        TintTypedArray u = TintTypedArray.u(context, null, iArr);
        try {
            return u.c(0);
        } finally {
            u.x();
        }
    }

    private static TypedValue f() {
        ThreadLocal threadLocal = f1001a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }
}
