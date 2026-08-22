package cn.nubia.gamelauncher.gamecontrolpanel;

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
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.PerformanceConstant;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.Utils;

/* loaded from: classes.dex */
public class PerformanceCircleView extends View {
    private static final int ANGLE_LIMIT = 180;
    private static final int ANGLE_POINTER_START = 210;
    private static float CPU_IMAGE_WIDTH = 480.0f;
    public static final String PATH_CUR_CPU_MIDDLE_651 = "/sys/devices/system/cpu/cpu6/cpufreq/scaling_cur_freq";
    private static float SCALE_SIZE = 480.0f;
    private static final String TAG = "PerformanceCircleView";
    public static final int TIME_GAME_REFRESH = 1000;
    private final String BOOST;
    private final int MAX_DISPLAYED;
    private boolean isCpuType;
    private AnimatorListenerAdapter mAnimatorListenerAdapter;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    private Handler mBackHandler;
    private Bitmap mBitmapSrc;
    private ValueAnimator mCpuAnimator;
    private float mCpuCurF;
    private float mCpuEndSweepAngle;
    private Runnable mCpuRefreshRunnable;
    private float mCpuSweepAngle;
    private float mCpuSweepingAngle;
    private HandlerThread mDBThread;
    private ValueAnimator mGpuAnimator;
    private float mGpuCurF;
    private float mGpuEndSweepAngle;
    private Runnable mGpuRefreshRunnable;
    private float mGpuSweepAngle;
    private float mGpuSweepingAngle;
    boolean mIs651J;
    private float mMaxCpu;
    private float mMaxGpu;
    private Paint mNumPaint;
    private Paint mPaint;
    private String mPerformanceText;
    private Paint mTypePaint;
    public static final String PATH_MAX_CPU_MAIN = ControlPanelFeatureHelper.getCpuFileNode()[0];
    public static final String PATH_CUR_CPU_MAIN = ControlPanelFeatureHelper.getCpuFileNode()[1];
    public static final String PATH_CUR_CPU_MIDDLE = ControlPanelFeatureHelper.getCpuFileNode()[2];
    public static final String PATH_CUR_CPU_MINOR = ControlPanelFeatureHelper.getCpuFileNode()[3];
    public static final String PATH_CUR_GPU = ControlPanelFeatureHelper.getGpuFileNode()[1];
    public static final String PATH_MAX_GPU = ControlPanelFeatureHelper.getGpuFileNode()[0];

    public PerformanceCircleView(Context context) {
        this(context, null);
    }

