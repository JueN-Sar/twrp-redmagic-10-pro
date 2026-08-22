package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;

@RestrictTo
/* loaded from: classes.dex */
public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder B;
    private MenuItemImpl C;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.B = menuBuilder;
        this.C = menuItemImpl;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public MenuBuilder F() {
        return this.B.F();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean I() {
        return this.B.I();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean J() {
        return this.B.J();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean K() {
        return this.B.K();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public void W(MenuBuilder.Callback callback) {
        this.B.W(callback);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean f(MenuItemImpl menuItemImpl) {
        return this.B.f(menuItemImpl);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    boolean h(MenuBuilder menuBuilder, MenuItem menuItem) {
        return super.h(menuBuilder, menuItem) || this.B.h(menuBuilder, menuItem);
    }

    public Menu j0() {
        return this.B;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean m(MenuItemImpl menuItemImpl) {
        return this.B.m(menuItemImpl);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.B.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.a0(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.d0(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.e0(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.C.setIcon(drawable);
        return this;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.B.setQwertyMode(z);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public String v() {
        MenuItemImpl menuItemImpl = this.C;
        int itemId = menuItemImpl != null ? menuItemImpl.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.v() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i2) {
        return (SubMenu) super.Z(i2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i2) {
        return (SubMenu) super.c0(i2);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i2) {
        this.C.setIcon(i2);
        return this;
    }
}
