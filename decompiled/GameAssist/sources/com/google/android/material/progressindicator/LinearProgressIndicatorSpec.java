package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes.dex */
public final class LinearProgressIndicatorSpec extends BaseProgressIndicatorSpec {

    /* renamed from: h, reason: collision with root package name */
    public int f14946h;

    /* renamed from: i, reason: collision with root package name */
    public int f14947i;

    /* renamed from: j, reason: collision with root package name */
    boolean f14948j;

    /* renamed from: k, reason: collision with root package name */
    public int f14949k;

    public LinearProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.linearProgressIndicatorStyle);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicatorSpec
    void e() {
        super.e();
        if (this.f14949k < 0) {
            throw new IllegalArgumentException("Stop indicator size must be >= 0.");
        }
        if (this.f14946h == 0) {
            if (this.f14869b > 0 && this.f14874g == 0) {
                throw new IllegalArgumentException("Rounded corners without gap are not supported in contiguous indeterminate animation.");
            }
            if (this.f14870c.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }

    public LinearProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, LinearProgressIndicator.DEF_STYLE_RES);
    }

    public LinearProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray i4 = ThemeEnforcement.i(context, attributeSet, R.styleable.LinearProgressIndicator, R.attr.linearProgressIndicatorStyle, LinearProgressIndicator.DEF_STYLE_RES, new int[0]);
        this.f14946h = i4.getInt(R.styleable.LinearProgressIndicator_indeterminateAnimationType, 1);
        this.f14947i = i4.getInt(R.styleable.LinearProgressIndicator_indicatorDirectionLinear, 0);
        this.f14949k = Math.min(i4.getDimensionPixelSize(R.styleable.LinearProgressIndicator_trackStopIndicatorSize, 0), this.f14868a);
        i4.recycle();
        e();
        this.f14948j = this.f14947i == 1;
    }
}
