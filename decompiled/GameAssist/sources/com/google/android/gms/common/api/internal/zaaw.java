package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zaaw implements zabf {

    /* renamed from: a, reason: collision with root package name */
    private final zabi f10688a;

    /* renamed from: b, reason: collision with root package name */
    private final Lock f10689b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f10690c;

    /* renamed from: d, reason: collision with root package name */
    private final GoogleApiAvailabilityLight f10691d;

    /* renamed from: e, reason: collision with root package name */
    private ConnectionResult f10692e;

    /* renamed from: f, reason: collision with root package name */
    private int f10693f;

    /* renamed from: h, reason: collision with root package name */
    private int f10695h;

    /* renamed from: k, reason: collision with root package name */
    private com.google.android.gms.signin.zae f10698k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f10699l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f10700m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f10701n;

    /* renamed from: o, reason: collision with root package name */
    private IAccountAccessor f10702o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f10703p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f10704q;

    /* renamed from: r, reason: collision with root package name */
    private final ClientSettings f10705r;

    /* renamed from: s, reason: collision with root package name */
    private final Map f10706s;
    private final Api.AbstractClientBuilder t;

    /* renamed from: g, reason: collision with root package name */
    private int f10694g = 0;

    /* renamed from: i, reason: collision with root package name */
    private final Bundle f10696i = new Bundle();

    /* renamed from: j, reason: collision with root package name */
    private final Set f10697j = new HashSet();
    private final ArrayList u = new ArrayList();

    public zaaw(zabi zabiVar, ClientSettings clientSettings, Map map, GoogleApiAvailabilityLight googleApiAvailabilityLight, Api.AbstractClientBuilder abstractClientBuilder, Lock lock, Context context) {
        this.f10688a = zabiVar;
        this.f10705r = clientSettings;
        this.f10706s = map;
        this.f10691d = googleApiAvailabilityLight;
        this.t = abstractClientBuilder;
        this.f10689b = lock;
        this.f10690c = context;
    }

    static /* bridge */ /* synthetic */ void A(zaaw zaawVar, com.google.android.gms.signin.internal.zak zakVar) {
        if (zaawVar.n(0)) {
            ConnectionResult G = zakVar.G();
            if (!G.W()) {
                if (!zaawVar.p(G)) {
                    zaawVar.k(G);
                    return;
                } else {
                    zaawVar.h();
                    zaawVar.m();
                    return;
                }
            }
            com.google.android.gms.common.internal.zav zavVar = (com.google.android.gms.common.internal.zav) Preconditions.i(zakVar.P());
            ConnectionResult G2 = zavVar.G();
            if (!G2.W()) {
                String valueOf = String.valueOf(G2);
                Log.wtf("GACConnecting", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
                zaawVar.k(G2);
                return;
            }
            zaawVar.f10701n = true;
            zaawVar.f10702o = (IAccountAccessor) Preconditions.i(zavVar.P());
            zaawVar.f10703p = zavVar.R();
            zaawVar.f10704q = zavVar.T();
            zaawVar.m();
        }
    }

    private final void I() {
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Future) arrayList.get(i2)).cancel(true);
        }
        this.u.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h() {
        this.f10700m = false;
        this.f10688a.f10753n.f10732n = Collections.emptySet();
        for (Api.AnyClientKey anyClientKey : this.f10697j) {
            if (!this.f10688a.f10746g.containsKey(anyClientKey)) {
                zabi zabiVar = this.f10688a;
                zabiVar.f10746g.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    private final void i(boolean z) {
        com.google.android.gms.signin.zae zaeVar = this.f10698k;
        if (zaeVar != null) {
            if (zaeVar.isConnected() && z) {
                zaeVar.f();
            }
            zaeVar.disconnect();
            this.f10702o = null;
        }
    }

    private final void j() {
        this.f10688a.j();
        zabj.a().execute(new zaak(this));
        com.google.android.gms.signin.zae zaeVar = this.f10698k;
        if (zaeVar != null) {
            if (this.f10703p) {
                zaeVar.p((IAccountAccessor) Preconditions.i(this.f10702o), this.f10704q);
            }
            i(false);
        }
        Iterator it = this.f10688a.f10746g.keySet().iterator();
        while (it.hasNext()) {
            ((Api.Client) Preconditions.i((Api.Client) this.f10688a.f10745f.get((Api.AnyClientKey) it.next()))).disconnect();
        }
        this.f10688a.f10754o.a(this.f10696i.isEmpty() ? null : this.f10696i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void k(ConnectionResult connectionResult) {
        I();
        i(!connectionResult.T());
        this.f10688a.l(connectionResult);
        this.f10688a.f10754o.c(connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l(ConnectionResult connectionResult, Api api, boolean z) {
        int priority = api.c().getPriority();
        if ((!z || connectionResult.T() || this.f10691d.c(connectionResult.G()) != null) && (this.f10692e == null || priority < this.f10693f)) {
            this.f10692e = connectionResult;
            this.f10693f = priority;
        }
        zabi zabiVar = this.f10688a;
        zabiVar.f10746g.put(api.b(), connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m() {
        if (this.f10695h != 0) {
            return;
        }
        if (!this.f10700m || this.f10701n) {
            ArrayList arrayList = new ArrayList();
            this.f10694g = 1;
            this.f10695h = this.f10688a.f10745f.size();
            for (Api.AnyClientKey anyClientKey : this.f10688a.f10745f.keySet()) {
                if (!this.f10688a.f10746g.containsKey(anyClientKey)) {
                    arrayList.add((Api.Client) this.f10688a.f10745f.get(anyClientKey));
                } else if (o()) {
                    j();
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.u.add(zabj.a().submit(new zaap(this, arrayList)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean n(int i2) {
        if (this.f10694g == i2) {
            return true;
        }
        Log.w("GACConnecting", this.f10688a.f10753n.o());
        Log.w("GACConnecting", "Unexpected callback in ".concat(toString()));
        Log.w("GACConnecting", "mRemainingConnections=" + this.f10695h);
        Log.e("GACConnecting", "GoogleApiClient connecting is in step " + q(this.f10694g) + " but received callback for step " + q(i2), new Exception());
        k(new ConnectionResult(8, null));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean o() {
        int i2 = this.f10695h - 1;
        this.f10695h = i2;
        if (i2 > 0) {
            return false;
        }
        if (i2 < 0) {
            Log.w("GACConnecting", this.f10688a.f10753n.o());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            k(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult = this.f10692e;
        if (connectionResult == null) {
            return true;
        }
        this.f10688a.f10752m = this.f10693f;
        k(connectionResult);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean p(ConnectionResult connectionResult) {
        return this.f10699l && !connectionResult.T();
    }

    private static final String q(int i2) {
        return i2 != 0 ? "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    static /* bridge */ /* synthetic */ Set x(zaaw zaawVar) {
        ClientSettings clientSettings = zaawVar.f10705r;
        if (clientSettings == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(clientSettings.e());
        Map i2 = zaawVar.f10705r.i();
        for (Api api : i2.keySet()) {
            zabi zabiVar = zaawVar.f10688a;
            if (!zabiVar.f10746g.containsKey(api.b())) {
                hashSet.addAll(((com.google.android.gms.common.internal.zab) i2.get(api)).f11048a);
            }
        }
        return hashSet;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void a(Bundle bundle) {
        if (n(1)) {
            if (bundle != null) {
                this.f10696i.putAll(bundle);
            }
            if (o()) {
                j();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    @Override // com.google.android.gms.common.api.internal.zabf
    public final void b() {
        this.f10688a.f10746g.clear();
        this.f10700m = false;
        zaas zaasVar = null;
        this.f10692e = null;
        this.f10694g = 0;
        this.f10699l = true;
        this.f10701n = false;
        this.f10703p = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api api : this.f10706s.keySet()) {
            Api.Client client = (Api.Client) Preconditions.i((Api.Client) this.f10688a.f10745f.get(api.b()));
            z |= api.c().getPriority() == 1;
            boolean booleanValue = ((Boolean) this.f10706s.get(api)).booleanValue();
            if (client.g()) {
                this.f10700m = true;
                if (booleanValue) {
                    this.f10697j.add(api.b());
                } else {
                    this.f10699l = false;
                }
            }
            hashMap.put(client, new zaal(this, api, booleanValue));
        }
        if (z) {
            this.f10700m = false;
        }
        if (this.f10700m) {
            Preconditions.i(this.f10705r);
            Preconditions.i(this.t);
            this.f10705r.j(Integer.valueOf(System.identityHashCode(this.f10688a.f10753n)));
            zaat zaatVar = new zaat(this, zaasVar);
            Api.AbstractClientBuilder abstractClientBuilder = this.t;
            Context context = this.f10690c;
            zabi zabiVar = this.f10688a;
            ClientSettings clientSettings = this.f10705r;
            this.f10698k = abstractClientBuilder.a(context, zabiVar.f10753n.h(), clientSettings, clientSettings.f(), zaatVar, zaatVar);
        }
        this.f10695h = this.f10688a.f10745f.size();
        this.u.add(zabj.a().submit(new zaao(this, hashMap)));
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void c() {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void d(ConnectionResult connectionResult, Api api, boolean z) {
        if (n(1)) {
            l(connectionResult, api, z);
            if (o()) {
                j();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void e(int i2) {
        k(new ConnectionResult(8, null));
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final boolean f() {
        I();
        i(true);
        this.f10688a.l(null);
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl g(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
