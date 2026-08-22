package cn.nubia.gameassist.performance;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BasePerformanceView;
import cn.nubia.gameassist.performance.PerformanceCircleView;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.Constants;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceCircleView extends BasePerformanceView {
    private static final int ANGLE_LIMIT = 180;
    private static final int ANGLE_POINTER_START = 210;
    private static float CircleViewText_height = 70.0f;
    private static float CircleViewText_width_offset = 5.0f;
    private static final int MASK_INITIAL_ANGLE = 180;
    private static float NUM_SIZE = 60.0f;
    private static float PerformanceText_height_offset = 20.0f;
    private static float PerformanceText_width_offset = 8.0f;
    private static float SCALE_SIZE = 213.0f;
    private static final String TAG = "PerformanceCircleView";
    private static float TYPE_SIZE = 27.0f;
    private static float unit_height_offset = 50.0f;
    private static float unit_width_offset = 5.0f;
    private final String BOOST;
    private final int MAX_DISPLAYED;
    private boolean isCpuType;
    private AnimatorListenerAdapter mAnimatorListenerAdapter;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    private Handler mBackHandler;
    private Bitmap mBitmapDst;
    private Bitmap mBitmapPointer;
    private Bitmap mBitmapPointerBottom;
    private Bitmap mBitmapSrc;
    private Bitmap mBitmapSrcBottom;
    private Paint mBottomPaint;
    int mBottomPaintAlpha;
    private ValueAnimator mCpuAnimator;
    private float mCpuCurF;
    private float mCpuEndSweepAngle;
    private Runnable mCpuRefreshRunnable;
    private float mCpuSweepAngle;
    private float mCpuSweepingAngle;
    private Paint mDstPaint;
    private double mFraction;
    private ValueAnimator mGpuAnimator;
    private float mGpuCurF;
    private float mGpuEndSweepAngle;
    private Runnable mGpuRefreshRunnable;
    private float mGpuSweepAngle;
    private float mGpuSweepingAngle;
    private float mMaxCpu;
    private float mMaxGpu;
    private Paint mNumPaint;
    private Paint mPaint;
    private String mPerformanceCircleViewText;
    private String mPerformanceText;
    private Paint mPointerPaint;
    int mPointerPaintAlpha;
    private Paint mPointerPaintBottom;
    private float mStartAngle;
    private Paint mTypePaint;
    private Handler mUIHandler;

    /* renamed from: cn.nubia.gameassist.performance.PerformanceCircleView$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            PerformanceCircleView.this.R();
        }

        @Override // java.lang.Runnable
        public void run() {
            float parseFloat = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq"));
            float parseFloat2 = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq"));
            float parseFloat3 = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
            if (PerformanceCircleView.this.mMaxCpu <= 0.0f) {
                PerformanceCircleView.this.mMaxCpu = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/cpuinfo_max_freq"));
            }
            float max = Math.max(parseFloat, Math.max(parseFloat2, parseFloat3));
            if (PerformanceCircleView.this.mMaxCpu <= 0.0f || max < 0.0f) {
                return;
            }
            if (max != PerformanceCircleView.this.mCpuCurF) {
                PerformanceCircleView.this.mCpuCurF = max;
                PerformanceCircleView.this.mCpuSweepAngle = (max / PerformanceCircleView.this.mMaxCpu) * 180.0f;
                PerformanceCircleView performanceCircleView = PerformanceCircleView.this;
                performanceCircleView.mCpuSweepingAngle = performanceCircleView.mCpuSweepAngle;
                PerformanceCircleView.this.mUIHandler.post(new Runnable() { // from class: cn.nubia.gameassist.performance.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        PerformanceCircleView.AnonymousClass1.this.b();
                    }
                });
            }
            if (PerformanceCircleView.this.mBackHandler != null) {
                PerformanceCircleView.this.mBackHandler.postDelayed(PerformanceCircleView.this.mCpuRefreshRunnable, 1000L);
            }
        }
    }

    /* renamed from: cn.nubia.gameassist.performance.PerformanceCircleView$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            PerformanceCircleView.this.R();
        }

        @Override // java.lang.Runnable
        public void run() {
            float parseFloat = Float.parseFloat(Utils.l(Constants.f16470j));
            if (PerformanceCircleView.this.mMaxGpu < 0.0f) {
                PerformanceCircleView.this.mMaxGpu = Float.parseFloat(Utils.l(Constants.f16471k));
            }
            if (PerformanceCircleView.this.mMaxGpu < 0.0f || parseFloat < 0.0f) {
                return;
            }
            if (parseFloat != PerformanceCircleView.this.mGpuCurF) {
                PerformanceCircleView.this.mGpuCurF = parseFloat;
                PerformanceCircleView.this.mGpuSweepAngle = (parseFloat / PerformanceCircleView.this.mMaxGpu) * 180.0f;
                PerformanceCircleView performanceCircleView = PerformanceCircleView.this;
                performanceCircleView.mGpuSweepingAngle = performanceCircleView.mGpuSweepAngle;
                PerformanceCircleView.this.mUIHandler.post(new Runnable() { // from class: cn.nubia.gameassist.performance.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        PerformanceCircleView.AnonymousClass2.this.b();
                    }
                });
            }
            if (PerformanceCircleView.this.mBackHandler != null) {
                PerformanceCircleView.this.mBackHandler.postDelayed(PerformanceCircleView.this.mGpuRefreshRunnable, 1000L);
            }
        }
    }

    public PerformanceCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void I(Canvas canvas) {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (this.mPaint == null || (bitmap = this.mBitmapDst) == null || this.mBitmapSrc == null || this.mBitmapPointer == null) {
            GaLog.k(TAG, "drawBitmapWithXfermode,view not init. mPaint = " + this.mPaint + " , mBitmapDst = " + this.mBitmapDst + " , mBitmapSrc = " + this.mBitmapSrc + " , mBitmapPointer = " + this.mBitmapPointer);
            return;
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mDstPaint);
        if (this.mIsPerformaceButtonClick && (bitmap2 = this.mBitmapSrcBottom) != null) {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, bitmap2.getWidth(), this.mBitmapSrcBottom.getHeight(), null, 31);
            this.mBottomPaintAlpha = this.mBottomPaint.getAlpha();
            J(canvas, this.mBitmapSrcBottom, this.mBottomPaint);
            canvas.restoreToCount(saveLayer);
        }
        int saveLayer2 = canvas.saveLayer(0.0f, 0.0f, this.mBitmapSrc.getWidth(), this.mBitmapSrc.getHeight(), null, 31);
        this.mPaint.setAlpha(255 - this.mBottomPaintAlpha);
        J(canvas, this.mBitmapSrc, this.mPaint);
        canvas.restoreToCount(saveLayer2);
        int save = canvas.save();
        if (this.mIsPerformaceButtonClick && this.mBitmapPointerBottom != null) {
            this.mPointerPaintAlpha = this.mPointerPaintBottom.getAlpha();
            K(canvas, this.mBitmapPointerBottom, this.mPointerPaintBottom);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        this.mPointerPaint.setAlpha(255 - this.mPointerPaintAlpha);
        K(canvas, this.mBitmapPointer, this.mPointerPaint);
        canvas.restoreToCount(save2);
        canvas.drawText(this.mPerformanceCircleViewText, this.isCpuType ? (this.mBitmapSrc.getWidth() / 2) + CircleViewText_width_offset : (this.mBitmapSrc.getWidth() / 2) - CircleViewText_width_offset, CircleViewText_height, this.mTypePaint);
        canvas.drawText(this.mPerformanceText, this.isCpuType ? (this.mBitmapSrc.getWidth() / 2) + PerformanceText_width_offset : (this.mBitmapSrc.getWidth() / 2) - PerformanceText_width_offset, (this.mBitmapSrc.getHeight() / 2) + PerformanceText_height_offset, this.mNumPaint);
        boolean z = this.isCpuType;
        canvas.drawText(z ? "GHz" : "MHz", z ? (this.mBitmapSrc.getWidth() / 2) + unit_width_offset : (this.mBitmapSrc.getWidth() / 2) - unit_width_offset, this.mBitmapSrc.getHeight() - unit_height_offset, this.mTypePaint);
    }

    private void J(Canvas canvas, Bitmap bitmap, Paint paint) {
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        paint.setAlpha(255);
        if (this.isCpuType) {
            canvas.drawArc(rectF, this.mStartAngle, this.mCpuSweepAngle - 180.0f, true, paint);
        } else {
            canvas.drawArc(rectF, this.mStartAngle, 180.0f - this.mGpuSweepAngle, true, paint);
        }
        paint.setAlpha(this.mBottomPaintAlpha);
        paint.setXfermode(null);
    }

    private void K(Canvas canvas, Bitmap bitmap, Paint paint) {
        if (this.isCpuType) {
            canvas.rotate(this.mCpuSweepAngle + 210.0f, this.mBitmapSrc.getWidth() / 2, this.mBitmapSrc.getHeight() / 2);
        } else {
            canvas.rotate((-210.0f) - this.mGpuSweepAngle, this.mBitmapSrc.getWidth() / 2, this.mBitmapSrc.getHeight() / 2);
        }
        canvas.drawBitmap(bitmap, -S(bitmap.getWidth(), this.mBitmapSrc.getWidth()), -S(bitmap.getHeight(), this.mBitmapSrc.getHeight()), paint);
    }

    private Bitmap L(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap N(Bitmap bitmap) {
        int i2 = R.drawable.dst_gray_right;
        float f2 = SCALE_SIZE;
        return a(i2, bitmap, f2, f2);
    }

    private void O() {
        if (this.mCpuRefreshRunnable == null) {
            this.mCpuRefreshRunnable = new AnonymousClass1();
        }
        this.mBackHandler.post(this.mCpuRefreshRunnable);
    }

    private void P() {
        if (this.mGpuRefreshRunnable == null) {
            this.mGpuRefreshRunnable = new AnonymousClass2();
        }
        this.mBackHandler.post(this.mGpuRefreshRunnable);
    }

    private void Q() {
        GaLog.e(TAG, "reset");
        this.mBitmapSrc = null;
        this.mBitmapDst = null;
        this.mBitmapPointer = null;
        this.mPaint = null;
        this.mTypePaint = null;
        this.mNumPaint = null;
        this.mBottomPaint = null;
        this.mPointerPaint = null;
        this.mPointerPaintBottom = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        if (this.isCpuType) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mCpuEndSweepAngle, this.mCpuSweepAngle);
            this.mCpuAnimator = ofFloat;
            ofFloat.setDuration(500L);
            this.mCpuAnimator.addListener(this.mAnimatorListenerAdapter);
            this.mCpuAnimator.addUpdateListener(this.mAnimatorUpdateListener);
            if (this.mCpuAnimator.isRunning()) {
                this.mCpuAnimator.cancel();
            }
            this.mCpuAnimator.start();
            return;
        }
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(this.mGpuEndSweepAngle, this.mGpuSweepAngle);
        this.mGpuAnimator = ofFloat2;
        ofFloat2.setDuration(500L);
        this.mGpuAnimator.addListener(this.mAnimatorListenerAdapter);
        this.mGpuAnimator.addUpdateListener(this.mAnimatorUpdateListener);
        if (this.mGpuAnimator.isRunning()) {
            this.mGpuAnimator.cancel();
        }
        this.mGpuAnimator.start();
    }

    private float S(float f2, float f3) {
        return (f2 - f3) / 2.0f;
    }

    public String M(float f2) {
        return String.format("%.0f", Float.valueOf(f2 / 1000000.0f));
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void b() {
        this.mBackHandler = new Handler(ThreadManager.c().b());
        this.mUIHandler = new Handler();
        Bitmap N = N(BitmapFactory.decodeResource(getResources(), R.drawable.dst_gray_right));
        if (this.isCpuType) {
            N = L(N);
        }
        this.mBitmapDst = N;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setFilterBitmap(true);
        Paint paint2 = new Paint();
        this.mDstPaint = paint2;
        paint2.setAntiAlias(true);
        this.mDstPaint.setDither(true);
        this.mDstPaint.setFilterBitmap(true);
        Paint paint3 = new Paint();
        this.mPointerPaint = paint3;
        paint3.setAntiAlias(true);
        this.mPointerPaint.setDither(true);
        this.mPointerPaint.setFilterBitmap(true);
        Paint paint4 = new Paint();
        this.mPointerPaintBottom = paint4;
        paint4.setAntiAlias(true);
        this.mPointerPaintBottom.setDither(true);
        this.mPointerPaintBottom.setFilterBitmap(true);
        Paint paint5 = new Paint();
        this.mBottomPaint = paint5;
        paint5.setAntiAlias(true);
        this.mBottomPaint.setDither(true);
        this.mBottomPaint.setFilterBitmap(true);
        Paint paint6 = new Paint();
        this.mTypePaint = paint6;
        paint6.setTextSize(TYPE_SIZE);
        this.mTypePaint.setColor(Color.parseColor("#FFFFFFFF"));
        Paint paint7 = this.mTypePaint;
        Paint.Align align = Paint.Align.CENTER;
        paint7.setTextAlign(align);
        this.mTypePaint.setAntiAlias(true);
        this.mTypePaint.setDither(true);
        this.mTypePaint.setFilterBitmap(true);
        Paint paint8 = new Paint();
        this.mNumPaint = paint8;
        paint8.setTextSize(NUM_SIZE);
        this.mNumPaint.setColor(Color.parseColor("#FFEBEBEB"));
        this.mNumPaint.setTypeface(Utils.h("YouSheBiaoTiHei-2.ttf"));
        this.mNumPaint.setTextAlign(align);
        this.mNumPaint.setAntiAlias(true);
        this.mNumPaint.setDither(true);
        this.mNumPaint.setFilterBitmap(true);
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void c() {
        Paint paint = this.mBottomPaint;
        if (paint != null && this.mPointerPaintBottom != null && this.mPaint != null && this.mPointerPaint != null) {
            paint.setAlpha(0);
            this.mPointerPaintBottom.setAlpha(0);
            this.mPaint.setAlpha(255);
            this.mPointerPaint.setAlpha(255);
            postInvalidate();
            return;
        }
        GaLog.b(TAG, "onPerformanceAnimEnd: window has hidden, unable to continue draw! , mBottomPaint = " + this.mBottomPaint + " , mPointerPaintBottom = " + this.mPointerPaintBottom + " , mPaint = " + this.mPaint + " , mPointerPaint = " + this.mPointerPaint);
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void d(double d2) {
        Paint paint;
        if (this.mBottomPaint == null || (paint = this.mPointerPaintBottom) == null) {
            GaLog.b(TAG, "onPerformanceAnimUpdate: window has hidden, unable to continue draw! , mBottomPaint = " + this.mBottomPaint + " , mPointerPaintBottom = " + this.mPointerPaintBottom);
            return;
        }
        int i2 = (int) (255.0d - (d2 * 255.0d));
        paint.setAlpha(i2);
        this.mBottomPaint.setAlpha(i2);
        if (this.mFraction != d2) {
            this.mFraction = d2;
            postInvalidate();
        }
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void f() {
        super.f();
        if (this.isCpuType) {
            O();
            this.mStartAngle = -60.0f;
        } else {
            P();
            this.mStartAngle = -120.0f;
        }
        postInvalidate();
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void h() {
        super.h();
        this.mBottomPaintAlpha = 0;
        this.mPointerPaintAlpha = 0;
        ValueAnimator valueAnimator = this.mCpuAnimator;
        if (valueAnimator != null) {
            if (valueAnimator.isRunning()) {
                this.mCpuAnimator.cancel();
            }
            this.mCpuAnimator = null;
        }
        ValueAnimator valueAnimator2 = this.mGpuAnimator;
        if (valueAnimator2 != null) {
            if (valueAnimator2.isRunning()) {
                this.mGpuAnimator.cancel();
            }
            this.mGpuAnimator = null;
        }
        Handler handler = this.mBackHandler;
        if (handler != null) {
            Runnable runnable = this.mCpuRefreshRunnable;
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
            Runnable runnable2 = this.mGpuRefreshRunnable;
            if (runnable2 != null) {
                this.mBackHandler.removeCallbacks(runnable2);
            }
        }
        Q();
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void i() {
        Bitmap N;
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap N2;
        Bitmap N3;
        int i2 = this.mPerformanceIndex;
        if (i2 < 0) {
            GaLog.k(TAG, "updateBitmapByIndex, mPerformanceIndex not init," + this.mPerformanceIndex);
            return;
        }
        Bitmap bitmap3 = null;
        if (i2 == 0) {
            N = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right));
            Bitmap N4 = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance));
            int i3 = this.mPerformancePrevIndex;
            if (i3 == 1) {
                bitmap3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right));
                bitmap = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise));
            } else if (i3 == 2) {
                bitmap3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right));
                bitmap = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond));
            } else {
                bitmap = null;
            }
            bitmap2 = N4;
        } else if (i2 != 1) {
            if (i2 == 2) {
                N = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right));
                N2 = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond));
                int i4 = this.mPerformancePrevIndex;
                if (i4 == 0) {
                    bitmap3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right));
                    N3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance));
                } else {
                    if (i4 == 1) {
                        bitmap3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right));
                        N3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise));
                    }
                    bitmap2 = N2;
                }
                Bitmap bitmap4 = N3;
                bitmap2 = N2;
                bitmap = bitmap4;
            } else if (i2 != 3) {
                N = null;
                bitmap = null;
                bitmap2 = null;
            } else {
                N = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_infinite_right));
                N2 = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_infinite));
                bitmap2 = N2;
            }
            bitmap = null;
        } else {
            N = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right));
            bitmap2 = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise));
            int i5 = this.mPerformancePrevIndex;
            if (i5 == 0) {
                bitmap3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right));
                bitmap = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance));
            } else {
                if (i5 == 2) {
                    bitmap3 = N(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right));
                    bitmap = N(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond));
                }
                bitmap = null;
            }
        }
        if (bitmap3 != null) {
            if (this.isCpuType) {
                bitmap3 = L(bitmap3);
            }
            this.mBitmapSrcBottom = bitmap3;
        }
        if (this.isCpuType) {
            N = L(N);
        }
        this.mBitmapSrc = N;
        if (bitmap != null) {
            this.mBitmapPointerBottom = bitmap;
        }
        this.mBitmapPointer = bitmap2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        I(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Bitmap bitmap = this.mBitmapSrc;
        if (bitmap != null) {
            setMeasuredDimension(bitmap.getWidth(), this.mBitmapSrc.getHeight());
        }
    }

    public PerformanceCircleView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PerformanceCircleView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mPointerPaintAlpha = 0;
        this.mBottomPaintAlpha = 0;
        this.mPerformanceText = "";
        this.BOOST = "Boost";
        this.MAX_DISPLAYED = 587;
        this.mMaxCpu = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/cpuinfo_max_freq"));
        this.mMaxGpu = Float.parseFloat(Utils.l(Constants.f16471k));
        this.mCpuAnimator = null;
        this.mGpuAnimator = null;
        this.mAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: cn.nubia.gameassist.performance.PerformanceCircleView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (PerformanceCircleView.this.isCpuType) {
                    PerformanceCircleView performanceCircleView = PerformanceCircleView.this;
                    performanceCircleView.mCpuEndSweepAngle = performanceCircleView.mCpuSweepAngle;
                    PerformanceCircleView performanceCircleView2 = PerformanceCircleView.this;
                    performanceCircleView2.mPerformanceText = String.format("%.2f", Float.valueOf(performanceCircleView2.mCpuCurF / 1000000.0f));
                    return;
                }
                PerformanceCircleView performanceCircleView3 = PerformanceCircleView.this;
                performanceCircleView3.mGpuEndSweepAngle = performanceCircleView3.mGpuSweepAngle;
                PerformanceCircleView performanceCircleView4 = PerformanceCircleView.this;
                performanceCircleView4.mPerformanceText = performanceCircleView4.M(performanceCircleView4.mGpuCurF);
            }
        };
        this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.performance.PerformanceCircleView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (PerformanceCircleView.this.isCpuType) {
                    PerformanceCircleView.this.mCpuSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f2 = PerformanceCircleView.this.mCpuSweepAngle / PerformanceCircleView.this.mCpuSweepingAngle;
                    PerformanceCircleView performanceCircleView = PerformanceCircleView.this;
                    performanceCircleView.mPerformanceText = String.format("%.2f", Float.valueOf((performanceCircleView.mCpuCurF * f2) / 1000000.0f));
                } else {
                    PerformanceCircleView.this.mGpuSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f3 = PerformanceCircleView.this.mGpuSweepAngle / PerformanceCircleView.this.mGpuSweepingAngle;
                    PerformanceCircleView performanceCircleView2 = PerformanceCircleView.this;
                    performanceCircleView2.mPerformanceText = performanceCircleView2.M(performanceCircleView2.mGpuCurF * f3);
                }
                PerformanceCircleView.this.postInvalidate();
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.performanceView);
        this.isCpuType = obtainStyledAttributes.getBoolean(R.styleable.performanceView_isCpuType, false);
        this.mPerformanceCircleViewText = obtainStyledAttributes.getString(R.styleable.performanceView_text);
        obtainStyledAttributes.recycle();
    }
}
