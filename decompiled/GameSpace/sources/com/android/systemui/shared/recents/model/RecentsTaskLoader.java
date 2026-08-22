package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.Trace;
import android.util.LruCache;
import com.android.systemui.shared.recents.model.BackgroundTaskLoader;
import com.android.systemui.shared.recents.model.IconLoader;
import com.android.systemui.shared.recents.model.RecentsTaskLoadPlan;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.recents.model.TaskKeyLruCache;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.io.PrintWriter;
import java.util.Objects;

/* loaded from: classes2.dex */
public class RecentsTaskLoader {
    private static final boolean DEBUG = false;
    public static final int SVELTE_DISABLE_CACHE = 2;
    public static final int SVELTE_DISABLE_LOADING = 3;
    public static final int SVELTE_LIMIT_CACHE = 1;
    public static final int SVELTE_NONE = 0;
    private static final String TAG = "RecentsTaskLoader";
    private final LruCache<ComponentName, ActivityInfo> mActivityInfoCache;
    private final TaskKeyLruCache<String> mActivityLabelCache;
    private final TaskKeyLruCache<String> mContentDescriptionCache;
    private int mDefaultTaskBarBackgroundColor;
    private int mDefaultTaskViewBackgroundColor;
    private final HighResThumbnailLoader mHighResThumbnailLoader;
    private final TaskKeyLruCache<Drawable> mIconCache;
    private final IconLoader mIconLoader;
    private final TaskResourceLoadQueue mLoadQueue;
    private final BackgroundTaskLoader mLoader;
    private final int mMaxIconCacheSize;
    private final int mMaxThumbnailCacheSize;
    private int mNumVisibleTasksLoaded;
    private int mSvelteLevel;
    private final TaskKeyStrongCache<ThumbnailData> mThumbnailCache = new TaskKeyStrongCache<>();
    private final TaskKeyStrongCache<ThumbnailData> mTempCache = new TaskKeyStrongCache<>();
    private TaskKeyLruCache.EvictionCallback mClearActivityInfoOnEviction = new TaskKeyLruCache.EvictionCallback() { // from class: com.android.systemui.shared.recents.model.RecentsTaskLoader.1
        @Override // com.android.systemui.shared.recents.model.TaskKeyLruCache.EvictionCallback
        public void onEntryEvicted(Task.TaskKey taskKey) {
            if (taskKey != null) {
                RecentsTaskLoader.this.mActivityInfoCache.remove(taskKey.getComponent());
            }
        }
    };

    public RecentsTaskLoader(Context context, int i, int i2, int i3) {
        this.mMaxThumbnailCacheSize = i;
        this.mMaxIconCacheSize = i2;
        this.mSvelteLevel = i3;
        int maxRecentTasksStatic = ActivityManager.getMaxRecentTasksStatic();
        final HighResThumbnailLoader highResThumbnailLoader = new HighResThumbnailLoader(ActivityManagerWrapper.getInstance(), Looper.getMainLooper(), ActivityManager.isLowRamDeviceStatic());
        this.mHighResThumbnailLoader = highResThumbnailLoader;
        TaskResourceLoadQueue taskResourceLoadQueue = new TaskResourceLoadQueue();
        this.mLoadQueue = taskResourceLoadQueue;
        TaskKeyLruCache<Drawable> taskKeyLruCache = new TaskKeyLruCache<>(i2, this.mClearActivityInfoOnEviction);
        this.mIconCache = taskKeyLruCache;
        this.mActivityLabelCache = new TaskKeyLruCache<>(maxRecentTasksStatic, this.mClearActivityInfoOnEviction);
        this.mContentDescriptionCache = new TaskKeyLruCache<>(maxRecentTasksStatic, this.mClearActivityInfoOnEviction);
        LruCache<ComponentName, ActivityInfo> lruCache = new LruCache<>(maxRecentTasksStatic);
        this.mActivityInfoCache = lruCache;
        IconLoader createNewIconLoader = createNewIconLoader(context, taskKeyLruCache, lruCache);
        this.mIconLoader = createNewIconLoader;
        Objects.requireNonNull(highResThumbnailLoader);
        this.mLoader = new BackgroundTaskLoader(taskResourceLoadQueue, createNewIconLoader, new BackgroundTaskLoader.OnIdleChangedListener() { // from class: com.android.systemui.shared.recents.model.RecentsTaskLoader$$ExternalSyntheticLambda0
            @Override // com.android.systemui.shared.recents.model.BackgroundTaskLoader.OnIdleChangedListener
            public final void onIdleChanged(boolean z) {
                HighResThumbnailLoader.this.setTaskLoadQueueIdle(z);
            }
        });
    }

