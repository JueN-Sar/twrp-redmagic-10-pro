package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
final class zacv extends TaskApiCall {

    /* renamed from: d, reason: collision with root package name */
    final /* synthetic */ TaskApiCall.Builder f10819d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zacv(TaskApiCall.Builder builder, Feature[] featureArr, boolean z, int i2) {
        super(featureArr, z, i2);
        this.f10819d = builder;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final void b(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        RemoteCall remoteCall;
        remoteCall = this.f10819d.f10639a;
        remoteCall.accept(anyClient, taskCompletionSource);
    }
}
