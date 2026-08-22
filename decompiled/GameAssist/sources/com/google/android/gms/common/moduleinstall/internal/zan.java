package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final /* synthetic */ class zan implements RemoteCall {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ zay f11174a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiFeatureRequest f11175b;

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).E()).zaf(new zaw(this.f11174a, (TaskCompletionSource) obj2), this.f11175b);
    }
}
