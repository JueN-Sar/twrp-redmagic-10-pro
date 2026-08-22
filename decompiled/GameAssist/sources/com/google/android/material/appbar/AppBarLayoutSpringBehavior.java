package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.google.android.material.appbar.AppBarLayout;
import com.zte.mifavor.utils.SpringAnimationCommon;

/* loaded from: classes.dex */
public class AppBarLayoutSpringBehavior extends AppBarLayout.Behavior {
    private int A;
    private int B;
    private ValueAnimator C;
    private boolean D;
    private ValueAnimator E;
    private int F;
    private boolean G;
    private boolean H;
    private int I;
    private boolean J;
    private boolean K;
    private boolean L;
    private CoordinatorLayout M;
    private AppBarLayout N;
    private SpringAnimation O;
    private ValueAnimator P;
    private ValueAnimator Q;
    PathInterpolator w;
    PathInterpolator x;
    Interpolator y;
    private Context z;

    /* renamed from: com.google.android.material.appbar.AppBarLayoutSpringBehavior$2, reason: invalid class name */
    class AnonymousClass2 implements DynamicAnimation.OnAnimationUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ CoordinatorLayout f13860c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ AppBarLayout f13861h;

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void a(DynamicAnimation dynamicAnimation, float f2, float f3) {
            this.f13860c.p(this.f13861h);
        }
    }

    /* renamed from: com.google.android.material.appbar.AppBarLayoutSpringBehavior$3, reason: invalid class name */
    class AnonymousClass3 implements DynamicAnimation.OnAnimationEndListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AppBarLayoutSpringBehavior f13862a;

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
        public void a(DynamicAnimation dynamicAnimation, boolean z, float f2, float f3) {
            if (z) {
                this.f13862a.O.n(0.0f);
            }
        }
    }

    public AppBarLayoutSpringBehavior(Context context) {
        this.w = new PathInterpolator(0.33f, 0.0f, 0.0f, 1.0f);
        this.x = new PathInterpolator(0.33f, 0.0f, 0.2f, 1.0f);
        this.y = null;
        this.A = 0;
        this.B = 0;
        this.C = null;
        this.D = false;
        this.E = null;
        this.G = false;
        this.H = true;
        this.I = 0;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = null;
        this.N = null;
        this.P = null;
        this.Q = null;
        this.z = context;
        W0(context);
    }

    private void C0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        int R = R();
        int j0 = j0(appBarLayout, R);
        if (j0 >= 0) {
            View childAt = appBarLayout.getChildAt(j0);
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) childAt.getLayoutParams();
            int c2 = layoutParams.c();
            int i2 = -childAt.getTop();
            int i3 = -childAt.getBottom();
            if (j0 == appBarLayout.getChildCount() - 1) {
                i3 += appBarLayout.getTopInset();
            }
            if (f0(c2, 2)) {
                i3 += ViewCompat.w(childAt);
            } else if (f0(c2, 5)) {
                int w = ViewCompat.w(childAt) + i3;
                if (R < w) {
                    i2 = w;
                } else {
                    i3 = w;
                }
            }
            if (f0(c2, 32)) {
                i2 += ((LinearLayout.LayoutParams) layoutParams).topMargin;
                i3 -= ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
            }
            if (R < (i3 + i2) / 2) {
                i2 = i3;
            }
            a0(coordinatorLayout, appBarLayout, MathUtils.b(i2, -appBarLayout.getTotalScrollRange(), 0), 0.0f);
        }
    }

    private void P0(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        ValueAnimator valueAnimator = this.P;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            ValueAnimator valueAnimator2 = this.Q;
            if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
                ValueAnimator valueAnimator3 = this.C;
                if (valueAnimator3 == null) {
                    ValueAnimator valueAnimator4 = new ValueAnimator();
                    this.C = valueAnimator4;
                    valueAnimator4.setDuration(375L);
                    this.C.setInterpolator(this.x);
                    this.C.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayoutSpringBehavior.1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator5) {
                            AppBarLayoutSpringBehavior.this.f1(coordinatorLayout, appBarLayout, ((Integer) valueAnimator5.getAnimatedValue()).intValue());
                        }
                    });
                } else if (valueAnimator3.isRunning()) {
                    this.C.cancel();
                }
                this.C.setIntValues(this.A, 0);
                this.C.start();
            }
        }
    }

    private void Q0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        if (this.A > 0) {
            this.D = true;
            P0(coordinatorLayout, appBarLayout);
        }
    }

    private int T0(AppBarLayout appBarLayout, int i2, float f2) {
        int abs = Math.abs(R() - i2);
        float abs2 = Math.abs(f2);
        return Math.min(abs2 > 0.0f ? Math.round((abs / abs2) * 1000.0f) * 3 : (int) (((abs / appBarLayout.getHeight()) + 1.0f) * 150.0f), 250);
    }

    private int U0(AppBarLayout appBarLayout) {
        int childCount = appBarLayout.getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = appBarLayout.getChildAt(i3);
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) childAt.getLayoutParams();
            i2 += childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
        }
        return i2;
    }

    private void W0(Context context) {
        if (X0()) {
            try {
                this.f13877k = new OverScroller(context);
            } catch (Throwable th) {
                Log.e("BSZ#AppBarLySpringBehavior", "initOverScroller Throwable", th);
            }
        }
    }

    private static boolean X0() {
        try {
            return SystemProperties.getBoolean("ro.vendor.feature.mfv_feature_smartslide", false);
        } catch (Exception e2) {
            Log.e("BSZ#AppBarLySpringBehavior", "isSmartSlideFeatureEnabled Exception", e2);
            return false;
        }
    }

    private void a0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, float f2) {
        int abs = Math.abs(R() - i2);
        float abs2 = Math.abs(f2);
        b0(coordinatorLayout, appBarLayout, i2, abs2 > 0.0f ? Math.round((abs / abs2) * 1000.0f) * 3 : (int) (((abs / appBarLayout.getHeight()) + 1.0f) * 150.0f));
    }

    private void a1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
        if (i2 == this.A) {
            return;
        }
        this.A = i2;
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).height = this.B + i2;
        appBarLayout.setLayoutParams(layoutParams);
        coordinatorLayout.p(appBarLayout);
    }

    private void b0(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int i2, int i3) {
        int R = R();
        if (R == i2) {
            ValueAnimator valueAnimator = this.E;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return;
            }
            this.E.cancel();
            return;
        }
        ValueAnimator valueAnimator2 = this.P;
        if (valueAnimator2 != null) {
            valueAnimator2.isRunning();
        }
        ValueAnimator valueAnimator3 = this.Q;
        if (valueAnimator3 != null) {
            valueAnimator3.isRunning();
        }
        ValueAnimator valueAnimator4 = this.E;
        if (valueAnimator4 == null) {
            ValueAnimator valueAnimator5 = new ValueAnimator();
            this.E = valueAnimator5;
            valueAnimator5.setInterpolator(this.x);
            this.E.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayoutSpringBehavior.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator6) {
                    AppBarLayoutSpringBehavior.this.U(coordinatorLayout, appBarLayout, ((Integer) valueAnimator6.getAnimatedValue()).intValue());
                }
            });
        } else {
            valueAnimator4.cancel();
        }
        this.E.setDuration(i3 * 2);
        this.E.setIntValues(R, i2);
        this.E.start();
    }

    private int b1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4, int i5) {
        int R = R();
        int i6 = this.A;
        if (i6 != 0 && i2 < 0) {
            int i7 = i6 + i2;
            h1(coordinatorLayout, appBarLayout, i7 >= 0 ? i7 : 0);
            return R() - i2;
        }
        if (i6 > 0 && appBarLayout.getHeight() >= this.B && i2 > 0) {
            return e1(coordinatorLayout, appBarLayout, i5, i2);
        }
        if (i3 == 0 || R < i3 || R > i4) {
            return super.V(coordinatorLayout, appBarLayout, i2, i3, i4);
        }
        int b2 = MathUtils.b(i2, i3, i4);
        if (R != b2 || (R == b2 && i5 == -1)) {
            return super.V(coordinatorLayout, appBarLayout, i2, i3, i4);
        }
        if (R != i3) {
            return e1(coordinatorLayout, appBarLayout, i5, i2);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c1() {
        final int i2 = ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) this.N.getLayoutParams())).height;
        try {
            int T0 = T0(this.N, i2 - this.B, 0.0f) * 2;
            ValueAnimator ofInt = ValueAnimator.ofInt(i2, this.B);
            this.Q = ofInt;
            if (ofInt != null) {
                ofInt.setDuration(T0);
                this.Q.setInterpolator(this.x);
                this.Q.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayoutSpringBehavior.6
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior = AppBarLayoutSpringBehavior.this;
                        appBarLayoutSpringBehavior.d1(appBarLayoutSpringBehavior.M, AppBarLayoutSpringBehavior.this.N, intValue);
                        if (intValue != AppBarLayoutSpringBehavior.this.B || AppBarLayoutSpringBehavior.this.P == null) {
                            return;
                        }
                        AppBarLayoutSpringBehavior.this.Q.cancel();
                        AppBarLayoutSpringBehavior.this.Q = null;
                    }
                });
                this.Q.start();
            }
        } catch (Exception e2) {
            Log.e("BSZ#AppBarLySpringBehavior", "Custom Spring Anim error, e = ", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).height = i2;
        appBarLayout.setLayoutParams(layoutParams);
        coordinatorLayout.p(appBarLayout);
    }

    private int e1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3) {
        if (appBarLayout.getHeight() >= this.B && i2 == 1) {
            return i3;
        }
        i1(coordinatorLayout, appBarLayout, this.A + ((i3 * 4) / 5), i2);
        return R() - i3;
    }

    private static boolean f0(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
        if (appBarLayout.getHeight() < this.B || i2 < 0) {
            return;
        }
        a1(coordinatorLayout, appBarLayout, i2);
    }

    private void g1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3) {
        int height;
        int i4;
        if (this.B > 0 && (height = appBarLayout.getHeight()) >= (i4 = this.B) && i2 >= 0) {
            if (this.A <= 0 || i3 != -1 || height <= i4) {
                a1(coordinatorLayout, appBarLayout, i2);
            }
        }
    }

    private void h1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
        ValueAnimator valueAnimator = this.C;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.C.cancel();
        }
        f1(coordinatorLayout, appBarLayout, i2);
    }

    private void i1(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3) {
        ValueAnimator valueAnimator = this.C;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.C.cancel();
        }
        g1(coordinatorLayout, appBarLayout, i2, i3);
    }

    private int j0(AppBarLayout appBarLayout, int i2) {
        int childCount = appBarLayout.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = appBarLayout.getChildAt(i3);
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) childAt.getLayoutParams();
            if (f0(layoutParams.c(), 32)) {
                top -= ((LinearLayout.LayoutParams) layoutParams).topMargin;
                bottom += ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
            }
            int i4 = -i2;
            if (top <= i4 && bottom >= i4) {
                return i3;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, com.google.android.material.appbar.HeaderBehavior
    /* renamed from: A0 */
    public int V(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4) {
        return b1(coordinatorLayout, appBarLayout, i2, i3, i4, -1);
    }

    public void R0(int i2) {
        ValueAnimator valueAnimator = this.E;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.E.cancel();
        }
        ValueAnimator valueAnimator2 = this.C;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.C.cancel();
        }
        T0(this.N, i2, 0.0f);
        int m2 = (int) (SpringAnimationCommon.m(this.z) * ((Math.abs(i2 / 767.0f) * 0.5f) + 1.0f));
        final int i3 = ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) this.N.getLayoutParams())).height;
        final int i4 = i2 + i3;
        try {
            ValueAnimator ofInt = ValueAnimator.ofInt(i3, i4);
            this.P = ofInt;
            if (ofInt != null) {
                ofInt.setDuration(m2);
                this.P.setInterpolator(this.w);
                this.P.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayoutSpringBehavior.5
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                        int intValue = ((Integer) valueAnimator3.getAnimatedValue()).intValue();
                        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior = AppBarLayoutSpringBehavior.this;
                        appBarLayoutSpringBehavior.d1(appBarLayoutSpringBehavior.M, AppBarLayoutSpringBehavior.this.N, intValue);
                        if (intValue != i4 || AppBarLayoutSpringBehavior.this.P == null) {
                            return;
                        }
                        AppBarLayoutSpringBehavior.this.P.cancel();
                        AppBarLayoutSpringBehavior.this.P = null;
                        AppBarLayoutSpringBehavior.this.c1();
                    }
                });
                this.P.start();
            }
        } catch (Exception e2) {
            Log.e("BSZ#AppBarLySpringBehavior", "fling Anim error, e = ", e2);
        }
    }

    public boolean S0() {
        ValueAnimator valueAnimator = this.P;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            return true;
        }
        ValueAnimator valueAnimator2 = this.Q;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            return true;
        }
        ValueAnimator valueAnimator3 = this.E;
        if (valueAnimator3 != null && valueAnimator3.isRunning()) {
            return true;
        }
        ValueAnimator valueAnimator4 = this.C;
        return valueAnimator4 != null && valueAnimator4.isRunning();
    }

    public boolean V0() {
        return this.G;
    }

    @Override // com.google.android.material.appbar.AppBarLayout.Behavior, com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /* renamed from: Y0, reason: merged with bridge method [inline-methods] */
    public boolean I(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.L = false;
            this.K = false;
        } else if (actionMasked == 1) {
            this.K = true;
        } else if (actionMasked == 2) {
            this.L = true;
        } else if (actionMasked == 3) {
            if (this.L && !this.K) {
                C0(coordinatorLayout, appBarLayout);
            }
            this.L = false;
            this.K = false;
        }
        return super.I(coordinatorLayout, appBarLayout, motionEvent);
    }

    public void Z0(boolean z) {
        this.H = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, com.google.android.material.appbar.HeaderBehavior
    /* renamed from: o0 */
    public void S(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        super.S(coordinatorLayout, appBarLayout);
        C0(coordinatorLayout, appBarLayout);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /* renamed from: q0 */
    public boolean r(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4, int i5) {
        boolean r2 = super.r(coordinatorLayout, appBarLayout, i2, i3, i4, i5);
        if (this.B == 0 && appBarLayout.getHeight() != 0) {
            this.B = U0(appBarLayout);
        }
        return r2;
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /* renamed from: r0 */
    public void v(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int[] iArr, int i4) {
        int i5;
        int i6;
        if (i3 != 0) {
            if (i3 < 0) {
                int i7 = -appBarLayout.getTotalScrollRange();
                i6 = i7;
                i5 = appBarLayout.getDownNestedPreScrollRange() + i7;
            } else {
                i5 = 0;
                i6 = -appBarLayout.getUpNestedPreScrollRange();
            }
            this.J = false;
            if (this.I > 0 && i3 < 0) {
                this.J = true;
            }
            this.I = i3;
            if (i6 != i5) {
                if (i3 > 0 && this.A > 0 && appBarLayout.getHeight() > this.B && !this.D) {
                    boolean z = view instanceof NestedScrollView;
                }
                iArr[1] = T(coordinatorLayout, appBarLayout, i3, i6, i5);
            }
        }
        if (appBarLayout.q()) {
            appBarLayout.C(appBarLayout.F(view));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /* renamed from: s0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void y(androidx.coordinatorlayout.widget.CoordinatorLayout r11, com.google.android.material.appbar.AppBarLayout r12, android.view.View r13, int r14, int r15, int r16, int r17, int r18, int[] r19) {
        /*
            r10 = this;
            r7 = r10
            if (r17 >= 0) goto L40
            r8 = 1
            if (r15 >= 0) goto Lc
            int r0 = r7.A
            if (r0 > 0) goto Lc
            r7.G = r8
        Lc:
            boolean r0 = r7.G
            if (r0 != 0) goto L40
            int r0 = r12.getTop()
            r9 = r13
            boolean r1 = r9 instanceof androidx.core.widget.NestedScrollView
            if (r1 != 0) goto L28
            int r0 = java.lang.Math.abs(r0)
            int r1 = r12.getTotalScrollRange()
            if (r0 < r1) goto L28
            boolean r0 = r7.J
            if (r0 == 0) goto L28
            goto L41
        L28:
            int r0 = r10.R()
            int r3 = r0 - r17
            int r0 = r12.getDownNestedScrollRange()
            int r4 = -r0
            r5 = 0
            r0 = r10
            r1 = r11
            r2 = r12
            r6 = r18
            int r0 = r0.b1(r1, r2, r3, r4, r5, r6)
            r19[r8] = r0
            goto L41
        L40:
            r9 = r13
        L41:
            if (r17 != 0) goto L46
            super.y(r11, r12, r13, r14, r15, r16, r17, r18, r19)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayoutSpringBehavior.y(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.View, int, int, int, int, int, int[]):void");
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /* renamed from: v0 */
    public boolean F(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i3) {
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimator2;
        this.M = coordinatorLayout;
        this.N = appBarLayout;
        boolean F = super.F(coordinatorLayout, appBarLayout, view, view2, i2, i3);
        this.F = i3;
        this.D = false;
        if (F && (valueAnimator2 = this.E) != null) {
            valueAnimator2.cancel();
        }
        if (F && (valueAnimator = this.C) != null && valueAnimator.isRunning()) {
            this.C.cancel();
        }
        this.J = false;
        this.I = 0;
        return F && this.H;
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /* renamed from: w0 */
    public void H(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
        super.H(coordinatorLayout, appBarLayout, view, i2);
        if (this.F == 0 || i2 == 1) {
            C0(coordinatorLayout, appBarLayout);
            Q0(coordinatorLayout, appBarLayout);
        }
        if (this.G) {
            this.G = false;
        }
    }

    public AppBarLayoutSpringBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = new PathInterpolator(0.33f, 0.0f, 0.0f, 1.0f);
        this.x = new PathInterpolator(0.33f, 0.0f, 0.2f, 1.0f);
        this.y = null;
        this.A = 0;
        this.B = 0;
        this.C = null;
        this.D = false;
        this.E = null;
        this.G = false;
        this.H = true;
        this.I = 0;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = null;
        this.N = null;
        this.P = null;
        this.Q = null;
        this.z = context;
    }
}
