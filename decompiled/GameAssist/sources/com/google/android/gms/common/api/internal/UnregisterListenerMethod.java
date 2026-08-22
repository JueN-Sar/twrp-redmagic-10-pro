package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class UnregisterListenerMethod<A extends Api.AnyClient, L> {

    /* renamed from: a, reason: collision with root package name */
    private final ListenerHolder.ListenerKey f10643a;

    protected UnregisterListenerMethod(ListenerHolder.ListenerKey listenerKey) {
        this.f10643a = listenerKey;
    }

    public ListenerHolder.ListenerKey a() {
        return this.f10643a;
    }

    protected abstract void b(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource);
}
