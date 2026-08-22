package cn.nubia.screensaver.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import cn.nubia.screensaver.GSWindowController;
import cn.nubia.screensaver.GameScreensaverManager;
import cn.nubia.screensaver.common.IController;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.sensor.GSSensorController;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.TraceWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class GSSensorController implements SensorEventListener, IController, GSPowerController.PowerCallback, GSWindowController.Callback {
    public static String J = "GameScreensaver.Sensor";
    private boolean A;
    private final GSSensorEventListener D;
    private final GSSensorEventListener E;
    private int F;
    private GSPowerController H;

    /* renamed from: c, reason: collision with root package name */
    private long f9119c;

    /* renamed from: h, reason: collision with root package name */
    private int f9120h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f9121i;

    /* renamed from: j, reason: collision with root package name */
    private Runnable f9122j;

    /* renamed from: k, reason: collision with root package name */
    private Runnable f9123k;

    /* renamed from: m, reason: collision with root package name */
    private int f9125m;

    /* renamed from: n, reason: collision with root package name */
    private final SensorManager f9126n;

    /* renamed from: o, reason: collision with root package name */
    private final Sensor f9127o;

    /* renamed from: p, reason: collision with root package name */
    private final Sensor f9128p;

    /* renamed from: r, reason: collision with root package name */
    private Handler f9130r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f9131s;
    private GameScreensaverManager t;
    private Context u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* renamed from: l, reason: collision with root package name */
    private final List f9124l = new ArrayList();

    /* renamed from: q, reason: collision with root package name */
    private boolean f9129q = true;
    private final Runnable B = new Runnable() { // from class: cn.nubia.screensaver.sensor.f
        @Override // java.lang.Runnable
        public final void run() {
            GSSensorController.this.e0();
        }
    };
    private final AccelerometerValue C = new AccelerometerValue();
    private final DelaySendRotation G = new DelaySendRotation();
    private Runnable I = new Runnable() { // from class: cn.nubia.screensaver.sensor.g
        @Override // java.lang.Runnable
        public final void run() {
            GSSensorController.this.S();
        }
    };

    private final class AccelerometerValue {

        /* renamed from: a, reason: collision with root package name */
        private float f9132a;

        /* renamed from: b, reason: collision with root package name */
        private float f9133b;

        /* renamed from: c, reason: collision with root package name */
        private float f9134c;

        /* renamed from: d, reason: collision with root package name */
        private long f9135d;

        /* renamed from: e, reason: collision with root package name */
        private DecimalFormat f9136e;

        public void a(float f2, float f3, float f4, long j2) {
            this.f9132a = f2;
            this.f9133b = f3;
            this.f9134c = f4;
            this.f9135d = j2;
        }

        public String toString() {
            return "[" + this.f9136e.format(this.f9132a) + "," + this.f9136e.format(this.f9133b) + "," + this.f9136e.format(this.f9134c) + " " + this.f9135d + "]";
        }

        private AccelerometerValue(GSSensorController gSSensorController) {
            this.f9136e = new DecimalFormat("#.00");
        }
    }

    public interface Callback {
        default void a(boolean z) {
        }

        default void f() {
        }

        default void j(boolean z) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DelaySendRotation implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private boolean f9137c;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e() {
            GSSensorController.this.f0(1);
        }

        public void f() {
            if (GSSensorController.this.A) {
                GaLog.e(GSSensorController.J, "sensor reset isHorizontal=" + this.f9137c);
                GSSensorController.this.f9130r.removeCallbacks(this);
                this.f9137c = GSSensorController.this.f9131s;
            }
        }

        public void g(boolean z) {
            if (this.f9137c == z && GSSensorController.this.z) {
                return;
            }
            this.f9137c = z;
            GSSensorController.this.z = true;
            long j2 = GSSensorController.this.t.P() ? 0L : 500L;
            GaLog.e(GSSensorController.J, "setHorizontal isHorizontal=" + z + " " + GSSensorController.this.C + " delay=" + j2);
            GSSensorController.this.f9130r.removeCallbacks(this);
            GSSensorController.this.f9130r.postDelayed(this, j2);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((GSWindowController) GSSensorController.this.t.I(GSWindowController.class)).H() && GSSensorController.this.A) {
                f();
                return;
            }
            if (this.f9137c == GSSensorController.this.f9131s && GSSensorController.this.A) {
                return;
            }
            GSSensorController.this.A = true;
            GSSensorController.this.f9131s = this.f9137c;
            GaLog.e(GSSensorController.J, "sensor callback  onRotationChanged mIsHorizontal=" + GSSensorController.this.f9131s + ", rotation=" + GSSensorController.this.f9120h + " " + GSSensorController.this.f9124l.size());
            GSSensorController.this.f9124l.forEach(new Consumer() { // from class: cn.nubia.screensaver.sensor.j
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((GSSensorController.Callback) obj).f();
                }
            });
            if (!GSSensorController.this.a0() && GSSensorController.this.F == 0 && GSSensorController.this.H.w()) {
                GSSensorController.this.f9130r.postDelayed(new Runnable() { // from class: cn.nubia.screensaver.sensor.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        GSSensorController.DelaySendRotation.this.e();
                    }
                }, 200L);
            }
        }

        private DelaySendRotation() {
        }
    }

    private final class GSSensorEventListener implements SensorEventListener {

        /* renamed from: c, reason: collision with root package name */
        private final SensorEventListener f9139c;

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
            this.f9139c.onAccuracyChanged(sensor, i2);
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            this.f9139c.onSensorChanged(sensorEvent);
        }

        private GSSensorEventListener(GSSensorController gSSensorController, SensorEventListener sensorEventListener) {
            this.f9139c = sensorEventListener;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GSSensorController(GameScreensaverManager gameScreensaverManager) {
        this.t = gameScreensaverManager;
        this.u = gameScreensaverManager.H();
        this.f9130r = this.t.C();
        SensorManager sensorManager = (SensorManager) this.u.getSystemService(SensorManager.class);
        this.f9126n = sensorManager;
        this.f9127o = sensorManager.getDefaultSensor(1);
        this.f9128p = sensorManager.getDefaultSensor(8);
        this.D = new GSSensorEventListener(this);
        this.E = new GSSensorEventListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R(Callback callback) {
        if (this.f9124l.contains(callback)) {
            return;
        }
        this.f9124l.add(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S() {
        ArrayList arrayList = new ArrayList();
        if (this.x && !this.z && this.v) {
            arrayList.add("check accelerometer sensor error and setHorizontal false");
            this.G.g(false);
            if (this.f9125m < 5) {
                arrayList.add("unregister and update");
                this.f9125m++;
                d0(true);
                this.f9130r.removeCallbacks(this.B);
                this.f9130r.post(this.B);
            }
        }
        if (arrayList.size() > 0) {
            GaLog.e(J, "--- " + ((String) arrayList.stream().collect(Collectors.joining(", "))) + " --- ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T(SensorEvent sensorEvent) {
        this.f9122j = null;
        b0(sensorEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U(SensorEvent sensorEvent) {
        this.f9123k = null;
        c0(sensorEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V(Callback callback) {
        if (this.f9124l.contains(callback)) {
            this.f9124l.remove(callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W(Callback callback) {
        callback.j(this.f9129q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X(Callback callback) {
        callback.a(this.F == 1);
    }

    private void Y() {
        this.f9126n.registerListener(this.D, this.f9127o, 2, this.f9130r);
        this.x = true;
        this.z = false;
        this.A = false;
    }

    private void b0(SensorEvent sensorEvent) {
        if (sensorEvent == null) {
            return;
        }
        float[] fArr = sensorEvent.values;
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        long j2 = sensorEvent.timestamp;
        if (j2 >= this.f9119c) {
            if (f2 == 0.0f && f3 == 0.0f && f4 == 0.0f) {
                return;
            }
            TraceWrapper.traceBegin(8L, "Accelerometer:" + f2 + "," + f3 + "," + f4 + "-" + j2);
            this.f9119c = j2;
            this.C.a(f2, f3, f4, j2);
            int round = (int) Math.round(Math.asin((double) (f2 / ((float) Math.sqrt((double) (((f2 * f2) + (f3 * f3)) + (f4 * f4)))))) * 57.295780181884766d);
            this.f9121i = Math.abs(round) > (this.f9131s ? 30 : 45);
            int round2 = (int) Math.round((-Math.atan2(-f2, f3)) * 57.295780181884766d);
            if (round2 < 0) {
                round2 += 360;
            }
            if (GaLog.f17035c) {
                GaLog.e(J, "mAccelerometerValue=" + this.C + " orientationAngle=" + round2 + " tiltAngle=" + round);
            }
            int i2 = (round2 + 45) / 90;
            if (i2 == 4) {
                i2 = 0;
            }
            this.f9120h = i2;
            boolean z = (i2 == 1 || i2 == 3) && this.f9121i;
            this.G.g(z);
            boolean H = ((GSWindowController) this.t.I(GSWindowController.class)).H();
            if (z && !H) {
                boolean z2 = i2 == 1;
                if (this.f9129q != z2) {
                    this.f9129q = z2;
                    this.f9124l.forEach(new Consumer() { // from class: cn.nubia.screensaver.sensor.h
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            GSSensorController.this.W((GSSensorController.Callback) obj);
                        }
                    });
                    this.G.f();
                    GaLog.e(J, "sensor callback  onFrontChanged mIsFront=" + this.f9129q + " isHorizontal=" + this.G.f9137c);
                }
            }
            TraceWrapper.traceEnd(8L);
        }
    }

    private void c0(SensorEvent sensorEvent) {
        GaLog.j(J, "proximity to " + sensorEvent.values[0]);
        f0(sensorEvent.values[0] <= 0.0f ? -1 : 1);
    }

    private void d0(boolean z) {
        this.f9126n.unregisterListener(this.D, this.f9127o);
        this.x = false;
        if (!z) {
            this.C.a(0.0f, 0.0f, 0.0f, 0L);
            this.z = false;
            this.A = false;
        }
        this.f9130r.removeCallbacks(this.I);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0() {
        TraceWrapper.traceBegin(8L, "updatePickupSensorState");
        ArrayList arrayList = new ArrayList();
        boolean z = GameScreensaverManager.I && this.t.R() && this.v;
        boolean z2 = this.x;
        if (!z2 && z && this.f9127o != null) {
            arrayList.add("register Accelerometer");
            Y();
            this.f9130r.removeCallbacks(this.I);
            this.f9130r.postDelayed(this.I, 500L);
        } else if (z2 && !z && this.f9127o != null) {
            arrayList.add("unregister Accelerometer");
            d0(false);
        }
        boolean z3 = GameScreensaverManager.I && this.t.R() && this.w && this.t.P();
        boolean z4 = this.y;
        if (!z4 && z3 && this.f9128p != null) {
            arrayList.add("register Proximity");
            this.F = 0;
            this.f9126n.registerListener(this.E, this.f9128p, 2, this.f9130r);
            this.y = true;
        } else if (!z4 || z3 || this.f9128p == null) {
            this.F = 0;
        } else {
            arrayList.add("unregister Proximity");
            this.f9126n.unregisterListener(this.E, this.f9128p);
            this.y = false;
        }
        if (arrayList.size() > 0) {
            GaLog.e(J, "--- " + this.f9126n.getClass().getSimpleName() + " " + ((String) arrayList.stream().collect(Collectors.joining(", "))) + " --- ");
        }
        TraceWrapper.traceEnd(8L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(int i2) {
        if (this.F != i2) {
            this.F = i2;
            this.f9124l.forEach(new Consumer() { // from class: cn.nubia.screensaver.sensor.e
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GSSensorController.this.X((GSSensorController.Callback) obj);
                }
            });
        }
    }

    public void L(final Callback callback) {
        this.f9130r.post(new Runnable() { // from class: cn.nubia.screensaver.sensor.d
            @Override // java.lang.Runnable
            public final void run() {
                GSSensorController.this.R(callback);
            }
        });
    }

    public int M() {
        return this.F;
    }

    public boolean N() {
        return this.A || this.f9127o == null;
    }

    public boolean O() {
        return this.f9129q;
    }

    public boolean P() {
        return this.f9131s && this.A;
    }

    public boolean Q() {
        return M() == -1;
    }

    public void Z(final Callback callback) {
        this.f9130r.post(new Runnable() { // from class: cn.nubia.screensaver.sensor.c
            @Override // java.lang.Runnable
            public final void run() {
                GSSensorController.this.V(callback);
            }
        });
    }

    @Override // cn.nubia.screensaver.common.IController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + J);
        String str2 = str + "  ";
        printWriter.println(str2 + "mIsFront=" + this.f9129q);
        printWriter.println(str2 + "mTiltHorizontal=" + this.f9121i);
        printWriter.println(str2 + "mHasAccelerometerValue=" + this.z);
        printWriter.println(str2 + "mAccelerometerValue=" + this.C);
        printWriter.println(str2 + "mHasHorizontalValue=" + this.A);
        printWriter.println(str2 + "mIsHorizontal=" + this.f9131s);
        printWriter.println(str2 + "sensorHorizontal=" + this.G.f9137c);
        printWriter.println(str2 + "mRotation=" + this.f9120h);
        printWriter.println(str2 + "isRegisterProximity=" + this.y);
        printWriter.println(str2 + "isRegisterAccelerometer=" + this.x);
        printWriter.println(str2 + "mAccelerometerSensor=" + this.f9127o);
        printWriter.println(str2 + "mProximitySensor=" + this.f9128p);
    }

    public boolean a0() {
        return this.f9128p != null;
    }

    @Override // cn.nubia.screensaver.GSWindowController.Callback
    public void c() {
        this.w = true;
        this.f9130r.removeCallbacks(this.B);
        this.f9130r.post(this.B);
    }

    @Override // cn.nubia.screensaver.common.IController
    public void f() {
        GSPowerController gSPowerController = (GSPowerController) this.t.I(GSPowerController.class);
        this.H = gSPowerController;
        gSPowerController.t(this);
        ((GSWindowController) this.t.I(GSWindowController.class)).x(this);
        this.v = ((GSPowerController) this.t.I(GSPowerController.class)).y();
        e0();
    }

    @Override // cn.nubia.screensaver.common.IController
    public void j(int i2) {
        this.v = i2 == 3 || i2 == 2;
        if (i2 == 2) {
            this.f9125m = 0;
            if (this.x && !this.z) {
                this.f9130r.removeCallbacks(this.I);
                this.f9130r.postDelayed(this.I, 500L);
            }
        }
        this.f9130r.removeCallbacks(this.B);
        this.f9130r.post(this.B);
    }

    @Override // cn.nubia.screensaver.GSWindowController.Callback
    public void k() {
        this.w = false;
        this.f9130r.removeCallbacks(this.B);
        this.f9130r.post(this.B);
    }

    @Override // cn.nubia.screensaver.common.IController
    public void o(int i2, boolean z) {
        if (i2 == 0) {
            this.f9130r.removeCallbacks(this.B);
            this.f9130r.post(this.B);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
        GaLog.e(J, "onAccuracyChanged sensor=" + sensor + " accuracy=" + i2);
    }

    @Override // android.hardware.SensorEventListener
    public synchronized void onSensorChanged(final SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            try {
                if (sensorEvent.values != null) {
                    int type = sensorEvent.sensor.getType();
                    if (type == 1) {
                        this.f9130r.removeCallbacks(this.I);
                        this.f9125m = 0;
                        Runnable runnable = this.f9122j;
                        if (runnable != null) {
                            this.f9130r.removeCallbacks(runnable);
                        }
                        Runnable runnable2 = new Runnable() { // from class: cn.nubia.screensaver.sensor.a
                            @Override // java.lang.Runnable
                            public final void run() {
                                GSSensorController.this.T(sensorEvent);
                            }
                        };
                        this.f9122j = runnable2;
                        this.f9130r.post(runnable2);
                    } else if (type != 8) {
                        GaLog.e(J, "---unknown--- sensor " + sensorEvent.sensor);
                    } else {
                        Runnable runnable3 = this.f9123k;
                        if (runnable3 != null) {
                            this.f9130r.removeCallbacks(runnable3);
                        }
                        Runnable runnable4 = new Runnable() { // from class: cn.nubia.screensaver.sensor.b
                            @Override // java.lang.Runnable
                            public final void run() {
                                GSSensorController.this.U(sensorEvent);
                            }
                        };
                        this.f9123k = runnable4;
                        this.f9130r.postDelayed(runnable4, 200L);
                    }
                }
            } finally {
            }
        }
    }
}
