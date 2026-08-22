package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {

    /* renamed from: a, reason: collision with root package name */
    private final Feature[] f10636a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f10637b;

    /* renamed from: c, reason: collision with root package name */
    private final int f10638c;

    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, ResultT> {

        /* renamed from: a, reason: collision with root package name */
        private RemoteCall f10639a;

        /* renamed from: c, reason: collision with root package name */
        private Feature[] f10641c;

        /* renamed from: b, reason: collision with root package name */
        private boolean f10640b = true;

        /* renamed from: d, reason: collision with root package name */
        private int f10642d = 0;

        /* synthetic */ Builder(zacw zacwVar) {
        }

        public TaskApiCall a() {
            Preconditions.b(this.f10639a != null, "execute parameter required");
            return new zacv(this, this.f10641c, this.f10640b, this.f10642d);
        }

        public Builder b(RemoteCall remoteCall) {
            this.f10639a = remoteCall;
            return this;
        }

        public Builder c(boolean z) {
            this.f10640b = z;
            return this;
        }

        public Builder d(Feature... featureArr) {
            this.f10641c = featureArr;
            return this;
        }

        public Builder e(int i2) {
            this.f10642d = i2;
            return this;
        }
    }

    protected TaskApiCall(Feature[] featureArr, boolean z, int i2) {
        this.f10636a = featureArr;
        boolean z2 = false;
        if (featureArr != null && z) {
            z2 = true;
        }
        this.f10637b = z2;
        this.f10638c = i2;
    }

    public static Builder a() {
        return new Builder(null);
    }

    protected abstract void b(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource);

    public boolean c() {
        return this.f10637b;
    }

    public final int d() {
        return this.f10638c;
    }

    public final Feature[] e() {
        return this.f10636a;
    }
}
