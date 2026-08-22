package com.android.systemui.shared.recents.model;

import android.util.Log;
import android.util.SparseArray;
import com.android.systemui.shared.recents.model.Task;

/* loaded from: classes2.dex */
public abstract class TaskKeyCache<V> {
    protected static final String TAG = "TaskKeyCache";
    protected final SparseArray<Task.TaskKey> mKeys = new SparseArray<>();

    public final synchronized void evictAll() {
        evictAllCache();
        this.mKeys.clear();
    }

    protected abstract void evictAllCache();

    public final synchronized V get(Task.TaskKey taskKey) {
        return getCacheEntry(taskKey.id);
    }

    public final synchronized V getAndInvalidateIfModified(Task.TaskKey taskKey) {
        Task.TaskKey taskKey2 = this.mKeys.get(taskKey.id);
        if (taskKey2 == null || (taskKey2.windowingMode == taskKey.windowingMode && taskKey2.lastActiveTime == taskKey.lastActiveTime)) {
            return getCacheEntry(taskKey.id);
        }
        remove(taskKey);
        return null;
    }

    protected abstract V getCacheEntry(int i);

    public final synchronized void put(Task.TaskKey taskKey, V v) {
        if (taskKey == null || v == null) {
            Log.e(TAG, "Unexpected null key or value: " + taskKey + ", " + v);
        } else {
            this.mKeys.put(taskKey.id, taskKey);
            putCacheEntry(taskKey.id, v);
        }
    }

    protected abstract void putCacheEntry(int i, V v);

    public final synchronized void remove(Task.TaskKey taskKey) {
        removeCacheEntry(taskKey.id);
        this.mKeys.remove(taskKey.id);
    }

    protected abstract void removeCacheEntry(int i);
}
