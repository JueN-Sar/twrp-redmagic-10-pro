package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;

@RestrictTo
/* loaded from: classes.dex */
public final class MenuItemImpl implements SupportMenuItem {
    private View A;
    private ActionProvider B;
    private MenuItem.OnActionExpandListener C;
    private ContextMenu.ContextMenuInfo E;

    /* renamed from: a, reason: collision with root package name */
    private final int f576a;

    /* renamed from: b, reason: collision with root package name */
    private final int f577b;

    /* renamed from: c, reason: collision with root package name */
    private final int f578c;

    /* renamed from: d, reason: collision with root package name */
    private final int f579d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f580e;

    /* renamed from: f, reason: collision with root package name */
    private CharSequence f581f;

    /* renamed from: g, reason: collision with root package name */
    private Intent f582g;

    /* renamed from: h, reason: collision with root package name */
    private char f583h;

    /* renamed from: j, reason: collision with root package name */
    private char f585j;

    /* renamed from: l, reason: collision with root package name */
    private Drawable f587l;

    /* renamed from: n, reason: collision with root package name */
    MenuBuilder f589n;

    /* renamed from: o, reason: collision with root package name */
    private SubMenuBuilder f590o;

    /* renamed from: p, reason: collision with root package name */
    private Runnable f591p;

    /* renamed from: q, reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f592q;

    /* renamed from: r, reason: collision with root package name */
    private CharSequence f593r;

    /* renamed from: s, reason: collision with root package name */
    private CharSequence f594s;
    private int z;

    /* renamed from: i, reason: collision with root package name */
    private int f584i = 4096;

    /* renamed from: k, reason: collision with root package name */
    private int f586k = 4096;

    /* renamed from: m, reason: collision with root package name */
    private int f588m = 0;
    private ColorStateList t = null;
    private PorterDuff.Mode u = null;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private int y = 16;
    private boolean D = false;

    MenuItemImpl(MenuBuilder menuBuilder, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.f589n = menuBuilder;
        this.f576a = i3;
        this.f577b = i2;
        this.f578c = i4;
        this.f579d = i5;
        this.f580e = charSequence;
        this.z = i6;
    }

    private static void d(StringBuilder sb, int i2, int i3, String str) {
        if ((i2 & i3) == i3) {
            sb.append(str);
        }
    }

    private Drawable e(Drawable drawable) {
        if (drawable != null && this.x && (this.v || this.w)) {
            drawable = DrawableCompat.r(drawable).mutate();
            if (this.v) {
                DrawableCompat.o(drawable, this.t);
            }
            if (this.w) {
                DrawableCompat.p(drawable, this.u);
            }
            this.x = false;
        }
        return drawable;
    }

    public boolean A() {
        return (this.z & 4) == 4;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public ActionProvider a() {
        return this.B;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem b(ActionProvider actionProvider) {
        ActionProvider actionProvider2 = this.B;
        if (actionProvider2 != null) {
            actionProvider2.h();
        }
        this.A = null;
        this.B = actionProvider;
        this.f589n.N(true);
        ActionProvider actionProvider3 = this.B;
        if (actionProvider3 != null) {
            actionProvider3.j(new ActionProvider.VisibilityListener() { // from class: androidx.appcompat.view.menu.MenuItemImpl.1
                @Override // androidx.core.view.ActionProvider.VisibilityListener
                public void onActionProviderVisibilityChanged(boolean z) {
                    MenuItemImpl menuItemImpl = MenuItemImpl.this;
                    menuItemImpl.f589n.M(menuItemImpl);
                }
            });
        }
        return this;
    }

    public void c() {
        this.f589n.L(this);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f589n.f(this);
        }
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean expandActionView() {
        if (!j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f589n.m(this);
        }
        return false;
    }

    public int f() {
        return this.f579d;
    }

    char g() {
        return this.f589n.J() ? this.f585j : this.f583h;
    }

    @Override // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        ActionProvider actionProvider = this.B;
        if (actionProvider == null) {
            return null;
        }
        View d2 = actionProvider.d(this);
        this.A = d2;
        return d2;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f586k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f585j;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f593r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f577b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f587l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.f588m == 0) {
            return null;
        }
        Drawable b2 = AppCompatResources.b(this.f589n.w(), this.f588m);
        this.f588m = 0;
        this.f587l = b2;
        return e(b2);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.t;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f582g;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f576a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f584i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f583h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f578c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f590o;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f580e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f581f;
        return charSequence != null ? charSequence : this.f580e;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f594s;
    }

