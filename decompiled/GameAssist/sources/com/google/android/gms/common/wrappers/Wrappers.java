package com.google.android.gms.common.wrappers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class Wrappers {

    /* renamed from: b, reason: collision with root package name */
    private static final Wrappers f11289b = new Wrappers();

    /* renamed from: a, reason: collision with root package name */
    private PackageManagerWrapper f11290a = null;

    public static PackageManagerWrapper a(Context context) {
        return f11289b.zza(context);
    }

    @NonNull
    @VisibleForTesting
    public final synchronized PackageManagerWrapper zza(@NonNull Context context) {
        try {
            if (this.f11290a == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                this.f11290a = new PackageManagerWrapper(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f11290a;
    }
}
