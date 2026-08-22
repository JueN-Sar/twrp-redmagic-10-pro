package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes.dex */
public class RegistrationMethods<A extends Api.AnyClient, L> {

    /* renamed from: a, reason: collision with root package name */
    public final RegisterListenerMethod f10626a;

    /* renamed from: b, reason: collision with root package name */
    public final UnregisterListenerMethod f10627b;

    /* renamed from: c, reason: collision with root package name */
    public final Runnable f10628c;

    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, L> {

        /* renamed from: a, reason: collision with root package name */
        private RemoteCall f10629a;

        /* renamed from: b, reason: collision with root package name */
        private RemoteCall f10630b;

        /* renamed from: d, reason: collision with root package name */
        private ListenerHolder f10632d;

        /* renamed from: e, reason: collision with root package name */
        private Feature[] f10633e;

        /* renamed from: g, reason: collision with root package name */
        private int f10635g;

        /* renamed from: c, reason: collision with root package name */
        private Runnable f10631c = new Runnable() { // from class: com.google.android.gms.common.api.internal.zacj
            @Override // java.lang.Runnable
            public final void run() {
            }
        };

        /* renamed from: f, reason: collision with root package name */
        private boolean f10634f = true;

        /* synthetic */ Builder(zacm zacmVar) {
        }

        public RegistrationMethods a() {
            Preconditions.b(this.f10629a != null, "Must set register function");
            Preconditions.b(this.f10630b != null, "Must set unregister function");
            Preconditions.b(this.f10632d != null, "Must set holder");
            return new RegistrationMethods(new zack(this, this.f10632d, this.f10633e, this.f10634f, this.f10635g), new zacl(this, (ListenerHolder.ListenerKey) Preconditions.j(this.f10632d.b(), "Key must not be null")), this.f10631c, null);
        }

        public Builder b(RemoteCall remoteCall) {
            this.f10629a = remoteCall;
            return this;
        }

        public Builder c(boolean z) {
            this.f10634f = z;
            return this;
        }

        public Builder d(Feature... featureArr) {
            this.f10633e = featureArr;
            return this;
        }

        public Builder e(int i2) {
            this.f10635g = i2;
            return this;
        }

        public Builder f(RemoteCall remoteCall) {
            this.f10630b = remoteCall;
            return this;
        }

        public Builder g(ListenerHolder listenerHolder) {
            this.f10632d = listenerHolder;
            return this;
        }
    }

    /* synthetic */ RegistrationMethods(RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, Runnable runnable, zacn zacnVar) {
        this.f10626a = registerListenerMethod;
        this.f10627b = unregisterListenerMethod;
        this.f10628c = runnable;
    }

    public static Builder a() {
        return new Builder(null);
    }
}
