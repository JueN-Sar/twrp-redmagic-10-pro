package com.google.android.gms.common.images;

/* loaded from: classes.dex */
public final class Size {

    /* renamed from: a, reason: collision with root package name */
    private final int f10929a;

    /* renamed from: b, reason: collision with root package name */
    private final int f10930b;

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.f10929a == size.f10929a && this.f10930b == size.f10930b) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.f10929a;
        return this.f10930b ^ ((i2 >>> 16) | (i2 << 16));
    }

    public String toString() {
        return this.f10929a + "x" + this.f10930b;
    }
}
