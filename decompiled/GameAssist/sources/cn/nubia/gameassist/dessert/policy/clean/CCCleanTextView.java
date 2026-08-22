package cn.nubia.gameassist.dessert.policy.clean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes.dex */
public class CCCleanTextView extends TextView {
    private float mIconTrans;
    private final Path mPath;

    public CCCleanTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(this.mPath);
        super.draw(canvas);
        canvas.restore();
    }

    public void setClipTrans(float f2) {
        this.mIconTrans = f2;
        this.mPath.reset();
        this.mPath.moveTo(this.mIconTrans, 0.0f);
        Path path = this.mPath;
        float f3 = this.mIconTrans;
        path.cubicTo(f3 - 31.0f, 30.0f, f3 - 31.0f, getHeight() - 30, this.mIconTrans, getHeight());
        this.mPath.lineTo(getWidth(), getHeight());
        this.mPath.lineTo(getWidth(), 0.0f);
        this.mPath.close();
        invalidate();
    }

    public CCCleanTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPath = new Path();
        setWillNotDraw(false);
        setLayerType(1, null);
    }
}
