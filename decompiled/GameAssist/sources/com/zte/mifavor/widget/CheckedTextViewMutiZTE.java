package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

/* loaded from: classes2.dex */
public class CheckedTextViewMutiZTE extends CheckedTextView {
    private static final int[] STATE_ENABLE_UNCHECKED = {R.attr.state_enabled, -16842912};
    private static final int[] STATE_ENABLE_CHECKED = {R.attr.state_enabled, R.attr.state_checked};
    private static final int[] STATE_DISABLE_UNCHECKED = {-16842910, -16842912};
    private static final int[] STATE_DISABLE_CHECKED = {-16842910, R.attr.state_checked};

    public CheckedTextViewMutiZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setCheckMarkDrawable(a(context.getResources().getColor(com.zte.extres.R.color.mfv_common_cb_on)));
    }

    private Drawable a(int i2) {
        int color = getResources().getColor(com.zte.extres.R.color.mfv_common_cb_off);
        AnimatedStateListDrawable animatedStateListDrawable = new AnimatedStateListDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_007);
        bitmapDrawable.setAlpha(31);
        bitmapDrawable.setTint(i2);
        animatedStateListDrawable.addState(STATE_DISABLE_CHECKED, bitmapDrawable, 0);
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_000);
        bitmapDrawable2.setAlpha(31);
        bitmapDrawable2.setTint(color);
        animatedStateListDrawable.addState(STATE_DISABLE_UNCHECKED, bitmapDrawable2, 0);
        BitmapDrawable bitmapDrawable3 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_007);
        bitmapDrawable3.setAlpha(255);
        bitmapDrawable3.setTint(i2);
        animatedStateListDrawable.addState(STATE_ENABLE_CHECKED, bitmapDrawable3, com.zte.extres.R.id.on);
        BitmapDrawable bitmapDrawable4 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_000);
        bitmapDrawable4.setAlpha(255);
        bitmapDrawable4.setTint(color);
        animatedStateListDrawable.addState(STATE_ENABLE_UNCHECKED, bitmapDrawable4, com.zte.extres.R.id.off);
        int i3 = com.zte.extres.R.id.off;
        int i4 = com.zte.extres.R.id.on;
        AnimationDrawable animationDrawable = new AnimationDrawable();
        BitmapDrawable bitmapDrawable5 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_000);
        bitmapDrawable5.setTint(color);
        animationDrawable.addFrame(bitmapDrawable5, 15);
        BitmapDrawable bitmapDrawable6 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_001);
        bitmapDrawable6.setTint(color);
        animationDrawable.addFrame(bitmapDrawable6, 15);
        BitmapDrawable bitmapDrawable7 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_002);
        bitmapDrawable7.setTint(color);
        animationDrawable.addFrame(bitmapDrawable7, 15);
        BitmapDrawable bitmapDrawable8 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_003);
        bitmapDrawable8.setTint(color);
        animationDrawable.addFrame(bitmapDrawable8, 15);
        BitmapDrawable bitmapDrawable9 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_004);
        bitmapDrawable9.setTint(i2);
        animationDrawable.addFrame(bitmapDrawable9, 15);
        BitmapDrawable bitmapDrawable10 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_005);
        bitmapDrawable10.setTint(i2);
        animationDrawable.addFrame(bitmapDrawable10, 15);
        BitmapDrawable bitmapDrawable11 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_006);
        bitmapDrawable11.setTint(i2);
        animationDrawable.addFrame(bitmapDrawable11, 15);
        BitmapDrawable bitmapDrawable12 = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.btn_check_to_on_mtrl_007);
        bitmapDrawable12.setTint(i2);
        animationDrawable.addFrame(bitmapDrawable12, 15);
        animatedStateListDrawable.addTransition(i3, i4, animationDrawable, false);
        int i5 = com.zte.extres.R.id.on;
        int i6 = com.zte.extres.R.id.off;
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        bitmapDrawable12.setTint(i2);
        animationDrawable2.addFrame(bitmapDrawable12, 15);
        bitmapDrawable11.setTint(i2);
        animationDrawable2.addFrame(bitmapDrawable11, 15);
        bitmapDrawable10.setTint(i2);
        animationDrawable2.addFrame(bitmapDrawable10, 15);
        bitmapDrawable9.setTint(i2);
        animationDrawable2.addFrame(bitmapDrawable9, 15);
        bitmapDrawable8.setTint(color);
        animationDrawable2.addFrame(bitmapDrawable8, 15);
        bitmapDrawable7.setTint(color);
        animationDrawable2.addFrame(bitmapDrawable7, 15);
        bitmapDrawable6.setTint(color);
        animationDrawable2.addFrame(bitmapDrawable6, 15);
        bitmapDrawable5.setTint(color);
        animationDrawable2.addFrame(bitmapDrawable5, 15);
        animatedStateListDrawable.addTransition(i5, i6, animationDrawable2, false);
        return animatedStateListDrawable;
    }

    public CheckedTextViewMutiZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setCheckMarkDrawable(a(context.getResources().getColor(com.zte.extres.R.color.mfv_common_cb_on)));
    }

    public CheckedTextViewMutiZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        setCheckMarkDrawable(a(context.getResources().getColor(com.zte.extres.R.color.mfv_common_cb_on)));
    }
}
