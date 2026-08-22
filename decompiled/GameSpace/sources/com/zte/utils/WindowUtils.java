package com.zte.utils;

import android.app.ActivityThread;
import android.os.Bundle;
import com.zte.activityevent.ActivityEventsManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WindowUtils {

    public static class VisibleActivity {
        static final String ACTIVITY_TYPE = "activityType";
        static final String DISPLAY_ID = "displayId";
        static final String PACKAGE_NAME = "packageName";
        static final String RESERVED = "reserved";
        static final String ROOT_TASK_ID = "rootTaskId";
        static final String TOP_ACTIVITY = "topActivity";
        static final String WINDOW_MODE = "windowMode";
        static final String WINDOW_TITLE = "windowTitle";
        public final int mActivityType;
        public final int mDisplayId;
        public final String mPackageName;
        public final int mRootTaskId;
        public final String mTopActivity;
        public final String mUid;
        public final int mWindowMode;
        public final String mWindowTitle;

        public VisibleActivity(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4) {
            this.mPackageName = str;
            this.mTopActivity = str2;
            this.mWindowTitle = str3;
            this.mUid = str4;
            this.mWindowMode = i;
            this.mActivityType = i2;
            this.mRootTaskId = i3;
            this.mDisplayId = i4;
        }

        public String toString() {
            return "Task{mPackageName='" + this.mPackageName + "', mTopActivity='" + this.mTopActivity + "', mUid='" + this.mUid + "', mWindowMode=" + this.mWindowMode + ", mActivityType=" + this.mActivityType + ", mRootTaskId=" + this.mRootTaskId + ", mDisplayId=" + this.mDisplayId + '}';
        }
    }

    public static List<VisibleActivity> getVisibleActivity() {
        ArrayList arrayList = new ArrayList();
        ActivityEventsManager activityEventsManager = ActivityEventsManager.getInstance(ActivityThread.currentApplication());
        if (activityEventsManager != null) {
            try {
                List visiblePackageDates = activityEventsManager.getVisiblePackageDates();
                if (visiblePackageDates != null && visiblePackageDates.size() > 0) {
                    for (int size = visiblePackageDates.size() - 1; size >= 0; size--) {
                        Bundle bundle = (Bundle) visiblePackageDates.get(size);
                        if (bundle != null && bundle.containsKey("packageName") && bundle.containsKey("windowMode")) {
                            arrayList.add(new VisibleActivity(bundle.getString("packageName"), bundle.getString("topActivity"), bundle.getString("windowTitle"), bundle.getString("reserved"), bundle.getInt("windowMode"), bundle.getInt("activityType"), bundle.getInt("rootTaskId"), bundle.getInt("displayId")));
                        } else {
                            arrayList.add(null);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static boolean isInFreeformMode() {
        List visiblePackageDates = ActivityEventsManager.getInstance(ActivityThread.currentApplication()).getVisiblePackageDates();
        return (visiblePackageDates == null || visiblePackageDates.size() <= 1 || visiblePackageDates.get(1) == null) ? false : true;
    }
}
