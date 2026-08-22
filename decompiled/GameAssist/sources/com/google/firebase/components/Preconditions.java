package com.google.firebase.components;

/* loaded from: classes.dex */
public final class Preconditions {
    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static Object b(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    public static Object c(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static void d(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }
}
