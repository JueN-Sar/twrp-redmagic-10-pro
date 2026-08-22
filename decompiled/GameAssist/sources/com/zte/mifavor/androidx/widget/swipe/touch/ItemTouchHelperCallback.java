package com.zte.mifavor.androidx.widget.swipe.touch;

import android.graphics.Canvas;
import android.util.Log;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    /* renamed from: d, reason: collision with root package name */
    private OnItemMovementListener f17245d;

    /* renamed from: e, reason: collision with root package name */
    private OnItemMoveListener f17246e;

    /* renamed from: f, reason: collision with root package name */
    private OnItemStateChangedListener f17247f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f17248g;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void A(RecyclerView.ViewHolder viewHolder, int i2) {
        super.A(viewHolder, i2);
        if (this.f17247f == null || i2 == 0) {
            return;
        }
        Log.e("Z#Swipe-ItemTHCallback", "onSelectedChanged in. onSelectedChanged actionState = " + i2);
        this.f17247f.a(viewHolder, i2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void B(RecyclerView.ViewHolder viewHolder, int i2) {
        if (this.f17246e != null) {
            Log.e("Z#Swipe-ItemTHCallback", "+++++++++++++++++++ onSwiped onItemDismiss. ");
            this.f17246e.a(viewHolder);
        }
    }

    public OnItemMoveListener C() {
        return this.f17246e;
    }

    public void D(boolean z) {
        this.f17248g = z;
    }

    public void E(OnItemMoveListener onItemMoveListener) {
        this.f17246e = onItemMoveListener;
    }

    public void F(OnItemMovementListener onItemMovementListener) {
        this.f17245d = onItemMovementListener;
    }

    public void G(OnItemStateChangedListener onItemStateChangedListener) {
        this.f17247f = onItemStateChangedListener;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.c(recyclerView, viewHolder);
        if (this.f17247f != null) {
            Log.e("Z#Swipe-ItemTHCallback", "clearView. onSelectedChanged ... ... to idle.");
            this.f17247f.a(viewHolder, 0);
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int k(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int i2;
        OnItemMovementListener onItemMovementListener = this.f17245d;
        if (onItemMovementListener != null) {
            int b2 = onItemMovementListener.b(recyclerView, viewHolder);
            i2 = this.f17245d.a(recyclerView, viewHolder);
            Log.d("Z#Swipe-ItemTHCallback", "getMovementFlags dragFlags = " + b2 + ", swipeFlags = " + i2);
        } else {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                if (((LinearLayoutManager) layoutManager).y2() == 0) {
                    Log.d("Z#Swipe-ItemTHCallback", "makeMovementFlags GridLayoutManager HORIZONTAL dragFlags = 15, swipeFlags = 3");
                    i2 = 3;
                } else {
                    Log.d("Z#Swipe-ItemTHCallback", "makeMovementFlags GridLayoutManager VERTICAL dragFlags = 15, swipeFlags = 12");
                    i2 = 12;
                }
            } else if (!(layoutManager instanceof LinearLayoutManager)) {
                i2 = 0;
            } else if (((LinearLayoutManager) layoutManager).y2() == 0) {
                Log.d("Z#Swipe-ItemTHCallback", "makeMovementFlags LinearLayoutManager HORIZONTAL dragFlags = 12, swipeFlags = 3");
                i2 = 3;
            } else {
                Log.d("Z#Swipe-ItemTHCallback", "makeMovementFlags LinearLayoutManager VERTICAL dragFlags = 3, swipeFlags = 12");
                i2 = 12;
            }
        }
        return ItemTouchHelper.Callback.t(0, i2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean q() {
        return this.f17248g;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void u(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
        float f4;
        float abs;
        int width;
        if (i2 == 1) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int y2 = ((LinearLayoutManager) layoutManager).y2();
                if (y2 == 0) {
                    abs = Math.abs(f3);
                    width = viewHolder.f5252a.getHeight();
                } else if (y2 == 1) {
                    abs = Math.abs(f2);
                    width = viewHolder.f5252a.getWidth();
                }
                f4 = 1.0f - (abs / width);
                Log.d("Z#Swipe-ItemTHCallback", "onChildDraw ACTION_STATE_SWIPE. alpha = " + f4);
                viewHolder.f5252a.setAlpha(f4);
            }
            f4 = 1.0f;
            Log.d("Z#Swipe-ItemTHCallback", "onChildDraw ACTION_STATE_SWIPE. alpha = " + f4);
            viewHolder.f5252a.setAlpha(f4);
        }
        if (i2 == 0) {
            Log.d("Z#Swipe-ItemTHCallback", "onChildDraw ACTION_STATE_IDLE.");
            viewHolder.f5252a.setAlpha(1.0f);
            viewHolder.f5252a.setBackgroundColor(0);
        }
        super.u(canvas, recyclerView, viewHolder, f2, f3, i2, z);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean y(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (this.f17246e == null) {
            return false;
        }
        Log.e("Z#Swipe-ItemTHCallback", "onMove onItemMove.");
        return this.f17246e.b(viewHolder, viewHolder2);
    }
}
