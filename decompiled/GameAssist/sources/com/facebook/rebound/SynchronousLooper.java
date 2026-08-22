package com.facebook.rebound;

/* loaded from: classes.dex */
public class SynchronousLooper extends SpringLooper {

    /* renamed from: b, reason: collision with root package name */
    private double f10043b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f10044c;

    @Override // com.facebook.rebound.SpringLooper
    public void b() {
        this.f10044c = true;
        while (!this.f10040a.e() && this.f10044c) {
            this.f10040a.f(this.f10043b);
        }
    }

    @Override // com.facebook.rebound.SpringLooper
    public void c() {
        this.f10044c = false;
    }
}
