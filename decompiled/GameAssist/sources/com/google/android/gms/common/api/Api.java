package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class Api<O extends ApiOptions> {

    /* renamed from: a, reason: collision with root package name */
    private final AbstractClientBuilder f10510a;

    /* renamed from: b, reason: collision with root package name */
    private final ClientKey f10511b;

    /* renamed from: c, reason: collision with root package name */
    private final String f10512c;

    @KeepForSdk
    public static abstract class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O> {
        public Client a(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return b(context, looper, clientSettings, obj, connectionCallbacks, onConnectionFailedListener);
        }

        public Client b(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    @KeepForSdk
    public interface AnyClient {
    }

    @KeepForSdk
    public static class AnyClientKey<C extends AnyClient> {
    }

    public interface ApiOptions {

        /* renamed from: b, reason: collision with root package name */
        public static final NoOptions f10513b = new NoOptions(null);

        public interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            Account k();
        }

        public interface HasGoogleSignInAccountOptions extends HasOptions {
            GoogleSignInAccount i();
        }

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            /* synthetic */ NoOptions(zaa zaaVar) {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public static abstract class BaseClientBuilder<T extends AnyClient, O> {

        @KeepForSdk
        public static final int API_PRIORITY_GAMES = 1;

        @KeepForSdk
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;

        @KeepForSdk
        public static final int API_PRIORITY_PLUS = 2;

        @NonNull
        @KeepForSdk
        public List<Scope> getImpliedScopes(@Nullable O o2) {
            return Collections.emptyList();
        }

        @KeepForSdk
        public int getPriority() {
            return API_PRIORITY_OTHER;
        }
    }

    @KeepForSdk
    public interface Client extends AnyClient {
        boolean a();

        boolean b();

        String c();

        void d(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        void disconnect();

        void disconnect(String str);

        boolean e();

        boolean g();

        Set i();

        boolean isConnected();

        void j(IAccountAccessor iAccountAccessor, Set set);

        void k(BaseGmsClient.SignOutCallbacks signOutCallbacks);

        void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        int n();

        Feature[] o();

        String q();

        Intent r();
    }

    @KeepForSdk
    public static final class ClientKey<C extends Client> extends AnyClientKey<C> {
    }

    public Api(String str, AbstractClientBuilder abstractClientBuilder, ClientKey clientKey) {
        Preconditions.j(abstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.j(clientKey, "Cannot construct an Api with a null ClientKey");
        this.f10512c = str;
        this.f10510a = abstractClientBuilder;
        this.f10511b = clientKey;
    }

    public final AbstractClientBuilder a() {
        return this.f10510a;
    }

    public final AnyClientKey b() {
        return this.f10511b;
    }

    public final BaseClientBuilder c() {
        return this.f10510a;
    }

    public final String d() {
        return this.f10512c;
    }
}
