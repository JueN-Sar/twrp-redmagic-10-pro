package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zat;

@KeepForSdk
/* loaded from: classes.dex */
public class SignInClientImpl extends GmsClient<zaf> implements com.google.android.gms.signin.zae {
    public static final /* synthetic */ int P = 0;
    private final boolean L;
    private final ClientSettings M;
    private final Bundle N;
    private final Integer O;

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.L = true;
        this.M = clientSettings;
        this.N = bundle;
        this.O = clientSettings.g();
    }

    public static Bundle l0(ClientSettings clientSettings) {
        clientSettings.f();
        Integer g2 = clientSettings.g();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.a());
        if (g2 != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", g2.intValue());
        }
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
        bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
        bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
        bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
        bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
        bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle B() {
        if (!z().getPackageName().equals(this.M.d())) {
            this.N.putString("com.google.android.gms.signin.internal.realClientPackageName", this.M.d());
        }
        return this.N;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String F() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String G() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.signin.zae
    public final void f() {
        try {
            ((zaf) E()).zae(((Integer) Preconditions.i(this.O)).intValue());
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean g() {
        return this.L;
    }

    @Override // com.google.android.gms.signin.zae
    public final void h() {
        d(new BaseGmsClient.LegacyClientCallbackAdapter());
    }

    @Override // com.google.android.gms.signin.zae
    public final void m(zae zaeVar) {
        Preconditions.j(zaeVar, "Expecting a valid ISignInCallbacks");
        try {
            Account b2 = this.M.b();
            ((zaf) E()).zag(new zai(1, new zat(b2, ((Integer) Preconditions.i(this.O)).intValue(), "<<default account>>".equals(b2.name) ? Storage.a(z()).b() : null)), zaeVar);
        } catch (RemoteException e2) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zaeVar.zab(new zak(1, new ConnectionResult(8, null), null));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e2);
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int n() {
        return GooglePlayServicesUtilLight.f10505a;
    }

    @Override // com.google.android.gms.signin.zae
    public final void p(IAccountAccessor iAccountAccessor, boolean z) {
        try {
            ((zaf) E()).zaf(iAccountAccessor, ((Integer) Preconditions.i(this.O)).intValue(), z);
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface t(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return queryLocalInterface instanceof zaf ? (zaf) queryLocalInterface : new zaf(iBinder);
    }
}
