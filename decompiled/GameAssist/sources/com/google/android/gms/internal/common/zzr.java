package com.google.android.gms.internal.common;

import org.jspecify.nullness.NullMarked;

@NullMarked
/* loaded from: classes.dex */
public final class zzr extends zzp {
    public static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
