package com.google.android.material.navigation;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;

@RestrictTo
/* loaded from: classes.dex */
public abstract class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_LABELED_TRANSFORM;
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int INVALID_ITEM_POSITION = -1;
    private ValueAnimator activeIndicatorAnimator;
    private int activeIndicatorDesiredHeight;
    private int activeIndicatorDesiredWidth;
    private boolean activeIndicatorEnabled;
    private int activeIndicatorLabelPadding;
    private int activeIndicatorMarginHorizontal;
    private float activeIndicatorProgress;
    private boolean activeIndicatorResizeable;
    private ActiveIndicatorTransform activeIndicatorTransform;

    @Nullable
    private final View activeIndicatorView;

    @StyleRes
    private int activeTextAppearance;

    @Nullable
    private BadgeDrawable badgeDrawable;
    private final ImageView icon;

    @Nullable
    private final FrameLayout iconContainer;

    @Nullable
    private ColorStateList iconTint;
    private boolean initialized;
    private boolean isShifting;

    @Nullable
    Drawable itemBackground;

    @Nullable
    private MenuItemImpl itemData;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private int itemPosition;
    private ColorStateList itemRippleColor;
    private final ViewGroup labelGroup;
    private int labelVisibilityMode;
    private final TextView largeLabel;

    @Nullable
    private Drawable originalIconDrawable;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;

    @Nullable
    private Drawable wrappedIconDrawable;

    private static class ActiveIndicatorTransform {
        private ActiveIndicatorTransform() {
        }

        protected float a(float f2, float f3) {
            return AnimationUtils.b(0.0f, 1.0f, f3 == 0.0f ? 0.8f : 0.0f, f3 == 0.0f ? 1.0f : 0.2f, f2);
        }

        protected float b(float f2, float f3) {
            return AnimationUtils.a(0.4f, 1.0f, f2);
        }

        protected float c(float f2, float f3) {
            return 1.0f;
        }

        public void d(float f2, float f3, View view) {
            view.setScaleX(b(f2, f3));
            view.setScaleY(c(f2, f3));
            view.setAlpha(a(f2, f3));
        }
    }

    private static class ActiveIndicatorUnlabeledTransform extends ActiveIndicatorTransform {
        private ActiveIndicatorUnlabeledTransform() {
            super();
        }

        @Override // com.google.android.material.navigation.NavigationBarItemView.ActiveIndicatorTransform
        protected float c(float f2, float f3) {
            return b(f2, f3);
        }
    }

    static {
        ACTIVE_INDICATOR_LABELED_TRANSFORM = new ActiveIndicatorTransform();
        ACTIVE_INDICATOR_UNLABELED_TRANSFORM = new ActiveIndicatorUnlabeledTransform();
    }

    public NavigationBarItemView(Context context) {
        super(context);
        this.initialized = false;
        this.itemPosition = -1;
        this.activeTextAppearance = 0;
        this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
        this.activeIndicatorProgress = 0.0f;
        this.activeIndicatorEnabled = false;
        this.activeIndicatorDesiredWidth = 0;
        this.activeIndicatorDesiredHeight = 0;
        this.activeIndicatorResizeable = false;
        this.activeIndicatorMarginHorizontal = 0;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.iconContainer = (FrameLayout) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_container);
        this.activeIndicatorView = findViewById(com.google.android.material.R.id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_view);
        this.icon = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(com.google.android.material.R.id.navigation_bar_item_labels_group);
        this.labelGroup = viewGroup;
        TextView textView = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_small_label_view);
        this.smallLabel = textView;
        TextView textView2 = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_large_label_view);
        this.largeLabel = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.itemPaddingTop = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.itemPaddingBottom = viewGroup.getPaddingBottom();
        this.activeIndicatorLabelPadding = getResources().getDimensionPixelSize(com.google.android.material.R.dimen.m3_navigation_item_active_indicator_label_padding);
        ViewCompat.s0(textView, 2);
        ViewCompat.s0(textView2, 2);
        setFocusable(true);
        f(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.material.navigation.NavigationBarItemView.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    if (NavigationBarItemView.this.icon.getVisibility() == 0) {
                        NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
                        navigationBarItemView.v(navigationBarItemView.icon);
                    }
                }
            });
        }
    }

    private void f(float f2, float f3) {
        this.shiftAmount = f2 - f3;
        this.scaleUpFactor = (f3 * 1.0f) / f2;
        this.scaleDownFactor = (f2 * 1.0f) / f3;
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.iconContainer;
        return frameLayout != null ? frameLayout : this.icon;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i2 = 0;
        for (int i3 = 0; i3 < indexOfChild; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i2++;
            }
        }
        return i2;
    }

    private int getSuggestedIconHeight() {
        return ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin + getIconOrContainer().getMeasuredHeight();
    }

    private int getSuggestedIconWidth() {
        BadgeDrawable badgeDrawable = this.badgeDrawable;
        int minimumWidth = badgeDrawable == null ? 0 : badgeDrawable.getMinimumWidth() - this.badgeDrawable.l();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.leftMargin) + this.icon.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.rightMargin);
    }

    private static Drawable h(ColorStateList colorStateList) {
        return new RippleDrawable(RippleUtils.a(colorStateList), null, null);
    }

    private FrameLayout i(View view) {
        ImageView imageView = this.icon;
        if (view == imageView && BadgeUtils.f13948a) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    private boolean j() {
        return this.badgeDrawable != null;
    }

    private boolean k() {
        return this.activeIndicatorResizeable && this.labelVisibilityMode == 2;
    }

    private void l(final float f2) {
        if (!this.activeIndicatorEnabled || !this.initialized || !ViewCompat.M(this)) {
            p(f2, f2);
            return;
        }
        ValueAnimator valueAnimator = this.activeIndicatorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.activeIndicatorAnimator = null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.activeIndicatorProgress, f2);
        this.activeIndicatorAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.navigation.NavigationBarItemView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                NavigationBarItemView.this.p(((Float) valueAnimator2.getAnimatedValue()).floatValue(), f2);
            }
        });
        this.activeIndicatorAnimator.setInterpolator(MotionUtils.g(getContext(), com.google.android.material.R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13815b));
        this.activeIndicatorAnimator.setDuration(MotionUtils.f(getContext(), com.google.android.material.R.attr.motionDurationLong2, getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1)));
        this.activeIndicatorAnimator.start();
    }

    private void m() {
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null) {
            setChecked(menuItemImpl.isChecked());
        }
    }

    private void n() {
        Drawable drawable = this.itemBackground;
        RippleDrawable rippleDrawable = null;
        boolean z = true;
        if (this.itemRippleColor != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.activeIndicatorEnabled && getActiveIndicatorDrawable() != null && this.iconContainer != null && activeIndicatorDrawable != null) {
                rippleDrawable = new RippleDrawable(RippleUtils.d(this.itemRippleColor), null, activeIndicatorDrawable);
                z = false;
            } else if (drawable == null) {
                drawable = h(this.itemRippleColor);
            }
        }
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null) {
            frameLayout.setPadding(0, 0, 0, 0);
            this.iconContainer.setForeground(rippleDrawable);
        }
        ViewCompat.m0(this, drawable);
        setDefaultFocusHighlightEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(float f2, float f3) {
        View view = this.activeIndicatorView;
        if (view != null) {
            this.activeIndicatorTransform.d(f2, f3, view);
        }
        this.activeIndicatorProgress = f2;
    }

    private static void q(TextView textView, int i2) {
        TextViewCompat.p(textView, i2);
        int i3 = MaterialResources.i(textView.getContext(), i2, 0);
        if (i3 != 0) {
            textView.setTextSize(0, i3);
        }
    }

    private static void r(View view, float f2, float f3, int i2) {
        view.setScaleX(f2);
        view.setScaleY(f3);
        view.setVisibility(i2);
    }

    private static void s(View view, int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.bottomMargin = i2;
        layoutParams.gravity = i3;
        view.setLayoutParams(layoutParams);
    }

    private void t(View view) {
        if (j() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            BadgeUtils.c(this.badgeDrawable, view, i(view));
        }
    }

    private void u(View view) {
        if (j()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                BadgeUtils.f(this.badgeDrawable, view);
            }
            this.badgeDrawable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(View view) {
        if (j()) {
            BadgeUtils.g(this.badgeDrawable, view, i(view));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i2) {
        if (this.activeIndicatorView == null || i2 <= 0) {
            return;
        }
        int min = Math.min(this.activeIndicatorDesiredWidth, i2 - (this.activeIndicatorMarginHorizontal * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.activeIndicatorView.getLayoutParams();
        layoutParams.height = k() ? min : this.activeIndicatorDesiredHeight;
        layoutParams.width = min;
        this.activeIndicatorView.setLayoutParams(layoutParams);
    }

    private void x() {
        if (k()) {
            this.activeIndicatorTransform = ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
        } else {
            this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
        }
    }

    private static void y(View view, int i2) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i2);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void c(MenuItemImpl menuItemImpl, int i2) {
        this.itemData = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitle());
        setId(menuItemImpl.getItemId());
        if (!TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(menuItemImpl.getContentDescription());
        }
        TooltipCompat.a(this, !TextUtils.isEmpty(menuItemImpl.getTooltipText()) ? menuItemImpl.getTooltipText() : menuItemImpl.getTitle());
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        this.initialized = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null && this.activeIndicatorEnabled) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    void g() {
        o();
        this.itemData = null;
        this.activeIndicatorProgress = 0.0f;
        this.initialized = false;
    }

    @Nullable
    public Drawable getActiveIndicatorDrawable() {
        View view = this.activeIndicatorView;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    @Nullable
    public BadgeDrawable getBadge() {
        return this.badgeDrawable;
    }

    @DrawableRes
    protected int getItemBackgroundResId() {
        return com.google.android.material.R.drawable.mtrl_navigation_bar_item_background;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    @Nullable
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    @DimenRes
    protected int getItemDefaultMarginResId() {
        return com.google.android.material.R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    @LayoutRes
    protected abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.itemPosition;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        return getSuggestedIconHeight() + (this.labelGroup.getVisibility() == 0 ? this.activeIndicatorLabelPadding : 0) + layoutParams.topMargin + this.labelGroup.getMeasuredHeight() + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), layoutParams.leftMargin + this.labelGroup.getMeasuredWidth() + layoutParams.rightMargin);
    }

    void o() {
        u(this.icon);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.badgeDrawable;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            CharSequence title = this.itemData.getTitle();
            if (!TextUtils.isEmpty(this.itemData.getContentDescription())) {
                title = this.itemData.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(((Object) title) + ", " + ((Object) this.badgeDrawable.i()));
        }
        AccessibilityNodeInfoCompat O0 = AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo);
        O0.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected()) {
            O0.i0(false);
            O0.a0(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3486i);
        }
        O0.C0(getResources().getString(com.google.android.material.R.string.item_view_role_description));
    }

    @Override // android.view.View
    protected void onSizeChanged(final int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        post(new Runnable() { // from class: com.google.android.material.navigation.NavigationBarItemView.2
            @Override // java.lang.Runnable
            public void run() {
                NavigationBarItemView.this.w(i2);
            }
        });
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setActiveIndicatorDrawable(@Nullable Drawable drawable) {
        View view = this.activeIndicatorView;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
        n();
    }

    public void setActiveIndicatorEnabled(boolean z) {
        this.activeIndicatorEnabled = z;
        n();
        View view = this.activeIndicatorView;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i2) {
        this.activeIndicatorDesiredHeight = i2;
        w(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int i2) {
        if (this.activeIndicatorLabelPadding != i2) {
            this.activeIndicatorLabelPadding = i2;
            m();
        }
    }

    public void setActiveIndicatorMarginHorizontal(@Px int i2) {
        this.activeIndicatorMarginHorizontal = i2;
        w(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z) {
        this.activeIndicatorResizeable = z;
    }

    public void setActiveIndicatorWidth(int i2) {
        this.activeIndicatorDesiredWidth = i2;
        w(getWidth());
    }

    void setBadge(@NonNull BadgeDrawable badgeDrawable) {
        if (this.badgeDrawable == badgeDrawable) {
            return;
        }
        if (j() && this.icon != null) {
            Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
            u(this.icon);
        }
        this.badgeDrawable = badgeDrawable;
        ImageView imageView = this.icon;
        if (imageView != null) {
            t(imageView);
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        this.largeLabel.setPivotX(r0.getWidth() / 2);
        this.largeLabel.setPivotY(r0.getBaseline());
        this.smallLabel.setPivotX(r0.getWidth() / 2);
        this.smallLabel.setPivotY(r0.getBaseline());
        l(z ? 1.0f : 0.0f);
        int i2 = this.labelVisibilityMode;
        if (i2 != -1) {
            if (i2 == 0) {
                if (z) {
                    s(getIconOrContainer(), this.itemPaddingTop, 49);
                    y(this.labelGroup, this.itemPaddingBottom);
                    this.largeLabel.setVisibility(0);
                } else {
                    s(getIconOrContainer(), this.itemPaddingTop, 17);
                    y(this.labelGroup, 0);
                    this.largeLabel.setVisibility(4);
                }
                this.smallLabel.setVisibility(4);
            } else if (i2 == 1) {
                y(this.labelGroup, this.itemPaddingBottom);
                if (z) {
                    s(getIconOrContainer(), (int) (this.itemPaddingTop + this.shiftAmount), 49);
                    r(this.largeLabel, 1.0f, 1.0f, 0);
                    TextView textView = this.smallLabel;
                    float f2 = this.scaleUpFactor;
                    r(textView, f2, f2, 4);
                } else {
                    s(getIconOrContainer(), this.itemPaddingTop, 49);
                    TextView textView2 = this.largeLabel;
                    float f3 = this.scaleDownFactor;
                    r(textView2, f3, f3, 4);
                    r(this.smallLabel, 1.0f, 1.0f, 0);
                }
            } else if (i2 == 2) {
                s(getIconOrContainer(), this.itemPaddingTop, 17);
                this.largeLabel.setVisibility(8);
                this.smallLabel.setVisibility(8);
            }
        } else if (this.isShifting) {
            if (z) {
                s(getIconOrContainer(), this.itemPaddingTop, 49);
                y(this.labelGroup, this.itemPaddingBottom);
                this.largeLabel.setVisibility(0);
            } else {
                s(getIconOrContainer(), this.itemPaddingTop, 17);
                y(this.labelGroup, 0);
                this.largeLabel.setVisibility(4);
            }
            this.smallLabel.setVisibility(4);
        } else {
            y(this.labelGroup, this.itemPaddingBottom);
            if (z) {
                s(getIconOrContainer(), (int) (this.itemPaddingTop + this.shiftAmount), 49);
                r(this.largeLabel, 1.0f, 1.0f, 0);
                TextView textView3 = this.smallLabel;
                float f4 = this.scaleUpFactor;
                r(textView3, f4, f4, 4);
            } else {
                s(getIconOrContainer(), this.itemPaddingTop, 49);
                TextView textView4 = this.largeLabel;
                float f5 = this.scaleDownFactor;
                r(textView4, f5, f5, 4);
                r(this.smallLabel, 1.0f, 1.0f, 0);
            }
        }
        refreshDrawableState();
        setSelected(z);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.smallLabel.setEnabled(z);
        this.largeLabel.setEnabled(z);
        this.icon.setEnabled(z);
        if (z) {
            ViewCompat.z0(this, PointerIconCompat.b(getContext(), 1002));
        } else {
            ViewCompat.z0(this, null);
        }
    }

    public void setIcon(@Nullable Drawable drawable) {
        if (drawable == this.originalIconDrawable) {
            return;
        }
        this.originalIconDrawable = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = DrawableCompat.r(drawable).mutate();
            this.wrappedIconDrawable = drawable;
            ColorStateList colorStateList = this.iconTint;
            if (colorStateList != null) {
                DrawableCompat.o(drawable, colorStateList);
            }
        }
        this.icon.setImageDrawable(drawable);
    }

    public void setIconSize(int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.icon.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.icon.setLayoutParams(layoutParams);
    }

    public void setIconTintList(@Nullable ColorStateList colorStateList) {
        Drawable drawable;
        this.iconTint = colorStateList;
        if (this.itemData == null || (drawable = this.wrappedIconDrawable) == null) {
            return;
        }
        DrawableCompat.o(drawable, colorStateList);
        this.wrappedIconDrawable.invalidateSelf();
    }

    public void setItemBackground(int i2) {
        setItemBackground(i2 == 0 ? null : ContextCompat.e(getContext(), i2));
    }

    public void setItemPaddingBottom(int i2) {
        if (this.itemPaddingBottom != i2) {
            this.itemPaddingBottom = i2;
            m();
        }
    }

    public void setItemPaddingTop(int i2) {
        if (this.itemPaddingTop != i2) {
            this.itemPaddingTop = i2;
            m();
        }
    }

    public void setItemPosition(int i2) {
        this.itemPosition = i2;
    }

    public void setItemRippleColor(@Nullable ColorStateList colorStateList) {
        this.itemRippleColor = colorStateList;
        n();
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.labelVisibilityMode != i2) {
            this.labelVisibilityMode = i2;
            x();
            w(getWidth());
            m();
        }
    }

    public void setShifting(boolean z) {
        if (this.isShifting != z) {
            this.isShifting = z;
            m();
        }
    }

    public void setTextAppearanceActive(@StyleRes int i2) {
        this.activeTextAppearance = i2;
        q(this.largeLabel, i2);
        f(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z) {
        setTextAppearanceActive(this.activeTextAppearance);
        TextView textView = this.largeLabel;
        textView.setTypeface(textView.getTypeface(), z ? 1 : 0);
    }

    public void setTextAppearanceInactive(@StyleRes int i2) {
        q(this.smallLabel, i2);
        f(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextColor(@Nullable ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.smallLabel.setTextColor(colorStateList);
            this.largeLabel.setTextColor(colorStateList);
        }
    }

    public void setTitle(@Nullable CharSequence charSequence) {
        this.smallLabel.setText(charSequence);
        this.largeLabel.setText(charSequence);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl == null || TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(charSequence);
        }
        MenuItemImpl menuItemImpl2 = this.itemData;
        if (menuItemImpl2 != null && !TextUtils.isEmpty(menuItemImpl2.getTooltipText())) {
            charSequence = this.itemData.getTooltipText();
        }
        TooltipCompat.a(this, charSequence);
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.itemBackground = drawable;
        n();
    }
}
