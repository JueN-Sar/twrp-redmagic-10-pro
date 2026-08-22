package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.HashSet;

@RestrictTo
/* loaded from: classes.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int ITEM_POOL_SIZE = 5;
    private static final int NO_PADDING = -1;

    @NonNull
    private final SparseArray<BadgeDrawable> badgeDrawables;

    @Nullable
    private NavigationBarItemView[] buttons;
    private ColorStateList itemActiveIndicatorColor;
    private boolean itemActiveIndicatorEnabled;
    private int itemActiveIndicatorHeight;
    private int itemActiveIndicatorLabelPadding;
    private int itemActiveIndicatorMarginHorizontal;
    private boolean itemActiveIndicatorResizeable;
    private ShapeAppearanceModel itemActiveIndicatorShapeAppearance;
    private int itemActiveIndicatorWidth;
    private Drawable itemBackground;
    private int itemBackgroundRes;

    @Dimension
    private int itemIconSize;

    @Nullable
    private ColorStateList itemIconTint;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private final Pools.Pool<NavigationBarItemView> itemPool;

    @Nullable
    private ColorStateList itemRippleColor;

    @StyleRes
    private int itemTextAppearanceActive;
    private boolean itemTextAppearanceActiveBoldEnabled;

    @StyleRes
    private int itemTextAppearanceInactive;

    @Nullable
    private final ColorStateList itemTextColorDefault;
    private ColorStateList itemTextColorFromUser;
    private int labelVisibilityMode;
    private MenuBuilder menu;

    @NonNull
    private final View.OnClickListener onClickListener;

    @NonNull
    private final SparseArray<View.OnTouchListener> onTouchListeners;
    private NavigationBarPresenter presenter;
    private int selectedItemId;
    private int selectedItemPosition;

    @Nullable
    private final TransitionSet set;

    public NavigationBarMenuView(Context context) {
        super(context);
        this.itemPool = new Pools.SynchronizedPool(5);
        this.onTouchListeners = new SparseArray<>(5);
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        this.badgeDrawables = new SparseArray<>(5);
        this.itemPaddingTop = -1;
        this.itemPaddingBottom = -1;
        this.itemActiveIndicatorLabelPadding = -1;
        this.itemActiveIndicatorResizeable = false;
        this.itemTextColorDefault = e(R.attr.textColorSecondary);
        if (isInEditMode()) {
            this.set = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.set = autoTransition;
            autoTransition.B0(0);
            autoTransition.h0(MotionUtils.f(getContext(), com.google.android.material.R.attr.motionDurationMedium4, getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1)));
            autoTransition.j0(MotionUtils.g(getContext(), com.google.android.material.R.attr.motionEasingStandard, AnimationUtils.f13815b));
            autoTransition.s0(new TextScale());
        }
        this.onClickListener = new View.OnClickListener() { // from class: com.google.android.material.navigation.NavigationBarMenuView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MenuItemImpl itemData = ((NavigationBarItemView) view).getItemData();
                if (NavigationBarMenuView.this.menu.P(itemData, NavigationBarMenuView.this.presenter, 0)) {
                    return;
                }
                itemData.setChecked(true);
            }
        };
        ViewCompat.s0(this, 1);
    }

    private Drawable f() {
        if (this.itemActiveIndicatorShapeAppearance == null || this.itemActiveIndicatorColor == null) {
            return null;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.itemActiveIndicatorShapeAppearance);
        materialShapeDrawable.a0(this.itemActiveIndicatorColor);
        return materialShapeDrawable;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.itemPool.acquire();
        return navigationBarItemView == null ? g(getContext()) : navigationBarItemView;
    }

    private boolean i(int i2) {
        return i2 != -1;
    }

    private void j() {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < this.menu.size(); i2++) {
            hashSet.add(Integer.valueOf(this.menu.getItem(i2).getItemId()));
        }
        for (int i3 = 0; i3 < this.badgeDrawables.size(); i3++) {
            int keyAt = this.badgeDrawables.keyAt(i3);
            if (!hashSet.contains(Integer.valueOf(keyAt))) {
                this.badgeDrawables.delete(keyAt);
            }
        }
    }

    private void setBadgeIfNeeded(@NonNull NavigationBarItemView navigationBarItemView) {
        BadgeDrawable badgeDrawable;
        int id = navigationBarItemView.getId();
        if (i(id) && (badgeDrawable = this.badgeDrawables.get(id)) != null) {
            navigationBarItemView.setBadge(badgeDrawable);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void a(MenuBuilder menuBuilder) {
        this.menu = menuBuilder;
    }

    public void d() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.itemPool.release(navigationBarItemView);
                    navigationBarItemView.g();
                }
            }
        }
        if (this.menu.size() == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            return;
        }
        j();
        this.buttons = new NavigationBarItemView[this.menu.size()];
        boolean h2 = h(this.labelVisibilityMode, this.menu.G().size());
        for (int i2 = 0; i2 < this.menu.size(); i2++) {
            this.presenter.i(true);
            this.menu.getItem(i2).setCheckable(true);
            this.presenter.i(false);
            NavigationBarItemView newItem = getNewItem();
            this.buttons[i2] = newItem;
            newItem.setIconTintList(this.itemIconTint);
            newItem.setIconSize(this.itemIconSize);
            newItem.setTextColor(this.itemTextColorDefault);
            newItem.setTextAppearanceInactive(this.itemTextAppearanceInactive);
            newItem.setTextAppearanceActive(this.itemTextAppearanceActive);
            newItem.setTextAppearanceActiveBoldEnabled(this.itemTextAppearanceActiveBoldEnabled);
            newItem.setTextColor(this.itemTextColorFromUser);
            int i3 = this.itemPaddingTop;
            if (i3 != -1) {
                newItem.setItemPaddingTop(i3);
            }
            int i4 = this.itemPaddingBottom;
            if (i4 != -1) {
                newItem.setItemPaddingBottom(i4);
            }
            int i5 = this.itemActiveIndicatorLabelPadding;
            if (i5 != -1) {
                newItem.setActiveIndicatorLabelPadding(i5);
            }
            newItem.setActiveIndicatorWidth(this.itemActiveIndicatorWidth);
            newItem.setActiveIndicatorHeight(this.itemActiveIndicatorHeight);
            newItem.setActiveIndicatorMarginHorizontal(this.itemActiveIndicatorMarginHorizontal);
            newItem.setActiveIndicatorDrawable(f());
            newItem.setActiveIndicatorResizeable(this.itemActiveIndicatorResizeable);
            newItem.setActiveIndicatorEnabled(this.itemActiveIndicatorEnabled);
            Drawable drawable = this.itemBackground;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.itemBackgroundRes);
            }
            newItem.setItemRippleColor(this.itemRippleColor);
            newItem.setShifting(h2);
            newItem.setLabelVisibilityMode(this.labelVisibilityMode);
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.menu.getItem(i2);
            newItem.c(menuItemImpl, 0);
            newItem.setItemPosition(i2);
            int itemId = menuItemImpl.getItemId();
            newItem.setOnTouchListener(this.onTouchListeners.get(itemId));
            newItem.setOnClickListener(this.onClickListener);
            int i6 = this.selectedItemId;
            if (i6 != 0 && itemId == i6) {
                this.selectedItemPosition = i2;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int min = Math.min(this.menu.size() - 1, this.selectedItemPosition);
        this.selectedItemPosition = min;
        this.menu.getItem(min).setChecked(true);
    }

    public ColorStateList e(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList a2 = AppCompatResources.a(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = a2.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, ViewGroup.EMPTY_STATE_SET}, new int[]{a2.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    protected abstract NavigationBarItemView g(Context context);

    @Px
    public int getActiveIndicatorLabelPadding() {
        return this.itemActiveIndicatorLabelPadding;
    }

    SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.badgeDrawables;
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    @Nullable
    public ColorStateList getItemActiveIndicatorColor() {
        return this.itemActiveIndicatorColor;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.itemActiveIndicatorEnabled;
    }

    @Px
    public int getItemActiveIndicatorHeight() {
        return this.itemActiveIndicatorHeight;
    }

    @Px
    public int getItemActiveIndicatorMarginHorizontal() {
        return this.itemActiveIndicatorMarginHorizontal;
    }

    @Nullable
    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.itemActiveIndicatorShapeAppearance;
    }

    @Px
    public int getItemActiveIndicatorWidth() {
        return this.itemActiveIndicatorWidth;
    }

    @Nullable
    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.itemBackground : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    @Dimension
    public int getItemIconSize() {
        return this.itemIconSize;
    }

    @Px
    public int getItemPaddingBottom() {
        return this.itemPaddingBottom;
    }

    @Px
    public int getItemPaddingTop() {
        return this.itemPaddingTop;
    }

    @Nullable
    public ColorStateList getItemRippleColor() {
        return this.itemRippleColor;
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    @Nullable
    protected MenuBuilder getMenu() {
        return this.menu;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    protected int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    public int getWindowAnimations() {
        return 0;
    }

    protected boolean h(int i2, int i3) {
        if (i2 == -1) {
            if (i3 <= 3) {
                return false;
            }
        } else if (i2 != 0) {
            return false;
        }
        return true;
    }

    void k(SparseArray sparseArray) {
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            int keyAt = sparseArray.keyAt(i2);
            if (this.badgeDrawables.indexOfKey(keyAt) < 0) {
                this.badgeDrawables.append(keyAt, (BadgeDrawable) sparseArray.get(keyAt));
            }
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                BadgeDrawable badgeDrawable = this.badgeDrawables.get(navigationBarItemView.getId());
                if (badgeDrawable != null) {
                    navigationBarItemView.setBadge(badgeDrawable);
                }
            }
        }
    }

    void l(int i2) {
        int size = this.menu.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.menu.getItem(i3);
            if (i2 == item.getItemId()) {
                this.selectedItemId = i2;
                this.selectedItemPosition = i3;
                item.setChecked(true);
                return;
            }
        }
    }

    public void m() {
        TransitionSet transitionSet;
        MenuBuilder menuBuilder = this.menu;
        if (menuBuilder == null || this.buttons == null) {
            return;
        }
        int size = menuBuilder.size();
        if (size != this.buttons.length) {
            d();
            return;
        }
        int i2 = this.selectedItemId;
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.menu.getItem(i3);
            if (item.isChecked()) {
                this.selectedItemId = item.getItemId();
                this.selectedItemPosition = i3;
            }
        }
        if (i2 != this.selectedItemId && (transitionSet = this.set) != null) {
            TransitionManager.a(this, transitionSet);
        }
        boolean h2 = h(this.labelVisibilityMode, this.menu.G().size());
        for (int i4 = 0; i4 < size; i4++) {
            this.presenter.i(true);
            this.buttons[i4].setLabelVisibilityMode(this.labelVisibilityMode);
            this.buttons[i4].setShifting(h2);
            this.buttons[i4].c((MenuItemImpl) this.menu.getItem(i4), 0);
            this.presenter.i(false);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo).j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.b(1, this.menu.G().size(), false, 1));
    }

    public void setActiveIndicatorLabelPadding(@Px int i2) {
        this.itemActiveIndicatorLabelPadding = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorLabelPadding(i2);
            }
        }
    }

    public void setIconTintList(@Nullable ColorStateList colorStateList) {
        this.itemIconTint = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemActiveIndicatorColor(@Nullable ColorStateList colorStateList) {
        this.itemActiveIndicatorColor = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(f());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.itemActiveIndicatorEnabled = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z);
            }
        }
    }

    public void setItemActiveIndicatorHeight(@Px int i2) {
        this.itemActiveIndicatorHeight = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i2);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(@Px int i2) {
        this.itemActiveIndicatorMarginHorizontal = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i2);
            }
        }
    }

    protected void setItemActiveIndicatorResizeable(boolean z) {
        this.itemActiveIndicatorResizeable = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.itemActiveIndicatorShapeAppearance = shapeAppearanceModel;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(f());
            }
        }
    }

    public void setItemActiveIndicatorWidth(@Px int i2) {
        this.itemActiveIndicatorWidth = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i2);
            }
        }
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.itemBackground = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i2) {
        this.itemBackgroundRes = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i2);
            }
        }
    }

    public void setItemIconSize(@Dimension int i2) {
        this.itemIconSize = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i2);
            }
        }
    }

    public void setItemPaddingBottom(@Px int i2) {
        this.itemPaddingBottom = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i2);
            }
        }
    }

    public void setItemPaddingTop(@Px int i2) {
        this.itemPaddingTop = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i2);
            }
        }
    }

    public void setItemRippleColor(@Nullable ColorStateList colorStateList) {
        this.itemRippleColor = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemRippleColor(colorStateList);
            }
        }
    }

    public void setItemTextAppearanceActive(@StyleRes int i2) {
        this.itemTextAppearanceActive = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i2);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.itemTextAppearanceActiveBoldEnabled = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActiveBoldEnabled(z);
            }
        }
    }

    public void setItemTextAppearanceInactive(@StyleRes int i2) {
        this.itemTextAppearanceInactive = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i2);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.itemTextColorFromUser = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i2) {
        this.labelVisibilityMode = i2;
    }

    public void setPresenter(@NonNull NavigationBarPresenter navigationBarPresenter) {
        this.presenter = navigationBarPresenter;
    }
}
