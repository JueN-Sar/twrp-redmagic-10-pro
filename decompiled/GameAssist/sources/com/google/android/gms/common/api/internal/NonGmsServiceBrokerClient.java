package com.google.android.gms.common.api.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;

@KeepForSdk
/* loaded from: classes.dex */
public final class NonGmsServiceBrokerClient implements Api.Client, ServiceConnection {

    /* renamed from: c, reason: collision with root package name */
    private final String f10610c;

    /* renamed from: h, reason: collision with root package name */
    private final String f10611h;

    /* renamed from: i, reason: collision with root package name */
    private final ComponentName f10612i;

    /* renamed from: j, reason: collision with root package name */
    private final Context f10613j;

    /* renamed from: k, reason: collision with root package name */
    private final ConnectionCallbacks f10614k;

    /* renamed from: l, reason: collision with root package name */
    private final Handler f10615l;

    /* renamed from: m, reason: collision with root package name */
    private final OnConnectionFailedListener f10616m;

    /* renamed from: n, reason: collision with root package name */
    private IBinder f10617n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f10618o;

    /* renamed from: p, reason: collision with root package name */
    private String f10619p;

    /* renamed from: q, reason: collision with root package name */
    private String f10620q;

    private final void u() {
        if (Thread.currentThread() != this.f10615l.getLooper().getThread()) {
            throw new IllegalStateException("This method should only run on the NonGmsServiceBrokerClient's handler thread.");
        }
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean a() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean b() {
        u();
        return this.f10618o;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final String c() {
        String str = this.f10610c;
        if (str != null) {
            return str;
        }
        Preconditions.i(this.f10612i);
        return this.f10612i.getPackageName();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void d(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        u();
        String.valueOf(this.f10617n);
        if (isConnected()) {
            try {
                disconnect("connect() called when already connected");
            } catch (Exception unused) {
            }
        }
        try {
            Intent intent = new Intent();
            ComponentName componentName = this.f10612i;
            if (componentName != null) {
                intent.setComponent(componentName);
            } else {
                intent.setPackage(this.f10610c).setAction(this.f10611h);
            }
            boolean bindService = this.f10613j.bindService(intent, this, GmsClientSupervisor.a());
            this.f10618o = bindService;
            if (!bindService) {
                this.f10617n = null;
                this.f10616m.onConnectionFailed(new ConnectionResult(16));
            }
            String.valueOf(this.f10617n);
        } catch (SecurityException e2) {
            this.f10618o = false;
            this.f10617n = null;
            throw e2;
        }
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        u();
        String.valueOf(this.f10617n);
        try {
            this.f10613j.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
        this.f10618o = false;
        this.f10617n = null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean e() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean g() {
        return false;
    }

    final /* synthetic */ void h() {
        this.f10618o = false;
        this.f10617n = null;
        this.f10614k.onConnectionSuspended(1);
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Set i() {
        return Collections.emptySet();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean isConnected() {
        u();
        return this.f10617n != null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void j(IAccountAccessor iAccountAccessor, Set set) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void k(BaseGmsClient.SignOutCallbacks signOutCallbacks) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final int n() {
        return 0;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Feature[] o() {
        return new Feature[0];
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        this.f10615l.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.zacg
            @Override // java.lang.Runnable
            public final void run() {
                NonGmsServiceBrokerClient.this.s(iBinder);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f10615l.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.zacf
            @Override // java.lang.Runnable
            public final void run() {
                NonGmsServiceBrokerClient.this.h();
            }
        });
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final String q() {
        return this.f10619p;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Intent r() {
        return new Intent();
    }

    final /* synthetic */ void s(IBinder iBinder) {
        this.f10618o = false;
        this.f10617n = iBinder;
        String.valueOf(iBinder);
        this.f10614k.onConnected(new Bundle());
    }

    public final void t(String str) {
        this.f10620q = str;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void disconnect(String str) {
        u();
        this.f10619p = str;
        disconnect();
    }
}
