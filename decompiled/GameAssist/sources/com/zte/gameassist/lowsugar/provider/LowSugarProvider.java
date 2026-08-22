package com.zte.gameassist.lowsugar.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class LowSugarProvider extends ContentProvider {

    /* renamed from: h, reason: collision with root package name */
    private static final UriMatcher f16942h;

    /* renamed from: c, reason: collision with root package name */
    private SQLiteOpenHelper f16943c;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f16942h = uriMatcher;
        uriMatcher.addURI("cn.nubia.gameassist.lowsugar", "events", 0);
        uriMatcher.addURI("cn.nubia.gameassist.lowsugar", "events/#", 1);
    }

    private void a(Uri uri) {
        GaLog.b("LowSugarProvider", "notifyChange uri = " + uri);
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int delete;
        SQLiteDatabase writableDatabase = this.f16943c.getWritableDatabase();
        if (f16942h.match(uri) != 1) {
            delete = writableDatabase.delete("events", str, strArr);
        } else {
            String str2 = "_id = " + uri.getLastPathSegment();
            if (str != null && str.length() > 0) {
                str2 = str2 + " AND (" + str + ")";
            }
            delete = writableDatabase.delete("events", str2, strArr);
        }
        if (delete > 0) {
            a(uri);
        } else {
            GaLog.b("LowSugarProvider", "Failed to delete from " + uri);
        }
        return delete;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = f16942h.match(uri);
        if (match == 0) {
            return "vnd.android-dir/low_sugar_event";
        }
        if (match == 1) {
            return "vnd.android-dir/low_sugar_event/item";
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        long insert = this.f16943c.getWritableDatabase().insert("events", null, contentValues);
        if (insert > 0) {
            a(uri);
            return ContentUris.withAppendedId(uri, insert);
        }
        GaLog.b("LowSugarProvider", "Failed to insert row into " + uri);
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f16943c = LowSugarDatabaseHelper.a(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.f16943c.getReadableDatabase();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("events");
        if (f16942h.match(uri) == 1) {
            sQLiteQueryBuilder.appendWhere("_id = " + uri.getLastPathSegment());
        }
        Cursor query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
        if (query != null) {
            query.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return query;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int update;
        SQLiteDatabase writableDatabase = this.f16943c.getWritableDatabase();
        if (f16942h.match(uri) != 1) {
            update = writableDatabase.update("events", contentValues, str, strArr);
        } else {
            String str2 = "_id = " + uri.getLastPathSegment();
            if (str != null && str.length() > 0) {
                str2 = str2 + " AND (" + str + ")";
            }
            update = writableDatabase.update("events", contentValues, str2, strArr);
        }
        if (update > 0) {
            a(uri);
        } else {
            GaLog.b("LowSugarProvider", "Failed to update into " + uri);
        }
        return update;
    }
}
