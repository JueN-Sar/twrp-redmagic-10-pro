package com.google.android.material.ripple;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.StateSet;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.color.MaterialColors;

@RestrictTo
/* loaded from: classes.dex */
public class RippleUtils {

    @VisibleForTesting
    static final String TRANSPARENT_DEFAULT_COLOR_WARNING = "Use a non-transparent color for the default color as it will be used to finish ripple animations.";

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f14980a = true;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f14981b = {R.attr.state_pressed};

    /* renamed from: c, reason: collision with root package name */
    private static final int[] f14982c = {R.attr.state_hovered, R.attr.state_focused};

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f14983d = {R.attr.state_focused};

    /* renamed from: e, reason: collision with root package name */
    private static final int[] f14984e = {R.attr.state_hovered};

    /* renamed from: f, reason: collision with root package name */
    private static final int[] f14985f = {R.attr.state_selected, R.attr.state_pressed};

    /* renamed from: g, reason: collision with root package name */
    private static final int[] f14986g = {R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};

    /* renamed from: h, reason: collision with root package name */
    private static final int[] f14987h = {R.attr.state_selected, R.attr.state_focused};

    /* renamed from: i, reason: collision with root package name */
    private static final int[] f14988i = {R.attr.state_selected, R.attr.state_hovered};

    /* renamed from: j, reason: collision with root package name */
    private static final int[] f14989j = {R.attr.state_selected};

    /* renamed from: k, reason: collision with root package name */
    private static final int[] f14990k = {R.attr.state_enabled, R.attr.state_pressed};

    @VisibleForTesting
    static final String LOG_TAG = RippleUtils.class.getSimpleName();

    @RequiresApi
    private static class RippleUtilsLollipop {
        @DoNotInline
        private static Drawable a(@NonNull Context context, @Px int i2) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-1);
            gradientDrawable.setShape(1);
            return new RippleDrawable(MaterialColors.g(context, com.google.android.material.R.attr.colorControlHighlight, ColorStateList.valueOf(0)), null, new InsetDrawable((Drawable) gradientDrawable, i2, i2, i2, i2));
        }
    }

    public static ColorStateList a(ColorStateList colorStateList) {
        if (f14980a) {
            int[] iArr = f14983d;
            return new ColorStateList(new int[][]{f14989j, iArr, StateSet.NOTHING}, new int[]{c(colorStateList, f14985f), c(colorStateList, iArr), c(colorStateList, f14981b)});
        }
        int[] iArr2 = f14985f;
        int[] iArr3 = f14986g;
        int[] iArr4 = f14987h;
        int[] iArr5 = f14988i;
        int[] iArr6 = f14981b;
        int[] iArr7 = f14982c;
        int[] iArr8 = f14983d;
        int[] iArr9 = f14984e;
        return new ColorStateList(new int[][]{iArr2, iArr3, iArr4, iArr5, f14989j, iArr6, iArr7, iArr8, iArr9, StateSet.NOTHING}, new int[]{c(colorStateList, iArr2), c(colorStateList, iArr3), c(colorStateList, iArr4), c(colorStateList, iArr5), 0, c(colorStateList, iArr6), c(colorStateList, iArr7), c(colorStateList, iArr8), c(colorStateList, iArr9), 0});
    }

    private static int b(int i2) {
        return ColorUtils.k(i2, Math.min(Color.alpha(i2) * 2, 255));
    }

    private static int c(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return f14980a ? b(colorForState) : colorForState;
    }

    public static ColorStateList d(ColorStateList colorStateList) {
        return colorStateList != null ? colorStateList : ColorStateList.valueOf(0);
    }

    public static boolean e(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z = true;
            } else if (i2 == 16842908 || i2 == 16842919 || i2 == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }
}
