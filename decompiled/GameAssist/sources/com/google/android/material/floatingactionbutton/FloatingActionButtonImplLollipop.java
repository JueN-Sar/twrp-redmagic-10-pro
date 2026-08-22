package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Property;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import com.google.android.material.R;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;

@RequiresApi
/* loaded from: classes.dex */
class FloatingActionButtonImplLollipop extends FloatingActionButtonImpl {
    private StateListAnimator O;

    static class AlwaysStatefulMaterialShapeDrawable extends MaterialShapeDrawable {
        AlwaysStatefulMaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
            super(shapeAppearanceModel);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    FloatingActionButtonImplLollipop(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        super(floatingActionButton, shadowViewDelegate);
    }

    private StateListAnimator k0(float f2, float f3, float f4) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(FloatingActionButtonImpl.I, l0(f2, f4));
        stateListAnimator.addState(FloatingActionButtonImpl.J, l0(f2, f3));
        stateListAnimator.addState(FloatingActionButtonImpl.K, l0(f2, f3));
        stateListAnimator.addState(FloatingActionButtonImpl.L, l0(f2, f3));
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.w, "elevation", f2).setDuration(0L));
        arrayList.add(ObjectAnimator.ofFloat(this.w, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
        animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
        animatorSet.setInterpolator(FloatingActionButtonImpl.D);
        stateListAnimator.addState(FloatingActionButtonImpl.M, animatorSet);
        stateListAnimator.addState(FloatingActionButtonImpl.N, l0(0.0f, 0.0f));
        return stateListAnimator;
    }

    private Animator l0(float f2, float f3) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.w, "elevation", f2).setDuration(0L)).with(ObjectAnimator.ofFloat(this.w, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f3).setDuration(100L));
        animatorSet.setInterpolator(FloatingActionButtonImpl.D);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void B() {
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void D() {
        g0();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void F(int[] iArr) {
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void G(float f2, float f3, float f4) {
        if (this.w.getStateListAnimator() == this.O) {
            StateListAnimator k0 = k0(f2, f3, f4);
            this.O = k0;
            this.w.setStateListAnimator(k0);
        }
        if (a0()) {
            g0();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    boolean L() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void W(ColorStateList colorStateList) {
        Drawable drawable = this.f14638c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(RippleUtils.d(colorStateList));
        } else {
            super.W(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    boolean a0() {
        return this.x.b() || !c0();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void e0() {
    }

    BorderDrawable j0(int i2, ColorStateList colorStateList) {
        Context context = this.w.getContext();
        BorderDrawable borderDrawable = new BorderDrawable((ShapeAppearanceModel) Preconditions.h(this.f14636a));
        borderDrawable.e(ContextCompat.c(context, R.color.design_fab_stroke_top_outer_color), ContextCompat.c(context, R.color.design_fab_stroke_top_inner_color), ContextCompat.c(context, R.color.design_fab_stroke_end_inner_color), ContextCompat.c(context, R.color.design_fab_stroke_end_outer_color));
        borderDrawable.d(i2);
        borderDrawable.c(colorStateList);
        return borderDrawable;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    MaterialShapeDrawable l() {
        return new AlwaysStatefulMaterialShapeDrawable((ShapeAppearanceModel) Preconditions.h(this.f14636a));
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public float n() {
        return this.w.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void s(Rect rect) {
        if (this.x.b()) {
            super.s(rect);
        } else if (c0()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.f14646k - this.w.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    void y(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        Drawable drawable;
        MaterialShapeDrawable l2 = l();
        this.f14637b = l2;
        l2.setTintList(colorStateList);
        if (mode != null) {
            this.f14637b.setTintMode(mode);
        }
        this.f14637b.P(this.w.getContext());
        if (i2 > 0) {
            this.f14639d = j0(i2, colorStateList);
            drawable = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.h(this.f14639d), (Drawable) Preconditions.h(this.f14637b)});
        } else {
            this.f14639d = null;
            drawable = this.f14637b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.d(colorStateList2), drawable, null);
        this.f14638c = rippleDrawable;
        this.f14640e = rippleDrawable;
    }
}
