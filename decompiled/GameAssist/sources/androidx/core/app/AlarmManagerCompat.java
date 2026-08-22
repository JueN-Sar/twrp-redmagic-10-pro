package androidx.core.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class AlarmManagerCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static AlarmManager.AlarmClockInfo a(long j2, PendingIntent pendingIntent) {
            return new AlarmManager.AlarmClockInfo(j2, pendingIntent);
        }

        @DoNotInline
        static void b(AlarmManager alarmManager, Object obj, PendingIntent pendingIntent) {
            alarmManager.setAlarmClock((AlarmManager.AlarmClockInfo) obj, pendingIntent);
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static void a(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
            alarmManager.setAndAllowWhileIdle(i2, j2, pendingIntent);
        }

        @DoNotInline
        static void b(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
            alarmManager.setExactAndAllowWhileIdle(i2, j2, pendingIntent);
        }
    }

    @RequiresApi
    static class Api31Impl {
        @DoNotInline
        static boolean a(AlarmManager alarmManager) {
            return alarmManager.canScheduleExactAlarms();
        }
    }
}
