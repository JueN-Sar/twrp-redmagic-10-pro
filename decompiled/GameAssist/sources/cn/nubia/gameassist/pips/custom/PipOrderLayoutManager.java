package cn.nubia.gameassist.pips.custom;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.view.PipOrderRecycleView;

/* loaded from: classes.dex */
public class PipOrderLayoutManager extends RecyclerView.LayoutManager {

    /* renamed from: s, reason: collision with root package name */
    private final int f7178s;
    private int t;
    private int u = 0;
    private final int v;
    private final int w;
    private View x;
    private PipOrderRecycleView y;
    private ItemTouchCallback z;

    public PipOrderLayoutManager(Context context) {
        this.f7178s = context.getResources().getDimensionPixelSize(R.dimen.dessert_tile_icon_width);
        this.v = context.getResources().getDimensionPixelSize(R.dimen.game_pip_order_list_scrollbar_ps);
        this.w = context.getResources().getDimensionPixelSize(R.dimen.game_pip_order_list_start_ps);
    }

    private int X1() {
        return (w0() - n0()) - k0();
    }

    private int Y1() {
        return V1() ? X1() - this.v : X1();
    }

    private void f2() {
        if (this.x != null) {
            this.x.setTranslationX(this.v + ((r0.getWidth() * this.t) / Y1()));
        }
    }

    private void g2() {
        float pow = ((float) Math.pow(Y1(), 2.0d)) / this.u;
        ViewGroup.LayoutParams layoutParams = this.x.getLayoutParams();
        layoutParams.width = (int) pow;
        this.x.setLayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int F1(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!W1()) {
            return i2;
        }
        int i3 = this.t;
        int Y1 = i3 + i2 < 0 ? -i3 : i3 + i2 > this.u - Y1() ? (this.u - Y1()) - this.t : i2;
        this.t += Y1;
        b2(-Y1);
        f2();
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams J() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public boolean V1() {
        return f() > 22;
    }

    public boolean W1() {
        PipOrderRecycleView pipOrderRecycleView = this.y;
        return pipOrderRecycleView != null && pipOrderRecycleView.getTranslationX() == 0.0f;
    }

    public int Z1() {
        return this.t;
    }

    public boolean a2() {
        ItemTouchCallback itemTouchCallback = this.z;
        return itemTouchCallback != null && itemTouchCallback.h() > 0;
    }

    public void b2(int i2) {
        for (int i3 = 4; i3 < f(); i3++) {
            View O = O(i3);
            if (O != null) {
                O.offsetLeftAndRight(i2);
            }
        }
    }

    public void c2(ItemTouchCallback itemTouchCallback) {
        this.z = itemTouchCallback;
    }

    public void d2(PipOrderRecycleView pipOrderRecycleView) {
        this.y = pipOrderRecycleView;
    }

    public void e2(View view) {
        this.x = view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.g1(recycler, state);
        C(recycler);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < f()) {
            View o2 = recycler.o(i2);
            ItemTouchCallback itemTouchCallback = this.z;
            if (itemTouchCallback != null) {
                itemTouchCallback.j(i2, o2);
            }
            j(o2);
            J0(o2, 0, 0);
            int Y = Y(o2);
            int X = X(o2);
            if (i2 == 0) {
                p(o2, new Rect());
                H0(o2, 0, 0, Y, X);
            } else {
                int i5 = i4 % 6;
                int i6 = this.w + ((i4 / 6) * (this.f7178s + (Y * 2))) + ((i5 % 2) * Y);
                int i7 = (i5 / 2) * X;
                int i8 = Y + i6;
                i3 = Math.max(i3, i8);
                p(o2, new Rect());
                H0(o2, i6, i7, i8, X + i7);
                i4 += i2 == 3 ? 4 : 1;
            }
            i2++;
        }
        this.u = Math.max(i3, X1());
        b2(-this.t);
        f2();
        g2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean q() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean r() {
        return false;
    }
}