    private void stopLoader() {
        this.mLoader.stop();
        this.mLoadQueue.clearTasks();
    }

    public synchronized void clearIconAndTitleCache() {
        this.mIconCache.evictAll();
        this.mActivityLabelCache.evictAll();
    }

    protected IconLoader createNewIconLoader(Context context, TaskKeyLruCache<Drawable> taskKeyLruCache, LruCache<ComponentName, ActivityInfo> lruCache) {
        return new IconLoader.DefaultIconLoader(context, taskKeyLruCache, lruCache);
    }

    public void deleteTaskData(Task task, boolean z) {
        this.mLoadQueue.removeTask(task);
        this.mIconCache.remove(task.key);
        this.mActivityLabelCache.remove(task.key);
        this.mContentDescriptionCache.remove(task.key);
        if (z) {
            task.notifyTaskDataUnloaded(this.mIconLoader.getDefaultIcon(task.key.userId));
        }
    }

    public synchronized void dump(String str, PrintWriter printWriter) {
        String str2 = str + "  ";
        printWriter.print(str);
        printWriter.println(TAG);
        printWriter.print(str);
        printWriter.println("Icon Cache");
        this.mIconCache.dump(str2, printWriter);
        printWriter.print(str);
        printWriter.println("Thumbnail Cache");
        this.mThumbnailCache.dump(str2, printWriter);
        printWriter.print(str);
        printWriter.println("Temp Thumbnail Cache");
        this.mTempCache.dump(str2, printWriter);
    }

    int getActivityBackgroundColor(ActivityManager.TaskDescription taskDescription) {
        return (taskDescription == null || taskDescription.getBackgroundColor() == 0) ? this.mDefaultTaskViewBackgroundColor : taskDescription.getBackgroundColor();
    }

    int getActivityPrimaryColor(ActivityManager.TaskDescription taskDescription) {
        return (taskDescription == null || taskDescription.getPrimaryColor() == 0) ? this.mDefaultTaskBarBackgroundColor : taskDescription.getPrimaryColor();
    }

    Drawable getAndUpdateActivityIcon(Task.TaskKey taskKey, ActivityManager.TaskDescription taskDescription, boolean z) {
        return this.mIconLoader.getAndInvalidateIfModified(taskKey, taskDescription, z);
    }

    ActivityInfo getAndUpdateActivityInfo(Task.TaskKey taskKey) {
        return this.mIconLoader.getAndUpdateActivityInfo(taskKey);
    }

    String getAndUpdateActivityTitle(Task.TaskKey taskKey, ActivityManager.TaskDescription taskDescription) {
        if (taskDescription != null && taskDescription.getLabel() != null) {
            return taskDescription.getLabel();
        }
        String andInvalidateIfModified = this.mActivityLabelCache.getAndInvalidateIfModified(taskKey);
        if (andInvalidateIfModified != null) {
            return andInvalidateIfModified;
        }
        ActivityInfo andUpdateActivityInfo = getAndUpdateActivityInfo(taskKey);
        if (andUpdateActivityInfo == null) {
            return "";
        }
        String badgedActivityLabel = ActivityManagerWrapper.getInstance().getBadgedActivityLabel(andUpdateActivityInfo, taskKey.userId);
        this.mActivityLabelCache.put(taskKey, badgedActivityLabel);
        return badgedActivityLabel;
    }

    String getAndUpdateContentDescription(Task.TaskKey taskKey, ActivityManager.TaskDescription taskDescription) {
        String andInvalidateIfModified = this.mContentDescriptionCache.getAndInvalidateIfModified(taskKey);
        if (andInvalidateIfModified != null) {
            return andInvalidateIfModified;
        }
        ActivityInfo andUpdateActivityInfo = getAndUpdateActivityInfo(taskKey);
        if (andUpdateActivityInfo == null) {
            return "";
        }
        String badgedContentDescription = ActivityManagerWrapper.getInstance().getBadgedContentDescription(andUpdateActivityInfo, taskKey.userId, taskDescription);
        if (taskDescription == null) {
            this.mContentDescriptionCache.put(taskKey, badgedContentDescription);
        }
        return badgedContentDescription;
    }

