package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
@KeepName
/* loaded from: classes.dex */
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {

    /* renamed from: p, reason: collision with root package name */
    static final ThreadLocal f10565p = new zaq();

    /* renamed from: a, reason: collision with root package name */
    private final Object f10566a;

    /* renamed from: b, reason: collision with root package name */
    protected final CallbackHandler f10567b;

    /* renamed from: c, reason: collision with root package name */
    protected final WeakReference f10568c;

    /* renamed from: d, reason: collision with root package name */
    private final CountDownLatch f10569d;

    /* renamed from: e, reason: collision with root package name */
    private final ArrayList f10570e;

    /* renamed from: f, reason: collision with root package name */
    private ResultCallback f10571f;

    /* renamed from: g, reason: collision with root package name */
    private final AtomicReference f10572g;

    /* renamed from: h, reason: collision with root package name */
    private Result f10573h;

    /* renamed from: i, reason: collision with root package name */
    private Status f10574i;

    /* renamed from: j, reason: collision with root package name */
    private volatile boolean f10575j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f10576k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f10577l;

    /* renamed from: m, reason: collision with root package name */
    private ICancelToken f10578m;

    /* renamed from: n, reason: collision with root package name */
    private volatile zada f10579n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f10580o;

    @KeepName
    private zas resultGuardian;

    protected BasePendingResult(GoogleApiClient googleApiClient) {
        this.f10566a = new Object();
        this.f10569d = new CountDownLatch(1);
        this.f10570e = new ArrayList();
        this.f10572g = new AtomicReference();
        this.f10580o = false;
        this.f10567b = new CallbackHandler(googleApiClient != null ? googleApiClient.h() : Looper.getMainLooper());
        this.f10568c = new WeakReference(googleApiClient);
    }

    private final Result k() {
        Result result;
        synchronized (this.f10566a) {
            Preconditions.m(!this.f10575j, "Result has already been consumed.");
            Preconditions.m(i(), "Result is not ready.");
            result = this.f10573h;
            this.f10573h = null;
            this.f10571f = null;
            this.f10575j = true;
        }
        zadb zadbVar = (zadb) this.f10572g.getAndSet(null);
        if (zadbVar != null) {
            zadbVar.f10833a.zab.remove(this);
        }
        return (Result) Preconditions.i(result);
    }

    private final void l(Result result) {
        this.f10573h = result;
        this.f10574i = result.a();
        zar zarVar = null;
        this.f10578m = null;
        this.f10569d.countDown();
        if (this.f10576k) {
            this.f10571f = null;
        } else {
            ResultCallback resultCallback = this.f10571f;
            if (resultCallback != null) {
                this.f10567b.removeMessages(2);
                this.f10567b.zaa(resultCallback, k());
            } else if (this.f10573h instanceof Releasable) {
                this.resultGuardian = new zas(this, zarVar);
            }
        }
        ArrayList arrayList = this.f10570e;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((PendingResult.StatusListener) arrayList.get(i2)).a(this.f10574i);
        }
        this.f10570e.clear();
    }

    public static void o(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e2) {
                Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(result)), e2);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void b(PendingResult.StatusListener statusListener) {
        Preconditions.b(statusListener != null, "Callback cannot be null.");
        synchronized (this.f10566a) {
            try {
                if (i()) {
                    statusListener.a(this.f10574i);
                } else {
                    this.f10570e.add(statusListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final Result c(long j2, TimeUnit timeUnit) {
        if (j2 > 0) {
            Preconditions.h("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.m(!this.f10575j, "Result has already been consumed.");
        Preconditions.m(this.f10579n == null, "Cannot await if then() has been called.");
        try {
            if (!this.f10569d.await(j2, timeUnit)) {
                g(Status.f10546o);
            }
        } catch (InterruptedException unused) {
            g(Status.f10544m);
        }
        Preconditions.m(i(), "Result is not ready.");
        return k();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public void d() {
        synchronized (this.f10566a) {
            if (!this.f10576k && !this.f10575j) {
                ICancelToken iCancelToken = this.f10578m;
                if (iCancelToken != null) {
                    try {
                        iCancelToken.cancel();
                    } catch (RemoteException unused) {
                    }
                }
                o(this.f10573h);
                this.f10576k = true;
                l(f(Status.f10547p));
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void e(ResultCallback resultCallback) {
        synchronized (this.f10566a) {
            try {
                if (resultCallback == null) {
                    this.f10571f = null;
                    return;
                }
                boolean z = true;
                Preconditions.m(!this.f10575j, "Result has already been consumed.");
                if (this.f10579n != null) {
                    z = false;
                }
                Preconditions.m(z, "Cannot set callbacks if then() has been called.");
                if (h()) {
                    return;
                }
                if (i()) {
                    this.f10567b.zaa(resultCallback, k());
                } else {
                    this.f10571f = resultCallback;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected abstract Result f(Status status);

    public final void g(Status status) {
        synchronized (this.f10566a) {
            try {
                if (!i()) {
                    j(f(status));
                    this.f10577l = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean h() {
        boolean z;
        synchronized (this.f10566a) {
            z = this.f10576k;
        }
        return z;
    }

    public final boolean i() {
        return this.f10569d.getCount() == 0;
    }

    public final void j(Result result) {
        synchronized (this.f10566a) {
            try {
                if (this.f10577l || this.f10576k) {
                    o(result);
                    return;
                }
                i();
                Preconditions.m(!i(), "Results have already been set");
                Preconditions.m(!this.f10575j, "Result has already been consumed");
                l(result);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void n() {
        boolean z = true;
        if (!this.f10580o && !((Boolean) f10565p.get()).booleanValue()) {
            z = false;
        }
        this.f10580o = z;
    }

    public final boolean p() {
        boolean h2;
        synchronized (this.f10566a) {
            try {
                if (((GoogleApiClient) this.f10568c.get()) != null) {
                    if (!this.f10580o) {
                    }
                    h2 = h();
                }
                d();
                h2 = h();
            } catch (Throwable th) {
                throw th;
            }
        }
        return h2;
    }

    public final void q(zadb zadbVar) {
        this.f10572g.set(zadbVar);
    }

    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends com.google.android.gms.internal.base.zau {
        public CallbackHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public final void handleMessage(@NonNull Message message) {
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 == 2) {
                    ((BasePendingResult) message.obj).g(Status.f10546o);
                    return;
                }
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + i2, new Exception());
                return;
            }
            Pair pair = (Pair) message.obj;
            ResultCallback resultCallback = (ResultCallback) pair.first;
            Result result = (Result) pair.second;
            try {
                resultCallback.a(result);
            } catch (RuntimeException e2) {
                BasePendingResult.o(result);
                throw e2;
            }
        }

        public final void zaa(@NonNull ResultCallback resultCallback, @NonNull Result result) {
            ThreadLocal threadLocal = BasePendingResult.f10565p;
            sendMessage(obtainMessage(1, new Pair((ResultCallback) Preconditions.i(resultCallback), result)));
        }

        public CallbackHandler(@NonNull Looper looper) {
            super(looper);
        }
    }

    @KeepForSdk
    @VisibleForTesting
    protected BasePendingResult(@NonNull CallbackHandler<R> callbackHandler) {
        this.f10566a = new Object();
        this.f10569d = new CountDownLatch(1);
        this.f10570e = new ArrayList();
        this.f10572g = new AtomicReference();
        this.f10580o = false;
        this.f10567b = (CallbackHandler) Preconditions.j(callbackHandler, "CallbackHandler must not be null");
        this.f10568c = new WeakReference(null);
    }
}
