package cn.nubia.gameassist.meditationmode.danmu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import cn.nubia.gameassist.meditationmode.danmu.BarrageController;
import cn.nubia.gameassist.meditationmode.danmu.BarrageHandler;
import cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageBitmapModel;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageConfig;
import cn.nubia.gameassist.meditationmode.danmu.painter.BarragePainter;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import cn.nubia.gameassist.meditationmode.danmu.view.BarrageSurfaceView;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class BarrageSurfaceView extends SurfaceView implements IBarrageView, SurfaceHolder.Callback {
    private static final String TAG = "BarrageSurfaceView";
    private boolean isSurfaceCreated;
    private BarrageConfig mBarrageConfig;
    private BarrageController mBarrageController;
    private BarrageHandler mBarrageHandler;
    private SurfaceHolder mSurfaceHolder;

    public BarrageSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isSurfaceCreated = false;
        g(context, attributeSet);
    }

    private void g(Context context, AttributeSet attributeSet) {
        BarrageLog.b(TAG, "init...");
        this.mBarrageConfig = new BarrageConfig(context, attributeSet);
        this.mBarrageConfig.m(new BarragePainter());
        this.mBarrageController = new BarrageController(this);
        HandlerThread handlerThread = new HandlerThread("barrageRender");
        handlerThread.start();
        this.mBarrageHandler = new BarrageHandler(handlerThread.getLooper(), this.mBarrageController);
        this.mSurfaceHolder = getHolder();
        setZOrderOnTop(true);
        getHolder().setFormat(-2);
    }

    private void i(Canvas canvas) {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.mBarrageController.j(canvas.getWidth(), canvas.getHeight(), iArr, this.mBarrageConfig);
        this.mBarrageHandler.d();
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public void a() {
        this.mSurfaceHolder.addCallback(this);
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public void b(PrintWriter printWriter) {
        BarrageController barrageController = this.mBarrageController;
        if (barrageController != null) {
            barrageController.e(printWriter);
        }
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public void c(int i2) {
        this.mBarrageController.o(i2);
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public void d() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.mBarrageController.t(iArr);
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public void e(DanmuNotificationBean danmuNotificationBean) {
        BarrageBitmapModel barrageBitmapModel = new BarrageBitmapModel();
        barrageBitmapModel.k(danmuNotificationBean);
        this.mBarrageController.c(barrageBitmapModel);
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public void f() {
        if (this.isSurfaceCreated) {
            BarrageLog.a("lockDraw");
            Canvas lockHardwareCanvas = this.mSurfaceHolder.lockHardwareCanvas();
            if (lockHardwareCanvas == null) {
                return;
            }
            lockHardwareCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.mBarrageController.d(lockHardwareCanvas);
            if (this.isSurfaceCreated) {
                this.mSurfaceHolder.unlockCanvasAndPost(lockHardwareCanvas);
            }
            BarrageLog.e();
        }
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView
    public BarrageConfig getBarrageConfig() {
        return this.mBarrageConfig;
    }

    public void h() {
        this.mBarrageController.s();
        this.mBarrageHandler.e();
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        this.mBarrageController.p(i3, i4);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas lockHardwareCanvas = this.mSurfaceHolder.lockHardwareCanvas();
        i(lockHardwareCanvas);
        this.mSurfaceHolder.unlockCanvasAndPost(lockHardwareCanvas);
        this.isSurfaceCreated = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.isSurfaceCreated = false;
        this.mBarrageHandler.post(new Runnable() { // from class: e.a
            @Override // java.lang.Runnable
            public final void run() {
                BarrageSurfaceView.this.h();
            }
        });
    }
}
