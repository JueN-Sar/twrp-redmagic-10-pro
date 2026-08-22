package cn.nubia.gameassist.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CtsUtils {

    /* renamed from: a, reason: collision with root package name */
    public static String f7651a = "CtsUtils";

    /* renamed from: b, reason: collision with root package name */
    public static String[] f7652b = {"android.permission.ACCOUNT_MANAGER", "android.permission.BIND_ACCESSIBILITY_SERVICE", "android.permission.BIND_AUTOFILL_SERVICE", "android.permission.BIND_CHOOSER_TARGET_SERVICE", "android.permission.BIND_CONDITION_PROVIDER_SERVICE", "android.permission.BIND_DREAM_SERVICE", "android.permission.BIND_INPUT_METHOD", "android.permission.BIND_MIDI_DEVICE_SERVICE", "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", "android.permission.BIND_PRINT_SERVICE", "android.permission.BIND_TEXT_SERVICE", "android.permission.BIND_VOICE_INTERACTION", "android.permission.BIND_VPN_SERVICE", "android.permission.BIND_VR_LISTENER_SERVICE"};

    public static void a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (String str : f7652b) {
            for (PackageInfo packageInfo : packageManager.getPackagesHoldingPermissions(new String[]{str}, 8192)) {
                if (!"android".equals(packageInfo.packageName) && context.getPackageManager().equals(packageInfo.packageName)) {
                    arrayList.add(str + " held by " + packageInfo.packageName);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            Toast.makeText(context, "Found permissions granted to packages outside of the core system: " + arrayList, 0).show();
            return;
        }
        Log.i(f7651a, "permissions good: " + context.getPackageName());
    }
}
