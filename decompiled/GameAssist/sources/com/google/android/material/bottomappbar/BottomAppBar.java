package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    private static final int FAB_ALIGNMENT_ANIM_DURATION_DEFAULT = 300;
    private static final float FAB_ALIGNMENT_ANIM_EASING_MIDPOINT = 0.2f;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    public static final int FAB_ANCHOR_MODE_CRADLE = 1;
    public static final int FAB_ANCHOR_MODE_EMBED = 0;
    public static final int FAB_ANIMATION_MODE_SCALE = 0;
    public static final int FAB_ANIMATION_MODE_SLIDE = 1;
    public static final int MENU_ALIGNMENT_MODE_AUTO = 0;
    public static final int MENU_ALIGNMENT_MODE_START = 1;
    private static final int NO_FAB_END_MARGIN = -1;
    private static final int NO_MENU_RES_ID = 0;
    private int animatingModeChangeCounter;
    private ArrayList<AnimationListener> animationListeners;
    private Behavior behavior;
    private int bottomInset;
    private int fabAlignmentMode;

    @Px
    private int fabAlignmentModeEndMargin;
    private int fabAnchorMode;

    @NonNull
    AnimatorListenerAdapter fabAnimationListener;
    private int fabAnimationMode;
    private boolean fabAttached;
    private final int fabOffsetEndMode;

    @NonNull
    TransformationCallback<FloatingActionButton> fabTransformationCallback;
    private boolean hideOnScroll;
    private int leftInset;
    private final MaterialShapeDrawable materialShapeDrawable;
    private int menuAlignmentMode;
    private boolean menuAnimatingWithFabAlignmentMode;

    @Nullable
    private Animator menuAnimator;

    @Nullable
    private Animator modeAnimator;

    @Nullable
    private Integer navigationIconTint;
    private final boolean paddingBottomSystemWindowInsets;
    private final boolean paddingLeftSystemWindowInsets;
    private final boolean paddingRightSystemWindowInsets;

    @MenuRes
    private int pendingMenuResId;
    private final boolean removeEmbeddedFabElevation;
    private int rightInset;
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_BottomAppBar;
    private static final int FAB_ALIGNMENT_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
    private static final int FAB_ALIGNMENT_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;

    interface AnimationListener {
        void a(BottomAppBar bottomAppBar);

        void b(BottomAppBar bottomAppBar);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface FabAnchorMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface MenuAlignmentMode {
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomappbar.BottomAppBar.SavedState.1
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
        int f14006i;

        /* renamed from: j, reason: collision with root package name */
        boolean f14007j;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f14006i);
            parcel.writeInt(this.f14007j ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f14006i = parcel.readInt();
            this.f14007j = parcel.readInt() != 0;
        }
    }

    public BottomAppBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomAppBarStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0(FloatingActionButton floatingActionButton) {
        floatingActionButton.e(this.fabAnimationListener);
        floatingActionButton.f(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BottomAppBar.this.fabAnimationListener.onAnimationStart(animator);
                FloatingActionButton M0 = BottomAppBar.this.M0();
                if (M0 != null) {
                    M0.setTranslationX(BottomAppBar.this.getFabTranslationX());
                }
            }
        });
        floatingActionButton.g(this.fabTransformationCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G0() {
        Animator animator = this.menuAnimator;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.modeAnimator;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    private void I0(int i2, List list) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(M0(), "translationX", P0(i2));
        ofFloat.setDuration(getFabAlignmentAnimationDuration());
        list.add(ofFloat);
    }

    private void J0(final int i2, final boolean z, List list) {
        final ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        float fabAlignmentAnimationDuration = getFabAlignmentAnimationDuration();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        ofFloat.setDuration((long) (0.8f * fabAlignmentAnimationDuration));
        if (Math.abs(actionMenuView.getTranslationX() - O0(actionMenuView, i2, z)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(ofFloat);
            }
        } else {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            ofFloat2.setDuration((long) (fabAlignmentAnimationDuration * FAB_ALIGNMENT_ANIM_EASING_MIDPOINT));
            ofFloat2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.7

                /* renamed from: c, reason: collision with root package name */
                public boolean f13994c;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    this.f13994c = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (this.f13994c) {
                        return;
                    }
                    boolean z2 = BottomAppBar.this.pendingMenuResId != 0;
                    BottomAppBar bottomAppBar = BottomAppBar.this;
                    bottomAppBar.V0(bottomAppBar.pendingMenuResId);
                    BottomAppBar.this.b1(actionMenuView, i2, z, z2);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(ofFloat2, ofFloat);
            list.add(animatorSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K0() {
        ArrayList<AnimationListener> arrayList;
        int i2 = this.animatingModeChangeCounter - 1;
        this.animatingModeChangeCounter = i2;
        if (i2 != 0 || (arrayList = this.animationListeners) == null) {
            return;
        }
        Iterator<AnimationListener> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0() {
        ArrayList<AnimationListener> arrayList;
        int i2 = this.animatingModeChangeCounter;
        this.animatingModeChangeCounter = i2 + 1;
        if (i2 != 0 || (arrayList = this.animationListeners) == null) {
            return;
        }
        Iterator<AnimationListener> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FloatingActionButton M0() {
        View N0 = N0();
        if (N0 instanceof FloatingActionButton) {
            return (FloatingActionButton) N0;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View N0() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).w(this)) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float P0(int i2) {
        boolean p2 = ViewUtils.p(this);
        if (i2 != 1) {
            return 0.0f;
        }
        return ((getMeasuredWidth() / 2) - ((p2 ? this.leftInset : this.rightInset) + ((this.fabAlignmentModeEndMargin == -1 || N0() == null) ? this.fabOffsetEndMode : (r6.getMeasuredWidth() / 2) + this.fabAlignmentModeEndMargin))) * (p2 ? -1 : 1);
    }

    private boolean Q0() {
        FloatingActionButton M0 = M0();
        return M0 != null && M0.p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0(int i2, boolean z) {
        if (!ViewCompat.N(this)) {
            this.menuAnimatingWithFabAlignmentMode = false;
            V0(this.pendingMenuResId);
            return;
        }
        Animator animator = this.menuAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (!Q0()) {
            i2 = 0;
            z = false;
        }
        J0(i2, z, arrayList);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.menuAnimator = animatorSet;
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                BottomAppBar.this.K0();
                BottomAppBar.this.menuAnimatingWithFabAlignmentMode = false;
                BottomAppBar.this.menuAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                BottomAppBar.this.L0();
            }
        });
        this.menuAnimator.start();
    }

    private void T0(int i2) {
        if (this.fabAlignmentMode == i2 || !ViewCompat.N(this)) {
            return;
        }
        Animator animator = this.modeAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.fabAnimationMode == 1) {
            I0(i2, arrayList);
        } else {
            H0(i2, arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        animatorSet.setInterpolator(MotionUtils.g(getContext(), FAB_ALIGNMENT_ANIM_EASING_ATTR, AnimationUtils.f13814a));
        this.modeAnimator = animatorSet;
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                BottomAppBar.this.K0();
                BottomAppBar.this.modeAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                BottomAppBar.this.L0();
            }
        });
        this.modeAnimator.start();
    }

    private Drawable U0(Drawable drawable) {
        if (drawable == null || this.navigationIconTint == null) {
            return drawable;
        }
        Drawable r2 = DrawableCompat.r(drawable.mutate());
        DrawableCompat.n(r2, this.navigationIconTint.intValue());
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.menuAnimator != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        if (Q0()) {
            a1(actionMenuView, this.fabAlignmentMode, this.fabAttached);
        } else {
            a1(actionMenuView, 0, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0() {
        getTopEdgeTreatment().o(getFabTranslationX());
        this.materialShapeDrawable.b0((this.fabAttached && Q0() && this.fabAnchorMode == 1) ? 1.0f : 0.0f);
        View N0 = N0();
        if (N0 != null) {
            N0.setTranslationY(getFabTranslationY());
            N0.setTranslationX(getFabTranslationX());
        }
    }

    private void a1(ActionMenuView actionMenuView, int i2, boolean z) {
        b1(actionMenuView, i2, z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b1(final ActionMenuView actionMenuView, final int i2, final boolean z, boolean z2) {
        Runnable runnable = new Runnable() { // from class: com.google.android.material.bottomappbar.BottomAppBar.8
            @Override // java.lang.Runnable
            public void run() {
                actionMenuView.setTranslationX(BottomAppBar.this.O0(r0, i2, z));
            }
        };
        if (z2) {
            actionMenuView.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c1(BottomAppBar bottomAppBar, View view) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        layoutParams.f2583d = 17;
        int i2 = bottomAppBar.fabAnchorMode;
        if (i2 == 1) {
            layoutParams.f2583d = 17 | 48;
        }
        if (i2 == 0) {
            layoutParams.f2583d |= 80;
        }
    }

    @Nullable
    private ActionMenuView getActionMenuView() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.bottomInset;
    }

    private int getFabAlignmentAnimationDuration() {
        return MotionUtils.f(getContext(), FAB_ALIGNMENT_ANIM_DURATION_ATTR, 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return P0(this.fabAlignmentMode);
    }

    private float getFabTranslationY() {
        if (this.fabAnchorMode == 1) {
            return -getTopEdgeTreatment().c();
        }
        return N0() != null ? (-((getMeasuredHeight() + getBottomInset()) - r0.getMeasuredHeight())) / 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.leftInset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.rightInset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public BottomAppBarTopEdgeTreatment getTopEdgeTreatment() {
        return (BottomAppBarTopEdgeTreatment) this.materialShapeDrawable.getShapeAppearanceModel().p();
    }

    protected void H0(final int i2, List list) {
        FloatingActionButton M0 = M0();
        if (M0 == null || M0.o()) {
            return;
        }
        L0();
        M0.m(new FloatingActionButton.OnVisibilityChangedListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.5
            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
            public void a(FloatingActionButton floatingActionButton) {
                floatingActionButton.setTranslationX(BottomAppBar.this.P0(i2));
                floatingActionButton.s(new FloatingActionButton.OnVisibilityChangedListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.5.1
                    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
                    public void b(FloatingActionButton floatingActionButton2) {
                        BottomAppBar.this.K0();
                    }
                });
            }
        });
    }

    protected int O0(ActionMenuView actionMenuView, int i2, boolean z) {
        int i3 = 0;
        if (this.menuAlignmentMode != 1 && (i2 != 1 || !z)) {
            return 0;
        }
        boolean p2 = ViewUtils.p(this);
        int measuredWidth = p2 ? getMeasuredWidth() : 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            if ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).f143a & 8388615) == 8388611) {
                measuredWidth = p2 ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        int right = p2 ? actionMenuView.getRight() : actionMenuView.getLeft();
        int i5 = p2 ? this.rightInset : -this.leftInset;
        if (getNavigationIcon() == null) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.m3_bottomappbar_horizontal_padding);
            if (!p2) {
                dimensionPixelOffset = -dimensionPixelOffset;
            }
            i3 = dimensionPixelOffset;
        }
        return measuredWidth - ((right + i5) + i3);
    }

    public void V0(int i2) {
        if (i2 != 0) {
            this.pendingMenuResId = 0;
            getMenu().clear();
            z(i2);
        }
    }

    public void Y0(int i2, int i3) {
        this.pendingMenuResId = i3;
        this.menuAnimatingWithFabAlignmentMode = true;
        S0(i2, this.fabAttached);
        T0(i2);
        this.fabAlignmentMode = i2;
    }

    boolean Z0(int i2) {
        float f2 = i2;
        if (f2 == getTopEdgeTreatment().h()) {
            return false;
        }
        getTopEdgeTreatment().n(f2);
        this.materialShapeDrawable.invalidateSelf();
        return true;
    }

    @Nullable
    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.H();
    }

    @Dimension
    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().c();
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    @Px
    public int getFabAlignmentModeEndMargin() {
        return this.fabAlignmentModeEndMargin;
    }

    public int getFabAnchorMode() {
        return this.fabAnchorMode;
    }

    public int getFabAnimationMode() {
        return this.fabAnimationMode;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().f();
    }

    @Dimension
    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().g();
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    public int getMenuAlignmentMode() {
        return this.menuAlignmentMode;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.f(this, this.materialShapeDrawable);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            G0();
            X0();
            final View N0 = N0();
            if (N0 != null && ViewCompat.N(N0)) {
                N0.post(new Runnable() { // from class: com.google.android.material.bottomappbar.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        N0.requestLayout();
                    }
                });
            }
        }
        W0();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        this.fabAlignmentMode = savedState.f14006i;
        this.fabAttached = savedState.f14007j;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f14006i = this.fabAlignmentMode;
        savedState.f14007j = this.fabAttached;
        return savedState;
    }

    public void setBackgroundTint(@Nullable ColorStateList colorStateList) {
        DrawableCompat.o(this.materialShapeDrawable, colorStateList);
    }

    public void setCradleVerticalOffset(@Dimension float f2) {
        if (f2 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().j(f2);
            this.materialShapeDrawable.invalidateSelf();
            X0();
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        this.materialShapeDrawable.Z(f2);
        getBehavior().N(this, this.materialShapeDrawable.D() - this.materialShapeDrawable.C());
    }

    public void setFabAlignmentMode(int i2) {
        Y0(i2, 0);
    }

    public void setFabAlignmentModeEndMargin(@Px int i2) {
        if (this.fabAlignmentModeEndMargin != i2) {
            this.fabAlignmentModeEndMargin = i2;
            X0();
        }
    }

    public void setFabAnchorMode(int i2) {
        this.fabAnchorMode = i2;
        X0();
        View N0 = N0();
        if (N0 != null) {
            c1(this, N0);
            N0.requestLayout();
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabAnimationMode(int i2) {
        this.fabAnimationMode = i2;
    }

    void setFabCornerSize(@Dimension float f2) {
        if (f2 != getTopEdgeTreatment().e()) {
            getTopEdgeTreatment().k(f2);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabCradleMargin(@Dimension float f2) {
        if (f2 != getFabCradleMargin()) {
            getTopEdgeTreatment().l(f2);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(@Dimension float f2) {
        if (f2 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().m(f2);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.hideOnScroll = z;
    }

    public void setMenuAlignmentMode(int i2) {
        if (this.menuAlignmentMode != i2) {
            this.menuAlignmentMode = i2;
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                a1(actionMenuView, this.fabAlignmentMode, Q0());
            }
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(@Nullable Drawable drawable) {
        super.setNavigationIcon(U0(drawable));
    }

    public void setNavigationIconTint(@ColorInt int i2) {
        this.navigationIconTint = Integer.valueOf(i2);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BottomAppBar(@androidx.annotation.NonNull android.content.Context r13, @androidx.annotation.Nullable android.util.AttributeSet r14, int r15) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    @NonNull
    public Behavior getBehavior() {
        if (this.behavior == null) {
            this.behavior = new Behavior();
        }
        return this.behavior;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* renamed from: s, reason: collision with root package name */
        private final Rect f14004s;
        private WeakReference t;
        private int u;
        private final View.OnLayoutChangeListener v;

        public Behavior() {
            this.v = new View.OnLayoutChangeListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.Behavior.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.t.get();
                    if (bottomAppBar == null || !((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton))) {
                        view.removeOnLayoutChangeListener(this);
                        return;
                    }
                    int height = view.getHeight();
                    if (view instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                        floatingActionButton.j(Behavior.this.f14004s);
                        int height2 = Behavior.this.f14004s.height();
                        bottomAppBar.Z0(height2);
                        bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().r().a(new RectF(Behavior.this.f14004s)));
                        height = height2;
                    }
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    if (Behavior.this.u == 0) {
                        if (bottomAppBar.fabAnchorMode == 1) {
                            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fab_bottom_margin) - ((view.getMeasuredHeight() - height) / 2));
                        }
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = bottomAppBar.getLeftInset();
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = bottomAppBar.getRightInset();
                        if (ViewUtils.p(view)) {
                            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin += bottomAppBar.fabOffsetEndMode;
                        } else {
                            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin += bottomAppBar.fabOffsetEndMode;
                        }
                    }
                    bottomAppBar.X0();
                }
            };
            this.f14004s = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: W, reason: merged with bridge method [inline-methods] */
        public boolean q(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i2) {
            this.t = new WeakReference(bottomAppBar);
            View N0 = bottomAppBar.N0();
            if (N0 != null && !ViewCompat.N(N0)) {
                BottomAppBar.c1(bottomAppBar, N0);
                this.u = ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) N0.getLayoutParams())).bottomMargin;
                if (N0 instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) N0;
                    if (bottomAppBar.fabAnchorMode == 0 && bottomAppBar.removeEmbeddedFabElevation) {
                        ViewCompat.q0(floatingActionButton, 0.0f);
                        floatingActionButton.setCompatElevation(0.0f);
                    }
                    if (floatingActionButton.getShowMotionSpec() == null) {
                        floatingActionButton.setShowMotionSpecResource(R.animator.mtrl_fab_show_motion_spec);
                    }
                    if (floatingActionButton.getHideMotionSpec() == null) {
                        floatingActionButton.setHideMotionSpecResource(R.animator.mtrl_fab_hide_motion_spec);
                    }
                    bottomAppBar.F0(floatingActionButton);
                }
                N0.addOnLayoutChangeListener(this.v);
                bottomAppBar.X0();
            }
            coordinatorLayout.M(bottomAppBar, i2);
            return super.q(coordinatorLayout, bottomAppBar, i2);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: X, reason: merged with bridge method [inline-methods] */
        public boolean F(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i2, int i3) {
            return bottomAppBar.getHideOnScroll() && super.F(coordinatorLayout, bottomAppBar, view, view2, i2, i3);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.v = new View.OnLayoutChangeListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.Behavior.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.t.get();
                    if (bottomAppBar == null || !((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton))) {
                        view.removeOnLayoutChangeListener(this);
                        return;
                    }
                    int height = view.getHeight();
                    if (view instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                        floatingActionButton.j(Behavior.this.f14004s);
                        int height2 = Behavior.this.f14004s.height();
                        bottomAppBar.Z0(height2);
                        bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().r().a(new RectF(Behavior.this.f14004s)));
                        height = height2;
                    }
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    if (Behavior.this.u == 0) {
                        if (bottomAppBar.fabAnchorMode == 1) {
                            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fab_bottom_margin) - ((view.getMeasuredHeight() - height) / 2));
                        }
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = bottomAppBar.getLeftInset();
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = bottomAppBar.getRightInset();
                        if (ViewUtils.p(view)) {
                            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin += bottomAppBar.fabOffsetEndMode;
                        } else {
                            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin += bottomAppBar.fabOffsetEndMode;
                        }
                    }
                    bottomAppBar.X0();
                }
            };
            this.f14004s = new Rect();
        }
    }
}
