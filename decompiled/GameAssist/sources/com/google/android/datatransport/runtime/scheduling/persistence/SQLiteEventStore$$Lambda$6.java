package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$6 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteEventStore$$Lambda$6 f10408a = new SQLiteEventStore$$Lambda$6();

    private SQLiteEventStore$$Lambda$6() {
    }

    public static SQLiteEventStore.Function a() {
        return f10408a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.D((Cursor) obj);
    }
}
