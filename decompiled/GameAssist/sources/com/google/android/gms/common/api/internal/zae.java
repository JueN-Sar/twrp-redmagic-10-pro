package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public final class zae extends zai {

    /* renamed from: b, reason: collision with root package name */
    protected final BaseImplementation.ApiMethodImpl f10836b;

    public zae(int i2, BaseImplementation.ApiMethodImpl apiMethodImpl) {
        super(i2);
        this.f10836b = (BaseImplementation.ApiMethodImpl) Preconditions.j(apiMethodImpl, "Null methods are not runnable.");
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void a(Status status) {
        try {
            this.f10836b.x(status);
        } catch (IllegalStateException e2) {
            Log.w("ApiCallRunner", "Exception reporting failure", e2);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void b(Exception exc) {
        try {
            this.f10836b.x(new Status(10, exc.getClass().getSimpleName() + ": " + exc.getLocalizedMessage()));
        } catch (IllegalStateException e2) {
            Log.w("ApiCallRunner", "Exception reporting failure", e2);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void c(zabq zabqVar) {
        try {
            this.f10836b.v(zabqVar.u());
        } catch (RuntimeException e2) {
            b(e2);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void d(zaad zaadVar, boolean z) {
        zaadVar.c(this.f10836b, z);
    }
}
