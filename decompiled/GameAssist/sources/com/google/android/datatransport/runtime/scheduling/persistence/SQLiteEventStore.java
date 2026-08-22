package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import javax.inject.Singleton;

@Singleton
@WorkerThread
/* loaded from: classes.dex */
public class SQLiteEventStore implements EventStore, SynchronizationGuard {

    /* renamed from: k, reason: collision with root package name */
    private static final Encoding f10382k = Encoding.b("proto");

    /* renamed from: c, reason: collision with root package name */
    private final SchemaManager f10383c;

    /* renamed from: h, reason: collision with root package name */
    private final Clock f10384h;

    /* renamed from: i, reason: collision with root package name */
    private final Clock f10385i;

    /* renamed from: j, reason: collision with root package name */
    private final EventStoreConfig f10386j;

    interface Function<T, U> {
        Object apply(Object obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Metadata {

        /* renamed from: a, reason: collision with root package name */
        final String f10413a;

        /* renamed from: b, reason: collision with root package name */
        final String f10414b;

        private Metadata(String str, String str2) {
            this.f10413a = str;
            this.f10414b = str2;
        }
    }

    interface Producer<T> {
        Object a();
    }

    SQLiteEventStore(Clock clock, Clock clock2, EventStoreConfig eventStoreConfig, SchemaManager schemaManager) {
        this.f10383c = schemaManager;
        this.f10384h = clock;
        this.f10385i = clock2;
        this.f10386j = eventStoreConfig;
    }

    static /* synthetic */ Object A(Throwable th) {
        throw new SynchronizationException("Timed out while trying to acquire the lock.", th);
    }

    static /* synthetic */ SQLiteDatabase B(Throwable th) {
        throw new SynchronizationException("Timed out while trying to open db.", th);
    }

    static /* synthetic */ Long C(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return 0L;
    }

    static /* synthetic */ Long D(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return null;
    }

    static /* synthetic */ Boolean E(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        Long i2 = sQLiteEventStore.i(sQLiteDatabase, transportContext);
        return i2 == null ? Boolean.FALSE : (Boolean) tryWithCursor(sQLiteEventStore.getDb().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{i2.toString()}), SQLiteEventStore$$Lambda$21.a());
    }

    static /* synthetic */ List F(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(TransportContext.a().b(cursor.getString(1)).d(PriorityMapping.b(cursor.getInt(2))).c(f0(cursor.getString(3))).a());
        }
        return arrayList;
    }

