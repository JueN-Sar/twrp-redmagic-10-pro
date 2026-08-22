package cn.nubia.gamelauncher.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import cn.nubia.gamecenter.settings.barrageMessage.AppBean;
import cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AppAddDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "appadd.db";
    private static final int DATABASE_VERSION = 10;
    private String CREATE_APPADD;
    private String CREATE_BARRAGE_MESSAGE_SOURCE;
    private String CREATE_USER_REMOVE;
    private String CREATE_VERIFIED_APPS;
    private String SHORTCUT_ADD;
    private Context mContext;

    public AppAddDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 10);
        this.CREATE_APPADD = "create table if not exists appadd( _id integer primary key autoincrement, component text not null, isAdd integer,isGame integer,urlType text,imageUrl text,netUrl text,gamename text,middleImageUrl text,lastStartTime long,lastUpdateUrlTime long,widgetUrl text,autoOpenFan integer NOT NULL DEFAULT 1,autoOpenLiquid integer NOT NULL DEFAULT 1)";
        this.CREATE_USER_REMOVE = "create table if not exists user_remove( _id integer primary key autoincrement, component text not null)";
        this.CREATE_VERIFIED_APPS = "create table if not exists verified_apps( _id integer primary key autoincrement, component text not null)";
        this.CREATE_BARRAGE_MESSAGE_SOURCE = "create table if not exists barrage_message_source( _id integer primary key autoincrement, component text not null, isAdd integer NOT NULL DEFAULT 1)";
        this.SHORTCUT_ADD = "create table if not exists shortcut_adds( _id integer primary key autoincrement, component text not null, shortcutId text not null, label text not null, hashcode text not null, urlType text,imageUrl text,netUrl text,middleImageUrl text,lastStartTime long,lastUpdateUrlTime long,widgetUrl text,autoOpenFan integer NOT NULL DEFAULT 1,autoOpenLiquid integer NOT NULL DEFAULT 1)";
        this.mContext = context;
    }

    private boolean addColumn(SQLiteDatabase sQLiteDatabase, String str, String str2, boolean z, String str3) {
        Log.d("db", "addColumn(s) columnName : " + str);
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("ALTER TABLE appadd ADD COLUMN " + str + " " + str2 + (z ? " NOT NULL DEFAULT " + str3 : "") + ";");
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            Log.d("db", "addColumn(e) OK");
            return true;
        } catch (SQLException unused) {
            sQLiteDatabase.endTransaction();
            return false;
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    private void deleteSystemSource(SQLiteDatabase sQLiteDatabase) {
        Log.i(AppAddProvider.TAG, "deleteSystemSource");
        if (this.mContext == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                List<AppBean> source = BarrageMessageSourceActivity.getSource(this.mContext);
                List<String> allApp = BarrageMessageSourceActivity.getAllApp(this.mContext);
                for (AppBean appBean : source) {
                    if (!allApp.contains(appBean.getPackageName())) {
                        sQLiteDatabase.delete(AppAddProvider.TABLE_BARRAGE_MESSAGE_SOURCE, "component= ?", new String[]{appBean.getPackageName()});
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                this.mContext.getContentResolver().notifyChange(ConstantVariable.BARRAGE_MESSAGE_SOURCE_URI, null);
            } catch (SQLException e) {
                Log.wtf(AppAddProvider.TAG, e);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void doDbUpgrade(SQLiteDatabase sQLiteDatabase, int i) {
        Log.d("db", "doDbUpgrade() oldVersion : " + i);
        switch (i) {
            case 1:
                addColumn(sQLiteDatabase, "middleImageUrl", "text", false, null);
                addColumn(sQLiteDatabase, "lastStartTime", "long", true, "0");
                break;
            case 2:
                sQLiteDatabase.execSQL(this.CREATE_VERIFIED_APPS);
                addColumn(sQLiteDatabase, "lastUpdateUrlTime", "long", true, "0");
                break;
            case 3:
                addColumn(sQLiteDatabase, "widgetUrl", "text", false, null);
                break;
            case 4:
                addColumn(sQLiteDatabase, "autoOpenFan", "integer", true, "1");
                break;
            case 5:
                sQLiteDatabase.execSQL(this.CREATE_BARRAGE_MESSAGE_SOURCE);
                moveBarrageMessageSource(sQLiteDatabase);
                break;
            case 6:
                deleteSystemSource(sQLiteDatabase);
                break;
            case 7:
                addColumn(sQLiteDatabase, "urlType", "text", false, null);
                addColumn(sQLiteDatabase, "netUrl", "text", false, null);
                break;
            case 8:
                sQLiteDatabase.execSQL(this.SHORTCUT_ADD);
                break;
            case 9:
                addColumn(sQLiteDatabase, "autoOpenLiquid", "integer", true, "1");
                shortCutAddColumn(sQLiteDatabase, "autoOpenLiquid", "integer", true, "1");
                break;
        }
    }

    private void initBarrageMessageSource(SQLiteDatabase sQLiteDatabase) {
        Log.i(AppAddProvider.TAG, "initBarrageMessageSource");
        if (this.mContext == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                Iterator<String> it = BarrageMessageSourceActivity.getAllApp(this.mContext).iterator();
                while (it.hasNext()) {
                    insertBarrageMessageSource(sQLiteDatabase, it.next(), true);
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e) {
                Log.wtf(AppAddProvider.TAG, e);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void insertBarrageMessageSource(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("component", str);
        contentValues.put("isAdd", Boolean.valueOf(z));
        sQLiteDatabase.insert(AppAddProvider.TABLE_BARRAGE_MESSAGE_SOURCE, null, contentValues);
    }

    private void moveBarrageMessageSource(SQLiteDatabase sQLiteDatabase) {
        Log.i(AppAddProvider.TAG, "moveBarrageMessageSource");
        if (this.mContext == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                for (AppBean appBean : BarrageMessageSourceActivity.getSource(this.mContext)) {
                    insertBarrageMessageSource(sQLiteDatabase, appBean.getPackageName(), appBean.isChecked());
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e) {
                Log.wtf(AppAddProvider.TAG, e);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private boolean shortCutAddColumn(SQLiteDatabase sQLiteDatabase, String str, String str2, boolean z, String str3) {
        Log.d("db", "shortCutAddColumn(s) columnName : " + str);
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("ALTER TABLE shortcut_adds ADD COLUMN " + str + " " + str2 + (z ? " NOT NULL DEFAULT " + str3 : "") + ";");
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            Log.d("db", "shortCutAddColumn(e) OK");
            return true;
        } catch (SQLException unused) {
            sQLiteDatabase.endTransaction();
            return false;
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.d("db", "onCreate()");
        sQLiteDatabase.execSQL(this.CREATE_APPADD);
        sQLiteDatabase.execSQL(this.CREATE_USER_REMOVE);
        sQLiteDatabase.execSQL(this.CREATE_VERIFIED_APPS);
        sQLiteDatabase.execSQL(this.CREATE_BARRAGE_MESSAGE_SOURCE);
        sQLiteDatabase.execSQL(this.SHORTCUT_ADD);
        initBarrageMessageSource(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d("db", "onUpgrade() oldVersion : " + i + ", newVersion : " + i2);
        while (i < i2) {
            doDbUpgrade(sQLiteDatabase, i);
            i++;
        }
    }
}
