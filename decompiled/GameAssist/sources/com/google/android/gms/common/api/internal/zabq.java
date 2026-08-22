package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes.dex */
public final class zabq implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zau {

    /* renamed from: b, reason: collision with root package name */
    private final Api.Client f10764b;

    /* renamed from: c, reason: collision with root package name */
    private final ApiKey f10765c;

    /* renamed from: d, reason: collision with root package name */
    private final zaad f10766d;

    /* renamed from: g, reason: collision with root package name */
    private final int f10769g;

    /* renamed from: h, reason: collision with root package name */
    private final zact f10770h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f10771i;

    /* renamed from: m, reason: collision with root package name */
    final /* synthetic */ GoogleApiManager f10775m;

    /* renamed from: a, reason: collision with root package name */
    private final Queue f10763a = new LinkedList();

    /* renamed from: e, reason: collision with root package name */
    private final Set f10767e = new HashSet();

    /* renamed from: f, reason: collision with root package name */
    private final Map f10768f = new HashMap();

    /* renamed from: j, reason: collision with root package name */
    private final List f10772j = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private ConnectionResult f10773k = null;

    /* renamed from: l, reason: collision with root package name */
    private int f10774l = 0;

    public zabq(GoogleApiManager googleApiManager, GoogleApi googleApi) {
        this.f10775m = googleApiManager;
        Api.Client n2 = googleApi.n(googleApiManager.t.getLooper(), this);
        this.f10764b = n2;
        this.f10765c = googleApi.i();
        this.f10766d = new zaad();
        this.f10769g = googleApi.m();
        if (n2.g()) {
            this.f10770h = googleApi.o(googleApiManager.f10588k, googleApiManager.t);
        } else {
            this.f10770h = null;
        }
    }

    static /* bridge */ /* synthetic */ void A(zabq zabqVar, zabs zabsVar) {
        if (zabqVar.f10772j.contains(zabsVar) && !zabqVar.f10771i) {
            if (zabqVar.f10764b.isConnected()) {
                zabqVar.h();
            } else {
                zabqVar.D();
            }
        }
    }

