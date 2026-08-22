package cn.nubia.gameassist.meditationmode;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class CustomNotificationListener extends NotificationListenerService {

    /* renamed from: c, reason: collision with root package name */
    private static final String f6518c = "CustomNotificationListener";

    /* renamed from: h, reason: collision with root package name */
    private static IBinder f6519h;

    /* renamed from: i, reason: collision with root package name */
    private static final List f6520i = Arrays.asList("com.whatsapp", "com.discord", "org.telegram.messenger");

    /* renamed from: j, reason: collision with root package name */
    private static final List f6521j = Arrays.asList("com_tencent_qqmusic_player");

    private boolean a(CustomNotificationEntry customNotificationEntry) {
        CharSequence charSequence;
        Notification notification = customNotificationEntry.b().getNotification();
        Bundle bundle = notification.extras;
        CharSequence charSequence2 = "";
        if (bundle != null) {
            charSequence2 = bundle.getCharSequence("android.title", "");
            charSequence = notification.extras.getCharSequence("android.text", "");
        } else {
            charSequence = "";
        }
        return notification.extras == null || (TextUtils.isEmpty(charSequence2) && TextUtils.isEmpty(charSequence));
    }

    private boolean b(CustomNotificationEntry customNotificationEntry) {
        if (customNotificationEntry.b() == null) {
            return false;
        }
        return f6520i.contains(customNotificationEntry.b().getPackageName()) && "msg".equals(customNotificationEntry.b().getNotification().category);
    }

    private void c(StatusBarNotification statusBarNotification, NotificationListenerService.Ranking ranking) {
        if (statusBarNotification == null || ranking == null) {
            GaLog.b(f6518c, "postNotification, sbn or ranking is null !");
            return;
        }
        CustomNotificationEntry customNotificationEntry = new CustomNotificationEntry(statusBarNotification, ranking);
        if (e(customNotificationEntry)) {
            MeditationController.s().G(customNotificationEntry);
            boolean z = Settings.System.getInt(getContentResolver(), "multi_sub_screen_notification_msg", 1) == 1;
            if (MultiSubScreenUtils.t() && z) {
                PrimaryDeviceDataMgr.C().j0(statusBarNotification);
            }
        }
    }

    private NotificationListenerService.Ranking d(NotificationListenerService.RankingMap rankingMap, String str) {
        if (rankingMap == null || TextUtils.isEmpty(str)) {
            GaLog.b(f6518c, "requireRanking, rankingMap or key is invalid ! key:" + str);
            return null;
        }
        NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
        if (rankingMap.getRanking(str, ranking)) {
            return ranking;
        }
        GaLog.k(f6518c, "requireRanking, ranking map doesn't contain key: " + str);
        return null;
    }

    private boolean e(CustomNotificationEntry customNotificationEntry) {
        if (!a(customNotificationEntry)) {
            return customNotificationEntry.a() >= 4 || b(customNotificationEntry);
        }
        GaLog.k(f6518c, "ignore invalid notification !");
        return false;
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public IBinder onBind(Intent intent) {
        if (f6519h == null) {
            f6519h = super.onBind(intent);
        }
        return f6519h;
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        GaLog.e(f6518c, "onDestroy");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        GaLog.a(f6518c, "onListenerConnected");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerDisconnected() {
        GaLog.a(f6518c, "onListenerDisconnected");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        String str = f6518c;
        GaLog.a(str, "onNotificationPosted, sbn:" + statusBarNotification);
        if (statusBarNotification == null || rankingMap == null) {
            GaLog.b(str, "onNotificationPosted, sbn or rankingMap is null !");
        } else {
            c(statusBarNotification, d(rankingMap, statusBarNotification.getKey()));
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        GaLog.a(f6518c, "onNotificationRemoved, sbn:" + statusBarNotification);
        boolean z = Settings.System.getInt(getContentResolver(), "multi_sub_screen_notification_msg", 1) == 1;
        if (MultiSubScreenUtils.t() && z) {
            PrimaryDeviceDataMgr.C().l0(statusBarNotification);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        GaLog.a(f6518c, "onStartCommand");
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
