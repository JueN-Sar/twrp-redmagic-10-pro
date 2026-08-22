package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zag extends zac {

    /* renamed from: b, reason: collision with root package name */
    private final TaskApiCall f10838b;

    /* renamed from: c, reason: collision with root package name */
    private final TaskCompletionSource f10839c;

    /* renamed from: d, reason: collision with root package name */
    private final StatusExceptionMapper f10840d;

    public zag(int i2, TaskApiCall taskApiCall, TaskCompletionSource taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i2);
        this.f10839c = taskCompletionSource;
        this.f10838b = taskApiCall;
        this.f10840d = statusExceptionMapper;
        if (i2 == 2 && taskApiCall.c()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void a(Status status) {
        this.f10839c.d(this.f10840d.a(status));
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void b(Exception exc) {
        this.f10839c.d(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void c(zabq zabqVar) {
        try {
            this.f10838b.b(zabqVar.u(), this.f10839c);
        } catch (DeadObjectException e2) {
            throw e2;
        } catch (RemoteException e3) {
            a(zai.e(e3));
        } catch (RuntimeException e4) {
            this.f10839c.d(e4);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void d(zaad zaadVar, boolean z) {
        zaadVar.d(this.f10839c, z);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean f(zabq zabqVar) {
        return this.f10838b.c();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] g(zabq zabqVar) {
        return this.f10838b.e();
    }
}
