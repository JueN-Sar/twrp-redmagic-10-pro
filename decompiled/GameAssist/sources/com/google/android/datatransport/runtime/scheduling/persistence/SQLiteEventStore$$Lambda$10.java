package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$10 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final long f10388a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10389b;

    private SQLiteEventStore$$Lambda$10(long j2, TransportContext transportContext) {
        this.f10388a = j2;
        this.f10389b = transportContext;
    }

    public static SQLiteEventStore.Function a(long j2, TransportContext transportContext) {
        return new SQLiteEventStore$$Lambda$10(j2, transportContext);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.Y(this.f10388a, this.f10389b, (SQLiteDatabase) obj);
    }
}
