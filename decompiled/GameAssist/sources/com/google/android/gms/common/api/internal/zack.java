package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
final class zack extends RegisterListenerMethod {

    /* renamed from: e, reason: collision with root package name */
    final /* synthetic */ RegistrationMethods.Builder f10811e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zack(RegistrationMethods.Builder builder, ListenerHolder listenerHolder, Feature[] featureArr, boolean z, int i2) {
        super(listenerHolder, featureArr, z, i2);
        this.f10811e = builder;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected final void d(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        RemoteCall remoteCall;
        remoteCall = this.f10811e.f10629a;
        remoteCall.accept(anyClient, taskCompletionSource);
    }
}
