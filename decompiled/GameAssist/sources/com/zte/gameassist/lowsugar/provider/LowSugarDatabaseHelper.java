package com.zte.gameassist.lowsugar.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class LowSugarDatabaseHelper extends SQLiteOpenHelper {

    /* renamed from: c, reason: collision with root package name */
    private static volatile LowSugarDatabaseHelper f16923c;

    private LowSugarDatabaseHelper(Context context) {
        super(context, "low_sugar.db", (SQLiteDatabase.CursorFactory) null, 4);
        GaLog.a("LowSugarDatabaseHelper", "Database helper instance created");
    }

    public static LowSugarDatabaseHelper a(Context context) {
        if (f16923c == null) {
            synchronized (LowSugarDatabaseHelper.class) {
                try {
                    if (f16923c == null) {
                        f16923c = new LowSugarDatabaseHelper(context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        return f16923c;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        GaLog.a("LowSugarDatabaseHelper", "Creating database tables");
        sQLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, alarm_time INTEGER, time INTEGER, package TEXT, app_exist INTEGER DEFAULT 1, ocr_bitmap_dhash TEXT);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        GaLog.k("LowSugarDatabaseHelper", "Downgrading database from version " + i2 + " to " + i3);
        if (i2 > i3) {
            if (i2 >= 3 && i3 < 3) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE events DROP COLUMN app_exist");
                    GaLog.e("LowSugarDatabaseHelper", "Dropped column app_exist from table events");
                } catch (Exception e2) {
                    GaLog.b("LowSugarDatabaseHelper", "Error dropping column app_exist: " + e2.getMessage());
                }
            }
            if (i2 >= 4 && i3 < 4) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE events DROP COLUMN ocr_bitmap_dhash");
                    GaLog.e("LowSugarDatabaseHelper", "Dropped column ocr_bitmap_dhash from table events");
                } catch (Exception e3) {
                    GaLog.b("LowSugarDatabaseHelper", "Error dropping column ocr_bitmap_dhash: " + e3.getMessage());
                }
            }
        }
        GaLog.e("LowSugarDatabaseHelper", "Database downgrade from version " + i2 + " to " + i3 + " completed");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        GaLog.k("LowSugarDatabaseHelper", "Upgrading database from version " + i2 + " to " + i3);
        if (i2 < 3) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN app_exist INTEGER DEFAULT 1");
                GaLog.e("LowSugarDatabaseHelper", "Added column app_exist to table events");
            } catch (Exception e2) {
                GaLog.b("LowSugarDatabaseHelper", "Error adding column app_exist: " + e2.getMessage());
            }
        }
        if (i2 < 4) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN ocr_bitmap_dhash TEXT");
                GaLog.e("LowSugarDatabaseHelper", "Added column ocr_bitmap_dhash to table events");
            } catch (Exception e3) {
                GaLog.b("LowSugarDatabaseHelper", "Error adding column ocr_bitmap_dhash: " + e3.getMessage());
            }
        }
        GaLog.e("LowSugarDatabaseHelper", "Database upgrade from version " + i2 + " to " + i3 + " completed");
    }
}
