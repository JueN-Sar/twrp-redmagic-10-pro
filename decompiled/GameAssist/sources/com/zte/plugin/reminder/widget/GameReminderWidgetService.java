package com.zte.plugin.reminder.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.plugin.reminder.GameReminderDatabaseHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class GameReminderWidgetService extends RemoteViewsService {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f18094c = new Object();

    protected static class GameReminderFactory implements RemoteViewsService.RemoteViewsFactory {

        /* renamed from: a, reason: collision with root package name */
        private final Context f18095a;

        /* renamed from: b, reason: collision with root package name */
        private final int f18096b;

        /* renamed from: c, reason: collision with root package name */
        protected Cursor f18097c;

        /* renamed from: d, reason: collision with root package name */
        private final AppWidgetManager f18098d;

        public GameReminderFactory(Context context, Intent intent) {
            this.f18095a = context;
            int intExtra = intent.getIntExtra("appWidgetId", 0);
            this.f18096b = intExtra;
            this.f18098d = AppWidgetManager.getInstance(context);
            GaLog.j("GameReminderWidgetService", "MmsFactory intent: " + intent + "widget id: " + intExtra);
        }

        private Bitmap a(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        private String b(long j2) {
            return new SimpleDateFormat("MM/dd").format(new Date(j2));
        }

        private String c(long j2) {
            return new SimpleDateFormat("HH:mm").format(new Date(j2));
        }

        private Cursor e() {
            SQLiteDatabase readableDatabase = GameReminderDatabaseHelper.a(this.f18095a).getReadableDatabase();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("events");
            return sQLiteQueryBuilder.query(readableDatabase, null, "time>?", new String[]{Long.toString(System.currentTimeMillis())}, null, null, "time asc", null);
        }

        private void f(RemoteViews remoteViews, String str) {
            Drawable c2;
            PackageManager packageManager = this.f18095a.getPackageManager();
            try {
                if (TextUtils.isEmpty(str)) {
                    int i2 = R.drawable.game_reminder_zte_notification_icon;
                    if (ZteFeature.isRedMagicProduct()) {
                        i2 = R.drawable.game_reminder_notification_icon;
                    }
                    c2 = this.f18095a.getDrawable(i2);
                } else {
                    c2 = WechatHelper.i(str) ? WechatHelper.a().c(str, true) : packageManager.getApplicationIcon(str);
                }
                remoteViews.setImageViewBitmap(R.id.widget_event_icon, a(c2));
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }

        public void d() {
            RemoteViews remoteViews = new RemoteViews(this.f18095a.getPackageName(), R.layout.game_reminder_widget_view);
            Cursor cursor = this.f18097c;
            if (cursor == null || cursor.getCount() == 0) {
                GaLog.a("GameReminderWidgetService", "onLoadComplete cursor=" + this.f18097c);
                remoteViews.setViewVisibility(R.id.reminder_empty_widget, 0);
                remoteViews.setViewVisibility(R.id.reminder_empty_icon_widget, 0);
                remoteViews.setViewVisibility(R.id.reminder_list_widget, 8);
                remoteViews.setViewVisibility(R.id.reminder_title_content, 8);
            } else {
                GaLog.a("GameReminderWidgetService", "onLoadComplete");
                remoteViews.setViewVisibility(R.id.reminder_empty_widget, 8);
                remoteViews.setViewVisibility(R.id.reminder_empty_icon_widget, 8);
                remoteViews.setViewVisibility(R.id.reminder_list_widget, 0);
                remoteViews.setViewVisibility(R.id.reminder_title_content, 0);
            }
            this.f18098d.partiallyUpdateAppWidget(this.f18096b, remoteViews);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public int getCount() {
            synchronized (GameReminderWidgetService.f18094c) {
                try {
                    Cursor cursor = this.f18097c;
                    if (cursor == null) {
                        return 0;
                    }
                    return cursor.getCount();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public RemoteViews getLoadingView() {
            GaLog.a("GameReminderWidgetService", "getLoadingView");
            return new RemoteViews(this.f18095a.getPackageName(), R.layout.game_reminder_widget_loading);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public RemoteViews getViewAt(int i2) {
            GaLog.a("GameReminderWidgetService", "getViewAt position: " + i2);
            synchronized (GameReminderWidgetService.f18094c) {
                try {
                    Cursor cursor = this.f18097c;
                    if (cursor == null) {
                        GaLog.k("GameReminderWidgetService", "getViewAt null cursor");
                        return null;
                    }
                    if (!cursor.moveToPosition(i2)) {
                        GaLog.k("GameReminderWidgetService", "Failed to move to position: " + i2);
                        return null;
                    }
                    RemoteViews remoteViews = new RemoteViews(this.f18095a.getPackageName(), R.layout.game_reminder_widget_list_item);
                    Cursor cursor2 = this.f18097c;
                    String string = cursor2.getString(cursor2.getColumnIndex("package"));
                    Cursor cursor3 = this.f18097c;
                    String string2 = cursor3.getString(cursor3.getColumnIndex("title"));
                    Cursor cursor4 = this.f18097c;
                    long j2 = cursor4.getLong(cursor4.getColumnIndex("time"));
                    int i3 = R.id.widget_title;
                    if (TextUtils.isEmpty(string2)) {
                        string2 = this.f18095a.getString(R.string.game_reminder_input_hint);
                    }
                    remoteViews.setTextViewText(i3, string2);
                    remoteViews.setTextViewText(R.id.widget_date, b(j2));
                    remoteViews.setTextViewText(R.id.widget_time, c(j2));
                    f(remoteViews, string);
                    Intent intent = new Intent("com.zte.plugin.reminder.action.ACTION_ITEM_CLICK");
                    intent.putExtra("package_name", string);
                    remoteViews.setOnClickFillInIntent(R.id.widget_list_item, intent);
                    return remoteViews;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public boolean hasStableIds() {
            return true;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onCreate() {
            GaLog.a("GameReminderWidgetService", "onCreate");
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onDataSetChanged() {
            GaLog.a("GameReminderWidgetService", "onDataSetChanged");
            synchronized (GameReminderWidgetService.f18094c) {
                try {
                    Cursor cursor = this.f18097c;
                    if (cursor != null) {
                        cursor.close();
                        this.f18097c = null;
                    }
                    this.f18097c = e();
                    d();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onDestroy() {
            GaLog.a("GameReminderWidgetService", "onDestroy");
            synchronized (GameReminderWidgetService.f18094c) {
                try {
                    Cursor cursor = this.f18097c;
                    if (cursor != null && !cursor.isClosed()) {
                        this.f18097c.close();
                        this.f18097c = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GameReminderFactory(getApplicationContext(), intent);
    }
}
