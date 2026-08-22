package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$16 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteEventStore$$Lambda$16 f10398a = new SQLiteEventStore$$Lambda$16();

    private SQLiteEventStore$$Lambda$16() {
    }

    public static SQLiteEventStore.Function a() {
        return f10398a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.T((Cursor) obj);
    }
}
