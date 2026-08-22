package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbcr {

    /* renamed from: a, reason: collision with root package name */
    private final int f12751a;

    /* renamed from: b, reason: collision with root package name */
    private final int f12752b;

    public zbcr(int i2, int i3) {
        zbkj.c(i2 < 32767 && i2 >= 0);
        zbkj.c(i3 < 32767 && i3 >= 0);
        this.f12751a = i2;
        this.f12752b = i3;
    }

    public final int a() {
        return this.f12752b;
    }

    public final int b() {
        return this.f12751a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zbcr) {
            zbcr zbcrVar = (zbcr) obj;
            if (this.f12751a == zbcrVar.f12751a && this.f12752b == zbcrVar.f12752b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f12752b | (this.f12751a << 16);
    }

    public final String toString() {
        return this.f12751a + "x" + this.f12752b;
    }
}
