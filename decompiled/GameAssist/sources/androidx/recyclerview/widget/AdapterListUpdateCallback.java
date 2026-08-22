package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public final class AdapterListUpdateCallback implements ListUpdateCallback {

    /* renamed from: c, reason: collision with root package name */
    private final RecyclerView.Adapter f4871c;

    public AdapterListUpdateCallback(RecyclerView.Adapter adapter) {
        this.f4871c = adapter;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void a(int i2, int i3) {
        this.f4871c.x(i2, i3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void b(int i2, int i3) {
        this.f4871c.y(i2, i3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void c(int i2, int i3, Object obj) {
        this.f4871c.w(i2, i3, obj);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void d(int i2, int i3) {
        this.f4871c.u(i2, i3);
    }
}
