package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes2.dex */
public class ListViewZTE extends android.widget.ListView {
    private int mMaxWidth;
    private int mMinWidth;

    public ListViewZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    private int a() {
        View view = null;
        int i2 = 0;
        for (int i3 = 0; i3 < getAdapter().getCount(); i3++) {
            view = getAdapter().getView(i3, view, this);
            view.measure(0, 0);
            if (view.getMeasuredWidth() > i2) {
                i2 = view.getMeasuredWidth();
            }
        }
        return i2;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int a2 = a() + getPaddingLeft() + getPaddingRight();
        int i4 = this.mMaxWidth;
        if (a2 > i4 || a2 < (i4 = this.mMinWidth)) {
            a2 = i4;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(a2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
    }

    public ListViewZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListViewZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mMinWidth = Utils.c(getContext(), 112);
        this.mMaxWidth = Utils.c(getContext(), 320);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{com.zte.extres.R.attr.mfvMinWidth, com.zte.extres.R.attr.mfvMaxWidth}, i2, i3);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(0, this.mMinWidth);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(1, this.mMaxWidth);
        obtainStyledAttributes.recycle();
    }
}
