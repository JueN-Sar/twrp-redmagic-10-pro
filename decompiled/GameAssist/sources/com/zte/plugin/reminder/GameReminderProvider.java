package com.zte.plugin.reminder;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/* loaded from: classes2.dex */
public class GameReminderProvider extends ContentProvider {

    /* renamed from: h, reason: collision with root package name */
    private static final UriMatcher f18027h;

    /* renamed from: c, reason: collision with root package name */
    private SQLiteOpenHelper f18028c;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f18027h = uriMatcher;
        uriMatcher.addURI("com.zte.plugin.reminder", null, 0);
        uriMatcher.addURI("com.zte.plugin.reminder", "#", 1);
    }

    private void b(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    protected SQLiteOpenHelper a() {
        return GameReminderDatabaseHelper.a(getContext());
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int i2 = -1;
        try {
            i2 = this.f18028c.getWritableDatabase().delete("events", str, strArr);
            if (i2 > 0) {
                b(uri);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i2;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android-dir/reminder";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            long insert = this.f18028c.getWritableDatabase().insert("events", null, contentValues);
            if (insert > 0) {
                b(uri);
            }
            return ContentUris.withAppendedId(uri, insert);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f18028c = a();
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursor = null;
        try {
            SQLiteDatabase readableDatabase = this.f18028c.getReadableDatabase();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("events");
            cursor = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2, null);
            if (cursor != null) {
                try {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return cursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i2 = -1;
        try {
            i2 = this.f18028c.getWritableDatabase().update("events", contentValues, str, strArr);
            if (i2 > 0) {
                b(uri);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i2;
    }
}
