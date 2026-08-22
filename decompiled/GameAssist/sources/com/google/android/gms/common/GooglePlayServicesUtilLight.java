package com.google.android.gms.common;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public class GooglePlayServicesUtilLight {

    /* renamed from: a, reason: collision with root package name */
    public static final int f10505a = 12451000;

    /* renamed from: b, reason: collision with root package name */
    static final AtomicBoolean f10506b = new AtomicBoolean();

    /* renamed from: c, reason: collision with root package name */
    private static final AtomicBoolean f10507c = new AtomicBoolean();

    @VisibleForTesting
    static boolean zza = false;

    public static void a(Context context) {
        if (f10506b.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(10436);
            }
        } catch (SecurityException e2) {
            Log.d("GooglePlayServicesUtil", "Suppressing Security Exception %s in cancelAvailabilityErrorNotifications.", e2);
        }
    }

    public static void b(Context context, int i2) {
        int j2 = GoogleApiAvailabilityLight.h().j(context, i2);
        if (j2 != 0) {
            Intent d2 = GoogleApiAvailabilityLight.h().d(context, j2, DistBusKeys.KEY_PHYSICAL_TYPE);
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + j2);
            if (d2 != null) {
                throw new GooglePlayServicesRepairableException(j2, "Google Play Services not available", d2);
            }
            throw new GooglePlayServicesNotAvailableException(j2);
        }
    }

    public static int c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    public static String d(int i2) {
        return ConnectionResult.Y(i2);
    }

    public static Context e(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static Resources f(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static int g(Context context, int i2) {
        PackageInfo packageInfo;
        try {
            context.getResources().getString(R.string.common_google_play_services_unknown_issue);
        } catch (Throwable unused) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName()) && !f10507c.get()) {
            int a2 = zzah.a(context);
            if (a2 == 0) {
                throw new GooglePlayServicesMissingManifestValueException();
            }
            if (a2 != f10505a) {
                throw new GooglePlayServicesIncorrectManifestValueException(a2);
            }
        }
        boolean z = (DeviceProperties.c(context) || DeviceProperties.e(context)) ? false : true;
        Preconditions.a(i2 >= 0);
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (PackageManager.NameNotFoundException unused2) {
                Log.w("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires the Google Play Store, but it is missing."));
            }
        } else {
            packageInfo = null;
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            GoogleSignatureVerifier.a(context);
            if (GoogleSignatureVerifier.c(packageInfo2, true)) {
                if (z) {
                    Preconditions.i(packageInfo);
                    if (!GoogleSignatureVerifier.c(packageInfo, true)) {
                        Log.w("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play Store, but its signature is invalid."));
                    }
                }
                if (!z || packageInfo == null || packageInfo.signatures[0].equals(packageInfo2.signatures[0])) {
                    if (com.google.android.gms.common.util.zza.a(packageInfo2.versionCode) >= com.google.android.gms.common.util.zza.a(i2)) {
                        ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                        if (applicationInfo == null) {
                            try {
                                applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                            } catch (PackageManager.NameNotFoundException e2) {
                                Log.wtf("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play services, but they're missing when getting application info."), e2);
                                return 1;
                            }
                        }
                        return !applicationInfo.enabled ? 3 : 0;
                    }
                    Log.w("GooglePlayServicesUtil", "Google Play services out of date for " + packageName + ".  Requires " + i2 + " but found " + packageInfo2.versionCode);
                    return 2;
                }
                Log.w("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play Store, but its signature doesn't match that of Google Play services."));
            } else {
                Log.w("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play services, but their signature is invalid."));
            }
            return 9;
        } catch (PackageManager.NameNotFoundException unused3) {
            Log.w("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play services, but they are missing."));
            return 1;
        }
    }

    public static boolean h(Context context, int i2) {
        if (i2 == 18) {
            return true;
        }
        if (i2 == 1) {
            return k(context, "com.google.android.gms");
        }
        return false;
    }

    public static boolean i(Context context) {
        if (!PlatformVersion.b()) {
            return false;
        }
        Object systemService = context.getSystemService("user");
        Preconditions.i(systemService);
        Bundle applicationRestrictions = ((UserManager) systemService).getApplicationRestrictions(context.getPackageName());
        return applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"));
    }

    public static boolean j(int i2) {
        return i2 == 1 || i2 == 2 || i2 == 3 || i2 == 9;
    }

    static boolean k(Context context, String str) {
        ApplicationInfo applicationInfo;
        boolean equals = str.equals("com.google.android.gms");
        if (PlatformVersion.d()) {
            try {
                Iterator<PackageInstaller.SessionInfo> it = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
                while (it.hasNext()) {
                    if (str.equals(it.next().getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception unused) {
                return false;
            }
        }
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        return equals ? applicationInfo.enabled : applicationInfo.enabled && !i(context);
    }
}
