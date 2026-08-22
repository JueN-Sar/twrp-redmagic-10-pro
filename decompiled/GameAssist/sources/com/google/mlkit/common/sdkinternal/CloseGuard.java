package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_common.zzmm;
import com.google.android.gms.internal.mlkit_common.zzmn;
import com.google.android.gms.internal.mlkit_common.zzmv;
import com.google.android.gms.internal.mlkit_common.zzmw;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.mlkit.common.sdkinternal.Cleaner;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
/* loaded from: classes.dex */
public class CloseGuard implements Closeable {

    /* renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f15929c;

    /* renamed from: h, reason: collision with root package name */
    private final String f15930h;

    /* renamed from: i, reason: collision with root package name */
    private final Cleaner.Cleanable f15931i;

    @KeepForSdk
    public static class Factory {

        /* renamed from: a, reason: collision with root package name */
        private final Cleaner f15932a;

        public Factory(Cleaner cleaner) {
            this.f15932a = cleaner;
        }
    }

    final /* synthetic */ void a(int i2, zzsh zzshVar, Runnable runnable) {
        if (!this.f15929c.get()) {
            Log.e("MlKitCloseGuard", String.format(Locale.ENGLISH, "%s has not been closed", this.f15930h));
            zzmw zzmwVar = new zzmw();
            zzmn zzmnVar = new zzmn();
            zzmnVar.b(zzmm.c(i2));
            zzmwVar.h(zzmnVar.c());
            zzshVar.d(zzsk.e(zzmwVar), zzmv.HANDLE_LEAKED);
        }
        runnable.run();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f15929c.set(true);
        this.f15931i.clean();
    }
}
