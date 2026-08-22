package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import com.google.android.material.card.MaterialCardView;

@RestrictTo
/* loaded from: classes.dex */
public class ToolbarWidgetWrapper implements DecorToolbar {

    /* renamed from: a, reason: collision with root package name */
    Toolbar f1052a;

    /* renamed from: b, reason: collision with root package name */
    private int f1053b;

    /* renamed from: c, reason: collision with root package name */
    private View f1054c;

    /* renamed from: d, reason: collision with root package name */
    private View f1055d;

    /* renamed from: e, reason: collision with root package name */
    private Drawable f1056e;

    /* renamed from: f, reason: collision with root package name */
    private Drawable f1057f;

    /* renamed from: g, reason: collision with root package name */
    private Drawable f1058g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f1059h;

    /* renamed from: i, reason: collision with root package name */
    CharSequence f1060i;

    /* renamed from: j, reason: collision with root package name */
    private CharSequence f1061j;

    /* renamed from: k, reason: collision with root package name */
    private CharSequence f1062k;

    /* renamed from: l, reason: collision with root package name */
    Window.Callback f1063l;

    /* renamed from: m, reason: collision with root package name */
    boolean f1064m;

    /* renamed from: n, reason: collision with root package name */
    private ActionMenuPresenter f1065n;

    /* renamed from: o, reason: collision with root package name */
    private int f1066o;

    /* renamed from: p, reason: collision with root package name */
    private int f1067p;

