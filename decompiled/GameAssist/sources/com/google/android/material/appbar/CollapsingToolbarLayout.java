package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_CollapsingToolbar;
    public static final int TITLE_COLLAPSE_MODE_FADE = 1;
    public static final int TITLE_COLLAPSE_MODE_SCALE = 0;

    @NonNull
    final CollapsingTextHelper collapsingTextHelper;
    private boolean collapsingTitleEnabled;

    @Nullable
    private Drawable contentScrim;
    int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;

    @NonNull
    final ElevationOverlayProvider elevationOverlayProvider;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    private int extraMultilineHeight;
    private boolean extraMultilineHeightEnabled;
    private boolean forceApplySystemWindowInsetTop;

    @Nullable
    WindowInsetsCompat lastInsets;
    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;
    private boolean refreshToolbar;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private final TimeInterpolator scrimAnimationFadeInInterpolator;
    private final TimeInterpolator scrimAnimationFadeOutInterpolator;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;

    @Nullable
    Drawable statusBarScrim;
    private int titleCollapseMode;
    private final Rect tmpRect;

    @Nullable
    private ViewGroup toolbar;

    @Nullable
    private View toolbarDirectChild;
    private int toolbarId;
    private int topInsetApplied;

    private class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
        OffsetUpdateListener() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void a(AppBarLayout appBarLayout, int i2) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.currentOffset = i2;
            WindowInsetsCompat windowInsetsCompat = collapsingToolbarLayout.lastInsets;
            int l2 = windowInsetsCompat != null ? windowInsetsCompat.l() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ViewOffsetHelper k2 = CollapsingToolbarLayout.k(childAt);
                int i4 = layoutParams.f13873a;
                if (i4 == 1) {
                    k2.f(MathUtils.b(-i2, 0, CollapsingToolbarLayout.this.i(childAt)));
                } else if (i4 == 2) {
                    k2.f(Math.round((-i2) * layoutParams.f13874b));
                }
            }
            CollapsingToolbarLayout.this.v();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.statusBarScrim != null && l2 > 0) {
                ViewCompat.Z(collapsingToolbarLayout2);
            }
            int height = (CollapsingToolbarLayout.this.getHeight() - ViewCompat.w(CollapsingToolbarLayout.this)) - l2;
            float f2 = height;
            CollapsingToolbarLayout.this.collapsingTextHelper.A0(Math.min(1.0f, (r0 - CollapsingToolbarLayout.this.getScrimVisibleHeightTrigger()) / f2));
            CollapsingToolbarLayout collapsingToolbarLayout3 = CollapsingToolbarLayout.this;
            collapsingToolbarLayout3.collapsingTextHelper.n0(collapsingToolbarLayout3.currentOffset + height);
            CollapsingToolbarLayout.this.collapsingTextHelper.y0(Math.abs(i2) / f2);
        }
    }

    @RequiresApi
    @RestrictTo
    public interface StaticLayoutBuilderConfigurer extends com.google.android.material.internal.StaticLayoutBuilderConfigurer {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface TitleCollapseMode {
    }

    public CollapsingToolbarLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.collapsingToolbarLayoutStyle);
    }

    private void a(int i2) {
        d();
        ValueAnimator valueAnimator = this.scrimAnimator;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.scrimAnimator = valueAnimator2;
            valueAnimator2.setInterpolator(i2 > this.scrimAlpha ? this.scrimAnimationFadeInInterpolator : this.scrimAnimationFadeOutInterpolator);
            this.scrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.CollapsingToolbarLayout.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator3.getAnimatedValue()).intValue());
                }
            });
        } else if (valueAnimator.isRunning()) {
            this.scrimAnimator.cancel();
        }
        this.scrimAnimator.setDuration(this.scrimAnimationDuration);
        this.scrimAnimator.setIntValues(this.scrimAlpha, i2);
        this.scrimAnimator.start();
    }

    private TextUtils.TruncateAt b(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 3 ? TextUtils.TruncateAt.END : TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.MIDDLE : TextUtils.TruncateAt.START;
    }

    private void c(AppBarLayout appBarLayout) {
        if (l()) {
            appBarLayout.setLiftOnScroll(false);
        }
    }

    private void d() {
        if (this.refreshToolbar) {
            ViewGroup viewGroup = null;
            this.toolbar = null;
            this.toolbarDirectChild = null;
            int i2 = this.toolbarId;
            if (i2 != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i2);
                this.toolbar = viewGroup2;
                if (viewGroup2 != null) {
                    this.toolbarDirectChild = e(viewGroup2);
                }
            }
            if (this.toolbar == null) {
                int childCount = getChildCount();
                int i3 = 0;
                while (true) {
                    if (i3 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i3);
                    if (m(childAt)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                    i3++;
                }
                this.toolbar = viewGroup;
            }
            u();
            this.refreshToolbar = false;
        }
    }

    private View e(View view) {
        for (ViewParent parent = view.getParent(); parent != this && parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                view = parent;
            }
        }
        return view;
    }

    @ColorInt
    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        ColorStateList h2 = MaterialColors.h(getContext(), R.attr.colorSurfaceContainer);
        if (h2 != null) {
            return h2.getDefaultColor();
        }
        return this.elevationOverlayProvider.d(getResources().getDimension(R.dimen.design_appbar_elevation));
    }

    private static int h(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return view.getMeasuredHeight();
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private static CharSequence j(View view) {
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getTitle();
        }
        if (view instanceof android.widget.Toolbar) {
            return ((android.widget.Toolbar) view).getTitle();
        }
        return null;
    }

    static ViewOffsetHelper k(View view) {
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(R.id.view_offset_helper);
        if (viewOffsetHelper != null) {
            return viewOffsetHelper;
        }
        ViewOffsetHelper viewOffsetHelper2 = new ViewOffsetHelper(view);
        view.setTag(R.id.view_offset_helper, viewOffsetHelper2);
        return viewOffsetHelper2;
    }

    private boolean l() {
        return this.titleCollapseMode == 1;
    }

    private static boolean m(View view) {
        return (view instanceof Toolbar) || (view instanceof android.widget.Toolbar);
    }

    private boolean n(View view) {
        View view2 = this.toolbarDirectChild;
        if (view2 == null || view2 == this) {
            if (view != this.toolbar) {
                return false;
            }
        } else if (view != view2) {
            return false;
        }
        return true;
    }

    private void q(boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        View view = this.toolbarDirectChild;
        if (view == null) {
            view = this.toolbar;
        }
        int i6 = i(view);
        DescendantOffsetUtils.a(this, this.dummyView, this.tmpRect);
        ViewGroup viewGroup = this.toolbar;
        if (viewGroup instanceof Toolbar) {
            Toolbar toolbar = (Toolbar) viewGroup;
            i2 = toolbar.getTitleMarginStart();
            i4 = toolbar.getTitleMarginEnd();
            i5 = toolbar.getTitleMarginTop();
            i3 = toolbar.getTitleMarginBottom();
        } else if (viewGroup instanceof android.widget.Toolbar) {
            android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
            i2 = toolbar2.getTitleMarginStart();
            i4 = toolbar2.getTitleMarginEnd();
            i5 = toolbar2.getTitleMarginTop();
            i3 = toolbar2.getTitleMarginBottom();
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        Rect rect = this.tmpRect;
        int i7 = rect.left + (z ? i4 : i2);
        int i8 = rect.top + i6 + i5;
        int i9 = rect.right;
        if (!z) {
            i2 = i4;
        }
        collapsingTextHelper.e0(i7, i8, i9 - i2, (rect.bottom + i6) - i3);
    }

    private void r() {
        setContentDescription(getTitle());
    }

    private void s(Drawable drawable, int i2, int i3) {
        t(drawable, this.toolbar, i2, i3);
    }

    private void t(Drawable drawable, View view, int i2, int i3) {
        if (l() && view != null && this.collapsingTitleEnabled) {
            i3 = view.getBottom();
        }
        drawable.setBounds(0, 0, i2, i3);
    }

    private void u() {
        View view;
        if (!this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.dummyView);
            }
        }
        if (!this.collapsingTitleEnabled || this.toolbar == null) {
            return;
        }
        if (this.dummyView == null) {
            this.dummyView = new View(getContext());
        }
        if (this.dummyView.getParent() == null) {
            this.toolbar.addView(this.dummyView, -1, -1);
        }
    }

    private void w(int i2, int i3, int i4, int i5, boolean z) {
        View view;
        if (!this.collapsingTitleEnabled || (view = this.dummyView) == null) {
            return;
        }
        boolean z2 = ViewCompat.M(view) && this.dummyView.getVisibility() == 0;
        this.drawCollapsingTitle = z2;
        if (z2 || z) {
            boolean z3 = ViewCompat.v(this) == 1;
            q(z3);
            this.collapsingTextHelper.o0(z3 ? this.expandedMarginEnd : this.expandedMarginStart, this.tmpRect.top + this.expandedMarginTop, (i4 - i2) - (z3 ? this.expandedMarginStart : this.expandedMarginEnd), (i5 - i3) - this.expandedMarginBottom);
            this.collapsingTextHelper.b0(z);
        }
    }

    private void x() {
        if (this.toolbar != null && this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.O())) {
            setTitle(j(this.toolbar));
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        d();
        if (this.toolbar == null && (drawable = this.contentScrim) != null && this.scrimAlpha > 0) {
            drawable.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
        }
        if (this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            if (this.toolbar == null || this.contentScrim == null || this.scrimAlpha <= 0 || !l() || this.collapsingTextHelper.F() >= this.collapsingTextHelper.G()) {
                this.collapsingTextHelper.l(canvas);
            } else {
                int save = canvas.save();
                canvas.clipRect(this.contentScrim.getBounds(), Region.Op.DIFFERENCE);
                this.collapsingTextHelper.l(canvas);
                canvas.restoreToCount(save);
            }
        }
        if (this.statusBarScrim == null || this.scrimAlpha <= 0) {
            return;
        }
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        int l2 = windowInsetsCompat != null ? windowInsetsCompat.l() : 0;
        if (l2 > 0) {
            this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), l2 - this.currentOffset);
            this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
            this.statusBarScrim.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        boolean z;
        if (this.contentScrim == null || this.scrimAlpha <= 0 || !n(view)) {
            z = false;
        } else {
            t(this.contentScrim, view, getWidth(), getHeight());
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
            z = true;
        }
        return super.drawChild(canvas, view, j2) || z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarScrim;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        if (collapsingTextHelper != null) {
            state |= collapsingTextHelper.I0(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public int getCollapsedTitleGravity() {
        return this.collapsingTextHelper.q();
    }

    public float getCollapsedTitleTextSize() {
        return this.collapsingTextHelper.u();
    }

    @NonNull
    public Typeface getCollapsedTitleTypeface() {
        return this.collapsingTextHelper.v();
    }

    @Nullable
    public Drawable getContentScrim() {
        return this.contentScrim;
    }

    public int getExpandedTitleGravity() {
        return this.collapsingTextHelper.B();
    }

    public int getExpandedTitleMarginBottom() {
        return this.expandedMarginBottom;
    }

    public int getExpandedTitleMarginEnd() {
        return this.expandedMarginEnd;
    }

    public int getExpandedTitleMarginStart() {
        return this.expandedMarginStart;
    }

    public int getExpandedTitleMarginTop() {
        return this.expandedMarginTop;
    }

    public float getExpandedTitleTextSize() {
        return this.collapsingTextHelper.D();
    }

    @NonNull
    public Typeface getExpandedTitleTypeface() {
        return this.collapsingTextHelper.E();
    }

    @RequiresApi
    @RestrictTo
    public int getHyphenationFrequency() {
        return this.collapsingTextHelper.H();
    }

    @RestrictTo
    public int getLineCount() {
        return this.collapsingTextHelper.I();
    }

    @RequiresApi
    @RestrictTo
    public float getLineSpacingAdd() {
        return this.collapsingTextHelper.J();
    }

    @RequiresApi
    @RestrictTo
    public float getLineSpacingMultiplier() {
        return this.collapsingTextHelper.K();
    }

    @RestrictTo
    public int getMaxLines() {
        return this.collapsingTextHelper.L();
    }

    int getScrimAlpha() {
        return this.scrimAlpha;
    }

    public long getScrimAnimationDuration() {
        return this.scrimAnimationDuration;
    }

    public int getScrimVisibleHeightTrigger() {
        int i2 = this.scrimVisibleHeightTrigger;
        if (i2 >= 0) {
            return i2 + this.topInsetApplied + this.extraMultilineHeight;
        }
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        int l2 = windowInsetsCompat != null ? windowInsetsCompat.l() : 0;
        int w = ViewCompat.w(this);
        return w > 0 ? Math.min((w * 2) + l2, getHeight()) : getHeight() / 3;
    }

    @Nullable
    public Drawable getStatusBarScrim() {
        return this.statusBarScrim;
    }

    @Nullable
    public CharSequence getTitle() {
        if (this.collapsingTitleEnabled) {
            return this.collapsingTextHelper.O();
        }
        return null;
    }

    public int getTitleCollapseMode() {
        return this.titleCollapseMode;
    }

    @Nullable
    public TimeInterpolator getTitlePositionInterpolator() {
        return this.collapsingTextHelper.N();
    }

    @NonNull
    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.collapsingTextHelper.R();
    }

    final int i(View view) {
        return ((getHeight() - k(view).b()) - view.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    WindowInsetsCompat o(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = ViewCompat.s(this) ? windowInsetsCompat : null;
        if (!ObjectsCompat.a(this.lastInsets, windowInsetsCompat2)) {
            this.lastInsets = windowInsetsCompat2;
            requestLayout();
        }
        return windowInsetsCompat.c();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            c(appBarLayout);
            ViewCompat.r0(this, ViewCompat.s(appBarLayout));
            if (this.onOffsetChangedListener == null) {
                this.onOffsetChangedListener = new OffsetUpdateListener();
            }
            appBarLayout.d(this.onOffsetChangedListener);
            ViewCompat.f0(this);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.collapsingTextHelper.Y(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = this.onOffsetChangedListener;
        if (onOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).x(onOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            int l2 = windowInsetsCompat.l();
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = getChildAt(i6);
                if (!ViewCompat.s(childAt) && childAt.getTop() < l2) {
                    ViewCompat.T(childAt, l2);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i7 = 0; i7 < childCount2; i7++) {
            k(getChildAt(i7)).d();
        }
        w(i2, i3, i4, i5, false);
        x();
        v();
        int childCount3 = getChildCount();
        for (int i8 = 0; i8 < childCount3; i8++) {
            k(getChildAt(i8)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        d();
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i3);
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        int l2 = windowInsetsCompat != null ? windowInsetsCompat.l() : 0;
        if ((mode == 0 || this.forceApplySystemWindowInsetTop) && l2 > 0) {
            this.topInsetApplied = l2;
            super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + l2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
        }
        if (this.extraMultilineHeightEnabled && this.collapsingTextHelper.L() > 1) {
            x();
            w(0, 0, getMeasuredWidth(), getMeasuredHeight(), true);
            int z = this.collapsingTextHelper.z();
            if (z > 1) {
                this.extraMultilineHeight = Math.round(this.collapsingTextHelper.A()) * (z - 1);
                super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.extraMultilineHeight, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
            }
        }
        ViewGroup viewGroup = this.toolbar;
        if (viewGroup != null) {
            View view = this.toolbarDirectChild;
            if (view == null || view == this) {
                setMinimumHeight(h(viewGroup));
            } else {
                setMinimumHeight(h(view));
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Drawable drawable = this.contentScrim;
        if (drawable != null) {
            s(drawable, i2, i3);
        }
    }

    public void p(boolean z, boolean z2) {
        if (this.scrimsAreShown != z) {
            if (z2) {
                a(z ? 255 : 0);
            } else {
                setScrimAlpha(z ? 255 : 0);
            }
            this.scrimsAreShown = z;
        }
    }

    public void setCollapsedTitleGravity(int i2) {
        this.collapsingTextHelper.j0(i2);
    }

    public void setCollapsedTitleTextAppearance(@StyleRes int i2) {
        this.collapsingTextHelper.g0(i2);
    }

    public void setCollapsedTitleTextColor(@ColorInt int i2) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setCollapsedTitleTextSize(float f2) {
        this.collapsingTextHelper.k0(f2);
    }

    public void setCollapsedTitleTypeface(@Nullable Typeface typeface) {
        this.collapsingTextHelper.l0(typeface);
    }

    public void setContentScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.contentScrim = mutate;
            if (mutate != null) {
                s(mutate, getWidth(), getHeight());
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            ViewCompat.Z(this);
        }
    }

    public void setContentScrimColor(@ColorInt int i2) {
        setContentScrim(new ColorDrawable(i2));
    }

    public void setContentScrimResource(@DrawableRes int i2) {
        setContentScrim(ContextCompat.e(getContext(), i2));
    }

    public void setExpandedTitleColor(@ColorInt int i2) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setExpandedTitleGravity(int i2) {
        this.collapsingTextHelper.u0(i2);
    }

    public void setExpandedTitleMarginBottom(int i2) {
        this.expandedMarginBottom = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i2) {
        this.expandedMarginEnd = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i2) {
        this.expandedMarginStart = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i2) {
        this.expandedMarginTop = i2;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(@StyleRes int i2) {
        this.collapsingTextHelper.r0(i2);
    }

    public void setExpandedTitleTextColor(@NonNull ColorStateList colorStateList) {
        this.collapsingTextHelper.t0(colorStateList);
    }

    public void setExpandedTitleTextSize(float f2) {
        this.collapsingTextHelper.v0(f2);
    }

    public void setExpandedTitleTypeface(@Nullable Typeface typeface) {
        this.collapsingTextHelper.w0(typeface);
    }

    @RestrictTo
    public void setExtraMultilineHeightEnabled(boolean z) {
        this.extraMultilineHeightEnabled = z;
    }

    @RestrictTo
    public void setForceApplySystemWindowInsetTop(boolean z) {
        this.forceApplySystemWindowInsetTop = z;
    }

    @RequiresApi
    @RestrictTo
    public void setHyphenationFrequency(int i2) {
        this.collapsingTextHelper.B0(i2);
    }

    @RequiresApi
    @RestrictTo
    public void setLineSpacingAdd(float f2) {
        this.collapsingTextHelper.D0(f2);
    }

    @RequiresApi
    @RestrictTo
    public void setLineSpacingMultiplier(@FloatRange float f2) {
        this.collapsingTextHelper.E0(f2);
    }

    @RestrictTo
    public void setMaxLines(int i2) {
        this.collapsingTextHelper.F0(i2);
    }

    @RestrictTo
    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.collapsingTextHelper.H0(z);
    }

    void setScrimAlpha(int i2) {
        ViewGroup viewGroup;
        if (i2 != this.scrimAlpha) {
            if (this.contentScrim != null && (viewGroup = this.toolbar) != null) {
                ViewCompat.Z(viewGroup);
            }
            this.scrimAlpha = i2;
            ViewCompat.Z(this);
        }
    }

    public void setScrimAnimationDuration(@IntRange long j2) {
        this.scrimAnimationDuration = j2;
    }

    public void setScrimVisibleHeightTrigger(@IntRange int i2) {
        if (this.scrimVisibleHeightTrigger != i2) {
            this.scrimVisibleHeightTrigger = i2;
            v();
        }
    }

    public void setScrimsShown(boolean z) {
        p(z, ViewCompat.N(this) && !isInEditMode());
    }

    @RequiresApi
    @RestrictTo
    public void setStaticLayoutBuilderConfigurer(@Nullable StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer) {
        this.collapsingTextHelper.J0(staticLayoutBuilderConfigurer);
    }

    public void setStatusBarScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = this.statusBarScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.statusBarScrim = mutate;
            if (mutate != null) {
                if (mutate.isStateful()) {
                    this.statusBarScrim.setState(getDrawableState());
                }
                DrawableCompat.m(this.statusBarScrim, ViewCompat.v(this));
                this.statusBarScrim.setVisible(getVisibility() == 0, false);
                this.statusBarScrim.setCallback(this);
                this.statusBarScrim.setAlpha(this.scrimAlpha);
            }
            ViewCompat.Z(this);
        }
    }

    public void setStatusBarScrimColor(@ColorInt int i2) {
        setStatusBarScrim(new ColorDrawable(i2));
    }

    public void setStatusBarScrimResource(@DrawableRes int i2) {
        setStatusBarScrim(ContextCompat.e(getContext(), i2));
    }

    public void setTitle(@Nullable CharSequence charSequence) {
        this.collapsingTextHelper.K0(charSequence);
        r();
    }

    public void setTitleCollapseMode(int i2) {
        this.titleCollapseMode = i2;
        boolean l2 = l();
        this.collapsingTextHelper.z0(l2);
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            c((AppBarLayout) parent);
        }
        if (l2 && this.contentScrim == null) {
            setContentScrimColor(getDefaultContentScrimColorForTitleCollapseFadeMode());
        }
    }

    public void setTitleEllipsize(@NonNull TextUtils.TruncateAt truncateAt) {
        this.collapsingTextHelper.M0(truncateAt);
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.collapsingTitleEnabled) {
            this.collapsingTitleEnabled = z;
            r();
            u();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.collapsingTextHelper.G0(timeInterpolator);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        Drawable drawable = this.statusBarScrim;
        if (drawable != null && drawable.isVisible() != z) {
            this.statusBarScrim.setVisible(z, false);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 == null || drawable2.isVisible() == z) {
            return;
        }
        this.contentScrim.setVisible(z, false);
    }

    final void v() {
        if (this.contentScrim == null && this.statusBarScrim == null) {
            return;
        }
        setScrimsShown(getHeight() + this.currentOffset < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.contentScrim || drawable == this.statusBarScrim;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CollapsingToolbarLayout(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.Nullable android.util.AttributeSet r12, int r13) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCollapsedTitleTextColor(@NonNull ColorStateList colorStateList) {
        this.collapsingTextHelper.i0(colorStateList);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {

        /* renamed from: a, reason: collision with root package name */
        int f13873a;

        /* renamed from: b, reason: collision with root package name */
        float f13874b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f13873a = 0;
            this.f13874b = 0.5f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingToolbarLayout_Layout);
            this.f13873a = obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(obtainStyledAttributes.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            obtainStyledAttributes.recycle();
        }

        public void a(float f2) {
            this.f13874b = f2;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f13873a = 0;
            this.f13874b = 0.5f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f13873a = 0;
            this.f13874b = 0.5f;
        }
    }
}
