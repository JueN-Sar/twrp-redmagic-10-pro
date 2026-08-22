package cn.nubia.screensaver.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.annotation.RequiresApi;
import cn.nubia.screensaver.GameScreensaverManager;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.sensor.GSSensorController;
import cn.nubia.screensaver.system.GSSystemController;
import cn.nubia.screensaver.system.ISnapshotKeyguard;
import cn.nubia.screensaver.system.KeyguardShade;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.TraceWrapper;

@RequiresApi
/* loaded from: classes.dex */
public class ScreensaverRootView extends FrameLayout implements GSSensorController.Callback {
    private static final long ANMATION_DELAY = 500;
    private static final long NO_ANMATION_DELAY = 500;
    public static final String TAG = "GameScreensaver";
    private boolean isAccelerometerFront;
    private boolean isAttachedToWindow;
    PathInterpolator mBlursInterpolator;
    private Matrix mCanvasMatrix;
    private int mDrawPath;
    private Matrix mEventMatrix;
    private GameScreensaverManager mGSManager;
    private Handler mHandler;
    private boolean mIsVertical;
    private final Rect mKeyguardBounds;
    private KeyguardShade mKeyguardShade;
    private GSPowerController mPowerController;
    private GSSensorController mScreensaverSensor;
    private long mShowTime;
    private ValueAnimator mTransformAnimator;

    private static class EndRun implements Runnable, Animator.AnimatorListener {

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f9195c;

