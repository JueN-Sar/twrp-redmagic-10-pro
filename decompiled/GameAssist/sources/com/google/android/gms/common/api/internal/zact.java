package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

/* loaded from: classes.dex */
public final class zact extends com.google.android.gms.signin.internal.zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final Api.AbstractClientBuilder zaa = com.google.android.gms.signin.zad.f13662c;
    private final Context zab;
    private final Handler zac;
    private final Api.AbstractClientBuilder zad;
    private final Set zae;
    private final ClientSettings zaf;
    private com.google.android.gms.signin.zae zag;
    private zacs zah;

    @WorkerThread
    public zact(Context context, Handler handler, @NonNull ClientSettings clientSettings) {
        Api.AbstractClientBuilder abstractClientBuilder = zaa;
        this.zab = context;
        this.zac = handler;
        this.zaf = (ClientSettings) Preconditions.j(clientSettings, "ClientSettings must not be null");
        this.zae = clientSettings.e();
        this.zad = abstractClientBuilder;
    }

    static /* bridge */ /* synthetic */ void zad(zact zactVar, com.google.android.gms.signin.internal.zak zakVar) {
        ConnectionResult G = zakVar.G();
        if (G.W()) {
            com.google.android.gms.common.internal.zav zavVar = (com.google.android.gms.common.internal.zav) Preconditions.i(zakVar.P());
            ConnectionResult G2 = zavVar.G();
            if (!G2.W()) {
                String valueOf = String.valueOf(G2);
                Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
                zactVar.zah.c(G2);
                zactVar.zag.disconnect();
                return;
            }
            zactVar.zah.b(zavVar.P(), zactVar.zae);
        } else {
            zactVar.zah.c(G);
        }
        zactVar.zag.disconnect();
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.zag.m(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zah.c(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    @WorkerThread
    public final void onConnectionSuspended(int i2) {
        this.zah.d(i2);
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zae
    @BinderThread
    public final void zab(com.google.android.gms.signin.internal.zak zakVar) {
        this.zac.post(new zacr(this, zakVar));
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    @WorkerThread
    public final void zae(zacs zacsVar) {
        com.google.android.gms.signin.zae zaeVar = this.zag;
        if (zaeVar != null) {
            zaeVar.disconnect();
        }
        this.zaf.j(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder abstractClientBuilder = this.zad;
        Context context = this.zab;
        Handler handler = this.zac;
        ClientSettings clientSettings = this.zaf;
        this.zag = abstractClientBuilder.a(context, handler.getLooper(), clientSettings, clientSettings.f(), this, this);
        this.zah = zacsVar;
        Set set = this.zae;
        if (set == null || set.isEmpty()) {
            this.zac.post(new zacq(this));
        } else {
            this.zag.h();
        }
    }

    public final void zaf() {
        com.google.android.gms.signin.zae zaeVar = this.zag;
        if (zaeVar != null) {
            zaeVar.disconnect();
        }
    }
}
