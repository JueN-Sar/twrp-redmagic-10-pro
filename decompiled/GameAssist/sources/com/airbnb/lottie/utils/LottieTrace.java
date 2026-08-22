package com.airbnb.lottie.utils;

import androidx.core.os.TraceCompat;

/* loaded from: classes.dex */
public class LottieTrace {

    /* renamed from: a, reason: collision with root package name */
    private final String[] f9918a = new String[5];

    /* renamed from: b, reason: collision with root package name */
    private final long[] f9919b = new long[5];

    /* renamed from: c, reason: collision with root package name */
    private int f9920c = 0;

    /* renamed from: d, reason: collision with root package name */
    private int f9921d = 0;

    public void a(String str) {
        int i2 = this.f9920c;
        if (i2 == 5) {
            this.f9921d++;
            return;
        }
        this.f9918a[i2] = str;
        this.f9919b[i2] = System.nanoTime();
        TraceCompat.a(str);
        this.f9920c++;
    }

    public float b(String str) {
        int i2 = this.f9921d;
        if (i2 > 0) {
            this.f9921d = i2 - 1;
            return 0.0f;
        }
        int i3 = this.f9920c - 1;
        this.f9920c = i3;
        if (i3 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (str.equals(this.f9918a[i3])) {
            TraceCompat.b();
            return (System.nanoTime() - this.f9919b[this.f9920c]) / 1000000.0f;
        }
        throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + this.f9918a[this.f9920c] + ".");
    }
}
