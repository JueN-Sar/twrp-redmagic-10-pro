package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/* loaded from: classes2.dex */
public class ButtonZTE extends Button implements FontScaleSupport {
    private FontScale mFontScale;

    public ButtonZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyle);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(@StyleRes int i2) {
        super.setTextAppearance(i2);
        this.mFontScale.d(i2);
    }

    public void setTextFontScale(int i2) {
        this.mFontScale.c(i2);
    }

    public ButtonZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ButtonZTE(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        FontScale fontScale = new FontScale();
        this.mFontScale = fontScale;
        fontScale.b(this, attributeSet, i2, i3);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }
}
