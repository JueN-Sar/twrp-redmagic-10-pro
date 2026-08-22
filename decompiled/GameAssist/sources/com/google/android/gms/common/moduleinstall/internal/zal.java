package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final /* synthetic */ class zal implements RemoteCall {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ zay f11170a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiFeatureRequest f11171b;

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).E()).zae(new zar(this.f11170a, (TaskCompletionSource) obj2), this.f11171b);
    }
}
