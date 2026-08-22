package com.google.android.material.navigation;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

@RestrictTo
/* loaded from: classes.dex */
public final class NavigationBarMenu extends MenuBuilder {
    private final Class B;
    private final int C;

    public NavigationBarMenu(Context context, Class cls, int i2) {
        super(context);
        this.B = cls;
        this.C = i2;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    protected MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        if (size() + 1 <= this.C) {
            i0();
            MenuItem a2 = super.a(i2, i3, i4, charSequence);
            if (a2 instanceof MenuItemImpl) {
                ((MenuItemImpl) a2).s(true);
            }
            h0();
            return a2;
        }
        String simpleName = this.B.getSimpleName();
        throw new IllegalArgumentException("Maximum number of items supported by " + simpleName + " is " + this.C + ". Limit can be checked with " + simpleName + "#getMaxItemCount()");
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.B.getSimpleName() + " does not support submenus");
    }
}
