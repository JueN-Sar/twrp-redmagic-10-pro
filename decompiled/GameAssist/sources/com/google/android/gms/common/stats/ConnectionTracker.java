package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public class ConnectionTracker {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11237a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static volatile ConnectionTracker f11238b;

    @NonNull
    @VisibleForTesting
    public final ConcurrentHashMap zza = new ConcurrentHashMap();

    private ConnectionTracker() {
    }

    public static ConnectionTracker a() {
        if (f11238b == null) {
            synchronized (f11237a) {
                try {
                    if (f11238b == null) {
                        f11238b = new ConnectionTracker();
                    }
                } finally {
                }
            }
        }
        ConnectionTracker connectionTracker = f11238b;
        Preconditions.i(connectionTracker);
        return connectionTracker;
    }

    private static void d(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused) {
        }
    }

    private final boolean e(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i2, boolean z, Executor executor) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            String packageName = component.getPackageName();
            "com.google.android.gms".equals(packageName);
            try {
                if ((Wrappers.a(context).a(packageName, 0).flags & WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_EXCLUDE_FROM_SCREEN_MAGNIFICATION) != 0) {
                    Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
                    return false;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (!f(serviceConnection)) {
            return g(context, intent, serviceConnection, i2, executor);
        }
        ServiceConnection serviceConnection2 = (ServiceConnection) this.zza.putIfAbsent(serviceConnection, serviceConnection);
        if (serviceConnection2 != null && serviceConnection != serviceConnection2) {
            Log.w("ConnectionTracker", String.format("Duplicate binding with the same ServiceConnection: %s, %s, %s.", serviceConnection, str, intent.getAction()));
        }
        try {
            boolean g2 = g(context, intent, serviceConnection, i2, executor);
            if (g2) {
                return g2;
            }
            return false;
        } finally {
            this.zza.remove(serviceConnection, serviceConnection);
        }
    }

    private static boolean f(ServiceConnection serviceConnection) {
        return !(serviceConnection instanceof zzt);
    }

    private static final boolean g(Context context, Intent intent, ServiceConnection serviceConnection, int i2, Executor executor) {
        if (executor == null) {
            executor = null;
        }
        return (!PlatformVersion.h() || executor == null) ? context.bindService(intent, serviceConnection, i2) : context.bindService(intent, i2, executor, serviceConnection);
    }

    public void b(Context context, ServiceConnection serviceConnection) {
        if (!f(serviceConnection) || !this.zza.containsKey(serviceConnection)) {
            d(context, serviceConnection);
            return;
        }
        try {
            d(context, (ServiceConnection) this.zza.get(serviceConnection));
        } finally {
            this.zza.remove(serviceConnection);
        }
    }

    public final boolean c(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i2, Executor executor) {
        return e(context, str, intent, serviceConnection, 4225, true, executor);
    }
}
