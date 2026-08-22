package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.airbnb.lottie.LottieComposition;

/* loaded from: classes.dex */
public class Keyframe<T> {

    /* renamed from: a, reason: collision with root package name */
    private final LottieComposition f9941a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f9942b;

    /* renamed from: c, reason: collision with root package name */
    public Object f9943c;

    /* renamed from: d, reason: collision with root package name */
    public final Interpolator f9944d;

    /* renamed from: e, reason: collision with root package name */
    public final Interpolator f9945e;

    /* renamed from: f, reason: collision with root package name */
    public final Interpolator f9946f;

    /* renamed from: g, reason: collision with root package name */
    public final float f9947g;

    /* renamed from: h, reason: collision with root package name */
    public Float f9948h;

    /* renamed from: i, reason: collision with root package name */
    private float f9949i;

    /* renamed from: j, reason: collision with root package name */
    private float f9950j;

    /* renamed from: k, reason: collision with root package name */
    private int f9951k;

    /* renamed from: l, reason: collision with root package name */
    private int f9952l;

    /* renamed from: m, reason: collision with root package name */
    private float f9953m;

    /* renamed from: n, reason: collision with root package name */
    private float f9954n;

    /* renamed from: o, reason: collision with root package name */
    public PointF f9955o;

    /* renamed from: p, reason: collision with root package name */
    public PointF f9956p;

    public Keyframe(LottieComposition lottieComposition, Object obj, Object obj2, Interpolator interpolator, float f2, Float f3) {
        this.f9949i = -3987645.8f;
        this.f9950j = -3987645.8f;
        this.f9951k = 784923401;
        this.f9952l = 784923401;
        this.f9953m = Float.MIN_VALUE;
        this.f9954n = Float.MIN_VALUE;
        this.f9955o = null;
        this.f9956p = null;
        this.f9941a = lottieComposition;
        this.f9942b = obj;
        this.f9943c = obj2;
        this.f9944d = interpolator;
        this.f9945e = null;
        this.f9946f = null;
        this.f9947g = f2;
        this.f9948h = f3;
    }

    public boolean a(float f2) {
        return f2 >= f() && f2 < c();
    }

    public Keyframe b(Object obj, Object obj2) {
        return new Keyframe(obj, obj2);
    }

    public float c() {
        if (this.f9941a == null) {
            return 1.0f;
        }
        if (this.f9954n == Float.MIN_VALUE) {
            if (this.f9948h == null) {
                this.f9954n = 1.0f;
            } else {
                this.f9954n = f() + ((this.f9948h.floatValue() - this.f9947g) / this.f9941a.e());
            }
        }
        return this.f9954n;
    }

    public float d() {
        if (this.f9950j == -3987645.8f) {
            this.f9950j = ((Float) this.f9943c).floatValue();
        }
        return this.f9950j;
    }

    public int e() {
        if (this.f9952l == 784923401) {
            this.f9952l = ((Integer) this.f9943c).intValue();
        }
        return this.f9952l;
    }

    public float f() {
        LottieComposition lottieComposition = this.f9941a;
        if (lottieComposition == null) {
            return 0.0f;
        }
        if (this.f9953m == Float.MIN_VALUE) {
            this.f9953m = (this.f9947g - lottieComposition.p()) / this.f9941a.e();
        }
        return this.f9953m;
    }

    public float g() {
        if (this.f9949i == -3987645.8f) {
            this.f9949i = ((Float) this.f9942b).floatValue();
        }
        return this.f9949i;
    }

    public int h() {
        if (this.f9951k == 784923401) {
            this.f9951k = ((Integer) this.f9942b).intValue();
        }
        return this.f9951k;
    }

    public boolean i() {
        return this.f9944d == null && this.f9945e == null && this.f9946f == null;
    }

    public String toString() {
        return "Keyframe{startValue=" + this.f9942b + ", endValue=" + this.f9943c + ", startFrame=" + this.f9947g + ", endFrame=" + this.f9948h + ", interpolator=" + this.f9944d + '}';
    }

    public Keyframe(LottieComposition lottieComposition, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, float f2, Float f3) {
        this.f9949i = -3987645.8f;
        this.f9950j = -3987645.8f;
        this.f9951k = 784923401;
        this.f9952l = 784923401;
        this.f9953m = Float.MIN_VALUE;
        this.f9954n = Float.MIN_VALUE;
        this.f9955o = null;
        this.f9956p = null;
        this.f9941a = lottieComposition;
        this.f9942b = obj;
        this.f9943c = obj2;
        this.f9944d = null;
        this.f9945e = interpolator;
        this.f9946f = interpolator2;
        this.f9947g = f2;
        this.f9948h = f3;
    }

    protected Keyframe(LottieComposition lottieComposition, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, float f2, Float f3) {
        this.f9949i = -3987645.8f;
        this.f9950j = -3987645.8f;
        this.f9951k = 784923401;
        this.f9952l = 784923401;
        this.f9953m = Float.MIN_VALUE;
        this.f9954n = Float.MIN_VALUE;
        this.f9955o = null;
        this.f9956p = null;
        this.f9941a = lottieComposition;
        this.f9942b = obj;
        this.f9943c = obj2;
        this.f9944d = interpolator;
        this.f9945e = interpolator2;
        this.f9946f = interpolator3;
        this.f9947g = f2;
        this.f9948h = f3;
    }

    public Keyframe(Object obj) {
        this.f9949i = -3987645.8f;
        this.f9950j = -3987645.8f;
        this.f9951k = 784923401;
        this.f9952l = 784923401;
        this.f9953m = Float.MIN_VALUE;
        this.f9954n = Float.MIN_VALUE;
        this.f9955o = null;
        this.f9956p = null;
        this.f9941a = null;
        this.f9942b = obj;
        this.f9943c = obj;
        this.f9944d = null;
        this.f9945e = null;
        this.f9946f = null;
        this.f9947g = Float.MIN_VALUE;
        this.f9948h = Float.valueOf(Float.MAX_VALUE);
    }

    private Keyframe(Object obj, Object obj2) {
        this.f9949i = -3987645.8f;
        this.f9950j = -3987645.8f;
        this.f9951k = 784923401;
        this.f9952l = 784923401;
        this.f9953m = Float.MIN_VALUE;
        this.f9954n = Float.MIN_VALUE;
        this.f9955o = null;
        this.f9956p = null;
        this.f9941a = null;
        this.f9942b = obj;
        this.f9943c = obj2;
        this.f9944d = null;
        this.f9945e = null;
        this.f9946f = null;
        this.f9947g = Float.MIN_VALUE;
        this.f9948h = Float.valueOf(Float.MAX_VALUE);
    }
}
