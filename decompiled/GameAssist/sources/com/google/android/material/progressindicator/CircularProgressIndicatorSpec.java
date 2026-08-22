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
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes.dex */
public final class CircularProgressIndicatorSpec extends BaseProgressIndicatorSpec {

    /* renamed from: h, reason: collision with root package name */
    public int f14895h;

    /* renamed from: i, reason: collision with root package name */
    public int f14896i;

    /* renamed from: j, reason: collision with root package name */
    public int f14897j;

    public CircularProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, CircularProgressIndicator.DEF_STYLE_RES);
    }

    public CircularProgressIndicatorSpec(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_circular_size_medium);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_circular_inset_medium);
        TypedArray i4 = ThemeEnforcement.i(context, attributeSet, R.styleable.CircularProgressIndicator, i2, i3, new int[0]);
        this.f14895h = Math.max(MaterialResources.d(context, i4, R.styleable.CircularProgressIndicator_indicatorSize, dimensionPixelSize), this.f14868a * 2);
        this.f14896i = MaterialResources.d(context, i4, R.styleable.CircularProgressIndicator_indicatorInset, dimensionPixelSize2);
        this.f14897j = i4.getInt(R.styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        i4.recycle();
        e();
    }
}
