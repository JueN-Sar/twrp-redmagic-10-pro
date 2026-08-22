package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

/* loaded from: classes.dex */
class ToolbarActionBar extends ActionBar {

    /* renamed from: a, reason: collision with root package name */
    final DecorToolbar f300a;

    /* renamed from: b, reason: collision with root package name */
    final Window.Callback f301b;

    /* renamed from: c, reason: collision with root package name */
    final AppCompatDelegateImpl.ActionBarMenuCallback f302c;

    /* renamed from: d, reason: collision with root package name */
    boolean f303d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f304e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f305f;

    /* renamed from: g, reason: collision with root package name */
    private ArrayList f306g = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private final Runnable f307h = new Runnable() { // from class: androidx.appcompat.app.ToolbarActionBar.1
        @Override // java.lang.Runnable
        public void run() {
            ToolbarActionBar.this.s();
        }
    };

    /* renamed from: i, reason: collision with root package name */
    private final Toolbar.OnMenuItemClickListener f308i;

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {

        /* renamed from: c, reason: collision with root package name */
        private boolean f311c;

        ActionMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void a(MenuBuilder menuBuilder, boolean z) {
            if (this.f311c) {
                return;
            }
            this.f311c = true;
            ToolbarActionBar.this.f300a.dismissPopupMenus();
            ToolbarActionBar.this.f301b.onPanelClosed(108, menuBuilder);
            this.f311c = false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean b(MenuBuilder menuBuilder) {
            ToolbarActionBar.this.f301b.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void b(MenuBuilder menuBuilder) {
            if (ToolbarActionBar.this.f300a.isOverflowMenuShowing()) {
                ToolbarActionBar.this.f301b.onPanelClosed(108, menuBuilder);
            } else if (ToolbarActionBar.this.f301b.onPreparePanel(0, null, menuBuilder)) {
                ToolbarActionBar.this.f301b.onMenuOpened(108, menuBuilder);
            }
        }
    }

    private class ToolbarMenuCallback implements AppCompatDelegateImpl.ActionBarMenuCallback {
        ToolbarMenuCallback() {
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.ActionBarMenuCallback
        public boolean a(int i2) {
            if (i2 != 0) {
                return false;
            }
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            if (toolbarActionBar.f303d) {
                return false;
            }
            toolbarActionBar.f300a.setMenuPrepared();
            ToolbarActionBar.this.f303d = true;
            return false;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.ActionBarMenuCallback
        public View onCreatePanelView(int i2) {
            if (i2 == 0) {
                return new View(ToolbarActionBar.this.f300a.getContext());
            }
            return null;
        }
    }

    ToolbarActionBar(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: androidx.appcompat.app.ToolbarActionBar.2
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                return ToolbarActionBar.this.f301b.onMenuItemSelected(0, menuItem);
            }
        };
        this.f308i = onMenuItemClickListener;
        Preconditions.h(toolbar);
        ToolbarWidgetWrapper toolbarWidgetWrapper = new ToolbarWidgetWrapper(toolbar, false);
        this.f300a = toolbarWidgetWrapper;
        this.f301b = (Window.Callback) Preconditions.h(callback);
        toolbarWidgetWrapper.setWindowCallback(callback);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        toolbarWidgetWrapper.setWindowTitle(charSequence);
        this.f302c = new ToolbarMenuCallback();
    }

    private Menu r() {
        if (!this.f304e) {
            this.f300a.c(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.f304e = true;
        }
        return this.f300a.getMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a() {
        return this.f300a.hideOverflowMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean b() {
        if (!this.f300a.hasExpandedActionView()) {
            return false;
        }
        this.f300a.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void c(boolean z) {
        if (z == this.f305f) {
            return;
        }
        this.f305f = z;
        int size = this.f306g.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ActionBar.OnMenuVisibilityListener) this.f306g.get(i2)).onMenuVisibilityChanged(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public int d() {
        return this.f300a.getDisplayOptions();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context e() {
        return this.f300a.getContext();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean f() {
        this.f300a.getViewGroup().removeCallbacks(this.f307h);
        ViewCompat.a0(this.f300a.getViewGroup(), this.f307h);
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void g(Configuration configuration) {
        super.g(configuration);
    }

    @Override // androidx.appcompat.app.ActionBar
    void h() {
        this.f300a.getViewGroup().removeCallbacks(this.f307h);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean i(int i2, KeyEvent keyEvent) {
        Menu r2 = r();
        if (r2 == null) {
            return false;
        }
        r2.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return r2.performShortcut(i2, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean j(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            k();
        }
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean k() {
        return this.f300a.showOverflowMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void l(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void m(boolean z) {
        t(z ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void n(int i2) {
        this.f300a.setNavigationContentDescription(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void o(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void p(CharSequence charSequence) {
        this.f300a.setWindowTitle(charSequence);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void s() {
        /*
            r5 = this;
            android.view.Menu r0 = r5.r()
            boolean r1 = r0 instanceof androidx.appcompat.view.menu.MenuBuilder
            r2 = 0
            if (r1 == 0) goto Ld
            r1 = r0
            androidx.appcompat.view.menu.MenuBuilder r1 = (androidx.appcompat.view.menu.MenuBuilder) r1
            goto Le
        Ld:
            r1 = r2
        Le:
            if (r1 == 0) goto L13
            r1.i0()
        L13:
            r0.clear()     // Catch: java.lang.Throwable -> L28
            android.view.Window$Callback r3 = r5.f301b     // Catch: java.lang.Throwable -> L28
            r4 = 0
            boolean r3 = r3.onCreatePanelMenu(r4, r0)     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L2a
            android.view.Window$Callback r5 = r5.f301b     // Catch: java.lang.Throwable -> L28
            boolean r5 = r5.onPreparePanel(r4, r2, r0)     // Catch: java.lang.Throwable -> L28
            if (r5 != 0) goto L2d
            goto L2a
        L28:
            r5 = move-exception
            goto L33
        L2a:
            r0.clear()     // Catch: java.lang.Throwable -> L28
        L2d:
            if (r1 == 0) goto L32
            r1.h0()
        L32:
            return
        L33:
            if (r1 == 0) goto L38
            r1.h0()
        L38:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.ToolbarActionBar.s():void");
    }

    public void t(int i2, int i3) {
        this.f300a.setDisplayOptions((i2 & i3) | ((~i3) & this.f300a.getDisplayOptions()));
    }
}
