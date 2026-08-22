package com.zte.mifavor.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes2.dex */
public class ClickScaleAnimHelper {

    /* renamed from: a, reason: collision with root package name */
    private View f17400a;

    /* renamed from: b, reason: collision with root package name */
    private String f17401b;

    /* renamed from: c, reason: collision with root package name */
    private float f17402c;

    /* renamed from: d, reason: collision with root package name */
    private float f17403d;

    /* renamed from: com.zte.mifavor.utils.ClickScaleAnimHelper$1, reason: invalid class name */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ClickScaleAnimHelper f17404c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ClickScaleAnimHelper.f(this.f17404c.f17400a, (1.0f - ((1.0f - this.f17404c.f17403d) * ((Float) valueAnimator.getAnimatedValue()).floatValue())) * this.f17404c.f17402c);
        }
    }

    /* renamed from: com.zte.mifavor.utils.ClickScaleAnimHelper$2, reason: invalid class name */
    class AnonymousClass2 extends AnimatorListenerAdapter {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            super.onAnimationEnd(animator, z);
        }
    }

    /* renamed from: com.zte.mifavor.utils.ClickScaleAnimHelper$3, reason: invalid class name */
    class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ClickScaleAnimHelper f17405c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ClickScaleAnimHelper.f(this.f17405c.f17400a, (this.f17405c.f17403d + ((1.0f - this.f17405c.f17403d) * ((Float) valueAnimator.getAnimatedValue()).floatValue())) * this.f17405c.f17402c);
        }
    }

    /* renamed from: com.zte.mifavor.utils.ClickScaleAnimHelper$4, reason: invalid class name */
    class AnonymousClass4 extends AnimatorListenerAdapter {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            super.onAnimationEnd(animator, z);
        }
    }

    /* renamed from: com.zte.mifavor.utils.ClickScaleAnimHelper$5, reason: invalid class name */
    class AnonymousClass5 implements DynamicAnimation.OnAnimationUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ClickScaleAnimHelper f17406c;

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void a(DynamicAnimation dynamicAnimation, float f2, float f3) {
            Log.d("ClickScaleAnimHelper", this.f17406c.f17401b + ", SmallToBig onAnimationUpdate value=" + f2);
            this.f17406c.f17400a.setScaleY(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(View view, float f2) {
        if (view != null) {
            view.setScaleX(f2);
            view.setScaleY(f2);
        }
    }
}
