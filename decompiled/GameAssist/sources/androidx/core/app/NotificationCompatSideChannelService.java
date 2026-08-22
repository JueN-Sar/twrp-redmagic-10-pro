package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.INotificationSideChannel;
import androidx.annotation.DeprecatedSinceApi;

/* loaded from: classes.dex */
public abstract class NotificationCompatSideChannelService extends Service {

    private class NotificationSideChannelStub extends INotificationSideChannel.Stub {
        NotificationSideChannelStub() {
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void cancel(String str, int i2, String str2) {
            NotificationCompatSideChannelService.this.c(Binder.getCallingUid(), str);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.a(str, i2, str2);
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void cancelAll(String str) {
            NotificationCompatSideChannelService.this.c(Binder.getCallingUid(), str);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.b(str);
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void notify(String str, int i2, String str2, Notification notification) {
            NotificationCompatSideChannelService.this.c(Binder.getCallingUid(), str);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.d(str, i2, str2, notification);
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public abstract void a(String str, int i2, String str2);

    public abstract void b(String str);

    void c(int i2, String str) {
        for (String str2 : getPackageManager().getPackagesForUid(i2)) {
            if (str2.equals(str)) {
                return;
            }
        }
        throw new SecurityException("NotificationSideChannelService: Uid " + i2 + " is not authorized for package " + str);
    }

    public abstract void d(String str, int i2, String str2, Notification notification);

    @Override // android.app.Service
    @DeprecatedSinceApi
    public IBinder onBind(Intent intent) {
        intent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL");
        return null;
    }
}
