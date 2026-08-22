package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;

@Deprecated
/* loaded from: classes.dex */
public abstract class FragmentPagerAdapter extends PagerAdapter {

    /* renamed from: c, reason: collision with root package name */
    private final FragmentManager f4107c;

    /* renamed from: d, reason: collision with root package name */
    private final int f4108d;

    /* renamed from: e, reason: collision with root package name */
    private FragmentTransaction f4109e;

    /* renamed from: f, reason: collision with root package name */
    private Fragment f4110f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f4111g;

    private static String x(int i2, long j2) {
        return "android:switcher:" + i2 + ":" + j2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void b(ViewGroup viewGroup, int i2, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.f4109e == null) {
            this.f4109e = this.f4107c.p();
        }
        this.f4109e.l(fragment);
        if (fragment.equals(this.f4110f)) {
            this.f4110f = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void d(ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.f4109e;
        if (fragmentTransaction != null) {
            if (!this.f4111g) {
                try {
                    this.f4111g = true;
                    fragmentTransaction.k();
                } finally {
                    this.f4111g = false;
                }
            }
            this.f4109e = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object j(ViewGroup viewGroup, int i2) {
        if (this.f4109e == null) {
            this.f4109e = this.f4107c.p();
        }
        long w = w(i2);
        Fragment l0 = this.f4107c.l0(x(viewGroup.getId(), w));
        if (l0 != null) {
            this.f4109e.g(l0);
        } else {
            l0 = v(i2);
            this.f4109e.c(viewGroup.getId(), l0, x(viewGroup.getId(), w));
        }
        if (l0 != this.f4110f) {
            l0.N1(false);
            if (this.f4108d == 1) {
                this.f4109e.s(l0, Lifecycle.State.STARTED);
            } else {
                l0.U1(false);
            }
        }
        return l0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean k(View view, Object obj) {
        return ((Fragment) obj).h0() == view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void n(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable o() {
        return null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void q(ViewGroup viewGroup, int i2, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f4110f;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.N1(false);
                if (this.f4108d == 1) {
                    if (this.f4109e == null) {
                        this.f4109e = this.f4107c.p();
                    }
                    this.f4109e.s(this.f4110f, Lifecycle.State.STARTED);
                } else {
                    this.f4110f.U1(false);
                }
            }
            fragment.N1(true);
            if (this.f4108d == 1) {
                if (this.f4109e == null) {
                    this.f4109e = this.f4107c.p();
                }
                this.f4109e.s(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.U1(true);
            }
            this.f4110f = fragment;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void t(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public abstract Fragment v(int i2);

    public long w(int i2) {
        return i2;
    }
}
