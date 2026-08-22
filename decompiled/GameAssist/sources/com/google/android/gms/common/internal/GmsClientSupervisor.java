package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class GmsClientSupervisor {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11002a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static zzs f11003b = null;

    /* renamed from: c, reason: collision with root package name */
    private static Executor f11004c = null;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f11005d = false;

    @Nullable
    @VisibleForTesting
    static HandlerThread zza;

    public static int a() {
        return 4225;
    }

    public static GmsClientSupervisor b(Context context) {
        synchronized (f11002a) {
            try {
                if (f11003b == null) {
                    f11003b = new zzs(context.getApplicationContext(), f11005d ? c().getLooper() : context.getMainLooper(), f11004c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f11003b;
    }

    public static HandlerThread c() {
        synchronized (f11002a) {
            try {
                HandlerThread handlerThread = zza;
                if (handlerThread != null) {
                    return handlerThread;
                }
                HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", 9);
                zza = handlerThread2;
                handlerThread2.start();
                return zza;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected abstract void d(zzo zzoVar, ServiceConnection serviceConnection, String str);

    public final void e(String str, String str2, int i2, ServiceConnection serviceConnection, String str3, boolean z) {
        d(new zzo(str, str2, 4225, z), serviceConnection, str3);
    }

    protected abstract boolean f(zzo zzoVar, ServiceConnection serviceConnection, String str, Executor executor);
}
