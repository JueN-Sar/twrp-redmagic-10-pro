package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.content.ComponentName;

/* loaded from: classes2.dex */
public class RecentTaskInfoCompat {
    private ActivityManager.RecentTaskInfo mInfo;

    public RecentTaskInfoCompat(ActivityManager.RecentTaskInfo recentTaskInfo) {
        this.mInfo = recentTaskInfo;
    }

    public ActivityManager.TaskDescription getTaskDescription() {
        return this.mInfo.taskDescription;
    }

    public ComponentName getTopActivity() {
        return this.mInfo.topActivity;
    }

    public int getUserId() {
        return this.mInfo.userId;
    }

    public boolean supportsSplitScreenMultiWindow() {
        return this.mInfo.supportsSplitScreenMultiWindow;
    }
}
