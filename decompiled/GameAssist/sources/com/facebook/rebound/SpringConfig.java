package com.facebook.rebound;

/* loaded from: classes.dex */
public class SpringConfig {

    /* renamed from: c, reason: collision with root package name */
    public static SpringConfig f10035c = a(40.0d, 7.0d);

    /* renamed from: a, reason: collision with root package name */
    public double f10036a;

    /* renamed from: b, reason: collision with root package name */
    public double f10037b;

    public SpringConfig(double d2, double d3) {
        this.f10037b = d2;
        this.f10036a = d3;
    }

    public static SpringConfig a(double d2, double d3) {
        return new SpringConfig(OrigamiValueConverter.d(d2), OrigamiValueConverter.a(d3));
    }
}
