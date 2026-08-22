package com.zte.plugin.reminder;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.plugin.reminder.widget.GameReminderWidget;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
public class AlarmService extends Service {

    /* renamed from: c, reason: collision with root package name */
    private NotificationManager f17980c;

    /* renamed from: h, reason: collision with root package name */
    private Looper f17981h;

    /* renamed from: i, reason: collision with root package name */
    protected ServiceHandler f17982i;

    /* renamed from: j, reason: collision with root package name */
    private AlarmManager f17983j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f17984k;

    /* renamed from: l, reason: collision with root package name */
    private AsyncRingtonePlayer f17985l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f17986m = true;

    /* renamed from: n, reason: collision with root package name */
    private PlayTimeoutRunnable f17987n = new PlayTimeoutRunnable();

    /* renamed from: o, reason: collision with root package name */
    public AlarmManager.OnAlarmListener f17988o = new AlarmManager.OnAlarmListener() { // from class: com.zte.plugin.reminder.AlarmService.1
        @Override // android.app.AlarmManager.OnAlarmListener
        public void onAlarm() {
            AlarmService.this.f17986m = true;
            AlarmService.this.L(AlarmService.this.getSharedPreferences("game_reminder", 0).getLong("single_alarm_time", 0L));
            AlarmService.this.D();
            AlarmService.this.q();
            if (AlarmService.this.f17984k || !AlarmService.this.f17986m) {
                return;
            }
            AlarmService.this.stopSelf();
        }
    };

