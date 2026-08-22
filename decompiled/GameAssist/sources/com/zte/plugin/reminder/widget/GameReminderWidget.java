package com.zte.plugin.reminder.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.plugin.reminder.GameReminderUtils;

/* loaded from: classes2.dex */
public class GameReminderWidget extends AppWidgetProvider {
    public static void a(Context context) {
        GaLog.j("GameReminderWidget", "notifyDatasetChanged");
        context.sendBroadcast(new Intent("com.zte.plugin.reminder.action.ACTION_NOTIFY_DATASET_CHANGED"));
    }

    private void b(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("app_name", GameReminderUtils.d(context, str));
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_remind_widget_used", bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0093 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(android.content.Context r7, android.widget.RemoteViews r8) {
        /*
            r6 = this;
            r6 = 0
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            android.net.Uri r1 = com.zte.plugin.reminder.widget.GameReminderWidgetColumn.f18093a     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r3 = "time>?"
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r2 = java.lang.Long.toString(r4)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String[] r4 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r5 = "time asc"
            r2 = 0
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r0 = "GameReminderWidget"
            r1 = 8
            r2 = 0
            if (r6 == 0) goto L5f
            int r3 = r6.getCount()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            if (r3 != 0) goto L2a
            goto L5f
        L2a:
            java.lang.String r3 = "updateUI setPendingIntentTemplate"
            com.zte.gameassist.utils.GaLog.a(r0, r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r0 = com.zte.gameassist.reminder.R.id.reminder_empty_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r0, r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r0 = com.zte.gameassist.reminder.R.id.reminder_empty_icon_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r0, r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r0 = com.zte.gameassist.reminder.R.id.reminder_list_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r0, r2)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r0 = com.zte.gameassist.reminder.R.id.reminder_title_content     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r0, r2)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r1 = "com.zte.plugin.reminder.action.ACTION_ITEM_CLICK"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r1 = "cn.nubia.gameassist"
            r0.setPackage(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r1 = 167772160(0xa000000, float:6.162976E-33)
            android.app.PendingIntent r7 = android.app.PendingIntent.getBroadcast(r7, r2, r0, r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r0 = com.zte.gameassist.reminder.R.id.reminder_list_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setPendingIntentTemplate(r0, r7)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            goto L87
        L5b:
            r7 = move-exception
            goto L94
        L5d:
            r7 = move-exception
            goto L8d
        L5f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r7.<init>()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r3 = "updateUI cursor="
            r7.append(r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r7.append(r6)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            com.zte.gameassist.utils.GaLog.a(r0, r7)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r7 = com.zte.gameassist.reminder.R.id.reminder_empty_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r7, r2)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r7 = com.zte.gameassist.reminder.R.id.reminder_empty_icon_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r7, r2)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r7 = com.zte.gameassist.reminder.R.id.reminder_list_widget     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r7, r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            int r7 = com.zte.gameassist.reminder.R.id.reminder_title_content     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r8.setViewVisibility(r7, r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
        L87:
            if (r6 == 0) goto L93
        L89:
            r6.close()
            goto L93
        L8d:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L5b
            if (r6 == 0) goto L93
            goto L89
        L93:
            return
        L94:
            if (r6 == 0) goto L99
            r6.close()
        L99:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.plugin.reminder.widget.GameReminderWidget.d(android.content.Context, android.widget.RemoteViews):void");
    }

    void c(Context context, int i2) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.game_reminder_widget_view);
        Intent intent = new Intent(context, (Class<?>) GameReminderWidgetService.class);
        intent.putExtra("appWidgetId", i2);
        remoteViews.setRemoteAdapter(i2, R.id.reminder_list_widget, intent);
        d(context, remoteViews);
        AppWidgetManager.getInstance(context).updateAppWidget(i2, remoteViews);
        AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(i2, R.id.reminder_list_widget);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        GaLog.a("GameReminderWidget", "onDisabled");
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        GaLog.a("GameReminderWidget", "onEnabled");
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            GaLog.a("GameReminderWidget", "onReceive action = " + action);
            if ("com.zte.plugin.reminder.action.ACTION_NOTIFY_DATASET_CHANGED".equals(action)) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) GameReminderWidget.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.reminder_list_widget);
                GaLog.a("GameReminderWidget", "onReceive appWidgetIds.length= " + appWidgetIds.length);
                for (int i2 : appWidgetIds) {
                    c(context, i2);
                }
                return;
            }
            if (!"com.zte.plugin.reminder.action.ACTION_ITEM_CLICK".equals(action)) {
                super.onReceive(context, intent);
                return;
            }
            String stringExtra = intent.getStringExtra("package_name");
            if (TextUtils.isEmpty(stringExtra)) {
                GaLog.a("GameReminderWidget", "onReceive package name is null and not handler click!");
                return;
            }
            if (WechatHelper.i(stringExtra)) {
                WechatHelper.a().m(stringExtra, true);
                return;
            }
            try {
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(stringExtra));
                b(context, stringExtra);
            } catch (Exception e2) {
                e2.printStackTrace();
                GaLog.a("GameReminderWidget", "onReceive e = " + e2);
            }
        } catch (Exception e3) {
            GaLog.a("GameReminderWidget", "onReceive e = " + e3);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        GaLog.a("GameReminderWidget", "onUpdate:appWidgetIds.length" + iArr.length);
        for (int i2 : iArr) {
            c(context, i2);
        }
    }
}