        public EndRun(Runnable runnable) {
            this.f9195c = runnable;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            run();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            run();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = this.f9195c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public ScreensaverRootView(Context context, GameScreensaverManager gameScreensaverManager) {
        super(context);
        this.mBlursInterpolator = new PathInterpolator(0.0f, 0.5f, 0.5f, 1.0f);
        this.mKeyguardBounds = new Rect();
        this.mEventMatrix = new Matrix();
        this.mCanvasMatrix = new Matrix();
        this.mGSManager = gameScreensaverManager;
        this.mScreensaverSensor = (GSSensorController) gameScreensaverManager.I(GSSensorController.class);
        this.mPowerController = (GSPowerController) gameScreensaverManager.I(GSPowerController.class);
        this.mScreensaverSensor.L(this);
        this.isAccelerometerFront = this.mScreensaverSensor.O();
        this.mHandler = new Handler(Looper.myLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(KeyguardShade keyguardShade) {
        if (this.isAttachedToWindow) {
            this.mKeyguardShade = keyguardShade;
        } else if (keyguardShade != null) {
            keyguardShade.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(View view, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f2 = ((1.0f - floatValue) * 0.15f) + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
        view.setAlpha(floatValue);
        invalidate();
        if (floatValue <= 0.0d) {
            this.mTransformAnimator = null;
            KeyguardShade keyguardShade = this.mKeyguardShade;
            if (keyguardShade != null) {
                keyguardShade.c();
            }
            this.mKeyguardShade = null;
            setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(KeyguardShade keyguardShade) {
        if (this.isAttachedToWindow) {
            this.mKeyguardShade = keyguardShade;
        } else if (keyguardShade != null) {
            keyguardShade.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(View view, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f2 = ((1.0f - floatValue) * 0.15f) + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
        view.setAlpha(floatValue);
        invalidate();
        if (floatValue >= 1.0f) {
            view.setScaleX(f2);
            view.setScaleY(f2);
            this.mTransformAnimator = null;
            KeyguardShade keyguardShade = this.mKeyguardShade;
            if (keyguardShade != null) {
                keyguardShade.c();
            }
            this.mKeyguardShade = null;
        }
    }

    private MotionEvent p(MotionEvent motionEvent) {
        if (this.mIsVertical) {
            motionEvent.transform(this.mEventMatrix);
        }
        long downTime = motionEvent.getDownTime();
        long eventTime = motionEvent.getEventTime();
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i2 = 0; i2 < pointerCount; i2++) {
            MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
            pointerPropertiesArr[i2] = pointerProperties;
            pointerProperties.id = motionEvent.getPointerId(i2);
            pointerPropertiesArr[i2].toolType = motionEvent.getToolType(i2);
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i3 = 0; i3 < pointerCount; i3++) {
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            pointerCoordsArr[i3] = pointerCoords;
            pointerCoords.x = motionEvent.getX(i3);
            pointerCoordsArr[i3].y = motionEvent.getY(i3);
            pointerCoordsArr[i3].pressure = motionEvent.getPressure(i3);
            pointerCoordsArr[i3].size = motionEvent.getSize(i3);
        }
        return MotionEvent.obtain(downTime, eventTime, action, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
    }

    private void q() {
        int width = getWidth();
        int height = getHeight();
        this.mEventMatrix.reset();
        this.mCanvasMatrix.reset();
        if (this.mIsVertical) {
            if (this.isAccelerometerFront) {
                float f2 = width / 2.0f;
                this.mEventMatrix.setRotate(-90.0f, f2, f2);
                this.mCanvasMatrix.setRotate(90.0f, f2, f2);
            } else {
                float f3 = width / 2.0f;
                this.mEventMatrix.setRotate(90.0f, f3, f3);
                float f4 = height - width;
                this.mEventMatrix.postTranslate(f4, 0.0f);
                this.mCanvasMatrix.setRotate(-90.0f, f3, f3);
                this.mCanvasMatrix.postTranslate(0.0f, f4);
            }
        }
        setAnimationMatrix(this.mCanvasMatrix);
    }

    private void r() {
        if (this.mScreensaverSensor.Q()) {
            this.mPowerController.Q();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        ValueAnimator valueAnimator;
        TraceWrapper.traceBegin(8L, "mDrawPath=" + this.mDrawPath);
        if (this.mPowerController.w()) {
            canvas.drawColor((this.mScreensaverSensor.P() || !this.mScreensaverSensor.N()) ? -16777216 : 0);
            postInvalidate();
            this.mDrawPath = 1;
            this.mShowTime = 0L;
        } else {
            if (this.mShowTime == 0) {
                this.mShowTime = SystemClock.elapsedRealtime();
            }
            if (this.mGSManager.R() && this.mKeyguardShade != null && (valueAnimator = this.mTransformAnimator) != null && valueAnimator.isRunning()) {
                float floatValue = ((Float) this.mTransformAnimator.getAnimatedValue()).floatValue();
                float interpolation = (this.mBlursInterpolator.getInterpolation(floatValue) * 22.0f) + 3.0f;
                KeyguardShade keyguardShade = this.mKeyguardShade;
                Rect rect = this.mKeyguardBounds;
                if (!this.isAccelerometerFront) {
                    floatValue = -floatValue;
                }
                keyguardShade.a(canvas, rect, interpolation, floatValue);
            }
            super.dispatchDraw(canvas);
            this.mDrawPath = 2;
        }
        TraceWrapper.traceEnd(8L);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent p2 = p(motionEvent);
        if (motionEvent.getActionMasked() == 0) {
            r();
        }
        try {
            try {
                return super.dispatchTouchEvent(p2);
            } catch (Exception e2) {
                e2.printStackTrace();
                p2.recycle();
                return false;
            }
        } finally {
            p2.recycle();
        }
    }

    public void g() {
        ValueAnimator valueAnimator = this.mTransformAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mTransformAnimator.cancel();
        this.mTransformAnimator = null;
    }

    public boolean h() {
        ValueAnimator valueAnimator = this.mTransformAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    @Override // cn.nubia.screensaver.sensor.GSSensorController.Callback
    public void j(boolean z) {
        this.isAccelerometerFront = z;
        q();
        postInvalidate();
    }

    public void n(Runnable runnable) {
        KeyguardShade keyguardShade = this.mKeyguardShade;
        if (keyguardShade != null) {
            keyguardShade.c();
        }
        if (!this.mPowerController.y()) {
            runnable.run();
            return;
        }
        ((GSSystemController) this.mGSManager.I(GSSystemController.class)).g().b(new ISnapshotKeyguard.Callback() { // from class: cn.nubia.screensaver.view.h
            @Override // cn.nubia.screensaver.system.ISnapshotKeyguard.Callback
            public final void a(KeyguardShade keyguardShade2) {
                ScreensaverRootView.this.i(keyguardShade2);
            }
        });
        final View childAt = getChildAt(0);
        boolean z = this.mShowTime == 0 || SystemClock.elapsedRealtime() - this.mShowTime < 500;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(childAt.getAlpha(), 0.0f);
        this.mTransformAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.screensaver.view.i
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreensaverRootView.this.k(childAt, valueAnimator);
            }
        });
        long alpha = (long) (z ? 0.0f : childAt.getAlpha() * 500.0f);
        this.mTransformAnimator.addListener(new EndRun(runnable));
        this.mTransformAnimator.setDuration(alpha);
        this.mTransformAnimator.start();
        TraceWrapper.traceBegin(8L, "startHideAnimation");
        GaLog.e(TAG, "---start hide animation--- " + alpha);
        TraceWrapper.traceEnd(8L);
    }

    public void o(Runnable runnable) {
        KeyguardShade keyguardShade = this.mKeyguardShade;
        if (keyguardShade != null) {
            keyguardShade.c();
        }
        if (!this.mPowerController.y()) {
            runnable.run();
            return;
        }
        final View childAt = getChildAt(0);
        childAt.setAlpha(0.0f);
        setVisibility(0);
        ((GSSystemController) this.mGSManager.I(GSSystemController.class)).g().b(new ISnapshotKeyguard.Callback() { // from class: cn.nubia.screensaver.view.f
            @Override // cn.nubia.screensaver.system.ISnapshotKeyguard.Callback
            public final void a(KeyguardShade keyguardShade2) {
                ScreensaverRootView.this.l(keyguardShade2);
            }
        });
        ValueAnimator ofFloat = ValueAnimator.ofFloat(childAt.getAlpha(), 1.0f);
        this.mTransformAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.screensaver.view.g
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreensaverRootView.this.m(childAt, valueAnimator);
            }
        });
        this.mTransformAnimator.addListener(new EndRun(runnable));
        long alpha = (long) ((1.0f - childAt.getAlpha()) * 500.0f);
        this.mTransformAnimator.setDuration(alpha);
        this.mTransformAnimator.start();
        TraceWrapper.traceBegin(8L, "showAnimation");
        GaLog.e(TAG, "---reshow animation--- " + alpha);
        TraceWrapper.traceEnd(8L);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        g();
        this.isAttachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mScreensaverSensor.Z(this);
        this.isAttachedToWindow = false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        boolean z2 = i7 > i6;
        this.mIsVertical = z2;
        Rect rect = this.mKeyguardBounds;
        int i8 = z2 ? i7 : i6;
        if (!z2) {
            i6 = i7;
        }
        rect.set(0, 0, i8, i6);
        setAnimationMatrix(this.mCanvasMatrix);
        if (!this.mIsVertical || getChildAt(0) == null) {
            super.onLayout(z, i2, i3, i4, i5);
        } else {
            getChildAt(0).layout(i3, i2, i5, i4);
        }
        q();
    }

    @Override // android.view.View
    public String toString() {
        return super.toString() + " mDrawPath=" + this.mDrawPath + " alpha=" + getChildAt(0).getAlpha() + " " + this.mKeyguardBounds;
    }
}
