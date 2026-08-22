package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class TabLayoutMediator {

    /* renamed from: a, reason: collision with root package name */
    private final TabLayout f15362a;

    /* renamed from: b, reason: collision with root package name */
    private final ViewPager2 f15363b;

    /* renamed from: c, reason: collision with root package name */
    private final TabConfigurationStrategy f15364c;

    /* renamed from: d, reason: collision with root package name */
    private RecyclerView.Adapter f15365d;

    private class PagerAdapterObserver extends RecyclerView.AdapterDataObserver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ TabLayoutMediator f15366a;

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void a() {
            this.f15366a.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void b(int i2, int i3) {
            this.f15366a.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void c(int i2, int i3, Object obj) {
            this.f15366a.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void d(int i2, int i3) {
            this.f15366a.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void e(int i2, int i3, int i4) {
            this.f15366a.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void f(int i2, int i3) {
            this.f15366a.a();
        }
    }

    public interface TabConfigurationStrategy {
        void a(TabLayout.Tab tab, int i2);
    }

    private static class TabLayoutOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference f15367a;

        /* renamed from: b, reason: collision with root package name */
        private int f15368b;

        /* renamed from: c, reason: collision with root package name */
        private int f15369c;

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void a(int i2) {
            this.f15368b = this.f15369c;
            this.f15369c = i2;
            TabLayout tabLayout = (TabLayout) this.f15367a.get();
            if (tabLayout != null) {
                tabLayout.V(this.f15369c);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void b(int i2, float f2, int i3) {
            TabLayout tabLayout = (TabLayout) this.f15367a.get();
            if (tabLayout != null) {
                int i4 = this.f15369c;
                tabLayout.P(i2, f2, i4 != 2 || this.f15368b == 1, (i4 == 2 && this.f15368b == 0) ? false : true, false);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void c(int i2) {
            TabLayout tabLayout = (TabLayout) this.f15367a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i2 || i2 >= tabLayout.getTabCount()) {
                return;
            }
            int i3 = this.f15369c;
            tabLayout.L(tabLayout.B(i2), i3 == 0 || (i3 == 2 && this.f15368b == 0));
        }
    }

    private static class ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener {

        /* renamed from: a, reason: collision with root package name */
        private final ViewPager2 f15370a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f15371b;

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void a(TabLayout.Tab tab) {
            this.f15370a.j(tab.g(), this.f15371b);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void b(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void c(TabLayout.Tab tab) {
        }
    }

    void a() {
        this.f15362a.H();
        RecyclerView.Adapter adapter = this.f15365d;
        if (adapter != null) {
            int m2 = adapter.m();
            for (int i2 = 0; i2 < m2; i2++) {
                TabLayout.Tab E = this.f15362a.E();
                this.f15364c.a(E, i2);
                this.f15362a.k(E, false);
            }
            if (m2 > 0) {
                int min = Math.min(this.f15363b.getCurrentItem(), this.f15362a.getTabCount() - 1);
                if (min != this.f15362a.getSelectedTabPosition()) {
                    TabLayout tabLayout = this.f15362a;
                    tabLayout.K(tabLayout.B(min));
                }
            }
        }
    }
}
