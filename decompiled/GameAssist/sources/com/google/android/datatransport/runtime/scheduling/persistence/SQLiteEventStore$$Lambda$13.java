package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$13 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final long f10393a;

    private SQLiteEventStore$$Lambda$13(long j2) {
        this.f10393a = j2;
    }

    public static SQLiteEventStore.Function a(long j2) {
        return new SQLiteEventStore$$Lambda$13(j2);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        Integer valueOf;
        valueOf = Integer.valueOf(((SQLiteDatabase) obj).delete("events", "timestamp_ms < ?", new String[]{String.valueOf(this.f10393a)}));
        return valueOf;
    }
}
