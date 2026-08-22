package androidx.appcompat.view;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

@RestrictTo
/* loaded from: classes.dex */
public class StandaloneActionMode extends ActionMode implements MenuBuilder.Callback {

    /* renamed from: i, reason: collision with root package name */
    private Context f429i;

    /* renamed from: j, reason: collision with root package name */
    private ActionBarContextView f430j;

    /* renamed from: k, reason: collision with root package name */
    private ActionMode.Callback f431k;

    /* renamed from: l, reason: collision with root package name */
    private WeakReference f432l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f433m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f434n;

    /* renamed from: o, reason: collision with root package name */
    private MenuBuilder f435o;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback, boolean z) {
        this.f429i = context;
        this.f430j = actionBarContextView;
        this.f431k = callback;
        MenuBuilder X = new MenuBuilder(actionBarContextView.getContext()).X(1);
        this.f435o = X;
        X.W(this);
        this.f434n = z;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.f431k.c(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public void b(MenuBuilder menuBuilder) {
        k();
        this.f430j.g();
    }

    @Override // androidx.appcompat.view.ActionMode
    public void c() {
        if (this.f433m) {
            return;
        }
        this.f433m = true;
        this.f431k.a(this);
    }

    @Override // androidx.appcompat.view.ActionMode
    public View d() {
        WeakReference weakReference = this.f432l;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // androidx.appcompat.view.ActionMode
    public Menu e() {
        return this.f435o;
    }

    @Override // androidx.appcompat.view.ActionMode
    public MenuInflater f() {
        return new SupportMenuInflater(this.f430j.getContext());
    }

    @Override // androidx.appcompat.view.ActionMode
    public CharSequence g() {
        return this.f430j.getSubtitle();
    }

    @Override // androidx.appcompat.view.ActionMode
    public CharSequence i() {
        return this.f430j.getTitle();
    }

    @Override // androidx.appcompat.view.ActionMode
    public void k() {
        this.f431k.d(this, this.f435o);
    }

    @Override // androidx.appcompat.view.ActionMode
    public boolean l() {
        return this.f430j.k();
    }

    @Override // androidx.appcompat.view.ActionMode
    public void m(View view) {
        this.f430j.setCustomView(view);
        this.f432l = view != null ? new WeakReference(view) : null;
    }

    @Override // androidx.appcompat.view.ActionMode
    public void n(int i2) {
        o(this.f429i.getString(i2));
    }

    @Override // androidx.appcompat.view.ActionMode
    public void o(CharSequence charSequence) {
        this.f430j.setSubtitle(charSequence);
    }

    @Override // androidx.appcompat.view.ActionMode
    public void q(int i2) {
        r(this.f429i.getString(i2));
    }

    @Override // androidx.appcompat.view.ActionMode
    public void r(CharSequence charSequence) {
        this.f430j.setTitle(charSequence);
    }

    @Override // androidx.appcompat.view.ActionMode
    public void s(boolean z) {
        super.s(z);
        this.f430j.setTitleOptional(z);
    }
}