    synchronized ThumbnailData getAndUpdateThumbnail(Task.TaskKey taskKey, boolean z, boolean z2) {
        ThumbnailData andInvalidateIfModified = this.mThumbnailCache.getAndInvalidateIfModified(taskKey);
        if (andInvalidateIfModified != null) {
            return andInvalidateIfModified;
        }
        ThumbnailData andInvalidateIfModified2 = this.mTempCache.getAndInvalidateIfModified(taskKey);
        if (andInvalidateIfModified2 != null) {
            this.mThumbnailCache.put(taskKey, andInvalidateIfModified2);
            return andInvalidateIfModified2;
        }
        if (z && this.mSvelteLevel < 3) {
            ThumbnailData taskThumbnail = ActivityManagerWrapper.getInstance().getTaskThumbnail(taskKey.id, true);
            if (taskThumbnail.thumbnail != null) {
                if (z2) {
                    this.mThumbnailCache.put(taskKey, taskThumbnail);
                }
                return taskThumbnail;
            }
        }
        return null;
    }

    public HighResThumbnailLoader getHighResThumbnailLoader() {
        return this.mHighResThumbnailLoader;
    }

    public int getIconCacheSize() {
        return this.mMaxIconCacheSize;
    }

    public int getThumbnailCacheSize() {
        return this.mMaxThumbnailCacheSize;
    }

    public void loadTaskData(Task task) {
        Drawable andInvalidateIfModified = this.mIconCache.getAndInvalidateIfModified(task.key);
        if (andInvalidateIfModified == null) {
            andInvalidateIfModified = this.mIconLoader.getDefaultIcon(task.key.userId);
        }
        this.mLoadQueue.addTask(task);
        task.notifyTaskDataLoaded(task.thumbnail, andInvalidateIfModified);
    }

    public synchronized void loadTasks(RecentsTaskLoadPlan recentsTaskLoadPlan, RecentsTaskLoadPlan.Options options) {
        if (options == null) {
            throw new RuntimeException("Requires load options");
        }
        if (options.onlyLoadForCache && options.loadThumbnails) {
            this.mTempCache.copyEntries(this.mThumbnailCache);
            this.mThumbnailCache.evictAll();
        }
        recentsTaskLoadPlan.executePlan(options, this);
        this.mTempCache.evictAll();
        if (!options.onlyLoadForCache) {
            this.mNumVisibleTasksLoaded = options.numVisibleTasks;
        }
    }

    public synchronized void onLanguageChangedEvent() {
        this.mActivityLabelCache.evictAll();
    }

    public void onPackageChanged(String str) {
        for (ComponentName componentName : this.mActivityInfoCache.snapshot().keySet()) {
            if (componentName.getPackageName().equals(str)) {
                this.mActivityInfoCache.remove(componentName);
            }
        }
    }

