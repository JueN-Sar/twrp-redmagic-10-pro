package com.google.android.datatransport.runtime.dagger.internal;

/* loaded from: classes.dex */
public final class Preconditions {
    public static void a(Object obj, Class cls) {
        if (obj != null) {
            return;
        }
        throw new IllegalStateException(cls.getCanonicalName() + " must be set");
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
}
