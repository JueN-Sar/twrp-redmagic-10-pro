package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PathImageView extends View {
    private Paint cropPaint;
    private Bitmap croppedBitmap;
    private int flag;
    private Paint linePaint;
    private Bitmap originalBitmap;
    private Path path;
    private List<Point> points;

    public PathImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.flag = 0;
        b();
    }

    private Bitmap a(Bitmap bitmap) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 480, 900, true);
        Bitmap createBitmap = Bitmap.createBitmap(480, 900, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Path path = new Path();
        this.path = path;
        path.moveTo(this.points.get(0).x, this.points.get(0).y);
        for (int i2 = 1; i2 < this.points.size(); i2++) {
            this.path.lineTo(this.points.get(i2).x, this.points.get(i2).y);
        }
        this.path.lineTo(480.0f, 0.0f);
        this.path.lineTo(0.0f, 0.0f);
        this.path.close();
        canvas.drawBitmap(createScaledBitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawPath(this.path, this.cropPaint);
        this.cropPaint.setXfermode(null);
        return createBitmap;
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        this.points = arrayList;
        arrayList.add(new Point(0, 100));
        this.points.add(new Point(200, 300));
        this.points.add(new Point(400, 150));
        this.points.add(new Point(600, 400));
        Paint paint = new Paint(1);
        this.cropPaint = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.cropPaint.setColor(-65536);
        Paint paint2 = new Paint();
        this.linePaint = paint2;
        paint2.setColor(-65536);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeWidth(10.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.croppedBitmap != null) {
            int save = canvas.save();
            canvas.drawBitmap(this.croppedBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restoreToCount(save);
        }
        if (this.flag == 2) {
            Path path = new Path();
            this.path = path;
            path.moveTo(this.points.get(0).x, this.points.get(0).y);
            for (int i2 = 1; i2 < this.points.size(); i2++) {
                this.path.lineTo(this.points.get(i2).x, this.points.get(i2).y);
            }
            canvas.drawPath(this.path, this.linePaint);
        }
    }

    public void setFlag(int i2) {
        this.flag = i2;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.originalBitmap = bitmap;
        this.croppedBitmap = a(bitmap);
        invalidate();
    }

    public PathImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.flag = 0;
        b();
    }
}
