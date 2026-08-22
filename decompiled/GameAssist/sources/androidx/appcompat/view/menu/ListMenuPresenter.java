package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {

    /* renamed from: c, reason: collision with root package name */
    Context f535c;

    /* renamed from: h, reason: collision with root package name */
    LayoutInflater f536h;

    /* renamed from: i, reason: collision with root package name */
    MenuBuilder f537i;

    /* renamed from: j, reason: collision with root package name */
    ExpandedMenuView f538j;

    /* renamed from: k, reason: collision with root package name */
    int f539k;

    /* renamed from: l, reason: collision with root package name */
    int f540l;

    /* renamed from: m, reason: collision with root package name */
    int f541m;

    /* renamed from: n, reason: collision with root package name */
    private MenuPresenter.Callback f542n;

    /* renamed from: o, reason: collision with root package name */
    MenuAdapter f543o;

    /* renamed from: p, reason: collision with root package name */
    private int f544p;

    private class MenuAdapter extends BaseAdapter {

        /* renamed from: c, reason: collision with root package name */
        private int f545c = -1;

        public MenuAdapter() {
            a();
        }

        void a() {
            MenuItemImpl x = ListMenuPresenter.this.f537i.x();
            if (x != null) {
                ArrayList B = ListMenuPresenter.this.f537i.B();
                int size = B.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((MenuItemImpl) B.get(i2)) == x) {
                        this.f545c = i2;
                        return;
                    }
                }
            }
            this.f545c = -1;
        }

        @Override // android.widget.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MenuItemImpl getItem(int i2) {
            ArrayList B = ListMenuPresenter.this.f537i.B();
            int i3 = i2 + ListMenuPresenter.this.f539k;
            int i4 = this.f545c;
            if (i4 >= 0 && i3 >= i4) {
                i3++;
            }
            return (MenuItemImpl) B.get(i3);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = ListMenuPresenter.this.f537i.B().size() - ListMenuPresenter.this.f539k;
            return this.f545c < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                ListMenuPresenter listMenuPresenter = ListMenuPresenter.this;
                view = listMenuPresenter.f536h.inflate(listMenuPresenter.f541m, viewGroup, false);
            }
            ((MenuView.ItemView) view).c(getItem(i2), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(Context context, int i2) {
        this(i2, 0);
        this.f535c = context;
        this.f536h = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.f542n;
        if (callback != null) {
            callback.a(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void c(MenuPresenter.Callback callback) {
        this.f542n = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).d(null);
        MenuPresenter.Callback callback = this.f542n;
        if (callback == null) {
            return true;
        }
        callback.b(subMenuBuilder);
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void f(Context context, MenuBuilder menuBuilder) {
        if (this.f540l != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.f540l);
            this.f535c = contextThemeWrapper;
            this.f536h = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f535c != null) {
            this.f535c = context;
            if (this.f536h == null) {
                this.f536h = LayoutInflater.from(context);
            }
        }
        this.f537i = menuBuilder;
        MenuAdapter menuAdapter = this.f543o;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter g() {
        if (this.f543o == null) {
            this.f543o = new MenuAdapter();
        }
        return this.f543o;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.f544p;
    }

    public MenuView h(ViewGroup viewGroup) {
        if (this.f538j == null) {
            this.f538j = (ExpandedMenuView) this.f536h.inflate(R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.f543o == null) {
                this.f543o = new MenuAdapter();
            }
            this.f538j.setAdapter((ListAdapter) this.f543o);
            this.f538j.setOnItemClickListener(this);
        }
        return this.f538j;
    }

    public void i(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f538j.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public void j(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f538j;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        this.f537i.P(this.f543o.getItem(i2), this, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        i((Bundle) parcelable);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        if (this.f538j == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        j(bundle);
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        MenuAdapter menuAdapter = this.f543o;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int i2, int i3) {
        this.f541m = i2;
        this.f540l = i3;
    }
}
