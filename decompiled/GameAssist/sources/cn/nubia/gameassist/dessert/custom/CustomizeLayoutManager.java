package cn.nubia.gameassist.dessert.custom;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class CustomizeLayoutManager extends RecyclerView.LayoutManager {

    /* renamed from: s, reason: collision with root package name */
    private int f6242s = 108;
    private RecyclerView t;

    public CustomizeLayoutManager(Context context, RecyclerView recyclerView) {
        this.t = recyclerView;
    }

    public static int V1(int i2) {
        return (i2 % 6) % 2;
    }

    public static int W1(int i2) {
        return i2 / 6;
    }

    public static int X1(int i2) {
        return (i2 % 6) / 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams J() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.g1(recycler, state);
        C(recycler);
        int i2 = 0;
        for (int i3 = 0; i3 < f(); i3++) {
            View o2 = recycler.o(i3);
            if (e0(o2) != 1) {
                j(o2);
                J0(o2, 0, 0);
                int Y = Y(o2);
                int X = X(o2);
                int i4 = i3 % 6;
                int i5 = ((i3 / 6) * (this.f6242s + (Y * 2))) + ((i4 % 2) * Y);
                int i6 = (i4 / 2) * X;
                int i7 = i5 + Y;
                i2 = Math.max(i2, i7);
                p(o2, new Rect());
                H0(o2, i5, i6, i7, i6 + X);
            }
        }
        this.t.setTranslationX((this.t.getWidth() - i2) / 2);
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
