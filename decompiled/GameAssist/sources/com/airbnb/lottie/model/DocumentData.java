package com.airbnb.lottie.model;

import android.graphics.PointF;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class DocumentData {

    /* renamed from: a, reason: collision with root package name */
    public String f9589a;

    /* renamed from: b, reason: collision with root package name */
    public String f9590b;

    /* renamed from: c, reason: collision with root package name */
    public float f9591c;

    /* renamed from: d, reason: collision with root package name */
    public Justification f9592d;

    /* renamed from: e, reason: collision with root package name */
    public int f9593e;

    /* renamed from: f, reason: collision with root package name */
    public float f9594f;

    /* renamed from: g, reason: collision with root package name */
    public float f9595g;

    /* renamed from: h, reason: collision with root package name */
    public int f9596h;

    /* renamed from: i, reason: collision with root package name */
    public int f9597i;

    /* renamed from: j, reason: collision with root package name */
    public float f9598j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f9599k;

    /* renamed from: l, reason: collision with root package name */
    public PointF f9600l;

    /* renamed from: m, reason: collision with root package name */
    public PointF f9601m;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, float f2, Justification justification, int i2, float f3, float f4, int i3, int i4, float f5, boolean z, PointF pointF, PointF pointF2) {
        a(str, str2, f2, justification, i2, f3, f4, i3, i4, f5, z, pointF, pointF2);
    }

    public void a(String str, String str2, float f2, Justification justification, int i2, float f3, float f4, int i3, int i4, float f5, boolean z, PointF pointF, PointF pointF2) {
        this.f9589a = str;
        this.f9590b = str2;
        this.f9591c = f2;
        this.f9592d = justification;
        this.f9593e = i2;
        this.f9594f = f3;
        this.f9595g = f4;
        this.f9596h = i3;
        this.f9597i = i4;
        this.f9598j = f5;
        this.f9599k = z;
        this.f9600l = pointF;
        this.f9601m = pointF2;
    }

    public int hashCode() {
        int hashCode = (((((int) ((((this.f9589a.hashCode() * 31) + this.f9590b.hashCode()) * 31) + this.f9591c)) * 31) + this.f9592d.ordinal()) * 31) + this.f9593e;
        long floatToRawIntBits = Float.floatToRawIntBits(this.f9594f);
        return (((hashCode * 31) + ((int) (floatToRawIntBits ^ (floatToRawIntBits >>> 32)))) * 31) + this.f9596h;
    }

    public DocumentData() {
    }
}
