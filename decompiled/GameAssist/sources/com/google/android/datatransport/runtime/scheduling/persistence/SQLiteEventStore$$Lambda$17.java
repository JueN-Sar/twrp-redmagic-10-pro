package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$17 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final Map f10399a;

    private SQLiteEventStore$$Lambda$17(Map map) {
        this.f10399a = map;
    }

    public static SQLiteEventStore.Function a(Map map) {
        return new SQLiteEventStore$$Lambda$17(map);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.P(this.f10399a, (Cursor) obj);
    }
}
