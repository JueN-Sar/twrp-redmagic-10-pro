package com.airbnb.lottie.animation.keyframe;

import android.view.animation.Interpolator;
import com.airbnb.lottie.L;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseKeyframeAnimation<K, A> {

    /* renamed from: c, reason: collision with root package name */
    private final KeyframesWrapper f9485c;

    /* renamed from: e, reason: collision with root package name */
    protected LottieValueCallback f9487e;

    /* renamed from: a, reason: collision with root package name */
    final List f9483a = new ArrayList(1);

    /* renamed from: b, reason: collision with root package name */
    private boolean f9484b = false;

    /* renamed from: d, reason: collision with root package name */
    protected float f9486d = 0.0f;

    /* renamed from: f, reason: collision with root package name */
    private Object f9488f = null;

    /* renamed from: g, reason: collision with root package name */
    private float f9489g = -1.0f;

    /* renamed from: h, reason: collision with root package name */
    private float f9490h = -1.0f;

    public interface AnimationListener {
        void a();
    }

    private static final class EmptyKeyframeWrapper<T> implements KeyframesWrapper<T> {
        private EmptyKeyframeWrapper() {
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean a(float f2) {
            throw new IllegalStateException("not implemented");
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public Keyframe b() {
            throw new IllegalStateException("not implemented");
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean c(float f2) {
            return false;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public float d() {
            return 0.0f;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public float e() {
            return 1.0f;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean isEmpty() {
            return true;
        }
    }

    private interface KeyframesWrapper<T> {
        boolean a(float f2);

        Keyframe b();

        boolean c(float f2);

        float d();

        float e();

        boolean isEmpty();
    }

    private static final class KeyframesWrapperImpl<T> implements KeyframesWrapper<T> {

        /* renamed from: a, reason: collision with root package name */
        private final List f9491a;

        /* renamed from: c, reason: collision with root package name */
        private Keyframe f9493c = null;

        /* renamed from: d, reason: collision with root package name */
        private float f9494d = -1.0f;

        /* renamed from: b, reason: collision with root package name */
        private Keyframe f9492b = f(0.0f);

        KeyframesWrapperImpl(List list) {
            this.f9491a = list;
        }

        private Keyframe f(float f2) {
            List list = this.f9491a;
            Keyframe keyframe = (Keyframe) list.get(list.size() - 1);
            if (f2 >= keyframe.f()) {
                return keyframe;
            }
            for (int size = this.f9491a.size() - 2; size >= 1; size--) {
                Keyframe keyframe2 = (Keyframe) this.f9491a.get(size);
                if (this.f9492b != keyframe2 && keyframe2.a(f2)) {
                    return keyframe2;
                }
            }
            return (Keyframe) this.f9491a.get(0);
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean a(float f2) {
            Keyframe keyframe = this.f9493c;
            Keyframe keyframe2 = this.f9492b;
            if (keyframe == keyframe2 && this.f9494d == f2) {
                return true;
            }
            this.f9493c = keyframe2;
            this.f9494d = f2;
            return false;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public Keyframe b() {
            return this.f9492b;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean c(float f2) {
            if (this.f9492b.a(f2)) {
                return !this.f9492b.i();
            }
            this.f9492b = f(f2);
            return true;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public float d() {
            return ((Keyframe) this.f9491a.get(0)).f();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public float e() {
            return ((Keyframe) this.f9491a.get(r1.size() - 1)).c();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean isEmpty() {
            return false;
        }
    }

    private static final class SingleKeyframeWrapper<T> implements KeyframesWrapper<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Keyframe f9495a;

        /* renamed from: b, reason: collision with root package name */
        private float f9496b = -1.0f;

        SingleKeyframeWrapper(List list) {
            this.f9495a = (Keyframe) list.get(0);
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean a(float f2) {
            if (this.f9496b == f2) {
                return true;
            }
            this.f9496b = f2;
            return false;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public Keyframe b() {
            return this.f9495a;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean c(float f2) {
            return !this.f9495a.i();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public float d() {
            return this.f9495a.f();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public float e() {
            return this.f9495a.c();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.KeyframesWrapper
        public boolean isEmpty() {
            return false;
        }
    }

    BaseKeyframeAnimation(List list) {
        this.f9485c = p(list);
    }

    private float g() {
        if (this.f9489g == -1.0f) {
            this.f9489g = this.f9485c.d();
        }
        return this.f9489g;
    }

    private static KeyframesWrapper p(List list) {
        return list.isEmpty() ? new EmptyKeyframeWrapper() : list.size() == 1 ? new SingleKeyframeWrapper(list) : new KeyframesWrapperImpl(list);
    }

    public void a(AnimationListener animationListener) {
        this.f9483a.add(animationListener);
    }

    protected Keyframe b() {
        L.b("BaseKeyframeAnimation#getCurrentKeyframe");
        Keyframe b2 = this.f9485c.b();
        L.c("BaseKeyframeAnimation#getCurrentKeyframe");
        return b2;
    }

    float c() {
        if (this.f9490h == -1.0f) {
            this.f9490h = this.f9485c.e();
        }
        return this.f9490h;
    }

    protected float d() {
        Keyframe b2 = b();
        if (b2 == null || b2.i()) {
            return 0.0f;
        }
        return b2.f9944d.getInterpolation(e());
    }

    float e() {
        if (this.f9484b) {
            return 0.0f;
        }
        Keyframe b2 = b();
        if (b2.i()) {
            return 0.0f;
        }
        return (this.f9486d - b2.f()) / (b2.c() - b2.f());
    }

    public float f() {
        return this.f9486d;
    }

    public Object h() {
        float e2 = e();
        if (this.f9487e == null && this.f9485c.a(e2)) {
            return this.f9488f;
        }
        Keyframe b2 = b();
        Interpolator interpolator = b2.f9945e;
        Object i2 = (interpolator == null || b2.f9946f == null) ? i(b2, d()) : j(b2, e2, interpolator.getInterpolation(e2), b2.f9946f.getInterpolation(e2));
        this.f9488f = i2;
        return i2;
    }

    abstract Object i(Keyframe keyframe, float f2);

    protected Object j(Keyframe keyframe, float f2, float f3, float f4) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    public boolean k() {
        return this.f9487e != null;
    }

    public void l() {
        L.b("BaseKeyframeAnimation#notifyListeners");
        for (int i2 = 0; i2 < this.f9483a.size(); i2++) {
            ((AnimationListener) this.f9483a.get(i2)).a();
        }
        L.c("BaseKeyframeAnimation#notifyListeners");
    }

    public void m() {
        this.f9484b = true;
    }

    public void n(float f2) {
        L.b("BaseKeyframeAnimation#setProgress");
        if (this.f9485c.isEmpty()) {
            L.c("BaseKeyframeAnimation#setProgress");
            return;
        }
        if (f2 < g()) {
            f2 = g();
        } else if (f2 > c()) {
            f2 = c();
        }
        if (f2 == this.f9486d) {
            L.c("BaseKeyframeAnimation#setProgress");
            return;
        }
        this.f9486d = f2;
        if (this.f9485c.c(f2)) {
            l();
        }
        L.c("BaseKeyframeAnimation#setProgress");
    }

    public void o(LottieValueCallback lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.f9487e;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.c(null);
        }
        this.f9487e = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.c(this);
        }
    }
}
