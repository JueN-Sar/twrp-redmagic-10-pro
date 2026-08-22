package com.android.systemui.shared.recents.model;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.ArraySet;
import com.android.systemui.shared.recents.model.HighResThumbnailLoader;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.util.ArrayDeque;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class HighResThumbnailLoader implements Task.TaskCallbacks {
    private final ActivityManagerWrapper mActivityManager;
    private boolean mFlingingFast;
    private final boolean mIsLowRamDevice;
    private final Thread mLoadThread;
    private final Runnable mLoader;
    private boolean mLoaderIdling;
    private boolean mLoading;
    private final Handler mMainThreadHandler;
    private boolean mTaskLoadQueueIdle;
    private boolean mVisible;
    private final ArrayDeque<Task> mLoadQueue = new ArrayDeque<>();
    private final ArraySet<Task> mLoadingTasks = new ArraySet<>();
    private final ArrayList<Task> mVisibleTasks = new ArrayList<>();

    /* renamed from: com.android.systemui.shared.recents.model.HighResThumbnailLoader$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        private void loadTask(final Task task) {
            final ThumbnailData taskThumbnail = HighResThumbnailLoader.this.mActivityManager.getTaskThumbnail(task.key.id, false);
            HighResThumbnailLoader.this.mMainThreadHandler.post(new Runnable() { // from class: com.android.systemui.shared.recents.model.HighResThumbnailLoader$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HighResThumbnailLoader.AnonymousClass1.this.m361xfb98f07f(task, taskThumbnail);
                }
            });
        }

        /* renamed from: lambda$loadTask$0$com-android-systemui-shared-recents-model-HighResThumbnailLoader$1, reason: not valid java name */
        /* synthetic */ void m361xfb98f07f(Task task, ThumbnailData thumbnailData) {
            synchronized (HighResThumbnailLoader.this.mLoadQueue) {
                HighResThumbnailLoader.this.mLoadingTasks.remove(task);
            }
            if (HighResThumbnailLoader.this.mVisibleTasks.contains(task)) {
                task.notifyTaskDataLoaded(thumbnailData, task.icon);
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(7:4|5|(3:10|(1:12)|13)|20|21|22|13) */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r3 = this;
                r0 = 11
                android.os.Process.setThreadPriority(r0)
            L5:
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r0 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this
                java.util.ArrayDeque r0 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$000(r0)
                monitor-enter(r0)
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.Throwable -> L56
                boolean r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$100(r1)     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L39
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.Throwable -> L56
                java.util.ArrayDeque r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$000(r1)     // Catch: java.lang.Throwable -> L56
                boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L21
                goto L39
            L21:
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.Throwable -> L56
                java.util.ArrayDeque r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$000(r1)     // Catch: java.lang.Throwable -> L56
                java.lang.Object r1 = r1.poll()     // Catch: java.lang.Throwable -> L56
                com.android.systemui.shared.recents.model.Task r1 = (com.android.systemui.shared.recents.model.Task) r1     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L4f
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r2 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.Throwable -> L56
                android.util.ArraySet r2 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$300(r2)     // Catch: java.lang.Throwable -> L56
                r2.add(r1)     // Catch: java.lang.Throwable -> L56
                goto L4f
            L39:
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
                r2 = 1
                com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$202(r1, r2)     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
                java.util.ArrayDeque r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$000(r1)     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
                r1.wait()     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
                com.android.systemui.shared.recents.model.HighResThumbnailLoader r1 = com.android.systemui.shared.recents.model.HighResThumbnailLoader.this     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
                r2 = 0
                com.android.systemui.shared.recents.model.HighResThumbnailLoader.access$202(r1, r2)     // Catch: java.lang.InterruptedException -> L4e java.lang.Throwable -> L56
            L4e:
                r1 = 0
            L4f:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L5
                r3.loadTask(r1)
                goto L5
            L56:
                r3 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.recents.model.HighResThumbnailLoader.AnonymousClass1.run():void");
        }
    }

    public HighResThumbnailLoader(ActivityManagerWrapper activityManagerWrapper, Looper looper, boolean z) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mLoader = anonymousClass1;
        this.mActivityManager = activityManagerWrapper;
        this.mMainThreadHandler = new Handler(looper);
        Thread thread = new Thread(anonymousClass1, "Recents-HighResThumbnailLoader");
        this.mLoadThread = thread;
        thread.start();
        this.mIsLowRamDevice = z;
    }

    private void setLoading(boolean z) {
        if (z == this.mLoading) {
            return;
        }
        synchronized (this.mLoadQueue) {
            this.mLoading = z;
            if (z) {
                startLoading();
            } else {
                stopLoading();
            }
        }
    }

    private void startLoading() {
        for (int size = this.mVisibleTasks.size() - 1; size >= 0; size--) {
            Task task = this.mVisibleTasks.get(size);
            if ((task.thumbnail == null || task.thumbnail.reducedResolution) && !this.mLoadQueue.contains(task) && !this.mLoadingTasks.contains(task)) {
                this.mLoadQueue.add(task);
            }
        }
        this.mLoadQueue.notifyAll();
    }

    private void stopLoading() {
        this.mLoadQueue.clear();
        this.mLoadQueue.notifyAll();
    }

    private void updateLoading() {
        setLoading(this.mVisible && !this.mFlingingFast && this.mTaskLoadQueueIdle);
    }

    boolean isLoading() {
        return this.mLoading;
    }

    @Override // com.android.systemui.shared.recents.model.Task.TaskCallbacks
    public void onTaskDataLoaded(Task task, ThumbnailData thumbnailData) {
        if (thumbnailData == null || thumbnailData.reducedResolution) {
            return;
        }
        synchronized (this.mLoadQueue) {
            this.mLoadQueue.remove(task);
        }
    }

    @Override // com.android.systemui.shared.recents.model.Task.TaskCallbacks
    public void onTaskDataUnloaded() {
    }

    public void onTaskInvisible(Task task) {
        task.removeCallback(this);
        this.mVisibleTasks.remove(task);
        synchronized (this.mLoadQueue) {
            this.mLoadQueue.remove(task);
        }
    }

    public void onTaskVisible(Task task) {
        task.addCallback(this);
        this.mVisibleTasks.add(task);
        if ((task.thumbnail == null || task.thumbnail.reducedResolution) && this.mLoading) {
            synchronized (this.mLoadQueue) {
                this.mLoadQueue.add(task);
                this.mLoadQueue.notifyAll();
            }
        }
    }

    @Override // com.android.systemui.shared.recents.model.Task.TaskCallbacks
    public void onTaskWindowingModeChanged() {
    }

    public void setFlingingFast(boolean z) {
        if (this.mFlingingFast == z || this.mIsLowRamDevice) {
            return;
        }
        this.mFlingingFast = z;
        updateLoading();
    }

    public void setTaskLoadQueueIdle(boolean z) {
        if (this.mIsLowRamDevice) {
            return;
        }
        this.mTaskLoadQueueIdle = z;
        updateLoading();
    }

    public void setVisible(boolean z) {
        if (this.mIsLowRamDevice) {
            return;
        }
        this.mVisible = z;
        updateLoading();
    }

    void waitForLoaderIdle() {
        while (true) {
            synchronized (this.mLoadQueue) {
                if (this.mLoadQueue.isEmpty() && this.mLoaderIdling) {
                    return;
                }
            }
            SystemClock.sleep(100L);
        }
    }
}
