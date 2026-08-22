package androidx.core.app;

import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class NotificationChannelCompat {

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static boolean a(NotificationChannel notificationChannel) {
            return notificationChannel.canBypassDnd();
        }

        @DoNotInline
        static boolean b(NotificationChannel notificationChannel) {
            return notificationChannel.canShowBadge();
        }

        @DoNotInline
        static NotificationChannel c(String str, CharSequence charSequence, int i2) {
            return new NotificationChannel(str, charSequence, i2);
        }

        @DoNotInline
        static void d(NotificationChannel notificationChannel, boolean z) {
            notificationChannel.enableLights(z);
        }

        @DoNotInline
        static void e(NotificationChannel notificationChannel, boolean z) {
            notificationChannel.enableVibration(z);
        }

        @DoNotInline
        static AudioAttributes f(NotificationChannel notificationChannel) {
            return notificationChannel.getAudioAttributes();
        }

        @DoNotInline
        static String g(NotificationChannel notificationChannel) {
            return notificationChannel.getDescription();
        }

        @DoNotInline
        static String h(NotificationChannel notificationChannel) {
            return notificationChannel.getGroup();
        }

        @DoNotInline
        static String i(NotificationChannel notificationChannel) {
            return notificationChannel.getId();
        }

        @DoNotInline
        static int j(NotificationChannel notificationChannel) {
            return notificationChannel.getImportance();
        }

        @DoNotInline
        static int k(NotificationChannel notificationChannel) {
            return notificationChannel.getLightColor();
        }

        @DoNotInline
        static int l(NotificationChannel notificationChannel) {
            return notificationChannel.getLockscreenVisibility();
        }

        @DoNotInline
        static CharSequence m(NotificationChannel notificationChannel) {
            return notificationChannel.getName();
        }

        @DoNotInline
        static Uri n(NotificationChannel notificationChannel) {
            return notificationChannel.getSound();
        }

        @DoNotInline
        static long[] o(NotificationChannel notificationChannel) {
            return notificationChannel.getVibrationPattern();
        }

        @DoNotInline
        static void p(NotificationChannel notificationChannel, String str) {
            notificationChannel.setDescription(str);
        }

        @DoNotInline
        static void q(NotificationChannel notificationChannel, String str) {
            notificationChannel.setGroup(str);
        }

        @DoNotInline
        static void r(NotificationChannel notificationChannel, int i2) {
            notificationChannel.setLightColor(i2);
        }

        @DoNotInline
        static void s(NotificationChannel notificationChannel, boolean z) {
            notificationChannel.setShowBadge(z);
        }

        @DoNotInline
        static void t(NotificationChannel notificationChannel, Uri uri, AudioAttributes audioAttributes) {
            notificationChannel.setSound(uri, audioAttributes);
        }

        @DoNotInline
        static void u(NotificationChannel notificationChannel, long[] jArr) {
            notificationChannel.setVibrationPattern(jArr);
        }

        @DoNotInline
        static boolean v(NotificationChannel notificationChannel) {
            return notificationChannel.shouldShowLights();
        }

        @DoNotInline
        static boolean w(NotificationChannel notificationChannel) {
            return notificationChannel.shouldVibrate();
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static boolean a(NotificationChannel notificationChannel) {
            return notificationChannel.canBubble();
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static String a(NotificationChannel notificationChannel) {
            return notificationChannel.getConversationId();
        }

        @DoNotInline
        static String b(NotificationChannel notificationChannel) {
            return notificationChannel.getParentChannelId();
        }

        @DoNotInline
        static boolean c(NotificationChannel notificationChannel) {
            return notificationChannel.isImportantConversation();
        }

        @DoNotInline
        static void d(NotificationChannel notificationChannel, String str, String str2) {
            notificationChannel.setConversationId(str, str2);
        }
    }

    public static class Builder {
    }
}
