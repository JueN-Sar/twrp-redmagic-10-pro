package com.android.systemui.shared.recents.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.android.systemui.shared.system.ActivityManagerWrapper;

/* loaded from: classes2.dex */
class BackgroundTaskLoader implements Runnable {
    static boolean DEBUG = false;
    static String TAG = "BackgroundTaskLoader";
    private boolean mCancelled;
    private Context mContext;
    private final IconLoader mIconLoader;
    private final TaskResourceLoadQueue mLoadQueue;
    private final HandlerThread mLoadThread;
    private final Handler mLoadThreadHandler;
    private final Handler mMainThreadHandler = new Handler();
    private final OnIdleChangedListener mOnIdleChangedListener;
    private boolean mStarted;
    private boolean mWaitingOnLoadQueue;

    interface OnIdleChangedListener {
        void onIdleChanged(boolean z);
    }

    public BackgroundTaskLoader(TaskResourceLoadQueue taskResourceLoadQueue, IconLoader iconLoader, OnIdleChangedListener onIdleChangedListener) {
        this.mLoadQueue = taskResourceLoadQueue;
        this.mIconLoader = iconLoader;
        this.mOnIdleChangedListener = onIdleChangedListener;
        HandlerThread handlerThread = new HandlerThread("Recents-TaskResourceLoader", 10);
        this.mLoadThread = handlerThread;
        handlerThread.start();
        this.mLoadThreadHandler = new Handler(handlerThread.getLooper());
    }

    private void processLoadQueueItem() {
        final Task nextTask = this.mLoadQueue.nextTask();
        if (nextTask != null) {
            final Drawable icon = this.mIconLoader.getIcon(nextTask);
            if (DEBUG) {
                Log.d(TAG, "Loading thumbnail: " + nextTask.key);
            }
            final ThumbnailData taskThumbnail = ActivityManagerWrapper.getInstance().getTaskThumbnail(nextTask.key.id, true);
            if (this.mCancelled) {
                return;
            }
            this.mMainThreadHandler.post(new Runnable() { // from class: com.android.systemui.shared.recents.model.BackgroundTaskLoader$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Task.this.notifyTaskDataLoaded(taskThumbnail, icon);
                }
            });
        }
    }

    /* renamed from: lambda$run$0$com-android-systemui-shared-recents-model-BackgroundTaskLoader, reason: not valid java name */
    /* synthetic */ void m359xb2482e31() {
        this.mOnIdleChangedListener.onIdleChanged(true);
    }

    /* renamed from: lambda$run$1$com-android-systemui-shared-recents-model-BackgroundTaskLoader, reason: not valid java name */
    /* synthetic */ void m360x30a93210() {
        this.mOnIdleChangedListener.onIdleChanged(false);
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            if (this.mCancelled) {
                this.mContext = null;
                synchronized (this.mLoadThread) {
                    try {
                        this.mLoadThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                processLoadQueueItem();
                if (!this.mCancelled && this.mLoadQueue.isEmpty()) {
                    synchronized (this.mLoadQueue) {
                        try {
                            this.mWaitingOnLoadQueue = true;
                            this.mMainThreadHandler.post(new Runnable() { // from class: com.android.systemui.shared.recents.model.BackgroundTaskLoader$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BackgroundTaskLoader.this.m359xb2482e31();
                                }
                            });
                            this.mLoadQueue.wait();
                            this.mMainThreadHandler.post(new Runnable() { // from class: com.android.systemui.shared.recents.model.BackgroundTaskLoader$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BackgroundTaskLoader.this.m360x30a93210();
                                }
                            });
                            this.mWaitingOnLoadQueue = false;
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    void start(Context context) {
        this.mContext = context;
        this.mCancelled = false;
        if (!this.mStarted) {
            this.mStarted = true;
            this.mLoadThreadHandler.post(this);
        } else {
            synchronized (this.mLoadThread) {
                this.mLoadThread.notifyAll();
            }
        }
    }

    void stop() {
        this.mCancelled = true;
        if (this.mWaitingOnLoadQueue) {
            this.mContext = null;
        }
    }
}
