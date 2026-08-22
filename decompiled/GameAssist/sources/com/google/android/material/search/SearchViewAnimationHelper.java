package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.FadeThroughUpdateListener;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.RectEvaluator;
import com.google.android.material.internal.ReversableAnimatedValueInterpolator;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.search.SearchView;
import java.util.Objects;

/* loaded from: classes.dex */
class SearchViewAnimationHelper {

    /* renamed from: a, reason: collision with root package name */
    private final SearchView f15015a;

    /* renamed from: b, reason: collision with root package name */
    private final View f15016b;

    /* renamed from: c, reason: collision with root package name */
    private final ClippableRoundedCornerLayout f15017c;

    /* renamed from: d, reason: collision with root package name */
    private final FrameLayout f15018d;

    /* renamed from: e, reason: collision with root package name */
    private final FrameLayout f15019e;

    /* renamed from: f, reason: collision with root package name */
    private final Toolbar f15020f;

    /* renamed from: g, reason: collision with root package name */
    private final Toolbar f15021g;

    /* renamed from: h, reason: collision with root package name */
    private final TextView f15022h;

    /* renamed from: i, reason: collision with root package name */
    private final EditText f15023i;

    /* renamed from: j, reason: collision with root package name */
    private final ImageButton f15024j;

    /* renamed from: k, reason: collision with root package name */
    private final View f15025k;

    /* renamed from: l, reason: collision with root package name */
    private final TouchObserverFrameLayout f15026l;

    /* renamed from: m, reason: collision with root package name */
    private final MaterialMainContainerBackHelper f15027m;

    /* renamed from: n, reason: collision with root package name */
    private AnimatorSet f15028n;

    /* renamed from: o, reason: collision with root package name */
    private SearchBar f15029o;

    SearchViewAnimationHelper(SearchView searchView) {
        this.f15015a = searchView;
        this.f15016b = searchView.scrim;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = searchView.rootView;
        this.f15017c = clippableRoundedCornerLayout;
        this.f15018d = searchView.headerContainer;
        this.f15019e = searchView.toolbarContainer;
        this.f15020f = searchView.toolbar;
        this.f15021g = searchView.dummyToolbar;
        this.f15022h = searchView.searchPrefix;
        this.f15023i = searchView.editText;
        this.f15024j = searchView.clearButton;
        this.f15025k = searchView.divider;
        this.f15026l = searchView.contentContainer;
        this.f15027m = new MaterialMainContainerBackHelper(clippableRoundedCornerLayout);
    }

    private Animator A(boolean z) {
        return K(z, true, this.f15023i);
    }

