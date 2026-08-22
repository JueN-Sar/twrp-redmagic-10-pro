package cn.nubia.tgk.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import cn.nubia.globalsearch.GlobalSearchConstants;
import cn.nubia.tgk.data.TgkDataContract;
import cn.nubia.tgk.util.TgkUtils;

/* loaded from: classes2.dex */
public class TgkDataProvider extends ContentProvider {
    public static final String AUTHORITY = "cn.nubia.tgk.data.TgkDataProvider";
    private static final String TAG = "TgkDataProvider";
    private Context mContext;
    private SQLiteOpenHelper mSqliteHelper = null;

    private boolean checkTableName(String str) {
        if (str != null) {
            return true;
        }
        Log.e(TAG, "update table name is null!");
        return false;
    }

    private String getTableName(Uri uri) {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(AUTHORITY, TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME, 0);
        uriMatcher.addURI(AUTHORITY, TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME, 1);
        uriMatcher.addURI(AUTHORITY, TgkDataContract.TgkEntry.GAME_MORE_INFO_TABLE_NAME, 10);
        uriMatcher.addURI(AUTHORITY, TgkDataContract.TgkEntry.LAMP_CASE_TABLE_NAME, 2);
        int match = uriMatcher.match(uri);
        if (match == 0) {
            return TgkDataContract.TgkEntry.PRESET_CASE_TABLE_NAME;
        }
        if (match == 1) {
            return TgkDataContract.TgkEntry.IMPORT_CASE_TABLE_NAME;
        }
        if (match == 2) {
            return TgkDataContract.TgkEntry.LAMP_CASE_TABLE_NAME;
        }
        if (match != 10) {
            return null;
        }
        return TgkDataContract.TgkEntry.GAME_MORE_INFO_TABLE_NAME;
    }

    private void sendNotify(Uri uri) {
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Log.i(TAG, "call method : " + str);
        if ("getTgkFileUri".equals(str)) {
            if (bundle == null) {
                return null;
            }
            String sharedFileUri = TgkUtils.getSharedFileUri(this.mContext, bundle.getInt("_id"), bundle.getInt("state"), bundle.getString("packageName"));
            Log.i(TAG, "getStrategyFileUri return uri " + sharedFileUri);
            Bundle bundle2 = new Bundle();
            bundle2.putString("sharedUri", sharedFileUri);
            return bundle2;
        }
        if (!"applyTgkCase".equals(str)) {
            if ("getTgkEnables".equals(str)) {
                return TgkUtils.getTgkEnables(this.mContext);
            }
            return null;
        }
        if (bundle == null) {
            return null;
        }
        return TgkUtils.applyTgkCase(this.mContext, bundle.getString(GlobalSearchConstants.NAME), bundle.getString(TgkDataContract.TgkEntry.TGK_CASE_KEY), bundle.getString("gamePkgName"), bundle.getString("fileUri"), bundle.getInt("screenWidth"), bundle.getInt("screenHeight"));
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mSqliteHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        if (!checkTableName(tableName)) {
            return -1;
        }
        int delete = writableDatabase.delete(tableName, str, strArr);
        writableDatabase.close();
        return delete;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.mSqliteHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        if (!checkTableName(tableName)) {
            return null;
        }
        long insert = writableDatabase.insert(tableName, null, contentValues);
        if (insert > 0) {
            return ContentUris.withAppendedId(uri, insert);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Log.e(TAG, "in onCreate");
        this.mContext = getContext().getApplicationContext();
        this.mSqliteHelper = new TgkSQLiteHelper(this.mContext);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String tableName = getTableName(uri);
        if (checkTableName(tableName)) {
            return this.mSqliteHelper.getReadableDatabase().query(tableName, strArr, str, strArr2, null, null, str2);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mSqliteHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        if (checkTableName(tableName)) {
            return writableDatabase.update(tableName, contentValues, str, strArr);
        }
        return -1;
    }
}
