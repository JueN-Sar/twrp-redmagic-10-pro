package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.List;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$15 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final SQLiteEventStore f10395a;

    /* renamed from: b, reason: collision with root package name */
    private final List f10396b;

    /* renamed from: c, reason: collision with root package name */
    private final TransportContext f10397c;

    private SQLiteEventStore$$Lambda$15(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext) {
        this.f10395a = sQLiteEventStore;
        this.f10396b = list;
        this.f10397c = transportContext;
    }

    public static SQLiteEventStore.Function a(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext) {
        return new SQLiteEventStore$$Lambda$15(sQLiteEventStore, list, transportContext);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.L(this.f10395a, this.f10396b, this.f10397c, (Cursor) obj);
    }
}
