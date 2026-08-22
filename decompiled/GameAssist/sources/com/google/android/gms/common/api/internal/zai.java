package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
public abstract class zai {

    /* renamed from: a, reason: collision with root package name */
    public final int f10842a;

    public zai(int i2) {
        this.f10842a = i2;
    }

    static /* bridge */ /* synthetic */ Status e(RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage());
    }

    public abstract void a(Status status);

    public abstract void b(Exception exc);

    public abstract void c(zabq zabqVar);

    public abstract void d(zaad zaadVar, boolean z);
}
