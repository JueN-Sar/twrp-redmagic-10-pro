package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager;

/* loaded from: classes.dex */
final /* synthetic */ class SchemaManager$$Lambda$4 implements SchemaManager.Migration {

    /* renamed from: a, reason: collision with root package name */
    private static final SchemaManager$$Lambda$4 f10430a = new SchemaManager$$Lambda$4();

    private SchemaManager$$Lambda$4() {
    }

    public static SchemaManager.Migration b() {
        return f10430a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
    public void a(SQLiteDatabase sQLiteDatabase) {
        SchemaManager.h(sQLiteDatabase);
    }
}
