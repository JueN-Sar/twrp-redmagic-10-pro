package cn.nubia.gameassist.meditationmode.danmu;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class BarrageBundleCreator {

    /* renamed from: b, reason: collision with root package name */
    private static volatile BarrageBundleCreator f6558b;

    /* renamed from: a, reason: collision with root package name */
    private Context f6559a;

    private BarrageBundleCreator(Context context) {
        this.f6559a = context;
    }

    public static BarrageBundleCreator b(Context context) {
        if (f6558b == null) {
            synchronized (BarrageBundleCreator.class) {
                try {
                    if (f6558b == null) {
                        f6558b = new BarrageBundleCreator(context);
                    }
                } finally {
                }
            }
        }
        return f6558b;
    }

    public DanmuNotificationBean a(StatusBarNotification statusBarNotification, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        Notification notification = statusBarNotification.getNotification();
        Bundle bundle = notification.extras;
        if (bundle != null) {
            CharSequence charSequence = bundle.getCharSequence("android.text", "");
            str2 = notification.extras.getCharSequence("android.title", "").toString();
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = str2;
            }
            str = charSequence.toString();
        } else {
            str = "";
            str2 = str;
        }
        PendingIntent pendingIntent = statusBarNotification.getNotification().contentIntent != null ? statusBarNotification.getNotification().contentIntent : statusBarNotification.getNotification().fullScreenIntent;
        String T = BarrageManager.r().T(statusBarNotification);
        String packageName = statusBarNotification.getPackageName();
        if (BarrageManager.r().w(!TextUtils.isEmpty(T) ? T : packageName)) {
            str3 = this.f6559a.getResources().getString(R.string.lock_app_notification_info);
            str4 = "";
        } else {
            str3 = str;
            str4 = str2;
        }
        return new DanmuNotificationBean(str4, str3, packageName, T, pendingIntent, i2, notification.getChannelId());
    }
}
