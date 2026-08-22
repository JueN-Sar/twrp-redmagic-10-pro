package cn.nubia.componentsdk.until;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import java.io.File;

/* loaded from: classes.dex */
public final class UriUtils {
    public static Uri a(Context context, File file) {
        int i2 = context.getApplicationInfo().targetSdkVersion;
        return FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".NubiaFiles", file);
    }
}
