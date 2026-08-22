package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$19 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteEventStore$$Lambda$19 f10401a = new SQLiteEventStore$$Lambda$19();

    private SQLiteEventStore$$Lambda$19() {
    }

    public static SQLiteEventStore.Function a() {
        return f10401a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.A((Throwable) obj);
    }
}
