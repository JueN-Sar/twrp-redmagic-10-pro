package cn.nubia.hostassist;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class InvalidateImageView extends ImageView {
    public boolean m3DModeAlwaysInvalidate;

    public InvalidateImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m3DModeAlwaysInvalidate = false;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m3DModeAlwaysInvalidate) {
            postInvalidate();
        }
    }

    public InvalidateImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.m3DModeAlwaysInvalidate = false;
    }

    public InvalidateImageView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.m3DModeAlwaysInvalidate = false;
    }
}
