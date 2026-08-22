package cn.nubia.gamelauncher.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes.dex */
public class AppAddProvider extends ContentProvider {
    public static final String APPADD_AUTO_OPEN_FAN = "autoOpenFan";
    public static final String APPADD_AUTO_OPEN_LIQUID = "autoOpenLiquid";
    public static final String APPADD_COMPONENT = "component";
    public static final String APPADD_ID = "_id";
    public static final String APPADD_IMAGE_URL = "imageUrl";
    public static final String APPADD_ISADD = "isAdd";
    public static final String APPADD_ISGAME = "isGame";
    public static final String APPADD_LAST_START_TIME = "lastStartTime";
    public static final String APPADD_LAST_UPDATE_URL_TIME = "lastUpdateUrlTime";
    public static final String APPADD_MEDIUM_IMAGE_URL = "middleImageUrl";
    public static final String APPADD_NAME = "gamename";
    public static final String APPADD_NET_URL = "netUrl";
    public static final String APPADD_URL_TYPE = "urlType";
    public static final String APPADD_WIDGET_URL = "widgetUrl";
    public static final String AUTHORITY = "cn.nubia.gamelauncher.db.AppAddProvider";
    public static final String MAX_ID = "max(_id)";
    public static final String NOTIFY = "notify";
    public static final String RESOURCE_LIB_DATA = "data";
    public static final String RESOURCE_LIB_FILENAME = "file_name";
    public static final String RESOURCE_LIB_ID = "_id";
    public static final String TABLE_APPADD_NAME = "appadd";
    public static final String TABLE_BARRAGE_MESSAGE_SOURCE = "barrage_message_source";
    public static final String TABLE_RESOURCE_LIB_NAME = "resource_lib";
    public static final String TABLE_SHORTCUT_NAME = "shortcut_adds";
    public static final String TABLE_USER_REMOVE_NAME = "user_remove";
    public static final String TABLE_VERIFIED_NAME = "verified_apps";
    public static final String TAG = "AppAddProvider";
    private SQLiteOpenHelper mSqliteHelper = null;

    private String getTableName(Uri uri) {
        if (uri.getPathSegments().get(0) != null) {
            return uri.getPathSegments().get(0);
        }
        return null;
    }

    private void sendNotify(Uri uri) {
        if (uri == null || !"true".equals(uri.getQueryParameter("notify"))) {
            return;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        Log.i(TAG, "sendNotify uri == " + uri);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        return doCall(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mSqliteHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        if (tableName == null) {
            Log.i(TAG, "delete uri is error format");
            return -1;
        }
        writableDatabase.delete(tableName, str, strArr);
        sendNotify(uri);
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0216, code lost:
    
        return r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle doCall(java.lang.String r6, java.lang.String r7, android.os.Bundle r8) {
        /*
            Method dump skipped, instructions count: 596
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.db.AppAddProvider.doCall(java.lang.String, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.mSqliteHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        if (tableName == null) {
            Log.i(TAG, "insert uri is error format");
            return null;
        }
        long insert = writableDatabase.insert(tableName, null, contentValues);
        sendNotify(uri);
        Log.i(TAG, "tableName == " + tableName + " provider insert value == " + contentValues + " result == " + insert);
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mSqliteHelper = new AppAddDataBaseHelper(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.mSqliteHelper.getReadableDatabase();
        String tableName = getTableName(uri);
        String str3 = null;
        if (tableName == null) {
            Log.i(TAG, "query uri is error format");
            return null;
        }
        Cursor rawQuery = MAX_ID.equals(str) ? readableDatabase.rawQuery("select max(_id) from " + tableName, null) : readableDatabase.query(tableName, strArr, str, strArr2, null, null, null);
        try {
            str3 = getCallingPackage();
        } catch (SecurityException e) {
            Log.w(TAG, "Failed to get calling package", e);
        }
        Log.d(TAG, "query() cursor = " + rawQuery + ", selection : " + str + ", calling : " + str3);
        if (rawQuery != null) {
            Log.d(TAG, "query() cursor.getCount() = " + rawQuery.getCount());
        }
        return rawQuery;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mSqliteHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        if (tableName == null) {
            Log.i(TAG, "delete uri is error format");
            return -1;
        }
        writableDatabase.update(tableName, contentValues, str, strArr);
        sendNotify(uri);
        return 0;
    }
}
