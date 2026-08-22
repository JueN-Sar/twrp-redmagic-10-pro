package com.zte.plugin.reminder;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.WechatHelper;

/* loaded from: classes2.dex */
public class GameReminderUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final String f18029a;

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f18030b;

    /* renamed from: c, reason: collision with root package name */
    public static final boolean f18031c;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f18032d;

    static {
        String str = SystemProperties.get("ro.vendor.feature.soc_vendor");
        f18029a = str;
        f18030b = "mediatek".equals(str);
        f18031c = "sprd".equals(str);
        f18032d = "qcom".equals(str);
    }

    public static void b(String str, String str2) {
        GaLog.b("GameReminder[" + str + "]", str2);
    }

    public static void c(String str, String str2, Throwable th) {
        GaLog.d("GameReminder[" + str + "]", str2, th);
    }

    public static String d(Context context, String str) {
        ApplicationInfo applicationInfo;
        GaLog.a("WechatHelper", "GameReminderUtils getApplicationName packageName = " + str);
        if (WechatHelper.i(str)) {
            return WechatHelper.a().f(str, true);
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            return "";
        }
        CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
        return TextUtils.isEmpty(applicationLabel) ? "" : applicationLabel.toString();
    }

    public static void e(String str, String str2) {
        GaLog.e("GameReminder[" + str + "]", str2);
    }

    public static boolean f(Context context) {
        int i2 = Settings.System.getInt(context.getContentResolver(), "message_prompts", 0);
        return i2 == 0 || i2 == 1;
    }

    public static void h(Context context) {
        SoundPool build = new SoundPool.Builder().setMaxStreams(2).build();
        final int load = build.load(context, R.raw.jingle, 1);
        build.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.zte.plugin.reminder.e
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public final void onLoadComplete(SoundPool soundPool, int i2, int i3) {
                soundPool.play(load, 0.5f, 0.5f, 0, 0, 1.0f);
            }
        });
    }

    public static void i(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), str, 0).show();
    }

    public static void j(Context context, String str, long j2, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("app_name", d(context, str2));
        bundle.putString("ring", i2 == 1 ? "yes" : "no");
        bundle.putString("expiration_time", String.format("%.1f", Float.valueOf(j2 / 3600000.0f)));
        bundle.putString("custom_note", TextUtils.isEmpty(str) ? "no" : "yes");
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_reminder_set", bundle);
    }
}
