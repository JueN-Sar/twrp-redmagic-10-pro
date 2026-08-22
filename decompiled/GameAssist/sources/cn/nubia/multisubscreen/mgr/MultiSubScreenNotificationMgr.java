package cn.nubia.multisubscreen.mgr;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class MultiSubScreenNotificationMgr {

    /* renamed from: c, reason: collision with root package name */
    private static volatile MultiSubScreenNotificationMgr f7915c;

    /* renamed from: a, reason: collision with root package name */
    private final Context f7916a;

    /* renamed from: b, reason: collision with root package name */
    private NotificationManager f7917b;

    private MultiSubScreenNotificationMgr(Context context) {
        this.f7916a = context;
        this.f7917b = (NotificationManager) context.getSystemService("notification");
    }

    private NotificationChannel e() {
        NotificationChannel notificationChannel = new NotificationChannel("multi_sub_screen_connected_notify", "multi_sub_screen_connected_notify_ticker", 3);
        notificationChannel.setSound(null, null);
        notificationChannel.enableVibration(false);
        notificationChannel.setImportance(3);
        this.f7917b.createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    private NotificationChannel f() {
        NotificationChannel notificationChannel = new NotificationChannel("multi_sub_screen_disconnected_notify", "multi_sub_screen_disconnected_notify_ticker", 4);
        notificationChannel.setSound(null, null);
        notificationChannel.enableVibration(false);
        notificationChannel.setImportance(4);
        this.f7917b.createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    public static MultiSubScreenNotificationMgr g() {
        if (f7915c == null) {
            synchronized (MultiSubScreenNotificationMgr.class) {
                try {
                    if (f7915c == null) {
                        f7915c = new MultiSubScreenNotificationMgr(GameAssistApplication.j());
                    }
                } finally {
                }
            }
        }
        return f7915c;
    }

    public void a() {
        GaLog.a("MultiSubScreen_MultiSubScreenNotificationMgr", "notify buildConnectedNotificationAndNotify");
        d();
        Notification.Action build = new Notification.Action.Builder(R.drawable.multi_sub_screen_notification_icon, this.f7916a.getResources().getString(R.string.disconnect_sencond_screen), PendingIntent.getBroadcast(this.f7916a, 0, new Intent("cn.nubia.multisubscreen.disconnect"), 201326592)).build();
        NotificationChannel e2 = e();
        e2.setImportance(3);
        Notification.Builder builder = new Notification.Builder(this.f7916a, e2.getId());
        builder.setContentText(this.f7916a.getResources().getString(R.string.notification_toast_connected));
        builder.addAction(build);
        builder.setOngoing(true);
        c(builder);
    }

    public void b() {
        GaLog.a("MultiSubScreen_MultiSubScreenNotificationMgr", "notify buildDisconnectNotificationAndNotify");
        d();
        NotificationChannel f2 = f();
        f2.setImportance(4);
        Notification.Builder builder = new Notification.Builder(this.f7916a, f2.getId());
        builder.setContentText(this.f7916a.getResources().getString(R.string.notification_toast_disconnect));
        c(builder);
    }

    public void c(Notification.Builder builder) {
        int i2 = R.drawable.multi_sub_screen_notification_icon;
        if (ZteFeature.isRedMagicProduct()) {
            i2 = R.drawable.multi_sub_screen_notification_icon;
        }
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", this.f7916a.getResources().getString(R.string.game_navi_home));
        PendingIntent broadcast = PendingIntent.getBroadcast(this.f7916a, 0, new Intent("cn.nubia.multisubscreen.show"), 201326592);
        builder.setCategory("alarm");
        builder.setSmallIcon(i2);
        builder.setContentIntent(broadcast);
        builder.setExtras(bundle);
        this.f7917b.notify(2147360191, builder.build());
    }

    public void d() {
        NotificationManager notificationManager = this.f7917b;
        if (notificationManager != null) {
            notificationManager.cancel(2147360191);
        }
    }
}
