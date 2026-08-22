package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
final class SchemaManager extends SQLiteOpenHelper {

    /* renamed from: i, reason: collision with root package name */
    static int f10419i = 4;

    /* renamed from: j, reason: collision with root package name */
    private static final Migration f10420j;

    /* renamed from: k, reason: collision with root package name */
    private static final Migration f10421k;

    /* renamed from: l, reason: collision with root package name */
    private static final Migration f10422l;

    /* renamed from: m, reason: collision with root package name */
    private static final Migration f10423m;

    /* renamed from: n, reason: collision with root package name */
    private static final List f10424n;

    /* renamed from: c, reason: collision with root package name */
    private final int f10425c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f10426h;

    public interface Migration {
        void a(SQLiteDatabase sQLiteDatabase);
    }

    static {
        Migration b2 = SchemaManager$$Lambda$1.b();
        f10420j = b2;
        Migration b3 = SchemaManager$$Lambda$2.b();
        f10421k = b3;
        Migration b4 = SchemaManager$$Lambda$3.b();
        f10422l = b4;
        Migration b5 = SchemaManager$$Lambda$4.b();
        f10423m = b5;
        f10424n = Arrays.asList(b2, b3, b4, b5);
    }

    SchemaManager(Context context, String str, int i2) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i2);
        this.f10426h = false;
        this.f10425c = i2;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        if (this.f10426h) {
            return;
        }
        onConfigure(sQLiteDatabase);
    }

    static /* synthetic */ void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY, context_id INTEGER NOT NULL, transport_name TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, uptime_ms INTEGER NOT NULL, payload BLOB NOT NULL, code INTEGER, num_attempts INTEGER NOT NULL,FOREIGN KEY (context_id) REFERENCES transport_contexts(_id) ON DELETE CASCADE)");
        sQLiteDatabase.execSQL("CREATE TABLE event_metadata (_id INTEGER PRIMARY KEY, event_id INTEGER NOT NULL, name TEXT NOT NULL, value TEXT NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE)");
        sQLiteDatabase.execSQL("CREATE TABLE transport_contexts (_id INTEGER PRIMARY KEY, backend_name TEXT NOT NULL, priority INTEGER NOT NULL, next_request_ms INTEGER NOT NULL)");
        sQLiteDatabase.execSQL("CREATE INDEX events_backend_id on events(context_id)");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX contexts_backend_priority on transport_contexts(backend_name, priority)");
    }

    static /* synthetic */ void d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE transport_contexts ADD COLUMN extras BLOB");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX contexts_backend_priority_extras on transport_contexts(backend_name, priority, extras)");
        sQLiteDatabase.execSQL("DROP INDEX contexts_backend_priority");
    }

    static /* synthetic */ void h(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN inline BOOLEAN NOT NULL DEFAULT 1");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS event_payloads");
        sQLiteDatabase.execSQL("CREATE TABLE event_payloads (sequence_num INTEGER NOT NULL, event_id INTEGER NOT NULL, bytes BLOB NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE,PRIMARY KEY (sequence_num, event_id))");
    }

    private void i(SQLiteDatabase sQLiteDatabase, int i2) {
        a(sQLiteDatabase);
        j(sQLiteDatabase, 0, i2);
    }

    private void j(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        List list = f10424n;
        if (i3 <= list.size()) {
            while (i2 < i3) {
                ((Migration) f10424n.get(i2)).a(sQLiteDatabase);
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("Migration from " + i2 + " to " + i3 + " was requested, but cannot be performed. Only " + list.size() + " migrations are provided");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        this.f10426h = true;
        sQLiteDatabase.rawQuery("PRAGMA busy_timeout=0;", new String[0]).close();
        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        i(sQLiteDatabase, this.f10425c);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("DROP TABLE events");
        sQLiteDatabase.execSQL("DROP TABLE event_metadata");
        sQLiteDatabase.execSQL("DROP TABLE transport_contexts");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS event_payloads");
        i(sQLiteDatabase, i3);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        a(sQLiteDatabase);
        j(sQLiteDatabase, i2, i3);
    }
}
