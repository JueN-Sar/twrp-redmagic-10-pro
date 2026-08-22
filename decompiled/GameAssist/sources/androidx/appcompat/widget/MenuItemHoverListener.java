package androidx.appcompat.widget;

import android.view.MenuItem;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;

@RestrictTo
/* loaded from: classes.dex */
public interface MenuItemHoverListener {
    void a(MenuBuilder menuBuilder, MenuItem menuItem);

    void b(MenuBuilder menuBuilder, MenuItem menuItem);
}
