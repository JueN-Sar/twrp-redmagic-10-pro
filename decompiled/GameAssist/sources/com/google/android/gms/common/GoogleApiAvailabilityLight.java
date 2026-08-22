package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public class GoogleApiAvailabilityLight {

    /* renamed from: a, reason: collision with root package name */
    public static final int f10502a = GooglePlayServicesUtilLight.f10505a;

    /* renamed from: b, reason: collision with root package name */
    private static final GoogleApiAvailabilityLight f10503b = new GoogleApiAvailabilityLight();

    GoogleApiAvailabilityLight() {
    }

    public static GoogleApiAvailabilityLight h() {
        return f10503b;
    }

    public void a(Context context) {
        GooglePlayServicesUtilLight.a(context);
    }

    public int b(Context context) {
        return GooglePlayServicesUtilLight.c(context);
    }

    public Intent c(int i2) {
        return d(null, i2, null);
    }

    public Intent d(Context context, int i2, String str) {
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return null;
            }
            Uri fromParts = Uri.fromParts("package", "com.google.android.gms", null);
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(fromParts);
            return intent;
        }
        if (context != null && DeviceProperties.c(context)) {
            Intent intent2 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
            intent2.setPackage("com.google.android.wearable.app");
            return intent2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(f10502a);
        sb.append("-");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        if (context != null) {
            try {
                sb.append(Wrappers.a(context).c(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        String sb2 = sb.toString();
        Intent intent3 = new Intent("android.intent.action.VIEW");
        Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter(VirtualHandleWrapper.KEY_ID, "com.google.android.gms");
        if (!TextUtils.isEmpty(sb2)) {
            appendQueryParameter.appendQueryParameter("pcampaignid", sb2);
        }
        intent3.setData(appendQueryParameter.build());
        intent3.setPackage("com.android.vending");
        intent3.addFlags(WindowManagerWrapper.LayoutParams.SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS);
        return intent3;
    }

    public PendingIntent e(Context context, int i2, int i3) {
        return f(context, i2, i3, null);
    }

    public PendingIntent f(Context context, int i2, int i3, String str) {
        Intent d2 = d(context, i2, str);
        if (d2 == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i3, d2, com.google.android.gms.internal.common.zzd.f11393a | 134217728);
    }

    public String g(int i2) {
        return GooglePlayServicesUtilLight.d(i2);
    }

    public int i(Context context) {
        return j(context, f10502a);
    }

    public int j(Context context, int i2) {
        int g2 = GooglePlayServicesUtilLight.g(context, i2);
        if (GooglePlayServicesUtilLight.h(context, g2)) {
            return 18;
        }
        return g2;
    }

    public boolean k(Context context, int i2) {
        return GooglePlayServicesUtilLight.h(context, i2);
    }

    public boolean l(Context context, String str) {
        return GooglePlayServicesUtilLight.k(context, str);
    }

    public boolean m(int i2) {
        return GooglePlayServicesUtilLight.j(i2);
    }

    public void n(Context context, int i2) {
        GooglePlayServicesUtilLight.b(context, i2);
    }
}
