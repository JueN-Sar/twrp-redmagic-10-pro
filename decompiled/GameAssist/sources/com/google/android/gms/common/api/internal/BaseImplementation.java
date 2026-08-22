package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes.dex */
public class BaseImplementation {

    @KeepForSdk
    public interface ResultHolder<R> {
        void a(Object obj);
    }

    @KeepForSdk
    public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {

        /* renamed from: q, reason: collision with root package name */
        private final Api.AnyClientKey f10563q;

        /* renamed from: r, reason: collision with root package name */
        private final Api f10564r;

        protected ApiMethodImpl(Api api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.j(googleApiClient, "GoogleApiClient must not be null"));
            Preconditions.j(api, "Api must not be null");
            this.f10563q = api.b();
            this.f10564r = api;
        }

        private void w(RemoteException remoteException) {
            x(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
        public /* bridge */ /* synthetic */ void a(Object obj) {
            super.j((Result) obj);
        }

        protected abstract void r(Api.AnyClient anyClient);

        public final Api s() {
            return this.f10564r;
        }

        public final Api.AnyClientKey t() {
            return this.f10563q;
        }

        protected void u(Result result) {
        }

        public final void v(Api.AnyClient anyClient) {
            try {
                r(anyClient);
            } catch (DeadObjectException e2) {
                w(e2);
                throw e2;
            } catch (RemoteException e3) {
                w(e3);
            }
        }

        public final void x(Status status) {
            Preconditions.b(!status.Y(), "Failed result must not be success");
            Result f2 = f(status);
            j(f2);
            u(f2);
        }

        @KeepForSdk
        @VisibleForTesting
        protected ApiMethodImpl(@NonNull BasePendingResult.CallbackHandler<R> callbackHandler) {
            super(callbackHandler);
            this.f10563q = new Api.AnyClientKey();
            this.f10564r = null;
        }
    }
}
