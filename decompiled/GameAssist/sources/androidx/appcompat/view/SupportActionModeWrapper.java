package androidx.appcompat.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class SupportActionModeWrapper extends android.view.ActionMode {

    /* renamed from: a, reason: collision with root package name */
    final Context f436a;

    /* renamed from: b, reason: collision with root package name */
    final ActionMode f437b;

    @RestrictTo
    public static class CallbackWrapper implements ActionMode.Callback {

        /* renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f438a;

        /* renamed from: b, reason: collision with root package name */
        final Context f439b;

        /* renamed from: c, reason: collision with root package name */
        final ArrayList f440c = new ArrayList();

        /* renamed from: d, reason: collision with root package name */
        final SimpleArrayMap f441d = new SimpleArrayMap();

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.f439b = context;
            this.f438a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = (Menu) this.f441d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            MenuWrapperICS menuWrapperICS = new MenuWrapperICS(this.f439b, (SupportMenu) menu);
            this.f441d.put(menu, menuWrapperICS);
            return menuWrapperICS;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void a(ActionMode actionMode) {
            this.f438a.onDestroyActionMode(e(actionMode));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean b(ActionMode actionMode, Menu menu) {
            return this.f438a.onCreateActionMode(e(actionMode), f(menu));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean c(ActionMode actionMode, MenuItem menuItem) {
            return this.f438a.onActionItemClicked(e(actionMode), new MenuItemWrapperICS(this.f439b, (SupportMenuItem) menuItem));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean d(ActionMode actionMode, Menu menu) {
            return this.f438a.onPrepareActionMode(e(actionMode), f(menu));
        }

        public android.view.ActionMode e(ActionMode actionMode) {
            int size = this.f440c.size();
            for (int i2 = 0; i2 < size; i2++) {
                SupportActionModeWrapper supportActionModeWrapper = (SupportActionModeWrapper) this.f440c.get(i2);
                if (supportActionModeWrapper != null && supportActionModeWrapper.f437b == actionMode) {
                    return supportActionModeWrapper;
                }
            }
            SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.f439b, actionMode);
            this.f440c.add(supportActionModeWrapper2);
            return supportActionModeWrapper2;
        }
    }

    public SupportActionModeWrapper(Context context, ActionMode actionMode) {
        this.f436a = context;
        this.f437b = actionMode;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f437b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f437b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new MenuWrapperICS(this.f436a, (SupportMenu) this.f437b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f437b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f437b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f437b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f437b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f437b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f437b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f437b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f437b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f437b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f437b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f437b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f437b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i2) {
        this.f437b.n(i2);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i2) {
        this.f437b.q(i2);
    }
}
