package com.google.android.gms.tasks;

/* loaded from: classes.dex */
public class TaskCompletionSource<TResult> {

    /* renamed from: a, reason: collision with root package name */
    private final zzw f13670a = new zzw();

    public TaskCompletionSource() {
    }

    public Task a() {
        return this.f13670a;
    }

    public void b(Exception exc) {
        this.f13670a.n(exc);
    }

    public void c(Object obj) {
        this.f13670a.o(obj);
    }

    public boolean d(Exception exc) {
        return this.f13670a.q(exc);
    }

    public boolean e(Object obj) {
        return this.f13670a.r(obj);
    }

    public TaskCompletionSource(CancellationToken cancellationToken) {
        cancellationToken.b(new zzs(this));
    }
}
