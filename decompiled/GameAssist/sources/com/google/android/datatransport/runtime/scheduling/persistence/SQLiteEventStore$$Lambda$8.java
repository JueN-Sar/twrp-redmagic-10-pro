package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$8 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteEventStore$$Lambda$8 f10410a = new SQLiteEventStore$$Lambda$8();

    private SQLiteEventStore$$Lambda$8() {
    }

    public static SQLiteEventStore.Function a() {
        return f10410a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.C((Cursor) obj);
    }
}
