package cn.nubia.gameassist.theme;

import android.animation.ValueAnimator;

/* loaded from: classes.dex */
public interface ThemeWidget {

    public static abstract class AnimatorColor implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        private int f7498c;

        /* renamed from: h, reason: collision with root package name */
        private int f7499h;

        /* renamed from: i, reason: collision with root package name */
        private int f7500i;

        /* renamed from: j, reason: collision with root package name */
        private int f7501j;

        /* renamed from: k, reason: collision with root package name */
        private int f7502k;

        /* renamed from: l, reason: collision with root package name */
        private int f7503l;

        /* renamed from: m, reason: collision with root package name */
        private int f7504m;

        /* renamed from: n, reason: collision with root package name */
        private int f7505n;

        /* renamed from: o, reason: collision with root package name */
        private int f7506o;

        public abstract void a(int i2);

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i2 = (((((int) (this.f7503l + ((this.f7499h - r0) * floatValue))) & 255) << ((((int) (this.f7504m + ((this.f7500i - r1) * floatValue))) & 255) + 24)) << ((((int) (this.f7505n + ((this.f7501j - r2) * floatValue))) & 255) + 16)) << ((((int) (this.f7506o + ((this.f7502k - r3) * floatValue))) & 255) + 8);
            this.f7498c = i2;
            a(i2);
        }
    }

    default void d(Theme theme) {
    }
}
