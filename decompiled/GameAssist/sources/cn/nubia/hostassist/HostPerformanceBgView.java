package cn.nubia.hostassist;

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
public class HostPerformanceBgView extends BasePerformanceView {
    private static final String TAG = "HostPerformanceBgView";
    public Bitmap mBitmapBottom;
    public Bitmap mBitmapTop;
    public Paint mBottomPaint;
    private double mFraction;
    public float mLeft;
    public Paint mPaint;
    public float mTargetHeight;
    public float mTargetWidth;
    public float mTop;

    public HostPerformanceBgView(Context context, AttributeSet attributeSet) {
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
        if (paint == null || this.mPaint == null) {
            GaLog.b(TAG, "onPerformanceAnimEnd: window has hidden, unable to continue draw!");
            return;
        }
        paint.setAlpha(0);
        this.mPaint.setAlpha(255);
        postInvalidate();
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
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg_host);
            int i3 = this.mPerformancePrevIndex;
            if (i3 == 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg_host);
            } else if (i3 == 2) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg_host);
            } else if (i3 == 4) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.custom_bg_host);
            }
        } else if (i2 == 1) {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg_host);
            int i4 = this.mPerformancePrevIndex;
            if (i4 == 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg_host);
            } else if (i4 == 2) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg_host);
            } else if (i4 == 4) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.custom_bg_host);
            }
        } else if (i2 == 2) {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg_host);
            int i5 = this.mPerformancePrevIndex;
            if (i5 == 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg_host);
            } else if (i5 == 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg_host);
            } else if (i5 == 4) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.custom_bg_host);
            }
        } else if (i2 == 3) {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.infinite_bg_host);
        } else if (i2 != 4) {
            decodeResource = null;
        } else {
            decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.custom_bg_host);
            int i6 = this.mPerformancePrevIndex;
            if (i6 == 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balance_bg_host);
            } else if (i6 == 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rise_bg_host);
            } else if (i6 == 2) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beyond_bg_host);
            }
        }
        if (bitmap != null) {
            this.mBitmapBottom = a(R.drawable.balance_bg_host, bitmap, this.mTargetWidth, this.mTargetHeight);
        }
        if (decodeResource != null) {
            this.mBitmapTop = a(R.drawable.balance_bg_host, decodeResource, this.mTargetWidth, this.mTargetHeight);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        if (this.mIsPerformaceButtonClick && (bitmap = this.mBitmapBottom) != null) {
            canvas.drawBitmap(bitmap, this.mLeft, this.mTop, this.mBottomPaint);
        }
        Bitmap bitmap2 = this.mBitmapTop;
        if (bitmap2 != null) {
            canvas.drawBitmap(bitmap2, this.mLeft, this.mTop, this.mPaint);
        }
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void setViewSize(boolean z) {
        if (z) {
            this.mTargetWidth = 80.0f;
            this.mTargetHeight = 44.0f;
            this.mTop = 50.0f;
            this.mLeft = 11.0f;
            return;
        }
        this.mTargetWidth = 160.0f;
        this.mTargetHeight = 88.0f;
        this.mTop = 100.0f;
        this.mLeft = 22.0f;
    }

    public HostPerformanceBgView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public HostPerformanceBgView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTargetWidth = 80.0f;
        this.mTargetHeight = 44.0f;
        this.mTop = 50.0f;
        this.mLeft = 11.0f;
    }
}
