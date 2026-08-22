package cn.nubia.gamelauncher.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.gamelauncher.recycler.Anim3DHelper;

/* loaded from: classes.dex */
public class BannerView extends ConstraintLayout {
    private Camera mCamera;
    private Matrix mMatrix;
    private float mOffset;
    private float mRotateDeg;
    private float mScale;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMatrix = new Matrix();
        this.mCamera = new Camera();
        Anim3DHelper.setDensity(context.getResources().getDisplayMetrics().density);
    }

    private void drawItem(Canvas canvas, int i, long j) {
        int width = this.mOffset > 0.0f ? 0 : getWidth();
        int height = getHeight() / 2;
        View childAt = getChildAt(i);
        if (childAt == null || childAt.getVisibility() != 0) {
            return;
        }
        Camera camera = this.mCamera;
        Matrix matrix = this.mMatrix;
        canvas.save();
        camera.save();
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        camera.rotateY(this.mRotateDeg);
        camera.getMatrix(matrix);
        camera.restore();
        float f = this.mScale;
        matrix.postScale(f, f);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        fArr[6] = fArr[6] / Anim3DHelper.getDensity();
        fArr[7] = fArr[7] / Anim3DHelper.getDensity();
        matrix.setValues(fArr);
        matrix.preTranslate(-width, -height);
        matrix.postTranslate(width, height);
        canvas.concat(matrix);
        drawChild(canvas, childAt, j);
        canvas.restore();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            drawItem(canvas, i, getDrawingTime());
        }
    }

    public void updateRotate(float f) {
        this.mRotateDeg = Anim3DHelper.getDegreesByOffset(f);
        this.mOffset = f;
        this.mScale = Anim3DHelper.getScaleRateByOffset(f);
        invalidate();
    }
}
