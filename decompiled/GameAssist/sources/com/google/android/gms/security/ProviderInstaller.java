package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ProviderInstaller {

    /* renamed from: a, reason: collision with root package name */
    private static final GoogleApiAvailabilityLight f13634a = GoogleApiAvailabilityLight.h();

    /* renamed from: b, reason: collision with root package name */
    private static final Object f13635b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static Method f13636c = null;

    /* renamed from: d, reason: collision with root package name */
    private static Method f13637d = null;

    public interface ProviderInstallListener {
        void a();

        void b(int i2, Intent intent);
    }

    public static void a(Context context) {
        Context context2;
        Preconditions.j(context, "Context must not be null");
        f13634a.n(context, 11925000);
        synchronized (f13635b) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                context2 = DynamiteModule.e(context, DynamiteModule.f11344f, "com.google.android.gms.providerinstaller.dynamite").b();
            } catch (DynamiteModule.LoadingException e2) {
                Log.w("ProviderInstaller", "Failed to load providerinstaller module: ".concat(String.valueOf(e2.getMessage())));
                context2 = null;
            }
            if (context2 != null) {
                d(context2, context, "com.google.android.gms.providerinstaller.ProviderInstallerImpl");
                return;
            }
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            Context e3 = GooglePlayServicesUtilLight.e(context);
            if (e3 != null) {
                try {
                    if (f13637d == null) {
                        Class cls = Long.TYPE;
                        f13637d = c(e3, "com.google.android.gms.common.security.ProviderInstallerImpl", "reportRequestStats", new Class[]{Context.class, cls, cls});
                    }
                    f13637d.invoke(null, context, Long.valueOf(elapsedRealtime), Long.valueOf(elapsedRealtime2));
                } catch (Exception e4) {
                    Log.w("ProviderInstaller", "Failed to report request stats: ".concat(String.valueOf(e4.getMessage())));
                }
            }
            if (e3 != null) {
                d(e3, context, "com.google.android.gms.common.security.ProviderInstallerImpl");
            } else {
                Log.e("ProviderInstaller", "Failed to get remote context");
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    private static Method c(Context context, String str, String str2, Class[] clsArr) {
        return context.getClassLoader().loadClass(str).getMethod(str2, clsArr);
    }

    private static void d(Context context, Context context2, String str) {
        try {
            if (f13636c == null) {
                f13636c = c(context, str, "insertProvider", new Class[]{Context.class});
            }
            f13636c.invoke(null, context);
        } catch (Exception e2) {
            Throwable cause = e2.getCause();
            if (Log.isLoggable("ProviderInstaller", 6)) {
                Log.e("ProviderInstaller", "Failed to install provider: ".concat(String.valueOf(cause == null ? e2.getMessage() : cause.getMessage())));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
