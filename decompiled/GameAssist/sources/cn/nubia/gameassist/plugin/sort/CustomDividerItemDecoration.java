package cn.nubia.gameassist.plugin.sort;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class CustomDividerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f7305a;

    /* renamed from: b, reason: collision with root package name */
    private int f7306b;

    public CustomDividerItemDecoration(Drawable drawable, int i2) {
        this.f7305a = drawable;
        this.f7306b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(0, 0, 0, this.f7305a.getIntrinsicHeight());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int paddingLeft = recyclerView.getPaddingLeft() + this.f7306b;
        int width = (recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.f7306b;
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            int bottom = recyclerView.getChildAt(i2).getBottom();
            this.f7305a.setBounds(paddingLeft, bottom, width, this.f7305a.getIntrinsicHeight() + bottom);
            this.f7305a.draw(canvas);
        }
    }
}
