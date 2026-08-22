package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.collection.ArraySet;
import androidx.collection.LongSparseArray;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.Adapter<FragmentViewHolder> implements StatefulAdapter {

    /* renamed from: c, reason: collision with root package name */
    final Lifecycle f5776c;

    /* renamed from: d, reason: collision with root package name */
    final FragmentManager f5777d;

    /* renamed from: e, reason: collision with root package name */
    final LongSparseArray f5778e;

    /* renamed from: f, reason: collision with root package name */
    private final LongSparseArray f5779f;

    /* renamed from: g, reason: collision with root package name */
    private final LongSparseArray f5780g;

    /* renamed from: h, reason: collision with root package name */
    private FragmentMaxLifecycleEnforcer f5781h;

    /* renamed from: i, reason: collision with root package name */
    boolean f5782i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f5783j;

    private static abstract class DataSetChangeObserver extends RecyclerView.AdapterDataObserver {
        private DataSetChangeObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public abstract void a();

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void b(int i2, int i3) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void c(int i2, int i3, Object obj) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void d(int i2, int i3) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void e(int i2, int i3, int i4) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void f(int i2, int i3) {
            a();
        }
    }

    class FragmentMaxLifecycleEnforcer {

        /* renamed from: a, reason: collision with root package name */
        private ViewPager2.OnPageChangeCallback f5796a;

        /* renamed from: b, reason: collision with root package name */
        private RecyclerView.AdapterDataObserver f5797b;

        /* renamed from: c, reason: collision with root package name */
        private LifecycleEventObserver f5798c;

        /* renamed from: d, reason: collision with root package name */
        private ViewPager2 f5799d;

        /* renamed from: e, reason: collision with root package name */
        private long f5800e = -1;

        FragmentMaxLifecycleEnforcer() {
        }

        private ViewPager2 a(RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        void b(RecyclerView recyclerView) {
            this.f5799d = a(recyclerView);
            ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.1
                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void a(int i2) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void c(int i2) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }
            };
            this.f5796a = onPageChangeCallback;
            this.f5799d.g(onPageChangeCallback);
            DataSetChangeObserver dataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.2
                @Override // androidx.viewpager2.adapter.FragmentStateAdapter.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void a() {
                    FragmentMaxLifecycleEnforcer.this.d(true);
                }
            };
            this.f5797b = dataSetChangeObserver;
            FragmentStateAdapter.this.I(dataSetChangeObserver);
            LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }
            };
            this.f5798c = lifecycleEventObserver;
            FragmentStateAdapter.this.f5776c.a(lifecycleEventObserver);
        }

        void c(RecyclerView recyclerView) {
            a(recyclerView).n(this.f5796a);
            FragmentStateAdapter.this.K(this.f5797b);
            FragmentStateAdapter.this.f5776c.c(this.f5798c);
            this.f5799d = null;
        }

        void d(boolean z) {
            int currentItem;
            Fragment fragment;
            if (FragmentStateAdapter.this.e0() || this.f5799d.getScrollState() != 0 || FragmentStateAdapter.this.f5778e.i() || FragmentStateAdapter.this.m() == 0 || (currentItem = this.f5799d.getCurrentItem()) >= FragmentStateAdapter.this.m()) {
                return;
            }
            long n2 = FragmentStateAdapter.this.n(currentItem);
            if ((n2 != this.f5800e || z) && (fragment = (Fragment) FragmentStateAdapter.this.f5778e.f(n2)) != null && fragment.m0()) {
                this.f5800e = n2;
                FragmentTransaction p2 = FragmentStateAdapter.this.f5777d.p();
                Fragment fragment2 = null;
                for (int i2 = 0; i2 < FragmentStateAdapter.this.f5778e.n(); i2++) {
                    long j2 = FragmentStateAdapter.this.f5778e.j(i2);
                    Fragment fragment3 = (Fragment) FragmentStateAdapter.this.f5778e.o(i2);
                    if (fragment3.m0()) {
                        if (j2 != this.f5800e) {
                            p2.s(fragment3, Lifecycle.State.STARTED);
                        } else {
                            fragment2 = fragment3;
                        }
                        fragment3.N1(j2 == this.f5800e);
                    }
                }
                if (fragment2 != null) {
                    p2.s(fragment2, Lifecycle.State.RESUMED);
                }
                if (p2.o()) {
                    return;
                }
                p2.j();
            }
        }
    }

    private static String O(String str, long j2) {
        return str + j2;
    }

    private void P(int i2) {
        long n2 = n(i2);
        if (this.f5778e.e(n2)) {
            return;
        }
        Fragment N = N(i2);
        N.M1((Fragment.SavedState) this.f5779f.f(n2));
        this.f5778e.k(n2, N);
    }

    private boolean R(long j2) {
        View h0;
        if (this.f5780g.e(j2)) {
            return true;
        }
        Fragment fragment = (Fragment) this.f5778e.f(j2);
        return (fragment == null || (h0 = fragment.h0()) == null || h0.getParent() == null) ? false : true;
    }

    private static boolean S(String str, String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    private Long T(int i2) {
        Long l2 = null;
        for (int i3 = 0; i3 < this.f5780g.n(); i3++) {
            if (((Integer) this.f5780g.o(i3)).intValue() == i2) {
                if (l2 != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                l2 = Long.valueOf(this.f5780g.j(i3));
            }
        }
        return l2;
    }

    private static long Z(String str, String str2) {
        return Long.parseLong(str.substring(str2.length()));
    }

    private void b0(long j2) {
        ViewParent parent;
        Fragment fragment = (Fragment) this.f5778e.f(j2);
        if (fragment == null) {
            return;
        }
        if (fragment.h0() != null && (parent = fragment.h0().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!M(j2)) {
            this.f5779f.l(j2);
        }
        if (!fragment.m0()) {
            this.f5778e.l(j2);
            return;
        }
        if (e0()) {
            this.f5783j = true;
            return;
        }
        if (fragment.m0() && M(j2)) {
            this.f5779f.k(j2, this.f5777d.v1(fragment));
        }
        this.f5777d.p().p(fragment).j();
        this.f5778e.l(j2);
    }

    private void c0() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
                fragmentStateAdapter.f5782i = false;
                fragmentStateAdapter.Q();
            }
        };
        this.f5776c.a(new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    handler.removeCallbacks(runnable);
                    lifecycleOwner.a().c(this);
                }
            }
        });
        handler.postDelayed(runnable, 10000L);
    }

    private void d0(final Fragment fragment, final FrameLayout frameLayout) {
        this.f5777d.l1(new FragmentManager.FragmentLifecycleCallbacks() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.3
            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void m(FragmentManager fragmentManager, Fragment fragment2, View view, Bundle bundle) {
                if (fragment2 == fragment) {
                    fragmentManager.E1(this);
                    FragmentStateAdapter.this.L(view, frameLayout);
                }
            }
        }, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void D(RecyclerView recyclerView) {
        this.f5781h.c(recyclerView);
        this.f5781h = null;
    }

    void L(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public boolean M(long j2) {
        return j2 >= 0 && j2 < ((long) m());
    }

    public abstract Fragment N(int i2);

    void Q() {
        if (!this.f5783j || e0()) {
            return;
        }
        ArraySet arraySet = new ArraySet();
        for (int i2 = 0; i2 < this.f5778e.n(); i2++) {
            long j2 = this.f5778e.j(i2);
            if (!M(j2)) {
                arraySet.add(Long.valueOf(j2));
                this.f5780g.l(j2);
            }
        }
        if (!this.f5782i) {
            this.f5783j = false;
            for (int i3 = 0; i3 < this.f5778e.n(); i3++) {
                long j3 = this.f5778e.j(i3);
                if (!R(j3)) {
                    arraySet.add(Long.valueOf(j3));
                }
            }
        }
        Iterator<E> it = arraySet.iterator();
        while (it.hasNext()) {
            b0(((Long) it.next()).longValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: U, reason: merged with bridge method [inline-methods] */
    public final void A(final FragmentViewHolder fragmentViewHolder, int i2) {
        long l2 = fragmentViewHolder.l();
        int id = fragmentViewHolder.O().getId();
        Long T = T(id);
        if (T != null && T.longValue() != l2) {
            b0(T.longValue());
            this.f5780g.l(T.longValue());
        }
        this.f5780g.k(l2, Integer.valueOf(id));
        P(i2);
        final FrameLayout O = fragmentViewHolder.O();
        if (ViewCompat.M(O)) {
            if (O.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            O.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                    if (O.getParent() != null) {
                        O.removeOnLayoutChangeListener(this);
                        FragmentStateAdapter.this.a0(fragmentViewHolder);
                    }
                }
            });
        }
        Q();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public final FragmentViewHolder C(ViewGroup viewGroup, int i2) {
        return FragmentViewHolder.N(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public final boolean E(FragmentViewHolder fragmentViewHolder) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: X, reason: merged with bridge method [inline-methods] */
    public final void F(FragmentViewHolder fragmentViewHolder) {
        a0(fragmentViewHolder);
        Q();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Y, reason: merged with bridge method [inline-methods] */
    public final void H(FragmentViewHolder fragmentViewHolder) {
        Long T = T(fragmentViewHolder.O().getId());
        if (T != null) {
            b0(T.longValue());
            this.f5780g.l(T.longValue());
        }
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    public final Parcelable a() {
        Bundle bundle = new Bundle(this.f5778e.n() + this.f5779f.n());
        for (int i2 = 0; i2 < this.f5778e.n(); i2++) {
            long j2 = this.f5778e.j(i2);
            Fragment fragment = (Fragment) this.f5778e.f(j2);
            if (fragment != null && fragment.m0()) {
                this.f5777d.k1(bundle, O("f#", j2), fragment);
            }
        }
        for (int i3 = 0; i3 < this.f5779f.n(); i3++) {
            long j3 = this.f5779f.j(i3);
            if (M(j3)) {
                bundle.putParcelable(O("s#", j3), (Parcelable) this.f5779f.f(j3));
            }
        }
        return bundle;
    }

    void a0(final FragmentViewHolder fragmentViewHolder) {
        Fragment fragment = (Fragment) this.f5778e.f(fragmentViewHolder.l());
        if (fragment == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout O = fragmentViewHolder.O();
        View h0 = fragment.h0();
        if (!fragment.m0() && h0 != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (fragment.m0() && h0 == null) {
            d0(fragment, O);
            return;
        }
        if (fragment.m0() && h0.getParent() != null) {
            if (h0.getParent() != O) {
                L(h0, O);
                return;
            }
            return;
        }
        if (fragment.m0()) {
            L(h0, O);
            return;
        }
        if (e0()) {
            if (this.f5777d.M0()) {
                return;
            }
            this.f5776c.a(new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (FragmentStateAdapter.this.e0()) {
                        return;
                    }
                    lifecycleOwner.a().c(this);
                    if (ViewCompat.M(fragmentViewHolder.O())) {
                        FragmentStateAdapter.this.a0(fragmentViewHolder);
                    }
                }
            });
            return;
        }
        d0(fragment, O);
        this.f5777d.p().e(fragment, "f" + fragmentViewHolder.l()).s(fragment, Lifecycle.State.STARTED).j();
        this.f5781h.d(false);
    }

    boolean e0() {
        return this.f5777d.U0();
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    public final void f(Parcelable parcelable) {
        if (!this.f5779f.i() || !this.f5778e.i()) {
            throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
        }
        Bundle bundle = (Bundle) parcelable;
        if (bundle.getClassLoader() == null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        for (String str : bundle.keySet()) {
            if (S(str, "f#")) {
                this.f5778e.k(Z(str, "f#"), this.f5777d.v0(bundle, str));
            } else {
                if (!S(str, "s#")) {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + str);
                }
                long Z = Z(str, "s#");
                Fragment.SavedState savedState = (Fragment.SavedState) bundle.getParcelable(str);
                if (M(Z)) {
                    this.f5779f.k(Z, savedState);
                }
            }
        }
        if (this.f5778e.i()) {
            return;
        }
        this.f5783j = true;
        this.f5782i = true;
        Q();
        c0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long n(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void z(RecyclerView recyclerView) {
        Preconditions.a(this.f5781h == null);
        FragmentMaxLifecycleEnforcer fragmentMaxLifecycleEnforcer = new FragmentMaxLifecycleEnforcer();
        this.f5781h = fragmentMaxLifecycleEnforcer;
        fragmentMaxLifecycleEnforcer.b(recyclerView);
    }
}
