package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final /* synthetic */ class zacu implements RemoteCall {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BiConsumer f10818a;

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        this.f10818a.accept((Api.AnyClient) obj, (TaskCompletionSource) obj2);
    }
}
