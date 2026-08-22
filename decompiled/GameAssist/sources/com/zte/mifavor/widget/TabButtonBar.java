package com.zte.mifavor.widget;

import android.annotation.NonNull;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ActionMenuView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.view.menu.MenuBuilder;
import com.android.internal.view.menu.MenuItemImpl;
import com.android.internal.view.menu.MenuPresenter;
import com.android.internal.view.menu.MenuView;
import com.android.internal.view.menu.SubMenuBuilder;
import com.zte.extres.R;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TabButtonBar extends ViewGroup implements onMenuEvent {
    public static final int MODE_SELECT = 1;
    public static final int MODE_TAB = 0;
    public static final int MODE_TITLE_MENU = 2;
    private static final String TAG = "TabButtonBar";
    private int mActionColor;
    private int mActionFlag;
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private int mButtonGravity;
    private ImageButton mCollapseButtonView;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mCurrentMode;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private View mMenuDividerView;
    private ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private int mNavButtonStyle;
    private ImageButton mNavButtonView;
    private int mNavColor;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ImageButton mSelelctButtonView;
    private final Runnable mShowOverflowMenuRunnable;
    private PagerSecond mTabBar;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private boolean mTextAllCaps;
    private int mTitleColor;
    private TextView mTitleTextView;

    private class ExpandedActionViewMenuPresenter implements MenuPresenter {

        /* renamed from: a, reason: collision with root package name */
        MenuBuilder f17782a;

        /* renamed from: b, reason: collision with root package name */
        MenuItemImpl f17783b;

        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            KeyEvent.Callback callback = TabButtonBar.this.mExpandedActionView;
            if (callback instanceof CollapsibleActionView) {
                ((CollapsibleActionView) callback).onActionViewCollapsed();
            }
            TabButtonBar tabButtonBar = TabButtonBar.this;
            tabButtonBar.removeView(tabButtonBar.mExpandedActionView);
            TabButtonBar tabButtonBar2 = TabButtonBar.this;
            tabButtonBar2.removeView(tabButtonBar2.mCollapseButtonView);
            TabButtonBar tabButtonBar3 = TabButtonBar.this;
            tabButtonBar3.mExpandedActionView = null;
            tabButtonBar3.d();
            this.f17783b = null;
            TabButtonBar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            TabButtonBar.this.g();
            ViewParent parent = TabButtonBar.this.mCollapseButtonView.getParent();
            TabButtonBar tabButtonBar = TabButtonBar.this;
            if (parent != tabButtonBar) {
                tabButtonBar.addView(tabButtonBar.mCollapseButtonView);
            }
            TabButtonBar.this.mExpandedActionView = menuItemImpl.getActionView();
            this.f17783b = menuItemImpl;
            if (TabButtonBar.this.mExpandedActionView.getParent() != TabButtonBar.this) {
                TabButtonBar.this.mExpandedActionView.setLayoutParams(new LayoutParams(-2, -2));
                TabButtonBar tabButtonBar2 = TabButtonBar.this;
                tabButtonBar2.addView(tabButtonBar2.mExpandedActionView);
            }
            TabButtonBar.this.z();
            TabButtonBar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(true);
            KeyEvent.Callback callback = TabButtonBar.this.mExpandedActionView;
            if (callback instanceof CollapsibleActionView) {
                ((CollapsibleActionView) callback).onActionViewExpanded();
            }
            return true;
        }

        public boolean flagActionItems() {
            return false;
        }

        public int getId() {
            return 0;
        }

        public MenuView getMenuView(ViewGroup viewGroup) {
            return null;
        }

        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            MenuItemImpl menuItemImpl;
            MenuBuilder menuBuilder2 = this.f17782a;
            if (menuBuilder2 != null && (menuItemImpl = this.f17783b) != null) {
                menuBuilder2.collapseItemActionView(menuItemImpl);
            }
            this.f17782a = menuBuilder;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public void setCallback(MenuPresenter.Callback callback) {
        }

        public void updateMenuView(boolean z) {
            if (this.f17783b != null) {
                MenuBuilder menuBuilder = this.f17782a;
                if (menuBuilder != null) {
                    int size = menuBuilder.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (this.f17782a.getItem(i2) == this.f17783b) {
                            return;
                        }
                    }
                }
                collapseItemActionView(this.f17782a, this.f17783b);
            }
        }

        private ExpandedActionViewMenuPresenter() {
        }
    }

    public static class LayoutParams extends ActionBar.LayoutParams {
        public LayoutParams(@NonNull Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public TabButtonBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.mfvTabButtonBarStyle);
    }

    private boolean B() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (D(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean C() {
        return D(this.mMenuView) && this.mMenuView.getMenu().hasVisibleItems() && this.mMenuView.getChildCount() > 0;
    }

    private boolean D(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private void e(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (view.getParent() != null) {
            Log.e(TAG, "view has parent!");
        } else {
            addView(view, layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.mCollapseButtonView == null) {
            ImageButton imageButton = new ImageButton(getContext(), null, 0, this.mNavButtonStyle);
            this.mCollapseButtonView = imageButton;
            imageButton.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setLayoutParams(new LayoutParams(-2, -1));
            this.mCollapseButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.widget.TabButtonBar.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TabButtonBar.this.f();
                }
            });
        }
    }

    private int getCurrentContentInsetEnd() {
        if (C()) {
            return -1;
        }
        return getResources().getDimensionPixelSize(R.dimen.mfvc_large_padding);
    }

    private int getCurrentContentInsetStart() {
        return (D(this.mNavButtonView) || D(this.mCollapseButtonView)) ? getResources().getDimensionPixelSize(R.dimen.mfvc_list_ic_txt_left_padding) : getResources().getDimensionPixelSize(R.dimen.mfvc_large_padding);
    }

    private MenuInflater getMenuInflater() {
        return new MenuInflater(getContext());
    }

    private void h() {
        j();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder menu = this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void i() {
        Log.d(TAG, "add MenuDivider");
        View view = this.mMenuDividerView;
        if (view != null) {
            e(view);
            return;
        }
        View view2 = new View(getContext());
        this.mMenuDividerView = view2;
        view2.setId(R.id.tab_menu_divider);
        this.mMenuDividerView.setBackgroundColor(getContext().getResources().getColor(R.color.mfv_common_divl));
        this.mMenuDividerView.setLayoutParams(new LayoutParams(1, getContext().getResources().getDimensionPixelSize(R.dimen.mfvc_ic_medium_height)));
        this.mMenuDividerView.setPadding(Utils.c(getContext(), 4), 0, Utils.c(getContext(), 4), 0);
        e(this.mMenuDividerView);
    }

    private void j() {
        Log.d(TAG, "add Menu");
        if (this.mMenuView == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.mMenuView = actionMenuView;
            actionMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            ((ActionBar.LayoutParams) layoutParams).gravity = (this.mButtonGravity & 112) | 8388613;
            this.mMenuView.setLayoutParams(layoutParams);
            e(this.mMenuView);
        }
    }

    private void k() {
        Log.d(TAG, "add NavButton");
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            e(imageButton);
            return;
        }
        ImageButton imageButton2 = new ImageButton(getContext(), null, 0, this.mNavButtonStyle);
        this.mNavButtonView = imageButton2;
        imageButton2.setImageResource(R.drawable.ic_ab_back_material);
        this.mNavButtonView.setBackgroundColor(0);
        this.mNavButtonView.getDrawable().setTint(this.mNavColor);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        ((ActionBar.LayoutParams) layoutParams).gravity = (this.mButtonGravity & 112) | 8388611;
        this.mNavButtonView.setLayoutParams(layoutParams);
        e(this.mNavButtonView);
    }

    private void l() {
        Log.d(TAG, "add SelectButton");
        ImageButton imageButton = this.mSelelctButtonView;
        if (imageButton != null) {
            e(imageButton);
            return;
        }
        ImageButton imageButton2 = new ImageButton(getContext());
        this.mSelelctButtonView = imageButton2;
        imageButton2.setImageResource(R.drawable.done_all);
        this.mSelelctButtonView.setBackgroundColor(0);
        this.mSelelctButtonView.getDrawable().setTint(this.mNavColor);
        LayoutParams layoutParams = new LayoutParams(Utils.c(getContext(), 48), Utils.c(getContext(), 48));
        ((ActionBar.LayoutParams) layoutParams).gravity = (this.mButtonGravity & 112) | 8388611;
        this.mSelelctButtonView.setLayoutParams(layoutParams);
        e(this.mSelelctButtonView);
    }

    private void m() {
        Log.d(TAG, "add Title");
        if (this.mTitleTextView != null) {
            int color = getResources().getColor(R.color.mfv_common_acb_txt);
            this.mTitleColor = color;
            this.mTitleTextView.setTextColor(color);
            e(this.mTitleTextView);
            return;
        }
        Context context = getContext();
        TextView textView = new TextView(context);
        this.mTitleTextView = textView;
        textView.setSingleLine();
        this.mTitleTextView.setTextAppearance(context, R.style.mfvc_appbar_primary_font);
        int color2 = getResources().getColor(R.color.mfv_common_acb_txt);
        this.mTitleColor = color2;
        this.mTitleTextView.setTextColor(color2);
        this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.mTitleTextView.setLayoutParams(generateDefaultLayoutParams());
        e(this.mTitleTextView);
    }

    private int o(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int p2 = p(((ActionBar.LayoutParams) layoutParams).gravity);
        if (p2 == 48) {
            return getPaddingTop() - i3;
        }
        if (p2 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ActionBar.LayoutParams) layoutParams).bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ActionBar.LayoutParams) layoutParams).topMargin;
        if (i4 < i5) {
            i4 = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            int i7 = ((ActionBar.LayoutParams) layoutParams).bottomMargin;
            if (i6 < i7) {
                i4 = Math.max(0, i4 - (i7 - i6));
            }
        }
        return paddingTop + i4;
    }

    private int p(int i2) {
        int i3 = i2 & 112;
        return (i3 == 16 || i3 == 48 || i3 == 80) ? i3 : this.mGravity & 112;
    }

    private int q(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
    }

    private int r(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int u(View view, int i2, int[] iArr, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ActionBar.LayoutParams) layoutParams).leftMargin - iArr[0];
        int max = i2 + Math.max(0, i4);
        iArr[0] = Math.max(0, -i4);
        int o2 = o(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        if (view.getId() == R.id.tabs_shadow) {
            view.layout(max, Utils.c(getContext(), 52), max + measuredWidth, Utils.c(getContext(), 52) + view.getMeasuredHeight());
        } else {
            view.layout(max, o2, max + measuredWidth, view.getMeasuredHeight() + o2);
        }
        return max + measuredWidth + ((ActionBar.LayoutParams) layoutParams).rightMargin;
    }

    private int v(View view, int i2, int[] iArr, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ActionBar.LayoutParams) layoutParams).rightMargin - iArr[1];
        int max = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int o2 = o(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        StringBuilder sb = new StringBuilder();
        sb.append("right - childWidth=");
        int i5 = max - measuredWidth;
        sb.append(i5);
        sb.append("top=");
        sb.append(o2);
        sb.append("right=");
        sb.append(max);
        sb.append("top + child.getMeasuredHeight()=");
        sb.append(o2);
        sb.append(view.getMeasuredHeight());
        Log.e("guojingdong", sb.toString());
        view.layout(i5, o2, max, view.getMeasuredHeight() + o2);
        return max - (measuredWidth + ((ActionBar.LayoutParams) layoutParams).leftMargin);
    }

    private int w(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i7);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, ((ViewGroup) this).mPaddingLeft + ((ViewGroup) this).mPaddingRight + max + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, ((ViewGroup) this).mPaddingTop + ((ViewGroup) this).mPaddingBottom + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private void x(View view, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, ((ViewGroup) this).mPaddingLeft + ((ViewGroup) this).mPaddingRight + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, ((ViewGroup) this).mPaddingTop + ((ViewGroup) this).mPaddingBottom + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i6 >= 0) {
            if (mode != 0) {
                i6 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i6);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i6, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void y() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    void A() {
        TextView textView;
        Drawable[] compoundDrawables;
        Drawable drawable;
        if (this.mActionFlag == 0 || this.mMenuView == null) {
            return;
        }
        for (int i2 = 0; i2 < this.mMenuView.getChildCount(); i2++) {
            View childAt = this.mMenuView.getChildAt(i2);
            if ((childAt instanceof TextView) && (drawable = (compoundDrawables = (textView = (TextView) childAt).getCompoundDrawables())[0]) != null) {
                drawable.setTint(this.mActionColor);
                textView.setCompoundDrawables(compoundDrawables[0], null, null, null);
            }
            if (childAt instanceof ImageView) {
                ImageView imageView = (ImageView) childAt;
                Drawable mutate = imageView.getDrawable().mutate();
                if (mutate != null) {
                    mutate.setTint(this.mActionColor);
                    imageView.setImageDrawable(mutate);
                }
            }
        }
        this.mActionFlag = 0;
    }

    public boolean E() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.showOverflowMenu();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    void d() {
        PagerSecond pagerSecond = this.mTabBar;
        if (pagerSecond != null) {
            pagerSecond.setVisibility(0);
        }
    }

    public void f() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        MenuItemImpl menuItemImpl = expandedActionViewMenuPresenter == null ? null : expandedActionViewMenuPresenter.f17783b;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public Menu getMenu() {
        h();
        return this.mMenuView.getMenu();
    }

    public ImageButton getNavButton() {
        return this.mNavButtonView;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public ImageButton getSelectAllButton() {
        return this.mSelelctButtonView;
    }

    public PagerSecond getTabBar() {
        Log.d(TAG, "add PagerSecond");
        PagerSecond pagerSecond = this.mTabBar;
        if (pagerSecond == null) {
            this.mTabBar = new PagerSecond(getContext());
            LayoutParams layoutParams = new LayoutParams(-1, Utils.c(getContext(), 52));
            this.mTabBar.setLayoutParams(layoutParams);
            ((ActionBar.LayoutParams) layoutParams).gravity = (this.mButtonGravity & 112) | 48;
            this.mTabBar.setTextSize(17.0f);
            this.mTabBar.setBackground(null);
            this.mTabBar.setElevation(0.0f);
            this.mTabBar.setTextAllCaps(t());
            this.mTabBar.Q();
            e(this.mTabBar);
        } else {
            e(pagerSecond);
        }
        return this.mTabBar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "onLayout");
        boolean z2 = getLayoutDirection() == 1;
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = width - getPaddingRight();
        int minimumHeight = getMinimumHeight();
        int[] iArr = {0, 0};
        if (D(this.mMenuView) || D(this.mSelelctButtonView)) {
            if (z2) {
                paddingLeft += Math.max(getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.mfvc_small_padding));
            } else {
                paddingRight -= Math.max(getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.mfvc_small_padding));
            }
        }
        if (D(this.mSelelctButtonView)) {
            if (z2) {
                paddingLeft = u(this.mSelelctButtonView, paddingLeft, iArr, minimumHeight);
            } else {
                paddingRight = v(this.mSelelctButtonView, paddingRight, iArr, minimumHeight);
            }
        }
        if (D(this.mMenuDividerView)) {
            if (z2) {
                paddingLeft = u(this.mMenuDividerView, paddingLeft, iArr, minimumHeight);
            } else {
                paddingRight = v(this.mMenuDividerView, paddingRight, iArr, minimumHeight);
            }
        }
        if (D(this.mMenuView)) {
            if (z2) {
                paddingLeft = u(this.mMenuView, paddingLeft, iArr, minimumHeight);
            } else {
                paddingRight = v(this.mMenuView, paddingRight, iArr, minimumHeight);
            }
        }
        if (D(this.mNavButtonView)) {
            if (z2) {
                paddingRight = v(this.mNavButtonView, paddingRight, iArr, minimumHeight);
            } else {
                paddingLeft = u(this.mNavButtonView, paddingLeft, iArr, minimumHeight);
            }
        }
        if (D(this.mCollapseButtonView)) {
            if (z2) {
                paddingRight = v(this.mCollapseButtonView, paddingRight, iArr, minimumHeight);
            } else {
                paddingLeft = u(this.mCollapseButtonView, paddingLeft, iArr, minimumHeight);
            }
        }
        if (D(this.mTabBar)) {
            if (z2) {
                paddingRight = v(this.mTabBar, paddingRight, iArr, minimumHeight);
            } else {
                paddingLeft = u(this.mTabBar, paddingLeft, iArr, minimumHeight);
            }
        }
        if (D(this.mExpandedActionView)) {
            if (z2) {
                paddingRight = v(this.mExpandedActionView, paddingRight, iArr, minimumHeight);
            } else {
                paddingLeft = u(this.mExpandedActionView, paddingLeft, iArr, minimumHeight);
            }
        }
        if (D(this.mTitleTextView)) {
            if (z2) {
                v(this.mTitleTextView, Math.min(paddingRight, width - getCurrentContentInsetStart()), iArr, minimumHeight);
            } else {
                u(this.mTitleTextView, Math.max(paddingLeft, getCurrentContentInsetStart()), iArr, minimumHeight);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int max;
        int i8;
        int i9;
        int i10;
        int[] iArr = this.mTempMargins;
        boolean isLayoutRtl = isLayoutRtl();
        int i11 = !isLayoutRtl ? 1 : 0;
        Log.d(TAG, "====onMeasure W: " + View.MeasureSpec.toString(i2));
        int max2 = (C() || D(this.mSelelctButtonView)) ? Math.max(getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.mfvc_small_padding)) : 0;
        if (D(this.mSelelctButtonView)) {
            x(this.mSelelctButtonView, i2, max2, i3, 0, this.mMaxButtonHeight);
            i5 = this.mSelelctButtonView.getMeasuredWidth() + q(this.mSelelctButtonView);
            i6 = Math.max(0, this.mSelelctButtonView.getMeasuredHeight() + r(this.mSelelctButtonView));
            i4 = ViewGroup.combineMeasuredStates(0, this.mSelelctButtonView.getMeasuredState());
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        if (D(this.mMenuDividerView) && D(this.mSelelctButtonView) && C()) {
            x(this.mMenuDividerView, i2, max2, i3, 0, -1);
            i5 += this.mMenuDividerView.getMeasuredWidth() + q(this.mMenuDividerView);
            i6 = Math.max(i6, this.mMenuDividerView.getMeasuredHeight() + r(this.mMenuDividerView));
            i4 = ViewGroup.combineMeasuredStates(i4, this.mMenuDividerView.getMeasuredState());
        }
        int i12 = i4;
        if (D(this.mMenuView)) {
            x(this.mMenuView, i2, max2, i3, 0, this.mMaxButtonHeight);
            i5 += this.mMenuView.getMeasuredWidth() + q(this.mMenuView);
            i6 = Math.max(i6, this.mMenuView.getMeasuredHeight() + r(this.mMenuView));
            i12 = ViewGroup.combineMeasuredStates(i12, this.mMenuView.getMeasuredState());
        }
        if (D(this.mTabBar)) {
            max = max2 + i5;
            if (C()) {
                max += Math.max(getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.tab_margin_end));
            }
            x(this.mTabBar, i2, max, i3, 0, -1);
            i10 = Math.max(i6, this.mTabBar.getMeasuredHeight() + r(this.mTabBar));
            i9 = ViewGroup.combineMeasuredStates(i12, this.mTabBar.getMeasuredState());
            i8 = 0;
        } else {
            if (D(this.mNavButtonView)) {
                x(this.mNavButtonView, i2, max2, i3, 0, this.mMaxButtonHeight);
                i7 = this.mNavButtonView.getMeasuredWidth() + q(this.mNavButtonView);
                i6 = Math.max(i6, this.mNavButtonView.getMeasuredHeight() + r(this.mNavButtonView));
                i12 = ViewGroup.combineMeasuredStates(i12, this.mNavButtonView.getMeasuredState());
            } else {
                i7 = 0;
            }
            if (D(this.mCollapseButtonView)) {
                x(this.mCollapseButtonView, i2, max2, i3, 0, this.mMaxButtonHeight);
                i7 = this.mCollapseButtonView.getMeasuredWidth() + q(this.mCollapseButtonView);
                i6 = Math.max(i6, this.mCollapseButtonView.getMeasuredHeight() + r(this.mCollapseButtonView));
                i12 = ViewGroup.combineMeasuredStates(i12, this.mCollapseButtonView.getMeasuredState());
            }
            int currentContentInsetStart = getCurrentContentInsetStart();
            int max3 = max2 + Math.max(currentContentInsetStart, i7);
            iArr[isLayoutRtl ? 1 : 0] = Math.max(0, currentContentInsetStart - i7);
            Log.d(TAG, "nav usedW:" + max3);
            int currentContentInsetEnd = getCurrentContentInsetEnd();
            Log.d(TAG, "contentInsetEnd:" + currentContentInsetEnd);
            max = max3 + Math.max(currentContentInsetEnd, i5);
            i8 = 0;
            iArr[i11] = Math.max(0, currentContentInsetEnd - i5);
            Log.d(TAG, "menu usedW:" + max);
            if (D(this.mTitleTextView)) {
                if (C() || D(this.mSelelctButtonView)) {
                    max += Math.max(getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.actionbar_title_margin_end));
                }
                x(this.mTitleTextView, i2, max, i3, 0, -1);
                i6 = Math.max(i6, this.mTitleTextView.getMeasuredHeight() + r(this.mTitleTextView));
                i12 = ViewGroup.combineMeasuredStates(i12, this.mTitleTextView.getMeasuredState());
            }
            Log.d(TAG, "title usedW:" + max);
            if (D(this.mExpandedActionView)) {
                if (C() || D(this.mSelelctButtonView)) {
                    max += Math.max(getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.expanded_action_view_margin_end));
                }
                w(this.mExpandedActionView, i2, max, i3, 0, iArr);
                i10 = Math.max(i6, this.mExpandedActionView.getMeasuredHeight() + r(this.mExpandedActionView));
                i9 = ViewGroup.combineMeasuredStates(i12, this.mExpandedActionView.getMeasuredState());
            } else {
                i9 = i12;
                i10 = i6;
            }
        }
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(max + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, (-16777216) & i9), B() ? i8 : ViewGroup.resolveSizeAndState(Math.max(i10 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, i9 << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        ActionMenuView actionMenuView = this.mMenuView;
        MenuBuilder peekMenu = actionMenuView != null ? actionMenuView.peekMenu() : null;
        int i2 = savedState.f17785c;
        if (i2 != 0 && this.mExpandedMenuPresenter != null && peekMenu != null && (findItem = peekMenu.findItem(i2)) != null) {
            findItem.expandActionView();
        }
        if (savedState.f17786h) {
            y();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        MenuItemImpl menuItemImpl;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        if (expandedActionViewMenuPresenter != null && (menuItemImpl = expandedActionViewMenuPresenter.f17783b) != null) {
            savedState.f17785c = menuItemImpl.getItemId();
        }
        savedState.f17786h = s();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public boolean s() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.isOverflowMenuShowing();
    }

    public void setActionMenuColor(int i2) {
        this.mActionFlag = 1;
        this.mActionColor = i2;
        A();
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        requestLayout();
    }

    public void setCurrentMode(int i2) {
        this.mCurrentMode = i2;
        if (i2 == 1) {
            k();
            m();
            l();
            i();
            PagerSecond pagerSecond = this.mTabBar;
            if (pagerSecond != null && pagerSecond.getParent() != null) {
                removeView(this.mTabBar);
            }
            ActionMenuView actionMenuView = this.mMenuView;
            if (actionMenuView != null && actionMenuView.getParent() != null) {
                this.mMenuView.setVisibility(8);
            }
            View view = this.mMenuDividerView;
            if (view != null && view.getParent() != null) {
                this.mMenuDividerView.setVisibility(8);
            }
            ImageButton imageButton = this.mCollapseButtonView;
            if (imageButton != null && imageButton.getParent() != null) {
                this.mCollapseButtonView.setVisibility(8);
            }
            View view2 = this.mExpandedActionView;
            if (view2 == null || view2.getParent() == null) {
                return;
            }
            this.mExpandedActionView.setVisibility(8);
            return;
        }
        if (i2 == 2) {
            k();
            m();
            ActionMenuView actionMenuView2 = this.mMenuView;
            if (actionMenuView2 != null && actionMenuView2.getParent() != null) {
                this.mMenuView.setVisibility(0);
            }
            View view3 = this.mMenuDividerView;
            if (view3 != null && view3.getParent() != null) {
                this.mMenuDividerView.setVisibility(8);
            }
            PagerSecond pagerSecond2 = this.mTabBar;
            if (pagerSecond2 != null && pagerSecond2.getParent() != null) {
                removeView(this.mTabBar);
            }
            ImageButton imageButton2 = this.mCollapseButtonView;
            if (imageButton2 != null && imageButton2.getParent() != null) {
                this.mCollapseButtonView.setVisibility(8);
            }
            View view4 = this.mExpandedActionView;
            if (view4 != null && view4.getParent() != null) {
                this.mExpandedActionView.setVisibility(8);
            }
            ImageButton imageButton3 = this.mSelelctButtonView;
            if (imageButton3 == null || imageButton3.getParent() == null) {
                return;
            }
            removeView(this.mSelelctButtonView);
            return;
        }
        TextView textView = this.mTitleTextView;
        if (textView != null && textView.getParent() != null) {
            removeView(this.mTitleTextView);
        }
        ImageButton imageButton4 = this.mNavButtonView;
        if (imageButton4 != null && imageButton4.getParent() != null) {
            removeView(this.mNavButtonView);
        }
        ImageButton imageButton5 = this.mSelelctButtonView;
        if (imageButton5 != null && imageButton5.getParent() != null) {
            removeView(this.mSelelctButtonView);
        }
        getTabBar();
        ActionMenuView actionMenuView3 = this.mMenuView;
        if (actionMenuView3 != null && actionMenuView3.getParent() != null) {
            this.mMenuView.setVisibility(0);
        }
        View view5 = this.mMenuDividerView;
        if (view5 != null && view5.getParent() != null) {
            this.mMenuDividerView.setVisibility(8);
        }
        ImageButton imageButton6 = this.mCollapseButtonView;
        if (imageButton6 != null && imageButton6.getParent() != null) {
            this.mCollapseButtonView.setVisibility(0);
        }
        View view6 = this.mExpandedActionView;
        if (view6 == null || view6.getParent() == null) {
            return;
        }
        this.mExpandedActionView.setVisibility(0);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mTabBar.setEnabled(z);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setPopupTheme(int i2) {
        if (this.mPopupTheme != i2) {
            this.mPopupTheme = i2;
            if (i2 == 0) {
                this.mPopupContext = ((ViewGroup) this).mContext;
            } else {
                this.mPopupContext = new ContextThemeWrapper(((ViewGroup) this).mContext, i2);
            }
        }
    }

    public void setTextAllCaps(boolean z) {
        this.mTextAllCaps = z;
        PagerSecond pagerSecond = this.mTabBar;
        if (pagerSecond != null) {
            pagerSecond.setTextAllCaps(z);
        }
    }

    public void setTitle(String str) {
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public boolean t() {
        return this.mTextAllCaps;
    }

    void z() {
        PagerSecond pagerSecond = this.mTabBar;
        if (pagerSecond != null) {
            pagerSecond.setVisibility(8);
        }
    }

    public TabButtonBar(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, R.style.Widget_TabButtonBar);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.zte.mifavor.widget.TabButtonBar.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        public int f17785c;

        /* renamed from: h, reason: collision with root package name */
        public boolean f17786h;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f17785c = parcel.readInt();
            this.f17786h = parcel.readInt() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f17785c);
            parcel.writeInt(this.f17786h ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TabButtonBar(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTempViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mHiddenViews = new ArrayList<>();
        this.mShowOverflowMenuRunnable = new Runnable() { // from class: com.zte.mifavor.widget.TabButtonBar.1
            @Override // java.lang.Runnable
            public void run() {
                TabButtonBar.this.E();
            }
        };
        this.mActionFlag = 0;
        this.mActionColor = 0;
        this.mCurrentMode = 0;
        this.mMaxButtonHeight = -1;
        this.mGravity = 8388627;
        this.mTextAllCaps = false;
        this.mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() { // from class: com.zte.mifavor.widget.TabButtonBar.2
            @Override // android.widget.ActionMenuView.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (TabButtonBar.this.mOnMenuItemClickListener != null) {
                    return TabButtonBar.this.mOnMenuItemClickListener.onMenuItemClick(menuItem);
                }
                return false;
            }
        };
        Log.d(TAG, "===========construct");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabButtonBar, i2, i3);
        this.mCollapseIcon = obtainStyledAttributes.getDrawable(R.styleable.TabButtonBar_android_collapseIcon);
        this.mNavButtonStyle = obtainStyledAttributes.getResourceId(R.styleable.TabButtonBar_mfvNavigationButtonStyle, 0);
        this.mTextAllCaps = obtainStyledAttributes.getBoolean(R.styleable.TabButtonBar_android_textAllCaps, this.mTextAllCaps);
        obtainStyledAttributes.recycle();
        setActionMenuColor(getResources().getColor(R.color.mfv_common_acb_icon));
        setBackgroundResource(R.drawable.acb_bg);
        setElevation(getResources().getDimensionPixelSize(R.dimen.actionbar_elevation));
        if (context instanceof MenuRigister) {
            Log.d(TAG, "MenuRigister");
            ((MenuRigister) context).a(this);
        }
    }
}
