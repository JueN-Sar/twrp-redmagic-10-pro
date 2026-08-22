package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$9 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final SQLiteEventStore f10411a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10412b;

    private SQLiteEventStore$$Lambda$9(SQLiteEventStore sQLiteEventStore, TransportContext transportContext) {
        this.f10411a = sQLiteEventStore;
        this.f10412b = transportContext;
    }

    public static SQLiteEventStore.Function a(SQLiteEventStore sQLiteEventStore, TransportContext transportContext) {
        return new SQLiteEventStore$$Lambda$9(sQLiteEventStore, transportContext);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.E(this.f10411a, this.f10412b, (SQLiteDatabase) obj);
    }
}