    public PerformanceCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PerformanceCircleView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PerformanceCircleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIs651J = supportGameVoice();
        this.mPerformanceText = "";
        this.BOOST = "Boost";
        this.MAX_DISPLAYED = 587;
        this.mMaxCpu = getCurrentGPUNodeValue();
        this.mMaxGpu = getMaxGPUNodeValue();
        this.mCpuAnimator = null;
        this.mGpuAnimator = null;
        this.mAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceCircleView.2
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
                performanceCircleView4.mPerformanceText = performanceCircleView4.getLabel(performanceCircleView4.mGpuCurF);
            }
        };
        this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceCircleView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (PerformanceCircleView.this.isCpuType) {
                    PerformanceCircleView.this.mCpuSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f = PerformanceCircleView.this.mCpuSweepAngle / PerformanceCircleView.this.mCpuSweepingAngle;
                    PerformanceCircleView performanceCircleView = PerformanceCircleView.this;
                    performanceCircleView.mPerformanceText = String.format("%.2f", Float.valueOf((performanceCircleView.mCpuCurF * f) / 1000000.0f));
                } else {
                    PerformanceCircleView.this.mGpuSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f2 = PerformanceCircleView.this.mGpuSweepAngle / PerformanceCircleView.this.mGpuSweepingAngle;
                    PerformanceCircleView performanceCircleView2 = PerformanceCircleView.this;
                    performanceCircleView2.mPerformanceText = performanceCircleView2.getLabel(performanceCircleView2.mGpuCurF * f2);
                }
                PerformanceCircleView.this.postInvalidate();
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.performanceView);
        this.isCpuType = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        init(context);
    }

    private void drawBitmapWithXfermode(Canvas canvas) {
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, this.mBitmapSrc.getWidth(), this.mBitmapSrc.getHeight() + (getScaleSize() * 100.0f), null, 31);
        canvas.drawBitmap(this.mBitmapSrc, 0.0f, getScaleSize() * 100.0f, this.mPaint);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
        canvas.save();
        if (this.isCpuType) {
            canvas.rotate(this.mCpuSweepAngle + 210.0f, this.mBitmapSrc.getWidth() / 2, this.mBitmapSrc.getHeight() / 2);
        } else {
            canvas.rotate((-210.0f) - this.mGpuSweepAngle, this.mBitmapSrc.getWidth() / 2, this.mBitmapSrc.getHeight() / 2);
        }
        canvas.restore();
        canvas.drawText(this.mPerformanceText, this.isCpuType ? (this.mBitmapSrc.getWidth() / 2) + (getScaleSize() * 8.0f) : (this.mBitmapSrc.getWidth() / 2) - (getScaleSize() * 8.0f), (this.mBitmapSrc.getHeight() / 2) - (getScaleSize() * 50.0f), this.mNumPaint);
        boolean z = this.isCpuType;
        canvas.drawText(z ? "GHz" : "MHz", z ? (this.mBitmapSrc.getWidth() / 2) + (getScaleSize() * 10.0f) : (this.mBitmapSrc.getWidth() / 2) - (getScaleSize() * 10.0f), this.mBitmapSrc.getHeight() - (getScaleSize() * 140.0f), this.mTypePaint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getCurrentGPUNodeValue() {
        if (PerformanceConstant.MTK_CHIP) {
            return Utils.getValueInNode(PATH_CUR_GPU, "OPP", "Freq", 268000) * 1000;
        }
        String fileContent = Utils.getFileContent(PATH_CUR_GPU);
        try {
            return Float.parseFloat(fileContent.trim());
        } catch (NumberFormatException unused) {
            LogUtil.e(TAG, "Invalid GPU frequency value: " + fileContent);
            return 0.0f;
        }
    }

    private Bitmap getHorizontalRotateIcon(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getMaxGPUNodeValue() {
        if (PerformanceConstant.MTK_CHIP) {
            return Utils.getValueInNode(PATH_MAX_GPU, "freq", "freq", 650000) * 1000;
        }
        String fileContent = Utils.getFileContent(PATH_MAX_GPU);
        try {
            return Float.parseFloat(fileContent);
        } catch (NumberFormatException e) {
            LogUtil.e(TAG, "Invalid GPU frequency value in file: " + fileContent, e);
            return 0.0f;
        }
    }

    private Bitmap getScaleBitmap(Bitmap bitmap) {
        float scaleSize = getScaleSize();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleSize, scaleSize);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private float getScaleSize() {
        return SCALE_SIZE / CPU_IMAGE_WIDTH;
    }

    private void init(Context context) {
        SCALE_SIZE = context.getResources().getDimensionPixelSize(R.dimen.performance_circle_view_width);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        int i = displayMetrics.densityDpi;
        float f2 = displayMetrics.xdpi;
        float f3 = displayMetrics.ydpi;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (GameControlOrientationManager.getInstance().isPortrait()) {
            SCALE_SIZE = (i2 - context.getResources().getDimensionPixelSize(R.dimen.gamecontrol_menu_width_port)) * context.getResources().getFloat(R.dimen.performance_circle_view_width_scale);
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.balance_cpu);
        CPU_IMAGE_WIDTH = decodeResource.getWidth();
        decodeResource.recycle();
        Bitmap scaleBitmap = getScaleBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.balance_cpu));
        Bitmap scaleBitmap2 = getScaleBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.balance_gpu));
        if (!this.isCpuType) {
            scaleBitmap = scaleBitmap2;
        }
        this.mBitmapSrc = scaleBitmap;
        HandlerThread handlerThread = new HandlerThread("DatabaseThread");
        this.mDBThread = handlerThread;
        handlerThread.start();
        this.mBackHandler = new Handler(this.mDBThread.getLooper());
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setFilterBitmap(true);
        Paint paint2 = new Paint();
        this.mTypePaint = paint2;
        paint2.setTextSize(35.0f);
        this.mTypePaint.setColor(Color.parseColor("#FFFFFFFF"));
        this.mTypePaint.setTextAlign(Paint.Align.CENTER);
        this.mTypePaint.setAntiAlias(true);
        this.mTypePaint.setDither(true);
        this.mTypePaint.setFilterBitmap(true);
        Paint paint3 = new Paint();
        this.mNumPaint = paint3;
        paint3.setTextSize(60.0f);
        this.mNumPaint.setColor(Color.parseColor("#FFEBEBEB"));
        this.mNumPaint.setTextAlign(Paint.Align.CENTER);
        this.mNumPaint.setAntiAlias(true);
        this.mNumPaint.setDither(true);
        this.mNumPaint.setFilterBitmap(true);
    }

    private void monitorCpu() {
        if (this.mCpuRefreshRunnable == null) {
            this.mCpuRefreshRunnable = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceCircleView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceCircleView.this.m274xf64fa03d();
                }
            };
        }
        this.mBackHandler.post(this.mCpuRefreshRunnable);
    }

    private void monitorGpu() {
        if (this.mGpuRefreshRunnable == null) {
            this.mGpuRefreshRunnable = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceCircleView.1
                @Override // java.lang.Runnable
                public void run() {
                    float currentGPUNodeValue = PerformanceCircleView.this.getCurrentGPUNodeValue();
                    if (PerformanceCircleView.this.mMaxGpu < 0.0f) {
                        PerformanceCircleView performanceCircleView = PerformanceCircleView.this;
                        performanceCircleView.mMaxGpu = performanceCircleView.getMaxGPUNodeValue();
                    }
                    if (PerformanceCircleView.this.mMaxGpu < 0.0f || currentGPUNodeValue < 0.0f) {
                        return;
                    }
                    if (currentGPUNodeValue != PerformanceCircleView.this.mGpuCurF) {
                        PerformanceCircleView.this.mGpuCurF = currentGPUNodeValue;
                        PerformanceCircleView.this.mGpuSweepAngle = (currentGPUNodeValue / PerformanceCircleView.this.mMaxGpu) * 180.0f;
                        PerformanceCircleView performanceCircleView2 = PerformanceCircleView.this;
                        performanceCircleView2.mGpuSweepingAngle = performanceCircleView2.mGpuSweepAngle;
                        PerformanceCircleView.this.startAnim();
                    }
                    PerformanceCircleView.this.mBackHandler.postDelayed(PerformanceCircleView.this.mGpuRefreshRunnable, 1000L);
                }
            };
        }
        this.mBackHandler.post(this.mGpuRefreshRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnim() {
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

    private boolean supportGameVoice() {
        return Build.DEVICE.contains("NX651");
    }

    private float translateSize(float f, float f2) {
        return (f - f2) / 2.0f;
    }

    public String getLabel(float f) {
        float f2 = f / 1000000.0f;
        return (f2 <= 587.0f || !Utils.supportOverClock()) ? String.format("%.0f", Float.valueOf(f2)) : "Boost";
    }

    /* renamed from: lambda$monitorCpu$0$cn-nubia-gamelauncher-gamecontrolpanel-PerformanceCircleView, reason: not valid java name */
    /* synthetic */ void m274xf64fa03d() {
        float parseFloat = Float.parseFloat(Utils.getFileContent(PATH_CUR_CPU_MAIN));
        float parseFloat2 = Float.parseFloat(Utils.getFileContent(this.mIs651J ? PATH_CUR_CPU_MIDDLE_651 : PATH_CUR_CPU_MIDDLE));
        float parseFloat3 = Float.parseFloat(Utils.getFileContent(PATH_CUR_CPU_MINOR));
        if (this.mMaxCpu <= 0.0f) {
            this.mMaxCpu = Float.parseFloat(Utils.getFileContent(PATH_MAX_CPU_MAIN));
        }
        float max = Math.max(parseFloat, Math.max(parseFloat2, parseFloat3));
        float f = this.mMaxCpu;
        if (f <= 0.0f || max < 0.0f) {
            return;
        }
        if (max != this.mCpuCurF) {
            this.mCpuCurF = max;
            float f2 = (max / f) * 180.0f;
            this.mCpuSweepAngle = f2;
            this.mCpuSweepingAngle = f2;
            startAnim();
        }
        this.mBackHandler.postDelayed(this.mCpuRefreshRunnable, 1000L);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBitmapWithXfermode(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int width = this.mBitmapSrc.getWidth();
        this.mBitmapSrc.getHeight();
        View.MeasureSpec.getMode(i);
        View.MeasureSpec.getSize(i);
        View.MeasureSpec.getMode(i2);
        setMeasuredDimension(width, View.MeasureSpec.getSize(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setMode(int r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == r0) goto L6d
            r0 = 2
            if (r4 == r0) goto L4e
            r0 = 3
            if (r4 == r0) goto L2f
            r0 = 4
            if (r4 == r0) goto L10
            r4 = 0
            r0 = r4
            goto L8e
        L10:
            android.content.res.Resources r4 = r3.getResources()
            r0 = 2131231523(0x7f080323, float:1.807913E38)
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeResource(r4, r0)
            android.graphics.Bitmap r4 = r3.getScaleBitmap(r4)
            android.content.res.Resources r0 = r3.getResources()
            r1 = 2131231524(0x7f080324, float:1.8079131E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r1)
            android.graphics.Bitmap r0 = r3.getScaleBitmap(r0)
            goto L8b
        L2f:
            android.content.res.Resources r4 = r3.getResources()
            r0 = 2131230914(0x7f0800c2, float:1.8077894E38)
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeResource(r4, r0)
            android.graphics.Bitmap r4 = r3.getScaleBitmap(r4)
            android.content.res.Resources r0 = r3.getResources()
            r1 = 2131230915(0x7f0800c3, float:1.8077896E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r1)
            android.graphics.Bitmap r0 = r3.getScaleBitmap(r0)
            goto L8b
        L4e:
            android.content.res.Resources r4 = r3.getResources()
            r0 = 2131231927(0x7f0804b7, float:1.8079949E38)
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeResource(r4, r0)
            android.graphics.Bitmap r4 = r3.getScaleBitmap(r4)
            android.content.res.Resources r0 = r3.getResources()
            r1 = 2131231928(0x7f0804b8, float:1.807995E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r1)
            android.graphics.Bitmap r0 = r3.getScaleBitmap(r0)
            goto L8b
        L6d:
            android.content.res.Resources r4 = r3.getResources()
            r0 = 2131230890(0x7f0800aa, float:1.8077846E38)
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeResource(r4, r0)
            android.graphics.Bitmap r4 = r3.getScaleBitmap(r4)
            android.content.res.Resources r0 = r3.getResources()
            r1 = 2131230891(0x7f0800ab, float:1.8077848E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r1)
            android.graphics.Bitmap r0 = r3.getScaleBitmap(r0)
        L8b:
            r2 = r0
            r0 = r4
            r4 = r2
        L8e:
            boolean r1 = r3.isCpuType
            if (r1 == 0) goto L93
            r4 = r0
        L93:
            r3.mBitmapSrc = r4
            r3.postInvalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceCircleView.setMode(int):void");
    }

    public void start() {
        if (this.isCpuType) {
            monitorCpu();
        } else {
            monitorGpu();
        }
    }

    public void stop() {
        ValueAnimator valueAnimator = this.mCpuAnimator;
        if (valueAnimator != null) {
            if (valueAnimator.isRunning()) {
                this.mCpuAnimator.cancel();
            }
            this.mCpuAnimator.removeAllListeners();
            this.mCpuAnimator.removeAllUpdateListeners();
            this.mCpuAnimator = null;
        }
        ValueAnimator valueAnimator2 = this.mGpuAnimator;
        if (valueAnimator2 != null) {
            if (valueAnimator2.isRunning()) {
                this.mGpuAnimator.cancel();
            }
            this.mGpuAnimator.removeAllListeners();
            this.mGpuAnimator.removeAllUpdateListeners();
            this.mGpuAnimator = null;
        }
        this.mDBThread.quit();
    }
}
