package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public class LruCache<K, V> {

    /* renamed from: a, reason: collision with root package name */
    private int f1302a;

    /* renamed from: b, reason: collision with root package name */
    private final LruHashMap f1303b;

    /* renamed from: c, reason: collision with root package name */
    private final Lock f1304c;

    /* renamed from: d, reason: collision with root package name */
    private int f1305d;

    /* renamed from: e, reason: collision with root package name */
    private int f1306e;

    /* renamed from: f, reason: collision with root package name */
    private int f1307f;

    /* renamed from: g, reason: collision with root package name */
    private int f1308g;

    /* renamed from: h, reason: collision with root package name */
    private int f1309h;

    /* renamed from: i, reason: collision with root package name */
    private int f1310i;

    public LruCache(int i2) {
        this.f1302a = i2;
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        this.f1303b = new LruHashMap(0, 0.75f);
        this.f1304c = new Lock();
    }

    private final int f(Object obj, Object obj2) {
        int g2 = g(obj, obj2);
        if (g2 >= 0) {
            return g2;
        }
        throw new IllegalStateException(("Negative size: " + obj + '=' + obj2).toString());
    }

    protected Object a(Object key) {
        Intrinsics.e(key, "key");
        return null;
    }

    protected void b(boolean z, Object key, Object oldValue, Object obj) {
        Intrinsics.e(key, "key");
        Intrinsics.e(oldValue, "oldValue");
    }

    public final void c() {
        h(-1);
    }

    public final Object d(Object key) {
        Object d2;
        Intrinsics.e(key, "key");
        synchronized (this.f1304c) {
            Object a2 = this.f1303b.a(key);
            if (a2 != null) {
                this.f1309h++;
                return a2;
            }
            this.f1310i++;
            Object a3 = a(key);
            if (a3 == null) {
                return null;
            }
            synchronized (this.f1304c) {
                try {
                    this.f1307f++;
                    d2 = this.f1303b.d(key, a3);
                    if (d2 != null) {
                        this.f1303b.d(key, d2);
                    } else {
                        this.f1305d += f(key, a3);
                        Unit unit = Unit.f18288a;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (d2 != null) {
                b(false, key, a3, d2);
                return d2;
            }
            h(this.f1302a);
            return a3;
        }
    }

    public final Object e(Object key, Object value) {
        Object d2;
        Intrinsics.e(key, "key");
        Intrinsics.e(value, "value");
        synchronized (this.f1304c) {
            try {
                this.f1306e++;
                this.f1305d += f(key, value);
                d2 = this.f1303b.d(key, value);
                if (d2 != null) {
                    this.f1305d -= f(key, d2);
                }
                Unit unit = Unit.f18288a;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (d2 != null) {
            b(false, key, d2, value);
        }
        h(this.f1302a);
        return d2;
    }

    protected int g(Object key, Object value) {
        Intrinsics.e(key, "key");
        Intrinsics.e(value, "value");
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0062, code lost:
    
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!".toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h(int r6) {
        /*
            r5 = this;
        L0:
            androidx.collection.internal.Lock r0 = r5.f1304c
            monitor-enter(r0)
            int r1 = r5.f1305d     // Catch: java.lang.Throwable -> L14
            if (r1 < 0) goto L57
            androidx.collection.internal.LruHashMap r1 = r5.f1303b     // Catch: java.lang.Throwable -> L14
            boolean r1 = r1.c()     // Catch: java.lang.Throwable -> L14
            if (r1 == 0) goto L16
            int r1 = r5.f1305d     // Catch: java.lang.Throwable -> L14
            if (r1 != 0) goto L57
            goto L16
        L14:
            r5 = move-exception
            goto L63
        L16:
            int r1 = r5.f1305d     // Catch: java.lang.Throwable -> L14
            if (r1 <= r6) goto L55
            androidx.collection.internal.LruHashMap r1 = r5.f1303b     // Catch: java.lang.Throwable -> L14
            boolean r1 = r1.c()     // Catch: java.lang.Throwable -> L14
            if (r1 == 0) goto L23
            goto L55
        L23:
            androidx.collection.internal.LruHashMap r1 = r5.f1303b     // Catch: java.lang.Throwable -> L14
            java.util.Set r1 = r1.b()     // Catch: java.lang.Throwable -> L14
            java.lang.Object r1 = kotlin.collections.CollectionsKt.y(r1)     // Catch: java.lang.Throwable -> L14
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L14
            if (r1 != 0) goto L33
            monitor-exit(r0)
            return
        L33:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L14
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L14
            androidx.collection.internal.LruHashMap r3 = r5.f1303b     // Catch: java.lang.Throwable -> L14
            r3.e(r2)     // Catch: java.lang.Throwable -> L14
            int r3 = r5.f1305d     // Catch: java.lang.Throwable -> L14
            int r4 = r5.f(r2, r1)     // Catch: java.lang.Throwable -> L14
            int r3 = r3 - r4
            r5.f1305d = r3     // Catch: java.lang.Throwable -> L14
            int r3 = r5.f1308g     // Catch: java.lang.Throwable -> L14
            r4 = 1
            int r3 = r3 + r4
            r5.f1308g = r3     // Catch: java.lang.Throwable -> L14
            monitor-exit(r0)
            r0 = 0
            r5.b(r4, r2, r1, r0)
            goto L0
        L55:
            monitor-exit(r0)
            return
        L57:
            java.lang.String r5 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L14
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L14
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L14
            throw r6     // Catch: java.lang.Throwable -> L14
        L63:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.h(int):void");
    }

    public String toString() {
        String str;
        synchronized (this.f1304c) {
            try {
                int i2 = this.f1309h;
                int i3 = this.f1310i + i2;
                str = "LruCache[maxSize=" + this.f1302a + ",hits=" + this.f1309h + ",misses=" + this.f1310i + ",hitRate=" + (i3 != 0 ? (i2 * 100) / i3 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
