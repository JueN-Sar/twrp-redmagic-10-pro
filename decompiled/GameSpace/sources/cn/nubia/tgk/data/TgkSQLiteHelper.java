package cn.nubia.tgk.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.tgk.data.TgkDataContract;
import cn.nubia.tgk.util.TgkUtils;

/* loaded from: classes2.dex */
public class TgkSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tgk.db";
    private static final int DATABASE_VERSION = 5;
    private static final String TAG = "TgkSQLiteHelper";

    public TgkSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 5);
    }

    private void createMoreInfoTable(SQLiteDatabase sQLiteDatabase) {
        Log.e(TAG, "Create game more info table!");
        sQLiteDatabase.execSQL("CREATE TABLE game_more_info_table(package_name TEXT,top_visual_effect_sw INTEGER,center_visual_effect_sw INTEGER,center_visual_effect_transparency INTEGER);");
    }

    private void updateAddData(SQLiteDatabase sQLiteDatabase) {
        try {
            Cursor query = sQLiteDatabase.query(TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, null, null, null, null, null, null);
            if (query != null && query.moveToFirst()) {
                do {
                    TgkData cursorToTgkDataNoPicture = TgkHelper.cursorToTgkDataNoPicture(query);
                    ContentValues contentValues = new ContentValues();
                    long j = cursorToTgkDataNoPicture.ID;
                    String genUniqueId = TgkUtils.genUniqueId(cursorToTgkDataNoPicture);
                    contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_KEY, genUniqueId);
                    Log.d(TAG, "updateAddData uniqueId : " + genUniqueId + " caseId : " + j);
                    if ((cursorToTgkDataNoPicture.state & 2) > 0) {
                        Log.d(TAG, "updateAddData state : " + cursorToTgkDataNoPicture.state);
                        contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_CHANGE, (Integer) 1);
                    }
                    contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    sQLiteDatabase.update(TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, contentValues, "_id = ? ", new String[]{j + ""});
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
            int i = 1;
            String str = TgkDataContract.TgkEntry.TGK_CASE_KEY;
            Cursor query2 = sQLiteDatabase.query(TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, null, null, null, null, null, null);
            if (query2 != null && query2.moveToFirst()) {
                while (true) {
                    TgkData cursorToTgkDataNoPicture2 = TgkHelper.cursorToTgkDataNoPicture(query2);
                    long j2 = cursorToTgkDataNoPicture2.ID;
                    String genUniqueId2 = TgkUtils.genUniqueId(cursorToTgkDataNoPicture2);
                    ContentValues contentValues2 = new ContentValues();
                    String str2 = str;
                    contentValues2.put(str2, genUniqueId2);
                    contentValues2.put(TgkDataContract.TgkEntry.TGK_CASE_CHANGE, Integer.valueOf(i));
                    contentValues2.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    Log.d(TAG, "updateAddData uniqueId : " + genUniqueId2 + " caseId : " + j2);
                    String[] strArr = new String[i];
                    strArr[0] = j2 + "";
                    sQLiteDatabase.update(TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, contentValues2, "_id = ? ", strArr);
                    if (!query2.moveToNext()) {
                        break;
                    }
                    str = str2;
                    i = 1;
                }
            }
            if (query2 != null) {
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "updateAllPolicyUniqueId exception : " + e);
        }
    }

    private void updateIsLandscape(SQLiteDatabase sQLiteDatabase) {
        try {
            Cursor query = sQLiteDatabase.query(TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, null, null, null, null, null, null);
            if (query != null && query.moveToFirst()) {
                do {
                    TgkData cursorToTgkDataNoPicture = TgkHelper.cursorToTgkDataNoPicture(query);
                    ContentValues contentValues = new ContentValues();
                    long j = cursorToTgkDataNoPicture.ID;
                    contentValues.put(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE, (Integer) 1);
                    sQLiteDatabase.update(TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, contentValues, "_id = ? ", new String[]{j + ""});
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
            Cursor query2 = sQLiteDatabase.query(TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, null, null, null, null, null, null);
            if (query2 != null && query2.moveToFirst()) {
                do {
                    long j2 = TgkHelper.cursorToTgkDataNoPicture(query2).ID;
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE, (Integer) 1);
                    sQLiteDatabase.update(TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, contentValues2, "_id = ? ", new String[]{j2 + ""});
                } while (query2.moveToNext());
            }
            if (query2 != null) {
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "updateIsLandscape exception : " + e);
        }
    }

    private void updateTime(SQLiteDatabase sQLiteDatabase) {
        try {
            Cursor query = sQLiteDatabase.query(TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, null, null, null, null, null, null);
            if (query != null && query.moveToFirst()) {
                do {
                    TgkData cursorToTgkDataNoPicture = TgkHelper.cursorToTgkDataNoPicture(query);
                    ContentValues contentValues = new ContentValues();
                    long j = cursorToTgkDataNoPicture.ID;
                    contentValues.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    sQLiteDatabase.update(TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, contentValues, "_id = ? ", new String[]{j + ""});
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
            Cursor query2 = sQLiteDatabase.query(TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, null, null, null, null, null, null);
            if (query2 != null && query2.moveToFirst()) {
                do {
                    long j2 = TgkHelper.cursorToTgkDataNoPicture(query2).ID;
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    sQLiteDatabase.update(TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, contentValues2, "_id = ? ", new String[]{j2 + ""});
                } while (query2.moveToNext());
            }
            if (query2 != null) {
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "updateTime exception : " + e);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.e(TAG, "onCreate");
        sQLiteDatabase.execSQL("CREATE TABLE preset_case_table(_id INTEGER PRIMARY KEY,state INTEGER,original_name TEXT,show_name TEXT,package_name TEXT,main_sw INTEGER,left_sw INTEGER,right_sw INTEGER,middle_sw INTEGER,vibrate_sw INTEGER,left_sensitivity INTEGER,right_sensitivity INTEGER,left_points TEXT,right_points TEXT,middle_points TEXT,left_option INTEGER,right_option INTEGER,middle_option INTEGER,picture BLOB,shot_picture TEXT,uniqueId TEXT,change INTEGER,update_time INTEGER,isLandscape INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE import_case_table(_id INTEGER PRIMARY KEY,state INTEGER,original_name TEXT,show_name TEXT,package_name TEXT,main_sw INTEGER,left_sw INTEGER,right_sw INTEGER,middle_sw INTEGER,vibrate_sw INTEGER,left_sensitivity INTEGER,right_sensitivity INTEGER,left_points TEXT,right_points TEXT,middle_points TEXT,left_option INTEGER,right_option INTEGER,middle_option INTEGER,picture BLOB,shot_picture TEXT,uniqueId TEXT,change INTEGER,update_time INTEGER,isLandscape INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE lamp_case_table(_id INTEGER PRIMARY KEY,state INTEGER,package_name TEXT);");
        createMoreInfoTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.e(TAG, "onUpgrade oldVersion = " + i + " ; newVersion = " + i2);
        if (1 == i && 2 == i2) {
            createMoreInfoTable(sQLiteDatabase);
            return;
        }
        if (2 == i && 3 == i2) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN uniqueId TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN shot_picture TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN change INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN uniqueId TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN shot_picture TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN change INTEGER");
                updateAddData(sQLiteDatabase);
                return;
            } catch (Exception e) {
                Log.e(TAG, "onUpgrade e = " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        if (2 == i && 4 == i2) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN uniqueId TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN shot_picture TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN change INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN update_time INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN uniqueId TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN shot_picture TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN change INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN update_time INTEGER");
                updateAddData(sQLiteDatabase);
                return;
            } catch (Exception e2) {
                Log.e(TAG, "onUpgrade e1 = " + e2.getMessage());
                e2.printStackTrace();
                return;
            }
        }
        if (3 == i && 4 == i2) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN update_time INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN update_time INTEGER");
                updateTime(sQLiteDatabase);
                return;
            } catch (Exception e3) {
                Log.e(TAG, "onUpgrade e2 = " + e3.getMessage());
                e3.printStackTrace();
                return;
            }
        }
        if (4 == i && 5 == i2) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE preset_case_table ADD COLUMN isLandscape INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE import_case_table ADD COLUMN isLandscape INTEGER");
                updateIsLandscape(sQLiteDatabase);
            } catch (Exception e4) {
                Log.e(TAG, "onUpgrade e2 = " + e4.getMessage());
                e4.printStackTrace();
            }
        }
    }
}
