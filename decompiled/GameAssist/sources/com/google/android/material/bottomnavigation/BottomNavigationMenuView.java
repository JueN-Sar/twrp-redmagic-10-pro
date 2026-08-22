package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private boolean itemHorizontalTranslationEnabled;
    private final List<Integer> tempChildWidths;

    public BottomNavigationMenuView(Context context) {
        super(context);
        this.tempChildWidths = new ArrayList();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView g(Context context) {
        return new BottomNavigationItemView(context);
    }

    public boolean n() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.v(this) == 1) {
                    int i10 = i6 - i8;
                    childAt.layout(i10 - childAt.getMeasuredWidth(), 0, i10, i7);
                } else {
                    childAt.layout(i8, 0, childAt.getMeasuredWidth() + i8, i7);
                }
                i8 += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        MenuBuilder menu = getMenu();
        int size = View.MeasureSpec.getSize(i2);
        int size2 = menu.G().size();
        int childCount = getChildCount();
        this.tempChildWidths.clear();
        int size3 = View.MeasureSpec.getSize(i3);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size3, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        if (h(getLabelVisibilityMode(), size2) && n()) {
            View childAt = getChildAt(getSelectedItemPosition());
            int i6 = this.activeItemMinWidth;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), makeMeasureSpec);
                i6 = Math.max(i6, childAt.getMeasuredWidth());
            }
            int i7 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int min = Math.min(size - (this.inactiveItemMinWidth * i7), Math.min(i6, this.activeItemMaxWidth));
            int i8 = size - min;
            int min2 = Math.min(i8 / (i7 != 0 ? i7 : 1), this.inactiveItemMaxWidth);
            int i9 = i8 - (i7 * min2);
            int i10 = 0;
            while (i10 < childCount) {
                if (getChildAt(i10).getVisibility() != 8) {
                    i5 = i10 == getSelectedItemPosition() ? min : min2;
                    if (i9 > 0) {
                        i5++;
                        i9--;
                    }
                } else {
                    i5 = 0;
                }
                this.tempChildWidths.add(Integer.valueOf(i5));
                i10++;
            }
        } else {
            int min3 = Math.min(size / (size2 != 0 ? size2 : 1), this.activeItemMaxWidth);
            int i11 = size - (size2 * min3);
            for (int i12 = 0; i12 < childCount; i12++) {
                if (getChildAt(i12).getVisibility() == 8) {
                    i4 = 0;
                } else if (i11 > 0) {
                    i4 = min3 + 1;
                    i11--;
                } else {
                    i4 = min3;
                }
                this.tempChildWidths.add(Integer.valueOf(i4));
            }
        }
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths.get(i14).intValue(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i13 += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(i13, size3);
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.itemHorizontalTranslationEnabled = z;
    }
}
