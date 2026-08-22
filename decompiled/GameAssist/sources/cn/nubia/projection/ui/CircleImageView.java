package cn.nubia.projection.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class CircleImageView extends ImageView {
    private int height;
    private Paint paint;
    private Path path;
    private Path srcPath;
    private RectF srcRectF;
    private int width;
    private Xfermode xfermode;

    public CircleImageView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.path = new Path();
        this.paint = new Paint();
        this.xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.srcPath = new Path();
    }

    private void a() {
        RectF rectF = new RectF();
        this.srcRectF = rectF;
        rectF.set(0.0f, 0.0f, this.width, this.height);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(this.srcRectF, null, 31);
        super.onDraw(canvas);
        this.path.reset();
        this.paint.reset();
        Path path = this.path;
        int i2 = this.width;
        Path.Direction direction = Path.Direction.CCW;
        path.addCircle(i2 / 2, this.height / 2, i2 / 2, direction);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setXfermode(this.xfermode);
        this.srcPath.reset();
        this.srcPath.addRect(this.srcRectF, direction);
        this.srcPath.op(this.path, Path.Op.DIFFERENCE);
        canvas.drawPath(this.srcPath, this.paint);
        this.paint.setXfermode(null);
        canvas.restore();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.width = i2;
        this.height = i3;
        a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
