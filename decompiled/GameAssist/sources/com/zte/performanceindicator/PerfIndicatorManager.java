package com.zte.performanceindicator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.os.Handler;
import android.os.Message;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import cn.nubia.componentcenter.api.power.IPowerStateMonitor;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameAssistComService;
import com.zte.gameassist.common.ThreadManager;
import com.zte.performanceindicator.network.NetworkLatencyCheck;
import com.zte.performanceindicator.utils.Utils;
import com.zte.performanceindicator.widget.PerformanceIndicatorWidget;
import java.util.ArrayList;
import java.util.Comparator;

/* loaded from: classes2.dex */
public class PerfIndicatorManager {
    private static int w = 1;
    private static int x = 2;
    private static int y = 3;
    private static volatile PerfIndicatorManager z;

    /* renamed from: a, reason: collision with root package name */
    private Context f17896a;

    /* renamed from: b, reason: collision with root package name */
    private BroadcastReceiver f17897b;

    /* renamed from: c, reason: collision with root package name */
    private BroadcastReceiver f17898c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f17899d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f17900e;

    /* renamed from: h, reason: collision with root package name */
    private ConnectivityManager f17903h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f17904i;

    /* renamed from: l, reason: collision with root package name */
    private boolean f17907l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f17908m;

    /* renamed from: p, reason: collision with root package name */
    private UserManager f17911p;

    /* renamed from: q, reason: collision with root package name */
    private IPowerStateMonitor f17912q;

    /* renamed from: f, reason: collision with root package name */
    private NetworkLatencyCheck f17901f = null;

    /* renamed from: g, reason: collision with root package name */
    private int f17902g = 0;

    /* renamed from: j, reason: collision with root package name */
    private long f17905j = -1;

    /* renamed from: k, reason: collision with root package name */
    private long f17906k = -1;

    /* renamed from: n, reason: collision with root package name */
    public boolean f17909n = false;

    /* renamed from: o, reason: collision with root package name */
    public boolean f17910o = false;

    /* renamed from: r, reason: collision with root package name */
    private final IPowerStateMonitor.PowerStateCallback f17913r = new IPowerStateMonitor.PowerStateCallback() { // from class: com.zte.performanceindicator.PerfIndicatorManager.1
        @Override // cn.nubia.componentcenter.api.power.IPowerStateMonitor.PowerStateCallback
        public void onGotoSleep() {
            if (PerfIndicatorManager.this.f17899d) {
                Log.i("PerfIndicatorManager", "mPowerStateCallback onGotoSleep");
                PerfIndicatorManager.this.f17899d = false;
                PerfIndicatorManager.this.f17900e = true;
            }
        }

        @Override // cn.nubia.componentcenter.api.power.IPowerStateMonitor.PowerStateCallback
        public void onWakingUp() {
        }
    };