    /* renamed from: p, reason: collision with root package name */
    private AudioManager.OnAudioFocusChangeListener f17989p = new AudioManager.OnAudioFocusChangeListener() { // from class: com.zte.plugin.reminder.AlarmService.2
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i2) {
            GameReminderUtils.e("AlarmService", "onAudioFocusChange: focusChange: " + i2);
            AlarmService.this.r().q(i2);
        }
    };

    private class PlayTimeoutRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        long f17992c;

        /* renamed from: h, reason: collision with root package name */
        String f17993h;

        /* renamed from: i, reason: collision with root package name */
        long f17994i;

        /* renamed from: j, reason: collision with root package name */
        String f17995j;

        /* renamed from: k, reason: collision with root package name */
        int f17996k;

        public void a(long j2, String str, long j3, String str2, int i2) {
            this.f17992c = j2;
            this.f17993h = str;
            this.f17994i = j3;
            this.f17995j = str2;
            this.f17996k = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlarmService.this.n(false, 1, -1L);
            AlarmService.this.l(this.f17992c, this.f17995j, this.f17993h, this.f17994i, 2, this.f17996k);
            if (AlarmService.this.f17986m) {
                AlarmService.this.stopSelf();
            }
        }

        private PlayTimeoutRunnable() {
        }
    }

    protected final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.arg1;
            Intent intent = (Intent) message.obj;
            GameReminderUtils.e("AlarmService", "handleMessage serviceId: " + i2 + "intent: " + intent);
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.LOCKED_BOOT_COMPLETED".equals(action)) {
                    AlarmService.this.D();
                } else if ("cn.nubia.gamereminder.UPDATE".equals(action)) {
                    AlarmService.this.m();
                    AlarmService.this.D();
                } else if ("cn.nubia.gamereminder.OPERATION".equals(action)) {
                    AlarmService.this.y(intent);
                } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                    AlarmService.this.A(intent);
                    AlarmService.this.m();
                    AlarmService.this.D();
                } else if ("android.intent.action.TIME_SET".equals(action)) {
                    AlarmService.this.m();
                    AlarmService.this.D();
                }
                if (AlarmService.this.f17984k || !AlarmService.this.f17986m) {
                    return;
                }
                AlarmService.this.stopSelf();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(Intent intent) {
        String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
        GameReminderUtils.e("AlarmService", "handleMessage handlePackageRemoved packageName: " + encodedSchemeSpecificPart);
        if (TextUtils.isEmpty(encodedSchemeSpecificPart)) {
            return;
        }
        getContentResolver().delete(GameReminderColumn.f18021a, "package=?", new String[]{encodedSchemeSpecificPart});
        GameReminderWidget.a(this);
    }

    private void B(Intent intent) {
        long longExtra = intent.getLongExtra("reminder_id", -1L);
        int intExtra = intent.getIntExtra("reminder_alarm", 0);
        String stringExtra = intent.getStringExtra("reminder_title");
        String stringExtra2 = intent.getStringExtra("reminder_package");
        boolean booleanExtra = intent.getBooleanExtra("reminder_gamemode", false);
        int intExtra2 = intent.getIntExtra("reminder_postpone_num", 0);
        if (longExtra <= 0) {
            return;
        }
        n(booleanExtra, intExtra, longExtra);
        long currentTimeMillis = (System.currentTimeMillis() / 60000) * 60000;
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", stringExtra);
        contentValues.put("time", Long.valueOf(currentTimeMillis + 600000));
        contentValues.put("package", stringExtra2);
        if (intExtra == 2) {
            intExtra = 1;
        }
        contentValues.put("alarm", Integer.valueOf(intExtra));
        contentValues.put("postpone_num", Integer.valueOf(intExtra2 + 1));
        getContentResolver().insert(GameReminderColumn.f18021a, contentValues);
        GameReminderUtils.i(this, getString(R.string.game_reminder_postpone));
        GameReminderWidget.a(this);
        D();
    }

    private void C(long j2, Intent intent) {
        for (ResolveInfo resolveInfo : getPackageManager().queryBroadcastReceivers(intent, 0)) {
            if ("com.qualcomm.qti.poweroffalarm".equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                Intent intent2 = new Intent(intent);
                intent2.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
                intent2.putExtra("time", j2);
                intent2.putExtra("alarm", "cn.nubia.gameassist");
                intent2.putExtra("alarm_notice", "deskclock_alarm");
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
                sendBroadcast(intent2);
            }
        }
    }

    private void E(long j2) {
        GameReminderUtils.e("AlarmService", "setOrCancelPowerOffAlarm time: " + j2);
        if (GameReminderUtils.f18032d) {
            F(j2);
        } else if (GameReminderUtils.f18031c) {
            G(j2);
        }
    }

    private void F(long j2) {
        GameReminderUtils.e("AlarmService", "setQcomPowerOffAlarm time: " + j2);
        SharedPreferences sharedPreferences = getSharedPreferences("game_reminder", 0);
        long j3 = sharedPreferences.getLong("next_alarm_time", 0L);
        GameReminderUtils.e("AlarmService", "preferencesTime: " + j3);
        if (j3 != 0 && j3 != j2) {
            C(j3, new Intent("org.codeaurora.poweroffalarm.action.CANCEL_ALARM"));
            if (j2 == 0) {
                sharedPreferences.edit().putLong("next_alarm_time", 0L).apply();
            }
        }
        if (j2 > 0) {
            C(j2, new Intent("org.codeaurora.poweroffalarm.action.SET_ALARM"));
            sharedPreferences.edit().putLong("next_alarm_time", j2).apply();
        }
    }

    private void G(long j2) {
        GameReminderUtils.e("AlarmService", "setSprPowerOffAlarm time: " + j2);
        SharedPreferences sharedPreferences = getSharedPreferences("game_reminder", 0);
        long j3 = sharedPreferences.getLong("next_alarm_time", 0L);
        GameReminderUtils.e("AlarmService", "preferencesTime: " + j3);
        Intent intent = new Intent("org.codeaurora.poweroffalarm.action.SET_ALARM");
        intent.putExtra("reminder_time", j2);
        intent.setPackage("cn.nubia.gameassist");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, 335544320);
        if (j3 != 0 && j3 != j2) {
            o(broadcast);
            if (j2 == 0) {
                sharedPreferences.edit().putLong("next_alarm_time", 0L).apply();
            }
        }
        if (j2 > 0) {
            H(j2, broadcast);
            sharedPreferences.edit().putLong("next_alarm_time", j2).apply();
        }
    }

    private void H(long j2, PendingIntent pendingIntent) {
        try {
            Class<?> cls = Class.forName("com.zte.power.ZteAlarmControllerManager");
            Object invoke = cls.getMethod("getInstance", null).invoke(null, null);
            Class<?>[] clsArr = {Long.TYPE, PendingIntent.class};
            cls.getDeclaredMethod("setPowerOffAlarm", clsArr).invoke(invoke, Long.valueOf(j2), pendingIntent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void I(long j2) {
        this.f17986m = false;
        getSharedPreferences("game_reminder", 0).edit().putLong("single_alarm_time", j2).apply();
        GameReminderUtils.e("AlarmService", "setSingAlarm time: " + j2);
        AlarmManager alarmManager = this.f17983j;
        if (alarmManager != null) {
            try {
                alarmManager.setExact(0, j2, "game_reminder_alarm", this.f17988o, this.f17982i);
            } catch (Exception e2) {
                e2.printStackTrace();
                GameReminderUtils.e("AlarmService", "set exact alarm exception !");
            }
        }
    }

    private void J() {
        r().H();
        r().p();
    }

    private void K(String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("app_name", GameReminderUtils.d(this, str));
        bundle.putString("reason_for_finish", str2);
        bundle.putString("delays", Integer.toString(i2));
        if (NubiaTrackManager.p() != null) {
            NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_reminder_used", bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(long j2) {
        Cursor query = getContentResolver().query(GameReminderColumn.f18021a, null, "time=?", new String[]{Long.toString(j2)}, null);
        if (query != null) {
            try {
                try {
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (query.getCount() != 0) {
                    while (query.moveToNext()) {
                        k(query);
                    }
                    return;
                }
            } finally {
                query.close();
            }
        }
        if (query != null) {
        }
    }

    private void k(Cursor cursor) {
        long j2 = cursor.getLong(cursor.getColumnIndex("_id"));
        String string = cursor.getString(cursor.getColumnIndex("package"));
        String string2 = cursor.getString(cursor.getColumnIndex("title"));
        if (TextUtils.isEmpty(string2)) {
            string2 = getString(R.string.game_reminder_input_hint);
        }
        l(j2, string, string2, cursor.getLong(cursor.getColumnIndex("time")), cursor.getInt(cursor.getColumnIndex("alarm")), cursor.getInt(cursor.getColumnIndex("postpone_num")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.f17986m = true;
        AlarmManager alarmManager = this.f17983j;
        if (alarmManager != null) {
            alarmManager.cancel(this.f17988o);
            GameReminderUtils.e("AlarmService", "cancelAlarm");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(boolean z, int i2, long j2) {
        if (i2 != 1) {
            this.f17980c.cancel((int) j2);
            return;
        }
        J();
        this.f17984k = false;
        this.f17982i.removeCallbacks(this.f17987n);
        this.f17980c.cancel(-1);
    }

    private void o(PendingIntent pendingIntent) {
        try {
            Class<?> cls = Class.forName("com.zte.power.ZteAlarmControllerManager");
            cls.getDeclaredMethod("cancelAlarm", PendingIntent.class).invoke(cls.getMethod("getInstance", null).invoke(null, null), pendingIntent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private NotificationChannel p(boolean z, int i2) {
        String str;
        String str2;
        if (i2 == 1) {
            str = "game_reminder_alarm_ticker";
            str2 = "game_reminder_alarm";
        } else if (i2 == 2) {
            str = "game_reminder_notify_ticker_update";
            str2 = "game_reminder_notify_update";
        } else {
            str = "game_reminder_notify_ticker";
            str2 = "game_reminder_notify";
        }
        NotificationChannel notificationChannel = new NotificationChannel(str2, str, 4);
        if (i2 == 1) {
            notificationChannel.setSound(null, null);
            notificationChannel.enableVibration(false);
        } else if (i2 == 2) {
            notificationChannel.setSound(null, null);
            notificationChannel.enableVibration(false);
        } else {
            notificationChannel.setSound(RingtoneManager.getDefaultUri(2), new AudioAttributes.Builder().setUsage(4).setContentType(4).build());
            notificationChannel.enableVibration(true);
        }
        notificationChannel.setImportance(4);
        this.f17980c.createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        getContentResolver().delete(GameReminderColumn.f18021a, "time<?", new String[]{Long.toString(System.currentTimeMillis())});
        GameReminderWidget.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized AsyncRingtonePlayer r() {
        try {
            if (this.f17985l == null) {
                this.f17985l = new AsyncRingtonePlayer(getApplicationContext(), this.f17989p);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f17985l;
    }

    private String s() {
        String str = "";
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null || runningTasks.isEmpty()) {
                GameReminderUtils.b("AlarmService", "runningTasks is null !");
            } else {
                str = runningTasks.get(0).topActivity.getPackageName();
            }
        } catch (Exception unused) {
            GameReminderUtils.b("AlarmService", "getCurrentPackageName Exception !");
        }
        return str;
    }

    private String t(String str) {
        String d2 = GameReminderUtils.d(this, str);
        if (TextUtils.isEmpty(d2)) {
            return getString(R.string.game_reminder_input_hint);
        }
        return getString(R.string.game_reminder_input_hint) + " · " + d2.toString();
    }

    private String u(String str, String str2) {
        return GameReminderUtils.d(this, str);
    }

    private void v(Intent intent) {
        long longExtra = intent.getLongExtra("reminder_id", -1L);
        int intExtra = intent.getIntExtra("reminder_alarm", 0);
        String stringExtra = intent.getStringExtra("reminder_package");
        int intExtra2 = intent.getIntExtra("reminder_postpone_num", 0);
        boolean booleanExtra = intent.getBooleanExtra("reminder_gamemode", false);
        if (longExtra <= 0) {
            return;
        }
        n(booleanExtra, intExtra, longExtra);
        K(stringExtra, "close", intExtra2);
    }

    private void w(Intent intent) {
        long longExtra = intent.getLongExtra("reminder_id", -1L);
        int intExtra = intent.getIntExtra("reminder_alarm", 0);
        int intExtra2 = intent.getIntExtra("reminder_postpone_num", 0);
        boolean booleanExtra = intent.getBooleanExtra("reminder_gamemode", false);
        if (longExtra <= 0) {
            GameReminderUtils.b("AlarmService", "handleContentClick id: " + longExtra);
            return;
        }
        String stringExtra = intent.getStringExtra("reminder_package");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        if (stringExtra.equals(s())) {
            n(booleanExtra, intExtra, longExtra);
            return;
        }
        GaLog.a("WechatHelper", "AlarmService handleContentClick packageName: " + stringExtra);
        if (WechatHelper.i(stringExtra)) {
            WechatHelper.a().m(stringExtra, true);
            return;
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(stringExtra);
        try {
            n(booleanExtra, intExtra, longExtra);
            startActivity(launchIntentForPackage);
            K(stringExtra, "enter", intExtra2);
        } catch (Exception unused) {
        }
    }

    private void x(Intent intent) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(Intent intent) {
        String stringExtra = intent.getStringExtra("reminder_action");
        if ("postpone".equals(stringExtra)) {
            B(intent);
            return;
        }
        if ("close".equals(stringExtra)) {
            v(intent);
            return;
        }
        if ("content".equals(stringExtra)) {
            w(intent);
        } else if ("fullscreen".equals(stringExtra)) {
            x(intent);
        } else if ("alarm_close".equals(stringExtra)) {
            z();
        }
    }

    private void z() {
        J();
        this.f17984k = false;
        this.f17982i.removeCallbacks(this.f17987n);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x004a, code lost:
    
        if (r2 == null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void D() {
        /*
            r13 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.ContentResolver r2 = r13.getContentResolver()
            android.net.Uri r3 = com.zte.plugin.reminder.GameReminderColumn.f18021a
            java.lang.String r4 = java.lang.Long.toString(r0)
            java.lang.String[] r6 = new java.lang.String[]{r4}
            java.lang.String r7 = "time asc"
            r4 = 0
            java.lang.String r5 = "time>?"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)
            r3 = 0
            java.lang.String r5 = "time"
            if (r2 == 0) goto L3e
            int r6 = r2.getCount()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            if (r6 != 0) goto L28
            goto L3e
        L28:
            r2.moveToFirst()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            int r6 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            long r6 = r2.getLong(r6)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r13.I(r6)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
        L36:
            r2.close()
            goto L4d
        L3a:
            r13 = move-exception
            goto L90
        L3c:
            r6 = move-exception
            goto L47
        L3e:
            r13.E(r3)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            if (r2 == 0) goto L46
            r2.close()
        L46:
            return
        L47:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L3a
            if (r2 == 0) goto L4d
            goto L36
        L4d:
            android.content.ContentResolver r7 = r13.getContentResolver()
            android.net.Uri r8 = com.zte.plugin.reminder.GameReminderColumn.f18021a
            java.lang.String r0 = java.lang.Long.toString(r0)
            java.lang.String[] r11 = new java.lang.String[]{r0}
            java.lang.String r12 = "time asc"
            r9 = 0
            java.lang.String r10 = "time>? and alarm=1"
            android.database.Cursor r0 = r7.query(r8, r9, r10, r11, r12)
            if (r0 == 0) goto L81
            int r1 = r0.getCount()     // Catch: java.lang.Throwable -> L7f
            if (r1 != 0) goto L6d
            goto L81
        L6d:
            r0.moveToFirst()     // Catch: java.lang.Throwable -> L7f
            int r1 = r0.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L7f
            long r1 = r0.getLong(r1)     // Catch: java.lang.Throwable -> L7f
            r13.E(r1)     // Catch: java.lang.Throwable -> L7f
            r0.close()
            return
        L7f:
            r13 = move-exception
            goto L8a
        L81:
            r13.E(r3)     // Catch: java.lang.Throwable -> L7f
            if (r0 == 0) goto L89
            r0.close()
        L89:
            return
        L8a:
            if (r0 == 0) goto L8f
            r0.close()
        L8f:
            throw r13
        L90:
            if (r2 == 0) goto L95
            r2.close()
        L95:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.plugin.reminder.AlarmService.D():void");
    }

    protected void l(long j2, String str, String str2, long j3, int i2, int i3) {
        int i4;
        boolean z = Settings.Global.getInt(getContentResolver(), "nubia_game_scene", 0) != 0;
        GameReminderUtils.e("AlarmService", "buildNotificationAndNotifyInternal isGameMode: " + z);
        Intent intent = new Intent("cn.nubia.gamereminder.OPERATION");
        intent.putExtra("reminder_action", "postpone");
        intent.putExtra("reminder_package", str);
        intent.putExtra("reminder_title", str2);
        intent.putExtra("reminder_time", j3);
        intent.putExtra("reminder_id", j2);
        intent.putExtra("reminder_alarm", i2);
        intent.putExtra("reminder_postpone_num", i3);
        intent.putExtra("reminder_gamemode", z);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, UUID.randomUUID().hashCode(), intent, 201326592);
        Intent intent2 = new Intent("cn.nubia.gamereminder.OPERATION");
        intent2.putExtra("reminder_action", "close");
        intent2.putExtra("reminder_package", str);
        intent2.putExtra("reminder_id", j2);
        intent2.putExtra("reminder_alarm", i2);
        intent2.putExtra("reminder_postpone_num", i3);
        intent2.putExtra("reminder_gamemode", z);
        boolean z2 = z;
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this, UUID.randomUUID().hashCode(), intent2, 201326592);
        int i5 = R.drawable.game_reminder_zte_notification_icon;
        if (ZteFeature.isRedMagicProduct()) {
            i5 = R.drawable.game_reminder_notification_icon;
        }
        Notification.Action build = new Notification.Action.Builder(i5, getResources().getString(R.string.game_reminder_notification_postpone), broadcast).build();
        Notification.Action build2 = new Notification.Action.Builder(i5, getResources().getString(R.string.game_reminder_notification_close), broadcast2).build();
        Intent intent3 = new Intent("cn.nubia.gamereminder.OPERATION");
        intent3.putExtra("reminder_action", "content");
        intent3.putExtra("reminder_package", str);
        intent3.putExtra("reminder_id", j2);
        intent3.putExtra("reminder_alarm", i2);
        intent3.putExtra("reminder_postpone_num", i3);
        intent3.putExtra("reminder_gamemode", z2);
        PendingIntent broadcast3 = PendingIntent.getBroadcast(this, UUID.randomUUID().hashCode(), intent3, 201326592);
        Intent intent4 = new Intent("cn.nubia.gamereminder.OPERATION");
        intent4.putExtra("reminder_action", "fullscreen");
        PendingIntent broadcast4 = PendingIntent.getBroadcast(this, UUID.randomUUID().hashCode(), intent4, 201326592);
        Notification.Builder category = new Notification.Builder(this).setContentTitle(str2).addAction(build).addAction(build2).setContentText(u(str, str2)).setCategory("alarm");
        category.setPriority(2);
        category.setDefaults(7);
        category.setSmallIcon(i5);
        category.setContentIntent(broadcast3);
        if (i2 == 1) {
            Intent intent5 = new Intent("cn.nubia.gamereminder.OPERATION");
            intent5.putExtra("reminder_action", "alarm_close");
            i4 = 0;
            category.setDeleteIntent(PendingIntent.getBroadcast(this, 0, intent5, 201326592));
        } else {
            i4 = 0;
        }
        if (!z2) {
            if (i2 == 1) {
                category.setFullScreenIntent(broadcast4, true);
            } else if (i2 == 2) {
                category.setPriority(i4);
            }
        }
        Bundle bundle = new Bundle();
        String t = t(str);
        GameReminderUtils.e("AlarmService", "appName: " + t);
        bundle.putString("android.substName", t);
        bundle.putBoolean("use_custom_icon", true);
        bundle.putBoolean("keep_in_lockscreen", true);
        bundle.putParcelableArray("ticker_action", new Notification.Action[]{build, build2});
        category.setExtras(bundle);
        category.setChannelId(p(z2, i2).getId());
        if (i2 != 1) {
            this.f17980c.notify((int) j2, category.build());
            return;
        }
        sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        this.f17980c.notify(-1, category.build());
        r().z(RingtoneManager.getDefaultUri(4), false);
        this.f17984k = true;
        this.f17987n.a(j2, str2, j3, str, i3);
        this.f17982i.removeCallbacks(this.f17987n);
        this.f17982i.postDelayed(this.f17987n, 120000L);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("Gamereminder");
        handlerThread.start();
        this.f17981h = handlerThread.getLooper();
        this.f17982i = new ServiceHandler(this.f17981h);
        this.f17980c = (NotificationManager) getSystemService("notification");
        this.f17983j = (AlarmManager) getSystemService("alarm");
        GameReminderUtils.e("AlarmService", "onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        GameReminderUtils.e("AlarmService", "onDestroy");
        this.f17982i.removeCallbacks(this.f17987n);
        this.f17981h.quit();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Message obtainMessage = this.f17982i.obtainMessage();
        obtainMessage.arg1 = i3;
        obtainMessage.obj = intent;
        this.f17982i.sendMessage(obtainMessage);
        GameReminderUtils.e("AlarmService", "onStartCommand startId: " + i3);
        return 2;
    }
}
