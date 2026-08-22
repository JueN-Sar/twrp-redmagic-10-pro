package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager;

/* loaded from: classes.dex */
final /* synthetic */ class SchemaManager$$Lambda$2 implements SchemaManager.Migration {

    /* renamed from: a, reason: collision with root package name */
    private static final SchemaManager$$Lambda$2 f10428a = new SchemaManager$$Lambda$2();

    private SchemaManager$$Lambda$2() {
    }

    public static SchemaManager.Migration b() {
        return f10428a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
    public void a(SQLiteDatabase sQLiteDatabase) {
        SchemaManager.d(sQLiteDatabase);
    }
}
