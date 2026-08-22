package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import androidx.annotation.RestrictTo;

@SuppressLint({"AppCompatCustomView"})
@RestrictTo
/* loaded from: classes.dex */
public class VisibilityAwareImageButton extends ImageButton {
    private int userSetVisibility;

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void b(int i2, boolean z) {
        super.setVisibility(i2);
        if (z) {
            this.userSetVisibility = i2;
        }
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i2) {
        b(i2, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.userSetVisibility = getVisibility();
    }
}