    public synchronized void onThemeChangedEvent() {
        this.mIconCache.evictAll();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x001b, code lost:
    
        if (r3 != 80) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onTrimMemory(int r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 5
            r1 = 1
            if (r3 == r0) goto L68
            r0 = 10
            if (r3 == r0) goto L4b
            r0 = 15
            if (r3 == r0) goto L31
            r0 = 20
            if (r3 == r0) goto L1e
            r0 = 40
            if (r3 == r0) goto L68
            r0 = 60
            if (r3 == r0) goto L4b
            r0 = 80
            if (r3 == r0) goto L31
            goto L84
        L1e:
            r2.stopLoader()     // Catch: java.lang.Throwable -> L86
            com.android.systemui.shared.recents.model.TaskKeyLruCache<android.graphics.drawable.Drawable> r3 = r2.mIconCache     // Catch: java.lang.Throwable -> L86
            int r0 = r2.mNumVisibleTasksLoaded     // Catch: java.lang.Throwable -> L86
            int r1 = r2.mMaxIconCacheSize     // Catch: java.lang.Throwable -> L86
            int r1 = r1 / 2
            int r0 = java.lang.Math.max(r0, r1)     // Catch: java.lang.Throwable -> L86
            r3.trimToSize(r0)     // Catch: java.lang.Throwable -> L86
            goto L84
        L31:
            com.android.systemui.shared.recents.model.TaskKeyLruCache<android.graphics.drawable.Drawable> r3 = r2.mIconCache     // Catch: java.lang.Throwable -> L86
            r3.evictAll()     // Catch: java.lang.Throwable -> L86
            android.util.LruCache<android.content.ComponentName, android.content.pm.ActivityInfo> r3 = r2.mActivityInfoCache     // Catch: java.lang.Throwable -> L86
            r3.evictAll()     // Catch: java.lang.Throwable -> L86
            com.android.systemui.shared.recents.model.TaskKeyLruCache<java.lang.String> r3 = r2.mActivityLabelCache     // Catch: java.lang.Throwable -> L86
            r3.evictAll()     // Catch: java.lang.Throwable -> L86
            com.android.systemui.shared.recents.model.TaskKeyLruCache<java.lang.String> r3 = r2.mContentDescriptionCache     // Catch: java.lang.Throwable -> L86
            r3.evictAll()     // Catch: java.lang.Throwable -> L86
            com.android.systemui.shared.recents.model.TaskKeyStrongCache<com.android.systemui.shared.recents.model.ThumbnailData> r3 = r2.mThumbnailCache     // Catch: java.lang.Throwable -> L86
            r3.evictAll()     // Catch: java.lang.Throwable -> L86
            goto L84
        L4b:
            com.android.systemui.shared.recents.model.TaskKeyLruCache<android.graphics.drawable.Drawable> r3 = r2.mIconCache     // Catch: java.lang.Throwable -> L86
            int r0 = r2.mMaxIconCacheSize     // Catch: java.lang.Throwable -> L86
            int r0 = r0 / 4
            int r0 = java.lang.Math.max(r1, r0)     // Catch: java.lang.Throwable -> L86
            r3.trimToSize(r0)     // Catch: java.lang.Throwable -> L86
            android.util.LruCache<android.content.ComponentName, android.content.pm.ActivityInfo> r3 = r2.mActivityInfoCache     // Catch: java.lang.Throwable -> L86
            int r0 = android.app.ActivityManager.getMaxRecentTasksStatic()     // Catch: java.lang.Throwable -> L86
            int r0 = r0 / 4
            int r0 = java.lang.Math.max(r1, r0)     // Catch: java.lang.Throwable -> L86
            r3.trimToSize(r0)     // Catch: java.lang.Throwable -> L86
            goto L84
        L68:
            com.android.systemui.shared.recents.model.TaskKeyLruCache<android.graphics.drawable.Drawable> r3 = r2.mIconCache     // Catch: java.lang.Throwable -> L86
            int r0 = r2.mMaxIconCacheSize     // Catch: java.lang.Throwable -> L86
            int r0 = r0 / 2
            int r0 = java.lang.Math.max(r1, r0)     // Catch: java.lang.Throwable -> L86
            r3.trimToSize(r0)     // Catch: java.lang.Throwable -> L86
            android.util.LruCache<android.content.ComponentName, android.content.pm.ActivityInfo> r3 = r2.mActivityInfoCache     // Catch: java.lang.Throwable -> L86
            int r0 = android.app.ActivityManager.getMaxRecentTasksStatic()     // Catch: java.lang.Throwable -> L86
            int r0 = r0 / 2
            int r0 = java.lang.Math.max(r1, r0)     // Catch: java.lang.Throwable -> L86
            r3.trimToSize(r0)     // Catch: java.lang.Throwable -> L86
        L84:
            monitor-exit(r2)
            return
        L86:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.recents.model.RecentsTaskLoader.onTrimMemory(int):void");
    }

    public synchronized void preloadTasks(RecentsTaskLoadPlan recentsTaskLoadPlan, int i) {
        preloadTasks(recentsTaskLoadPlan, i, ActivityManagerWrapper.getInstance().getCurrentUserId());
    }

    public synchronized void preloadTasks(RecentsTaskLoadPlan recentsTaskLoadPlan, int i, int i2) {
        try {
            Trace.beginSection("preloadPlan");
            recentsTaskLoadPlan.preloadPlan(new RecentsTaskLoadPlan.PreloadOptions(), this, i, i2);
        } finally {
            Trace.endSection();
        }
    }

    public void setDefaultColors(int i, int i2) {
        this.mDefaultTaskBarBackgroundColor = i;
        this.mDefaultTaskViewBackgroundColor = i2;
    }

    public void startLoader(Context context) {
        this.mLoader.start(context);
    }

    public void unloadTaskData(Task task) {
        this.mLoadQueue.removeTask(task);
        task.notifyTaskDataUnloaded(this.mIconLoader.getDefaultIcon(task.key.userId));
    }
}
