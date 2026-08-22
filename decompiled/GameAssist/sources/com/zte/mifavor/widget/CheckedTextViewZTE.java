package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import androidx.annotation.StyleRes;

/* loaded from: classes2.dex */
public class CheckedTextViewZTE extends CheckedTextView implements FontScaleSupport {
    private static final boolean DEBUG = false;
    private static final String TAG = "CheckedTextViewZTE";
    private FontScale mFontScale;
    private static final int[] STATE_ENABLE_UNCHECKED = {R.attr.state_enabled, -16842912};
    private static final int[] STATE_ENABLE_CHECKED = {R.attr.state_enabled, R.attr.state_checked};
    private static final int[] STATE_DISABLE_UNCHECKED = {-16842910, -16842912};
    private static final int[] STATE_DISABLE_CHECKED = {-16842910, R.attr.state_checked};

    public CheckedTextViewZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    private Drawable a(int i2, int i3) {
        return getContext().getDrawable(com.zte.extres.R.drawable.btn_radio_material_anim);
    }

    public Drawable getAnimationDrawable() {
        AnimatedStateListDrawable animatedStateListDrawable = new AnimatedStateListDrawable();
        getContext().getResources().getColor(com.zte.extres.R.color.mfv_common_rb_on);
        int color = getContext().getResources().getColor(com.zte.extres.R.color.mfv_common_rb_off);
        Drawable mutate = getResources().getDrawable(com.zte.extres.R.drawable.radio_button_on_common).mutate();
        animatedStateListDrawable.addState(STATE_DISABLE_CHECKED, mutate, 0);
        Drawable mutate2 = getResources().getDrawable(com.zte.extres.R.drawable.radio_button_off).mutate();
        mutate.setAlpha(66);
        mutate.setTint(color);
        animatedStateListDrawable.addState(STATE_DISABLE_UNCHECKED, mutate2, 0);
        animatedStateListDrawable.addState(STATE_ENABLE_CHECKED, getResources().getDrawable(com.zte.extres.R.drawable.radio_button_on_common).mutate(), com.zte.extres.R.id.on);
        Drawable mutate3 = getResources().getDrawable(com.zte.extres.R.drawable.radio_button_off).mutate();
        mutate3.setAlpha(255);
        mutate3.setTint(color);
        animatedStateListDrawable.addState(STATE_ENABLE_UNCHECKED, mutate3, com.zte.extres.R.id.off);
        animatedStateListDrawable.addTransition(com.zte.extres.R.id.off, com.zte.extres.R.id.on, (AnimatedVectorDrawable) getContext().getDrawable(com.zte.extres.R.drawable.radiobuttonoff_on_common), false);
        animatedStateListDrawable.addTransition(com.zte.extres.R.id.on, com.zte.extres.R.id.off, (AnimatedVectorDrawable) getContext().getDrawable(com.zte.extres.R.drawable.radiobuttonon_off_commom), false);
        return animatedStateListDrawable;
    }

    @Override // android.widget.TextView
    public void setTextAppearance(@StyleRes int i2) {
        super.setTextAppearance(i2);
        this.mFontScale.d(i2);
    }

    public void setTextFontScale(int i2) {
        this.mFontScale.c(i2);
    }

    public CheckedTextViewZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public CheckedTextViewZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mFontScale = new FontScale();
        setCheckMarkDrawable(a(context.getResources().getColor(com.zte.extres.R.color.mfv_common_rb_on), context.getResources().getColor(com.zte.extres.R.color.mfv_common_rb_off)));
        this.mFontScale.b(this, attributeSet, i2, i3);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
        Utils.m(context);
    }
}
