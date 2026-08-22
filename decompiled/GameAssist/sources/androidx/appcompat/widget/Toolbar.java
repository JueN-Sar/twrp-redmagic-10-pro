package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.ColorInt;
import androidx.annotation.DoNotInline;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class Toolbar extends ViewGroup implements MenuHost {
    private static final String TAG = "Toolbar";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private OnBackInvokedCallback mBackInvokedCallback;
    private boolean mBackInvokedCallbackEnabled;
    private OnBackInvokedDispatcher mBackInvokedDispatcher;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    MenuBuilder.Callback mMenuBuilderCallback;
    final MenuHostHelper mMenuHostHelper;
    ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private ArrayList<MenuItem> mProvidedMenuItems;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    @RequiresApi
    static class Api33Impl {
        @Nullable
        @DoNotInline
        static OnBackInvokedDispatcher a(@NonNull View view) {
            return view.findOnBackInvokedDispatcher();
        }

        @NonNull
        @DoNotInline
        static OnBackInvokedCallback b(@NonNull final Runnable runnable) {
            Objects.requireNonNull(runnable);
            return new OnBackInvokedCallback() { // from class: androidx.appcompat.widget.c
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    runnable.run();
                }
            };
        }

        @DoNotInline
        static void c(@NonNull Object obj, @NonNull Object obj2) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) obj2);
        }

        @DoNotInline
        static void d(@NonNull Object obj, @NonNull Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    private class ExpandedActionViewMenuPresenter implements MenuPresenter {

        /* renamed from: c, reason: collision with root package name */
        MenuBuilder f1027c;

        /* renamed from: h, reason: collision with root package name */
        MenuItemImpl f1028h;

        ExpandedActionViewMenuPresenter() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void a(MenuBuilder menuBuilder, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.mCollapseButtonView);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = menuItemImpl.getActionView();
            this.f1028h = menuItemImpl;
            ViewParent parent2 = Toolbar.this.mExpandedActionView.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.mExpandedActionView);
                }
                LayoutParams generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.f143a = (toolbar4.mButtonGravity & 112) | 8388611;
                generateDefaultLayoutParams.f1049b = 2;
                toolbar4.mExpandedActionView.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.mExpandedActionView);
            }
            Toolbar.this.K();
            Toolbar.this.requestLayout();
            menuItemImpl.q(true);
            KeyEvent.Callback callback = Toolbar.this.mExpandedActionView;
            if (callback instanceof CollapsibleActionView) {
                ((CollapsibleActionView) callback).onActionViewExpanded();
            }
            Toolbar.this.T();
            return true;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean d(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            KeyEvent.Callback callback = Toolbar.this.mExpandedActionView;
            if (callback instanceof CollapsibleActionView) {
                ((CollapsibleActionView) callback).onActionViewCollapsed();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.mExpandedActionView);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.mCollapseButtonView);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.mExpandedActionView = null;
            toolbar3.a();
            this.f1028h = null;
            Toolbar.this.requestLayout();
            menuItemImpl.q(false);
            Toolbar.this.T();
            return true;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void f(Context context, MenuBuilder menuBuilder) {
            MenuItemImpl menuItemImpl;
            MenuBuilder menuBuilder2 = this.f1027c;
            if (menuBuilder2 != null && (menuItemImpl = this.f1028h) != null) {
                menuBuilder2.f(menuItemImpl);
            }
            this.f1027c = menuBuilder;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean flagActionItems() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public int getId() {
            return 0;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public Parcelable onSaveInstanceState() {
            return null;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void updateMenuView(boolean z) {
            if (this.f1028h != null) {
                MenuBuilder menuBuilder = this.f1027c;
                if (menuBuilder != null) {
                    int size = menuBuilder.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (this.f1027c.getItem(i2) == this.f1028h) {
                            return;
                        }
                    }
                }
                e(this.f1027c, this.f1028h);
            }
        }
    }

    @RequiresApi
    @RestrictTo
    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<Toolbar> {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1030a;

        /* renamed from: b, reason: collision with root package name */
        private int f1031b;

        /* renamed from: c, reason: collision with root package name */
        private int f1032c;

        /* renamed from: d, reason: collision with root package name */
        private int f1033d;

        /* renamed from: e, reason: collision with root package name */
        private int f1034e;

        /* renamed from: f, reason: collision with root package name */
        private int f1035f;

        /* renamed from: g, reason: collision with root package name */
        private int f1036g;

        /* renamed from: h, reason: collision with root package name */
        private int f1037h;

        /* renamed from: i, reason: collision with root package name */
        private int f1038i;

        /* renamed from: j, reason: collision with root package name */
        private int f1039j;

        /* renamed from: k, reason: collision with root package name */
        private int f1040k;

        /* renamed from: l, reason: collision with root package name */
        private int f1041l;

        /* renamed from: m, reason: collision with root package name */
        private int f1042m;

        /* renamed from: n, reason: collision with root package name */
        private int f1043n;

        /* renamed from: o, reason: collision with root package name */
        private int f1044o;

        /* renamed from: p, reason: collision with root package name */
        private int f1045p;

        /* renamed from: q, reason: collision with root package name */
        private int f1046q;

        /* renamed from: r, reason: collision with root package name */
        private int f1047r;

        /* renamed from: s, reason: collision with root package name */
        private int f1048s;
        private int t;
        private int u;

        @Override // android.view.inspector.InspectionCompanion
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void readProperties(Toolbar toolbar, PropertyReader propertyReader) {
            if (!this.f1030a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.f1031b, toolbar.getCollapseContentDescription());
            propertyReader.readObject(this.f1032c, toolbar.getCollapseIcon());
            propertyReader.readInt(this.f1033d, toolbar.getContentInsetEnd());
            propertyReader.readInt(this.f1034e, toolbar.getContentInsetEndWithActions());
            propertyReader.readInt(this.f1035f, toolbar.getContentInsetLeft());
            propertyReader.readInt(this.f1036g, toolbar.getContentInsetRight());
            propertyReader.readInt(this.f1037h, toolbar.getContentInsetStart());
            propertyReader.readInt(this.f1038i, toolbar.getContentInsetStartWithNavigation());
            propertyReader.readObject(this.f1039j, toolbar.getLogo());
            propertyReader.readObject(this.f1040k, toolbar.getLogoDescription());
            propertyReader.readObject(this.f1041l, toolbar.getMenu());
            propertyReader.readObject(this.f1042m, toolbar.getNavigationContentDescription());
            propertyReader.readObject(this.f1043n, toolbar.getNavigationIcon());
            propertyReader.readResourceId(this.f1044o, toolbar.getPopupTheme());
            propertyReader.readObject(this.f1045p, toolbar.getSubtitle());
            propertyReader.readObject(this.f1046q, toolbar.getTitle());
            propertyReader.readInt(this.f1047r, toolbar.getTitleMarginBottom());
            propertyReader.readInt(this.f1048s, toolbar.getTitleMarginEnd());
            propertyReader.readInt(this.t, toolbar.getTitleMarginStart());
            propertyReader.readInt(this.u, toolbar.getTitleMarginTop());
        }

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.f1031b = propertyMapper.mapObject("collapseContentDescription", R.attr.collapseContentDescription);
            this.f1032c = propertyMapper.mapObject("collapseIcon", R.attr.collapseIcon);
            this.f1033d = propertyMapper.mapInt("contentInsetEnd", R.attr.contentInsetEnd);
            this.f1034e = propertyMapper.mapInt("contentInsetEndWithActions", R.attr.contentInsetEndWithActions);
            this.f1035f = propertyMapper.mapInt("contentInsetLeft", R.attr.contentInsetLeft);
            this.f1036g = propertyMapper.mapInt("contentInsetRight", R.attr.contentInsetRight);
            this.f1037h = propertyMapper.mapInt("contentInsetStart", R.attr.contentInsetStart);
            this.f1038i = propertyMapper.mapInt("contentInsetStartWithNavigation", R.attr.contentInsetStartWithNavigation);
            this.f1039j = propertyMapper.mapObject("logo", R.attr.logo);
            this.f1040k = propertyMapper.mapObject("logoDescription", R.attr.logoDescription);
            this.f1041l = propertyMapper.mapObject("menu", R.attr.menu);
            this.f1042m = propertyMapper.mapObject("navigationContentDescription", R.attr.navigationContentDescription);
            this.f1043n = propertyMapper.mapObject("navigationIcon", R.attr.navigationIcon);
            this.f1044o = propertyMapper.mapResourceId("popupTheme", R.attr.popupTheme);
            this.f1045p = propertyMapper.mapObject("subtitle", R.attr.subtitle);
            this.f1046q = propertyMapper.mapObject("title", R.attr.title);
            this.f1047r = propertyMapper.mapInt("titleMarginBottom", R.attr.titleMarginBottom);
            this.f1048s = propertyMapper.mapInt("titleMarginEnd", R.attr.titleMarginEnd);
            this.t = propertyMapper.mapInt("titleMarginStart", R.attr.titleMarginStart);
            this.u = propertyMapper.mapInt("titleMarginTop", R.attr.titleMarginTop);
            this.f1030a = true;
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    private boolean B(View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }

    private int E(View view, int i2, int[] iArr, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin - iArr[0];
        int max = i2 + Math.max(0, i4);
        iArr[0] = Math.max(0, -i4);
        int r2 = r(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, r2, max + measuredWidth, view.getMeasuredHeight() + r2);
        return max + measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    private int F(View view, int i2, int[] iArr, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin - iArr[1];
        int max = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int r2 = r(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, r2, max, view.getMeasuredHeight() + r2);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
    }

    private int G(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i7);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + max + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private void H(View view, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i6 >= 0) {
            if (mode != 0) {
                i6 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i6);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i6, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void I() {
        Menu menu = getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        this.mMenuHostHelper.b(menu, getMenuInflater());
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.mProvidedMenuItems = currentMenuItems2;
    }

    private void J() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    private boolean Q() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (R(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean R(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private void b(List list, int i2) {
        boolean z = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int b2 = GravityCompat.b(i2, getLayoutDirection());
        list.clear();
        if (!z) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f1049b == 0 && R(childAt) && q(layoutParams.f143a) == b2) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.f1049b == 0 && R(childAt2) && q(layoutParams2.f143a) == b2) {
                list.add(childAt2);
            }
        }
    }

    private void c(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        LayoutParams generateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (LayoutParams) layoutParams;
        generateDefaultLayoutParams.f1049b = 1;
        if (!z || this.mExpandedActionView == null) {
            addView(view, generateDefaultLayoutParams);
        } else {
            view.setLayoutParams(generateDefaultLayoutParams);
            this.mHiddenViews.add(view);
        }
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i2 = 0; i2 < menu.size(); i2++) {
            arrayList.add(menu.getItem(i2));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    private void h() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }

    private void i() {
        if (this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext());
        }
    }

    private void j() {
        k();
        if (this.mMenuView.M() == null) {
            MenuBuilder menuBuilder = (MenuBuilder) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menuBuilder.c(this.mExpandedMenuPresenter, this.mPopupContext);
            T();
        }
    }

    private void k() {
        if (this.mMenuView == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.mMenuView = actionMenuView;
            actionMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.N(this.mActionMenuPresenterCallback, new MenuBuilder.Callback() { // from class: androidx.appcompat.widget.Toolbar.3
                @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
                public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
                    MenuBuilder.Callback callback = Toolbar.this.mMenuBuilderCallback;
                    return callback != null && callback.a(menuBuilder, menuItem);
                }

                @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
                public void b(MenuBuilder menuBuilder) {
                    if (!Toolbar.this.mMenuView.I()) {
                        Toolbar.this.mMenuHostHelper.e(menuBuilder);
                    }
                    MenuBuilder.Callback callback = Toolbar.this.mMenuBuilderCallback;
                    if (callback != null) {
                        callback.b(menuBuilder);
                    }
                }
            });
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f143a = (this.mButtonGravity & 112) | 8388613;
            this.mMenuView.setLayoutParams(generateDefaultLayoutParams);
            c(this.mMenuView, false);
        }
    }

    private void l() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f143a = (this.mButtonGravity & 112) | 8388611;
            this.mNavButtonView.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    private int q(int i2) {
        int layoutDirection = getLayoutDirection();
        int b2 = GravityCompat.b(i2, layoutDirection) & 7;
        return (b2 == 1 || b2 == 3 || b2 == 5) ? b2 : layoutDirection == 1 ? 5 : 3;
    }

    private int r(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int s2 = s(layoutParams.f143a);
        if (s2 == 48) {
            return getPaddingTop() - i3;
        }
        if (s2 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        if (i4 < i5) {
            i4 = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            if (i6 < i7) {
                i4 = Math.max(0, i4 - (i7 - i6));
            }
        }
        return paddingTop + i4;
    }

    private int s(int i2) {
        int i3 = i2 & 112;
        return (i3 == 16 || i3 == 48 || i3 == 80) ? i3 : this.mGravity & 112;
    }

    private int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
    }

    private int u(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int v(List list, int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int size = list.size();
        int i4 = 0;
        int i5 = 0;
        while (i4 < size) {
            View view = (View) list.get(i4);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin - i2;
            int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin - i3;
            int max = Math.max(0, i6);
            int max2 = Math.max(0, i7);
            int max3 = Math.max(0, -i6);
            int max4 = Math.max(0, -i7);
            i5 += max + view.getMeasuredWidth() + max2;
            i4++;
            i3 = max4;
            i2 = max3;
        }
        return i5;
    }

    public void A() {
        Iterator<MenuItem> it = this.mProvidedMenuItems.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(it.next().getItemId());
        }
        I();
    }

    public boolean C() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.H();
    }

    public boolean D() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.I();
    }

    void K() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((LayoutParams) childAt.getLayoutParams()).f1049b != 2 && childAt != this.mMenuView) {
                removeViewAt(childCount);
                this.mHiddenViews.add(childAt);
            }
        }
    }

    public void L(int i2, int i3) {
        h();
        this.mContentInsets.g(i2, i3);
    }

    public void M(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder == null && this.mMenuView == null) {
            return;
        }
        k();
        MenuBuilder M = this.mMenuView.M();
        if (M == menuBuilder) {
            return;
        }
        if (M != null) {
            M.R(this.mOuterActionMenuPresenter);
            M.R(this.mExpandedMenuPresenter);
        }
        if (this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
        }
        actionMenuPresenter.E(true);
        if (menuBuilder != null) {
            menuBuilder.c(actionMenuPresenter, this.mPopupContext);
            menuBuilder.c(this.mExpandedMenuPresenter, this.mPopupContext);
        } else {
            actionMenuPresenter.f(this.mPopupContext, null);
            this.mExpandedMenuPresenter.f(this.mPopupContext, null);
            actionMenuPresenter.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(actionMenuPresenter);
        this.mOuterActionMenuPresenter = actionMenuPresenter;
        T();
    }

    public void N(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.N(callback, callback2);
        }
    }

    public void O(Context context, int i2) {
        this.mSubtitleTextAppearance = i2;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void P(Context context, int i2) {
        this.mTitleTextAppearance = i2;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public boolean S() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.O();
    }

    void T() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        OnBackInvokedDispatcher a2 = Api33Impl.a(this);
        boolean z = x() && a2 != null && isAttachedToWindow() && this.mBackInvokedCallbackEnabled;
        if (z && this.mBackInvokedDispatcher == null) {
            if (this.mBackInvokedCallback == null) {
                this.mBackInvokedCallback = Api33Impl.b(new Runnable() { // from class: androidx.appcompat.widget.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        Toolbar.this.e();
                    }
                });
            }
            Api33Impl.c(a2, this.mBackInvokedCallback);
            this.mBackInvokedDispatcher = a2;
            return;
        }
        if (z || (onBackInvokedDispatcher = this.mBackInvokedDispatcher) == null) {
            return;
        }
        Api33Impl.d(onBackInvokedDispatcher, this.mBackInvokedCallback);
        this.mBackInvokedDispatcher = null;
    }

    void a() {
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            addView(this.mHiddenViews.get(size));
        }
        this.mHiddenViews.clear();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.mMenuView) != null && actionMenuView.J();
    }

    public void e() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        MenuItemImpl menuItemImpl = expandedActionViewMenuPresenter == null ? null : expandedActionViewMenuPresenter.f1028h;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.A();
        }
    }

    void g() {
        if (this.mCollapseButtonView == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f143a = (this.mButtonGravity & 112) | 8388611;
            generateDefaultLayoutParams.f1049b = 2;
            this.mCollapseButtonView.setLayoutParams(generateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new View.OnClickListener() { // from class: androidx.appcompat.widget.Toolbar.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Toolbar.this.e();
                }
            });
        }
    }

    @Nullable
    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    @Nullable
    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.mContentInsetEndWithActions;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.mContentInsetStartWithNavigation;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        MenuBuilder M;
        ActionMenuView actionMenuView = this.mMenuView;
        return (actionMenuView == null || (M = actionMenuView.M()) == null || !M.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
    }

    public int getCurrentContentInsetLeft() {
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.mMenuView.getMenu();
    }

    @Nullable
    @VisibleForTesting
    View getNavButtonView() {
        return this.mNavButtonView;
    }

    @Nullable
    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    @Nullable
    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    @Nullable
    public Drawable getOverflowIcon() {
        j();
        return this.mMenuView.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.mPopupContext;
    }

    @StyleRes
    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    @Nullable
    @VisibleForTesting
    final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    @Nullable
    @VisibleForTesting
    final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    @RestrictTo
    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.core.view.MenuHost
    public void o(MenuProvider menuProvider) {
        this.mMenuHostHelper.f(menuProvider);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        T();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
        T();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02a1 A[LOOP:0: B:41:0x029f->B:42:0x02a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02c3 A[LOOP:1: B:45:0x02c1->B:46:0x02c3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02fc A[LOOP:2: B:54:0x02fa->B:55:0x02fc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0227  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 785
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int[] iArr = this.mTempMargins;
        boolean b2 = ViewUtils.b(this);
        int i11 = !b2 ? 1 : 0;
        if (R(this.mNavButtonView)) {
            H(this.mNavButtonView, i2, 0, i3, 0, this.mMaxButtonHeight);
            i4 = this.mNavButtonView.getMeasuredWidth() + t(this.mNavButtonView);
            i5 = Math.max(0, this.mNavButtonView.getMeasuredHeight() + u(this.mNavButtonView));
            i6 = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        if (R(this.mCollapseButtonView)) {
            H(this.mCollapseButtonView, i2, 0, i3, 0, this.mMaxButtonHeight);
            i4 = this.mCollapseButtonView.getMeasuredWidth() + t(this.mCollapseButtonView);
            i5 = Math.max(i5, this.mCollapseButtonView.getMeasuredHeight() + u(this.mCollapseButtonView));
            i6 = View.combineMeasuredStates(i6, this.mCollapseButtonView.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = Math.max(currentContentInsetStart, i4);
        iArr[b2 ? 1 : 0] = Math.max(0, currentContentInsetStart - i4);
        if (R(this.mMenuView)) {
            H(this.mMenuView, i2, max, i3, 0, this.mMaxButtonHeight);
            i7 = this.mMenuView.getMeasuredWidth() + t(this.mMenuView);
            i5 = Math.max(i5, this.mMenuView.getMeasuredHeight() + u(this.mMenuView));
            i6 = View.combineMeasuredStates(i6, this.mMenuView.getMeasuredState());
        } else {
            i7 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i7);
        iArr[i11] = Math.max(0, currentContentInsetEnd - i7);
        if (R(this.mExpandedActionView)) {
            max2 += G(this.mExpandedActionView, i2, max2, i3, 0, iArr);
            i5 = Math.max(i5, this.mExpandedActionView.getMeasuredHeight() + u(this.mExpandedActionView));
            i6 = View.combineMeasuredStates(i6, this.mExpandedActionView.getMeasuredState());
        }
        if (R(this.mLogoView)) {
            max2 += G(this.mLogoView, i2, max2, i3, 0, iArr);
            i5 = Math.max(i5, this.mLogoView.getMeasuredHeight() + u(this.mLogoView));
            i6 = View.combineMeasuredStates(i6, this.mLogoView.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((LayoutParams) childAt.getLayoutParams()).f1049b == 0 && R(childAt)) {
                max2 += G(childAt, i2, max2, i3, 0, iArr);
                i5 = Math.max(i5, childAt.getMeasuredHeight() + u(childAt));
                i6 = View.combineMeasuredStates(i6, childAt.getMeasuredState());
            }
        }
        int i13 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int i14 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (R(this.mTitleTextView)) {
            G(this.mTitleTextView, i2, max2 + i14, i3, i13, iArr);
            int measuredWidth = this.mTitleTextView.getMeasuredWidth() + t(this.mTitleTextView);
            i8 = this.mTitleTextView.getMeasuredHeight() + u(this.mTitleTextView);
            i9 = View.combineMeasuredStates(i6, this.mTitleTextView.getMeasuredState());
            i10 = measuredWidth;
        } else {
            i8 = 0;
            i9 = i6;
            i10 = 0;
        }
        if (R(this.mSubtitleTextView)) {
            i10 = Math.max(i10, G(this.mSubtitleTextView, i2, max2 + i14, i3, i8 + i13, iArr));
            i8 += this.mSubtitleTextView.getMeasuredHeight() + u(this.mSubtitleTextView);
            i9 = View.combineMeasuredStates(i9, this.mSubtitleTextView.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max2 + i10 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, (-16777216) & i9), Q() ? 0 : View.resolveSizeAndState(Math.max(Math.max(i5, i8) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, i9 << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.mMenuView;
        MenuBuilder M = actionMenuView != null ? actionMenuView.M() : null;
        int i2 = savedState.f1050i;
        if (i2 != 0 && this.mExpandedMenuPresenter != null && M != null && (findItem = M.findItem(i2)) != null) {
            findItem.expandActionView();
        }
        if (savedState.f1051j) {
            J();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        h();
        this.mContentInsets.f(i2 == 1);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        MenuItemImpl menuItemImpl;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        if (expandedActionViewMenuPresenter != null && (menuItemImpl = expandedActionViewMenuPresenter.f1028h) != null) {
            savedState.f1050i = menuItemImpl.getItemId();
        }
        savedState.f1051j = D();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ActionBar.LayoutParams ? new LayoutParams((ActionBar.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if (this.mBackInvokedCallbackEnabled != z) {
            this.mBackInvokedCallbackEnabled = z;
            T();
        }
    }

    public void setCollapseContentDescription(@StringRes int i2) {
        setCollapseContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setCollapseIcon(@DrawableRes int i2) {
        setCollapseIcon(AppCompatResources.b(getContext(), i2));
    }

    @RestrictTo
    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(@DrawableRes int i2) {
        setLogo(AppCompatResources.b(getContext(), i2));
    }

    public void setLogoDescription(@StringRes int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setNavigationContentDescription(@StringRes int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(@DrawableRes int i2) {
        setNavigationIcon(AppCompatResources.b(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        j();
        this.mMenuView.setOverflowIcon(drawable);
    }

    public void setPopupTheme(@StyleRes int i2) {
        if (this.mPopupTheme != i2) {
            this.mPopupTheme = i2;
            if (i2 == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(@StringRes int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextColor(@ColorInt int i2) {
        setSubtitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setTitle(@StringRes int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleMarginBottom(int i2) {
        this.mTitleMarginBottom = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.mTitleMarginEnd = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.mTitleMarginStart = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.mTitleMarginTop = i2;
        requestLayout();
    }

    public void setTitleTextColor(@ColorInt int i2) {
        setTitleTextColor(ColorStateList.valueOf(i2));
    }

    @Override // androidx.core.view.MenuHost
    public void w(MenuProvider menuProvider) {
        this.mMenuHostHelper.a(menuProvider);
    }

    public boolean x() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        return (expandedActionViewMenuPresenter == null || expandedActionViewMenuPresenter.f1028h == null) ? false : true;
    }

    public boolean y() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.G();
    }

    public void z(int i2) {
        getMenuInflater().inflate(i2, getMenu());
    }

    public static class LayoutParams extends ActionBar.LayoutParams {

        /* renamed from: b, reason: collision with root package name */
        int f1049b;

        public LayoutParams(@NonNull Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1049b = 0;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f1049b = 0;
            this.f143a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ActionBar.LayoutParams) layoutParams);
            this.f1049b = 0;
            this.f1049b = layoutParams.f1049b;
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1049b = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1049b = 0;
            a(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1049b = 0;
        }
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<>();
        this.mHiddenViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mMenuHostHelper = new MenuHostHelper(new Runnable() { // from class: androidx.appcompat.widget.b
            @Override // java.lang.Runnable
            public final void run() {
                Toolbar.this.A();
            }
        });
        this.mProvidedMenuItems = new ArrayList<>();
        this.mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() { // from class: androidx.appcompat.widget.Toolbar.1
            @Override // androidx.appcompat.widget.ActionMenuView.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (Toolbar.this.mMenuHostHelper.d(menuItem)) {
                    return true;
                }
                OnMenuItemClickListener onMenuItemClickListener = Toolbar.this.mOnMenuItemClickListener;
                if (onMenuItemClickListener != null) {
                    return onMenuItemClickListener.onMenuItemClick(menuItem);
                }
                return false;
            }
        };
        this.mShowOverflowMenuRunnable = new Runnable() { // from class: androidx.appcompat.widget.Toolbar.2
            @Override // java.lang.Runnable
            public void run() {
                Toolbar.this.S();
            }
        };
        TintTypedArray v = TintTypedArray.v(getContext(), attributeSet, R.styleable.Toolbar, i2, 0);
        ViewCompat.g0(this, context, R.styleable.Toolbar, attributeSet, v.r(), i2, 0);
        this.mTitleTextAppearance = v.n(R.styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = v.n(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = v.l(R.styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = v.l(R.styleable.Toolbar_buttonGravity, 48);
        int e2 = v.e(R.styleable.Toolbar_titleMargin, 0);
        e2 = v.s(R.styleable.Toolbar_titleMargins) ? v.e(R.styleable.Toolbar_titleMargins, e2) : e2;
        this.mTitleMarginBottom = e2;
        this.mTitleMarginTop = e2;
        this.mTitleMarginEnd = e2;
        this.mTitleMarginStart = e2;
        int e3 = v.e(R.styleable.Toolbar_titleMarginStart, -1);
        if (e3 >= 0) {
            this.mTitleMarginStart = e3;
        }
        int e4 = v.e(R.styleable.Toolbar_titleMarginEnd, -1);
        if (e4 >= 0) {
            this.mTitleMarginEnd = e4;
        }
        int e5 = v.e(R.styleable.Toolbar_titleMarginTop, -1);
        if (e5 >= 0) {
            this.mTitleMarginTop = e5;
        }
        int e6 = v.e(R.styleable.Toolbar_titleMarginBottom, -1);
        if (e6 >= 0) {
            this.mTitleMarginBottom = e6;
        }
        this.mMaxButtonHeight = v.f(R.styleable.Toolbar_maxButtonHeight, -1);
        int e7 = v.e(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int e8 = v.e(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int f2 = v.f(R.styleable.Toolbar_contentInsetLeft, 0);
        int f3 = v.f(R.styleable.Toolbar_contentInsetRight, 0);
        h();
        this.mContentInsets.e(f2, f3);
        if (e7 != Integer.MIN_VALUE || e8 != Integer.MIN_VALUE) {
            this.mContentInsets.g(e7, e8);
        }
        this.mContentInsetStartWithNavigation = v.e(R.styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = v.e(R.styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = v.g(R.styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = v.p(R.styleable.Toolbar_collapseContentDescription);
        CharSequence p2 = v.p(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(p2)) {
            setTitle(p2);
        }
        CharSequence p3 = v.p(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(p3)) {
            setSubtitle(p3);
        }
        this.mPopupContext = getContext();
        setPopupTheme(v.n(R.styleable.Toolbar_popupTheme, 0));
        Drawable g2 = v.g(R.styleable.Toolbar_navigationIcon);
        if (g2 != null) {
            setNavigationIcon(g2);
        }
        CharSequence p4 = v.p(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(p4)) {
            setNavigationContentDescription(p4);
        }
        Drawable g3 = v.g(R.styleable.Toolbar_logo);
        if (g3 != null) {
            setLogo(g3);
        }
        CharSequence p5 = v.p(R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(p5)) {
            setLogoDescription(p5);
        }
        if (v.s(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(v.c(R.styleable.Toolbar_titleTextColor));
        }
        if (v.s(R.styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(v.c(R.styleable.Toolbar_subtitleTextColor));
        }
        if (v.s(R.styleable.Toolbar_menu)) {
            z(v.n(R.styleable.Toolbar_menu, 0));
        }
        v.x();
    }

    public void setCollapseContentDescription(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(@Nullable Drawable drawable) {
        if (drawable != null) {
            g();
            this.mCollapseButtonView.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.mCollapseButtonView;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.mCollapseIcon);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!B(this.mLogoView)) {
                c(this.mLogoView, true);
            }
        } else {
            ImageView imageView = this.mLogoView;
            if (imageView != null && B(imageView)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView imageView2 = this.mLogoView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
            TooltipCompat.a(this.mNavButtonView, charSequence);
        }
    }

    public void setNavigationIcon(@Nullable Drawable drawable) {
        if (drawable != null) {
            l();
            if (!B(this.mNavButtonView)) {
                c(this.mNavButtonView, true);
            }
        } else {
            ImageButton imageButton = this.mNavButtonView;
            if (imageButton != null && B(imageButton)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton imageButton2 = this.mNavButtonView;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mSubtitleTextView;
            if (textView != null && B(textView)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        } else {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.mSubtitleTextView = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.mSubtitleTextAppearance;
                if (i2 != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.mSubtitleTextColor;
                if (colorStateList != null) {
                    this.mSubtitleTextView.setTextColor(colorStateList);
                }
            }
            if (!B(this.mSubtitleTextView)) {
                c(this.mSubtitleTextView, true);
            }
        }
        TextView textView2 = this.mSubtitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextColor(@NonNull ColorStateList colorStateList) {
        this.mSubtitleTextColor = colorStateList;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mTitleTextView;
            if (textView != null && B(textView)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        } else {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.mTitleTextView = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.mTitleTextAppearance;
                if (i2 != 0) {
                    this.mTitleTextView.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.mTitleTextColor;
                if (colorStateList != null) {
                    this.mTitleTextView.setTextColor(colorStateList);
                }
            }
            if (!B(this.mTitleTextView)) {
                c(this.mTitleTextView, true);
            }
        }
        TextView textView2 = this.mTitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleTextColor(@NonNull ColorStateList colorStateList) {
        this.mTitleTextColor = colorStateList;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.appcompat.widget.Toolbar.SavedState.1
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
        int f1050i;

        /* renamed from: j, reason: collision with root package name */
        boolean f1051j;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1050i = parcel.readInt();
            this.f1051j = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1050i);
            parcel.writeInt(this.f1051j ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
