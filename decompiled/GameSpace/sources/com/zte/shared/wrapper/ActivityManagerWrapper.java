package com.zte.shared.wrapper;

import android.app.Activity;
import android.app.ActivityClient;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.AppGlobals;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ActivityManagerWrapper {
    private static final int NUM_RECENT_ACTIVITIES_REQUEST = 3;
    private static final String TAG = "ActivityManagerWrapper";
    private static final ActivityManagerWrapper sInstance = new ActivityManagerWrapper();
    private final ActivityTaskManager mAtm = ActivityTaskManager.getInstance();
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final PackageManager mPackageManager = AppGlobals.getInitialApplication().getPackageManager();

    private ActivityManagerWrapper() {
    }

    public static boolean checkTaskSupportWr(int i) {
        return ActivityClient.getInstance().checkTaskSupportWr(i);
    }

    public static boolean checkTaskSupportWr(ComponentName componentName) {
        return ActivityClient.getInstance().checkTaskSupportForCompWr(componentName);
    }

    public static boolean checkTaskSupportWr(String str) {
        return ActivityClient.getInstance().checkTaskSupportForPkgWr(str);
    }

    private String getBadgedLabel(String str, int i) {
        return i != UserHandle.myUserId() ? this.mPackageManager.getUserBadgedLabel(str, new UserHandle(i)).toString() : str;
    }

    public static int getCurrentUser() {
        return ActivityManager.getCurrentUser();
    }

    public static Rect getDefaultWindowParamByTaskForHangWr(int i, int i2) {
        return ActivityClient.getInstance().getDefaultWindowParamByTaskForHangWr(i, i2);
    }

    public static Rect getDefaultWindowParamByTaskForNormalWr(int i) {
        return ActivityClient.getInstance().getDefaultWindowParamByTaskForNormalWr(i);
    }

    public static Rect getDefaultWindowParamForNormalWr(boolean z) {
        return ActivityClient.getInstance().getDefaultWindowParamForNormalWr(z);
    }

    public static ActivityManagerWrapper getInstance() {
        return sInstance;
    }

    public static String getTopActivityTypeInDefaultDisplay() {
        try {
            return ActivityTaskManager.getService().getTopActivityTypeInDefaultDisplay();
        } catch (Exception e) {
            Log.e(TAG, "error=" + e);
            return null;
        }
    }

    public static UserHandle getUserHandle(Context context, int i) {
        int i2;
        UserManager userManager = (UserManager) context.getSystemService("user");
        Iterator it = userManager.getProfiles(userManager.getUserHandle()).iterator();
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            UserInfo userInfo = (UserInfo) it.next();
            if (userInfo.isManagedProfile() && userInfo.id == i) {
                i2 = userInfo.id;
                break;
            }
        }
        UserHandle userHandle = i2 != -1 ? new UserHandle(i2) : null;
        Log.i("SysShared", "getDoubleAppsProfile: userHandle" + i2);
        return userHandle;
    }

    public static boolean isHomeTask(ActivityManager.RunningTaskInfo runningTaskInfo) {
        return runningTaskInfo.configuration.windowConfiguration.getActivityType() == 2;
    }

    public static void startPipActivity(Intent intent, Context context) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        context.startActivity(intent, makeBasic.toBundle());
    }

    public static void startPipActivityAsUser(Intent intent, Context context, UserHandle userHandle) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        context.startActivityAsUser(intent, makeBasic.toBundle(), userHandle);
    }

    public static void startWindowFreeForm(Intent intent, Context context, int i, int i2) {
        try {
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setLaunchWindowingMode(5);
            if (context.getDisplayId() > 0) {
                Log.i(TAG, "startWindowFreeForm: DisplayId=" + context.getDisplayId());
                makeBasic.setLaunchDisplayId(context.getDisplayId());
            }
            Bundle bundle = makeBasic.toBundle();
            if (bundle == null) {
                Log.d(TAG, "startWindowFreeForm null");
                return;
            }
            bundle.putBoolean("WindowReply", true);
            bundle.putInt("Start_WindowReply_Mode", i2);
            Log.d(TAG, "startWindowFreeForm intent=" + intent + " startMode =" + i2);
            context.startActivityAsUser(intent, bundle, UserHandle.of(i));
        } catch (Exception e) {
            Log.e(TAG, "startWindowFreeForm e2=" + e);
            e.printStackTrace();
        }
    }

    public static void startWindowFreeFormWithPendingIntent(PendingIntent pendingIntent, Context context, int i) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        if (context.getDisplayId() > 0) {
            Log.i(TAG, "startWindowFreeFormWithPendingIntent: DisplayId=" + context.getDisplayId());
            makeBasic.setLaunchDisplayId(context.getDisplayId());
        }
        Bundle bundle = makeBasic.toBundle();
        if (bundle != null) {
            bundle.putBoolean("WindowReply", true);
            bundle.putInt("Start_WindowReply_Mode", i);
        }
        try {
            pendingIntent.send(null, 0, null, null, null, null, bundle);
        } catch (Exception e) {
            Log.e(TAG, "Sending intent failed: " + e);
            e.printStackTrace();
        }
    }

    public static boolean toggleSwitchFromFreeformWrtoFullScreen(int i) {
        return ActivityClient.getInstance().toggleSwitchFromFreeformWrtoFullScreen(i);
    }

    public static boolean toggleSwitchFromFullScreenToFreeformWr(int i, int i2) {
        return ActivityClient.getInstance().toggleSwitchFromFullScreenToFreeformWr(i, i2);
    }

    public static boolean toggleSwitchHangtoNormalWr(int i) {
        return ActivityClient.getInstance().toggleSwitchHangtoNormalWr(i);
    }

    public static boolean toggleSwitchNormaltoHangWr(int i) {
        return ActivityClient.getInstance().toggleSwitchNormaltoHangWr(i);
    }

    public void closeSystemWindows(String str) {
        try {
            ActivityManager.getService().closeSystemDialogs(str);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to close system windows", e);
        }
    }

    public String getBadgedActivityLabel(ActivityInfo activityInfo, int i) {
        return getBadgedLabel(activityInfo.loadLabel(this.mPackageManager).toString(), i);
    }

    public String getBadgedApplicationLabel(ApplicationInfo applicationInfo, int i) {
        return getBadgedLabel(applicationInfo.loadLabel(this.mPackageManager).toString(), i);
    }

    public String getBadgedContentDescription(ActivityInfo activityInfo, int i, ActivityManager.TaskDescription taskDescription) {
        String obj = (taskDescription == null || taskDescription.getLabel() == null) ? activityInfo.loadLabel(this.mPackageManager).toString() : taskDescription.getLabel();
        String obj2 = activityInfo.applicationInfo.loadLabel(this.mPackageManager).toString();
        String badgedLabel = getBadgedLabel(obj2, i);
        return obj2.equals(obj) ? badgedLabel : badgedLabel + " " + obj;
    }

    public int getCurrentUserId() {
        try {
            UserInfo currentUser = ActivityManager.getService().getCurrentUser();
            if (currentUser != null) {
                return currentUser.id;
            }
            return 0;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final Rect getDefaultWindowParamForHangWr(boolean z, int i) {
        return ActivityClient.getInstance().getDefaultWindowParamForHangWr(z, i);
    }

    public List<ActivityManager.RecentTaskInfo> getRecentTasks(int i, int i2) {
        return this.mAtm.getRecentTasks(i, 2, i2);
    }

    public ActivityManager.RunningTaskInfo getRunningTask() {
        return getRunningTask(false);
    }

    public ActivityManager.RunningTaskInfo getRunningTask(boolean z) {
        List tasks = this.mAtm.getTasks(1, z);
        if (tasks.isEmpty()) {
            return null;
        }
        return (ActivityManager.RunningTaskInfo) tasks.get(0);
    }

    public ActivityManager.RunningTaskInfo[] getRunningTasks(boolean z) {
        List tasks = this.mAtm.getTasks(3, z);
        return (ActivityManager.RunningTaskInfo[]) tasks.toArray(new ActivityManager.RunningTaskInfo[tasks.size()]);
    }

    public void invalidateHomeTaskSnapshot(Activity activity) {
        try {
            ActivityClient.getInstance().invalidateHomeTaskSnapshot(activity == null ? null : activity.getActivityToken());
        } catch (Throwable th) {
            Log.w(TAG, "Failed to invalidate home snapshot", th);
        }
    }

    public boolean isLockToAppActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() != 0;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isScreenPinningActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() == 2;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isScreenPinningEnabled() {
        return Settings.System.getInt(AppGlobals.getInitialApplication().getContentResolver(), "lock_to_app_enabled", 0) != 0;
    }

    public void removeAllRecentTasks() {
        try {
            ActivityTaskManager.getService().removeAllVisibleRecentTasks();
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to remove all tasks", e);
        }
    }

    public void removeTask(int i) {
        try {
            ActivityTaskManager.getService().removeTask(i);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to remove task=" + i, e);
        }
    }

    public boolean startActivityFromRecents(int i, ActivityOptions activityOptions) {
        Bundle bundle;
        if (activityOptions == null) {
            bundle = null;
        } else {
            try {
                bundle = activityOptions.toBundle();
            } catch (Exception e) {
                Log.e(TAG, "startActivityFromRecent error=" + e);
                return false;
            }
        }
        ActivityTaskManager.getService().startActivityFromRecents(i, bundle);
        return true;
    }

    public boolean supportsFreeformMultiWindow(Context context) {
        boolean z = Settings.Global.getInt(context.getContentResolver(), "enable_freeform_support", 0) != 0;
        if (ActivityTaskManager.supportsMultiWindow(context)) {
            return context.getPackageManager().hasSystemFeature("android.software.freeform_window_management") || z;
        }
        return false;
    }
}
