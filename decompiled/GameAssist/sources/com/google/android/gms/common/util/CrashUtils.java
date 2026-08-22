package com.google.android.gms.common.util;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes.dex */
public final class CrashUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f11254a = {"android.", "com.android.", "dalvik.", "java.", "javax."};

    public static boolean a(Context context, Throwable th) {
        try {
            Preconditions.i(context);
            Preconditions.i(th);
            return false;
        } catch (Exception e2) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e2);
            return false;
        }
    }
}
