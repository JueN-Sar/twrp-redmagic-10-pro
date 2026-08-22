package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public class GoogleApiManager implements Handler.Callback {
    public static final Status v = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status w = new Status(4, "The user must be signed in to make this API call.");
    private static final Object x = new Object();
    private static GoogleApiManager y;

    /* renamed from: i, reason: collision with root package name */
    private TelemetryData f10586i;

    /* renamed from: j, reason: collision with root package name */
    private TelemetryLoggingClient f10587j;

    /* renamed from: k, reason: collision with root package name */
    private final Context f10588k;

    /* renamed from: l, reason: collision with root package name */
    private final GoogleApiAvailability f10589l;

    /* renamed from: m, reason: collision with root package name */
    private final com.google.android.gms.common.internal.zal f10590m;
    private final Handler t;
    private volatile boolean u;

    /* renamed from: c, reason: collision with root package name */
    private long f10584c = 10000;

    /* renamed from: h, reason: collision with root package name */
    private boolean f10585h = false;

    /* renamed from: n, reason: collision with root package name */
    private final AtomicInteger f10591n = new AtomicInteger(1);

    /* renamed from: o, reason: collision with root package name */
    private final AtomicInteger f10592o = new AtomicInteger(0);

    /* renamed from: p, reason: collision with root package name */
    private final Map f10593p = new ConcurrentHashMap(5, 0.75f, 1);

    /* renamed from: q, reason: collision with root package name */
    private zaae f10594q = null;

    /* renamed from: r, reason: collision with root package name */
    private final Set f10595r = new ArraySet();

    /* renamed from: s, reason: collision with root package name */
    private final Set f10596s = new ArraySet();

    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.u = true;
        this.f10588k = context;
        com.google.android.gms.internal.base.zau zauVar = new com.google.android.gms.internal.base.zau(looper, this);
        this.t = zauVar;
        this.f10589l = googleApiAvailability;
        this.f10590m = new com.google.android.gms.common.internal.zal(googleApiAvailability);
        if (DeviceProperties.a(context)) {
            this.u = false;
        }
        zauVar.sendMessage(zauVar.obtainMessage(6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status f(ApiKey apiKey, ConnectionResult connectionResult) {
        return new Status(connectionResult, "API: " + apiKey.b() + " is not available on this device. Connection failed with: " + String.valueOf(connectionResult));
    }

    private final zabq g(GoogleApi googleApi) {
        Map map = this.f10593p;
        ApiKey i2 = googleApi.i();
        zabq zabqVar = (zabq) map.get(i2);
        if (zabqVar == null) {
            zabqVar = new zabq(this, googleApi);
            this.f10593p.put(i2, zabqVar);
        }
        if (zabqVar.a()) {
            this.f10596s.add(i2);
        }
        zabqVar.D();
        return zabqVar;
    }

    private final TelemetryLoggingClient h() {
        if (this.f10587j == null) {
            this.f10587j = TelemetryLogging.a(this.f10588k);
        }
        return this.f10587j;
    }

    private final void i() {
        TelemetryData telemetryData = this.f10586i;
        if (telemetryData != null) {
            if (telemetryData.G() > 0 || d()) {
                h().a(telemetryData);
            }
            this.f10586i = null;
        }
    }

    private final void j(TaskCompletionSource taskCompletionSource, int i2, GoogleApi googleApi) {
        zacd b2;
        if (i2 == 0 || (b2 = zacd.b(this, i2, googleApi.i())) == null) {
            return;
        }
        Task a2 = taskCompletionSource.a();
        final Handler handler = this.t;
        handler.getClass();
        a2.c(new Executor() { // from class: com.google.android.gms.common.api.internal.zabk
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, b2);
    }

    public static GoogleApiManager t(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (x) {
            try {
                if (y == null) {
                    y = new GoogleApiManager(context.getApplicationContext(), GmsClientSupervisor.c().getLooper(), GoogleApiAvailability.q());
                }
                googleApiManager = y;
            } catch (Throwable th) {
                throw th;
            }
        }
        return googleApiManager;
    }

    public final void B(GoogleApi googleApi, int i2, BaseImplementation.ApiMethodImpl apiMethodImpl) {
        this.t.sendMessage(this.t.obtainMessage(4, new zach(new zae(i2, apiMethodImpl), this.f10592o.get(), googleApi)));
    }

    public final void C(GoogleApi googleApi, int i2, TaskApiCall taskApiCall, TaskCompletionSource taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        j(taskCompletionSource, taskApiCall.d(), googleApi);
        this.t.sendMessage(this.t.obtainMessage(4, new zach(new zag(i2, taskApiCall, taskCompletionSource, statusExceptionMapper), this.f10592o.get(), googleApi)));
    }

    final void D(MethodInvocation methodInvocation, int i2, long j2, int i3) {
        this.t.sendMessage(this.t.obtainMessage(18, new zace(methodInvocation, i2, j2, i3)));
    }

    public final void E(ConnectionResult connectionResult, int i2) {
        if (e(connectionResult, i2)) {
            return;
        }
        Handler handler = this.t;
        handler.sendMessage(handler.obtainMessage(5, i2, 0, connectionResult));
    }

    public final void F() {
        Handler handler = this.t;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void G(GoogleApi googleApi) {
        Handler handler = this.t;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public final void a(zaae zaaeVar) {
        synchronized (x) {
            try {
                if (this.f10594q != zaaeVar) {
                    this.f10594q = zaaeVar;
                    this.f10595r.clear();
                }
                this.f10595r.addAll(zaaeVar.i());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    final void b(zaae zaaeVar) {
        synchronized (x) {
            try {
                if (this.f10594q == zaaeVar) {
                    this.f10594q = null;
                    this.f10595r.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    final boolean d() {
        if (this.f10585h) {
            return false;
        }
        RootTelemetryConfiguration a2 = RootTelemetryConfigManager.b().a();
        if (a2 != null && !a2.R()) {
            return false;
        }
        int a3 = this.f10590m.a(this.f10588k, 203400000);
        return a3 == -1 || a3 == 0;
    }

    final boolean e(ConnectionResult connectionResult, int i2) {
        return this.f10589l.A(this.f10588k, connectionResult, i2);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        ApiKey apiKey;
        boolean p2;
        ApiKey apiKey2;
        ApiKey apiKey3;
        ApiKey apiKey4;
        ApiKey apiKey5;
        int i2 = message.what;
        zabq zabqVar = null;
        switch (i2) {
            case 1:
                this.f10584c = true == ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.t.removeMessages(12);
                for (ApiKey apiKey6 : this.f10593p.keySet()) {
                    Handler handler = this.t;
                    handler.sendMessageDelayed(handler.obtainMessage(12, apiKey6), this.f10584c);
                }
                return true;
            case 2:
                zal zalVar = (zal) message.obj;
                Iterator it = zalVar.a().iterator();
                while (true) {
                    if (it.hasNext()) {
                        ApiKey apiKey7 = (ApiKey) it.next();
                        zabq zabqVar2 = (zabq) this.f10593p.get(apiKey7);
                        if (zabqVar2 == null) {
                            zalVar.b(apiKey7, new ConnectionResult(13), null);
                        } else if (zabqVar2.O()) {
                            zalVar.b(apiKey7, ConnectionResult.f10484k, zabqVar2.u().c());
                        } else {
                            ConnectionResult s2 = zabqVar2.s();
                            if (s2 != null) {
                                zalVar.b(apiKey7, s2, null);
                            } else {
                                zabqVar2.I(zalVar);
                                zabqVar2.D();
                            }
                        }
                    }
                }
                return true;
            case 3:
                for (zabq zabqVar3 : this.f10593p.values()) {
                    zabqVar3.C();
                    zabqVar3.D();
                }
                return true;
            case 4:
            case 8:
            case 13:
                zach zachVar = (zach) message.obj;
                zabq zabqVar4 = (zabq) this.f10593p.get(zachVar.f10806c.i());
                if (zabqVar4 == null) {
                    zabqVar4 = g(zachVar.f10806c);
                }
                if (!zabqVar4.a() || this.f10592o.get() == zachVar.f10805b) {
                    zabqVar4.E(zachVar.f10804a);
                } else {
                    zachVar.f10804a.a(v);
                    zabqVar4.K();
                }
                return true;
            case 5:
                int i3 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it2 = this.f10593p.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zabq zabqVar5 = (zabq) it2.next();
                        if (zabqVar5.q() == i3) {
                            zabqVar = zabqVar5;
                        }
                    }
                }
                if (zabqVar == null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i3 + " while trying to fail enqueued calls.", new Exception());
                } else if (connectionResult.G() == 13) {
                    zabqVar.e(new Status(17, "Error resolution was canceled by the user, original error message: " + this.f10589l.g(connectionResult.G()) + ": " + connectionResult.P()));
                } else {
                    apiKey = zabqVar.f10765c;
                    zabqVar.e(f(apiKey, connectionResult));
                }
                return true;
            case 6:
                if (this.f10588k.getApplicationContext() instanceof Application) {
                    BackgroundDetector.c((Application) this.f10588k.getApplicationContext());
                    BackgroundDetector.b().a(new zabl(this));
                    if (!BackgroundDetector.b().e(true)) {
                        this.f10584c = 300000L;
                    }
                }
                return true;
            case 7:
                g((GoogleApi) message.obj);
                return true;
            case 9:
                if (this.f10593p.containsKey(message.obj)) {
                    ((zabq) this.f10593p.get(message.obj)).J();
                }
                return true;
            case 10:
                Iterator it3 = this.f10596s.iterator();
                while (it3.hasNext()) {
                    zabq zabqVar6 = (zabq) this.f10593p.remove((ApiKey) it3.next());
                    if (zabqVar6 != null) {
                        zabqVar6.K();
                    }
                }
                this.f10596s.clear();
                return true;
            case 11:
                if (this.f10593p.containsKey(message.obj)) {
                    ((zabq) this.f10593p.get(message.obj)).L();
                }
                return true;
            case 12:
                if (this.f10593p.containsKey(message.obj)) {
                    ((zabq) this.f10593p.get(message.obj)).b();
                }
                return true;
            case 14:
                zaaf zaafVar = (zaaf) message.obj;
                ApiKey a2 = zaafVar.a();
                if (this.f10593p.containsKey(a2)) {
                    p2 = ((zabq) this.f10593p.get(a2)).p(false);
                    zaafVar.b().c(Boolean.valueOf(p2));
                } else {
                    zaafVar.b().c(Boolean.FALSE);
                }
                return true;
            case 15:
                zabs zabsVar = (zabs) message.obj;
                Map map = this.f10593p;
                apiKey2 = zabsVar.f10776a;
                if (map.containsKey(apiKey2)) {
                    Map map2 = this.f10593p;
                    apiKey3 = zabsVar.f10776a;
                    zabq.A((zabq) map2.get(apiKey3), zabsVar);
                }
                return true;
            case 16:
                zabs zabsVar2 = (zabs) message.obj;
                Map map3 = this.f10593p;
                apiKey4 = zabsVar2.f10776a;
                if (map3.containsKey(apiKey4)) {
                    Map map4 = this.f10593p;
                    apiKey5 = zabsVar2.f10776a;
                    zabq.B((zabq) map4.get(apiKey5), zabsVar2);
                }
                return true;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                i();
                return true;
            case MlKitException.UNSUPPORTED /* 18 */:
                zace zaceVar = (zace) message.obj;
                if (zaceVar.f10799c == 0) {
                    h().a(new TelemetryData(zaceVar.f10798b, Arrays.asList(zaceVar.f10797a)));
                } else {
                    TelemetryData telemetryData = this.f10586i;
                    if (telemetryData != null) {
                        List P = telemetryData.P();
                        if (telemetryData.G() != zaceVar.f10798b || (P != null && P.size() >= zaceVar.f10800d)) {
                            this.t.removeMessages(17);
                            i();
                        } else {
                            this.f10586i.R(zaceVar.f10797a);
                        }
                    }
                    if (this.f10586i == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(zaceVar.f10797a);
                        this.f10586i = new TelemetryData(zaceVar.f10798b, arrayList);
                        Handler handler2 = this.t;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), zaceVar.f10799c);
                    }
                }
                return true;
            case 19:
                this.f10585h = false;
                return true;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i2);
                return false;
        }
    }

    public final int k() {
        return this.f10591n.getAndIncrement();
    }

    final zabq s(ApiKey apiKey) {
        return (zabq) this.f10593p.get(apiKey);
    }

    public final Task v(GoogleApi googleApi, RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, Runnable runnable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        j(taskCompletionSource, registerListenerMethod.e(), googleApi);
        this.t.sendMessage(this.t.obtainMessage(8, new zach(new zaf(new zaci(registerListenerMethod, unregisterListenerMethod, runnable), taskCompletionSource), this.f10592o.get(), googleApi)));
        return taskCompletionSource.a();
    }

    public final Task w(GoogleApi googleApi, ListenerHolder.ListenerKey listenerKey, int i2) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        j(taskCompletionSource, i2, googleApi);
        this.t.sendMessage(this.t.obtainMessage(13, new zach(new zah(listenerKey, taskCompletionSource), this.f10592o.get(), googleApi)));
        return taskCompletionSource.a();
    }
}
