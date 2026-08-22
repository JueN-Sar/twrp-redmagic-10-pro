package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zabb implements ResultCallback {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ StatusPendingResult f10714a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ boolean f10715b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ GoogleApiClient f10716c;

    /* renamed from: d, reason: collision with root package name */
    final /* synthetic */ zabe f10717d;

    zabb(zabe zabeVar, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.f10717d = zabeVar;
        this.f10714a = statusPendingResult;
        this.f10715b = z;
        this.f10716c = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* bridge */ /* synthetic */ void a(Result result) {
        Context context;
        Status status = (Status) result;
        context = this.f10717d.f10724f;
        Storage.a(context).e();
        if (status.Y() && this.f10717d.l()) {
            zabe zabeVar = this.f10717d;
            zabeVar.e();
            zabeVar.d();
        }
        this.f10714a.j(status);
        if (this.f10715b) {
            this.f10716c.e();
        }
    }
}
