package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$5 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final SQLiteEventStore f10405a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10406b;

    /* renamed from: c, reason: collision with root package name */
    private final EventInternal f10407c;

    private SQLiteEventStore$$Lambda$5(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, EventInternal eventInternal) {
        this.f10405a = sQLiteEventStore;
        this.f10406b = transportContext;
        this.f10407c = eventInternal;
    }

    public static SQLiteEventStore.Function a(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, EventInternal eventInternal) {
        return new SQLiteEventStore$$Lambda$5(sQLiteEventStore, transportContext, eventInternal);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.R(this.f10405a, this.f10406b, this.f10407c, (SQLiteDatabase) obj);
    }
}
