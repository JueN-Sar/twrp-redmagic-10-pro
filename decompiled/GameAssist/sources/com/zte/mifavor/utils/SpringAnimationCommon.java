package com.zte.mifavor.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.PathInterpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.zte.mifavor.androidx.widget.NestedScrollView;
import com.zte.mifavor.androidx.widget.RecyclerView;
import com.zte.mifavor.androidx.widget.sink.BaseSinkActivity;
import com.zte.mifavor.widget.GridView;
import com.zte.mifavor.widget.ISpringView;
import com.zte.mifavor.widget.ScrollView;

/* loaded from: classes2.dex */
public class SpringAnimationCommon {

    /* renamed from: q, reason: collision with root package name */
    public static float f17438q = 150.0f;

    /* renamed from: r, reason: collision with root package name */
    public static int f17439r = 120;

    /* renamed from: s, reason: collision with root package name */
    public static float f17440s = 1.0f;
    private static float u;

    /* renamed from: a, reason: collision with root package name */
    private View f17441a;

    /* renamed from: e, reason: collision with root package name */
    private SpringAnimation f17445e;

    /* renamed from: f, reason: collision with root package name */
    private FloatPropertyCompat f17446f;

    /* renamed from: k, reason: collision with root package name */
    private int f17451k;

    /* renamed from: l, reason: collision with root package name */
    private int f17452l;
    private static float t = ViewConfiguration.getScrollFriction();
    private static float v = (float) (Math.log(0.78d) / Math.log(0.9d));
    private static int w = 22;

    /* renamed from: b, reason: collision with root package name */
    private final float f17442b = -1.0f;

    /* renamed from: c, reason: collision with root package name */
    private int f17443c = 300;

    /* renamed from: d, reason: collision with root package name */
    private float f17444d = 1.0f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f17447g = true;

    /* renamed from: h, reason: collision with root package name */
    private float f17448h = 0.0f;

    /* renamed from: i, reason: collision with root package name */
    private boolean f17449i = false;

    /* renamed from: j, reason: collision with root package name */
    private boolean f17450j = false;

    /* renamed from: m, reason: collision with root package name */
    private boolean f17453m = true;

    /* renamed from: n, reason: collision with root package name */
    public BaseSinkActivity f17454n = null;

    /* renamed from: o, reason: collision with root package name */
    PathInterpolator f17455o = new PathInterpolator(0.25f, 0.5f, 0.4f, 1.0f);

    /* renamed from: p, reason: collision with root package name */
    private ValueAnimator f17456p = null;

