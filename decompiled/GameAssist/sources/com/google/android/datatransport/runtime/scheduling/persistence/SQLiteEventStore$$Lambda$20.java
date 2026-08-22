package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$20 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteEventStore$$Lambda$20 f10402a = new SQLiteEventStore$$Lambda$20();

    private SQLiteEventStore$$Lambda$20() {
    }

    public static SQLiteEventStore.Function a() {
        return f10402a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.F((Cursor) obj);
    }
}
