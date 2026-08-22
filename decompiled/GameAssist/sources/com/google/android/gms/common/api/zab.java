package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* loaded from: classes.dex */
final class zab implements PendingResult.StatusListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Batch f10880a;

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void a(Status status) {
        Object obj;
        int i2;
        int i3;
        boolean z;
        boolean z2;
        PendingResult[] pendingResultArr;
        obj = this.f10880a.u;
        synchronized (obj) {
            try {
                if (this.f10880a.h()) {
                    return;
                }
                if (status.W()) {
                    this.f10880a.f10516s = true;
                } else if (!status.Y()) {
                    this.f10880a.f10515r = true;
                }
                Batch batch = this.f10880a;
                i2 = batch.f10514q;
                batch.f10514q = i2 - 1;
                Batch batch2 = this.f10880a;
                i3 = batch2.f10514q;
                if (i3 == 0) {
                    z = batch2.f10516s;
                    if (z) {
                        super/*com.google.android.gms.common.api.internal.BasePendingResult*/.d();
                    } else {
                        z2 = batch2.f10515r;
                        Status status2 = z2 ? new Status(13) : Status.f10543l;
                        Batch batch3 = this.f10880a;
                        pendingResultArr = batch3.t;
                        batch3.j(new BatchResult(status2, pendingResultArr));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
