package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Intent;
import android.service.quicksettings.TileService;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class TileServiceCompat {

    @RequiresApi
    private static class Api24Impl {
        @DoNotInline
        static void a(TileService tileService, Intent intent) {
            tileService.startActivityAndCollapse(intent);
        }
    }

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        static void a(TileService tileService, PendingIntent pendingIntent) {
            tileService.startActivityAndCollapse(pendingIntent);
        }
    }

    interface TileServiceWrapper {
    }
}
