package androidx.appcompat.app;

import android.app.Activity;
import android.view.View;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {

    /* renamed from: a, reason: collision with root package name */
    private final Delegate f144a;

    /* renamed from: b, reason: collision with root package name */
    private final DrawerLayout f145b;

    /* renamed from: c, reason: collision with root package name */
    private DrawerArrowDrawable f146c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f147d;

    /* renamed from: e, reason: collision with root package name */
    boolean f148e;

    /* renamed from: f, reason: collision with root package name */
    private final int f149f;

    /* renamed from: g, reason: collision with root package name */
    private final int f150g;

    /* renamed from: h, reason: collision with root package name */
    View.OnClickListener f151h;

    /* renamed from: androidx.appcompat.app.ActionBarDrawerToggle$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ActionBarDrawerToggle f152c;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActionBarDrawerToggle actionBarDrawerToggle = this.f152c;
            if (actionBarDrawerToggle.f148e) {
                actionBarDrawerToggle.g();
                return;
            }
            View.OnClickListener onClickListener = actionBarDrawerToggle.f151h;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public interface Delegate {
        void a(int i2);
    }

    public interface DelegateProvider {
    }

    private static class FrameworkActionBarDelegate implements Delegate {

        /* renamed from: a, reason: collision with root package name */
        private final Activity f153a;

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void a(int i2) {
            android.app.ActionBar actionBar = this.f153a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i2);
            }
        }
    }

    static class ToolbarCompatDelegate implements Delegate {

        /* renamed from: a, reason: collision with root package name */
        final Toolbar f154a;

        /* renamed from: b, reason: collision with root package name */
        final CharSequence f155b;

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void a(int i2) {
            if (i2 == 0) {
                this.f154a.setNavigationContentDescription(this.f155b);
            } else {
                this.f154a.setNavigationContentDescription(i2);
            }
        }
    }

    private void f(float f2) {
        if (f2 == 1.0f) {
            this.f146c.g(true);
        } else if (f2 == 0.0f) {
            this.f146c.g(false);
        }
        this.f146c.e(f2);
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void a(View view) {
        f(1.0f);
        if (this.f148e) {
            e(this.f150g);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void b(View view) {
        f(0.0f);
        if (this.f148e) {
            e(this.f149f);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void c(int i2) {
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void d(View view, float f2) {
        if (this.f147d) {
            f(Math.min(1.0f, Math.max(0.0f, f2)));
        } else {
            f(0.0f);
        }
    }

    void e(int i2) {
        this.f144a.a(i2);
    }

    void g() {
        int p2 = this.f145b.p(8388611);
        if (this.f145b.C(8388611) && p2 != 2) {
            this.f145b.d(8388611);
        } else if (p2 != 1) {
            this.f145b.H(8388611);
        }
    }
}
