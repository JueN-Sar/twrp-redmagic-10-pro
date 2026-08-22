package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public interface MenuPresenter {

    public interface Callback {
        void a(MenuBuilder menuBuilder, boolean z);

        boolean b(MenuBuilder menuBuilder);
    }

    void a(MenuBuilder menuBuilder, boolean z);

    boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    void c(Callback callback);

    boolean d(SubMenuBuilder subMenuBuilder);

    boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    void f(Context context, MenuBuilder menuBuilder);

    boolean flagActionItems();

    int getId();

    void onRestoreInstanceState(Parcelable parcelable);

    Parcelable onSaveInstanceState();

    void updateMenuView(boolean z);
}
