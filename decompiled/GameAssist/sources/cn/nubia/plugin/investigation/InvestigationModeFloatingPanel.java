package cn.nubia.plugin.investigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.hardware.HardwareBuffer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.ArrayMap;
import android.view.Choreographer;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ChoreographerWrapper;
import com.zte.shared.wrapper.GameAssistControllerWrapper;
import com.zte.shared.wrapper.MyOsUtilsWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Map;

@SuppressLint({"InflateParams"})
/* loaded from: classes.dex */
public class InvestigationModeFloatingPanel implements SurfaceHolder.Callback {
    private Display A;
    private final int B;
    private int C;
    private Object D;
    private Runnable E;
    private GameAssistControllerWrapper.Callback F;
    private final ContentObserver G;

    /* renamed from: c, reason: collision with root package name */
    private final String f8539c = "investigation_mode_enable";

    /* renamed from: h, reason: collision with root package name */
    private final Uri f8540h;

    /* renamed from: i, reason: collision with root package name */
    private final Map f8541i;

    /* renamed from: j, reason: collision with root package name */
    private Context f8542j;

    /* renamed from: k, reason: collision with root package name */
    private View f8543k;

    /* renamed from: l, reason: collision with root package name */
    private SurfaceView f8544l;

    /* renamed from: m, reason: collision with root package name */
    private WindowManager.LayoutParams f8545m;

    /* renamed from: n, reason: collision with root package name */
    private WindowManager f8546n;

    /* renamed from: o, reason: collision with root package name */
    private Choreographer f8547o;

    /* renamed from: p, reason: collision with root package name */
    private Paint f8548p;

    /* renamed from: q, reason: collision with root package name */
    private Path f8549q;

    /* renamed from: r, reason: collision with root package name */
    private Matrix f8550r;

    /* renamed from: s, reason: collision with root package name */
    private SurfaceHolder f8551s;
    private Context t;
    private int u;
    private int v;
    private int w;
    private int x;
    private HandlerThread y;
    private DrawHandler z;

    private class CircleDrawable extends ColorDrawable {

        /* renamed from: a, reason: collision with root package name */
        private Paint f8556a;

        /* renamed from: b, reason: collision with root package name */
        private int f8557b;

        /* renamed from: c, reason: collision with root package name */
        private int f8558c;

        /* renamed from: d, reason: collision with root package name */
        private int f8559d;

        public CircleDrawable(InvestigationModeFloatingPanel investigationModeFloatingPanel, int i2, int i3, int i4, int i5, int i6) {
            super(i2);
            Paint paint = new Paint();
            this.f8556a = paint;
            this.f8557b = i3;
            this.f8558c = i4;
            this.f8559d = i5;
            paint.setColor(getColor());
            this.f8556a.setStyle(Paint.Style.STROKE);
            this.f8556a.setStrokeWidth(i6);
        }

