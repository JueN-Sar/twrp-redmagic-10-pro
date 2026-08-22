package com.zte.mifavor.animation;

import android.util.AndroidRuntimeException;
import android.view.View;
import com.zte.mifavor.animation.AnimationHandler;
import com.zte.mifavor.animation.DynamicAnimation;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements AnimationHandler.AnimationFrameCallback {

    /* renamed from: a, reason: collision with root package name */
    private AnimationHandler f17277a;

    /* renamed from: b, reason: collision with root package name */
    private final ArrayList f17278b;

    /* renamed from: c, reason: collision with root package name */
    private long f17279c;

    /* renamed from: d, reason: collision with root package name */
    float f17280d;

    /* renamed from: e, reason: collision with root package name */
    float f17281e;

    /* renamed from: f, reason: collision with root package name */
    private float f17282f;

    /* renamed from: g, reason: collision with root package name */
    final FloatPropertyCompat f17283g;

    /* renamed from: h, reason: collision with root package name */
    boolean f17284h;

    /* renamed from: i, reason: collision with root package name */
    boolean f17285i;

    /* renamed from: j, reason: collision with root package name */
    final Object f17286j;

    /* renamed from: k, reason: collision with root package name */
    private final ArrayList f17287k;

    /* renamed from: l, reason: collision with root package name */
    float f17288l;

    /* renamed from: m, reason: collision with root package name */
    float f17289m;

    /* renamed from: n, reason: collision with root package name */
    public static final ViewProperty f17271n = new ViewProperty("translationX") { // from class: com.zte.mifavor.animation.DynamicAnimation.1
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getTranslationX();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setTranslationX(f2);
        }
    };

    /* renamed from: o, reason: collision with root package name */
    public static final ViewProperty f17272o = new ViewProperty("translationY") { // from class: com.zte.mifavor.animation.DynamicAnimation.2
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getTranslationY();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setTranslationY(f2);
        }
    };

    /* renamed from: p, reason: collision with root package name */
    public static final ViewProperty f17273p = new ViewProperty("translationZ") { // from class: com.zte.mifavor.animation.DynamicAnimation.3
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getTranslationZ();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setTranslationZ(f2);
        }
    };

    /* renamed from: q, reason: collision with root package name */
    public static final ViewProperty f17274q = new ViewProperty("scaleX") { // from class: com.zte.mifavor.animation.DynamicAnimation.4
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScaleX();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScaleX(f2);
        }
    };

    /* renamed from: r, reason: collision with root package name */
    public static final ViewProperty f17275r = new ViewProperty("scaleY") { // from class: com.zte.mifavor.animation.DynamicAnimation.5
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScaleY();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScaleY(f2);
        }
    };

    /* renamed from: s, reason: collision with root package name */
    public static final ViewProperty f17276s = new ViewProperty("rotation") { // from class: com.zte.mifavor.animation.DynamicAnimation.6
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getRotation();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setRotation(f2);
        }
    };
    public static final ViewProperty t = new ViewProperty("rotationX") { // from class: com.zte.mifavor.animation.DynamicAnimation.7
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getRotationX();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setRotationX(f2);
        }
    };
    public static final ViewProperty u = new ViewProperty("rotationY") { // from class: com.zte.mifavor.animation.DynamicAnimation.8
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getRotationY();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setRotationY(f2);
        }
    };
    public static final ViewProperty v = new ViewProperty("x") { // from class: com.zte.mifavor.animation.DynamicAnimation.9
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getX();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setX(f2);
        }
    };
    public static final ViewProperty w = new ViewProperty("y") { // from class: com.zte.mifavor.animation.DynamicAnimation.10
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getY();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setY(f2);
        }
    };
    public static final ViewProperty x = new ViewProperty("z") { // from class: com.zte.mifavor.animation.DynamicAnimation.11
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getZ();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setZ(f2);
        }
    };
    public static final ViewProperty y = new ViewProperty("alpha") { // from class: com.zte.mifavor.animation.DynamicAnimation.12
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getAlpha();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setAlpha(f2);
        }
    };
    public static final ViewProperty z = new ViewProperty("scrollX") { // from class: com.zte.mifavor.animation.DynamicAnimation.13
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScrollX();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScrollX((int) f2);
        }
    };
    public static final ViewProperty A = new ViewProperty("scrollY") { // from class: com.zte.mifavor.animation.DynamicAnimation.14
        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(View view) {
            return view.getScrollY();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(View view, float f2) {
            view.setScrollY((int) f2);
        }
    };

    /* renamed from: com.zte.mifavor.animation.DynamicAnimation$15, reason: invalid class name */
    class AnonymousClass15 extends FloatPropertyCompat {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FloatValueHolder f17290b;

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        public float a(Object obj) {
            return this.f17290b.a();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        public void b(Object obj, float f2) {
            this.f17290b.b(f2);
        }
    }

    static class MassState {

        /* renamed from: a, reason: collision with root package name */
        float f17291a;

        /* renamed from: b, reason: collision with root package name */
        float f17292b;

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

    private void a(boolean z2) {
        this.f17284h = false;
        b().j(this);
        this.f17279c = 0L;
        this.f17285i = false;
        for (int i2 = 0; i2 < this.f17278b.size(); i2++) {
            if (this.f17278b.get(i2) != null) {
                ((OnAnimationEndListener) this.f17278b.get(i2)).a(this, z2, this.f17288l, this.f17289m);
            }
        }
        f(this.f17278b);
    }

    private float c() {
        return this.f17283g.a(this.f17286j);
    }

    private static void f(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    private void i() {
        if (this.f17284h) {
            return;
        }
        this.f17284h = true;
        if (!this.f17285i) {
            this.f17288l = c();
        }
        float f2 = this.f17288l;
        if (f2 > this.f17280d || f2 < this.f17281e) {
            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
        }
        b().d(this, 0L);
    }

    public AnimationHandler b() {
        if (this.f17277a == null) {
            this.f17277a = AnimationHandler.g();
        }
        return this.f17277a;
    }

    public float d() {
        return this.f17282f * 0.75f;
    }

    @Override // com.zte.mifavor.animation.AnimationHandler.AnimationFrameCallback
    public boolean doAnimationFrame(long j2) {
        long j3 = this.f17279c;
        if (j3 == 0) {
            this.f17279c = j2;
            g(this.f17288l);
            return false;
        }
        this.f17279c = j2;
        boolean j4 = j(j2 - j3);
        float min = Math.min(this.f17288l, this.f17280d);
        this.f17288l = min;
        float max = Math.max(min, this.f17281e);
        this.f17288l = max;
        g(max);
        if (j4) {
            a(false);
        }
        return j4;
    }

    public boolean e() {
        return this.f17284h;
    }

    void g(float f2) {
        this.f17283g.b(this.f17286j, f2);
        for (int i2 = 0; i2 < this.f17287k.size(); i2++) {
            if (this.f17287k.get(i2) != null) {
                ((OnAnimationUpdateListener) this.f17287k.get(i2)).a(this, this.f17288l, this.f17289m);
            }
        }
        f(this.f17287k);
    }

    public void h() {
        if (!b().i()) {
            throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
        }
        if (this.f17284h) {
            return;
        }
        i();
    }

    abstract boolean j(long j2);
}
