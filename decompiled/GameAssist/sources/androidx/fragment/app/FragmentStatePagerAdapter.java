package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes.dex */
public abstract class FragmentStatePagerAdapter extends PagerAdapter {

    /* renamed from: c, reason: collision with root package name */
    private final FragmentManager f4133c;

    /* renamed from: d, reason: collision with root package name */
    private final int f4134d;

    /* renamed from: e, reason: collision with root package name */
    private FragmentTransaction f4135e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f4136f;

    /* renamed from: g, reason: collision with root package name */
    private ArrayList f4137g;

    /* renamed from: h, reason: collision with root package name */
    private Fragment f4138h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4139i;

    @Override // androidx.viewpager.widget.PagerAdapter
    public void b(ViewGroup viewGroup, int i2, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.f4135e == null) {
            this.f4135e = this.f4133c.p();
        }
        while (this.f4136f.size() <= i2) {
            this.f4136f.add(null);
        }
        this.f4136f.set(i2, fragment.m0() ? this.f4133c.v1(fragment) : null);
        this.f4137g.set(i2, null);
        this.f4135e.p(fragment);
        if (fragment.equals(this.f4138h)) {
            this.f4138h = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void d(ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.f4135e;
        if (fragmentTransaction != null) {
            if (!this.f4139i) {
                try {
                    this.f4139i = true;
                    fragmentTransaction.k();
                } finally {
                    this.f4139i = false;
                }
            }
            this.f4135e = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object j(ViewGroup viewGroup, int i2) {
        Fragment.SavedState savedState;
        Fragment fragment;
        if (this.f4137g.size() > i2 && (fragment = (Fragment) this.f4137g.get(i2)) != null) {
            return fragment;
        }
        if (this.f4135e == null) {
            this.f4135e = this.f4133c.p();
        }
        Fragment v = v(i2);
        if (this.f4136f.size() > i2 && (savedState = (Fragment.SavedState) this.f4136f.get(i2)) != null) {
            v.M1(savedState);
        }
        while (this.f4137g.size() <= i2) {
            this.f4137g.add(null);
        }
        v.N1(false);
        if (this.f4134d == 0) {
            v.U1(false);
        }
        this.f4137g.set(i2, v);
        this.f4135e.b(viewGroup.getId(), v);
        if (this.f4134d == 1) {
            this.f4135e.s(v, Lifecycle.State.STARTED);
        }
        return v;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean k(View view, Object obj) {
        return ((Fragment) obj).h0() == view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void n(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.f4136f.clear();
            this.f4137g.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.f4136f.add((Fragment.SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment v0 = this.f4133c.v0(bundle, str);
                    if (v0 != null) {
                        while (this.f4137g.size() <= parseInt) {
                            this.f4137g.add(null);
                        }
                        v0.N1(false);
                        this.f4137g.set(parseInt, v0);
                    } else {
                        Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable o() {
        Bundle bundle;
        if (this.f4136f.size() > 0) {
            bundle = new Bundle();
            Fragment.SavedState[] savedStateArr = new Fragment.SavedState[this.f4136f.size()];
            this.f4136f.toArray(savedStateArr);
            bundle.putParcelableArray("states", savedStateArr);
        } else {
            bundle = null;
        }
        for (int i2 = 0; i2 < this.f4137g.size(); i2++) {
            Fragment fragment = (Fragment) this.f4137g.get(i2);
            if (fragment != null && fragment.m0()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.f4133c.k1(bundle, "f" + i2, fragment);
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void q(ViewGroup viewGroup, int i2, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f4138h;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.N1(false);
                if (this.f4134d == 1) {
                    if (this.f4135e == null) {
                        this.f4135e = this.f4133c.p();
                    }
                    this.f4135e.s(this.f4138h, Lifecycle.State.STARTED);
                } else {
                    this.f4138h.U1(false);
                }
            }
            fragment.N1(true);
            if (this.f4134d == 1) {
                if (this.f4135e == null) {
                    this.f4135e = this.f4133c.p();
                }
                this.f4135e.s(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.U1(true);
            }
            this.f4138h = fragment;
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
}
