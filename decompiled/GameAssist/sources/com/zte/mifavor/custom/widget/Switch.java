package com.zte.mifavor.custom.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.PathInterpolator;
import com.zte.mifavor.custom.Config;

/* loaded from: classes2.dex */
public class Switch {
    private static final String TAG = "SwitchMiFavor";

    public static void animateThumbToCheckedState_setPositionAnimator(Object[] objArr) {
        ObjectAnimator objectAnimator;
        if (Config.isMifavorTheme((Context) objArr[0]) && (objectAnimator = (ObjectAnimator) objArr[1]) != null) {
            objectAnimator.setDuration(150L);
            objectAnimator.setInterpolator(new PathInterpolator(0.16f, 0.1f, 0.15f, 1.0f));
        }
    }
}
