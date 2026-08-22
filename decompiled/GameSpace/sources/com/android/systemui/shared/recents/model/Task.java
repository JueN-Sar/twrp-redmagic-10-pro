package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ViewDebug;
import com.android.systemui.shared.recents.utilities.Utilities;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes2.dex */
public class Task {
    public static final String TAG = "Task";

    @ViewDebug.ExportedProperty(category = "recents")
    public int colorBackground;

    @ViewDebug.ExportedProperty(category = "recents")
    public int colorPrimary;
    public Drawable icon;

    @ViewDebug.ExportedProperty(category = "recents")
    public int instanceId;

    @ViewDebug.ExportedProperty(category = "recents")
    public boolean isDockable;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isLaunchTarget;

    @ViewDebug.ExportedProperty(category = "recents")
    public boolean isLocked;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isSafetyCleanLocked;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isStackTask;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isSystemApp;

    @ViewDebug.ExportedProperty(deepExport = true, prefix = "key_")
    public TaskKey key;
    private ArrayList<TaskCallbacks> mCallbacks;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public int resizeMode;
    public ActivityManager.TaskDescription taskDescription;

    @Deprecated
    public int temporarySortIndexInStack;
    public ThumbnailData thumbnail;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public String title;

    @ViewDebug.ExportedProperty(category = "recents")
    public String titleDescription;

    @ViewDebug.ExportedProperty(category = "recents")
    public ComponentName topActivity;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean useLightOnPrimaryColor;

    @Deprecated
    public interface TaskCallbacks {
        void onTaskDataLoaded(Task task, ThumbnailData thumbnailData);

        void onTaskDataUnloaded();

        void onTaskWindowingModeChanged();
    }

    public static class TaskKey {

        @ViewDebug.ExportedProperty(category = "recents")
        public final Intent baseIntent;

        @ViewDebug.ExportedProperty(category = "recents")
        public final int displayId;

        @ViewDebug.ExportedProperty(category = "recents")
        public final int id;

        @ViewDebug.ExportedProperty(category = "recents")
        public long lastActiveTime;
        private int mHashCode;
        public final ComponentName sourceComponent;

        @ViewDebug.ExportedProperty(category = "recents")
        public final int userId;

        @ViewDebug.ExportedProperty(category = "recents")
        public int windowingMode;

        public TaskKey(int i, int i2, Intent intent, int i3, long j) {
            this(i, i2, intent, null, i3, j);
        }

        public TaskKey(int i, int i2, Intent intent, ComponentName componentName, int i3, long j) {
            this.id = i;
            this.windowingMode = i2;
            this.baseIntent = intent;
            this.sourceComponent = componentName;
            this.userId = i3;
            this.lastActiveTime = j;
            this.displayId = 0;
            updateHashCode();
        }

        public TaskKey(int i, int i2, Intent intent, ComponentName componentName, int i3, long j, int i4) {
            this.id = i;
            this.windowingMode = i2;
            this.baseIntent = intent;
            this.sourceComponent = componentName;
            this.userId = i3;
            this.lastActiveTime = j;
            this.displayId = i4;
            updateHashCode();
        }

        public TaskKey(ActivityManager.RecentTaskInfo recentTaskInfo) {
            ComponentName componentName = recentTaskInfo.origActivity != null ? recentTaskInfo.origActivity : recentTaskInfo.realActivity;
            this.id = recentTaskInfo.taskId;
            this.windowingMode = recentTaskInfo.configuration.windowConfiguration.getWindowingMode();
            this.baseIntent = recentTaskInfo.baseIntent;
            this.sourceComponent = componentName;
            this.userId = recentTaskInfo.userId;
            this.lastActiveTime = recentTaskInfo.lastActiveTime;
            this.displayId = recentTaskInfo.displayId;
            updateHashCode();
        }

