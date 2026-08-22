package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.internal.view.SupportMenuItem;

/* loaded from: classes.dex */
public final class MenuItemCompat {

    /* renamed from: androidx.core.view.MenuItemCompat$1, reason: invalid class name */
    class AnonymousClass1 implements MenuItem.OnActionExpandListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ OnActionExpandListener f3345a;

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.f3345a.onMenuItemActionCollapse(menuItem);
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.f3345a.onMenuItemActionExpand(menuItem);
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static int a(MenuItem menuItem) {
            return menuItem.getAlphabeticModifiers();
        }

        @DoNotInline
        static CharSequence b(MenuItem menuItem) {
            return menuItem.getContentDescription();
        }

        @DoNotInline
        static ColorStateList c(MenuItem menuItem) {
            return menuItem.getIconTintList();
        }

        @DoNotInline
        static PorterDuff.Mode d(MenuItem menuItem) {
            return menuItem.getIconTintMode();
        }

        @DoNotInline
        static int e(MenuItem menuItem) {
            return menuItem.getNumericModifiers();
        }

        @DoNotInline
        static CharSequence f(MenuItem menuItem) {
            return menuItem.getTooltipText();
        }

        @DoNotInline
        static MenuItem g(MenuItem menuItem, char c2, int i2) {
            return menuItem.setAlphabeticShortcut(c2, i2);
        }

        @DoNotInline
        static MenuItem h(MenuItem menuItem, CharSequence charSequence) {
            return menuItem.setContentDescription(charSequence);
        }

        @DoNotInline
        static MenuItem i(MenuItem menuItem, ColorStateList colorStateList) {
            return menuItem.setIconTintList(colorStateList);
        }

        @DoNotInline
        static MenuItem j(MenuItem menuItem, PorterDuff.Mode mode) {
            return menuItem.setIconTintMode(mode);
        }

        @DoNotInline
        static MenuItem k(MenuItem menuItem, char c2, int i2) {
            return menuItem.setNumericShortcut(c2, i2);
        }

        @DoNotInline
        static MenuItem l(MenuItem menuItem, char c2, char c3, int i2, int i3) {
            return menuItem.setShortcut(c2, c3, i2, i3);
        }

        @DoNotInline
        static MenuItem m(MenuItem menuItem, CharSequence charSequence) {
            return menuItem.setTooltipText(charSequence);
        }
    }

    @Deprecated
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem menuItem);

        boolean onMenuItemActionExpand(MenuItem menuItem);
    }

    public static MenuItem a(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).b(actionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static void b(MenuItem menuItem, char c2, int i2) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setAlphabeticShortcut(c2, i2);
        } else {
            Api26Impl.g(menuItem, c2, i2);
        }
    }

    public static void c(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setContentDescription(charSequence);
        } else {
            Api26Impl.h(menuItem, charSequence);
        }
    }

    public static void d(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setIconTintList(colorStateList);
        } else {
            Api26Impl.i(menuItem, colorStateList);
        }
    }

    public static void e(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setIconTintMode(mode);
        } else {
            Api26Impl.j(menuItem, mode);
        }
    }

    public static void f(MenuItem menuItem, char c2, int i2) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setNumericShortcut(c2, i2);
        } else {
            Api26Impl.k(menuItem, c2, i2);
        }
    }

    public static void g(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setTooltipText(charSequence);
        } else {
            Api26Impl.m(menuItem, charSequence);
        }
    }
}
