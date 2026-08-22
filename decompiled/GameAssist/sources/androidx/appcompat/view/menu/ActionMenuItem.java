package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;

@RestrictTo
/* loaded from: classes.dex */
public class ActionMenuItem implements SupportMenuItem {

    /* renamed from: a, reason: collision with root package name */
    private final int f480a;

    /* renamed from: b, reason: collision with root package name */
    private final int f481b;

    /* renamed from: c, reason: collision with root package name */
    private final int f482c;

    /* renamed from: d, reason: collision with root package name */
    private CharSequence f483d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f484e;

    /* renamed from: f, reason: collision with root package name */
    private Intent f485f;

    /* renamed from: g, reason: collision with root package name */
    private char f486g;

    /* renamed from: i, reason: collision with root package name */
    private char f488i;

    /* renamed from: k, reason: collision with root package name */
    private Drawable f490k;

    /* renamed from: l, reason: collision with root package name */
    private Context f491l;

    /* renamed from: m, reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f492m;

    /* renamed from: n, reason: collision with root package name */
    private CharSequence f493n;

    /* renamed from: o, reason: collision with root package name */
    private CharSequence f494o;

    /* renamed from: h, reason: collision with root package name */
    private int f487h = 4096;

    /* renamed from: j, reason: collision with root package name */
    private int f489j = 4096;

    /* renamed from: p, reason: collision with root package name */
    private ColorStateList f495p = null;

    /* renamed from: q, reason: collision with root package name */
    private PorterDuff.Mode f496q = null;

    /* renamed from: r, reason: collision with root package name */
    private boolean f497r = false;

    /* renamed from: s, reason: collision with root package name */
    private boolean f498s = false;
    private int t = 16;

    public ActionMenuItem(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.f491l = context;
        this.f480a = i3;
        this.f481b = i2;
        this.f482c = i5;
        this.f483d = charSequence;
    }

    private void c() {
        Drawable drawable = this.f490k;
        if (drawable != null) {
            if (this.f497r || this.f498s) {
                Drawable r2 = DrawableCompat.r(drawable);
                this.f490k = r2;
                Drawable mutate = r2.mutate();
                this.f490k = mutate;
                if (this.f497r) {
                    DrawableCompat.o(mutate, this.f495p);
                }
                if (this.f498s) {
                    DrawableCompat.p(this.f490k, this.f496q);
                }
            }
        }
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public ActionProvider a() {
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem b(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public SupportMenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public SupportMenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public SupportMenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f489j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f488i;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f493n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f481b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f490k;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f495p;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f496q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f485f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f480a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f487h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f486g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f482c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f483d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f484e;
        return charSequence != null ? charSequence : this.f483d;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f494o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.t & 8) == 0;
    }

    public boolean requiresActionButton() {
        return true;
    }

    public boolean requiresOverflow() {
        return false;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.f488i = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.t = (z ? 1 : 0) | (this.t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.t = (z ? 2 : 0) | (this.t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.t = (z ? 16 : 0) | (this.t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f490k = drawable;
        c();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f495p = colorStateList;
        this.f497r = true;
        c();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f496q = mode;
        this.f498s = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f485f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.f486g = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f492m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f486g = c2;
        this.f488i = Character.toLowerCase(c3);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public void setShowAsAction(int i2) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f483d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f484e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        this.t = (this.t & 8) | (z ? 0 : 8);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.f488i = Character.toLowerCase(c2);
        this.f489j = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.f493n = charSequence;
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i2) {
        this.f486g = c2;
        this.f487h = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        this.f483d = this.f491l.getResources().getString(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.f494o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.f490k = ContextCompat.e(this.f491l, i2);
        c();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f486g = c2;
        this.f487h = KeyEvent.normalizeMetaState(i2);
        this.f488i = Character.toLowerCase(c3);
        this.f489j = KeyEvent.normalizeMetaState(i3);
        return this;
    }
}
