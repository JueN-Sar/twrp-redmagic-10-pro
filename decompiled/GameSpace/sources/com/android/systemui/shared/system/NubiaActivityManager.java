package com.android.systemui.shared.system;

import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import nubia.os.ApplicationManager;
import nubia.os.ITaskCallback;

/* loaded from: classes2.dex */
public class NubiaActivityManager {
    private static final int MSG_REGISTER = 1001;
    private static final int MSG_TOP_PKG_CHANGED = 1000;
    private static final String TAG = "SysShared";
    private ITaskCallback mCallback;
    private InnerH mHandler;
    private ArrayList<ITaskChangeCallback> mTaskCallbacks;
    private String mTopPackageName;

    public interface ITaskChangeCallback {
        void onTopActivityChange(String str);
    }

    public class InnerH extends Handler {
        public InnerH(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1000) {
                if (i != 1001) {
                    return;
                }
                ApplicationManager.Trigger.unregisterCallback(NubiaActivityManager.this.mCallback);
                ApplicationManager.Trigger.registerCallback(NubiaActivityManager.this.mCallback, Long.MAX_VALUE);
                return;
            }
            NubiaActivityManager nubiaActivityManager = NubiaActivityManager.this;
            nubiaActivityManager.mTopPackageName = nubiaActivityManager.getCurrentPkg();
            Iterator it = NubiaActivityManager.this.mTaskCallbacks.iterator();
            while (it.hasNext()) {
                ((ITaskChangeCallback) it.next()).onTopActivityChange(NubiaActivityManager.this.mTopPackageName);
            }
        }
    }

    private static class InstanceHolder {
        private static NubiaActivityManager sInstance = new NubiaActivityManager();

        private InstanceHolder() {
        }
    }

    private NubiaActivityManager() {
        this.mTopPackageName = "";
        this.mTaskCallbacks = new ArrayList<>();
        this.mCallback = new ITaskCallback.Stub() { // from class: com.android.systemui.shared.system.NubiaActivityManager.1
            public void actionPerformed(Message message) {
                message.getData();
                if (message.what != 2001) {
                    return;
                }
                NubiaActivityManager.this.mHandler.removeMessages(1000);
                NubiaActivityManager.this.mHandler.sendEmptyMessageDelayed(1000, 100L);
            }
        };
    }

    public static NubiaActivityManager getInstance() {
        return InstanceHolder.sInstance;
    }

    public void addTaskListener(ITaskChangeCallback iTaskChangeCallback) {
        if (iTaskChangeCallback == null || this.mTaskCallbacks.contains(iTaskChangeCallback)) {
            return;
        }
        this.mTaskCallbacks.add(iTaskChangeCallback);
        iTaskChangeCallback.onTopActivityChange(this.mTopPackageName);
    }

    public String getCurrentPkg() {
        IActivityTaskManager service = ActivityTaskManager.getService();
        if (service == null) {
            return null;
        }
        try {
            return service.getFocusedStackResumedPkg();
        } catch (Exception e) {
            Log.d(TAG, "getCurrentPkg error = " + e);
            return null;
        }
    }

    public String getTopPackageName() {
        return this.mTopPackageName;
    }

    public void initTaskRegist() {
        Log.i(TAG, "initTaskRegist");
        this.mHandler.sendEmptyMessageDelayed(1001, 100L);
        this.mTopPackageName = getCurrentPkg();
    }

    public void removeTaskListener(ITaskChangeCallback iTaskChangeCallback) {
        if (iTaskChangeCallback == null || !this.mTaskCallbacks.contains(iTaskChangeCallback)) {
            return;
        }
        this.mTaskCallbacks.remove(iTaskChangeCallback);
    }

    public void start(Looper looper) {
        this.mHandler = new InnerH(looper);
        initTaskRegist();
    }
}
