package cn.nubia.nbgame.sdk.util;

import android.content.Context;
import android.net.Uri;
import cn.nubia.nbgame.sdk.service.NubiaFileProvider;
import java.io.File;

/* loaded from: classes.dex */
public final class UriUtils {
    public static Uri a(Context context, File file) {
        int i2 = context.getApplicationInfo().targetSdkVersion;
        String str = context.getApplicationContext().getPackageName() + ".NubiaFiles";
        NeoLog.g("UriUtils", "authority:" + str);
        return NubiaFileProvider.getUriForFile(context.getApplicationContext(), str, file);
    }
}
