package com.airbnb.lottie.model;

/* loaded from: classes.dex */
public class Marker {

    /* renamed from: a, reason: collision with root package name */
    private final String f9618a;

    /* renamed from: b, reason: collision with root package name */
    public final float f9619b;

    /* renamed from: c, reason: collision with root package name */
    public final float f9620c;

    public Marker(String str, float f2, float f3) {
        this.f9618a = str;
        this.f9620c = f3;
        this.f9619b = f2;
    }

    public boolean a(String str) {
        if (this.f9618a.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f9618a.endsWith("\r")) {
            String str2 = this.f9618a;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
