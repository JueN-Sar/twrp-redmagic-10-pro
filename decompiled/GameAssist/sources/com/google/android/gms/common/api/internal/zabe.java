package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.ClientLibraryUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zabe extends GoogleApiClient implements zabz {

    /* renamed from: b, reason: collision with root package name */
    private final Lock f10720b;

    /* renamed from: c, reason: collision with root package name */
    private final com.google.android.gms.common.internal.zak f10721c;

    /* renamed from: d, reason: collision with root package name */
    private zaca f10722d;

    /* renamed from: e, reason: collision with root package name */
    private final int f10723e;

    /* renamed from: f, reason: collision with root package name */
    private final Context f10724f;

    /* renamed from: g, reason: collision with root package name */
    private final Looper f10725g;

    /* renamed from: h, reason: collision with root package name */
    private volatile boolean f10726h;

    /* renamed from: i, reason: collision with root package name */
    private long f10727i;

    /* renamed from: j, reason: collision with root package name */
    private long f10728j;

    /* renamed from: k, reason: collision with root package name */
    private final zabc f10729k;

    /* renamed from: l, reason: collision with root package name */
    private final GoogleApiAvailability f10730l;

    /* renamed from: m, reason: collision with root package name */
    final Map f10731m;

    /* renamed from: n, reason: collision with root package name */
    Set f10732n;

    /* renamed from: o, reason: collision with root package name */
    final ClientSettings f10733o;

    /* renamed from: p, reason: collision with root package name */
    final Map f10734p;

    /* renamed from: q, reason: collision with root package name */
    final Api.AbstractClientBuilder f10735q;

    /* renamed from: r, reason: collision with root package name */
    private final ListenerHolders f10736r;

    /* renamed from: s, reason: collision with root package name */
    private final ArrayList f10737s;
    private Integer t;
    Set u;
    final zadc v;

    @VisibleForTesting
    final Queue zaa;

    @Nullable
    @VisibleForTesting
    zabx zab;

    public static int m(Iterable iterable, boolean z) {
        Iterator it = iterable.iterator();
        boolean z2 = false;
        boolean z3 = false;
        while (it.hasNext()) {
            Api.Client client = (Api.Client) it.next();
            z2 |= client.g();
            z3 |= client.a();
        }
        if (z2) {
            return (z3 && z) ? 2 : 1;
        }
        return 3;
    }

    static String p(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    static /* bridge */ /* synthetic */ void r(zabe zabeVar) {
        zabeVar.f10720b.lock();
        try {
            if (zabeVar.f10726h) {
                zabeVar.w();
            }
        } finally {
            zabeVar.f10720b.unlock();
        }
    }

    static /* bridge */ /* synthetic */ void s(zabe zabeVar) {
        zabeVar.f10720b.lock();
        try {
            if (zabeVar.t()) {
                zabeVar.w();
            }
        } finally {
            zabeVar.f10720b.unlock();
        }
    }

    private final void u(int i2) {
        Integer num = this.t;
        if (num == null) {
            this.t = Integer.valueOf(i2);
        } else if (num.intValue() != i2) {
            throw new IllegalStateException("Cannot use sign-in mode: " + p(i2) + ". Mode was already set to " + p(this.t.intValue()));
        }
        if (this.f10722d != null) {
            return;
        }
        boolean z = false;
        boolean z2 = false;
        for (Api.Client client : this.f10731m.values()) {
            z |= client.g();
            z2 |= client.a();
        }
        int intValue = this.t.intValue();
        if (intValue == 1) {
            if (!z) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            }
            if (z2) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
        } else if (intValue == 2 && z) {
            this.f10722d = zaaa.m(this.f10724f, this, this.f10720b, this.f10725g, this.f10730l, this.f10731m, this.f10733o, this.f10734p, this.f10735q, this.f10737s);
            return;
        }
        this.f10722d = new zabi(this.f10724f, this, this.f10720b, this.f10725g, this.f10730l, this.f10731m, this.f10733o, this.f10734p, this.f10735q, this.f10737s, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void v(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        Common.f11042d.a(googleApiClient).e(new zabb(this, statusPendingResult, z, googleApiClient));
    }

    private final void w() {
        this.f10721c.b();
        ((zaca) Preconditions.i(this.f10722d)).a();
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void a(Bundle bundle) {
        while (!this.zaa.isEmpty()) {
            g((BaseImplementation.ApiMethodImpl) this.zaa.remove());
        }
        this.f10721c.zad(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void b(int i2, boolean z) {
        if (i2 == 1) {
            if (!z && !this.f10726h) {
                this.f10726h = true;
                if (this.zab == null && !ClientLibraryUtils.a()) {
                    try {
                        this.zab = this.f10730l.v(this.f10724f.getApplicationContext(), new zabd(this));
                    } catch (SecurityException unused) {
                    }
                }
                zabc zabcVar = this.f10729k;
                zabcVar.sendMessageDelayed(zabcVar.obtainMessage(1), this.f10727i);
                zabc zabcVar2 = this.f10729k;
                zabcVar2.sendMessageDelayed(zabcVar2.obtainMessage(2), this.f10728j);
            }
            i2 = 1;
        }
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.v.zab.toArray(new BasePendingResult[0])) {
            basePendingResult.g(zadc.f10834b);
        }
        this.f10721c.zae(i2);
        this.f10721c.a();
        if (i2 == 2) {
            w();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void c(ConnectionResult connectionResult) {
        if (!this.f10730l.k(this.f10724f, connectionResult.G())) {
            t();
        }
        if (this.f10726h) {
            return;
        }
        this.f10721c.zac(connectionResult);
        this.f10721c.a();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void d() {
        this.f10720b.lock();
        try {
            int i2 = 2;
            boolean z = false;
            if (this.f10723e >= 0) {
                Preconditions.m(this.t != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.t;
                if (num == null) {
                    this.t = Integer.valueOf(m(this.f10731m.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            int intValue = ((Integer) Preconditions.i(this.t)).intValue();
            this.f10720b.lock();
            try {
                if (intValue == 3 || intValue == 1) {
                    i2 = intValue;
                } else if (intValue != 2) {
                    i2 = intValue;
                    Preconditions.b(z, "Illegal sign-in mode: " + i2);
                    u(i2);
                    w();
                    this.f10720b.unlock();
                    return;
                }
                Preconditions.b(z, "Illegal sign-in mode: " + i2);
                u(i2);
                w();
                this.f10720b.unlock();
                return;
            } finally {
                this.f10720b.unlock();
            }
            z = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void e() {
        this.f10720b.lock();
        try {
            this.v.b();
            zaca zacaVar = this.f10722d;
            if (zacaVar != null) {
                zacaVar.c();
            }
            this.f10736r.d();
            for (BaseImplementation.ApiMethodImpl apiMethodImpl : this.zaa) {
                apiMethodImpl.q(null);
                apiMethodImpl.d();
            }
            this.zaa.clear();
            if (this.f10722d != null) {
                t();
                this.f10721c.a();
            }
            this.f10720b.unlock();
        } catch (Throwable th) {
            this.f10720b.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.f10724f);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.f10726h);
        printWriter.append(" mWorkQueue.size()=").print(this.zaa.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.v.zab.size());
        zaca zacaVar = this.f10722d;
        if (zacaVar != null) {
            zacaVar.d(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final BaseImplementation.ApiMethodImpl g(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        Map map = this.f10731m;
        Api s2 = apiMethodImpl.s();
        Preconditions.b(map.containsKey(apiMethodImpl.t()), "GoogleApiClient is not configured to use " + (s2 != null ? s2.d() : "the API") + " required for this call.");
        this.f10720b.lock();
        try {
            zaca zacaVar = this.f10722d;
            if (zacaVar == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (this.f10726h) {
                this.zaa.add(apiMethodImpl);
                while (!this.zaa.isEmpty()) {
                    BaseImplementation.ApiMethodImpl apiMethodImpl2 = (BaseImplementation.ApiMethodImpl) this.zaa.remove();
                    this.v.a(apiMethodImpl2);
                    apiMethodImpl2.x(Status.f10545n);
                }
            } else {
                apiMethodImpl = zacaVar.f(apiMethodImpl);
            }
            this.f10720b.unlock();
            return apiMethodImpl;
        } catch (Throwable th) {
            this.f10720b.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper h() {
        return this.f10725g;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void i(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.f10721c.c(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void j(zada zadaVar) {
        this.f10720b.lock();
        try {
            if (this.u == null) {
                this.u = new HashSet();
            }
            this.u.add(zadaVar);
            this.f10720b.unlock();
        } catch (Throwable th) {
            this.f10720b.unlock();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
    
        if (r3 == false) goto L21;
     */
    @Override // com.google.android.gms.common.api.GoogleApiClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(com.google.android.gms.common.api.internal.zada r3) {
        /*
            r2 = this;
            java.util.concurrent.locks.Lock r0 = r2.f10720b
            r0.lock()
            java.util.Set r0 = r2.u     // Catch: java.lang.Throwable -> L16
            java.lang.String r1 = "GoogleApiClientImpl"
            if (r0 != 0) goto L18
            java.lang.String r3 = "Attempted to remove pending transform when no transforms are registered."
            java.lang.Exception r0 = new java.lang.Exception     // Catch: java.lang.Throwable -> L16
            r0.<init>()     // Catch: java.lang.Throwable -> L16
            android.util.Log.wtf(r1, r3, r0)     // Catch: java.lang.Throwable -> L16
            goto L4c
        L16:
            r3 = move-exception
            goto L59
        L18:
            boolean r3 = r0.remove(r3)     // Catch: java.lang.Throwable -> L16
            if (r3 != 0) goto L29
            java.lang.String r3 = "Failed to remove pending transform - this may lead to memory leaks!"
            java.lang.Exception r0 = new java.lang.Exception     // Catch: java.lang.Throwable -> L16
            r0.<init>()     // Catch: java.lang.Throwable -> L16
            android.util.Log.wtf(r1, r3, r0)     // Catch: java.lang.Throwable -> L16
            goto L4c
        L29:
            java.util.concurrent.locks.Lock r3 = r2.f10720b     // Catch: java.lang.Throwable -> L16
            r3.lock()     // Catch: java.lang.Throwable -> L16
            java.util.Set r3 = r2.u     // Catch: java.lang.Throwable -> L52
            if (r3 != 0) goto L38
            java.util.concurrent.locks.Lock r3 = r2.f10720b     // Catch: java.lang.Throwable -> L16
            r3.unlock()     // Catch: java.lang.Throwable -> L16
            goto L45
        L38:
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L52
            r3 = r3 ^ 1
            java.util.concurrent.locks.Lock r0 = r2.f10720b     // Catch: java.lang.Throwable -> L16
            r0.unlock()     // Catch: java.lang.Throwable -> L16
            if (r3 != 0) goto L4c
        L45:
            com.google.android.gms.common.api.internal.zaca r3 = r2.f10722d     // Catch: java.lang.Throwable -> L16
            if (r3 == 0) goto L4c
            r3.b()     // Catch: java.lang.Throwable -> L16
        L4c:
            java.util.concurrent.locks.Lock r2 = r2.f10720b
            r2.unlock()
            return
        L52:
            r3 = move-exception
            java.util.concurrent.locks.Lock r0 = r2.f10720b     // Catch: java.lang.Throwable -> L16
            r0.unlock()     // Catch: java.lang.Throwable -> L16
            throw r3     // Catch: java.lang.Throwable -> L16
        L59:
            java.util.concurrent.locks.Lock r2 = r2.f10720b
            r2.unlock()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zabe.k(com.google.android.gms.common.api.internal.zada):void");
    }

    public final boolean l() {
        zaca zacaVar = this.f10722d;
        return zacaVar != null && zacaVar.e();
    }

    final String o() {
        StringWriter stringWriter = new StringWriter();
        f("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    final boolean t() {
        if (!this.f10726h) {
            return false;
        }
        this.f10726h = false;
        this.f10729k.removeMessages(2);
        this.f10729k.removeMessages(1);
        zabx zabxVar = this.zab;
        if (zabxVar != null) {
            zabxVar.b();
            this.zab = null;
        }
        return true;
    }
}
