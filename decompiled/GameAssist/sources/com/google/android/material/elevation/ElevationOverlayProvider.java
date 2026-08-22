package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes.dex */
public class ElevationOverlayProvider {

    /* renamed from: f, reason: collision with root package name */
    private static final int f14572f = (int) Math.round(5.1000000000000005d);

    /* renamed from: a, reason: collision with root package name */
    private final boolean f14573a;

    /* renamed from: b, reason: collision with root package name */
    private final int f14574b;

    /* renamed from: c, reason: collision with root package name */
    private final int f14575c;

    /* renamed from: d, reason: collision with root package name */
    private final int f14576d;

    /* renamed from: e, reason: collision with root package name */
    private final float f14577e;

    public ElevationOverlayProvider(Context context) {
        this(MaterialAttributes.b(context, R.attr.elevationOverlayEnabled, false), MaterialColors.b(context, R.attr.elevationOverlayColor, 0), MaterialColors.b(context, R.attr.elevationOverlayAccentColor, 0), MaterialColors.b(context, R.attr.colorSurface, 0), context.getResources().getDisplayMetrics().density);
    }

    private boolean f(int i2) {
        return ColorUtils.k(i2, 255) == this.f14576d;
    }

    public float a(float f2) {
        if (this.f14577e <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r2)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i2, float f2) {
        int i3;
        float a2 = a(f2);
        int alpha = Color.alpha(i2);
        int l2 = MaterialColors.l(ColorUtils.k(i2, 255), this.f14574b, a2);
        if (a2 > 0.0f && (i3 = this.f14575c) != 0) {
            l2 = MaterialColors.k(l2, ColorUtils.k(i3, f14572f));
        }
        return ColorUtils.k(l2, alpha);
    }

    public int c(int i2, float f2) {
        return (this.f14573a && f(i2)) ? b(i2, f2) : i2;
    }

    public int d(float f2) {
        return c(this.f14576d, f2);
    }

    public boolean e() {
        return this.f14573a;
    }

    public ElevationOverlayProvider(boolean z, int i2, int i3, int i4, float f2) {
        this.f14573a = z;
        this.f14574b = i2;
        this.f14575c = i3;
        this.f14576d = i4;
        this.f14577e = f2;
    }
}
