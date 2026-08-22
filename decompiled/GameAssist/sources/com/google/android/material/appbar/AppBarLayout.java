package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = R.style.Widget_Design_AppBarLayout;
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    private final float appBarElevation;
    private Behavior behavior;
    private int currentOffset;
    private int downPreScrollRange;
    private int downScrollRange;
    private final boolean hasLiftOnScrollColor;
    private boolean haveChildWithInterpolator;

    @Nullable
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;

    @Nullable
    private ValueAnimator liftOnScrollColorAnimator;
    private final long liftOnScrollColorDuration;
    private final TimeInterpolator liftOnScrollColorInterpolator;

    @Nullable
    private ValueAnimator.AnimatorUpdateListener liftOnScrollColorUpdateListener;
    private final List<LiftOnScrollListener> liftOnScrollListeners;

    @Nullable
    private WeakReference<View> liftOnScrollTargetView;

    @IdRes
    private int liftOnScrollTargetViewId;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int pendingAction;

    @Nullable
    private Drawable statusBarForeground;

    @Nullable
    private Integer statusBarForegroundOriginalColor;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    /* JADX INFO: Access modifiers changed from: protected */
    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {

        /* renamed from: q, reason: collision with root package name */
        private int f13838q;

        /* renamed from: r, reason: collision with root package name */
        private int f13839r;

        /* renamed from: s, reason: collision with root package name */
        private ValueAnimator f13840s;
        private SavedState t;
        private WeakReference u;
        private BaseDragCallback v;

        public static abstract class BaseDragCallback<T extends AppBarLayout> {
            public abstract boolean a(AppBarLayout appBarLayout);
        }

        public BaseBehavior() {
        }

        private boolean B0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            List w = coordinatorLayout.w(appBarLayout);
            int size = w.size();
            for (int i2 = 0; i2 < size; i2++) {
                CoordinatorLayout.Behavior f2 = ((CoordinatorLayout.LayoutParams) ((View) w.get(i2)).getLayoutParams()).f();
                if (f2 instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) f2).P() != 0;
                }
            }
            return false;
        }

        private void C0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int topInset = appBarLayout.getTopInset() + appBarLayout.getPaddingTop();
            int R = R() - topInset;
            int j0 = j0(appBarLayout, R);
            if (j0 >= 0) {
                View childAt = appBarLayout.getChildAt(j0);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int c2 = layoutParams.c();
                if ((c2 & 17) == 17) {
                    int i2 = -childAt.getTop();
                    int i3 = -childAt.getBottom();
                    if (j0 == 0 && ViewCompat.s(appBarLayout) && ViewCompat.s(childAt)) {
                        i2 -= appBarLayout.getTopInset();
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
                    a0(coordinatorLayout, appBarLayout, MathUtils.b(c0(R, i3, i2) + topInset, -appBarLayout.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private void D0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, boolean z) {
            View i0 = i0(appBarLayout, i2);
            boolean z2 = false;
            if (i0 != null) {
                int c2 = ((LayoutParams) i0.getLayoutParams()).c();
                if ((c2 & 1) != 0) {
                    int w = ViewCompat.w(i0);
                    if (i3 <= 0 || (c2 & 12) == 0 ? !((c2 & 2) == 0 || (-i2) < (i0.getBottom() - w) - appBarLayout.getTopInset()) : (-i2) >= (i0.getBottom() - w) - appBarLayout.getTopInset()) {
                        z2 = true;
                    }
                }
            }
            if (appBarLayout.q()) {
                z2 = appBarLayout.F(h0(coordinatorLayout));
            }
            boolean C = appBarLayout.C(z2);
            if (z || (C && B0(coordinatorLayout, appBarLayout))) {
                if (appBarLayout.getBackground() != null) {
                    appBarLayout.getBackground().jumpToCurrentState();
                }
                if (appBarLayout.getForeground() != null) {
                    appBarLayout.getForeground().jumpToCurrentState();
                }
                if (appBarLayout.getStateListAnimator() != null) {
                    appBarLayout.getStateListAnimator().jumpToCurrentState();
                }
            }
        }

        private void Z(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            if (ViewCompat.I(coordinatorLayout)) {
                return;
            }
            ViewCompat.i0(coordinatorLayout, new AccessibilityDelegateCompat() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.2
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    View k0;
                    super.g(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.h0(ScrollView.class.getName());
                    if (appBarLayout.getTotalScrollRange() == 0 || (k0 = BaseBehavior.this.k0(coordinatorLayout)) == null || !BaseBehavior.this.g0(appBarLayout)) {
                        return;
                    }
                    if (BaseBehavior.this.R() != (-appBarLayout.getTotalScrollRange())) {
                        accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3494q);
                        accessibilityNodeInfoCompat.E0(true);
                    }
                    if (BaseBehavior.this.R() != 0) {
                        if (!k0.canScrollVertically(-1)) {
                            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3495r);
                            accessibilityNodeInfoCompat.E0(true);
                        } else if ((-appBarLayout.getDownNestedPreScrollRange()) != 0) {
                            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3495r);
                            accessibilityNodeInfoCompat.E0(true);
                        }
                    }
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean j(View view, int i2, Bundle bundle) {
                    if (i2 == 4096) {
                        appBarLayout.setExpanded(false);
                        return true;
                    }
                    if (i2 != 8192) {
                        return super.j(view, i2, bundle);
                    }
                    if (BaseBehavior.this.R() != 0) {
                        View k0 = BaseBehavior.this.k0(coordinatorLayout);
                        if (!k0.canScrollVertically(-1)) {
                            appBarLayout.setExpanded(true);
                            return true;
                        }
                        int i3 = -appBarLayout.getDownNestedPreScrollRange();
                        if (i3 != 0) {
                            BaseBehavior.this.v(coordinatorLayout, appBarLayout, k0, 0, i3, new int[]{0, 0}, 1);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        private void a0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, float f2) {
            int abs = Math.abs(R() - i2);
            float abs2 = Math.abs(f2);
            b0(coordinatorLayout, appBarLayout, i2, abs2 > 0.0f ? Math.round((abs / abs2) * 1000.0f) * 3 : (int) (((abs / appBarLayout.getHeight()) + 1.0f) * 150.0f));
        }

        private void b0(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int i2, int i3) {
            int R = R();
            if (R == i2) {
                ValueAnimator valueAnimator = this.f13840s;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.f13840s.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.f13840s;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.f13840s = valueAnimator3;
                valueAnimator3.setInterpolator(AnimationUtils.f13818e);
                this.f13840s.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator4) {
                        BaseBehavior.this.U(coordinatorLayout, appBarLayout, ((Integer) valueAnimator4.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.f13840s.setDuration(Math.min(i3, 600));
            this.f13840s.setIntValues(R, i2);
            this.f13840s.start();
        }

        private int c0(int i2, int i3, int i4) {
            return i2 < (i3 + i4) / 2 ? i3 : i4;
        }

        private boolean e0(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view) {
            return appBarLayout.m() && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight();
        }

        private static boolean f0(int i2, int i3) {
            return (i2 & i3) == i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean g0(AppBarLayout appBarLayout) {
            int childCount = appBarLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (((LayoutParams) appBarLayout.getChildAt(i2).getLayoutParams()).f13854a != 0) {
                    return true;
                }
            }
            return false;
        }

        private View h0(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if ((childAt instanceof NestedScrollingChild) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        private static View i0(AppBarLayout appBarLayout, int i2) {
            int abs = Math.abs(i2);
            int childCount = appBarLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = appBarLayout.getChildAt(i3);
                if (abs >= childAt.getTop() && abs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        private int j0(AppBarLayout appBarLayout, int i2) {
            int childCount = appBarLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = appBarLayout.getChildAt(i3);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
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

        /* JADX INFO: Access modifiers changed from: private */
        public View k0(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).f() instanceof ScrollingViewBehavior) {
                    return childAt;
                }
            }
            return null;
        }

        private int n0(AppBarLayout appBarLayout, int i2) {
            int abs = Math.abs(i2);
            int childCount = appBarLayout.getChildCount();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    break;
                }
                View childAt = appBarLayout.getChildAt(i4);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Interpolator d2 = layoutParams.d();
                if (abs < childAt.getTop() || abs > childAt.getBottom()) {
                    i4++;
                } else if (d2 != null) {
                    int c2 = layoutParams.c();
                    if ((c2 & 1) != 0) {
                        i3 = childAt.getHeight() + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                        if ((c2 & 2) != 0) {
                            i3 -= ViewCompat.w(childAt);
                        }
                    }
                    if (ViewCompat.s(childAt)) {
                        i3 -= appBarLayout.getTopInset();
                    }
                    if (i3 > 0) {
                        float f2 = i3;
                        return Integer.signum(i2) * (childAt.getTop() + Math.round(f2 * d2.getInterpolation((abs - childAt.getTop()) / f2)));
                    }
                }
            }
            return i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /* renamed from: A0, reason: merged with bridge method [inline-methods] */
        public int V(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4) {
            int R = R();
            int i5 = 0;
            if (i3 == 0 || R < i3 || R > i4) {
                this.f13838q = 0;
            } else {
                int b2 = MathUtils.b(i2, i3, i4);
                if (R != b2) {
                    int n0 = appBarLayout.k() ? n0(appBarLayout, b2) : b2;
                    boolean L = L(n0);
                    int i6 = R - b2;
                    this.f13838q = b2 - n0;
                    if (L) {
                        while (i5 < appBarLayout.getChildCount()) {
                            LayoutParams layoutParams = (LayoutParams) appBarLayout.getChildAt(i5).getLayoutParams();
                            ChildScrollEffect b3 = layoutParams.b();
                            if (b3 != null && (layoutParams.c() & 1) != 0) {
                                b3.a(appBarLayout, appBarLayout.getChildAt(i5), J());
                            }
                            i5++;
                        }
                    }
                    if (!L && appBarLayout.k()) {
                        coordinatorLayout.p(appBarLayout);
                    }
                    appBarLayout.u(J());
                    D0(coordinatorLayout, appBarLayout, b2, b2 < R ? -1 : 1, false);
                    i5 = i6;
                }
            }
            Z(coordinatorLayout, appBarLayout);
            return i5;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        int R() {
            return J() + this.f13838q;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /* renamed from: d0, reason: merged with bridge method [inline-methods] */
        public boolean M(AppBarLayout appBarLayout) {
            BaseDragCallback baseDragCallback = this.v;
            if (baseDragCallback != null) {
                return baseDragCallback.a(appBarLayout);
            }
            WeakReference weakReference = this.u;
            if (weakReference == null) {
                return true;
            }
            View view = (View) weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        @VisibleForTesting
        boolean isOffsetAnimatorRunning() {
            ValueAnimator valueAnimator = this.f13840s;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /* renamed from: l0, reason: merged with bridge method [inline-methods] */
        public int P(AppBarLayout appBarLayout) {
            return (-appBarLayout.getDownNestedScrollRange()) + appBarLayout.getTopInset();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /* renamed from: m0, reason: merged with bridge method [inline-methods] */
        public int Q(AppBarLayout appBarLayout) {
            return appBarLayout.getTotalScrollRange();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /* renamed from: o0, reason: merged with bridge method [inline-methods] */
        public void S(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            C0(coordinatorLayout, appBarLayout);
            if (appBarLayout.q()) {
                appBarLayout.C(appBarLayout.F(h0(coordinatorLayout)));
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: p0, reason: merged with bridge method [inline-methods] */
        public boolean q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
            boolean q2 = super.q(coordinatorLayout, appBarLayout, i2);
            int pendingAction = appBarLayout.getPendingAction();
            SavedState savedState = this.t;
            if (savedState == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int i3 = -appBarLayout.getUpNestedPreScrollRange();
                        if (z) {
                            a0(coordinatorLayout, appBarLayout, i3, 0.0f);
                        } else {
                            U(coordinatorLayout, appBarLayout, i3);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z) {
                            a0(coordinatorLayout, appBarLayout, 0, 0.0f);
                        } else {
                            U(coordinatorLayout, appBarLayout, 0);
                        }
                    }
                }
            } else if (savedState.f13847i) {
                U(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
            } else if (savedState.f13848j) {
                U(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(savedState.f13849k);
                U(coordinatorLayout, appBarLayout, (-childAt.getBottom()) + (this.t.f13851m ? ViewCompat.w(childAt) + appBarLayout.getTopInset() : Math.round(childAt.getHeight() * this.t.f13850l)));
            }
            appBarLayout.y();
            this.t = null;
            L(MathUtils.b(J(), -appBarLayout.getTotalScrollRange(), 0));
            D0(coordinatorLayout, appBarLayout, J(), 0, true);
            appBarLayout.u(J());
            Z(coordinatorLayout, appBarLayout);
            return q2;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: q0, reason: merged with bridge method [inline-methods] */
        public boolean r(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4, int i5) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams())).height != -2) {
                return super.r(coordinatorLayout, appBarLayout, i2, i3, i4, i5);
            }
            coordinatorLayout.N(appBarLayout, i2, i3, View.MeasureSpec.makeMeasureSpec(0, 0), i5);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: r0, reason: merged with bridge method [inline-methods] */
        public void v(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int[] iArr, int i4) {
            int i5;
            int i6;
            if (i3 != 0) {
                if (i3 < 0) {
                    i5 = -appBarLayout.getTotalScrollRange();
                    i6 = appBarLayout.getDownNestedPreScrollRange() + i5;
                } else {
                    i5 = -appBarLayout.getUpNestedPreScrollRange();
                    i6 = 0;
                }
                int i7 = i5;
                int i8 = i6;
                if (i7 != i8) {
                    iArr[1] = T(coordinatorLayout, appBarLayout, i3, i7, i8);
                }
            }
            if (appBarLayout.q()) {
                appBarLayout.C(appBarLayout.F(view));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: s0, reason: merged with bridge method [inline-methods] */
        public void y(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
            if (i5 < 0) {
                iArr[1] = T(coordinatorLayout, appBarLayout, i5, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i5 == 0) {
                Z(coordinatorLayout, appBarLayout);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: t0, reason: merged with bridge method [inline-methods] */
        public void C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                x0((SavedState) parcelable, true);
                super.C(coordinatorLayout, appBarLayout, this.t.a());
            } else {
                super.C(coordinatorLayout, appBarLayout, parcelable);
                this.t = null;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: u0, reason: merged with bridge method [inline-methods] */
        public Parcelable D(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            Parcelable D = super.D(coordinatorLayout, appBarLayout);
            SavedState y0 = y0(D, appBarLayout);
            return y0 == null ? D : y0;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: v0, reason: merged with bridge method [inline-methods] */
        public boolean F(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i3) {
            ValueAnimator valueAnimator;
            boolean z = (i2 & 2) != 0 && (appBarLayout.q() || e0(coordinatorLayout, appBarLayout, view));
            if (z && (valueAnimator = this.f13840s) != null) {
                valueAnimator.cancel();
            }
            this.u = null;
            this.f13839r = i3;
            return z;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: w0, reason: merged with bridge method [inline-methods] */
        public void H(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
            if (this.f13839r == 0 || i2 == 1) {
                C0(coordinatorLayout, appBarLayout);
                if (appBarLayout.q()) {
                    appBarLayout.C(appBarLayout.F(view));
                }
            }
            this.u = new WeakReference(view);
        }

        void x0(SavedState savedState, boolean z) {
            if (this.t == null || z) {
                this.t = savedState;
            }
        }

        SavedState y0(Parcelable parcelable, AppBarLayout appBarLayout) {
            int J = J();
            int childCount = appBarLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = appBarLayout.getChildAt(i2);
                int bottom = childAt.getBottom() + J;
                if (childAt.getTop() + J <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.f3582h;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    boolean z = J == 0;
                    savedState.f13848j = z;
                    savedState.f13847i = !z && (-J) >= appBarLayout.getTotalScrollRange();
                    savedState.f13849k = i2;
                    savedState.f13851m = bottom == ViewCompat.w(childAt) + appBarLayout.getTopInset();
                    savedState.f13850l = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        public void z0(BaseDragCallback baseDragCallback) {
            this.v = baseDragCallback;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.SavedState.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return new SavedState(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return new SavedState(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i2) {
                    return new SavedState[i2];
                }
            };

            /* renamed from: i, reason: collision with root package name */
            boolean f13847i;

            /* renamed from: j, reason: collision with root package name */
            boolean f13848j;

            /* renamed from: k, reason: collision with root package name */
            int f13849k;

            /* renamed from: l, reason: collision with root package name */
            float f13850l;

            /* renamed from: m, reason: collision with root package name */
            boolean f13851m;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f13847i = parcel.readByte() != 0;
                this.f13848j = parcel.readByte() != 0;
                this.f13849k = parcel.readInt();
                this.f13850l = parcel.readFloat();
                this.f13851m = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                super.writeToParcel(parcel, i2);
                parcel.writeByte(this.f13847i ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.f13848j ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.f13849k);
                parcel.writeFloat(this.f13850l);
                parcel.writeByte(this.f13851m ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void a(AppBarLayout appBarLayout, int i2);
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {

        public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {
        }

        public Behavior() {
        }

        @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean I(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.I(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int J() {
            return super.J();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean L(int i2) {
            return super.L(i2);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.p(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: p0 */
        public /* bridge */ /* synthetic */ boolean q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
            return super.q(coordinatorLayout, appBarLayout, i2);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: q0 */
        public /* bridge */ /* synthetic */ boolean r(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4, int i5) {
            return super.r(coordinatorLayout, appBarLayout, i2, i3, i4, i5);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: r0 */
        public /* bridge */ /* synthetic */ void v(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int[] iArr, int i4) {
            super.v(coordinatorLayout, appBarLayout, view, i2, i3, iArr, i4);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: s0 */
        public /* bridge */ /* synthetic */ void y(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
            super.y(coordinatorLayout, appBarLayout, view, i2, i3, i4, i5, i6, iArr);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: t0 */
        public /* bridge */ /* synthetic */ void C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.C(coordinatorLayout, appBarLayout, parcelable);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: u0 */
        public /* bridge */ /* synthetic */ Parcelable D(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.D(coordinatorLayout, appBarLayout);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: v0 */
        public /* bridge */ /* synthetic */ boolean F(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i3) {
            return super.F(coordinatorLayout, appBarLayout, view, view2, i2, i3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: w0 */
        public /* bridge */ /* synthetic */ void H(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
            super.H(coordinatorLayout, appBarLayout, view, i2);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void z0(BaseBehavior.BaseDragCallback baseDragCallback) {
            super.z0(baseDragCallback);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static abstract class ChildScrollEffect {
        public abstract void a(AppBarLayout appBarLayout, View view, float f2);
    }

    public static class CompressChildScrollEffect extends ChildScrollEffect {

        /* renamed from: a, reason: collision with root package name */
        private final Rect f13852a = new Rect();

        /* renamed from: b, reason: collision with root package name */
        private final Rect f13853b = new Rect();

        private static void b(Rect rect, AppBarLayout appBarLayout, View view) {
            view.getDrawingRect(rect);
            appBarLayout.offsetDescendantRectToMyCoords(view, rect);
            rect.offset(0, -appBarLayout.getTopInset());
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ChildScrollEffect
        public void a(AppBarLayout appBarLayout, View view, float f2) {
            b(this.f13852a, appBarLayout, view);
            float abs = this.f13852a.top - Math.abs(f2);
            if (abs > 0.0f) {
                ViewCompat.p0(view, null);
                view.setTranslationY(0.0f);
                view.setVisibility(0);
                return;
            }
            float a2 = 1.0f - MathUtils.a(Math.abs(abs / this.f13852a.height()), 0.0f, 1.0f);
            float height = (-abs) - ((this.f13852a.height() * 0.3f) * (1.0f - (a2 * a2)));
            view.setTranslationY(height);
            view.getDrawingRect(this.f13853b);
            this.f13853b.offset(0, (int) (-height));
            if (height >= this.f13853b.height()) {
                view.setVisibility(4);
            } else {
                view.setVisibility(0);
            }
            ViewCompat.p0(view, this.f13853b);
        }
    }

    public interface LiftOnScrollListener {
        void a(float f2, int i2);
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        private static int W(AppBarLayout appBarLayout) {
            CoordinatorLayout.Behavior f2 = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).f();
            if (f2 instanceof BaseBehavior) {
                return ((BaseBehavior) f2).R();
            }
            return 0;
        }

        private void X(View view, View view2) {
            CoordinatorLayout.Behavior f2 = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).f();
            if (f2 instanceof BaseBehavior) {
                ViewCompat.T(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) f2).f13838q) + R()) - N(view2));
            }
        }

        private void Y(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.q()) {
                    appBarLayout.C(appBarLayout.F(view));
                }
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean B(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout M = M(coordinatorLayout.v(view));
            if (M != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                Rect rect3 = this.f13886j;
                rect3.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect3.contains(rect2)) {
                    M.z(false, !z);
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        float O(View view) {
            int i2;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int W = W(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + W > downNestedPreScrollRange) && (i2 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (W / i2) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        int Q(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.Q(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        /* renamed from: V, reason: merged with bridge method [inline-methods] */
        public AppBarLayout M(List list) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) list.get(i2);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean j(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean m(CoordinatorLayout coordinatorLayout, View view, View view2) {
            X(view, view2);
            Y(view, view2);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void n(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                ViewCompat.i0(coordinatorLayout, null);
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
            return super.q(coordinatorLayout, view, i2);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean r(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
            return super.r(coordinatorLayout, view, i2, i3, i4, i5);
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingViewBehavior_Layout);
            T(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            obtainStyledAttributes.recycle();
        }
    }

    public AppBarLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.appBarLayoutStyle);
    }

    private void A(boolean z, boolean z2, boolean z3) {
        this.pendingAction = (z ? 1 : 2) | (z2 ? 4 : 0) | (z3 ? 8 : 0);
        requestLayout();
    }

    private boolean B(boolean z) {
        if (this.liftable == z) {
            return false;
        }
        this.liftable = z;
        refreshDrawableState();
        return true;
    }

    private boolean E() {
        return this.statusBarForeground != null && getTopInset() > 0;
    }

    private boolean G() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        return (childAt.getVisibility() == 8 || ViewCompat.s(childAt)) ? false : true;
    }

    private void H(float f2, float f3) {
        ValueAnimator valueAnimator = this.liftOnScrollColorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        this.liftOnScrollColorAnimator = ofFloat;
        ofFloat.setDuration(this.liftOnScrollColorDuration);
        this.liftOnScrollColorAnimator.setInterpolator(this.liftOnScrollColorInterpolator);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.liftOnScrollColorUpdateListener;
        if (animatorUpdateListener != null) {
            this.liftOnScrollColorAnimator.addUpdateListener(animatorUpdateListener);
        }
        this.liftOnScrollColorAnimator.start();
    }

    private void I() {
        setWillNotDraw(!E());
    }

    private void e() {
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    private Integer f() {
        Drawable drawable = this.statusBarForeground;
        if (drawable instanceof MaterialShapeDrawable) {
            return Integer.valueOf(((MaterialShapeDrawable) drawable).A());
        }
        ColorStateList g2 = DrawableUtils.g(drawable);
        if (g2 != null) {
            return Integer.valueOf(g2.getDefaultColor());
        }
        return null;
    }

    private View g(View view) {
        int i2;
        if (this.liftOnScrollTargetView == null && (i2 = this.liftOnScrollTargetViewId) != -1) {
            View findViewById = view != null ? view.findViewById(i2) : null;
            if (findViewById == null && (getParent() instanceof ViewGroup)) {
                findViewById = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
            }
            if (findViewById != null) {
                this.liftOnScrollTargetView = new WeakReference<>(findViewById);
            }
        }
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private boolean l() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).e()) {
                return true;
            }
        }
        return false;
    }

    private void n(final MaterialShapeDrawable materialShapeDrawable, final ColorStateList colorStateList, final ColorStateList colorStateList2) {
        final Integer f2 = MaterialColors.f(getContext(), R.attr.colorSurface);
        this.liftOnScrollColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AppBarLayout.this.s(colorStateList, colorStateList2, materialShapeDrawable, f2, valueAnimator);
            }
        };
        ViewCompat.m0(this, materialShapeDrawable);
    }

    private void o(Context context, final MaterialShapeDrawable materialShapeDrawable) {
        materialShapeDrawable.P(context);
        this.liftOnScrollColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AppBarLayout.this.t(materialShapeDrawable, valueAnimator);
            }
        };
        ViewCompat.m0(this, materialShapeDrawable);
    }

    private void p() {
        Behavior behavior = this.behavior;
        BaseBehavior.SavedState y0 = (behavior == null || this.totalScrollRange == -1 || this.pendingAction != 0) ? null : behavior.y0(AbsSavedState.f3582h, this);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if (y0 != null) {
            this.behavior.x0(y0, false);
        }
    }

    private boolean r() {
        return getBackground() instanceof MaterialShapeDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(ColorStateList colorStateList, ColorStateList colorStateList2, MaterialShapeDrawable materialShapeDrawable, Integer num, ValueAnimator valueAnimator) {
        Integer num2;
        int l2 = MaterialColors.l(colorStateList.getDefaultColor(), colorStateList2.getDefaultColor(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
        materialShapeDrawable.a0(ColorStateList.valueOf(l2));
        if (this.statusBarForeground != null && (num2 = this.statusBarForegroundOriginalColor) != null && num2.equals(num)) {
            DrawableCompat.n(this.statusBarForeground, l2);
        }
        if (this.liftOnScrollListeners.isEmpty()) {
            return;
        }
        for (LiftOnScrollListener liftOnScrollListener : this.liftOnScrollListeners) {
            if (materialShapeDrawable.x() != null) {
                liftOnScrollListener.a(0.0f, l2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(MaterialShapeDrawable materialShapeDrawable, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        materialShapeDrawable.Z(floatValue);
        Drawable drawable = this.statusBarForeground;
        if (drawable instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) drawable).Z(floatValue);
        }
        Iterator<LiftOnScrollListener> it = this.liftOnScrollListeners.iterator();
        while (it.hasNext()) {
            it.next().a(floatValue, materialShapeDrawable.A());
        }
    }

    boolean C(boolean z) {
        return D(z, !this.liftableOverride);
    }

    boolean D(boolean z, boolean z2) {
        if (!z2 || this.lifted == z) {
            return false;
        }
        this.lifted = z;
        refreshDrawableState();
        if (!r()) {
            return true;
        }
        if (this.hasLiftOnScrollColor) {
            H(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
            return true;
        }
        if (!this.liftOnScroll) {
            return true;
        }
        H(z ? 0.0f : this.appBarElevation, z ? this.appBarElevation : 0.0f);
        return true;
    }

    boolean F(View view) {
        View g2 = g(view);
        if (g2 != null) {
            view = g2;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    public void c(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        if (baseOnOffsetChangedListener == null || this.listeners.contains(baseOnOffsetChangedListener)) {
            return;
        }
        this.listeners.add(baseOnOffsetChangedListener);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void d(OnOffsetChangedListener onOffsetChangedListener) {
        c(onOffsetChangedListener);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (E()) {
            int save = canvas.save();
            canvas.translate(0.0f, -this.currentOffset);
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarForeground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    @NonNull
    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        Behavior behavior = new Behavior();
        this.behavior = behavior;
        return behavior;
    }

    int getDownNestedPreScrollRange() {
        int i2;
        int w;
        int i3 = this.downPreScrollRange;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i5 = layoutParams.f13854a;
                if ((i5 & 5) != 5) {
                    if (i4 > 0) {
                        break;
                    }
                } else {
                    int i6 = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                    if ((i5 & 8) != 0) {
                        w = ViewCompat.w(childAt);
                    } else if ((i5 & 2) != 0) {
                        w = measuredHeight - ViewCompat.w(childAt);
                    } else {
                        i2 = i6 + measuredHeight;
                        if (childCount == 0 && ViewCompat.s(childAt)) {
                            i2 = Math.min(i2, measuredHeight - getTopInset());
                        }
                        i4 += i2;
                    }
                    i2 = i6 + w;
                    if (childCount == 0) {
                        i2 = Math.min(i2, measuredHeight - getTopInset());
                    }
                    i4 += i2;
                }
            }
        }
        int max = Math.max(0, i4);
        this.downPreScrollRange = max;
        return max;
    }

    int getDownNestedScrollRange() {
        int i2 = this.downScrollRange;
        if (i2 != -1) {
            return i2;
        }
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                int i5 = layoutParams.f13854a;
                if ((i5 & 1) == 0) {
                    break;
                }
                i4 += measuredHeight;
                if ((i5 & 2) != 0) {
                    i4 -= ViewCompat.w(childAt);
                    break;
                }
            }
            i3++;
        }
        int max = Math.max(0, i4);
        this.downScrollRange = max;
        return max;
    }

    @IdRes
    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    @Nullable
    public MaterialShapeDrawable getMaterialShapeBackground() {
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            return (MaterialShapeDrawable) background;
        }
        return null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int w = ViewCompat.w(this);
        if (w == 0) {
            int childCount = getChildCount();
            w = childCount >= 1 ? ViewCompat.w(getChildAt(childCount - 1)) : 0;
            if (w == 0) {
                return getHeight() / 3;
            }
        }
        return (w * 2) + topInset;
    }

    int getPendingAction() {
        return this.pendingAction;
    }

    @Nullable
    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    @VisibleForTesting
    final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.l();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i2 = this.totalScrollRange;
        if (i2 != -1) {
            return i2;
        }
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i5 = layoutParams.f13854a;
                if ((i5 & 1) == 0) {
                    break;
                }
                i4 += measuredHeight + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                if (i3 == 0 && ViewCompat.s(childAt)) {
                    i4 -= getTopInset();
                }
                if ((i5 & 2) != 0) {
                    i4 -= ViewCompat.w(childAt);
                    break;
                }
            }
            i3++;
        }
        int max = Math.max(0, i4);
        this.totalScrollRange = max;
        return max;
    }

    int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LinearLayout.LayoutParams ? new LayoutParams((LinearLayout.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    boolean k() {
        return this.haveChildWithInterpolator;
    }

    boolean m() {
        return getTotalScrollRange() != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.e(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + iArr.length);
        boolean z = this.liftable;
        int i3 = R.attr.state_liftable;
        if (!z) {
            i3 = -i3;
        }
        iArr[0] = i3;
        iArr[1] = (z && this.lifted) ? R.attr.state_lifted : -R.attr.state_lifted;
        int i4 = R.attr.state_collapsible;
        if (!z) {
            i4 = -i4;
        }
        iArr[2] = i4;
        iArr[3] = (z && this.lifted) ? R.attr.state_collapsed : -R.attr.state_collapsed;
        return LinearLayout.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        boolean z2 = true;
        if (ViewCompat.s(this) && G()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                ViewCompat.T(getChildAt(childCount), topInset);
            }
        }
        p();
        this.haveChildWithInterpolator = false;
        int childCount2 = getChildCount();
        int i6 = 0;
        while (true) {
            if (i6 >= childCount2) {
                break;
            }
            if (((LayoutParams) getChildAt(i6).getLayoutParams()).d() != null) {
                this.haveChildWithInterpolator = true;
                break;
            }
            i6++;
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.liftableOverride) {
            return;
        }
        if (!this.liftOnScroll && !l()) {
            z2 = false;
        }
        B(z2);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i3);
        if (mode != 1073741824 && ViewCompat.s(this) && G()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = MathUtils.b(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(i3));
            } else if (mode == 0) {
                measuredHeight += getTopInset();
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        p();
    }

    public boolean q() {
        return this.liftOnScroll;
    }

    @Override // android.view.View
    @RequiresApi
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.d(this, f2);
    }

    public void setExpanded(boolean z) {
        z(z, ViewCompat.N(this));
    }

    public void setLiftOnScroll(boolean z) {
        this.liftOnScroll = z;
    }

    public void setLiftOnScrollTargetView(@Nullable View view) {
        this.liftOnScrollTargetViewId = -1;
        if (view == null) {
            e();
        } else {
            this.liftOnScrollTargetView = new WeakReference<>(view);
        }
    }

    public void setLiftOnScrollTargetViewId(@IdRes int i2) {
        this.liftOnScrollTargetViewId = i2;
        e();
    }

    public void setLiftableOverrideEnabled(boolean z) {
        this.liftableOverride = z;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i2) {
        if (i2 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i2);
    }

    public void setStatusBarForeground(@Nullable Drawable drawable) {
        Drawable drawable2 = this.statusBarForeground;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.statusBarForeground = drawable != null ? drawable.mutate() : null;
            this.statusBarForegroundOriginalColor = f();
            Drawable drawable3 = this.statusBarForeground;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.statusBarForeground.setState(getDrawableState());
                }
                DrawableCompat.m(this.statusBarForeground, ViewCompat.v(this));
                this.statusBarForeground.setVisible(getVisibility() == 0, false);
                this.statusBarForeground.setCallback(this);
            }
            I();
            ViewCompat.Z(this);
        }
    }

    public void setStatusBarForegroundColor(@ColorInt int i2) {
        setStatusBarForeground(new ColorDrawable(i2));
    }

    public void setStatusBarForegroundResource(@DrawableRes int i2) {
        setStatusBarForeground(AppCompatResources.b(getContext(), i2));
    }

    @Deprecated
    public void setTargetElevation(float f2) {
        ViewUtilsLollipop.b(this, f2);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    void u(int i2) {
        this.currentOffset = i2;
        if (!willNotDraw()) {
            ViewCompat.Z(this);
        }
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                BaseOnOffsetChangedListener baseOnOffsetChangedListener = this.listeners.get(i3);
                if (baseOnOffsetChangedListener != null) {
                    baseOnOffsetChangedListener.a(this, i2);
                }
            }
        }
    }

    WindowInsetsCompat v(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = ViewCompat.s(this) ? windowInsetsCompat : null;
        if (!ObjectsCompat.a(this.lastInsets, windowInsetsCompat2)) {
            this.lastInsets = windowInsetsCompat2;
            I();
            requestLayout();
        }
        return windowInsetsCompat;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.statusBarForeground;
    }

    public void w(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list == null || baseOnOffsetChangedListener == null) {
            return;
        }
        list.remove(baseOnOffsetChangedListener);
    }

    public void x(OnOffsetChangedListener onOffsetChangedListener) {
        w(onOffsetChangedListener);
    }

    void y() {
        this.pendingAction = 0;
    }

    public void z(boolean z, boolean z2) {
        A(z, z2, true);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppBarLayout(@androidx.annotation.NonNull android.content.Context r10, @androidx.annotation.Nullable android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            int r4 = com.google.android.material.appbar.AppBarLayout.DEF_STYLE_RES
            android.content.Context r10 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r10, r11, r12, r4)
            r9.<init>(r10, r11, r12)
            r10 = -1
            r9.totalScrollRange = r10
            r9.downPreScrollRange = r10
            r9.downScrollRange = r10
            r6 = 0
            r9.pendingAction = r6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r9.liftOnScrollListeners = r0
            android.content.Context r7 = r9.getContext()
            r8 = 1
            r9.setOrientation(r8)
            android.view.ViewOutlineProvider r0 = r9.getOutlineProvider()
            android.view.ViewOutlineProvider r1 = android.view.ViewOutlineProvider.BACKGROUND
            if (r0 != r1) goto L2d
            com.google.android.material.appbar.ViewUtilsLollipop.a(r9)
        L2d:
            com.google.android.material.appbar.ViewUtilsLollipop.c(r9, r11, r12, r4)
            int[] r2 = com.google.android.material.R.styleable.AppBarLayout
            int[] r5 = new int[r6]
            r0 = r7
            r1 = r11
            r3 = r12
            android.content.res.TypedArray r11 = com.google.android.material.internal.ThemeEnforcement.i(r0, r1, r2, r3, r4, r5)
            int r12 = com.google.android.material.R.styleable.AppBarLayout_android_background
            android.graphics.drawable.Drawable r12 = r11.getDrawable(r12)
            androidx.core.view.ViewCompat.m0(r9, r12)
            int r12 = com.google.android.material.R.styleable.AppBarLayout_liftOnScrollColor
            android.content.res.ColorStateList r12 = com.google.android.material.resources.MaterialResources.a(r7, r11, r12)
            if (r12 == 0) goto L4d
            goto L4e
        L4d:
            r8 = r6
        L4e:
            r9.hasLiftOnScrollColor = r8
            android.graphics.drawable.Drawable r0 = r9.getBackground()
            android.content.res.ColorStateList r0 = com.google.android.material.drawable.DrawableUtils.g(r0)
            if (r0 == 0) goto L6b
            com.google.android.material.shape.MaterialShapeDrawable r1 = new com.google.android.material.shape.MaterialShapeDrawable
            r1.<init>()
            r1.a0(r0)
            if (r12 == 0) goto L68
            r9.n(r1, r0, r12)
            goto L6b
        L68:
            r9.o(r7, r1)
        L6b:
            int r12 = com.google.android.material.R.attr.motionDurationMedium2
            android.content.res.Resources r0 = r9.getResources()
            int r1 = com.google.android.material.R.integer.app_bar_elevation_anim_duration
            int r0 = r0.getInteger(r1)
            int r12 = com.google.android.material.motion.MotionUtils.f(r7, r12, r0)
            long r0 = (long) r12
            r9.liftOnScrollColorDuration = r0
            int r12 = com.google.android.material.R.attr.motionEasingStandardInterpolator
            android.animation.TimeInterpolator r0 = com.google.android.material.animation.AnimationUtils.f13814a
            android.animation.TimeInterpolator r12 = com.google.android.material.motion.MotionUtils.g(r7, r12, r0)
            r9.liftOnScrollColorInterpolator = r12
            int r12 = com.google.android.material.R.styleable.AppBarLayout_expanded
            boolean r12 = r11.hasValue(r12)
            if (r12 == 0) goto L99
            int r12 = com.google.android.material.R.styleable.AppBarLayout_expanded
            boolean r12 = r11.getBoolean(r12, r6)
            r9.A(r12, r6, r6)
        L99:
            int r12 = com.google.android.material.R.styleable.AppBarLayout_elevation
            boolean r12 = r11.hasValue(r12)
            if (r12 == 0) goto Lab
            int r12 = com.google.android.material.R.styleable.AppBarLayout_elevation
            int r12 = r11.getDimensionPixelSize(r12, r6)
            float r12 = (float) r12
            com.google.android.material.appbar.ViewUtilsLollipop.b(r9, r12)
        Lab:
            int r12 = com.google.android.material.R.styleable.AppBarLayout_android_keyboardNavigationCluster
            boolean r12 = r11.hasValue(r12)
            if (r12 == 0) goto Lbc
            int r12 = com.google.android.material.R.styleable.AppBarLayout_android_keyboardNavigationCluster
            boolean r12 = r11.getBoolean(r12, r6)
            r9.setKeyboardNavigationCluster(r12)
        Lbc:
            int r12 = com.google.android.material.R.styleable.AppBarLayout_android_touchscreenBlocksFocus
            boolean r12 = r11.hasValue(r12)
            if (r12 == 0) goto Lcd
            int r12 = com.google.android.material.R.styleable.AppBarLayout_android_touchscreenBlocksFocus
            boolean r12 = r11.getBoolean(r12, r6)
            r9.setTouchscreenBlocksFocus(r12)
        Lcd:
            android.content.res.Resources r12 = r9.getResources()
            int r0 = com.google.android.material.R.dimen.design_appbar_elevation
            float r12 = r12.getDimension(r0)
            r9.appBarElevation = r12
            int r12 = com.google.android.material.R.styleable.AppBarLayout_liftOnScroll
            boolean r12 = r11.getBoolean(r12, r6)
            r9.liftOnScroll = r12
            int r12 = com.google.android.material.R.styleable.AppBarLayout_liftOnScrollTargetViewId
            int r10 = r11.getResourceId(r12, r10)
            r9.liftOnScrollTargetViewId = r10
            int r10 = com.google.android.material.R.styleable.AppBarLayout_statusBarForeground
            android.graphics.drawable.Drawable r10 = r11.getDrawable(r10)
            r9.setStatusBarForeground(r10)
            r11.recycle()
            com.google.android.material.appbar.AppBarLayout$1 r10 = new com.google.android.material.appbar.AppBarLayout$1
            r10.<init>()
            androidx.core.view.ViewCompat.x0(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {

        /* renamed from: a, reason: collision with root package name */
        int f13854a;

        /* renamed from: b, reason: collision with root package name */
        private ChildScrollEffect f13855b;

        /* renamed from: c, reason: collision with root package name */
        Interpolator f13856c;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface ScrollEffect {
        }

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface ScrollFlags {
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f13854a = 1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarLayout_Layout);
            this.f13854a = obtainStyledAttributes.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            f(obtainStyledAttributes.getInt(R.styleable.AppBarLayout_Layout_layout_scrollEffect, 0));
            if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.f13856c = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            obtainStyledAttributes.recycle();
        }

        private ChildScrollEffect a(int i2) {
            if (i2 != 1) {
                return null;
            }
            return new CompressChildScrollEffect();
        }

        public ChildScrollEffect b() {
            return this.f13855b;
        }

        public int c() {
            return this.f13854a;
        }

        public Interpolator d() {
            return this.f13856c;
        }

        boolean e() {
            int i2 = this.f13854a;
            return (i2 & 1) == 1 && (i2 & 10) != 0;
        }

        public void f(int i2) {
            this.f13855b = a(i2);
        }

        public void g(int i2) {
            this.f13854a = i2;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f13854a = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f13854a = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f13854a = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.f13854a = 1;
        }
    }
}
