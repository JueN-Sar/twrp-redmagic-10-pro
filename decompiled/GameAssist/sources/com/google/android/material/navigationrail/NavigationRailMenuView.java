package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

@RestrictTo
/* loaded from: classes.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {

    @Px
    private int itemMinimumHeight;
    private final FrameLayout.LayoutParams layoutParams;

    public NavigationRailMenuView(Context context) {
        super(context);
        this.itemMinimumHeight = -1;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 49;
        setLayoutParams(layoutParams);
        setItemActiveIndicatorResizeable(true);
    }

    private int o(int i2, int i3, int i4) {
        int max = i3 / Math.max(1, i4);
        int i5 = this.itemMinimumHeight;
        if (i5 == -1) {
            i5 = View.MeasureSpec.getSize(i2);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(i5, max), 0);
    }

    private int p(View view, int i2, int i3) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        view.measure(i2, i3);
        return view.getMeasuredHeight();
    }

    private int q(int i2, int i3, int i4, View view) {
        int o2 = view == null ? o(i2, i3, i4) : View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt != view) {
                i5 += p(childAt, i2, o2);
            }
        }
        return i5;
    }

    private int r(int i2, int i3, int i4) {
        int i5;
        View childAt = getChildAt(getSelectedItemPosition());
        if (childAt != null) {
            i5 = p(childAt, i2, o(i2, i3, i4));
            i3 -= i5;
            i4--;
        } else {
            i5 = 0;
        }
        return i5 + q(i2, i3, i4, childAt);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView g(Context context) {
        return new NavigationRailItemView(context);
    }

    @Px
    public int getItemMinimumHeight() {
        return this.itemMinimumHeight;
    }

    int getMenuGravity() {
        return this.layoutParams.gravity;
    }

    boolean n() {
        return (this.layoutParams.gravity & 112) == 48;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i7;
                childAt.layout(0, i7, i6, measuredHeight);
                i7 = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i3);
        int size2 = getMenu().G().size();
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.resolveSizeAndState((size2 <= 1 || !h(getLabelVisibilityMode(), size2)) ? q(i2, size, size2, null) : r(i2, size, size2), i3, 0));
    }

    public void setItemMinimumHeight(@Px int i2) {
        if (this.itemMinimumHeight != i2) {
            this.itemMinimumHeight = i2;
            requestLayout();
        }
    }

    void setMenuGravity(int i2) {
        FrameLayout.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams.gravity != i2) {
            layoutParams.gravity = i2;
            setLayoutParams(layoutParams);
        }
    }
}
