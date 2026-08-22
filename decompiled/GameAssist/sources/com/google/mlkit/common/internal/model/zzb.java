package com.google.mlkit.common.internal.model;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.model.CustomRemoteModel;

/* loaded from: classes.dex */
public final /* synthetic */ class zzb implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ zzg f15898c;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ CustomRemoteModel f15899h;

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f15900i;

    @Override // java.lang.Runnable
    public final void run() {
        this.f15898c.b(this.f15899h, this.f15900i);
    }
}
