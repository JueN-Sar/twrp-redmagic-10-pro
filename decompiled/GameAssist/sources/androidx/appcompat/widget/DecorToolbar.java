package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewPropertyAnimatorCompat;

@RestrictTo
/* loaded from: classes.dex */
public interface DecorToolbar {
    void a(Menu menu, MenuPresenter.Callback callback);

    void b(ScrollingTabContainerView scrollingTabContainerView);

    void c(MenuPresenter.Callback callback, MenuBuilder.Callback callback2);

    boolean canShowOverflowMenu();

    void collapseActionView();

    void dismissPopupMenus();

    Context getContext();

    int getDisplayOptions();

    Menu getMenu();

    int getNavigationMode();

    CharSequence getTitle();

    ViewGroup getViewGroup();

    boolean hasExpandedActionView();

    boolean hideOverflowMenu();

    void initIndeterminateProgress();

    void initProgress();

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setCollapsible(boolean z);

    void setDisplayOptions(int i2);

    void setHomeButtonEnabled(boolean z);

    void setIcon(int i2);

    void setIcon(Drawable drawable);

    void setLogo(int i2);

    void setMenuPrepared();

    void setNavigationContentDescription(int i2);

    void setVisibility(int i2);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i2, long j2);

    boolean showOverflowMenu();
}
