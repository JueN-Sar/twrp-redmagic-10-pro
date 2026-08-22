package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$1 implements SQLiteEventStore.Producer {

    /* renamed from: a, reason: collision with root package name */
    private final SchemaManager f10387a;

    private SQLiteEventStore$$Lambda$1(SchemaManager schemaManager) {
        this.f10387a = schemaManager;
    }

    public static SQLiteEventStore.Producer b(SchemaManager schemaManager) {
        return new SQLiteEventStore$$Lambda$1(schemaManager);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Producer
    public Object a() {
        return this.f10387a.getWritableDatabase();
    }
}
