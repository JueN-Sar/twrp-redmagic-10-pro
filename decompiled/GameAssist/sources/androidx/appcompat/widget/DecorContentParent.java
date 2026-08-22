package androidx.appcompat.widget;

import android.view.Menu;
import android.view.Window;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuPresenter;

@RestrictTo
/* loaded from: classes.dex */
public interface DecorContentParent {
    void a(Menu menu, MenuPresenter.Callback callback);

    boolean canShowOverflowMenu();

    void dismissPopups();

    boolean hideOverflowMenu();

    void initFeature(int i2);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setMenuPrepared();

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
