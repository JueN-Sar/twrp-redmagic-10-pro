package okio;

import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ForwardingTimeout extends Timeout {

    /* renamed from: e, reason: collision with root package name */
    private Timeout f19586e;

    @Override // okio.Timeout
    public Timeout a() {
        return this.f19586e.a();
    }

    @Override // okio.Timeout
    public long b() {
        return this.f19586e.b();
    }

    @Override // okio.Timeout
    public Timeout c(long j2) {
        return this.f19586e.c(j2);
    }

    @Override // okio.Timeout
    public boolean d() {
        return this.f19586e.d();
    }

    @Override // okio.Timeout
    public void f() {
        this.f19586e.f();
    }

    @Override // okio.Timeout
    public Timeout g(long j2, TimeUnit timeUnit) {
        return this.f19586e.g(j2, timeUnit);
    }

    @Override // okio.Timeout
    public long h() {
        return this.f19586e.h();
    }
}
