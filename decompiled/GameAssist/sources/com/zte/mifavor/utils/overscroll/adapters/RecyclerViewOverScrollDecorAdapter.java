package com.zte.mifavor.utils.overscroll.adapters;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.List;

/* loaded from: classes2.dex */
public class RecyclerViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected final RecyclerView f17502c;

    /* renamed from: h, reason: collision with root package name */
    protected final Impl f17503h;

    /* renamed from: i, reason: collision with root package name */
    protected boolean f17504i = false;

    /* renamed from: com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter$1, reason: invalid class name */
    class AnonymousClass1 extends ItemTouchHelperCallbackWrapper {

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ RecyclerViewOverScrollDecorAdapter f17505e;

        @Override // com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.ItemTouchHelperCallbackWrapper, androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void A(RecyclerView.ViewHolder viewHolder, int i2) {
            this.f17505e.f17504i = i2 != 0;
            super.A(viewHolder, i2);
        }
    }

    protected interface Impl {
        boolean a();

        boolean b();
    }

    protected class ImplHorizLayout implements Impl {
        protected ImplHorizLayout() {
        }

        @Override // com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean a() {
            return !RecyclerViewOverScrollDecorAdapter.this.f17502c.canScrollHorizontally(1);
        }

        @Override // com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean b() {
            return !RecyclerViewOverScrollDecorAdapter.this.f17502c.canScrollHorizontally(-1);
        }
    }

    protected class ImplVerticalLayout implements Impl {
        protected ImplVerticalLayout() {
        }

        @Override // com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean a() {
            return !RecyclerViewOverScrollDecorAdapter.this.f17502c.canScrollVertically(1);
        }

        @Override // com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter.Impl
        public boolean b() {
            return !RecyclerViewOverScrollDecorAdapter.this.f17502c.canScrollVertically(-1);
        }
    }

    private static class ItemTouchHelperCallbackWrapper extends ItemTouchHelper.Callback {

        /* renamed from: d, reason: collision with root package name */
        final ItemTouchHelper.Callback f17508d;

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void A(RecyclerView.ViewHolder viewHolder, int i2) {
            this.f17508d.A(viewHolder, i2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void B(RecyclerView.ViewHolder viewHolder, int i2) {
            this.f17508d.B(viewHolder, i2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return this.f17508d.a(recyclerView, viewHolder, viewHolder2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public RecyclerView.ViewHolder b(RecyclerView.ViewHolder viewHolder, List list, int i2, int i3) {
            return this.f17508d.b(viewHolder, list, i2, i3);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            this.f17508d.c(recyclerView, viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int d(int i2, int i3) {
            return this.f17508d.d(i2, i3);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public long g(RecyclerView recyclerView, int i2, float f2, float f3) {
            return this.f17508d.g(recyclerView, i2, f2, f3);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int h() {
            return this.f17508d.h();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public float j(RecyclerView.ViewHolder viewHolder) {
            return this.f17508d.j(viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int k(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.f17508d.k(recyclerView, viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public float m(RecyclerView.ViewHolder viewHolder) {
            return this.f17508d.m(viewHolder);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int p(RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
            return this.f17508d.p(recyclerView, i2, i3, i4, j2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean q() {
            return this.f17508d.q();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean r() {
            return this.f17508d.r();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void u(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            this.f17508d.u(canvas, recyclerView, viewHolder, f2, f3, i2, z);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void v(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            this.f17508d.v(canvas, recyclerView, viewHolder, f2, f3, i2, z);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean y(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return this.f17508d.y(recyclerView, viewHolder, viewHolder2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void z(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
            this.f17508d.z(recyclerView, viewHolder, i2, viewHolder2, i3, i4, i5);
        }
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {
        int z2;
        this.f17502c = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            z2 = ((LinearLayoutManager) layoutManager).y2();
        } else {
            if (!(layoutManager instanceof GridLayoutManager)) {
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    z2 = ((StaggeredGridLayoutManager) layoutManager).z2();
                }
                this.f17503h = new ImplVerticalLayout();
            }
            z2 = ((GridLayoutManager) layoutManager).y2();
        }
        if (z2 == 0) {
            this.f17503h = new ImplHorizLayout();
            return;
        }
        this.f17503h = new ImplVerticalLayout();
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        return !this.f17504i && this.f17503h.a();
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        return !this.f17504i && this.f17503h.b();
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17502c;
    }
}
