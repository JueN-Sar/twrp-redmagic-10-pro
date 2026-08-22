package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.common.util.DeviceProperties;

/* loaded from: classes.dex */
public final class zaaa extends Button {
    public zaaa(Context context, @Nullable AttributeSet attributeSet) {
        super(context, null, R.attr.buttonStyle);
    }

    private static final int b(int i2, int i3, int i4, int i5) {
        if (i2 == 0) {
            return i3;
        }
        if (i2 == 1) {
            return i4;
        }
        if (i2 == 2) {
            return i5;
        }
        throw new IllegalStateException("Unknown color scheme: " + i2);
    }

    public final void a(Resources resources, int i2, int i3) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i4 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i4);
        setMinWidth(i4);
        int i5 = com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark;
        int i6 = com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_light;
        int b2 = b(i3, i5, i6, i6);
        int i7 = com.google.android.gms.base.R.drawable.common_google_signin_btn_text_dark;
        int i8 = com.google.android.gms.base.R.drawable.common_google_signin_btn_text_light;
        int b3 = b(i3, i7, i8, i8);
        if (i2 == 0 || i2 == 1) {
            b2 = b3;
        } else if (i2 != 2) {
            throw new IllegalStateException("Unknown button size: " + i2);
        }
        Drawable r2 = DrawableCompat.r(resources.getDrawable(b2));
        DrawableCompat.o(r2, resources.getColorStateList(com.google.android.gms.base.R.color.common_google_signin_btn_tint));
        DrawableCompat.p(r2, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(r2);
        int i9 = com.google.android.gms.base.R.color.common_google_signin_btn_text_dark;
        int i10 = com.google.android.gms.base.R.color.common_google_signin_btn_text_light;
        setTextColor((ColorStateList) Preconditions.i(resources.getColorStateList(b(i3, i9, i10, i10))));
        if (i2 == 0) {
            setText(resources.getString(com.google.android.gms.base.R.string.common_signin_button_text));
        } else if (i2 == 1) {
            setText(resources.getString(com.google.android.gms.base.R.string.common_signin_button_text_long));
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("Unknown button size: " + i2);
            }
            setText((CharSequence) null);
        }
        setTransformationMethod(null);
        if (DeviceProperties.b(getContext())) {
            setGravity(19);
        }
    }
}
