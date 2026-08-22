package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {

    /* renamed from: g, reason: collision with root package name */
    boolean f5273g = true;

    public abstract boolean B(RecyclerView.ViewHolder viewHolder);

    public abstract boolean C(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4, int i5);

    public abstract boolean D(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5);

    public abstract boolean E(RecyclerView.ViewHolder viewHolder);

    public final void F(RecyclerView.ViewHolder viewHolder) {
        N(viewHolder);
        h(viewHolder);
    }

    public final void G(RecyclerView.ViewHolder viewHolder) {
        O(viewHolder);
    }

    public final void H(RecyclerView.ViewHolder viewHolder, boolean z) {
        P(viewHolder, z);
        h(viewHolder);
    }

    public final void I(RecyclerView.ViewHolder viewHolder, boolean z) {
        Q(viewHolder, z);
    }

    public final void J(RecyclerView.ViewHolder viewHolder) {
        R(viewHolder);
        h(viewHolder);
    }

    public final void K(RecyclerView.ViewHolder viewHolder) {
        S(viewHolder);
    }

    public final void L(RecyclerView.ViewHolder viewHolder) {
        T(viewHolder);
        h(viewHolder);
    }

    public final void M(RecyclerView.ViewHolder viewHolder) {
        U(viewHolder);
    }

    public void N(RecyclerView.ViewHolder viewHolder) {
    }

    public void O(RecyclerView.ViewHolder viewHolder) {
    }

    public void P(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    public void Q(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    public void R(RecyclerView.ViewHolder viewHolder) {
    }

    public void S(RecyclerView.ViewHolder viewHolder) {
    }

    public void T(RecyclerView.ViewHolder viewHolder) {
    }

    public void U(RecyclerView.ViewHolder viewHolder) {
    }

    public void V(boolean z) {
        this.f5273g = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean a(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2;
        int i3;
        return (itemHolderInfo == null || ((i2 = itemHolderInfo.f5162a) == (i3 = itemHolderInfo2.f5162a) && itemHolderInfo.f5163b == itemHolderInfo2.f5163b)) ? B(viewHolder) : D(viewHolder, i2, itemHolderInfo.f5163b, i3, itemHolderInfo2.f5163b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean b(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2;
        int i3;
        int i4 = itemHolderInfo.f5162a;
        int i5 = itemHolderInfo.f5163b;
        if (viewHolder2.K()) {
            int i6 = itemHolderInfo.f5162a;
            i3 = itemHolderInfo.f5163b;
            i2 = i6;
        } else {
            i2 = itemHolderInfo2.f5162a;
            i3 = itemHolderInfo2.f5163b;
        }
        return C(viewHolder, viewHolder2, i4, i5, i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean c(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2 = itemHolderInfo.f5162a;
        int i3 = itemHolderInfo.f5163b;
        View view = viewHolder.f5252a;
        int left = itemHolderInfo2 == null ? view.getLeft() : itemHolderInfo2.f5162a;
        int top = itemHolderInfo2 == null ? view.getTop() : itemHolderInfo2.f5163b;
        if (viewHolder.w() || (i2 == left && i3 == top)) {
            return E(viewHolder);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return D(viewHolder, i2, i3, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean d(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2 = itemHolderInfo.f5162a;
        int i3 = itemHolderInfo2.f5162a;
        if (i2 != i3 || itemHolderInfo.f5163b != itemHolderInfo2.f5163b) {
            return D(viewHolder, i2, itemHolderInfo.f5163b, i3, itemHolderInfo2.f5163b);
        }
        J(viewHolder);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean f(RecyclerView.ViewHolder viewHolder) {
        return !this.f5273g || viewHolder.u();
    }
}
