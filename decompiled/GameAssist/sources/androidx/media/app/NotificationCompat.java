package androidx.media.app;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;

/* loaded from: classes.dex */
public class NotificationCompat {

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            notificationBuilderWithBuilderAccessor.a().setStyle(h(new Notification.DecoratedMediaCustomViewStyle()));
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        public RemoteViews d(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        public RemoteViews e(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews f(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }
    }

    public static class MediaStyle extends NotificationCompat.Style {

        /* renamed from: e, reason: collision with root package name */
        int[] f4634e;

        /* renamed from: f, reason: collision with root package name */
        MediaSessionCompat.Token f4635f;

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            notificationBuilderWithBuilderAccessor.a().setStyle(h(new Notification.MediaStyle()));
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews d(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews e(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        Notification.MediaStyle h(Notification.MediaStyle mediaStyle) {
            int[] iArr = this.f4634e;
            if (iArr != null) {
                mediaStyle.setShowActionsInCompactView(iArr);
            }
            MediaSessionCompat.Token token = this.f4635f;
            if (token != null) {
                mediaStyle.setMediaSession((MediaSession.Token) token.getToken());
            }
            return mediaStyle;
        }
    }
}
