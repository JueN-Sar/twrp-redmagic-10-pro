package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$7 implements SQLiteEventStore.Function {

    /* renamed from: a, reason: collision with root package name */
    private final String f10409a;

    private SQLiteEventStore$$Lambda$7(String str) {
        this.f10409a = str;
    }

    public static SQLiteEventStore.Function a(String str) {
        return new SQLiteEventStore$$Lambda$7(str);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.W(this.f10409a, (SQLiteDatabase) obj);
    }
}
