package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Locale;

@KeepForSdk
/* loaded from: classes.dex */
public class CommonUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final GmsLogger f15933a = new GmsLogger("CommonUtils", "");

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e2) {
            f15933a.c("CommonUtils", "Exception thrown when trying to get app version ".concat(e2.toString()));
            return "";
        }
    }

    public static String b(Locale locale) {
        if (PlatformVersion.d()) {
            return locale.toLanguageTag();
        }
        StringBuilder sb = new StringBuilder(locale.getLanguage());
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-");
            sb.append(locale.getCountry());
        }
        if (!TextUtils.isEmpty(locale.getVariant())) {
            sb.append("-");
            sb.append(locale.getVariant());
        }
        return sb.toString();
    }
}