    /* renamed from: q, reason: collision with root package name */
    private Drawable f1068q;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z) {
        this(toolbar, z, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_material);
    }

    private int d() {
        if (this.f1052a.getNavigationIcon() == null) {
            return 11;
        }
        this.f1068q = this.f1052a.getNavigationIcon();
        return 15;
    }

    private void l(CharSequence charSequence) {
        this.f1060i = charSequence;
        if ((this.f1053b & 8) != 0) {
            this.f1052a.setTitle(charSequence);
            if (this.f1059h) {
                ViewCompat.l0(this.f1052a.getRootView(), charSequence);
            }
        }
    }

    private void m() {
        if ((this.f1053b & 4) != 0) {
            if (TextUtils.isEmpty(this.f1062k)) {
                this.f1052a.setNavigationContentDescription(this.f1067p);
            } else {
                this.f1052a.setNavigationContentDescription(this.f1062k);
            }
        }
    }

    private void n() {
        if ((this.f1053b & 4) == 0) {
            this.f1052a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f1052a;
        Drawable drawable = this.f1058g;
        if (drawable == null) {
            drawable = this.f1068q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void o() {
        Drawable drawable;
        int i2 = this.f1053b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) != 0) {
            drawable = this.f1057f;
            if (drawable == null) {
                drawable = this.f1056e;
            }
        } else {
            drawable = this.f1056e;
        }
        this.f1052a.setLogo(drawable);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void a(Menu menu, MenuPresenter.Callback callback) {
        if (this.f1065n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f1052a.getContext());
            this.f1065n = actionMenuPresenter;
            actionMenuPresenter.n(R.id.action_menu_presenter);
        }
        this.f1065n.c(callback);
        this.f1052a.M((MenuBuilder) menu, this.f1065n);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void b(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f1054c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1052a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1054c);
            }
        }
        this.f1054c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.f1066o != 2) {
            return;
        }
        this.f1052a.addView(scrollingTabContainerView, 0);
        Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.f1054c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = -2;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = -2;
        layoutParams.f143a = MaterialCardView.CHECKED_ICON_GRAVITY_BOTTOM_START;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void c(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.f1052a.N(callback, callback2);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean canShowOverflowMenu() {
        return this.f1052a.d();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void collapseActionView() {
        this.f1052a.e();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void dismissPopupMenus() {
        this.f1052a.f();
    }

    public void e(View view) {
        View view2 = this.f1055d;
        if (view2 != null && (this.f1053b & 16) != 0) {
            this.f1052a.removeView(view2);
        }
        this.f1055d = view;
        if (view == null || (this.f1053b & 16) == 0) {
            return;
        }
        this.f1052a.addView(view);
    }

    public void f(int i2) {
        if (i2 == this.f1067p) {
            return;
        }
        this.f1067p = i2;
        if (TextUtils.isEmpty(this.f1052a.getNavigationContentDescription())) {
            setNavigationContentDescription(this.f1067p);
        }
    }

    public void g(Drawable drawable) {
        this.f1057f = drawable;
        o();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public Context getContext() {
        return this.f1052a.getContext();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public int getDisplayOptions() {
        return this.f1053b;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public Menu getMenu() {
        return this.f1052a.getMenu();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public int getNavigationMode() {
        return this.f1066o;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public CharSequence getTitle() {
        return this.f1052a.getTitle();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public ViewGroup getViewGroup() {
        return this.f1052a;
    }

    public void h(CharSequence charSequence) {
        this.f1062k = charSequence;
        m();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean hasExpandedActionView() {
        return this.f1052a.x();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean hideOverflowMenu() {
        return this.f1052a.y();
    }

    public void i(Drawable drawable) {
        this.f1058g = drawable;
        n();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean isOverflowMenuShowPending() {
        return this.f1052a.C();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean isOverflowMenuShowing() {
        return this.f1052a.D();
    }

    public void j(CharSequence charSequence) {
        this.f1061j = charSequence;
        if ((this.f1053b & 8) != 0) {
            this.f1052a.setSubtitle(charSequence);
        }
    }

    public void k(CharSequence charSequence) {
        this.f1059h = true;
        l(charSequence);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setCollapsible(boolean z) {
        this.f1052a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setDisplayOptions(int i2) {
        View view;
        int i3 = this.f1053b ^ i2;
        this.f1053b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    m();
                }
                n();
            }
            if ((i3 & 3) != 0) {
                o();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.f1052a.setTitle(this.f1060i);
                    this.f1052a.setSubtitle(this.f1061j);
                } else {
                    this.f1052a.setTitle((CharSequence) null);
                    this.f1052a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) == 0 || (view = this.f1055d) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                this.f1052a.addView(view);
            } else {
                this.f1052a.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setHomeButtonEnabled(boolean z) {
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setIcon(int i2) {
        setIcon(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setLogo(int i2) {
        g(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setMenuPrepared() {
        this.f1064m = true;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setNavigationContentDescription(int i2) {
        h(i2 == 0 ? null : getContext().getString(i2));
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setVisibility(int i2) {
        this.f1052a.setVisibility(i2);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setWindowCallback(Window.Callback callback) {
        this.f1063l = callback;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f1059h) {
            return;
        }
        l(charSequence);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int i2, long j2) {
        return ViewCompat.d(this.f1052a).b(i2 == 0 ? 1.0f : 0.0f).f(j2).h(new ViewPropertyAnimatorListenerAdapter() { // from class: androidx.appcompat.widget.ToolbarWidgetWrapper.2

            /* renamed from: a, reason: collision with root package name */
            private boolean f1071a = false;

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void a(View view) {
                this.f1071a = true;
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void b(View view) {
                if (this.f1071a) {
                    return;
                }
                ToolbarWidgetWrapper.this.f1052a.setVisibility(i2);
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void c(View view) {
                ToolbarWidgetWrapper.this.f1052a.setVisibility(0);
            }
        });
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean showOverflowMenu() {
        return this.f1052a.S();
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z, int i2, int i3) {
        Drawable drawable;
        this.f1066o = 0;
        this.f1067p = 0;
        this.f1052a = toolbar;
        this.f1060i = toolbar.getTitle();
        this.f1061j = toolbar.getSubtitle();
        this.f1059h = this.f1060i != null;
        this.f1058g = toolbar.getNavigationIcon();
        TintTypedArray v = TintTypedArray.v(toolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        this.f1068q = v.g(R.styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence p2 = v.p(R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(p2)) {
                k(p2);
            }
            CharSequence p3 = v.p(R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(p3)) {
                j(p3);
            }
            Drawable g2 = v.g(R.styleable.ActionBar_logo);
            if (g2 != null) {
                g(g2);
            }
            Drawable g3 = v.g(R.styleable.ActionBar_icon);
            if (g3 != null) {
                setIcon(g3);
            }
            if (this.f1058g == null && (drawable = this.f1068q) != null) {
                i(drawable);
            }
            setDisplayOptions(v.k(R.styleable.ActionBar_displayOptions, 0));
            int n2 = v.n(R.styleable.ActionBar_customNavigationLayout, 0);
            if (n2 != 0) {
                e(LayoutInflater.from(this.f1052a.getContext()).inflate(n2, (ViewGroup) this.f1052a, false));
                setDisplayOptions(this.f1053b | 16);
            }
            int m2 = v.m(R.styleable.ActionBar_height, 0);
            if (m2 > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1052a.getLayoutParams();
                layoutParams.height = m2;
                this.f1052a.setLayoutParams(layoutParams);
            }
            int e2 = v.e(R.styleable.ActionBar_contentInsetStart, -1);
            int e3 = v.e(R.styleable.ActionBar_contentInsetEnd, -1);
            if (e2 >= 0 || e3 >= 0) {
                this.f1052a.L(Math.max(e2, 0), Math.max(e3, 0));
            }
            int n3 = v.n(R.styleable.ActionBar_titleTextStyle, 0);
            if (n3 != 0) {
                Toolbar toolbar2 = this.f1052a;
                toolbar2.P(toolbar2.getContext(), n3);
            }
            int n4 = v.n(R.styleable.ActionBar_subtitleTextStyle, 0);
            if (n4 != 0) {
                Toolbar toolbar3 = this.f1052a;
                toolbar3.O(toolbar3.getContext(), n4);
            }
            int n5 = v.n(R.styleable.ActionBar_popupTheme, 0);
            if (n5 != 0) {
                this.f1052a.setPopupTheme(n5);
            }
        } else {
            this.f1053b = d();
        }
        v.x();
        f(i2);
        this.f1062k = this.f1052a.getNavigationContentDescription();
        this.f1052a.setNavigationOnClickListener(new View.OnClickListener() { // from class: androidx.appcompat.widget.ToolbarWidgetWrapper.1

            /* renamed from: c, reason: collision with root package name */
            final ActionMenuItem f1069c;

            {
                this.f1069c = new ActionMenuItem(ToolbarWidgetWrapper.this.f1052a.getContext(), 0, android.R.id.home, 0, 0, ToolbarWidgetWrapper.this.f1060i);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ToolbarWidgetWrapper toolbarWidgetWrapper = ToolbarWidgetWrapper.this;
                Window.Callback callback = toolbarWidgetWrapper.f1063l;
                if (callback == null || !toolbarWidgetWrapper.f1064m) {
                    return;
                }
                callback.onMenuItemSelected(0, this.f1069c);
            }
        });
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setIcon(Drawable drawable) {
        this.f1056e = drawable;
        o();
    }
}