    private void i(final int i2) {
        float abs = Math.abs(i2 / 767.0f) * 0.5f;
        int m2 = m(this.f17441a.getContext());
        int i3 = (int) (m2 * (1.0f + abs));
        Log.d("Z#QScroll-SpringAnim", "fling Anim in. timeFactor = " + abs + ", baseDuration = " + m2 + ", duration = " + i3);
        try {
            ValueAnimator ofInt = ValueAnimator.ofInt(0, i2);
            this.f17456p = ofInt;
            if (ofInt != null) {
                ofInt.setDuration(i3);
                this.f17456p.setInterpolator(this.f17455o);
                this.f17456p.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.utils.SpringAnimationCommon.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        SpringAnimationCommon.this.f17441a.setTranslationY(intValue);
                        if (intValue != i2 || SpringAnimationCommon.this.f17456p == null) {
                            return;
                        }
                        SpringAnimationCommon.this.f17456p.cancel();
                        SpringAnimationCommon.this.f17450j = false;
                        SpringAnimationCommon.this.f17449i = false;
                        SpringAnimationCommon.this.p().k();
                    }
                });
                Log.d("Z#QScroll-SpringAnim", "+++++++++++ Start Fling Anim nAnim, toY = " + i2);
                this.f17456p.start();
            }
        } catch (Exception e2) {
            Log.e("Z#QScroll-SpringAnim", "fling Anim error, e = ", e2);
        }
    }

    public static int m(Context context) {
        float d2 = UIUtils.d(context);
        if (d2 > 1.0f) {
            return 150;
        }
        if (d2 < 1.0f) {
            return 100;
        }
        return f17439r;
    }

    private double n(int i2) {
        u = this.f17441a.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        return Math.log((Math.abs(i2) * 0.35f) / (t * u));
    }

    private double o(int i2) {
        double n2 = n(i2);
        float f2 = v;
        return t * u * Math.exp((f2 / (f2 - 1.0d)) * n2);
    }

    public static float q(Context context) {
        float d2 = UIUtils.d(context);
        if (d2 > 1.0f) {
            return 120.0f;
        }
        if (d2 < 1.0f) {
            return 180.0f;
        }
        return f17438q;
    }

    public void A(int i2) {
        f17439r = i2;
    }

    public void B(boolean z) {
        this.f17453m = z;
        View view = this.f17441a;
        if (view != null && z) {
            view.setOverScrollMode(2);
        }
        Log.d("Z#QScroll-SpringAnim", "setmIsUseSpring mIsUseSpring = " + this.f17453m);
    }

    public void C(int i2) {
        this.f17452l = Math.abs(i2);
    }

    public void D(float f2) {
        this.f17444d = f2;
        Log.d("Z#QScroll-SpringAnim", "setSlipAmplitude slipAmplitude = " + f2);
    }

    public void E(View view, FloatPropertyCompat floatPropertyCompat, float f2) {
        this.f17446f = floatPropertyCompat;
        if (DynamicAnimation.f3651n.equals(floatPropertyCompat)) {
            this.f17447g = false;
        } else {
            this.f17447g = true;
        }
        float q2 = q(view.getContext());
        SpringAnimation springAnimation = new SpringAnimation(this.f17441a, floatPropertyCompat, f2);
        this.f17445e = springAnimation;
        springAnimation.p().f(q2);
        this.f17445e.p().d(f17440s);
        Log.w("Z#QScroll-SpringAnim", "set Spring Animation Property. stiffness = " + q2 + ", mDampingRatio = " + f17440s);
        this.f17445e.a(new DynamicAnimation.OnAnimationEndListener() { // from class: com.zte.mifavor.utils.SpringAnimationCommon.1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void a(DynamicAnimation dynamicAnimation, boolean z, float f3, float f4) {
                Log.d("Z#QScroll-SpringAnim", "====================== onAnimationEnd. mSpringAnimation  v = " + f3 + ", b = " + z);
                if (z) {
                    SpringAnimationCommon.this.f17445e.n(0.0f);
                    SpringAnimationCommon.this.f17441a.setTranslationY(0.0f);
                    SpringAnimationCommon.this.f17441a.setTranslationX(0.0f);
                }
            }
        });
    }

    public void F(float f2) {
        f17438q = f2;
        SpringAnimation springAnimation = this.f17445e;
        if (springAnimation == null || springAnimation.p() == null) {
            return;
        }
        this.f17445e.p().f(f17438q);
        Log.d("Z#QScroll-SpringAnim", "setStiffness mStiffness = " + f17438q);
    }

    public void f() {
        this.f17450j = false;
        this.f17449i = false;
        this.f17452l = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0018, code lost:
    
        if (r5 < 33.0f) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001a, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0024, code lost:
    
        if (r5 < 0.0f) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(float r5) {
        /*
            r4 = this;
            r0 = 1145569280(0x44480000, float:800.0)
            int r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r1 <= 0) goto L7
            goto L8
        L7:
            r0 = r5
        L8:
            r1 = -1001914368(0xffffffffc4480000, float:-800.0)
            int r2 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r2 >= 0) goto Lf
            r0 = r1
        Lf:
            r1 = 0
            int r2 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r2 >= 0) goto L1c
            r2 = 1107558400(0x42040000, float:33.0)
            int r3 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r3 >= 0) goto L1c
        L1a:
            r0 = r2
            goto L27
        L1c:
            r2 = -1039925248(0xffffffffc2040000, float:-33.0)
            int r3 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r3 >= 0) goto L27
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 >= 0) goto L27
            goto L1a
        L27:
            android.view.View r5 = r4.f17441a
            r5.getTranslationY()
            android.animation.ValueAnimator r5 = r4.f17456p
            if (r5 == 0) goto L40
            boolean r5 = r5.isRunning()
            if (r5 == 0) goto L40
            android.animation.ValueAnimator r5 = r4.f17456p
            r5.cancel()
            android.view.View r5 = r4.f17441a
            r5.setTranslationY(r1)
        L40:
            androidx.dynamicanimation.animation.SpringAnimation r5 = r4.p()
            boolean r5 = r5.f()
            if (r5 == 0) goto L58
            androidx.dynamicanimation.animation.SpringAnimation r5 = r4.p()
            r5.t()
            androidx.dynamicanimation.animation.SpringAnimation r5 = r4.p()
            r5.b()
        L58:
            int r5 = (int) r0
            r4.i(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.utils.SpringAnimationCommon.g(float):void");
    }

    public void h(int i2) {
        if (!this.f17453m) {
            Log.w("Z#QScroll-SpringAnim", "fling. mIsUseSpring = " + this.f17453m);
            return;
        }
        if (i2 > 0) {
            this.f17450j = true;
            this.f17449i = false;
        } else if (i2 < 0) {
            this.f17449i = true;
            this.f17450j = false;
        }
        this.f17451k = i2;
    }

    public void j(Context context) {
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof BaseSinkActivity) {
            this.f17454n = (BaseSinkActivity) context;
            return;
        }
        Log.d("Z#QScroll-SpringAnim", "getActivityByContext isn't BaseSinkActivity. context = " + context);
        this.f17454n = null;
    }

    public int k() {
        BaseSinkActivity baseSinkActivity = this.f17454n;
        if (baseSinkActivity != null) {
            return baseSinkActivity.E0();
        }
        return 3;
    }

    public float l(View view, int i2) {
        float f2 = 0.0f;
        if (!this.f17453m || Math.abs(this.f17451k) < 2000) {
            Log.w("Z#QScroll-SpringAnim", "get AppBar Spring Distance abort, mIsUseSpring=" + this.f17453m + ", mFlingVelocity=" + this.f17451k);
            return 0.0f;
        }
        boolean canScrollVertically = this.f17441a.canScrollVertically(-1);
        boolean canScrollVertically2 = this.f17441a.canScrollVertically(-1);
        if (view == null || ((canScrollVertically || !this.f17449i) && (canScrollVertically || canScrollVertically2 || !this.f17449i))) {
            Log.e("Z#QScroll-SpringAnim", "get AppBar Spring Distance warning, canToTop=" + canScrollVertically + ", canToBottom=" + canScrollVertically2 + ", mIsToTopFling=" + this.f17449i);
        } else {
            double o2 = o(this.f17451k);
            if (1000 == i2) {
                if (((RecyclerView) this.f17441a).getIsBeingDragged()) {
                    Log.w("Z#QScroll-SpringAnim", "get AppBar Spring Distance ++++, RecyclerView is Dragged, and do nothing.");
                    return 0.0f;
                }
                int i3 = this.f17452l;
                if (o2 > i3) {
                    f2 = (((float) Math.max(0.0d, o2 - i3)) * this.f17444d) / w;
                }
            } else if (1003 == i2 || 1004 == i2) {
                View view2 = this.f17441a;
                if ((view2 instanceof ScrollView) && ((ScrollView) view2).getIsBeingDragged()) {
                    return 0.0f;
                }
                View view3 = this.f17441a;
                if ((view3 instanceof NestedScrollView) && ((NestedScrollView) view3).getIsBeingDragged()) {
                    return 0.0f;
                }
                int i4 = this.f17452l;
                if (o2 > i4) {
                    f2 = (int) ((((float) Math.max(0.0d, o2 - i4)) * this.f17444d) / 25.0f);
                }
            }
        }
        float f3 = f2 <= 800.0f ? f2 : 800.0f;
        if (f2 < -800.0f) {
            return -800.0f;
        }
        return f3;
    }

    public SpringAnimation p() {
        return this.f17445e;
    }

    public void r(View view) {
        this.f17441a = view;
        view.setOverScrollMode(2);
    }

    public void s(View view, FloatPropertyCompat floatPropertyCompat, float f2) {
        r(view);
        E(view, floatPropertyCompat, f2);
    }

    public boolean t() {
        BaseSinkActivity baseSinkActivity = this.f17454n;
        return baseSinkActivity == null || 2 == baseSinkActivity.E0();
    }

    public boolean u() {
        BaseSinkActivity baseSinkActivity = this.f17454n;
        return baseSinkActivity == null || 1 == baseSinkActivity.E0();
    }

    public boolean v() {
        BaseSinkActivity baseSinkActivity = this.f17454n;
        if (baseSinkActivity != null) {
            return baseSinkActivity.F0();
        }
        return true;
    }

    public boolean w() {
        BaseSinkActivity baseSinkActivity = this.f17454n;
        return (baseSinkActivity == null || baseSinkActivity.E0() == 0) ? false : true;
    }

    public void x(View view, int i2) {
        if (!this.f17453m) {
            Log.w("Z#QScroll-SpringAnim", "onScrollStateChanged abort, mIsUseSpring = " + this.f17453m);
            return;
        }
        if (!this.f17450j && !this.f17449i) {
            Log.w("Z#QScroll-SpringAnim", "onScrollStateChanged abort, drag and the fling is not available, +++do thing+++ mIsToBottomFling=" + this.f17450j + ", mIsToTopFling=" + this.f17449i);
            this.f17451k = 0;
            return;
        }
        this.f17441a.getLayoutParams();
        boolean canScrollVertically = this.f17441a.canScrollVertically(1);
        boolean canScrollVertically2 = this.f17441a.canScrollVertically(-1);
        if ((!canScrollVertically2 && !canScrollVertically) || (canScrollVertically2 && canScrollVertically)) {
            if (this.f17441a instanceof RecyclerView) {
                this.f17450j = false;
                this.f17449i = false;
                return;
            }
            return;
        }
        this.f17441a.getTranslationY();
        if (view != null) {
            if ((canScrollVertically || !this.f17450j) && (canScrollVertically2 || !this.f17449i)) {
                return;
            }
            double o2 = o(this.f17451k);
            if (1000 == i2) {
                if (((RecyclerView) this.f17441a).getIsBeingDragged()) {
                    Log.w("Z#QScroll-SpringAnim", "onScrollStateChanged ++++, RecyclerView is Dragged, and do nothing.");
                    return;
                }
                int i3 = this.f17452l;
                if (o2 <= i3) {
                    if (this.f17450j) {
                        g(-33.0f);
                        return;
                    } else {
                        g(33.0f);
                        return;
                    }
                }
                float max = ((float) Math.max(0.0d, o2 - i3)) * this.f17444d;
                if (this.f17450j) {
                    g(((-max) / w) * 1.2f);
                    return;
                } else {
                    g(max / w);
                    return;
                }
            }
            if (1001 == i2 || 1002 == i2) {
                KeyEvent.Callback callback = this.f17441a;
                if ((callback instanceof ISpringView) && ((ISpringView) callback).getIsBeingDragged()) {
                    Log.w("Z#QScroll-SpringAnim", "onScrollStateChanged+++++ listView is Dragged, and do nothing.");
                    return;
                }
                View view2 = this.f17441a;
                if ((view2 instanceof GridView) && ((GridView) view2).getIsBeingDragged()) {
                    Log.w("Z#QScroll-SpringAnim", "onScrollStateChanged+++++ GridView is Dragged, and do nothing.");
                    return;
                }
                float abs = Math.abs((float) (o2 - this.f17452l)) * this.f17444d;
                if (this.f17450j) {
                    g((-abs) / 30.0f);
                    return;
                } else {
                    g(abs / 30.0f);
                    return;
                }
            }
            if (1003 == i2 || 1004 == i2) {
                View view3 = this.f17441a;
                if ((view3 instanceof ScrollView) && ((ScrollView) view3).getIsBeingDragged()) {
                    return;
                }
                View view4 = this.f17441a;
                if ((view4 instanceof NestedScrollView) && ((NestedScrollView) view4).getIsBeingDragged()) {
                    return;
                }
                int i4 = this.f17452l;
                if (o2 <= i4) {
                    if (this.f17450j) {
                        g(-33.0f);
                        return;
                    } else {
                        g(33.0f);
                        return;
                    }
                }
                int max2 = (int) ((((float) Math.max(0.0d, o2 - i4)) * this.f17444d) / 30.0f);
                if (this.f17450j) {
                    g(-max2);
                } else {
                    g(max2);
                }
            }
        }
    }

    public void y(float f2) {
        f17440s = f2;
        SpringAnimation springAnimation = this.f17445e;
        if (springAnimation == null || springAnimation.p() == null) {
            return;
        }
        this.f17445e.p().d(f17440s);
        Log.d("Z#QScroll-SpringAnim", "setDampingRatio dampingRatio = " + f2);
    }

    public void z(int i2) {
        this.f17443c = i2;
        Log.d("Z#QScroll-SpringAnim", "setDragAmplitude EDGE_DRAG_MAX_DISTANCE = " + this.f17443c);
    }
}
