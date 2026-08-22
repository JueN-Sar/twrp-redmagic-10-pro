package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    private static final int EXTEND = 3;
    private static final int EXTEND_STRATEGY_AUTO = 0;
    private static final int EXTEND_STRATEGY_MATCH_PARENT = 2;
    private static final int EXTEND_STRATEGY_WRAP_CONTENT = 1;
    static final Property<View, Float> HEIGHT;
    private static final int HIDE = 1;
    static final Property<View, Float> PADDING_END;
    static final Property<View, Float> PADDING_START;
    private static final int SHOW = 0;
    private static final int SHRINK = 2;
    static final Property<View, Float> WIDTH;
    private int animState;
    private boolean animateShowBeforeLayout;

    @NonNull
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    private final AnimatorTracker changeVisibilityTracker;
    private final int collapsedSize;

    @NonNull
    private final MotionStrategy extendStrategy;
    private final int extendStrategyType;
    private int extendedPaddingEnd;
    private int extendedPaddingStart;
    private final MotionStrategy hideStrategy;
    private boolean isExtended;
    private boolean isTransforming;
    private int originalHeight;

    @NonNull
    protected ColorStateList originalTextCsl;
    private int originalWidth;
    private final MotionStrategy showStrategy;

    @NonNull
    private final MotionStrategy shrinkStrategy;

    class ChangeSizeStrategy extends BaseMotionStrategy {

        /* renamed from: g, reason: collision with root package name */
        private final Size f14617g;

        /* renamed from: h, reason: collision with root package name */
        private final boolean f14618h;

        ChangeSizeStrategy(AnimatorTracker animatorTracker, Size size, boolean z) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
            this.f14617g = size;
            this.f14618h = z;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void a() {
            super.a();
            ExtendedFloatingActionButton.this.isTransforming = false;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.f14617g.c().width;
            layoutParams.height = this.f14617g.c().height;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void b() {
            ExtendedFloatingActionButton.this.isExtended = this.f14618h;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            if (!this.f14618h) {
                ExtendedFloatingActionButton.this.originalWidth = layoutParams.width;
                ExtendedFloatingActionButton.this.originalHeight = layoutParams.height;
            }
            layoutParams.width = this.f14617g.c().width;
            layoutParams.height = this.f14617g.c().height;
            ViewCompat.y0(ExtendedFloatingActionButton.this, this.f14617g.d(), ExtendedFloatingActionButton.this.getPaddingTop(), this.f14617g.a(), ExtendedFloatingActionButton.this.getPaddingBottom());
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean d() {
            return this.f14618h == ExtendedFloatingActionButton.this.isExtended || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int f() {
            return this.f14618h ? R.animator.mtrl_extended_fab_change_size_expand_motion_spec : R.animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public AnimatorSet h() {
            MotionSpec m2 = m();
            if (m2.j("width")) {
                PropertyValuesHolder[] g2 = m2.g("width");
                g2[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.f14617g.b());
                m2.l("width", g2);
            }
            if (m2.j("height")) {
                PropertyValuesHolder[] g3 = m2.g("height");
                g3[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.f14617g.getHeight());
                m2.l("height", g3);
            }
            if (m2.j("paddingStart")) {
                PropertyValuesHolder[] g4 = m2.g("paddingStart");
                g4[0].setFloatValues(ViewCompat.z(ExtendedFloatingActionButton.this), this.f14617g.d());
                m2.l("paddingStart", g4);
            }
            if (m2.j("paddingEnd")) {
                PropertyValuesHolder[] g5 = m2.g("paddingEnd");
                g5[0].setFloatValues(ViewCompat.y(ExtendedFloatingActionButton.this), this.f14617g.a());
                m2.l("paddingEnd", g5);
            }
            if (m2.j("labelOpacity")) {
                PropertyValuesHolder[] g6 = m2.g("labelOpacity");
                boolean z = this.f14618h;
                g6[0].setFloatValues(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
                m2.l("labelOpacity", g6);
            }
            return super.l(m2);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void j(OnChangedCallback onChangedCallback) {
            if (onChangedCallback == null) {
                return;
            }
            if (this.f14618h) {
                onChangedCallback.a(ExtendedFloatingActionButton.this);
            } else {
                onChangedCallback.d(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.isExtended = this.f14618h;
            ExtendedFloatingActionButton.this.isTransforming = true;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    class HideStrategy extends BaseMotionStrategy {

        /* renamed from: g, reason: collision with root package name */
        private boolean f14625g;

        public HideStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void a() {
            super.a();
            ExtendedFloatingActionButton.this.animState = 0;
            if (this.f14625g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void b() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean d() {
            return ExtendedFloatingActionButton.this.x();
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void e() {
            super.e();
            this.f14625g = true;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int f() {
            return R.animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void j(OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                onChangedCallback.b(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.f14625g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 1;
        }
    }

    public static abstract class OnChangedCallback {
        public void a(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void b(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void c(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void d(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }
    }

    class ShowStrategy extends BaseMotionStrategy {
        public ShowStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void a() {
            super.a();
            ExtendedFloatingActionButton.this.animState = 0;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void b() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean d() {
            return ExtendedFloatingActionButton.this.y();
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int f() {
            return R.animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void j(OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                onChangedCallback.c(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 2;
        }
    }

    interface Size {
        int a();

        int b();

        ViewGroup.LayoutParams c();

        int d();

        int getHeight();
    }

    static {
        Class<Float> cls = Float.class;
        WIDTH = new Property<View, Float>(cls, "width") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.6
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(View view) {
                return Float.valueOf(view.getLayoutParams().width);
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, Float f2) {
                view.getLayoutParams().width = f2.intValue();
                view.requestLayout();
            }
        };
        HEIGHT = new Property<View, Float>(cls, "height") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.7
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(View view) {
                return Float.valueOf(view.getLayoutParams().height);
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, Float f2) {
                view.getLayoutParams().height = f2.intValue();
                view.requestLayout();
            }
        };
        PADDING_START = new Property<View, Float>(cls, "paddingStart") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.8
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(View view) {
                return Float.valueOf(ViewCompat.z(view));
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, Float f2) {
                ViewCompat.y0(view, f2.intValue(), view.getPaddingTop(), ViewCompat.y(view), view.getPaddingBottom());
            }
        };
        PADDING_END = new Property<View, Float>(cls, "paddingEnd") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.9
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(View view) {
                return Float.valueOf(ViewCompat.y(view));
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, Float f2) {
                ViewCompat.y0(view, ViewCompat.z(view), view.getPaddingTop(), f2.intValue(), view.getPaddingBottom());
            }
        };
    }

    public ExtendedFloatingActionButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.extendedFloatingActionButtonStyle);
    }

    private void A() {
        this.originalTextCsl = getTextColors();
    }

    private boolean B() {
        return (ViewCompat.N(this) || (!y() && this.animateShowBeforeLayout)) && !isInEditMode();
    }

    private Size w(int i2) {
        final Size size = new Size() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.2
            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int a() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int b() {
                return (ExtendedFloatingActionButton.this.getMeasuredWidth() - (ExtendedFloatingActionButton.this.getCollapsedPadding() * 2)) + ExtendedFloatingActionButton.this.extendedPaddingStart + ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public ViewGroup.LayoutParams c() {
                return new ViewGroup.LayoutParams(-2, -2);
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int d() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getHeight() {
                return ExtendedFloatingActionButton.this.getMeasuredHeight();
            }
        };
        final Size size2 = new Size() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.3
            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int a() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int b() {
                ViewGroup.MarginLayoutParams marginLayoutParams;
                if (!(ExtendedFloatingActionButton.this.getParent() instanceof View)) {
                    return size.b();
                }
                View view = (View) ExtendedFloatingActionButton.this.getParent();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null || layoutParams.width != -2) {
                    return (view.getWidth() - ((!(ExtendedFloatingActionButton.this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) ExtendedFloatingActionButton.this.getLayoutParams()) == null) ? 0 : marginLayoutParams.leftMargin + marginLayoutParams.rightMargin)) - (view.getPaddingLeft() + view.getPaddingRight());
                }
                return size.b();
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public ViewGroup.LayoutParams c() {
                return new ViewGroup.LayoutParams(-1, ExtendedFloatingActionButton.this.originalHeight == 0 ? -2 : ExtendedFloatingActionButton.this.originalHeight);
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int d() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getHeight() {
                ViewGroup.MarginLayoutParams marginLayoutParams;
                if (ExtendedFloatingActionButton.this.originalHeight != -1) {
                    return (ExtendedFloatingActionButton.this.originalHeight == 0 || ExtendedFloatingActionButton.this.originalHeight == -2) ? size.getHeight() : ExtendedFloatingActionButton.this.originalHeight;
                }
                if (!(ExtendedFloatingActionButton.this.getParent() instanceof View)) {
                    return size.getHeight();
                }
                View view = (View) ExtendedFloatingActionButton.this.getParent();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null || layoutParams.height != -2) {
                    return (view.getHeight() - ((!(ExtendedFloatingActionButton.this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) ExtendedFloatingActionButton.this.getLayoutParams()) == null) ? 0 : marginLayoutParams.topMargin + marginLayoutParams.bottomMargin)) - (view.getPaddingTop() + view.getPaddingBottom());
                }
                return size.getHeight();
            }
        };
        return i2 != 1 ? i2 != 2 ? new Size() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.4
            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int a() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int b() {
                return ExtendedFloatingActionButton.this.originalWidth == -1 ? size2.b() : (ExtendedFloatingActionButton.this.originalWidth == 0 || ExtendedFloatingActionButton.this.originalWidth == -2) ? size.b() : ExtendedFloatingActionButton.this.originalWidth;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public ViewGroup.LayoutParams c() {
                return new ViewGroup.LayoutParams(ExtendedFloatingActionButton.this.originalWidth == 0 ? -2 : ExtendedFloatingActionButton.this.originalWidth, ExtendedFloatingActionButton.this.originalHeight != 0 ? ExtendedFloatingActionButton.this.originalHeight : -2);
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int d() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
            public int getHeight() {
                return ExtendedFloatingActionButton.this.originalHeight == -1 ? size2.getHeight() : (ExtendedFloatingActionButton.this.originalHeight == 0 || ExtendedFloatingActionButton.this.originalHeight == -2) ? size.getHeight() : ExtendedFloatingActionButton.this.originalHeight;
            }
        } : size2 : size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x() {
        return getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        return getVisibility() != 0 ? this.animState == 2 : this.animState != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(int i2, final OnChangedCallback onChangedCallback) {
        final MotionStrategy motionStrategy;
        if (i2 == 0) {
            motionStrategy = this.showStrategy;
        } else if (i2 == 1) {
            motionStrategy = this.hideStrategy;
        } else if (i2 == 2) {
            motionStrategy = this.shrinkStrategy;
        } else {
            if (i2 != 3) {
                throw new IllegalStateException("Unknown strategy type: " + i2);
            }
            motionStrategy = this.extendStrategy;
        }
        if (motionStrategy.d()) {
            return;
        }
        if (!B()) {
            motionStrategy.b();
            motionStrategy.j(onChangedCallback);
            return;
        }
        if (i2 == 2) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                this.originalWidth = layoutParams.width;
                this.originalHeight = layoutParams.height;
            } else {
                this.originalWidth = getWidth();
                this.originalHeight = getHeight();
            }
        }
        measure(0, 0);
        AnimatorSet h2 = motionStrategy.h();
        h2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.5

            /* renamed from: c, reason: collision with root package name */
            private boolean f14613c;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                this.f14613c = true;
                motionStrategy.e();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                motionStrategy.a();
                if (this.f14613c) {
                    return;
                }
                motionStrategy.j(onChangedCallback);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                motionStrategy.onAnimationStart(animator);
                this.f14613c = false;
            }
        });
        Iterator it = motionStrategy.i().iterator();
        while (it.hasNext()) {
            h2.addListener((Animator.AnimatorListener) it.next());
        }
        h2.start();
    }

    protected void C(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    @NonNull
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    @VisibleForTesting
    int getCollapsedSize() {
        int i2 = this.collapsedSize;
        return i2 < 0 ? (Math.min(ViewCompat.z(this), ViewCompat.y(this)) * 2) + getIconSize() : i2;
    }

    @Nullable
    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.c();
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.c();
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.c();
    }

    @Nullable
    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.c();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            this.shrinkStrategy.b();
        }
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.animateShowBeforeLayout = z;
    }

    public void setExtendMotionSpec(@Nullable MotionSpec motionSpec) {
        this.extendStrategy.g(motionSpec);
    }

    public void setExtendMotionSpecResource(@AnimatorRes int i2) {
        setExtendMotionSpec(MotionSpec.d(getContext(), i2));
    }

    public void setExtended(boolean z) {
        if (this.isExtended == z) {
            return;
        }
        MotionStrategy motionStrategy = z ? this.extendStrategy : this.shrinkStrategy;
        if (motionStrategy.d()) {
            return;
        }
        motionStrategy.b();
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.hideStrategy.g(motionSpec);
    }

    public void setHideMotionSpecResource(@AnimatorRes int i2) {
        setHideMotionSpec(MotionSpec.d(getContext(), i2));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        if (!this.isExtended || this.isTransforming) {
            return;
        }
        this.extendedPaddingStart = ViewCompat.z(this);
        this.extendedPaddingEnd = ViewCompat.y(this);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
        super.setPaddingRelative(i2, i3, i4, i5);
        if (!this.isExtended || this.isTransforming) {
            return;
        }
        this.extendedPaddingStart = i2;
        this.extendedPaddingEnd = i4;
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.showStrategy.g(motionSpec);
    }

    public void setShowMotionSpecResource(@AnimatorRes int i2) {
        setShowMotionSpec(MotionSpec.d(getContext(), i2));
    }

    public void setShrinkMotionSpec(@Nullable MotionSpec motionSpec) {
        this.shrinkStrategy.g(motionSpec);
    }

    public void setShrinkMotionSpecResource(@AnimatorRes int i2) {
        setShrinkMotionSpec(MotionSpec.d(getContext(), i2));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        super.setTextColor(i2);
        A();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ExtendedFloatingActionButton(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            int r9 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.DEF_STYLE_RES
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            r10 = 0
            r0.animState = r10
            com.google.android.material.floatingactionbutton.AnimatorTracker r1 = new com.google.android.material.floatingactionbutton.AnimatorTracker
            r1.<init>()
            r0.changeVisibilityTracker = r1
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ShowStrategy r11 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ShowStrategy
            r11.<init>(r1)
            r0.showStrategy = r11
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$HideStrategy r12 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$HideStrategy
            r12.<init>(r1)
            r0.hideStrategy = r12
            r13 = 1
            r0.isExtended = r13
            r0.isTransforming = r10
            r0.animateShowBeforeLayout = r10
            android.content.Context r14 = r16.getContext()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior r1 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior
            r1.<init>(r14, r7)
            r0.behavior = r1
            int[] r3 = com.google.android.material.R.styleable.ExtendedFloatingActionButton
            int[] r6 = new int[r10]
            r1 = r14
            r2 = r18
            r4 = r19
            r5 = r9
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.i(r1, r2, r3, r4, r5, r6)
            int r2 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_showMotionSpec
            com.google.android.material.animation.MotionSpec r2 = com.google.android.material.animation.MotionSpec.c(r14, r1, r2)
            int r3 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_hideMotionSpec
            com.google.android.material.animation.MotionSpec r3 = com.google.android.material.animation.MotionSpec.c(r14, r1, r3)
            int r4 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_extendMotionSpec
            com.google.android.material.animation.MotionSpec r4 = com.google.android.material.animation.MotionSpec.c(r14, r1, r4)
            int r5 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec
            com.google.android.material.animation.MotionSpec r5 = com.google.android.material.animation.MotionSpec.c(r14, r1, r5)
            int r6 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_collapsedSize
            r15 = -1
            int r6 = r1.getDimensionPixelSize(r6, r15)
            r0.collapsedSize = r6
            int r6 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_extendStrategy
            int r6 = r1.getInt(r6, r13)
            r0.extendStrategyType = r6
            int r15 = androidx.core.view.ViewCompat.z(r16)
            r0.extendedPaddingStart = r15
            int r15 = androidx.core.view.ViewCompat.y(r16)
            r0.extendedPaddingEnd = r15
            com.google.android.material.floatingactionbutton.AnimatorTracker r15 = new com.google.android.material.floatingactionbutton.AnimatorTracker
            r15.<init>()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy r10 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r6 = r0.w(r6)
            r10.<init>(r15, r6, r13)
            r0.extendStrategy = r10
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy r6 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1 r13 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1
            r13.<init>()
            r7 = 0
            r6.<init>(r15, r13, r7)
            r0.shrinkStrategy = r6
            r11.g(r2)
            r12.g(r3)
            r10.g(r4)
            r6.g(r5)
            r1.recycle()
            com.google.android.material.shape.CornerSize r1 = com.google.android.material.shape.ShapeAppearanceModel.f15133m
            r2 = r18
            com.google.android.material.shape.ShapeAppearanceModel$Builder r1 = com.google.android.material.shape.ShapeAppearanceModel.g(r14, r2, r8, r9, r1)
            com.google.android.material.shape.ShapeAppearanceModel r1 = r1.m()
            r0.setShapeAppearanceModel(r1)
            r16.A()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {

        /* renamed from: c, reason: collision with root package name */
        private Rect f14620c;

        /* renamed from: h, reason: collision with root package name */
        private OnChangedCallback f14621h;

        /* renamed from: i, reason: collision with root package name */
        private OnChangedCallback f14622i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f14623j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f14624k;

        public ExtendedFloatingActionButtonBehavior() {
            this.f14623j = false;
            this.f14624k = true;
        }

        private static boolean L(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean O(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f14623j || this.f14624k) && ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        private boolean Q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!O(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f14620c == null) {
                this.f14620c = new Rect();
            }
            Rect rect = this.f14620c;
            DescendantOffsetUtils.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                P(extendedFloatingActionButton);
                return true;
            }
            J(extendedFloatingActionButton);
            return true;
        }

        private boolean R(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!O(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                P(extendedFloatingActionButton);
                return true;
            }
            J(extendedFloatingActionButton);
            return true;
        }

        protected void J(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.f14624k;
            extendedFloatingActionButton.z(z ? 3 : 0, z ? this.f14622i : this.f14621h);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: K, reason: merged with bridge method [inline-methods] */
        public boolean g(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.g(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean m(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                Q(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!L(view)) {
                return false;
            }
            R(view, extendedFloatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean q(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i2) {
            List v = coordinatorLayout.v(extendedFloatingActionButton);
            int size = v.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = (View) v.get(i3);
                if (!(view instanceof AppBarLayout)) {
                    if (L(view) && R(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (Q(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.M(extendedFloatingActionButton, i2);
            return true;
        }

        protected void P(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.f14624k;
            extendedFloatingActionButton.z(z ? 2 : 1, z ? this.f14622i : this.f14621h);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void l(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.f2587h == 0) {
                layoutParams.f2587h = 80;
            }
        }

        @VisibleForTesting
        void setInternalAutoHideCallback(@Nullable OnChangedCallback onChangedCallback) {
            this.f14621h = onChangedCallback;
        }

        @VisibleForTesting
        void setInternalAutoShrinkCallback(@Nullable OnChangedCallback onChangedCallback) {
            this.f14622i = onChangedCallback;
        }

        public ExtendedFloatingActionButtonBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.f14623j = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f14624k = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView
    public void setTextColor(@NonNull ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        A();
    }
}
