package androidx.recyclerview.widget;

import android.R;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f4996d = {R.attr.listDivider};

    /* renamed from: a, reason: collision with root package name */
    private Drawable f4997a;

    /* renamed from: b, reason: collision with root package name */
    private int f4998b;

    /* renamed from: c, reason: collision with root package name */
    private final Rect f4999c;

    private void c(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int i2;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i2, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            i2 = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = recyclerView.getChildAt(i3);
            recyclerView.getLayoutManager().V(childAt, this.f4999c);
            int round = this.f4999c.right + Math.round(childAt.getTranslationX());
            this.f4997a.setBounds(round - this.f4997a.getIntrinsicWidth(), i2, round, height);
            this.f4997a.draw(canvas);
        }
        canvas.restore();
    }

    private void d(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int i2;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i2, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            i2 = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = recyclerView.getChildAt(i3);
            recyclerView.j0(childAt, this.f4999c);
            int round = this.f4999c.bottom + Math.round(childAt.getTranslationY());
            this.f4997a.setBounds(i2, round - this.f4997a.getIntrinsicHeight(), width, round);
            this.f4997a.draw(canvas);
        }
        canvas.restore();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Drawable drawable = this.f4997a;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.f4998b == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getLayoutManager() == null || this.f4997a == null) {
            return;
        }
        if (this.f4998b == 1) {
            d(canvas, recyclerView);
        } else {
            c(canvas, recyclerView);
        }
    }
}
