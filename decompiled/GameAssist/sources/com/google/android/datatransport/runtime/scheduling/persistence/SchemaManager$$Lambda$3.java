package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager;

/* loaded from: classes.dex */
final /* synthetic */ class SchemaManager$$Lambda$3 implements SchemaManager.Migration {

    /* renamed from: a, reason: collision with root package name */
    private static final SchemaManager$$Lambda$3 f10429a = new SchemaManager$$Lambda$3();

    private SchemaManager$$Lambda$3() {
    }

    public static SchemaManager.Migration b() {
        return f10429a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
    }
}