    static /* bridge */ /* synthetic */ void B(zabq zabqVar, zabs zabsVar) {
        Feature feature;
        Feature[] g2;
        if (zabqVar.f10772j.remove(zabsVar)) {
            zabqVar.f10775m.t.removeMessages(15, zabsVar);
            zabqVar.f10775m.t.removeMessages(16, zabsVar);
            feature = zabsVar.f10777b;
            ArrayList arrayList = new ArrayList(zabqVar.f10763a.size());
            for (zai zaiVar : zabqVar.f10763a) {
                if ((zaiVar instanceof zac) && (g2 = ((zac) zaiVar).g(zabqVar)) != null && ArrayUtils.b(g2, feature)) {
                    arrayList.add(zaiVar);
                }
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                zai zaiVar2 = (zai) arrayList.get(i2);
                zabqVar.f10763a.remove(zaiVar2);
                zaiVar2.b(new UnsupportedApiCallException(feature));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Feature c(Feature[] featureArr) {
        if (featureArr != null && featureArr.length != 0) {
            Feature[] o2 = this.f10764b.o();
            if (o2 == null) {
                o2 = new Feature[0];
            }
            ArrayMap arrayMap = new ArrayMap(o2.length);
            for (Feature feature : o2) {
                arrayMap.put(feature.G(), Long.valueOf(feature.P()));
            }
            for (Feature feature2 : featureArr) {
                Long l2 = (Long) arrayMap.get(feature2.G());
                if (l2 == null || l2.longValue() < feature2.P()) {
                    return feature2;
                }
            }
        }
        return null;
    }

    private final void d(ConnectionResult connectionResult) {
        Iterator it = this.f10767e.iterator();
        while (it.hasNext()) {
            ((zal) it.next()).b(this.f10765c, connectionResult, Objects.a(connectionResult, ConnectionResult.f10484k) ? this.f10764b.c() : null);
        }
        this.f10767e.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e(Status status) {
        Preconditions.d(this.f10775m.t);
        f(status, null, false);
    }

    private final void f(Status status, Exception exc, boolean z) {
        Preconditions.d(this.f10775m.t);
        if ((status == null) == (exc == null)) {
            throw new IllegalArgumentException("Status XOR exception should be null");
        }
        Iterator it = this.f10763a.iterator();
        while (it.hasNext()) {
            zai zaiVar = (zai) it.next();
            if (!z || zaiVar.f10842a == 2) {
                if (status != null) {
                    zaiVar.a(status);
                } else {
                    zaiVar.b(exc);
                }
                it.remove();
            }
        }
    }

    private final void h() {
        ArrayList arrayList = new ArrayList(this.f10763a);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zai zaiVar = (zai) arrayList.get(i2);
            if (!this.f10764b.isConnected()) {
                return;
            }
            if (n(zaiVar)) {
                this.f10763a.remove(zaiVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i() {
        C();
        d(ConnectionResult.f10484k);
        m();
        Iterator it = this.f10768f.values().iterator();
        while (it.hasNext()) {
            zaci zaciVar = (zaci) it.next();
            if (c(zaciVar.f10807a.c()) != null) {
                it.remove();
            } else {
                try {
                    zaciVar.f10807a.d(this.f10764b, new TaskCompletionSource());
                } catch (DeadObjectException unused) {
                    onConnectionSuspended(3);
                    this.f10764b.disconnect("DeadObjectException thrown while calling register listener method.");
                } catch (RemoteException unused2) {
                    it.remove();
                }
            }
        }
        h();
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void j(int i2) {
        C();
        this.f10771i = true;
        this.f10766d.e(i2, this.f10764b.q());
        ApiKey apiKey = this.f10765c;
        GoogleApiManager googleApiManager = this.f10775m;
        googleApiManager.t.sendMessageDelayed(Message.obtain(googleApiManager.t, 9, apiKey), 5000L);
        ApiKey apiKey2 = this.f10765c;
        GoogleApiManager googleApiManager2 = this.f10775m;
        googleApiManager2.t.sendMessageDelayed(Message.obtain(googleApiManager2.t, 11, apiKey2), 120000L);
        this.f10775m.f10590m.c();
        Iterator it = this.f10768f.values().iterator();
        while (it.hasNext()) {
            ((zaci) it.next()).f10809c.run();
        }
    }

    private final void k() {
        this.f10775m.t.removeMessages(12, this.f10765c);
        ApiKey apiKey = this.f10765c;
        GoogleApiManager googleApiManager = this.f10775m;
        googleApiManager.t.sendMessageDelayed(googleApiManager.t.obtainMessage(12, apiKey), this.f10775m.f10584c);
    }

    private final void l(zai zaiVar) {
        zaiVar.d(this.f10766d, a());
        try {
            zaiVar.c(this);
        } catch (DeadObjectException unused) {
            onConnectionSuspended(1);
            this.f10764b.disconnect("DeadObjectException thrown while running ApiCallRunner.");
        }
    }

    private final void m() {
        if (this.f10771i) {
            GoogleApiManager googleApiManager = this.f10775m;
            googleApiManager.t.removeMessages(11, this.f10765c);
            GoogleApiManager googleApiManager2 = this.f10775m;
            googleApiManager2.t.removeMessages(9, this.f10765c);
            this.f10771i = false;
        }
    }

    private final boolean n(zai zaiVar) {
        if (!(zaiVar instanceof zac)) {
            l(zaiVar);
            return true;
        }
        zac zacVar = (zac) zaiVar;
        Feature c2 = c(zacVar.g(this));
        if (c2 == null) {
            l(zaiVar);
            return true;
        }
        Log.w("GoogleApiManager", this.f10764b.getClass().getName() + " could not execute call because it requires feature (" + c2.G() + ", " + c2.P() + ").");
        if (!this.f10775m.u || !zacVar.f(this)) {
            zacVar.b(new UnsupportedApiCallException(c2));
            return true;
        }
        zabs zabsVar = new zabs(this.f10765c, c2, null);
        int indexOf = this.f10772j.indexOf(zabsVar);
        if (indexOf >= 0) {
            zabs zabsVar2 = (zabs) this.f10772j.get(indexOf);
            this.f10775m.t.removeMessages(15, zabsVar2);
            GoogleApiManager googleApiManager = this.f10775m;
            googleApiManager.t.sendMessageDelayed(Message.obtain(googleApiManager.t, 15, zabsVar2), 5000L);
            return false;
        }
        this.f10772j.add(zabsVar);
        GoogleApiManager googleApiManager2 = this.f10775m;
        googleApiManager2.t.sendMessageDelayed(Message.obtain(googleApiManager2.t, 15, zabsVar), 5000L);
        GoogleApiManager googleApiManager3 = this.f10775m;
        googleApiManager3.t.sendMessageDelayed(Message.obtain(googleApiManager3.t, 16, zabsVar), 120000L);
        ConnectionResult connectionResult = new ConnectionResult(2, null);
        if (o(connectionResult)) {
            return false;
        }
        this.f10775m.e(connectionResult, this.f10769g);
        return false;
    }

    private final boolean o(ConnectionResult connectionResult) {
        synchronized (GoogleApiManager.x) {
            try {
                GoogleApiManager googleApiManager = this.f10775m;
                if (googleApiManager.f10594q == null || !googleApiManager.f10595r.contains(this.f10765c)) {
                    return false;
                }
                this.f10775m.f10594q.h(connectionResult, this.f10769g);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean p(boolean z) {
        Preconditions.d(this.f10775m.t);
        if (!this.f10764b.isConnected() || !this.f10768f.isEmpty()) {
            return false;
        }
        if (!this.f10766d.g()) {
            this.f10764b.disconnect("Timing out service connection.");
            return true;
        }
        if (!z) {
            return false;
        }
        k();
        return false;
    }

    public final void C() {
        Preconditions.d(this.f10775m.t);
        this.f10773k = null;
    }

    public final void D() {
        Preconditions.d(this.f10775m.t);
        if (this.f10764b.isConnected() || this.f10764b.b()) {
            return;
        }
        try {
            GoogleApiManager googleApiManager = this.f10775m;
            int b2 = googleApiManager.f10590m.b(googleApiManager.f10588k, this.f10764b);
            if (b2 == 0) {
                GoogleApiManager googleApiManager2 = this.f10775m;
                Api.Client client = this.f10764b;
                zabu zabuVar = new zabu(googleApiManager2, client, this.f10765c);
                if (client.g()) {
                    ((zact) Preconditions.i(this.f10770h)).zae(zabuVar);
                }
                try {
                    this.f10764b.d(zabuVar);
                    return;
                } catch (SecurityException e2) {
                    G(new ConnectionResult(10), e2);
                    return;
                }
            }
            ConnectionResult connectionResult = new ConnectionResult(b2, null);
            Log.w("GoogleApiManager", "The service for " + this.f10764b.getClass().getName() + " is not available: " + connectionResult.toString());
            G(connectionResult, null);
        } catch (IllegalStateException e3) {
            G(new ConnectionResult(10), e3);
        }
    }

    public final void E(zai zaiVar) {
        Preconditions.d(this.f10775m.t);
        if (this.f10764b.isConnected()) {
            if (n(zaiVar)) {
                k();
                return;
            } else {
                this.f10763a.add(zaiVar);
                return;
            }
        }
        this.f10763a.add(zaiVar);
        ConnectionResult connectionResult = this.f10773k;
        if (connectionResult == null || !connectionResult.T()) {
            D();
        } else {
            G(this.f10773k, null);
        }
    }

    final void F() {
        this.f10774l++;
    }

    public final void G(ConnectionResult connectionResult, Exception exc) {
        Preconditions.d(this.f10775m.t);
        zact zactVar = this.f10770h;
        if (zactVar != null) {
            zactVar.zaf();
        }
        C();
        this.f10775m.f10590m.c();
        d(connectionResult);
        if ((this.f10764b instanceof com.google.android.gms.common.internal.service.zap) && connectionResult.G() != 24) {
            this.f10775m.f10585h = true;
            GoogleApiManager googleApiManager = this.f10775m;
            googleApiManager.t.sendMessageDelayed(googleApiManager.t.obtainMessage(19), 300000L);
        }
        if (connectionResult.G() == 4) {
            e(GoogleApiManager.w);
            return;
        }
        if (this.f10763a.isEmpty()) {
            this.f10773k = connectionResult;
            return;
        }
        if (exc != null) {
            Preconditions.d(this.f10775m.t);
            f(null, exc, false);
            return;
        }
        if (!this.f10775m.u) {
            e(GoogleApiManager.f(this.f10765c, connectionResult));
            return;
        }
        f(GoogleApiManager.f(this.f10765c, connectionResult), null, true);
        if (this.f10763a.isEmpty() || o(connectionResult) || this.f10775m.e(connectionResult, this.f10769g)) {
            return;
        }
        if (connectionResult.G() == 18) {
            this.f10771i = true;
        }
        if (!this.f10771i) {
            e(GoogleApiManager.f(this.f10765c, connectionResult));
            return;
        }
        GoogleApiManager googleApiManager2 = this.f10775m;
        googleApiManager2.t.sendMessageDelayed(Message.obtain(googleApiManager2.t, 9, this.f10765c), 5000L);
    }

    public final void H(ConnectionResult connectionResult) {
        Preconditions.d(this.f10775m.t);
        Api.Client client = this.f10764b;
        client.disconnect("onSignInFailed for " + client.getClass().getName() + " with " + String.valueOf(connectionResult));
        G(connectionResult, null);
    }

    public final void I(zal zalVar) {
        Preconditions.d(this.f10775m.t);
        this.f10767e.add(zalVar);
    }

    public final void J() {
        Preconditions.d(this.f10775m.t);
        if (this.f10771i) {
            D();
        }
    }

    public final void K() {
        Preconditions.d(this.f10775m.t);
        e(GoogleApiManager.v);
        this.f10766d.f();
        for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.f10768f.keySet().toArray(new ListenerHolder.ListenerKey[0])) {
            E(new zah(listenerKey, new TaskCompletionSource()));
        }
        d(new ConnectionResult(4));
        if (this.f10764b.isConnected()) {
            this.f10764b.k(new zabp(this));
        }
    }

    public final void L() {
        Preconditions.d(this.f10775m.t);
        if (this.f10771i) {
            m();
            GoogleApiManager googleApiManager = this.f10775m;
            e(googleApiManager.f10589l.i(googleApiManager.f10588k) == 18 ? new Status(21, "Connection timed out waiting for Google Play services update to complete.") : new Status(22, "API failed to connect while resuming due to an unknown error."));
            this.f10764b.disconnect("Timing out connection while resuming.");
        }
    }

    final boolean O() {
        return this.f10764b.isConnected();
    }

    public final boolean a() {
        return this.f10764b.g();
    }

    public final boolean b() {
        return p(true);
    }

    @Override // com.google.android.gms.common.api.internal.zau
    public final void g(ConnectionResult connectionResult, Api api, boolean z) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        if (Looper.myLooper() == this.f10775m.t.getLooper()) {
            i();
        } else {
            this.f10775m.t.post(new zabm(this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        G(connectionResult, null);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
        if (Looper.myLooper() == this.f10775m.t.getLooper()) {
            j(i2);
        } else {
            this.f10775m.t.post(new zabn(this, i2));
        }
    }

    public final int q() {
        return this.f10769g;
    }

    final int r() {
        return this.f10774l;
    }

    public final ConnectionResult s() {
        Preconditions.d(this.f10775m.t);
        return this.f10773k;
    }

    public final Api.Client u() {
        return this.f10764b;
    }

    public final Map w() {
        return this.f10768f;
    }
}
