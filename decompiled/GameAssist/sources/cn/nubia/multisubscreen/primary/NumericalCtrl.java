package cn.nubia.multisubscreen.primary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import cn.nubia.componentcenter.api.performance.ICpuMonitor;
import cn.nubia.componentcenter.api.performance.IGpuMonitor;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.gameassist.common.GameDurationManager;
import cn.nubia.gameassist.dessert.policy.performancemonitor.fpsTicker.FpsTick;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import cn.nubia.systemwrapper.InputChannelWrapper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class NumericalCtrl extends AbsMultiCtrl implements GameDurationManager.CallBack, ObserverManager.SettingCallback, ICpuMonitor.Callback, IGpuMonitor.Callback, MultiSubScreenUtils.PerformanceMonitorGameDurationCallback {
    private Handler A;
    private Handler B;
    private Runnable C;
    private Runnable D;
    private Runnable E;
    private Runnable F;
    private boolean G;
    private String H;
    private boolean I;
    private ICpuMonitor J;
    private IGpuMonitor K;
    private FpsTick L;
    private int M;

    /* renamed from: l, reason: collision with root package name */
    private int f7963l;

    /* renamed from: m, reason: collision with root package name */
    private int f7964m;

    /* renamed from: n, reason: collision with root package name */
    private int f7965n;

    /* renamed from: o, reason: collision with root package name */
    private int f7966o;

    /* renamed from: p, reason: collision with root package name */
    private long f7967p;

    /* renamed from: q, reason: collision with root package name */
    private float f7968q;

    /* renamed from: r, reason: collision with root package name */
    public long f7969r;

    /* renamed from: s, reason: collision with root package name */
    public long f7970s;
    public int t;
    private ICpuMonitor.CpuParameter u;
    private IGpuMonitor.GpuParameter v;
    private BroadcastReceiver w;
    private BroadcastReceiver x;
    private InputChannelWrapper y;
    private int z;

    public NumericalCtrl(Context context, String str) {
        super(context, str);
        this.f7963l = 0;
        this.f7964m = 0;
        this.f7965n = 0;
        this.H = "";
        this.M = 0;
        this.B = new Handler(ThreadManager.c().f());
        this.A = new Handler(ThreadManager.c().j()) { // from class: cn.nubia.multisubscreen.primary.NumericalCtrl.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    NumericalCtrl numericalCtrl = NumericalCtrl.this;
                    numericalCtrl.s0(numericalCtrl.k0());
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    NumericalCtrl numericalCtrl2 = NumericalCtrl.this;
                    numericalCtrl2.s0(numericalCtrl2.U());
                }
            }
        };
    }

    private void A0(float f2) {
        this.f7968q = f2;
        v0();
    }

    private void B0() {
        BroadcastReceiver broadcastReceiver = this.x;
        if (broadcastReceiver != null) {
            try {
                this.f7938h.unregisterReceiver(broadcastReceiver);
            } catch (Exception e2) {
                GaLog.b("MultiSubScreen_Numerical", "unregisterReceiver exception, e  = " + e2);
            }
            this.x = null;
            this.z = 0;
        }
    }

    private void C0() {
        FpsTick fpsTick = this.L;
        if (fpsTick != null) {
            fpsTick.unRegister();
            this.L = null;
            this.f7966o = 0;
        }
    }

    private void D0() {
        InputChannelWrapper inputChannelWrapper = this.y;
        if (inputChannelWrapper != null) {
            inputChannelWrapper.d("MultiSubScreenChannel");
            this.y = null;
            N();
            O();
        }
    }

    private void E0() {
        BroadcastReceiver broadcastReceiver = this.w;
        if (broadcastReceiver != null) {
            try {
                this.f7938h.unregisterReceiver(broadcastReceiver);
            } catch (Exception e2) {
                GaLog.b("MultiSubScreen_Numerical", "unregisterReceiver exception, e  = " + e2);
            }
            this.w = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public void Z() {
        if (this.G) {
            this.A.removeMessages(1);
            this.A.sendEmptyMessageDelayed(1, 150L);
            this.A.sendEmptyMessageDelayed(1, 1500L);
            this.A.sendEmptyMessageDelayed(1, 3000L);
            this.A.sendEmptyMessageDelayed(1, 5000L);
        }
    }

    private void L() {
        if (this.G) {
            this.A.sendEmptyMessage(1);
        }
    }

    private void M() {
        this.M = 0;
        this.f7969r = 0L;
        this.f7970s = 0L;
        this.f7968q = 0.0f;
        this.t = 0;
        this.G = false;
        this.u = null;
        this.v = null;
    }

    private void N() {
        this.f7963l = 0;
    }

    private void O() {
        this.f7965n = 0;
        this.f7964m = 0;
    }

    private void P() {
        long currentTimeMillis = System.currentTimeMillis();
        long totalRxBytes = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
        float f2 = (totalRxBytes - this.f7970s) / ((currentTimeMillis - this.f7969r) / 1000.0f);
        this.f7968q = f2;
        A0(f2);
        this.f7970s = totalRxBytes;
        this.f7969r = currentTimeMillis;
    }

    private void Q() {
        if (this.I) {
            GameDurationManager.n().p(this.H, this);
        }
    }

    public static int R(int i2, int i3, int i4) {
        if (i4 < 0 || i2 == 0 || i3 == 0) {
            return 0;
        }
        return (int) Math.floor(new BigDecimal(Integer.toString(i2)).divide(new BigDecimal(Integer.toString(i3)), i4, 0).doubleValue() * 10.0d * 10.0d);
    }

    private ICpuMonitor T() {
        if (this.J == null) {
            this.J = (ICpuMonitor) Router.getDependence(ICpuMonitor.class);
        }
        return this.J;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String U() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(0);
        jSONArray.put(16500);
        return jSONArray.toString();
    }

    private IGpuMonitor V() {
        if (this.K == null) {
            this.K = (IGpuMonitor) Router.getDependence(IGpuMonitor.class);
        }
        return this.K;
    }

    private boolean Y() {
        return MultiSubScreenUtils.r(this.f7938h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0() {
        boolean Y = Y();
        if (this.G != Y) {
            this.G = Y;
            if (Y) {
                Z();
            } else {
                this.A.removeMessages(1);
                this.A.sendEmptyMessage(2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0() {
        this.H = SystemMgr.t();
        this.I = true;
        Q();
        Z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0() {
        this.I = false;
        Z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d0() {
        this.H = SystemMgr.t();
        Q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e0() {
        this.B.postDelayed(this.C, 1000L);
        P();
        q0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f0() {
        u0();
        this.B.postDelayed(this.D, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g0() {
        L();
        this.B.postDelayed(this.E, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h0() {
        Q();
        this.B.postDelayed(this.F, 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0() {
        this.H = SystemMgr.t();
        n0();
        m0();
        l0();
        o0();
        P();
        Q();
        T().startMonitor(this);
        V().startMonitor(this);
        MultiSubScreenUtils.I(this);
        if (ZteFeature.supportFan()) {
            this.G = Y();
            L();
            ObserverManager.c().b(this.f7938h, Settings.System.getUriFor("fan_state_of_manual"), this);
            ObserverManager.c().b(this.f7938h, Settings.System.getUriFor("fan_state_of_mode"), this);
        }
        if (this.C == null) {
            Runnable runnable = new Runnable() { // from class: cn.nubia.multisubscreen.primary.i
                @Override // java.lang.Runnable
                public final void run() {
                    NumericalCtrl.this.e0();
                }
            };
            this.C = runnable;
            this.B.postDelayed(runnable, 1000L);
        }
        if (this.D == null) {
            Runnable runnable2 = new Runnable() { // from class: cn.nubia.multisubscreen.primary.j
                @Override // java.lang.Runnable
                public final void run() {
                    NumericalCtrl.this.f0();
                }
            };
            this.D = runnable2;
            this.B.postDelayed(runnable2, 5000L);
        }
        if (this.E == null) {
            Runnable runnable3 = new Runnable() { // from class: cn.nubia.multisubscreen.primary.k
                @Override // java.lang.Runnable
                public final void run() {
                    NumericalCtrl.this.g0();
                }
            };
            this.E = runnable3;
            this.B.postDelayed(runnable3, 10000L);
        }
        if (this.F == null) {
            Runnable runnable4 = new Runnable() { // from class: cn.nubia.multisubscreen.primary.b
                @Override // java.lang.Runnable
                public final void run() {
                    NumericalCtrl.this.h0();
                }
            };
            this.F = runnable4;
            this.B.postDelayed(runnable4, 60000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0() {
        D0();
        C0();
        B0();
        E0();
        T().stopMonitor(this);
        V().stopMonitor(this);
        MultiSubScreenUtils.I(null);
        M();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String k0() {
        GaLog.e("MultiSubScreen_Numerical", "read fan speed");
        int parseInt = Integer.parseInt(Utils.l("sys/kernel/fan/fan_speed_count"));
        GaLog.e("MultiSubScreen_Numerical", "fan speed " + parseInt);
        if (parseInt < 0 || this.t == parseInt) {
            return null;
        }
        this.t = parseInt;
        if (parseInt > 16500) {
            parseInt = 16500;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(parseInt);
        jSONArray.put(16500);
        return jSONArray.toString();
    }

    private void l0() {
        if (this.x == null) {
            this.x = new BroadcastReceiver() { // from class: cn.nubia.multisubscreen.primary.NumericalCtrl.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    int intExtra;
                    if (!intent.getAction().equals("android.intent.action.BATTERY_CHANGED") || (intExtra = (int) ((intent.getIntExtra("level", 0) * 100.0f) / intent.getIntExtra("scale", 100))) == NumericalCtrl.this.z) {
                        return;
                    }
                    NumericalCtrl.this.x0(intExtra);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            this.f7938h.registerReceiver(this.x, intentFilter, 2);
        }
        p0();
    }

    private void m0() {
        if (this.L == null) {
            FpsTick fpsTick = new FpsTick() { // from class: cn.nubia.multisubscreen.primary.NumericalCtrl.5
                @Override // cn.nubia.gameassist.dessert.policy.performancemonitor.fpsTicker.FpsTick
                public void onFps(int i2, float f2, String str, String str2) {
                    if (i2 != NumericalCtrl.this.f7966o) {
                        GaLog.a("MultiSubScreen_Numerical", "onFps: fps = " + i2);
                    }
                    NumericalCtrl.this.y0(i2);
                }
            };
            this.L = fpsTick;
            fpsTick.register();
        }
        t0();
    }

    private void n0() {
        if (this.y == null) {
            InputChannelWrapper a2 = InputChannelWrapper.a();
            this.y = a2;
            a2.c(Looper.getMainLooper(), this.f7938h, "MultiSubScreenChannel", new InputChannelWrapper.EventListener() { // from class: cn.nubia.multisubscreen.primary.NumericalCtrl.4
                @Override // cn.nubia.systemwrapper.InputChannelWrapper.EventListener
                public void onInputEvent(InputEvent inputEvent) {
                    if (inputEvent instanceof MotionEvent) {
                        int actionMasked = ((MotionEvent) inputEvent).getActionMasked();
                        if (actionMasked == 0) {
                            NumericalCtrl.this.f7963l++;
                            NumericalCtrl.this.f7965n++;
                        } else if (actionMasked == 5) {
                            NumericalCtrl.this.f7963l++;
                            NumericalCtrl.this.f7964m++;
                            NumericalCtrl.this.f7965n++;
                        }
                    } else if ((inputEvent instanceof KeyEvent) && ((KeyEvent) inputEvent).getAction() == 0) {
                        NumericalCtrl.this.f7963l++;
                        NumericalCtrl.this.f7965n++;
                    }
                    super.onInputEvent(inputEvent);
                }
            });
        }
        q0();
        u0();
    }

    private void o0() {
        if (this.w == null) {
            this.w = new BroadcastReceiver() { // from class: cn.nubia.multisubscreen.primary.NumericalCtrl.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    intent.getAction();
                    if ("android.intent.action.TIME_TICK".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction())) {
                        NumericalCtrl.this.r0();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            this.f7938h.registerReceiver(this.w, intentFilter, 2);
        }
        r0();
    }

    private void p0() {
        w0("battery_level", String.valueOf(this.z));
    }

    private void q0() {
        w0("cps", String.valueOf(this.f7963l));
        N();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r0() {
        w0("current_time", W());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0(String str) {
        w0("fan_speed", str);
    }

    private void t0() {
        w0("fps", String.valueOf(this.f7966o));
    }

    private void u0() {
        w0("mpm", String.valueOf(R(this.f7964m, this.f7965n, 2)));
        O();
    }

    private void v0() {
        w0("net", String.valueOf(this.f7968q));
    }

    private void w0(String str, String str2) {
        if (str2 != null) {
            PrimaryDeviceDataMgr.C().k0(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x0(int i2) {
        this.z = i2;
        p0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0(int i2) {
        this.f7966o = i2;
        t0();
    }

    private void z0(long j2) {
        this.f7967p = j2;
        w0("play_time", String.valueOf(j2));
    }

    public void S(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr, String str) {
        printWriter.append((CharSequence) str).println("Numerical");
        printWriter.append((CharSequence) str).append("  fps: ").print(this.f7966o);
        printWriter.append(", net: ").println(this.f7968q);
        if (this.u != null) {
            float f2 = 1000000;
            printWriter.append((CharSequence) str).append("  cpu: ").print(this.u.f5864b / f2);
            printWriter.append("/").print(this.u.f5865c / f2);
            printWriter.println(" GHZ");
        }
        if (this.v != null) {
            float f3 = 1000000;
            printWriter.append((CharSequence) str).append("  gpu: ").print(this.v.f5868b / f3);
            printWriter.append("/").print(this.v.f5869c / f3);
            printWriter.println(" MHZ");
        }
        if (ZteFeature.supportFan()) {
            printWriter.append((CharSequence) str).append("  fan state: ").print(this.G);
            printWriter.append(", fan speed: ").println(this.t);
        }
    }

    public String W() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String X(String str) {
        str.hashCode();
        switch (str) {
            case "play_time":
                return String.valueOf(this.f7967p);
            case "battery_level":
                return String.valueOf(this.z);
            case "cpu":
                try {
                    if (this.u == null) {
                        this.u = T().getCurrentValue();
                    }
                    if (this.u.f5864b <= 0.0f) {
                        return null;
                    }
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(this.u.f5864b);
                    jSONArray.put(this.u.f5865c);
                    return jSONArray.toString();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            case "fps":
                int i2 = this.f7966o;
                if (i2 != 0) {
                    return String.valueOf(i2);
                }
                return null;
            case "gpu":
                try {
                    if (this.v == null) {
                        this.v = V().getCurrentValue();
                    }
                    if (this.v.f5868b <= 0.0f) {
                        return null;
                    }
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put(this.v.f5868b);
                    jSONArray2.put(this.v.f5869c);
                    return jSONArray2.toString();
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            case "net":
                return String.valueOf(this.f7968q);
            case "current_time":
                return W();
            default:
                return null;
        }
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.PerformanceMonitorGameDurationCallback
    public void a(long j2) {
        GaLog.g("MultiSubScreen_Numerical", "onGameDurationChange: time = " + j2);
        z0(j2);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    protected void b() {
    }

    @Override // cn.nubia.multisubscreen.primary.AbsMultiCtrl, cn.nubia.multisubscreen.primary.AbsCtrl
    protected boolean e() {
        return true;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.g
            @Override // java.lang.Runnable
            public final void run() {
                NumericalCtrl.this.i0();
            }
        });
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.h
            @Override // java.lang.Runnable
            public final void run() {
                NumericalCtrl.this.j0();
            }
        });
        Runnable runnable = this.C;
        if (runnable != null) {
            this.B.removeCallbacks(runnable);
            this.C = null;
        }
        Runnable runnable2 = this.D;
        if (runnable2 != null) {
            this.B.removeCallbacks(runnable2);
            this.D = null;
        }
        Runnable runnable3 = this.E;
        if (runnable3 != null) {
            this.B.removeCallbacks(runnable3);
            this.E = null;
        }
        Runnable runnable4 = this.F;
        if (runnable4 != null) {
            this.B.removeCallbacks(runnable4);
            this.F = null;
        }
        this.A.removeMessages(1);
        ObserverManager.c().d(this.f7938h, Settings.System.getUriFor("fan_state_of_manual"), this);
        ObserverManager.c().d(this.f7938h, Settings.System.getUriFor("fan_state_of_mode"), this);
    }

    @Override // cn.nubia.gameassist.common.GameDurationManager.CallBack
    public void onBundlePrepare(Bundle bundle) {
        if (bundle != null) {
            long j2 = bundle.getLong("time");
            GaLog.g("MultiSubScreen_Numerical", "onBundlePrepare: playTime = " + j2);
            z0(j2);
            return;
        }
        GaLog.g("MultiSubScreen_Numerical", "onBundlePrepare: bundle = " + bundle);
        if (this.M < 3) {
            this.B.postDelayed(this.F, 5000L);
            this.M++;
        }
    }

    @Override // cn.nubia.componentcenter.api.performance.ICpuMonitor.Callback
    public void onCpuPerformanceChanged(ICpuMonitor.CpuParameter cpuParameter) {
        this.u = cpuParameter;
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(cpuParameter.f5864b);
            jSONArray.put(cpuParameter.f5865c);
            w0("cpu", jSONArray.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.a
            @Override // java.lang.Runnable
            public final void run() {
                NumericalCtrl.this.b0();
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.c
            @Override // java.lang.Runnable
            public final void run() {
                NumericalCtrl.this.c0();
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.f
            @Override // java.lang.Runnable
            public final void run() {
                NumericalCtrl.this.d0();
            }
        });
    }

    @Override // cn.nubia.componentcenter.api.performance.IGpuMonitor.Callback
    public void onGpuPerformanceChanged(IGpuMonitor.GpuParameter gpuParameter) {
        this.v = gpuParameter;
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(gpuParameter.f5868b);
            jSONArray.put(gpuParameter.f5869c);
            w0("gpu", jSONArray.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        if (uri == null) {
            return;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if ("fan_state_of_manual".equals(lastPathSegment)) {
            this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.d
                @Override // java.lang.Runnable
                public final void run() {
                    NumericalCtrl.this.a0();
                }
            });
        } else if ("fan_state_of_mode".equals(lastPathSegment)) {
            this.B.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.e
                @Override // java.lang.Runnable
                public final void run() {
                    NumericalCtrl.this.Z();
                }
            });
        }
    }
}
