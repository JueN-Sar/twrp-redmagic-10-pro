package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$11 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final SQLiteEventStore f10390a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10391b;

    private SQLiteEventStore$$Lambda$11(SQLiteEventStore sQLiteEventStore, TransportContext transportContext) {
        this.f10390a = sQLiteEventStore;
        this.f10391b = transportContext;
    }

    public static SQLiteEventStore.Function a(SQLiteEventStore sQLiteEventStore, TransportContext transportContext) {
        return new SQLiteEventStore$$Lambda$11(sQLiteEventStore, transportContext);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.I(this.f10390a, this.f10391b, (SQLiteDatabase) obj);
    }
}
