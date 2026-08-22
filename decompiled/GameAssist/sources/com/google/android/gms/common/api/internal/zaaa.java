package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zaaa implements zaca {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10644a;

    /* renamed from: b, reason: collision with root package name */
    private final zabe f10645b;

    /* renamed from: c, reason: collision with root package name */
    private final Looper f10646c;

    /* renamed from: d, reason: collision with root package name */
    private final zabi f10647d;

    /* renamed from: e, reason: collision with root package name */
    private final zabi f10648e;

    /* renamed from: f, reason: collision with root package name */
    private final Map f10649f;

    /* renamed from: h, reason: collision with root package name */
    private final Api.Client f10651h;

    /* renamed from: i, reason: collision with root package name */
    private Bundle f10652i;

    /* renamed from: m, reason: collision with root package name */
    private final Lock f10656m;

    /* renamed from: g, reason: collision with root package name */
    private final Set f10650g = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: j, reason: collision with root package name */
    private ConnectionResult f10653j = null;

    /* renamed from: k, reason: collision with root package name */
    private ConnectionResult f10654k = null;

    /* renamed from: l, reason: collision with root package name */
    private boolean f10655l = false;

    /* renamed from: n, reason: collision with root package name */
    private int f10657n = 0;

    private zaaa(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, Map map2, ClientSettings clientSettings, Api.AbstractClientBuilder abstractClientBuilder, Api.Client client, ArrayList arrayList, ArrayList arrayList2, Map map3, Map map4) {
        this.f10644a = context;
        this.f10645b = zabeVar;
        this.f10656m = lock;
        this.f10646c = looper;
        this.f10651h = client;
        this.f10647d = new zabi(context, zabeVar, lock, looper, googleApiAvailabilityLight, map2, null, map4, null, arrayList2, new zax(this, null));
        this.f10648e = new zabi(context, zabeVar, lock, looper, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, arrayList, new zaz(this, null));
        ArrayMap arrayMap = new ArrayMap();
        Iterator it = map2.keySet().iterator();
        while (it.hasNext()) {
            arrayMap.put((Api.AnyClientKey) it.next(), this.f10647d);
        }
        Iterator it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            arrayMap.put((Api.AnyClientKey) it2.next(), this.f10648e);
        }
        this.f10649f = Collections.unmodifiableMap(arrayMap);
    }

    private final void g(ConnectionResult connectionResult) {
        int i2 = this.f10657n;
        if (i2 != 1) {
            if (i2 != 2) {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                this.f10657n = 0;
            }
            this.f10645b.c(connectionResult);
        }
        h();
        this.f10657n = 0;
    }

    private final void h() {
        Iterator it = this.f10650g.iterator();
        while (it.hasNext()) {
            ((SignInConnectionListener) it.next()).a();
        }
        this.f10650g.clear();
    }

    private final boolean i() {
        ConnectionResult connectionResult = this.f10654k;
        return connectionResult != null && connectionResult.G() == 4;
    }

    private final boolean j(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        zabi zabiVar = (zabi) this.f10649f.get(apiMethodImpl.t());
        Preconditions.j(zabiVar, "GoogleApiClient is not configured to use the API required for this call.");
        return zabiVar.equals(this.f10648e);
    }

    private static boolean k(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.W();
    }

    public static zaaa m(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, ClientSettings clientSettings, Map map2, Api.AbstractClientBuilder abstractClientBuilder, ArrayList arrayList) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        Api.Client client = null;
        for (Map.Entry entry : map.entrySet()) {
            Api.Client client2 = (Api.Client) entry.getValue();
            if (true == client2.a()) {
                client = client2;
            }
            if (client2.g()) {
                arrayMap.put((Api.AnyClientKey) entry.getKey(), client2);
            } else {
                arrayMap2.put((Api.AnyClientKey) entry.getKey(), client2);
            }
        }
        Preconditions.m(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            Api.AnyClientKey b2 = api.b();
            if (arrayMap.containsKey(b2)) {
                arrayMap3.put(api, (Boolean) map2.get(api));
            } else {
                if (!arrayMap2.containsKey(b2)) {
                    throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                }
                arrayMap4.put(api, (Boolean) map2.get(api));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zat zatVar = (zat) arrayList.get(i2);
            if (arrayMap3.containsKey(zatVar.f10864a)) {
                arrayList2.add(zatVar);
            } else {
                if (!arrayMap4.containsKey(zatVar.f10864a)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                }
                arrayList3.add(zatVar);
            }
        }
        return new zaaa(context, zabeVar, lock, looper, googleApiAvailabilityLight, arrayMap, arrayMap2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    static /* bridge */ /* synthetic */ void t(zaaa zaaaVar, int i2, boolean z) {
        zaaaVar.f10645b.b(i2, z);
        zaaaVar.f10654k = null;
        zaaaVar.f10653j = null;
    }

    static /* bridge */ /* synthetic */ void u(zaaa zaaaVar, Bundle bundle) {
        Bundle bundle2 = zaaaVar.f10652i;
        if (bundle2 == null) {
            zaaaVar.f10652i = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }

    static /* bridge */ /* synthetic */ void v(zaaa zaaaVar) {
        ConnectionResult connectionResult;
        if (!k(zaaaVar.f10653j)) {
            if (zaaaVar.f10653j != null && k(zaaaVar.f10654k)) {
                zaaaVar.f10648e.c();
                zaaaVar.g((ConnectionResult) Preconditions.i(zaaaVar.f10653j));
                return;
            }
            ConnectionResult connectionResult2 = zaaaVar.f10653j;
            if (connectionResult2 == null || (connectionResult = zaaaVar.f10654k) == null) {
                return;
            }
            if (zaaaVar.f10648e.f10752m < zaaaVar.f10647d.f10752m) {
                connectionResult2 = connectionResult;
            }
            zaaaVar.g(connectionResult2);
            return;
        }
        if (!k(zaaaVar.f10654k) && !zaaaVar.i()) {
            ConnectionResult connectionResult3 = zaaaVar.f10654k;
            if (connectionResult3 != null) {
                if (zaaaVar.f10657n == 1) {
                    zaaaVar.h();
                    return;
                } else {
                    zaaaVar.g(connectionResult3);
                    zaaaVar.f10647d.c();
                    return;
                }
            }
            return;
        }
        int i2 = zaaaVar.f10657n;
        if (i2 != 1) {
            if (i2 != 2) {
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                zaaaVar.f10657n = 0;
            }
            ((zabe) Preconditions.i(zaaaVar.f10645b)).a(zaaaVar.f10652i);
        }
        zaaaVar.h();
        zaaaVar.f10657n = 0;
    }

    private final PendingIntent x() {
        Api.Client client = this.f10651h;
        if (client == null) {
            return null;
        }
        return PendingIntent.getActivity(this.f10644a, System.identityHashCode(this.f10645b), client.r(), com.google.android.gms.internal.base.zap.f11385a | 134217728);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void a() {
        this.f10657n = 2;
        this.f10655l = false;
        this.f10654k = null;
        this.f10653j = null;
        this.f10647d.a();
        this.f10648e.a();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void b() {
        this.f10647d.b();
        this.f10648e.b();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void c() {
        this.f10654k = null;
        this.f10653j = null;
        this.f10657n = 0;
        this.f10647d.c();
        this.f10648e.c();
        h();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.f10648e.d(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.f10647d.d(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
    
        if (r3.f10657n == 1) goto L11;
     */
    @Override // com.google.android.gms.common.api.internal.zaca
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean e() {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.f10656m
            r0.lock()
            com.google.android.gms.common.api.internal.zabi r0 = r3.f10647d     // Catch: java.lang.Throwable -> L23
            boolean r0 = r0.e()     // Catch: java.lang.Throwable -> L23
            r1 = 0
            if (r0 == 0) goto L25
            com.google.android.gms.common.api.internal.zabi r0 = r3.f10648e     // Catch: java.lang.Throwable -> L23
            boolean r0 = r0.e()     // Catch: java.lang.Throwable -> L23
            r2 = 1
            if (r0 != 0) goto L21
            boolean r0 = r3.i()     // Catch: java.lang.Throwable -> L23
            if (r0 != 0) goto L21
            int r0 = r3.f10657n     // Catch: java.lang.Throwable -> L23
            if (r0 != r2) goto L25
        L21:
            r1 = r2
            goto L25
        L23:
            r0 = move-exception
            goto L2b
        L25:
            java.util.concurrent.locks.Lock r3 = r3.f10656m
            r3.unlock()
            return r1
        L2b:
            java.util.concurrent.locks.Lock r3 = r3.f10656m
            r3.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaaa.e():boolean");
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final BaseImplementation.ApiMethodImpl f(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        if (!j(apiMethodImpl)) {
            return this.f10647d.f(apiMethodImpl);
        }
        if (!i()) {
            return this.f10648e.f(apiMethodImpl);
        }
        apiMethodImpl.x(new Status(4, (String) null, x()));
        return apiMethodImpl;
    }
}
