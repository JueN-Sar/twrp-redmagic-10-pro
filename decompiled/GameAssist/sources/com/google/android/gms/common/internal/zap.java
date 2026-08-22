package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zap implements PendingResult.StatusListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ PendingResult f11071a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f11072b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ PendingResultUtil.ResultConverter f11073c;

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void a(Status status) {
        if (!status.Y()) {
            this.f11072b.b(ApiExceptionUtil.a(status));
        } else {
            this.f11072b.c(this.f11073c.a(this.f11071a.c(0L, TimeUnit.MILLISECONDS)));
        }
    }
}
