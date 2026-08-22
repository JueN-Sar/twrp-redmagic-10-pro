package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class ServiceCompat {

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static void a(Service service, int i2) {
            service.stopForeground(i2);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(Service service, int i2, Notification notification, int i3) {
            if (i3 == 0 || i3 == -1) {
                service.startForeground(i2, notification, i3);
            } else {
                service.startForeground(i2, notification, i3 & 255);
            }
        }
    }

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static void a(Service service, int i2, Notification notification, int i3) {
            if (i3 == 0 || i3 == -1) {
                service.startForeground(i2, notification, i3);
            } else {
                service.startForeground(i2, notification, i3 & 1073745919);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface StopForegroundFlags {
    }
}
