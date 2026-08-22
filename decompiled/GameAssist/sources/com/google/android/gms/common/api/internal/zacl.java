package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
final class zacl extends UnregisterListenerMethod {

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ RegistrationMethods.Builder f10812b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zacl(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.f10812b = builder;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected final void b(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        RemoteCall remoteCall;
        remoteCall = this.f10812b.f10630b;
        remoteCall.accept(anyClient, taskCompletionSource);
    }
}
