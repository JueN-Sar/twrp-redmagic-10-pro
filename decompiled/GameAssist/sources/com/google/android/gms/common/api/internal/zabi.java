package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zabi implements zaca, zau {

    /* renamed from: a, reason: collision with root package name */
    private final Lock f10740a;

    /* renamed from: b, reason: collision with root package name */
    private final Condition f10741b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f10742c;

    /* renamed from: d, reason: collision with root package name */
    private final GoogleApiAvailabilityLight f10743d;

    /* renamed from: e, reason: collision with root package name */
    private final zabh f10744e;

    /* renamed from: f, reason: collision with root package name */
    final Map f10745f;

    /* renamed from: h, reason: collision with root package name */
    final ClientSettings f10747h;

    /* renamed from: i, reason: collision with root package name */
    final Map f10748i;

    /* renamed from: j, reason: collision with root package name */
    final Api.AbstractClientBuilder f10749j;

    /* renamed from: k, reason: collision with root package name */
    private volatile zabf f10750k;

    /* renamed from: m, reason: collision with root package name */
    int f10752m;

    /* renamed from: n, reason: collision with root package name */
    final zabe f10753n;

    /* renamed from: o, reason: collision with root package name */
    final zabz f10754o;

    /* renamed from: g, reason: collision with root package name */
    final Map f10746g = new HashMap();

    /* renamed from: l, reason: collision with root package name */
    private ConnectionResult f10751l = null;

    public zabi(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, ClientSettings clientSettings, Map map2, Api.AbstractClientBuilder abstractClientBuilder, ArrayList arrayList, zabz zabzVar) {
        this.f10742c = context;
        this.f10740a = lock;
        this.f10743d = googleApiAvailabilityLight;
        this.f10745f = map;
        this.f10747h = clientSettings;
        this.f10748i = map2;
        this.f10749j = abstractClientBuilder;
        this.f10753n = zabeVar;
        this.f10754o = zabzVar;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((zat) arrayList.get(i2)).a(this);
        }
        this.f10744e = new zabh(this, looper);
        this.f10741b = lock.newCondition();
        this.f10750k = new zaax(this);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void a() {
        this.f10750k.c();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void b() {
        if (this.f10750k instanceof zaaj) {
            ((zaaj) this.f10750k).i();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void c() {
        if (this.f10750k.f()) {
            this.f10746g.clear();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mState=").println(this.f10750k);
        for (Api api : this.f10748i.keySet()) {
            String valueOf = String.valueOf(str);
            printWriter.append((CharSequence) str).append((CharSequence) api.d()).println(":");
            ((Api.Client) Preconditions.i((Api.Client) this.f10745f.get(api.b()))).l(valueOf.concat("  "), fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean e() {
        return this.f10750k instanceof zaaj;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final BaseImplementation.ApiMethodImpl f(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.n();
        return this.f10750k.g(apiMethodImpl);
    }

    @Override // com.google.android.gms.common.api.internal.zau
    public final void g(ConnectionResult connectionResult, Api api, boolean z) {
        this.f10740a.lock();
        try {
            this.f10750k.d(connectionResult, api, z);
        } finally {
            this.f10740a.unlock();
        }
    }

    final void j() {
        this.f10740a.lock();
        try {
            this.f10753n.t();
            this.f10750k = new zaaj(this);
            this.f10750k.b();
            this.f10741b.signalAll();
        } finally {
            this.f10740a.unlock();
        }
    }

    final void k() {
        this.f10740a.lock();
        try {
            this.f10750k = new zaaw(this, this.f10747h, this.f10748i, this.f10743d, this.f10749j, this.f10740a, this.f10742c);
            this.f10750k.b();
            this.f10741b.signalAll();
        } finally {
            this.f10740a.unlock();
        }
    }

    final void l(ConnectionResult connectionResult) {
        this.f10740a.lock();
        try {
            this.f10751l = connectionResult;
            this.f10750k = new zaax(this);
            this.f10750k.b();
            this.f10741b.signalAll();
        } finally {
            this.f10740a.unlock();
        }
    }

    final void m(zabg zabgVar) {
        zabh zabhVar = this.f10744e;
        zabhVar.sendMessage(zabhVar.obtainMessage(1, zabgVar));
    }

    final void n(RuntimeException runtimeException) {
        zabh zabhVar = this.f10744e;
        zabhVar.sendMessage(zabhVar.obtainMessage(2, runtimeException));
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.f10740a.lock();
        try {
            this.f10750k.a(bundle);
        } finally {
            this.f10740a.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
        this.f10740a.lock();
        try {
            this.f10750k.e(i2);
        } finally {
            this.f10740a.unlock();
        }
    }
}