    private AnimatorSet B(final boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (this.f15028n == null) {
            animatorSet.playTogether(s(z), t(z));
        }
        animatorSet.playTogether(H(z), G(z), u(z), w(z), F(z), z(z), q(z), A(z), I(z));
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.U(z ? 1.0f : 0.0f);
                SearchViewAnimationHelper.this.f15017c.a();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.U(z ? 0.0f : 1.0f);
            }
        });
        return animatorSet;
    }

    private int C(View view) {
        int a2 = MarginLayoutParamsCompat.a((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        return ViewUtils.p(this.f15029o) ? this.f15029o.getLeft() - a2 : (this.f15029o.getRight() - this.f15015a.getWidth()) + a2;
    }

    private int D(View view) {
        int b2 = MarginLayoutParamsCompat.b((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        int z = ViewCompat.z(this.f15029o);
        return ViewUtils.p(this.f15029o) ? ((this.f15029o.getWidth() - this.f15029o.getRight()) + b2) - z : (this.f15029o.getLeft() - b2) + z;
    }

    private int E() {
        return ((this.f15029o.getTop() + this.f15029o.getBottom()) / 2) - ((this.f15019e.getTop() + this.f15019e.getBottom()) / 2);
    }

    private Animator F(boolean z) {
        return K(z, false, this.f15018d);
    }

    private Animator G(boolean z) {
        Rect m2 = this.f15027m.m();
        Rect l2 = this.f15027m.l();
        if (m2 == null) {
            m2 = ViewUtils.d(this.f15015a);
        }
        if (l2 == null) {
            l2 = ViewUtils.c(this.f15017c, this.f15029o);
        }
        final Rect rect = new Rect(l2);
        final float cornerSize = this.f15029o.getCornerSize();
        final float max = Math.max(this.f15017c.getCornerRadius(), this.f15027m.k());
        ValueAnimator ofObject = ValueAnimator.ofObject(new RectEvaluator(rect), l2, m2);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.n
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SearchViewAnimationHelper.this.P(cornerSize, max, rect, valueAnimator);
            }
        });
        ofObject.setDuration(z ? 300L : 250L);
        ofObject.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        return ofObject;
    }

    private Animator H(boolean z) {
        TimeInterpolator timeInterpolator = z ? AnimationUtils.f13814a : AnimationUtils.f13815b;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(z ? 300L : 250L);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.a(z, timeInterpolator));
        ofFloat.addUpdateListener(MultiViewUpdateListener.e(this.f15016b));
        return ofFloat;
    }

    private Animator I(boolean z) {
        return K(z, true, this.f15022h);
    }

    private AnimatorSet J(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(L());
        k(animatorSet);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        animatorSet.setDuration(z ? 350L : 300L);
        return animatorSet;
    }

    private Animator K(boolean z, boolean z2, View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(z2 ? D(view) : C(view), 0.0f);
        ofFloat.addUpdateListener(MultiViewUpdateListener.k(view));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(E(), 0.0f);
        ofFloat2.addUpdateListener(MultiViewUpdateListener.l(view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        return animatorSet;
    }

    private Animator L() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.f15017c.getHeight(), 0.0f);
        ofFloat.addUpdateListener(MultiViewUpdateListener.l(this.f15017c));
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void N(DrawerArrowDrawable drawerArrowDrawable, ValueAnimator valueAnimator) {
        drawerArrowDrawable.e(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void O(FadeThroughDrawable fadeThroughDrawable, ValueAnimator valueAnimator) {
        fadeThroughDrawable.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(float f2, float f3, Rect rect, ValueAnimator valueAnimator) {
        this.f15017c.c(rect, AnimationUtils.a(f2, f3, valueAnimator.getAnimatedFraction()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Q() {
        AnimatorSet B = B(true);
        B.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!SearchViewAnimationHelper.this.f15015a.t()) {
                    SearchViewAnimationHelper.this.f15015a.K();
                }
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.SHOWN);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.f15017c.setVisibility(0);
                SearchViewAnimationHelper.this.f15029o.k0();
            }
        });
        B.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R() {
        this.f15017c.setTranslationY(r0.getHeight());
        AnimatorSet J = J(true);
        J.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!SearchViewAnimationHelper.this.f15015a.t()) {
                    SearchViewAnimationHelper.this.f15015a.K();
                }
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.SHOWN);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.f15017c.setVisibility(0);
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.SHOWING);
            }
        });
        J.start();
    }

    private void T(float f2) {
        ActionMenuView b2;
        if (!this.f15015a.w() || (b2 = ToolbarUtils.b(this.f15020f)) == null) {
            return;
        }
        b2.setAlpha(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(float f2) {
        this.f15024j.setAlpha(f2);
        this.f15025k.setAlpha(f2);
        this.f15026l.setAlpha(f2);
        T(f2);
    }

    private void V(Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            ((DrawerArrowDrawable) drawable).e(1.0f);
        }
        if (drawable instanceof FadeThroughDrawable) {
            ((FadeThroughDrawable) drawable).a(1.0f);
        }
    }

    private void W(Toolbar toolbar) {
        ActionMenuView b2 = ToolbarUtils.b(toolbar);
        if (b2 != null) {
            for (int i2 = 0; i2 < b2.getChildCount(); i2++) {
                View childAt = b2.getChildAt(i2);
                childAt.setClickable(false);
                childAt.setFocusable(false);
                childAt.setFocusableInTouchMode(false);
            }
        }
    }

    private void Y() {
        Menu menu = this.f15021g.getMenu();
        if (menu != null) {
            menu.clear();
        }
        if (this.f15029o.getMenuResId() == -1 || !this.f15015a.w()) {
            this.f15021g.setVisibility(8);
            return;
        }
        this.f15021g.z(this.f15029o.getMenuResId());
        W(this.f15021g);
        this.f15021g.setVisibility(0);
    }

    private AnimatorSet b0() {
        if (this.f15015a.t()) {
            this.f15015a.q();
        }
        AnimatorSet B = B(false);
        B.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.f15017c.setVisibility(8);
                if (!SearchViewAnimationHelper.this.f15015a.t()) {
                    SearchViewAnimationHelper.this.f15015a.q();
                }
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.HIDDEN);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.HIDING);
            }
        });
        B.start();
        return B;
    }

    private AnimatorSet c0() {
        if (this.f15015a.t()) {
            this.f15015a.q();
        }
        AnimatorSet J = J(false);
        J.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.f15017c.setVisibility(8);
                if (!SearchViewAnimationHelper.this.f15015a.t()) {
                    SearchViewAnimationHelper.this.f15015a.q();
                }
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.HIDDEN);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.f15015a.setTransitionState(SearchView.TransitionState.HIDING);
            }
        });
        J.start();
        return J;
    }

    private void d0() {
        if (this.f15015a.t()) {
            this.f15015a.K();
        }
        this.f15015a.setTransitionState(SearchView.TransitionState.SHOWING);
        Y();
        this.f15023i.setText(this.f15029o.getText());
        EditText editText = this.f15023i;
        editText.setSelection(editText.getText().length());
        this.f15017c.setVisibility(4);
        this.f15017c.post(new Runnable() { // from class: com.google.android.material.search.q
            @Override // java.lang.Runnable
            public final void run() {
                SearchViewAnimationHelper.this.Q();
            }
        });
    }

    private void e0() {
        if (this.f15015a.t()) {
            final SearchView searchView = this.f15015a;
            Objects.requireNonNull(searchView);
            searchView.postDelayed(new Runnable() { // from class: com.google.android.material.search.r
                @Override // java.lang.Runnable
                public final void run() {
                    SearchView.this.K();
                }
            }, 150L);
        }
        this.f15017c.setVisibility(4);
        this.f15017c.post(new Runnable() { // from class: com.google.android.material.search.s
            @Override // java.lang.Runnable
            public final void run() {
                SearchViewAnimationHelper.this.R();
            }
        });
    }

    private void j(AnimatorSet animatorSet) {
        ActionMenuView b2 = ToolbarUtils.b(this.f15020f);
        if (b2 == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(C(b2), 0.0f);
        ofFloat.addUpdateListener(MultiViewUpdateListener.k(b2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(E(), 0.0f);
        ofFloat2.addUpdateListener(MultiViewUpdateListener.l(b2));
        animatorSet.playTogether(ofFloat, ofFloat2);
    }

    private void k(AnimatorSet animatorSet) {
        ImageButton e2 = ToolbarUtils.e(this.f15020f);
        if (e2 == null) {
            return;
        }
        Drawable q2 = DrawableCompat.q(e2.getDrawable());
        if (!this.f15015a.u()) {
            V(q2);
        } else {
            m(animatorSet, q2);
            n(animatorSet, q2);
        }
    }

    private void l(AnimatorSet animatorSet) {
        ImageButton e2 = ToolbarUtils.e(this.f15020f);
        if (e2 == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(D(e2), 0.0f);
        ofFloat.addUpdateListener(MultiViewUpdateListener.k(e2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(E(), 0.0f);
        ofFloat2.addUpdateListener(MultiViewUpdateListener.l(e2));
        animatorSet.playTogether(ofFloat, ofFloat2);
    }

    private void m(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            final DrawerArrowDrawable drawerArrowDrawable = (DrawerArrowDrawable) drawable;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.o
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SearchViewAnimationHelper.N(DrawerArrowDrawable.this, valueAnimator);
                }
            });
            animatorSet.playTogether(ofFloat);
        }
    }

    private void n(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof FadeThroughDrawable) {
            final FadeThroughDrawable fadeThroughDrawable = (FadeThroughDrawable) drawable;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.p
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SearchViewAnimationHelper.O(FadeThroughDrawable.this, valueAnimator);
                }
            });
            animatorSet.playTogether(ofFloat);
        }
    }

    private Animator q(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(z ? 300L : 250L);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        if (this.f15015a.w()) {
            ofFloat.addUpdateListener(new FadeThroughUpdateListener(ToolbarUtils.b(this.f15021g), ToolbarUtils.b(this.f15020f)));
        }
        return ofFloat;
    }

    private AnimatorSet s(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        k(animatorSet);
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        return animatorSet;
    }

    private AnimatorSet t(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        l(animatorSet);
        j(animatorSet);
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        return animatorSet;
    }

    private Animator u(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(z ? 50L : 42L);
        ofFloat.setStartDelay(z ? 250L : 0L);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13814a));
        ofFloat.addUpdateListener(MultiViewUpdateListener.e(this.f15024j));
        return ofFloat;
    }

    private Animator v(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(z ? 150L : 83L);
        ofFloat.setStartDelay(z ? 75L : 0L);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13814a));
        ofFloat.addUpdateListener(MultiViewUpdateListener.e(this.f15025k, this.f15026l));
        return ofFloat;
    }

    private Animator w(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(v(z), y(z), x(z));
        return animatorSet;
    }

    private Animator x(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.95f, 1.0f);
        ofFloat.setDuration(z ? 300L : 250L);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        ofFloat.addUpdateListener(MultiViewUpdateListener.f(this.f15026l));
        return ofFloat;
    }

    private Animator y(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat((this.f15026l.getHeight() * 0.050000012f) / 2.0f, 0.0f);
        ofFloat.setDuration(z ? 300L : 250L);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.a(z, AnimationUtils.f13815b));
        ofFloat.addUpdateListener(MultiViewUpdateListener.l(this.f15025k));
        return ofFloat;
    }

    private Animator z(boolean z) {
        return K(z, false, this.f15021g);
    }

    AnimatorSet M() {
        return this.f15029o != null ? b0() : c0();
    }

    public BackEventCompat S() {
        return this.f15027m.c();
    }

    void X(SearchBar searchBar) {
        this.f15029o = searchBar;
    }

    void Z() {
        if (this.f15029o != null) {
            d0();
        } else {
            e0();
        }
    }

    void a0(BackEventCompat backEventCompat) {
        this.f15027m.s(backEventCompat, this.f15029o);
    }

    public void f0(BackEventCompat backEventCompat) {
        if (backEventCompat.a() <= 0.0f) {
            return;
        }
        MaterialMainContainerBackHelper materialMainContainerBackHelper = this.f15027m;
        SearchBar searchBar = this.f15029o;
        materialMainContainerBackHelper.t(backEventCompat, searchBar, searchBar.getCornerSize());
        AnimatorSet animatorSet = this.f15028n;
        if (animatorSet != null) {
            animatorSet.setCurrentPlayTime((long) (backEventCompat.a() * this.f15028n.getDuration()));
            return;
        }
        if (this.f15015a.t()) {
            this.f15015a.q();
        }
        if (this.f15015a.u()) {
            AnimatorSet s2 = s(false);
            this.f15028n = s2;
            s2.start();
            this.f15028n.pause();
        }
    }

    public void o() {
        this.f15027m.g(this.f15029o);
        AnimatorSet animatorSet = this.f15028n;
        if (animatorSet != null) {
            animatorSet.reverse();
        }
        this.f15028n = null;
    }

    public void p() {
        this.f15027m.j(M().getTotalDuration(), this.f15029o);
        if (this.f15028n != null) {
            t(false).start();
            this.f15028n.resume();
        }
        this.f15028n = null;
    }

    MaterialMainContainerBackHelper r() {
        return this.f15027m;
    }
}
