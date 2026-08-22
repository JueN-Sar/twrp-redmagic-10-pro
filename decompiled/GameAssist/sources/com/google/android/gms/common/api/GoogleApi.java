package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.NonGmsServiceBrokerClient;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabv;
import com.google.android.gms.common.api.internal.zact;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class GoogleApi<O extends Api.ApiOptions> implements HasApiKey<O> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10521a;

    /* renamed from: b, reason: collision with root package name */
    private final String f10522b;

    /* renamed from: c, reason: collision with root package name */
    private final Api f10523c;

    /* renamed from: d, reason: collision with root package name */
    private final Api.ApiOptions f10524d;

    /* renamed from: e, reason: collision with root package name */
    private final ApiKey f10525e;

    /* renamed from: f, reason: collision with root package name */
    private final Looper f10526f;

    /* renamed from: g, reason: collision with root package name */
    private final int f10527g;

    /* renamed from: h, reason: collision with root package name */
    private final GoogleApiClient f10528h;

    /* renamed from: i, reason: collision with root package name */
    private final StatusExceptionMapper f10529i;

    /* renamed from: j, reason: collision with root package name */
    protected final GoogleApiManager f10530j;

    @KeepForSdk
    public static class Settings {

        /* renamed from: c, reason: collision with root package name */
        public static final Settings f10531c = new Builder().a();

        /* renamed from: a, reason: collision with root package name */
        public final StatusExceptionMapper f10532a;

        /* renamed from: b, reason: collision with root package name */
        public final Looper f10533b;

        @KeepForSdk
        public static class Builder {

            /* renamed from: a, reason: collision with root package name */
            private StatusExceptionMapper f10534a;

            /* renamed from: b, reason: collision with root package name */
            private Looper f10535b;

            /* JADX WARN: Multi-variable type inference failed */
            public Settings a() {
                if (this.f10534a == null) {
                    this.f10534a = new ApiExceptionMapper();
                }
                if (this.f10535b == null) {
                    this.f10535b = Looper.getMainLooper();
                }
                return new Settings(this.f10534a, this.f10535b);
            }
        }

        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.f10532a = statusExceptionMapper;
            this.f10533b = looper;
        }
    }

    private GoogleApi(Context context, Activity activity, Api api, Api.ApiOptions apiOptions, Settings settings) {
        Preconditions.j(context, "Null context is not permitted.");
        Preconditions.j(api, "Api must not be null.");
        Preconditions.j(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context context2 = (Context) Preconditions.j(context.getApplicationContext(), "The provided context did not have an application context.");
        this.f10521a = context2;
        String attributionTag = context.getAttributionTag();
        this.f10522b = attributionTag;
        this.f10523c = api;
        this.f10524d = apiOptions;
        this.f10526f = settings.f10533b;
        ApiKey a2 = ApiKey.a(api, apiOptions, attributionTag);
        this.f10525e = a2;
        this.f10528h = new zabv(this);
        GoogleApiManager t = GoogleApiManager.t(context2);
        this.f10530j = t;
        this.f10527g = t.k();
        this.f10529i = settings.f10532a;
        if (activity != null && !(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            zaae.j(activity, t, a2);
        }
        t.G(this);
    }

    private final BaseImplementation.ApiMethodImpl p(int i2, BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.n();
        this.f10530j.B(this, i2, apiMethodImpl);
        return apiMethodImpl;
    }

    private final Task q(int i2, TaskApiCall taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f10530j.C(this, i2, taskApiCall, taskCompletionSource, this.f10529i);
        return taskCompletionSource.a();
    }

    protected ClientSettings.Builder c() {
        Account k2;
        Set emptySet;
        GoogleSignInAccount i2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        Api.ApiOptions apiOptions = this.f10524d;
        if (!(apiOptions instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (i2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions).i()) == null) {
            Api.ApiOptions apiOptions2 = this.f10524d;
            k2 = apiOptions2 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) apiOptions2).k() : null;
        } else {
            k2 = i2.k();
        }
        builder.d(k2);
        Api.ApiOptions apiOptions3 = this.f10524d;
        if (apiOptions3 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) {
            GoogleSignInAccount i3 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions3).i();
            emptySet = i3 == null ? Collections.emptySet() : i3.e0();
        } else {
            emptySet = Collections.emptySet();
        }
        builder.c(emptySet);
        builder.e(this.f10521a.getClass().getName());
        builder.b(this.f10521a.getPackageName());
        return builder;
    }

    public Task d(TaskApiCall taskApiCall) {
        return q(2, taskApiCall);
    }

    public Task e(TaskApiCall taskApiCall) {
        return q(0, taskApiCall);
    }

    public Task f(RegistrationMethods registrationMethods) {
        Preconditions.i(registrationMethods);
        Preconditions.j(registrationMethods.f10626a.b(), "Listener has already been released.");
        Preconditions.j(registrationMethods.f10627b.a(), "Listener has already been released.");
        return this.f10530j.v(this, registrationMethods.f10626a, registrationMethods.f10627b, registrationMethods.f10628c);
    }

    public Task g(ListenerHolder.ListenerKey listenerKey, int i2) {
        Preconditions.j(listenerKey, "Listener key cannot be null.");
        return this.f10530j.w(this, listenerKey, i2);
    }

    public BaseImplementation.ApiMethodImpl h(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        p(1, apiMethodImpl);
        return apiMethodImpl;
    }

    public final ApiKey i() {
        return this.f10525e;
    }

    protected String j() {
        return this.f10522b;
    }

    public Looper k() {
        return this.f10526f;
    }

    public ListenerHolder l(Object obj, String str) {
        return ListenerHolders.a(obj, this.f10526f, str);
    }

    public final int m() {
        return this.f10527g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Api.Client n(Looper looper, zabq zabqVar) {
        ClientSettings a2 = c().a();
        Api.Client a3 = ((Api.AbstractClientBuilder) Preconditions.i(this.f10523c.a())).a(this.f10521a, looper, a2, this.f10524d, zabqVar, zabqVar);
        String j2 = j();
        if (j2 != null && (a3 instanceof BaseGmsClient)) {
            ((BaseGmsClient) a3).Q(j2);
        }
        if (j2 != null && (a3 instanceof NonGmsServiceBrokerClient)) {
            ((NonGmsServiceBrokerClient) a3).t(j2);
        }
        return a3;
    }

    public final zact o(Context context, Handler handler) {
        return new zact(context, handler, c().a());
    }

    public GoogleApi(Context context, Api api, Api.ApiOptions apiOptions, Settings settings) {
        this(context, null, api, apiOptions, settings);
    }
}
