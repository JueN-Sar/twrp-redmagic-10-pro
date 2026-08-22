package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public abstract class zbtc implements Iterable, Serializable {
    public static final zbtc zbb = new zbtb(zbuo.f12985b);
    private int zba = 0;

    static {
        int i2 = zbsm.f12933a;
    }

    zbtc() {
    }

    static int j(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if ((i2 | i3 | i5 | (i4 - i3)) >= 0) {
            return i5;
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i2 + " < 0");
        }
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i2 + ", " + i3);
        }
        throw new IndexOutOfBoundsException("End index: " + i3 + " >= " + i4);
    }

    public static zbtc l(byte[] bArr, int i2, int i3) {
        j(i2, i2 + i3, bArr.length);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return new zbtb(bArr2);
    }

    public abstract byte b(int i2);

    abstract byte d(int i2);

    public abstract boolean equals(Object obj);

    public abstract int f();

    protected abstract int g(int i2, int i3, int i4);

    public abstract zbtc h(int i2, int i3);

    public final int hashCode() {
        int i2 = this.zba;
        if (i2 == 0) {
            int f2 = f();
            i2 = g(f2, 0, f2);
            if (i2 == 0) {
                i2 = 1;
            }
            this.zba = i2;
        }
        return i2;
    }

    abstract void i(zbst zbstVar);

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zbsu(this);
    }

    protected final int k() {
        return this.zba;
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(f()), f() <= 50 ? zbwj.a(this) : zbwj.a(h(0, 47)).concat("..."));
    }
}
