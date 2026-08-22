package com.zte.shared.wrapper;

import android.app.NotificationManager;
import android.content.ComponentName;

/* loaded from: classes2.dex */
public class NotificationManagerWrapper {
    public static void setNotificationListenerAccessGranted(NotificationManager notificationManager, ComponentName componentName, boolean z) {
        notificationManager.setNotificationListenerAccessGranted(componentName, z);
    }
}
