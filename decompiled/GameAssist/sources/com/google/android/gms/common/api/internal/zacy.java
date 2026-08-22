package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class zacy implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Result f10820c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zada f10821h;

    zacy(zada zadaVar, Result result) {
        this.f10821h = zadaVar;
        this.f10820c = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WeakReference weakReference;
        zacz zaczVar;
        zacz zaczVar2;
        WeakReference weakReference2;
        GoogleApiClient googleApiClient;
        ResultTransform resultTransform;
        zacz zaczVar3;
        zacz zaczVar4;
        WeakReference weakReference3;
        try {
            try {
                ThreadLocal threadLocal = BasePendingResult.f10565p;
                threadLocal.set(Boolean.TRUE);
                resultTransform = this.f10821h.f10824a;
                PendingResult b2 = ((ResultTransform) Preconditions.i(resultTransform)).b(this.f10820c);
                zada zadaVar = this.f10821h;
                zaczVar3 = zadaVar.f10831h;
                zaczVar4 = zadaVar.f10831h;
                zaczVar3.sendMessage(zaczVar4.obtainMessage(0, b2));
                threadLocal.set(Boolean.FALSE);
                zada zadaVar2 = this.f10821h;
                zada.o(this.f10820c);
                weakReference3 = this.f10821h.f10830g;
                googleApiClient = (GoogleApiClient) weakReference3.get();
                if (googleApiClient == null) {
                    return;
                }
            } catch (RuntimeException e2) {
                zada zadaVar3 = this.f10821h;
                zaczVar = zadaVar3.f10831h;
                zaczVar2 = zadaVar3.f10831h;
                zaczVar.sendMessage(zaczVar2.obtainMessage(1, e2));
                BasePendingResult.f10565p.set(Boolean.FALSE);
                zada zadaVar4 = this.f10821h;
                zada.o(this.f10820c);
                weakReference2 = this.f10821h.f10830g;
                googleApiClient = (GoogleApiClient) weakReference2.get();
                if (googleApiClient == null) {
                    return;
                }
            }
            googleApiClient.k(this.f10821h);
        } catch (Throwable th) {
            BasePendingResult.f10565p.set(Boolean.FALSE);
            zada zadaVar5 = this.f10821h;
            zada.o(this.f10820c);
            weakReference = this.f10821h.f10830g;
            GoogleApiClient googleApiClient2 = (GoogleApiClient) weakReference.get();
            if (googleApiClient2 != null) {
                googleApiClient2.k(this.f10821h);
            }
            throw th;
        }
    }
}
