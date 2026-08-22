package androidx.dynamicanimation.animation;

import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.DynamicAnimation;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements AnimationHandler.AnimationFrameCallback {

    /* renamed from: d, reason: collision with root package name */
    final Object f3660d;

    /* renamed from: e, reason: collision with root package name */
    final FloatPropertyCompat f3661e;

    /* renamed from: j, reason: collision with root package name */
    private float f3666j;

    /* renamed from: m, reason: collision with root package name */
    private AnimationHandler f3669m;

    /* renamed from: n, reason: collision with root package name */
    public static final ViewProperty f3651n = new ViewProperty("translationX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getTranslationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setTranslationX(f2);
        }
    };

    /* renamed from: o, reason: collision with root package name */
    public static final ViewProperty f3652o = new ViewProperty("translationY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.2
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getTranslationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setTranslationY(f2);
        }
    };

    /* renamed from: p, reason: collision with root package name */
    public static final ViewProperty f3653p = new ViewProperty("translationZ") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.3
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return ViewCompat.E(view);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            ViewCompat.D0(view, f2);
        }
    };

    /* renamed from: q, reason: collision with root package name */
    public static final ViewProperty f3654q = new ViewProperty("scaleX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.4
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScaleX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScaleX(f2);
        }
    };

    /* renamed from: r, reason: collision with root package name */
    public static final ViewProperty f3655r = new ViewProperty("scaleY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.5
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScaleY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScaleY(f2);
        }
    };

    /* renamed from: s, reason: collision with root package name */
    public static final ViewProperty f3656s = new ViewProperty("rotation") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.6
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getRotation();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setRotation(f2);
        }
    };
    public static final ViewProperty t = new ViewProperty("rotationX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.7
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getRotationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setRotationX(f2);
        }
    };
    public static final ViewProperty u = new ViewProperty("rotationY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.8
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getRotationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setRotationY(f2);
        }
    };
    public static final ViewProperty v = new ViewProperty("x") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.9
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setX(f2);
        }
    };
    public static final ViewProperty w = new ViewProperty("y") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.10
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setY(f2);
        }
    };
    public static final ViewProperty x = new ViewProperty("z") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.11
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return ViewCompat.H(view);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            ViewCompat.F0(view, f2);
        }
    };
    public static final ViewProperty y = new ViewProperty("alpha") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.12
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getAlpha();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setAlpha(f2);
        }
    };
    public static final ViewProperty z = new ViewProperty("scrollX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.13
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScrollX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScrollX((int) f2);
        }
    };
    public static final ViewProperty A = new ViewProperty("scrollY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.14
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScrollY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScrollY((int) f2);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    float f3657a = 0.0f;

    /* renamed from: b, reason: collision with root package name */
    float f3658b = Float.MAX_VALUE;

    /* renamed from: c, reason: collision with root package name */
    boolean f3659c = false;

    /* renamed from: f, reason: collision with root package name */
    boolean f3662f = false;

    /* renamed from: g, reason: collision with root package name */
    float f3663g = Float.MAX_VALUE;

    /* renamed from: h, reason: collision with root package name */
    float f3664h = -Float.MAX_VALUE;

    /* renamed from: i, reason: collision with root package name */
    private long f3665i = 0;

    /* renamed from: k, reason: collision with root package name */
    private final ArrayList f3667k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    private final ArrayList f3668l = new ArrayList();

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$15, reason: invalid class name */
    class AnonymousClass15 extends FloatPropertyCompat {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FloatValueHolder f3670b;

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float a(Object obj) {
            return this.f3670b.a();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void b(Object obj, float f2) {
            this.f3670b.b(f2);
        }
    }

    static class MassState {

        /* renamed from: a, reason: collision with root package name */
        float f3671a;

        /* renamed from: b, reason: collision with root package name */
        float f3672b;

        MassState() {
        }
    }

    public interface OnAnimationEndListener {
        void a(DynamicAnimation dynamicAnimation, boolean z, float f2, float f3);
    }

    public interface OnAnimationUpdateListener {
        void a(DynamicAnimation dynamicAnimation, float f2, float f3);
    }

    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        private ViewProperty(String str) {
            super(str);
        }
    }

    DynamicAnimation(Object obj, FloatPropertyCompat floatPropertyCompat) {
        this.f3660d = obj;
        this.f3661e = floatPropertyCompat;
        if (floatPropertyCompat == f3656s || floatPropertyCompat == t || floatPropertyCompat == u) {
            this.f3666j = 0.1f;
            return;
        }
        if (floatPropertyCompat == y) {
            this.f3666j = 0.00390625f;
        } else if (floatPropertyCompat == f3654q || floatPropertyCompat == f3655r) {
            this.f3666j = 0.002f;
        } else {
            this.f3666j = 1.0f;
        }
    }

    private void c(boolean z2) {
        this.f3662f = false;
        getAnimationHandler().k(this);
        this.f3665i = 0L;
        this.f3659c = false;
        for (int i2 = 0; i2 < this.f3667k.size(); i2++) {
            if (this.f3667k.get(i2) != null) {
                ((OnAnimationEndListener) this.f3667k.get(i2)).a(this, z2, this.f3658b, this.f3657a);
            }
        }
        g(this.f3667k);
    }

    private float d() {
        return this.f3661e.a(this.f3660d);
    }

    private static void g(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    private void l() {
        if (this.f3662f) {
            return;
        }
        this.f3662f = true;
        if (!this.f3659c) {
            this.f3658b = d();
        }
        float f2 = this.f3658b;
        if (f2 > this.f3663g || f2 < this.f3664h) {
            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
        }
        getAnimationHandler().d(this, 0L);
    }

    public DynamicAnimation a(OnAnimationEndListener onAnimationEndListener) {
        if (!this.f3667k.contains(onAnimationEndListener)) {
            this.f3667k.add(onAnimationEndListener);
        }
        return this;
    }

    public void b() {
        if (!getAnimationHandler().i()) {
            throw new AndroidRuntimeException("Animations may only be canceled from the same thread as the animation handler");
        }
        if (this.f3662f) {
            c(true);
        }
    }

    @Override // androidx.dynamicanimation.animation.AnimationHandler.AnimationFrameCallback
    public boolean doAnimationFrame(long j2) {
        long j3 = this.f3665i;
        if (j3 == 0) {
            this.f3665i = j2;
            h(this.f3658b);
            return false;
        }
        long j4 = j2 - j3;
        this.f3665i = j2;
        float durationScale = getAnimationHandler().getDurationScale();
        boolean m2 = m(durationScale == 0.0f ? 2147483647L : (long) (j4 / durationScale));
        float min = Math.min(this.f3658b, this.f3663g);
        this.f3658b = min;
        float max = Math.max(min, this.f3664h);
        this.f3658b = max;
        h(max);
        if (m2) {
            c(false);
        }
        return m2;
    }

    float e() {
        return this.f3666j * 0.75f;
    }

    public boolean f() {
        return this.f3662f;
    }

    @VisibleForTesting
    public AnimationHandler getAnimationHandler() {
        AnimationHandler animationHandler = this.f3669m;
        return animationHandler != null ? animationHandler : AnimationHandler.g();
    }

    void h(float f2) {
        this.f3661e.b(this.f3660d, f2);
        for (int i2 = 0; i2 < this.f3668l.size(); i2++) {
            if (this.f3668l.get(i2) != null) {
                ((OnAnimationUpdateListener) this.f3668l.get(i2)).a(this, this.f3658b, this.f3657a);
            }
        }
        g(this.f3668l);
    }

    public DynamicAnimation i(float f2) {
        this.f3658b = f2;
        this.f3659c = true;
        return this;
    }

    public DynamicAnimation j(float f2) {
        this.f3657a = f2;
        return this;
    }

    public void k() {
        if (!getAnimationHandler().i()) {
            throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
        }
        if (this.f3662f) {
            return;
        }
        l();
    }

    abstract boolean m(long j2);
}
