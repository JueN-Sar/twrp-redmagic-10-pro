package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzw<TResult> extends Task<TResult> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f13726a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private final zzr f13727b = new zzr();

    /* renamed from: c, reason: collision with root package name */
    private boolean f13728c;

    /* renamed from: d, reason: collision with root package name */
    private volatile boolean f13729d;

    /* renamed from: e, reason: collision with root package name */
    private Object f13730e;

    /* renamed from: f, reason: collision with root package name */
    private Exception f13731f;

    zzw() {
    }

    private final void s() {
        Preconditions.m(this.f13728c, "Task is not yet complete");
    }

    private final void t() {
        if (this.f13729d) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void u() {
        if (this.f13728c) {
            throw DuplicateTaskCompletionException.a(this);
        }
    }

    private final void v() {
        synchronized (this.f13726a) {
            try {
                if (this.f13728c) {
                    this.f13727b.b(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task a(Executor executor, OnCanceledListener onCanceledListener) {
        this.f13727b.a(new zzh(executor, onCanceledListener));
        v();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task b(OnCompleteListener onCompleteListener) {
        this.f13727b.a(new zzj(TaskExecutors.f13671a, onCompleteListener));
        v();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task c(Executor executor, OnCompleteListener onCompleteListener) {
        this.f13727b.a(new zzj(executor, onCompleteListener));
        v();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task d(OnFailureListener onFailureListener) {
        e(TaskExecutors.f13671a, onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task e(Executor executor, OnFailureListener onFailureListener) {
        this.f13727b.a(new zzl(executor, onFailureListener));
        v();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task f(OnSuccessListener onSuccessListener) {
        g(TaskExecutors.f13671a, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task g(Executor executor, OnSuccessListener onSuccessListener) {
        this.f13727b.a(new zzn(executor, onSuccessListener));
        v();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Exception h() {
        Exception exc;
        synchronized (this.f13726a) {
            exc = this.f13731f;
        }
        return exc;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Object i() {
        Object obj;
        synchronized (this.f13726a) {
            try {
                s();
                t();
                Exception exc = this.f13731f;
                if (exc != null) {
                    throw new RuntimeExecutionException(exc);
                }
                obj = this.f13730e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean j() {
        return this.f13729d;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean k() {
        boolean z;
        synchronized (this.f13726a) {
            z = this.f13728c;
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean l() {
        boolean z;
        synchronized (this.f13726a) {
            try {
                z = false;
                if (this.f13728c && !this.f13729d && this.f13731f == null) {
                    z = true;
                }
            } finally {
            }
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task m(SuccessContinuation successContinuation) {
        Executor executor = TaskExecutors.f13671a;
        zzw zzwVar = new zzw();
        this.f13727b.a(new zzp(executor, successContinuation, zzwVar));
        v();
        return zzwVar;
    }

    public final void n(Exception exc) {
        Preconditions.j(exc, "Exception must not be null");
        synchronized (this.f13726a) {
            u();
            this.f13728c = true;
            this.f13731f = exc;
        }
        this.f13727b.b(this);
    }

    public final void o(Object obj) {
        synchronized (this.f13726a) {
            u();
            this.f13728c = true;
            this.f13730e = obj;
        }
        this.f13727b.b(this);
    }

    public final boolean p() {
        synchronized (this.f13726a) {
            try {
                if (this.f13728c) {
                    return false;
                }
                this.f13728c = true;
                this.f13729d = true;
                this.f13727b.b(this);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean q(Exception exc) {
        Preconditions.j(exc, "Exception must not be null");
        synchronized (this.f13726a) {
            try {
                if (this.f13728c) {
                    return false;
                }
                this.f13728c = true;
                this.f13731f = exc;
                this.f13727b.b(this);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean r(Object obj) {
        synchronized (this.f13726a) {
            try {
                if (this.f13728c) {
                    return false;
                }
                this.f13728c = true;
                this.f13730e = obj;
                this.f13727b.b(this);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