    static /* synthetic */ List G(SQLiteDatabase sQLiteDatabase) {
        return (List) tryWithCursor(sQLiteDatabase.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), SQLiteEventStore$$Lambda$20.a());
    }

    static /* synthetic */ List I(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        List a0 = sQLiteEventStore.a0(sQLiteDatabase, transportContext);
        return sQLiteEventStore.k(a0, sQLiteEventStore.e0(sQLiteDatabase, a0));
    }

    static /* synthetic */ Object L(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j2 = cursor.getLong(0);
            boolean z = cursor.getInt(7) != 0;
            EventInternal.Builder k2 = EventInternal.a().j(cursor.getString(1)).i(cursor.getLong(2)).k(cursor.getLong(3));
            if (z) {
                k2.h(new EncodedPayload(k0(cursor.getString(4)), cursor.getBlob(5)));
            } else {
                k2.h(new EncodedPayload(k0(cursor.getString(4)), sQLiteEventStore.h0(j2)));
            }
            if (!cursor.isNull(6)) {
                k2.g(Integer.valueOf(cursor.getInt(6)));
            }
            list.add(PersistedEvent.a(j2, transportContext, k2.d()));
        }
        return null;
    }

    static /* synthetic */ Object P(Map map, Cursor cursor) {
        while (true) {
            if (!cursor.moveToNext()) {
                return null;
            }
            long j2 = cursor.getLong(0);
            Set set = (Set) map.get(Long.valueOf(j2));
            if (set == null) {
                set = new HashSet();
                map.put(Long.valueOf(j2), set);
            }
            set.add(new Metadata(cursor.getString(1), cursor.getString(2)));
        }
    }

    static /* synthetic */ Long R(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, EventInternal eventInternal, SQLiteDatabase sQLiteDatabase) {
        if (sQLiteEventStore.j()) {
            return -1L;
        }
        long d2 = sQLiteEventStore.d(sQLiteDatabase, transportContext);
        int e2 = sQLiteEventStore.f10386j.e();
        byte[] a2 = eventInternal.e().a();
        boolean z = a2.length <= e2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("context_id", Long.valueOf(d2));
        contentValues.put("transport_name", eventInternal.j());
        contentValues.put("timestamp_ms", Long.valueOf(eventInternal.f()));
        contentValues.put("uptime_ms", Long.valueOf(eventInternal.k()));
        contentValues.put("payload_encoding", eventInternal.e().b().a());
        contentValues.put("code", eventInternal.d());
        contentValues.put("num_attempts", (Integer) 0);
        contentValues.put("inline", Boolean.valueOf(z));
        contentValues.put("payload", z ? a2 : new byte[0]);
        long insert = sQLiteDatabase.insert("events", null, contentValues);
        if (!z) {
            int ceil = (int) Math.ceil(a2.length / e2);
            for (int i2 = 1; i2 <= ceil; i2++) {
                byte[] copyOfRange = Arrays.copyOfRange(a2, (i2 - 1) * e2, Math.min(i2 * e2, a2.length));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Long.valueOf(insert));
                contentValues2.put("sequence_num", Integer.valueOf(i2));
                contentValues2.put("bytes", copyOfRange);
                sQLiteDatabase.insert("event_payloads", null, contentValues2);
            }
        }
        for (Map.Entry entry : eventInternal.i().entrySet()) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("event_id", Long.valueOf(insert));
            contentValues3.put("name", (String) entry.getKey());
            contentValues3.put("value", (String) entry.getValue());
            sQLiteDatabase.insert("event_metadata", null, contentValues3);
        }
        return Long.valueOf(insert);
    }

    static /* synthetic */ byte[] T(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(0);
            arrayList.add(blob);
            i2 += blob.length;
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            byte[] bArr2 = (byte[]) arrayList.get(i4);
            System.arraycopy(bArr2, 0, bArr, i3, bArr2.length);
            i3 += bArr2.length;
        }
        return bArr;
    }

    static /* synthetic */ Object W(String str, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.compileStatement(str).execute();
        sQLiteDatabase.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
        return null;
    }

    static /* synthetic */ Object Y(long j2, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("next_request_ms", Long.valueOf(j2));
        if (sQLiteDatabase.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{transportContext.b(), String.valueOf(PriorityMapping.a(transportContext.d()))}) < 1) {
            contentValues.put("backend_name", transportContext.b());
            contentValues.put("priority", Integer.valueOf(PriorityMapping.a(transportContext.d())));
            sQLiteDatabase.insert("transport_contexts", null, contentValues);
        }
        return null;
    }

    private List a0(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        ArrayList arrayList = new ArrayList();
        Long i2 = i(sQLiteDatabase, transportContext);
        if (i2 == null) {
            return arrayList;
        }
        tryWithCursor(sQLiteDatabase.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{i2.toString()}, null, null, null, String.valueOf(this.f10386j.d())), SQLiteEventStore$$Lambda$15.a(this, arrayList, transportContext));
        return arrayList;
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        j0(SQLiteEventStore$$Lambda$18.b(sQLiteDatabase), SQLiteEventStore$$Lambda$19.a());
    }

    private long d(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        Long i2 = i(sQLiteDatabase, transportContext);
        if (i2 != null) {
            return i2.longValue();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("backend_name", transportContext.b());
        contentValues.put("priority", Integer.valueOf(PriorityMapping.a(transportContext.d())));
        contentValues.put("next_request_ms", (Integer) 0);
        if (transportContext.c() != null) {
            contentValues.put("extras", Base64.encodeToString(transportContext.c(), 0));
        }
        return sQLiteDatabase.insert("transport_contexts", null, contentValues);
    }

    private long e() {
        return getDb().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    private Map e0(SQLiteDatabase sQLiteDatabase, List list) {
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder("event_id IN (");
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(((PersistedEvent) list.get(i2)).c());
            if (i2 < list.size() - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        tryWithCursor(sQLiteDatabase.query("event_metadata", new String[]{"event_id", "name", "value"}, sb.toString(), null, null, null, null), SQLiteEventStore$$Lambda$17.a(hashMap));
        return hashMap;
    }

    private static byte[] f0(String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 0);
    }

    private long h() {
        return getDb().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    private byte[] h0(long j2) {
        return (byte[]) tryWithCursor(getDb().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(j2)}, null, null, "sequence_num"), SQLiteEventStore$$Lambda$16.a());
    }

    private Long i(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        StringBuilder sb = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(transportContext.b(), String.valueOf(PriorityMapping.a(transportContext.d()))));
        if (transportContext.c() != null) {
            sb.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(transportContext.c(), 0));
        } else {
            sb.append(" and extras is null");
        }
        return (Long) tryWithCursor(sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null), SQLiteEventStore$$Lambda$6.a());
    }

    private boolean j() {
        return e() * h() >= this.f10386j.f();
    }

    private Object j0(Producer producer, Function function) {
        long a2 = this.f10385i.a();
        while (true) {
            try {
                return producer.a();
            } catch (SQLiteDatabaseLockedException e2) {
                if (this.f10385i.a() >= this.f10386j.b() + a2) {
                    return function.apply(e2);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    private List k(List list, Map map) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            PersistedEvent persistedEvent = (PersistedEvent) listIterator.next();
            if (map.containsKey(Long.valueOf(persistedEvent.c()))) {
                EventInternal.Builder l2 = persistedEvent.b().l();
                for (Metadata metadata : (Set) map.get(Long.valueOf(persistedEvent.c()))) {
                    l2.c(metadata.f10413a, metadata.f10414b);
                }
                listIterator.set(PersistedEvent.a(persistedEvent.c(), persistedEvent.d(), l2.d()));
            }
        }
        return list;
    }

    private static Encoding k0(String str) {
        return str == null ? f10382k : Encoding.b(str);
    }

    private static String l0(Iterable iterable) {
        StringBuilder sb = new StringBuilder("(");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(((PersistedEvent) it.next()).c());
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }

    static /* synthetic */ Object s(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("events", null, new String[0]);
        sQLiteDatabase.delete("transport_contexts", null, new String[0]);
        return null;
    }

    static /* synthetic */ Object t(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        return null;
    }

    @VisibleForTesting
    static <T> T tryWithCursor(Cursor cursor, Function<Cursor, T> function) {
        try {
            return (T) function.apply(cursor);
        } finally {
            cursor.close();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public long K(TransportContext transportContext) {
        return ((Long) tryWithCursor(getDb().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{transportContext.b(), String.valueOf(PriorityMapping.a(transportContext.d()))}), SQLiteEventStore$$Lambda$8.a())).longValue();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public boolean M(TransportContext transportContext) {
        return ((Boolean) inTransaction(SQLiteEventStore$$Lambda$9.a(this, transportContext))).booleanValue();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void N(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            inTransaction(SQLiteEventStore$$Lambda$7.a("UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + l0(iterable)));
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable S(TransportContext transportContext) {
        return (Iterable) inTransaction(SQLiteEventStore$$Lambda$11.a(this, transportContext));
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard
    public Object a(SynchronizationGuard.CriticalSection criticalSection) {
        SQLiteDatabase db = getDb();
        c(db);
        try {
            Object a2 = criticalSection.a();
            db.setTransactionSuccessful();
            return a2;
        } finally {
            db.endTransaction();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public PersistedEvent c0(TransportContext transportContext, EventInternal eventInternal) {
        Logging.b("SQLiteEventStore", "Storing event with priority=%s, name=%s for destination %s", transportContext.d(), eventInternal.j(), transportContext.b());
        long longValue = ((Long) inTransaction(SQLiteEventStore$$Lambda$5.a(this, transportContext, eventInternal))).longValue();
        if (longValue < 1) {
            return null;
        }
        return PersistedEvent.a(longValue, transportContext, eventInternal);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f10383c.close();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public int f() {
        return ((Integer) inTransaction(SQLiteEventStore$$Lambda$13.a(this.f10384h.a() - this.f10386j.c()))).intValue();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void g(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            getDb().compileStatement("DELETE FROM events WHERE _id in " + l0(iterable)).execute();
        }
    }

    @VisibleForTesting
    long getByteSize() {
        return e() * h();
    }

    @VisibleForTesting
    SQLiteDatabase getDb() {
        SchemaManager schemaManager = this.f10383c;
        schemaManager.getClass();
        return (SQLiteDatabase) j0(SQLiteEventStore$$Lambda$1.b(schemaManager), SQLiteEventStore$$Lambda$4.a());
    }

    @VisibleForTesting
    <T> T inTransaction(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase db = getDb();
        db.beginTransaction();
        try {
            T t = (T) function.apply(db);
            db.setTransactionSuccessful();
            return t;
        } finally {
            db.endTransaction();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void l(TransportContext transportContext, long j2) {
        inTransaction(SQLiteEventStore$$Lambda$10.a(j2, transportContext));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable o() {
        return (Iterable) inTransaction(SQLiteEventStore$$Lambda$12.a());
    }
}
