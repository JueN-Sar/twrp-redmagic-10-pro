package com.zte.gameassist.ext.utils;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import androidx.media3.common.C;

/* loaded from: classes2.dex */
public class HandleHelper {
    public static final String TAG = "HandleHelper";

    public interface Action<T> {
        T invoke();
    }

    public static final class ActionTask<T> extends WaitTask<T> {
        private final Action<T> mTask;
        private T mValue;

        public ActionTask(Action<T> action, T t) {
            this.mTask = action;
            this.mValue = t;
        }

        @Override // com.zte.gameassist.ext.utils.HandleHelper.WaitTask
        public T invokeAndWait(Handler handler, long j) {
            if (handler.getLooper().isCurrentThread()) {
                return this.mTask.invoke();
            }
            if (handler.post(this)) {
                waitTask(handler, j);
                return this.mValue;
            }
            Log.w(HandleHelper.TAG, "post err: mTask=" + this.mTask);
            return null;
        }

        @Override // com.zte.gameassist.ext.utils.HandleHelper.WaitTask, java.lang.Runnable
        public void run() {
            try {
                this.mValue = this.mTask.invoke();
            } finally {
                super.run();
            }
        }
    }

    private static final class VoidTask extends WaitTask<Boolean> {
        private final Runnable mTask;

        public VoidTask(Runnable runnable) {
            this.mTask = runnable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.zte.gameassist.ext.utils.HandleHelper.WaitTask
        public Boolean invokeAndWait(Handler handler, long j) {
            if (handler.getLooper().isCurrentThread()) {
                this.mTask.run();
                return true;
            }
            if (!handler.post(this)) {
                return false;
            }
            waitTask(handler, j);
            return true;
        }

        @Override // com.zte.gameassist.ext.utils.HandleHelper.WaitTask, java.lang.Runnable
        public void run() {
            try {
                this.mTask.run();
            } finally {
                super.run();
            }
        }
    }

    public static abstract class WaitTask<T> implements Runnable {
        protected boolean mDone;

        public T invokeAndWait(Handler handler) {
            return invokeAndWait(handler, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        }

        abstract T invokeAndWait(Handler handler, long j);

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                this.mDone = true;
                notifyAll();
            }
        }

        public void waitTask() {
            waitTask(0L);
        }

        public void waitTask(long j) {
            synchronized (this) {
                if (!this.mDone) {
                    try {
                        if (j > 0) {
                            wait(j);
                        } else {
                            wait();
                        }
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }

        protected void waitTask(Handler handler, long j) {
            if (j <= 0) {
                while (!this.mDone) {
                    waitTask();
                }
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis() + j;
            while (!this.mDone) {
                long uptimeMillis2 = uptimeMillis - SystemClock.uptimeMillis();
                if (uptimeMillis2 <= 0) {
                    waitTask();
                } else {
                    waitTask(uptimeMillis2);
                }
            }
        }
    }

    public static <T> T invokeAndWait(T t, Handler handler, long j, Action<T> action) {
        return handler.getLooper().isCurrentThread() ? action.invoke() : new ActionTask(action, t).invokeAndWait(handler, j);
    }

    public static <T> T invokeAndWait(T t, Handler handler, Action<T> action) {
        return (T) invokeAndWait(t, handler, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS, action);
    }

    public static boolean invokeAndWait(long j, Handler handler, Runnable runnable) {
        if (!handler.getLooper().isCurrentThread()) {
            return new VoidTask(runnable).invokeAndWait(handler, j).booleanValue();
        }
        runnable.run();
        return true;
    }

    public static boolean invokeAndWait(Handler handler, Runnable runnable) {
        return invokeAndWait(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS, handler, runnable);
    }

    public static boolean invokeOrPost(Handler handler, Runnable runnable) {
        if (!handler.getLooper().isCurrentThread()) {
            return handler.post(runnable);
        }
        runnable.run();
        return true;
    }

    private static boolean isCurrentThread(Handler handler) {
        return handler.getLooper().isCurrentThread();
    }
}
