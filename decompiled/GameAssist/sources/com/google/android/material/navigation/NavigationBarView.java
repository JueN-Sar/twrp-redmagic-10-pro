package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;
    private static final int MENU_PRESENTER_ID = 1;

    @NonNull
    private final NavigationBarMenu menu;
    private MenuInflater menuInflater;

    @NonNull
    private final NavigationBarMenuView menuView;

    @NonNull
    private final NavigationBarPresenter presenter;
    private OnItemReselectedListener reselectedListener;
    private OnItemSelectedListener selectedListener;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface LabelVisibility {
    }

    public interface OnItemReselectedListener {
        void a(MenuItem menuItem);
    }

    public interface OnItemSelectedListener {
        boolean a(MenuItem menuItem);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.navigation.NavigationBarView.SavedState.1
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
        Bundle f14854i;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void b(Parcel parcel, ClassLoader classLoader) {
            this.f14854i = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeBundle(this.f14854i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            b(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public NavigationBarView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(MaterialThemeOverlay.c(context, attributeSet, i2, i3), attributeSet, i2);
        NavigationBarPresenter navigationBarPresenter = new NavigationBarPresenter();
        this.presenter = navigationBarPresenter;
        Context context2 = getContext();
        TintTypedArray j2 = ThemeEnforcement.j(context2, attributeSet, R.styleable.NavigationBarView, i2, i3, R.styleable.NavigationBarView_itemTextAppearanceInactive, R.styleable.NavigationBarView_itemTextAppearanceActive);
        NavigationBarMenu navigationBarMenu = new NavigationBarMenu(context2, getClass(), getMaxItemCount());
        this.menu = navigationBarMenu;
        NavigationBarMenuView c2 = c(context2);
        this.menuView = c2;
        navigationBarPresenter.h(c2);
        navigationBarPresenter.g(1);
        c2.setPresenter(navigationBarPresenter);
        navigationBarMenu.b(navigationBarPresenter);
        navigationBarPresenter.f(getContext(), navigationBarMenu);
        if (j2.s(R.styleable.NavigationBarView_itemIconTint)) {
            c2.setIconTintList(j2.c(R.styleable.NavigationBarView_itemIconTint));
        } else {
            c2.setIconTintList(c2.e(android.R.attr.textColorSecondary));
        }
        setItemIconSize(j2.f(R.styleable.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_bar_item_default_icon_size)));
        if (j2.s(R.styleable.NavigationBarView_itemTextAppearanceInactive)) {
            setItemTextAppearanceInactive(j2.n(R.styleable.NavigationBarView_itemTextAppearanceInactive, 0));
        }
        if (j2.s(R.styleable.NavigationBarView_itemTextAppearanceActive)) {
            setItemTextAppearanceActive(j2.n(R.styleable.NavigationBarView_itemTextAppearanceActive, 0));
        }
        setItemTextAppearanceActiveBoldEnabled(j2.a(R.styleable.NavigationBarView_itemTextAppearanceActiveBoldEnabled, true));
        if (j2.s(R.styleable.NavigationBarView_itemTextColor)) {
            setItemTextColor(j2.c(R.styleable.NavigationBarView_itemTextColor));
        }
        Drawable background = getBackground();
        ColorStateList g2 = DrawableUtils.g(background);
        if (background == null || g2 != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.e(context2, attributeSet, i2, i3).m());
            if (g2 != null) {
                materialShapeDrawable.a0(g2);
            }
            materialShapeDrawable.P(context2);
            ViewCompat.m0(this, materialShapeDrawable);
        }
        if (j2.s(R.styleable.NavigationBarView_itemPaddingTop)) {
            setItemPaddingTop(j2.f(R.styleable.NavigationBarView_itemPaddingTop, 0));
        }
        if (j2.s(R.styleable.NavigationBarView_itemPaddingBottom)) {
            setItemPaddingBottom(j2.f(R.styleable.NavigationBarView_itemPaddingBottom, 0));
        }
        if (j2.s(R.styleable.NavigationBarView_activeIndicatorLabelPadding)) {
            setActiveIndicatorLabelPadding(j2.f(R.styleable.NavigationBarView_activeIndicatorLabelPadding, 0));
        }
        if (j2.s(R.styleable.NavigationBarView_elevation)) {
            setElevation(j2.f(R.styleable.NavigationBarView_elevation, 0));
        }
        DrawableCompat.o(getBackground().mutate(), MaterialResources.b(context2, j2, R.styleable.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(j2.l(R.styleable.NavigationBarView_labelVisibilityMode, -1));
        int n2 = j2.n(R.styleable.NavigationBarView_itemBackground, 0);
        if (n2 != 0) {
            c2.setItemBackgroundRes(n2);
        } else {
            setItemRippleColor(MaterialResources.b(context2, j2, R.styleable.NavigationBarView_itemRippleColor));
        }
        int n3 = j2.n(R.styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (n3 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(n3, R.styleable.NavigationBarActiveIndicator);
            setItemActiveIndicatorWidth(obtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_android_width, 0));
            setItemActiveIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_android_height, 0));
            setItemActiveIndicatorMarginHorizontal(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_marginHorizontal, 0));
            setItemActiveIndicatorColor(MaterialResources.a(context2, obtainStyledAttributes, R.styleable.NavigationBarActiveIndicator_android_color));
            setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel.b(context2, obtainStyledAttributes.getResourceId(R.styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0).m());
            obtainStyledAttributes.recycle();
        }
        if (j2.s(R.styleable.NavigationBarView_menu)) {
            d(j2.n(R.styleable.NavigationBarView_menu, 0));
        }
        j2.x();
        addView(c2);
        navigationBarMenu.W(new MenuBuilder.Callback() { // from class: com.google.android.material.navigation.NavigationBarView.1
            @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
            public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
                if (NavigationBarView.this.reselectedListener == null || menuItem.getItemId() != NavigationBarView.this.getSelectedItemId()) {
                    return (NavigationBarView.this.selectedListener == null || NavigationBarView.this.selectedListener.a(menuItem)) ? false : true;
                }
                NavigationBarView.this.reselectedListener.a(menuItem);
                return true;
            }

            @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
            public void b(MenuBuilder menuBuilder) {
            }
        });
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    protected abstract NavigationBarMenuView c(Context context);

    public void d(int i2) {
        this.presenter.i(true);
        getMenuInflater().inflate(i2, this.menu);
        this.presenter.i(false);
        this.presenter.updateMenuView(true);
    }

    @Px
    public int getActiveIndicatorLabelPadding() {
        return this.menuView.getActiveIndicatorLabelPadding();
    }

    @Nullable
    public ColorStateList getItemActiveIndicatorColor() {
        return this.menuView.getItemActiveIndicatorColor();
    }

    @Px
    public int getItemActiveIndicatorHeight() {
        return this.menuView.getItemActiveIndicatorHeight();
    }

    @Px
    public int getItemActiveIndicatorMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorMarginHorizontal();
    }

    @Nullable
    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.menuView.getItemActiveIndicatorShapeAppearance();
    }

    @Px
    public int getItemActiveIndicatorWidth() {
        return this.menuView.getItemActiveIndicatorWidth();
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    @DrawableRes
    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    @Dimension
    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    @Px
    public int getItemPaddingBottom() {
        return this.menuView.getItemPaddingBottom();
    }

    @Px
    public int getItemPaddingTop() {
        return this.menuView.getItemPaddingTop();
    }

    @Nullable
    public ColorStateList getItemRippleColor() {
        return this.menuView.getItemRippleColor();
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    @NonNull
    public Menu getMenu() {
        return this.menu;
    }

    @NonNull
    @RestrictTo
    public MenuView getMenuView() {
        return this.menuView;
    }

    @NonNull
    @RestrictTo
    public NavigationBarPresenter getPresenter() {
        return this.presenter;
    }

    @IdRes
    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.e(this);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        this.menu.T(savedState.f14854i);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f14854i = bundle;
        this.menu.V(bundle);
        return savedState;
    }

    public void setActiveIndicatorLabelPadding(@Px int i2) {
        this.menuView.setActiveIndicatorLabelPadding(i2);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.d(this, f2);
    }

    public void setItemActiveIndicatorColor(@Nullable ColorStateList colorStateList) {
        this.menuView.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.menuView.setItemActiveIndicatorEnabled(z);
    }

    public void setItemActiveIndicatorHeight(@Px int i2) {
        this.menuView.setItemActiveIndicatorHeight(i2);
    }

    public void setItemActiveIndicatorMarginHorizontal(@Px int i2) {
        this.menuView.setItemActiveIndicatorMarginHorizontal(i2);
    }

    public void setItemActiveIndicatorShapeAppearance(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.menuView.setItemActiveIndicatorShapeAppearance(shapeAppearanceModel);
    }

    public void setItemActiveIndicatorWidth(@Px int i2) {
        this.menuView.setItemActiveIndicatorWidth(i2);
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.menuView.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(@DrawableRes int i2) {
        this.menuView.setItemBackgroundRes(i2);
    }

    public void setItemIconSize(@Dimension int i2) {
        this.menuView.setItemIconSize(i2);
    }

    public void setItemIconSizeRes(@DimenRes int i2) {
        setItemIconSize(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.menuView.setIconTintList(colorStateList);
    }

    public void setItemPaddingBottom(@Px int i2) {
        this.menuView.setItemPaddingBottom(i2);
    }

    public void setItemPaddingTop(@Px int i2) {
        this.menuView.setItemPaddingTop(i2);
    }

    public void setItemRippleColor(@Nullable ColorStateList colorStateList) {
        this.menuView.setItemRippleColor(colorStateList);
    }

    public void setItemTextAppearanceActive(@StyleRes int i2) {
        this.menuView.setItemTextAppearanceActive(i2);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.menuView.setItemTextAppearanceActiveBoldEnabled(z);
    }

    public void setItemTextAppearanceInactive(@StyleRes int i2) {
        this.menuView.setItemTextAppearanceInactive(i2);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.menuView.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.menuView.getLabelVisibilityMode() != i2) {
            this.menuView.setLabelVisibilityMode(i2);
            this.presenter.updateMenuView(false);
        }
    }

    public void setOnItemReselectedListener(@Nullable OnItemReselectedListener onItemReselectedListener) {
        this.reselectedListener = onItemReselectedListener;
    }

    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.selectedListener = onItemSelectedListener;
    }

    public void setSelectedItemId(@IdRes int i2) {
        MenuItem findItem = this.menu.findItem(i2);
        if (findItem == null || this.menu.P(findItem, this.presenter, 0)) {
            return;
        }
        findItem.setChecked(true);
    }
}
