package com.google.android.gms.common.moduleinstall.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class zay extends GoogleApi implements ModuleInstallClient {

    /* renamed from: k, reason: collision with root package name */
    private static final Api.ClientKey f11180k;

    /* renamed from: l, reason: collision with root package name */
    private static final Api.AbstractClientBuilder f11181l;

    /* renamed from: m, reason: collision with root package name */
    private static final Api f11182m;

    /* renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f11183n = 0;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        f11180k = clientKey;
        zaq zaqVar = new zaq();
        f11181l = zaqVar;
        f11182m = new Api("ModuleInstall.API", zaqVar, clientKey);
    }

    public zay(Context context) {
        super(context, f11182m, Api.ApiOptions.f10513b, GoogleApi.Settings.f10531c);
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task b(ModuleInstallRequest moduleInstallRequest) {
        final ApiFeatureRequest G = ApiFeatureRequest.G(moduleInstallRequest);
        final InstallStatusListener b2 = moduleInstallRequest.b();
        Executor c2 = moduleInstallRequest.c();
        if (G.P().isEmpty()) {
            return Tasks.c(new ModuleInstallResponse(0));
        }
        if (b2 == null) {
            TaskApiCall.Builder a2 = TaskApiCall.a();
            a2.d(com.google.android.gms.internal.base.zav.f11388a);
            a2.c(true);
            a2.e(27304);
            a2.b(new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zao
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) {
                    ((zaf) ((zaz) obj).E()).zag(new zat(zay.this, (TaskCompletionSource) obj2), G, null);
                }
            });
            return e(a2.a());
        }
        Preconditions.i(b2);
        ListenerHolder l2 = c2 == null ? l(b2, InstallStatusListener.class.getSimpleName()) : ListenerHolders.b(b2, c2, InstallStatusListener.class.getSimpleName());
        final zaab zaabVar = new zaab(l2);
        final AtomicReference atomicReference = new AtomicReference();
        RemoteCall remoteCall = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zai
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((zaf) ((zaz) obj).E()).zag(new zau(zay.this, atomicReference, (TaskCompletionSource) obj2, b2), G, zaabVar);
            }
        };
        RemoteCall remoteCall2 = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zaj
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((zaf) ((zaz) obj).E()).zai(new zav(zay.this, (TaskCompletionSource) obj2), zaabVar);
            }
        };
        RegistrationMethods.Builder a3 = RegistrationMethods.a();
        a3.g(l2);
        a3.d(com.google.android.gms.internal.base.zav.f11388a);
        a3.c(true);
        a3.b(remoteCall);
        a3.f(remoteCall2);
        a3.e(27305);
        return f(a3.a()).m(new SuccessContinuation() { // from class: com.google.android.gms.common.moduleinstall.internal.zak
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task a(Object obj) {
                int i2 = zay.f11183n;
                AtomicReference atomicReference2 = atomicReference;
                return atomicReference2.get() != null ? Tasks.c((ModuleInstallResponse) atomicReference2.get()) : Tasks.b(new ApiException(Status.f10545n));
            }
        });
    }
}
