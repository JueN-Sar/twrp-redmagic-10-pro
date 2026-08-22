package com.zte.gameassist.ext.utils;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import com.zte.gameassist.ext.common.GAControllerProxy;

/* loaded from: classes2.dex */
public class SettingsHelper {
    public static boolean isGlobalSettingsError;
    public static boolean isSecureSettingsError;
    public static boolean isSystemSettingsError;

    static /* synthetic */ void lambda$putGlobalSettings$2(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        bundle.putString("bundle_key_value", str2);
        bundle.putString("bundle_key_type", "global");
        ExtendUtils.invokeWithBundle(GAControllerProxy.INVAKE_SET_SETTINGS, bundle);
    }

    static /* synthetic */ void lambda$putSecureSettings$1(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        bundle.putString("bundle_key_value", str2);
        bundle.putString("bundle_key_type", "secure");
        ExtendUtils.invokeWithBundle(GAControllerProxy.INVAKE_SET_SETTINGS, bundle);
    }

    static /* synthetic */ void lambda$putSystemSettings$0(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("bundle_key_name", str);
        bundle.putString("bundle_key_value", str2);
        bundle.putString("bundle_key_type", "system");
        ExtendUtils.invokeWithBundle(GAControllerProxy.INVAKE_SET_SETTINGS, bundle);
    }

    public static void putGlobalSettings(Context context, final String str, final String str2) {
        Runnable runnable = new Runnable() { // from class: com.zte.gameassist.ext.utils.SettingsHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SettingsHelper.lambda$putGlobalSettings$2(str, str2);
            }
        };
        if (isGlobalSettingsError) {
            runnable.run();
            return;
        }
        try {
            Settings.Global.putString(context.getContentResolver(), str, str2);
        } catch (Exception e) {
            isGlobalSettingsError = true;
            runnable.run();
            e.printStackTrace();
        }
    }

    public static void putSecureSettings(Context context, final String str, final String str2) {
        Runnable runnable = new Runnable() { // from class: com.zte.gameassist.ext.utils.SettingsHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SettingsHelper.lambda$putSecureSettings$1(str, str2);
            }
        };
        if (isSecureSettingsError) {
            runnable.run();
            return;
        }
        try {
            Settings.Secure.putString(context.getContentResolver(), str, str2);
        } catch (Exception e) {
            isSecureSettingsError = true;
            runnable.run();
            e.printStackTrace();
        }
    }

    public static void putSystemSettings(Context context, final String str, final String str2) {
        Runnable runnable = new Runnable() { // from class: com.zte.gameassist.ext.utils.SettingsHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SettingsHelper.lambda$putSystemSettings$0(str, str2);
            }
        };
        if (isSystemSettingsError) {
            runnable.run();
            return;
        }
        try {
            Settings.System.putString(context.getContentResolver(), str, str2);
        } catch (Exception e) {
            isSystemSettingsError = true;
            runnable.run();
            e.printStackTrace();
        }
    }
}
