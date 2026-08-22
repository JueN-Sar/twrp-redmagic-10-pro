package com.zte.mifavor.androidx.preference;

import android.R;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f17113a;

    /* renamed from: b, reason: collision with root package name */
    private int f17114b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f17115c;

    /* renamed from: d, reason: collision with root package name */
    private int f17116d;

    /* renamed from: e, reason: collision with root package name */
    private int f17117e;

    private boolean d(View view, RecyclerView recyclerView) {
        RecyclerView.ViewHolder h0 = recyclerView.h0(view);
        boolean z = false;
        if (!(h0 instanceof PreferenceViewHolder) || !((PreferenceViewHolder) h0).P()) {
            return false;
        }
        boolean z2 = this.f17115c;
        int indexOfChild = recyclerView.indexOfChild(view);
        if (indexOfChild >= recyclerView.getChildCount() - 1) {
            return z2;
        }
        RecyclerView.ViewHolder h02 = recyclerView.h0(recyclerView.getChildAt(indexOfChild + 1));
        if ((h02 instanceof PreferenceViewHolder) && ((PreferenceViewHolder) h02).O()) {
            z = true;
        }
        return z;
    }

    public int c(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        Resources resources = view.getContext().getResources();
        int i2 = this.f17116d;
        if (imageView == null || imageView.getVisibility() == 8 || imageView.getDrawable() == null) {
            return i2;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_list_avatar_txt_left_padding);
        boolean z = imageView.getDrawable().getMinimumWidth() >= dimensionPixelSize - (this.f17116d * 2);
        int maxWidth = imageView.getMaxWidth();
        int i3 = this.f17116d;
        boolean z2 = z & (maxWidth >= dimensionPixelSize - (i3 * 2));
        int i4 = this.f17117e;
        return i4 > 0 ? i4 + i3 : z2 ? dimensionPixelSize : resources.getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_1line_list_with_avatar_height_edge);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (d(view, recyclerView)) {
            rect.bottom = this.f17114b;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.f17113a == null) {
            return;
        }
        int childCount = recyclerView.getChildCount();
        int width = recyclerView.getWidth();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (d(childAt, recyclerView)) {
                int y = ((int) childAt.getY()) + childAt.getHeight();
                this.f17113a.setBounds(c(childAt), y, width - this.f17116d, this.f17114b + y);
                this.f17113a.draw(canvas);
            }
        }
    }
}
