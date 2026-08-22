package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    private static final Interpolator F = new AccelerateInterpolator();
    private static final Interpolator G = new DecelerateInterpolator();
    private boolean A;
    boolean B;

    /* renamed from: a, reason: collision with root package name */
    Context f325a;

    /* renamed from: b, reason: collision with root package name */
    private Context f326b;

    /* renamed from: c, reason: collision with root package name */
    private Activity f327c;

    /* renamed from: d, reason: collision with root package name */
    ActionBarOverlayLayout f328d;

    /* renamed from: e, reason: collision with root package name */
    ActionBarContainer f329e;

    /* renamed from: f, reason: collision with root package name */
    DecorToolbar f330f;

    /* renamed from: g, reason: collision with root package name */
    ActionBarContextView f331g;

    /* renamed from: h, reason: collision with root package name */
    View f332h;

    /* renamed from: i, reason: collision with root package name */
    ScrollingTabContainerView f333i;

    /* renamed from: k, reason: collision with root package name */
    private TabImpl f335k;

    /* renamed from: m, reason: collision with root package name */
    private boolean f337m;

    /* renamed from: n, reason: collision with root package name */
    ActionModeImpl f338n;

    /* renamed from: o, reason: collision with root package name */
    ActionMode f339o;

    /* renamed from: p, reason: collision with root package name */
    ActionMode.Callback f340p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f341q;

    /* renamed from: s, reason: collision with root package name */
    private boolean f343s;
    boolean v;
    boolean w;
    private boolean x;
    ViewPropertyAnimatorCompatSet z;

    /* renamed from: j, reason: collision with root package name */
    private ArrayList f334j = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    private int f336l = -1;

    /* renamed from: r, reason: collision with root package name */
    private ArrayList f342r = new ArrayList();
    private int t = 0;
    boolean u = true;
    private boolean y = true;
    final ViewPropertyAnimatorListener C = new ViewPropertyAnimatorListenerAdapter() { // from class: androidx.appcompat.app.WindowDecorActionBar.1
        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void b(View view) {
            View view2;
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.u && (view2 = windowDecorActionBar.f332h) != null) {
                view2.setTranslationY(0.0f);
                WindowDecorActionBar.this.f329e.setTranslationY(0.0f);
            }
            WindowDecorActionBar.this.f329e.setVisibility(8);
            WindowDecorActionBar.this.f329e.setTransitioning(false);
            WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
            windowDecorActionBar2.z = null;
            windowDecorActionBar2.t();
            ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.f328d;
            if (actionBarOverlayLayout != null) {
                ViewCompat.f0(actionBarOverlayLayout);
            }
        }
    };
    final ViewPropertyAnimatorListener D = new ViewPropertyAnimatorListenerAdapter() { // from class: androidx.appcompat.app.WindowDecorActionBar.2
        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void b(View view) {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.z = null;
            windowDecorActionBar.f329e.requestLayout();
        }
    };
    final ViewPropertyAnimatorUpdateListener E = new ViewPropertyAnimatorUpdateListener() { // from class: androidx.appcompat.app.WindowDecorActionBar.3
        @Override // androidx.core.view.ViewPropertyAnimatorUpdateListener
        public void a(View view) {
            ((View) WindowDecorActionBar.this.f329e.getParent()).invalidate();
        }
    };

    @RestrictTo
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {

        /* renamed from: i, reason: collision with root package name */
        private final Context f347i;

        /* renamed from: j, reason: collision with root package name */
        private final MenuBuilder f348j;

        /* renamed from: k, reason: collision with root package name */
        private ActionMode.Callback f349k;

        /* renamed from: l, reason: collision with root package name */
        private WeakReference f350l;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.f347i = context;
            this.f349k = callback;
            MenuBuilder X = new MenuBuilder(context).X(1);
            this.f348j = X;
            X.W(this);
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback callback = this.f349k;
            if (callback != null) {
                return callback.c(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void b(MenuBuilder menuBuilder) {
            if (this.f349k == null) {
                return;
            }
            k();
            WindowDecorActionBar.this.f331g.g();
        }

        @Override // androidx.appcompat.view.ActionMode
        public void c() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.f338n != this) {
                return;
            }
            if (WindowDecorActionBar.s(windowDecorActionBar.v, windowDecorActionBar.w, false)) {
                this.f349k.a(this);
            } else {
                WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
                windowDecorActionBar2.f339o = this;
                windowDecorActionBar2.f340p = this.f349k;
            }
            this.f349k = null;
            WindowDecorActionBar.this.r(false);
            WindowDecorActionBar.this.f331g.h();
            WindowDecorActionBar windowDecorActionBar3 = WindowDecorActionBar.this;
            windowDecorActionBar3.f328d.setHideOnContentScrollEnabled(windowDecorActionBar3.B);
            WindowDecorActionBar.this.f338n = null;
        }

        @Override // androidx.appcompat.view.ActionMode
        public View d() {
            WeakReference weakReference = this.f350l;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        @Override // androidx.appcompat.view.ActionMode
        public Menu e() {
            return this.f348j;
        }

        @Override // androidx.appcompat.view.ActionMode
        public MenuInflater f() {
            return new SupportMenuInflater(this.f347i);
        }

        @Override // androidx.appcompat.view.ActionMode
        public CharSequence g() {
            return WindowDecorActionBar.this.f331g.getSubtitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public CharSequence i() {
            return WindowDecorActionBar.this.f331g.getTitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public void k() {
            if (WindowDecorActionBar.this.f338n != this) {
                return;
            }
            this.f348j.i0();
            try {
                this.f349k.d(this, this.f348j);
            } finally {
                this.f348j.h0();
            }
        }

        @Override // androidx.appcompat.view.ActionMode
        public boolean l() {
            return WindowDecorActionBar.this.f331g.k();
        }

        @Override // androidx.appcompat.view.ActionMode
        public void m(View view) {
            WindowDecorActionBar.this.f331g.setCustomView(view);
            this.f350l = new WeakReference(view);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void n(int i2) {
            o(WindowDecorActionBar.this.f325a.getResources().getString(i2));
        }

        @Override // androidx.appcompat.view.ActionMode
        public void o(CharSequence charSequence) {
            WindowDecorActionBar.this.f331g.setSubtitle(charSequence);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void q(int i2) {
            r(WindowDecorActionBar.this.f325a.getResources().getString(i2));
        }

        @Override // androidx.appcompat.view.ActionMode
        public void r(CharSequence charSequence) {
            WindowDecorActionBar.this.f331g.setTitle(charSequence);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void s(boolean z) {
            super.s(z);
            WindowDecorActionBar.this.f331g.setTitleOptional(z);
        }

        public boolean t() {
            this.f348j.i0();
            try {
                return this.f349k.b(this, this.f348j);
            } finally {
                this.f348j.h0();
            }
        }
    }

    @RestrictTo
    public class TabImpl extends ActionBar.Tab {

        /* renamed from: a, reason: collision with root package name */
        private ActionBar.TabListener f352a;

        /* renamed from: b, reason: collision with root package name */
        private Drawable f353b;

        /* renamed from: c, reason: collision with root package name */
        private CharSequence f354c;

        /* renamed from: d, reason: collision with root package name */
        private CharSequence f355d;

        /* renamed from: e, reason: collision with root package name */
        private int f356e;

        /* renamed from: f, reason: collision with root package name */
        private View f357f;

        /* renamed from: g, reason: collision with root package name */
        final /* synthetic */ WindowDecorActionBar f358g;

        @Override // androidx.appcompat.app.ActionBar.Tab
        public CharSequence a() {
            return this.f355d;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public View b() {
            return this.f357f;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public Drawable c() {
            return this.f353b;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public int d() {
            return this.f356e;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public CharSequence e() {
            return this.f354c;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public void f() {
            this.f358g.A(this);
        }

        public ActionBar.TabListener g() {
            return this.f352a;
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        this.f327c = activity;
        View decorView = activity.getWindow().getDecorView();
        z(decorView);
        if (z) {
            return;
        }
        this.f332h = decorView.findViewById(R.id.content);
    }

    private void E(boolean z) {
        this.f343s = z;
        if (z) {
            this.f329e.setTabContainer(null);
            this.f330f.b(this.f333i);
        } else {
            this.f330f.b(null);
            this.f329e.setTabContainer(this.f333i);
        }
        boolean z2 = x() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.f333i;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f328d;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.f0(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f330f.setCollapsible(!this.f343s && z2);
        this.f328d.setHasNonEmbeddedTabs(!this.f343s && z2);
    }

    private boolean H() {
        return this.f329e.isLaidOut();
    }

    private void I() {
        if (this.x) {
            return;
        }
        this.x = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f328d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        J(false);
    }

    private void J(boolean z) {
        if (s(this.v, this.w, this.x)) {
            if (this.y) {
                return;
            }
            this.y = true;
            v(z);
            return;
        }
        if (this.y) {
            this.y = false;
            u(z);
        }
    }

    static boolean s(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DecorToolbar w(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void y() {
        if (this.x) {
            this.x = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f328d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            J(false);
        }
    }

    private void z(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(androidx.appcompat.R.id.decor_content_parent);
        this.f328d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f330f = w(view.findViewById(androidx.appcompat.R.id.action_bar));
        this.f331g = (ActionBarContextView) view.findViewById(androidx.appcompat.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(androidx.appcompat.R.id.action_bar_container);
        this.f329e = actionBarContainer;
        DecorToolbar decorToolbar = this.f330f;
        if (decorToolbar == null || this.f331g == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f325a = decorToolbar.getContext();
        boolean z = (this.f330f.getDisplayOptions() & 4) != 0;
        if (z) {
            this.f337m = true;
        }
        ActionBarPolicy b2 = ActionBarPolicy.b(this.f325a);
        G(b2.a() || z);
        E(b2.g());
        TypedArray obtainStyledAttributes = this.f325a.obtainStyledAttributes(null, androidx.appcompat.R.styleable.ActionBar, androidx.appcompat.R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.ActionBar_hideOnContentScroll, false)) {
            F(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            D(dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    public void A(ActionBar.Tab tab) {
        if (x() != 2) {
            this.f336l = tab != null ? tab.d() : -1;
            return;
        }
        FragmentTransaction m2 = (!(this.f327c instanceof FragmentActivity) || this.f330f.getViewGroup().isInEditMode()) ? null : ((FragmentActivity) this.f327c).V().p().m();
        TabImpl tabImpl = this.f335k;
        if (tabImpl != tab) {
            this.f333i.setTabSelected(tab != null ? tab.d() : -1);
            TabImpl tabImpl2 = this.f335k;
            if (tabImpl2 != null) {
                tabImpl2.g().b(this.f335k, m2);
            }
            TabImpl tabImpl3 = (TabImpl) tab;
            this.f335k = tabImpl3;
            if (tabImpl3 != null) {
                tabImpl3.g().a(this.f335k, m2);
            }
        } else if (tabImpl != null) {
            tabImpl.g().c(this.f335k, m2);
            this.f333i.a(tab.d());
        }
        if (m2 == null || m2.o()) {
            return;
        }
        m2.h();
    }

    public void B(boolean z) {
        C(z ? 4 : 0, 4);
    }

    public void C(int i2, int i3) {
        int displayOptions = this.f330f.getDisplayOptions();
        if ((i3 & 4) != 0) {
            this.f337m = true;
        }
        this.f330f.setDisplayOptions((i2 & i3) | ((~i3) & displayOptions));
    }

    public void D(float f2) {
        ViewCompat.q0(this.f329e, f2);
    }

    public void F(boolean z) {
        if (z && !this.f328d.p()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.B = z;
        this.f328d.setHideOnContentScrollEnabled(z);
    }

    public void G(boolean z) {
        this.f330f.setHomeButtonEnabled(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean b() {
        DecorToolbar decorToolbar = this.f330f;
        if (decorToolbar == null || !decorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.f330f.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void c(boolean z) {
        if (z == this.f341q) {
            return;
        }
        this.f341q = z;
        int size = this.f342r.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ActionBar.OnMenuVisibilityListener) this.f342r.get(i2)).onMenuVisibilityChanged(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public int d() {
        return this.f330f.getDisplayOptions();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context e() {
        if (this.f326b == null) {
            TypedValue typedValue = new TypedValue();
            this.f325a.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f326b = new ContextThemeWrapper(this.f325a, i2);
            } else {
                this.f326b = this.f325a;
            }
        }
        return this.f326b;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void enableContentAnimations(boolean z) {
        this.u = z;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void g(Configuration configuration) {
        E(ActionBarPolicy.b(this.f325a).g());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void hideForSystem() {
        if (this.w) {
            return;
        }
        this.w = true;
        J(true);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean i(int i2, KeyEvent keyEvent) {
        Menu e2;
        ActionModeImpl actionModeImpl = this.f338n;
        if (actionModeImpl == null || (e2 = actionModeImpl.e()) == null) {
            return false;
        }
        e2.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return e2.performShortcut(i2, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void l(boolean z) {
        if (this.f337m) {
            return;
        }
        B(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void m(boolean z) {
        C(z ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void n(int i2) {
        this.f330f.setNavigationContentDescription(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void o(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.A = z;
        if (z || (viewPropertyAnimatorCompatSet = this.z) == null) {
            return;
        }
        viewPropertyAnimatorCompatSet.a();
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.z;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.a();
            this.z = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStopped() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onWindowVisibilityChanged(int i2) {
        this.t = i2;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void p(CharSequence charSequence) {
        this.f330f.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionMode q(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl = this.f338n;
        if (actionModeImpl != null) {
            actionModeImpl.c();
        }
        this.f328d.setHideOnContentScrollEnabled(false);
        this.f331g.l();
        ActionModeImpl actionModeImpl2 = new ActionModeImpl(this.f331g.getContext(), callback);
        if (!actionModeImpl2.t()) {
            return null;
        }
        this.f338n = actionModeImpl2;
        actionModeImpl2.k();
        this.f331g.i(actionModeImpl2);
        r(true);
        return actionModeImpl2;
    }

    public void r(boolean z) {
        ViewPropertyAnimatorCompat f2;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        if (z) {
            I();
        } else {
            y();
        }
        if (!H()) {
            if (z) {
                this.f330f.setVisibility(4);
                this.f331g.setVisibility(0);
                return;
            } else {
                this.f330f.setVisibility(0);
                this.f331g.setVisibility(8);
                return;
            }
        }
        if (z) {
            f2 = this.f330f.setupAnimatorToVisibility(4, 100L);
            viewPropertyAnimatorCompat = this.f331g.f(0, 200L);
        } else {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = this.f330f.setupAnimatorToVisibility(0, 200L);
            f2 = this.f331g.f(8, 100L);
            viewPropertyAnimatorCompat = viewPropertyAnimatorCompat2;
        }
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        viewPropertyAnimatorCompatSet.d(f2, viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompatSet.h();
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void showForSystem() {
        if (this.w) {
            this.w = false;
            J(true);
        }
    }

    void t() {
        ActionMode.Callback callback = this.f340p;
        if (callback != null) {
            callback.a(this.f339o);
            this.f339o = null;
            this.f340p = null;
        }
    }

    public void u(boolean z) {
        View view;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.z;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.a();
        }
        if (this.t != 0 || (!this.A && !z)) {
            this.C.b(null);
            return;
        }
        this.f329e.setAlpha(1.0f);
        this.f329e.setTransitioning(true);
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
        float f2 = -this.f329e.getHeight();
        if (z) {
            this.f329e.getLocationInWindow(new int[]{0, 0});
            f2 -= r5[1];
        }
        ViewPropertyAnimatorCompat m2 = ViewCompat.d(this.f329e).m(f2);
        m2.k(this.E);
        viewPropertyAnimatorCompatSet2.c(m2);
        if (this.u && (view = this.f332h) != null) {
            viewPropertyAnimatorCompatSet2.c(ViewCompat.d(view).m(f2));
        }
        viewPropertyAnimatorCompatSet2.f(F);
        viewPropertyAnimatorCompatSet2.e(250L);
        viewPropertyAnimatorCompatSet2.g(this.C);
        this.z = viewPropertyAnimatorCompatSet2;
        viewPropertyAnimatorCompatSet2.h();
    }

    public void v(boolean z) {
        View view;
        View view2;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.z;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.a();
        }
        this.f329e.setVisibility(0);
        if (this.t == 0 && (this.A || z)) {
            this.f329e.setTranslationY(0.0f);
            float f2 = -this.f329e.getHeight();
            if (z) {
                this.f329e.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            this.f329e.setTranslationY(f2);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat m2 = ViewCompat.d(this.f329e).m(0.0f);
            m2.k(this.E);
            viewPropertyAnimatorCompatSet2.c(m2);
            if (this.u && (view2 = this.f332h) != null) {
                view2.setTranslationY(f2);
                viewPropertyAnimatorCompatSet2.c(ViewCompat.d(this.f332h).m(0.0f));
            }
            viewPropertyAnimatorCompatSet2.f(G);
            viewPropertyAnimatorCompatSet2.e(250L);
            viewPropertyAnimatorCompatSet2.g(this.D);
            this.z = viewPropertyAnimatorCompatSet2;
            viewPropertyAnimatorCompatSet2.h();
        } else {
            this.f329e.setAlpha(1.0f);
            this.f329e.setTranslationY(0.0f);
            if (this.u && (view = this.f332h) != null) {
                view.setTranslationY(0.0f);
            }
            this.D.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f328d;
        if (actionBarOverlayLayout != null) {
            ViewCompat.f0(actionBarOverlayLayout);
        }
    }

    public int x() {
        return this.f330f.getNavigationMode();
    }

    public WindowDecorActionBar(Dialog dialog) {
        z(dialog.getWindow().getDecorView());
    }
}
