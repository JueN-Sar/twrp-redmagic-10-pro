package com.google.android.material.navigationrail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.resources.MaterialResources;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class NavigationRailView extends NavigationBarView {
    private static final int DEFAULT_HEADER_GRAVITY = 49;
    static final int DEFAULT_MENU_GRAVITY = 49;
    static final int MAX_ITEM_COUNT = 7;
    static final int NO_ITEM_MINIMUM_HEIGHT = -1;

    @Nullable
    private View headerView;

    @Nullable
    private Boolean paddingBottomSystemWindowInsets;

    @Nullable
    private Boolean paddingStartSystemWindowInsets;

    @Nullable
    private Boolean paddingTopSystemWindowInsets;
    private final int topMargin;

    public NavigationRailView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.navigationRailStyle);
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    private void k() {
        ViewUtils.g(this, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.navigationrail.NavigationRailView.1
            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
                Insets f2 = windowInsetsCompat.f(WindowInsetsCompat.Type.e());
                NavigationRailView navigationRailView = NavigationRailView.this;
                if (navigationRailView.p(navigationRailView.paddingTopSystemWindowInsets)) {
                    relativePadding.f14802b += f2.f2921b;
                }
                NavigationRailView navigationRailView2 = NavigationRailView.this;
                if (navigationRailView2.p(navigationRailView2.paddingBottomSystemWindowInsets)) {
                    relativePadding.f14804d += f2.f2923d;
                }
                NavigationRailView navigationRailView3 = NavigationRailView.this;
                if (navigationRailView3.p(navigationRailView3.paddingStartSystemWindowInsets)) {
                    relativePadding.f14801a += ViewUtils.p(view) ? f2.f2922c : f2.f2920a;
                }
                relativePadding.a(view);
                return windowInsetsCompat;
            }
        });
    }

    private boolean m() {
        View view = this.headerView;
        return (view == null || view.getVisibility() == 8) ? false : true;
    }

    private int n(int i2) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(i2) == 1073741824 || suggestedMinimumWidth <= 0) {
            return i2;
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i2), suggestedMinimumWidth + getPaddingLeft() + getPaddingRight()), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p(Boolean bool) {
        return bool != null ? bool.booleanValue() : ViewCompat.s(this);
    }

    @Nullable
    public View getHeaderView() {
        return this.headerView;
    }

    public int getItemMinimumHeight() {
        return ((NavigationRailMenuView) getMenuView()).getItemMinimumHeight();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 7;
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    public void i(int i2) {
        j(LayoutInflater.from(getContext()).inflate(i2, (ViewGroup) this, false));
    }

    public void j(View view) {
        o();
        this.headerView = view;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 49;
        layoutParams.topMargin = this.topMargin;
        addView(view, 0, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.navigation.NavigationBarView
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public NavigationRailMenuView c(Context context) {
        return new NavigationRailMenuView(context);
    }

    public void o() {
        View view = this.headerView;
        if (view != null) {
            removeView(view);
            this.headerView = null;
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        NavigationRailMenuView navigationRailMenuView = getNavigationRailMenuView();
        int i6 = 0;
        if (m()) {
            int bottom = this.headerView.getBottom() + this.topMargin;
            int top = navigationRailMenuView.getTop();
            if (top < bottom) {
                i6 = bottom - top;
            }
        } else if (navigationRailMenuView.n()) {
            i6 = this.topMargin;
        }
        if (i6 > 0) {
            navigationRailMenuView.layout(navigationRailMenuView.getLeft(), navigationRailMenuView.getTop() + i6, navigationRailMenuView.getRight(), navigationRailMenuView.getBottom() + i6);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int n2 = n(i2);
        super.onMeasure(n2, i3);
        if (m()) {
            measureChild(getNavigationRailMenuView(), n2, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.headerView.getMeasuredHeight()) - this.topMargin, Integer.MIN_VALUE));
        }
    }

    public void setItemMinimumHeight(@Px int i2) {
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(i2);
    }

    public void setMenuGravity(int i2) {
        getNavigationRailMenuView().setMenuGravity(i2);
    }

    public NavigationRailView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, R.style.Widget_MaterialComponents_NavigationRailView);
    }

    public NavigationRailView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.paddingTopSystemWindowInsets = null;
        this.paddingBottomSystemWindowInsets = null;
        this.paddingStartSystemWindowInsets = null;
        this.topMargin = getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_rail_margin);
        Context context2 = getContext();
        TintTypedArray j2 = ThemeEnforcement.j(context2, attributeSet, R.styleable.NavigationRailView, i2, i3, new int[0]);
        int n2 = j2.n(R.styleable.NavigationRailView_headerLayout, 0);
        if (n2 != 0) {
            i(n2);
        }
        setMenuGravity(j2.k(R.styleable.NavigationRailView_menuGravity, 49));
        if (j2.s(R.styleable.NavigationRailView_itemMinHeight)) {
            setItemMinimumHeight(j2.f(R.styleable.NavigationRailView_itemMinHeight, -1));
        }
        if (j2.s(R.styleable.NavigationRailView_paddingTopSystemWindowInsets)) {
            this.paddingTopSystemWindowInsets = Boolean.valueOf(j2.a(R.styleable.NavigationRailView_paddingTopSystemWindowInsets, false));
        }
        if (j2.s(R.styleable.NavigationRailView_paddingBottomSystemWindowInsets)) {
            this.paddingBottomSystemWindowInsets = Boolean.valueOf(j2.a(R.styleable.NavigationRailView_paddingBottomSystemWindowInsets, false));
        }
        if (j2.s(R.styleable.NavigationRailView_paddingStartSystemWindowInsets)) {
            this.paddingStartSystemWindowInsets = Boolean.valueOf(j2.a(R.styleable.NavigationRailView_paddingStartSystemWindowInsets, false));
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.m3_navigation_rail_item_padding_top_with_large_font);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.m3_navigation_rail_item_padding_bottom_with_large_font);
        float b2 = AnimationUtils.b(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.f(context2) - 1.0f);
        float c2 = AnimationUtils.c(getItemPaddingTop(), dimensionPixelOffset, b2);
        float c3 = AnimationUtils.c(getItemPaddingBottom(), dimensionPixelOffset2, b2);
        setItemPaddingTop(Math.round(c2));
        setItemPaddingBottom(Math.round(c3));
        j2.x();
        k();
    }
}
