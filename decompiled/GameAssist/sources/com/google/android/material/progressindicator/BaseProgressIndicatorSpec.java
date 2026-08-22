package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes.dex */
public abstract class BaseProgressIndicatorSpec {

    /* renamed from: a, reason: collision with root package name */
    public int f14868a;

    /* renamed from: b, reason: collision with root package name */
    public int f14869b;

    /* renamed from: c, reason: collision with root package name */
    public int[] f14870c = new int[0];

    /* renamed from: d, reason: collision with root package name */
    public int f14871d;

    /* renamed from: e, reason: collision with root package name */
    public int f14872e;

    /* renamed from: f, reason: collision with root package name */
    public int f14873f;

    /* renamed from: g, reason: collision with root package name */
    public int f14874g;

    protected BaseProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i2, int i3) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_track_thickness);
        TypedArray i4 = ThemeEnforcement.i(context, attributeSet, R.styleable.BaseProgressIndicator, i2, i3, new int[0]);
        this.f14868a = MaterialResources.d(context, i4, R.styleable.BaseProgressIndicator_trackThickness, dimensionPixelSize);
        this.f14869b = Math.min(MaterialResources.d(context, i4, R.styleable.BaseProgressIndicator_trackCornerRadius, 0), this.f14868a / 2);
        this.f14872e = i4.getInt(R.styleable.BaseProgressIndicator_showAnimationBehavior, 0);
        this.f14873f = i4.getInt(R.styleable.BaseProgressIndicator_hideAnimationBehavior, 0);
        this.f14874g = i4.getDimensionPixelSize(R.styleable.BaseProgressIndicator_indicatorTrackGapSize, 0);
        c(context, i4);
        d(context, i4);
        i4.recycle();
    }

    private void c(Context context, TypedArray typedArray) {
        if (!typedArray.hasValue(R.styleable.BaseProgressIndicator_indicatorColor)) {
            this.f14870c = new int[]{MaterialColors.b(context, R.attr.colorPrimary, -1)};
            return;
        }
        if (typedArray.peekValue(R.styleable.BaseProgressIndicator_indicatorColor).type != 1) {
            this.f14870c = new int[]{typedArray.getColor(R.styleable.BaseProgressIndicator_indicatorColor, -1)};
            return;
        }
        int[] intArray = context.getResources().getIntArray(typedArray.getResourceId(R.styleable.BaseProgressIndicator_indicatorColor, -1));
        this.f14870c = intArray;
        if (intArray.length == 0) {
            throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
        }
    }

    private void d(Context context, TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.BaseProgressIndicator_trackColor)) {
            this.f14871d = typedArray.getColor(R.styleable.BaseProgressIndicator_trackColor, -1);
            return;
        }
        this.f14871d = this.f14870c[0];
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.disabledAlpha});
        float f2 = obtainStyledAttributes.getFloat(0, 0.2f);
        obtainStyledAttributes.recycle();
        this.f14871d = MaterialColors.a(this.f14871d, (int) (f2 * 255.0f));
    }

    public boolean a() {
        return this.f14873f != 0;
    }

    public boolean b() {
        return this.f14872e != 0;
    }

    void e() {
        if (this.f14874g < 0) {
            throw new IllegalArgumentException("indicatorTrackGapSize must be >= 0.");
        }
    }
}
