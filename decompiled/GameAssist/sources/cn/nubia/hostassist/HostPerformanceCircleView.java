package cn.nubia.hostassist;

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
import cn.nubia.gameassist.performance.GamePerformanceViewController;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.hostassist.HostPerformanceCircleView;
import com.zte.gameassist.common.Constants;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class HostPerformanceCircleView extends BasePerformanceView {
    private static final int ANGLE_LIMIT = 180;
    private static final int ANGLE_POINTER_START = 210;
    private static float CircleViewText_height = 25.0f;
    private static float CircleViewText_width_offset = 5.0f;
    private static final int MASK_INITIAL_ANGLE = 180;
    private static final float NUM_SIZE = 26.0f;
    private static final float NUM_SIZE_PORT = 52.0f;
    private static float PerformanceText_height_offset = 10.0f;
    private static float PerformanceText_width_offset = 4.0f;
    private static final String TAG = "HostPerformanceCircleView";
    private static final float TYPE_SIZE = 10.0f;
    private static final float TYPE_SIZE_PORT = 20.0f;
    private static float mPionterScaleSize = 100.0f;
    private static float mScaleSize = 80.0f;
    private static float unit_height_offset = 15.0f;
    private static float unit_width_offset = 3.0f;
    private final String BOOST;
    private final int MAX_DISPLAYED;
    private boolean isCpuType;
    private AnimatorListenerAdapter mAnimatorListenerAdapter;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    public Handler mBackHandler;
    private Bitmap mBitmapDst;
    private Bitmap mBitmapPointer;
    private Bitmap mBitmapPointerBottom;
    public Bitmap mBitmapSrc;
    private Bitmap mBitmapSrcBottom;
    private Paint mBottomPaint;
    int mBottomPaintAlpha;
    public ValueAnimator mCpuAnimator;
    private float mCpuCurF;
    private float mCpuEndSweepAngle;
    private Runnable mCpuRefreshRunnable;
    private float mCpuSweepAngle;
    private float mCpuSweepingAngle;
    private Paint mDstPaint;
    private double mFraction;
    private GamePerformanceViewController mGamePerformanceViewController;
    public ValueAnimator mGpuAnimator;
    private float mGpuCurF;
    private float mGpuEndSweepAngle;
    private Runnable mGpuRefreshRunnable;
    private float mGpuSweepAngle;
    private float mGpuSweepingAngle;
    private boolean mIsHorizontal;
    private float mMaxCpu;
    private float mMaxGpu;
    public Paint mNumPaint;
    private Paint mPaint;
    private String mPerformanceCircleViewText;
    private String mPerformanceText;
    private Paint mPointerPaint;
    int mPointerPaintAlpha;
    private Paint mPointerPaintBottom;
    private float mStartAngle;
    public Paint mTypePaint;
    public Handler mUIHandler;

    /* renamed from: cn.nubia.hostassist.HostPerformanceCircleView$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            HostPerformanceCircleView.this.Q();
        }

        @Override // java.lang.Runnable
        public void run() {
            float parseFloat = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq"));
            float parseFloat2 = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq"));
            float parseFloat3 = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
            if (HostPerformanceCircleView.this.mMaxCpu <= 0.0f) {
                HostPerformanceCircleView.this.mMaxCpu = Float.parseFloat(Utils.l("/sys/devices/system/cpu/cpu7/cpufreq/cpuinfo_max_freq"));
            }
            float max = Math.max(parseFloat, Math.max(parseFloat2, parseFloat3));
            if (HostPerformanceCircleView.this.mMaxCpu <= 0.0f || max < 0.0f) {
                return;
            }
            if (max != HostPerformanceCircleView.this.mCpuCurF) {
                HostPerformanceCircleView.this.mCpuCurF = max;
                HostPerformanceCircleView.this.mCpuSweepAngle = (max / HostPerformanceCircleView.this.mMaxCpu) * 180.0f;
                HostPerformanceCircleView hostPerformanceCircleView = HostPerformanceCircleView.this;
                hostPerformanceCircleView.mCpuSweepingAngle = hostPerformanceCircleView.mCpuSweepAngle;
                HostPerformanceCircleView.this.mUIHandler.post(new Runnable() { // from class: cn.nubia.hostassist.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        HostPerformanceCircleView.AnonymousClass1.this.b();
                    }
                });
            }
            HostPerformanceCircleView hostPerformanceCircleView2 = HostPerformanceCircleView.this;
            Handler handler = hostPerformanceCircleView2.mBackHandler;
            if (handler != null) {
                handler.postDelayed(hostPerformanceCircleView2.mCpuRefreshRunnable, 1000L);
            }
        }
    }

    /* renamed from: cn.nubia.hostassist.HostPerformanceCircleView$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            HostPerformanceCircleView.this.Q();
        }

        @Override // java.lang.Runnable
        public void run() {
            float parseFloat = Float.parseFloat(Utils.l(Constants.f16470j));
            if (HostPerformanceCircleView.this.mMaxGpu < 0.0f) {
                HostPerformanceCircleView.this.mMaxGpu = Float.parseFloat(Utils.l(Constants.f16471k));
            }
            if (HostPerformanceCircleView.this.mMaxGpu < 0.0f || parseFloat < 0.0f) {
                return;
            }
            if (parseFloat != HostPerformanceCircleView.this.mGpuCurF) {
                HostPerformanceCircleView.this.mGpuCurF = parseFloat;
                HostPerformanceCircleView.this.mGpuSweepAngle = (parseFloat / HostPerformanceCircleView.this.mMaxGpu) * 180.0f;
                HostPerformanceCircleView hostPerformanceCircleView = HostPerformanceCircleView.this;
                hostPerformanceCircleView.mGpuSweepingAngle = hostPerformanceCircleView.mGpuSweepAngle;
                HostPerformanceCircleView.this.mUIHandler.post(new Runnable() { // from class: cn.nubia.hostassist.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        HostPerformanceCircleView.AnonymousClass2.this.b();
                    }
                });
            }
            HostPerformanceCircleView hostPerformanceCircleView2 = HostPerformanceCircleView.this;
            Handler handler = hostPerformanceCircleView2.mBackHandler;
            if (handler != null) {
                handler.postDelayed(hostPerformanceCircleView2.mGpuRefreshRunnable, 1000L);
            }
        }
    }

    public HostPerformanceCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void G(Canvas canvas) {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (this.mPaint == null || (bitmap = this.mBitmapDst) == null || this.mBitmapSrc == null || this.mBitmapPointer == null) {
            GaLog.k(TAG, "drawBitmapWithXfermode,view not init");
            return;
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mDstPaint);
        if (this.mIsPerformaceButtonClick && (bitmap2 = this.mBitmapSrcBottom) != null) {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, bitmap2.getWidth(), this.mBitmapSrcBottom.getHeight(), null, 31);
            this.mBottomPaintAlpha = this.mBottomPaint.getAlpha();
            H(canvas, this.mBitmapSrcBottom, this.mBottomPaint);
            canvas.restoreToCount(saveLayer);
        }
        int saveLayer2 = canvas.saveLayer(0.0f, 0.0f, this.mBitmapSrc.getWidth(), this.mBitmapSrc.getHeight(), null, 31);
        this.mPaint.setAlpha(255 - this.mBottomPaintAlpha);
        H(canvas, this.mBitmapSrc, this.mPaint);
        canvas.restoreToCount(saveLayer2);
        int save = canvas.save();
        if (this.mIsPerformaceButtonClick && this.mBitmapPointerBottom != null) {
            this.mPointerPaintAlpha = this.mPointerPaintBottom.getAlpha();
            I(canvas, this.mBitmapPointerBottom, this.mPointerPaintBottom);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        this.mPointerPaint.setAlpha(255 - this.mPointerPaintAlpha);
        I(canvas, this.mBitmapPointer, this.mPointerPaint);
        canvas.restoreToCount(save2);
        canvas.drawText(this.mPerformanceCircleViewText, this.isCpuType ? (this.mBitmapSrc.getWidth() / 2) + CircleViewText_width_offset : (this.mBitmapSrc.getWidth() / 2) - CircleViewText_width_offset, CircleViewText_height, this.mTypePaint);
        canvas.drawText(this.mPerformanceText, this.isCpuType ? (this.mBitmapSrc.getWidth() / 2) + PerformanceText_width_offset : (this.mBitmapSrc.getWidth() / 2) - PerformanceText_width_offset, (this.mBitmapSrc.getHeight() / 2) + PerformanceText_height_offset, this.mNumPaint);
        boolean z = this.isCpuType;
        canvas.drawText(z ? "GHz" : "MHz", z ? (this.mBitmapSrc.getWidth() / 2) + unit_width_offset : (this.mBitmapSrc.getWidth() / 2) - unit_width_offset, this.mBitmapSrc.getHeight() - unit_height_offset, this.mTypePaint);
    }

    private void H(Canvas canvas, Bitmap bitmap, Paint paint) {
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        RectF rectF = new RectF(-1.0f, -1.0f, bitmap.getWidth() + 1.0f, bitmap.getHeight() + 1.0f);
        paint.setAlpha(255);
        if (this.isCpuType) {
            canvas.drawArc(rectF, this.mStartAngle, this.mCpuSweepAngle - 180.0f, true, paint);
        } else {
            canvas.drawArc(rectF, this.mStartAngle, 180.0f - this.mGpuSweepAngle, true, paint);
        }
        paint.setAlpha(this.mBottomPaintAlpha);
        paint.setXfermode(null);
    }

    private void I(Canvas canvas, Bitmap bitmap, Paint paint) {
        if (this.isCpuType) {
            canvas.rotate(this.mCpuSweepAngle + 210.0f, this.mBitmapSrc.getWidth() / 2, this.mBitmapSrc.getHeight() / 2);
        } else {
            canvas.rotate((-210.0f) - this.mGpuSweepAngle, this.mBitmapSrc.getWidth() / 2, this.mBitmapSrc.getHeight() / 2);
        }
        canvas.drawBitmap(bitmap, -R(bitmap.getWidth(), this.mBitmapSrc.getWidth()), -R(bitmap.getHeight(), this.mBitmapSrc.getHeight()), paint);
    }

    private Bitmap J(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap L(Bitmap bitmap) {
        int i2 = R.drawable.pionter_balance_host;
        float f2 = mPionterScaleSize;
        return a(i2, bitmap, f2, f2);
    }

    private Bitmap M(Bitmap bitmap) {
        int i2 = R.drawable.dst_gray_right_host;
        float f2 = mScaleSize;
        return a(i2, bitmap, f2, f2);
    }

    private void N() {
        if (this.mCpuRefreshRunnable == null) {
            this.mCpuRefreshRunnable = new AnonymousClass1();
        }
        this.mBackHandler.post(this.mCpuRefreshRunnable);
    }

    private void O() {
        if (this.mGpuRefreshRunnable == null) {
            this.mGpuRefreshRunnable = new AnonymousClass2();
        }
        this.mBackHandler.post(this.mGpuRefreshRunnable);
    }

    private void P() {
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
    public void Q() {
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

    private float R(float f2, float f3) {
        return (f2 - f3) / 2.0f;
    }

    public String K(float f2) {
        return String.format("%.0f", Float.valueOf(f2 / 1000000.0f));
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void b() {
        this.mBackHandler = new Handler(ThreadManager.c().b());
        this.mUIHandler = new Handler();
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
        paint6.setColor(Color.parseColor("#FFFFFFFF"));
        Paint paint7 = this.mTypePaint;
        Paint.Align align = Paint.Align.CENTER;
        paint7.setTextAlign(align);
        this.mTypePaint.setAntiAlias(true);
        this.mTypePaint.setDither(true);
        this.mTypePaint.setFilterBitmap(true);
        this.mNumPaint = new Paint();
        if (this.mIsHorizontal) {
            this.mTypePaint.setTextSize(TYPE_SIZE);
            this.mNumPaint.setTextSize(NUM_SIZE);
        } else {
            this.mTypePaint.setTextSize(TYPE_SIZE_PORT);
            this.mNumPaint.setTextSize(NUM_SIZE_PORT);
        }
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
        if (paint == null || this.mPointerPaintBottom == null || this.mPaint == null || this.mPointerPaint == null) {
            GaLog.b(TAG, "onPerformanceAnimEnd: window has hidden, unable to continue draw!");
            return;
        }
        paint.setAlpha(0);
        this.mPointerPaintBottom.setAlpha(0);
        this.mPaint.setAlpha(255);
        this.mPointerPaint.setAlpha(255);
        postInvalidate();
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void d(double d2) {
        Paint paint;
        if (this.mBottomPaint == null || (paint = this.mPointerPaintBottom) == null) {
            GaLog.b(TAG, "onPerformanceAnimUpdate: window has hidden, unable to continue draw!");
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
            N();
            this.mStartAngle = -58.0f;
        } else {
            O();
            this.mStartAngle = -122.0f;
        }
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
            this.mBackHandler = null;
        }
        P();
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void i() {
        Bitmap M;
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap L;
        Bitmap L2;
        if (this.mPerformanceIndex < 0) {
            GaLog.k(TAG, "updateBitmapByIndex, mPerformanceIndex not init," + this.mPerformanceIndex);
            return;
        }
        Bitmap M2 = M(BitmapFactory.decodeResource(getResources(), R.drawable.dst_gray_right_host));
        if (this.isCpuType) {
            M2 = J(M2);
        }
        this.mBitmapDst = M2;
        int i2 = this.mPerformanceIndex;
        Bitmap bitmap3 = null;
        if (i2 == 0) {
            M = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right_host));
            Bitmap L3 = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance_host));
            int i3 = this.mPerformancePrevIndex;
            if (i3 == 1) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise_host));
            } else if (i3 == 2) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond_host));
            } else if (i3 == 4) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_custom_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_custom_host));
            } else {
                bitmap = null;
            }
            bitmap2 = L3;
        } else if (i2 == 1) {
            M = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right_host));
            Bitmap L4 = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise_host));
            int i4 = this.mPerformancePrevIndex;
            if (i4 == 0) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance_host));
            } else if (i4 == 2) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond_host));
            } else if (i4 == 4) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_custom_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_custom_host));
            } else {
                bitmap2 = L4;
                bitmap = null;
            }
            bitmap2 = L4;
        } else if (i2 != 2) {
            if (i2 == 3) {
                M = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_infinite_right_host));
                L = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_infinite_host));
            } else if (i2 != 4) {
                M = null;
                bitmap = null;
                bitmap2 = null;
            } else {
                M = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_custom_right_host));
                L = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_custom_host));
                int i5 = this.mPerformancePrevIndex;
                if (i5 == 0) {
                    bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right_host));
                    L2 = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance_host));
                } else if (i5 == 1) {
                    bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right_host));
                    L2 = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise_host));
                } else if (i5 == 2) {
                    bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right_host));
                    L2 = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond_host));
                }
                Bitmap bitmap4 = L2;
                bitmap2 = L;
                bitmap = bitmap4;
            }
            bitmap2 = L;
            bitmap = null;
        } else {
            M = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_beyond_right_host));
            bitmap2 = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_beyond_host));
            int i6 = this.mPerformancePrevIndex;
            if (i6 == 0) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_balance_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_balance_host));
            } else if (i6 == 1) {
                bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_rise_right_host));
                bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_rise_host));
            } else {
                if (i6 == 4) {
                    bitmap3 = M(BitmapFactory.decodeResource(getResources(), R.drawable.src_custom_right_host));
                    bitmap = L(BitmapFactory.decodeResource(getResources(), R.drawable.pionter_custom_host));
                }
                bitmap = null;
            }
        }
        if (bitmap3 != null) {
            if (this.isCpuType) {
                bitmap3 = J(bitmap3);
            }
            this.mBitmapSrcBottom = bitmap3;
        }
        if (this.isCpuType) {
            M = J(M);
        }
        this.mBitmapSrc = M;
        if (bitmap != null) {
            this.mBitmapPointerBottom = bitmap;
        }
        this.mBitmapPointer = bitmap2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        G(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Bitmap bitmap = this.mBitmapSrc;
        if (bitmap != null) {
            setMeasuredDimension(bitmap.getWidth(), this.mBitmapSrc.getHeight());
        }
    }

    @Override // cn.nubia.gameassist.common.BasePerformanceView
    public void setViewSize(boolean z) {
        GaLog.b(TAG, "setViewSize: isHorizontal: " + z);
        this.mIsHorizontal = z;
        if (z) {
            mScaleSize = 80.0f;
            mPionterScaleSize = 100.0f;
            CircleViewText_width_offset = 5.0f;
            CircleViewText_height = 25.0f;
            PerformanceText_width_offset = 4.0f;
            PerformanceText_height_offset = TYPE_SIZE;
            unit_width_offset = 3.0f;
            unit_height_offset = 15.0f;
            return;
        }
        mScaleSize = 160.0f;
        mPionterScaleSize = 200.0f;
        CircleViewText_width_offset = TYPE_SIZE;
        CircleViewText_height = 50.0f;
        PerformanceText_width_offset = 8.0f;
        PerformanceText_height_offset = TYPE_SIZE_PORT;
        unit_width_offset = 6.0f;
        unit_height_offset = 30.0f;
    }

    public HostPerformanceCircleView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public HostPerformanceCircleView(Context context, AttributeSet attributeSet, int i2, int i3) {
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
        this.mAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: cn.nubia.hostassist.HostPerformanceCircleView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (HostPerformanceCircleView.this.isCpuType) {
                    HostPerformanceCircleView hostPerformanceCircleView = HostPerformanceCircleView.this;
                    hostPerformanceCircleView.mCpuEndSweepAngle = hostPerformanceCircleView.mCpuSweepAngle;
                    HostPerformanceCircleView hostPerformanceCircleView2 = HostPerformanceCircleView.this;
                    hostPerformanceCircleView2.mPerformanceText = String.format("%.2f", Float.valueOf(hostPerformanceCircleView2.mCpuCurF / 1000000.0f));
                    return;
                }
                HostPerformanceCircleView hostPerformanceCircleView3 = HostPerformanceCircleView.this;
                hostPerformanceCircleView3.mGpuEndSweepAngle = hostPerformanceCircleView3.mGpuSweepAngle;
                HostPerformanceCircleView hostPerformanceCircleView4 = HostPerformanceCircleView.this;
                hostPerformanceCircleView4.mPerformanceText = hostPerformanceCircleView4.K(hostPerformanceCircleView4.mGpuCurF);
            }
        };
        this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.hostassist.HostPerformanceCircleView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HostPerformanceCircleView.this.isCpuType) {
                    HostPerformanceCircleView.this.mCpuSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f2 = HostPerformanceCircleView.this.mCpuSweepAngle / HostPerformanceCircleView.this.mCpuSweepingAngle;
                    HostPerformanceCircleView hostPerformanceCircleView = HostPerformanceCircleView.this;
                    hostPerformanceCircleView.mPerformanceText = String.format("%.2f", Float.valueOf((hostPerformanceCircleView.mCpuCurF * f2) / 1000000.0f));
                } else {
                    HostPerformanceCircleView.this.mGpuSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f3 = HostPerformanceCircleView.this.mGpuSweepAngle / HostPerformanceCircleView.this.mGpuSweepingAngle;
                    HostPerformanceCircleView hostPerformanceCircleView2 = HostPerformanceCircleView.this;
                    hostPerformanceCircleView2.mPerformanceText = hostPerformanceCircleView2.K(hostPerformanceCircleView2.mGpuCurF * f3);
                }
                HostPerformanceCircleView.this.postInvalidate();
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.performanceView);
        this.isCpuType = obtainStyledAttributes.getBoolean(R.styleable.performanceView_isCpuType, false);
        this.mPerformanceCircleViewText = obtainStyledAttributes.getString(R.styleable.performanceView_text);
        this.mGamePerformanceViewController = GamePerformanceViewController.k(context);
        obtainStyledAttributes.recycle();
    }
}
