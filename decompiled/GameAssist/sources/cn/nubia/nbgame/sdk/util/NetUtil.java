package cn.nubia.nbgame.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
public final class NetUtil {
    public static synchronized boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        synchronized (NetUtil.class) {
            boolean z = false;
            if (context == null) {
                return false;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.isConnected()) {
                    z = true;
                }
            }
            return z;
        }
    }
}
