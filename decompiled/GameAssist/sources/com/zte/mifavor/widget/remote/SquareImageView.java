package com.zte.mifavor.widget.remote;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes2.dex */
public class SquareImageView extends AppCompatImageView {
    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth);
    }

    public SquareImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
