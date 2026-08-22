package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class LayoutState {

    /* renamed from: b, reason: collision with root package name */
    int f5074b;

    /* renamed from: c, reason: collision with root package name */
    int f5075c;

    /* renamed from: d, reason: collision with root package name */
    int f5076d;

    /* renamed from: e, reason: collision with root package name */
    int f5077e;

    /* renamed from: h, reason: collision with root package name */
    boolean f5080h;

    /* renamed from: i, reason: collision with root package name */
    boolean f5081i;

    /* renamed from: a, reason: collision with root package name */
    boolean f5073a = true;

    /* renamed from: f, reason: collision with root package name */
    int f5078f = 0;

    /* renamed from: g, reason: collision with root package name */
    int f5079g = 0;

    LayoutState() {
    }

    boolean a(RecyclerView.State state) {
        int i2 = this.f5075c;
        return i2 >= 0 && i2 < state.b();
    }

    View b(RecyclerView.Recycler recycler) {
        View o2 = recycler.o(this.f5075c);
        this.f5075c += this.f5076d;
        return o2;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f5074b + ", mCurrentPosition=" + this.f5075c + ", mItemDirection=" + this.f5076d + ", mLayoutDirection=" + this.f5077e + ", mStartLine=" + this.f5078f + ", mEndLine=" + this.f5079g + '}';
    }
}
