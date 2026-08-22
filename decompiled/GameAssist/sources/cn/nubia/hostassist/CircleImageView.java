package cn.nubia.hostassist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class CircleImageView extends ImageView {
    private final Path mClipPath;
    private float mRotation;

    public CircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClipPath = new Path();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRotation != 0.0f) {
            int save = canvas.save();
            canvas.clipPath(this.mClipPath);
            canvas.rotate(this.mRotation, getWidth() / 2.0f, getHeight() / 2.0f);
            super.onDraw(canvas);
            canvas.restoreToCount(save);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        int i6 = i2 < i3 ? i2 : i3;
        this.mClipPath.reset();
        this.mClipPath.addCircle(i2 / 2, i3 / 2, i6 / 2, Path.Direction.CCW);
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        if (f2 != this.mRotation) {
            this.mRotation = f2;
            postInvalidate();
        }
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mClipPath = new Path();
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mClipPath = new Path();
    }
}
