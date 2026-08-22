package com.zte.mifavor.utils;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;

/* loaded from: classes2.dex */
public class AnimationUtil {

    /* renamed from: a, reason: collision with root package name */
    private static float f17357a;

    /* renamed from: b, reason: collision with root package name */
    private static float f17358b;

    /* renamed from: c, reason: collision with root package name */
    private static float f17359c;

    /* renamed from: d, reason: collision with root package name */
    private static float f17360d;

    /* renamed from: e, reason: collision with root package name */
    private static float f17361e;

    /* renamed from: f, reason: collision with root package name */
    private static float f17362f;

    /* renamed from: g, reason: collision with root package name */
    private static int f17363g;

    /* renamed from: h, reason: collision with root package name */
    private static int f17364h;

    /* renamed from: i, reason: collision with root package name */
    private static int f17365i;

    /* renamed from: j, reason: collision with root package name */
    private static int f17366j;

    /* renamed from: k, reason: collision with root package name */
    private static float f17367k;

    /* renamed from: l, reason: collision with root package name */
    private static float f17368l;

    /* renamed from: m, reason: collision with root package name */
    private static float f17369m;

    /* renamed from: n, reason: collision with root package name */
    private static float f17370n;

    /* renamed from: o, reason: collision with root package name */
    private static int f17371o;

    /* renamed from: p, reason: collision with root package name */
    private static int f17372p;

    /* renamed from: q, reason: collision with root package name */
    public static PathInterpolator f17373q = new PathInterpolator(0.33f, 0.0f, 0.0f, 1.0f);

    /* renamed from: r, reason: collision with root package name */
    public static PathInterpolator f17374r = new PathInterpolator(0.15f, 0.73f, 0.33f, 1.0f);

    /* renamed from: s, reason: collision with root package name */
    public static PathInterpolator f17375s = new PathInterpolator(0.24f, 0.38f, 0.0f, 1.0f);

    /* renamed from: com.zte.mifavor.utils.AnimationUtil$1, reason: invalid class name */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f17376c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            Drawable background = this.f17376c.getBackground();
            if (background instanceof RoundedCornersDrawable) {
                Log.w("FB#AnimationUtil", "onAnimationUpdate, Rect radius=" + floatValue);
                ((RoundedCornersDrawable) background).a(floatValue);
                return;
            }
            Log.e("FB#AnimationUtil", "onAnimationUpdate error, Rect radius=" + floatValue + ", drawable = " + background);
        }
    }

    /* renamed from: com.zte.mifavor.utils.AnimationUtil$2, reason: invalid class name */
    class AnonymousClass2 implements Animation.AnimationListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f17377a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f17378b;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Log.d("FB#AnimationUtil", "+++ onAnimationEnd   scale isEnter=" + this.f17377a + ", scaleDuration=" + AnimationUtil.f17371o);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Log.d("FB#AnimationUtil", "+++ onAnimationStart scale isEnter=" + this.f17377a + ", isCenter=" + this.f17378b + ", scaleDuration=" + AnimationUtil.f17371o + ", delayScaleDuration=" + AnimationUtil.f17372p + ", scaleFromX=" + AnimationUtil.f17367k + ", scaleToX=" + AnimationUtil.f17369m + ", scaleFromY=" + AnimationUtil.f17368l + ", scaleToY=" + AnimationUtil.f17370n);
        }
    }

    /* renamed from: com.zte.mifavor.utils.AnimationUtil$3, reason: invalid class name */
    class AnonymousClass3 implements Animation.AnimationListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f17379a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Log.d("FB#AnimationUtil", "=== onAnimationEnd   alpha isEnter=" + this.f17379a);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Log.d("FB#AnimationUtil", "=== onAnimationStart alpha isEnter=" + this.f17379a + ", aplhaDuration=" + AnimationUtil.f17364h + ", delayAlphaDuration=" + AnimationUtil.f17363g + ", fromAlpha=" + AnimationUtil.f17361e + ", toAlpha=" + AnimationUtil.f17362f);
        }
    }

    /* renamed from: com.zte.mifavor.utils.AnimationUtil$4, reason: invalid class name */
    class AnonymousClass4 implements Animation.AnimationListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f17380a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Log.d("FB#AnimationUtil", "*** onAnimationEnd   translate isEnter=" + this.f17380a);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Log.d("FB#AnimationUtil", "*** onAnimationStart translate isEnter=" + this.f17380a + ", translateDuration=" + AnimationUtil.f17366j + ", delayTranslateDuration=" + AnimationUtil.f17365i + ", fromXDelta=" + AnimationUtil.f17357a + ", toXDelta=" + AnimationUtil.f17358b + ", fromYDelta=" + AnimationUtil.f17359c + ", toYDelta=" + AnimationUtil.f17360d);
        }
    }

    /* renamed from: com.zte.mifavor.utils.AnimationUtil$5, reason: invalid class name */
    class AnonymousClass5 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }
}
