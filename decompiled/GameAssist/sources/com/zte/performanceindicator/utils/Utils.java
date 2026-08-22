package com.zte.performanceindicator.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.text.SimpleDateFormat;

/* loaded from: classes2.dex */
public class Utils {
    public static String a() {
        return new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(System.currentTimeMillis()));
    }

    public static boolean b(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardLocked();
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    public static int d(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
