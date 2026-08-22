package cn.nubia.gameassist.performance;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BasePerformanceView;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceBgView extends BasePerformanceView {
    private static final String TAG = "PerformanceBgView";
    private Bitmap mBitmapBottom;
    private Bitmap mBitmapTop;
    private Paint mBottomPaint;
    private double mFraction;
    private Paint mPaint;
    private float mTargetHeight;
    private float mTargetWidth;
    private float mTop;

    public PerformanceBgView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void b() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setFilterBitmap(true);
        Paint paint2 = new Paint();
        this.mBottomPaint = paint2;
        paint2.setAntiAlias(true);
        this.mBottomPaint.setDither(true);
        this.mBottomPaint.setFilterBitmap(true);
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void c() {
        Paint paint = this.mBottomPaint;
        if (paint != null && this.mPaint != null) {
            paint.setAlpha(0);
            this.mPaint.setAlpha(255);
            postInvalidate();
        } else {
            GaLog.b(TAG, "onPerformanceAnimEnd: window has hidden, unable to continue draw!  mBottomPaint = " + this.mBottomPaint + " , mPaint = " + this.mPaint);
        }
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void d(double d2) {
        Paint paint = this.mBottomPaint;
        if (paint == null || this.mPaint == null) {
            GaLog.b(TAG, "onPerformanceAnimUpdate: window has hidden, unable to continue draw!");
            return;
        }
        double d3 = d2 * 255.0d;
        paint.setAlpha((int) (255.0d - d3));
        this.mPaint.setAlpha((int) d3);
        if (this.mFraction != d2) {
            this.mFraction = d2;
            postInvalidate();
        }
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void f() {
        super.f();
        postInvalidate();
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void h() {
        super.h();
        this.mBitmapBottom = null;
        this.mBitmapTop = null;
        this.mPaint = null;
        this.mBottomPaint = null;
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void i() {
        Bitmap decodeResource;
        int i2 = this.mPerformanceIndex;
        Bitmap bitmap = null;
        if (i2 == 0) {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg);
            int i3 = this.mPerformancePrevIndex;
            if (i3 == 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg);
            } else if (i3 == 2) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg);
            }
        } else if (i2 == 1) {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg);
            int i4 = this.mPerformancePrevIndex;
            if (i4 == 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg);
            } else if (i4 == 2) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg);
            }
        } else if (i2 != 2) {
            decodeResource = i2 != 3 ? null : BitmapFactory.decodeResource(getResources(), R.drawable.infinite_bg);
        } else {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg);
            int i5 = this.mPerformancePrevIndex;
            if (i5 == 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg);
            } else if (i5 == 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg);
            }
        }
        if (bitmap != null) {
            this.mBitmapBottom = a(R.drawable.balance_bg, bitmap, this.mTargetWidth, this.mTargetHeight);
        }
        if (decodeResource != null) {
            this.mBitmapTop = a(R.drawable.balance_bg, decodeResource, this.mTargetWidth, this.mTargetHeight);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        if (this.mIsPerformaceButtonClick && (bitmap = this.mBitmapBottom) != null) {
            canvas.drawBitmap(bitmap, 0.0f, this.mTop, this.mBottomPaint);
        }
        Bitmap bitmap2 = this.mBitmapTop;
        if (bitmap2 != null) {
            canvas.drawBitmap(bitmap2, 0.0f, this.mTop, this.mPaint);
        }
    }

    public void setParams(int i2) {
        if (i2 == 1) {
            this.mTargetWidth = 104.0f;
            this.mTargetHeight = 60.0f;
            this.mTop = 40.0f;
        } else {
            this.mTargetWidth = 246.0f;
            this.mTargetHeight = 150.0f;
            this.mTop = 70.0f;
        }
    }

    public PerformanceBgView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PerformanceBgView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTargetWidth = 246.0f;
        this.mTargetHeight = 150.0f;
        this.mTop = 70.0f;
    }
}