    String h() {
        char g2 = g();
        if (g2 == 0) {
            return "";
        }
        Resources resources = this.f589n.w().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f589n.w()).hasPermanentMenuKey()) {
            sb.append(resources.getString(R.string.abc_prepend_shortcut_label));
        }
        int i2 = this.f589n.J() ? this.f586k : this.f584i;
        d(sb, i2, 65536, resources.getString(R.string.abc_menu_meta_shortcut_label));
        d(sb, i2, 4096, resources.getString(R.string.abc_menu_ctrl_shortcut_label));
        d(sb, i2, 2, resources.getString(R.string.abc_menu_alt_shortcut_label));
        d(sb, i2, 1, resources.getString(R.string.abc_menu_shift_shortcut_label));
        d(sb, i2, 4, resources.getString(R.string.abc_menu_sym_shortcut_label));
        d(sb, i2, 8, resources.getString(R.string.abc_menu_function_shortcut_label));
        if (g2 == '\b') {
            sb.append(resources.getString(R.string.abc_menu_delete_shortcut_label));
        } else if (g2 == '\n') {
            sb.append(resources.getString(R.string.abc_menu_enter_shortcut_label));
        } else if (g2 != ' ') {
            sb.append(g2);
        } else {
            sb.append(resources.getString(R.string.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f590o != null;
    }

    CharSequence i(MenuView.ItemView itemView) {
        return (itemView == null || !itemView.prefersCondensedTitle()) ? getTitle() : getTitleCondensed();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        ActionProvider actionProvider = this.B;
        return (actionProvider == null || !actionProvider.g()) ? (this.y & 8) == 0 : (this.y & 8) == 0 && this.B.b();
    }

    public boolean j() {
        ActionProvider actionProvider;
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && (actionProvider = this.B) != null) {
            this.A = actionProvider.d(this);
        }
        return this.A != null;
    }

    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f592q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        MenuBuilder menuBuilder = this.f589n;
        if (menuBuilder.h(menuBuilder, this)) {
            return true;
        }
        Runnable runnable = this.f591p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f582g != null) {
            try {
                this.f589n.w().startActivity(this.f582g);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e2);
            }
        }
        ActionProvider actionProvider = this.B;
        return actionProvider != null && actionProvider.e();
    }

    public boolean l() {
        return (this.y & 32) == 32;
    }

    public boolean m() {
        return (this.y & 4) != 0;
    }

    public boolean n() {
        return (this.z & 1) == 1;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public SupportMenuItem setActionView(int i2) {
        Context w = this.f589n.w();
        setActionView(LayoutInflater.from(w).inflate(i2, (ViewGroup) new LinearLayout(w), false));
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public SupportMenuItem setActionView(View view) {
        int i2;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i2 = this.f576a) > 0) {
            view.setId(i2);
        }
        this.f589n.L(this);
        return this;
    }

    public void q(boolean z) {
        this.D = z;
        this.f589n.N(false);
    }

    void r(boolean z) {
        int i2 = this.y;
        int i3 = (z ? 2 : 0) | (i2 & (-3));
        this.y = i3;
        if (i2 != i3) {
            this.f589n.N(false);
        }
    }

    public boolean requiresActionButton() {
        return (this.z & 2) == 2;
    }

    public boolean requiresOverflow() {
        return (requiresActionButton() || n()) ? false : true;
    }

    public void s(boolean z) {
        this.y = (z ? 4 : 0) | (this.y & (-5));
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.f585j == c2) {
            return this;
        }
        this.f585j = Character.toLowerCase(c2);
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        int i2 = this.y;
        int i3 = (z ? 1 : 0) | (i2 & (-2));
        this.y = i3;
        if (i2 != i3) {
            this.f589n.N(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        if ((this.y & 4) != 0) {
            this.f589n.Y(this);
        } else {
            r(z);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f588m = 0;
        this.f587l = drawable;
        this.x = true;
        this.f589n.N(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.t = colorStateList;
        this.v = true;
        this.x = true;
        this.f589n.N(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.u = mode;
        this.w = true;
        this.x = true;
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f582g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        if (this.f583h == c2) {
            return this;
        }
        this.f583h = c2;
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f592q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f583h = c2;
        this.f585j = Character.toLowerCase(c3);
        this.f589n.N(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.z = i2;
        this.f589n.L(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f580e = charSequence;
        this.f589n.N(false);
        SubMenuBuilder subMenuBuilder = this.f590o;
        if (subMenuBuilder != null) {
            subMenuBuilder.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f581f = charSequence;
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        if (x(z)) {
            this.f589n.M(this);
        }
        return this;
    }

    public void t(boolean z) {
        if (z) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    public String toString() {
        CharSequence charSequence = this.f580e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    void u(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: v, reason: merged with bridge method [inline-methods] */
    public SupportMenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public void w(SubMenuBuilder subMenuBuilder) {
        this.f590o = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    boolean x(boolean z) {
        int i2 = this.y;
        int i3 = (z ? 0 : 8) | (i2 & (-9));
        this.y = i3;
        return i2 != i3;
    }

    public boolean y() {
        return this.f589n.C();
    }

    boolean z() {
        return this.f589n.K() && g() != 0;
    }

    @Override // android.view.MenuItem
    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.f593r = charSequence;
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.f594s = charSequence;
        this.f589n.N(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        if (this.f585j == c2 && this.f586k == i2) {
            return this;
        }
        this.f585j = Character.toLowerCase(c2);
        this.f586k = KeyEvent.normalizeMetaState(i2);
        this.f589n.N(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i2) {
        if (this.f583h == c2 && this.f584i == i2) {
            return this;
        }
        this.f583h = c2;
        this.f584i = KeyEvent.normalizeMetaState(i2);
        this.f589n.N(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f583h = c2;
        this.f584i = KeyEvent.normalizeMetaState(i2);
        this.f585j = Character.toLowerCase(c3);
        this.f586k = KeyEvent.normalizeMetaState(i3);
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.f587l = null;
        this.f588m = i2;
        this.x = true;
        this.f589n.N(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        return setTitle(this.f589n.w().getString(i2));
    }
}
