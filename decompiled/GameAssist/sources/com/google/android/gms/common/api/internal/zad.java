package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
abstract class zad extends zac {

    /* renamed from: b, reason: collision with root package name */
    protected final TaskCompletionSource f10823b;

    public zad(int i2, TaskCompletionSource taskCompletionSource) {
        super(i2);
        this.f10823b = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void a(Status status) {
        this.f10823b.d(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void b(Exception exc) {
        this.f10823b.d(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void c(zabq zabqVar) {
        try {
            h(zabqVar);
        } catch (DeadObjectException e2) {
            a(zai.e(e2));
            throw e2;
        } catch (RemoteException e3) {
            a(zai.e(e3));
        } catch (RuntimeException e4) {
            this.f10823b.d(e4);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public void d(zaad zaadVar, boolean z) {
    }

    protected abstract void h(zabq zabqVar);
}
