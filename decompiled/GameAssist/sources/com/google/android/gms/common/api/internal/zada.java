package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class zada<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {

    /* renamed from: a, reason: collision with root package name */
    private ResultTransform f10824a;

    /* renamed from: b, reason: collision with root package name */
    private zada f10825b;

    /* renamed from: c, reason: collision with root package name */
    private volatile ResultCallbacks f10826c;

    /* renamed from: d, reason: collision with root package name */
    private PendingResult f10827d;

    /* renamed from: e, reason: collision with root package name */
    private final Object f10828e;

    /* renamed from: f, reason: collision with root package name */
    private Status f10829f;

    /* renamed from: g, reason: collision with root package name */
    private final WeakReference f10830g;

    /* renamed from: h, reason: collision with root package name */
    private final zacz f10831h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f10832i;

    /* JADX INFO: Access modifiers changed from: private */
    public final void k(Status status) {
        synchronized (this.f10828e) {
            this.f10829f = status;
            m(status);
        }
    }

    private final void l() {
        if (this.f10824a == null && this.f10826c == null) {
            return;
        }
        GoogleApiClient googleApiClient = (GoogleApiClient) this.f10830g.get();
        if (!this.f10832i && this.f10824a != null && googleApiClient != null) {
            googleApiClient.j(this);
            this.f10832i = true;
        }
        Status status = this.f10829f;
        if (status != null) {
            m(status);
            return;
        }
        PendingResult pendingResult = this.f10827d;
        if (pendingResult != null) {
            pendingResult.e(this);
        }
    }

    private final void m(Status status) {
        synchronized (this.f10828e) {
            try {
                ResultTransform resultTransform = this.f10824a;
                if (resultTransform != null) {
                    ((zada) Preconditions.i(this.f10825b)).k((Status) Preconditions.j(resultTransform.a(status), "onFailure must not return null"));
                } else if (n()) {
                    ((ResultCallbacks) Preconditions.i(this.f10826c)).b(status);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final boolean n() {
        return (this.f10826c == null || ((GoogleApiClient) this.f10830g.get()) == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void o(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e2) {
                Log.w("TransformedResultImpl", "Unable to release ".concat(String.valueOf(result)), e2);
            }
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void a(Result result) {
        synchronized (this.f10828e) {
            try {
                if (!result.a().Y()) {
                    k(result.a());
                    o(result);
                } else if (this.f10824a != null) {
                    zaco.a().submit(new zacy(this, result));
                } else if (n()) {
                    ((ResultCallbacks) Preconditions.i(this.f10826c)).c(result);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    final void i() {
        this.f10826c = null;
    }

    public final void j(PendingResult pendingResult) {
        synchronized (this.f10828e) {
            this.f10827d = pendingResult;
            l();
        }
    }
}