        private void updateHashCode() {
            this.mHashCode = Objects.hash(Integer.valueOf(this.id), Integer.valueOf(this.windowingMode), Integer.valueOf(this.userId));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TaskKey)) {
                return false;
            }
            TaskKey taskKey = (TaskKey) obj;
            return this.id == taskKey.id && this.windowingMode == taskKey.windowingMode && this.userId == taskKey.userId;
        }

        public ComponentName getComponent() {
            return this.baseIntent.getComponent();
        }

        public String getPackageName() {
            return this.baseIntent.getComponent() != null ? this.baseIntent.getComponent().getPackageName() : this.baseIntent.getPackage();
        }

        public int hashCode() {
            return this.mHashCode;
        }

        public void setWindowingMode(int i) {
            this.windowingMode = i;
            updateHashCode();
        }

        public String toString() {
            return "id=" + this.id + " windowingMode=" + this.windowingMode + " user=" + this.userId + " lastActiveTime=" + this.lastActiveTime;
        }
    }

    public Task() {
        this.instanceId = -1;
        this.mCallbacks = new ArrayList<>();
    }

    public Task(TaskKey taskKey) {
        this.instanceId = -1;
        this.mCallbacks = new ArrayList<>();
        this.key = taskKey;
        this.taskDescription = new ActivityManager.TaskDescription();
    }

    public Task(TaskKey taskKey, int i, int i2, boolean z, boolean z2, ActivityManager.TaskDescription taskDescription, ComponentName componentName) {
        this.instanceId = -1;
        this.mCallbacks = new ArrayList<>();
        this.key = taskKey;
        this.colorPrimary = i;
        this.colorBackground = i2;
        this.taskDescription = taskDescription;
        this.isDockable = z;
        this.isLocked = z2;
        this.topActivity = componentName;
    }

    @Deprecated
    public Task(TaskKey taskKey, Drawable drawable, ThumbnailData thumbnailData, String str, String str2, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, ActivityManager.TaskDescription taskDescription, int i3, ComponentName componentName, boolean z5) {
        this.instanceId = -1;
        this.mCallbacks = new ArrayList<>();
        this.key = taskKey;
        this.icon = drawable;
        this.thumbnail = thumbnailData;
        this.title = str;
        this.titleDescription = str2;
        this.colorPrimary = i;
        this.colorBackground = i2;
        this.useLightOnPrimaryColor = Utilities.computeContrastBetweenColors(i, -1) > 3.0f;
        this.taskDescription = taskDescription;
        this.isLaunchTarget = z;
        this.isStackTask = z2;
        this.isSystemApp = z3;
        this.isDockable = z4;
        this.resizeMode = i3;
        this.topActivity = componentName;
        this.isLocked = z5;
    }

    public static Task from(TaskKey taskKey, TaskInfo taskInfo, boolean z) {
        ActivityManager.TaskDescription taskDescription = taskInfo.taskDescription;
        return new Task(taskKey, taskDescription != null ? taskDescription.getPrimaryColor() : 0, taskDescription != null ? taskDescription.getBackgroundColor() : 0, taskInfo.supportsSplitScreenMultiWindow, z, taskDescription, taskInfo.topActivity);
    }

    @Deprecated
    public void addCallback(TaskCallbacks taskCallbacks) {
        if (this.mCallbacks.contains(taskCallbacks)) {
            return;
        }
        this.mCallbacks.add(taskCallbacks);
    }

    @Deprecated
    public void copyFrom(Task task) {
        this.key = task.key;
        this.icon = task.icon;
        this.thumbnail = task.thumbnail;
        this.title = task.title;
        this.titleDescription = task.titleDescription;
        this.colorPrimary = task.colorPrimary;
        this.colorBackground = task.colorBackground;
        this.useLightOnPrimaryColor = task.useLightOnPrimaryColor;
        this.taskDescription = task.taskDescription;
        this.isLaunchTarget = task.isLaunchTarget;
        this.isStackTask = task.isStackTask;
        this.isSystemApp = task.isSystemApp;
        this.isDockable = task.isDockable;
        this.resizeMode = task.resizeMode;
        this.isLocked = task.isLocked;
        this.topActivity = task.topActivity;
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print(this.key);
        if (!this.isDockable) {
            printWriter.print(" dockable=N");
        }
        if (this.isLaunchTarget) {
            printWriter.print(" launchTarget=Y");
        }
        if (this.isLocked) {
            printWriter.print(" locked=Y");
        }
        printWriter.print(" ");
        printWriter.print(this.title);
        printWriter.println();
    }

    public boolean equals(Object obj) {
        return this.key.equals(((Task) obj).key);
    }

    public ComponentName getTopComponent() {
        ComponentName componentName = this.topActivity;
        return componentName != null ? componentName : this.key.baseIntent.getComponent();
    }

    @Deprecated
    public void notifyTaskDataLoaded(ThumbnailData thumbnailData, Drawable drawable) {
        this.icon = drawable;
        this.thumbnail = thumbnailData;
        int size = this.mCallbacks.size();
        for (int i = 0; i < size; i++) {
            this.mCallbacks.get(i).onTaskDataLoaded(this, thumbnailData);
        }
    }

    @Deprecated
    public void notifyTaskDataUnloaded(Drawable drawable) {
        this.icon = drawable;
        this.thumbnail = null;
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onTaskDataUnloaded();
        }
    }

    @Deprecated
    public void removeCallback(TaskCallbacks taskCallbacks) {
        this.mCallbacks.remove(taskCallbacks);
    }

    @Deprecated
    public void setWindowingMode(int i) {
        this.key.setWindowingMode(i);
        int size = this.mCallbacks.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mCallbacks.get(i2).onTaskWindowingModeChanged();
        }
    }

    public String toString() {
        return "[" + this.key.toString() + "] " + this.title;
    }
}
