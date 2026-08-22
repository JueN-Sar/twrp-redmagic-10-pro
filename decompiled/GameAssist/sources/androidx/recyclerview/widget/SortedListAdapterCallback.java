package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

/* loaded from: classes.dex */
public abstract class SortedListAdapterCallback<T2> extends SortedList.Callback<T2> {

    /* renamed from: c, reason: collision with root package name */
    final RecyclerView.Adapter f5282c;

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void a(int i2, int i3) {
        this.f5282c.x(i2, i3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void b(int i2, int i3) {
        this.f5282c.y(i2, i3);
    }

    @Override // androidx.recyclerview.widget.SortedList.Callback, androidx.recyclerview.widget.ListUpdateCallback
    public void c(int i2, int i3, Object obj) {
        this.f5282c.w(i2, i3, obj);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void d(int i2, int i3) {
        this.f5282c.u(i2, i3);
    }

    @Override // androidx.recyclerview.widget.SortedList.Callback
    public void e(int i2, int i3) {
        this.f5282c.v(i2, i3);
    }
}
