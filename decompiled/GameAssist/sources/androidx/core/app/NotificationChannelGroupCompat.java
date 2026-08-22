package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.util.List;

/* loaded from: classes.dex */
public class NotificationChannelGroupCompat {

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static NotificationChannelGroup a(String str, CharSequence charSequence) {
            return new NotificationChannelGroup(str, charSequence);
        }

        @DoNotInline
        static List<NotificationChannel> b(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getChannels();
        }

        @DoNotInline
        static String c(NotificationChannel notificationChannel) {
            return notificationChannel.getGroup();
        }

        @DoNotInline
        static String d(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getId();
        }

        @DoNotInline
        static CharSequence e(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getName();
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static String a(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getDescription();
        }

        @DoNotInline
        static boolean b(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.isBlocked();
        }

        @DoNotInline
        static void c(NotificationChannelGroup notificationChannelGroup, String str) {
            notificationChannelGroup.setDescription(str);
        }
    }

    public static class Builder {
    }
}
