package com.google.android.material.appbar;

import android.R;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;
import com.google.android.material.internal.ThemeEnforcement;

@RequiresApi
/* loaded from: classes.dex */
class ViewUtilsLollipop {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f13900a = {R.attr.stateListAnimator};

    static void a(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    static void b(View view, float f2) {
        int integer = view.getResources().getInteger(com.google.android.material.R.integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j2 = integer;
        stateListAnimator.addState(new int[]{R.attr.state_enabled, com.google.android.material.R.attr.state_liftable, -com.google.android.material.R.attr.state_lifted}, ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(j2));
        stateListAnimator.addState(new int[]{R.attr.state_enabled}, ObjectAnimator.ofFloat(view, "elevation", f2).setDuration(j2));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(0L));
        view.setStateListAnimator(stateListAnimator);
    }

    static void c(View view, AttributeSet attributeSet, int i2, int i3) {
        Context context = view.getContext();
        TypedArray i4 = ThemeEnforcement.i(context, attributeSet, f13900a, i2, i3, new int[0]);
        try {
            if (i4.hasValue(0)) {
                view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, i4.getResourceId(0, 0)));
            }
        } finally {
            i4.recycle();
        }
    }
}
