package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {

    /* renamed from: a, reason: collision with root package name */
    private final ListenerHolder f10622a;

    /* renamed from: b, reason: collision with root package name */
    private final Feature[] f10623b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f10624c;

    /* renamed from: d, reason: collision with root package name */
    private final int f10625d;

    protected RegisterListenerMethod(ListenerHolder listenerHolder, Feature[] featureArr, boolean z, int i2) {
        this.f10622a = listenerHolder;
        this.f10623b = featureArr;
        this.f10624c = z;
        this.f10625d = i2;
    }

    public void a() {
        this.f10622a.a();
    }

    public ListenerHolder.ListenerKey b() {
        return this.f10622a.b();
    }

    public Feature[] c() {
        return this.f10623b;
    }

    protected abstract void d(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource);

    public final int e() {
        return this.f10625d;
    }

    public final boolean f() {
        return this.f10624c;
    }
}
