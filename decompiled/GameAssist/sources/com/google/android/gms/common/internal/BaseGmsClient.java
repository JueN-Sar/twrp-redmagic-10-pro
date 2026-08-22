package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class BaseGmsClient<T extends IInterface> {
    private final int A;
    private final String B;
    private volatile String C;

    /* renamed from: c, reason: collision with root package name */
    private int f10950c;

    /* renamed from: h, reason: collision with root package name */
    private long f10951h;

    /* renamed from: i, reason: collision with root package name */
    private long f10952i;

    /* renamed from: j, reason: collision with root package name */
    private int f10953j;

    /* renamed from: k, reason: collision with root package name */
    private long f10954k;

    /* renamed from: m, reason: collision with root package name */
    private final Context f10956m;

    /* renamed from: n, reason: collision with root package name */
    private final Looper f10957n;

    /* renamed from: o, reason: collision with root package name */
    private final GmsClientSupervisor f10958o;

    /* renamed from: p, reason: collision with root package name */
    private final GoogleApiAvailabilityLight f10959p;

    /* renamed from: q, reason: collision with root package name */
    final Handler f10960q;
    private IGmsServiceBroker t;
    private IInterface u;
    private zze w;
    private final BaseConnectionCallbacks y;
    private final BaseOnConnectionFailedListener z;

    @VisibleForTesting
    zzv zza;

    @NonNull
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzc;
    private static final Feature[] H = new Feature[0];
    public static final String[] G = {"service_esmobile", "service_googleme"};

    /* renamed from: l, reason: collision with root package name */
    private volatile String f10955l = null;

    /* renamed from: r, reason: collision with root package name */
    private final Object f10961r = new Object();

    /* renamed from: s, reason: collision with root package name */
    private final Object f10962s = new Object();
    private final ArrayList v = new ArrayList();
    private int x = 1;
    private ConnectionResult D = null;
    private boolean E = false;
    private volatile zzk F = null;

    @NonNull
    @VisibleForTesting
    protected AtomicInteger zzd = new AtomicInteger(0);

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i2);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        void a(ConnectionResult connectionResult);
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter() {
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void a(ConnectionResult connectionResult) {
            if (connectionResult.W()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.j(null, baseGmsClient.D());
            } else if (BaseGmsClient.this.z != null) {
                BaseGmsClient.this.z.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        void a();
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(@NonNull Context context, @NonNull Handler handler, @NonNull GmsClientSupervisor gmsClientSupervisor, @NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight, int i2, @Nullable BaseConnectionCallbacks baseConnectionCallbacks, @Nullable BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        Preconditions.j(context, "Context must not be null");
        this.f10956m = context;
        Preconditions.j(handler, "Handler must not be null");
        this.f10960q = handler;
        this.f10957n = handler.getLooper();
        Preconditions.j(gmsClientSupervisor, "Supervisor must not be null");
        this.f10958o = gmsClientSupervisor;
        Preconditions.j(googleApiAvailabilityLight, "API availability must not be null");
        this.f10959p = googleApiAvailabilityLight;
        this.A = i2;
        this.y = baseConnectionCallbacks;
        this.z = baseOnConnectionFailedListener;
        this.B = null;
    }

    static /* bridge */ /* synthetic */ void c0(BaseGmsClient baseGmsClient, zzk zzkVar) {
        baseGmsClient.F = zzkVar;
        if (baseGmsClient.S()) {
            ConnectionTelemetryConfiguration connectionTelemetryConfiguration = zzkVar.f11108j;
            RootTelemetryConfigManager.b().zza(connectionTelemetryConfiguration == null ? null : connectionTelemetryConfiguration.Y());
        }
    }

    static /* bridge */ /* synthetic */ void d0(BaseGmsClient baseGmsClient, int i2) {
        int i3;
        int i4;
        synchronized (baseGmsClient.f10961r) {
            i3 = baseGmsClient.x;
        }
        if (i3 == 3) {
            baseGmsClient.E = true;
            i4 = 5;
        } else {
            i4 = 4;
        }
        Handler handler = baseGmsClient.f10960q;
        handler.sendMessage(handler.obtainMessage(i4, baseGmsClient.zzd.get(), 16));
    }

    static /* bridge */ /* synthetic */ boolean g0(BaseGmsClient baseGmsClient, int i2, int i3, IInterface iInterface) {
        synchronized (baseGmsClient.f10961r) {
            try {
                if (baseGmsClient.x != i2) {
                    return false;
                }
                baseGmsClient.i0(i3, iInterface);
                return true;
            } finally {
            }
        }
    }

    static /* bridge */ /* synthetic */ boolean h0(BaseGmsClient baseGmsClient) {
        if (baseGmsClient.E || TextUtils.isEmpty(baseGmsClient.F()) || TextUtils.isEmpty(baseGmsClient.C())) {
            return false;
        }
        try {
            Class.forName(baseGmsClient.F());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i0(int i2, IInterface iInterface) {
        zzv zzvVar;
        Preconditions.a((i2 == 4) == (iInterface != null));
        synchronized (this.f10961r) {
            try {
                this.x = i2;
                this.u = iInterface;
                if (i2 == 1) {
                    zze zzeVar = this.w;
                    if (zzeVar != null) {
                        GmsClientSupervisor gmsClientSupervisor = this.f10958o;
                        String b2 = this.zza.b();
                        Preconditions.i(b2);
                        gmsClientSupervisor.e(b2, this.zza.a(), 4225, zzeVar, X(), this.zza.c());
                        this.w = null;
                    }
                } else if (i2 == 2 || i2 == 3) {
                    zze zzeVar2 = this.w;
                    if (zzeVar2 != null && (zzvVar = this.zza) != null) {
                        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + zzvVar.b() + " on " + zzvVar.a());
                        GmsClientSupervisor gmsClientSupervisor2 = this.f10958o;
                        String b3 = this.zza.b();
                        Preconditions.i(b3);
                        gmsClientSupervisor2.e(b3, this.zza.a(), 4225, zzeVar2, X(), this.zza.c());
                        this.zzd.incrementAndGet();
                    }
                    zze zzeVar3 = new zze(this, this.zzd.get());
                    this.w = zzeVar3;
                    zzv zzvVar2 = (this.x != 3 || C() == null) ? new zzv(H(), G(), false, 4225, J()) : new zzv(z().getPackageName(), C(), true, 4225, false);
                    this.zza = zzvVar2;
                    if (zzvVar2.c() && n() < 17895000) {
                        throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(this.zza.b())));
                    }
                    GmsClientSupervisor gmsClientSupervisor3 = this.f10958o;
                    String b4 = this.zza.b();
                    Preconditions.i(b4);
                    if (!gmsClientSupervisor3.f(new zzo(b4, this.zza.a(), 4225, this.zza.c()), zzeVar3, X(), x())) {
                        Log.w("GmsClient", "unable to connect to service: " + this.zza.b() + " on " + this.zza.a());
                        e0(16, null, this.zzd.get());
                    }
                } else if (i2 == 4) {
                    Preconditions.i(iInterface);
                    L(iInterface);
                }
            } finally {
            }
        }
    }

    public int A() {
        return this.A;
    }

    protected Bundle B() {
        return new Bundle();
    }

    protected String C() {
        return null;
    }

    protected Set D() {
        return Collections.emptySet();
    }

    public final IInterface E() {
        IInterface iInterface;
        synchronized (this.f10961r) {
            try {
                if (this.x == 5) {
                    throw new DeadObjectException();
                }
                s();
                iInterface = this.u;
                Preconditions.j(iInterface, "Client is connected but service is null");
            } catch (Throwable th) {
                throw th;
            }
        }
        return iInterface;
    }

    protected abstract String F();

    protected abstract String G();

    protected String H() {
        return "com.google.android.gms";
    }

    public ConnectionTelemetryConfiguration I() {
        zzk zzkVar = this.F;
        if (zzkVar == null) {
            return null;
        }
        return zzkVar.f11108j;
    }

    protected boolean J() {
        return n() >= 211700000;
    }

    public boolean K() {
        return this.F != null;
    }

    protected void L(IInterface iInterface) {
        this.f10952i = System.currentTimeMillis();
    }

    protected void M(ConnectionResult connectionResult) {
        this.f10953j = connectionResult.G();
        this.f10954k = System.currentTimeMillis();
    }

    protected void N(int i2) {
        this.f10950c = i2;
        this.f10951h = System.currentTimeMillis();
    }

    protected void O(int i2, IBinder iBinder, Bundle bundle, int i3) {
        this.f10960q.sendMessage(this.f10960q.obtainMessage(1, i3, -1, new zzf(this, i2, iBinder, bundle)));
    }

    public boolean P() {
        return false;
    }

    public void Q(String str) {
        this.C = str;
    }

    public void R(int i2) {
        this.f10960q.sendMessage(this.f10960q.obtainMessage(6, this.zzd.get(), i2));
    }

    public boolean S() {
        return false;
    }

    protected final String X() {
        String str = this.B;
        return str == null ? this.f10956m.getClass().getName() : str;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        boolean z;
        synchronized (this.f10961r) {
            int i2 = this.x;
            z = true;
            if (i2 != 2 && i2 != 3) {
                z = false;
            }
        }
        return z;
    }

    public String c() {
        zzv zzvVar;
        if (!isConnected() || (zzvVar = this.zza) == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
        return zzvVar.a();
    }

    public void d(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        Preconditions.j(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zzc = connectionProgressReportCallbacks;
        i0(2, null);
    }

    public void disconnect() {
        this.zzd.incrementAndGet();
        synchronized (this.v) {
            try {
                int size = this.v.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((zzc) this.v.get(i2)).d();
                }
                this.v.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        synchronized (this.f10962s) {
            this.t = null;
        }
        i0(1, null);
    }

    public boolean e() {
        return true;
    }

    protected final void e0(int i2, Bundle bundle, int i3) {
        this.f10960q.sendMessage(this.f10960q.obtainMessage(7, i3, -1, new zzg(this, i2, null)));
    }

    public boolean g() {
        return false;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.f10961r) {
            z = this.x == 4;
        }
        return z;
    }

    public void j(IAccountAccessor iAccountAccessor, Set set) {
        Bundle B = B();
        String str = this.C;
        int i2 = GoogleApiAvailabilityLight.f10502a;
        Scope[] scopeArr = GetServiceRequest.u;
        Bundle bundle = new Bundle();
        int i3 = this.A;
        Feature[] featureArr = GetServiceRequest.v;
        GetServiceRequest getServiceRequest = new GetServiceRequest(6, i3, i2, null, null, scopeArr, bundle, null, featureArr, featureArr, true, 0, false, str);
        getServiceRequest.f10992j = this.f10956m.getPackageName();
        getServiceRequest.f10995m = B;
        if (set != null) {
            getServiceRequest.f10994l = (Scope[]) set.toArray(new Scope[0]);
        }
        if (g()) {
            Account v = v();
            if (v == null) {
                v = new Account("<<default account>>", "com.google");
            }
            getServiceRequest.f10996n = v;
            if (iAccountAccessor != null) {
                getServiceRequest.f10993k = iAccountAccessor.asBinder();
            }
        } else if (P()) {
            getServiceRequest.f10996n = v();
        }
        getServiceRequest.f10997o = H;
        getServiceRequest.f10998p = w();
        if (S()) {
            getServiceRequest.f11001s = true;
        }
        try {
            try {
                synchronized (this.f10962s) {
                    try {
                        IGmsServiceBroker iGmsServiceBroker = this.t;
                        if (iGmsServiceBroker != null) {
                            iGmsServiceBroker.getService(new zzd(this, this.zzd.get()), getServiceRequest);
                        } else {
                            Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                        }
                    } finally {
                    }
                }
            } catch (RemoteException | RuntimeException e2) {
                Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
                O(8, null, null, this.zzd.get());
            }
        } catch (DeadObjectException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            R(3);
        } catch (SecurityException e4) {
            throw e4;
        }
    }

    public void k(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.a();
    }

    public void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i2;
        IInterface iInterface;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.f10961r) {
            i2 = this.x;
            iInterface = this.u;
        }
        synchronized (this.f10962s) {
            iGmsServiceBroker = this.t;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        if (i2 == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i2 == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i2 == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i2 == 4) {
            printWriter.print("CONNECTED");
        } else if (i2 != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) F()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.f10952i > 0) {
            PrintWriter append = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j2 = this.f10952i;
            append.println(j2 + " " + simpleDateFormat.format(new Date(j2)));
        }
        if (this.f10951h > 0) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            int i3 = this.f10950c;
            if (i3 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i3 == 2) {
                printWriter.append("CAUSE_NETWORK_LOST");
            } else if (i3 != 3) {
                printWriter.append((CharSequence) String.valueOf(i3));
            } else {
                printWriter.append("CAUSE_DEAD_OBJECT_EXCEPTION");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j3 = this.f10951h;
            append2.println(j3 + " " + simpleDateFormat.format(new Date(j3)));
        }
        if (this.f10954k > 0) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) CommonStatusCodes.a(this.f10953j));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j4 = this.f10954k;
            append3.println(j4 + " " + simpleDateFormat.format(new Date(j4)));
        }
    }

    public int n() {
        return GoogleApiAvailabilityLight.f10502a;
    }

    public final Feature[] o() {
        zzk zzkVar = this.F;
        if (zzkVar == null) {
            return null;
        }
        return zzkVar.f11106h;
    }

    public String q() {
        return this.f10955l;
    }

    public Intent r() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    protected final void s() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    protected abstract IInterface t(IBinder iBinder);

    @KeepForSdk
    @VisibleForTesting
    protected void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i2, @Nullable PendingIntent pendingIntent) {
        Preconditions.j(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zzc = connectionProgressReportCallbacks;
        this.f10960q.sendMessage(this.f10960q.obtainMessage(3, this.zzd.get(), i2, pendingIntent));
    }

    protected boolean u() {
        return false;
    }

    public Account v() {
        return null;
    }

    public Feature[] w() {
        return H;
    }

    protected Executor x() {
        return null;
    }

    public Bundle y() {
        return null;
    }

    public final Context z() {
        return this.f10956m;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(@NonNull Context context, @NonNull Looper looper, @NonNull GmsClientSupervisor gmsClientSupervisor, @NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight, int i2, @Nullable BaseConnectionCallbacks baseConnectionCallbacks, @Nullable BaseOnConnectionFailedListener baseOnConnectionFailedListener, @Nullable String str) {
        Preconditions.j(context, "Context must not be null");
        this.f10956m = context;
        Preconditions.j(looper, "Looper must not be null");
        this.f10957n = looper;
        Preconditions.j(gmsClientSupervisor, "Supervisor must not be null");
        this.f10958o = gmsClientSupervisor;
        Preconditions.j(googleApiAvailabilityLight, "API availability must not be null");
        this.f10959p = googleApiAvailabilityLight;
        this.f10960q = new zzb(this, looper);
        this.A = i2;
        this.y = baseConnectionCallbacks;
        this.z = baseOnConnectionFailedListener;
        this.B = str;
    }

    public void disconnect(String str) {
        this.f10955l = str;
        disconnect();
    }
}
