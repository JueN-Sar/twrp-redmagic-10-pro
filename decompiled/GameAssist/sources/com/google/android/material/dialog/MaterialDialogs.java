package com.google.android.material.dialog;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialDialogs {
    public static InsetDrawable a(Drawable drawable, Rect rect) {
        return new InsetDrawable(drawable, rect.left, rect.top, rect.right, rect.bottom);
    }
}
