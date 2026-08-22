package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimatorRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageHelper;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TintableImageSourceView;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.expandable.ExpandableTransformationWidget;
import com.google.android.material.expandable.ExpandableWidgetHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButtonImpl;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.stateful.ExtendableSavedState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements TintableBackgroundView, TintableImageSourceView, ExpandableTransformationWidget, Shapeable, CoordinatorLayout.AttachedBehavior {
    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_FloatingActionButton;
    private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int NO_CUSTOM_SIZE = 0;
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;

    @Nullable
    private ColorStateList backgroundTint;

    @Nullable
    private PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    boolean compatPadding;
    private int customSize;

    @NonNull
    private final ExpandableWidgetHelper expandableWidgetHelper;

    @NonNull
    private final AppCompatImageHelper imageHelper;

    @Nullable
    private PorterDuff.Mode imageMode;
    private int imagePadding;

    @Nullable
    private ColorStateList imageTint;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;

    @Nullable
    private ColorStateList rippleColor;
    final Rect shadowPadding;
    private int size;
    private final Rect touchArea;

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* renamed from: J */
        public /* bridge */ /* synthetic */ boolean g(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            return super.g(coordinatorLayout, floatingActionButton, rect);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* renamed from: M */
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.m(coordinatorLayout, floatingActionButton, view);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* renamed from: N */
        public /* bridge */ /* synthetic */ boolean q(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i2) {
            return super.q(coordinatorLayout, floatingActionButton, i2);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void l(CoordinatorLayout.LayoutParams layoutParams) {
            super.l(layoutParams);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        @VisibleForTesting
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(OnVisibilityChangedListener onVisibilityChangedListener) {
            super.setInternalAutoHideListener(onVisibilityChangedListener);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static abstract class OnVisibilityChangedListener {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    private class ShadowDelegateImpl implements ShadowViewDelegate {
        ShadowDelegateImpl() {
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public void a(int i2, int i3, int i4, int i5) {
            FloatingActionButton.this.shadowPadding.set(i2, i3, i4, i5);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.setPadding(i2 + floatingActionButton.imagePadding, i3 + FloatingActionButton.this.imagePadding, i4 + FloatingActionButton.this.imagePadding, i5 + FloatingActionButton.this.imagePadding);
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public boolean b() {
            return FloatingActionButton.this.compatPadding;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null) {
                FloatingActionButton.super.setBackgroundDrawable(drawable);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Size {
    }

    class TransformationCallbackWrapper<T extends FloatingActionButton> implements FloatingActionButtonImpl.InternalTransformationCallback {

        /* renamed from: a, reason: collision with root package name */
        private final TransformationCallback f14634a;

        TransformationCallbackWrapper(TransformationCallback transformationCallback) {
            this.f14634a = transformationCallback;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalTransformationCallback
        public void a() {
            this.f14634a.b(FloatingActionButton.this);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalTransformationCallback
        public void b() {
            this.f14634a.a(FloatingActionButton.this);
        }

        public boolean equals(Object obj) {
            return (obj instanceof TransformationCallbackWrapper) && ((TransformationCallbackWrapper) obj).f14634a.equals(this.f14634a);
        }

        public int hashCode() {
            return this.f14634a.hashCode();
        }
    }

    public FloatingActionButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.floatingActionButtonStyle);
    }

    private FloatingActionButtonImpl getImpl() {
        if (this.impl == null) {
            this.impl = h();
        }
        return this.impl;
    }

    private FloatingActionButtonImpl h() {
        return new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
    }

    private int k(int i2) {
        int i3 = this.customSize;
        if (i3 != 0) {
            return i3;
        }
        Resources resources = getResources();
        return i2 != -1 ? i2 != 1 ? resources.getDimensionPixelSize(R.dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R.dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < AUTO_MINI_LARGEST_SCREEN_WIDTH ? k(1) : k(0);
    }

    private void l(Rect rect) {
        j(rect);
        int i2 = -this.impl.w();
        rect.inset(i2, i2);
    }

    private void q(Rect rect) {
        int i2 = rect.left;
        Rect rect2 = this.shadowPadding;
        rect.left = i2 + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    private void r() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.imageTint;
        if (colorStateList == null) {
            DrawableCompat.c(drawable);
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.imageMode;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(AppCompatDrawableManager.e(colorForState, mode));
    }

    private FloatingActionButtonImpl.InternalVisibilityChangedListener u(final OnVisibilityChangedListener onVisibilityChangedListener) {
        if (onVisibilityChangedListener == null) {
            return null;
        }
        return new FloatingActionButtonImpl.InternalVisibilityChangedListener() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButton.1
            @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalVisibilityChangedListener
            public void a() {
                onVisibilityChangedListener.b(FloatingActionButton.this);
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalVisibilityChangedListener
            public void b() {
                onVisibilityChangedListener.a(FloatingActionButton.this);
            }
        };
    }

    @Override // com.google.android.material.expandable.ExpandableWidget
    public boolean a() {
        return this.expandableWidgetHelper.c();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().F(getDrawableState());
    }

    public void e(Animator.AnimatorListener animatorListener) {
        getImpl().e(animatorListener);
    }

    public void f(Animator.AnimatorListener animatorListener) {
        getImpl().f(animatorListener);
    }

    public void g(TransformationCallback transformationCallback) {
        getImpl().g(new TransformationCallbackWrapper(transformationCallback));
    }

    @Override // android.view.View
    @Nullable
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    @Override // android.view.View
    @Nullable
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    @NonNull
    public CoordinatorLayout.Behavior<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().n();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().q();
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().t();
    }

    @Nullable
    public Drawable getContentBackground() {
        return getImpl().m();
    }

    @Px
    public int getCustomSize() {
        return this.customSize;
    }

    public int getExpandedComponentIdHint() {
        return this.expandableWidgetHelper.b();
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return getImpl().p();
    }

    @ColorInt
    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.rippleColor;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    @Nullable
    public ColorStateList getRippleColorStateList() {
        return this.rippleColor;
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return (ShapeAppearanceModel) Preconditions.h(getImpl().u());
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return getImpl().v();
    }

    public int getSize() {
        return this.size;
    }

    int getSizeDimension() {
        return k(this.size);
    }

    @Nullable
    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    @Nullable
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    @Nullable
    public ColorStateList getSupportImageTintList() {
        return this.imageTint;
    }

    @Nullable
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.imageMode;
    }

    public boolean getUseCompatPadding() {
        return this.compatPadding;
    }

    public boolean i(Rect rect) {
        if (!ViewCompat.N(this)) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        q(rect);
        return true;
    }

    public void j(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        q(rect);
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().B();
    }

    public void m(OnVisibilityChangedListener onVisibilityChangedListener) {
        n(onVisibilityChangedListener, true);
    }

    void n(OnVisibilityChangedListener onVisibilityChangedListener, boolean z) {
        getImpl().x(u(onVisibilityChangedListener), z);
    }

    public boolean o() {
        return getImpl().z();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().C();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().E();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int sizeDimension = getSizeDimension();
        this.imagePadding = (sizeDimension - this.maxImageSize) / 2;
        getImpl().g0();
        int min = Math.min(View.resolveSize(sizeDimension, i2), View.resolveSize(sizeDimension, i3));
        Rect rect = this.shadowPadding;
        setMeasuredDimension(rect.left + min + rect.right, min + rect.top + rect.bottom);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.a());
        this.expandableWidgetHelper.d((Bundle) Preconditions.h((Bundle) extendableSavedState.f15338i.get(EXPANDABLE_WIDGET_HELPER_KEY)));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (onSaveInstanceState == null) {
            onSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(onSaveInstanceState);
        extendableSavedState.f15338i.put(EXPANDABLE_WIDGET_HELPER_KEY, this.expandableWidgetHelper.e());
        return extendableSavedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            l(this.touchArea);
            if (!this.touchArea.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean p() {
        return getImpl().A();
    }

    public void s(OnVisibilityChangedListener onVisibilityChangedListener) {
        t(onVisibilityChangedListener, true);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            getImpl().M(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            getImpl().N(mode);
        }
    }

    public void setCompatElevation(float f2) {
        getImpl().O(f2);
    }

    public void setCompatElevationResource(@DimenRes int i2) {
        setCompatElevation(getResources().getDimension(i2));
    }

    public void setCompatHoveredFocusedTranslationZ(float f2) {
        getImpl().R(f2);
    }

    public void setCompatHoveredFocusedTranslationZResource(@DimenRes int i2) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i2));
    }

    public void setCompatPressedTranslationZ(float f2) {
        getImpl().V(f2);
    }

    public void setCompatPressedTranslationZResource(@DimenRes int i2) {
        setCompatPressedTranslationZ(getResources().getDimension(i2));
    }

    public void setCustomSize(@Px int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i2 != this.customSize) {
            this.customSize = i2;
            requestLayout();
        }
    }

    @Override // android.view.View
    @RequiresApi
    public void setElevation(float f2) {
        super.setElevation(f2);
        getImpl().h0(f2);
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().o()) {
            getImpl().P(z);
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(@IdRes int i2) {
        this.expandableWidgetHelper.f(i2);
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        getImpl().Q(motionSpec);
    }

    public void setHideMotionSpecResource(@AnimatorRes int i2) {
        setHideMotionSpec(MotionSpec.d(getContext(), i2));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(@Nullable Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            getImpl().f0();
            if (this.imageTint != null) {
                r();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i2) {
        this.imageHelper.i(i2);
        r();
    }

    public void setMaxImageSize(int i2) {
        this.maxImageSize = i2;
        getImpl().T(i2);
    }

    public void setRippleColor(@ColorInt int i2) {
        setRippleColor(ColorStateList.valueOf(i2));
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        super.setScaleX(f2);
        getImpl().J();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        super.setScaleY(f2);
        getImpl().J();
    }

    @RestrictTo
    @VisibleForTesting
    public void setShadowPaddingEnabled(boolean z) {
        getImpl().X(z);
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        getImpl().Y(shapeAppearanceModel);
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        getImpl().Z(motionSpec);
    }

    public void setShowMotionSpecResource(@AnimatorRes int i2) {
        setShowMotionSpec(MotionSpec.d(getContext(), i2));
    }

    public void setSize(int i2) {
        this.customSize = 0;
        if (i2 != this.size) {
            this.size = i2;
            requestLayout();
        }
    }

    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    public void setSupportImageTintList(@Nullable ColorStateList colorStateList) {
        if (this.imageTint != colorStateList) {
            this.imageTint = colorStateList;
            r();
        }
    }

    public void setSupportImageTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.imageMode != mode) {
            this.imageMode = mode;
            r();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        super.setTranslationX(f2);
        getImpl().K();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        super.setTranslationY(f2);
        getImpl().K();
    }

    @Override // android.view.View
    public void setTranslationZ(float f2) {
        super.setTranslationZ(f2);
        getImpl().K();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.compatPadding != z) {
            this.compatPadding = z;
            getImpl().D();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    void t(OnVisibilityChangedListener onVisibilityChangedListener, boolean z) {
        getImpl().d0(u(onVisibilityChangedListener), z);
    }

    protected static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {

        /* renamed from: c, reason: collision with root package name */
        private Rect f14630c;

        /* renamed from: h, reason: collision with root package name */
        private OnVisibilityChangedListener f14631h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f14632i;

        public BaseBehavior() {
            this.f14632i = true;
        }

        private static boolean K(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private void L(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            Rect rect = floatingActionButton.shadowPadding;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return;
            }
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
            int i2 = 0;
            int i3 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin ? -rect.left : 0;
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) {
                i2 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) {
                i2 = -rect.top;
            }
            if (i2 != 0) {
                ViewCompat.T(floatingActionButton, i2);
            }
            if (i3 != 0) {
                ViewCompat.S(floatingActionButton, i3);
            }
        }

        private boolean O(View view, FloatingActionButton floatingActionButton) {
            return this.f14632i && ((CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams()).e() == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        private boolean P(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!O(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.f14630c == null) {
                this.f14630c = new Rect();
            }
            Rect rect = this.f14630c;
            DescendantOffsetUtils.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.n(this.f14631h, false);
                return true;
            }
            floatingActionButton.t(this.f14631h, false);
            return true;
        }

        private boolean Q(View view, FloatingActionButton floatingActionButton) {
            if (!O(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.n(this.f14631h, false);
                return true;
            }
            floatingActionButton.t(this.f14631h, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: J, reason: merged with bridge method [inline-methods] */
        public boolean g(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.shadowPadding;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean m(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                P(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
                return false;
            }
            if (!K(view)) {
                return false;
            }
            Q(view, floatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean q(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i2) {
            List v = coordinatorLayout.v(floatingActionButton);
            int size = v.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = (View) v.get(i3);
                if (!(view instanceof AppBarLayout)) {
                    if (K(view) && Q(view, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (P(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.M(floatingActionButton, i2);
            L(coordinatorLayout, floatingActionButton);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void l(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.f2587h == 0) {
                layoutParams.f2587h = 80;
            }
        }

        @VisibleForTesting
        public void setInternalAutoHideListener(OnVisibilityChangedListener onVisibilityChangedListener) {
            this.f14631h = onVisibilityChangedListener;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton_Behavior_Layout);
            this.f14632i = obtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FloatingActionButton(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.Nullable android.util.AttributeSet r12, int r13) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.FloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            getImpl().W(this.rippleColor);
        }
    }
}
