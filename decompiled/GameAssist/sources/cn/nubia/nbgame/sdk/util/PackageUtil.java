package cn.nubia.nbgame.sdk.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes.dex */
public class PackageUtil {

    /* renamed from: a, reason: collision with root package name */
    public static String f8323a = "PackageUtil";

    /* renamed from: b, reason: collision with root package name */
    public static boolean f8324b = false;

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        String str = (String) SharedPsHelps.a(context, "chl_key", "");
        NeoLog.k("getchannel getLocalC localC is:" + str);
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String b(Context context) {
        if (context == null) {
            NeoLog.k("getchannel rc context is null");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                NeoLog.k("  PackageUtil rc app is null");
                return a(context);
            }
            String c2 = c(applicationInfo.sourceDir);
            NeoLog.k("getchannel rc result is:" + c2);
            return TextUtils.isEmpty(c2) ? a(context) : c2;
        } catch (Exception e2) {
            e2.printStackTrace();
            NeoLog.k("getchannel rc has error");
            return "";
        }
    }

    public static String c(String str) {
        String e2 = e(str);
        NeoLog.l(f8323a, "getchannel vtc is:" + e2);
        return ("".equals(e2) || e2 == null) ? d(str) : e2;
    }

    public static String d(String str) {
        return VoHelp.b(str);
    }

    public static String e(String str) {
        if ("".equals(str) || str == null) {
            NeoLog.l(f8323a, "getchannel vtr filePath == null or '' ");
            return "";
        }
        File file = new File(str);
        if (!file.exists()) {
            NeoLog.l(f8323a, "getchannel file == null || !file.exists()");
            return "";
        }
        ChannelInfo a2 = ChannelReader.a(file);
        if (a2 == null) {
            NeoLog.l(f8323a, "getchannel hannelReader.get(file) info is null");
            NeoLog.l(f8323a, "getchannel get channel fail");
            return "";
        }
        NeoLog.l(f8323a, "getchannel info.getchannel is:" + a2.a());
        return a2.a();
    }
}
