package com.google.android.gms.tasks;

import java.util.concurrent.ExecutionException;

/* loaded from: classes.dex */
final class zzaf<T> implements zzae<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f13677a;

    /* renamed from: b, reason: collision with root package name */
    private final int f13678b;

    /* renamed from: c, reason: collision with root package name */
    private final zzw f13679c;

    /* renamed from: d, reason: collision with root package name */
    private int f13680d;

    /* renamed from: e, reason: collision with root package name */
    private int f13681e;

    /* renamed from: f, reason: collision with root package name */
    private int f13682f;

    /* renamed from: g, reason: collision with root package name */
    private Exception f13683g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f13684h;

    private final void c() {
        if (this.f13680d + this.f13681e + this.f13682f == this.f13678b) {
            if (this.f13683g == null) {
                if (this.f13684h) {
                    this.f13679c.p();
                    return;
                } else {
                    this.f13679c.o(null);
                    return;
                }
            }
            this.f13679c.n(new ExecutionException(this.f13681e + " out of " + this.f13678b + " underlying tasks failed", this.f13683g));
        }
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void a(Object obj) {
        synchronized (this.f13677a) {
            this.f13680d++;
            c();
        }
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void b() {
        synchronized (this.f13677a) {
            this.f13682f++;
            this.f13684h = true;
            c();
        }
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void d(Exception exc) {
        synchronized (this.f13677a) {
            this.f13681e++;
            this.f13683g = exc;
            c();
        }
    }
}
