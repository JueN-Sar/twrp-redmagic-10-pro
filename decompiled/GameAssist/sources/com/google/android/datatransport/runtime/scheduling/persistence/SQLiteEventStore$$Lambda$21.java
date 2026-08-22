package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$21 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteEventStore$$Lambda$21 f10403a = new SQLiteEventStore$$Lambda$21();

    private SQLiteEventStore$$Lambda$21() {
    }

    public static SQLiteEventStore.Function a() {
        return f10403a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return Boolean.valueOf(((Cursor) obj).moveToNext());
    }
}
