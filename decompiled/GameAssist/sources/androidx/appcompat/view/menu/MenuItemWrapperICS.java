package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;
import java.lang.reflect.Method;

@RestrictTo
/* loaded from: classes.dex */
public class MenuItemWrapperICS extends BaseMenuWrapper implements MenuItem {

    /* renamed from: d, reason: collision with root package name */
    private final SupportMenuItem f596d;

    /* renamed from: e, reason: collision with root package name */
    private Method f597e;

    private class ActionProviderWrapper extends ActionProvider implements ActionProvider.VisibilityListener {

        /* renamed from: d, reason: collision with root package name */
        private ActionProvider.VisibilityListener f598d;

        /* renamed from: e, reason: collision with root package name */
        private final android.view.ActionProvider f599e;

        ActionProviderWrapper(Context context, android.view.ActionProvider actionProvider) {
            super(context);
            this.f599e = actionProvider;
        }

        @Override // androidx.core.view.ActionProvider
        public boolean a() {
            return this.f599e.hasSubMenu();
        }

        @Override // androidx.core.view.ActionProvider
        public boolean b() {
            return this.f599e.isVisible();
        }

        @Override // androidx.core.view.ActionProvider
        public View c() {
            return this.f599e.onCreateActionView();
        }

        @Override // androidx.core.view.ActionProvider
        public View d(MenuItem menuItem) {
            return this.f599e.onCreateActionView(menuItem);
        }

        @Override // androidx.core.view.ActionProvider
        public boolean e() {
            return this.f599e.onPerformDefaultAction();
        }

        @Override // androidx.core.view.ActionProvider
        public void f(SubMenu subMenu) {
            this.f599e.onPrepareSubMenu(MenuItemWrapperICS.this.d(subMenu));
        }

        @Override // androidx.core.view.ActionProvider
        public boolean g() {
            return this.f599e.overridesItemVisibility();
        }

        @Override // androidx.core.view.ActionProvider
        public void j(ActionProvider.VisibilityListener visibilityListener) {
            this.f598d = visibilityListener;
            android.view.ActionProvider actionProvider = this.f599e;
            if (visibilityListener == null) {
                this = null;
            }
            actionProvider.setVisibilityListener(this);
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z) {
            ActionProvider.VisibilityListener visibilityListener = this.f598d;
            if (visibilityListener != null) {
                visibilityListener.onActionProviderVisibilityChanged(z);
            }
        }
    }

    static class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
        final android.view.CollapsibleActionView mWrappedView;

        /* JADX WARN: Multi-variable type inference failed */
        CollapsibleActionViewWrapper(View view) {
            super(view.getContext());
            this.mWrappedView = (android.view.CollapsibleActionView) view;
            addView(view);
        }

        View a() {
            return (View) this.mWrappedView;
        }

        @Override // androidx.appcompat.view.CollapsibleActionView
        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        @Override // androidx.appcompat.view.CollapsibleActionView
        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }

    private class OnActionExpandListenerWrapper implements MenuItem.OnActionExpandListener {

        /* renamed from: a, reason: collision with root package name */
        private final MenuItem.OnActionExpandListener f601a;

        OnActionExpandListenerWrapper(MenuItem.OnActionExpandListener onActionExpandListener) {
            this.f601a = onActionExpandListener;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.f601a.onMenuItemActionCollapse(MenuItemWrapperICS.this.c(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.f601a.onMenuItemActionExpand(MenuItemWrapperICS.this.c(menuItem));
        }
    }

    private class OnMenuItemClickListenerWrapper implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final MenuItem.OnMenuItemClickListener f603a;

        OnMenuItemClickListenerWrapper(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            this.f603a = onMenuItemClickListener;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.f603a.onMenuItemClick(MenuItemWrapperICS.this.c(menuItem));
        }
    }

    public MenuItemWrapperICS(Context context, SupportMenuItem supportMenuItem) {
        super(context);
        if (supportMenuItem == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f596d = supportMenuItem;
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return this.f596d.collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return this.f596d.expandActionView();
    }

    @Override // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        androidx.core.view.ActionProvider a2 = this.f596d.a();
        if (a2 instanceof ActionProviderWrapper) {
            return ((ActionProviderWrapper) a2).f599e;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = this.f596d.getActionView();
        return actionView instanceof CollapsibleActionViewWrapper ? ((CollapsibleActionViewWrapper) actionView).a() : actionView;
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f596d.getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f596d.getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f596d.getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f596d.getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f596d.getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f596d.getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f596d.getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f596d.getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f596d.getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f596d.getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return this.f596d.getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f596d.getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f596d.getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return d(this.f596d.getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f596d.getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.f596d.getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f596d.getTooltipText();
    }

    public void h(boolean z) {
        try {
            if (this.f597e == null) {
                this.f597e = this.f596d.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f597e.invoke(this.f596d, Boolean.valueOf(z));
        } catch (Exception e2) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
        }
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f596d.hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f596d.isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return this.f596d.isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return this.f596d.isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return this.f596d.isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return this.f596d.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        ActionProviderWrapper actionProviderWrapper = new ActionProviderWrapper(this.f510a, actionProvider);
        SupportMenuItem supportMenuItem = this.f596d;
        if (actionProvider == null) {
            actionProviderWrapper = null;
        }
        supportMenuItem.b(actionProviderWrapper);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof android.view.CollapsibleActionView) {
            view = new CollapsibleActionViewWrapper(view);
        }
        this.f596d.setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.f596d.setAlphabeticShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.f596d.setCheckable(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.f596d.setChecked(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        this.f596d.setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.f596d.setEnabled(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f596d.setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f596d.setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f596d.setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f596d.setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.f596d.setNumericShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f596d.setOnActionExpandListener(onActionExpandListener != null ? new OnActionExpandListenerWrapper(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f596d.setOnMenuItemClickListener(onMenuItemClickListener != null ? new OnMenuItemClickListenerWrapper(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f596d.setShortcut(c2, c3);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i2) {
        this.f596d.setShowAsAction(i2);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i2) {
        this.f596d.setShowAsActionFlags(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f596d.setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f596d.setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence) {
        this.f596d.setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        return this.f596d.setVisible(z);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.f596d.setAlphabeticShortcut(c2, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.f596d.setIcon(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i2) {
        this.f596d.setNumericShortcut(c2, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f596d.setShortcut(c2, c3, i2, i3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        this.f596d.setTitle(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i2) {
        this.f596d.setActionView(i2);
        View actionView = this.f596d.getActionView();
        if (actionView instanceof android.view.CollapsibleActionView) {
            this.f596d.setActionView(new CollapsibleActionViewWrapper(actionView));
        }
        return this;
    }
}
