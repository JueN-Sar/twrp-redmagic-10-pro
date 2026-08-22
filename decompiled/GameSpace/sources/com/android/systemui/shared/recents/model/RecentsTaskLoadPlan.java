package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseBooleanArray;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class RecentsTaskLoadPlan {
    private final Context mContext;
    private final KeyguardManager mKeyguardManager;
    private List<ActivityManager.RecentTaskInfo> mRawTasks;
    private TaskStack mStack;
    private final SparseBooleanArray mTmpLockedUsers = new SparseBooleanArray();

    public static class Options {
        public int runningTaskId = -1;
        public boolean loadIcons = true;
        public boolean loadThumbnails = false;
        public boolean onlyLoadForCache = false;
        public boolean onlyLoadPausedActivities = false;
        public int numVisibleTasks = 0;
        public int numVisibleTaskThumbnails = 0;
    }

    public static class PreloadOptions {
        public boolean loadTitles = true;
    }

    public RecentsTaskLoadPlan(Context context) {
        this.mContext = context;
        this.mKeyguardManager = (KeyguardManager) context.getSystemService("keyguard");
    }

    public static ComponentName getTaskComponentName(ActivityManager.RecentTaskInfo recentTaskInfo) {
        return recentTaskInfo.origActivity != null ? recentTaskInfo.origActivity : recentTaskInfo.realActivity;
    }

    public void executePlan(Options options, RecentsTaskLoader recentsTaskLoader) {
        this.mContext.getResources();
        ArrayList<Task> tasks = this.mStack.getTasks();
        int size = tasks.size();
        int i = 0;
        while (i < size) {
            Task task = tasks.get(i);
            Task.TaskKey taskKey = task.key;
            boolean z = task.key.id == options.runningTaskId;
            boolean z2 = i >= size - options.numVisibleTasks;
            boolean z3 = i >= size - options.numVisibleTaskThumbnails;
            if (!options.onlyLoadPausedActivities || !z) {
                if (options.loadIcons && ((z || z2) && task.icon == null)) {
                    task.icon = recentsTaskLoader.getAndUpdateActivityIcon(taskKey, task.taskDescription, true);
                }
                if (options.loadThumbnails && z3) {
                    task.thumbnail = recentsTaskLoader.getAndUpdateThumbnail(taskKey, true, true);
                }
            }
            i++;
        }
    }

    public TaskStack getTaskStack() {
        return this.mStack;
    }

    public boolean hasTasks() {
        TaskStack taskStack = this.mStack;
        return taskStack != null && taskStack.getTaskCount() > 0;
    }

    public void preloadPlan(PreloadOptions preloadOptions, RecentsTaskLoader recentsTaskLoader, int i, int i2) {
        boolean z;
        Drawable drawable;
        this.mContext.getResources();
        ArrayList arrayList = new ArrayList();
        if (this.mRawTasks == null) {
            List<ActivityManager.RecentTaskInfo> recentTasks = ActivityManagerWrapper.getInstance().getRecentTasks(ActivityManager.getMaxRecentTasksStatic(), i2);
            this.mRawTasks = recentTasks;
            Collections.reverse(recentTasks);
        }
        int size = this.mRawTasks.size();
        int i3 = 0;
        while (i3 < size) {
            ActivityManager.RecentTaskInfo recentTaskInfo = this.mRawTasks.get(i3);
            ComponentName componentName = recentTaskInfo.origActivity != null ? recentTaskInfo.origActivity : recentTaskInfo.realActivity;
            int windowingMode = recentTaskInfo.configuration.windowConfiguration.getWindowingMode();
            int i4 = i3;
            int i5 = size;
            Task.TaskKey taskKey = new Task.TaskKey(recentTaskInfo.persistentId, windowingMode, recentTaskInfo.baseIntent, componentName, recentTaskInfo.userId, recentTaskInfo.lastActiveTime);
            boolean z2 = !(Build.DEVICE.contains("NX627") && windowingMode == 5);
            boolean z3 = taskKey.id == i;
            ActivityInfo andUpdateActivityInfo = recentsTaskLoader.getAndUpdateActivityInfo(taskKey);
            if (andUpdateActivityInfo != null) {
                String andUpdateActivityTitle = preloadOptions.loadTitles ? recentsTaskLoader.getAndUpdateActivityTitle(taskKey, recentTaskInfo.taskDescription) : "";
                String andUpdateContentDescription = preloadOptions.loadTitles ? recentsTaskLoader.getAndUpdateContentDescription(taskKey, recentTaskInfo.taskDescription) : "";
                if (z2) {
                    z = false;
                    drawable = recentsTaskLoader.getAndUpdateActivityIcon(taskKey, recentTaskInfo.taskDescription, false);
                } else {
                    z = false;
                    drawable = null;
                }
                Drawable drawable2 = drawable;
                ThumbnailData andUpdateThumbnail = recentsTaskLoader.getAndUpdateThumbnail(taskKey, z, z);
                int activityPrimaryColor = recentsTaskLoader.getActivityPrimaryColor(recentTaskInfo.taskDescription);
                int activityBackgroundColor = recentsTaskLoader.getActivityBackgroundColor(recentTaskInfo.taskDescription);
                boolean z4 = (andUpdateActivityInfo == null || (andUpdateActivityInfo.applicationInfo.flags & 1) == 0) ? false : true;
                if (this.mTmpLockedUsers.indexOfKey(recentTaskInfo.userId) < 0) {
                    this.mTmpLockedUsers.put(recentTaskInfo.userId, this.mKeyguardManager.isDeviceLocked(recentTaskInfo.userId));
                }
                arrayList.add(new Task(taskKey, drawable2, andUpdateThumbnail, andUpdateActivityTitle, andUpdateContentDescription, activityPrimaryColor, activityBackgroundColor, z3, z2, z4, recentTaskInfo.supportsSplitScreenMultiWindow, recentTaskInfo.taskDescription, recentTaskInfo.resizeMode, recentTaskInfo.topActivity, this.mTmpLockedUsers.get(recentTaskInfo.userId)));
            }
            i3 = i4 + 1;
            size = i5;
        }
        TaskStack taskStack = new TaskStack();
        this.mStack = taskStack;
        taskStack.setTasks((List<Task>) arrayList, false);
    }
}
