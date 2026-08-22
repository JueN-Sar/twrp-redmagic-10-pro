package com.zte.plugin.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes2.dex */
public class GameReminderDatabaseHelper extends SQLiteOpenHelper {

    /* renamed from: c, reason: collision with root package name */
    private static volatile GameReminderDatabaseHelper f18024c;

    private GameReminderDatabaseHelper(Context context) {
        super(context, "reminder.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static GameReminderDatabaseHelper a(Context context) {
        if (f18024c == null) {
            synchronized (GameReminderDatabaseHelper.class) {
                try {
                    if (f18024c == null) {
                        f18024c = new GameReminderDatabaseHelper(context);
                    }
                } finally {
                }
            }
        }
        return f18024c;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, time INTEGER, package TEXT, alarm INTEGER DEFAULT 0, postpone_num INTEGER DEFAULT 0);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
