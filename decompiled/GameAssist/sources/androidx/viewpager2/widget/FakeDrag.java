package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
final class FakeDrag {

    /* renamed from: a, reason: collision with root package name */
    private final ViewPager2 f5810a;

    /* renamed from: b, reason: collision with root package name */
    private final ScrollEventAdapter f5811b;

    /* renamed from: c, reason: collision with root package name */
    private final RecyclerView f5812c;

    FakeDrag(ViewPager2 viewPager2, ScrollEventAdapter scrollEventAdapter, RecyclerView recyclerView) {
        this.f5810a = viewPager2;
        this.f5811b = scrollEventAdapter;
        this.f5812c = recyclerView;
    }

    boolean a() {
        return this.f5811b.i();
    }
}