        @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.drawCircle(this.f8557b, this.f8558c, this.f8559d, this.f8556a);
        }
    }

    final class DrawHandler extends Handler {
        public DrawHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 50) {
                super.handleMessage(message);
            } else {
                InvestigationModeFloatingPanel.this.j();
            }
        }
    }

    public InvestigationModeFloatingPanel(Context context) {
        Uri uriFor = Settings.Global.getUriFor("investigation_mode_enable");
        this.f8540h = uriFor;
        this.f8541i = new ArrayMap();
        this.f8542j = null;
        this.u = 600;
        this.v = 600;
        this.w = 0;
        this.x = 0;
        this.B = 50;
        this.C = 16;
        this.D = new Object();
        this.E = new Runnable() { // from class: cn.nubia.plugin.investigation.InvestigationModeFloatingPanel.1
            @Override // java.lang.Runnable
            public void run() {
                InvestigationModeFloatingPanel.this.z.sendEmptyMessage(50);
                InvestigationModeFloatingPanel.this.m();
            }
        };
        this.F = new GameAssistControllerWrapper.Callback() { // from class: cn.nubia.plugin.investigation.InvestigationModeFloatingPanel.3
            @Override // com.zte.shared.wrapper.GameAssistControllerWrapper.Callback
            protected void onCallback(Bundle bundle) {
                InvestigationModeFloatingPanel.this.f8541i.put("displayToken", bundle.getBinder("displayToken"));
            }
        };
        ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.plugin.investigation.InvestigationModeFloatingPanel.4
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (uri != null && InvestigationModeFloatingPanel.this.f8540h.equals(uri)) {
                    boolean z2 = false;
                    try {
                        if (Settings.Global.getInt(InvestigationModeFloatingPanel.this.f8542j.getContentResolver(), "investigation_mode_enable", 0) == 1) {
                            z2 = true;
                        }
                    } catch (Exception unused) {
                        GaLog.b("InvestigationModePanel", " get url investigation_mode_enable failed!");
                    }
                    GaLog.a("InvestigationModePanel", "onChange curSwitchOn:" + z2);
                    if (z2) {
                        return;
                    }
                    InvestigationModeFloatingPanel.this.i();
                }
            }
        };
        this.G = contentObserver;
        this.f8542j = context;
        Context applicationContext = context.getApplicationContext();
        this.t = applicationContext;
        WindowManager windowManager = (WindowManager) applicationContext.getSystemService("window");
        this.f8546n = windowManager;
        this.A = windowManager.getDefaultDisplay();
        this.f8542j.getContentResolver().registerContentObserver(uriFor, false, contentObserver);
        Path path = new Path();
        this.f8549q = path;
        int i2 = this.u;
        path.addCircle(i2 / 2, this.v / 2, (i2 / 2) - 20, Path.Direction.CW);
        Matrix matrix = new Matrix();
        this.f8550r = matrix;
        matrix.setScale(1.0f, 1.0f);
        Paint paint = new Paint();
        this.f8548p = paint;
        paint.setAntiAlias(true);
        this.f8548p.setStrokeWidth(70.0f);
        this.f8548p.setColor(-16777216);
        this.f8548p.setStyle(Paint.Style.STROKE);
        HandlerThread handlerThread = new HandlerThread("draw thread");
        this.y = handlerThread;
        handlerThread.start();
        this.z = new DrawHandler(this.y.getLooper());
        this.f8547o = Choreographer.getInstance();
    }

    private void h() {
        GaLog.a("InvestigationModePanel", "cancelInputCallback");
        ChoreographerWrapper.removeInputFrame(this.f8547o, this.E);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        synchronized (this.D) {
            try {
                if (this.f8541i.getOrDefault("displayToken", null) == null && Build.VERSION.SDK_INT != 33) {
                    GaLog.k("InvestigationModePanel", "mFloatLayout is null,window removed,not draw again " + this.f8541i);
                }
                this.f8541i.put("screenWidth", Integer.valueOf(this.w));
                this.f8541i.put("screenHeight", Integer.valueOf(this.x));
                this.f8541i.put("widthForMagnification", Integer.valueOf(this.u));
                this.f8541i.put("heightForMagnification", Integer.valueOf(this.v));
                MyOsUtilsWrapper.drawScreenShotBitmapProx(this.f8544l, this.f8541i);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void k() {
        Point point = new Point();
        this.A.getRealSize(point);
        this.w = point.x;
        this.x = point.y;
        GaLog.a("InvestigationModePanel", "getScreenSize:mScreenWidth =" + this.w + " mScreenHeight =" + this.x);
    }

    private void l(Context context) {
        GaLog.a("InvestigationModePanel", "initFloatLayout");
        View f2 = InflaterHelper.f(R.layout.investigation_mode_floating_panel, null);
        this.f8543k = f2;
        int i2 = this.u;
        int i3 = this.C;
        f2.setBackground(new CircleDrawable(this, -16777216, (i2 + i3) / 2, (this.v + i3) / 2, i2 / 2, i3));
        SurfaceView surfaceView = (SurfaceView) this.f8543k.findViewById(R.id.investigation_mode_window);
        this.f8544l = surfaceView;
        surfaceView.setZOrderOnTop(true);
        this.f8544l.setAlpha(1.0f);
        n(this.f8544l, this.u / 2.0f);
        SurfaceHolder holder = this.f8544l.getHolder();
        this.f8551s = holder;
        holder.addCallback(this);
        this.f8551s.setFormat(-3);
        this.f8543k.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cn.nubia.plugin.investigation.InvestigationModeFloatingPanel.2
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (InvestigationModeFloatingPanel.this.f8543k == null) {
                    return false;
                }
                InvestigationModeFloatingPanel.this.f8543k.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
        this.f8543k.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ChoreographerWrapper.postInputFrame(this.f8547o, this.E);
    }

    private void n(SurfaceView surfaceView, float f2) {
        try {
            SurfaceView.class.getMethod("setCornerRadius", Float.TYPE).invoke(surfaceView, Float.valueOf(f2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void o() {
        Bundle bundle = new Bundle();
        bundle.putInt("displayId", this.A.getDisplayId());
        GameAssistControllerWrapper.invake("getDisplayToken", bundle, this.F);
    }

    private void q() {
        GaLog.a("InvestigationModePanel", "startHideAnimator-removeView");
        synchronized (this.D) {
            try {
                View view = this.f8543k;
                if (view != null) {
                    view.setVisibility(8);
                    this.f8546n.removeView(this.f8543k);
                    this.f8543k = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void i() {
        h();
        this.z.removeMessages(50);
        q();
    }

    public void p() {
        GaLog.a("InvestigationModePanel", "showUp mFloatLayout=" + this.f8543k);
        if (this.f8543k == null) {
            Settings.Global.putInt(this.f8542j.getContentResolver(), "investigation_mode_enable", 1);
            k();
            l(this.t);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.f8545m = layoutParams;
            layoutParams.type = 2038;
            layoutParams.setTitle("InvestigationModeFloatingPanel");
            WindowManager.LayoutParams layoutParams2 = this.f8545m;
            layoutParams2.flags = 24;
            layoutParams2.gravity = 17;
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams2);
            WindowManager.LayoutParams layoutParams3 = this.f8545m;
            int i2 = this.u;
            int i3 = this.C;
            layoutParams3.width = i2 + i3;
            layoutParams3.height = this.v + i3;
            layoutParams3.layoutInDisplayCutoutMode = 1;
            this.f8546n.addView(this.f8543k, layoutParams3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        GaLog.a("InvestigationModePanel", "surfaceChanged");
        this.f8551s = surfaceHolder;
        o();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        GaLog.a("InvestigationModePanel", "surfaceCreated");
        j();
        m();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        HardwareBuffer hardwareBuffer;
        GaLog.a("InvestigationModePanel", "surfaceDestroyed");
        synchronized (this.D) {
            try {
                if (this.f8541i.containsKey("screenshotGraphicBuffer") && (hardwareBuffer = (HardwareBuffer) this.f8541i.getOrDefault("screenshotGraphicBuffer", null)) != null && !hardwareBuffer.isClosed()) {
                    this.f8541i.remove("screenshotGraphicBuffer");
                    hardwareBuffer.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
