package androidx.core.app;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.graphics.drawable.Icon;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcelable;

/* loaded from: classes.dex */
public final class RemoteActionCompat implements VersionedParcelable {

    /* renamed from: a, reason: collision with root package name */
    public IconCompat f2822a;

    /* renamed from: b, reason: collision with root package name */
    public CharSequence f2823b;

    /* renamed from: c, reason: collision with root package name */
    public CharSequence f2824c;

    /* renamed from: d, reason: collision with root package name */
    public PendingIntent f2825d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2826e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2827f;

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static RemoteAction a(Icon icon, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent) {
            return new RemoteAction(icon, charSequence, charSequence2, pendingIntent);
        }

        @DoNotInline
        static PendingIntent b(RemoteAction remoteAction) {
            return remoteAction.getActionIntent();
        }

        @DoNotInline
        static CharSequence c(RemoteAction remoteAction) {
            return remoteAction.getContentDescription();
        }

        @DoNotInline
        static Icon d(RemoteAction remoteAction) {
            return remoteAction.getIcon();
        }

        @DoNotInline
        static CharSequence e(RemoteAction remoteAction) {
            return remoteAction.getTitle();
        }

        @DoNotInline
        static boolean f(RemoteAction remoteAction) {
            return remoteAction.isEnabled();
        }

        @DoNotInline
        static void g(RemoteAction remoteAction, boolean z) {
            remoteAction.setEnabled(z);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static void a(RemoteAction remoteAction, boolean z) {
            remoteAction.setShouldShowIcon(z);
        }

        @DoNotInline
        static boolean b(RemoteAction remoteAction) {
            return remoteAction.shouldShowIcon();
        }
    }

    @RestrictTo
    public RemoteActionCompat() {
    }
}