    /* renamed from: s, reason: collision with root package name */
    private ArrayList f17914s = new ArrayList();
    private ArrayList t = new ArrayList();
    private Handler u = new Handler(ThreadManager.c().g()) { // from class: com.zte.performanceindicator.PerfIndicatorManager.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == PerfIndicatorManager.w) {
                PerfIndicatorManager.this.c();
            } else if (message.what == PerfIndicatorManager.x) {
                PerfIndicatorManager.this.b();
            }
        }
    };
    private final NetworkLatencyCheck.NetworkLatencyCheckListener v = new NetworkLatencyCheck.NetworkLatencyCheckListener() { // from class: com.zte.performanceindicator.PerfIndicatorManager.3
        @Override // com.zte.performanceindicator.network.NetworkLatencyCheck.NetworkLatencyCheckListener
        public void a() {
            PerfIndicatorManager.this.u.sendMessage(PerfIndicatorManager.this.u.obtainMessage(PerfIndicatorManager.w));
        }
    };

    private class LockScreenBroadcast extends BroadcastReceiver {
        private LockScreenBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                return;
            }
            Log.i("PerfIndicatorManager", "onReceive: ACTION_USER_PRESENT");
            PerfIndicatorManager.this.f17899d = true;
            PerfIndicatorManager.this.f17900e = false;
        }
    }

    private class NetworkBroadcast extends BroadcastReceiver {
        private NetworkBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                return;
            }
            Log.i("PerfIndicatorManager", "onReceive: ACTION_NETWORK_CHANGE");
            if (PerfIndicatorManager.this.x() || !PerfIndicatorManager.this.f17911p.isUserUnlocked()) {
                return;
            }
            Intent intent2 = new Intent(PerfIndicatorManager.this.f17896a, (Class<?>) PerformanceIndicatorWidget.class);
            intent2.setAction("com.zte.performanceindicator.CONNECTIVITY_CHANGE");
            PerfIndicatorManager.this.f17896a.sendBroadcast(intent2);
        }
    }

    private PerfIndicatorManager() {
        this.f17897b = new NetworkBroadcast();
        this.f17898c = new LockScreenBroadcast();
    }

    private void A() {
        Log.d("PerfIndicatorManager", "sendWidgetBroadcast: ");
        Intent intent = new Intent(this.f17896a, (Class<?>) PerformanceIndicatorWidget.class);
        intent.setAction("com.zte.performanceindicator.ACTION_NETWORK_RESULT");
        intent.putExtra("isCheckError", this.f17904i);
        intent.putExtra("latency", this.f17905j);
        intent.putExtra("jitter", this.f17906k);
        this.f17896a.sendBroadcast(intent);
    }

    private void D(NetworkLatencyCheck.MeasurementStatistic measurementStatistic) {
        this.f17905j = measurementStatistic.a();
        this.f17906k = measurementStatistic.b();
        this.f17904i = false;
    }

    private void E(Context context) {
        boolean b2 = Utils.b(context);
        this.f17900e = b2;
        this.f17899d = !b2;
        Log.i("PerfIndicatorManager", "updateKeyguardStatus: mIsKeyguardShow=" + this.f17900e + " mIsFirstScreenOff=" + this.f17899d);
    }

    private void F() {
        boolean d2 = this.f17901f.u().d();
        boolean d3 = this.f17901f.s().d();
        if (!d2) {
            Log.d("PerfIndicatorManager", "updateNetworkData: return ICMP result");
            D(u(this.f17914s));
        } else if (d3) {
            Log.d("PerfIndicatorManager", "updateNetworkData: return ICMP and DNS result failed");
            this.f17905j = -1L;
            this.f17906k = -1L;
            this.f17904i = true;
        } else {
            Log.d("PerfIndicatorManager", "updateNetworkData: retrun DNS result");
            D(u(this.t));
        }
        Log.d("PerfIndicatorManager", "mMeasurementICMP after update updateNetworkData " + this.f17914s.size());
        this.t.clear();
        this.f17914s.clear();
        Log.d("PerfIndicatorManager", "updateNetworkData: mIsCheckError=" + this.f17904i + " mLatency=" + this.f17905j + " mJitter=" + this.f17906k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Log.d("PerfIndicatorManager", "MSG_START_NETWORK_LATENCY_CHECK mReCheckCount " + this.f17902g);
        if (this.f17902g == 0) {
            Log.d("PerfIndicatorManager", "mMeasurementICMP start networkchek mReCheckCount is 0 mMeasurementICMP size is " + this.f17914s.size());
        }
        ConnectivityManager connectivityManager = this.f17903h;
        if (connectivityManager == null || this.f17896a == null) {
            return;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null) {
            NetworkLatencyCheck networkLatencyCheck = new NetworkLatencyCheck(activeNetwork, new LinkProperties(this.f17903h.getLinkProperties(activeNetwork)), 1500L, this.f17896a);
            this.f17901f = networkLatencyCheck;
            networkLatencyCheck.J(this.v);
        }
        NetworkLatencyCheck networkLatencyCheck2 = this.f17901f;
        if (networkLatencyCheck2 != null) {
            networkLatencyCheck2.K();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        int i2;
        int i3;
        NetworkLatencyCheck networkLatencyCheck = this.f17901f;
        if (networkLatencyCheck != null) {
            NetworkLatencyCheck.MeasurementStatistic s2 = networkLatencyCheck.s();
            NetworkLatencyCheck.MeasurementStatistic u = this.f17901f.u();
            o(s2);
            p(u);
            if ((s2.a() > 100 || u.a() > 100) && (i2 = this.f17902g) < y) {
                this.f17902g = i2 + 1;
                Log.d("PerfIndicatorManager", "latency is > 100, so recheck " + this.f17902g);
                Handler handler = this.u;
                handler.sendMessageDelayed(handler.obtainMessage(x), 200L);
                return;
            }
            if ((s2.c() == 0 || u.c() == 0) && (i3 = this.f17902g) < y) {
                this.f17902g = i3 + 1;
                Log.d("PerfIndicatorManager", "latency is 0 so recheck " + this.f17902g);
                Handler handler2 = this.u;
                handler2.sendMessageDelayed(handler2.obtainMessage(x), 200L);
                return;
            }
            Log.d("PerfIndicatorManager", "MSG_UPDATE_LATENCY_RESULT mReCheckCount " + this.f17902g);
            F();
            C(false);
            if (this.f17907l) {
                A();
            }
            if (this.f17908m) {
                q();
            }
            this.f17901f.O(this.v);
            this.f17901f = null;
            this.f17902g = 0;
            this.f17908m = false;
            this.f17907l = false;
        }
    }

    private void o(NetworkLatencyCheck.MeasurementStatistic measurementStatistic) {
        if (measurementStatistic != null) {
            this.t.add(measurementStatistic);
        }
    }

    private void p(NetworkLatencyCheck.MeasurementStatistic measurementStatistic) {
        if (measurementStatistic != null) {
            this.f17914s.add(measurementStatistic);
        }
    }

    private void q() {
        Settings.Global.putString(this.f17896a.getContentResolver(), "perf_indicator_network_result", this.f17904i + "_" + this.f17905j + "_" + this.f17906k + "_" + Utils.a());
    }

    public static PerfIndicatorManager t() {
        if (z == null) {
            synchronized (PerfIndicatorManager.class) {
                try {
                    if (z == null) {
                        z = new PerfIndicatorManager();
                    }
                } finally {
                }
            }
        }
        return z;
    }

    private NetworkLatencyCheck.MeasurementStatistic u(ArrayList arrayList) {
        return (NetworkLatencyCheck.MeasurementStatistic) arrayList.stream().min(new Comparator() { // from class: com.zte.performanceindicator.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int y2;
                y2 = PerfIndicatorManager.y((NetworkLatencyCheck.MeasurementStatistic) obj, (NetworkLatencyCheck.MeasurementStatistic) obj2);
                return y2;
            }
        }).orElse(null);
    }

    private synchronized IPowerStateMonitor v() {
        try {
            if (this.f17912q == null) {
                this.f17912q = (IPowerStateMonitor) ((GameAssistComService) Router.getInstance().getService(GameAssistComService.class.getSimpleName())).a(IPowerStateMonitor.class);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f17912q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x() {
        return this.f17900e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int y(NetworkLatencyCheck.MeasurementStatistic measurementStatistic, NetworkLatencyCheck.MeasurementStatistic measurementStatistic2) {
        return Long.compare(measurementStatistic.a(), measurementStatistic2.a());
    }

    private void z() {
        v().setListening(true, this.f17913r);
    }

    public void B(boolean z2) {
        this.f17910o = z2;
    }

    public void C(boolean z2) {
        this.f17909n = z2;
    }

    public void r() {
        Log.d("PerfIndicatorManager", "detectForGameSpace: ");
        this.f17908m = true;
        Handler handler = this.u;
        handler.sendMessage(handler.obtainMessage(x));
    }

    public void s() {
        Log.d("PerfIndicatorManager", "detectForWidget: ");
        this.f17907l = true;
        C(true);
        Handler handler = this.u;
        handler.sendMessage(handler.obtainMessage(x));
    }

    public void w(Context context) {
        Log.d("PerfIndicatorManager", "init: ");
        this.f17896a = context;
        this.f17903h = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.f17911p = (UserManager) this.f17896a.getSystemService("user");
        E(this.f17896a);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.f17896a.registerReceiver(this.f17898c, intentFilter, 2);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f17896a.registerReceiver(this.f17897b, intentFilter2, 2);
        z();
    }
}
