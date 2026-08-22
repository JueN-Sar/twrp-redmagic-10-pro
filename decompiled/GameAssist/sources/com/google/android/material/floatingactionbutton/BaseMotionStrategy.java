package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
abstract class BaseMotionStrategy implements MotionStrategy {

    /* renamed from: a, reason: collision with root package name */
    private final Context f14582a;

    /* renamed from: b, reason: collision with root package name */
    private final ExtendedFloatingActionButton f14583b;

    /* renamed from: c, reason: collision with root package name */
    private final ArrayList f14584c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private final AnimatorTracker f14585d;

    /* renamed from: e, reason: collision with root package name */
    private MotionSpec f14586e;

    /* renamed from: f, reason: collision with root package name */
    private MotionSpec f14587f;

    BaseMotionStrategy(ExtendedFloatingActionButton extendedFloatingActionButton, AnimatorTracker animatorTracker) {
        this.f14583b = extendedFloatingActionButton;
        this.f14582a = extendedFloatingActionButton.getContext();
        this.f14585d = animatorTracker;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void a() {
        this.f14585d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public MotionSpec c() {
        return this.f14587f;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void e() {
        this.f14585d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void g(MotionSpec motionSpec) {
        this.f14587f = motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public AnimatorSet h() {
        return l(m());
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final List i() {
        return this.f14584c;
    }

    AnimatorSet l(MotionSpec motionSpec) {
        ArrayList arrayList = new ArrayList();
        if (motionSpec.j("opacity")) {
            arrayList.add(motionSpec.f("opacity", this.f14583b, View.ALPHA));
        }
        if (motionSpec.j("scale")) {
            arrayList.add(motionSpec.f("scale", this.f14583b, View.SCALE_Y));
            arrayList.add(motionSpec.f("scale", this.f14583b, View.SCALE_X));
        }
        if (motionSpec.j("width")) {
            arrayList.add(motionSpec.f("width", this.f14583b, ExtendedFloatingActionButton.WIDTH));
        }
        if (motionSpec.j("height")) {
            arrayList.add(motionSpec.f("height", this.f14583b, ExtendedFloatingActionButton.HEIGHT));
        }
        if (motionSpec.j("paddingStart")) {
            arrayList.add(motionSpec.f("paddingStart", this.f14583b, ExtendedFloatingActionButton.PADDING_START));
        }
        if (motionSpec.j("paddingEnd")) {
            arrayList.add(motionSpec.f("paddingEnd", this.f14583b, ExtendedFloatingActionButton.PADDING_END));
        }
        if (motionSpec.j("labelOpacity")) {
            arrayList.add(motionSpec.f("labelOpacity", this.f14583b, new Property<ExtendedFloatingActionButton, Float>(Float.class, "LABEL_OPACITY_PROPERTY") { // from class: com.google.android.material.floatingactionbutton.BaseMotionStrategy.1
                @Override // android.util.Property
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Float get(ExtendedFloatingActionButton extendedFloatingActionButton) {
                    return Float.valueOf(AnimationUtils.a(0.0f, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.originalTextCsl.getColorForState(extendedFloatingActionButton.getDrawableState(), BaseMotionStrategy.this.f14583b.originalTextCsl.getDefaultColor()))));
                }

                @Override // android.util.Property
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void set(ExtendedFloatingActionButton extendedFloatingActionButton, Float f2) {
                    int colorForState = extendedFloatingActionButton.originalTextCsl.getColorForState(extendedFloatingActionButton.getDrawableState(), BaseMotionStrategy.this.f14583b.originalTextCsl.getDefaultColor());
                    ColorStateList valueOf = ColorStateList.valueOf(Color.argb((int) (AnimationUtils.a(0.0f, Color.alpha(colorForState) / 255.0f, f2.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
                    if (f2.floatValue() == 1.0f) {
                        extendedFloatingActionButton.C(extendedFloatingActionButton.originalTextCsl);
                    } else {
                        extendedFloatingActionButton.C(valueOf);
                    }
                }
            }));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final MotionSpec m() {
        MotionSpec motionSpec = this.f14587f;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.f14586e == null) {
            this.f14586e = MotionSpec.d(this.f14582a, f());
        }
        return (MotionSpec) Preconditions.h(this.f14586e);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationStart(Animator animator) {
        this.f14585d.c(animator);
    }
}
