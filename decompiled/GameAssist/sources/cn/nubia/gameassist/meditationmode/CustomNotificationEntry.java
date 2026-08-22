package cn.nubia.gameassist.meditationmode;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/* loaded from: classes.dex */
public class CustomNotificationEntry {

    /* renamed from: a, reason: collision with root package name */
    private final String f6515a;

    /* renamed from: b, reason: collision with root package name */
    private StatusBarNotification f6516b;

    /* renamed from: c, reason: collision with root package name */
    private NotificationListenerService.Ranking f6517c;

    public CustomNotificationEntry(StatusBarNotification statusBarNotification, NotificationListenerService.Ranking ranking) {
        this.f6515a = statusBarNotification.getKey();
        this.f6516b = statusBarNotification;
        this.f6517c = ranking;
    }

    public int a() {
        return this.f6517c.getImportance();
    }

    public StatusBarNotification b() {
        return this.f6516b;
    }
}
