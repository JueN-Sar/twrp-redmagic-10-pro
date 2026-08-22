package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.google.android.material.R;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class NavigationMenuPresenter implements MenuPresenter {
    int A;
    int B;
    int C;
    boolean D;
    private int F;
    private int G;
    int H;

    /* renamed from: c, reason: collision with root package name */
    private NavigationMenuView f14728c;

    /* renamed from: h, reason: collision with root package name */
    LinearLayout f14729h;

    /* renamed from: i, reason: collision with root package name */
    private MenuPresenter.Callback f14730i;

    /* renamed from: j, reason: collision with root package name */
    MenuBuilder f14731j;

    /* renamed from: k, reason: collision with root package name */
    private int f14732k;

    /* renamed from: l, reason: collision with root package name */
    NavigationMenuAdapter f14733l;

    /* renamed from: m, reason: collision with root package name */
    LayoutInflater f14734m;

    /* renamed from: o, reason: collision with root package name */
    ColorStateList f14736o;

    /* renamed from: r, reason: collision with root package name */
    ColorStateList f14739r;

    /* renamed from: s, reason: collision with root package name */
    ColorStateList f14740s;
    Drawable t;
    RippleDrawable u;
    int v;
    int w;
    int x;
    int y;
    int z;

    /* renamed from: n, reason: collision with root package name */
    int f14735n = 0;

    /* renamed from: p, reason: collision with root package name */
    int f14737p = 0;

    /* renamed from: q, reason: collision with root package name */
    boolean f14738q = true;
    boolean E = true;
    private int I = -1;
    final View.OnClickListener J = new View.OnClickListener() { // from class: com.google.android.material.internal.NavigationMenuPresenter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = true;
            NavigationMenuPresenter.this.U(true);
            MenuItemImpl itemData = ((NavigationMenuItemView) view).getItemData();
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            boolean P = navigationMenuPresenter.f14731j.P(itemData, navigationMenuPresenter, 0);
            if (itemData != null && itemData.isCheckable() && P) {
                NavigationMenuPresenter.this.f14733l.X(itemData);
            } else {
                z = false;
            }
            NavigationMenuPresenter.this.U(false);
            if (z) {
                NavigationMenuPresenter.this.updateMenuView(false);
            }
        }
    };

    private static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    private class NavigationMenuAdapter extends RecyclerView.Adapter<ViewHolder> {

        /* renamed from: c, reason: collision with root package name */
        private final ArrayList f14742c = new ArrayList();

        /* renamed from: d, reason: collision with root package name */
        private MenuItemImpl f14743d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f14744e;

        NavigationMenuAdapter() {
            U();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int M(int i2) {
            int i3 = i2;
            for (int i4 = 0; i4 < i2; i4++) {
                if (NavigationMenuPresenter.this.f14733l.o(i4) == 2 || NavigationMenuPresenter.this.f14733l.o(i4) == 3) {
                    i3--;
                }
            }
            return i3;
        }

        private void N(int i2, int i3) {
            while (i2 < i3) {
                ((NavigationMenuTextItem) this.f14742c.get(i2)).f14752b = true;
                i2++;
            }
        }

        private void U() {
            if (this.f14744e) {
                return;
            }
            boolean z = true;
            this.f14744e = true;
            this.f14742c.clear();
            this.f14742c.add(new NavigationMenuHeaderItem());
            int size = NavigationMenuPresenter.this.f14731j.G().size();
            int i2 = -1;
            int i3 = 0;
            boolean z2 = false;
            int i4 = 0;
            while (i3 < size) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) NavigationMenuPresenter.this.f14731j.G().get(i3);
                if (menuItemImpl.isChecked()) {
                    X(menuItemImpl);
                }
                if (menuItemImpl.isCheckable()) {
                    menuItemImpl.s(false);
                }
                if (menuItemImpl.hasSubMenu()) {
                    SubMenu subMenu = menuItemImpl.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i3 != 0) {
                            this.f14742c.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.H, 0));
                        }
                        this.f14742c.add(new NavigationMenuTextItem(menuItemImpl));
                        int size2 = this.f14742c.size();
                        int size3 = subMenu.size();
                        int i5 = 0;
                        boolean z3 = false;
                        while (i5 < size3) {
                            MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenu.getItem(i5);
                            if (menuItemImpl2.isVisible()) {
                                if (!z3 && menuItemImpl2.getIcon() != null) {
                                    z3 = z;
                                }
                                if (menuItemImpl2.isCheckable()) {
                                    menuItemImpl2.s(false);
                                }
                                if (menuItemImpl.isChecked()) {
                                    X(menuItemImpl);
                                }
                                this.f14742c.add(new NavigationMenuTextItem(menuItemImpl2));
                            }
                            i5++;
                            z = true;
                        }
                        if (z3) {
                            N(size2, this.f14742c.size());
                        }
                    }
                } else {
                    int groupId = menuItemImpl.getGroupId();
                    if (groupId != i2) {
                        i4 = this.f14742c.size();
                        z2 = menuItemImpl.getIcon() != null;
                        if (i3 != 0) {
                            i4++;
                            ArrayList arrayList = this.f14742c;
                            int i6 = NavigationMenuPresenter.this.H;
                            arrayList.add(new NavigationMenuSeparatorItem(i6, i6));
                        }
                    } else if (!z2 && menuItemImpl.getIcon() != null) {
                        N(i4, this.f14742c.size());
                        z2 = true;
                    }
                    NavigationMenuTextItem navigationMenuTextItem = new NavigationMenuTextItem(menuItemImpl);
                    navigationMenuTextItem.f14752b = z2;
                    this.f14742c.add(navigationMenuTextItem);
                    i2 = groupId;
                }
                i3++;
                z = true;
            }
            this.f14744e = false;
        }

        private void W(View view, final int i2, final boolean z) {
            ViewCompat.i0(view, new AccessibilityDelegateCompat() { // from class: com.google.android.material.internal.NavigationMenuPresenter.NavigationMenuAdapter.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.g(view2, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(NavigationMenuAdapter.this.M(i2), 1, 1, 1, z, view2.isSelected()));
                }
            });
        }

        public Bundle O() {
            Bundle bundle = new Bundle();
            MenuItemImpl menuItemImpl = this.f14743d;
            if (menuItemImpl != null) {
                bundle.putInt("android:menu:checked", menuItemImpl.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f14742c.size();
            for (int i2 = 0; i2 < size; i2++) {
                NavigationMenuItem navigationMenuItem = (NavigationMenuItem) this.f14742c.get(i2);
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    MenuItemImpl a2 = ((NavigationMenuTextItem) navigationMenuItem).a();
                    View actionView = a2 != null ? a2.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(a2.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public MenuItemImpl P() {
            return this.f14743d;
        }

        int Q() {
            int i2 = 0;
            for (int i3 = 0; i3 < NavigationMenuPresenter.this.f14733l.m(); i3++) {
                int o2 = NavigationMenuPresenter.this.f14733l.o(i3);
                if (o2 == 0 || o2 == 1) {
                    i2++;
                }
            }
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: R, reason: merged with bridge method [inline-methods] */
        public void A(ViewHolder viewHolder, int i2) {
            int o2 = o(i2);
            if (o2 != 0) {
                if (o2 != 1) {
                    if (o2 != 2) {
                        return;
                    }
                    NavigationMenuSeparatorItem navigationMenuSeparatorItem = (NavigationMenuSeparatorItem) this.f14742c.get(i2);
                    viewHolder.f5252a.setPadding(NavigationMenuPresenter.this.z, navigationMenuSeparatorItem.b(), NavigationMenuPresenter.this.A, navigationMenuSeparatorItem.a());
                    return;
                }
                TextView textView = (TextView) viewHolder.f5252a;
                textView.setText(((NavigationMenuTextItem) this.f14742c.get(i2)).a().getTitle());
                TextViewCompat.p(textView, NavigationMenuPresenter.this.f14735n);
                textView.setPadding(NavigationMenuPresenter.this.B, textView.getPaddingTop(), NavigationMenuPresenter.this.C, textView.getPaddingBottom());
                ColorStateList colorStateList = NavigationMenuPresenter.this.f14736o;
                if (colorStateList != null) {
                    textView.setTextColor(colorStateList);
                }
                W(textView, i2, true);
                return;
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) viewHolder.f5252a;
            navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.f14740s);
            navigationMenuItemView.setTextAppearance(NavigationMenuPresenter.this.f14737p);
            ColorStateList colorStateList2 = NavigationMenuPresenter.this.f14739r;
            if (colorStateList2 != null) {
                navigationMenuItemView.setTextColor(colorStateList2);
            }
            Drawable drawable = NavigationMenuPresenter.this.t;
            ViewCompat.m0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            RippleDrawable rippleDrawable = NavigationMenuPresenter.this.u;
            if (rippleDrawable != null) {
                navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
            }
            NavigationMenuTextItem navigationMenuTextItem = (NavigationMenuTextItem) this.f14742c.get(i2);
            navigationMenuItemView.setNeedsEmptyIcon(navigationMenuTextItem.f14752b);
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            int i3 = navigationMenuPresenter.v;
            int i4 = navigationMenuPresenter.w;
            navigationMenuItemView.setPadding(i3, i4, i3, i4);
            navigationMenuItemView.setIconPadding(NavigationMenuPresenter.this.x);
            NavigationMenuPresenter navigationMenuPresenter2 = NavigationMenuPresenter.this;
            if (navigationMenuPresenter2.D) {
                navigationMenuItemView.setIconSize(navigationMenuPresenter2.y);
            }
            navigationMenuItemView.setMaxLines(NavigationMenuPresenter.this.F);
            navigationMenuItemView.C(navigationMenuTextItem.a(), NavigationMenuPresenter.this.f14738q);
            W(navigationMenuItemView, i2, false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: S, reason: merged with bridge method [inline-methods] */
        public ViewHolder C(ViewGroup viewGroup, int i2) {
            if (i2 == 0) {
                NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
                return new NormalViewHolder(navigationMenuPresenter.f14734m, viewGroup, navigationMenuPresenter.J);
            }
            if (i2 == 1) {
                return new SubheaderViewHolder(NavigationMenuPresenter.this.f14734m, viewGroup);
            }
            if (i2 == 2) {
                return new SeparatorViewHolder(NavigationMenuPresenter.this.f14734m, viewGroup);
            }
            if (i2 != 3) {
                return null;
            }
            return new HeaderViewHolder(NavigationMenuPresenter.this.f14729h);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: T, reason: merged with bridge method [inline-methods] */
        public void H(ViewHolder viewHolder) {
            if (viewHolder instanceof NormalViewHolder) {
                ((NavigationMenuItemView) viewHolder.f5252a).D();
            }
        }

        public void V(Bundle bundle) {
            MenuItemImpl a2;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            MenuItemImpl a3;
            int i2 = bundle.getInt("android:menu:checked", 0);
            if (i2 != 0) {
                this.f14744e = true;
                int size = this.f14742c.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        break;
                    }
                    NavigationMenuItem navigationMenuItem = (NavigationMenuItem) this.f14742c.get(i3);
                    if ((navigationMenuItem instanceof NavigationMenuTextItem) && (a3 = ((NavigationMenuTextItem) navigationMenuItem).a()) != null && a3.getItemId() == i2) {
                        X(a3);
                        break;
                    }
                    i3++;
                }
                this.f14744e = false;
                U();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f14742c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    NavigationMenuItem navigationMenuItem2 = (NavigationMenuItem) this.f14742c.get(i4);
                    if ((navigationMenuItem2 instanceof NavigationMenuTextItem) && (a2 = ((NavigationMenuTextItem) navigationMenuItem2).a()) != null && (actionView = a2.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(a2.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void X(MenuItemImpl menuItemImpl) {
            if (this.f14743d == menuItemImpl || !menuItemImpl.isCheckable()) {
                return;
            }
            MenuItemImpl menuItemImpl2 = this.f14743d;
            if (menuItemImpl2 != null) {
                menuItemImpl2.setChecked(false);
            }
            this.f14743d = menuItemImpl;
            menuItemImpl.setChecked(true);
        }

        public void Y(boolean z) {
            this.f14744e = z;
        }

        public void Z() {
            U();
            r();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int m() {
            return this.f14742c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long n(int i2) {
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int o(int i2) {
            NavigationMenuItem navigationMenuItem = (NavigationMenuItem) this.f14742c.get(i2);
            if (navigationMenuItem instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if (navigationMenuItem instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if (navigationMenuItem instanceof NavigationMenuTextItem) {
                return ((NavigationMenuTextItem) navigationMenuItem).a().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }
    }

    private static class NavigationMenuHeaderItem implements NavigationMenuItem {
        NavigationMenuHeaderItem() {
        }
    }

    private interface NavigationMenuItem {
    }

    private static class NavigationMenuSeparatorItem implements NavigationMenuItem {

        /* renamed from: a, reason: collision with root package name */
        private final int f14749a;

        /* renamed from: b, reason: collision with root package name */
        private final int f14750b;

        public NavigationMenuSeparatorItem(int i2, int i3) {
            this.f14749a = i2;
            this.f14750b = i3;
        }

        public int a() {
            return this.f14750b;
        }

        public int b() {
            return this.f14749a;
        }
    }

    private static class NavigationMenuTextItem implements NavigationMenuItem {

        /* renamed from: a, reason: collision with root package name */
        private final MenuItemImpl f14751a;

        /* renamed from: b, reason: collision with root package name */
        boolean f14752b;

        NavigationMenuTextItem(MenuItemImpl menuItemImpl) {
            this.f14751a = menuItemImpl;
        }

        public MenuItemImpl a() {
            return this.f14751a;
        }
    }

    private class NavigationMenuViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
        NavigationMenuViewAccessibilityDelegate(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.g(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.a(NavigationMenuPresenter.this.f14733l.Q(), 1, false));
        }
    }

    private static class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.f5252a.setOnClickListener(onClickListener);
        }
    }

    private static class SeparatorViewHolder extends ViewHolder {
        public SeparatorViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    private static class SubheaderViewHolder extends ViewHolder {
        public SubheaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    private static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    private void V() {
        int i2 = (x() || !this.E) ? 0 : this.G;
        NavigationMenuView navigationMenuView = this.f14728c;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    private boolean x() {
        return m() > 0;
    }

    public void A(MenuItemImpl menuItemImpl) {
        this.f14733l.X(menuItemImpl);
    }

    public void B(int i2) {
        this.A = i2;
        updateMenuView(false);
    }

    public void C(int i2) {
        this.z = i2;
        updateMenuView(false);
    }

    public void D(int i2) {
        this.f14732k = i2;
    }

    public void E(Drawable drawable) {
        this.t = drawable;
        updateMenuView(false);
    }

    public void F(RippleDrawable rippleDrawable) {
        this.u = rippleDrawable;
        updateMenuView(false);
    }

    public void G(int i2) {
        this.v = i2;
        updateMenuView(false);
    }

    public void H(int i2) {
        this.x = i2;
        updateMenuView(false);
    }

    public void I(int i2) {
        if (this.y != i2) {
            this.y = i2;
            this.D = true;
            updateMenuView(false);
        }
    }

    public void J(ColorStateList colorStateList) {
        this.f14740s = colorStateList;
        updateMenuView(false);
    }

    public void K(int i2) {
        this.F = i2;
        updateMenuView(false);
    }

    public void L(int i2) {
        this.f14737p = i2;
        updateMenuView(false);
    }

    public void M(boolean z) {
        this.f14738q = z;
        updateMenuView(false);
    }

    public void N(ColorStateList colorStateList) {
        this.f14739r = colorStateList;
        updateMenuView(false);
    }

    public void O(int i2) {
        this.w = i2;
        updateMenuView(false);
    }

    public void P(int i2) {
        this.I = i2;
        NavigationMenuView navigationMenuView = this.f14728c;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void Q(ColorStateList colorStateList) {
        this.f14736o = colorStateList;
        updateMenuView(false);
    }

    public void R(int i2) {
        this.C = i2;
        updateMenuView(false);
    }

    public void S(int i2) {
        this.B = i2;
        updateMenuView(false);
    }

    public void T(int i2) {
        this.f14735n = i2;
        updateMenuView(false);
    }

    public void U(boolean z) {
        NavigationMenuAdapter navigationMenuAdapter = this.f14733l;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.Y(z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.f14730i;
        if (callback != null) {
            callback.a(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void f(Context context, MenuBuilder menuBuilder) {
        this.f14734m = LayoutInflater.from(context);
        this.f14731j = menuBuilder;
        this.H = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.f14732k;
    }

    public void h(View view) {
        this.f14729h.addView(view);
        NavigationMenuView navigationMenuView = this.f14728c;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    public void i(WindowInsetsCompat windowInsetsCompat) {
        int l2 = windowInsetsCompat.l();
        if (this.G != l2) {
            this.G = l2;
            V();
        }
        NavigationMenuView navigationMenuView = this.f14728c;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, windowInsetsCompat.i());
        ViewCompat.f(this.f14729h, windowInsetsCompat);
    }

    public MenuItemImpl j() {
        return this.f14733l.P();
    }

    public int k() {
        return this.A;
    }

    public int l() {
        return this.z;
    }

    public int m() {
        return this.f14729h.getChildCount();
    }

    public Drawable n() {
        return this.t;
    }

    public int o() {
        return this.v;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f14728c.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f14733l.V(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f14729h.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.f14728c != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f14728c.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        NavigationMenuAdapter navigationMenuAdapter = this.f14733l;
        if (navigationMenuAdapter != null) {
            bundle.putBundle("android:menu:adapter", navigationMenuAdapter.O());
        }
        if (this.f14729h != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f14729h.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    public int p() {
        return this.x;
    }

    public int q() {
        return this.F;
    }

    public ColorStateList r() {
        return this.f14739r;
    }

    public ColorStateList s() {
        return this.f14740s;
    }

    public int t() {
        return this.w;
    }

    public MenuView u(ViewGroup viewGroup) {
        if (this.f14728c == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.f14734m.inflate(R.layout.design_navigation_menu, viewGroup, false);
            this.f14728c = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new NavigationMenuViewAccessibilityDelegate(this.f14728c));
            if (this.f14733l == null) {
                NavigationMenuAdapter navigationMenuAdapter = new NavigationMenuAdapter();
                this.f14733l = navigationMenuAdapter;
                navigationMenuAdapter.J(true);
            }
            int i2 = this.I;
            if (i2 != -1) {
                this.f14728c.setOverScrollMode(i2);
            }
            LinearLayout linearLayout = (LinearLayout) this.f14734m.inflate(R.layout.design_navigation_item_header, (ViewGroup) this.f14728c, false);
            this.f14729h = linearLayout;
            ViewCompat.s0(linearLayout, 2);
            this.f14728c.setAdapter(this.f14733l);
        }
        return this.f14728c;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        NavigationMenuAdapter navigationMenuAdapter = this.f14733l;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.Z();
        }
    }

    public int v() {
        return this.C;
    }

    public int w() {
        return this.B;
    }

    public View y(int i2) {
        View inflate = this.f14734m.inflate(i2, (ViewGroup) this.f14729h, false);
        h(inflate);
        return inflate;
    }

    public void z(boolean z) {
        if (this.E != z) {
            this.E = z;
            V();
        }
    }
}
