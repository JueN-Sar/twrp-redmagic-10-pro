package okio;

import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class PushableTimeout extends Timeout {

    /* renamed from: e, reason: collision with root package name */
    private Timeout f19628e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f19629f;

    /* renamed from: g, reason: collision with root package name */
    private long f19630g;

    /* renamed from: h, reason: collision with root package name */
    private long f19631h;

    void j() {
        this.f19628e.g(this.f19631h, TimeUnit.NANOSECONDS);
        if (this.f19629f) {
            this.f19628e.c(this.f19630g);
        } else {
            this.f19628e.a();
        }
    }

    void k(Timeout timeout) {
        this.f19628e = timeout;
        boolean d2 = timeout.d();
        this.f19629f = d2;
        this.f19630g = d2 ? timeout.b() : -1L;
        long h2 = timeout.h();
        this.f19631h = h2;
        timeout.g(Timeout.e(h2, h()), TimeUnit.NANOSECONDS);
        if (this.f19629f && d()) {
            timeout.c(Math.min(b(), this.f19630g));
        } else if (d()) {
            timeout.c(b());
        }
    }
}
