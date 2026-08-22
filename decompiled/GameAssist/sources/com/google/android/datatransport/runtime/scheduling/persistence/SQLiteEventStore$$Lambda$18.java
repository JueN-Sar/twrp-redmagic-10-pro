package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$18 implements SQLiteEventStore.Producer {

    /* renamed from: a, reason: collision with root package name */
    private final SQLiteDatabase f10400a;

    private SQLiteEventStore$$Lambda$18(SQLiteDatabase sQLiteDatabase) {
        this.f10400a = sQLiteDatabase;
    }

    public static SQLiteEventStore.Producer b(SQLiteDatabase sQLiteDatabase) {
        return new SQLiteEventStore$$Lambda$18(sQLiteDatabase);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Producer
    public Object a() {
        return SQLiteEventStore.t(this.f10400a);
    }
}
