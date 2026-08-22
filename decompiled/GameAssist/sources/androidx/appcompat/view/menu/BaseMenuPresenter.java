package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public abstract class BaseMenuPresenter implements MenuPresenter {

    /* renamed from: c, reason: collision with root package name */
    protected Context f500c;

    /* renamed from: h, reason: collision with root package name */
    protected Context f501h;

    /* renamed from: i, reason: collision with root package name */
    protected MenuBuilder f502i;

    /* renamed from: j, reason: collision with root package name */
    protected LayoutInflater f503j;

    /* renamed from: k, reason: collision with root package name */
    protected LayoutInflater f504k;

    /* renamed from: l, reason: collision with root package name */
    private MenuPresenter.Callback f505l;

    /* renamed from: m, reason: collision with root package name */
    private int f506m;

    /* renamed from: n, reason: collision with root package name */
    private int f507n;

    /* renamed from: o, reason: collision with root package name */
    protected MenuView f508o;

    /* renamed from: p, reason: collision with root package name */
    private int f509p;

    public BaseMenuPresenter(Context context, int i2, int i3) {
        this.f500c = context;
        this.f503j = LayoutInflater.from(context);
        this.f506m = i2;
        this.f507n = i3;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.f505l;
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
        this.f505l = callback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.appcompat.view.menu.MenuBuilder] */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        MenuPresenter.Callback callback = this.f505l;
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        if (callback == null) {
            return false;
        }
        if (subMenuBuilder == null) {
            subMenuBuilder2 = this.f502i;
        }
        return callback.b(subMenuBuilder2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void f(Context context, MenuBuilder menuBuilder) {
        this.f501h = context;
        this.f504k = LayoutInflater.from(context);
        this.f502i = menuBuilder;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    protected void g(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f508o).addView(view, i2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.f509p;
    }

    public abstract void h(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    public MenuView.ItemView i(ViewGroup viewGroup) {
        return (MenuView.ItemView) this.f503j.inflate(this.f507n, viewGroup, false);
    }

    protected boolean j(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    public MenuPresenter.Callback k() {
        return this.f505l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View l(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView i2 = view instanceof MenuView.ItemView ? (MenuView.ItemView) view : i(viewGroup);
        h(menuItemImpl, i2);
        return (View) i2;
    }

    public MenuView m(ViewGroup viewGroup) {
        if (this.f508o == null) {
            MenuView menuView = (MenuView) this.f503j.inflate(this.f506m, viewGroup, false);
            this.f508o = menuView;
            menuView.a(this.f502i);
            updateMenuView(true);
        }
        return this.f508o;
    }

    public void n(int i2) {
        this.f509p = i2;
    }

    public boolean o(int i2, MenuItemImpl menuItemImpl) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f508o;
        if (viewGroup == null) {
            return;
        }
        MenuBuilder menuBuilder = this.f502i;
        int i2 = 0;
        if (menuBuilder != null) {
            menuBuilder.t();
            ArrayList G = this.f502i.G();
            int size = G.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) G.get(i4);
                if (o(i3, menuItemImpl)) {
                    View childAt = viewGroup.getChildAt(i3);
                    MenuItemImpl itemData = childAt instanceof MenuView.ItemView ? ((MenuView.ItemView) childAt).getItemData() : null;
                    View l2 = l(menuItemImpl, childAt, viewGroup);
                    if (menuItemImpl != itemData) {
                        l2.setPressed(false);
                        l2.jumpDrawablesToCurrentState();
                    }
                    if (l2 != childAt) {
                        g(l2, i3);
                    }
                    i3++;
                }
            }
            i2 = i3;
        }
        while (i2 < viewGroup.getChildCount()) {
            if (!j(viewGroup, i2)) {
                i2++;
            }
        }
    }
}
