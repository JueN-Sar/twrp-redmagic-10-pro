package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.drawable.IconCompat;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ShortcutManagerCompat {

    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    /* renamed from: a, reason: collision with root package name */
    private static volatile ShortcutInfoCompatSaver f2856a;

    /* renamed from: b, reason: collision with root package name */
    private static volatile List f2857b;

    /* renamed from: androidx.core.content.pm.ShortcutManagerCompat$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IntentSender f2858a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                this.f2858a.sendIntent(context, 0, null, null, null);
            } catch (IntentSender.SendIntentException unused) {
            }
        }
    }

    @RequiresApi
    private static class Api25Impl {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ShortcutMatchFlags {
    }

    @VisibleForTesting
    static boolean convertUriIconToBitmapIcon(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        Bitmap decodeStream;
        IconCompat iconCompat = shortcutInfoCompat.f2855a;
        if (iconCompat == null) {
            return false;
        }
        int i2 = iconCompat.f2973a;
        if (i2 != 6 && i2 != 4) {
            return true;
        }
        InputStream o2 = iconCompat.o(context);
        if (o2 == null || (decodeStream = BitmapFactory.decodeStream(o2)) == null) {
            return false;
        }
        shortcutInfoCompat.f2855a = i2 == 6 ? IconCompat.c(decodeStream) : IconCompat.f(decodeStream);
        return true;
    }

    @VisibleForTesting
    static void convertUriIconsToBitmapIcons(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        for (ShortcutInfoCompat shortcutInfoCompat : new ArrayList(list)) {
            if (!convertUriIconToBitmapIcon(context, shortcutInfoCompat)) {
                list.remove(shortcutInfoCompat);
            }
        }
    }

    @VisibleForTesting
    static List<ShortcutInfoChangeListener> getShortcutInfoChangeListeners() {
        return f2857b;
    }

    @VisibleForTesting
    static void setShortcutInfoChangeListeners(List<ShortcutInfoChangeListener> list) {
        f2857b = list;
    }

    @VisibleForTesting
    static void setShortcutInfoCompatSaver(ShortcutInfoCompatSaver<Void> shortcutInfoCompatSaver) {
        f2856a = shortcutInfoCompatSaver;
    }
}
