package com.airbnb.lottie.model;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class Font {

    /* renamed from: a, reason: collision with root package name */
    private final String f9602a;

    /* renamed from: b, reason: collision with root package name */
    private final String f9603b;

    /* renamed from: c, reason: collision with root package name */
    private final String f9604c;

    /* renamed from: d, reason: collision with root package name */
    private final float f9605d;

    /* renamed from: e, reason: collision with root package name */
    private Typeface f9606e;

    public Font(String str, String str2, String str3, float f2) {
        this.f9602a = str;
        this.f9603b = str2;
        this.f9604c = str3;
        this.f9605d = f2;
    }

    public String a() {
        return this.f9602a;
    }

    public String b() {
        return this.f9603b;
    }

    public String c() {
        return this.f9604c;
    }

    public Typeface d() {
        return this.f9606e;
    }

    public void e(Typeface typeface) {
        this.f9606e = typeface;
    }
}
