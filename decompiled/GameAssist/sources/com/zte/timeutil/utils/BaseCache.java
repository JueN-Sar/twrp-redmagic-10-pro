package com.zte.timeutil.utils;

import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class BaseCache<K, V> implements Serializable {
    private static final long serialVersionUID = 1;
    private final Map<K, V> cache;
    private final ReentrantReadWriteLock lock;

    public BaseCache() {
        this(new WeakHashMap());
    }

    public Object a(Object obj) {
        this.lock.readLock().lock();
        try {
            return this.cache.get(obj);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object b(Object obj, Supplier supplier) {
        Object a2 = a(obj);
        if (a2 == null && supplier != null) {
            this.lock.writeLock().lock();
            try {
                a2 = this.cache.get(obj);
                if (a2 == null) {
                    try {
                        Object obj2 = supplier.get();
                        this.cache.put(obj, obj2);
                        a2 = obj2;
                    } catch (Exception e2) {
                        throw new RuntimeException(e2);
                    }
                }
                this.lock.writeLock().unlock();
            } catch (Throwable th) {
                this.lock.writeLock().unlock();
                throw th;
            }
        }
        return a2;
    }

    public BaseCache(Map<K, V> map) {
        this.lock = new ReentrantReadWriteLock();
        this.cache = map;
    }
}
