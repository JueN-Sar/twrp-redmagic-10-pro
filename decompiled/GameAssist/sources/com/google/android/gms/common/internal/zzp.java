package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzp implements ServiceConnection, zzt {

    /* renamed from: c, reason: collision with root package name */
    private final Map f11115c = new HashMap();

    /* renamed from: h, reason: collision with root package name */
    private int f11116h = 2;

    /* renamed from: i, reason: collision with root package name */
    private boolean f11117i;

    /* renamed from: j, reason: collision with root package name */
    private IBinder f11118j;

    /* renamed from: k, reason: collision with root package name */
    private final zzo f11119k;

    /* renamed from: l, reason: collision with root package name */
    private ComponentName f11120l;

    /* renamed from: m, reason: collision with root package name */
    final /* synthetic */ zzs f11121m;

    public zzp(zzs zzsVar, zzo zzoVar) {
        this.f11121m = zzsVar;
        this.f11119k = zzoVar;
    }

    public final int a() {
        return this.f11116h;
    }

    public final ComponentName b() {
        return this.f11120l;
    }

    public final IBinder c() {
        return this.f11118j;
    }

    public final void d(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.f11115c.put(serviceConnection, serviceConnection2);
    }

    public final void e(String str, Executor executor) {
        ConnectionTracker connectionTracker;
        Context context;
        Context context2;
        ConnectionTracker connectionTracker2;
        Context context3;
        Handler handler;
        Handler handler2;
        long j2;
        this.f11116h = 3;
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (PlatformVersion.j()) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(vmPolicy).permitUnsafeIntentLaunch().build());
        }
        try {
            zzs zzsVar = this.f11121m;
            connectionTracker = zzsVar.f11127i;
            context = zzsVar.f11124f;
            zzo zzoVar = this.f11119k;
            context2 = zzsVar.f11124f;
            boolean c2 = connectionTracker.c(context, str, zzoVar.b(context2), this, 4225, executor);
            this.f11117i = c2;
            if (c2) {
                handler = this.f11121m.f11125g;
                Message obtainMessage = handler.obtainMessage(1, this.f11119k);
                handler2 = this.f11121m.f11125g;
                j2 = this.f11121m.f11129k;
                handler2.sendMessageDelayed(obtainMessage, j2);
            } else {
                this.f11116h = 2;
                try {
                    zzs zzsVar2 = this.f11121m;
                    connectionTracker2 = zzsVar2.f11127i;
                    context3 = zzsVar2.f11124f;
                    connectionTracker2.b(context3, this);
                } catch (IllegalArgumentException unused) {
                }
            }
            StrictMode.setVmPolicy(vmPolicy);
        } catch (Throwable th) {
            StrictMode.setVmPolicy(vmPolicy);
            throw th;
        }
    }

    public final void f(ServiceConnection serviceConnection, String str) {
        this.f11115c.remove(serviceConnection);
    }

    public final void g(String str) {
        Handler handler;
        ConnectionTracker connectionTracker;
        Context context;
        zzo zzoVar = this.f11119k;
        handler = this.f11121m.f11125g;
        handler.removeMessages(1, zzoVar);
        zzs zzsVar = this.f11121m;
        connectionTracker = zzsVar.f11127i;
        context = zzsVar.f11124f;
        connectionTracker.b(context, this);
        this.f11117i = false;
        this.f11116h = 2;
    }

    public final boolean h(ServiceConnection serviceConnection) {
        return this.f11115c.containsKey(serviceConnection);
    }

    public final boolean i() {
        return this.f11115c.isEmpty();
    }

    public final boolean j() {
        return this.f11117i;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.f11121m.f11123e;
        synchronized (hashMap) {
            try {
                handler = this.f11121m.f11125g;
                handler.removeMessages(1, this.f11119k);
                this.f11118j = iBinder;
                this.f11120l = componentName;
                Iterator it = this.f11115c.values().iterator();
                while (it.hasNext()) {
                    ((ServiceConnection) it.next()).onServiceConnected(componentName, iBinder);
                }
                this.f11116h = 1;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.f11121m.f11123e;
        synchronized (hashMap) {
            try {
                handler = this.f11121m.f11125g;
                handler.removeMessages(1, this.f11119k);
                this.f11118j = null;
                this.f11120l = componentName;
                Iterator it = this.f11115c.values().iterator();
                while (it.hasNext()) {
                    ((ServiceConnection) it.next()).onServiceDisconnected(componentName);
                }
                this.f11116h = 2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
