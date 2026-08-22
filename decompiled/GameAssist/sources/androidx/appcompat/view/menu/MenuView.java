package androidx.appcompat.view.menu;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public interface MenuView {

    public interface ItemView {
        void c(MenuItemImpl menuItemImpl, int i2);

        MenuItemImpl getItemData();

        boolean prefersCondensedTitle();
    }

    void a(MenuBuilder menuBuilder);
}
