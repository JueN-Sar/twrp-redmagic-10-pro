package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class zbki<T> implements Serializable {
    zbki() {
    }

    public static zbki d() {
        return zbjy.zba;
    }

    public static zbki e(Object obj) {
        obj.getClass();
        return new zbkk(obj);
    }

    public abstract Object a();

    public abstract Object b(Object obj);

    public abstract boolean c();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();
}
