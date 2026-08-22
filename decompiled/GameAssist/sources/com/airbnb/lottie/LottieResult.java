package com.airbnb.lottie;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class LottieResult<V> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f9324a;

    /* renamed from: b, reason: collision with root package name */
    private final Throwable f9325b;

    public LottieResult(Object obj) {
        this.f9324a = obj;
        this.f9325b = null;
    }

    public Throwable a() {
        return this.f9325b;
    }

    public Object b() {
        return this.f9324a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottieResult)) {
            return false;
        }
        LottieResult lottieResult = (LottieResult) obj;
        if (b() != null && b().equals(lottieResult.b())) {
            return true;
        }
        if (a() == null || lottieResult.a() == null) {
            return false;
        }
        return a().toString().equals(a().toString());
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{b(), a()});
    }

    public LottieResult(Throwable th) {
        this.f9325b = th;
        this.f9324a = null;
    }
}
