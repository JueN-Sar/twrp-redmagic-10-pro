package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class MenuPopup implements ShowableListMenu, MenuPresenter, AdapterView.OnItemClickListener {

    /* renamed from: c, reason: collision with root package name */
    private Rect f605c;

    MenuPopup() {
    }

    protected static int j(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i3 = 0;
        int i4 = 0;
        View view = null;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = listAdapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i5, view, viewGroup);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i2) {
                return i2;
            }
            if (measuredWidth > i3) {
                i3 = measuredWidth;
            }
        }
        return i3;
    }

    protected static boolean s(MenuBuilder menuBuilder) {
        int size = menuBuilder.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menuBuilder.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    protected static MenuAdapter t(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (MenuAdapter) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (MenuAdapter) listAdapter;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void f(Context context, MenuBuilder menuBuilder) {
    }

    public abstract void g(MenuBuilder menuBuilder);

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return 0;
    }

    protected boolean h() {
        return true;
    }

    public Rect i() {
        return this.f605c;
    }

    public abstract void k(View view);

    public void l(Rect rect) {
        this.f605c = rect;
    }

    public abstract void m(boolean z);

    public abstract void n(int i2);

    public abstract void o(int i2);

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        t(listAdapter).f547c.P((MenuItem) listAdapter.getItem(i2), this, h() ? 0 : 4);
    }

    public abstract void p(PopupWindow.OnDismissListener onDismissListener);

    public abstract void q(boolean z);

    public abstract void r(int i2);
}
