package com.facebook.rebound;

/* loaded from: classes.dex */
public class SteppingLooper extends SpringLooper {

    /* renamed from: b, reason: collision with root package name */
    private boolean f10041b;

    /* renamed from: c, reason: collision with root package name */
    private long f10042c;

    @Override // com.facebook.rebound.SpringLooper
    public void b() {
        this.f10041b = true;
        this.f10042c = 0L;
    }

    @Override // com.facebook.rebound.SpringLooper
    public void c() {
        this.f10041b = false;
    }
}
