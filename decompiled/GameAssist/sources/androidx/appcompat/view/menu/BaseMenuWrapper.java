package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.internal.view.SupportSubMenu;

/* loaded from: classes.dex */
abstract class BaseMenuWrapper {

    /* renamed from: a, reason: collision with root package name */
    final Context f510a;

    /* renamed from: b, reason: collision with root package name */
    private SimpleArrayMap f511b;

    /* renamed from: c, reason: collision with root package name */
    private SimpleArrayMap f512c;

    BaseMenuWrapper(Context context) {
        this.f510a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.f511b == null) {
            this.f511b = new SimpleArrayMap();
        }
        MenuItem menuItem2 = (MenuItem) this.f511b.get(supportMenuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.f510a, supportMenuItem);
        this.f511b.put(supportMenuItem, menuItemWrapperICS);
        return menuItemWrapperICS;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof SupportSubMenu)) {
            return subMenu;
        }
        SupportSubMenu supportSubMenu = (SupportSubMenu) subMenu;
        if (this.f512c == null) {
            this.f512c = new SimpleArrayMap();
        }
        SubMenu subMenu2 = (SubMenu) this.f512c.get(supportSubMenu);
        if (subMenu2 != null) {
            return subMenu2;
        }
        SubMenuWrapperICS subMenuWrapperICS = new SubMenuWrapperICS(this.f510a, supportSubMenu);
        this.f512c.put(supportSubMenu, subMenuWrapperICS);
        return subMenuWrapperICS;
    }

    final void e() {
        SimpleArrayMap simpleArrayMap = this.f511b;
        if (simpleArrayMap != null) {
            simpleArrayMap.clear();
        }
        SimpleArrayMap simpleArrayMap2 = this.f512c;
        if (simpleArrayMap2 != null) {
            simpleArrayMap2.clear();
        }
    }

    final void f(int i2) {
        if (this.f511b == null) {
            return;
        }
        int i3 = 0;
        while (i3 < this.f511b.size()) {
            if (((SupportMenuItem) this.f511b.f(i3)).getGroupId() == i2) {
                this.f511b.h(i3);
                i3--;
            }
            i3++;
        }
    }

    final void g(int i2) {
        if (this.f511b == null) {
            return;
        }
        for (int i3 = 0; i3 < this.f511b.size(); i3++) {
            if (((SupportMenuItem) this.f511b.f(i3)).getItemId() == i2) {
                this.f511b.h(i3);
                return;
            }
        }
    }
}
