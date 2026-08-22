package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.data.DataHolder;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class DataHolderNotifier<L> implements ListenerHolder.Notifier<L> {

    /* renamed from: a, reason: collision with root package name */
    private final DataHolder f10581a;

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void a(Object obj) {
        c(obj, this.f10581a);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public void b() {
        DataHolder dataHolder = this.f10581a;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }

    protected abstract void c(Object obj, DataHolder dataHolder);
}
