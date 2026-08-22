package com.zte.mifavor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import androidx.annotation.Nullable;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class CheckBoxZTE extends CheckBox {

    @Nullable
    private static Animation animationOff;

    @Nullable
    private static Animation animationOn;

    public CheckBoxZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        if (animationOn == null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_checkbox_to_on);
            animationOn = loadAnimation;
            loadAnimation.setFillAfter(true);
        }
        if (animationOff == null) {
            Animation loadAnimation2 = AnimationUtils.loadAnimation(context, R.anim.anim_checkbox_to_off);
            animationOff = loadAnimation2;
            loadAnimation2.setFillAfter(true);
        }
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    public void setVisibilityEx(int i2) {
        if (Utils.w()) {
            super.setVisibility(i2);
            return;
        }
        if (i2 == 0) {
            Animation animation = animationOn;
            if (animation != null) {
                animation.cancel();
                startAnimation(animationOn);
                return;
            }
            return;
        }
        Animation animation2 = animationOff;
        if (animation2 != null) {
            animation2.cancel();
            startAnimation(animationOff);
        }
    }

    public CheckBoxZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    public CheckBoxZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(context);
    }
}
