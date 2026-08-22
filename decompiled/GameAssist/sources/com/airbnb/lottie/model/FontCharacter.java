package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
public class FontCharacter {

    /* renamed from: a, reason: collision with root package name */
    private final List f9607a;

    /* renamed from: b, reason: collision with root package name */
    private final char f9608b;

    /* renamed from: c, reason: collision with root package name */
    private final double f9609c;

    /* renamed from: d, reason: collision with root package name */
    private final double f9610d;

    /* renamed from: e, reason: collision with root package name */
    private final String f9611e;

    /* renamed from: f, reason: collision with root package name */
    private final String f9612f;

    public FontCharacter(List list, char c2, double d2, double d3, String str, String str2) {
        this.f9607a = list;
        this.f9608b = c2;
        this.f9609c = d2;
        this.f9610d = d3;
        this.f9611e = str;
        this.f9612f = str2;
    }

    public static int c(char c2, String str, String str2) {
        return (((c2 * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List a() {
        return this.f9607a;
    }

    public double b() {
        return this.f9610d;
    }

    public int hashCode() {
        return c(this.f9608b, this.f9612f, this.f9611e);
    }
}
