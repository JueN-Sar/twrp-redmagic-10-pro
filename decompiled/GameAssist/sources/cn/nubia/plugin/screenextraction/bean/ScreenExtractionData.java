package cn.nubia.plugin.screenextraction.bean;

import android.graphics.Rect;

/* loaded from: classes.dex */
public class ScreenExtractionData {

    /* renamed from: a, reason: collision with root package name */
    private final String f8574a;

    /* renamed from: b, reason: collision with root package name */
    private final Rect f8575b;

    /* renamed from: c, reason: collision with root package name */
    private final Rect f8576c;

    /* renamed from: d, reason: collision with root package name */
    private final float f8577d;

    /* renamed from: e, reason: collision with root package name */
    private final int f8578e;

    public ScreenExtractionData(String str, Rect rect, Rect rect2, int i2, float f2) {
        this.f8574a = str;
        this.f8575b = rect;
        this.f8576c = rect2;
        this.f8577d = f2;
        this.f8578e = i2;
    }

    public static ScreenExtractionData a(String str, String str2) {
        if (str2 == null || str == null) {
            return null;
        }
        String[] split = str2.split("-");
        if (split.length != 10) {
            return null;
        }
        int[] iArr = new int[8];
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = Integer.valueOf(split[i2].trim()).intValue();
        }
        return new ScreenExtractionData(str, new Rect(iArr[0], iArr[1], iArr[2], iArr[3]), new Rect(iArr[4], iArr[5], iArr[6], iArr[7]), Integer.valueOf(split[9].trim()).intValue(), Float.valueOf(split[8].trim()).floatValue());
    }

    public static ScreenExtractionData j(ScreenExtractionData screenExtractionData) {
        if (screenExtractionData == null) {
            return null;
        }
        return new ScreenExtractionData(screenExtractionData.e(), new Rect(screenExtractionData.h()), new Rect(screenExtractionData.c()), screenExtractionData.d(), screenExtractionData.b());
    }

    public float b() {
        return this.f8577d;
    }

    public Rect c() {
        return this.f8576c;
    }

    public int d() {
        return this.f8578e;
    }

    public String e() {
        return this.f8574a;
    }

    public String f() {
        return (this.f8575b.left + "-" + this.f8575b.top + "-" + this.f8575b.width() + "-" + this.f8575b.height()) + "-" + (this.f8576c.left + "-" + this.f8576c.top + "-" + this.f8576c.width() + "-" + this.f8576c.height()) + "-" + this.f8577d;
    }

    public String g() {
        return this.f8575b.flattenToString().replace(" ", "-").trim() + "-" + this.f8576c.flattenToString().replace(" ", "-").trim() + "-" + this.f8577d + "-" + this.f8578e;
    }

    public Rect h() {
        return this.f8575b;
    }

    public String i() {
        try {
            return this.f8574a.split("@")[0] + "-" + f() + "-" + this.f8578e;
        } catch (Exception unused) {
            return this.f8574a + "-" + this.f() + "-" + this.f8578e;
        }
    }

    public String toString() {
        return this.f8574a + "-" + g();
    }
}
