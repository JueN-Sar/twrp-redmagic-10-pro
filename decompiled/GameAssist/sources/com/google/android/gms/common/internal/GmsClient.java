package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, zaj {
    private final ClientSettings I;
    private final Set J;
    private final Account K;

    @KeepForSdk
    @VisibleForTesting
    protected GmsClient(@NonNull Context context, @NonNull Handler handler, int i2, @NonNull ClientSettings clientSettings) {
        super(context, handler, GmsClientSupervisor.b(context), GoogleApiAvailability.q(), i2, null, null);
        this.I = (ClientSettings) Preconditions.i(clientSettings);
        this.K = clientSettings.a();
        this.J = k0(clientSettings.c());
    }

    private final Set k0(Set set) {
        Set j0 = j0(set);
        Iterator it = j0.iterator();
        while (it.hasNext()) {
            if (!set.contains((Scope) it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return j0;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Set D() {
        return this.J;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public Set i() {
        return g() ? this.J : Collections.emptySet();
    }

    protected Set j0(Set set) {
        return set;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Account v() {
        return this.K;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Executor x() {
        return null;
    }

    protected GmsClient(Context context, Looper looper, int i2, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, i2, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }

    protected GmsClient(Context context, Looper looper, int i2, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, GmsClientSupervisor.b(context), GoogleApiAvailability.q(), i2, clientSettings, (ConnectionCallbacks) Preconditions.i(connectionCallbacks), (OnConnectionFailedListener) Preconditions.i(onConnectionFailedListener));
    }

    @VisibleForTesting
    protected GmsClient(@NonNull Context context, @NonNull Looper looper, @NonNull GmsClientSupervisor gmsClientSupervisor, @NonNull GoogleApiAvailability googleApiAvailability, int i2, @NonNull ClientSettings clientSettings, @Nullable ConnectionCallbacks connectionCallbacks, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, gmsClientSupervisor, googleApiAvailability, i2, connectionCallbacks == null ? null : new zah(connectionCallbacks), onConnectionFailedListener == null ? null : new zai(onConnectionFailedListener), clientSettings.h());
        this.I = clientSettings;
        this.K = clientSettings.a();
        this.J = k0(clientSettings.c());
    }
}
